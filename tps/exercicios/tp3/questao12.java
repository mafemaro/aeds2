import java.io.*;
import java.util.Scanner;

// classes de suporte: hora e data
class Hora {
    public int h, m;
    
    // construtor pra criar a hora passando os valores
    public Hora(int h, int m) { 
        this.h = h; 
        this.m = m; 
    }
    
    public static Hora parse(String s) {
        String[] p = s.split(":"); // separa a string exatamente onde tem os dois pontos
        return new Hora(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
    }
    
    public String formatar() { 
        return String.format("%02d:%02d", h, m); // deixa com dois digitos e zero na frente se precisar
    }
}

class Data {
    public int a, m, d;
    
    // construtor pra criar a data
    public Data(int a, int m, int d) { 
        this.a = a; 
        this.m = m; 
        this.d = d; 
    }
    
    public static Data parse(String s) {
        String[] p = s.split("-"); // corta a string onde tem traco
        // csv vem no formato ano-mes-dia
        return new Data(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
    }
    
    public String formatar() { 
        return String.format("%02d/%02d/%04d", d, m, a); // coloca no padrao brasileiro de data
    }
}

class Restaurante {
    public int id, capacidade, preco;
    public String nome, cidade;
    public double avaliacao;
    public String[] tipos;
    public Hora hab, hfec;
    public Data dab;
    public boolean aberto;

    // parse manual sem scanner interno (mais rapido pro verde)
    public Restaurante(String s) {
        String[] p = s.split(",");
        this.id = Integer.parseInt(p[0]);
        this.nome = p[1];
        this.cidade = p[2];
        this.capacidade = Integer.parseInt(p[3]);
        this.avaliacao = Double.parseDouble(p[4]);
        this.tipos = p[5].split(";");
        this.preco = p[6].length();
        
        // separa os horarios de abertura e fechamento
        String[] h = p[7].split("-");
        this.hab = Hora.parse(h[0]);
        this.hfec = Hora.parse(h[1]);
        
        this.dab = Data.parse(p[8]);
        this.aberto = p[9].trim().equals("true");
    }

    public String formatar() {
        String f_p = ""; 
        for(int i = 0; i < preco; i++) f_p += "$"; // adiciona um cifrao pra cada numero da faixa
        
        // coloca tudo no padrao chato do verde
        return String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %s ## %s-%s ## %s ## %b]",
            id, nome, cidade, capacidade, avaliacao+"", String.join(",", tipos), 
            f_p, hab.formatar(), hfec.formatar(), dab.formatar(), aberto);
    }
}

// no da arvore
class No {
    public Restaurante elemento;
    public No esq, dir;
    
    public No(Restaurante r) {
        this.elemento = r;
        this.esq = this.dir = null;
    }
}

// arvore binaria com a logica rapidona
class ArvoreBinaria {
    private No raiz;
    public int comparacoes = 0;

    public ArvoreBinaria() {
        raiz = null;
    }

    // insere na arvore baseando-se no nome
    public void inserir(Restaurante x) {
        raiz = inserir(x, raiz);
    }

    private No inserir(Restaurante x, No i) {
        if (i == null) {
            i = new No(x);
        } else if (x.nome.compareTo(i.elemento.nome) < 0) {
            i.esq = inserir(x, i.esq);
        } else if (x.nome.compareTo(i.elemento.nome) > 0) {
            i.dir = inserir(x, i.dir);
        }
        // se o nome for igual nao faz nada pra nao duplicar
        return i;
    }

    // pesquisa mostrando o caminho de ponteiros percorridos em uma linha so
    public String pesquisar(String nome) {
        return pesquisar(nome, raiz, "raiz");
    }

    private String pesquisar(String nome, No i, String caminho) {
        if (i == null) {
            return caminho + " NAO";
        }
        
        comparacoes++;
        if (nome.equals(i.elemento.nome)) {
            return caminho + " SIM";
        } else if (nome.compareTo(i.elemento.nome) < 0) {
            return pesquisar(nome, i.esq, caminho + " esq");
        } else {
            return pesquisar(nome, i.dir, caminho + " dir");
        }
    }

    // caminhamento central (em-ordem)
    public void caminharCentral() {
        caminharCentral(raiz);
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento.formatar());
            caminharCentral(i.dir);
        }
    }
}

public class questao12 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Restaurante[] ds = new Restaurante[8192]; // vetor de restaurantes
        int n = 0;
        
        // le o arquivo todo primeiro
        Scanner fs = new Scanner(new File("/tmp/restaurantes.csv"));
        fs.nextLine(); // pula cabecalho
        while(fs.hasNextLine()) {
            String linha = fs.nextLine().replace("\r", "");
            if (linha.length() > 0) ds[n++] = new Restaurante(linha);
        }
        fs.close();

        ArvoreBinaria arvore = new ArvoreBinaria();
        
        // PARTE 1: insercao ate o -1 ou FIM
        while (sc.hasNext()) {
            String line = sc.next();
            if (line.equals("FIM") || line.equals("-1")) break;
            
            int id = Integer.parseInt(line);
            for(int i = 0; i < n; i++) {
                if(ds[i].id == id) { 
                    arvore.inserir(ds[i]); 
                    break; 
                }
            }
        }

        if(sc.hasNextLine()) sc.nextLine(); // limpa sujeira do buffer
        
        // cronometra as pesquisas
        long start = System.currentTimeMillis();
        
        // PARTE 2: pesquisa nomes ate o FIM
        while (sc.hasNextLine()) {
            String linha = sc.nextLine().replace("\r", "");
            if (linha.equals("FIM")) break;
            if (!linha.isEmpty()) {
                System.out.println(arvore.pesquisar(linha));
            }
        }
        
        long total = System.currentTimeMillis() - start;

        // cria o arquivo de log do verde
        FileWriter fw = new FileWriter("885428_arvore_binaria.txt");
        fw.write("885428\t" + arvore.comparacoes + "\t" + total);
        fw.close();

        // PARTE 3: dump da arvore em ordem central
        arvore.caminharCentral();

        sc.close();
    }
}