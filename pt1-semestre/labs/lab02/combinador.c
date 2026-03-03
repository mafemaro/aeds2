#include<stdio.h>
int main(){
  char string1[100], string2[100], stringFinal[100];
  int i = 0, j = 0, f = 0;
   
  scanf("%s", string1);
  scanf("%s", string2);
   
  while (string1[i] != '\0' && string2[j] != '\0'){
    stringFinal[f]= string1[i];
    i++;
    f++;
    stringFinal[f] = string2[j];
    j++;
    f++;
  }
   
  while (string1[i] != '\0'){
    stringFinal[f] = string1[i];
    i++;
    f++;
  }
   
  while (string2[j] != '\0'){
    stringFinal[f] = string2[j];
    j++;
    f++;
  }
   
  stringFinal[f] ='\0';
   
  printf("%s\n", stringFinal);
   
  return 0;
}