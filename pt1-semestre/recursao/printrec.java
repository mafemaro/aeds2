public class printrec {
  public static void rec(int i){
    System.out.println(i);

    if(i > 0) rec(i - 1);
    
    System.out.println(i);
  }

  public static void main(String [] args){
    rec(3);
  }
}
