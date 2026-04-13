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

  public static boolean fim(String str1, String str2){
		if(str1.length() != str2.length()) return false; // se tem tamanhos diferentes, retorna falso

		for(int i = 0; i < str1.length(); i++){ // vai do inicio ate o final da string 
			if(str1.charAt(i) != str2.charAt(i)) return false; // se for diferente de F ou I ou M, retorna falso 
        }
		return true; // se nao cai em nenhum if, retorna verdadeiro 
	}

  public static void main(String[] args) {
      Scanner sc = new Scanner (System.in);
      String senha = sc.nextLine();

      while(!fim(senha, "FIM")){ // enquanto str nao for fim

        if(validacaoSenha(senha) == true) System.out.println("SIM"); // se validacaoSenha true, printa sim
        else System.out.println("NAO"); // senao, printa nao

        senha = sc.nextLine();
      }

      sc.close();
  }  
}
