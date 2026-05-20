package View;

import Controller.Armazenador;
import Controller.ArmazenadorInterface;
import Model.Aluno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interface de cadastro via janela gráfica (Swing).
 * Utiliza lista encadeada — sem limite de alunos.
 */
public class InterfaceGrafica implements InterfaceCadastro {

    private ArmazenadorInterface armazenador;

    /**
     * Construtor 
     */
    public InterfaceGrafica() {
        this.armazenador = new Armazenador();
    }

    // ------------------------------------------------------------------ inserir
    @Override
    public void inserirAluno() {
        JDialog dialog = new JDialog((Frame) null, "Cadastrar Aluno", true);
        dialog.setLayout(new BorderLayout(10, 10));

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

        JLabel lblErro = new JLabel(" ");
        lblErro.setForeground(Color.RED);
        lblErro.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar  = new JButton("Cancelar");
        JButton btnCadastrar = new JButton("Cadastrar");
        botoes.add(btnCancelar);
        botoes.add(btnCadastrar);

        JPanel sul = new JPanel(new BorderLayout());
        sul.add(lblErro, BorderLayout.NORTH);
        sul.add(botoes, BorderLayout.SOUTH);

        dialog.add(campos, BorderLayout.CENTER);
        dialog.add(sul,    BorderLayout.SOUTH);

        final boolean[] confirmado = {false};

        btnCancelar.addActionListener(e -> dialog.dispose());

        btnCadastrar.addActionListener(e -> {
            String ra       = tfRa.getText().trim();
            String nome     = tfNome.getText().trim();
            String idadeStr = tfIdade.getText().trim();
            String curso    = tfCurso.getText().trim();
            String semStr   = tfSemestre.getText().trim();

            if (ra.isBlank() || nome.isBlank() || idadeStr.isBlank()
                    || curso.isBlank() || semStr.isBlank()) {
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

            armazenador.inserir(new Aluno(nome, idade, ra, curso, semestre));
            confirmado[0] = true;
            dialog.dispose();
        });

        dialog.pack();
        dialog.setMinimumSize(new Dimension(350, dialog.getHeight()));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        if (confirmado[0]) {
            JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
        }
    }

    // ------------------------------------------------------------------ remover
    @Override
    public void removerAluno() {
        if (armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        String ra = JOptionPane.showInputDialog("RA do aluno a remover:");
        if (ra == null) return;
        if (armazenador.validarRA(ra)) {
            JOptionPane.showMessageDialog(null, "RA não encontrado.");
            return;
        }
        if (armazenador.remover(ra)) {
            JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover aluno.");
        }
    }

    // ------------------------------------------------------------------ editar
    @Override
    public void editarAluno() {
        if (armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }

        String ra = JOptionPane.showInputDialog("RA do aluno a editar:");
        if (ra == null) return;
        if (armazenador.validarRA(ra)) {
            JOptionPane.showMessageDialog(null, "RA não encontrado.");
            return;
        }

        Aluno a = armazenador.buscarAluno(ra);

        JDialog dialog = new JDialog((Frame) null, "Editar Aluno", true);
        dialog.setLayout(new BorderLayout(10, 10));

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

        JLabel lblErro = new JLabel(" ");
        lblErro.setForeground(Color.RED);
        lblErro.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar   = new JButton("Salvar");
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        JPanel sul = new JPanel(new BorderLayout());
        sul.add(lblErro, BorderLayout.NORTH);
        sul.add(botoes,  BorderLayout.SOUTH);

        dialog.add(campos, BorderLayout.CENTER);
        dialog.add(sul,    BorderLayout.SOUTH);

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

    // ------------------------------------------------------------------ listar
    @Override
    public void listarAlunos() {
        if (armazenador.quantidadeMinAlunos()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
            return;
        }
        JOptionPane.showMessageDialog(null, armazenador.listar());
    }

    // ---------------------------------------------------------------- contagem
    @Override
    public void contagem() {
        JOptionPane.showMessageDialog(null, "Total de alunos: " + armazenador.contagem());
    }

    // ------------------------------------------------------------------ salvar
    @Override
    public void salvarArquivo() {
        String nomeArq;
        if(!armazenador.quantidadeMinAlunos()){
            do {
                nomeArq = JOptionPane.showInputDialog("Nome do arquivo:");
                if (nomeArq == null) return;
                if (nomeArq.isBlank()) JOptionPane.showMessageDialog(null, "Insira um nome válido.");
            } while (nomeArq.isBlank());
    
            try {
                ArquivoBinario ab = new ArquivoBinario(nomeArq);
                ab.gravarObj(armazenador.retornarAlunos());
                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Não há alunos cadastrados.");
        }
    }

    // -------------------------------------------------------------------- ler
    @Override
    public Object lerArquivo() {
        String nomeArq;
        do {
            nomeArq = JOptionPane.showInputDialog("Nome do arquivo:");
            if (nomeArq == null) return null;
            if (nomeArq.isBlank()) JOptionPane.showMessageDialog(null, "Insira um nome válido.");
        } while (nomeArq.isBlank());

        try {
            ArquivoBinario ab = new ArquivoBinario(nomeArq);
            Aluno[] alunos = (Aluno[]) ab.lerObj();
            JOptionPane.showMessageDialog(null, "Arquivo carregado com sucesso!");
            return alunos;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar: " + e.getMessage());
            return null;
        }
    }

    // ----------------------------------------------------------------- executar
    @Override
    public void executar() {
        String[] opcoes = {
            "Inserir Aluno", "Remover Aluno", "Listar Alunos",
            "Editar Cadastro", "Contagem", "Salvar Arquivo", "Ler Arquivo", "Sair"
        };
        int op;
        do {
            op = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu — Cadastro de Alunos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );
            if (op == -1) op = 7; // fechou a janela → Sair

            switch (op) {
                case 0: inserirAluno(); break;
                case 1: removerAluno(); break;
                case 2: listarAlunos(); break;
                case 3: editarAluno();  break;
                case 4: contagem();     break;
                case 5: salvarArquivo(); break;
                case 6:
                    Aluno[] lidos = (Aluno[]) lerArquivo();
                    if (lidos != null) armazenador.setAlunos(lidos);
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        } while (op != 7);
    }
}