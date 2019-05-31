package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Usuario;
import util.Auxiliar;

public class UsuarioDao extends Conexao{
    
    private final Conexao conn;
    
    public UsuarioDao(){
        this.conn = new Conexao();
    }

    public boolean incluir(Usuario user) {
        String senha = Auxiliar.MD5(user.getSenha()); // Efetua criptografia da senha        
        
        try {           
            String query = "INSERT INTO usuario (nome_usu,login_usu,senha_usu) VALUES (?,?,?)";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            
            ps.setString(1, user.getNome());
            ps.setString(2, user.getLogin());
            ps.setString(3, senha);
            
            ps.execute();
            
            return true;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            this.conn.fecharConexao();           
        }       
        
        return false; 
    }
    
    public boolean acessoLogin(String login, String senha){
        String password = Auxiliar.MD5(senha); // Efetua criptografia da senha
        boolean check = false;
        
        try {
            String query = "SELECT * FROM usuario WHERE login_usu =? AND senha_usu =?";
            PreparedStatement ps = this.conn.conectar().prepareCall(query);
            
            ps.setString(1, login);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();   
            
            if(rs.next()){
                check = true;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            this.conn.fecharConexao();
        }
        
        return check;
    }
  
    
}
