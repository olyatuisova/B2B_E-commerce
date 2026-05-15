package com.example.demo;
public class MyData implements Comparable<MyData> {

    private int value;

    public MyData(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(MyData other) {
        return this.value - other.value;
    }

    @Override
    public String toString() {
        return "MyData(" + value + ")";
    }
}