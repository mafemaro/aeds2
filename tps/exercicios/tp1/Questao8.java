import java.util.Scanner;

public class Questao8 {
  public static boolean validacaoSenha (String senha){
    int maiuscula = 0, minuscula = 0, numero = 0, cEspecial = 0;

    if(senha.length() >= 8){
      for(int i = 0; i < senha.length(); i++){

        char charSenha = senha.charAt(i);

        if(charSenha >= 'A' && charSenha <= 'Z') maiuscula++; // se letra esta entre A a Z adiciona na var maiuscula
        else if(charSenha >= 'a' && charSenha <= 'z') minuscula++; // se letra esta entre a a z adiciona na var minuscula
        else if(charSenha >= '0' && charSenha <= '9') numero++; // se letra esta entre os num 0 a 9 adiciona na var numero
        else cEspecial++; // se nao corresponde a nenhuma das outras opcoes add na var do caractere especial
      }

    if(maiuscula > 0 && minuscula > 0 && numero > 0 && cEspecial > 0) return true; // se a string eh validada com todas as restricoes, retorna true
    else return false; // senao retorna falso

    } else return false; // se nao entra no if de ter 8 ou mais caracteres, retorna falso ja de uma vez
  }
  public static void main(String[] args) {
      Scanner sc = new Scanner (System.in);
      String senha = sc.nextLine();

      while(!senha.equals("FIM")){ // enquanto str nao for fim

        if(validacaoSenha(senha) == true) System.out.println("SIM"); // se validacaoSenha true, printa sim
        else System.out.println("NAO"); // senao, printa nao

        senha = sc.nextLine();
      }

      sc.close();
  }  
}
