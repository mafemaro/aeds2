#include <stdio.h>

// função que faz o particionamento do quick sort
int particionar(int vetor[], int inicio, int fim) {
  int pivo = vetor[fim]; // escolhe o ultimo elemento como pivô
  int i = inicio - 1; // indice do menor elemento

  for (int j = inicio; j < fim; j++) { // percorre o vetor
    if (vetor[j] <= pivo) { // se o elemento for menor ou igual ao pivô
      i++; // aumenta a fronteira dos menores
      // faz a troca
      int aux = vetor[i];
      vetor[i] = vetor[j];
      vetor[j] = aux;
    }
  }

  // coloca o pivô na posição correta
  int aux = vetor[i + 1];
  vetor[i + 1] = vetor[fim];
  vetor[fim] = aux;

  return i + 1; // retorna a posição do pivô
}

// função recursiva do quick sort
void quickSort(int vetor[], int inicio, int fim) {
  if (inicio < fim) {
    int p = particionar(vetor, inicio, fim); // particiona o vetor

    quickSort(vetor, inicio, p - 1); // ordena a parte esquerda
    quickSort(vetor, p + 1, fim); // ordena a parte direita
  }
}

int main() {
  int dados[] = {64, 34, 25, 12, 22, 11, 90};
  int n = sizeof(dados) / sizeof(dados[0]); // tamanho do vetor
  quickSort(dados, 0, n - 1); // chama o quick sort

  printf("Vetor ordenado: ");
  for (int i = 0; i < n; i++) {
    printf("%d ", dados[i]);
  }

  printf("\n");
  return 0;
}