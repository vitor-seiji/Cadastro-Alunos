package View;
import Controller.Armazenador;
import Controller.ArmazenadorInterface;
import Model.Aluno;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceGrafica implements InterfaceCadastro {
    // Instancia o armazenador
    private ArmazenadorInterface armazenador;

    // Construtor da interface
    public InterfaceGrafica(int qtde) {
        this.armazenador = new Armazenador(qtde);
    }

    // Método de inserir um aluno — formulário único com todos os campos
    public void inserirAluno() {
        if (armazenador.quantidadeMaxAlunos()) {
            JOptionPane.showMessageDialog(null, "Cadastro cheio.");
            return;
        }

        JDialog dialog = new JDialog((Frame) null, "Cadastrar Aluno", true);
        dialog.setLayout(new BorderLayout(10, 10));

        // Painel dos campos
        JPanel campos = new JPanel(new GridLayout(5, 2, 8, 8));
        campos.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JTextField tfRa       = new JTextField();
        JTextField tfNome     = new JTextField();
        JTextField tfIdade    = new JTextField();
        JTextField tfCurso    = new JTextField();
        JTextField tfSemestre = new JTextField();

        campos.add(new JLabel("RA:"));       campos.add(tfRa);
        campos.add(new JLabel("Nome:"));     campos.add(tfNome);
        campos.add(new JLabel("Idade:"));    campos.add(tfIdade);
        campos.add(new JLabel("Curso:"));    campos.add(tfCurso);
        campos.add(new JLabel("Semestre:")); campos.add(tfSemestre);

        // Label de erro
        JLabel lblErro = new JLabel(" ");
        lblErro.setForeground(Color.RED);
        lblErro.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        // Painel dos botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar  = new JButton("Cancelar");
        JButton btnCadastrar = new JButton("Cadastrar");
        botoes.add(btnCancelar);
        botoes.add(btnCadastrar);

        JPanel sul = new JPanel(new BorderLayout());
        sul.add(lblErro, BorderLayout.NORTH);
        sul.add(botoes, BorderLayout.SOUTH);

        dialog.add(campos, BorderLayout.CENTER);
        dialog.add(sul, BorderLayout.SOUTH);

        final boolean[] confirmado = {false};

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnCadastrar.addActionListener(e -> {
            String ra       = tfRa.getText().trim();
            String nome     = tfNome.getText().trim();
            String idadeStr = tfIdade.getText().trim();
            String curso    = tfCurso.getText().trim();
            String semStr   = tfSemestre.getText().trim();

            if (ra.isBlank() || nome.isBlank() || idadeStr.isBlank() || curso.isBlank() || semStr.isBlank()) {
                lblErro.setText("Preencha todos os campos.");
                return;
            }
            if (!armazenador.validarRA(ra)) {
                lblErro.setText("RA já está em uso.");
                return;
            }
            int idade, semestre;
            try {
                idade = Integer.parseInt(idadeStr);
                if (idade <= 0 || idade > 100) { lblErro.setText("Idade inválida (1-100)."); return; }
            } catch (NumberFormatException ex) {
                lblErro.setText("Idade deve ser um número inteiro.");
                return;
            }
            try {
                semestre = Integer.parseInt(semStr);
                if (semestre <= 0) { lblErro.setText("Semestre deve ser maior que 0."); return; }
            } catch (NumberFormatException ex) {
                lblErro.setText("Semestre deve ser um número inteiro.");
                return;
            }

            Aluno a = new Aluno(nome, idade, ra, curso, semestre);
            if (armazenador.inserir(a)) {
                confirmado[0] = true;
                dialog.dispose();
            } else {
                lblErro.setText("Erro ao inserir aluno.");
            }
        });

        dialog.pack();
        dialog.setMinimumSize(new Dimension(350, dialog.getHeight()));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (confirmado[0]) {
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
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

    // Método para a edição dos dados de um aluno — formulário único com todos os campos
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

        JDialog dialog = new JDialog((Frame) null, "Editar Aluno", true);
        dialog.setLayout(new BorderLayout(10, 10));

        // Painel dos campos preenchidos com os dados atuais do aluno
        JPanel campos = new JPanel(new GridLayout(4, 2, 8, 8));
        campos.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JTextField tfNome     = new JTextField(a.getNome().getNome());
        JTextField tfIdade    = new JTextField(String.valueOf(a.getIdade()));
        JTextField tfCurso    = new JTextField(a.getCurso());
        JTextField tfSemestre = new JTextField(String.valueOf(a.getSemestre()));

        campos.add(new JLabel("Nome:"));     campos.add(tfNome);
        campos.add(new JLabel("Idade:"));    campos.add(tfIdade);
        campos.add(new JLabel("Curso:"));    campos.add(tfCurso);
        campos.add(new JLabel("Semestre:")); campos.add(tfSemestre);

        // Label de erro
        JLabel lblErro = new JLabel(" ");
        lblErro.setForeground(Color.RED);
        lblErro.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        // Painel dos botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar   = new JButton("Salvar");
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        JPanel sul = new JPanel(new BorderLayout());
        sul.add(lblErro, BorderLayout.NORTH);
        sul.add(botoes, BorderLayout.SOUTH);

        dialog.add(campos, BorderLayout.CENTER);
        dialog.add(sul, BorderLayout.SOUTH);

        final boolean[] confirmado = {false};

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnSalvar.addActionListener(e -> {
            String nome     = tfNome.getText().trim();
            String idadeStr = tfIdade.getText().trim();
            String curso    = tfCurso.getText().trim();
            String semStr   = tfSemestre.getText().trim();

            if (nome.isBlank() || idadeStr.isBlank() || curso.isBlank() || semStr.isBlank()) {
                lblErro.setText("Preencha todos os campos.");
                return;
            }
            int idade, semestre;
            try {
                idade = Integer.parseInt(idadeStr);
                if (idade <= 0 || idade > 100) { lblErro.setText("Idade inválida (1-100)."); return; }
            } catch (NumberFormatException ex) {
                lblErro.setText("Idade deve ser um número inteiro.");
                return;
            }
            try {
                semestre = Integer.parseInt(semStr);
                if (semestre <= 0) { lblErro.setText("Semestre deve ser maior que 0."); return; }
            } catch (NumberFormatException ex) {
                lblErro.setText("Semestre deve ser um número inteiro.");
                return;
            }

            a.setNome(nome);
            a.setIdade(idade);
            a.setCurso(curso);
            a.setSemestre(semestre);
            confirmado[0] = true;
            dialog.dispose();
        });

        dialog.pack();
        dialog.setMinimumSize(new Dimension(350, dialog.getHeight()));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (confirmado[0]) {
            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
        }
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

    public void salvarArquivo() {
        String nomeArq;
        do {
            nomeArq = JOptionPane.showInputDialog("Arquivo:");
            if (nomeArq == null) return;
            if (nomeArq.isBlank()) {
                JOptionPane.showMessageDialog(null, "Insira um Arquivo válido.");
            }
        } while (nomeArq.isBlank());
        try {
            ArquivoBinario Ab = new ArquivoBinario(nomeArq);
            Ab.gravarObj(armazenador.retornarAlunos());
            JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public Object lerArquivo() {
        String nomeArq;
        do {
            nomeArq = JOptionPane.showInputDialog("Arquivo:");
            if (nomeArq == null) return null;
            if (nomeArq.isBlank()) {
                JOptionPane.showMessageDialog(null, "Insira um Arquivo válido.");
            }
        } while (nomeArq.isBlank());
        try {
            ArquivoBinario Ab = new ArquivoBinario(nomeArq);
            Aluno[] alunos = (Aluno[]) Ab.lerObj();
            JOptionPane.showMessageDialog(null, "Arquivo carregado com sucesso!");
            return alunos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar arquivo: " + e.getMessage());
            return null;
        }
    }

    // Método para o menu principal do cadastro
    public void executar() {
        String[] opcoes = {"Inserir Aluno", "Remover Aluno", "Listar Alunos", "Editar Cadastro", "Salvar Arquivo", "Ler Arquivo", "Sair"};
        int op;

        do {
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