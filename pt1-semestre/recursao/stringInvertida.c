#include<stdio.h>
void impInv(char str[], int i){
  if(str[i] == '\0') return;
  impInv(str, i + 1);

  printf("%c", str[i]);
}
int main(){
  char str[100];
  impInv(str, 0);
}