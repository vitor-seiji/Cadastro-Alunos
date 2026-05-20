package Controller;
import Model.Aluno;

/**
 * Interface que define as operacoes do armazenador de alunos.
 */
public interface ArmazenadorInterface {

    /**
     * Insere um aluno no armazenador.
     * @param a aluno a ser inserido
     * @return true se inserido com sucesso, false caso contrario
     */
    //Insere um aluno no armazenador
    public boolean inserir(Aluno a);

    /**
     * Remove um aluno do armazenador baseado em seu Ra.
     * @param ra registro academico do aluno a ser removido
     * @return true se removido com sucesso, false caso contrario
     */
    //Remove um aluno do armazenador beseado em seu Ra
    public boolean remover(String ra);

    /**
     * Lista todos os alunos cadastrados no armazenador.
     * @return string com os dados de todos os alunos
     */
    //Lista todos os alunos cadastrados no armazenador
    public String listar();

    /**
     * Retorna o total de alunos cadastrados no armazenador.
     * @return total de alunos cadastrados
     */
    //Retorna o total de alunos cadastrados no armazenador
    public int contagem();

    /**
     * Retorna se o total de cadastros possiveis foi atingido.
     * @return true se o cadastro estiver cheio, false caso contrario
     */
    //Retorna se o total de cadastro possíveis foi atingido
    public boolean quantidadeMaxAlunos();

    /**
     * Retorna se ha pelo menos um aluno cadastrado no armazenador.
     * @return true se o cadastro estiver vazio, false caso contrario
     */
    //Retorna se há pelo menos um alunos cadastrado no armazenador
    public boolean quantidadeMinAlunos();

    /**
     * Valida se um Ra inserido ja existe no armazenador.
     * @param ra registro academico a ser validado
     * @return true se o Ra nao existe, false se ja estiver em uso
     */
    //Valida se um Ra inserido existe no armazenador
    public boolean validarRA(String ra);

    /**
     * Busca um aluno no armazenador baseado em seu Ra.
     * @param ra registro academico do aluno a ser buscado
     * @return aluno encontrado ou null se nao existir
     */
    //Busca um aluno no armazenador baseado em seu Ra
    public Aluno buscarAluno(String ra);

    /**
     * Retorna o array completo de alunos.
     * @return array de alunos
     */
    //Retona toda o array dos alunos
    public Aluno[] retornarAlunos();

    /**
     * Define o array de alunos do armazenador.
     * @param alunos array de alunos a ser definido
     */
    public void setAlunos(Aluno[] alunos);
    
}