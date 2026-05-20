package Controller;
import Model.Aluno;

/**
 * Interface que define as operações do armazenador de alunos.
 */
public interface ArmazenadorInterface {

    /** Insere um aluno no armazenador. */
    public boolean inserir(Aluno a);

    /** Remove um aluno do armazenador baseado em seu RA. */
    public boolean remover(String ra);

    /** Lista todos os alunos cadastrados. */
    public String listar();

    /** Retorna o total de alunos cadastrados. */
    public int contagem();

    /**
     * Com lista encadeada não há limite real; implementações podem retornar
     * sempre false. Mantido para compatibilidade com a interface.
     */
    public boolean quantidadeMaxAlunos();

    /** Retorna true se o cadastro estiver vazio. */
    public boolean quantidadeMinAlunos();

    /** Retorna true se o RA não existe, false se já está em uso. */
    public boolean validarRA(String ra);

    /** Busca um aluno pelo RA. */
    public Aluno buscarAluno(String ra);

    /** Retorna todos os alunos como array (para salvar em arquivo). */
    public Aluno[] retornarAlunos();

    /** Carrega alunos a partir de um array (ao ler arquivo). */
    public void setAlunos(Aluno[] alunos);
}