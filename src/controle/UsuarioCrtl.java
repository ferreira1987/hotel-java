package controle;

import dao.UsuarioDao;
import modelo.Usuario;

public class UsuarioCrtl {
    
    public UsuarioCrtl(){}
    
    public boolean incluirCrtl(Usuario user){
        return new UsuarioDao().incluir(user);
    }
    
    public boolean loginCrtl(Usuario user){
        return new UsuarioDao().acessoLogin(user.getLogin(), user.getSenha());
    }
    
}
