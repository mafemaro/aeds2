#include<stdio.h>
int main(){
  char F[256];

  while(fgets(F, 256, stdin) != NULL){
    for(int i = 0; F[i] != '\0'; i++){
      if(F[i] == '@') F[i] = 'a';
      if(F[i] == '&') F[i] = 'e';
      if(F[i] == '!') F[i] = 'i';
      if(F[i] == '*') F[i] = 'o';
      if(F[i] == '#') F[i] = 'u';
    }

    printf("%s", F);
  }
}