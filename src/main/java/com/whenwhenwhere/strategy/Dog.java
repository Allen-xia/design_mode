package com.whenwhenwhere.strategy;

/**
 * @author whenwhenwhere
 * @create 2022-01-06 2:58
 */
public  class Dog implements Comparable<Dog>{
    int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }

    @Override
    public int compareTo(Dog d) {
        if (this.food < d.food){
            return -1;
        }else if (this.food > d.food){
            return 1;
        }else {
            return 0;
        }
    }
}
