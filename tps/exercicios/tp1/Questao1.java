import java.util.Scanner;

class Questao1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String linha = sc.nextLine();

        String resp = "";

        for(int i = 0; i < linha.length(); i++){
            resp += (char)(linha.charAt(i) + 3);
        }

      System.out.println("Ciframento: " + resp);
    }
}