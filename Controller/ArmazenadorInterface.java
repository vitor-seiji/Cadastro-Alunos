package Controller;
import Model.Aluno;

public interface ArmazenadorInterface {

    //Insere um aluno no armazenador
    public boolean inserir(Aluno a);

    //Remove um aluno do armazenador beseado em seu Ra
    public boolean remover(String ra);

    //Lista todos os alunos cadastrados no armazenador
    public String listar();

    //Retorna o total de alunos cadastrados no armazenador
    public int contagem();

    //Retorna se o total de cadastro possíveis foi atingido
    public boolean quantidadeMaxAlunos();

    //Retorna se há pelo menos um alunos cadastrado no armazenador
    public boolean quantidadeMinAlunos();

    //Valida se um Ra inserido existe no armazenador
    public boolean validarRA(String ra);

    //Busca um aluno no armazenador baseado em seu Ra
    public Aluno buscarAluno(String ra);
    
    //Retona toda o array dos alunos
    public Aluno[] retornarAlunos();
    
    public void setAlunos(Aluno[] alunos);
    
}