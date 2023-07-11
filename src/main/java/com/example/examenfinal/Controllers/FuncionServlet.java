package com.example.examenfinal.Controllers;

import com.example.examenfinal.Models.Beans.Cartelera;
import com.example.examenfinal.Models.Beans.Cine;
import com.example.examenfinal.Models.Beans.Pelicula;
import com.example.examenfinal.Models.Daos.CarteleraDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.function.ToDoubleBiFunction;

@WebServlet(name = "FuncionServlet", urlPatterns = {"/FuncionServlet"})
public class FuncionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //CAMBIAR "guardar" por "listar" cuando listar esté listo
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");

        RequestDispatcher view;
        CarteleraDao carteleraDao = new CarteleraDao();

        switch (action) {
            case "lista":
                request.setAttribute("listadorDeFunciones", carteleraDao.listarFunciones());
                view = request.getRequestDispatcher("employees/index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("creadorDeFunciones", carteleraDao.listarFunciones());

                //ACA SE TIENE QUE PONER LA RUTA DEL JSP, en este caso no pongo nada antes de crearFuncion.jsp porque
                //está directamente en el webapp
                view = request.getRequestDispatcher("crearFuncion.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Employee emp = employeeDao.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        request.setAttribute("empleado", emp);
                        request.setAttribute("listaTrabajos", jobDao.listarTrabajos());
                        request.setAttribute("listaDepartamentos", departmentDao.listaDepartamentos());
                        request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                        view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //POR DEFECTO, LA ACCION VA A SER GUARDAR, ESO DICE ESTA LINEA
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        CarteleraDao carteleraDao = new CarteleraDao();

        switch (action) {
            case "guardar":
                Cartelera cartelera = new Cartelera();

                //ESTE "idPelicula" NO está en el Bean de Cartelera, por lo que haremos la forma compleja
                String idpelicula = request.getParameter("idpelicula");
                Pelicula pelicula = new Pelicula(idpelicula);
                cartelera.setPelicula(pelicula);

                //ESTE "idcine" NO está en el Bean de Cartelera, por lo que haremos la forma compleja
                String idcine = request.getParameter("idcine");
                Cine cine = new Cine(idcine);
                cartelera.setCine(cine);

                //ESTE "doblada" está en el mismo Bean de Cartelera, por lo que haremos la forma simple
                cartelera.setDoblada(request.getParameter("doblada"));

                //ESTE "subtitulada" está en el mismo Bean de Cartelera, por lo que haremos la forma simple
                cartelera.setSubtitulada(request.getParameter("subtitulada"));

                //ESTE "subtitulada" está en el mismo Bean de Cartelera, por lo que haremos la forma simple
                cartelera.setHorario(request.getParameter("horario"));

                if (request.getParameter("employee_id") == null) {
                    try {
                        carteleraDao.crearFuncion(cartelera);
                        response.sendRedirect("FuncionServlet?msg=Funcion creada exitosamente");
                    } catch (SQLException ex) {
                        response.sendRedirect("FuncionServlet?err=Error al crear funcion");
                    }
                } else {
                    cartelera.setIdCartelera(Integer.parseInt(request.getParameter("idCartelera")));
                    try {
                        carteleraDao.editarEmpleado(cartelera);
                        response.sendRedirect("FuncionServlet?msg=Funcion actualizada exitosamente");
                    } catch (SQLException ex) {
                        response.sendRedirect("FuncionServlet?err=Error al actualizar funcion");
                    }
                }

                break;

        }
    }
}