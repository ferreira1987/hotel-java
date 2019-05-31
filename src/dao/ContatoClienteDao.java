package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ContatoCliente;

public class ContatoClienteDao extends Conexao {

    private final Conexao conn;
    private String error;
    private int result;

    public ContatoClienteDao() {
        this.conn = new Conexao();
    }

    public void exeContato(ContatoCliente cc) {

        if (cc.getAction().equals("insert")) {
            this.incluir(cc);
        } else {
            this.update(cc);
        }
    }

    public void incluir(ContatoCliente cc) {
        try {
            String query = "INSERT INTO contatocliente (codigo_clie_cc, telefone_cc, celular_cc, email_cc, site_cc)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setInt(1, cc.getCodCliente());
            ps.setString(2, cc.getTelefone());
            ps.setString(3, cc.getCelular());
            ps.setString(4, cc.getEmail());
            ps.setString(5, cc.getSite());

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao incluir contato: " + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public void update(ContatoCliente cc) {
        try {
            String query = "UPDATE contatocliente SET telefone_cc = ?, celular_cc = ?, email_cc = ?, site_cc = ? WHERE codigo_cc = ?";

            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setString(1, cc.getTelefone());
            ps.setString(2, cc.getCelular());
            ps.setString(3, cc.getEmail());
            ps.setString(4, cc.getSite());
            ps.setInt(5, cc.getCodigo());

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao atualizar contato: " + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public ArrayList<ContatoCliente> consultContatoPorId(int codCliente) {
        ArrayList<ContatoCliente> listContato = new ArrayList<>();

        try {
            String query = "SELECT * FROM contatocliente WHERE codigo_clie_cc = ? ORDER BY codigo_cc ASC";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setInt(1, codCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ContatoCliente contato = new ContatoCliente();
                contato.setCodigo(rs.getInt("codigo_cc"));
                contato.setTelefone(rs.getString("telefone_cc"));
                contato.setCelular(rs.getString("celular_cc"));
                contato.setEmail(rs.getString("email_cc"));
                contato.setSite(rs.getString("site_cc"));

                listContato.add(contato);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar contato do cliente.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.conn.fecharConexao();
        }

        return listContato;
    }

    public int getResult() {
        return this.result;
    }

    public String getError() {
        return this.error;
    }

}
