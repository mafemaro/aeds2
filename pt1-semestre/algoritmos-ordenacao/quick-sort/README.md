# Quick Sort

## 1. Visão Geral

O **Quick Sort (Ordenação Rápida)** é um dos algoritmos de ordenação mais eficientes e amplamente utilizados na computação. Ele utiliza o paradigma de **Divisão e Conquista (Divide and Conquer)**.

O coração do Quick Sort é a operação de particionamento. O algoritmo escolhe um elemento especial chamado de **pivô** e reorganiza o vetor de forma que:

- Todos os elementos menores que o pivô fiquem à sua esquerda  
- Todos os elementos maiores que o pivô fiquem à sua direita  

Após essa etapa, o pivô estará em sua posição definitiva e o algoritmo é aplicado recursivamente às duas metades.

---

## 2. Passo a Passo do Algoritmo

Para ordenar uma lista:

1. **Escolha do pivô**: selecione um elemento do vetor (comumente o último elemento do trecho).
2. **Particionamento**: reorganize o vetor para separar menores e maiores que o pivô.
3. **Posicionamento**: o pivô é colocado na sua posição final correta.
4. **Recursão (Divisão)**: o vetor é dividido em dois subvetores (esquerda e direita do pivô).
5. **Conquista**: repita o processo nos subvetores até que tenham tamanho 0 ou 1.

---

## 3. Análise de Eficiência e Complexidade

O Quick Sort é extremamente eficiente na prática.

### Melhor caso
- **O(n log n)**
- Ocorre quando o pivô divide o vetor igualmente.

### Caso médio
- **O(n log n)**
- Desempenho típico em cenários reais. *!! caso médio não vemos em AEDS 2.*

### Pior caso
- **O(n²)**
- Ocorre quando o pivô é sempre o menor ou maior elemento (ex: vetor já ordenado).

### Complexidade de espaço
- **O(log n)**
- Devido à pilha de chamadas recursivas (Call Stack).

---

## 4. Implementação em Java

```java
public class QuickSort {

  // Método principal que o usuário chama
  public static void ordenar(int[] vetor) {
    ordenarRecursivo(vetor, 0, vetor.length - 1);
  }

  // Método recursivo do Quick Sort
  private static void ordenarRecursivo(int[] vetor, int inicio, int fim) {
    if (inicio < fim) {

      int posicaoPivo = particionar(vetor, inicio, fim);

      ordenarRecursivo(vetor, inicio, posicaoPivo - 1);
      ordenarRecursivo(vetor, posicaoPivo + 1, fim);
    }
  }

  // Método que faz o particionamento
  private static int particionar(int[] vetor, int inicio, int fim) {
    int pivo = vetor[fim]; // escolhe o último elemento como pivô
    int i = inicio - 1;

    for (int j = inicio; j < fim; j++) {
      if (vetor[j] <= pivo) {
        i++;
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
      }
    }

    int aux = vetor[i + 1];
    vetor[i + 1] = vetor[fim];
    vetor[fim] = aux;

    return i + 1;
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

O **Quick Sort** é frequentemente o algoritmo de ordenação padrão em bibliotecas de várias linguagens devido à sua alta eficiência prática.

Ele ordena os dados no próprio vetor (**in-place**), economizando memória em comparação com o Merge Sort.

Apesar do pior caso **O(n²)**, isso raramente ocorre em aplicações reais, pois técnicas como:

- pivô aleatório  
- mediana de três  
- escolha do elemento central  

ajudam a evitar divisões desbalanceadas, mantendo o desempenho próximo de **O(n log n)** na maioria dos casos.