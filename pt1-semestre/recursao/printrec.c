#include<stdio.h>
void printrec(int i){
  printf("\t%d", i);

  if(i > 0) printrec(i-1);

  printf("\t%d", i);
}

int main(){
  printrec(3);
  return 0;
}