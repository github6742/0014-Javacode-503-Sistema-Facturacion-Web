package sys.dao;

import org.hibernate.Session;
import sys.model.Factura;

public interface facturaDao {
    // Obtener el utlimo registro en la tabla factura de la BD
    public Factura obtenerUltimoRegistro(Session session) throws Exception;
    
    // Averigurar si la tabla posee registros
    public Long obtenerTotalRegistrosEnFactura(Session session);
    
    //
    public boolean guardarVentaFactura(Session session, Factura factura) throws Exception;
}
