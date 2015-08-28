/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CW.modelo.service;

import CW.BD.ComprasDao;
import CW.modelo.entidade.Compras;
import java.util.List;

/**
 *
 * @author xca
 */
public class ComprasService {

        private ComprasDao dao;
    
    public ComprasService(){
        dao = new ComprasDao();
    }
    
    public void salvar(Compras compra){
        dao.inserir(compra);
    }
    
    public void deletar(Compras compra){
        dao.deletar(compra);        
    }
    
    public List<Compras> listarCompras(){
        return dao.listarTodasCompras();
    }

    
}
