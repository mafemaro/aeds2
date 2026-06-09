#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// structs p datas e horas
typedef struct Data{
  int dia;
  int mes;
  int ano;
}Data;

typedef struct Hora{
  int hora;
  int minuto;
}Hora;

// struct principal do restaurante
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

// quebra a string e transforma em data
Data parse_data(char *s){
  Data d;
  sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia); 
  return d; 
}

// deixa a data no jeito de printar dia/mes/ano
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

// limpa a memoria q a gente aloca manualmente p nao dar leak
void liberar_restaurante(Restaurante* r) { 
  free(r->nome);
  free(r->cidade);
  free(r->tipo_cozinha[0]);
  free(r->tipo_cozinha);
}

// faz o parse completo do restaurante vindo da linha do csv
Restaurante* parse_restaurante(char *s){
  Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante)); 
  if(r == NULL) return NULL;
  
  char hora_a[6], hora_f[6], data_a[11], nome[100], cidade[100], preco[10], tipo[40], aberto[10]; 

  sscanf(s, "%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%[^-]-%[^,],%[^,],%[^\n]",
      &r->id_restaurante, nome, cidade, &r->capacidade,
      &r->avaliacao, tipo, preco, hora_a, hora_f,
      data_a, aberto);
  
  // limpa sujeira de quebra de linha da string
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

// monta a string do restaurante no padrao chato do verde
void formatar_restaurante(Restaurante* restaurante, char* buffer){
  char hora_fechamento[7], hora_abertura[7], data_abertura[12], str_aberto[6]; 

  formatar_hora(&restaurante->hora_abertura, hora_abertura); 
  formatar_hora(&restaurante->hora_fechamento, hora_fechamento); 
  formatar_data(&restaurante->data_abertura, data_abertura); 

  char f_preco[5]; 
  int i; 
  for(i = 0; i < restaurante->faixa_preco; i++){ 
    f_preco[i] = '$'; 
  }
  f_preco[i] = '\0'; 

  if(restaurante->aberto == true) { 
    sprintf(str_aberto, "true"); 
  }else{
    sprintf(str_aberto, "false"); 
  }
  
  sprintf(buffer,"[%d ## %s ## %s ## %d ## %.1lf ## [%s] ## %s ## %s-%s ## %s ## %s]",
    restaurante->id_restaurante, restaurante->nome, restaurante->cidade,
    restaurante->capacidade, restaurante->avaliacao, restaurante->tipo_cozinha[0],
    f_preco, hora_abertura, hora_fechamento, data_abertura, str_aberto);
}

// le a colecao do arquivo csv e poe no vetor
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

// abre o csv e aloca o vetor de restaurantes
Colecao_Restaurante* ler_csv(){
  FILE *arq = fopen("/tmp/restaurantes.csv", "r"); 
  if(arq == NULL) return NULL; 

  int tam = 0; 
  char linha[200];
  while(fgets(linha, sizeof(linha), arq) != NULL){ 
    tam++; 
  }
  fclose(arq); 

  Colecao_Restaurante* novaCole = (Colecao_Restaurante*) malloc(sizeof(Colecao_Restaurante)); 
  novaCole->tamanho = tam - 1; 
  novaCole->restaurante = (Restaurante*)malloc((tam - 1) * sizeof(Restaurante)); 
  
  ler_csv_colecao(novaCole,"/tmp/restaurantes.csv"); 

  return novaCole; 
}

// procura o indice do restaurante pelo id
int buscarId(Colecao_Restaurante* colecao, int id_buscado) {
  for (int i = 0; i < colecao->tamanho; i++) { 
    if (colecao->restaurante[i].id_restaurante == id_buscado) { 
      return i; 
    }
  }
  return -1; 
}

// transforma string em int na mao
int transformarInt(char *s){
  int qtdCaracteres;
  for(qtdCaracteres=0; s[qtdCaracteres] != '\0' && s[qtdCaracteres] != '\n' && s[qtdCaracteres] != '\r'; qtdCaracteres++);

  int contador = 1;
  int resposta = 0;
  for(int i=qtdCaracteres-1; i>=0; i--) { 
    resposta += (s[i] - '0') * contador; 
    contador *= 10; 
  }
  return resposta; 
}

// nozinho da fila
typedef struct Celula {
    Restaurante restaurante;
    struct Celula* prox; 
} Celula;

typedef struct Fila {
    Celula* primeiro;
    Celula* ultimo;
} Fila;

// inicia a fila criando o no cabeca (dummy node)
void iniciar(Fila* f) {
    f->primeiro = (Celula*) malloc(sizeof(Celula));
    f->primeiro->prox = NULL;
    f->ultimo = f->primeiro; // na fila vazia os dois apontam p o cabeca
}

// enfileirar (inserir no fim)
void inserir(Fila* f, Restaurante r) {
    f->ultimo->prox = (Celula*) malloc(sizeof(Celula)); // aloca novo espaco no fim
    f->ultimo = f->ultimo->prox; // arrasta o ponteiro ultimo p la
    f->ultimo->restaurante = r; // guarda o restaurante
    f->ultimo->prox = NULL; // finaliza a fila
}

// desenfileirar (remover do inicio)
Restaurante remover(Fila* f) {
    if (f->primeiro == f->ultimo) exit(1); // erro se a fila tiver vazia
    
    // truque do no cabeca: o elemento q foi removido vira o novo cabeca
    Celula* tmp = f->primeiro;
    f->primeiro = f->primeiro->prox; 
    Restaurante resp = f->primeiro->restaurante; // salva p devolver
    
    tmp->prox = NULL;
    free(tmp); // joga fora o cabeca antigo
    
    return resp;
}

// imprime a fila do primeiro ate o ultimo
void mostrar(Fila* f) {
    // comeca ignorando o no cabeca
    for (Celula* i = f->primeiro->prox; i != NULL; i = i->prox) {
        char buffer[500];
        formatar_restaurante(&i->restaurante, buffer);
        printf("%s\n", buffer); 
    }
}

int main(){
    Colecao_Restaurante* cr = ler_csv(); 
    Fila fila;
    iniciar(&fila); // prepara os ponteiros da fila

    char linha[100];
    
    // parte 1: preenche a fila com os ids iniciais
    while(scanf("%s", linha) == 1 && strcmp(linha, "FIM") != 0 && strcmp(linha, "-1") != 0){ 
        int id = transformarInt(linha); 
        int idBuscado = buscarId(cr, id); 
        if(idBuscado != -1){ 
            inserir(&fila, cr->restaurante[idBuscado]); // entra na fila
        }
    }

    // parte 2: le a quantidade de operacoes de inserir/remover
    int nOperacoes;
    if(scanf("%d", &nOperacoes) == 1) { 
        // parte 3: trata as operacoes
        for (int i = 0; i < nOperacoes; i++) {
            char comando[5];
            scanf("%s", comando);

            // se for inserir (enfileirar)
            if (strcmp(comando, "I") == 0) {
                int id;
                scanf("%d", &id);
                int idx = buscarId(cr, id);
                if(idx != -1) inserir(&fila, cr->restaurante[idx]);
            }
            // se for remover (desenfileirar)
            else if (strcmp(comando, "R") == 0) {
                Restaurante removido = remover(&fila);
                printf("(R)%s\n", removido.nome); // imprime sem espaco depois do (R)
            }
        }
    }

    // parte 4: imprime quem sobrou na fila
    mostrar(&fila);

    // faxina final p limpar a memoria
    for (int i = 0; i < cr->tamanho; i++) {
        liberar_restaurante(&cr->restaurante[i]); 
    }
    free(cr->restaurante); 
    free(cr); 
  
    return 0;
}