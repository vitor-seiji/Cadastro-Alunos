package Model;


/**
 * Armazena um texto. Inverte texto e limpa espaços extras
 *
 * @author Julio Arakaki
 * @version 1.0 2019/02/29
 */
public class Texto implements java.io.Serializable{
    // Atributo da classe ou varáveis de instancia
    private String txt;

    Texto(String txt){
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    // setters e getters
    private void setTxt(String t){
        this.txt = t;
    }

    public String getTxt(){
        return this.txt;
    }

    public String inverterTexto(){
        String txtInvertido = "";
        if(!(this.txt == null || this.txt.equals("") )){
            for (int i=this.txt.length()-1; i >= 0; i--){
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            return null;
        }
        return txtInvertido;
    }

    public int getQtdePalavras(){
        return (getTxt().split(" ").length);
    }

    private void limpaEspacosExcedentes(){
        // Elimina espacos do inicio e fim da string
        setTxt(this.txt.trim());

        // Elimina espacos excedentes do meio da string
        String s = "";
        for (int i=0; i < this.txt.length(); i++){
            s = s + this.txt.charAt(i); // pega o caractere da posicao
            if(this.txt.charAt(i) == ' '){
                while(this.txt.charAt(i+1) == ' '){ // avanca se tiver mais espacos
                    i++;
                }
            }
        }
        setTxt(s);
    }

    public String toString(){
        return(getTxt());
    }
}
