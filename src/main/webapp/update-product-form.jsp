<!DOCTYPE html>
<html>

<head>
<title>Update Student</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>My Store</h2>
		</div>
	</div>

	<div id="container">
		<h3>Update Product</h3>

		<form action="TiendaControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" /> <input
				type="hidden" name="productId" value="${THE_PRODUCT.id}" />

			<table>
				<tbody>
					
					<tr>
						<td><label>Nombre:</label></td>
						<td><input type="text" name="nombre"
							value="${THE_PRODUCT.name}" /></td>
					</tr>

					<tr>
						<td><label>Descripcion:</label></td>
						<td><input type="text" name="descripcion"
							value="${THE_PRODUCT.descripcion}" /></td>
					</tr>
					

					<tr>
						<td><label>Stock:</label></td>
						<td><input type="text" name="stock"
							value="${THE_PRODUCT.stock}" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>
			</table>
		</form>

		<div style="clear: both;"></div>

		<p>
			<a href="TiendaControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











