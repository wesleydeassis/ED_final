package devolucao;



import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;

import javax.swing.JOptionPane;

import controller.OperacoesReserva;


public class OperacoesDevolucao {
	
	private LocalDate DataFesta;
    private LocalDate DataPrevista;
    private LocalDate DataRetorno;
    private String Cliente;
    private String Enfeite;
    private String status;
    private NO_Devolucao inicio;
    private BufferedReader buffer;

		
		
		public OperacoesDevolucao() {
			inicio = null;
		}

		public void MenuDevolucao() {
			int opcao = 0;
			while (opcao != 9) {

				opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu de Devolucao: \n"+
				"\n1- Registrar devolucao"+
				"\n2- Listar devoluções"+
				"\n9- Voltar  "));
				
				switch (opcao) {
					case 1:
						CadastrarDevolucao();
					break;
						
					case 2:	
						ListarDevolucoes();
					break;

			
					case 9:
						JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
					break;
					default:
					break;
				} // fim switch
			} // fim while
		} //
		
	

		public void CadastrarDevolucao() {  
			String df ="";
			Devolucoes devolucao = new Devolucoes(DataFesta, DataPrevista, DataRetorno, Cliente, Enfeite, status);
			OperacoesReserva opR = new OperacoesReserva();
			Cliente = JOptionPane.showInputDialog("Informe o CPF/RNE ou nome do cliente: ");
			
				
				
				try {
					String arqC = "ArquivoClientes.txt";
					if (opR.lerArquivos( arqC, Cliente ) == true ) {	
							devolucao.setCliente(Cliente);}
							
							 else {
									JOptionPane.showMessageDialog(null, "Cliente não localizado!");
									MenuDevolucao();}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
					
				}
				
				Enfeite = JOptionPane.showInputDialog("Informe o tema: ");
			
			try {
				String arqE = "ArquivoEnfeites.txt";
				if ( opR.lerArquivos(arqE, Enfeite ) == true ) {
					devolucao.setEnfeite(Enfeite);
					
				} else {
					JOptionPane.showMessageDialog(null, "Tema não localizado em Reserva");
					MenuDevolucao();
				}
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro!");
			} 
			 DataRetorno = LocalDate.now();
			 devolucao.setDataRetorno(DataRetorno);
			 
			 
					 
			try {
				df = BuscarDataArquivo(Cliente, "ArquivoReserva.txt");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			DataFesta = LocalDate.parse(df);
			devolucao.setDataFesta(DataFesta);
			
			DataPrevista =  DataFesta.plusDays(3);
			devolucao.setDataPrevista(DataPrevista);		 
		
		
		
			
			
			
		   Period periodo = Period.between(DataRetorno, DataPrevista);
		  int atraso=periodo.getDays();
			String status="";

			if (DataPrevista.compareTo(DataRetorno)>0){
				status= " produto devolvido antes do prazo";}
			else if(DataPrevista.compareTo(DataRetorno)<0){
				status="Produto devolvido depois do prazo" + atraso+ " dias";}
				else if (DataPrevista.compareTo(DataRetorno)==0){
					status="Produto devolvido devolvido no dia previsto"; 
			}
			
			devolucao.setStatus(status);

			
			
			if (inicio == null) {							// verifica se a lista est vazia
				NO_Devolucao n = new NO_Devolucao(devolucao);	// carrega o valor de "e" no n criado
				inicio = n;									// carrega inicio com "n" - novo no criado
			}  // fim if
			else {
				NO_Devolucao aux = inicio;				// cria endereco "aux" e carrega com o endereco de inicio
				while (aux.prox != null) {				// prox vem da classe contructor NO
					aux = aux.prox;						// vai movendo aux para a proximo endereco
				} // fim while
				NO_Devolucao n = new NO_Devolucao(devolucao);	// cria um novo no com endereco "n" e carrega dado "e"
				aux.prox = n;								// carrega n aux o endereco de n		
			} // fim do else
			GravarDevolucao();
			
			JOptionPane.showMessageDialog(null, "Devolucao Registrada com sucesso!");  
			System.out.println("Devolucao Registrada: \n" + 
								" Cliente: " +devolucao.getCliente() + 
								" - Tema: " +devolucao.getEnfeite()+ 
								" - Data da Festa: " + devolucao.getDataFesta() +
								" - Data Previsão de devolução: " + devolucao.getDataPrevista()+
								" - Data de devolucao: " + devolucao.getDataRetorno()+
								" - Status: "+ devolucao.getStatus());
			
			
		}
		
	
		
	

		
	
			
		private void ListarDevolucoes() {
			String nome = "ArquivoDevolucao.txt"; 
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


	

		private void GravarDevolucao() {
			NO_Devolucao aux = inicio;
			
			try {
				String fileName = "ArquivoDevolucao.txt";	
			    BufferedWriter gravar = new BufferedWriter(new FileWriter( fileName ));	
			
				while (aux != null) {
					gravar.newLine();
		            gravar.write("- Nova devolucao, ");

		            Cliente = aux.devolucao.getCliente();
		            gravar.write(aux.devolucao.getCliente()+", "); 

		            Enfeite = aux.devolucao.getEnfeite();
		            gravar.write(aux.devolucao.getEnfeite()+", "); 

		            DataFesta = aux.devolucao.getDataFesta();
		            gravar.write(aux.devolucao.getDataFesta().toString()+", "); 

					DataPrevista = aux.devolucao.getDataPrevista();
		            gravar.write(aux.devolucao.getDataPrevista().toString()+", ");
		            		
		            DataRetorno = aux.devolucao.getDataRetorno();
		            gravar.write(aux.devolucao.getDataRetorno().toString()+", ");		            		
		            
		            status= aux.devolucao.getStatus();
		            gravar.write(aux.devolucao.getStatus()); 

					aux = aux.prox;
				}
			     gravar.close();  			
			} 
			catch (Exception e) {
				System.err.println("Ocorreu um erro na gravação!");
			}  // fim try-catch
			
		}
			
			
		
			public String BuscarDataArquivo(String buscador, String arquivo) throws IOException { 
					File arq = new File(arquivo); 
					String ret= "";

					if (arq.exists() && arq.isFile()) {
						FileInputStream fluxo = new FileInputStream(arq);
						InputStreamReader leitor = new InputStreamReader(fluxo);
						buffer  = new BufferedReader(leitor);
						String linha = buffer.readLine();
						
						while (linha != null) {
							String [] frase;
							frase = linha.split(", ");

							for (String palavra: frase) {
								if (palavra.equalsIgnoreCase(buscador)) {
									if (arquivo.equalsIgnoreCase("ArquivoReserva.txt")) {
										ret= frase[4];
		
									} else {
										
										return null;
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
					return ret;
				}	
			 
		
		} 
			
		 
   
		
		// fim classe



