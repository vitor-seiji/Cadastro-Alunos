package Model;

public class Pessoa implements java.io.Serializable{
    //Atributos do tipo Pessoa
    private NomePessoa nome;//Nome da pessoa
    private int idade;//Idade atual da pessoa

    //Construtor do tipo pessoa
    public Pessoa(String nome, int idade) {
        this.nome = new NomePessoa(nome);
        this.idade = idade;
    }
    
    public Pessoa() {
    }

    //Getters
    public NomePessoa getNome() { return nome; }
    public int getIdade() { return idade; }

    //Setters
    public void setNome(String nome) { this.nome = new NomePessoa(nome); }
    public void setIdade(int idade) { this.idade = idade; }
}
