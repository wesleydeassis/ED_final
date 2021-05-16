package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import cliente.Clientes;
import cliente.OperacoesClientes;
//import cliente.OperacoesClientes;
import enfeite.Enfeites;
//import enfeite.OperacoesEnfeite;

public class OperacoesReserva {

	private LocalDate DataFesta = LocalDate.now();
    private LocalDate DataPrevista = DataFesta.plusDays(3);
    private LocalDate DataRetorno = LocalDate.now();
    private String HoraInicio = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	private String HoraPrevisto = LocalTime.now().plusHours(12).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    private String HoraRetorno = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    private String FormaDePagamento;
    private double PrecoFinal;
	private int cpf;
    private String Enfeite;

	private int codTema;
	private String temaEnfeite;
	private String descricaoEnfeite;
	private double preco;

	private int CPF_RNE;
	private String Nome;
	private String Telefone;
	private String Endereco;
	private LocalDate DataCadastro = LocalDate.now();
	private int QtdeAluguel;
	
	

	private NO_Reserva inicio;

	Clientes cliente = new Clientes(CPF_RNE, Nome, Endereco, Telefone, DataCadastro, QtdeAluguel);
	Enfeites enfeite = new Enfeites(codTema, temaEnfeite, descricaoEnfeite, preco);

	public OperacoesReserva() {
		inicio = null;
	}

    public void MenuReservar() {
		int opcao = 0;
		while (opcao != 9) {

			opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu de Reserva: "+
			"\n1- Realizar uma reserva"+
			"\n2- Realizar uma devolução"+
			"\n3- Remover uma devolução"+
			"\n4- Consultar reservas"+
			"\n9- Voltar  "));
			
			switch (opcao) {
				case 1:
					//JOptionPane.showMessageDialog(null, "Em desenvolvimento ... ");
					RealizarReserva();
				break;

				case 2:	
                    JOptionPane.showMessageDialog(null, "Em desenvolvimento ... ");
					//RealizarDevolucao()
				break;

				case 3:	
                    JOptionPane.showMessageDialog(null, "Em desenvolvimento ... ");
					//RemoverReserva()
				break;

				case 4:	
                    JOptionPane.showMessageDialog(null, "Em desenvolvimento ... ");
					//ConsultarReserva()
				break;

				case 9:
					JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
				break;
				default:
				break;
			} // fim switch
		} // fim while
	} // fim Menu Reserva
    
	public void RealizarReserva() {
		OperacoesClientes buscarClientes = new OperacoesClientes();
		//OperacoesEnfeite buscarEnfeite = new OperacoesEnfeite();

		Reserva reserva = new Reserva(DataFesta, DataPrevista, DataRetorno, HoraInicio, HoraPrevisto, HoraRetorno, FormaDePagamento, PrecoFinal, Nome, Enfeite);

		cpf = Integer.parseInt(JOptionPane.showInputDialog("Informe o CPF ou RNE do cliente: "));
		String auxNome=buscarClientes.BuscarClientes(cpf, false);//passa boolean false para não ter erro em sysout
		
		if (auxNome == null){
				
			JOptionPane.showMessageDialog(null, "Cliente não localizado");
				MenuReservar();
				
		   }
		
			
			else
		
		//cliente = cliente.getNome();
		reserva.setCliente(auxNome);
		/*Precisamos de uma forma para validar se o "Cliente" já está cadastrado
		if (CPF_RNE.equalsIgnoreCase(buscarClientes.BuscarClientes(CPF_RNE))) {
			//Precisamos pegar a quantidade de aluguel do cliente e somar com mais um	
			Cliente = cliente.getNome();
			reserva.setCliente(Cliente);
		} else {
			JOptionPane.showMessageDialog(null, "Cliente não localizado");
			MenuReservar();
		}
		*/
		
		Enfeite = JOptionPane.showInputDialog("Informe o tema que deseja reservar: ");
		//buscarEnfeite.BuscarEnfeites(Enfeite);
		//Enfeite = enfeite.getTemaEnfeite();
		reserva.setEnfeite(Enfeite);
		/*Precisamos de uma forma para validar se o "Tema" já está cadastrado
		if (CPF_RNE.equalsIgnoreCase(buscarEnfeite.BuscarEnfeites(tema))) {
			reserva.setEnfeite(enfeite);
			CalcularDesconto(PrecoFinal);
			//Precisamos pegar o preço do tema e CalcularDesconto(PrecoFinal);
		} else {
			JOptionPane.showMessageDialog(null, "Tema não localizado");
			MenuReservar();
		}
		*/
		FormaDePagamento = JOptionPane.showInputDialog("Informe a forma de pagamento: ");
		reserva.setFormaDePagamento(FormaDePagamento);

		reserva.setDataFesta(DataFesta);
		reserva.setHoraInicio(HoraInicio);
		reserva.setDataPrevista(DataPrevista);
		reserva.setHoraRetorno(HoraPrevisto);
		PrecoFinal = Double.parseDouble(JOptionPane.showInputDialog("Informe o preco final: "));
		reserva.setPrecoFinal(PrecoFinal);
		
		if (inicio == null) {								// verifica se a lista esta vazia
			NO_Reserva n = new NO_Reserva(reserva);	
			inicio = n;
			n.prox = null;
			n.anterior = null;									
		}  // fim if
		
		else {
				NO_Reserva aux = inicio;				
				while (aux.prox != null) {					// buscando o ultimo elemento da lista	
					aux = aux.prox;						
				} // fim while
				NO_Reserva n = new NO_Reserva(reserva);		// cria um novo Nó
				aux.prox = n;	
				n.anterior = aux;
				n.prox = null;
		} // fim do else
	//	GravarReserva();
		JOptionPane.showMessageDialog(null, "Reserva realizada e gravada com sucesso!");  
		System.out.println("Reserva realizada: \n" + 
							//" Cliente: " + cliente.getNome() + 
							" Cliente: " + reserva.getCliente() + 
							
							
							//" - Tema: " + enfeite.getTemaEnfeite() + 
							" - Tema: " + reserva.getEnfeite() + 
							" - Forma de Pagamento: " + reserva.getFormaDePagamento() +
							" - Preço Final: " + reserva.getPrecoFinal() +
							" \n Data da Festa: " + reserva.getDataFesta() +
							" - Horário da Festa: " + reserva.getHoraInicio() +
							" \n Data de devolução: " + reserva.getDataPrevista() +
							" - Horário de devolução: " + reserva.getHoraPrevisto());
	 }
	// fim cadastro cliente
	
	/*
	
	public void GravarReserva()  {
		NO_Reserva aux = inicio;
		
		try {
			String fileName = "ArquivoReserva.txt";	
		    BufferedWriter gravar = new BufferedWriter(new FileWriter( fileName ));	
		
			while (aux != null) {
	            gravar.write("** Nova reserva: "); 
				gravar.newLine();

				Cliente = aux.reservas.getCliente();
	            gravar.write(aux.reservas.getCliente()); 
				gravar.newLine();

				Enfeite = aux.reservas.getEnfeite();
	            gravar.write(aux.reservas.getEnfeite()); 
				gravar.newLine();

				FormaDePagamento = aux.reservas.getFormaDePagamento();
	            gravar.write(aux.reservas.getFormaDePagamento()); 
				gravar.newLine();

				PrecoFinal = aux.reservas.getPrecoFinal();
	            gravar.write(String.valueOf(aux.reservas.getPrecoFinal())); 
				gravar.newLine();
				
				DataFesta = aux.reservas.getDataFesta();
	            gravar.write(aux.reservas.getDataFesta().toString()); 
				gravar.newLine();
				
				HoraInicio = aux.reservas.getHoraInicio();
	            gravar.write(String.valueOf(aux.reservas.getHoraInicio())); 
				gravar.newLine();

				DataPrevista = aux.reservas.getDataPrevista();
	            gravar.write(aux.reservas.getDataPrevista().toString()); 
				gravar.newLine();
				
				HoraPrevisto = aux.reservas.getHoraPrevisto();
	            gravar.write(String.valueOf(aux.reservas.getHoraPrevisto())); 
				gravar.newLine();

				aux = aux.prox;
			}
		     gravar.close();  			
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro na gravação!");
		}  	// fim try-catch
	} // fim gravar  cliente
*/
	public void RealizarDevolucao() {

	}

	public void RemoverReserva() {

	}

	public void ConsultarReserva() {

	}

	public double CalcularDesconto(double PrecoFinal) {

		return PrecoFinal;
	}
}