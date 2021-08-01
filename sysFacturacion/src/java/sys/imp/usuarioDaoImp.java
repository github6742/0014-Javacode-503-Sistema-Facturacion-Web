package sys.imp;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sys.clasesClasesAuxiliares.encriptarPassword;
import sys.dao.usuarioDao;
import sys.model.Usuario;
import sys.util.HibernateUtil;

public class usuarioDaoImp implements usuarioDao{

    @Override
    public Usuario obtenerDatosPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Usuario WHERE nombreUsuario=:nombreUsuario";
        Query q = session.createQuery(hql);
        q.setParameter("nombreUsuario", usuario.getNombreUsuario());
        
        return (Usuario) q.uniqueResult();
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario user=this.obtenerDatosPorUsuario(usuario);
        if(user!=null){
            if (!user.getPassword().equals(encriptarPassword.sha512(usuario.getPassword()))){
                user=null;
            }
        }
        return user;
    }
    
}
