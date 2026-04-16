public class InsertionSort {

  public static void ordenar(int[] vetor) { // metodo do insertion sort onde passamos como parametro o vetor a ser ordenado

    int n = vetor.length; // variavel n que guarda o tamanho do vetor

    for (int i = 1; i < n; i++) { // começa do segundo elemento pois o primeiro ja é considerado ordenado
      int chave = vetor[i]; // guarda o elemento atual que vamos inserir na parte ordenada
      int j = i - 1; // indice para percorrer a parte ordenada da direita para a esquerda
      while (j >= 0 && vetor[j] > chave) { // enquanto existir elementos maiores que a chave
        vetor[j + 1] = vetor[j]; // desloca o elemento uma posição pra direita
        j--; // vai voltando na parte ordenada
      }
      vetor[j + 1] = chave; // insere a chave na posição correta
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 25, 12, 22, 11}; // vetor qualquer
    ordenar(dados); // chama o metodo ordenar passando o vetor

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // percorre o vetor para imprimir
      System.out.print(dados[i] + " ");
    }
    System.out.println(); // quebra de linha no final
  }
}