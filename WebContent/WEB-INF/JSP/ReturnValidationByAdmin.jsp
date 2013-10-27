<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Validation</title>
<script type="text/javascript">

window.history.forward();
function noBack() { window.history.forward(); }

function onApprove(tmp){
	
	/* alert(tmp.name); */
	document.getElementById("hidd").value=tmp.name;
	
	document.getElementById("details").action="adminapprove";
	document.getElementById("details").submit();
	
}

function onReject(tmp){
	
	/* alert(tmp.name); */
	document.getElementById("hidd1").value=tmp.name;
	
	document.getElementById("details").action="adminreject";
	document.getElementById("details").submit();
	
}
</script>
</head>

<body onload="noBack();">


<form action="" method="GET" id="details">
  <table align=center border='2' cellpadding='10' cellspacing='5'>
    
    <tr>
      <th>Serial
      <th>Order ID
      <th>Transaction ID
      <th>User Id
      <th>Product Id
      <th>Quantity
      <th>Product Name
      <th>Product Cost
      <th>Status
      <th>Status Id
      <th colspan="2">Option</th>
      
      
    </tr>
    <c:forEach items="${list1}" var="id">
    <tr>
    
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.returnId}"/> name="returnId" size="3" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.orderId}"/> name="orderId" size="3" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.transactionId}"/> name="transactionId" size="6" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.userId}"/> name="userId" size="3" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" style="text-align: center;" value=<c:out value="${id.productId}"/> name="productId" size="3" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.productQuantity}"/> name="productQuantity" size="3" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.productName}"/> name="productName" size="5" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.productCost}"/> name="productCost" size="6" readonly/>
      <td align="center" ><input type="text" style="text-align: center;" value=<c:out value="${id.returnStatus}"/> name="returnStatus" size="6" readonly/>
      <td id="${id.returnstatusId}"><input type="text" style="text-align: center;" value=<c:out value="${id.returnstatusId}"/> name="returnstatusId" size="3" readonly/>
      
     
      <c:if test="${fn:contains(id.returnStatus, 'Pending')}">
      <td><input type="button" id="${id.returnstatusId}" name="${id.returnstatusId}" onClick="onApprove(this)" value="Approve"></td>
      </c:if>
      
      <c:if test="${fn:contains(id.returnStatus, 'Pending')}">
      <td><input type="button" id="${id.returnstatusId}" name="${id.returnstatusId}" onClick="onReject(this)" value="Reject"></td>
      </c:if>
     
      
    </tr>
   </c:forEach>
  </table>
<input type="hidden" name="hidd" id="hidd"/>
<input type="hidden" name="hidd1" id="hidd1"/>

</form>
	

</body>
</html>