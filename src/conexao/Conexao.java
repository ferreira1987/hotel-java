/**
 * @author Adão Ferreira
 */
package conexao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private final String host = "127.0.0.1";
    private final String banco = "hotel";
    private final String user = "root";
    private final String pass = "";
    private String url;
    private Connection conexao;

    public Conexao() {

    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            this.url = "jdbc:mysql://" + this.host + "/" + this.banco;
            this.conexao = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
            
            return this.conexao;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }

    public void fecharConexao() {
        try {
            if (this.conexao != null) {
                this.conexao.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
