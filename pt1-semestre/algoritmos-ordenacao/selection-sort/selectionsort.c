#include <stdio.h>

// função que ordena um vetor usando o algoritmo selection sort
void ordenar(int vetor[], int n) {
  for (int i = 0; i < n - 1; i++) { // controla a posição onde o menor sera colocado
    int indiceMenor = i; // assume que o menor elemento esta na posicao atual

    for (int j = i + 1; j < n; j++) { // percorre o resto do vetor procurando o menor
      if (vetor[j] < vetor[indiceMenor]) { // se encontrar menor, atualiza o indice
        indiceMenor = j;
      }
    }
    if (indiceMenor != i) { // se nao estiver na posicao atual, troca
      int aux = vetor[i];
      vetor[i] = vetor[indiceMenor];
      vetor[indiceMenor] = aux;
    }
  }
}

int main() {
  int dados[] = {64, 34, 25, 12, 22, 11, 90}; // vetor qualquer
  int n = sizeof(dados) / sizeof(dados[0]); // calcula tamanho do vetor
  ordenar(dados, n); // chama funcao de ordenacao

  printf("Vetor ordenado: ");

  for (int i = 0; i < n; i++) { // imprime o vetor
    printf("%d ", dados[i]);
  }

  printf("\n");
  return 0;
}