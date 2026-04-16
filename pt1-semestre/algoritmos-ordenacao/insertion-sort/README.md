# Insertion Sort

## 1. Visão Geral

O **Insertion Sort (Ordenação por Inserção)** é um algoritmo de ordenação simples e intuitivo, semelhante à forma como organizamos cartas de baralho em nossas mãos. Ele atua **in-place**, ou seja, modifica o vetor original sem necessidade de estrutura auxiliar.

O algoritmo constrói uma parte do vetor já ordenada, elemento por elemento. O vetor é dividido logicamente em:

- **Parte ordenada** (inicialmente apenas o primeiro elemento)
- **Parte não ordenada** (o restante do vetor)

A cada iteração, um elemento da parte não ordenada é inserido na posição correta da parte ordenada.

---

## 2. Passo a Passo do Algoritmo

Para ordenar uma lista em ordem crescente:

1. Considere o primeiro elemento como já ordenado.
2. Pegue o próximo elemento (chamado de **chave**).
3. Compare a chave com os elementos da parte ordenada, da direita para a esquerda.
4. Desloque os elementos maiores uma posição para a direita.
5. Insira a chave na posição correta.
6. Repita o processo até o final do vetor.

---

## 3. Análise de Eficiência e Complexidade

O Insertion Sort é um algoritmo **adaptativo**, ou seja, seu desempenho melhora quando a lista já está parcialmente ordenada.

### Melhor caso
- **O(n)**
- Lista já ordenada
- Apenas comparações simples, sem deslocamentos significativos

### Caso médio
- **O(n²)**
- Não considerado em algumas disciplinas introdutórias de AEDs  *!! caso médio não vemos em AEDS 2.*

### Pior caso
- **O(n²)**
- Lista em ordem inversa
- Muitos deslocamentos e comparações

### Observações
- Utiliza **deslocamentos** ao invés de trocas
- Mais eficiente que algoritmos quadráticos simples em listas pequenas ou quase ordenadas

---

## 4. Implementação em Java

```java
public class InsertionSort {

  // método do insertion sort
  public static void ordenar(int[] vetor) {
    int n = vetor.length;

    // começa do segundo elemento
    for (int i = 1; i < n; i++) {
      int chave = vetor[i];
      int j = i - 1;

      // desloca elementos maiores que a chave
      while (j >= 0 && vetor[j] > chave) {
        vetor[j + 1] = vetor[j];
        j--;
      }

      // insere a chave na posição correta
      vetor[j + 1] = chave;
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 25, 12, 22, 11};
    ordenar(dados);

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) {
      System.out.print(dados[i] + " ");
    }
  }
}

---

## 5. Conclusão

O **Insertion Sort** é ideal para listas pequenas ou quase ordenadas, apresentando desempenho próximo de **O(n)** nesses casos.

Além disso, é um algoritmo:

- **Estável** (mantém a ordem de elementos iguais)
- **In-place** (não usa memória extra significativa)
- **Online** (pode ordenar enquanto recebe os dados)

Apesar dessas vantagens, sua complexidade **O(n²)** no pior caso o torna ineficiente para grandes volumes de dados. Nesses cenários, algoritmos como **Merge Sort** e **Quick Sort** são mais indicados.