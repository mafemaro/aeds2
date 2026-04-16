public class HeapSort {

  public static void ordenar(int[] vetor) {
    int n = vetor.length;

    // monta o heap
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(vetor, n, i);
    }

    // vai tirando o maior e colocando no final
    for (int i = n - 1; i > 0; i--) {
      int aux = vetor[0];
      vetor[0] = vetor[i];
      vetor[i] = aux;

      heapify(vetor, i, 0); // arruma o heap
    }
  }

  public static void heapify(int[] vetor, int n, int i) {
    int maior = i; // começa com o pai
    int esq = 2 * i + 1; // esquerda
    int dir = 2 * i + 2; // direita

    if (esq < n && vetor[esq] > vetor[maior]) {
      maior = esq;
    }

    if (dir < n && vetor[dir] > vetor[maior]) {
      maior = dir;
    }

    if (maior != i) {
      int aux = vetor[i];
      vetor[i] = vetor[maior];
      vetor[maior] = aux;

      heapify(vetor, n, maior); // continua
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90};
    ordenar(dados);

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) {
      System.out.print(dados[i] + " ");
    }

    System.out.println();
  }
}