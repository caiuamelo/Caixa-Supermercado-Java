package CW.modelo.service;

import java.util.List;

import CW.modelo.entidade.Produtos;
import CW.BD.ProdutosDao;

public class ProdutosService {
	
	private ProdutosDao dao;

	public ProdutosService(){
		dao = new ProdutosDao();
	}
	
	public void salvar(Produtos produto){
		
		
		dao.inserir(produto);
	
	}
	
        public void deletar(Produtos produto){
            dao.deletar(produto);
        }
        
	public List<Produtos> listarProdutos(){
            return dao.listarTodosProdutos();
	}
	public Produtos encontrarProdutoPorCodigo(long codigo){
        
            return dao.encontrarProdutoPorCodigo(codigo);
               
        }
	
        public void atualizar(Produtos produto){
        
            dao.atualizar(produto);
            
        }
	
}
