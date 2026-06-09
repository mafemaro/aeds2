public class Pilha {
    private int[] array;
    private int n; // Representa a quantidade e aponta para a próxima posição vaga

    public Pilha(int tamanho) {
        array = new int[tamanho]; // pega um array de tamanho definido pelo usuario
        n = 0;
    }

    // empilhar no fim
    public void empilhar(int x) throws Exception {
        if (n >= array.length) throw new Exception("Erro: Pilha cheia!"); // se o tamanho n for maior, a pilha ja nao tem mais capacidade
        array[n] = x; // pega a posicao do n e coloca o numero passado para empilhar

        n++; // passa pro proximo espaco da pilha, importante para controlar onde o prx elemento sera inserido
    }

    // desempilhar do fim
    public int desempilhar() throws Exception {
        if (n == 0) throw new Exception("Erro: Pilha vazia!"); // se n for 0, significa que nao tem nenhum elemento 
        return array[--n]; // decrementa o n para apontar para o penultimo elemento 
    }

    public void mostrar() {
        System.out.print("Topo -> [ ");
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}