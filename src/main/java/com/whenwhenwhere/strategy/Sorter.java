package com.whenwhenwhere.strategy;

/**
 * @author whenwhenwhere
 * @create 2022-01-06 2:21
 */
public class Sorter<T> {

    public  void sort(T[] arr, Comparator<T> comparator) {
        for(int i=0; i<arr.length - 1; i++) {
            int minPos = i;

            for(int j=i+1; j<arr.length; j++) {
//                minPos = arr[j].compareTo(arr[minPos]) == -1  ? j : minPos;
                minPos = comparator.compare(arr[j],arr[minPos])==-1 ? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

     void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//    static void print(Cat[] arr) {
//        for(int i=0; i<arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//    }
}
