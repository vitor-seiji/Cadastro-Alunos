import View.InterfaceGrafica;

/**
 * Classe de entrada (Main) do sistema CadastroAluno.
 * Inicia diretamente a interface gráfica, sem perguntar
 * estilo de interface nem quantidade de alunos.
 *
 * @author Ana
 * @version 2.0
 */
public class App {
    public static void main(String[] args) {
        new InterfaceGrafica().executar();
    }
}