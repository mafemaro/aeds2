# Bubble Sort

## 1. Visão Geral
O **Bubble Sort** (Ordenação por Flutuação) é um algoritmo de ordenação simples que atua localmente (*in-place*), alterando a própria lista de entrada. Seu funcionamento baseia-se na comparação contínua de elementos adjacentes, realizando a troca de posição (*swap*) quando estes encontram-se fora da ordem desejada.

O algoritmo recebe esse nome porque os elementos maiores "flutuam" para o final da lista a cada iteração.

---

## 2. Passo a Passo do Bubble Sort

Para ordenar uma lista em ordem crescente:

1. Percorra a coleção da esquerda para a direita.  
2. Compare cada elemento com o elemento adjacente à sua direita.  
3. Se o elemento à esquerda for estritamente maior que o elemento à direita, realize a troca de posição entre eles.  
4. Ao final da primeira passagem, o maior elemento da iteração estará garantidamente na última posição.  
5. Repita os passos de 1 a 3, ignorando os elementos finais que já foram ordenados nas passagens anteriores, até que a lista esteja completamente ordenada (nenhuma troca seja necessária).  

---

## 3. Análise de Eficiência e Complexidade

A eficiência do Bubble Sort é diretamente impactada pelo número de comparações e trocas de posição necessárias. Sempre que o algoritmo encontra uma inversão, realiza uma troca. Isso eleva substancialmente o tempo de execução à medida que o tamanho da lista aumenta.

- **Melhor Caso:** `O(n)`  
  Ocorre quando a lista já se encontra perfeitamente ordenada. Com a otimização adequada, o algoritmo realiza apenas uma passagem verificando os elementos e encerra a execução prematuramente.

- **Pior Caso:** `O(n²)`  
  Ocorre quando a lista está ordenada em ordem decrescente (inversa). O algoritmo precisará realizar o número máximo de comparações e trocas.

- **Caso Médio:** `O(n²)` *!! caso médio não vemos em AEDS 2.*

---

## 4. Implementação em Java

```java
public class BubbleSort {
  // metodo do bubblesort
  public static void ordenar(int[] vetor) {
    int n = vetor.length;
    
    // esse laço garante que ele vai passar pelo vetor n vezes
    for (int i = 0; i < n; i++) {

      // esse laço faz comparações lado a lado
      for (int j = 0; j < n - 1; j++) {

        // se o atual for maior que o proximo, troca de lado
        if (vetor[j] > vetor[j + 1]) {
          int aux = vetor[j];
          vetor[j] = vetor[j + 1];
          vetor[j + 1] = aux;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] dados = {64, 34, 25, 12, 22, 11, 90};
    ordenar(dados);

    System.out.print("Vetor ordenado: ");
    for (int i = 0; i < dados.length; i++) { // para imprimir todos os indices do vetor
      System.out.print(dados[i] + " ");
    }
  }
}
```
---

## 5. Alternativas e Conclusão

Embora o Bubble Sort seja didaticamente valioso por sua simplicidade e lógica direta, existem algoritmos mais robustos e eficientes, como:

- QuickSort  
- MergeSort  
- HeapSort  

Esses algoritmos operam com complexidade média de `O(n \log n)` e são mais indicados para grandes volumes de dados.

Ainda assim, o Bubble Sort mantém utilidade prática em listas pequenas ou quase ordenadas. Nessas situações, sua complexidade pode se aproximar de `O(n)`, tornando-o uma escolha ocasionalmente eficaz.