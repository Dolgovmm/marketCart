<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client info</title>
    </head>
    <body>
        <form method="POST" action='ProductController' name="frmAddClient">
            Client Name : <input type="text" name="name"
                               value="<c:out value="${client.uname}" />" /> <br /> 
            Client email : <input type="text" name="email"
                               value="<c:out value="${client.email}" />" /> <br />
            Client phone number : <input type="text" name="phoneNumber"
                				value="<c:out value="${client.phoneNumber}" />" /> <br /> 
            <input  type="submit" value="Submit" />
        </form>
    </body>
</html>