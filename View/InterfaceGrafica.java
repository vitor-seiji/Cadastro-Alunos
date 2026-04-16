package View;
import Controller.Armazenador;
import Controller.ArmazenadorInterface;
import Model.Aluno;
import java.io.*;

import javax.swing.JOptionPane;

public class InterfaceGrafica implements InterfaceCadastro {
    // Instancia o armazenador
    private ArmazenadorInterface armazenador;

    // Construtor da interface
    public InterfaceGrafica(int qtde) {
        this.armazenador = new Armazenador(qtde);
    }

    // Método de inserir um aluno
    public void inserirAluno() {
        if (armazenador.quantidadeMaxAlunos()) {
            JOptionPane.showMessageDialog(null, "Cadastro cheio.");
            return;
        }

        // Leitura do Ra
        String ra;
        do {
            ra = JOptionPane.showInputDialog("Ra:");
            if (ra == null) return; // Cancelou
            if (!(armazenador.validarRA(ra))) {
                JOptionPane.showMessageDialog(null, "O Ra informado já está em uso");
            }
            if (ra.isBlank()) {
                JOptionPane.showMessageDialog(null, "Insira um Ra válido");
            }
        } while (ra.isBlank() || !(armazenador.validarRA(ra)));

        // Leitura do nome
        String nome;
        do {
            nome = JOptionPane.showInputDialog("Nome:");
            if (nome == null) return;
            if (nome.isBlank()) {
                JOptionPane.showMessageDialog(null, "Nome inválido.");
            }
        } while (nome.isBlank());

        // Leitura da idade
        int idade = 0;
        do {
            try {
                String input = JOptionPane.showInputDialog("Idade:");
                if (input == null) return;
                idade = Integer.parseInt(input);
                if (idade <= 0 || idade > 100) {
                    JOptionPane.showMessageDialog(null, "Idade inválida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Idade inválida.");
            }
        } while (idade <= 0 || idade > 100);

        // Leitura do curso
        String curso;
        do {
            curso = JOptionPane.showInputDialog("Curso:");
            if (curso == null) return;
            if (curso.isBlank()) {
                JOptionPane.showMessageDialog(null, "Curso inválido.");
            }
        } while (curso.isBlank());

        // Leitura do semestre
        int semestre = 0;
        do {
            try {
                String input = JOptionPane.showInputDialog("Semestre:");
                if (input == null) return;
                semestre = Integer.parseInt(input);
                if (semestre <= 0) {
                    JOptionPane.showMessageDialog(null, "Semestre inválido.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Semestre inválido.");
            }
        } while (semestre <= 0);

        Aluno a = new Aluno(nome, idade, ra, curso, semestre);
        if (armazenador.inserir(a)) {
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao inserir aluno!");
        }
    }

    // Método para a remoção de um aluno
    public void removerAluno() {
        if (armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String ra = JOptionPane.showInputDialog("RA:");
        if (ra == null) return;
        if (armazenador.validarRA(ra)) {
            JOptionPane.showMessageDialog(null, "Ra não encontrado");
            return;
        }
        if (armazenador.remover(ra)) {
            JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover aluno!");
        }
    }

    // Método para a edição dos dados de um aluno — menu por botões verticais
    public void editarAluno() {
        if (armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }

        String ra = JOptionPane.showInputDialog("Ra:");
        if (ra == null) return;
        if (armazenador.validarRA(ra)) {
            JOptionPane.showMessageDialog(null, "Ra não encontrado.");
            return;
        }

        Aluno a = armazenador.buscarAluno(ra);

        // Opções do menu de edição como botões verticais
        String[] opcoesEditar = {"Nome", "Idade", "Curso", "Semestre", "Sair"};

        int op;
        do {
            op = JOptionPane.showOptionDialog(
                    null,
                    "O que deseja alterar?",
                    "Editar Aluno",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesEditar,
                    opcoesEditar[0]
            );

            // op == -1 significa que fechou a janela; trata como "Sair" (índice 4)
            if (op == -1 || op == 4) {
                JOptionPane.showMessageDialog(null, "Saindo...");
                return;
            }

            switch (op) {
                case 0: // Nome
                    String nome;
                    do {
                        nome = JOptionPane.showInputDialog("Nome:");
                        if (nome == null) return;
                        if (nome.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Digite um nome válido");
                        }
                    } while (nome.isBlank());
                    a.setNome(nome);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    break;

                case 1: // Idade
                    int idade = 0;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Idade:");
                            if (input == null) return;
                            idade = Integer.parseInt(input);
                            if (idade <= 0) {
                                JOptionPane.showMessageDialog(null, "Insira uma idade válida");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Insira uma idade válida");
                        }
                    } while (idade <= 0);
                    a.setIdade(idade);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    break;

                case 2: // Curso
                    String curso;
                    do {
                        curso = JOptionPane.showInputDialog("Curso:");
                        if (curso == null) return;
                        if (curso.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Digite um curso válido");
                        }
                    } while (curso.isBlank());
                    a.setCurso(curso);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    break;

                case 3: // Semestre
                    int semestre = 0;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Semestre:");
                            if (input == null) return;
                            semestre = Integer.parseInt(input);
                            if (semestre <= 0) {
                                JOptionPane.showMessageDialog(null, "Insira um semestre válido");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Insira um semestre válido");
                        }
                    } while (semestre <= 0);
                    a.setSemestre(semestre);
                    JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
                    break;
            }
        } while (op != 4 && op != -1);
    }

    // Método para listar os alunos cadastrados
    public void listarAlunos() {
        if (!armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, armazenador.listar());
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado!");
        }
    }

    // Método para a contagem de alunos cadastrados
    public void contagem() {
        JOptionPane.showMessageDialog(null, "Total: " + armazenador.contagem());
    }
    
    public void salvarArquivo(){
        String nomeArq;
        do{
           nomeArq = JOptionPane.showInputDialog("Arquivo:"); 
           if(nomeArq.isBlank()){
               JOptionPane.showMessageDialog(null, "Insira um Arquivo válido.");
           }
        }while(nomeArq.isBlank());
        ArquivoBinario Ab = new ArquivoBinario(nomeArq);
        Object Alunos = armazenador.retornarAlunos();
        Ab.gravarObj(Alunos);
    }
    
    public Object lerArquivo(){
        String nomeArq;
        try{
            do{
               nomeArq = JOptionPane.showInputDialog("Arquivo:"); 
               if(nomeArq.isBlank()){
                   JOptionPane.showMessageDialog(null, "Insira um Arquivo válido.");
               }
            }while(nomeArq.isBlank());
            ArquivoBinario Ab = new ArquivoBinario(nomeArq);
            Aluno[] alunos = (Aluno[]) Ab.lerObj();
            return alunos;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar arquivo.");
            return null;
        }
    }

    //Método para o menu principal do cadastro
    public void executar() {
        String[] opcoes = {"Inserir Aluno", "Remover Aluno", "Listar Alunos", "Editar Cadastro", "Salvar Arquivo", "Ler Arquivo", "Sair"};
        int op;

        do {//Botões verticais
            op = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Cadastro",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            // Fechou a janela → trata como Sair
            if (op == -1) op = 7;

            switch (op) {
                case 0:
                    inserirAluno();
                    break;
                case 1:
                    removerAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    editarAluno();
                    break;
                case 4:
                    salvarArquivo();
                    break;
                case 5:
                    armazenador.setAlunos((Aluno[]) lerArquivo());
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }
        } while (op != 6 && op != 7);
    }
    
}
