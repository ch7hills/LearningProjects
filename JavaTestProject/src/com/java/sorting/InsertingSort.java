package com.java.sorting;

import java.util.Arrays;

public class InsertingSort {
	public static int[] insertionSort(int[] a) {
		for(int i=1;i<a.length;i++) {
			int element=a[i];
			int j=i-1;
			while(j>=0 && a[j]>element) {
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=element;
		}
		return a;
	}
	public static void main(String args[]) {
		int a[]= {99,88,77,6,0,4,33,6,8,55};
		int result[] = insertionSort(a);
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]+",");
		}
		//.forEach(e->System.out.print(e));
	}
}
