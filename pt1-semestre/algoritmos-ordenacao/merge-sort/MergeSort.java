public class MergeSort {

  public static void ordenar(int[] vetor) { // metodo principal do merge sort
    int[] auxiliar = new int[vetor.length]; // vetor auxiliar

    mergeSort(vetor, auxiliar, 0, vetor.length - 1); // chama o metodo recursivo
  }

  private static void mergeSort(int[] vetor, int[] auxiliar, int inicio, int fim) { // metodo recursivo

    if (inicio < fim) {
      int meio = (inicio + fim) / 2; // encontra o meio
      mergeSort(vetor, auxiliar, inicio, meio); // esquerda
      mergeSort(vetor, auxiliar, meio + 1, fim); // direita

      intercalar(vetor, auxiliar, inicio, meio, fim); // merge
    }
  }

  private static void intercalar(int[] vetor, int[] auxiliar, int inicio, int meio, int fim) { // metodo de intercalação

    for (int i = inicio; i <= fim; i++) { // copia para auxiliar
      auxiliar[i] = vetor[i];
    }

    int i = inicio; // inicio da esquerda
    int j = meio + 1; // inicio da direita
    int k = inicio; // posição atual

    while (i <= meio && j <= fim) { // compara os elementos
      if (auxiliar[i] <= auxiliar[j]) {
        vetor[k] = auxiliar[i];
        i++;
      } else {
        vetor[k] = auxiliar[j];
        j++;
      }

      k++;
    }

    while (i <= meio) { // copia o restante da esquerda
      vetor[k] = auxiliar[i];
      i++;
      k++;
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90};
    ordenar(dados); // chama o merge sort

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // imprime o vetor
      System.out.print(dados[i] + " ");
    }

    System.out.println();
  }
}