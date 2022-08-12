<%@ page import="java.util.*, com.luv2code.web.jdbc.*"%>
<!DOCTYPE html>
<html>

<head>
<title>Product Tracker</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
// get the students from the request object (sent by servlet)
List<Producto> theProducts = (List<Producto>) request.getAttribute("PRODUCT_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<table>

				<tr>
					<th>Nombre</th>
					<th>Descripcion</th>
					<th>Stock</th>
				</tr>

				<%
				for (Producto tempProduct : theProducts) {
				%>

				<tr>
					<td><%=tempProduct.getName()%></td>
					<td><%=tempProduct.getDescription() %></td>
					<td><%=tempProduct.getStock()%></td>
				</tr>

				<%
				}
				%>

			</table>

		</div>

	</div>
</body>


</html>








