package enfeite;

public class Enfeites {
		private int codTema;
		private String temaEnfeite;
		private String descricaoEnfeite;
		private double preco;
	
	public Enfeites(int codTema, String temaEnfeite, String descricaoEnfeite, double preco) {
		this.codTema = codTema;
		this.temaEnfeite = temaEnfeite;
		this.descricaoEnfeite = descricaoEnfeite;
		this.preco = preco;
	} // fim enfeites

	public int getCodTema() {
		return codTema;
	}

 	public void setCodTema(int codTema) {
 		this.codTema = codTema;
 	}

	public String getTemaEnfeite() {
		return temaEnfeite;
	}

	public void setTemaEnfeite(String temaEnfeite) {
		this.temaEnfeite = temaEnfeite;
	}

	public String getDescricaoEnfeite() {
		return descricaoEnfeite;
	}

	public void setDescricaoEnfeite(String descricaoEnfeite) {
		this.descricaoEnfeite = descricaoEnfeite;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}  // fim classe.