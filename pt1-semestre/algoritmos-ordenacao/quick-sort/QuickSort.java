public class QuickSort {

  // metodo principal que chama o quick sort
  public static void ordenar(int[] vetor) {
    quickSort(vetor, 0, vetor.length - 1);
  }

  // metodo recursivo do quick sort
  private static void quickSort(int[] vetor, int inicio, int fim) {
    if (inicio < fim) {
      int p = particionar(vetor, inicio, fim); // particiona o vetor
      quickSort(vetor, inicio, p - 1); // ordena lado esquerdo
      quickSort(vetor, p + 1, fim); // ordena lado direito
    }
  }

  // metodo que faz o particionamento
  private static int particionar(int[] vetor, int inicio, int fim) {

    int pivo = vetor[fim]; // escolhe o ultimo elemento como pivô
    int i = inicio - 1; // indice dos menores

    for (int j = inicio; j < fim; j++) { // percorre o vetor
      if (vetor[j] <= pivo) { // se for menor ou igual ao pivô
        i++; // aumenta a fronteira dos menores

        // troca
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
      }
    }

    // coloca o pivô na posição correta
    int aux = vetor[i + 1];
    vetor[i + 1] = vetor[fim];
    vetor[fim] = aux;

    return i + 1; // retorna posição do pivô
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90};
    ordenar(dados); // chama o quick sort

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) {
      System.out.print(dados[i] + " ");
    }

    System.out.println();
  }
}