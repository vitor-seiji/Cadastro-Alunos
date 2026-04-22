package View;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class ArquivoBinario
{
    String nomeArq;

    ArquivoBinario(String nomeArq){
        this.nomeArq = nomeArq;
    }

    /**
     * Grava um objeto em arquivo. Lança IOException em caso de falha.
     */
    public void gravarObj(Object objeto) throws Exception {
        ObjectOutputStream output = null;
        try {
            File file = new File(this.nomeArq);
            output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(objeto);
        } finally {
            if (output != null) {
                try { output.close(); } catch(Exception ex) { }
            }
        }
    }

    /**
     * Lê um objeto do arquivo. Lança IOException se o arquivo não existir ou for inválido.
     */
    public Object lerObj() throws Exception {
        Object objeto = null;
        ObjectInputStream input = null;
        try {
            File file = new File(this.nomeArq);
            input = new ObjectInputStream(new FileInputStream(file));
            objeto = input.readObject();
        } finally {
            if (input != null) {
                try { input.close(); } catch(Exception ex) { }
            }
        }
        return objeto;
    }
}