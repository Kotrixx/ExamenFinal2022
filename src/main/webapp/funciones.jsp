<%@ page import="com.example.examenfinal.Models.Beans.Cartelera" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: sebas
  Date: 11/07/2023
  Time: 01:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Cartelera> listaCartelera = (ArrayList<Cartelera>) request.getAttribute("listaFunciones");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="pb-5 pt-4 px-3 titlecolor">
    <div class="col-lg-6">
        <h1 class='text-light'>Selecciones del mundial</h1>
    </div>
</div>
<div class="tabla">
    <table class="table table-dark table-transparent table-hover">
        <thead>
        <th>Cadena</th>
        <th>Cine</th>
        <th>Pelicula</th>
        <th>Horario</th>
        <th></th>
        </thead>
        <%
            for (Cartelera cartelera : listaCartelera) {
        %>
        <tr>
            <td><%=cartelera.getCine().getCadena().getNombreComercial()%>
            </td>
            <td><%=cartelera.getCine().getNombre()%>
            </td>
            <td>
                <%=cartelera.getHorario()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
