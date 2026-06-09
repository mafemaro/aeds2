import java.io.File;
import java.util.Scanner;

// classe p tratar a hora
class Hora{
  private int hora;
  private int minuto;
  
  // construtor padrao
  public Hora(int hora, int minuto){
    this.hora = hora;
    this.minuto = minuto;
  }
  
  // gets e sets basicos
  public int getHora(){ return hora; }
  public int getMinuto(){ return minuto; }
  public void setHora(int hora){ this.hora = hora; }
  public void setMinuto(int minuto){ this.minuto = minuto; }
  
  // transforma a string de hora em objeto
  public static Hora parseHora(String s){
    Scanner sc = new Scanner(s); 
    sc.useDelimiter(":"); // separa nos dois pontos
    int hora = sc.nextInt(); 
    int minuto = sc.nextInt(); 
    Hora h = new Hora(hora, minuto); 
    sc.close();
    return h; 
  }

  // deixa a hora formatada bonitinha
  public String formatar(){
    return String.format("%02d:%02d", this.hora, this.minuto); 
  }
}   

// classe p tratar a data
class Data{
  private int ano;
  private int mes;
  private int dia;
  
  // construtor da data
  public Data(int ano, int mes, int dia){
    this.ano = ano;
    this.mes = mes;
    this.dia = dia;
  }
  
  public int getAno(){ return ano; }
  public int getMes(){ return mes; }
  public int getDia(){ return dia; }
  public void setAno(int ano){ this.ano = ano; }
  public void setMes(int mes){ this.mes = mes; }
  public void setDia(int dia){ this.dia = dia; }
  
  // le a data do csv com traco
  public static Data parseData(String s){ 
    Scanner sc = new Scanner(s);  
    sc.useDelimiter("-"); // corta no traco
    int ano = sc.nextInt(); 
    int mes = sc.nextInt(); 
    int dia = sc.nextInt(); 
    sc.close();
    Data data = new Data(ano, mes, dia); 
    return data; 
  }
 
  // padrao brasileiro p printar
  public String formatar(){
    return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano); 
  }
}
  
// classe com os dados do restaurante
class Restaurante{
  private int idRestaurante;
  private String nome;
  private String cidade;
  private int capacidade;
  private double avaliacao;
  private String[] tiposCozinha;
  private int faixa_preco;
  private Hora horarioAbertura;
  private Hora horarioFechamento;
  private Data dataAbertura;
  private boolean aberto;
  
  // construtor completo p carregar tudo
  public Restaurante(int idRestaurante, String nome, String cidade, int capacidade, double avaliacao,
      String[] tiposCozinha,int faixa_preco, Hora horarioAbertura, Hora horarioFechamento, Data dataAbertura, boolean aberto) {
    this.idRestaurante = idRestaurante;
    this.nome = nome;
    this.cidade = cidade;
    this.capacidade = capacidade;
    this.avaliacao = avaliacao;
    this.tiposCozinha = tiposCozinha;
    this.faixa_preco = faixa_preco;
    this.horarioAbertura = horarioAbertura;
    this.horarioFechamento = horarioFechamento;
    this.dataAbertura = dataAbertura;
    this.aberto = aberto;
  }
  
  public int getIdRestaurante() { return idRestaurante; }
  public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public String getCidade() { return cidade; }
  public void setCidade(String cidade) { this.cidade = cidade; }
  public int getCapacidade() { return capacidade; }
  public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
  public double getAvaliacao() { return avaliacao; }
  public void setAvaliacao(double avaliacao) { this.avaliacao = avaliacao; }
  public String[] getTiposCozinha() { return tiposCozinha; }
  public void setTiposCozinha(String[] tiposCozinha) { this.tiposCozinha = tiposCozinha; }
  public int getFaixa_Preco(){ return faixa_preco; }
  public void setFaixa_Preco(int faixa_preco){ this.faixa_preco = faixa_preco; }
  public Hora getHorarioAbertura() { return horarioAbertura; }
  public void setHorarioAbertura(Hora horarioAbertura) { this.horarioAbertura = horarioAbertura; }
  public Hora getHorarioFechamento() { return horarioFechamento; }
  public void setHorarioFechamento(Hora horarioFechamento) { this.horarioFechamento = horarioFechamento; }
  public Data getDataAbertura() { return dataAbertura; }
  public void setDataAbertura(Data dataAbertura) { this.dataAbertura = dataAbertura; }
  public boolean isAberto() { return aberto; }
  public void setAberto(boolean aberto) { this.aberto = aberto; }
  
  // conta quantos cifroes p saber o nivel de preco
  public static int pegarFaixa_Preco(String s){
    int cont = 0;   
    for(int i = 0; i < s.length(); i++){ 
      if(s.charAt(i) == '$') cont++; 
    }
    return cont; 
  }

  // transforma a linha do csv em objeto
  public static Restaurante parseRestaurante(String s){
    Scanner sc = new Scanner(s);
    sc.useDelimiter(","); // corta na virgula
    
    int id = sc.nextInt();
    String nome = sc.next();
    String cidade = sc.next();
    int capacidade = sc.nextInt();
    
    String strAvaliacao = sc.next(); 
    double avaliacao = Double.parseDouble(strAvaliacao); 
    
    String tpCozinha = sc.next();  
    int faixa_preco = pegarFaixa_Preco(sc.next());
    String horarios = sc.next();
    
    // scanner p cuidar dos horarios com traco
    Scanner scHoras = new Scanner(horarios); 
    scHoras.useDelimiter("-"); 
    Hora horaAbertura = Hora.parseHora(scHoras.next()); 
    Hora horaFechamento = Hora.parseHora(scHoras.next()); 
    scHoras.close(); 
    
    Data dataAbertura = Data.parseData(sc.next()); 
    String abertoStr = sc.next(); 
    boolean aberto = false;
    if(abertoStr.compareTo("true") == 0) aberto = true; 

    sc.close(); 

    // separa os tipos de cozinha pelo ponto e virgula
    String[] aux = new String[10]; 
    int cout = 0; 
    Scanner scTipos = new Scanner(tpCozinha); 
    scTipos.useDelimiter(";"); 
    
    while(scTipos.hasNext()){ 
      String palavra = scTipos.next(); 
      if(palavra.length() > 0){ 
        aux[cout] = palavra; 
        cout++; 
      }
    }
    scTipos.close(); 
  
    // joga pro vetor com o tamanho certo
    String[] tipoCozinha = new String[cout]; 
    for(int i = 0; i < cout; i++){ 
      tipoCozinha[i] = aux[i]; 
    }
    
    return new Restaurante(id, nome, cidade, capacidade, avaliacao, tipoCozinha, 
        faixa_preco ,horaAbertura, horaFechamento, dataAbertura, aberto); 
  }

  // formata p o padrao que o verde exige
  public String formatar(){
    String strCozinhas = ""; 
    for(int i = 0; i < tiposCozinha.length; i++){ 
      strCozinhas += tiposCozinha[i]; 
      if(i < tiposCozinha.length - 1){ 
        strCozinhas += ","; 
      }
    }
    
    String faixa_p = ""; 
    for(int i = 0; i < this.faixa_preco; i++){
      faixa_p += '$'; 
    }
    
    String strAvaliacao = this.avaliacao + ""; 
                      
    String formatado = String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %s ## %s-%s ## %s ## %b]", 
        idRestaurante, nome, cidade, capacidade, strAvaliacao, strCozinhas, 
        faixa_p, horarioAbertura.formatar(), horarioFechamento.formatar(), 
        dataAbertura.formatar(), aberto); 
        
    return formatado; 
  }
}

// gerencia a base de dados vinda do csv
class ColecaoRestaurantes{
  private int tamanho;
  private Restaurante[] restaurantes;
  
  public ColecaoRestaurantes(int tamanho){
    this.tamanho = tamanho; 
    this.restaurantes = new Restaurante[tamanho]; 
  }

  // le o csv e preenche o vetor
  public void lerCsv(String path) throws Exception{ 
    File arquivo = new File(path); 
    Scanner sc = new Scanner(arquivo); 
    
    if(sc.hasNextLine()) sc.nextLine(); // pula cabecalho

    int i = 0; 
    while(sc.hasNextLine()){ 
      String linha = sc.nextLine(); 
      restaurantes[i] = Restaurante.parseRestaurante(linha); 
      i++; 
    }
    sc.close();
  }

  // prepara a colecao contando as linhas primeiro
  public static ColecaoRestaurantes lerCsv() throws Exception{
    File arquivo = new File("/tmp/restaurantes.csv"); 
    Scanner sc = new Scanner(arquivo); 

    int tam = 0; 
    while(sc.hasNext()){ 
      sc.nextLine(); 
      tam++; 
    }
    sc.close();
    
    ColecaoRestaurantes novaCol = new ColecaoRestaurantes(tam - 1); 
    novaCol.lerCsv("/tmp/restaurantes.csv"); 
    
    return novaCol; 
  }

  // busca o restaurante pelo id
  public Restaurante buscarPorId(int id){
    for(int i = 0; i < tamanho; i++){ 
      if(restaurantes[i].getIdRestaurante() == id){ 
        return restaurantes[i]; 
      }
    }
    return null; 
  }
}

// nozinho duplo: tem ponteiro p frente (prox) e p tras (ant)
class CelulaDupla {
    public Restaurante elemento;
    public CelulaDupla ant;
    public CelulaDupla prox;

    // construtor completo
    public CelulaDupla(Restaurante elemento) {
        this.elemento = elemento;
        this.ant = null;
        this.prox = null;
    }

    // no cabeca vazio
    public CelulaDupla() {
        this.elemento = null;
        this.ant = null;
        this.prox = null;
    }
}

class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;
    private int tamanho; // p nao ter q percorrer tudo p saber o tam

    public ListaDupla() {
        primeiro = new CelulaDupla(); // no cabeca dummy
        ultimo = primeiro;
        tamanho = 0;
    }

    // --- FUNCOES DE INSERCAO ---

    // coloca no comeco logo dps do cabeca
    public void inserirInicio(Restaurante x) {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp; 
        }
        tmp = null;
        tamanho++;
    }

    // coloca no final da lista
    public void inserirFim(Restaurante x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo; 
        ultimo = ultimo.prox; 
        tamanho++;
    }

    // insere em uma posicao qualquer da lista
    public void inserir(Restaurante x, int pos) throws Exception {
        if (pos < 0 || pos > tamanho) throw new Exception("Posicao invalida!");
        else if (pos == 0) inserirInicio(x);
        else if (pos == tamanho) inserirFim(x);
        else {
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);
            
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp;
            tmp.prox.ant = tmp; // liga os 4 ponteiros
            
            tmp = i = null;
            tamanho++;
        }
    }

    // --- FUNCOES DE REMOCAO ---

    // remove o primeiro elemento real
    public Restaurante removerInicio() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia!");
        
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox; 
        Restaurante resp = primeiro.elemento; 
        
        tmp.prox = primeiro.ant = null;
        tmp = null;
        tamanho--;
        return resp;
    }

    // remove o ultimo (muito facil na dupla pq tem o ant)
    public Restaurante removerFim() throws Exception {
        if (primeiro == ultimo) throw new Exception("Lista vazia!");
        
        Restaurante resp = ultimo.elemento;
        ultimo = ultimo.ant; 
        ultimo.prox.ant = null;
        ultimo.prox = null; 
        tamanho--;
        return resp;
    }

    // remove de uma posicao especifica
    public Restaurante remover(int pos) throws Exception {
        if (primeiro == ultimo || pos < 0 || pos >= tamanho) throw new Exception("Posicao invalida!");
        else if (pos == 0) return removerInicio();
        else if (pos == tamanho - 1) return removerFim();
        else {
            CelulaDupla i = primeiro.prox;
            for (int j = 0; j < pos; j++, i = i.prox);
            
            i.ant.prox = i.prox; 
            i.prox.ant = i.ant; 
            
            Restaurante resp = i.elemento;
            i.prox = i.ant = null; 
            i = null;
            tamanho--;
            return resp;
        }
    }

    // mostra a lista toda p o verde validar
    public void mostrar() {
        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.formatar());
        }
    }
}

public class questao08 {
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in); 
    ColecaoRestaurantes cr = ColecaoRestaurantes.lerCsv(); // carrega tudo
    
    ListaDupla lista = new ListaDupla(); 
    
    String linha = sc.next(); 
      
    // preenche a lista inicial
    while(linha.compareTo("FIM") != 0 && linha.compareTo("-1") != 0){ 
      int id = Integer.parseInt(linha); 
      Restaurante r = cr.buscarPorId(id); 
      if(r != null){ 
        lista.inserirFim(r); 
      }
      linha = sc.next(); 
    }
    
    // le quantos comandos o verde vai mandar
    if (sc.hasNextInt()) {
        int nOperacoes = sc.nextInt();
        
        // trata os comandos (II, IF, RI, etc)
        for (int i = 0; i < nOperacoes; i++) {
            String comando = sc.next();
            
            try {
                if (comando.equals("II")) {
                    int id = sc.nextInt();
                    Restaurante r = cr.buscarPorId(id);
                    if (r != null) lista.inserirInicio(r);
                } 
                else if (comando.equals("IF")) {
                    int id = sc.nextInt();
                    Restaurante r = cr.buscarPorId(id);
                    if (r != null) lista.inserirFim(r);
                } 
                else if (comando.equals("I*")) {
                    int pos = sc.nextInt();
                    int id = sc.nextInt();
                    Restaurante r = cr.buscarPorId(id);
                    if (r != null) lista.inserir(r, pos);
                } 
                else if (comando.equals("RI")) {
                    Restaurante r = lista.removerInicio();
                    System.out.println("(R)" + r.getNome()); 
                } 
                else if (comando.equals("RF")) {
                    Restaurante r = lista.removerFim();
                    System.out.println("(R)" + r.getNome());
                } 
                else if (comando.equals("R*")) {
                    int pos = sc.nextInt();
                    Restaurante r = lista.remover(pos);
                    System.out.println("(R)" + r.getNome());
                }
            } catch (Exception e) {
                // se der erro so ignora e continua
            }
        }
    }
    
    // mostra a lista final
    lista.mostrar();

    sc.close(); 
  }
}