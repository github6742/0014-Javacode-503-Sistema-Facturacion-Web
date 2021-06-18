/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Vendedor;

/**
 *
 * @author sgrsm
 */
public interface vendedorDao {
    public List<Vendedor> listarVendedores();
    public void newVendedor(Vendedor vendedor);
    public void updateVendedor(Vendedor vendedor);
    public void deleteVendedor(Vendedor vendedor);
    
}
