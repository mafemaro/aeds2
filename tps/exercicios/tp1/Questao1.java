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
    
    public static boolean fim(String str1, String str2){
		if(str1.length() != str2.length()) return false; // se tem tamanhos diferentes, retorna falso

		for(int i = 0; i < str1.length(); i++){ // vai do inicio ate o final da string 
			if(str1.charAt(i) != str2.charAt(i)) return false; // se for diferente de F ou I ou M, retorna falso 
        }
		return true; // se nao cai em nenhum if, retorna verdadeiro 
	}

    public static void main(String[] args){

        String linha = MyIO.readLine(); // le a primeira linha

        while(!fim(linha, "FIM")){ // pega a funcao fim e repete ate digitar FIM

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