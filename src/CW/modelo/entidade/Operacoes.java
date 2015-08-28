package CW.modelo.entidade;

import java.sql.Timestamp;
import java.util.Date;

public abstract class Operacoes {

	private int id;
	private Produtos[] produtos = new Produtos[100];
	private Date data;
	private int[] quantidades = new int[100];
	private java.sql.Timestamp timestamp;
        private int numeroProdutos;
        private float total;
	
	public Operacoes(Produtos[] produto, int[] quantidade) {
		this.produtos = produto;
		this.quantidades = quantidade;
	}

	public Operacoes() {
		
	}

	public Produtos getProduto(int index) {
		return produtos[index];
	}

        public Produtos[] getListaProdutos() {
		return produtos;
	}
        
	public void setProduto(Produtos produto, int index) {
		this.produtos[index] = produto;
	}

	public void setListaProdutos(Produtos[] produto) {
		this.produtos = produto;
	}        
        
	public java.sql.Timestamp getData() {
		data = new Date();
		timestamp = new Timestamp(data.getTime()); 
		return timestamp;
	}
	
	public void setData(java.sql.Timestamp timestamp){
		this.timestamp = timestamp;
	}
        
        public void setData(){
                data = new Date();
		this.timestamp = new Timestamp(data.getTime());
	}

	public int getQuantidade(int index) {
		return quantidades[index];
	}

        public int[] getListaQuantidades() {
		return quantidades;
	}
        
	public void setQuantidade(int quantidade, int index) {
		this.quantidades[index] = quantidade;
	}

      	public void setListaQuantidades(int[] quantidades) {
		this.quantidades = quantidades;
	}
       
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
        
        public int getNumeroProdutos() {
                return numeroProdutos;
        }

        public void setNumeroProdutos(int numeroProdutos) {
                this.numeroProdutos = numeroProdutos;
        }
	
}
