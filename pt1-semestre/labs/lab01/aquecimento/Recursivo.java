import java.util.Scanner;

public class Recursivo {
    public static int contarMaiusculas(String texto, int i) {
        if (i == texto.length()) return 0;

        char atual = texto.charAt(i);

        if (atual >= 'A' && atual <= 'Z') return 1 + contarMaiusculas(texto, i + 1); 
        else return contarMaiusculas(texto, i + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linha;

        while (true) {
            linha = sc.nextLine();
            if (linha.equals("FIM")) break;

            System.out.println(contarMaiusculas(linha, 0));
        }
        sc.close();
    }
}