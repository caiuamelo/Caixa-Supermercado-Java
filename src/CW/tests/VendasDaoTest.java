package CW.tests;


import CW.BD.VendasDao;
import CW.modelo.entidade.Produtos;
import CW.modelo.entidade.Vendas;

public class VendasDaoTest {
	public static void main (String[] args){
		
		Produtos produto = new Produtos("Copos", 12345678, 1.20f, 10);
//		Vendas venda = new Vendas(produto, 10); 
		VendasDao vendasdao = new VendasDao();
		
//		vendasdao.inserir(venda);
			
	}
}
