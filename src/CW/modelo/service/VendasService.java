/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CW.modelo.service;

import CW.BD.VendasDao;
import CW.modelo.entidade.Vendas;
import java.util.List;

/**
 *
 * @author xca
 */
public class VendasService {
    
    private VendasDao dao;
    
    public VendasService(){
        dao = new VendasDao();
    }
    
    public void salvar(Vendas venda){
        dao.inserir(venda);
    }
    
    public void deletar(Vendas venda){
        dao.deletar(venda);        
    }
    
    public List<Vendas> listarVendas(){
        return dao.listarTodasVendas();
    }

    public Vendas encontrarVendaPorID(int id){
        return dao.encontrarVendaPorID(id);
    }
    
    public Vendas getUltimaVenda(){
        return dao.getUltimaVenda();
    }
}
