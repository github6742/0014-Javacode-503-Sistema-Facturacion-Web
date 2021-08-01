package sys.dao;

import sys.model.Usuario;

public interface usuarioDao {
    
    public Usuario obtenerDatosPorUsuario(Usuario usuario);
    
    public Usuario login(Usuario usuario);
    
}
