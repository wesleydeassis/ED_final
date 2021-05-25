package devolucao;

import java.time.LocalDate;

public class Devolucoes {
	
	private LocalDate DataFesta;
    private LocalDate DataPrevista;
    private LocalDate DataRetorno;
    private String Cliente;
    private String Enfeite;
    private int atraso;
    
	public Devolucoes(LocalDate dataFesta, LocalDate dataPrevista, LocalDate dataRetorno, String cliente,
			String enfeite, int atraso) {
		
		DataFesta = dataFesta;
		DataPrevista = dataPrevista;
		DataRetorno = dataRetorno;
		Cliente = cliente;
		Enfeite = enfeite;
		this.atraso = atraso;
	}

	public LocalDate getDataFesta() {
		return DataFesta;
	}

	public void setDataFesta(LocalDate dataFesta) {
		DataFesta = dataFesta;
	}

	public LocalDate getDataPrevista() {
		return DataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		DataPrevista = dataPrevista;
	}

	public LocalDate getDataRetorno() {
		return DataRetorno;
	}

	public void setDataRetorno(LocalDate dataRetorno) {
		DataRetorno = dataRetorno;
	}

	public String getCliente() {
		return Cliente;
	}

	public void setCliente(String cliente) {
		Cliente = cliente;
	}

	public String getEnfeite() {
		return Enfeite;
	}

	public void setEnfeite(String enfeite) {
		Enfeite = enfeite;
	}

	public int getAtraso() {
		return atraso;
	}

	public void setAtraso(int atraso) {
		this.atraso = atraso;
	}
	
	
    
    

}
