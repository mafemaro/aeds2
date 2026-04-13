#include <stdio.h>

int letra(char s){
  return ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z'));
}

// compara se a string é "FIM"
int fim(char *str1, char *str2){
  int i = 0;

  // percorre as duas strings
  while(str1[i] != '\0' || str2[i] != '\0'){
    if(str1[i] != str2[i])
      return 0; // se for diferente nao é FIM
    i++;
  }

  return 1; // se chegou aqui é igual
}

// remove o \n do final da string
void tirarN(char *s){
  int i = 0;

  while(s[i] != '\0'){
    if(s[i] == '\n'){
      s[i] = '\0';
      return;
    }
    i++;
  }
}

int vogal(char *s){
  if(*s == '\0')
    return 1;

  if(!letra(*s)) // tem que ser letra
    return 0;

  // se nao for vogal retorna falso
  if(*s != 'a' && *s != 'A' &&
     *s != 'e' && *s != 'E' &&
     *s != 'i' && *s != 'I' &&
     *s != 'o' && *s != 'O' &&
     *s != 'u' && *s != 'U')
    return 0;

  return vogal(s + 1); // chamada recursiva
}

int consoante(char *s){
  if(*s == '\0')
    return 1;

  if(!letra(*s)) // tem que ser letra
    return 0;

  // se for vogal nao pode
  if(*s == 'a' || *s == 'A' ||
     *s == 'e' || *s == 'E' ||
     *s == 'i' || *s == 'I' ||
     *s == 'o' || *s == 'O' ||
     *s == 'u' || *s == 'U')
    return 0;

  return consoante(s + 1);
}

int soNumero(char *s){
  if(*s == '\0')
    return 1;

  if(!(*s >= '0' && *s <= '9')) // se nao for numero falha
    return 0;

  return soNumero(s + 1);
}

int soNumReal(char *s, int sep){
  if(*s == '\0')
    return 1;

  if(*s >= '0' && *s <= '9'){
    // ok numero
  }
  else if(*s == '.' || *s == ','){
    sep++; // conta separador

    if(sep > 1) // mais de um ponto ou virgula
      return 0;
  }
  else{
    return 0; // qualquer outro caractere invalido
  }

  return soNumReal(s + 1, sep);
}

int main(){
  char str1[2000];

  scanf("%[^\n]", str1);
  getchar();

  int x1, x2, x3, x4;

  // enquanto nao for FIM
  while(!fim(str1, "FIM")){

    x1 = vogal(str1);
    x2 = consoante(str1);
    x3 = soNumero(str1);
    x4 = soNumReal(str1, 0);

    // imprime resultados
    if(x1 == 1) printf("SIM ");
    else printf("NAO ");

    if(x2 == 1) printf("SIM ");
    else printf("NAO ");

    if(x3 == 1) printf("SIM ");
    else printf("NAO ");

    if(x4 == 1) printf("SIM");
    else printf("NAO");

    printf("\n");

    scanf("%[^\n]", str1);
    getchar();
  }
}