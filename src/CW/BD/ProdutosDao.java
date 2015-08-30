package CW.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CW.modelo.entidade.Produtos;
import CW.Framework.DaoHelper;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProdutosDao {
		
	DaoHelper daohelper = new DaoHelper();
	java.sql.PreparedStatement statement = null;
	ResultSet resultset = null;
	
	public void inserir(Produtos produto){
	
		daohelper.getConnect();
		
		try {
                        
                        statement = daohelper.connection.prepareStatement("INSERT INTO produtos (codigo,nome,preco,quantidade) VALUES (?,?,?,?)");
			
			statement.setLong(1, produto.getCodigo());	    
			statement.setString(2,produto.getNome());
			statement.setDouble(3,produto.getPreco());
			statement.setInt(4,produto.getQuantidade());
			
			statement.execute();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
	}
	
	public void atualizar(Produtos produto){
		
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("UPDATE produtos SET nome=?,preco=?,quantidade=? WHERE codigo=?");
				    
			statement.setString(1,produto.getNome());
			statement.setDouble(2,produto.getPreco());
			statement.setInt(3,produto.getQuantidade());
			statement.setLong(4, produto.getCodigo());
			
			statement.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
	}

	public void deletar(Produtos produto){
		
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("DELETE FROM produtos WHERE codigo=?");
				    
			statement.setLong(1,produto.getCodigo());

			statement.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
		
	}
	
	public List<Produtos> listarTodosProdutos(){
		
		List<Produtos> produtos = new ArrayList<Produtos>();
		daohelper.getConnect();
				
		try {
			statement = daohelper.connection.prepareStatement("SELECT * FROM produtos ORDER BY nome");
			resultset = statement.executeQuery();
			while (resultset.next()){
				Produtos produto = new Produtos();
				produto.setId(resultset.getInt(1));
				produto.setCodigo(resultset.getLong(2));
				produto.setNome(resultset.getString(3));
				produto.setPreco(resultset.getDouble(4));
				produto.setQuantidade(resultset.getInt(5));
				produtos.add(produto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			daohelper.ReleaseAll(statement, resultset);
		
		}
			
		return produtos;
	}
	
	public Produtos encontrarProdutoPorCodigo(long codigo){
		
		Produtos produto = new Produtos();
		
                produto.setPreco(-1);
                
		daohelper.getConnect();
		
		try {
			statement = daohelper.connection.prepareStatement("SELECT * FROM produtos WHERE codigo=? LIMIT 1");
					
			statement.setLong(1, codigo);
			
                        resultset = statement.executeQuery();
			if(resultset.first()) {
                            
                            produto.setId(resultset.getInt(1));
                            produto.setCodigo(resultset.getLong(2));
                            produto.setNome(resultset.getString(3));
                            produto.setPreco(resultset.getFloat(4));
                            produto.setQuantidade(resultset.getInt(5));
                            
                        }else {
                                          
                            //JOptionPane.showMessageDialog(null,"Produto não encontrado");
                            
                        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		daohelper.ReleaseAll(statement, resultset);
		
		return produto;
		
	}
	
}