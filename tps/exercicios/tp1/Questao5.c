#include<stdio.h>

int contarNumeros(int n){
  if(n == 0) return 0;
  else return (n % 10) + contarNumeros(n / 10); // ex: temos o numero 12, o primeiro a ser pego vai ser o 2 (12 % 10), depois chama a funcao com 1 (12 / 10), ai pega o 1 e soma tudo
}

int stringParaInt(char linha[]){
  int n = 0;

  // convertendo a string para inteiro pq se nao da erro na hora do pubin pubout
  for(int i = 0; linha[i] != '\0'; i++){

    if(linha[i] == '\n') break; // para de ler quando encontra o enter, senao ele tenta converter '\n' e da erro

    if(linha[i] >= '0' && linha[i] <= '9'){ // garante que só vai converter numero (evita lixo ou caractere estranho)
      n = n * 10 + (linha[i] - '0'); // pega cada caractere e transforma em numero (ex: '1' vira 1)
    }
  }

  return n;
}

int main(){
  char linha[100];

  while(fgets(linha, 100, stdin) != NULL){ // enquanto tiver linha pra ler
    int n = stringParaInt(linha); // pega a funcao e transforma string para inteiro
    int resposta = contarNumeros(n);
    printf("%d\n", resposta);
  }
}