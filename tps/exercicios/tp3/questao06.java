import java.io.File;
import java.util.Scanner;

// classe p guardar a hora e o minuto de abertura/fechamento
class Hora{
  private int hora;
  private int minuto;
  
  // construtor padrao p criar o objeto
  public Hora(int hora, int minuto){
    this.hora = hora;
    this.minuto = minuto;
  }
  
  // gets e sets p acessar/mudar os valores
  public int getHora(){ return hora; }
  public int getMinuto(){ return minuto; }
  public void setHora(int hora){ this.hora = hora; }
  public void setMinuto(int minuto){ this.minuto = minuto; }
  
  // transforma a string do csv em hora de vdd
  public static Hora parseHora(String s){
    Scanner sc = new Scanner(s); 
    sc.useDelimiter(":"); // separa onde tem os dois pontos
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

// classe p cuidar das datas
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
  
  // transforma o texto do arquivo em data real
  public static Data parseData(String s){ 
    Scanner sc = new Scanner(s);  
    sc.useDelimiter("-"); // corta onde tem o traco
    int ano = sc.nextInt(); 
    int mes = sc.nextInt(); 
    int dia = sc.nextInt(); 
    sc.close();
    Data data = new Data(ano, mes, dia); 
    return data; 
  }
 
  // formata p o padrao brasileiro
  public String formatar(){
    return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano); 
  }
}
  
// struct principal p guardar os dados do restaurante
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
  
  // construtor p preencher tudo de uma vez
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
  
  // conta os cifroes p saber o preco
  public static int pegarFaixa_Preco(String s){
    int cont = 0;   
    for(int i = 0; i < s.length(); i++){ 
      if(s.charAt(i) == '$') cont++; 
    }
    return cont; 
  }

  // magica p ler a linha e transformar em objeto
  public static Restaurante parseRestaurante(String s){
    Scanner sc = new Scanner(s);
    sc.useDelimiter(","); // separa por virgula
    
    int id = sc.nextInt();
    String nome = sc.next();
    String cidade = sc.next();
    int capacidade = sc.nextInt();
    
    String strAvaliacao = sc.next(); 
    double avaliacao = Double.parseDouble(strAvaliacao); 
    
    String tpCozinha = sc.next();  
    int faixa_preco = pegarFaixa_Preco(sc.next());
    String horarios = sc.next();
    
    // separa as horas de abrir e fechar
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

    // trata as cozinhas que vem com ponto e virgula
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
  
    // cria o vetor certinho com as cozinhas
    String[] tipoCozinha = new String[cout]; 
    for(int i = 0; i < cout; i++){ 
      tipoCozinha[i] = aux[i]; 
    }
    
    return new Restaurante(id, nome, cidade, capacidade, avaliacao, tipoCozinha, 
        faixa_preco ,horaAbertura, horaFechamento, dataAbertura, aberto); 
  }

  // monta a string p printar no padrao do verde
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

// gerencia todos os dados carregados do csv
class ColecaoRestaurantes{
  private int tamanho;
  private Restaurante[] restaurantes;
  
  public ColecaoRestaurantes(int tamanho){
    this.tamanho = tamanho; 
    this.restaurantes = new Restaurante[tamanho]; 
  }

  // le o csv e preenche o vetorzao
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

  // descobre o tamanho p criar a colecao certinha
  public static ColecaoRestaurantes lerCsv() throws Exception{
    File arquivo = new File("/tmp/restaurantes.csv"); // caminho do csv no linux
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

// no da nossa pilha flexivel
class Celula {
    public Restaurante elemento;
    public Celula prox; // aponta pro de baixo na pilha

    public Celula(Restaurante elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

// implementacao da pilha dinamica
class Pilha {
    private Celula topo; // so precisamos saber quem ta no topo

    public Pilha() {
        topo = null; // comeca vazia
    }

    // PUSH: bota um novo restaurante no topo
    public void inserir(Restaurante x) {
        Celula tmp = new Celula(x);
        tmp.prox = topo; // o novo aponta p o topo antigo
        topo = tmp;      // o topo vira o novo
        tmp = null;      
    }

    // POP: remove o restaurante que ta no topo
    public Restaurante remover() throws Exception {
        if (topo == null) {
            throw new Exception("Erro ao remover: Pilha vazia!");
        }
        Restaurante resp = topo.elemento; 
        Celula tmp = topo;                
        topo = topo.prox; // o topo desce p o de baixo
        tmp.prox = null;                  
        tmp = null;                       
        return resp;
    }

    // mostra a pilha do topo p a base
    public void mostrar() {
        for(Celula i = topo; i != null; i = i.prox) {
            System.out.println(i.elemento.formatar());
        }
    }
}

public class questao06 {
  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in); 
    ColecaoRestaurantes cr = ColecaoRestaurantes.lerCsv(); // carrega os dados
    
    Pilha pilha = new Pilha(); 
    
    String linha = sc.next(); 
      
    // PARTE 1: preenche a pilha com os ids iniciais
    while(linha.compareTo("FIM") != 0 && linha.compareTo("-1") != 0){ 
      int id = Integer.parseInt(linha); 
      Restaurante r = cr.buscarPorId(id); 
      if(r != null){ 
        pilha.inserir(r); // push
      }
      linha = sc.next(); 
    }
    
    // PARTE 2: le a quantidade de comandos extras
    int nOperacoes = sc.nextInt();
    
    // PARTE 3: executa os comandos I ou R
    for(int i = 0; i < nOperacoes; i++) {
        String operacao = sc.next(); 
        
        if(operacao.equals("I")) {
            int id = sc.nextInt();
            Restaurante r = cr.buscarPorId(id);
            if(r != null) {
                pilha.inserir(r); // push de novo
            }
        } 
        else if (operacao.equals("R")) {
            Restaurante removido = pilha.remover(); // pop
            // printa o removido sem espaco depois do (R) como o verde quer
            System.out.println("(R)" + removido.getNome()); 
        }
    }
    
    // PARTE 4: imprime quem sobrou na pilha
    pilha.mostrar();

    sc.close(); 
  }
}