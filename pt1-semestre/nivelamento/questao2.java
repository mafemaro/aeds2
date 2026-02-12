//  Faça um método que receba um array de inteiros e mostre na tela o maior e o menor elementos do array.

import java.util.Scanner;

public class questao2 {
  public static int maior(int vet[]){
    int maior = vet[0];
    for(int i = 0; i < vet.length; i++){
      if(vet[i] > maior) maior = vet[i];
    }
    return maior;
  }

  public static int menor(int vet[]){
    int menor = vet[0];
    for(int i = 0; i < vet.length; i++){
      if(vet[i] < menor) menor = vet[i];
    }
    return menor;
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int vet[] = new int[10];

    for(int i = 0; i < vet.length; i++){
      vet[i] = sc.nextInt();
    }

    int maior = maior(vet);
    int menor = menor(vet);


    System.out.println("Maior: " + maior);
    System.out.println("Menor: " + menor);
  }
}