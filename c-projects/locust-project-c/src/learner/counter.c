#include <stdio.h>

int main() {
  int n = 0;
  loop: ;
 
  printf("\n%d", n);
  n++;
  if (n<10) {
  goto loop;
  }
  return 0;
}