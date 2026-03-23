import java.io.*;
import java.nio.charset.*;

class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static String readLine(){
      String linha = "";
      try{
         linha = in.readLine();
      }catch(IOException e){
         System.out.println("Erro ao ler");
      }
      return linha;
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      } catch (UnsupportedEncodingException e) {
         System.out.println("Erro ao imprimir");
      }
   }
}