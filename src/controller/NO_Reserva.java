package controller;

public class NO_Reserva {
    public Reserva reservas;
	public NO_Reserva prox;
	public NO_Reserva anterior;
	
	public NO_Reserva (Reserva reservas) {
		this.reservas = reservas;
		prox = null;
		anterior = null;
	}
}