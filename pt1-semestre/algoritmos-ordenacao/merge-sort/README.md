# Merge Sort

## 1. Visão Geral

O **Merge Sort (Ordenação por Intercalação)** é um algoritmo de ordenação altamente eficiente e estável, baseado no paradigma de **Divisão e Conquista (Divide and Conquer)**.

Seu funcionamento consiste em dividir a estrutura de dados sucessivamente pela metade até que cada subvetor contenha apenas um único elemento (já ordenado). Em seguida, o algoritmo reconstrói o vetor original através da etapa de combinação (merge), juntando os subvetores ordenados.

Diferente de algoritmos como Selection Sort e Insertion Sort, o Merge Sort **não atua in-place**, necessitando de memória auxiliar.

---

## 2. Passo a Passo do Algoritmo

1. **Divisão**: Encontre o ponto médio do vetor e divida em duas metades.
2. **Conquista (Recursão)**: Aplique o Merge Sort recursivamente em cada metade até restarem vetores de tamanho 1.
3. **Combinação (Merge)**: Intercale os subvetores ordenados comparando seus elementos e reconstruindo o vetor final.

---

## 3. Análise de Eficiência e Complexidade

O Merge Sort possui comportamento previsível em todos os casos.

- **Melhor Caso:** `O(n log n)`
- **Caso Médio:** `O(n log n)` *!! caso médio não vemos em AEDS 2.*
- **Pior Caso:** `O(n log n)`

### Complexidade de espaço
- **O(n)**  
  Necessita de um vetor auxiliar para realizar a intercalação.

---

## 4. Implementação em Java

```java
public class MergeSort {

  // metodo principal
  public static void ordenar(int[] vetor) {
    int[] auxiliar = new int[vetor.length]; // vetor auxiliar
    ordenarRecursivo(vetor, auxiliar, 0, vetor.length - 1);
  }

  // metodo recursivo de divisao
  private static void ordenarRecursivo(int[] vetor, int[] auxiliar, int inicio, int fim) {
    if (inicio < fim) {
      int meio = (inicio + fim) / 2; // encontra o meio
      ordenarRecursivo(vetor, auxiliar, inicio, meio); // esquerda
      ordenarRecursivo(vetor, auxiliar, meio + 1, fim); // direita
      intercalar(vetor, auxiliar, inicio, meio, fim); // merge
    }
  }

  // metodo de intercalação
  private static void intercalar(int[] vetor, int[] auxiliar, int inicio, int meio, int fim) {

    for (int i = inicio; i <= fim; i++) {
      auxiliar[i] = vetor[i]; // copia para auxiliar
    }

    int i = inicio;
    int j = meio + 1;
    int k = inicio;

    while (i <= meio && j <= fim) {
      if (auxiliar[i] <= auxiliar[j]) {
        vetor[k] = auxiliar[i];
        i++;
      } else {
        vetor[k] = auxiliar[j];
        j++;
      }

      k++;
    }

    // copia o restante da esquerda
    while (i <= meio) {
      vetor[k] = auxiliar[i];
      i++;
      k++;
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90};
    ordenar(dados);

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) {
      System.out.print(dados[i] + " ");
    }
  }
}

---

## 5. Conclusão

O **Merge Sort** é um dos algoritmos de ordenação mais importantes da Ciência da Computação, pois garante complexidade **O(n log n)** em todos os casos (melhor, médio e pior).

### Vantagens:
- Algoritmo **estável** (mantém a ordem de elementos iguais)
- Ideal para **listas encadeadas**
- Muito eficiente em **ordenação externa** (grandes volumes de dados)

### Desvantagem:
- Uso de memória adicional **O(n)** devido ao vetor auxiliar

Apesar disso, sua previsibilidade e eficiência o tornam uma excelente escolha em cenários que exigem desempenho consistente.