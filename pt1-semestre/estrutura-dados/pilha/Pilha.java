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