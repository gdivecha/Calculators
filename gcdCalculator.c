#include <stdio.h>
#include <stdlib.h>

static int count = 0;

int gcd(int a, int b, int prevGCD){
    count++;
    int smallest = a;
    int largest = b;
    if(b<a){
        smallest = b;
        largest = a;
    }
    int multiple = largest/smallest;
    int currentGCD = (largest - multiple*smallest);
    if(currentGCD!=0){
        gcd(smallest, currentGCD, currentGCD);
    }
    else if(currentGCD==0){
        if(count!=1){
            return prevGCD;
        }
        else
            return smallest;
    }
}

int main(void){
    int a,b;
    printf("Please enter the values you want the GCD for:\n");
    printf("a: ");
    scanf("%d", &a);
    printf("b: ");
    scanf("%d", &b);
    printf("GCD = %d\n", gcd(a,b,0));
}
