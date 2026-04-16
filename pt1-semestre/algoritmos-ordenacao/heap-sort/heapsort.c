#include <stdio.h>

// função que organiza o heap
void heapify(int vetor[], int n, int i) {
  int maior = i; // começa assumindo que o maior é o pai
  int esq = 2 * i + 1; // filho da esquerda
  int dir = 2 * i + 2; // filho da direita

  // vê se o filho da esquerda é maior
  if (esq < n && vetor[esq] > vetor[maior]) {
    maior = esq;
  }

  // vê se o filho da direita é maior
  if (dir < n && vetor[dir] > vetor[maior]) {
    maior = dir;
  }

  // se o maior não for o pai, troca
  if (maior != i) {
    int aux = vetor[i];
    vetor[i] = vetor[maior];
    vetor[maior] = aux;

    heapify(vetor, n, maior); // continua ajustando
  }
}

// função principal do heap sort
void heapSort(int vetor[], int n) {

  // monta o heap
  for (int i = n / 2 - 1; i >= 0; i--) {
    heapify(vetor, n, i);
  }

  // vai tirando o maior e colocando no final
  for (int i = n - 1; i > 0; i--) {
    int aux = vetor[0];
    vetor[0] = vetor[i];
    vetor[i] = aux;

    heapify(vetor, i, 0); // arruma o heap de novo
  }
}

int main() {
  int dados[] = {64, 34, 25, 12, 22, 11, 90};
  int n = sizeof(dados) / sizeof(dados[0]);
  heapSort(dados, n);

  printf("Vetor ordenado: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", dados[i]);
  }

  printf("\n");
  return 0;
}