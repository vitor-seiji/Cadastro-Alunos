package Model;

public class Aluno extends Pessoa implements java.io.Serializable{
    //Atributos do tipo aluno
    private String ra; //Ra do aluno
    private String curso; //Curso do aluno
    private int semestre; //Semestre atual do aluno

    //Construtor do tipo aluno
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
