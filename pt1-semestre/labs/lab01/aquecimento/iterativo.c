#include<stdio.h>

// funções
int contarMaiusculas(char string[]) {
  int i = 0;
  int cont = 0;

  while (string[i] != '\0') {
    if (string[i] >= 'A' && string[i] <= 'Z') {
      cont++;
    }
    i++;
  }

  return cont;
}

int ehFim(char string[]) {
  if (string[0] == 'F' &&
    string[1] == 'I' &&
    string[2] == 'M' &&
    (string[3] == '\n' || string[3] == '\0')) {
    return 1;
  }
  return 0;
}

// sem modularizar
int main(){
  char string[100];
  int i, cont;

  fgets(string, 100, stdin);

  while(!(string[0] == 'F' && string[1] == 'I' && string[2] == 'M')){
    cont = 0;
    for(i = 0; string[i] != '\0'; i++){
      if(string[i] >= 'A' && string[i] <= 'Z'){
      cont++;
      }
    }
  }

  printf("%d\n", cont);
  fgets(string, 100, stdin);

  return 0;
}