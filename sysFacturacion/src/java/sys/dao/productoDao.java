/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Producto;

/**
 *
 * @author sgrsm
 */
public interface productoDao {
    public List<Producto> listarProductos();
    public void newProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
    // este metodo se utilizar en la factura, facturaBean
    public Producto obtenerProductoPorCodBarra (Session session, String codBarra) throws Exception;
}
