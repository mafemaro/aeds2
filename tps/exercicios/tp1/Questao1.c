#include<stdio.h>
int main(){
  char linha[100];
  char resp[100];
  
  scanf("%[^\n]", linha);
  for(int i = 0; i < linha[i] != '\0'; i++){
    resp[i] = (char)(linha[i] + 3);
  }

  printf("\nLinha modificada: %s", resp);
}