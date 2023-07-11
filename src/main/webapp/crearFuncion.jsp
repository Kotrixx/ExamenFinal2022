<%@ page import="com.example.examenfinal.Models.Beans.jugador.Jugador" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab7.models.beans.seleccion.Seleccion" %>
<%@ page import="com.example.examenfinal.Models.Beans.Cartelera" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ArrayList<Cartelera> lista = (ArrayList<Cartelera>) request.getAttribute("listaSelecciones");%>


<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Nuevo Jugador"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/navbar/navbar.jsp">
                <jsp:param name="page" value="jugadores"/>
            </jsp:include>
            <div class="container">
                <h1 style="color:white;" class='mb-3'>Registrar nuevo jugador</h1>
                <form method="POST" action="<%=request.getContextPath()%>/JugadorServlet">
                    <div class="mb-3">
                        <label hidden="" style="color:white;" for="idJugador">ID Jugador</label>
                        <input type="text" class="form-control" name="idJugador" id="idJugador" hidden="">
                    </div>
                    <div class="mb-3">
                        <label style="color:white;" for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="nombre">
                    </div>
                    <div class="mb-3">
                        <label style="color:white;" for="edad">Edad</label>
                        <input type="text" class="form-control" name="edad" id="edad">
                    </div>
                    <div class="mb-3">
                        <label style="color:white;" for="posicion">Posicion</label>
                        <input type="text" class="form-control" name="posicion" id="posicion">
                    </div>
                    <div class="mb-3">
                        <label style="color:white;" for="club">Club</label>
                        <input type="text" class="form-control" name="club" id="club">
                    </div>
                    <div class="form-group">
                        <label style="color:white;" for="seleccion">Selección</label>
                        <select  name="seleccion" id="seleccion" class="form-control">
                            <option value="0">Escoja una seleccion</option>
                            <% for (Seleccion seleccion : lista) {%>
                            <option value="<%=seleccion.getIdSeleccion()%>"><%=seleccion.getNombre()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                    <a class="btn btn-danger" href="<%=request.getContextPath()%>/JugadorServlet">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
                    crossorigin="anonymous"></script>

        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
