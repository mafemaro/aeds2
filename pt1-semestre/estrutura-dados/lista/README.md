# Lista Linear

## 1. Visão Geral
Uma **Lista Linear** é um Tipo Abstrato de Dados (TAD) fundamental onde os elementos são organizados de forma sequencial. Diferente de estruturas mais rígidas, a lista permite a inserção e remoção de itens em qualquer posição: no início, no fim ou em um índice específico.

Na computação, as listas são a base para organizar coleções de dados como nomes de usuários, notas de alunos ou objetos complexos em sistemas de software.

---

## 2. Estrutura de Dados
Para implementar uma lista linear sequencial (estática), utilizamos dois componentes principais:

* **Array (Vetor):** O espaço de memória contíguo que armazena os elementos.
* **Contador (n):** Uma variável inteira que rastreia a quantidade atual de elementos presentes na lista e indica a próxima posição disponível no fim.

---

## 3. Operações e Métodos

Uma implementação robusta de Lista deve oferecer métodos para manipular os dados de forma segura:

### 3.1. Inserção
* **InserirInicio(elemento):** Adiciona um item na primeira posição (índice 0). Exige o deslocamento de todos os elementos existentes para a direita.
* **InserirFim(elemento):** Adiciona um item na posição `n`. É a inserção mais eficiente.
* **Inserir(elemento, posição):** Adiciona um item em um local específico, deslocando os elementos à direita daquela posição.

### 3.2. Remoção
* **RemoverInicio():** Retira o primeiro elemento e desloca todos os outros para a esquerda para preencher o vazio.
* **RemoverFim():** Retira o último elemento apenas decrementando o contador `n`.
* **Remover(posição):** Retira o elemento de um índice específico e reorganiza os sucessores.

---

## 4. Implementação em Java (LISTA LINEAR)

```java
public class ListaLinear {
    private int[] array;
    private int n;

    public ListaLinear(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    public void inserirFim(int x) throws Exception {
        if (n >= array.length) throw new Exception("Erro: Lista cheia!");
        array[n] = x;
        n++;
    }

    public void inserirInicio(int x) throws Exception {
        if (n >= array.length) throw new Exception("Erro: Lista cheia!");
        
        // Deslocar elementos para a direita
        for (int i = n; i > 0; i--) {
            array[i] = array[i-1];
        }
        
        array[0] = x;
        n++;
    }

    public int removerFim() throws Exception {
        if (n == 0) throw new Exception("Erro: Lista vazia!");
        return array[--n];
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}
```

---

## 5. Análise de Eficiência

A eficiência de uma lista linear sequencial depende da posição da operação, devido à necessidade de movimentação de memória:

* **Acesso (Busca por índice):** `O(1)` - O acesso é direto via endereço de memória.
* **Inserção/Remoção no Fim:** `O(1)` - Não exige deslocamento de outros elementos.
* **Inserção/Remoção no Início/Meio:** `O(n)` - No pior caso, todos os elementos precisam ser movidos.

---

## 6. Conclusão
A Lista Linear Sequencial é ideal para situações onde o acesso aleatório frequente é necessário e o tamanho dos dados é previsível. Para cenários com muitas inserções no início ou tamanho altamente dinâmico, estruturas como **Listas Encadeadas** podem ser alternativas mais eficientes.

---

### **Explorador de Operações em Lista**

Para entender por que as operações no início da lista são mais "caras" (lentas) do que no final, é preciso visualizar o **deslocamento de elementos**. No simulador abaixo, você pode inserir ou remover números e observar como os outros itens precisam se mover para abrir espaço ou preencher lacunas.

```json?chameleon
{"component":"LlmGeneratedComponent","props":{"height":"600px","prompt":"Crie um simulador educativo de Lista Linear Sequencial (Array). \n\nObjetivo: Demonstrar visualmente o deslocamento de elementos durante inserção e remoção.\n\nConfiguração Inicial:\n- Array de tamanho fixo 8.\n- Elementos iniciais: [10, 20, 30].\n\nFuncionalidades:\n1. Botões de ação: 'Inserir Início', 'Inserir Fim', 'Remover Início', 'Remover Fim'.\n2. Visualização: Mostre os 8 slots do array. Slots ocupados devem ter um destaque visual e mostrar o valor. Slots vazios devem ser visualmente distintos.\n3. Animação de Deslocamento: Quando o usuário clicar em 'Inserir Início', mostre visualmente os elementos existentes (10, 20, 30) 'andando' um slot para a direita antes do novo valor aparecer na posição 0.\n4. Contador 'n': Exiba o valor atual de n (quantidade de elementos).\n5. Feedback de Complexidade: Ao realizar uma ação, mostre uma pequena etiqueta dizendo se aquela operação foi O(1) ou O(n).\n\nInstruções visuais: Use blocos quadrados para representar as células do array. Garanta que a transição dos números entre as células seja visível para enfatizar o custo computacional do movimento.","id":"im_f6e683a34277e496"}}
```