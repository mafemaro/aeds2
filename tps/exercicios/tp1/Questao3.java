class Questao3{

  //função que verifica se é uma letra
  public static boolean letra(char c){
    return (c>='a'&&c<='z')||(c>='A'&&c<='Z');
  }

  //verifica se a string é "FIM"
  public static boolean ehFim(String s){
    return (s.length()==3&&s.charAt(0)=='F'&&s.charAt(1)=='I'&&s.charAt(2)=='M');
  }

  //verifica se é só vogais
  public static boolean soVogais(String s){
    for(int i=0;i<s.length();i++){
      char c=s.charAt(i);//pega caracter por caracter

      if(!letra(c)) return false;//se nao for letra retorna false

      if(c!='a'&&c!='e'&&c!='i'&&c!='o'&&c!='u'&&
         c!='A'&&c!='E'&&c!='I'&&c!='O'&&c!='U') return false;//verifica vogal
    }

    return true;//se passar por tudo é verdadeiro
  }

  //verifica se é só consoantes
  public static boolean soConsoantes(String s){
    for(int i=0;i<s.length();i++){
      char c=s.charAt(i);//pega caracter

      if(!letra(c)) return false;//se nao for letra retorna false

      if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||
         c=='A'||c=='E'||c=='I'||c=='O'||c=='U') return false;//se for vogal retorna false
    }

    return true;//se passar por tudo é verdadeiro
  }

  //verifica se é numero inteiro
  public static boolean soInteiro(String s){
    for(int i=0;i<s.length();i++){
      char c=s.charAt(i);//pega caracter

      if(!(c>='0'&&c<='9')) return false;//se nao for numero retorna false
    }

    return true;//se passar por tudo é inteiro
  }

  //verifica se é numero real
  public static boolean soReal(String s){
    int sep=0;//contador de . ou ,

    for(int i=0;i<s.length();i++){
      char c=s.charAt(i);//pega caracter

      if((c>='0'&&c<='9')||c=='.'||c==','){
        if(c=='.'||c==',') sep++;//conta separadores
      }else return false;//se nao for retorna falso
    }

    if(sep>1) return false;//se tiver mais de um separador

    return true;//se passar por tudo é real
  }

  public static void main(String[] args){
    String linha=MyIO.readLine();//le primeira linha

    while(!ehFim(linha)){//enquanto linha nao for FIM

      boolean x1=soVogais(linha);
      boolean x2=soConsoantes(linha);
      boolean x3=soInteiro(linha);
      boolean x4=soReal(linha);

      if(x1) System.out.print("SIM "); else System.out.print("NAO ");
      if(x2) System.out.print("SIM "); else System.out.print("NAO ");
      if(x3) System.out.print("SIM "); else System.out.print("NAO ");
      if(x4) System.out.println("SIM"); else System.out.println("NAO");

      linha = MyIO.readLine(); // le proxima linha
    }
  }
}