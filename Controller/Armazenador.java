package Controller;
import Model.Aluno;

import javax.swing.*;

/**
 * Implementa o armazenamento de alunos em um array.
 */
public class Armazenador implements ArmazenadorInterface{
    //Array do tipo aluno
    private Aluno arm[];

    //Contador do total de alunos cadastrados
    private int cont = 0;

    /**
     * Construtor do armazenador.
     * @param qtde quantidade maxima de alunos a serem armazenados
     */
    //Construtor do armazenamento
    public Armazenador(int qtde){
        arm = new Aluno[qtde];
    }

    /**
     * Insere um aluno no array.
     * @param a aluno a ser inserido
     * @return true se inserido com sucesso, false se o cadastro estiver cheio
     */
    //Metodo de inserir alunos na array(usuario não vê)
    public boolean inserir(Aluno a){
        if(cont < arm.length){//Verifica se há espaço para inserir um aluno no array
            for(int i = 0; i < arm.length; i++){
                if(arm[i] == null){//Procura a primeira posição null, achou, inseriu
                    this.arm[i] = a;
                    cont++;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove um aluno do array baseado em seu Ra.
     * @param ra registro academico do aluno a ser removido
     * @return true se removido com sucesso, false se nao encontrado
     */
    //Metodo de inserir alunos na array
    public boolean remover(String ra){
        for(int i = 0; i < arm.length; i++){
            if(arm[i] != null && arm[i].getRa().equals(ra)){//Procura o aluno baseado em seu Ra
                arm[i] = null;
                cont--;
                return true;
            }
        }
        return false;//Se não achou retorna false
    }

    /**
     * Busca e retorna o aluno com o Ra passado como parametro.
     * @param ra registro academico do aluno a ser buscado
     * @return aluno encontrado ou null se nao existir
     */
    public Aluno buscarAluno(String ra){//Retorna o aluno com o Ra passado como parâmetro
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                return arm[i];
            }
        }
        return null;//se não achou retorna null
    }

    /**
     * Lista todos os alunos cadastrados.
     * @return string com os dados de todos os alunos cadastrados
     */
    //Método para listar os alunos cadastrados
    public String listar() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Lista de Alunos ===\n\n");

        int exibidos = 0;
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null) {//Se a posição não for null(existe um aluno), retorna seus dados
                exibidos++;
                sb.append("Aluno ").append(exibidos).append(":\n");
                sb.append("RA: ").append(arm[i].getRa()).append("\n");
                sb.append("Nome: ").append(arm[i].getNome().getNome()).append("\n");
                sb.append("Idade: ").append(arm[i].getIdade()).append("\n");
                sb.append("Curso: ").append(arm[i].getCurso()).append("\n");
                sb.append("Semestre: ").append(arm[i].getSemestre()).append("\n\n");
            }
        }

        return sb.toString();
    }

    /**
     * Retorna a quantidade de alunos cadastrados.
     * @return total de alunos cadastrados
     */
    //Método para retornar a quantidade de alunos cadastrados
    public int contagem(){
        return cont;
    }

    /**
     * Verifica se o cadastro esta cheio.
     * @return true se cheio, false caso contrario
     */
    //Método para saber se o cadastro esta cheio
    public boolean quantidadeMaxAlunos(){
        return cont == arm.length;
    }//true = cheio

    /**
     * Verifica se o cadastro esta vazio.
     * @return true se vazio, false caso contrario
     */
    //Método para saber se o cadastro está vazio
    public boolean quantidadeMinAlunos(){
        return cont == 0;
    }//true = vazio

    /**
     * Verifica se um Ra ja pertence ao cadastro.
     * @param ra registro academico a ser verificado
     * @return true se o Ra nao existe, false se ja estiver em uso
     */
    //Método para saber se um Ra pertence ao cadastro
    public boolean validarRA(String ra){
        for(int  i = 0; i < arm.length; i++){//Varre todo o cadastro em busca do Ra
            if(arm[i] != null && arm[i].getRa().equals(ra)){
                return false;//Achou retorna false
            }
        }
        return true;//Não achou retorna true
    }

    /**
     * Retorna o array completo de alunos.
     * @return array de alunos
     */
    public Aluno[] retornarAlunos(){
        return arm;
    }

    /**
     * Define o array de alunos do armazenador e recalcula o contador.
     * @param alunos array de alunos a ser definido
     */
    public void setAlunos(Aluno[] alunos){
        if(alunos == null) return;
        // Se o arquivo tiver mais alunos do que a capacidade atual,
        // expande o array automaticamente para o tamanho do arquivo
        if(alunos.length > arm.length){
            this.arm = new Aluno[alunos.length];
        }
        this.arm = alunos;
        // recalcula o contador
        cont = 0;
        for (Aluno a : arm) {
            if (a != null) {
                cont++;
            }
        }
    }
}