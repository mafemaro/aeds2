public class BubbleSort {

  public static void ordenar(int[] vetor) {  // metodo do bubblesort onde declaramose passamos como parametro o vetor a ser ordenado

    int n = vetor.length; // variavel n que guarda o tamanho do vetor

    for (int i = 0; i < n; i++) { // esse laço tem uma funcao simples, que eh de controle. ele garante que o vetor vai ser percorrido o numero de vezes necessario para que ele fique ordenado. o numero de vezes necessario eh igual ao numero de elementos do vetor, ou seja, n. entao, esse laço vai garantir que o vetor seja percorrido n vezes, o que eh suficiente para garantir que ele fique ordenado.
    
      for (int j = 0; j < n - 1; j++) { // j que vai de 0 a n-1, ou seja, ele percorre o vetor do inicio ao penultimo elemento. isso acontece porque na comparacao entre o elemento atual e o proximo, o proximo elemento nao pode ser o ultimo elemento do vetor, pois nao existe um elemento apos ele para comparar. entao, esse laço garante que a comparacao entre o elemento atual e o proximo seja feita para todos os elementos do vetor, exceto para o ultimo elemento.

        if (vetor[j] > vetor[j + 1]) { // aqui a comparacao entre o elemento atual e o proximo
          int aux = vetor[j]; // aux eh igual ao vetor do indice j
          vetor[j] = vetor[j + 1]; // aqui o elemento atual recebe o valor do proximo, ou seja, eles sao trocados de posicao
          vetor[j + 1] = aux; // aqui o proximo elemento recebe o valor do aux, que eh o valor do elemento atual antes da troca. entao, com isso, os dois elementos sao trocados de posicao.
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90}; // um vetor qualquer
    ordenar(dados); // chama o metodo ordenar passando o vetor como parametro

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // para imprimir todos os indices do vetor
      System.out.print(dados[i] + " ");
    }
  }
}