# Pilha (Stack, ou pilha de pratos da Maria Fernanda pros mais intímos haha)

## 1. Visão Geral
A **Pilha** é um Tipo Abstrato de Dados (TAD) linear que segue a política **LIFO** (*Last In, First Out* - o último a entrar é o primeiro a sair). É uma estrutura restrita, onde todas as operações de inserção e remoção ocorrem em uma única extremidade, chamada de **topo**.

Este conceito é amplamente utilizado em computação para:
* Gerir a execução de sub-rotinas (Pilha de Chamadas).
* Histórico de navegação em browsers (botão "Voltar").
* Mecanismos de "Desfazer" (Undo) em editores de texto.

---

## 2. Operações Fundamentais

Uma pilha opera basicamente com dois métodos principais:

1.  **Empilhar (Push):** Adiciona um novo elemento no topo da pilha.
2.  **Desempilhar (Pop):** Remove o elemento que está no topo (o mais recente).

Diferente da lista linear genérica, na pilha não é permitida a inserção ou remoção no meio da estrutura.

---

## 3. Estratégias de Implementação

Podemos criar uma pilha utilizando a lógica de uma lista linear sequencial (vetor). No entanto, a escolha de *onde* é o topo impacta a eficiência:

* **Solução 1 (Eficiente):** O topo é o final do array (`InserirFim` e `RemoverFim`).
    * Não exige deslocamento de elementos. Complexidade **O(1)**.
* **Solução 2 (Ineficiente):** O topo é o início do array (`InserirInicio` e `RemoverInicio`).
    * Exige deslocar todos os elementos a cada operação. Complexidade **O(n)**.

---

## 4. Implementação em Java (Estratégia Eficiente)

```java
public class Pilha {
    private int[] array;
    private int n; // Representa a quantidade e aponta para a próxima posição vaga

    public Pilha(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    // (Empilhar no fim)
    public void empilhar(int x) throws Exception {
        if (n >= array.length) throw new Exception("Erro: Pilha cheia!");
        array[n] = x;
        n++;
    }

    // (Desempilhar do fim)
    public int desempilhar() throws Exception {
        if (n == 0) throw new Exception("Erro: Pilha vazia!");
        return array[--n]; // Decrementa e retorna o topo
    }

    public void mostrar() {
        System.out.print("Topo -> [ ");
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}
```

---

## 5. Análise de Complexidade

Considerando a implementação onde o topo é o final do vetor:

* **Empilhar:** `O(1)` - Acesso direto ao índice `n`.
* **Desempilhar:** `O(1)` - Apenas decremento do ponteiro.
* **Espaço:** `O(n)` - Proporcional ao tamanho máximo definido para o array.

---

## 6. Conclusão
A pilha é a estrutura ideal quando a ordem de processamento deve ser a inversa da ordem de chegada. Sua implementação estática é extremamente veloz (tempo constante), desde que o topo seja mantido no final do array para evitar movimentações desnecessárias de memória.

---

### **Simulador de Pilha**

Para visualizar como o último elemento adicionado é sempre o primeiro a sair, utilize o simulador abaixo. Observe que, ao "Empilhar", o novo valor ocupa o topo e, ao "Desempilhar", o acesso é restrito ao item mais recente.

```json?chameleon
{"component":"LlmGeneratedComponent","props":{"height":"600px","prompt":"Crie um simulador educativo de Pilha (Stack). \n\nObjetivo: Demonstrar a política LIFO (Last In, First Out) e a eficiência O(1).\n\nConfiguração Inicial:\n- Array vertical de tamanho 6.\n- Elementos iniciais: [10, 20] (com 20 no topo).\n\nFuncionalidades:\n1. Botões: 'Empilhar (Push)' com input de valor, 'Desempilhar (Pop)'.\n2. Visualização Vertical: Represente a pilha como uma coluna de blocos. O fundo da pilha deve estar na base e o topo crescer para cima.\n3. Destaque do Topo: Mostre visualmente uma seta ou indicador escrito 'TOPO' apontando para o elemento mais alto.\n4. Animação: Quando o usuário clicar em Push, o novo elemento deve 'cair' no topo da pilha. No Pop, o elemento do topo deve 'sair' para cima.\n5. Log de Operações: Mostre uma lista das últimas ações realizadas (ex: 'Pop: removeu 20').\n\nInstruções Visuais: Use blocos coloridos para os números. Mantenha os slots vazios visíveis como contornos para mostrar o limite da pilha. Todo o texto da interface deve estar em Português.","id":"im_c6defa3320921770"}}
```