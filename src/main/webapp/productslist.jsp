<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Products</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Available</th>
				<th>Link to description</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.price}" /></td>
                    <td><c:out value="${product.available}" /></td>
                    <td><a href="products?action=productId&productId=<c:out value="${product.id}"/>">description</a></td>
					<td><form method="POST" action="products?action=addProduct&productId=<c:out value="${product.id}"/>" 
							name="frmAddProduct">
			            <input  type="submit" value="Add" />
			        </form></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="products?action=productFromCart">Show cart</a></p>
</body>
</html>