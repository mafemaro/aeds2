#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// structs basicas de data e hora
typedef struct Data{
  int dia;
  int mes;
  int ano;
}Data;

typedef struct Hora{
  int hora;
  int minuto;
}Hora;

// struct principal pra guardar os dados do restaurante
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

// funcao pra quebrar a string e transformar em data
Data parse_data(char *s){
  Data d;
  sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia); 
  return d; 
}

// arruma a data pra printar bonito
void formatar_data(Data* data, char* buffer){
  sprintf(buffer,"%02d/%02d/%04d", data->dia, data->mes, data->ano); 
}

// pega o texto e vira hora
Hora parse_hora(char *s){
  Hora h;
  sscanf(s,"%d:%d", &h.hora, &h.minuto); 
  return h; 
}

// deixa a hora com os dois pontos certinho
void formatar_hora(Hora* hora, char* buffer){
  sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto); 
}

// limpa os ponteiros pra nao dar vazamento de memoria
void liberar_restaurante(Restaurante* r) { 
  free(r->nome);
  free(r->cidade);
  free(r->tipo_cozinha[0]);
  free(r->tipo_cozinha);
}

// aqui eh onde a magica acontece, separa tudo do csv
Restaurante* parse_restaurante(char *s){
  Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante)); 
  if(r == NULL) return NULL;
  
  char hora_a[6], hora_f[6], data_a[11], nome[100], cidade[100], preco[10], tipo[40], aberto[10]; 

  sscanf(s, "%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%[^-]-%[^,],%[^,],%[^\n]",
      &r->id_restaurante, nome, cidade, &r->capacidade,
      &r->avaliacao, tipo, preco, hora_a, hora_f,
      data_a, aberto);
  
  // tira o lixo da string (quebra de linha e espacos)
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
  sprintf(r->cidade,"%s", city); 

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

// monta a string gigante no padrao chato do verde
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

// le o arquivo csv e joga os dados pro vetor
void ler_csv_colecao(Colecao_Restaurante* colecao, char* path){
  FILE *arq = fopen(path, "r"); 
  if(arq == NULL) return; 
  
  char linha[200]; 
  fgets(linha, sizeof(linha), arq); 
  
  int i = 0; 
  while(fgets(linha, sizeof(linha), arq) != NULL){ 
    Restaurante* aux = parse_restaurante(linha); 
    colecao->restaurante[i] = *aux; 
    i++;
    free(aux); 
  }
  fclose(arq); 
}

// carrega o csv todo p memoria antes de comecar as operacoes
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

// procura o restaurante pelo id no nosso vetorzao
int buscarId(Colecao_Restaurante* colecao, int id_buscado) {
  for (int i = 0; i < colecao->tamanho; i++) { 
    if (colecao->restaurante[i].id_restaurante == id_buscado) { 
      return i; 
    }
  }
  return -1; 
}

// converte o texto da entrada pra inteiro na mao
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

// nozinho da nossa lista flexivel
typedef struct Celula {
    Restaurante restaurante;
    struct Celula* prox; 
} Celula;

// controle da lista: tem o cabeca, o rabo e o tamanho atual
typedef struct Lista {
    Celula* primeiro;
    Celula* ultimo;
    int tam; // CORRECAO TIMEOUT: Armazenar o tamanho pra nao ter que percorrer a lista toda com O(N) no tamanho()
} Lista;

// prepara a lista criando o no cabeca (dummy)
void iniciar(Lista* l) {
    l->primeiro = (Celula*) malloc(sizeof(Celula)); // nó cabeça
    l->primeiro->prox = NULL;
    l->ultimo = l->primeiro; 
    l->tam = 0;
}

// bota o restaurante logo depois do no cabeca
void inserirInicio(Lista* l, Restaurante r) {
    Celula* tmp = (Celula*) malloc(sizeof(Celula));
    tmp->restaurante = r;
    tmp->prox = l->primeiro->prox; 
    l->primeiro->prox = tmp; 
    
    if (l->primeiro == l->ultimo) {
        l->ultimo = tmp;
    }
    l->tam++;
}

// joga o novo restaurante pro final da lista
void inserirFim(Lista* l, Restaurante r) {
    l->ultimo->prox = (Celula*) malloc(sizeof(Celula));
    l->ultimo = l->ultimo->prox;
    l->ultimo->restaurante = r;
    l->ultimo->prox = NULL;
    l->tam++;
}

// coloca o restaurante em uma posicao especifica (0 ate tam)
void inserirPosicao(Lista* l, Restaurante r, int pos) {
    if (pos < 0 || pos > l->tam) return; 
    
    if (pos == 0) {
        inserirInicio(l, r);
    } else if (pos == l->tam) {
        inserirFim(l, r);
    } else {
        Celula* i = l->primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        
        Celula* tmp = (Celula*) malloc(sizeof(Celula));
        tmp->restaurante = r;
        tmp->prox = i->prox;
        i->prox = tmp;
        l->tam++;
    }
}

// tira o primeiro restaurante da lista
Restaurante removerInicio(Lista* l) {
    if (l->primeiro == l->ultimo) exit(1); 
    
    Celula* tmp = l->primeiro;
    l->primeiro = l->primeiro->prox; 
    Restaurante resp = l->primeiro->restaurante; 
    
    tmp->prox = NULL;
    free(tmp); 
    l->tam--;
    return resp;
}

// remove o ultimo da fila (precisa percorrer a lista ate o penultimo)
Restaurante removerFim(Lista* l) {
    if (l->primeiro == l->ultimo) exit(1); 
    
    Celula* i;
    for (i = l->primeiro; i->prox != l->ultimo; i = i->prox);
    
    Restaurante resp = l->ultimo->restaurante; 
    i->prox = NULL;
    free(l->ultimo); 
    l->ultimo = i; 
    l->tam--;
    return resp;
}

// remove o restaurante de uma posicao especifica
Restaurante removerPosicao(Lista* l, int pos) {
    if (l->primeiro == l->ultimo || pos < 0 || pos >= l->tam) exit(1);
    
    if (pos == 0) return removerInicio(l);
    if (pos == l->tam - 1) return removerFim(l);
    
    Celula* i = l->primeiro;
    for (int j = 0; j < pos; j++, i = i->prox);
    
    Celula* tmp = i->prox; 
    Restaurante resp = tmp->restaurante;
    i->prox = tmp->prox; 
    tmp->prox = NULL;
    free(tmp); 
    l->tam--;
    return resp;
}

// percorre a lista e printa os restaurantes um por um
void mostrar(Lista* l) {
    for (Celula* i = l->primeiro->prox; i != NULL; i = i->prox) {
        char buffer[500];
        formatar_restaurante(&i->restaurante, buffer);
        // CORREÇÃO: O Verde NÃO quer o índice [%d] na frente, apenas o array
        printf("%s\n", buffer); 
    }
}

// gerencia a entrada de dados e chama os comandos
int main(){
    Colecao_Restaurante* cr = ler_csv(); 
    Lista lista;
    iniciar(&lista); 

    char linha[100];
    // le a primeira leva de ids
    while(scanf("%s", linha) == 1 && strcmp(linha, "FIM") != 0 && strcmp(linha, "-1") != 0){ 
        int id = transformarInt(linha); 
        int idBuscado = buscarId(cr, id); 
        if(idBuscado != -1){ 
            inserirFim(&lista, cr->restaurante[idBuscado]); 
        }
    }

    // pega a quantidade de comandos (II, IF, RI, etc)
    int nOperacoes;
    if(scanf("%d", &nOperacoes) == 1) { 
        for (int i = 0; i < nOperacoes; i++) {
            char comando[5];
            scanf("%s", comando);

            if (strcmp(comando, "II") == 0) {
                int id;
                scanf("%d", &id);
                int idx = buscarId(cr, id);
                if(idx != -1) inserirInicio(&lista, cr->restaurante[idx]);
            }
            else if (strcmp(comando, "IF") == 0) {
                int id;
                scanf("%d", &id);
                int idx = buscarId(cr, id);
                if(idx != -1) inserirFim(&lista, cr->restaurante[idx]);
            }
            else if (strcmp(comando, "I*") == 0) {
                int pos, id;
                scanf("%d %d", &pos, &id);
                int idx = buscarId(cr, id);
                if(idx != -1) inserirPosicao(&lista, cr->restaurante[idx], pos);
            }
            else if (strcmp(comando, "RI") == 0) {
                Restaurante removido = removerInicio(&lista);
                printf("(R)%s\n", removido.nome); // imprime sem espaco dps do (R)
            }
            else if (strcmp(comando, "RF") == 0) {
                Restaurante removido = removerFim(&lista);
                printf("(R)%s\n", removido.nome);
            }
            else if (strcmp(comando, "R*") == 0) {
                int pos;
                scanf("%d", &pos);
                Restaurante removido = removerPosicao(&lista, pos);
                printf("(R)%s\n", removido.nome);
            }
        }
    }

    // mostra o estado final da lista
    mostrar(&lista);

    // faxina na memoria antes de fechar
    for (int i = 0; i < cr->tamanho; i++) {
        liberar_restaurante(&cr->restaurante[i]); 
    }
    free(cr->restaurante); 
    free(cr); 
  
    return 0;
}