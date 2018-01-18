<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Products In Cart</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Available</th>
                <th>Quantity</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cartItems}" var="cartItem">
                <tr>
                    <td><c:out value="${cartItem.product.name}" /></td>
                    <td><c:out value="${cartItem.product.price}" /></td>
                    <td><c:out value="${cartItem.product.available}" /></td>
                    <td><c:out value="${cartItem.quantity}" /></td>
                    <td><a href="products?action=productId&productId=<c:out value="${cartItem.product.id}"/>">more</a></td>
					<td><form method="POST" action='updateProduct' name="frmUpdateProduct">
			            
			            <input type="text" name="quantity" value="<c:out value="${cartItem.quantity}" />" /> 
			            <input  type="submit" value="Submit" />
			        </form></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="products?action=order">Create order</a></p>
    <p><a href="products?action=productsList">Back to products list</a></p>
</body>
</html>