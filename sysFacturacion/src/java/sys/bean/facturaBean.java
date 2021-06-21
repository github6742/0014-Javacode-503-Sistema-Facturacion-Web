
package sys.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.clienteDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Producto;
import sys.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class facturaBean implements Serializable{
    
    Session session=null;
    Transaction transaction=null;
    
    private Cliente cliente;
    private Integer codigoCliente;
    
    private Producto producto;
    private String codigoBarra;
    
    private List<Detallefactura> listaDetalleFactura;
    
    public facturaBean(){
        this.listaDetalleFactura = new ArrayList<>();
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<Detallefactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
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
            
            //Obtener los datos del producto en la variable objeto cliente, segun el codigo del producto segun el codigo barra
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);
            
            if (this.cliente != null) {
                this.codigoCliente=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del producto agregado al detalle"));
            } else {
                this.codigoCliente=null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Incorrecto","Producto no encontrado"));
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
    
    
    
    //Metodo para agregar los datos del producto, por medio del dialogProductos
    public void agregarDatosProducto(String codBarra){
        this.transaction = null;
        this.session = null;
        System.out.println("agregarDatosProducto-0010-codBarra: "+codBarra);
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            
            this.transaction = this.session.beginTransaction();
            
            //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, codBarra);
            System.out.println("agregarDatosProducto-0020-nombre: "+this.producto.getNombreProducto());
                   
            
            this.listaDetalleFactura.add(new Detallefactura(null, 
                                                            null, 
                                                            this.producto.getCodBarra(), 
                                                            this.producto.getNombreProducto(), 
                                                            0,
                                                            this.producto.getPrecioVenta(), 
                                                            new BigDecimal(0)));
            
            this.transaction.commit();
            System.out.println("agregarDatosProducto-0030-coomit");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto","Datos del producto agregado"));
            
            
        } catch (Exception e) {
            System.out.println("agregarDatosProducto-0040");
            if(this.transaction!=null){
                System.out.println(e.getMessage());
                transaction.rollback();
            }
            
        } finally {
            System.out.println("agregarDatosProducto-0050");
            if(this.session!=null){
               this.session.close();
            }
        }
            System.out.println("agregarDatosProducto-0060");
    }
}
