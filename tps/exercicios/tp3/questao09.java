import java.util.Scanner;

// celula da matriz com quatro ponteiros
class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.inf = this.sup = this.esq = this.dir = null;
    }
}

// matriz dinamica com encadeamento nos quatro sentidos
class Matriz {
    private Celula inicio;
    private int linha, coluna;

    // aloca a matriz e interliga as celulas pelos ponteiros
    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        
        if (linha <= 0 || coluna <= 0) return;

        // cria a primeira celula
        inicio = new Celula();
        Celula atual = inicio;

        // cria a primeira linha
        for (int j = 1; j < coluna; j++) {
            Celula nova = new Celula();
            atual.dir = nova;
            nova.esq = atual;
            atual = nova;
        }

        // cria as demais linhas interligando com a anterior
        Celula topoLinhaAnterior = inicio;
        for (int i = 1; i < linha; i++) {
            Celula novaLinhaInicio = new Celula();
            novaLinhaInicio.sup = topoLinhaAnterior;
            topoLinhaAnterior.inf = novaLinhaInicio;

            Celula celulaAtual = novaLinhaInicio;
            Celula celulaAcima = topoLinhaAnterior.dir;

            for (int j = 1; j < coluna; j++) {
                Celula nova = new Celula();
                celulaAtual.dir = nova;
                nova.esq = celulaAtual;
                nova.sup = celulaAcima;
                celulaAcima.inf = nova;

                celulaAtual = nova;
                celulaAcima = celulaAcima.dir;
            }
            topoLinhaAnterior = novaLinhaInicio;
        }
    }

    // preenche a matriz com dados do teclado
    public void ler(Scanner sc) {
        Celula l = inicio;
        for (int i = 0; i < linha; i++) {
            Celula c = l;
            for (int j = 0; j < coluna; j++) {
                c.elemento = sc.nextInt();
                c = c.dir;
            }
            l = l.inf;
        }
    }

    // soma duas matrizes de mesma dimensao
    public Matriz somar(Matriz m) {
        Matriz resp = new Matriz(this.linha, this.coluna);
        Celula l1 = this.inicio, l2 = m.inicio, l3 = resp.inicio;

        while (l1 != null) {
            Celula c1 = l1, c2 = l2, c3 = l3;
            while (c1 != null) {
                c3.elemento = c1.elemento + c2.elemento;
                c1 = c1.dir; c2 = c2.dir; c3 = c3.dir;
            }
            l1 = l1.inf; l2 = l2.inf; l3 = l3.inf;
        }
        return resp;
    }

    // multiplica duas matrizes
    public Matriz multiplicar(Matriz m) {
        Matriz resp = new Matriz(this.linha, m.coluna);
        Celula l1 = this.inicio, l3 = resp.inicio;

        while (l1 != null) {
            Celula c2Inicio = m.inicio;
            Celula c3 = l3; 
            while (c2Inicio != null) {
                int soma = 0;
                Celula c1 = l1, c2 = c2Inicio; 
                while (c1 != null && c2 != null) {
                    soma += c1.elemento * c2.elemento;
                    c1 = c1.dir;
                    c2 = c2.inf;
                }
                c3.elemento = soma;
                c3 = c3.dir;
                c2Inicio = c2Inicio.dir; 
            }
            l1 = l1.inf; l3 = l3.inf;
        }
        return resp;
    }

    // imprime diagonal principal sem espaco sobrando no fim
    public void mostrarDiagonalPrincipal() {
        if (this.linha != this.coluna) return;
        Celula atual = inicio;
        while (atual != null) {
            System.out.print(atual.elemento);
            atual = atual.dir;
            if (atual != null) {
                atual = atual.inf;
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // imprime diagonal secundaria sem espaco sobrando no fim
    public void mostrarDiagonalSecundaria() {
        if (this.linha != this.coluna) return;
        Celula atual = inicio;
        while (atual.dir != null) atual = atual.dir;
        while (atual != null) {
            System.out.print(atual.elemento);
            atual = atual.inf;
            if (atual != null) {
                atual = atual.esq;
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    // imprime a matriz completa formatada para o verde
    public void mostrar() {
        Celula l = inicio;
        while (l != null) {
            Celula c = l;
            while (c != null) {
                System.out.print(c.elemento);
                if (c.dir != null) System.out.print(" ");
                c = c.dir;
            }
            System.out.println();
            l = l.inf;
        }
    }
}

public class questao09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int numCasos = sc.nextInt();
        for (int k = 0; k < numCasos; k++) {
            // leitura da primeira matriz
            int l1 = sc.nextInt(), c1 = sc.nextInt();
            Matriz m1 = new Matriz(l1, c1);
            m1.ler(sc);
            
            // leitura da segunda matriz
            int l2 = sc.nextInt(), c2 = sc.nextInt();
            Matriz m2 = new Matriz(l2, c2);
            m2.ler(sc);
            
            // operacoes e impressoes
            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();
            
            Matriz soma = m1.somar(m2);
            soma.mostrar();
            
            Matriz mult = m1.multiplicar(m2);
            mult.mostrar();
        }
        sc.close();
    }
}