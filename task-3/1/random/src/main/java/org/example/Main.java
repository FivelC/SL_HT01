package org.example;

public class Main {
    public static void main(String[] args) {
            int rnd = (new java.util.Random()).nextInt(Integer.MAX_VALUE);
            System.out.println(rnd);
            int res = 0;
            while(rnd>0){
                res += rnd%10;
                rnd = rnd/10;
            }
            System.out.println(res);
    }
}