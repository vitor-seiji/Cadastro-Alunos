package Controller;

import Model.Aluno;

/**
 * Nó da lista encadeada de alunos.
 */
public class No implements java.io.Serializable {
    private Aluno aluno;
    private No proximo;

    public No(Aluno aluno) {
        this.aluno = aluno;
        this.proximo = null;
    }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public No getProximo() { return proximo; }
    public void setProximo(No proximo) { this.proximo = proximo; }
}