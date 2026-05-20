package Controller;

import Model.Aluno;

/**
 * Lista encadeada simples de alunos.
 */
public class ListaEncadeada implements java.io.Serializable {
    private No cabeca;
    private int tamanho;

    public ListaEncadeada() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    /**
     * Insere um aluno no final da lista.
     */
    public void inserir(Aluno aluno) {
        No novo = new No(aluno);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
        tamanho++;
    }

    /**
     * Remove o aluno com o RA informado.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String ra) {
        if (cabeca == null) return false;

        // Caso especial: cabeça da lista
        if (cabeca.getAluno().getRa().equals(ra)) {
            cabeca = cabeca.getProximo();
            tamanho--;
            return true;
        }

        No anterior = cabeca;
        No atual = cabeca.getProximo();
        while (atual != null) {
            if (atual.getAluno().getRa().equals(ra)) {
                anterior.setProximo(atual.getProximo());
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return false;
    }

    /**
     * Busca um aluno pelo RA.
     * @return aluno encontrado ou null
     */
    public Aluno buscar(String ra) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.getAluno().getRa().equals(ra)) {
                return atual.getAluno();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    /**
     * Verifica se um RA já está cadastrado.
     * @return true se o RA NÃO existe (disponível), false se já está em uso
     */
    public boolean validarRA(String ra) {
        return buscar(ra) == null;
    }

    /**
     * Retorna a quantidade de alunos na lista.
     */
    public int getTamanho() { return tamanho; }

    /**
     * Retorna true se a lista estiver vazia.
     */
    public boolean isVazia() { return tamanho == 0; }

    /**
     * Retorna o nó cabeça (para iteração externa, ex.: serialização).
     */
    public No getCabeca() { return cabeca; }

    /**
     * Reconstrói a lista a partir de um array de alunos (usado ao carregar arquivo).
     */
    public void carregarDeArray(Aluno[] alunos) {
        cabeca = null;
        tamanho = 0;
        if (alunos == null) return;
        for (Aluno a : alunos) {
            if (a != null) inserir(a);
        }
    }

    /**
     * Converte a lista em um array de alunos (usado ao salvar arquivo).
     */
    public Aluno[] toArray() {
        Aluno[] arr = new Aluno[tamanho];
        No atual = cabeca;
        int i = 0;
        while (atual != null) {
            arr[i++] = atual.getAluno();
            atual = atual.getProximo();
        }
        return arr;
    }
}