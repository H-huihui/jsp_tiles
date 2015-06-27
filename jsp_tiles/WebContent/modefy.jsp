<%@page import="com.model.Student"%>
<%@ page import="java.io.*,java.sql.*" %>
<%@ page import="com.*" %>
<html>
<head>
	<title>SignInPage</title>
	<style type="text/css">
		<!--
		@import url(login.css);
		-->;
	</style>
</head>
<body>
<form method="post" action="MServlet">

	<!--this is banner part-->
	<div class="banner">
		<img src="image/google_banner.png">
		<center>
		</br></br>
			<h1>Please modify your google accout!</h1>
		</center>
	</div> 
	<!--this is all part-->
	<div class="all">

		<!--this is left part-->
		<div class="left">

			<!--add content there-->
			<div class="left_content">
				this is left content
			</div>

			left
			<img src="">
		</div>

		<!--this is right part-->
		<div class="right">

			<!--add content there-->
			<div class ="right_content">
				<h2><strong>&nbspPlease modify!</strong></h2>
				<table>
					<tr>
						<td>
							<%Student student=(Student)session.getAttribute("student"); %>
							<%String image=request.getParameter("image"); %>
							<input type="hidden" name="imagePath" value=<%out.print(image+".jpg");%>>
							<img width=100 height=50 alt="" src=<%if(image==null) {out.print(student.getImage());} else {out.print(image+".jpg");}%>>
						<td><a href="test.jsp"><input type="button" value="upload photo" onClick=window.location.href="test.jsp"></input></a></td>

						</td>
					</tr>
					<tr>
						<td>
							Name:
						</td>
						<td>
							<input type="text" name="name" value=<%=student.getName()%>>
						</td>
					</tr>
					<tr>
						<td>
							Mail:
						</td>
						<td>
							<input type="text" name="mail">
							<select name="com">
								<option value="@126.com">@126.com</option>
								<option value="@163.com">@163.com</option>
								<option value="@Gmail.com">@Gamil.com</option>
								<option value="@sina.com">@sina.com</option>
								<option value="@qq.com">@qq.com</option>
							</select>
						</td>
					</tr>

					<tr>
						<td>
							Password:
						</td>
						<td>
							<input type="password" name="password">
						</td>
					</tr>
					<tr>
						<td>
							Password:
						</td>
						<td>
							<input type="password" name="confirmpassword">
						</td>
					</tr>
					<tr>
						<td>
							Sex:
						</td>
						<td>
							<input type="radio" name="sex" value="m">male
							<input type="radio" name="sex" value="f">female
						</td>
					</tr>
					<tr>
						<td>
							birth:
						</td>
						<td>
							<select name="year">
								<option value="1990" selected>1990</option>
								<option value="1991">1991</option>
								<option value="1992">1992</option>
								<option value="1993">1993</option>
								<option value="1994">1994</option>
							</select>
							year
							<select name="month">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
							month
							<select name="day">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							day
						</td>
					</tr>
						
					<tr>
						<td>
							City:
						</td>
						<td>
							<select name="city">
								<option value="beijing" selected>Beijing</option>
								<option value="shanghai">Shanghai</option>
								<option value="shenzhen">Shenzhen</option>
								<option value="tianjin">Tianjin</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="register" >
						</td>
						<td>
							<input class="submit" type="reset" value="reset">
						</td>
					</tr>
				</form>

			</div>
			
			<img src="">
		</div>

	</div>
	

</body>
</html>