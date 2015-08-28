package CW.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import CW.Framework.DaoHelper;
import CW.modelo.entidade.Compras;
import CW.modelo.entidade.Produtos;
import java.util.ArrayList;
import java.util.List;


public class ComprasDao {
	DaoHelper daohelper = new DaoHelper();
	java.sql.PreparedStatement statement = null;
	ResultSet resultset = null;
	
	public void inserir(Compras compra){
	
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("INSERT INTO compras (codigoprodutos,quantidades,data) VALUES (?,?,?)");
			
			statement.setString(1, getProdutosString(compra));	    
			statement.setString(2, getQuantidadesString(compra));
			statement.setTimestamp(3,compra.getData());
					
			statement.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
	}
        
        

	public void deletar(Compras compra){
		
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("DELETE FROM produtos WHERE id=?");
				    
			statement.setLong(1,compra.getId());

			statement.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
		
	}

        public List<Compras> listarTodasCompras(){
		
		List<Compras> vendas = new ArrayList<>();
		daohelper.getConnect();
		ResultSet rset;
		
		try {
			statement = daohelper.connection.prepareStatement("SELECT * FROM vendas");
			rset = statement.executeQuery();
			while (rset.next()){
				Compras venda = new Compras();
				venda.setId(rset.getInt(1));
				venda.setListaProdutos(StringToProdutos(rset.getString(2)));
				venda.setListaQuantidades(StringToQuantidades(rset.getString(3)));
				venda.setData(rset.getTimestamp(4));
				vendas.add(venda);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			daohelper.ReleaseAll(statement, resultset);
		
		}
			
		return vendas;
	}
        
        public String getProdutosString(Compras compra){
            
            int i = 0;
            String produtosString;
            produtosString = ((Long) compra.getListaProdutos()[i].getCodigo()).toString();
            
            for(i=1;i<compra.getListaProdutos().length;i++) {
                        
                produtosString += "," + ((Long) compra.getListaProdutos()[i].getCodigo()).toString();
            
            }
                
            return produtosString;
            
        
        }

        
        public String getQuantidadesString(Compras compra){
            
            int i = 0;
            String quantidadesString;
            quantidadesString = ((Integer) compra.getListaProdutos()[i].getQuantidade()).toString();
            
            for(i=1;i<compra.getListaProdutos().length;i++) {
                        
                quantidadesString += "," + ((Integer) compra.getListaProdutos()[i].getQuantidade()).toString();
            
            }
                
            return quantidadesString;
        
        }

        public Produtos[] StringToProdutos(String stringProdutos) {
            
            int i;
            String[] parts;
            Produtos[] listaProdutos = null;
            parts = stringProdutos.split(",");
            
            ProdutosDao produtosDao = new ProdutosDao();
            
            for(i=0;i<parts.length;i++) {
             
                listaProdutos[i] = produtosDao.encontrarProdutoPorCodigo(Long.parseLong(parts[i]));
                
            }
                      
            return listaProdutos;
            
        }

        public int[] StringToQuantidades(String stringProdutos) {
            
            int i;
            String[] parts;
            int[] listaQuantidades = null;
            parts = stringProdutos.split(",");
            
            ProdutosDao produtosDao = new ProdutosDao();
            
            for(i=0;i<parts.length;i++) {
             
                listaQuantidades[i] = Integer.parseInt(parts[i]);
                
            }
                      
            return listaQuantidades;
            
        }
        
        
        
}
