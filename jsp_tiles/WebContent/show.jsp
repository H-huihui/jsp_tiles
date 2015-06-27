<%@ page import="java.sql.*,com.bift.Common"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>show</title>
</head>
<body>
	<h1>the result</h1>
	<%
		int intPageSize;
		intPageSize=2;
		
		String sql="select * from test.student";
		
		int intRowCount;
		intRowCount=Common.getResultCount(sql);
		
		int intPageCount;
		intPageCount=(intRowCount+intPageSize-1)/intPageSize;
		
		int intPage;
		String strPage=request.getParameter("page");
		if(strPage==null){
			intPage=1;
		}else{
			intPage=Integer.parseInt(strPage);
			if(intPage<1){
				intPage=1;
			}else if(intPage>intPageCount){
				intPage=intPageCount;
			}
		}
		ResultSet rs=Common.excuteQuery(sql);	
	%>
	<form method="post" action="show.jsp">
		<table border>
			<tr>
				<th>Name</th><th>sex</th><th>birthday</th><th>city</th><th>Mail</th>
			</tr>
			<%
				if(intPageCount>0){
					rs.absolute((intPage-1)*intPageSize+1);
					int i=1;
					while(i<=intPageSize && !rs.isAfterLast()){
			%>
			<tr>
				<td><%=rs.getString("name") %></td>
				<td><%=rs.getString("sex") %></td>
				<td><%=rs.getString("birthday") %></td>
				<td><%=rs.getString("city") %></td>
				<td><%=rs.getString("mail") %></td>
			</tr>
			<% 
			rs.next();
					}
				}
			%>
		</table>
		<%=intPage%>page    total<%=intPageCount%>page
		<% if(intPage>1){ %> 
		<a href="show.jsp?page=<%=intPage-1%>">lastPage</a>
		<%
		}
		if(intPage<intPageCount){
		%>
		<a href="show.jsp?page=<%=intPage+1 %>">nextPage</a>
		<%
		}
		%>
		go:<input type="text" name="page" size=2>page<input type="submit" value="go">

	</form>
</body>
</html>