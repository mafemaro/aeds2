#include <stdio.h>

// função que ordena um vetor usando o algoritmo insertion sort
void ordenar(int vetor[], int n) {
  for (int i = 1; i < n; i++) { // começa do segundo elemento pois o primeiro ja é considerado ordenado
    int chave = vetor[i]; // guarda o elemento atual que sera inserido na parte ordenada
    int j = i - 1; // indice para percorrer a parte ordenada da direita para a esquerda

    while (j >= 0 && vetor[j] > chave) { // enquanto existir elementos maiores que a chave
      vetor[j + 1] = vetor[j]; // desloca os elementos uma posição pra direita
      j--; // volta na parte ordenada
    }

    vetor[j + 1] = chave; // insere a chave na posição correta
  }
}

int main() {
  int dados[] = {64, 25, 12, 22, 11}; // vetor qualquer
  int n = sizeof(dados) / sizeof(dados[0]); // calcula tamanho do vetor
  ordenar(dados, n); // chama funcao de ordenacao

  printf("Vetor ordenado: ");
  for (int i = 0; i < n; i++) { // percorre e imprime o vetor
    printf("%d ", dados[i]);
  }

  printf("\n"); // quebra de linha no final
  return 0;
}