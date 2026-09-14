package org.example;

public class Flower {
    int code;
    int cost;
    public void setCost(int c){
        this.cost = c;
    }
    public void setCode(int c){
        this.code = c;
    }
    public int getCost(){
        return cost;
    }
    public Flower(int code, int cost){
        setCode(code);
        setCost(cost);
    }
}
