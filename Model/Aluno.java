package Model;
/**
 * Representa a entidade Aluno dentro do sistema CadastroAluno.
 * Armazena as informações básicas de identificação e as notas para análise.
 * * @author Ana
 * @version 1.0
 */
public class Aluno extends Pessoa implements java.io.Serializable{
    //Atributos do tipo aluno
    private String ra; //Ra do aluno
    private String curso; //Curso do aluno
    private int semestre; //Semestre atual do aluno

    /**
     * Construtor da classe Aluno para inicialização de dados.
     * * @param nome O nome completo do estudante.
     * @param nota A nota final a ser registrada no CadastroAluno.
     */
    public Aluno(String nome, int idade, String ra, String curso, int semestre) {
        super(nome, idade);
        this.ra = ra;
        this.curso = curso;
        this.semestre = semestre;
    }

    //Getters
    public String getRa() { return ra; }
    public String getCurso() { return curso; }
    public int getSemestre() { return semestre; }

    //Setters
    public void setRa(String ra) { this.ra = ra; }
    public void setCurso(String curso) { this.curso = curso; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
}
