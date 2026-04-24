// QUESTÃO 3
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h> 

// criei todas as structs que preciso pras datas e horas
typedef struct Data{
  int dia;
  int mes;
  int ano;
}Data;

typedef struct Hora{
  int hora;
  int minuto;
}Hora;

// struct principal do restaurante com os ponteiros pras strings
typedef struct Restaurante{
  int id_restaurante;
  char* nome;
  char* cidade;
  int capacidade;
  double avaliacao;
  char** tipo_cozinha;
  int faixa_preco;
  Hora hora_abertura;
  Hora hora_fechamento;
  Data data_abertura;
  bool aberto;
}Restaurante;

typedef struct ColecaoRestaurante{
  int tamanho;
  Restaurante* restaurante;
}Colecao_Restaurante;

// le a string de data e converte na struct
Data parse_data(char *s){
  Data d;
  sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia); // separa tudo pegando os numeros no meio dos tracos
  return d; // retorna a struct preenchida
}

// pega a struct de data e devolve formatada na string de buffer
void formatar_data(Data* data, char* buffer){
  sprintf(buffer,"%02d/%02d/%04d", data->dia, data->mes, data->ano); // joga tudo formatadinho dentro do buffer
}

// le a string de hora e converte na struct
Hora parse_hora(char *s){
  Hora h;
  sscanf(s,"%d:%d", &h.hora, &h.minuto); // separa onde tem os dois pontos
  return h; 
}

// pega a struct de hora e devolve formatada no buffer
void formatar_hora(Hora* hora, char* buffer){
  sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto); // formata com dois digitos e poe no buffer
}

// funcao pra limpar a memoria q a gente aloca manualmente
void liberar_restaurante(Restaurante* r) { 
  free(r->nome);
  free(r->cidade);
  free(r->tipo_cozinha[0]);
  free(r->tipo_cozinha);
}

// converte a linha csv inteira em um ponteiro pra restaurante
Restaurante* parse_restaurante(char *s){
  Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante)); // aloca na memoria espaco pro restaurante
  if(r == NULL){
    printf("erro ao criar");
    return NULL;
  }
  
  // cria arrays de char temporarios pra ler os dados da string gigante
  char hora_a[6], hora_f[6], data_a[11], nome[100], cidade[100], preco[10], tipo[40], aberto[10]; 

  // le a string toda separando pelas virgulas e ignorando as virgulas ate a proxima
  sscanf(s, "%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%[^-]-%[^,],%[^,],%[^\n]",
      &r->id_restaurante, nome, cidade, &r->capacidade,
      &r->avaliacao, tipo, preco, hora_a, hora_f,
      data_a, aberto);
  
  for(int i = 0; aberto[i] != '\0'; i++){ // vai limpando a string tirando quebras de linha lixo
    if(aberto[i] == '\r' || aberto[i] == '\n' || aberto[i] == ' ')
      aberto[i] = '\0'; // poe final de string onde era lixo
  }
  
  if(strcmp(aberto, "true") == 0) r->aberto = true; // se o texto for true a variavel fica true
  else r->aberto = false; // senao fica false
  
  r->hora_abertura = parse_hora(hora_a); // chama as funcoes q criamos ali em cima
  r->hora_fechamento = parse_hora(hora_f); 
  r->data_abertura = parse_data(data_a);  

  int tam = 0; 
  while(nome[tam] != '\0') tam++; // vai ate o final pra descobrir o tamanho exato da string
  r->nome = (char*)malloc((tam + 1) * sizeof(char)); // aloca na memoria o tamanho certinho mais o barra zero
  sprintf(r->nome,"%s", nome); // copia o nome temporario pro alocado
  
  tam = 0; 
  while(cidade[tam] != '\0') tam++; 
  r->cidade = (char*)malloc((tam + 1) * sizeof(char)); 
  sprintf(r->cidade,"%s", cidade); 

  tam = 0; 
  while(preco[tam] != '\0') tam++; // o tamanho do preco em string e a propria faixa preco
  r->faixa_preco = tam; // salva como inteiro

  tam = 0; 
  while(tipo[tam] != '\0') tam++; 
  for(int i = 0; tipo[i] != '\0'; i++){
    if(tipo[i] == ';') tipo[i] = ','; // passa pela string trocando os pontos e virgulas por virgulas normais
  }

  r->tipo_cozinha = (char**)malloc(1 * sizeof(char*)); // aloca os ponteiros duplos pras cozinhas
  r->tipo_cozinha[0] = (char*)malloc((tam + 1) * sizeof(char)); 
  sprintf(r->tipo_cozinha[0],"%s",tipo); // joga o tipo inteiro la pra dentro

  return r; // retorna a struct prontinha
}

// pega as informacoes do restaurante e monta uma unica string pro print
void formatar_restaurante(Restaurante* restaurante, char* buffer){
  char hora_fechamento[7], hora_abertura[7], data_abertura[12], str_aberto[6]; 

  formatar_hora(&restaurante->hora_abertura, hora_abertura); // passa os temporarios pra formatar
  formatar_hora(&restaurante->hora_fechamento, hora_fechamento); 
  formatar_data(&restaurante->data_abertura, data_abertura); 

  char f_preco[5]; 
  int i; 
  for(i = 0; i < restaurante->faixa_preco; i++){ // vai ate o numero da faixa preco
    f_preco[i] = '$'; // e bota um cifrao em cada posicao
  }
  f_preco[i] = '\0'; // finaliza a string de cifroes

  if(restaurante->aberto == true) { // passa o booleano de volta pra string pra imprimir
    sprintf(str_aberto, "true"); 
  }else{
    sprintf(str_aberto, "false"); 
  }
  
  // monta tudo no padrao de print dentro do buffer usando o formato do verde
  sprintf(buffer,"[%d ## %s ## %s ## %d ## %.1lf ## [%s] ## %s ## %s-%s ## %s ## %s]",
    restaurante->id_restaurante, restaurante->nome, restaurante->cidade,
    restaurante->capacidade, restaurante->avaliacao, restaurante->tipo_cozinha[0],
    f_preco, hora_abertura, hora_fechamento, data_abertura, str_aberto);
}

// vai percorrendo o csv linha por linha salvando os ponteiros
void ler_csv_colecao(Colecao_Restaurante* colecao, char* path){
  FILE *arq = fopen(path, "r"); // abre em modo de leitura
  if(arq == NULL) return; 
  
  char linha[200]; 
  fgets(linha, sizeof(linha), arq); // pega a primeira linha e descarta o cabecalho
  
  int i = 0; 
  while(fgets(linha, sizeof(linha), arq) != NULL){ // le linha a linha ate nao ter mais nada
    Restaurante* aux = parse_restaurante(linha); // manda pro parse fazer a magica de conversao
    colecao->restaurante[i] = *aux; // pega o objeto que voltou e coloca na colecao original
    i++;
    free(aux); // limpa o temporario pra nao estourar a memoria do c
  }
  fclose(arq); // fecha o arquivo lido
}

// descobre o tamanho maximo do arquivo e cria a colecao base
Colecao_Restaurante* ler_csv(){
  FILE *arq = fopen("/tmp/restaurantes.csv", "r"); 
  if(arq == NULL) return NULL; 

  int tam = 0; 
  char linha[200];
  while(fgets(linha, sizeof(linha), arq) != NULL){ // desce o arquivo todo contando as linhas
    tam++; 
  }
  fclose(arq); 

  Colecao_Restaurante* novaCole = (Colecao_Restaurante*) malloc(sizeof(Colecao_Restaurante)); 
  novaCole->tamanho = tam - 1; // tira a primeira q era cabecalho
  novaCole->restaurante = (Restaurante*)malloc((tam - 1) * sizeof(Restaurante)); // cria o vetor de structs gigante
  
  ler_csv_colecao(novaCole,"/tmp/restaurantes.csv"); // vai de novo agora preenchendo o vetor alocado

  return novaCole; // devolve a struct q guarda o vetor
}

// metodo pra procurar no arrayzao qual posicao tem o id
int buscarId(Colecao_Restaurante* colecao, int id_buscado) {
  for (int i = 0; i < colecao->tamanho; i++) { // vai do primeiro ate o ultimo do vetor
    if (colecao->restaurante[i].id_restaurante == id_buscado) { // confere id a id
      return i; // retorna onde achou
    }
  }
  return -1; // se chegou aqui nao achou na base
}

// logica caseira pra transformar o input num int
int transformarInt(char *s){
  int qtdCaracteres;
  // descobre o tamanho pulando as quebras de linha invisiveis
  for(qtdCaracteres=0; s[qtdCaracteres] != '\0' && s[qtdCaracteres] != '\n' && s[qtdCaracteres] != '\r'; qtdCaracteres++);

  int contador = 1;
  int resposta = 0;
  for(int i=qtdCaracteres-1; i>=0; i--) { // vai do fim pro inicio lendo
    resposta += (s[i] - '0') * contador; // pega o valor e multiplica pela casa de dez correspondente
    contador *= 10; // escala a casa decimal pra proxima
  }
  return resposta; // devolve o numero ja int
}

int num_comparacoes = 0; // cria var global pra guardar dados pro log
int num_movimentacoes = 0;

void ordenar_por_selecao(Colecao_Restaurante* arrayOrdenacao) {
  for (int i = 0; i < (arrayOrdenacao->tamanho - 1); i++) { // laco principal de fora
    int menor = i; // assume que o primeiro e o menor
    for (int j = (i + 1); j < arrayOrdenacao->tamanho; j++) { // laco que percorre o resto do array
      num_comparacoes++; // soma comparacao
      
      // se achar um menor troca a posicao
      if (strcmp(arrayOrdenacao->restaurante[menor].nome, arrayOrdenacao->restaurante[j].nome) > 0) {
        menor = j; // pega a posicao do menor
      }
    }
    
    // se achou mesmo alguem menor faz o swap
    if(menor != i){
      Restaurante aux = arrayOrdenacao->restaurante[menor];
      arrayOrdenacao->restaurante[menor] = arrayOrdenacao->restaurante[i];
      arrayOrdenacao->restaurante[i] = aux; // faz a troca das structs inteiras se precisar
      num_movimentacoes += 3; // soma tres nas movimentacoes do log
    }
  }
}

int main() {
  Colecao_Restaurante* cr = ler_csv(); // carrega base de dados toda
  
  Colecao_Restaurante* arrayOrdenacao = (Colecao_Restaurante*) malloc(sizeof(Colecao_Restaurante));
  arrayOrdenacao->tamanho = 0; // comeca no zero
  arrayOrdenacao->restaurante = (Restaurante*)malloc(500 * sizeof(Restaurante)); // aloca um array giga so por seguranca

  char linha[5];
  scanf("%s", linha); // le o console do verde
  while(strcmp(linha, "-1") != 0) { // repete ate digitar -1
    int id = transformarInt(linha); 
    int idBuscado = buscarId(cr, id); 
    
    if(idBuscado != -1) { 
      // achou copia o restaurante inteiro pro novo array q a gente vai ordenar
      arrayOrdenacao->restaurante[arrayOrdenacao->tamanho] = cr->restaurante[idBuscado];
      arrayOrdenacao->tamanho++; // cresce o tamanho
    }
    scanf("%s", linha); 
  }

  clock_t inicio = clock(); // pega a hora antes de comecar a ordenacao
  
  ordenar_por_selecao(arrayOrdenacao); // chama a ordenacao
  
  clock_t fim = clock(); // pega a hora que terminou
  double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC; // faz o calculo do tempo final
  
  FILE *log = fopen("matricula_selecao.txt", "w"); // abre arquivo de texto
  fprintf(log, "SuaMatriculaAqui\t%d\t%d\t%f", num_comparacoes, num_movimentacoes, tempoExecucao); // joga tudo de log la dentro
  fclose(log); // fecha o txt

  for(int i = 0; i < arrayOrdenacao->tamanho; i++){ // vai do inicio ate o final do novo array
    char leitura[300];
    formatar_restaurante(&(arrayOrdenacao->restaurante[i]), leitura); // puxa o formato padrao
    printf("%s\n", leitura); // printa na tela as strings organizadas
  }

  // faxina nas memorias q a gente alocou antes
  for (int i = 0; i < cr->tamanho; i++) {
    liberar_restaurante(&cr->restaurante[i]); 
  }
  free(cr->restaurante); 
  free(cr); 
  
  free(arrayOrdenacao->restaurante);
  free(arrayOrdenacao);

  return 0;
}