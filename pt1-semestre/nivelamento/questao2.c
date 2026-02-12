//  Faça um método que receba um array de inteiros e mostre na tela o maior e o menor elementos do array.
#include<stdio.h>
int maior(int vet[]){
  int maior = vet[0];
  for(int i = 1; i < 10; i++){
    if(vet[i] > maior) maior = vet[i];
  }
  return maior;
}

int menor(int vet[]){
  int menor = vet[0];
  for(int i = 1; i < 10; i++){
    if(vet[i] < menor) menor = vet[i];
  }
  return menor;
}

int main(){
  int vet[10];
  for(int i = 0; i < 10; i++){
    scanf("%d", &vet[i]);
  }
  printf("Maior: %d\n", maior(vet));
  printf("Menor: %d\n", menor(vet));
}