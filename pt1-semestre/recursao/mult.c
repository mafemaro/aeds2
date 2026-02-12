#include<stdio.h>
int mult(int a, int b){
  int m = 0;
  if (b > 0) m = a + mult(a, b - 1);

  return m;
}