import java.util.Scanner;

class palindromo{

  public static boolean palin(String str){
    int tam = str.length();
    for(int i = 0; i < tam / 2; i++){
      if(str.charAt(i) != str.charAt(tam - i - 1)){
        return false;
      }
    }
    return true;
  }
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    String str = sc.nextLine();

    do{
      if(palin(str)){
        System.err.println("SIM");
      }else{
        System.out.println("NAO");
      }
      str = sc.nextLine(); 
    }while(!str.equals("FIM"));
    sc.close();
  }
}