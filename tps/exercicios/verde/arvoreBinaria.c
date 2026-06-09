#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// estrutura do no da arvore
typedef struct No {
    int valor;
    struct No *esq, *dir;
} No;

// insere um novo valor na arvore
No* inserir(No* raiz, int valor) {
    if (raiz == NULL) {
        No* novo = (No*)malloc(sizeof(No));
        novo->valor = valor;
        novo->esq = novo->dir = NULL;
        return novo;
    }
    
    if (valor < raiz->valor) {
        raiz->esq = inserir(raiz->esq, valor);
    } else if (valor > raiz->valor) {
        raiz->dir = inserir(raiz->dir, valor);
    }
    
    // se for igual, simplesmente ignora para nao duplicar
    return raiz;
}

// pesquisa o valor e imprime o caminho percorrido
void pesquisar(No* raiz, int valor) {
    if (raiz == NULL) {
        printf("N\n");
        return;
    }
    
    No* atual = raiz;
    while (atual != NULL) {
        printf("%d ", atual->valor);
        
        if (atual->valor == valor) {
            printf("S\n");
            return;
        } else if (valor < atual->valor) {
            atual = atual->esq;
        } else {
            atual = atual->dir;
        }
    }
    
    // chegou em null (folha) e nao achou
    printf("N\n");
}

// caminhamento pre-ordem
void pre_ordem(No* raiz) {
    if (raiz != NULL) {
        printf("%d ", raiz->valor);
        pre_ordem(raiz->esq);
        pre_ordem(raiz->dir);
    }
}

// caminhamento em-ordem
void em_ordem(No* raiz) {
    if (raiz != NULL) {
        em_ordem(raiz->esq);
        printf("%d ", raiz->valor);
        em_ordem(raiz->dir);
    }
}

// caminhamento pos-ordem
void pos_ordem(No* raiz) {
    if (raiz != NULL) {
        pos_ordem(raiz->esq);
        pos_ordem(raiz->dir);
        printf("%d ", raiz->valor);
    }
}

int main() {
    char comando[10];
    int valor;
    No* arvore = NULL;

    // le ate o final da entrada (EOF)
    while (scanf("%s", comando) == 1) {
        if (strcmp(comando, "I") == 0) {
            scanf("%d", &valor);
            arvore = inserir(arvore, valor);
            
        } else if (strcmp(comando, "P") == 0) {
            scanf("%d", &valor);
            pesquisar(arvore, valor);
            
        } else if (strcmp(comando, "PRE") == 0) {
            if (arvore == NULL) {
                printf("V\n");
            } else {
                pre_ordem(arvore);
                printf("\n");
            }
            
        } else if (strcmp(comando, "POS") == 0) {
            if (arvore == NULL) {
                printf("V\n");
            } else {
                pos_ordem(arvore);
                printf("\n");
            }
            
        } else if (strcmp(comando, "EM") == 0) {
            if (arvore == NULL) {
                printf("V\n");
            } else {
                em_ordem(arvore);
                printf("\n");
            }
        }
    }

    return 0;
}