<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



           <div align="center">  
           <h1>order Details</h1>
           <textarea style="overflow:auto;resize:none;font-size: 18"  rows="13" cols="40" readonly="readonly" >   
       Order_Id   :  <c:out value="${trans_history.orders.getOrderId()}"></c:out>
       CartValue  :  <c:out value="${trans_history.orders.getCartValue()}"></c:out>
       OrderDate  :  <c:out value="${trans_history.orders.getOrderDate()}"></c:out>
       OrderStatus:  <c:out value="${trans_history.orders.getOrderStatus()}"></c:out>
       </textarea>
  
       <h1>shipping detail</h1>
       <table>
     
      <c:forEach items="${trans_history.shipping}" var="shipping" varStatus="status">
       <tr><td> track id		 </td><td>:</td><td><c:out value="${shipping.order.getOrderId()}"></c:out></td></tr>
       <tr><td>shipping address	 </td><td>:</td><td> <c:out value="${shipping.getShippingAddress()}"></c:out></td></tr>
       <tr><td>ship date		 </td><td>:</td><td><c:out value="${shipping.getShippingDate()}"></c:out></td></tr>
       <tr><td>ship status		 </td><td>:</td><td> <c:out value="${shipping.getShippingStatus()}"></c:out></td></tr>
       <tr><td>Delivery Date		 </td><td>:</td><td> <c:out value="${shipping.getDeliveryDate()}"></c:out></td></tr>
        </c:forEach>
    </table>
     
     </div>
           


       
   <table border="1" align="center">
     <thead>  <tr><th>item_name</th>
<th>price</th>
<th>quantity</th>
</tr>

</thead>
  
      <c:forEach items="${trans_history.transactions}" var="product" varStatus="status">
        <tr>
         <td><c:out value="${product.getProductName()}"></c:out></td>
         <td><c:out value="${product.getProductCost()}"></c:out></td>
          <td><c:out value="${product.getProductQuantity()}"></c:out></td>
        </tr>
        </c:forEach>
       
  </table>
 
  <h1><a href="buyhistory?">BACK</a></h1>
</body>
</html>