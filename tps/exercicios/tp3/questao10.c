#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

// structs base
typedef struct Data {
    int dia, mes, ano;
} Data;

typedef struct Hora {
    int hora, minuto;
} Hora;

typedef struct Restaurante {
    int id_restaurante;
    char *nome, *cidade;
    int capacidade;
    double avaliacao;
    char** tipo_cozinha;
    int faixa_preco;
    Hora hora_abertura, hora_fechamento;
    Data data_abertura;
    bool aberto;
} Restaurante;

// funcoes de suporte e parse
Data parse_data(char *s) {
    Data d;
    sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia);
    return d;
}

Hora parse_hora(char *s) {
    Hora h;
    sscanf(s, "%d:%d", &h.hora, &h.minuto);
    return h;
}

Restaurante* parse_restaurante(char *s) {
    Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante));
    char h_a[10], h_f[10], d_a[20], nome[100], cidade[100], preco[10], tipo[100], aberto[15];

    // parse da linha csv
    sscanf(s, "%d,%[^,],%[^,],%d,%lf,%[^,],%[^,],%[^-]-%[^,],%[^,],%[^\n\r]",
           &r->id_restaurante, nome, cidade, &r->capacidade, &r->avaliacao, 
           tipo, preco, h_a, h_f, d_a, aberto);

    r->aberto = (strcmp(aberto, "true") == 0);
    r->hora_abertura = parse_hora(h_a);
    r->hora_fechamento = parse_hora(h_f);
    r->data_abertura = parse_data(d_a);
    r->nome = strdup(nome);
    r->cidade = strdup(cidade);
    r->faixa_preco = strlen(preco);

    // trata os tipos de cozinha (troca ; por ,)
    for(int i = 0; tipo[i] != '\0'; i++) if(tipo[i] == ';') tipo[i] = ',';
    r->tipo_cozinha = (char**)malloc(sizeof(char*));
    r->tipo_cozinha[0] = strdup(tipo);

    return r;
}

void formatar_restaurante(Restaurante* r, char* buffer) {
    char h_a[6], h_f[6], d_a[11], f_p[10];
    sprintf(h_a, "%02d:%02d", r->hora_abertura.hora, r->hora_abertura.minuto);
    sprintf(h_f, "%02d:%02d", r->hora_fechamento.hora, r->hora_fechamento.minuto);
    sprintf(d_a, "%02d/%02d/%04d", r->data_abertura.dia, r->data_abertura.mes, r->data_abertura.ano);
    
    int i;
    for(i = 0; i < r->faixa_preco; i++) f_p[i] = '$';
    f_p[i] = '\0';

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]",
            r->id_restaurante, r->nome, r->cidade, r->capacidade, r->avaliacao,
            r->tipo_cozinha[0], f_p, h_a, h_f, d_a, r->aberto ? "true" : "false");
}

// lista encadeada e algoritmo de selecao
typedef struct Celula {
    Restaurante* restaurante;
    struct Celula* prox;
} Celula;

typedef struct Lista {
    Celula *primeiro, *ultimo;
} Lista;

void iniciar(Lista* l) {
    l->primeiro = (Celula*)malloc(sizeof(Celula));
    l->primeiro->prox = NULL;
    l->ultimo = l->primeiro;
}

void inserirFim(Lista* l, Restaurante* r) {
    l->ultimo->prox = (Celula*)malloc(sizeof(Celula));
    l->ultimo = l->ultimo->prox;
    l->ultimo->restaurante = r;
    l->ultimo->prox = NULL;
}

int comparacoes = 0, movimentacoes = 0;

void selecaoFlexivel(Lista* l) {
    for (Celula* i = l->primeiro->prox; i != NULL && i->prox != NULL; i = i->prox) {
        Celula* menor = i;
        for (Celula* j = i->prox; j != NULL; j = j->prox) {
            comparacoes++;
            if (strcmp(j->restaurante->nome, menor->restaurante->nome) < 0) {
                menor = j;
            }
        }
        
        if (menor != i) {
            // troca os ponteiros dos restaurantes entre as celulas
            Restaurante* temp = i->restaurante;
            i->restaurante = menor->restaurante;
            menor->restaurante = temp;
            movimentacoes += 3;
        }
    }
}

int main() {
    // carrega os restaurantes da base
    Restaurante* ds[8192];
    int tam_ds = 0;
    
    FILE* arq = fopen("/tmp/restaurantes.csv", "r");
    if (!arq) return 1;

    char linha[1024];
    fgets(linha, 1024, arq); // pula cabecalho

    while (fgets(linha, 1024, arq)) {
        linha[strcspn(linha, "\r\n")] = 0; // limpa quebras de linha
        if (strlen(linha) > 0) {
            ds[tam_ds++] = parse_restaurante(linha);
        }
    }
    fclose(arq);

    Lista lista;
    iniciar(&lista);

    // le ids da entrada padrao
    char entrada[100];
    while (scanf("%s", entrada) == 1 && strcmp(entrada, "FIM") != 0 && strcmp(entrada, "-1") != 0) {
        int id = atoi(entrada);
        for (int i = 0; i < tam_ds; i++) {
            if (ds[i]->id_restaurante == id) {
                inserirFim(&lista, ds[i]);
                break;
            }
        }
    }

    // ordenacao e tempo
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    
    selecaoFlexivel(&lista);
    
    clock_gettime(CLOCK_MONOTONIC, &end);
    double tempo = (end.tv_sec - start.tv_sec) * 1000.0 + (end.tv_nsec - start.tv_nsec) / 1000000.0;

    // log de desempenho
    FILE* log = fopen("904065_selecaoListaFlexivel.txt", "w");
    fprintf(log, "904065\t%d\t%d\t%.2lf", comparacoes, movimentacoes, tempo);
    fclose(log);

    // impressao final COM A SETINHA
    for (Celula* i = lista.primeiro->prox; i != NULL; i = i->prox) {
        char buf[1024];
        formatar_restaurante(i->restaurante, buf);
        printf("%s\n", buf);
    }

    return 0;
}