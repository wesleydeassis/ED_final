package view;

/*
Elen Carvalho de Oliveira - RA: 1110482013042
Luiz Antônio de Arruda - RA: 11100482013040
Wesley Silva de Assis - RA: 1110482013028
*/

import javax.swing.JOptionPane;
import cliente.OperacoesClientes;
import controller.OperacoesReserva;
import enfeite.OperacoesEnfeite;

public class Main {
	public static void main(String[] args) {		

		OperacoesEnfeite enfeites = new OperacoesEnfeite();
		OperacoesClientes clientes = new OperacoesClientes();
		OperacoesReserva reserva = new OperacoesReserva();

		int opcao = 0;
		
		while (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Buffet: \n\n"+ 
							"1 - Cadastrar Enfeites \n"+ 
							"2 - Cadastrar Clientes\n"+ 
							"3 - Registrar Reserva \n"+ 
							"9 - Sair "));
			
			switch (opcao) {
				case 1:
					enfeites.MenuEnfeites();
				break;

				case 2:
					clientes.MenuClientes();
				break;

				case 3:
					reserva.MenuReservar();
				break;
				
				case 9:
					JOptionPane.showMessageDialog(null, "Finalizando o programa");
				break;
					
				default:
				break;
			} // fim switch
		} // fim while
    } //fim main
} // fim classe