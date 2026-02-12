// Faça um método que receba um array de inteiros e um
// número inteiro x e retorne um valor booleano informando se o
// elemento x está contido no array
import java.util.Scanner; // ler dados do teclado

public class questao1 {
    public static boolean numero(int[] vet, int x) { // metodo
        for (int i = 0; i < vet.length; i++) {
            if(vet[i] == x) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // cria objeto para leitura
        int[] vet = new int[10];

        for (int i = 0; i < vet.length; i++) {
            vet[i] = sc.nextInt();
        }

        int x = sc.nextInt();
        if (numero(vet, x)) System.out.println("Encontrado");
        else System.out.println("Nao encontrado");

        sc.close(); // fecha scanner
    }
}