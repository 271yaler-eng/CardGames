
/**
 * This ArrayLog object represents a Log ADT implemented as
 * a generic data type array using the EnhancedLogInterface.
 * 
 * @author  
 * @version 
 */
@SuppressWarnings("unchecked")  // eliminates compiler warnings from cast below
public class ArrayLog<T> implements EnhancedLogInterface<T>
{
    // Instance variables
    private T[] log;
    private String name;
    private int size;
    
    // Create a new String array with a capacity of 4 elements
    // and assign values to instance variables.
    public ArrayLog(String name)
    {
        this.name = name;
        this.size = 0;
        // cannot create a generic array object, so has to be cast
        // from an Object back into the generic in order to compile
        this.log = (T[])new Object[4];
    }

    // Returns the name of this StringLog.
    public String getName()
    {
        return this.name;
    }

    // Returns the logical size of this StringLog.
    public int size()
    {
        return this.size;
    }
    
    // Returns true if this list contains no elements.
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    
    // Returns true if this list is completely full.
    public boolean isFull()
    {
        return this.size == this.log.length;
    }

    // Appends the specified element to the end of this list.
    public void add(T element)
    {
        if (isFull())
        {
            T[] newLog = (T[])new Object[log.length * 2];
            for (int i = 0; i < log.length; i++)
            {
                newLog[i] = log[i];
            }
            log = newLog;
        }
        log[size] = element;
        size++;
    }
  
    // Returns the element at the specified position in this list.
    public T get(int index)
    {   
        return log[index];
    }
    
    // Returns the index of the first occurance of the specified element
    // in this list, or -1 if this list does not contain the element.
    public int indexOf(T element)
    {
        for(int i = 0; i < size; i++)
        {
            if(log[i].equals(element))
            {
                return i;
            }
        }
        return -1;
    }
    
    // Returns true if this list contains the specified element.
    public boolean contains(T element)
    {
        return indexOf(element) != -1;
    }
    
    // Returns a formatted string representation of this StringLog.
    public String toString()
    {
        String result = "Log: " + name + "\n";
        for (int i = 0; i < size; i++)
        {
            result += (i + 1) + ". " + log[i] + "\n";
        }
        return result;
    }
    
    // Replaces the element at the specified position in this list
    // with the specified element.  Returns what was at that location
    public T set(int index, T element)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldElement = log[index];
        log[index] = element;
        return oldElement;
    }
    
    // Inserts the specified element at the specified position in this list.
    public void add(int index, T element)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (isFull())
        {
            T[] newLog = (T[])new Object[log.length * 2];
            for (int i = 0; i < log.length; i++)
            {
                newLog[i] = log[i];
            }
            log = newLog;
        }
        for(int i = size; i > index; i--)
        {
            log[i] = log[i - 1];
        }
        log[index] = element;
        size++;
    }
    
    // Removes the element at the specified position in this list, and
    // returns the element that was removed.  Any unused array elements
    // are set to null.
    public T remove(int index)
    {
        if(index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedElement = log[index];
        for(int i = index; i < size - 1; i++)
        {
            log[i] = log[i+1];
        }
        log[size - 1] = null;
        size--;

        if(size < log.length / 4 && log.length > 4 && size > 0)
        {
            T[] newLog = (T[])new Object[log.length / 2];
            for(int i = 0; i < size; i++)
            {
                newLog[i] = log[i];
            }
            log = newLog;
        }
        return removedElement;
    }
    
    // Removes the first occurance of the specified element from this
    // list, if it is present.  Returns true if element was found (and 
    // removed), false otherwise.
    public boolean remove(T element)
    {
        for(int i = 0; i < size; i++)
        {
            if(log[i].equals(element))
            {
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    // Removes all of the elements from this list.
    public void clear()
    {
        for(int i = 0; i < size; i++)
        {
            log[i] = null;
        }
        size = 0;
    }
}
