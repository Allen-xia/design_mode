package com.whenwhenwhere.strategy;

import java.util.Arrays;

/**
 * @author whenwhenwhere
 * @create 2022-01-06 2:18
 */
public class Main {
    public static void main(String[] args) {
//        int[] a = {9,3,7,5,2,6,4,8,1};
        Cat[] a = {new Cat(3,3),new Cat(1,1),new Cat(5,5)};
//        Dog[] a = {new Dog(5),new Dog(1),new Dog(3)};
//        Sorter<Dog> sorter = new Sorter<>();
        Sorter<Cat> sorter = new Sorter<>();
        sorter.sort(a,new CatHeightComparator());
        System.out.println(Arrays.toString(a));

    }
}
