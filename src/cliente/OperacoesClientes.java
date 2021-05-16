package cliente;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class OperacoesClientes {
   private int CPF_RNE;
   private String Nome;
   private String Telefone;
   private String Endereco;
   private LocalDate DataCadastro = LocalDate.now();
   private int QtdeAluguel;
   private NO_Cliente inicio;
  

   public OperacoesClientes() {
		inicio = null;
	}

	public void MenuClientes() {
		int opcao = 0;
		while (opcao != 9) {

			opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu de Clientes: \n "+
																	"\n1- Cadastrar Cliente"+
																	"\n2- Remover cliente da lista"+
																	"\n3- Buscar cliente por CPF ou RNE"+
																	"\n4- Listar clientes"+
																	"\n9- Voltar  "));
			
			switch (opcao) {
				case 1:
                    CadastrarClientes();
				break;

				case 2:	
					int posicao = Integer.parseInt(JOptionPane.showInputDialog("Digite a posição a ser removida: "));
					System.out.println(RemoverClientes(posicao));
				break;

				case 3:
					CPF_RNE = Integer.parseInt(JOptionPane.showInputDialog("Digitar codigo o CPF OU RNE para busca: ")); 
					BuscarClientes(CPF_RNE,true);
				break;

				case 4:
					ListarClientes();
				break;

				case 9:
					JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
				break;

				default:
				break;
			} // fim switch
		} // fim while
	} // fim MenuClientes()

	public void CadastrarClientes() {
		Clientes cliente = new Clientes(CPF_RNE, Nome, Endereco, Telefone, DataCadastro, QtdeAluguel);

		CPF_RNE = Integer.parseInt(JOptionPane.showInputDialog("Digite CPF/RNE: "));
		cliente.setCPF_RNE(CPF_RNE);
		
		Nome = JOptionPane.showInputDialog("Informe o Nome do Cliente: ");
		cliente.setNome(Nome);
		
		Endereco = JOptionPane.showInputDialog("Informe o Endereço do Cliente: ");
		cliente.setEndereco(Endereco);
		
		Telefone = JOptionPane.showInputDialog("Informe o Telefone do Cliente: ");
		cliente.setTelefone(Telefone);
		
		cliente.setDataCadastro(DataCadastro);
		
		QtdeAluguel = 0;
		cliente.setQtdeAluguel(QtdeAluguel);
		
		if (inicio == null) {								// verifica se a lista esta vazia
			NO_Cliente n = new NO_Cliente(cliente);	

			inicio = n;
			n.prox = null;
			n.anterior = null;									
		}  // fim if
		
		else {
			NO_Cliente aux = inicio;				
			while (aux.prox != null) {					// buscando o ultimo elemento da lista	
				aux = aux.prox;						
			} // fim while
			NO_Cliente n = new NO_Cliente(cliente);		// cria um novo Nó
			aux.prox = n;	
			n.anterior = aux;
			n.prox = null;
		} // fim do else
		GravarCliente();
		JOptionPane.showMessageDialog(null, "Cliente cadastrado e gravado com sucesso!");  
		System.out.println("Cliente Cadastrado: \n" + 
							" CPF_RNE: " + cliente.getCPF_RNE() + 
							" - Nome: " + cliente.getNome() + 
							" - Endereco: " + cliente.getEndereco() +
							" - Telefone: " + cliente.getTelefone() +
							" - Data Cadastro: " + cliente.getDataCadastro() +
							" - Quantidade aluguel: " + cliente.getQtdeAluguel() );

	} // fim cadastro cliente
	
	public void GravarCliente()  {
		NO_Cliente aux = inicio;
		
		try {
			String fileName = "ArquivoCliente.txt";	
		    BufferedWriter gravar = new BufferedWriter(new FileWriter( fileName ));	
		
			while (aux != null) {
	            gravar.write("** Novo cliente: "); 
				gravar.newLine();

				CPF_RNE = aux.clientes.getCPF_RNE();
	            gravar.write(aux.clientes.getCPF_RNE()); 
				gravar.newLine();

				Nome = aux.clientes.getNome();
	            gravar.write(aux.clientes.getNome()); 
				gravar.newLine();

				Endereco = aux.clientes.getEndereco();
	            gravar.write(aux.clientes.getEndereco()); 
				gravar.newLine();

				Telefone = aux.clientes.getTelefone();
	            gravar.write(aux.clientes.getTelefone()); 
				gravar.newLine();
				
				DataCadastro = aux.clientes.getDataCadastro();
	            gravar.write(aux.clientes.getDataCadastro().toString() ); 
				gravar.newLine();
				
				QtdeAluguel = aux.clientes.getQtdeAluguel();
	            gravar.write(String.valueOf(aux.clientes.getQtdeAluguel())); 
				gravar.newLine();

				aux = aux.prox;
			}
		     gravar.close();  			
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro na gravação!");
		}  	// fim try-catch
	} // fim gravar  cliente
	
	public void ListarClientes() {
		if (inicio == null) {
			System.out.println("Lista vazia");
		} // if
		else {

			RecuperarListaClientes();
			NO_Cliente aux = inicio;	// criação de duas variaveis
			
			while (aux != null) {
				JOptionPane.showMessageDialog(null, "A lista será mostrada no console");
				System.out.println("\n CPF_RNE: " +aux.clientes.getCPF_RNE() +
									" - Nome: " +aux.clientes.getNome()+
									" - Endereço: "+ aux.clientes.getEndereco()+ 
									" - Telefone: " + aux.clientes.getTelefone()+ 
									" - Data Cadastro: " +aux.clientes.getDataCadastro()+ 
									" - Quantidade Aluguel: " +aux.clientes.getQtdeAluguel()); 
				aux = aux.prox;
			} // fim while
		} // fim else
	} // fim lista cliente
	
	// DANDO ERRO 
	/*
	
	public String BuscarClientes(String CPF_RNE) {
		String aux = " ";
		for(NO_Cliente nodo = inicio; nodo != null; nodo = nodo.prox) {
			aux = nodo.clientes.getCPF_RNE();
			try {
		       if (CPF_RNE.equalsIgnoreCase(aux)) {
					System.out.println( "CPF_RNE: " +nodo.clientes.getCPF_RNE() +
										" - Nome: " +nodo.clientes.getNome()+
										" - Endereço: "+ nodo.clientes.getEndereco() + 
										" - Telefone: " + nodo.clientes.getTelefone() + 
										" - Data Cadastro: " +nodo.clientes.getDataCadastro() + 
										" - Quantidade Aluguel: " +nodo.clientes.getQtdeAluguel()); 
										return CPF_RNE;
		       }
			   break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Cliente não localizado!"); 
			}
		} // fim for
		return null;
	} // fim buscar cliente
	
	*/
	public String BuscarClientes(int CPF_RNE, boolean status) {
		int aux = 0;
		String nome="";	
		
		int cpf=0;
		
		if(status==true){
		
		for(NO_Cliente nodo = inicio; nodo != null; nodo = nodo.prox) {
			aux = nodo.clientes.getCPF_RNE();
		       if (CPF_RNE == aux) {
		    	   
		    	  
					System.out.println("CPF_RNE: " +nodo.clientes.getCPF_RNE() +" Nome: " +nodo.clientes.getNome()+" Endereço: "+ nodo.clientes.getEndereco() + " Telefone: " + nodo.clientes.getTelefone() + " Data Cadastro: " +nodo.clientes.getDataCadastro() + " Quantidade Aluguel: " +nodo.clientes.getQtdeAluguel()); 
					
		    	   
		    			    		  
		    	  
					break;}
		       }
		}
		
		else if(status==false){
			
			
			
			for(NO_Cliente nod = inicio; nod != null; nod = nod.prox) {
				aux = nod.clientes.getCPF_RNE();
			       if (CPF_RNE == aux) {
			    	   
			    	  
			    	   nome=  nod.clientes.getNome();
			    	   
			    	   break;
			      	   
			    	  
			    	  
			    	  
			    	   
			    	
			    	   }
			       
			       return nome;
			    			    		  
			}
 		   
 		 
		}
		return nome;
	

		
	}
		
		
	 // fim for
		

// fim buscar cliente
	
	
	/*
	public void BuscarClientes(int CPF_RNE) {
		int aux = 0;
		for(NO_Cliente nodo = inicio; nodo != null; nodo = nodo.prox) {
			aux = nodo.clientes.getCPF_RNE();
		       if (CPF_RNE == aux) {
					System.out.println("CPF_RNE: " +nodo.clientes.getCPF_RNE() +" Nome: " +nodo.clientes.getNome()+" Endereço: "+ nodo.clientes.getEndereco() + " Telefone: " + nodo.clientes.getTelefone() + " Data Cadastro: " +nodo.clientes.getDataCadastro() + " Quantidade Aluguel: " +nodo.clientes.getQtdeAluguel()); 
		        break;
		       }
		} // fim for
	}
	*/
	
	public String RemoverInicio() {	 // 6 remover no inico da lista
		int CPF_RNE = 0;								// criar as variaveis
		String Nome = " ";
		String Endereco = " ";
		String Telefone = " ";
		LocalDate DataCadastro = LocalDate.of(1970, 03, 01);
		int QtdeAluguel = 0;
		
		if (inicio == null) {
			JOptionPane.showConfirmDialog(null, "Lista Vazia");
		} // fim inicio 
		else {
			CPF_RNE = inicio.clientes.getCPF_RNE();				
			Nome = inicio.clientes.getNome();				
			Endereco = inicio.clientes.getEndereco();
			Telefone = inicio.clientes.getTelefone();
			DataCadastro = inicio.clientes.getDataCadastro();
			QtdeAluguel = inicio.clientes.getQtdeAluguel();
						
			inicio = inicio.prox;			// passar para inicio o enderço do proximos endereço
			if (inicio != null) {
				inicio.anterior = null;
			}
		} // fim else
		return "CPF_RNE: "+ CPF_RNE +
		" - Nome: "+ Nome + 
		" - Endereco: "+ Endereco + 
		" - Telefone: "+ Telefone + 
		" - Data Cadastro: "+ DataCadastro + 
		" - Quantidade Aluguel: "+ QtdeAluguel;
	} // fim da classe Remove Inicio
	
	public String RemoveFinal() {	// remover no final da lista
		int CPF_RNE = 0;								// criar as variaveis
		String Nome = " ";
		String Endereco = " ";
		String Telefone = " ";
		LocalDate DataCadastro = LocalDate.of(1970, 03, 01);
		int QtdeAluguel = 0;
		
		if (inicio == null ) {
			JOptionPane.showConfirmDialog(null, "Lista Vázia");
		}
		else {
			if (inicio.prox == null) {			// inicio é o primeiro elemento da lista
				CPF_RNE = inicio.clientes.getCPF_RNE();				
				Nome = inicio.clientes.getNome();				
				Endereco = inicio.clientes.getEndereco();
				Telefone = inicio.clientes.getTelefone();
				DataCadastro = inicio.clientes.getDataCadastro();
				QtdeAluguel = inicio.clientes.getQtdeAluguel();
				
				inicio = null;					// informa que é o ultimo elemento da lista
			} // fim IF
			else {
				NO_Cliente aux1 = inicio;			// gerando duas varias, uma para varrer a lista
				NO_Cliente aux2 = inicio;
				
				while (aux1.prox != null) {
					aux2 = aux1;
					aux1 = aux1.prox;
				}

				NO_Cliente aux = LocalizaDadoRemocaoFim(inicio, inicio);
				
				CPF_RNE = aux.clientes.getCPF_RNE();				
				Nome = aux.clientes.getNome();				
				Endereco = aux.clientes.getEndereco();
				Telefone = aux.clientes.getTelefone();
				DataCadastro = aux.clientes.getDataCadastro();
				QtdeAluguel = aux.clientes.getQtdeAluguel();
				
				aux1.anterior = null;
				aux2.prox = null;			// coloca null para mostrar o fim da lista. 
				
			} // fim else
		} // fim else
		return "CPF_RNE: "+ CPF_RNE +
		" - Nome: "+ Nome + 
		" - Endereco: "+ Endereco + 
		" - Telefone: "+ Telefone + 
		" - Data Cadastro: "+ DataCadastro + 
		" - Quantidade Aluguel: "+ QtdeAluguel;
	} // fim remover no final
	
	public NO_Cliente LocalizaDadoRemocaoFim(NO_Cliente aux1, NO_Cliente aux2) {
		if (aux1.prox != null ) {
			return LocalizaDadoRemocaoFim(aux1.prox, aux1);
		}
		return aux2 ;
	}
	
	public String RemoverClientes(int posicao) {
		int CPF_RNE = 0;								// criar as variaveis
		String Nome = " ";
		String Endereco = " ";
		String Telefone = " ";
		LocalDate DataCadastro = LocalDate.of(1970, 03, 01);
		int QtdeAluguel = 0;	
		int i = 1; 
		
		NO_Cliente aux = inicio;	// criar um endereçamento aux com valor inicial
		
		if (inicio == null) {
			JOptionPane.showConfirmDialog(null, "Lista Vazia !");
			CPF_RNE = inicio.clientes.getCPF_RNE();				
			Nome = inicio.clientes.getNome();				
			Endereco = inicio.clientes.getEndereco();
			Telefone = inicio.clientes.getTelefone();
			DataCadastro = inicio.clientes.getDataCadastro();
			QtdeAluguel = inicio.clientes.getQtdeAluguel();
			
			return "CPF_RNE: "+ CPF_RNE +
			" - Nome: "+ Nome + 
			" - Endereco: "+ Endereco + 
			" - Telefone: "+ Telefone + 
			" - Data Cadastro: "+ DataCadastro + 
			" - Quantidade Aluguel: "+ QtdeAluguel;
		} // fim IF 
		
		if (posicao == 1) {  							// remoção pos = 1, remoção será no inicio da lista
			CPF_RNE = aux.clientes.getCPF_RNE();				
			Nome = aux.clientes.getNome();				
			Endereco = aux.clientes.getEndereco();
			Telefone = aux.clientes.getTelefone();
			DataCadastro = aux.clientes.getDataCadastro();
			QtdeAluguel = aux.clientes.getQtdeAluguel();
			
			RemoverInicio();
			return "CPF_RNE: "+ CPF_RNE +
			" - Nome: "+ Nome + 
			" - Endereco: "+ Endereco + 
			" - Telefone: "+ Telefone + 
			" - Data Cadastro: "+ DataCadastro + 
			" - Quantidade Aluguel: "+ QtdeAluguel;
		} // Fim IF
		else {
			while (aux.prox != null) {  // remover no final da lista
				aux = aux.prox;   // guarda no aux o endereço do proximo da posição
				i++;				// vai guardando os posiçoes ate encontral null
			} // fim While
			if (posicao > i || posicao <=0) {  // posicoes invalidas
				JOptionPane.showConfirmDialog(null, "Posição invalida");
				return "CPF_RNE: "+ CPF_RNE +
				" - Nome: "+ Nome + 
				" - Endereco: "+ Endereco + 
				" - Telefone: "+ Telefone + 
				" - Data Cadastro: "+ DataCadastro + 
				" - Quantidade Aluguel: "+ QtdeAluguel;
			} // fim IF
			else if (posicao == i){			// Remoção no final
				CPF_RNE = aux.clientes.getCPF_RNE();				
				Nome = aux.clientes.getNome();				
				Endereco = aux.clientes.getEndereco();
				Telefone = aux.clientes.getTelefone();
				DataCadastro = aux.clientes.getDataCadastro();
				QtdeAluguel = aux.clientes.getQtdeAluguel();
				RemoveFinal();
				return "CPF_RNE: "+ CPF_RNE +
				" - Nome: "+ Nome + 
				" - Endereco: "+ Endereco + 
				" - Telefone: "+ Telefone + 
				" - Data Cadastro: "+ DataCadastro + 
				" - Quantidade Aluguel: "+ QtdeAluguel;
			} // fim else
			else {						// remover qualquer posição
				aux = inicio;			// carrega aux com inicio
				NO_Cliente aux2 = aux;			// cria endereçamenteo aux 2 e copia aux
				
				while(posicao > 1) {
					aux2 = aux;
					aux = aux.prox;
					posicao --;
				} // while
				
				CPF_RNE = aux.clientes.getCPF_RNE();				
				Nome = aux.clientes.getNome();				
				Endereco = aux.clientes.getEndereco();
				Telefone = aux.clientes.getTelefone();
				DataCadastro = aux.clientes.getDataCadastro();
				QtdeAluguel = aux.clientes.getQtdeAluguel();
				
				
				aux2.prox = aux.prox;
				aux.prox = aux2;
				aux.prox = null;
				aux.anterior = null;
				
				return 	"CPF_RNE: "+ CPF_RNE +
				" - Nome: "+ Nome + 
				" - Endereco: "+ Endereco + 
				" - Telefone: "+ Telefone + 
				" - Data Cadastro: "+ DataCadastro + 
				" - Quantidade Aluguel: "+ QtdeAluguel;
			} // fim else
		} // fim else
	} // fim metodo escolher remover
	
	public void RecuperarListaClientes()  {	
		try {
			String fileName = "ArquivoCliente.txt";
			BufferedReader ler = new BufferedReader(new FileReader(fileName));
			String linha = ler.readLine();

			while ( linha != null ) {  
				CPF_RNE = Integer.parseInt(ler.readLine());
				Nome = ler.readLine();
				Endereco = ler.readLine();
				Telefone = ler.readLine();
				String dataCadastroRec = ler.readLine(); 
				QtdeAluguel = Integer.parseInt(ler.readLine());

				System.out.println( "CPF_RNE: "+ CPF_RNE +
									" - Nome: "+ Nome + 
									" - Endereco: "+ Endereco + 
									" - Telefone: "+ Telefone + 
									" - Data Cadastro: "+ dataCadastroRec + 
									" - Quantidade Aluguel: "+ QtdeAluguel);
				linha = ler.readLine();
					
			}
			ler.close();
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro!");
		} // fim try e catch 
	} // fim da recuperar lista lista de clientes
} // fim classe