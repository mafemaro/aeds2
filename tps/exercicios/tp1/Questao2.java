import java.util.Random;
import java.util.Scanner;

public class Questao2 {
  public static String alterar(String frase, Random gerador){ // funcao que vai retornar uma string modificada!

    char primeira = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); // aqui para gerar a primeira palavra
    char segunda = (char)('a' + (Math.abs(gerador.nextInt()) % 26)); // gerar a segunda
    
    String alterada = "";

    for(int i = 0; i < frase.length(); i++){
      if(frase.charAt(i) == primeira) alterada += segunda;
      else alterada += frase.charAt(i);
    }

    return alterada;
  }

  public static boolean fim(String str1, String str2){
		if(str1.length() != str2.length()) return false; // se tem tamanhos diferentes, retorna falso

		for(int i = 0; i < str1.length(); i++){ // vai do inicio ate o final da string 
			if(str1.charAt(i) != str2.charAt(i)) return false; // se for diferente de F ou I ou M, retorna falso 
        }
		return true; // se nao cai em nenhum if, retorna verdadeiro 
	}

  public static void main (String [] args){
    Scanner sc = new Scanner(System.in);
    Random gerador = new Random();
    gerador.setSeed(4);

    String frase = sc.nextLine();
    
    while(!fim(frase, "FIM")){ // enquanto nao for o fim da strinng...
      String alterada = alterar(frase, gerador);
      System.out.println(alterada);
      frase = sc.nextLine();
  }
  sc.close();
}
}