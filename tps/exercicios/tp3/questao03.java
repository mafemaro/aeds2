import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// classe basica p guardar a hora
class Hora{
  private int hora;
  private int minuto;
  
  // construtor padrao p criar a hora
  public Hora(int hora, int minuto){
    this.hora = hora;
    this.minuto = minuto;
  }
  
  // gets e sets p mexer nos atributos
  public int getHora(){ return hora; }
  public int getMinuto(){ return minuto; }
  public void setHora(int hora){ this.hora = hora; }
  public void setMinuto(int minuto){ this.minuto = minuto; }
  
  // quebra a string do csv e monta o objeto hora
  public static Hora parseHora(String s){
    Scanner sc = new Scanner(s); 
    sc.useDelimiter(":"); // corta nos dois pontos
    int hora = sc.nextInt(); 
    int minuto = sc.nextInt(); 
    Hora h = new Hora(hora, minuto); 
    sc.close();
    return h; 
  }

  // devolve a hora bonitinha formatada
  public String formatar(){
    return String.format("%02d:%02d", this.hora, this.minuto); 
  }
}   

// classe p tratar as datas
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
  
  // le o texto com traco e transforma em data
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
 
  // formata p o padrao br
  public String formatar(){
    return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano); 
  }
}
  
// classe que guarda os dados do restaurante
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
  
  // construtor completo com todos os campos
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
  
  // conta os cifroes p saber a faixa de preco
  public static int pegarFaixa_Preco(String s){
    int cont = 0;   
    for(int i = 0; i < s.length(); i++){ 
      if(s.charAt(i) == '$') cont++; 
    }
    return cont; 
  }

  // transforma a linha do csv em um restaurante de verdade
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
    
    // cria um scanner so p tratar os horarios separados por traco
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

    // separa os tipos de cozinha que vem com ponto e virgula
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
  
    // joga pro vetor oficial com tamanho exato
    String[] tipoCozinha = new String[cout]; 
    for(int i = 0; i < cout; i++){ 
      tipoCozinha[i] = aux[i]; 
    }
    
    return new Restaurante(id, nome, cidade, capacidade, avaliacao, tipoCozinha, 
        faixa_preco ,horaAbertura, horaFechamento, dataAbertura, aberto); 
  }

  // monta a string p printar no estilo do verde
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

// gerencia a lista toda de restaurantes do csv
class ColecaoRestaurantes{
  private int tamanho;
  private Restaurante[] restaurantes;
  
  public ColecaoRestaurantes(int tamanho){
    this.tamanho = tamanho; 
    this.restaurantes = new Restaurante[tamanho]; 
  }

  // le o csv e carrega p o vetor
  public void lerCsv(String path) throws Exception{ 
    File arquivo = new File(path); 
    Scanner sc = new Scanner(arquivo); 
    
    if(sc.hasNextLine()) sc.nextLine(); // pula a primeira linha que e lixo

    int i = 0; 
    while(sc.hasNextLine()){ 
      String linha = sc.nextLine(); 
      restaurantes[i] = Restaurante.parseRestaurante(linha); 
      i++; 
    }
    sc.close();
  }

  // descobre o tamanho do arquivo antes de ler p criar o vetor certo
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

  // procura o id no vetorzão
  public Restaurante buscarPorId(int id){
    for(int i = 0; i < tamanho; i++){ 
      if(restaurantes[i].getIdRestaurante() == id){ 
        return restaurantes[i]; 
      }
    }
    return null; 
  }
}

public class questao03 {
    
  // variaveis globais p o log final
  public static int comparacoes = 0;
  public static int movimentacoes = 0;

  // troca dois elementos de lugar e ja conta as movimentacoes
  public static void swap(Restaurante[] array, int i, int j) {
    Restaurante temp = array[i];
    array[i] = array[j];
    array[j] = temp;
    movimentacoes += 3;
  }

  // funcao de comparacao p ordenar
  public static int comparar(Restaurante a, Restaurante b) {
    comparacoes++;
    // ordena pela avaliacao (crescente)
    if (a.getAvaliacao() < b.getAvaliacao()) return -1; 
    if (a.getAvaliacao() > b.getAvaliacao()) return 1;
    
    // se empatar na nota, desempata pelo nome
    comparacoes++;
    return a.getNome().compareTo(b.getNome());
  }

  // o brabo do quicksort parcial (pega so os k menores)
  public static void quicksortParcial(Restaurante[] array, int esq, int dir, int k) {
    int i = esq, j = dir;
    Restaurante pivo = array[(esq + dir) / 2]; // pivo no meio p nao dar ruim
    
    while (i <= j) {
      while (comparar(array[i], pivo) < 0) { 
        i++;
      }
      while (comparar(array[j], pivo) > 0) { 
        j--;
      }
      if (i <= j) { 
        swap(array, i, j);
        i++;
        j--;
      }
    }
    
    // recursao normal p esquerda
    if (esq < j) {
        quicksortParcial(array, esq, j, k);
    }
    // so vai p direita se ainda nao preencheu os k que o verde pediu
    if (i < k && i < dir) {
        quicksortParcial(array, i, dir, k);
    }
  }

  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in); 
    ColecaoRestaurantes cr = ColecaoRestaurantes.lerCsv(); // carrega tudo
    
    Restaurante[] arrayOrdenacao = new Restaurante[1000]; 
    int qtdRestaurantes = 0;
    
    String linha = sc.next(); 
      
    // le ate achar FIM p saber quem vai ser ordenado
    while(linha.compareTo("FIM") != 0 && linha.compareTo("-1") != 0){ 
      int id = Integer.parseInt(linha); 
      Restaurante r = cr.buscarPorId(id); 
      if(r != null){ 
        arrayOrdenacao[qtdRestaurantes] = r; 
        qtdRestaurantes++;
      }
      linha = sc.next(); 
    }
    
    long tempoInicio = System.currentTimeMillis(); 
    
    // manda rodar o quick parcial com k = 10
    quicksortParcial(arrayOrdenacao, 0, qtdRestaurantes - 1, 10);
    
    long tempoFim = System.currentTimeMillis(); 
    long tempoTotal = tempoFim - tempoInicio;
    
    // mostra o array todo p o verde conferir
    for(int i = 0; i < qtdRestaurantes; i++){ 
      System.out.println(arrayOrdenacao[i].formatar());
    }

    // escreve o arquivinho de log chato que o verde pede
    String matricula = "885428"; 
    File log = new File(matricula + "_quicksort_parcial.txt");
    FileWriter writer = new FileWriter(log);
    writer.write(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoTotal);
    writer.close();
    
    sc.close(); 
  }
}