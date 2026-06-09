#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h> 

// structs basicas pras datas e horas
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

// quebra a string e monta a data
Data parse_data(char *s){
  Data d;
  sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia); 
  return d; 
}

// deixa a data no jeito de printar
void formatar_data(Data* data, char* buffer){
  sprintf(buffer,"%02d/%02d/%04d", data->dia, data->mes, data->ano); 
}

// transforma texto em hora
Hora parse_hora(char *s){
  Hora h;
  sscanf(s,"%d:%d", &h.hora, &h.minuto); 
  return h; 
}

// formata a hora com os dois pontos
void formatar_hora(Hora* hora, char* buffer){
  sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto); 
}

// faxina na memoria pra nao dar leak
void liberar_restaurante(Restaurante* r) { 
  free(r->nome);
  free(r->cidade);
  free(r->tipo_cozinha[0]);
  free(r->tipo_cozinha);
}

// faz o parse completo do restaurante vindo do csv
Restaurante* parse_restaurante(char *s){
  Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante)); 
  if(r == NULL) return NULL;
  
  char hora_a[6], hora_f[6], data_a[11], nome[100], cidade[100], preco[10], tipo[40], aberto[10]; 

  sscanf(s, "%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%[^-]-%[^,],%[^,],%[^\n]",
      &r->id_restaurante, nome, cidade, &r->capacidade,
      &r->avaliacao, tipo, preco, hora_a, hora_f,
      data_a, aberto);
  
  // limpa sujeira de quebra de linha
  for(int i = 0; aberto[i] != '\0'; i++){ 
    if(aberto[i] == '\r' || aberto[i] == '\n' || aberto[i] == ' ')
      aberto[i] = '\0'; 
  }
  
  if(strcmp(aberto, "true") == 0) r->aberto = true; 
  else r->aberto = false; 
  
  r->hora_abertura = parse_hora(hora_a); 
  r->hora_fechamento = parse_hora(hora_f); 
  r->data_abertura = parse_data(data_a);  

  int tam = 0; 
  while(nome[tam] != '\0') tam++; 
  r->nome = (char*)malloc((tam + 1) * sizeof(char)); 
  sprintf(r->nome,"%s", nome); 
  
  tam = 0; 
  while(cidade[tam] != '\0') tam++; 
  r->cidade = (char*)malloc((tam + 1) * sizeof(char)); 
  sprintf(r->cidade,"%s", cidade); 

  tam = 0; 
  while(preco[tam] != '\0') tam++; 
  r->faixa_preco = tam; 

  tam = 0; 
  while(tipo[tam] != '\0') tam++; 
  for(int i = 0; tipo[i] != '\0'; i++){
    if(tipo[i] == ';') tipo[i] = ','; 
  }

  r->tipo_cozinha = (char**)malloc(1 * sizeof(char*)); 
  r->tipo_cozinha[0] = (char*)malloc((tam + 1) * sizeof(char)); 
  sprintf(r->tipo_cozinha[0],"%s",tipo); 

  return r; 
}

// monta a string gigante do restaurante pro verde
void formatar_restaurante(Restaurante* restaurante, char* buffer){
  char hora_fechamento[7], hora_abertura[7], data_abertura[12], str_aberto[6]; 

  formatar_hora(&restaurante->hora_abertura, hora_abertura); 
  formatar_hora(&restaurante->hora_fechamento, hora_fechamento); 
  formatar_data(&restaurante->data_abertura, data_abertura); 

  char f_preco[5]; 
  int i; 
  for(i = 0; i < restaurante->faixa_preco; i++) f_preco[i] = '$'; 
  f_preco[i] = '\0'; 

  if(restaurante->aberto == true) sprintf(str_aberto, "true"); 
  else sprintf(str_aberto, "false"); 
  
  sprintf(buffer,"[%d ## %s ## %s ## %d ## %.1lf ## [%s] ## %s ## %s-%s ## %s ## %s]",
    restaurante->id_restaurante, restaurante->nome, restaurante->cidade,
    restaurante->capacidade, restaurante->avaliacao, restaurante->tipo_cozinha[0],
    f_preco, hora_abertura, hora_fechamento, data_abertura, str_aberto);
}

// le a colecao do csv e joga no vetor
void ler_csv_colecao(Colecao_Restaurante* colecao, char* path){
  FILE *arq = fopen(path, "r"); 
  if(arq == NULL) return; 
  
  char linha[200]; 
  fgets(linha, sizeof(linha), arq); // pula o cabecalho
  
  int i = 0; 
  while(fgets(linha, sizeof(linha), arq) != NULL){ 
    Restaurante* aux = parse_restaurante(linha); 
    colecao->restaurante[i] = *aux; 
    i++;
    free(aux); 
  }
  fclose(arq); 
}

// prepara o terreno carregando o csv todo
Colecao_Restaurante* ler_csv(){
  FILE *arq = fopen("/tmp/restaurantes.csv", "r"); 
  if(arq == NULL) return NULL; 

  int tam = 0; 
  char linha[200];
  while(fgets(linha, sizeof(linha), arq) != NULL) tam++; 
  fclose(arq); 

  Colecao_Restaurante* novaCole = (Colecao_Restaurante*) malloc(sizeof(Colecao_Restaurante)); 
  novaCole->tamanho = tam - 1; 
  novaCole->restaurante = (Restaurante*)malloc((tam - 1) * sizeof(Restaurante)); 
  
  ler_csv_colecao(novaCole,"/tmp/restaurantes.csv"); 

  return novaCole; 
}

// busca o indice pelo id
int buscarId(Colecao_Restaurante* colecao, int id_buscado) {
  for (int i = 0; i < colecao->tamanho; i++) { 
    if (colecao->restaurante[i].id_restaurante == id_buscado) return i; 
  }
  return -1; 
}

// converte string pra int na mao
int transformarInt(char *s){
  int qtdCaracteres;
  for(qtdCaracteres=0; s[qtdCaracteres] != '\0' && s[qtdCaracteres] != '\n' && s[qtdCaracteres] != '\r'; qtdCaracteres++);
  int contador = 1, resposta = 0;
  for(int i=qtdCaracteres-1; i>=0; i--) { 
    resposta += (s[i] - '0') * contador; 
    contador *= 10; 
  }
  return resposta; 
}

int comparacoes = 0;
int movimentacoes = 0;

// troca dois restaurantes de lugar
void swap(Restaurante* a, Restaurante* b) {
    Restaurante temp = *a;
    *a = *b;
    *b = temp;
    movimentacoes += 3;
}

// funcao de comparacao: ordena pela data de abertura
int comparar(Restaurante a, Restaurante b) {
    comparacoes++;
    if (a.data_abertura.ano < b.data_abertura.ano) return -1; 
    if (a.data_abertura.ano > b.data_abertura.ano) return 1;
    
    comparacoes++;
    if (a.data_abertura.mes < b.data_abertura.mes) return -1;
    if (a.data_abertura.mes > b.data_abertura.mes) return 1;
    
    comparacoes++;
    if (a.data_abertura.dia < b.data_abertura.dia) return -1;
    if (a.data_abertura.dia > b.data_abertura.dia) return 1;
    
    // se as datas forem iguais desempata pelo id
    comparacoes++;
    if (a.id_restaurante < b.id_restaurante) return -1;
    if (a.id_restaurante > b.id_restaurante) return 1;
    
    return 0; 
}

// empurra o elemento pra baixo p manter o heap
void reconstruir(Restaurante* array, int tam_heap, int i) {
    int maior = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < tam_heap && comparar(array[esq], array[maior]) > 0) maior = esq;
    if (dir < tam_heap && comparar(array[dir], array[maior]) > 0) maior = dir;

    if (maior != i) {
        swap(&array[i], &array[maior]); 
        reconstruir(array, tam_heap, maior); 
    }
}

// o brabo do heapsort parcial p pegar so o top k
void heapsortParcial(Restaurante* array, int n, int k) {
    // monta o heap inicial com k elementos
    for (int i = k / 2 - 1; i >= 0; i--) reconstruir(array, k, i);

    // testa o resto do vetor contra o topo do heap
    for (int i = k; i < n; i++) {
        if (comparar(array[i], array[0]) < 0) {
            swap(&array[i], &array[0]);
            reconstruir(array, k, 0); 
        }
    }

    // ordena o heap final
    int tam_heap = k;
    for (int i = k - 1; i > 0; i--) {
        swap(&array[0], &array[i]); 
        tam_heap--; 
        reconstruir(array, tam_heap, 0); 
    }
}

int main(){
  Colecao_Restaurante* cr = ler_csv(); 
  Restaurante arrayOrdenacao[1000]; 
  int qtdRestaurantes = 0;
  char linha[100];
  
  scanf("%s", linha); 
  while(strcmp(linha, "-1") != 0 && strcmp(linha, "FIM") != 0){ 
    int id = transformarInt(linha); 
    int idBuscado = buscarId(cr, id); 
    if(idBuscado != -1){ 
      arrayOrdenacao[qtdRestaurantes] = cr->restaurante[idBuscado]; 
      qtdRestaurantes++;
    }
    scanf("%s", linha); 
  }

  clock_t tempo_inicio = clock(); 
  heapsortParcial(arrayOrdenacao, qtdRestaurantes, 10); // k=10 como pede o pdf
  clock_t tempo_fim = clock(); 

  double tempoTotal = ((double)(tempo_fim - tempo_inicio)) / CLOCKS_PER_SEC * 1000.0; 

  // mostra o resultado final
  for(int i = 0; i < qtdRestaurantes; i++){
    char leitura[500]; 
    formatar_restaurante(&arrayOrdenacao[i], leitura); 
    printf("%s\n", leitura); 
  }

  // gera o log chato p nao perder ponto
  FILE *log = fopen("885428_heapsort_parcial.txt", "w");
  fprintf(log, "885428\t%d\t%d\t%.2lf", comparacoes, movimentacoes, tempoTotal); 
  fclose(log);

  // faxina final
  for (int i = 0; i < cr->tamanho; i++) liberar_restaurante(&cr->restaurante[i]); 
  free(cr->restaurante); 
  free(cr); 
  
  return 0;
}