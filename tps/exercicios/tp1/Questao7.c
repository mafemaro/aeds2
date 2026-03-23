#include<stdio.h>

int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

int maiorSubstring(char linha[]){
  int tamanho = 0 , maior = 0; // tamanho da substring, maior substring (pq vao ter varias)

  for(int i = 0; linha[i] != '\0'; i++){
    int letras[256] = {0}; // zerando o vetor de letras
    for(int j = i; linha[j] != '\0'; j++){ // j comecando em i pq a substring comeca em i
      if(letras[linha[j]] == 1) break; // se aparecer a mesma letra na substring, ele para

      letras[linha[j]] = 1; // se existir o caractere, vai contar que ele já foi

      tamanho = j - i + 1; // final - inicio mais um

      if(tamanho > maior) maior = tamanho; // se o tamanho for maior que a var maior entao atualiza o valor de maior
    }
  }

  return maior;
}

int main(){
  char linha[100];

  scanf("%[^\n]", linha);

  while(ehFim(linha) == 0){
    int resp = maiorSubstring(linha);

    printf("%d\n", resp);
    scanf(" %[^\n]", linha);
  }
}