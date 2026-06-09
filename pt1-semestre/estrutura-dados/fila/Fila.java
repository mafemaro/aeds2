public class Fila {
    private int[] array;
    private int primeiro; // ele guarda a POSICAO do primeiro elemento
    private int ultimo;   // armazena a POSICAO onde o ultimo elemento foi inserido
    private int tamanho;  // tamanho atual da fila

    public Fila(int capacidade) {
        array = new int[capacidade]; // cria um array com tamanho definido pelo usuario
        primeiro = 0; // coloca todos os valores como 0 porque nao existe a fila ainda
        ultimo = 0;
        tamanho = 0;
    }

    // inserir no fim
    public void enfileirar(int x) throws Exception {
        if (tamanho == array.length) throw new Exception("Erro: Fila cheia!"); // se o array ja tiver lotado, nao adiciona nada
        
        array[ultimo] = x; // pega a ultima posicao
        ultimo = (ultimo + 1) % array.length; // avanca circularmente para a proxima posicao, como o array tem um tamanho fixo, quando chegar no final ele volta para o inicio

        // porque fazer esse circular? porque se fosse fixo, o que ia acontecer era um erro quando chegasse ao final da fila

        // impede que o índice ultimo saia dos limites do array e cause um erro, fazendo ele voltar para o começo (0) de forma automática.

        tamanho++; // adiciona mais um espaco para elemento na fila
    }

    // remover do inicio
    public int desenfileirar() throws Exception {
        if (tamanho == 0) throw new Exception("Erro: Fila vazia!");
        
        int removido = array[primeiro];
        primeiro = (primeiro + 1) % array.length; // usando a circularidade para avançar o índice do primeiro elemento, garantindo que ele volte ao início do array quando atingir o final.

        tamanho--; // decrementa tamanho pra tirar o ultimo elemento
         
        return removido; // retorna o elemento removido
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