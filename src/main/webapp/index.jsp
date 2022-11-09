<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelos.Productos"%>
<%
    ArrayList<Productos> lista = (ArrayList<Productos>) session.getAttribute("listapro");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Productos</title>
    </head>
    <style>
        .bor {
            margin-left: 30%;
            margin-right: 30%;
            border: black 8px solid;
        }
    </style>

    <body>

        <div class="bor" style="text-align: center;"  >
            <h1>  SEGUNDO PARCIAL TEM - 742  </h1>
            <h2>  Nombre: Geno Mariela Larico Choque  </h2>
            <h2> ************************************ </h2>
            <h2>  Carnet: 4304334 </h2>
            <h2>  ****************** </h2>
        </div>    

        <h1><center> --------PRODUCTOS-------- </center></h1>
        <div style="text-align: center;"  >
            <a href="SessionServlet?op=nuevo">Nuevo Producto</a>            
            <br>
            <br>
            <table border="5" style="margin: 0 auto;" > 
                <tr>
                    <th>Id</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Categoria</th>
                    <th>Editar</th>
                    <th>Eliminar</th>
                </tr>
                <%
                    if (lista != null) {
                        for (Productos item : lista) {

                %>
                <tr>
                    <td><%= item.getId()%></td>
                    <td><%= item.getDescripcion()%></td>
                    <td><%= item.getCantidad()%></td>
                    <td><%= item.getPrecio()%></td>
                    <td><%= item.getCategoria()%></td>
                    <td><a href="SessionServlet?op=editar&id=<%= item.getId()%>">Editar</a></td>
                    <td><a href="SessionServlet?op=eliminar&id=<%= item.getId()%>"
                           onclick='return confirm("Esta Seguro de Eliminar el Producto");' >Eliminar</a></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
