public class Lista {
    private int[] array;
    private int n;

    public Lista(int tamanho) {
        array = new int[tamanho];
        n = 0;
    } // pega o tamanho do array, e tambem o contador inicializado

    public void inserirFim(int x) throws Exception { // no inserir fim...
        if (n >= array.length) throw new Exception("Erro: Lista cheia!"); // verifica se o contador ta maior que o tamanho do array
        array[n] = x; // se nao tiver, insere o elemento na posicao do contador
        n++; // e depois incrementa o contador 
    }
    // o incremento do contador que vai ajudar a controlar onde o proximo elemento deve ser inserido, e tambem a controlar o numero de elementos na lista

    public void inserirInicio(int x) throws Exception { // no inserir inicio...
        if (n >= array.length) throw new Exception("Erro: Lista cheia!"); // se n for maior que o tamanho do array, lista cheia
    
        for (int i = n; i > 0; i--) { // desloca os elementos pra direita
            array[i] = array[i-1]; // o array na posicao i vira o array na posicao i-1, ou seja, o elemento anterior
        }
        
        array[0] = x; // e adiciona na 1 posicao o numero que foi passado para colocar no inicio
        n++;
    }

    public int removerFim() throws Exception { // remover fim
        if (n == 0) throw new Exception("Erro: Lista vazia!"); // se tiver vazia a lista, nao tem como remover nada
        return array[--n]; // pega o array TODO menos a ultima posicao)
        
        // (entao tem que pensar o seguinte, ainda vai existir aquele numero que removemos, mas o contador vai estar apontando para a posicao anterior, entao se a gente tentar inserir um novo numero, ele vai sobrescrever o numero que foi removido)
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) throws Exception {
        Lista lista = new Lista(5);
        
        lista.inserirFim(10);
        lista.inserirFim(20);
        lista.inserirInicio(5);

        lista.mostrar(); // [ 5 10 20 ]

        System.out.println("Removido: " + lista.removerFim()); // Removido: 20
        lista.mostrar(); // [ 5 10 ]
        
    }
}