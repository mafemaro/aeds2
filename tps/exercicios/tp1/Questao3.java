import java.util.Scanner;

public class Questao3 {

  public static boolean vogal(String linha){
    String vogais = "aeiouAEIOU";

    for(int i = 0; i < linha.length(); i++){
      char letra = linha.charAt(i); // aqui pega o caractere que ta no indice i
      
      if(vogais.indexOf(letra) == -1){ // pega o caractere letra e percorre a string vogais, se nao achar nada igual retorna -1
        return false; // retorna false se o if for verdade
      }
    }

    return true; // se nao entrar no if do for, retorna true
  }
  
  public static boolean consoante(String linha){
    String vogais = "aeiouAEIOU";

    for(int i = 0; i < linha.length(); i++){
      char letra = linha.charAt(i); // aqui pega o caractere que ta no indice i

      if(vogais.indexOf(letra) != -1){ // o if ao contrario das vogais
        return false;
      }

      if((letra < 'a' || letra > 'z') && (letra < 'A' || letra > 'Z')){ // verifica se nao é letra
        return false;
      }
    }

    return true;
  }

  public static boolean inteiro(String linha){
    for(int i = 0; i < linha.length(); i++){
      char letra = linha.charAt(i);

      if(letra < '0' || letra > '9'){ // verifica se nao é numero
        return false;
      }
    }

    return true; // se todos forem numeros retorna true
  }

  public static boolean real(String linha){
    int ponto = 0;

    for(int i = 0; i < linha.length(); i++){
      char c = linha.charAt(i);

      if(c == '.' || c == ','){ // verifica se é ponto ou virgula
        ponto++;
        if(ponto > 1){ // nao pode ter mais de um ponto
          return false;
        }
      }
      else if(c < '0' || c > '9'){ // verifica se nao é numero
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner (System.in);
    String linha = sc.nextLine();

    while(!linha.equals("FIM")){

      boolean vogalTrue = vogal(linha);
      boolean consoanteTrue = consoante(linha);
      boolean inteiroTrue = inteiro(linha);
      boolean realTrue = real(linha);

      // X1
      if(vogalTrue == true) System.out.print("SIM ");
      else System.out.print("NAO ");

      // X2
      if(consoanteTrue == true) System.out.print("SIM ");
      else System.out.print("NAO ");

      // X3
      if(inteiroTrue == true) System.out.print("SIM ");
      else System.out.print("NAO ");

      // X4
      if(realTrue == true) System.out.print("SIM");
      else System.out.print("NAO");

      linha = sc.nextLine();
    }
    
    sc.close();
  }
}