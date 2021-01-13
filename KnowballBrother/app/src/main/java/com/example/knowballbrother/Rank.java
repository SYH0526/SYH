package com.example.knowballbrother;

public class Rank {

    private String rank;
    private String college;
    private String name;
    private String number;

    public Rank(String rank, String college, String name, String number){
        this.rank = rank;
        this.college = college;
        this.name = name;
        this.number = number;
    }

    public String getRank(){
        return rank;
    }

    public String getCollege(){
        return college;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }
}


