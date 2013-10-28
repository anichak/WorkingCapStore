<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link type="text/css" href="CAPSTORE/WebContent/WEB-INF/resources/Css/login.css" rel="stylesheet"  />
<link type="text/css" href="CAPSTORE/WebContent/WEB-INF/resources/Css/style.css" rel="stylesheet"  />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="resources/Script/jquery-2.0.3.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 <script>
  $(function() {
    $( "#datepicker" ).datepicker();
    $( "#datepicker1" ).datepicker();
    });
  </script>
<script>
	function isContainOnlyDigit(string) {
		if (string.match(/^\d+$/))
			return true;
		else {
			alert("This field must contain only digits");
			return false;
		}

	}

	function dateValidator(string) {
		var now = new Date();
		string = string.split("/");
		string = string[2] + "/" + string[0] + "/" + string[1];
		var target = new Date(string);

		if (target.getFullYear() >= now.getFullYear()) {

			if (target.getMonth() >= now.getMonth()) {

				if (target.getDate() > now.getDate() || target.getDate() == now.getDate())
					return true;
				else {
					alert("You Can't Enter Past Date");
					
				     }
			} else {
				alert("You Can't Enter Past Month");
				
			}
		} else {
			alert("You Can't Enter Past Year");
			
		}
	}
	
	
</script>
<script src="Anand/Capstore0/Capstore/Script/login.js"></script>
</head>


<body oncontextmenu="return= false;">
<!--Header Section-->

<div class="header-tile"></div>
<div class="header" >
<div class="header-title">
<img src="Anand/Capstore0/Capstore/Images/fly.png" border="0" class="fly" height="3px" width="30px"  />
<img src="Anand/Capstore0/Capstore/Images/fly.png" border="0" class="fly1" height="3px" width="30px"/>
<img src="Anand/Capstore0/Capstore/Images/fly.png" border="0" class="fly2" height="3px" width="30px"/>Cap Store</div>



<!--Header Section->
<!--Search Bar-->
<form action="Anand/Capstore0/Capstore/tsearch" method="get">
<input  type="text" class="mainSearchBar" list="awards"  style="color: #000" title="Search" type="text" name="Search" onblur="showText()" onfocus="hideText();" value="" placeholder="       Search Here" >

<datalist id="awards" list="awards" class="datalist1">
<select>
<option value="Best Picture"></option>
<option value="Best Director"></option>
<option value="Best Adapted Screenplay"></option>
<option value="Best Original Screenplay"></option>
</select>
</datalist>

<input class="mainSubmit" value=" " type="submit" style="position:relative;margin-left:-76px;">
</form>
<!--Search Bar-->      
</div>
  <!-- Login Starts Here -->
            <div id="loginContainer">
                <a href="#" id="loginButton"><span>Login</span><em></em></a>
                <div style="clear:both"></div>
                <div id="loginBox">                
                    <form id="loginForm">
                        <fieldset id="body">
                            <fieldset>
                                <label for="email">Email Address</label>
                                <input type="text" name="email" id="email" />
                            </fieldset>
                            <fieldset>
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" />
                            </fieldset>
                            <input type="submit" id="login" value="Sign in" />
                            <label for="checkbox"><input type="checkbox" id="checkbox" />Remember me</label>
                        </fieldset>
                        <span><a href="#">Forgot your password?</a></span>
                    </form>
                </div>
            </div>
            <!-- Login Ends Here -->

<div class="left-border"></div>
<!--Header Section Ends Here-->


<!--Menu Section-->

<!--SETTING EMAIL-->      
<div >
<h1 align="center">SCHEDULE EMAIL </h1>
<form action="setmail" name="mailform" method="post">
<table align="center" width="421" height="445" cellpadding="10px" cellspacing="10px" id="table1">
<tr>
<td width="565"><font size="4">Enter Starting Date: </font></td>
<td id="td" width="265"><input type="text" name="txt_startdate" id="datepicker" required="required" onchange="return dateValidator(value)"/></td>
</tr>
<tr>
<td width="565"><font size="4">Enter Ending Date:</font> </td>
<td id="td1" width="265"><input type="text" name="txt_enddate" id="datepicker1" required="required" onchange="return dateValidator(value)"/></td>
</tr>
<tr>
<td width="565"><font size="3">Enter Duration frequency: </font></td>
<td id="td3" width="265"><input type="text" name="txt_duration"  required="required" onblur="return isContainOnlyDigit(value)"/></td>
</tr>
<tr>
<td width="565"><font size="4">Enter Starting Time: </font></td>
<td width="265">

<select name="txt_starttime" required="required" size="4" onchange="return timeValidator(value)">

						<option>01:00</option>
                        <option>01:15</option>
                        <option>01:30</option>
                        <option>01:45</option>
						<option>02:00</option>
                        <option>02:15</option>
                        <option>02:30</option>
                        <option>02:45</option>
						<option>03:00</option>
                        <option>03:15</option>
                        <option>03:30</option>
                        <option>03:45</option>
						<option>04:00</option>
                        <option>04:15</option>
                        <option>04:30</option>
                        <option>04:45</option>
						<option>05:00</option>
                        <option>05:15</option>
                        <option>05:30</option>
                        <option>05:45</option>
						<option>06:00</option>
                        <option>06:15</option>
                        <option>06:30</option>
                        <option>06:45</option>
						<option>07:00</option>
                        <option>07:15</option>
                        <option>07:30</option>
                        <option>07:45</option>
						<option>08:00</option>
                        <option>08:15</option>
                        <option>08:30</option>
                        <option>08:45</option>
						<option>09:00</option>
                        <option>09:15</option>
                        <option>09:30</option>
                        <option>09:45</option>
						<option>10:00</option>
                        <option>10:15</option>
                        <option>10:30</option>
                        <option>10:45</option>
						<option>11:00</option>
                        <option>11:15</option>
                        <option>11:30</option>
                        <option>11:45</option>
						<option>12:00</option>
                        <option>12:15</option>
                        <option>12:30</option>
                        <option>12:45</option>
						<option>13:00</option>
                        <option>13:15</option>
                        <option>13:30</option>
                        <option>13:45</option>
						<option>14:00</option>
                        <option>14:15</option>
                        <option>14:30</option>
                        <option>14:45</option>
						<option>15:00</option>
                        <option>15:15</option>
                        <option>15:30</option>
                        <option>15:45</option>
						<option>16:00</option>
                        <option>16:15</option>
                        <option>16:30</option>
                        <option>16:45</option>
						<option>17:00</option>
                        <option>17:15</option>
                        <option>17:30</option>
                        <option>17:45</option>
						<option>18:00</option>
                        <option>18:15</option>
                        <option>18:30</option>
                        <option>18:45</option>
						<option>19:00</option>
                        <option>19:15</option>
                        <option>19:30</option>
                        <option>19:45</option>
						<option>20:00</option>
                        <option>20:15</option>
                        <option>20:30</option>
                        <option>20:45</option>
						<option>21:00</option>
                        <option>21:15</option>
                        <option>21:30</option>
                        <option>21:45</option>
						<option>22:00</option>
                        <option>22:15</option>
                        <option>22:30</option>
                        <option>22:45</option>
						<option>23:00</option>
                        <option>23:15</option>
                        <option>23:30</option>
                        <option>23:45</option>
						
					
				</select>
			
				</td>
</tr>
<tr>
<td width="565"><font size="4">Enter Ending Time:</font> </td>
<td width="265">
<select name="txt_endtime" required="required" size="4">

						<option>01:00</option>
                        <option>01:15</option>
                        <option>01:30</option>
                        <option>01:45</option>
						<option>02:00</option>
                        <option>02:15</option>
                        <option>02:30</option>
                        <option>02:45</option>
						<option>03:00</option>
                        <option>03:15</option>
                        <option>03:30</option>
                        <option>03:45</option>
						<option>04:00</option>
                        <option>04:15</option>
                        <option>04:30</option>
                        <option>04:45</option>
						<option>05:00</option>
                        <option>05:15</option>
                        <option>05:30</option>
                        <option>05:45</option>
						<option>06:00</option>
                        <option>06:15</option>
                        <option>06:30</option>
                        <option>06:45</option>
						<option>07:00</option>
                        <option>07:15</option>
                        <option>07:30</option>
                        <option>07:45</option>
						<option>08:00</option>
                        <option>08:15</option>
                        <option>08:30</option>
                        <option>08:45</option>
						<option>09:00</option>
                        <option>09:15</option>
                        <option>09:30</option>
                        <option>09:45</option>
						<option>10:00</option>
                        <option>10:15</option>
                        <option>10:30</option>
                        <option>10:45</option>
						<option>11:00</option>
                        <option>11:15</option>
                        <option>11:30</option>
                        <option>11:45</option>
						<option>12:00</option>
                        <option>12:15</option>
                        <option>12:30</option>
                        <option>12:45</option>
						<option>13:00</option>
                        <option>13:15</option>
                        <option>13:30</option>
                        <option>13:45</option>
						<option>14:00</option>
                        <option>14:15</option>
                        <option>14:30</option>
                        <option>14:45</option>
						<option>15:00</option>
                        <option>15:15</option>
                        <option>15:30</option>
                        <option>15:45</option>
						<option>16:00</option>
                        <option>16:15</option>
                        <option>16:30</option>
                        <option>16:45</option>
						<option>17:00</option>
                        <option>17:15</option>
                        <option>17:30</option>
                        <option>17:45</option>
						<option>18:00</option>
                        <option>18:15</option>
                        <option>18:30</option>
                        <option>18:45</option>
						<option>19:00</option>
                        <option>19:15</option>
                        <option>19:30</option>
                        <option>19:45</option>
						<option>20:00</option>
                        <option>20:15</option>
                        <option>20:30</option>
                        <option>20:45</option>
						<option>21:00</option>
                        <option>21:15</option>
                        <option>21:30</option>
                        <option>21:45</option>
						<option>22:00</option>
                        <option>22:15</option>
                        <option>22:30</option>
                        <option>22:45</option>
						<option>23:00</option>
                        <option>23:15</option>
                        <option>23:30</option>
                        <option>23:45</option>
						
					
				</select>
				</td>
</tr>
</table>
<input  type="submit" name="setemail" value="Set" />
</form>
</div>


<!--Footer Section-->
<div class="right-border"></div>
<div class="footer">@Copyright Cap Store</div>
<div class="footer-tile"></div>
<!--Footer Section-->
</body>
</html>