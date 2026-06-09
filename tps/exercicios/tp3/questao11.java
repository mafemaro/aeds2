import java.io.*;
import java.util.Scanner;

// classes de suporte: data e hora
class Hora {
    public int h, m;
    public Hora(int h, int m) { this.h = h; this.m = m; }
    
    public static Hora parse(String s) {
        String[] p = s.split(":");
        return new Hora(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }
    
    public String formatar() { return String.format("%02d:%02d", h, m); }
}

class Data {
    public int d, m, a;
    public Data(int a, int m, int d) { this.a = a; this.m = m; this.d = d; }
    
    public static Data parse(String s) {
        String[] p = s.split("-");
        // csv vem no formato ano-mes-dia
        return new Data(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
    }
    
    public String formatar() { return String.format("%02d/%02d/%04d", d, m, a); }
}

class Restaurante {
    public int id, capacidade, preco;
    public String nome, cidade;
    public double avaliacao;
    public String[] tipos;
    public Hora hab, hfec;
    public Data dab;
    public boolean aberto;

    public Restaurante(String s) {
        String[] p = s.split(",");
        this.id = Integer.parseInt(p[0]);
        this.nome = p[1];
        this.cidade = p[2];
        this.capacidade = Integer.parseInt(p[3]);
        this.avaliacao = Double.parseDouble(p[4]);
        this.tipos = p[5].split(";");
        this.preco = p[6].length();
        String[] h = p[7].split("-");
        this.hab = Hora.parse(h[0]);
        this.hfec = Hora.parse(h[1]);
        this.dab = Data.parse(p[8]);
        this.aberto = p[9].trim().equals("true");
    }

    public String formatar() {
        String f_p = ""; 
        for(int i = 0; i < preco; i++) f_p += "$";
        
        return String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %s ## %s-%s ## %s ## %b]",
            id, nome, cidade, capacidade, avaliacao+"", String.join(",", tipos), 
            f_p, hab.formatar(), hfec.formatar(), dab.formatar(), aberto);
    }
}

// celula da lista dupla
class CelulaDupla {
    public Restaurante elemento;
    public CelulaDupla ant, prox;
    
    public CelulaDupla(Restaurante x) { 
        this.elemento = x; 
        this.ant = this.prox = null;
    }
}

// lista duplamente encadeada com quicksort
class ListaDupla {
    private CelulaDupla primeiro, ultimo;
    public int comp = 0, mov = 0;

    public ListaDupla() {
        primeiro = new CelulaDupla(null); // no sentinela
        ultimo = primeiro;
    }

    public void inserir(Restaurante x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    // metodo de comparacao adaptado (avaliacao e depois nome)
    private int comparar(Restaurante a, Restaurante b) {
        comp++;
        if (a.avaliacao > b.avaliacao) return 1;
        if (a.avaliacao < b.avaliacao) return -1;
        comp++;
        return a.nome.compareTo(b.nome);
    }

    public void quicksort() { 
        if (primeiro != ultimo) {
            quicksort(primeiro.prox, ultimo); 
        }
    }

    // logica do quicksort do codigo certo (com ponteiros duplos)
    private void quicksort(CelulaDupla esq, CelulaDupla dir) {
        if (esq == null || dir == null || esq == dir || esq.ant == dir) return;

        CelulaDupla i = esq;
        CelulaDupla j = dir;
        Restaurante pivo = esq.elemento;

        // particao: i vai para a direita e j para a esquerda
        while (i != j) {
            while (i != j && comparar(j.elemento, pivo) > 0) j = j.ant;
            while (i != j && comparar(i.elemento, pivo) <= 0) i = i.prox;

            if (i != j) {
                // troca elementos
                Restaurante tmp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = tmp;
                mov += 3;
            }
        }

        // coloca o pivo no lugar certo
        if (i != esq) {
            Restaurante tmp = esq.elemento;
            esq.elemento = i.elemento;
            i.elemento = tmp;
            mov += 3;
        }

        // recursividade
        quicksort(esq, i.ant);
        quicksort(i.prox, dir);
    }

    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.formatar());
        }
    }
}

public class questao11 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Restaurante[] ds = new Restaurante[1000];
        int n = 0;
        
        Scanner fs = new Scanner(new File("/tmp/restaurantes.csv"));
        fs.nextLine(); // pula cabecalho
        while(fs.hasNextLine()) {
            String linha = fs.nextLine().replace("\r", "");
            if (linha.length() > 0) ds[n++] = new Restaurante(linha);
        }
        fs.close();

        ListaDupla lista = new ListaDupla();
        
        while (sc.hasNext()) {
            String line = sc.next();
            if (line.equals("FIM") || line.equals("-1")) break;
            
            int id = Integer.parseInt(line);
            for(int i = 0; i < n; i++) {
                if(ds[i].id == id) { 
                    lista.inserir(ds[i]); 
                    break; 
                }
            }
        }

        long start = System.currentTimeMillis();
        lista.quicksort();
        long total = System.currentTimeMillis() - start;

        FileWriter fw = new FileWriter("885428_quicksort_flexivel.txt");
        fw.write("885428\t" + lista.comp + "\t" + lista.mov + "\t" + total);
        fw.close();

        lista.mostrar();
        sc.close();
    }
}