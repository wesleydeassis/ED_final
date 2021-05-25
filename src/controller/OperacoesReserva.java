package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import cliente.Clientes;
import cliente.NO_Cliente;
import cliente.OperacoesClientes;
import enfeite.Enfeites;
import enfeite.NO_Enfeite;
import enfeite.OperacoesEnfeite;
import devolucao.OperacoesDevolucao;

public class OperacoesReserva {

	private LocalDate DataFesta = LocalDate.now();
    private LocalDate DataPrevista = DataFesta.plusDays(3);
    private LocalDate DataRetorno = LocalDate.now();
    private String HoraInicio = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	private String HoraPrevisto = LocalTime.now().plusHours(12).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    private String HoraRetorno = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    private String FormaDePagamento;
    private double PrecoFinal;
	private String Cliente;
	private int QtdeAluguel;
    private String Enfeite;

	private int codTema;
	private String tema;
	private String descricao;
	private double preco;

	private String CPF_RNE;
	private String Nome;
	private String Telefone;
	private String Endereco;
	private LocalDate DataCadastro = LocalDate.now();

	private NO_Reserva inicio;

	Clientes cliente = new Clientes(CPF_RNE, Nome, Endereco, Telefone, DataCadastro);
	Enfeites enfeite = new Enfeites(codTema, tema, descricao, preco);
	
	NO_Cliente NoCliente = new NO_Cliente(cliente);
	NO_Enfeite NoEnfeite = new NO_Enfeite(enfeite);

	OperacoesClientes Clientes = new OperacoesClientes();
	OperacoesEnfeite Enfeites = new OperacoesEnfeite();
	OperacoesDevolucao Odv = new OperacoesDevolucao();
	private BufferedReader buffer;

	public OperacoesReserva() {
		inicio = null;
	}

    public void MenuReservar() {
		int opcao = 0;
		while (opcao != 9) {

			opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu de Reserva: "+
			"\n1- Realizar uma reserva"+
			"\n2- Devolução"+
			"\n3- Consultar todas as reservas"+
			"\n4- Buscar uma reserva"+
			"\n5- Buscar uma reserva"+
			"\n9- Voltar  "));
			
			switch (opcao) {
				case 1:
					RealizarReserva();
				break;

				case 2:	
					Odv.MenuDevolucao();
				break;

				case 3:	
					ListarReservas();
				break;

				case 4:	
					String reserva = JOptionPane.showInputDialog("Informe algo informado na reservas: ");
					try {
						String arquivo = "ArquivoReserva.txt";
						BuscarItemArquivo(reserva, arquivo);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Reserva não localizada");
					}
				break;
			
				case 5:	
					String devolucao = JOptionPane.showInputDialog("Informe algo informado na reservas: ");
					try {
						String arquivo = "ArquivoDevolucao.txt";
						BuscarItemArquivo(devolucao, arquivo);
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Reserva não localizada");
					}
				break;

				case 9:
					JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
				break;

				default:
					JOptionPane.showMessageDialog(null, "Opção inválida");
			} 
		} 
	} 
    
	public void RealizarReserva() {

		Reserva reservas = new Reserva(DataFesta, DataPrevista, DataRetorno, HoraInicio, HoraPrevisto, HoraRetorno, FormaDePagamento, PrecoFinal, QtdeAluguel, Cliente, Enfeite);

		Cliente = JOptionPane.showInputDialog("Informe o CPF/RNE ou nome do cliente: ");
		try {
			String arq = "ArquivoClientes.txt";
			if ( lerArquivos( arq, Cliente ) == true ) {	
				reservas.setCliente(Cliente);
			} else {
				JOptionPane.showMessageDialog(null, "Cliente não localizado, faça o cadastro dele");
				Clientes.CadastrarClientes();
				reservas.setCliente(Cliente);
			}

			QtdeAluguel = CalcularQtdeAluguel(Cliente);
			reservas.setQtdeAluguel(QtdeAluguel);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
		}

		Enfeite = JOptionPane.showInputDialog("Informe o tema que deseja reservar: ");
		try {
			String arq = "ArquivoEnfeites.txt";
			if ( lerArquivos( arq, Enfeite ) == true ) {
				reservas.setEnfeite(Enfeite);
				//CalcularDesconto(PrecoFinal); //Precisamos pegar o preço do tema e CalcularDesconto(PrecoFinal);
			} else {
				JOptionPane.showMessageDialog(null, "Tema não localizado, faça o cadastro dele");
				Enfeites.CadastrarEnfeites();
				reservas.setEnfeite(Enfeite);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
		}

		FormaDePagamento = JOptionPane.showInputDialog("Informe a forma de pagamento: ");
		reservas.setFormaDePagamento(FormaDePagamento);

		reservas.setDataFesta(DataFesta);
		reservas.setHoraInicio(HoraInicio);
		reservas.setDataPrevista(DataPrevista);
		reservas.setHoraRetorno(HoraPrevisto);
		reservas.setPrecoFinal(PrecoFinal);
		
		if (inicio == null) {								
			NO_Reserva n = new NO_Reserva(reservas);	
			inicio = n;
			n.prox = null;
			n.anterior = null;									
		}  
		else {
			NO_Reserva aux = inicio;				
			while (aux.prox != null) {				
				aux = aux.prox;						
			} 
			NO_Reserva n = new NO_Reserva(reservas);		
			aux.prox = n;	
			n.anterior = aux;
			n.prox = null;
		} 
		GravarReserva();
		JOptionPane.showMessageDialog(null, "Reserva realizada e gravada com sucesso!");  
		System.out.println("Reserva realizada: \n" + 
							" Cliente: " + reservas.getCliente() + 
							" - Tema: " + reservas.getEnfeite() + 
							" - Forma de Pagamento: " + reservas.getFormaDePagamento() +
							" - Preço Final: " + reservas.getPrecoFinal() +
							" \n Data da Festa: " + reservas.getDataFesta() +
							" - Horário da Festa: " + reservas.getHoraInicio() +
							" \n Data de devolução: " + reservas.getDataPrevista() +
							" - Horário de devolução: " + reservas.getHoraPrevisto()+
							" - Quantidade de Aluguel: " + reservas.getQtdeAluguel());
	} 
	
	public void GravarReserva()  {
		NO_Reserva aux = inicio;
		
		try {
			String fileName = "ArquivoReserva.txt";	
		    BufferedWriter gravar = new BufferedWriter(new FileWriter( fileName ));	
		
			while (aux != null) {
				gravar.newLine(); 

				Cliente = aux.reservas.getCliente();
	            gravar.write(aux.reservas.getCliente()+", "); 

				Enfeite = aux.reservas.getEnfeite();
	            gravar.write(aux.reservas.getEnfeite()+", ");

				FormaDePagamento = aux.reservas.getFormaDePagamento();
	            gravar.write(aux.reservas.getFormaDePagamento()+", ");

				PrecoFinal = aux.reservas.getPrecoFinal();
	            gravar.write(String.valueOf(aux.reservas.getPrecoFinal())+", "); 
				
				DataFesta = aux.reservas.getDataFesta();
	            gravar.write(aux.reservas.getDataFesta().toString()+", "); 
				
				HoraInicio = aux.reservas.getHoraInicio();
	            gravar.write(String.valueOf(aux.reservas.getHoraInicio())+", ");

				DataPrevista = aux.reservas.getDataPrevista();
	            gravar.write(aux.reservas.getDataPrevista().toString()+", "); 

				HoraPrevisto = aux.reservas.getHoraPrevisto();
	            gravar.write(String.valueOf(aux.reservas.getHoraPrevisto())+", "); 

				QtdeAluguel = aux.reservas.getQtdeAluguel();
				gravar.write(String.valueOf(aux.reservas.getQtdeAluguel()));

				aux = aux.prox;
			}
		     gravar.close();  			
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro na gravação!");
		}  	// fim try-catch
	} // fim gravar  cliente

	
	public double CalcularDesconto(double PrecoFinal) {

		return PrecoFinal;
	}
	
	public void RealizarDevolucao() {

	}

	public void ListarReservas() {
		String nome = "ArquivoReserva.txt"; 
		File arq = new File(nome);    

        if ( arq.exists() && arq.isFile() ) {
            try {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();

				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Lista está vazia!");
		}
	}

	
	public int BuscarQtdeReserva(String buscador) throws IOException {
		int cont = 0;
		String arquivo = "ArquivoReserva.txt"; 
		File arq = new File(arquivo); 

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			cont = 1;
			
			while (linha != null) {
				String [] frase;
				frase = linha.split(", ");

				for (String palavra: frase) {
					if (palavra.equalsIgnoreCase(buscador)) {
						cont++;
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			cont++;
		}
		return cont;
	}	

	
	public void BuscarItemArquivo( String buscador, String arquivo ) throws IOException {
		File arq = new File(arquivo); 

		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				String [] frase;
				frase = linha.split(", ");

				for (String palavra: frase) {
					if (palavra.equalsIgnoreCase(buscador)) {
						if (arquivo.equalsIgnoreCase("ArquivoReserva.txt")) {
							JOptionPane.showMessageDialog(null, "Reserva do Enfeite: "+frase[1] +
																", feita por: "+frase[0]+", no dia: "+frase[4]);
						} else {
							JOptionPane.showMessageDialog(null, "Devolução do Enfeite: "+frase[1] +
																", feita por: "+frase[0]+", no dia: "+frase[4]);
						}
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Invalido");
		}
	}	

	public void CalcularAtraso(String [] frase) {
		
	}

	public boolean lerArquivos(String arquivo, String buscador) throws IOException {
		File arq = new File(arquivo);
		boolean result = false;
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while (linha != null) {
				String [] frase;
				frase = linha.split(", ");

				for (String palavra: frase) {
					if (palavra.equalsIgnoreCase(buscador)) {
						JOptionPane.showMessageDialog(null, "Localizamos: "+frase[2]);
						result = true;
						return result;
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Invalido");
		}
		return result;
	}

	private int CalcularQtdeAluguel(String Cliente) throws IOException {
		QtdeAluguel = BuscarQtdeReserva(Cliente);
		return QtdeAluguel;
	}
}