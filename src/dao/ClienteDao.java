package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class ClienteDao extends Conexao {

    private final Conexao conn;
    private String error;
    private int result = 0;

    public ClienteDao() {
        this.conn = new Conexao();
    }

    public void exeCliente(Cliente cliente) {
        if (cliente.getAction().equals("insert")) {
            this.incluir(cliente);
        } else {
            this.update(cliente);
        }

    }

    public void incluir(Cliente cliente) {
        try {
            String query = "INSERT INTO cliente (codigo_tp_clie, codigo_gen_clie, status_clie, nomeRazaoSocial_clie, "
                    + "sobrenomeNomeFantasia_clie, dataNascimentoFundacao_clie, rgIe_clie, orgaoExpedidor_clie, cpfCnpj_clie)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = this.conn.conectar().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cliente.getTipo());
            ps.setInt(2, cliente.getSexo());
            ps.setBoolean(3, cliente.getStatus());
            ps.setString(4, cliente.getNome());
            ps.setString(5, cliente.getSobrenome());
            ps.setDate(6, cliente.getDataNasc());
            ps.setString(7, cliente.getRgIe());
            ps.setString(8, cliente.getOrgExpedidor());
            ps.setString(9, cliente.getCpfCnpj());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                this.result = rs.getInt(1);
            }

        } catch (SQLException e) {
            this.error = "Erro ao inserir Cliente: " + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public void update(Cliente cliente) {
        try {
            String query = "UPDATE cliente SET codigo_gen_clie = ?, status_clie = ? , nomeRazaoSocial_clie = ?, sobrenomeNomeFantasia_clie = ?, "
                    + "dataNascimentoFundacao_clie = ?, rgIe_clie = ?, orgaoExpedidor_clie = ?, cpfCnpj_clie = ? WHERE codigo_clie = ?";

            PreparedStatement ps = this.conn.conectar().prepareStatement(query);

            ps.setInt(1, cliente.getSexo());
            ps.setBoolean(2, cliente.getStatus());
            ps.setString(3, cliente.getNome());
            ps.setString(4, cliente.getSobrenome());
            ps.setDate(5, cliente.getDataNasc());
            ps.setString(6, cliente.getRgIe());
            ps.setString(7, cliente.getOrgExpedidor());
            ps.setString(8, cliente.getCpfCnpj());
            ps.setInt(9, cliente.getCodigo());

            ps.executeUpdate();
            this.result = cliente.getCodigo();

        } catch (SQLException e) {
            this.error = "Erro ao atualizar Cliente: " + e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }
    }

    public void excluir(int codCliente) {
        try {
            String query = "DELETE FROM cliente WHERE codigo_clie = ?";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setInt(1, codCliente);

            this.result = ps.executeUpdate();

        } catch (SQLException e) {
            this.error = "Erro ao excluir Cliente: " + e.getMessage();
        }
    }

    public String getError() {
        return this.error;
    }

    public int getResult() {
        return this.result;
    }

    public ArrayList<String> getGenero() {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("Selecione o gênero");

        try {
            String query = "SELECT * FROM genero ORDER BY descricao_gen ASC";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                strList.add(rs.getString("descricao_gen"));
            }

            return strList;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
            this.conn.fecharConexao();
        }
    }

    public ArrayList<String> listEstado() {
        ArrayList<String> estado = new ArrayList<>();
        estado.add("Selecione o estado");

        try {
            String query = "SELECT * FROM estado ORDER BY nome ASC";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                estado.add(rs.getString("uf"));
            }

            return estado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
            this.conn.fecharConexao();
        }
    }

    public ArrayList<String> getTipoEndereco() {
        ArrayList<String> tipo = new ArrayList<>();
        tipo.add("Selecione o tipo endereço");

        try {
            String query = "SELECT * FROM tipoendereco ORDER BY codigo_te ASC";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tipo.add(rs.getString("descricao_te"));
            }

            return tipo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } finally {
            this.conn.fecharConexao();
        }
    }

    public ArrayList<Cliente> consultClient(String param) {
        ArrayList list = new ArrayList<>();

        String query = "SELECT * FROM cliente WHERE (CONCAT(nomeRazaoSocial_clie,' ',sobrenomeNomeFantasia_clie) LIKE '%" + param + "%') OR (codigo_clie LIKE '" + param + "') ORDER BY codigo_clie";

        try {
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Cliente(rs.getInt("codigo_clie"), rs.getString("nomeRazaoSocial_clie"), rs.getString("sobrenomeNomeFantasia_clie"), rs.getString("cpfCnpj_clie")));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            this.conn.fecharConexao();
        }

        return list;
    }

    public Cliente consultClientPorCodigo(int codCliente) {
        Cliente cliente = new Cliente();

        try {
            String query = "SELECT * FROM cliente WHERE codigo_clie = ? ORDER BY codigo_clie";
            PreparedStatement ps = this.conn.conectar().prepareStatement(query);
            ps.setInt(1, codCliente);
            ResultSet rs = ps.executeQuery();

            rs.next();

            cliente.setCodigo(rs.getInt("codigo_clie"));
            cliente.setNome(rs.getString("nomeRazaoSocial_clie"));
            cliente.setSobrenome(rs.getString("sobrenomeNomeFantasia_clie"));
            cliente.setCpfCnpj(rs.getString("cpfCnpj_clie"));
            cliente.setRgIe(rs.getString("rgIe_clie"));
            cliente.setDataNasc(rs.getDate("dataNascimentoFundacao_clie"));
            cliente.setTipo(rs.getInt("codigo_tp_clie"));
            cliente.setSexo(rs.getInt("codigo_gen_clie"));
            cliente.setOrgExpedidor(rs.getString("orgaoExpedidor_clie"));
            cliente.setStatus(rs.getBoolean("status_clie"));

        } catch (SQLException e) {
            this.error = e.getMessage();
        } finally {
            this.conn.fecharConexao();
        }

        return cliente;
    }

}
