package modelo;

import java.sql.Date;

public class Cliente {

    private int codigo;
    private String nome;
    private String sobrenome;
    private String cpfCnpj;
    private String rgIe;
    private Date dataNasc;
    private String telefone;
    private String celular;
    private String email;
    private int sexo;
    private String orgExpedidor;
    private boolean status;
    private int tipo;
    private String action;
    
    public Cliente(){
    
    }
    
    public Cliente(int codigo, String nome, String sobrenome, String cpfCnpj) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
    }     

    public Cliente(int codigo, String nome, String sobrenome, String cpfCnpj, String rgIe, Date dataNasc, 
            String telefone, String celular, String email, int sexo, String orgExpedidor, boolean status, int tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpfCnpj = cpfCnpj;
        this.rgIe = rgIe;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.sexo = sexo;
        this.orgExpedidor = orgExpedidor;
        this.status = status;
        this.tipo = tipo;
    }        

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRgIe() {
        return rgIe;
    }

    public void setRgIe(String rgIe) {
        this.rgIe = rgIe;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getOrgExpedidor() {
        return orgExpedidor;
    }

    public void setOrgExpedidor(String orgExpedidor) {
        this.orgExpedidor = orgExpedidor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }       

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }    
    
}
