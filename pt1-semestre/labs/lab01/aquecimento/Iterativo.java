import java.util.Scanner;

public class Iterativo {
    public static int contarMaiusculas(String texto) {
        int maiusculas = 0;
        for (int i = 0; i < texto.length(); i++) {
            char atual = texto.charAt(i);
            if (atual >= 'A' && atual <= 'Z') maiusculas++;
        }
        return maiusculas;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linha;
        while (true) {
            linha = sc.nextLine();
            if (linha.equals("FIM")) break;

            System.out.println(contarMaiusculas(linha));
        }
        sc.close();
    }
}