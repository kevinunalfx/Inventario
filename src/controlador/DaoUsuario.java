package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.conexion;
import modelo.usuarios;

public class DaoUsuario {

    Connection con;
    conexion cn = new conexion(); // Asegúrate que esta clase devuelve un Connection válido
    PreparedStatement ps;
    ResultSet rs;

    public usuarios login(String usuario, String pass) {
        usuarios us = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND pass = AES_ENCRYPT(?, 'clave')";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            if (rs.next()) {
                us = new usuarios();
                us.setIdusuario(rs.getInt(1));
                us.setNombre(rs.getString(2));
                us.setApellido(rs.getString(3));
                us.setDocumento(rs.getString(4));
                us.setTelefono(rs.getString(6));
                us.setCorreo(rs.getString(7));
                us.setTipoUsuario(rs.getString(8));
                us.setUsuario(rs.getString(9));
                us.setPassword(rs.getString(10));
                us.setDireccion(rs.getString(5));
            }

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }

        return us;
    }
}