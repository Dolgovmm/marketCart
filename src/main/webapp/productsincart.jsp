<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.price}" /></td>
                    <td><c:out value="${product.available}" /></td>
                    <td><a href="ProductController?action=productId&productId=<c:out value="${product.id}"/>">more</a></td>
                    <td><a href="ProductController?action=addProduct&productId=<c:out value="${product.id}"/>">add to cart</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="ProductController?action=productFromCart">Show cart</a></p>
</body>
</html>