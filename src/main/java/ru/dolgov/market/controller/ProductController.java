package ru.dolgov.market.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.storage.Storage;
import ru.dolgov.market.storage.StorageImpl;

public class ProductController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private final String PRODUCTS_LIST = "/productslist.jsp";
	private final String PRODUCTS_IN_CART = "/productsincart.jsp";
	private final String PRODUCT = "/product.jsp";
	private final String EMPTY_CART = "/emptycart.jsp";
	private final String ORDER = "/order.jsp";
	private final String CONFIRM = "/confirm.jsp";
	
	private Storage storage;

	public ProductController() {
		super();
		try {
			storage = new StorageImpl();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				e.printStackTrace();
			}
		}
				
		if (action.equalsIgnoreCase("productFromCart")) {
			forward = PRODUCTS_IN_CART;

			String cartId = getCartId(request.getSession(true));
			request.setAttribute("cartItems", storage.getProductsFromCart(cartId));
		}
		
		if (action.equalsIgnoreCase("order")) {
			String cartId = getCartId(request.getSession(true));
			if (!storage.getProductsFromCart(cartId).isEmpty()) {
				forward = ORDER;
			} else {
				forward = EMPTY_CART;
			}	
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,  response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		
		if (action.equalsIgnoreCase("addProduct")) {
			forward = PRODUCTS_LIST;
			String cartId = getCartId(request.getSession(true));
			String productId = (String) request.getParameter("productId");
	
			try {
				storage.addProductToCart(cartId, productId);
				request.setAttribute("products", storage.getAllProducts());
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (action.equalsIgnoreCase("confirmOrder")) {
			forward = CONFIRM;
			Client client = new Client();
			client.setName((String) request.getParameter("name"));
			client.setEmail((String) request.getParameter("email"));
			client.setPhoneNumber((String) request.getParameter("phoneNumber"));
			
			String cartId = getCartId(request.getSession(true));
			
			storage.addClientToCart(cartId, client);
			
			try {
				storage.saveCart(cartId);
			}catch (SQLException ex) {
				ex.printStackTrace();
			}

		}		
		
		if (action.equalsIgnoreCase("updateProduct")) {
			forward = PRODUCTS_IN_CART;
			String quantity = request.getParameter("quantity");
			String cartId = getCartId(request.getSession(true));
			String productId = (String) request.getParameter("productId");
			try {
				storage.updateProductFromCart(cartId, productId, quantity);
				request.setAttribute("cartItems", storage.getProductsFromCart(cartId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (action.equalsIgnoreCase("removeProduct")) {
			forward = PRODUCTS_IN_CART;
			
			String cartId = getCartId(request.getSession(true));
			String productId = request.getParameter("productId");
			
			try {
				storage.updateProductFromCart(cartId, productId, "0");
				request.setAttribute("cartItems", storage.getProductsFromCart(cartId));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,  response);
	}
	
	private String getCartId(HttpSession session) {
		String cartId;
		cartId = (String)session.getAttribute("cartId");
		if (cartId == null) {
		    cartId = session.getId();
		    session.setAttribute("cartId", cartId);
		}   
		if (!storage.isCartExist(cartId)) {
			storage.createNewCart(cartId);
		}
		
		return cartId;
	}
}
