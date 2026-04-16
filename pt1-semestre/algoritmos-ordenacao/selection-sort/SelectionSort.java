public class SelectionSort {

  public static void ordenar(int[] vetor) { // metodo do selection sort onde passamos como parametro o vetor a ser ordenado

    int n = vetor.length; // variavel n que guarda o tamanho do vetor

    for (int i = 0; i < n - 1; i++) { // esse laço controla a posição atual onde o menor elemento vai ser colocado
      int indiceMenor = i; // assume que o menor elemento da parte nao ordenada é o atual
      for (int j = i + 1; j < n; j++) { // percorre o resto do vetor procurando um menor elemento
        if (vetor[j] < vetor[indiceMenor]) { // se encontrar um elemento menor, atualiza o indice do menor
          indiceMenor = j;
        }
      }
      if (indiceMenor != i) { // se o menor nao estiver na posição atual, faz a troca
        int aux = vetor[i]; // guarda o valor atual
        vetor[i] = vetor[indiceMenor]; // coloca o menor na posição atual
        vetor[indiceMenor] = aux; // completa a troca
      }
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 25, 12, 22, 11}; // vetor qualquer
    ordenar(dados); // chama o metodo ordenar passando o vetor

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // percorre o vetor para imprimir
      System.out.print(dados[i] + " ");
    }
  }
}