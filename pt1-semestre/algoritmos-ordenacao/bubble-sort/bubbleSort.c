// O CODIGO EM JAVA TÁ TUDO MAIS EXPLICADINHO

#include <stdio.h>

// função que ordena um vetor usando o algoritmo bubble sort
void ordenar(int vetor[], int n) {
  // o laço de fora garante que vamos passar pelo vetor várias vezes
  for (int i = 0; i < n; i++) {
    // o laço de dentro faz as comparações lado a lado
    for (int j = 0; j < n - 1; j++) {
      // se o atual for maior que o próximo, troca de lugar
      if (vetor[j] > vetor[j + 1]) {
        int aux = vetor[j];
        vetor[j] = vetor[j + 1];
        vetor[j + 1] = aux;
      }
    }
  }
}

int main() {
  int dados[] = {64, 34, 25, 12, 22, 11, 90};
    // em c, como eh uma linguagem de baixo nivel, calculamos o tamanho do vetor dividindo o tamanho total dele em bytes pelo tamanho em bytes de um único elemento.
    int n = sizeof(dados) / sizeof(dados[0]); 
    ordenar(dados, n); // chama o metodo ordenar passando o vetor como parametro

    printf("Vetor ordenado: ");

    for (int i = 0; i < n; i++) {
      printf("%d ", dados[i]);
    }
    printf("\n");

    return 0;
}