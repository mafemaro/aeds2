import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

class Hora{
  private int hora;
  private int minuto;
  
  // construtor pra criar a hora passando os valores
  public Hora(int hora, int minuto){
    this.hora = hora;
    this.minuto = minuto;
  }
  
  // gets e sets pra acessar e mudar os valores
  public int getHora(){ return hora; }
  public int getMinuto(){ return minuto; }
  public void setHora(int hora){ this.hora = hora; }
  public void setMinuto(int minuto){ this.minuto = minuto; }
  
  // funcao pra quebrar a string e montar o objeto hora
  public static Hora parseHora(String s){
    Scanner sc = new Scanner(s); 
    sc.useDelimiter(":"); // separa a string exatamente onde tem os dois pontos
    int hora = sc.nextInt(); // pega a primeira parte como hora
    int minuto = sc.nextInt(); // pega a segunda parte como minuto
    Hora h = new Hora(hora, minuto); // cria o objeto hora
    sc.close();
    return h; // retorna a hora montadinha
  }

  // devolve a hora no formato certo de string
  public String formatar(){
    return String.format("%02d:%02d", this.hora, this.minuto); // deixa com dois digitos e zero na frente se precisar
  }
}   

class Data{
  private int ano;
  private int mes;
  private int dia;
  
  // construtor pra criar a data
  public Data(int ano, int mes, int dia){
    this.ano = ano;
    this.mes = mes;
    this.dia = dia;
  }
  
  // gets e sets
  public int getAno(){ return ano; }
  public int getMes(){ return mes; }
  public int getDia(){ return dia; }
  public void setAno(int ano){ this.ano = ano; }
  public void setMes(int mes){ this.mes = mes; }
  public void setDia(int dia){ this.dia = dia; }
  
  // funcao pra quebrar a string e montar o objeto data
  public static Data parseData(String s){ 
    Scanner sc = new Scanner(s);  
    sc.useDelimiter("-"); // corta a string onde tem traco
    int ano = sc.nextInt(); // o primeiro e o ano
    int mes = sc.nextInt(); // depois o mes
    int dia = sc.nextInt(); // e por ultimo o dia
    sc.close();
    Data data = new Data(ano, mes, dia); // cria o objeto data com os valores
    return data; // retorna a data 
  }
 
  // devolve a data no formato certo
  public String formatar(){
    return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano); // coloca no padrao brasileiro de data
  }
}
  
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
  
  // construtor gigante pra passar tudo de uma vez
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
  
  // gets e sets de todos os atributos
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
  
  // conta os cifroes pra achar a faixa de preco
  public static int pegarFaixa_Preco(String s){
    int cont = 0;   
    for(int i = 0; i < s.length(); i++){ // vai do inicio ate o final da string
      if(s.charAt(i) == '$') cont++; // se achar um cifrao soma 1 no contador
    }
    return cont; // retorna a quantidade de cifroes
  }

  // converte a linha do arquivo no objeto restaurante
  public static Restaurante parseRestaurante(String s){
    Scanner sc = new Scanner(s);
    sc.useDelimiter(","); // fala pro scanner cortar quando achar virgula
    
    int id = sc.nextInt();
    String nome = sc.next();
    String cidade = sc.next();
    int capacidade = sc.nextInt();
    
    String strAvaliacao = sc.next(); // pega a avaliacao como texto primeiro pra n bugar
    double avaliacao = Double.parseDouble(strAvaliacao); // passa a string pra double na mao pra nao dar erro
    
    String tpCozinha = sc.next();  
    int faixa_preco = pegarFaixa_Preco(sc.next());
    String horarios = sc.next();
    
    Scanner scHoras = new Scanner(horarios); // cria um scanner novo so pras horas
    scHoras.useDelimiter("-"); // corta exatamente no traco
    Hora horaAbertura = Hora.parseHora(scHoras.next()); // le a primeira hora
    Hora horaFechamento = Hora.parseHora(scHoras.next()); // le a segunda hora
    scHoras.close(); 
    
    Data dataAbertura = Data.parseData(sc.next()); 
    String abertoStr = sc.next(); // pega a palavra true ou false
    boolean aberto = false;
    if(abertoStr.compareTo("true") == 0) aberto = true; // se a palavra for true muda a variavel pra true

    sc.close(); // fecha scanner principal

    String[] aux = new String[10]; // vetor auxiliar pra guardar as cozinhas temporariamente
    int cout = 0; 
    Scanner scTipos = new Scanner(tpCozinha); 
    scTipos.useDelimiter(";"); // separa as cozinhas pelo ponto e virgula
    
    while(scTipos.hasNext()){ // repete ate acabar as cozinhas
      String palavra = scTipos.next(); 
      if(palavra.length() > 0){ 
        aux[cout] = palavra; // joga a palavra que achou no vetor auxiliar
        cout++; 
      }
    }
    scTipos.close(); 
  
    String[] tipoCozinha = new String[cout]; // cria o vetor oficial com o tamanho certinho
    for(int i = 0; i < cout; i++){ // vai ate a quantidade de cozinhas que achou
      tipoCozinha[i] = aux[i]; // copia do auxiliar pro oficial
    }
    
    return new Restaurante(id, nome, cidade, capacidade, avaliacao, tipoCozinha, 
        faixa_preco ,horaAbertura, horaFechamento, dataAbertura, aberto); // retorna o objeto inteirao
  }

  // monta a string de saida no formato do verde
  public String formatar(){
    String strCozinhas = ""; 
    for(int i = 0; i < tiposCozinha.length; i++){ // passa por todas as cozinhas
      strCozinhas += tiposCozinha[i]; // junta a cozinha na string
      if(i < tiposCozinha.length - 1){ 
        strCozinhas += ","; // bota uma virgula se nao for a ultima cozinha
      }
    }
    
    String faixa_p = ""; 
    for(int i = 0; i < this.faixa_preco; i++){
      faixa_p += '$'; // adiciona um cifrao pra cada numero da faixa
    }
    
    String strAvaliacao = this.avaliacao + ""; // soma com string vazia pra forcar a virar texto
                      
    String formatado = String.format("[%d ## %s ## %s ## %d ## %s ## [%s] ## %s ## %s-%s ## %s ## %b]", 
        idRestaurante, nome, cidade, capacidade, strAvaliacao, strCozinhas, 
        faixa_p, horarioAbertura.formatar(), horarioFechamento.formatar(), 
        dataAbertura.formatar(), aberto); // coloca tudo no padrao de impressao
        
    return formatado; // retorna a string pronta pra printar
  }
}

class ColecaoRestaurantes{
  private int tamanho;
  private Restaurante[] restaurantes;
  
  // inicializa a colecao
  public ColecaoRestaurantes(int tamanho){
    this.tamanho = tamanho; 
    this.restaurantes = new Restaurante[tamanho]; // cria o vetor de restaurantes com o tamanho passado
  }

  // carrega os dados lendo as linhas do arquivo
  public void lerCsv(String path) throws Exception{ 
    File arquivo = new File(path); // abre o arquivo fisico
    Scanner sc = new Scanner(arquivo); 
    
    if(sc.hasNextLine()) sc.nextLine(); // se tiver linha pula a primeira que e o cabecalho

    int i = 0; 
    while(sc.hasNextLine()){ // le ate o final do arquivo todo
      String linha = sc.nextLine(); // pega a linha inteira
      restaurantes[i] = Restaurante.parseRestaurante(linha); // manda a linha pra virar objeto e guarda no vetor
      i++; 
    }
    sc.close();
  }

  // descobre o tamanho do arquivo e chama a leitura
  public static ColecaoRestaurantes lerCsv() throws Exception{
    File arquivo = new File("/tmp/restaurantes.csv"); 
    Scanner sc = new Scanner(arquivo); 

    int tam = 0; 
    while(sc.hasNext()){ // repete ate acabar o arquivo
      sc.nextLine(); 
      tam++; // conta quantas linhas o arquivo tem
    }
    sc.close();
    
    ColecaoRestaurantes novaCol = new ColecaoRestaurantes(tam - 1); // tira um do tamanho que e o cabecalho
    novaCol.lerCsv("/tmp/restaurantes.csv"); // le de verdade agora e preenche
    
    return novaCol; // retorna a colecao cheia
  }

  // procura restaurante pelo id no vetor
  public Restaurante buscarPorId(int id){
    for(int i = 0; i < tamanho; i++){ // vai do inicio ate o fim do vetor
      if(restaurantes[i].getIdRestaurante() == id){ // se o id bater com o que a gente quer
        return restaurantes[i]; // retorna o restaurante inteiro
      }
    }
    return null; // se nao achou nada retorna nulo
  }
}

class ArrayMergesort { 
  private Restaurante[] array;
  private int tamanho;
  public int numComparacoes = 0; 
  public int numMovimentacoes = 0;

  // array separado q a gente usa so na ordenacao
  public ArrayMergesort(int capacidade) {
    array = new Restaurante[capacidade]; 
    tamanho = 0;
  }

  public void inserir(Restaurante r) { 
    array[tamanho] = r; // bota na ultima posicao
    tamanho++;
  }
  
  public void ordenar() {
    mergesort(0, tamanho - 1); // ativa a primeira chamada do mergesort
  }

  // divide os arrays na metade sucessivamente
  private void mergesort(int esq, int dir) {
    if (esq < dir) { // enquanto a esquerda nao passar a direita
      int meio = (esq + dir) / 2; // acha o ponteiro do meio exato
      
      mergesort(esq, meio); // quebra a banda da esquerda
      mergesort(meio + 1, dir); // quebra a banda da direita
      intercalar(esq, meio, dir); // junta tudo no intercalar
    }
  }

  // junta as partes usando validacao limpa sem infinito de gambiarra do github
  private void intercalar(int esq, int meio, int dir) {
    int tam1 = meio - esq + 1; // acha o tamanho certinho do array 1
    int tam2 = dir - meio; // acha o tamanho do array 2

    Restaurante[] met1 = new Restaurante[tam1]; // arr temporario um
    Restaurante[] met2 = new Restaurante[tam2]; // arr temporario dois

    for (int i = 0; i < tam1; i++) {
      met1[i] = array[esq + i]; // transfere os dados da base
      numMovimentacoes++;
    }
    
    for (int j = 0; j < tam2; j++) {
      met2[j] = array[meio + 1 + j]; // transfere os dados pro lado direito
      numMovimentacoes++;
    }

    int i = 0, j = 0, k = esq;
    
    // enquanto nenhum dos dois vetores menores acabar a gente compara
    while (i < tam1 && j < tam2) {
      numComparacoes++; 
      int comparaCidade = met1[i].getCidade().compareTo(met2[j].getCidade());
      
      boolean primeiroVence = false; // cria a flag de quem ganha a comparacao
      
      if (comparaCidade < 0) {
        primeiroVence = true; // se a cidade 1 for menor ela vai primeiro
      } else if (comparaCidade == 0) {
        numComparacoes++; // soma comparacao de desempate
        // aqui foi o erro: o desempate tem que ser alfabetico pelo NOME e nao id
        if (met1[i].getNome().compareTo(met2[j].getNome()) < 0) {
          primeiroVence = true;
        }
      }
      
      if (primeiroVence) {
        array[k] = met1[i]; // puxa do primeiro vetor
        i++;
      } else {
        array[k] = met2[j]; // senao puxa do segundo
        j++;
      }
      
      k++;
      numMovimentacoes++; // contou pq trocou de lugar no array final
    }

    // pega o que sobrou do vetor um e coloca direto
    while (i < tam1) { 
      array[k] = met1[i]; 
      i++; k++; numMovimentacoes++; 
    }
    
    // pega o que sobrou do vetor dois e coloca direto
    while (j < tam2) { 
      array[k] = met2[j]; 
      j++; k++; numMovimentacoes++; 
    }
  }

  public void imprimir() {
    for(int i = 0; i < tamanho; i++){ 
      System.out.println(array[i].formatar()); 
    }
  }
}

public class Mergesort {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in); 
    ColecaoRestaurantes cr = ColecaoRestaurantes.lerCsv(); 
    
    ArrayMergesort arrayOrdenacao = new ArrayMergesort(500); 
    
    String linha = sc.next(); // le o id
      
    while(linha.compareTo("-1") != 0) { // roda enquanto for valido
      int id = Integer.parseInt(linha); 
      Restaurante r = cr.buscarPorId(id); 
      
      if(r != null) { 
        arrayOrdenacao.inserir(r); // insere no array q vai ser ordenado
      }
      linha = sc.next(); 
    }
    
    long inicio = System.currentTimeMillis(); 
    
    arrayOrdenacao.ordenar(); // chama a base do mergesort e ele resolve tudo sozinho
    
    long fim = System.currentTimeMillis(); 
    double tempoFinal = (fim - inicio) / 1000.0; 
    
    arrayOrdenacao.imprimir(); // printa os restaurantes 
    
    FileWriter log = new FileWriter("matricula_mergesort.txt"); // joga no arquivo da logica
    log.write("SuaMatriculaAqui\t" + arrayOrdenacao.numComparacoes + "\t" + arrayOrdenacao.numMovimentacoes + "\t" + tempoFinal); 
    log.close(); 

    sc.close(); 
  }
}