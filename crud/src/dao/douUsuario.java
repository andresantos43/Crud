package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Usuario;
import crud.conexion;

public class douUsuario {
	conexion cx=null;
	public douUsuario() {
		cx=new conexion();
	}
	public boolean insertarUsuario(Usuario user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Usuario>fetchUsusarios(){
		ArrayList<Usuario>lista=new ArrayList<Usuario>();
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM usuario");
			rs=ps.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setUser(rs.getString("user"));
				u.setPassword(rs.getString("password"));
				u.setNombre(rs.getString("nombre"));
				lista.add(u);
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}

