Aqui está o arquivo `README.md` completo, unindo toda a teoria dos PDFs com o estilo visual que utilizámos para o Bubble Sort e a versão "bonita" da P2.

---

# Somatórios na Análise de Algoritmos

## 1. Visão Geral
Na Ciência da Computação, os **Somatórios** são ferramentas matemáticas fundamentais para o levantamento de custo (tempo e memória) de algoritmos. O custo total de um software é, essencialmente, a soma dos custos de cada operação executada durante o seu processamento.

Para representar estas somas de forma compacta, utilizamos a **Notação Sigma ($\Sigma$)**, que define o padrão de repetição através de um índice, um limite inferior e um limite superior.

---

## 2. Regras Básicas de Transformação
Para simplificar somas complexas e chegar a resultados diretos, aplicamos as três regras básicas da manipulação algébrica:

1.  **Distributividade**: Permite mover constantes para dentro ou para fora do somatório.  
    *Exemplo:* $\sum c \cdot a_i = c \cdot \sum a_i$.
2.  **Associatividade**: Permite separar um somatório em partes ou unir somas diferentes. Útil para isolar termos específicos.
3.  **Comutatividade**: A ordem em que os termos são somados não altera o resultado final.

---

## 3. Propriedades Matemáticas

### 3.1. Propriedade P1 (Combinação de Conjuntos)
Utilizada para manipular somatórios com diferentes intervalos de índices, aplicando regras de união e interseção para unificar a análise.

### 3.2. Propriedade P2 (A Regra da Perturbação)
Esta é a propriedade mais poderosa para deduzir fórmulas. Ela baseia-se na ideia de que podemos olhar para uma sequência de duas formas: extraindo o primeiro ou o último termo.

> [!IMPORTANTE]
> **A Equação da Perturbação**
> $$S_n + a_{n+1} = a_0 + \sum_{0 \le i \le n} a_{i+1}$$

**Como funciona visualmente:**
* **Pelo Final:** Pegamos a soma atual ($S_n$) e adicionamos o próximo elemento ($a_{n+1}$).
* **Pelo Início:** Destacamos o primeiro elemento ($a_0$) e somamos o restante da sequência deslocada.

Ao igualar estas duas perspetivas, conseguimos isolar $S_n$ e encontrar a sua "fórmula fechada".

---

## 4. Métodos Gerais de Resolução

Para resolver um somatório (eliminar o símbolo $\Sigma$), seguimos estes passos:

1.  **Procure!**: Muitas fórmulas já foram resolvidas por matemáticos. Consulte referências como *Matemática Concreta* (Knuth) ou *Algoritmos* (Cormen).
2.  **Adivinhe e Prove por Indução**: Se identificar um padrão, pode propor uma solução e prová-la:
    * **Passo Base**: Provar para o primeiro termo.
    * **Passo Indutivo**: Provar que, se vale para $n$, também vale para $n+1$.
3.  **Perturbe a Soma**: Aplique as regras de transformação e a propriedade P2 para isolar o termo da soma através de algebrismo.

---

## 5. Implementação em Java
Um somatório é a representação matemática de um laço de repetição (`for`).

```java
public class Somatorio {
    public static void main(String[] args) {
        int n = 10;
        int soma = 0;

        // Representação de: Σ i para i=1 até n
        for (int i = 1; i <= n; i++) {
            soma += i;
        }

        System.out.println("Resultado da soma: " + soma);
        
        // Versão em Fórmula Fechada (O(1)):
        int formulaFechada = (n * (n + 1)) / 2;
        System.out.println("Resultado pela fórmula: " + formulaFechada);
    }
}
```

---

## 6. Conclusão
Dominar somatórios permite que um desenvolvedor saia do "eu acho que o código é rápido" para o "eu consigo provar que o custo é $O(n \log n)$". É a base matemática que sustenta a análise de complexidade em algoritmos de ordenação, busca e estruturas de dados avançadas.