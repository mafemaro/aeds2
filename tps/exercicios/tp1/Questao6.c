#include <stdio.h>

int minusculo(char c){ // retorna a letra minuscula correspondente, se for maiuscula, senao retorna o proprio caractere
    if(c >= 'A' && c <= 'Z'){
        c = c + 32;
    }
    return c;
}

int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

int tamanho(char s[]){
    int i = 0;
    while(s[i] != '\0'){ // percorre a string ate o final
        i++;
    }
    return i; // retorna o tamanho da string
}

int anagrama(char s1[], char s2[]){

    int tam1 = tamanho(s1);
    int tam2 = tamanho(s2);

    if(tam1 != tam2) return 0; // se tamanhos diferentes nao pode ser anagrama

    int usado[1000] = {0}; // vetor para marcar letras ja usadas

    for(int i = 0; i < tam1; i++){
        char c1 = minusculo(s1[i]); // pega letra da primeira string
        int achou = 0; // verificador se encontrou correspondente

        for(int j = 0; j < tam2; j++){
            char c2 = minusculo(s2[j]); // pega letra da segunda string
            if(c1 == c2 && usado[j] == 0){ // se for igual e ainda nao foi usado
                usado[j] = 1; // marca como usado
                achou = 1;
                break;
            }
        }

        if(!achou) return 0; // se nao encontrou correspondente nao é anagrama
    }

    return 1; // se passou por tudo é anagrama
}

int main(){

    char str1[1000], str2[1000];

    scanf("%s", str1); // le primeira palavra

    while(!ehFim(str1)){ // enquanto nao for FIM
        scanf("%s", str2); // le segunda palavra
        int test = anagrama(str1, str2); // verifica se é anagrama
        if(test == 1) // se for anagrama
          printf("SIM\n");
        else// se nao for
            printf("NAO\n");

        scanf("%s", str1); // le proxima entrada
    }

    return 0;
}