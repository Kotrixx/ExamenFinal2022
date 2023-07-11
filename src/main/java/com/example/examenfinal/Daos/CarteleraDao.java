package com.example.examenfinal.Daos;

import com.example.examenfinal.Beans.Cadena;
import com.example.examenfinal.Beans.Cartelera;
import com.example.examenfinal.Beans.Cine;
import com.example.examenfinal.Beans.Pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarteleraDao extends BaseDao{

    public ArrayList<Cartelera>listaFunciones(){

        ArrayList<Cartelera> lista=new ArrayList<>();
        String sql="select cart.idCartelera as \"idCartelera\",cad.nombre_comercial as \"nombre comercial\", cin.nombre as \"cine\",peli.nombre as \"pelicula\",3d,cart.subtitulada,cart.doblada, cart.horario from cartelera cart\n" +
                "inner join cine cin on cin.idcine=cart.idcine\n" +
                "inner join cadena cad on cad.idcadena=cin.idcadena\n" +
                "inner join pelicula peli on peli.idpelicula=cart.idpelicula\n" +
                "order by idCartelera;";
        try(Connection connection=this.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet resultSet=stmt.executeQuery(sql)){

            while(resultSet.next()){

                Cartelera cartelera=new Cartelera();
                cartelera.setIdCartelera(resultSet.getInt("idCartelera'"));
                Pelicula pelicula=new Pelicula();
                pelicula.setNombre(resultSet.getString("pelicula"));
                Cine cine=new Cine();
                cine.setNombre(resultSet.getString("cine"));
                cine.getCadena().setNombreComercial(resultSet.getString("nombre comercial"));
                cartelera.setCine(cine);
                cartelera.setPelicula(pelicula);

                cartelera.setTresD(resultSet.getInt(5));
                cartelera.setSubtitulada(resultSet.getInt(6));
                cartelera.setDoblada(resultSet.getInt(7));
                cartelera.setHorario(resultSet.getString(8));

                lista.add(cartelera);

            }

        }
        catch (SQLException e){
            System.out.println("Fallo conexion");
            e.printStackTrace();
        }
        return lista;
    }

}
