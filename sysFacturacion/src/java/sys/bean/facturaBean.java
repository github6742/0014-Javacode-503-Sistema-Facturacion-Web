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
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import sys.dao.clienteDao;
import sys.dao.detalleFacturaDao;
import sys.dao.facturaDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.detalleFacturaDaoImp;
import sys.imp.facturaDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Factura;
import sys.model.Producto;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class facturaBean implements Serializable {

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;

    private Producto producto;
    private String codigoBarra;

    private List<Detallefactura> listaDetalleFactura;

    private String cantidadProducto;
    private String cantidadProducto2;
    private String productoSeleccionado;
    private Factura factura;
    
    private Long numeroFactura;
    
    private BigDecimal totalVentaFactura;
    
    private Vendedor vendedor;

    public facturaBean() {
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList<>();
        this.vendedor = new Vendedor();
        this.cliente = new Cliente();
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

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(String cantidadProducto2) {
        this.cantidadProducto2 = cantidadProducto2;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotalVentaFactura() {
        return totalVentaFactura;
    }

    public void setTotalVentaFactura(BigDecimal totalVentaFactura) {
        this.totalVentaFactura = totalVentaFactura;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

        
    
    //Metodo para agregar los datos de los clientes, por medio del dialogClientes
    public void agregarDatosCliente(Integer codCliente) {
        this.transaction = null;
        this.session = null;
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);

            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Metodo para agregar los datos del cliente buscado por codCliente
    public void agregarDatosCliente2() {
        this.transaction = null;
        this.session = null;

        try {
            if (this.codigoCliente == null) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //Obtener los datos del producto en la variable objeto cliente, segun el codigo del producto segun el codigo barra
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);

            if (this.cliente != null) {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado al detalle"));
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Producto no encontrado"));
            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Metodo para solicitar la cantidad de producto a vender
    public void pedirCantidadProducto(String codBarra) {
        this.productoSeleccionado = codBarra;
    }

    //Metodo para agregar los datos del producto, por medio del dialogProductos
    public void agregarDatosProducto() {
        this.transaction = null;
        this.session = null;
        try {
            if (!(this.cantidadProducto.matches("[0-9]*"))
                    || this.cantidadProducto.equals("0")
                    || this.cantidadProducto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
                this.cantidadProducto = "";
            } else {

                this.session = HibernateUtil.getSessionFactory().openSession();
                productoDao pDao = new productoDaoImp();

                this.transaction = this.session.beginTransaction();

                //Obtener los datos del cliente en la variable objeto cliente, segun el codigo del cliente
                this.producto = pDao.obtenerProductoPorCodBarra(this.session, this.productoSeleccionado);
                System.out.println("agregarDatosProducto-0020-nombre: " + this.producto.getNombreProducto());

                this.listaDetalleFactura.add(new Detallefactura(null,
                        null,
                        this.producto.getCodBarra(),
                        this.producto.getNombreProducto(),
                        Integer.parseInt(this.cantidadProducto),
                        this.producto.getPrecioVenta(),
                        BigDecimal.valueOf(
                                Integer.parseInt(this.cantidadProducto)
                                * this.producto.getPrecioVenta()
                        )
                ));

                this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado"));

                //llamada al metodo calcular totalFacturaVenta
                this.totalFacturaVenta();

                this.cantidadProducto = "";

            }

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //Metodo para mostrar el dialogCantidadProducto2
    public void mostrarCantidadProducto2() {
        this.transaction = null;
        this.session = null;

        try {
            if (this.codigoBarra.equals("")) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();

            this.transaction = this.session.beginTransaction();

            //Obtener los datos del producto en la variable objeto producto, segun el codigo de barra
            this.producto = pDao.obtenerProductoPorCodBarra(this.session, this.codigoBarra);

            if (this.producto != null) {
                // Solicitar mostra el dialogCantidadProducto2
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogCantidadProducto2').show();");
                this.codigoBarra = null;

            } else {
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrecto", "Producto no encontrado"));
            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }

        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }

    }

    //Metodo para agregar los datos del producto buscado por codBarra
    public void agregarDatosProducto2() {
        if (!(this.cantidadProducto2.matches("[0-9]*"))
                || this.cantidadProducto2.equals("0")
                || this.cantidadProducto2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
            this.cantidadProducto2 = "";
        } else {
            this.listaDetalleFactura.add(new Detallefactura(null,
                    null,
                    this.producto.getCodBarra(),
                    this.producto.getNombreProducto(),
                    Integer.parseInt(this.cantidadProducto2),
                    this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(
                            Integer.parseInt(this.cantidadProducto2)
                            * this.producto.getPrecioVenta()
                    )
            ));
            this.cantidadProducto2 = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado al detalle"));
            this.totalFacturaVenta();
        }
    }

    //metodo para calcular el total a vender en la factura
    public void totalFacturaVenta() {
        this.totalVentaFactura = new BigDecimal("0");
        //BigDecimal totalFacturaVenta = new BigDecimal("0");
        //float totalFacturaVenta;

        try {
            for (Detallefactura item : listaDetalleFactura) {
                BigDecimal totalVentaPorProducto = BigDecimal.valueOf(item.getPrecioVenta()).multiply(new BigDecimal(item.getCantidad()));
                item.setTotal(totalVentaPorProducto);
                totalVentaFactura = totalVentaFactura.add(totalVentaPorProducto);
                //totalFacturaVenta.setScale(2, BigDecimal.ROUND_UP);
            }
            this.factura.setTotalVenta(totalVentaFactura);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Metodo para quitar un producto de la factura
    public void quitarProductoDetalleFactura(String codBarra, Integer filaSeleccionada){
        try{
            int i=0;
            for(Detallefactura item: this.listaDetalleFactura){
                if(item.getCodBarra().equals(codBarra) && filaSeleccionada.equals(i)){
                    this.listaDetalleFactura.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se ha retirado el producto de la factura"));
            //invocamos al metod calcularFactura, para actualizar el total a vender
            this.totalFacturaVenta();
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }
    
    // Metodos para editar la cantidad del producto en la tabla productosFactura
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informacion", "Se modifico la cantidad"));
        // Invocar al metodo calcularTotalFactura para actualizar el total a vender
        this.totalFacturaVenta();
        
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informacion", "No se realizo ningun cambio"));
    }
    
    
    //Metodo para generar el numero de factura
    public void numeracionFactura(){
        this.session = null;
        this.transaction = null;
                
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            
            facturaDao fDao = new facturaDaoImp();
            // verificar si hay registros en la tabla de la BD
            this.numeroFactura = fDao.obtenerTotalRegistrosEnFactura(this.session);
            
            if (this.numeroFactura <= 0 || this.numeroFactura == null){
                this.numeroFactura = Long.valueOf("1");
            } else {
                // recuperamos el utlimo registro que exista en la BD
                this.factura = fDao.obtenerUltimoRegistro(this.session);
                this.numeroFactura = Long.valueOf(this.factura.getNumeroFactura()+1);
                // limpiar la variable totalVentaFactura
                this.totalVentaFactura = new BigDecimal("0");
            }
            
            this.transaction.commit();
        } catch (Exception e) {
            if(this.transaction!=null){
                this.transaction.rollback();
            }
        } finally{
            if(this.session!=null){
                this.session.close();
            }
        }
        
    }
    
    // Metodo para limpiar la factura 
    public void limpiarFactura(){
        this.cliente = new Cliente();
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList();
        this.numeroFactura = null;
        this.totalVentaFactura = null;
        
        // invoca el metodo para desactivar controles en la factura
        this.disableButton();
    }
    
    // Metodo para guardar venta
    public void guardarVenta(){
        System.out.println("<<<guardarVenta>>>--0010--");
        this.session = null;
        this.transaction = null;
        this.vendedor.setCodVendedor(2);
        this.cliente.setCodCliente(2);
        System.out.println("<<<guardarVenta>>>--0020--");
        
        try {
            System.out.println("<<<guardarVenta>>>--0030--");
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            facturaDao fDao = new facturaDaoImp();
            detalleFacturaDao dFDao = new detalleFacturaDaoImp();
            
            System.out.println("<<<guardarVenta>>>--0040--");
            this.transaction = this.session.beginTransaction();
            
            // datos para guardar en la tabla factura de la bd
            System.out.println("<<<guardarVenta>>>--0050--");
            this.factura.setNumeroFactura(this.numeroFactura.intValue());
            this.factura.setCliente(this.cliente);
            this.factura.setVendedor(this.vendedor);
            
            System.out.println("<<<guardarVenta>>>--0060--");
            // hacemos el insert en la tabla factura de la fd
            fDao.guardarVentaFactura(this.session, this.factura);
            
            System.out.println("<<<guardarVenta>>>--0070--");
            // recupera el ultimo registro de la tabla factura
            this.factura = fDao.obtenerUltimoRegistro(this.session);
            
            // recorremos el arraylist para guardar cada registro en la tabla de bd
            System.out.println("<<<guardarVenta>>>--0080--");
            for (Detallefactura item : listaDetalleFactura){
                this.producto = pDao.obtenerProductoPorCodBarra(this.session, item.getCodBarra());
                item.setFactura(this.factura);
                item.setProducto(this.producto);
                
                // hacemos el insert en la tabla detalle factura de la bd
                dFDao.guardarVentaDetalleFactura(this.session, item);
                
            }
                
            System.out.println("<<<guardarVenta>>>--0090--");   
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta Registrada"));
            
            System.out.println("<<<guardarVenta>>>--0100--");
            this.limpiarFactura();
            
        } catch (Exception e){
            System.out.println("<<<guardarVenta>>>--0110--");
            System.out.println(e.getMessage());
            if (this.transaction != null){
                this.transaction.rollback();
            }
        } finally {
            System.out.println("<<<guardarVenta>>>--0120--");
            if(this.session!=null){
                this.session.close();
            }
        }
            System.out.println("<<<guardarVenta>>>--0130--");
    }
    
    // Metodos para activar o desactiva los controles en la factura
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }
    
    public void enableButton(){
        enabled = true;
    }
    
    public void disableButton(){
        enabled = false;
    }
}
