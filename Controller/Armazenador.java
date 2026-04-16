package Controller;
import Model.Aluno;

import javax.swing.*;

public class Armazenador implements ArmazenadorInterface{
    //Array do tipo aluno
    private Aluno arm[];

    //Contador do total de alunos cadastrados
    private int cont = 0;

    //Construtor do armazenamento
    public Armazenador(int qtde){
        arm = new Aluno[qtde];
    }

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

    public Aluno buscarAluno(String ra){//Retorna o aluno com o Ra passado como parâmetro
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                return arm[i];
            }
        }
        return null;//se não achou retorna null
    }

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

    //Método para retornar a quantidade de alunos cadastrados
    public int contagem(){
        return cont;
    }

    //Método para saber se o cadastro esta cheio
    public boolean quantidadeMaxAlunos(){
        return cont == arm.length;
    }//true = cheio

    //Método para saber se o cadastro está vazio
    public boolean quantidadeMinAlunos(){
        return cont == 0;
    }//true = vazio

    //Método para saber se um Ra pertence ao cadastro
    public boolean validarRA(String ra){
        for(int  i = 0; i < arm.length; i++){//Varre todo o cadastro em busca do Ra
            if(arm[i] != null && arm[i].getRa().equals(ra)){
                return false;//Achou retorna false
            }
        }
        return true;//Não achou retorna true
    }
    
    public Aluno[] retornarAlunos(){
        return arm;
    }
    
    public void setAlunos(Aluno[] alunos){
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
