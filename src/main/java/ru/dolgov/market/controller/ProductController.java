package ru.dolgov.market.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.storage.Storage;
import ru.dolgov.market.storage.StorageImpl;

public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private final String PRODUCTS_LIST = "/productslist.jsp";
	private final String PRODUCT = "/product.jsp";
	private final String ORDER = "/order.jsp";
	private final String CONFIRM = "/confirm.jsp";
	
	private Storage storage;

	public ProductController() {
		storage = new StorageImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String forward = "";
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("productsList")) {
			forward = PRODUCTS_LIST;
			try {
				request.setAttribute("products", storage.getAllProducts());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (action.equalsIgnoreCase("productId")) {
			forward = PRODUCT;
			String productId = request.getParameter("productId");
			Product product;
			try {
				product = storage.getProductById(productId);
				request.setAttribute("product", product);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
		if (action.equalsIgnoreCase("productFromCart")) {
			forward = PRODUCTS_LIST;

			String cartId = getCartId(request, response);
			
			request.setAttribute("products", storage.getProductsFromCart(cartId));
		}
		
		if (action.equalsIgnoreCase("removeProduct")) {
			forward = PRODUCTS_LIST;
			
			String cartId = getCartId(request, response);
			
			String productId = request.getParameter("productId");
			
			try {
				storage.removeProductFromCart(cartId, productId);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (action.equalsIgnoreCase("order")) {
			forward = ORDER;
		}
		
		if (request.getSession().isNew()) {
			storage.createNewCart(request.getSession().getId());
			Cookie cookie = new Cookie("cartId", request.getSession().getId());
			response.addCookie(cookie);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,  response);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("confirmOrder")) {
			forward = CONFIRM;
			Client client = new Client();
			client.setName((String) request.getAttribute("name"));
			client.setEmail((String) request.getAttribute("email"));
			client.setPhoneNumber((String) request.getAttribute("phoneNumber"));
			
			String cartId = getCartId(request, response);
			
			storage.addClientToCart(cartId, client);
			
			try {
				storage.saveCart(cartId);
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}		
		
		if (action.equalsIgnoreCase("addProduct")) {
			forward = PRODUCTS_LIST;
			String cartId = getCartId(request, response);
			String productId = (String) request.getAttribute("productId");
			String productQuantity = (String) request.getAttribute("quantity");
			try {
				storage.addProductToCart(cartId, productId, productQuantity);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,  response);
	}
	
	private String getCartId(HttpServletRequest request, HttpServletResponse response) {
		String cartId = "";
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("cartId")) {
				cartId = cookies[i].getValue();
			}
		}
		if (cartId.isEmpty()) {
			cartId = request.getSession().getId();
			storage.createNewCart(request.getSession().getId());
			Cookie cookie = new Cookie("cartId", request.getSession().getId());
			response.addCookie(cookie);
		}
		return cartId;
	}
}
