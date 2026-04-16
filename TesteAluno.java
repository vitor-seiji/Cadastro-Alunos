
import Model.Aluno;

import javax.swing.JOptionPane;

public class TesteAluno {
    public static void main(String[] args) {

        // Criando alunos para teste
        Aluno a1 = new Aluno("João da Silva", 20, "RA001", "Medicina", 1);
        Aluno a2 = new Aluno("Maria de Souza", 22, "RA002", "Engenharia", 3);
        Aluno a3 = new Aluno("Ana Carolina Lima", 19, "RA003", "Direito", 2);

        StringBuilder sb = new StringBuilder();
        sb.append("===== TESTE DE UNIDADE — Aluno =====\n\n");

        // Teste 1: herança — atributos de Pessoa
        sb.append("[ Teste 1 ] Herança de Pessoa — getNome() e getIdade()\n");
        sb.append("  a1 nome esperado: João da Silva -> " + a1.getNome().getNome() + "\n");
        sb.append("  a1 idade esperada: 20  -> " + a1.getIdade() + "\n");
        sb.append("  a2 nome esperado: Maria de Souza -> " + a2.getNome().getNome() + "\n");
        sb.append("  a2 idade esperada: 22 -> " + a2.getIdade() + "\n\n");

        // Teste 2: atributos próprios de Aluno
        sb.append("[ Teste 2 ] Atributos do Aluno — getRa(), getCurso(), getSemestre()\n");
        sb.append("  a1 RA esperado: RA001 -> " + a1.getRa() + "\n");
        sb.append("  a1 curso esperado: Medicina -> " + a1.getCurso() + "\n");
        sb.append("  a1 semestre esperado: 1 -> " + a1.getSemestre() + "\n\n");

        // Teste 3: getNomeInvertido()
        sb.append("[ Teste 3 ] getNomeInvertido()\n");
        sb.append("  a1 esperado: avliS ad oãoJ  -> " + a1.getNome().getNomeInvertido() + "\n");
        sb.append("  a2 esperado: azuoS ed airaM  -> " + a2.getNome().getNomeInvertido() + "\n");
        sb.append("  a3 esperado: amiL aniloraC anA -> " + a3.getNome().getNomeInvertido() + "\n\n");

        // Teste 4: getNomeBiblio() — herdado via Pessoa → NomePessoa
        sb.append("[ Teste 4 ] getNomeBiblio()\n");
        sb.append("  a1 esperado: Silva, J. -> " + a1.getNome().getNomeBiblio() + "\n");
        sb.append("  a2 esperado: Souza, M. -> " + a2.getNome().getNomeBiblio() + "\n");
        sb.append("  a3 esperado: Lima, A. C. -> " + a3.getNome().getNomeBiblio() + "\n\n");

        // Teste 5: setters de Aluno
        sb.append("[ Teste 5 ] setRa(), setCurso(), setSemestre()\n");
        a1.setRa("RA999");
        a1.setCurso("Sistemas");
        a1.setSemestre(4);
        sb.append("  a1 RA esperado: RA999 -> " + a1.getRa() + "\n");
        sb.append("  a1 curso esperado: Sistemas -> " + a1.getCurso() + "\n");
        sb.append("  a1 semestre esperado: 4 -> " + a1.getSemestre() + "\n\n");

        // Teste 6: setters herdados de Pessoa
        sb.append("[ Teste 6 ] setNome() e setIdade() herdados de Pessoa\n");
        a2.setNome("Pedro Alves");
        a2.setIdade(30);
        sb.append("  a2 nome esperado: Pedro Alves -> " + a2.getNome().getNome() + "\n");
        sb.append("  a2 idade esperada: 30 -> " + a2.getIdade() + "\n\n");

        // Teste 7: limpeza de espaços no nome do aluno
        sb.append("[ Teste 7 ] Limpeza de espaços extras no nome\n");
        Aluno a4 = new Aluno("  Lucas   Oliveira  ", 18, "RA004", "TI", 1);
        sb.append("  entrada:  '  Lucas   Oliveira  '\n");
        sb.append("  esperado: 'Lucas Oliveira' ->' " + a4.getNome().getNome() + "'\n");

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
