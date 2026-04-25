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