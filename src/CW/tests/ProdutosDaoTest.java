package CW.tests;

import java.util.List;
import static java.lang.System.out;

import CW.modelo.entidade.Produtos;
import CW.BD.ProdutosDao;

public class ProdutosDaoTest {
	
	public static void main (String[] args){
		
		//Produtos produto = new Produtos("Copos", 12345678, 1.20f, 10); 
		ProdutosDao produtosDao = new ProdutosDao();
		Produtos p;
		//List<Produtos> produtos = produtosDao.listarTodosProdutos();
		long codigo = 1234567;
		
		p = produtosDao.encontrarProdutoPorCodigo(codigo);
		out.println(p.getId());
		out.println(p.getCodigo());
		out.println(p.getNome());
		out.println(p.getPreco());
		out.println(p.getQuantidade());
		
		
		
		
		/*for (Produtos produtos2 : produtos) {
			System.out.println(produtos2.getNome());
		}
		*/
		//produtosdao.inserir(produto);
			
	}
	
	
	
}
