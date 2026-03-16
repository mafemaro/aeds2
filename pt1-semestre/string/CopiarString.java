import java.util.Scanner;

public class CopiarString {
  public static void main (String [] args) {
    Scanner sc = new Scanner (System.in);
    String s = sc.nextLine();
    String copia = "";

    for(int i = 0; i < s.length(); i++){
      copia += s.charAt(i);
    }

    System.out.println("A copia da string eh: " + copia);
    sc.close();
  }
}
