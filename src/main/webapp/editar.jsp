<%@page import="com.emergentes.modelos.Productos"%>
<%
    Productos item = (Productos) request.getAttribute("miProducto");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
       
        <div class="bor" style="text-align: center;"  >
        <h1><%=(item.getId() ==0)? "Nuevo Producto" : "Editar Producto" %></h1>
        <form action="SessionServlet" method="post" >
            <input type="hidden" name="id" value="<%= item.getId() %>"/>
            <table style="margin: 0 auto;">
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%= item.getDescripcion() %>" placeholder="Descripcion" /></td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="<%= item.getCantidad() %>" /></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="<%= item.getPrecio() %>" /></td>
                </tr>
                <tr>
                    <td>Categoria</td>
                    <td><input type="text" name="categoria" value="<%= item.getCategoria() %>"placeholder="Categoria" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Registrar"></td>
                </tr>
            </table>
            
        </form>
      </div>
    </body>
</html>
