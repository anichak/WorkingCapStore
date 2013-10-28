<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comparison Page</title>
</head>
<body>
You are on Comparison Page
<table border=1>
<tr>
<c:forEach items="${productDescription}" var="comparisonproduct">
		<th><img src="${comparisonproduct.image}" width=250 height=100/></th>
</c:forEach>
</tr>
</table>


<c:forEach items="${productDescription}" var="comparisonproduct">
<table border=1 style="float:left" width="250">
<c:forEach items="${comparisonproduct.proddescription}" var="desc">
<tr>
	<th scope="row"><c:out value="${desc.attributeName}"></c:out></th>
	<td><c:out value="${desc.attributeValue}"></c:out></td>
</tr>
</c:forEach>
</table>
</c:forEach>
</body>
</html>