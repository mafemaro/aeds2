import java.util.Scanner;

public class TamanhoString {
  public static void main (String [] args) {
    Scanner sc = new Scanner (System.in);
    String s = sc.nextLine();
    int i = 0;

    while (i < s.length()) {
        i++;
    }

    System.out.println("I eh igual a: " + i);

    sc.close();
  }
}
