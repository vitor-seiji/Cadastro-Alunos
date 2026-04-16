package View;

public interface InterfaceCadastro {
    //Insere um aluno no cadastro
    void inserirAluno();

    //Remove um aluno no cadastro
    void removerAluno();

    //Lista todos os alunos cadastrados
    void listarAlunos();

    //Conta quantos alunos tem cadastrados
    void contagem();

    //Menu principal do cadastro
    void executar();

    //Edita um aluno cadastrado
    void editarAluno();
    
    //Lê arquivo de cadastro
    Object lerArquivo();
    
    //Salva um arquivo de cadastro
    void salvarArquivo();
    
    
}