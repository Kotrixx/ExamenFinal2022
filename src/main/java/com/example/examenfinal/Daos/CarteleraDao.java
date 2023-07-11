package com.example.examenfinal.Daos;

import com.example.examenfinal.Beans.Cartelera;
import com.example.examenfinal.Beans.Pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarteleraDao extends BaseDao{

    public ArrayList<Cartelera>listaFunciones(){

        ArrayList<Cartelera> lista=new ArrayList<>();
        String sql="";
        try(Connection connection=this.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet resultSet=stmt.executeQuery(sql)){

            while(resultSet.next()){
                Cartelera cartelera=new Cartelera();


            }

        }
        catch (SQLException e){
            System.out.println("Fallo conexion");
            e.printStackTrace();
        }
        return lista;
    }

}
