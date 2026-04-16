
import Model.Pessoa;

import javax.swing.JOptionPane;

public class TestePessoa {
    public static void main(String[] args) {

        // Criando pessoas para teste
        Pessoa p1 = new Pessoa("João da Silva", 30);
        Pessoa p2 = new Pessoa("Maria de Souza", 25);
        Pessoa p3 = new Pessoa("Ana Carolina Lima", 22);

        StringBuilder sb = new StringBuilder();
        sb.append("===== TESTE DE UNIDADE — Pessoa =====\n\n");

        // Teste 1: getNome()
        sb.append("[ Teste 1 ] getNome()\n");
        sb.append("  p1 esperado: João da Silva -> " + p1.getNome().getNome() + "\n");
        sb.append("  p2 esperado: Maria de Souza -> " + p2.getNome().getNome() + "\n");
        sb.append("  p3 esperado: Ana Carolina Lima -> " + p3.getNome().getNome() + "\n\n");

        // Teste 2: getIdade()
        sb.append("[ Teste 2 ] getIdade()\n");
        sb.append("  p1 esperado: 30 -> " + p1.getIdade() + "\n");
        sb.append("  p2 esperado: 25 -> " + p2.getIdade() + "\n");
        sb.append("  p3 esperado: 22 -> " + p3.getIdade() + "\n\n");

        // Teste 3: getNomeInvertido()
        sb.append("[ Teste 3 ] getNomeInvertido()\n");
        sb.append("  p1 esperado: avliS ad oãoJ -> " + p1.getNome().getNomeInvertido() + "\n");
        sb.append("  p2 esperado: azuoS ed airaM -> " + p2.getNome().getNomeInvertido() + "\n");
        sb.append("  p3 esperado: amiL aniloraC anA -> " + p3.getNome().getNomeInvertido() + "\n\n");

        // Teste 4: getNomeBiblio()
        sb.append("[ Teste 4 ] getNomeBiblio()\n");
        sb.append("  p1 esperado: Silva, J. -> " + p1.getNome().getNomeBiblio() + "\n");
        sb.append("  p2 esperado: Souza, M. -> " + p2.getNome().getNomeBiblio() + "\n");
        sb.append("  p3 esperado: Lima, A. C. -> " + p3.getNome().getNomeBiblio() + "\n\n");

        // Teste 5: getQtdePalavras()
        sb.append("[ Teste 5 ] getQtdePalavras()\n");
        sb.append("  p1 esperado: 3 -> " + p1.getNome().getQtdePalavras() + "\n");
        sb.append("  p2 esperado: 3 -> " + p2.getNome().getQtdePalavras() + "\n");
        sb.append("  p3 esperado: 3 -> " + p3.getNome().getQtdePalavras() + "\n\n");

        // Teste 6: setNome() e setIdade()
        sb.append("[ Teste 6 ] setNome() e setIdade()\n");
        p1.setNome("Carlos Ferreira");
        p1.setIdade(40);
        sb.append("  p1 nome esperado: Carlos Ferreira ->" + p1.getNome().getNome() + "\n");
        sb.append("  p1 idade esperada: 40 -> " + p1.getIdade() + "\n\n");

        // Teste 7: limpeza de espaços (via Texto internamente)
        sb.append("[ Teste 7 ] Limpeza de espaços extras no nome\n");
        Pessoa p4 = new Pessoa("  Lucas   Oliveira  ", 18);
        sb.append("  entrada:  '  Lucas   Oliveira  '\n");
        sb.append("  esperado: 'Lucas Oliveira' ->' " + p4.getNome().getNome() + "'\n");

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
