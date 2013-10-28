<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Product Description</title>
<link type="text/css" href="resources/Css/style.css" rel="stylesheet" />
<link type="text/css" href="resources/Css/login.css" rel="stylesheet" />
<!-- <script>src="resources/Script/jquery.slides.min.js"></script>  -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="resources/Script/vishu.js"></script>
<script src="resources/Script/login.js"></script>
<script src="resources/Script/scripts.js"></script>
<!-- <script type="text/javascript" src="resources/Script/jquery.sudoSlider.min.js"></script> -->
<link rel="stylesheet" href="resources/Css/stylemayur.css"
	type="text/css"></link>

<!-- Mayur's Script -->
<script type="text/javascript">
	/*   $(document).ready(function(){	
	      var sudoSlider = $("#slider").sudoSlider({
	          slideCount:"${imageListSize}",
	          continuous:true,
			controlsShow:true
	      });
	  }); */
	function compareEnable() {
		var hasSelected = false;
		var FormObj = document.compareForm;
		var chklen = FormObj.elements.length;
		for ( var n = 0; n < chklen; n++) {
			currobj = FormObj.elements[n];
			if (currobj.type == "checkbox" && currobj.checked) {
				hasSelected = true;
			}
		}
		if (hasSelected) {
			document.getElementById("Submit").disabled = false;
		} else {
			document.getElementById("Submit").disabled = true;
		}
	}
</script>
<style>
#slider li {
	width: 232px;
}

.nextBtn,.prevBtn {
	top: 0px;
}
</style>




<!-- praveen scrippt -->
<script>
	$(document)
			.ready(
					function() {
						$("input[name='r1']")
								.change(
										function() {

											var qty = $("#qty").val();
											var result = $("#result").val();
											if (qty == "") {
												alert("please select a quantity");
												return;
											}
											var numbers = /^[0-9]+$/;
											if (qty.match(numbers)) {
											} else {
												alert("Enter valid phone no");
											}

											var status = '';

											$
													.ajax({

														type : "Post",

														url : "checkQty",

														data : "qty="
																+ escape(qty)
																+ "&result="
																+ escape(result),

														success : function(data) {
															status = data;

															if (data == "false") {
																alert("Selected quantity cannot be availed for selected scheme");
																$("#qty").val(
																		'');
																$('#qty')
																		.focus();

															} else {

															}
														},

														error : function(e) {
															alert('Error: ' + e);
														}
													});
										});
						$("input[name='qty']")
								.blur(
										function() {
											var qty = $("#qty").val();

											if (qty == "") {
												alert("please select a quantity");
												return;
											}
											var numbers = /^[0-9]+$/;
											if (qty.match(numbers)) {
											} else {
												alert("Enter valid phone no");
											}
											var status = '';

											$
													.ajax({

														type : "Post",

														url : "checkQtywithnoscheme",

														data : "qty="
																+ escape(qty),

														success : function(data) {
															status = data;

															if (data == "false") {
																alert("Selected quantity cannot be availed");
																$("#qty").val(
																		'');
																$('#qty')
																		.focus();

															} else {

															}

														},

														error : function(e) {

															alert('Error: ' + e);

														}

													});

										});

					});
	function displayResult(r1) {
		document.getElementById("result").value = r1;
	}
</script>
<script>
	//****************************************MRINAL SCRIPT**********************************************

	$(document)
			.ready(
					function() {
						//Stops the submit request=
						$(document).on('click', '.usercheck', function(e) {
							document.getElementById("hidd").value = this.value;
							document.getElementById("content").value = "";

						});

						$("#post_feedback")
								.click(
										function(e) {
											var rateString = document
													.getElementById("hrate").value;
											alert(rateString);
											var feedString = document
													.getElementById("content").value;
											if (document
													.getElementById("content").value.length == 0) {
												alert("Enter Feedback COntent");
											} else {
												$
														.ajax({
															type : "GET",
															url : "postfeedback",
															data : {
																"rateString" : rateString,
																"feedString" : feedString
															},
															dataType : 'jsonp',
															jsonp : 'callback',
															jsonpCallback : 'jsonpCallback',

															//if received a response from the server
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																//our country code was correct so we have some information to display
																$(
																		"#ajaxResponse")
																		.html(
																				"<div><b>Country code in Invalid!</b></div>");
																document
																		.getElementById("content").value = "";
															},
															//If there was no resonse from the server
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																console
																		.log("Something really bad happened "
																				+ textStatus);
																$(
																		"#ajaxResponse")
																		.html(
																				jqXHR.responseText);
																document
																		.getElementById("content").value = "";
															},
															//capture the request before it was sent to server
															beforeSend : function(
																	jqXHR,
																	settings) {
																//disable the button until we get the response
																$('#myButton')
																		.attr(
																				"disabled",
																				true);
																document
																		.getElementById("content").value = "";
															},
															//this is called after the response or error functions are finsihed
															//so that we can take some action
															complete : function(
																	jqXHR,
																	textStatus) {
																//enable the button 
																$('#myButton')
																		.attr(
																				"disabled",
																				false);
															}
														});
											}
											;
										});

						$(".link1").click(function(event) {
							event.preventDefault();
							var url = $(this).attr("href");
							$('#right-pane').load(url);
						});
						$("#deleteme")
								.click(
										function(e) {
											var dataString = hidd.value;
											alert(dataString);
											if (document.getElementById("hidd").value.length == 0) {
												alert("Select Data To delete");
											} else {
												$
														.ajax({
															type : "GET",
															url : "deletefeedback",
															data : {
																"dataString" : dataString
															},
															dataType : 'jsonp',
															jsonp : 'callback',
															jsonpCallback : 'jsonpCallback',
															//If there was no resonse from the server
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$(
																		"#ajaxResponse")
																		.html(
																				jqXHR.responseText);
																document
																		.getElementById("hidd").value = "";
															},
														});
											}
										});
						$("#show_feedback").click(
								function(e) {
									$.ajax({
										type : "GET",
										url : "view1feedback",
										data : {
											"dataString" : "2"
										},
										dataType : 'jsonp',
										jsonp : 'callback',
										jsonpCallback : 'jsonpCallback',

										error : function(jqXHR, textStatus,
												errorThrown) {
											$("#ajaxResponse").html(
													jqXHR.responseText);
										},
									});
								});
					});
</script>
<script type="text/javascript">
	//**************************************VISWANATH WORK**********************************************
	$(document).ready(
			function() {
				//make the AJAX request, dataType is set to json
				//meaning we are expecting JSON data in response from the server
				$.ajax({
					type : "GET",
					url : "getSuggestions",
					dataType : 'jsonp',
					jsonp : 'callback',
					jsonpCallback : 'jsonpCallback',

					//if received a response from the server

					success : function(data, textStatus, jqXHR) {

						//our country code was correct so we have some information to display

						$("#ajaxResponse2").html(
								"<div><b>Country code in Invalid!</b></div>");

					},

					//If there was no resonse from the server

					error : function(jqXHR, textStatus, errorThrown) {

						console.log("Something really bad happened "
								+ textStatus);

						$("#ajaxResponse2").html(jqXHR.responseText);

					},

					//capture the request before it was sent to server

					beforeSend : function(jqXHR, settings) {

						//disable the button until we get the response

						$('#myButton').attr("disabled", true);

					},

					//this is called after the response or error functions are finsihed

					//so that we can take some action

					complete : function(jqXHR, textStatus) {

						//enable the button 

						$('#myButton').attr("disabled", false);

					}

				});

			});
	function jsonpCallback(data) {
		console.log("callback", data);
		//callfeedback();
		//do nothing    
	}
</script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<!-- <script type="text/javascript">
	function setdata(tmp) {
		document.getElementById("hidd").value = tmp.name;
	}
</script> -->
</head>
<body oncontextmenu="return false;">
	<!--Header Section-->
	<div class="header-tile"></div>
	<div class="header">
		<div class="header-title">
			<img src="resources/Images/fly.png" border="0" class="fly"
				height="3px" width="30px" /> <img src="resources/Images/fly.png"
				border="0" class="fly1" height="3px" width="30px" /> <img
				src="resources/Images/fly.png" border="0" class="fly2" height="3px"
				width="30px" />Cap Store
		</div>
		<!--Header Section->
<!--Search Bar Starts Here-->
		<form action="tsearch" method="get">
			<input type="text" class="mainSearchBar" list="awards"
				style="color: #000" title="Search" type="text" name="Search"
				onblur="showText()" onfocus="hideText();" value=""
				placeholder="       Search Here"> <datalist id="awards"
					list="awards" class="datalist1"> <select>
					<option value="Best Picture"></option>
					<option value="Best Director"></option>
					<option value="Best Adapted Screenplay"></option>
					<option value="Best Original Screenplay"></option>
				</select> </datalist> <input class="mainSubmit" value=" " type="submit"
				style="position: relative; margin-left: -76px;">
		</form>
		<!--Search Bar Ends Here-->
	</div>
	<!-- Login Starts Here -->
	<div id="loginContainer">
		<a href="#" id="loginButton"><span>Login</span><em></em></a>
		<div style="clear: both"></div>
		<div id="loginBox">
			<form id="loginForm">
				<fieldset id="body">
					<fieldset>
						<label for="email">Email Address</label> <input type="text"
							name="email" id="email" onblur="showText()" />
					</fieldset>
					<fieldset>
						<label for="password">Password</label> <input type="password"
							name="password" id="password" onblur="showText()" />
					</fieldset>
					<input type="submit" id="login" value="Sign in" />

				</fieldset>
				<span><a href="#">Change password</a><a href="#">Forgot
						your password?</a></span>
			</form>
		</div>
	</div>
	<!-- Login Ends Here -->

	<div class="left-border"></div>
	<!--Header Section Ends Here-->
	${username}
	<!-- Logout Ends Here -->
	<a href="logout" id="logoutButton"><span>Logout</span><em></em></a>
	<!-- Login Ends Here -->
	<div class="left-border"></div>
	<!--Header Section Ends Here-->
	<!--Menu Section-->
	<div id='cssmenu'>
		<ul>
			<li class='active'><a href='home.jsp'><span>Home</span></a></li>
			<li><a href='#'><span>Mobiles @ Accessories</span></a></li>
			<li><a href='#'><span>Clothing</span></a></li>
			<li><a href='#'><span>Footwears</span></a></li>
			<li><a href='#'><span>Cosmetics</span></a></li>
			<li class='last'><a href='#'><span>Computers &
						Accessories</span></a></li>
		</ul>
	</div>

	<!--Menu Section-->
	<div class="middle-container">

		<form action="addtocart" method="get">
			<br /> <br /> <br /> <br /> <br /> <br />
<%-- 
<b>ProductBrand:</b> &nbsp;&nbsp;&nbsp;${list1[0].product.productBrand}<br />
			<b>ProductCost:</b>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${list1[0].product.productCost}<br /> <b>ProductName:</b>
			&nbsp;&nbsp;&nbsp;${list1[0].product.productName}<br /> <b>ProductTag:</b>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${list1[0].product.productTag}<br /> --%>
			

			<%-- ProductId:  ${list1.product.productId} --%>
			<img src="${path}" align="top"></img>
<c:forEach var="list1" items="${list1}" begin="0" end="0" >

${list1.product.productId}

</c:forEach>
<c:forEach var="list1" items="${list1}">
<b>${list1.proddesc.attributeName}-------${list1.proddesc.attributeValue}</b>

</c:forEach>
			 <br />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quantity:<input
				type="text" value="" name="qty" id="qty" size="2" />

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<!-- <a href="addtocart">Add
		to Cart</a><br /> -->
			<input type="submit" name="addtocart" value="Add To Cart"></input>

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="addtowish">Add to WishList</a><br />
			<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter your feedback<a href="home1">Enter your Feedback</a><br /><br />
 -->



			
				<table align="left" id="table1">
					<c:forEach var="discount" items="${listDiscount}">
						<tr>
							<td><input id="me" type="radio" name="selectedId"
								value="${discount.offer.schemeId}"
								onclick="displayResult(this.value)" /></td>
							<td><c:out value="${discount.offer.schemeDescription}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
				<input type="hidden" id="result" name="result"></input>
		</form>
		<div class="sub-middle-container" align="center">

			<div id="anotherSection2">
				<fieldset>
					<legend>You Can also try this...!</legend>
					<div id="ajaxResponse2">sadasdasda</div>
				</fieldset>
			</div>
		</div>
		<div class="bottom-container">
			<div class="bottom-container1">

				Rating&nbsp;<input type="radio" id="rate" name="rate" value="1"
					onclick="setrate(this)" />1 <input type="radio" id="rate"
					name="rate" value="2" onclick="setrate(this)" /> 2 <input
					type="radio" id="rate" name="rate" value="3"
					onclick="setrate(this)" /> 3 <input type="radio" id="rate"
					name="rate" value="4" onclick="setrate(this)" /> 4 <input
					type="radio" id="rate" name="rate" value="5"
					onclick="setrate(this)" /> 5 <input type="hidden" name="hrate"
					id="hrate" value="0" />
			</div>
			<br /> <br />

			<textarea name="content" id="content" rows="7" cols="50">
</textarea>
			<br /> <input type="button" id="post_feedback" name="post feedback"
				value="post feedback" /> <input type="button" value="show feedback"
				id="show_feedback" name="show_feedback" /> <input type="button"
				value="deleteme" id="deleteme" name="deleteme" /> <input
				type="hidden" name="hidd" id="hidd" visibility="hidden" />
			<div id="ajaxResponse" class="ajaxResponse" id="ajaxResponse"></div>
		</div>
		<br /> <br />
		<form action="compareproducts?prodId=${selectedProduct}" method="post"
			name="compareForm">
			<div id="slider">
				<c:forEach items="${productList}" var="product">
					<ul>
						<li><img src="${product.imgPath}" width="100" height="100"
							name="${product.prodId}" /><input type="checkbox"
							style="float: right; bottom: 0px; right: 0px;"
							value="${product.prodId}" name="similarProducts"
							onclick="compareEnable()" /></li>
					</ul>
				</c:forEach>
			</div>
			<input type="submit" id="Submit" value="Compare" disabled="disabled" />
		</form>
	</div>
	<!--Footer Section-->
	<div class="right-border"></div>
	<div class="footer">@Copyright Cap Store</div>
	<div class="footer-tile"></div>
	<!--Footer Section-->
</body>
</html>
