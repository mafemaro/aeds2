import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

// classe p guardar a hora e o minuto
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
    sc.useDelimiter(":"); // corta onde tem dois pontos
    int hora = sc.nextInt(); 
    int minuto = sc.nextInt(); 
    Hora h = new Hora(hora, minuto); 
    sc.close();
    return h; 
  }

  // deixa a hora bonitinha com 0 na frente
  public String formatar(){
    return String.format("%02d:%02d", this.hora, this.minuto); 
  }
}   

// classe p cuidar da data
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
  
  // transforma o texto do csv em data de verdade
  public static Data parseData(String s){ 
    Scanner sc = new Scanner(s);  
    sc.useDelimiter("-"); // separa pelo traco
    int ano = sc.nextInt(); 
    int mes = sc.nextInt(); 
    int dia = sc.nextInt(); 
    sc.close();
    Data data = new Data(ano, mes, dia); 
    return data; 
  }
 
  // formata p dia/mes/ano
  public String formatar(){
    return String.format("%02d/%02d/%04d", this.dia, this.mes, this.ano); 
  }
}
  
// classe principal do restaurante
class Restaurante{
  private int idRestaurante;
  private String nome;
  private String cidade;
  private int capacity;
  private double avaliacao;
  private String[] tiposCozinha;
  private int faixa_preco;
  private Hora horarioAbertura;
  private Hora horarioFechamento;
  private Data dataAbertura;
  private boolean aberto;
  
  // construtor gigante p preencher tudo
  public Restaurante(int idRestaurante, String nome, String cidade, int capacidade, double avaliacao,
      String[] tiposCozinha,int faixa_preco, Hora horarioAbertura, Hora horarioFechamento, Data dataAbertura, boolean aberto) {
    this.idRestaurante = idRestaurante;
    this.nome = nome;
    this.cidade = cidade;
    this.capacity = capacidade;
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
  public int getCapacidade() { return capacity; }
  public void setCapacidade(int capacidade) { this.capacity = capacidade; }
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
  
  // conta quantos cifroes tem na string p saber o preco
  public static int pegarFaixa_Preco(String s){
    int cont = 0;   
    for(int i = 0; i < s.length(); i++){ 
      if(s.charAt(i) == '$') cont++; 
    }
    return cont; 
  }

  // magica p ler a linha do csv e transformar em objeto restaurante
  public static Restaurante parseRestaurante(String s){
    Scanner sc = new Scanner(s);
    sc.useDelimiter(","); // separa pelas virgulas
    
    int id = sc.nextInt();
    String nome = sc.next();
    String cidade = sc.next();
    int capacidade = sc.nextInt();
    
    String strAvaliacao = sc.next(); 
    double avaliacao = Double.parseDouble(strAvaliacao); 
    
    String tpCozinha = sc.next();  
    int faixa_preco = pegarFaixa_Preco(sc.next());
    String horarios = sc.next();
    
    // separa as horas de abrir e fechar que estao com traco
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

    // trata os tipos de cozinha que vem com ponto e virgula
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
  
    // cria o vetor certinho com a quantidade de cozinhas achadas
    String[] tipoCozinha = new String[cout]; 
    for(int i = 0; i < cout; i++){ 
      tipoCozinha[i] = aux[i]; 
    }
    
    return new Restaurante(id, nome, cidade, capacidade, avaliacao, tipoCozinha, 
        faixa_preco ,horaAbertura, horaFechamento, dataAbertura, aberto); 
  }

  // monta a string do restaurante no padrao que o verde quer
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
        idRestaurante, nome, cidade, capacity, strAvaliacao, strCozinhas, 
        faixa_p, horarioAbertura.formatar(), horarioFechamento.formatar(), 
        dataAbertura.formatar(), aberto); 
        
    return formatado; 
  }
}

// gerencia o vetor com todos os restaurantes do csv
class ColecaoRestaurantes{
  private int tamanho;
  private Restaurante[] restaurantes;
  
  public ColecaoRestaurantes(int tamanho){
    this.tamanho = tamanho; 
    this.restaurantes = new Restaurante[tamanho]; 
  }

  // le o arquivo e vai preenchendo o vetor
  public void lerCsv(String path) throws Exception{ 
    File arquivo = new File(path); 
    Scanner sc = new Scanner(arquivo); 
    
    if(sc.hasNextLine()) sc.nextLine(); // pula o cabecalho

    int i = 0; 
    while(sc.hasNextLine()){ 
      String linha = sc.nextLine(); 
      restaurantes[i] = Restaurante.parseRestaurante(linha); 
      i++; 
    }
    sc.close();
  }

  // descobre o tamanho do arquivo p criar a colecao
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

  // busca um restaurante no vetor usando o id
  public Restaurante buscarPorId(int id){
    for(int i = 0; i < tamanho; i++){ 
      if(restaurantes[i].getIdRestaurante() == id){ 
        return restaurantes[i]; 
      }
    }
    return null; 
  }
}

public class questao01 {
    
  // globais p log de comparacao e movimentacao
  public static int comparacoes = 0;
  public static int movimentacoes = 0;

  // algoritmo de selecao que so vai ate o k (no caso k=10)
  public static void selecaoParcial(Restaurante[] array, int n, int k) {
    for (int i = 0; i < k && i < n - 1; i++) { 
      int menor = i;
      for (int j = i + 1; j < n; j++) { 
        comparacoes++;
        // compara por nome (ordem alfabetica)
        if (array[j].getNome().compareTo(array[menor].getNome()) < 0) { 
          menor = j;
        } else if (array[j].getNome().compareTo(array[menor].getNome()) == 0) { 
          // se os nomes forem iguais, desempata pelo id
          comparacoes++;
          if (array[j].getIdRestaurante() < array[menor].getIdRestaurante()) { 
            menor = j;
          }
        }
      }
      // faz a troca de posicoes
      movimentacoes += 3;
      Restaurante temp = array[i];
      array[i] = array[menor];
      array[menor] = temp;
    }
  }

  public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in); 
    ColecaoRestaurantes cr = ColecaoRestaurantes.lerCsv(); // puxa o csv p memoria
    
    Restaurante[] arrayOrdenacao = new Restaurante[1000]; 
    int qtdRestaurantes = 0;
    
    String linha = sc.next(); 
      
    // le os ids ate achar FIM ou -1
    while(linha.compareTo("FIM") != 0 && linha.compareTo("-1") != 0){ 
      int id = Integer.parseInt(linha); 
      Restaurante r = cr.buscarPorId(id); 
      if(r != null){ 
        arrayOrdenacao[qtdRestaurantes] = r; // guarda no vetor p ordenar
        qtdRestaurantes++;
      }
      linha = sc.next(); 
    }
    
    long tempoInicio = System.currentTimeMillis(); 
    
    // chama a selecao parcial p os 10 primeiros
    selecaoParcial(arrayOrdenacao, qtdRestaurantes, 10); 
    
    long tempoFim = System.currentTimeMillis(); 
    long tempoTotal = tempoFim - tempoInicio;
    
    // printa o array todo p o verde conferir as trocas
    for(int i = 0; i < qtdRestaurantes; i++){ 
      System.out.println(arrayOrdenacao[i].formatar());
    }

    // gera o txt de log com a matricula
    String matricula = "885428";
    File log = new File(matricula + "_selecao_parcial.txt");
    FileWriter writer = new FileWriter(log);
    writer.write(matricula + "\t" + comparacoes + "\t" + movimentacoes + "\t" + tempoTotal);
    writer.close();
    
    sc.close(); 
  }
}