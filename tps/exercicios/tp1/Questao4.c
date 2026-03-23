#include<stdio.h>

int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

void invertido(char linha[], char invertida[]){
  int tamanho = 0;

  while(linha[tamanho] != '\0'){ // calculando tam da str linha
    tamanho++;
  }
  tamanho--;

  int i = 0;
  for(i = 0; tamanho >= 0; i++, tamanho--){ // for que pega o comeco da str inv e o final do tam da linha
    invertida[i] = linha[tamanho];
  }

  invertida[i] = '\0'; // final da string
}

int main(){
  char linha[100];
  char invertida[100];

  scanf(" %[^\n]", linha);

  while(ehFim(linha) == 0){
    invertido(linha, invertida); // passa o vetor pra ser preenchido

    printf("%s\n", invertida); // quebra de linha importante

    scanf(" %[^\n]", linha);
  }
}