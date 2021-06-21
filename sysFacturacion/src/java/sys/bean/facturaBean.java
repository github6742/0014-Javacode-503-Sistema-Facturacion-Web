
package sys.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.clienteDao;
import sys.imp.clienteDaoImp;
import sys.model.Cliente;
import sys.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class facturaBean implements Serializable{
    
    Session session=null;
    Transaction transaction=null;
    
    private Cliente cliente;
    private Integer codigoCliente;
    
    public facturaBean(){
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    //Metodo para agregar los datos de los clientes, por medio del dialogClientes
    public void agregarDatosCliente(Integer codCliente){
        this.transaction = null;
        this.session = null;
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);
            
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del cliente agregado"));
            
            
        } catch (Exception e) {
            if(this.transaction!=null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            
        } finally {
            if(this.session!=null){
               this.session.close();
            }
        }
    }
    
    
    
    //Metodo para agregar los datos del cliente buscado por codCliente
    public void agregarDatosCliente2(){
        this.transaction = null;
        this.session = null;
        
        try{
            if (this.codigoCliente == null){
                return;
            }
            
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();
            
            //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);
            
            if (this.cliente != null) {
                this.codigoCliente=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del cliente agregado"));
            } else {
                this.codigoCliente=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Incorrecto","Cliente no encontrado"));
            }
            
            this.transaction.commit();
            
            
        } catch (Exception e) {
            if(this.transaction!=null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            
        } finally {
            if(this.session!=null){
               this.session.close();
            }
        }
    }
}
