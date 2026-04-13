import java.util.Scanner;

public class Questao12 {

static int processaEntrada(String texto){ // converte string para inteiro e chama funcao recursiva para somar os digitos
    int valor = Integer.parseInt(texto); // converte string para inteiro pelo parseInt da classe Integer
    return somaDigitos(Math.abs(valor)); // chama funcao recursiva para somar os digitos, usando valor absoluto para lidar com numeros negativos
}

static int somaDigitos(int numero){ // funcao recursiva para somar os digitos de um numero
    if(numero < 10) return numero; // se o numero for menor que 10, retorna o proprio numero (caso base)
    return (numero % 10) + somaDigitos(numero / 10); // soma o ultimo digito (numero % 10) com a soma dos digitos do numero sem o ultimo digito (numero / 10)
}

public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    String entrada;

    while(sc.hasNextLine()){
        entrada = sc.nextLine();
        if(entrada.equals("FIM")) // se a entrada for "FIM", break 
            break;
        System.out.println(processaEntrada(entrada)); // processa a entrada e imprime o resultado da soma dos digitos
    }
    sc.close();
}
}