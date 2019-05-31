package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Endereco;

public class EnderecoDao {

    private final Conexao conn;
    private String error;
    private int result;

    public EnderecoDao() {
        this.conn = new Conexao();
    }

    public void exeEndereco(Endereco endereco) {

        if (endereco.getAction() != null && endereco.getAction().equals("insert")) {
            this.incluir(endereco);
        } else if (endereco.getAction() != null && endereco.getAction().equals("update")) {
            this.update(endereco);
        }
    }

    public void incluir(Endereco endereco) {
        try {
            String query = "INSERT INTO endereco (codigo_clie, codigo_tip, logradouro_en, numero_en, complemento_en, bairro_en, cep_en, cidade_en, estado_en) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);

            ps.setInt(1, endereco.getCodCliente());
            ps.setInt(2, endereco.getTipo());
            ps.setString(3, endereco.getLogradouro());
            ps.setString(4, endereco.getNumero());
            ps.setString(5, endereco.getComplemento());
            ps.setString(6, endereco.getBairro());
            ps.setString(7, endereco.getCep());
            ps.setString(8, endereco.getCidade());
            ps.setString(9, endereco.getEstado());

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao incluir endereço:\n" + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public void update(Endereco endereco) {
        try {
            String query = "UPDATE endereco SET codigo_tip = ?, logradouro_en = ? , numero_en = ?, complemento_en = ?, bairro_en = ?, cep_en = ?, cidade_en = ?, estado_en = ? WHERE codigo_en = ?";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);

            ps.setInt(1, endereco.getTipo());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getBairro());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getEstado());
            ps.setInt(9, endereco.getCodigo());

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao atualizar endereço:\n" + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public void excluir(int codAddress) {
        try {
            String query = "DELETE FROM endereco WHERE codigo_en = ?";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);

            ps.setInt(1, codAddress);

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao excluir Endereço:\n" + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public int getResult() {
        return this.result;
    }

    public String getError() {
        return this.error;
    }

    public ArrayList<Endereco> consultEnderecoPorId(int codCliente) {
        ArrayList<Endereco> listEndereco = new ArrayList<>();

        try {
            String query = "SELECT * FROM endereco WHERE codigo_clie = ? ORDER BY codigo_en ASC";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setInt(1, codCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco();
                //codigo_en, codigo_clie, codigo_tip, logradouro_en, numero_en, bairro_en, complemento_en, cep_en, cidade_en, estado_en
                endereco.setTipo(rs.getInt("codigo_tip"));
                endereco.setCodigo(rs.getInt("codigo_en"));
                endereco.setLogradouro(rs.getString("logradouro_en"));
                endereco.setNumero(rs.getString("numero_en"));
                endereco.setComplemento(rs.getString("complemento_en"));
                endereco.setBairro(rs.getString("bairro_en"));
                endereco.setCidade(rs.getString("cidade_en"));
                endereco.setCep(rs.getString("cep_en"));
                endereco.setEstado(rs.getString("estado_en"));

                listEndereco.add(endereco);
            }

            return listEndereco;

        } catch (SQLException e) {
            this.error = e.getMessage();
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            this.conn.fecharConexao();
        }

        return null;
    }

}
