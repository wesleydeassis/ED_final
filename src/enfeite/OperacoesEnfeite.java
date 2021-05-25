package enfeite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class OperacoesEnfeite {

	private int codTema;
	private String tema;
	private String descricao;
	private double preco;
	private NO_Enfeite inicio;
	
	public OperacoesEnfeite() {
		inicio = null;
	}

	public void MenuEnfeites() {
		int opcao = 0;
		while (opcao != 9) {

			opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu de Enfeites: \n"+
			"\n1- Cadastrar enfeite"+
			"\n2- Remover da enfeite da lista"+
			"\n3- Buscar enfeite por tema"+
			"\n4- Listar enfeites"+
			"\n9- Voltar  "));
			
			switch (opcao) {
				case 1:
					CadastrarEnfeites();
				break;

				case 2:	
					int posicao = Integer.parseInt(JOptionPane.showInputDialog("Digite a posicao a ser removida: "));
					System.out.println(RemoverEnfeites(posicao));
				break;

				case 3:
					tema = JOptionPane.showInputDialog("Digite o nome do tema para buscar: ");
					BuscarEnfeites(tema);
				break;

				case 4:
					ListarEnfeites();
				break;
		
				case 9:
					JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
				break;
				default:
				break;
			} // fim switch
		} // fim while
	} // fim cadastro enfeites
	
	public void CadastrarEnfeites() {   // adicionar no final da lista
		Enfeites enfeites = new Enfeites(codTema, tema, descricao, preco);

		codTema = (int)(Math.random() * 1000);
		enfeites.setCodTema(codTema);

		tema = JOptionPane.showInputDialog("Informe o tema do enfeite");
		enfeites.setTemaEnfeite(tema);

		descricao = JOptionPane.showInputDialog("Informe a descricao do enfeite");
		enfeites.setDescricaoEnfeite(descricao);

		preco = Double.parseDouble(JOptionPane.showInputDialog("Informe o preco do tema"));
		enfeites.setPreco(preco);
		
		if (inicio == null) {							// verifica se a lista esta vazia
			NO_Enfeite n = new NO_Enfeite(enfeites);	// carrega o valor de "e" no  criado
			inicio = n;									// carrega inicio com "n" - novo  no criado
		}  // fim if
		else {
			NO_Enfeite aux = inicio;				// cria endereco do novo no "aux" e carrega com o endereco de inicio
			while (aux.prox != null) {				// prox vem da classe contructor NO
				aux = aux.prox;						// vai movendo aux para a proximo endereco
			} // fim while
			NO_Enfeite n = new NO_Enfeite(enfeites);	// cria um novo no com endereco "n" e carrega dado "e"
			aux.prox = n;								// carrega n aux o endereco de n		
		} // fim do else
		GravarEnfeites();
		JOptionPane.showMessageDialog(null, "Enfeite cadastrado e gravado com sucesso!");  
		System.out.println("Enfeite Cadastrado: \n" + 
							" Codigo: " +enfeites.getCodTema() + 
							" - Tema: " +enfeites.getTemaEnfeite()+ 
							" - Descricao: " + enfeites.getDescricaoEnfeite() +
							" - Preco: " + enfeites.getPreco());
	} // cadastro enfeites
	
	public void GravarEnfeites()  {
		NO_Enfeite aux = inicio;
		
		try {
			String fileName = "ArquivoEnfeites.txt";	
		    BufferedWriter gravar = new BufferedWriter(new FileWriter( fileName ));	
		
			while (aux != null) {
				gravar.newLine();
	            gravar.write("- Novo Enfeite, ");

				codTema = aux.enfeites.getCodTema();
	            gravar.write(String.valueOf(aux.enfeites.getCodTema()+", ")); 

				tema = aux.enfeites.getTemaEnfeite();
	            gravar.write(aux.enfeites.getTemaEnfeite()+", "); 

				descricao = aux.enfeites.getDescricaoEnfeite();
	            gravar.write(aux.enfeites.getDescricaoEnfeite()+", "); 

				preco = aux.enfeites.getPreco();
	            gravar.write(String.valueOf(aux.enfeites.getPreco())); 

				aux = aux.prox;
			}
		     gravar.close();  			
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro na gravacao!");
		}  // fim try-catch
	} // fim gravar  enfeites
	
	public void ListarEnfeites() {
		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "Lista esta vazia!"); 
		} // if
		else {
			RecuperarListaEnfeites();
			NO_Enfeite aux1 = inicio;	// criacao de duas variaveis
			
			while (aux1 != null) {
				System.out.println( "Codigo: " +aux1.enfeites.getCodTema() +
									" - Tema: " +aux1.enfeites.getTemaEnfeite()+
									" - Descricao: "+ aux1.enfeites.getDescricaoEnfeite() + 
									" - Preco: " + aux1.enfeites.getPreco()); 
				aux1 = aux1.prox;
			} // fim while
		} // fim else
	} // fim lista enfeites
	
	public boolean BuscarEnfeites(String tema) {
		NO_Enfeite nodo = inicio;
		String aux = nodo.enfeites.getTemaEnfeite();

		try {
			while ( nodo != null ) {
				if ( tema.equalsIgnoreCase(aux) ) {
					JOptionPane.showMessageDialog(null, "Enfeite será apresentado no console!"); 
					System.out.println( "Codigo " +nodo.enfeites.getCodTema()+ 
										" - Tema: "+ nodo.enfeites.getTemaEnfeite()+
										" - Descricao: " + nodo.enfeites.getDescricaoEnfeite()+
										" - Preco: "+nodo.enfeites.getPreco());
										return true;
				} //fim if
				nodo = nodo.prox;
				aux = nodo.enfeites.getTemaEnfeite();
			}
		} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Enfeite nao localizado!"); 
		}
		return false;
	} // fim buscar
	
	public String RemoverInicio() {			// 6 remover no inico da lista
		int codTema1 = 0;								// criar as variaveis
		String tema1 = "";
		String descricao1 = "";
		double preco1 = 0.0;

		if (inicio == null) {
			JOptionPane.showMessageDialog(null, "A Lista esta vazia");
		} // fim inicio 
		else {
			codTema1 = inicio.enfeites.getCodTema();				
			tema1 = inicio.enfeites.getTemaEnfeite();				
			descricao1 = inicio.enfeites.getDescricaoEnfeite();
			preco1 = inicio.enfeites.getPreco();
			JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 

			inicio = inicio.prox;			// passar para inicio o endereco do proximos endereco
		} // fim else

		return "Codigo: " + codTema1 + 
		" - Tema: " + tema1 + 
		" - Descricao: " +descricao1 + 
		" - Preco: " +preco1;
	} // fim da classe Remove Inicio
	
	public String RemoveFinal() {					// 5 remover no final da lista
		int codTema1 = 0;								// criar as variaveis
		String tema1 = "";
		String descricao1 = "";
		double preco1 = 0.0;

		if (inicio == null ) {
			JOptionPane.showMessageDialog(null, "Lista esta vazia");
		}
		else {
			if (inicio.prox == null) {			// inicio o primeiro elemento da lista
				codTema1 = inicio.enfeites.getCodTema();				
				tema1 = inicio.enfeites.getTemaEnfeite();				
				descricao1 = inicio.enfeites.getDescricaoEnfeite();
				preco1 = inicio.enfeites.getPreco();	

				JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 	

				inicio = null;					// informa que o ultimo elemento da lista

			} // fim if
			else {		
				NO_Enfeite aux = LocalizaDadoRemocaoFim(inicio, inicio);

				codTema1 = aux.prox.enfeites.getCodTema();				
				tema1 = aux.prox.enfeites.getTemaEnfeite();				
				descricao1 = aux.prox.enfeites.getDescricaoEnfeite();
				preco1 = aux.prox.enfeites.getPreco();	

				JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 
				aux.prox = null;			// coloca null para mostrar o fim da lista. 
			} // fim else
		} // fim else

		return "Codigo: " + codTema1 + 
		" - Tema: " + tema1 + 
		" - Descricao: " +descricao1 + 
		" - Preco: " +preco1;
	} // fim remover no final
	
	public NO_Enfeite LocalizaDadoRemocaoFim(NO_Enfeite aux1, NO_Enfeite aux2) {
		if ( aux1.prox != null ) {
			return LocalizaDadoRemocaoFim(aux1.prox, aux1);
		}
		return aux2 ;
	}
		
	public String RemoverEnfeites(int pos) {
		int codTema1 = 0;								// criar as variaveis
		String tema1 = "";
		String descricao1 = "";
		double preco1 = 0.0;

		int i = 1; 
		NO_Enfeite aux = inicio;	// criar um enderecamento aux com valor inicial
		
		if ( inicio == null ) {
			JOptionPane.showMessageDialog(null, "Lista esta vazia!");
			
			codTema1 = inicio.enfeites.getCodTema();				
			tema1 = inicio.enfeites.getTemaEnfeite();				
			descricao1 = inicio.enfeites.getDescricaoEnfeite();
			preco1 = inicio.enfeites.getPreco();
			
			return "Codigo: " + codTema1 + 
			" - Tema: " + tema1 + 
			" - Descrico: " +descricao1 + 
			" - Preco: " +preco1;
		} // fim IF 
		
		if ( pos == 1 ) {  // remocao pos = 1, remocao seria no inicio da lista
			codTema1 = aux.enfeites.getCodTema();
			tema1 = aux.enfeites.getTemaEnfeite();
			descricao1 = aux.enfeites.getDescricaoEnfeite();
			preco1 = aux.enfeites.getPreco();

			RemoverInicio();
			JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 
			return "Codigo: " + codTema1 + 
			" - Tema: " + tema1 + 
			" - Descricao: " +descricao1 + 
			" - Preco: " +preco1;
		} // Fim IF
		else {
			while (aux.prox != null) {  // remover no final da lista
				aux = aux.prox;   // guarda no aux o endereco do proximo da posicao
				i++;				// vai guardando os posicoes ate encontral null
			} // fim While
			if (pos > i || pos <=0) {  // posicoes invalidas
				JOptionPane.showMessageDialog(null, "Posicao invalida");
				
				return "Codigo: " + codTema1 + 
				" - Tema: " + tema1 + 
				" - Descricao: " +descricao1 + 
				" - Preco: " +preco1;
			} // fim IF
			else if (pos == i){			// Remocao no final
				codTema1 = aux.enfeites.getCodTema();
				tema1 = aux.enfeites.getTemaEnfeite();
				descricao1 = aux.enfeites.getDescricaoEnfeite();
				preco1 = aux.enfeites.getPreco();

				RemoveFinal();
				JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 
				return "Codigo: " + codTema1 + 
				" - Tema: " + tema1 + 
				" - Descricao: " +descricao1 + 
				" - Preco: " +preco1;
			} // fim else
			else {						// remover qualquer posicao
				aux = inicio;			// carrega aux com inicio
				NO_Enfeite aux2 = aux;			// cria enderecamenteo aux 2 e copia aux
				
				while(pos > 1) {
					aux2 = aux;
					aux = aux.prox;
					pos --;
				} // while

				codTema1 = aux.enfeites.getCodTema();
				tema1 = aux.enfeites.getTemaEnfeite();
				descricao1 = aux.enfeites.getDescricaoEnfeite();
				preco1 = aux.enfeites.getPreco();
				aux2.prox = aux.prox;

				JOptionPane.showMessageDialog(null, "Enfeite removido com sucesso!"); 
				return "Codigo: " + codTema1 + 
				" - Tema: " + tema1 + 
				" - Descricao: " +descricao1 + 
				" - Preco: " +preco1;
			} // fim else
		} // fim else
	} // fim metodo escolher remover

	public void RecuperarListaEnfeites()  {	
		try {
			String fileName = "ArquivoEnfeites.txt";
			BufferedReader ler = new BufferedReader(new FileReader(fileName));
			String linha = ler.readLine();

			while ( linha != null ) {        		
				codTema = Integer.parseInt(ler.readLine());
				tema = ler.readLine();
				descricao = ler.readLine();
				preco = Double.parseDouble(ler.readLine());

				linha = ler.readLine();
			}
			ler.close();
		} 
		catch (Exception e) {
			System.err.println("Ocorreu um erro!");
		} // fim try e catch 
		
	} // fim da lista de enfeites
} // fim classe