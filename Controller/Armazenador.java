package Controller;

import Model.Aluno;

/**
 * Implementa o armazenamento de alunos usando uma lista encadeada.
 * Não há limite de capacidade pré-definido.
 */
public class Armazenador implements ArmazenadorInterface {
    private ListaEncadeada lista;

    /**
     * Construtor — capacidade ilimitada, sem parâmetros necessários.
     */
    public Armazenador() {
        lista = new ListaEncadeada();
    }

    /**
     * Insere um aluno na lista encadeada.
     * @param a aluno a ser inserido
     * @return true sempre (sem limite de capacidade)
     */
    @Override
    public boolean inserir(Aluno a) {
        lista.inserir(a);
        return true;
    }

    /**
     * Remove um aluno da lista baseado em seu RA.
     * @param ra registro acadêmico do aluno
     * @return true se removido, false se não encontrado
     */
    @Override
    public boolean remover(String ra) {
        return lista.remover(ra);
    }

    /**
     * Busca e retorna o aluno com o RA informado.
     * @param ra registro acadêmico
     * @return aluno encontrado ou null
     */
    @Override
    public Aluno buscarAluno(String ra) {
        return lista.buscar(ra);
    }

    /**
     * Lista todos os alunos cadastrados.
     * @return string formatada com os dados de todos os alunos
     */
    @Override
    public String listar() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Lista de Alunos ===\n\n");

        No atual = lista.getCabeca();
        int num = 1;
        while (atual != null) {
            Aluno a = atual.getAluno();
            sb.append("Aluno ").append(num++).append(":\n");
            sb.append("RA: ").append(a.getRa()).append("\n");
            sb.append("Nome: ").append(a.getNome().getNome()).append("\n");
            sb.append("Idade: ").append(a.getIdade()).append("\n");
            sb.append("Curso: ").append(a.getCurso()).append("\n");
            sb.append("Semestre: ").append(a.getSemestre()).append("\n\n");
            atual = atual.getProximo();
        }
        return sb.toString();
    }

    /**
     * Retorna a quantidade de alunos cadastrados.
     */
    @Override
    public int contagem() {
        return lista.getTamanho();
    }

    /**
     * Com lista encadeada não há limite de capacidade.
     * @return sempre false
     */
    @Override
    public boolean quantidadeMaxAlunos() {
        return false;
    }

    /**
     * Verifica se o cadastro está vazio.
     * @return true se não houver alunos cadastrados
     */
    @Override
    public boolean quantidadeMinAlunos() {
        return lista.isVazia();
    }

    /**
     * Verifica se um RA já está em uso.
     * @param ra registro acadêmico a validar
     * @return true se o RA ainda não existe, false se já está em uso
     */
    @Override
    public boolean validarRA(String ra) {
        return lista.validarRA(ra);
    }

    /**
     * Retorna os alunos como array (para serialização em arquivo).
     */
    @Override
    public Aluno[] retornarAlunos() {
        return lista.toArray();
    }

    /**
     * Carrega alunos a partir de um array (ao ler arquivo).
     * @param alunos array de alunos
     */
    @Override
    public void setAlunos(Aluno[] alunos) {
        lista.carregarDeArray(alunos);
    }
}