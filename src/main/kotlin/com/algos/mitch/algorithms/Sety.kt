package com.algos.mitch.algorithms

import java.lang.IllegalArgumentException

interface Vector {

    fun add(element: Any)
    fun indexOf(element: Any): Int
    fun contains(element: Any): Boolean
    fun isEmpty(): Boolean
    fun remove(element: Any): Boolean
    fun set(index :Int, element: Any?)
}

class Sety : Vector {
    var size: Int = 0
    private var maxCapcity = 12
    var elements: Array<Any?>

    constructor() {
        this.elements = arrayOf()
    }

    constructor(intialCapacity: Int) {
        when {
            intialCapacity > 0 -> {
                this.elements = Array(intialCapacity) { _ -> null }
            }
            intialCapacity == 0 -> {
                this.elements = emptyArray()
            }
            else -> {
                throw IllegalArgumentException("Illegal Capcity: $intialCapacity")
            }
        }
    }

    override fun isEmpty(): Boolean {
        return this.size == 0
    }

    override fun remove(element: Any): Boolean {
        var foundElement: Any? = null
        if(contains(element)) {
            foundElement = element
            for (index in 0 until size) {
                if (element == elements[index]) {
                    elements[index] = elements[size - 1]
                    elements[size - 1] = null
                    size--
                    return true
                }
            }
        }
        return false
    }

    override fun set(index: Int, element: Any?) {
        if (index >= size || index < 0) throw IndexOutOfBoundsException("Index $index is not valid")
         elements[index] = element
    }


    //refactor to use indexOf
    override fun contains(element: Any): Boolean {
        this.elements.forEach { item ->
            if (element == item) return true
        }
        return false
    }

    override fun indexOf(element: Any): Int {
        for (index in 0 until size) {
            if (element == elements[index]) {
                return index
            }
        }
        return -1
    }

    override fun add(element: Any) {
        var a = elements
        val s = size
        if (size == maxCapcity) {
            val newArray = arrayOfNulls<Any>(s * 2)
            System.arraycopy(a, 0, newArray, 0, s)
            maxCapcity = newArray.size
            a = newArray
            elements = a
        }
        a[s-1] = element
        size = s + 1
    }

//    override fun remove(index: Int) {
//        if (index >= size) throw IndexOutOfBoundsException()
//        val oldValue = elements[index]
//        val numMoved = size - index - 1
//        if (numMoved > 0 )
//            System.arraycopy(elements, index + 1, elements, index, numMoved)
//        elements[size--] = null //let GC do its job and prevent memory leaks
//
//    }


//Immutable
//    override fun addImmutable(element: Any) : Sety{
//        val s = size
//        val newList = Sety(s + 1)
//        System.arraycopy(elements, 0, newList.elements, 0, s)
//        newList.elements[s] = element
//        newList.size = s + 1
//        return newList
//    }


//    fun add(element: Int) {
//        val newlist = arrayOf<Int>()
//        //check if there is room
//
//        if (!contains(element) && capacity >= size) {
//            if (size == 0) {
//                size++
//                list = arrayListOf(element)
//            } else {
//                val newList: ArrayList<Int> = ArrayList(initialCapacity = size++)
//                (0..size).forEach { index ->
//                    newList[index] = list[index]
//                }
//                newList[size] = element
//                list = newList
//            }
//        }
//        if (contains(element)) return
//
//        if (size >= capacity) {
//            capacity *=2
//            list = Arrays.copyOf(list, capacity)
//        }
//        list[size++] = element

}


//fun add(newValue: String?) {
//    val elem: Int = findElement(newValue)
//    if (numberOfElements < maxNumberOfElements && elem == -1) {
//        setElements.get(numberOfElements) = newValue
//        numberOfElements++
//    }
//}


/*
* public boolean remove(Object o) {
    int i = 0;
    for (Object elem : backingArray) {
        if (o != null && o.equals(elem)) {
            System.arraycopy(backingArray, i+1, backingArray, i, numElements-i-1);
            backingArray[numElements-1] = null;
            numElements = numElements - 1;
            return true;
        }
        i = i + 1;
    }
    return false;
}
* //********************************************************************
//  com.algos.mitch.algorithms.ArraySet.java       Author: Lewis/Chase
//
//  Represents an array implementation of a set.
//********************************************************************

package jss2;
import jss2.exceptions.*;
import java.util.*;

public class com.algos.mitch.algorithms.ArraySet<T> implements SetADT<T>
{
   private static Random rand = new Random();

   private final int DEFAULT_CAPACITY = 100;
   private final int NOT_FOUND = -1;

   private int count;  // the current number of elements in the set

   private T[] contents;

   //-----------------------------------------------------------------
   //  Creates an empty set using the default capacity.
   //-----------------------------------------------------------------
   public com.algos.mitch.algorithms.ArraySet()
   {
      count = 0;
      contents = (T[])(new Object[DEFAULT_CAPACITY]);
   }

   //-----------------------------------------------------------------
   //  Creates an empty set using the specified capacity.
   //-----------------------------------------------------------------
   public com.algos.mitch.algorithms.ArraySet (int initialCapacity)
   {
      count = 0;
      contents = (T[])(new Object[initialCapacity]);
   }

   //-----------------------------------------------------------------
   //  Adds the specified element to the set if it's not already
   //  present. Expands the capacity of the set array if necessary.
   //-----------------------------------------------------------------
   public void add (T element)
   {
      if (!(contains(element)))
      {
         if (size() == contents.length)
            expandCapacity();

         contents[count] = element;
         count++;
      }
   }

   //-----------------------------------------------------------------
   //  Adds the contents of the parameter to this set.
   //-----------------------------------------------------------------
   public void addAll (SetADT<T> set)
   {
      Iterator<T> scan = set.iterator();

      while (scan.hasNext())
         add (scan.next());
   }


   //-----------------------------------------------------------------
   //  Removes a random element from the set and returns it. Throws
   //  an EmptySetException if the set is empty.
   //-----------------------------------------------------------------
   public T removeRandom() throws EmptySetException
   {
      if (isEmpty())
         throw new EmptySetException();

      int choice = rand.nextInt(count);

      T result = contents[choice];

      contents[choice] = contents[count-1];  // fill the gap
      contents[count-1] = null;
      count--;

      return result;
   }

   //-----------------------------------------------------------------
   //  Removes the specified element from the set and returns it.
   //  Throws an EmptySetException if the set is empty and a
   //  NoSuchElementException if the target is not in the set.
   //-----------------------------------------------------------------
   public T remove (T target) throws EmptySetException,
                                     NoSuchElementException
   {
      int search = NOT_FOUND;

      if (isEmpty())
         throw new EmptySetException();

      for (int index=0; index < count && search == NOT_FOUND; index++)
         if (contents[index].equals(target))
            search = index;

      if (search == NOT_FOUND)
         throw new NoSuchElementException();

      T result = contents[search];

      contents[search] = contents[count-1];
      contents[count-1] = null;
      count--;

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns a new set that is the union of this set and the
   //  parameter.
   //-----------------------------------------------------------------
   public SetADT<T> union (SetADT<T> set)
   {
      com.algos.mitch.algorithms.ArraySet<T> both = new com.algos.mitch.algorithms.ArraySet<T>();

      for (int index = 0; index < count; index++)
         both.add (contents[index]);

      Iterator<T> scan = set.iterator();
      while (scan.hasNext())
         both.add (scan.next());

      return both;
   }

   //-----------------------------------------------------------------
   //  Returns true if this set contains the specified target
   //  element.
   //-----------------------------------------------------------------
   public boolean contains (T target)
   {
      int search = NOT_FOUND;

      for (int index=0; index < count && search == NOT_FOUND; index++)
         if (contents[index].equals(target))
            search = index;

      return (search != NOT_FOUND);
   }

   //-----------------------------------------------------------------
   //  Returns true if this set contains exactly the same elements
   //  as the parameter.
   //-----------------------------------------------------------------
   public boolean equals (SetADT<T> set)
   {
      boolean result = false;
      com.algos.mitch.algorithms.ArraySet<T> temp1 = new com.algos.mitch.algorithms.ArraySet<T>();
      com.algos.mitch.algorithms.ArraySet<T> temp2 = new com.algos.mitch.algorithms.ArraySet<T>();
      T obj;

      if (size() == set.size())
      {
         temp1.addAll(this);
         temp2.addAll(set);

         Iterator<T> scan = set.iterator();

         while (scan.hasNext())
         {
            obj = scan.next();
            if (temp1.contains(obj))
            {
               temp1.remove(obj);
               temp2.remove(obj);
            }

         }

        result = (temp1.isEmpty() && temp2.isEmpty());
      }

      return result;
   }

   //-----------------------------------------------------------------
   //  Returns true if this set is empty and false otherwise.
   //-----------------------------------------------------------------
   public boolean isEmpty()
   {
      return (count == 0);
   }

   //-----------------------------------------------------------------
   //  Returns the number of elements currently in this set.
   //-----------------------------------------------------------------
   public int size()
   {
      return count;
   }

   //-----------------------------------------------------------------
   //  Returns an iterator for the elements currently in this set.
   //-----------------------------------------------------------------
   public Iterator<T> iterator()
   {
      return new ArrayIterator<T> (contents, count);
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this set.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = "";

      for (int index=0; index < count; index++)
         result = result + contents[index].toString() + "\n";

      return result;
   }

   //-----------------------------------------------------------------
   //  Creates a new array to store the contents of the set with
   //  twice the capacity of the old one.
   //-----------------------------------------------------------------
   private void expandCapacity()
   {
      T[] larger = (T[])(new Object[contents.length*2]);

      for (int index=0; index < contents.length; index++)
         larger[index] = contents[index];

      contents = larger;
   }
}
*
*
*
*
*
*
* */