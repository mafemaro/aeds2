#include <stdio.h>

int ehFim(char string[]) {
    if (string[0] == 'F' &&
        string[1] == 'I' &&
        string[2] == 'M' &&
        (string[3] == '\n' || string[3] == '\0')){
            return 1;
        }
    return 0;
}

int contarMaiusculas(char string[], int i) {
    if (string[i] == '\0') return 0; // caso base

    if (string[i] >= 'A' && string[i] <= 'Z') return 1 + contarMaiusculas(string, i + 1);
    else return contarMaiusculas(string, i + 1);
}

int main() {
    char string[100];

    fgets(string, 100, stdin);

    while (!ehFim(string)) {
        printf("%d\n", contarMaiusculas(string, 0));
        fgets(string, 100, stdin);
    }

    return 0;
}
