class Questao1{

    public static String cifrada(String linha){
        String cifrado = "";

        for(int i = 0; i < linha.length(); i++){
            char letra = linha.charAt(i); // aqui pega o caractere que ta no indice i

            if(letra != '\r' && letra != '\n') 
                cifrado += (char)(letra + 3); // soma 3 na tabela ascii e adiciona na string cifrada
        }

        return cifrado; // retorna a string ja cifrada
    }

    public static void main(String[] args){

        MyIO.setCharset("ISO-8859-1"); // define o padrao de caracteres (importante pra nao dar erro com acento)

        String linha = MyIO.readLine(); // le a primeira linha

        while(!linha.equals("FIM")){ // repete ate digitar FIM

            String limpa = "";

            for(int i = 0; i < linha.length(); i++){ // limpando string
                char caractere = linha.charAt(i); // pega caractere por caractere
                if(caractere != '\r' && caractere != '\n') 
                    limpa += caractere; // adiciona na string limpa se nao for caractere invisivel
            }

            String cifrado = cifrada(limpa); // chama o metodo que faz a cifra
            MyIO.println(cifrado); // imprime o resultado

            linha = MyIO.readLine(); // le a proxima linha
        }
    }
}