#include<stdio.h>

int ehFim(char linha[]){
  if(linha[0] == 'F' && linha[1] == 'I' && linha[2] == 'M' && linha[3] == '\0'){
    return 1;
  }
  return 0;
}

int contarNumeros(int n){
  if(n == 0) return 0;
  else return (n % 10) + contarNumeros(n / 10); // ex: temos o numero 12, o primeiro a ser pego vai ser o 2 (12 % 10), depois chama a funcao com 1 (12 / 10), ai pega o 1 e soma tudo
}

int main(){
  char linha[100];

  scanf(" %[^\n]", linha); // le a linha como string (pq pode ser FIM)

  while(ehFim(linha) == 0){
    int n = 0;

    // convertendo a string para inteiro pq se nao da erro na hora do pubin pubout
    for(int i = 0; linha[i] != '\0'; i++){
      n = n * 10 + (linha[i] - '0'); // pega cada caractere e transforma em numero (ex: '1' vira 1)
    }

    int resposta = contarNumeros(n);
    printf("%d\n", resposta);

    scanf(" %[^\n]", linha);
  }
}