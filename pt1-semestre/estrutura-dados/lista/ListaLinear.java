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