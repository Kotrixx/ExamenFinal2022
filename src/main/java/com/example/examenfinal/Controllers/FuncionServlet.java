package com.example.examenfinal.Controllers;

import com.example.examenfinal.Models.Daos.CarteleraDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "FuncionServlet", urlPatterns = {"/FuncionServlet"})
public class FuncionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CarteleraDao cartelera=new CarteleraDao();
        String action = request.getParameter("a")==null ? "listarFunciones" : request.getParameter("a");
        switch (action){
            case "listarFunciones":
                request.setAttribute("listaFunciones",cartelera.listarFunciones());
                request.getRequestDispatcher("funciones.jsp").forward(request,response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}