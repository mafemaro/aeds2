# Selection Sort

## 1. Visão Geral
O **Selection Sort** (Ordenação por Seleção) é um algoritmo de ordenação simples que atua localmente (*in-place*), modificando a própria estrutura de dados sem necessidade de memória adicional significativa.

Seu funcionamento se baseia em dividir logicamente o vetor em duas partes:
- uma parte já ordenada (iniciando vazia)
- uma parte não ordenada

A cada iteração, o algoritmo percorre a parte não ordenada, encontra o menor elemento e o coloca na posição correta da parte ordenada.

---

## 2. Passo a Passo do Algoritmo

Para ordenar uma lista em ordem crescente:

1. Considere o primeiro elemento como o menor da iteração atual.
2. Percorra o restante da lista procurando o menor elemento.
3. Sempre que encontrar um valor menor, atualize o índice do menor elemento.
4. Ao final da varredura, troque o menor elemento encontrado com o elemento da posição atual.
5. Avance para a próxima posição e repita o processo até que toda a lista esteja ordenada.

---

## 3. Análise de Eficiência e Complexidade

O Selection Sort não é um algoritmo adaptativo, ou seja, seu desempenho não melhora mesmo que a lista já esteja parcialmente ordenada.

- **Melhor Caso:** `O(n²)`  
  Mesmo com a lista ordenada, o algoritmo realiza todas as comparações.

- **Pior Caso:** `O(n²)`  
  Ocorre independentemente da disposição dos elementos.

- **Caso Médio:** `O(n²)` *!! caso médio não vemos em AEDS 2.*

- **Número de trocas:** `O(n)`  
  Diferente do Bubble Sort, realiza no máximo uma troca por iteração.

---

## 4. Implementação em Java

```java
public class SelectionSort {

  // metodo do selection sort
  public static void ordenar(int[] vetor) {
    int n = vetor.length;

    // percorre o vetor definindo a fronteira da parte ordenada
    for (int i = 0; i < n - 1; i++) {

      // assume que o menor elemento é o da posição atual
      int indiceMenor = i;

      // procura o menor elemento no restante do vetor
      for (int j = i + 1; j < n; j++) {

        // se encontrar um elemento menor, atualiza o índice
        if (vetor[j] < vetor[indiceMenor]) {
          indiceMenor = j;
        }
      }

      // se o menor não estiver na posição atual, realiza a troca
      if (indiceMenor != i) {
        int aux = vetor[i];
        vetor[i] = vetor[indiceMenor];
        vetor[indiceMenor] = aux;
      }
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 25, 12, 22, 11};

    ordenar(dados);

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // percorre e imprime o vetor
      System.out.print(dados[i] + " ");
    }
  }
}

---

## 5. Conclusão

O Selection Sort é um algoritmo simples, previsível e fácil de implementar. Ele realiza sempre **O(n²) comparações**, independentemente da entrada, o que o torna ineficiente para grandes volumes de dados.

Seu principal ponto positivo é o número reduzido de trocas (**no máximo n-1**), o que pode ser vantajoso em cenários muito específicos.

Na prática, para aplicações reais, algoritmos como **Merge Sort** e **Quick Sort** são muito mais eficientes, com complexidade média de **O(n log n)** e desempenho superior em grandes conjuntos de dados.
