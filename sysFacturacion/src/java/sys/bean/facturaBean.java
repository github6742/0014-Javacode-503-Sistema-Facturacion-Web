
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
    private String codigoCliente;
    
    public facturaBean(){
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    //Metodo para agregar los datos de los clientes, por medio del dialogClientes
    public void agregarDatosCliente(Integer codCliente){
        this.transaction = null;
        this.session = null;
        System.out.println("agregarDatosCliente--->codCliente: "+codCliente);
        try{
            System.out.println("agregarDatosCliente--0010");
            this.session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("agregarDatosCliente--0020");
            clienteDao cDao = new clienteDaoImp();
            System.out.println("agregarDatosCliente--0030");
            this.transaction = this.session.beginTransaction();
            System.out.println("agregarDatosCliente--0040");
            
            //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);
            System.out.println("agregarDatosCliente--0050");
            
            this.transaction.commit();
            System.out.println("agregarDatosCliente--0060");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del cliente agregado"));
            System.out.println("agregarDatosCliente--0070");
            
        } catch (Exception e) {
            System.out.println("agregarDatosCliente--0080");
            if(this.transaction!=null){
                System.out.println("agregarDatosCliente--0090");
                System.out.println(e.getMessage());
                System.out.println("agregarDatosCliente--0100");
                transaction.rollback();
                System.out.println("agregarDatosCliente--0110");
            }
            
        } finally {
            System.out.println("agregarDatosCliente--0120");
            if(this.session!=null){
               System.out.println("agregarDatosCliente--0130");
               this.session.close();
            }
        System.out.println("agregarDatosCliente--0140");
        }
    System.out.println("agregarDatosCliente--0150");
    }
}
