package modelo;

public class ContatoCliente {
   
    private int codigo;
    private int codCliente;
    private String telefone;
    private String celular;
    private String email;
    private String site;
    private String action;
    
    
    public ContatoCliente(){
    
    }        

    public ContatoCliente(int codigo, int codCliente, String telefone, String celular, String email, String site) {
        this.codigo = codigo;
        this.codCliente = codCliente;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.site = site;
    }        

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }        
    
    public void setAction(String action){
        this.action = action;
    }
    
    public String getAction(){
        return this.action;
    }
}
