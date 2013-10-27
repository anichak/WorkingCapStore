<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
<script type="text/javascript">

window.history.forward();
function noBack() { window.history.forward(); }
	
 function status()
{
	
	var chklist=document.getElementsByName("rad_group");
	
for( var i=0;i<chklist.length;i++){
	if(chklist[i].checked){
		document.getElementById("hidd").value=chklist[i].id;
		x=document.getElementById(document.getElementById("hidd").value);  // Find the element
		x.innerHTML="PENDING";  
	}
}
	
  // Change the content
} 

function setVal(temp){
	
	/* alert(temp.value); */
	document.getElementById("prodid").value=temp.value;
	
}



function validate()
{
  var retval = true;
  for (var i=0; i < document.formmessage.r.length; i++)
  {
    if (document.formmessage.r[i].checked)
    {
      retval = true;
    }
    else{
    	retval=false;
    }
  }  

  return retval;
}
	
</script>
</head>

<body onload="noBack();">


<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>


<form name="formmessage" action="customerreturn" method="post" onsubmit="return validate()" >
  
  
  <table align=center border='2' cellpadding='10' cellspacing='5'>
    
    <tr>
    <th>OrderDetails
      <th>Order ID
      <th>Product
      <th>Cost
      <th>Shipping Status
      <th>Delivery Date
      <th>Return Status
      <th>
      
    </tr>
   
    <c:forEach items="${list}" var="id">
    <tr>
    <td><a href="view_detail?order_id=${id.orderId}">Get Details</a></td>
      <td><c:out value="${id.orderId}"/>
      <td><c:out value="${id.productName}"/>
      <td><c:out value="${id.productCost}"/>
      <td><c:out value="${id.shippingStatus}"/>
      <td><c:out value="${id.deliveryDate}"/>
      <td id="${id.orderId}"><c:out value="${id.returnStatus}"/>
       <fmt:formatDate pattern="yyyy-MM-dd" value="${id.deliveryDate}" type="date" var="datedb" />   <!-- sql -->
      
       <fmt:parseDate pattern="yyyy-MM-dd" value="${datedb}" var="datedb1" type="date" />   <!-- util -->
      <c:if test="${fn:contains(id.shippingStatus, 'Reached')}">
       <c:set var="datedb2" scope="page" value="${datedb1.getTime()}"/>
       <c:set var="now2" scope="page" value="${now.getTime()}"/>
      <c:if test="${now2 - datedb2 < 30*24*3600*1000}"> 
      <c:if test="${fn:contains(id.returnStatus, 'None') || fn:contains(id.returnStatus,'Rejected')}">
       <td><input type="radio" name="rad_group" id="r" value="${id.orderId},${id.userId},${id.productId},${id.productName},${id.productCost},${id.transactionId},${id.productQuantity},${id.returnStatus},${id.returnstatusId}" onclick="setVal(this)">
      </c:if>
      </c:if>
       </c:if> 
       
    </tr>
   </c:forEach>
   
  </table>


<input type="hidden"  id="prodid" name="prodid">

<br/><br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


<input type="submit" id="customerreturn" name="customerreturn" value="RETURN" >
<input type="hidden" name="hidd" id="hidd"/>
</form>
	




</body>
</html>