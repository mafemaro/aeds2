# Heap Sort

## 1. Visão Geral

O **Heap Sort (Ordenação por Heap)** é um algoritmo de ordenação eficiente que combina a performance do Merge Sort com a vantagem de ser **in-place**, como o Insertion Sort.

Ele é baseado na estrutura de dados **Heap Binário**, geralmente um **Max Heap** para ordenação crescente.

No Heap Binário:
- O pai está no índice `i`
- Filho esquerdo → `2*i + 1`
- Filho direito → `2*i + 2`

No **Max Heap**, todo nó pai é maior ou igual aos seus filhos.

---

## 2. Passo a Passo do Heap

### 1. Construção do Heap (Build-Heap)
- Reorganiza o vetor para obedecer à propriedade do Max Heap
- O maior elemento fica na raiz (posição 0)

### 2. Extração e Ordenação

1. Troca a raiz (maior elemento) com o último elemento
2. Reduz o tamanho lógico do heap
3. Aplica o **heapify** para restaurar o heap
4. Repete até ordenar todo o vetor

---

## 3. Análise de Eficiência e Complexidade

O Heap Sort é altamente previsível e não degrada como o Quick Sort.

- **Melhor Caso:** `O(n log n)`
- **Caso Médio:** `O(n log n)`
- **Pior Caso:** `O(n log n)`

### Complexidade de espaço
- **O(1)** (in-place)

### Estabilidade
- ❌ Não é estável

---

## 4. Implementação em Java

```java
public class HeapSort {

  // metodo principal
  public static void ordenar(int[] vetor) {
    int n = vetor.length;

    // constroi o heap (max heap)
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(vetor, n, i);
    }

    // extrai os elementos um por um
    for (int i = n - 1; i > 0; i--) {

      int aux = vetor[0];
      vetor[0] = vetor[i];
      vetor[i] = aux;

      heapify(vetor, i, 0); // refaz o heap
    }
  }

  // metodo heapify (ajusta o heap)
  private static void heapify(int[] vetor, int n, int i) {

    int maior = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < n && vetor[esq] > vetor[maior]) {
      maior = esq;
    }

    if (dir < n && vetor[dir] > vetor[maior]) {
      maior = dir;
    }

    if (maior != i) {

      int aux = vetor[i];
      vetor[i] = vetor[maior];
      vetor[maior] = aux;

      heapify(vetor, n, maior);
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

O **Heap Sort** é um algoritmo que garante desempenho **O(n log n)** em todos os casos, sem necessidade de memória extra significativa.

### Vantagens:
- Tempo garantido **O(n log n)**
- **In-place** (usa O(1) de memória)
- Não sofre degradação como o Quick Sort

### Desvantagens:
- ❌ Não é estável
- ❌ Pode ser mais lento na prática devido à baixa localidade de cache

Mesmo assim, é muito utilizado em sistemas críticos onde previsibilidade de desempenho é essencial.