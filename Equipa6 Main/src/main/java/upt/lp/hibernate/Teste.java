package upt.lp.hibernate;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Teste {

    private static final Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        // Gestores principais
        GerirUtilizador gerirU = new GerirUtilizador();
        GerirVoluntario gerirV = new GerirVoluntario();
        GerirProgramaVoluntariado gerirP = new GerirProgramaVoluntariado();
        GerirInscricao gerirI = new GerirInscricao();

        gerirU.setup();
        gerirV.setup();
        gerirP.setup();
        gerirI.setup();

        boolean flag = true;
        while (flag) {
            System.out.println("\n===== SISTEMA DE VOLUNTARIADO =====");
            System.out.println("1) Login");
            System.out.println("2) Registar novo Utilizador");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> login(gerirU, gerirV, gerirP, gerirI);
                case 2 -> registarUtilizador(gerirU);
                case 0 -> flag = false;
                default -> System.out.println("Opção inválida!!!");
            }
        }

        gerirI.exit();
        gerirP.exit();
        gerirV.exit();
        gerirU.exit();
        System.out.println("\n Obrigado por utilizar o sistema!!!");
    }

    //registo
    private static void registarUtilizador(GerirUtilizador g) {
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

        long id = g.createIfNotExistsTipoUtilizador(nome, email, pass, morada, tel, tipoUtilizador);
        //se id==-1 já existe utilizador, senão regista utilizador
        System.out.println(id == -1 ? "Já existe um utilizador com esse email!" : "Registo efetuado com sucesso como " + tipoUtilizador + ".");
    }
    
    //login
    private static void login(GerirUtilizador gU, GerirVoluntario gV,GerirProgramaVoluntariado gP, GerirInscricao gI) {
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = ler.next();
        System.out.print("Password: ");
        String pass = ler.next();

        //corre a tabela por email
        Utilizador u = gU.readByEmail(email);

        if (u != null && u.getPassword().equals(pass)) {
            String tipo = u.getTipoUtilizador() == null ? "" : u.getTipoUtilizador().trim().toUpperCase();
            if ("ADMIN".equals(tipo)) {
                System.out.println(" Bem-vindo, administrador " + u.getNome() + "!");
                menuAdmin(u, gU, gV, gP, gI);
                return;
            } else {
                System.out.println(" Bem-vindo, " + u.getNome() + "!");
                menuUtilizador(u, gU, gV, gI, gP);
            }
        } else {
            System.out.println("Email ou Palavra-Passe inválido.");
        }
    }

    //menu admin
    private static void menuAdmin(Utilizador admin, GerirUtilizador gU,GerirVoluntario gV, GerirProgramaVoluntariado gP, GerirInscricao gI) {
        boolean flag = true;
        while (true) {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1) Criar programa de voluntariado");
            System.out.println("2) Listar programas");
            System.out.println("3) Procurar programa por ID");
            System.out.println("4) Listar utilizadores");
            System.out.println("5) Listar voluntários");
            System.out.println("6) Listar inscrições");
            System.out.println("0) Terminar sessão");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> criarPrograma(gP);
                case 2 -> gP.listAll().forEach(System.out::println);
                case 3 -> procurarProgramaPorId(gP);
                case 4 -> gU.listAll().forEach(System.out::println);
                case 5 -> gV.listAll().forEach(System.out::println);
                case 6 -> gI.listAll().forEach(System.out::println);
                case 0 -> {
                    System.out.println(" Sessão terminada. A voltar ao menu principal...");
                    return;
                }
                default -> System.out.println(" Opção inválida!");
            }
        }
    }
    private static void criarPrograma(GerirProgramaVoluntariado gP) {
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

        long id = gP.create(titulo, descricao, localizacao, vagas, di, df, total);
        System.out.println(id > 0 ? "Programa criado com ID: " + id : "Falhou a criação!!!");
    }
    
    private static void procurarProgramaPorId(GerirProgramaVoluntariado gP) {
        System.out.print("ID do programa: ");
        long id = ler.nextLong();
        ProgramaVoluntariado p = gP.read(id);
        if (p == null) {
        	System.out.println("Programa não encontrado.");
        }
        else System.out.println(p);
    }

    //menu Utilizador
    private static void menuUtilizador(Utilizador u, GerirUtilizador gU, GerirVoluntario gV, GerirInscricao gI, GerirProgramaVoluntariado gP) {
        boolean flag = true;
        while (flag==true) {
            System.out.println("\n===== MENU UTILIZADOR =====");
            System.out.println("1) Consultar Programas de Voluntariado disponíveis");
            System.out.println("2) Inscrever-se num Programa de Voluntariado");
            System.out.println("3) Mudar Password");
            System.out.println("0) Terminar sessão");
            System.out.print("Escolha: ");
            int opcao = ler.nextInt();

            switch (opcao) {
                case 1 -> gP.listAll().forEach(System.out::println);
                case 2 -> inscreverPrograma(u, gV, gI, gP);
                case 3 -> alterarPassword(u, gU);
                case 0 -> flag = false;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    //inscrição-> garante que existe voluntário
    private static void inscreverPrograma(Utilizador u, GerirVoluntario gV, GerirInscricao gI, GerirProgramaVoluntariado gP) {
        System.out.println("\n--- Programas disponíveis ---");
        gP.listAll().forEach(p -> System.out.println(p.getId() + " - " + p.getTitulo()));

        System.out.print("Escolha o ID do programa para inscrever-se: ");
        long pid = ler.nextLong();

        // cria ou obtém o voluntário correspondente ao utilizador
        long voluntarioId = gV.ensureVoluntarioForUtilizador(u.getId());
        if (voluntarioId <= 0) {
            System.out.println("Não foi possível criar o voluntário para este utilizador.");
        }

        long id = gI.create(voluntarioId, pid, 0);
        System.out.println(id > 0 ? "Inscrição realizada!!!" : "Erro!!!");
    }

    private static void alterarPassword(Utilizador u, GerirUtilizador gU) {
        System.out.print("Password atual: ");
        String passAntiga = ler.next();
        ler.nextLine();
        System.out.print("Nova password: ");
        String passNova = ler.nextLine();
        boolean alterada = gU.atualizaPassword(u.getEmail(), passAntiga, passNova);
        System.out.println(alterada ? "Password alterada com sucesso!!!" : "Erro!!!");
    }
    private static LocalDate lerData() {
        while (true) {
            try { return LocalDate.parse(ler.nextLine()); }
            catch (DateTimeParseException e) { System.out.print("Data inválida (formato AAAA-MM-DD): "); }
        }
    }
}