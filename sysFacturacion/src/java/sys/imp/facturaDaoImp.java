/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sys.dao.facturaDao;
import sys.model.Factura;

/**
 *
 * @author sgrsm
 */
public class facturaDaoImp implements facturaDao{

    @Override
    public Factura obtenerUltimoRegistro(Session session) throws Exception {
        String hql = "FROM Factura ORDER BY codFactura DESC";
        Query q = session.createQuery(hql).setMaxResults(1);
        return (Factura) q.uniqueResult();
    }

    @Override
    public Long obtenerTotalRegistrosEnFactura(Session session) {
        System.out.println("--obtenerTotalRegistrosEnFactura--0010--");
        String hql = "SELECT COUNT(*) FROM Factura";
        System.out.println("--obtenerTotalRegistrosEnFactura--0020--");
        Query q = session.createQuery(hql);
        System.out.println("--obtenerTotalRegistrosEnFactura--0030--(Long) q.uniqueResult(): "+(Long) q.uniqueResult());
        return (Long) q.uniqueResult();
    }
    
}
