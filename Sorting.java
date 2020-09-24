/**
 * Name: Qixuan "Harrison" Ma
 * Email: q5ma@ucsd.edu
 *
 * this file is an implementation of 2 sorting methods, merge sort and 
 * insertion sort
 * this is a generic class whose type parameter extends Comparable
 * there will be 3 public methods in this file, 
 * one for insertion sort and two for merge sort
 */ 

import java.util.Arrays; 

/**
 * the class Sorting is a generic class whose type extends the Comparable
 * interface. it has 3 public methods, one for insertion sort and two for
 * merge sort. it will use the imported Arrays
 */ 
public class Sorting<E extends Comparable<E>>
{
    // 2 constants that I will use for dividing the array
    private static final int CONSTANT_TWO = 2;
    private static final int CONSTANT_FOUR = 4; 

    /**
     * this method is a custom implementation of the Insertion Sort algorithm
     * we will use Arrays.toString() to print out the array
     * @param array to be sorted
     */ 
    public void insertionSort(E[] array)
    {
        // throw a NullPointerException if the input array is null
        if (array == null)
        {
            throw new NullPointerException(); 
        }

        // throw a NullPointerException if any element in the array is null
        for (E e : array)
        {
            if (e == null)
            {
                throw new NullPointerException(); 
            }
        }

        int len = array.length; 

        for (int i = 0; i < len; i ++)
        {
            E curr = array[i]; 
            int j = i - 1; 

            while (j >= 0 && (array[j].compareTo(curr) > 0))
            {
                array[j + 1] = array[j]; 
                j --; 
            }

            array[j + 1] = curr; 

            System.out.println(Arrays.toString(array)); 
        }
    }

    /**
     * this method is a custom implementation of the Merge Sort algorithm
     * we will use Arrays.toString() to print out the array
     * the merge method will be used in this method
     * @param array to be sorted
     */ 
    public void mergeSort(E[] array) 
    {
        // throw a NullPointerException if the input array is null
        if (array == null)
        {
            throw new NullPointerException(); 
        }

        // throw a NullPointerException if any element in the array is null
        for (E e : array)
        {
            if (e == null)
            {
                throw new NullPointerException(); 
            }
        }

        int len = array.length;
        int splitInd = 0; 

        if (len > 1)
        {
            // apply the normal half method
            if (len < CONSTANT_FOUR)
            {
                splitInd = len / CONSTANT_TWO; 
            }
            // apply the 1:3 ratio method
            else
            {
                splitInd = len / CONSTANT_FOUR; 
            }

            E[] leftArray = Arrays.copyOfRange(array, 0, splitInd); 
            E[] rightArray = Arrays.copyOfRange(array, splitInd, len );

            int left = leftArray.length; 
            int right = rightArray.length; 

            // System.out.println("Left array is: " + Arrays.toString(leftArray)); 
            // System.out.println("Right array is: " + Arrays.toString(rightArray)); 

            mergeSort(leftArray); 
            mergeSort(rightArray); 

            merge(array, leftArray, rightArray, left, right); 

            System.out.println(Arrays.toString(array));          
        }
    }

    /**
     * this method merges two separate arrays together
     * @param complete array, left array, right array, 
     * @param left array size, right array size
     */ 
    public void merge(E[] array, E[] leftArray, E[] rightArray, 
            int left, int right)
    {
        int counter = 0; 
        int rightPos = 0; 
        int leftPos = 0; 

        while (leftPos < left && rightPos < right)
        {
            if (leftArray[leftPos].compareTo(rightArray[rightPos]) < 0)
            {
                array[counter] = leftArray[leftPos]; 
                leftPos ++; 
                counter ++; 
            }
            else
            {
                array[counter] = rightArray[rightPos]; 
                rightPos ++; 
                counter ++; 
            }
        }

        while (leftPos < left) 
        {
            array[counter] = leftArray[leftPos];
            counter ++; 
            leftPos ++; 
        }

        while (rightPos < right)
        {
            array[counter] = rightArray[rightPos]; 
            counter ++; 
            rightPos ++; 
        }
    }
}
