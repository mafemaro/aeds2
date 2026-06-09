#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

// structs base (as "classes" que voce pediu)
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

// parse manual direto com sscanf (muito mais rapido pro verde)
Restaurante* parse_restaurante(char *s) {
    Restaurante* r = (Restaurante*)malloc(sizeof(Restaurante));
    char h_a[10], h_f[10], d_a[20], nome[100], cidade[100], preco[10], tipo[100], aberto[15];

    // parse da linha csv cortando pelas virgulas
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
    for(i = 0; i < r->faixa_preco; i++) f_p[i] = '$'; // adiciona os cifroes
    f_p[i] = '\0';

    // coloca tudo no padrao chato do verde
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]",
            r->id_restaurante, r->nome, r->cidade, r->capacidade, r->avaliacao,
            r->tipo_cozinha[0], f_p, h_a, h_f, d_a, r->aberto ? "true" : "false");
}

// ==========================================================
// NO E ARVORE BINARIA
// ==========================================================

// no da arvore
typedef struct No {
    Restaurante* restaurante;
    struct No* esq;
    struct No* dir;
} No;

int comparacoes = 0;

// insere na arvore baseando-se no nome
No* inserir(No* i, Restaurante* r) {
    if (i == NULL) {
        No* novo = (No*)malloc(sizeof(No));
        novo->restaurante = r;
        novo->esq = novo->dir = NULL;
        return novo;
    }
    
    int cmp = strcmp(r->nome, i->restaurante->nome);
    if (cmp < 0) {
        i->esq = inserir(i->esq, r);
    } else if (cmp > 0) {
        i->dir = inserir(i->dir, r);
    }
    // se o nome for igual nao faz nada pra nao duplicar
    return i;
}

// pesquisa mostrando o caminho de ponteiros percorridos em uma linha so
void pesquisar(No* raiz, char* nome) {
    printf("raiz");
    No* i = raiz;
    while (i != NULL) {
        comparacoes++;
        int cmp = strcmp(nome, i->restaurante->nome);
        if (cmp == 0) {
            printf(" SIM\n");
            return;
        } else if (cmp < 0) {
            printf(" esq");
            i = i->esq;
        } else {
            printf(" dir");
            i = i->dir;
        }
    }
    printf(" NAO\n");
}

// caminhamento central (em-ordem)
void caminhar_central(No* i) {
    if (i != NULL) {
        caminhar_central(i->esq);
        char buf[1024];
        formatar_restaurante(i->restaurante, buf);
        printf("%s\n", buf);
        caminhar_central(i->dir);
    }
}

int main() {
    // carrega os restaurantes da base
    Restaurante* ds[8192];
    int n = 0;
    
    // le o arquivo todo primeiro
    FILE* arq = fopen("/tmp/restaurantes.csv", "r");
    if (!arq) return 1;

    char linha[1024];
    fgets(linha, 1024, arq); // pula cabecalho

    while (fgets(linha, 1024, arq)) {
        linha[strcspn(linha, "\r\n")] = 0; // limpa quebras de linha
        if (strlen(linha) > 0) {
            ds[n++] = parse_restaurante(linha);
        }
    }
    fclose(arq);

    No* arvore = NULL;

    // PARTE 1: insercao ate o -1 ou FIM
    char entrada[100];
    while (scanf("%s", entrada) == 1 && strcmp(entrada, "FIM") != 0 && strcmp(entrada, "-1") != 0) {
        int id = atoi(entrada);
        for (int i = 0; i < n; i++) {
            if (ds[i]->id_restaurante == id) {
                arvore = inserir(arvore, ds[i]);
                break;
            }
        }
    }

    // limpa sujeira do buffer do scanf
    int c;
    while ((c = getchar()) != '\n' && c != EOF);

    // cronometra as pesquisas
    clock_t inicio_t = clock();

    // PARTE 2: pesquisa nomes ate o FIM
    char nome_buf[512];
    while (fgets(nome_buf, 512, stdin)) {
        nome_buf[strcspn(nome_buf, "\r\n")] = 0;
        if (strlen(nome_buf) == 0) continue;
        if (strcmp(nome_buf, "FIM") == 0) break;
        
        pesquisar(arvore, nome_buf);
    }

    clock_t fim_t = clock();
    double tempo = ((double)(fim_t - inicio_t) / CLOCKS_PER_SEC) * 1000.0;

    // cria o arquivo de log do verde
    FILE* log = fopen("885428_arvore_binaria.txt", "w");
    fprintf(log, "885428\t%d\t%.2lf", comparacoes, tempo);
    fclose(log);

    // PARTE 3: dump da arvore em ordem central
    caminhar_central(arvore);

    return 0;
}