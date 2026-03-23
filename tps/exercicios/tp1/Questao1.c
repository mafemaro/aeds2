#include<stdio.h>
int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

void cifrado(char linha[], char cifrada[]){
  int i = 0;
  for(i = 0; linha[i] != '\0'; i++){
    cifrada[i] = (char)(linha[i] + 3); // pega a letra do vetor linha do indice i, adiciona 3 na tabela ascii e coloca esse valor no vetor cifrado
  }

  cifrada[i] = '\0';
}

int main(){
  char linha[100];
  char cifrada[100];

  scanf(" %[^\n]", linha);
  
  while(!ehFim(linha)){ // diferente de fim
    cifrado(linha, cifrada);

    printf("%s\n", cifrada);

    scanf(" %[^\n]", linha);
  }

}