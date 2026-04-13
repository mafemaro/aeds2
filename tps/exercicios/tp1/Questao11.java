import java.util.Scanner;

public class Questao11 {
    // inverte a string recursivamente
    public static String inverterString(String texto, int posicao){
        String resultado = "";
        // se a posicao for igual ao tamanho da string, retorna resultado
        if(posicao == texto.length())
            return resultado;
        
        return inverterString(texto, posicao + 1) + texto.charAt(posicao) + resultado; // chama a funcao recursiva com a posicao incrementada e concatena o caractere atual no resultado
    }

    // compara duas strings manualmente
    public static boolean saoIguais(String texto1, String texto2){ // compara tamanho das strings
        if(texto1.length() != texto2.length()) // se forem diferentes, retorna false
            return false;
        // compara caractere por caractere
        for(int i = 0; i < texto1.length(); i++){
            if(texto1.charAt(i) != texto2.charAt(i)) // se algum caractere for diferente, retorna false
                return false;
        }
        return true; // se todas as comparacoes forem iguais, retorna true
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String entrada = sc.nextLine();
        // enquanto nao for FIM
        while(!saoIguais(entrada, "FIM")){
            String invertida = inverterString(entrada, 0); // inverte a string
            MyIO.println(invertida); // imprime resultado
            entrada = sc.nextLine(); // le proxima linha
        }
        sc.close();
    }
}