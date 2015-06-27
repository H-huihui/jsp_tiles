<html>
<head>
	<title>
		sign in 
	</title>
	<style type="text/css">
		<!--@import url(sign.css)-->;
	</style>
</head>
<body>
	<center>
	<form action="SignServlet" method="get">
	<!--this is the banner image -->
	<img src="">

	<!-- user photo-->
	<img src="">
	<table class="sign">
		<tr>
			<td>
				Email:
			</td>
			<td>
				<input type="text" name="email">
			</td>
		</tr>
		<tr>
			<td>
				password:
			</td>
			<td>
				<input type="password" name="password">
			</td>
		</tr>

		<tr>
			<td>
				<input type="submit" value="sign"> 
			</td>
			<td>
				<input type="reset" value="reset">
			</td>
		</tr>

	</table>
	</center>
	</form>
</body>
</html>
<script>
	var errori='<%=request.getParameter("error")%>';
	if(errori=='yes'){
		alert("sign failed!");
	}
</script>

















