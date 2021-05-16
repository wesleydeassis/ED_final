package cliente;

public class NO_Cliente {
    public Clientes clientes;
	public NO_Cliente prox;
	public NO_Cliente anterior;
	
	public NO_Cliente (Clientes clientes) {
		this.clientes = clientes;
		prox = null;
		anterior = null;
	}
}