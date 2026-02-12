#include<stdio.h>

int T(int n){
  if(n == 0) return 1;
  else return T(n-1) * T(n-1);
}

int main(){
  int n = 4;
  T(n);
}