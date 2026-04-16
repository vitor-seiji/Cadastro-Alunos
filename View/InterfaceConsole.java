package View;
import Controller.Armazenador;
import Controller.ArmazenadorInterface;
import Model.Aluno;

import javax.swing.*;
import java.util.Scanner;


public class InterfaceConsole implements InterfaceCadastro {
    //Instancia o armazenador
    private ArmazenadorInterface armazenador;

    //Instacia do scanner
    private Scanner sc = new Scanner(System.in);

    //Construtor da interface
    public InterfaceConsole(int qtde) {
        this.armazenador = new Armazenador(qtde);
    }

    //Método de inserir um aluno
    public void inserirAluno() {
        if (armazenador.quantidadeMaxAlunos()) {//Se o cadastro estiver cheio não insere
            System.out.println("Cadastro cheio.");
            return;
        }

        //Leitura do ra
        String ra;
        do{
            System.out.println("Ra: ");
            ra = sc.nextLine();
            if(!(armazenador.validarRA(ra))){//Valida se o Ra pertence a um aluno
                System.out.println("O Ra informado ja está em uso");
            }
            if(ra.isBlank()){//Se o Ra for vazio
                System.out.println("Insira um Ra válido");
            }
        }while(ra.isBlank() || !(armazenador.validarRA(ra)));//Loop para repetir enquanto não entra um Ra válido

        //Leitura do nome
        String nome;
        do{
            System.out.println("Nome: ");
            nome = sc.nextLine();
            if(nome.isBlank()){//Se o nome for vazio
                System.out.println("Nome inválido.");
            }
        }while(nome.isBlank());//Loop para repetir enquanto não entra um nome válido

        //Leitura da idade
        int idade = 0;
        do{
            try {
                System.out.println("Idade: ");
                idade = sc.nextInt();
                sc.nextLine();//Limpeza do buffer
                if(idade <= 0 || idade > 100){//Se a idade for igual ou menor que 0
                    System.out.println("Idade inválida.");
                }
            } catch (Exception e) {//Caso entre com um valor não numérico
                System.out.println("Idade inválida.");
                sc.nextLine();
            }
        }while(idade <= 0 || idade > 100);//Loop enquanto a idade não for válida

        //Leitura do curso
        String curso;
        do{
            System.out.println("Curso: ");
            curso = sc.nextLine();
            if(curso.isBlank()){//Caso o curso for vazio
                System.out.println("Curso inválido.");
            }
        }while(curso.isBlank());//Loop

        //Leitura do semestre
        int semestre = 0;
        do{
            try {
                System.out.println("Semestre: ");
                semestre = sc.nextInt();
                if(semestre <= 0){//Caso o semestre for menor ou igual a 0
                    System.out.println("Semestre inválido.");
                }
            } catch (Exception e) {//Se o valor inserido não for numérico
                System.out.println("Semestre inválido.");
                sc.nextLine();
            }
        }while(semestre <= 0);//Loop

        //Instancia o novo aluno
        Aluno a = new Aluno(nome, idade, ra, curso, semestre);

        //Insere o novo aluno no armazenador
        if(armazenador.inserir(a)){
            System.out.println("Aluno cadastrado com sucesso!");
        }
        else{
            System.out.println("Erro ao inserir aluno!");
        }
    }

    //Método para remover um aluno do cadastro
    public void removerAluno() {
        if (armazenador.quantidadeMinAlunos()) {//Verifica se há ao menos um aluno cadastrado para ser removido
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        //Leitura o Ra do aluno a ser removido
        System.out.println("Ra: ");
        String ra = sc.nextLine();

        //Verifica se há algum aluno cadastrado com o Ra informado
        if(armazenador.validarRA(ra)){
            System.out.println("O Ra não encontrado");
            return;
        }
        else{
            //Remove o aluno do cadastro
            if(armazenador.remover(ra)){
                System.out.println("Aluno removido com sucesso!");
                return;
            }
        }
        System.out.println("Erro ao remover aluno!");
    }

    //Método para editar um aluno cadastrado
    public void editarAluno() {
        if (armazenador.quantidadeMinAlunos()) {//Caso não tenha nenhum aluno cadastrado
            System.out.println( "Nenhum aluno cadastrado.");
            return;
        }
        //Leitura do Ra do aluno a ser editado
        String ra;
        System.out.println("Ra: ");
        ra = sc.nextLine();
        if(armazenador.validarRA(ra)){//Verifica se há um aluno com o Ra inserido
            System.out.println("Ra não encontrado.");
            return;
        }

        //Instancia um aluno com os dados
        Aluno a = armazenador.buscarAluno(ra);
        int op = 0;
        boolean ok = true;

        //Loop para a edição dos dados do aluno
        do{
            ok = true;
            do{
                try{
                    System.out.println(
                            "\nO que deseja alterar:\n1- Nome\n2- Idade\n3- Curso\n" +
                                    "4- Semestre\n0 - Sair");
                    op = sc.nextInt();
                    sc.nextLine();
                    if(op >= 0 && op <= 4){
                        ok = false;
                    }
                    else{
                        System.out.println("Insira uma opção válida");
                        sc.nextLine();
                    }
                }catch (Exception e){
                    System.out.println("Insira uma opção válida");
                    sc.nextLine();
                }
            }while(ok);

            if(op == 0){
                System.out.println("Saindo...");
                return;
            }

            switch(op){
                case 1:
                    String nome;
                    //Validação do nome
                    do{
                        System.out.println("Nome: ");
                        nome = sc.nextLine();
                        if(nome.isBlank()){
                            System.out.println("Digite um nome válido");
                        }
                    }while(nome.isBlank());
                    a.setNome(nome);
                    break;
                case 2:
                    int idade = 0;
                    //Validação da idade
                    do{
                        try {
                            System.out.println("Idade: ");
                            idade = sc.nextInt();
                            sc.nextLine();
                            if(idade <= 0){
                                System.out.println("Insira uma idade válida");
                            }
                        }catch(Exception e){
                            System.out.println("Insira uma idade válida");
                            sc.nextLine();
                        }
                    }while(idade <= 0);
                    a.setIdade(idade);
                    break;
                case 3:
                    String curso;
                    //Validação do curso
                    do{
                        System.out.println("Curso: ");
                        curso = sc.nextLine();
                        if(curso.isBlank()){
                            System.out.println("Digite um curso válido");
                            sc.nextLine();
                        }
                    }while(curso.isBlank());
                    a.setCurso(curso);
                    break;
                case 4:
                    int semestre = 0;
                    //Validação do semestre
                    do{
                        try {
                            System.out.println("Semestre: ");
                            semestre = sc.nextInt();
                            if(semestre <= 0){
                                System.out.println("Insira um semestre válido");
                            }
                        }catch(Exception e){
                            System.out.println("Insira um semestre válido");
                            sc.nextLine();
                        }
                    }while(semestre <= 0);
                    a.setSemestre(semestre);
                    break;
                default:
                    System.out.println("Insira uma opção válida");
                    break;
            }
            System.out.println("Cadastro alterado com sucesso!");
        }while(op != 0);
    }

    //Método para listar os alunos cadastrados
    public void listarAlunos() {
        if(!armazenador.quantidadeMinAlunos()){//Caso tenha ao menos um aluno cadastrado
            System.out.println(armazenador.listar());
        }
        else{
            System.out.println("Nenhum aluno cadastrado!");
        }
    }

    //Retorna o total de alunos cadastrados
    public void contagem() {
        System.out.println("Total: " + armazenador.contagem());
    }
    
    public void salvarArquivo(){
        String nomeArq;
        do{
            System.out.println("Arquivo: ");
            nomeArq = sc.nextLine();
            if(nomeArq.isBlank()){
                System.out.println("Insira um arquivo válido.");
            }
        }while(nomeArq.isBlank());
        ArquivoBinario Ab = new ArquivoBinario(nomeArq);
        Object Alunos = armazenador.retornarAlunos();
        Ab.gravarObj(Alunos);
    }
    
    public Object lerArquivo(){
        String nomeArq;
        do{
            System.out.println("Arquivo: ");
            nomeArq = sc.nextLine();
            if(nomeArq.isBlank()){
                System.out.println("Insira um arquivo válido.");
            }
        }while(nomeArq.isBlank());
        ArquivoBinario Ab = new ArquivoBinario(nomeArq);
        Aluno[] alunos = (Aluno[]) Ab.lerObj();
        return alunos;
    }

    //Menu principal do cadastro
    public void executar() {
        int op = -1;
        boolean on = true;

        do {
            try {
                System.out.println(
                        "\n0- Sair\n1- Inserir Aluno\n2- Remover Aluno\n" +
                                "3- Listar Alunos\n4- Editar cadastro\n5- Salvar Cadastro\n6- Baixar Cadastro\n\nOpção:");
                op = sc.nextInt();
                sc.nextLine();
            }catch (Exception e) {
                System.out.println("Insira uma opção válida");
                sc.nextLine();
            }

            switch (op) {
                case 0:
                    System.out.println("Saindo...");
                    on = false;
                    break;
                case 1:
                    inserirAluno();
                    break;
                case 2:
                    removerAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    editarAluno();
                    break;
                case 5:
                    salvarArquivo();
                    break;
                case 6:
                    lerArquivo();
                    break;
                default:
                    System.out.println("Opção inválida.\n");
                    break;
            }
        } while (on);
    }
}
