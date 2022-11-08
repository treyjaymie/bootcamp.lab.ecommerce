<%@page language="java" %>
<%@page import="com.webshoppe.ecommerce.entity.Item" %>
<%@page import="java.util.List" %>

<%
List<Item> catalog = (List<Item>) request.getAttribute("catalog");
%>
<html>
	<head>
		<title>Webshoppe::Catalog</title>
		<style type="text/css">
			body {
				margin: 50px;
			}
		</style>
	</head>
	<body>
		<h2>Catalog</h2>
		<hr/>
		<table>
			<thead>
				<td>ID</td>
				<td>Name</td>
				<td>Description</td>
				<td>Price</td>
			</thead>
			<tbody>
				<%
				for(Item item : catalog) {
				%>
					<tr>
						<td><%= item.getId() %></td>
						<td><%= item.getName() %></td>
						<td><%= item.getDescription() %></td>
						<td><%= item.getPrice() %></td>
					</tr>
					<%
				}
					%>
			</tbody>
		</table>
	</body>
</html>