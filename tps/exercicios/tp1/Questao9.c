#include <stdio.h>

int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

// cifra de cesar (+3)
void ciframento(char s[]){
  if(*s == '\0') return; // fim da string
  *s += 3; // soma 3 no caractere atual
  ciframento(s + 1); // chama pro proximo
}

int main(){
  char str[1000];

  scanf("%[^\n]", str); // espaço antes do % pra consumir \n
  getchar();
  while(!ehFim(str)){

    ciframento(str); // aplica cifra
    printf("%s\n", str); // imprime resultado

    scanf("%[^\n]", str); // le proxima linha
    getchar();
  }

  return 0;
}