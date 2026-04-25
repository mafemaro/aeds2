# Fila (Queue)

## 1. Visão Geral
A **Fila** é um Tipo Abstrato de Dados (TAD) linear que opera sob o princípio **FIFO** (*First In, First Out* - o primeiro a entrar é o primeiro a sair). Como em uma fila de banco do mundo real, os novos elementos entram no final da fila, enquanto o atendimento (remoção) ocorre sempre na frente.

Na ciência da computação, filas são vitais para o gerenciamento de recursos e escalonamento, sendo utilizadas em:
* Spoolers de impressão (documentos aguardando para serem impressos).
* Fila de processos no Sistema Operacional.
* Troca de mensagens entre sistemas (Message Brokers).

---

## 2. Operações Fundamentais

Uma fila opera estritamente pelas extremidades, utilizando dois métodos essenciais:

1.  **Enfileirar (Enqueue):** Insere um novo elemento no final (traseira) da fila.
2.  **Desenfileirar (Dequeue):** Remove e retorna o elemento que está no início (frente) da fila.

---

## 3. O Desafio da Implementação Estática

Implementar uma Fila utilizando um vetor linear simples apresenta um dilema de performance:

* **O Problema do Deslocamento:** Se inserirmos no final (`O(1)`) e removermos do início, todos os elementos restantes precisarão "andar" uma casa para a esquerda para preencher o vazio na posição 0. Isso torna a remoção `O(n)`, o que é ineficiente para grandes volumes de dados.
* **A Solução (Fila Circular):** Para evitar o deslocamento, utilizamos ponteiros virtuais para o "Primeiro" e o "Último". Quando um ponteiro atinge o final do vetor, ele "dá a volta" e recomeça do índice 0 (utilizando a operação de módulo `%`). Isso cria um ciclo, tornando ambas as operações `O(1)`.

---

## 4. Implementação em Java (Fila Circular Eficiente)

```java
public class Fila {
    private int[] array;
    private int primeiro; // Aponta para o primeiro elemento
    private int ultimo;   // Aponta para a próxima posição vazia no fim
    private int tamanho;  // Contador de elementos

    public Fila(int capacidade) {
        array = new int[capacidade];
        primeiro = 0;
        ultimo = 0;
        tamanho = 0;
    }

    // ENQUEUE (Inserir no fim)
    public void enfileirar(int x) throws Exception {
        if (tamanho == array.length) throw new Exception("Erro: Fila cheia!");
        
        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length; // Avança circularmente
        tamanho++;
    }

    // DEQUEUE (Remover do início)
    public int desenfileirar() throws Exception {
        if (tamanho == 0) throw new Exception("Erro: Fila vazia!");
        
        int removido = array[primeiro];
        primeiro = (primeiro + 1) % array.length; // Avança circularmente
        tamanho--;
        return removido;
    }

    public void mostrar() {
        System.out.print("Frente -> [ ");
        int i = primeiro;
        for (int cont = 0; cont < tamanho; cont++) {
            System.out.print(array[i] + " ");
            i = (i + 1) % array.length;
        }
        System.out.println("] <- Fim");
    }
}
```

---

## 5. Análise de Complexidade

Ao utilizar a estratégia da **Fila Circular**, alcançamos o cenário ideal de performance:

* **Enfileirar (Enqueue):** `O(1)` - O cálculo do resto da divisão garante acesso direto ao fim da fila.
* **Desenfileirar (Dequeue):** `O(1)` - O ponteiro "primeiro" apenas avança, sem precisar deslocar os outros dados.
* **Espaço:** `O(n)` - Limitado à capacidade alocada no array.

---

## 6. Conclusão
A Fila é a abstração correta quando precisamos garantir justiça e ordem temporal no processamento de dados (quem chega primeiro, é atendido primeiro). O uso da técnica de *Array Circular* é uma das sacadas mais elegantes da estrutura de dados básica para contornar o alto custo de manipulação de memória inerente aos vetores estáticos.

---

### **Simulador de Fila (FIFO)**

Para fixar a diferença crucial entre a Fila e as estruturas anteriores, brinque com o simulador abaixo. Note como os elementos sempre entram pela "Traseira" e saem obrigatoriamente pela "Frente", respeitando rigidamente a ordem de chegada.

```json?chameleon
{"component":"LlmGeneratedComponent","props":{"height":"600px","prompt":"Crie um simulador educativo de Fila (Queue) com a política FIFO. \n\nObjetivo: Demonstrar visualmente a entrada pelo final e saída pelo início, enfatizando o First In, First Out.\n\nConfiguração Inicial:\n- Estrutura visual horizontal representando um 'Tubo' ou 'Corredor' de 6 posições.\n- Elementos iniciais (da esquerda para direita): [10, 20, 30]. O '10' é o 'Primeiro' (Frente) e o '30' é o último inserido (Traseira).\n\nFuncionalidades:\n1. Botões: 'Enfileirar (Enqueue)' com campo para digitar um valor, e 'Desenfileirar (Dequeue)'.\n2. Labels dinâmicos: Mostre visualmente um indicador 'Frente da Fila' (onde ocorre o Dequeue) e 'Fim da Fila' (onde ocorre o Enqueue).\n3. Animação: \n   - Ao Enfileirar, o novo elemento deve entrar pela direita.\n   - Ao Desenfileirar, o elemento mais à esquerda sai.\n   - Após desenfileirar, anime o deslocamento do restante da fila para a esquerda, ilustrando o conceito lógico de fila andando.\n4. Log de operações: Histórico em texto (ex: 'Saiu o elemento 10').\n\nInstruções Visuais: Todo texto da interface em Português. Use formas com aparência sólida para os elementos e mantenha espaços delimitados no contêiner da fila.","id":"im_b92fdaf776b4b471"}}
```