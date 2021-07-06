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
import sys.dao.clienteDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Factura;
import sys.model.Producto;
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

    private Integer cantidadProducto;
    private Integer cantidadProducto2;
    private String productoSeleccionado;
    private Factura factura;

    public facturaBean() {
        this.factura = new Factura();
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

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(Integer cantidadProducto2) {
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
                    this.cantidadProducto,
                    this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(
                            this.cantidadProducto.floatValue()
                            * this.producto.getPrecioVenta()
                    )
            ));

            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado"));

            //llamada al metodo calcular totalFacturaVenta
            this.totalFacturaVenta();

            this.cantidadProducto = null;

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

        this.listaDetalleFactura.add(new Detallefactura(null,
                    null,
                    this.producto.getCodBarra(),
                    this.producto.getNombreProducto(),
                    this.cantidadProducto2,
                    this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(
                            this.cantidadProducto2.floatValue()
                            * this.producto.getPrecioVenta()
                    )
            ));
        this.cantidadProducto2 = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del producto agregado al detalle"));
        this.totalFacturaVenta();
    }

    //metodo para calcular el total a vender en la factura
    public void totalFacturaVenta() {
        BigDecimal totalFacturaVenta = new BigDecimal("0");
        //float totalFacturaVenta;

        try {
            for (Detallefactura item : listaDetalleFactura) {
                BigDecimal totalVentaPorProducto = BigDecimal.valueOf(item.getPrecioVenta()).multiply(new BigDecimal(item.getCantidad()));
                item.setTotal(totalVentaPorProducto);
                totalFacturaVenta = totalFacturaVenta.add(totalVentaPorProducto);
                //totalFacturaVenta.setScale(2, BigDecimal.ROUND_UP);
            }
            this.factura.setTotalVenta(totalFacturaVenta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
