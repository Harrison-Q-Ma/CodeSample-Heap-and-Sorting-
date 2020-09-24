/**
 * This file contains a custom implimentation of the MinHeap Data
 * struture using the list ADT
 *
 * Name: Qixuan "Harrison" Ma
 * PID: A16018410
 * Email: q5ma@ucsd.edu
 */ 
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is a custom implementation of the MinHeap data structure
 * using the list ADT
 * It has 2 constructors, 1 instance variable, and 13 methods
 */
public class MyMinHeap<E extends Comparable<E>>
{
    // instance variable
    protected List<E> list;

    private static final int CONSTANT_TWO = 2; 

    /**
     * default no-arg constructor
     * initializes the list instance variable to empty ArrayList
     */
    public MyMinHeap() 
    {
        this.list = new ArrayList<E>(); 
    }

    /**
     * second constructor
     * initialize a MinHeap using elements in the collection
     *
     * @throws a NullPointerException if the collection or any element in the 
     * collection is null
     */ 
    public MyMinHeap(Collection<? extends E> collection) 
    {
        if (collection == null)
        {
            throw new NullPointerException("The input collection is null");
        }
        for (Object o : collection)
        {
            if (o == null)
            {
                throw new NullPointerException("The input collection is null"); 
            }
        }

        this.list = new ArrayList<E>(collection);
        
        for (int i = this.list.size() - 1; i >= 0; i --)
        {
            percolateDown(i); 
        }
    }

    /** 
     * swap the locations of the elements at the from and to indices in list
     * @params from and to are indices that we want to swap
     */
    @SuppressWarnings("unchecked")
    protected void swap(int from, int to) 
    {
        Object fromInd = this.list.get(from); 
        
        this.list.set(from, this.list.get(to));
        this.list.set(to, (E) fromInd); 
    }

    /**
     * calculate and return the parent index of the parameter index
     * @param index that we want to use to calculate parent index
     * @return the parent index of the input index
     */ 
    protected int getParentIdx(int index) 
    {
        return (index - 1)/ 2;  
    }

    /**
     * calculate and return the left child index of the parameter index
     * @param index of the parent
     * @returns index of the left child
     */ 
    protected int getLeftChildIdx(int index) 
    {
        return 2 * index + 1;
    }

    /**
     * calculate and return the right child index of the parameter index
     * @param index of the parent
     * @returns index of the right child
     */ 
    protected int getRightChildIdx(int index) 
    {
        return 2 * index + 2;
    }

    /**
     * return the index of the smaller child of the element at index
     * if the two children are equal, return the index of the left child
     * @param index of the parent
     * @returns index of the smaller child
     */
    @SuppressWarnings("unchecked")
    protected int getMinChildIdx(int index) 
    {
        if ((2 * index + 2) > list.size() - 1)
        {
            if ((2 * index + 1) > list.size() - 1)
            {
                return 2 * index + 1;
            }
            return -1;
        }

        Object leftChild = this.list.get(2 * index + 1);
        Object rightChild = this.list.get(2 * index + 2);

        // if the two elements are equal
        if (((E)leftChild).compareTo((E)rightChild) == 0)
        {
            return 2 * index + 1; 
        }
        // if the left child is smaller
        else if (((E)leftChild).compareTo((E)rightChild) < 0)
        {
            return 2 * index + 1; 
        }
        // if the right child is smaller
        else
        {
            return 2 * index + 2; 
        }
    }

    /**
     * percolate the element at the parameter index up until no heap
     * properties are violated by this element
     * @param index of element to be percolatedUp
     */ 
    protected void percolateUp(int index) 
    {
        int currInd = index; 
        int parentInd = getParentIdx(index);

        if (list.get(currInd).compareTo(list.get(parentInd)) < 0)
        {
            swap (currInd, parentInd); 
            percolateUp(parentInd); 
        }
    }

    /**
     * percolate the element at the parameter index down until no heap 
     * properties are violated by this element
     * @param index of element to be percolatedDown
     */ 
    protected void percolateDown(int index) 
    {
        if (index > list.size() - 1)
        {
            return; 
        }

        int currInd = index;
        int smallChild = getMinChildIdx(index);
        
        if (smallChild > list.size() - 1)
        {
            return;
        }

        if (list.get(currInd).compareTo(list.get(smallChild)) > 0)
        {
            swap(currInd, smallChild); 
            percolateDown(smallChild);
        }
    }
  
    /**
     * remove the element at the specified index from list and return it 
     * @param index of the element to be removed
     * @return element that was removed
     */
    @SuppressWarnings("unchecked")
    protected E deleteIndex(int index) 
    {
        Object orig = list.get(0);
        Object replacement = list.get(list.size() - 1); 

        list.set(index, list.get(list.size() - 1)); 

        // the replacement is the same as the original
        if (((E)orig).compareTo((E)replacement) == 0)
        {
            // do nothing, since the heap properties are already maintained
        }
        // the original is smaller than the replacement
        else if (((E)orig).compareTo((E)replacement) > 0)
        {
            percolateUp(index); 
        }
        // the original is larger than the replacement
        else
        {
            percolateDown(index); 
        }

        list.remove(list.size() - 1);

        return (E)orig; 
    }

    /** 
     * add element to the end of the heap and percolate the inserted element 
     * up until no heap properties are violated
     * @param element to be inserted
     */ 
    public void insert(E element) 
    {
        this.list.add(element); 

        percolateUp(this.list.size() - 1); 
    }

    /**
     * @return the root (smallest) element of the heap
     */ 
    public E getMin() 
    {
        return this.list.get(0);
    }

    /**
     * remove and return the root
     * @return root of the heap
     */ 
    @SuppressWarnings("unchecked")
    public E remove() 
    {
        if (this.size() == 0)
        {
            return null;
        }
        Object root = this.list.get(0);
        deleteIndex(0);

        return (E) root; 
    }

    /**
     * @return number of elements in the MinHeap
     */ 
    public int size() 
    {
        return this.list.size();
    }

    /**
     * clear out the entire heap
     */ 
    public void clear() 
    {
        this.list = new ArrayList<>();
    }		
}
