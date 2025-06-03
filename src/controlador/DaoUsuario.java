package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.conexion;
import modelo.usuarios;

public class DaoUsuario {

    Connection con;
    conexion cn = new conexion(); // Asegúrate que esta clase devuelve un Connection válido
    PreparedStatement ps;
    ResultSet rs;

    public usuarios login(String usuario, String pass) {
        usuarios us = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                us = new usuarios();
                us.setUsuario(rs.getString("usuario"));
                us.setPassword(rs.getString("password"));
                // Puedes añadir más campos si tu tabla tiene, por ejemplo:
                // us.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return us;
    }
}