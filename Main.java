package cliente_Consola;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lp.Equipa6_Comp2.entity.Utilizador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
	
	private static final String BASE_URL = "http://localhost:8080";
	private static final RestTemplate rest = new RestTemplate();
	private static final Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
            System.out.println("\n===== SISTEMA DE VOLUNTARIADO =====");
            System.out.println("1) Login");
            System.out.println("2) Registar novo Utilizador");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> login();
                case 2 -> registarUtilizador();
                case 0 -> {
                	System.out.println("A sair ...");
                	return;
                }
                default -> System.out.println("Opção inválida!!!");
            }
        }
	}
	
	//registo
	private static void registarUtilizador() {
		System.out.println("\n Registar novo utilizador");
        System.out.print("Nome: ");
        String nome = ler.next();
        System.out.print("Email: ");
        String email = ler.next();
        System.out.print("Password: ");
        String pass = ler.next();
        System.out.print("Morada: ");
        String morada = ler.next();
        System.out.print("Telemóvel: ");
        int tel = ler.nextInt();
        
        String tipoUtilizador;
        while (true) {
            System.out.print("Tipo de utilizador (1-Admin | 2-Utilizador ): ");
            int escolha = ler.nextInt();
            if (escolha==1){
            	tipoUtilizador  = "ADMIN";
            	break; 
            	}
            if (escolha==2) { 
            	tipoUtilizador = "USER";
            	break; 
            	}
            System.out.println("Escolha inválida. Introduza 1 para Admin ou 2 para Utilizador.");
        }
        
        String json = """
        		{
        			"nome" : "%s",
        			"email" : "%s",
        			"password" : "%s",
        			"morada" : "%s",
        			"telemovel" : %d,
        			"tipoUtilizador" : "%s"
        		}
        		""".formatted(nome,email,pass,morada,tel,tipoUtilizador);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/utilizadores", request, String.class);
        
        System.out.println("\nRegisto efetuado com sucesso como " + tipoUtilizador + ":");
        System.out.println(response);
	}
				
	//login
	private static void login() {
		System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = ler.next();
        System.out.print("Password: ");
        String password = ler.next();

        String json = """
        		{
        			"email" : "%s",
        			"password" : "%s"
        		}
        		""".formatted(email,password);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/utilizadores/login", request, String.class);
        
        try {
            // faz login e recebe o objeto Utilizador
        	// converter JSON → objeto
        	ObjectMapper mapper = new ObjectMapper();
        	Utilizador utilizador = mapper.readValue(response, Utilizador.class);

            System.out.println("\nLogin efetuado com sucesso! Bem-vindo, " + utilizador.getNome() + ".");

            // Agora escolhe menu com base no tipo
            if ("ADMIN".equalsIgnoreCase(utilizador.getTipoUtilizador())) {
                menuAdmin(utilizador);
            } else {
                menuUtilizador(utilizador);
            }
        
        } catch (Exception e) {
        	System.out.println("Credenciais inválidas!");

        }
        
	}
			
			
			
	//menu admin
	private static void menuAdmin(Utilizador u) {
		while (true) {
			System.out.println("\n===== MENU ADMIN =====");
			System.out.println("1) Criar tipos de voluntariado");
			System.out.println("2) Registar instituição");
            System.out.println("3) Criar programa de voluntariado");
            System.out.println("4) Criar edição");
            System.out.println("5) Listar programas");
            System.out.println("6) Procurar programa por ID");
            System.out.println("7) Listar utilizadores");
            System.out.println("8) Listar voluntários");
            System.out.println("9) Listar inscrições");
            System.out.println("0) Terminar sessão");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> criarTiposVoluntariado();
                case 2 -> registarInstituicao();
                case 3 -> criarPrograma();
                case 4 -> criarEdicao();
                case 5 -> listarProgramas();
                case 6 -> procurarProgramaPorId();
                case 7 -> listarUtilizadores();
                case 8 -> listarVoluntarios();
                case 9 -> listarInscricoes();
                case 0 -> {
                    System.out.println(" Sessão terminada. A voltar ao menu principal...");
                    return;
                }
                default -> System.out.println(" Opção inválida!");
            }
        }
	}
	
	private static void criarTiposVoluntariado() {
		System.out.println("\n--- Novo Tipo de Voluntariado ---");
		
		ler.nextLine();
        System.out.print("Descrição: ");    
        String descricao = ler.nextLine().trim();
        
        String json = """
        		{
        			"descricao" : "%s"
        		}
        		""".formatted(descricao);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/tiposvoluntariados", request, String.class);
        
        System.out.println("\nTipo de voluntariado criado:");
        System.out.println(response);
	}
	
	private static void registarInstituicao() {
		ler.nextLine();
        System.out.print("Nome da instituição: ");       
        String nome = ler.nextLine().trim();

        System.out.print("Data de fundação (AAAA-MM-DD): ");    
        LocalDate df = lerData();
        
        String json = """
        		{
        			"nome" : "%s",
        			"dataFundação" : "%s"
        		}
        		""".formatted(nome,df);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/instituicoes", request, String.class);
        
        System.out.println("\nInstituição registada:");
        System.out.println(response);
	}
	
	private static void criarPrograma() {
        System.out.println("\n--- Novo Programa de Voluntariado ---"); 
        
        ler.nextLine();
        System.out.print("Título: ");       
        String titulo = ler.nextLine().trim();

        System.out.print("Descrição: ");    
        String descricao = ler.nextLine().trim();

        System.out.print("Localização: ");  
        String localizacao = ler.nextLine().trim();

        System.out.print("Nº de vagas: ");  
        int vagas = Integer.parseInt(ler.nextLine().trim());  // lê linha e converte

        System.out.print("Data início (AAAA-MM-DD): "); 
        LocalDate di = lerData();

        System.out.print("Data fim (AAAA-MM-DD): ");    
        LocalDate df = lerData();

        System.out.print("Total de horas: ");           
        int total = Integer.parseInt(ler.nextLine().trim());

        String json = """
        		{
        			"titulo" : "%s",
        			"descricao" : "%s",
        			"localizacao" : "%s",
        			"numeroVagas" : %d,
        			"dataInicio" : "%s",
        			"dataFim" : "%s",
        			"totalHoras" : %d
        		}
        		""".formatted(titulo,descricao,localizacao,vagas,di,df,total);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        String response = rest.postForObject(BASE_URL + "/voluntariado/programasvoluntariado", request, String.class);
        
        System.out.println("\nPrograma de voluntariado criado:");
        System.out.println(response);
    }
	


	private static LocalDate lerData() {
        while (true) {
            try { return LocalDate.parse(ler.nextLine()); }
            catch (DateTimeParseException e) { System.out.print("Data inválida (formato AAAA-MM-DD): "); }
        }
    }

}
