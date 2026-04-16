

import View.InterfaceCadastro;
import View.InterfaceConsole;
import View.InterfaceGrafica;

import java.util.Scanner;
import javax.swing.JOptionPane;
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        do{
            try {
                    System.out.print("Escolha a interface:\n1- Gráfica\n2- Console\n");
                    escolha = sc.nextInt();
                    
                    if (escolha != 1 && escolha != 2) {
                        System.out.println("\nInsira uma opção válida");
                    }
                } catch (Exception e) {
                    System.out.println("\nInsira apenas números inteiros.");
                    sc.next(); // IMPORTANTE: Limpa o buffer do scanner
                }
        }while(escolha != 2 && escolha != 1);
        
        int qntd = 0;

        if (escolha == 2) {
            do {
                try {
                    System.out.print("Número de alunos: ");
                    qntd = sc.nextInt();
                    
                    if (qntd <= 0) {
                        System.out.println("Insira uma quantidade válida (maior que 0)\n");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: Insira apenas números inteiros.\n");
                    sc.next(); // IMPORTANTE: Limpa o buffer do scanner
                }
            } while (qntd <= 0);
        }
        else {
            do {
                try {
                    String input = JOptionPane.showInputDialog("Número de alunos:");
                    
                    // Se o usuário clicar em "Cancelar", o input vem null
                    if (input == null) break; 
                    
                    qntd = Integer.parseInt(input);
                    
                    if (qntd <= 0) {
                        JOptionPane.showMessageDialog(null, "Insira uma quantidade válida\n");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Erro: Digite um número inteiro válido.\n");
                }
            } while (qntd <= 0);
        }
        
        //Define a interface
        InterfaceCadastro cadastro = (escolha == 1) ? new InterfaceGrafica(qntd) : new InterfaceConsole(qntd);

        //Executa o menu do cadastro
        cadastro.executar();//cada um sabe como se comportar
    }
}
