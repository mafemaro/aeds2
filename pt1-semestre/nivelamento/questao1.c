// Faça um método que receba um array de inteiros e um
// número inteiro x e retorne um valor booleano informando se o
// elemento x está contido no array
#include<stdio.h>
#include <stdbool.h>

bool numero(int vet[], int n){
  for(int i = 0; i < 10; i++){
    if(n == vet[i]) return true;
  }
  return false;
}

int main(){
  int n;
  int vet[10];

  scanf("%d", &n);
  for(int i = 0; i < 10; i++){
    scanf("%d", &n);
  }
}