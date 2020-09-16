package com.example.mvvm_jetpack_practice.bean;

public class Person{

    public Person(String name, int id, boolean eatrice){
        this.name = name;
        this.id = id;
        this.eatrice = eatrice;
    }

    String name;
    int id;
    boolean eatrice;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean isEatrice(){
        return eatrice;
    }

    public void setEatrice(boolean eatrice){
        this.eatrice = eatrice;
    }

    @Override
    public String toString(){
        return "Person{" + "name='" + name + '\'' + ", id=" + id + ", eatrice=" + eatrice + '}';
    }
}
