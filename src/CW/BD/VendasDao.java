package CW.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CW.modelo.entidade.Produtos;
import CW.modelo.entidade.Vendas;
import CW.Framework.DaoHelper;
import javax.swing.JOptionPane;

public class VendasDao {
		
	DaoHelper daohelper = new DaoHelper();
	java.sql.PreparedStatement statement = null;
	ResultSet resultset = null;
	
	public void inserir(Vendas venda){
	
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("INSERT INTO vendas (codigoprodutos,quantidades,data,total) VALUES (?,?,?,?)");
			
			statement.setString(1, getProdutosString(venda));	    
			statement.setString(2,getQuantidadesString(venda));
			statement.setTimestamp(3,venda.getData());
                        statement.setFloat(4,venda.getTotal());
        		statement.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
	}
	
	public void deletar(Vendas venda){
		
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("DELETE FROM produtos WHERE id=? LIMIT 1");
				    
			statement.setLong(1,venda.getId());

			statement.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
		
	}

                public List<Vendas> listarTodasVendas(){
		
		List<Vendas> vendas = new ArrayList<>();
		daohelper.getConnect();
		ResultSet rset;
		
		try {
			statement = daohelper.connection.prepareStatement("SELECT * FROM vendas");
			rset = statement.executeQuery();
			while (rset.next()){
				Vendas venda = new Vendas();
				venda.setId(rset.getInt(1));
				venda.setListaProdutos(StringToProdutos(rset.getString(2)));
				venda.setListaQuantidades(StringToQuantidades(rset.getString(3)));
				venda.setData(rset.getTimestamp(4));
				venda.setTotal(rset.getFloat(5));
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
        
        public String getProdutosString(Vendas venda){
            
            int i = 0;
            String produtosString;
            produtosString = ((Long) venda.getProduto(i).getCodigo()).toString();
            
            for(i=1;i<venda.getNumeroProdutos();i++) {
                
                produtosString += "," + ((Long) venda.getProduto(i).getCodigo()).toString();
            
            }
                
            return produtosString;
                    
        }

        
        public String getQuantidadesString(Vendas venda){
            
            int i = 0;
            String quantidadesString;
            quantidadesString = ((Integer) venda.getQuantidade(i)).toString();
            
            for(i=1;i<venda.getNumeroProdutos();i++) {
                        
                quantidadesString += "," + ((Integer) venda.getQuantidade(i)).toString();
            
            }
                
            return quantidadesString;
        
        }

        public Produtos[] StringToProdutos(String stringProdutos) {
            
            int i;
            String[] parts;
            Produtos[] produtos = new Produtos[100];
            parts = stringProdutos.split(",");
            
            System.out.println(stringProdutos);
            System.out.println(parts[0]);
            
            ProdutosDao produtosDao = new ProdutosDao();
            
            for(i=0;i<parts.length;i++) {
             
                produtos[i] = produtosDao.encontrarProdutoPorCodigo(Long.parseLong(parts[i]));
                
            }
                      
            return produtos;
            
        }

        public int[] StringToQuantidades(String stringProdutos) {
            
            int i;
            String[] parts;
            int[] listaQuantidades = new int[100];
            parts = stringProdutos.split(",");
            
            ProdutosDao produtosDao = new ProdutosDao();
            
            for(i=0;i<parts.length;i++) {
             
                listaQuantidades[i] = Integer.parseInt(parts[i]);
                
            }
                      
            return listaQuantidades;
            
        }
        
	public Vendas encontrarVendaPorID(int id){
		
            Vendas venda = new Vendas();
		
            venda.setTotal(-1);
                
            daohelper.getConnect();
		
            try {
		statement = daohelper.connection.prepareStatement("SELECT * FROM vendas WHERE id=? LIMIT 1");
					
		statement.setInt(1, id);
			
                resultset = statement.executeQuery();
		if(resultset.first()) {
                            
                    venda.setId(resultset.getInt(1));
                    venda.setListaProdutos(StringToProdutos(resultset.getString(2)));
                    venda.setListaQuantidades(StringToQuantidades(resultset.getString(3)));
                    venda.setData(resultset.getTimestamp(4));
                    venda.setTotal(resultset.getFloat(5));
                    
                            
                }else {
                                          
                    JOptionPane.showMessageDialog(null,"Venda não encontrada");
                            
                }
            } catch (SQLException e) {
			
		e.printStackTrace();
            }
		
            daohelper.ReleaseAll(statement, resultset);
		
            return venda;

                   
	}
        
        public Vendas getUltimaVenda(){
		
            Vendas venda = new Vendas();
		
            venda.setTotal(-1);
                
            daohelper.getConnect();
		
            try {
		statement = daohelper.connection.prepareStatement("SELECT * FROM vendas ORDER BY id DESC LIMIT 1");
					
		resultset = statement.executeQuery();
		if(resultset.first()) {
                    venda.setId(resultset.getInt(1));
                    //System.out.println(venda.getId());
                    venda.setListaProdutos(StringToProdutos(resultset.getString(2)));
                    venda.setListaQuantidades(StringToQuantidades(resultset.getString(3)));
                    venda.setData(resultset.getTimestamp(4));
                    venda.setTotal(resultset.getFloat(5));
                    
                            
                }else {
                                          
                    JOptionPane.showMessageDialog(null,"Venda não encontrada");
                            
                }
            } catch (SQLException e) {
			
		e.printStackTrace();
            }
		
            daohelper.ReleaseAll(statement, resultset);
		
            return venda;
		
	}
        
	
}