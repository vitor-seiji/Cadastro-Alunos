package View;

/**
 * Interface que define as operacoes da tela de cadastro de alunos.
 */
public interface InterfaceCadastro {

    /**
     * Insere um aluno no cadastro.
     */
    //Insere um aluno no cadastro
    void inserirAluno();

    /**
     * Remove um aluno do cadastro.
     */
    //Remove um aluno no cadastro
    void removerAluno();

    /**
     * Lista todos os alunos cadastrados.
     */
    //Lista todos os alunos cadastrados
    void listarAlunos();

    /**
     * Conta quantos alunos estao cadastrados.
     */
    //Conta quantos alunos tem cadastrados
    void contagem();

    /**
     * Exibe o menu principal do cadastro.
     */
    //Menu principal do cadastro
    void executar();

    /**
     * Edita os dados de um aluno cadastrado.
     */
    //Edita um aluno cadastrado
    void editarAluno();

    /**
     * Le um arquivo de cadastro salvo anteriormente.
     * @return objeto lido do arquivo
     */
    //Lê arquivo de cadastro
    Object lerArquivo();

    /**
     * Salva o cadastro atual em um arquivo.
     */
    //Salva um arquivo de cadastro
    void salvarArquivo();


}