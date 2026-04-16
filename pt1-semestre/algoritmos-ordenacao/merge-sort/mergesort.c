#include <stdio.h>

// função que faz a intercalação dos vetores
void intercalar(int vetor[], int auxiliar[], int inicio, int meio, int fim) {
  for (int i = inicio; i <= fim; i++) { // copia para o vetor auxiliar
    auxiliar[i] = vetor[i];
  }

  int i = inicio; // inicio da parte esquerda
  int j = meio + 1; // inicio da parte direita
  int k = inicio; // posição atual no vetor original

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

// função recursiva do merge sort
void mergeSort(int vetor[], int auxiliar[], int inicio, int fim) {
  if (inicio < fim) {
    int meio = (inicio + fim) / 2; // encontra o meio
    mergeSort(vetor, auxiliar, inicio, meio); // esquerda
    mergeSort(vetor, auxiliar, meio + 1, fim); // direita

    intercalar(vetor, auxiliar, inicio, meio, fim); // merge
  }
}

int main() {
  int dados[] = {64, 34, 25, 12, 22, 11, 90};
  int n = sizeof(dados) / sizeof(dados[0]); // tamanho do vetor

  int auxiliar[n]; // vetor auxiliar

  mergeSort(dados, auxiliar, 0, n - 1); // chama o merge sort

  printf("Vetor ordenado: ");
  for (int i = 0; i < n; i++) { // imprime o vetor
    printf("%d ", dados[i]);
  }

  printf("\n");
  return 0;
}