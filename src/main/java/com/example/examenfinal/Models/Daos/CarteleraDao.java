package com.example.examenfinal.Models.Daos;

import com.example.examenfinal.Models.Beans.Cadena;
import com.example.examenfinal.Models.Daos.BaseDao;
import com.example.examenfinal.Models.Beans.Cartelera;
import com.example.examenfinal.Models.Beans.Cine;
import com.example.examenfinal.Models.Beans.Pelicula;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.util.ArrayList;

public class CarteleraDao extends BaseDao {

    public ArrayList<Cartelera>listarFunciones(){

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
    public ArrayList<Pelicula>listarPeliculas(){

        ArrayList<Pelicula> lista=new ArrayList<>();
        String sql="select idpelicula,nombre from pelicula";
        try(Connection connection=this.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet resultSet=stmt.executeQuery(sql)){

            while(resultSet.next()){

                Pelicula pelicula=new Pelicula();

                pelicula.setIdPelicula(resultSet.getInt(1));
                pelicula.setNombre(resultSet.getString(2));

                lista.add(pelicula);

            }

        }
        catch (SQLException e){
            System.out.println("Fallo conexion");
            e.printStackTrace();
        }
        return lista;

    }

    public ArrayList<Cine>listarCines(){

        ArrayList<Cine> lista=new ArrayList<>();
        String sql="select idcine,nombre from cine";
        try(Connection connection=this.getConnection();
            Statement stmt=connection.createStatement();
            ResultSet resultSet=stmt.executeQuery(sql)){

            while(resultSet.next()){

                Cine cine=new Cine();

                cine.setIdCine(resultSet.getInt(1));
                cine.setNombre(resultSet.getString(2));

                lista.add(cine);
            }
        }
        catch (SQLException e){
            System.out.println("Fallo conexion");
            e.printStackTrace();
        }
        return lista;
    }

    //ACÁ EN REALIDAD ESTOY CREANDO UNA FUNCIÓN, ASÍ ESTÉ USANDO EL OBJETO CARTELERA
    public void crearFuncion(Cartelera cartelera) throws SQLException {

        String sql = "INSERT INTO cartelera (idpelicula, idcine, doblada, subtitulada, horario) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection=this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(2, cartelera.getCine().getIdCine());
            pstmt.setInt(3, cartelera.getDoblada());
            pstmt.setInt(4, cartelera.getSubtitulada());
            pstmt.setString(5, cartelera.getHorario());

            pstmt.setInt(6, cartelera.getIdCartelera());
            pstmt.executeUpdate();
        }
    }

    public void editarEmpleado(Cartelera cartelera) throws SQLException {

        String sql = "UPDATE cartelera SET idpelicula = ?, idcine = ?, doblada = ?, subtitulada = ?, "
                + "horario = ? WHERE idCartelera = ?";

        try(Connection connection =this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {
            pstmt.setInt(1, cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(2, cartelera.getCine().getIdCine());
            pstmt.setInt(3, cartelera.getDoblada());
            pstmt.setInt(4, cartelera.getSubtitulada());
            pstmt.setString(5, cartelera.getHorario());

            pstmt.setInt(6, cartelera.getIdCartelera());
            pstmt.executeUpdate();
        }
    }







}
