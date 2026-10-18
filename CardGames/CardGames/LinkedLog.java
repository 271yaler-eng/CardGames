
/**
 * This LinkedLog object represents a Log ADT implemented as
 * a LinkedList using the EnhancedLogInterface.
 * 
 * @author 
 * @version 
 */
public class LinkedLog<T> implements EnhancedLogInterface<T>
{
    private LLNode<T> log;
    private String name;
    private int size;

    public LinkedLog(String name)
    {
        this.name = name;
        this.log = null;
        this.size = 0;
    }
    
    // returns the name of this StringLog
    public String getName()
    {
        return this.name;
    }

    // returns the logical size of this StringLog
    public int size()
    {
        return this.size;
    }
    
    // returns true if this list contains no elements
    public boolean isEmpty()
    {
        return this.size == 0;
    }
    
    // returns true if this list is completely full
    public boolean isFull()
    {
        if(this.size == Integer.MAX_VALUE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // appends the specified element to the end of this list
    public void add(T element)
    {
        LLNode<T> newNode = new LLNode<T>(element);
        if (this.log == null)
        {
            this.log = newNode;
        }
        else
        {
            LLNode<T> current = this.log;
            while (current.getLink() != null)
            {
                current = current.getLink();
            }
            current.setLink(newNode);
        }
        this.size++;
    }
  
    // returns the element at the specified position in this list
    public T get(int index)
    {
        if (index < 0 || index >= this.size())
        {
            return null;
        }
        else
        {
            LLNode<T> current = this.log;
            for(int i = 0; i < index; i++)
            {
                current = current.getLink();
            }
            return current.getInfo();
        }
    }
    
    // returns the index of the first occurance of the specified element
    // in this list, or -1 if this list does not contain the element
    public int indexOf(T element)
    {
        LLNode<T> current = this.log;
        for(int i = 0; i < this.size(); i++)
        {
            if(current.getInfo().equals(element))
            {
                return i;
            }
            current = current.getLink();
        }
        return -1;
    }
    
    // returns true if this list contains the specified element
    public boolean contains(T element)
    {
        return this.indexOf(element) != -1;
    }
    
    // returns a formatted string representation of this StringLog
    public String toString()
    {
        String result = "Log: " + name + "\n";
        LLNode<T> curNode = log;
        int count = 0;
    
        while (curNode != null)
        {
            count++;
            result += count + ". " + curNode.getInfo() + "\n";
            curNode = curNode.getLink();
        }
    return result;
    }
    
    // replaces the element at the specified position in this list
    // with the specified element
    public T set(int index, T element)
    {
        if (index < 0 || index >= this.size())
        {
            return null;
        }
        else
        {
            LLNode<T> current = this.log;
            for(int i = 0; i < index; i++)
            {
                current = current.getLink();
            }
            T oldElement = current.getInfo();
            current.setInfo(element);
            return oldElement;
        }
    }
    
    // inserts the specified element at the specified position in this list
    public void add(int index, T element)
    {
        if(index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        else if(index == 0)
        {
            LLNode<T> newNode = new LLNode<T>(element);
            newNode.setLink(this.log);
            this.log = newNode;
        }
        else
        {
            LLNode<T> current = this.log;
            for(int i = 0; i < index - 1; i++)
            {
                current = current.getLink();
            }
            LLNode<T> newNode = new LLNode<T>(element);
            newNode.setLink(current.getLink());
            current.setLink(newNode);
        }
        this.size++;
    }
    
    // removes the element at the specified position in this list
    public T remove(int index)
    {
        if(index < 0 || index >= this.size())
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        else if (index == 0)
        {
            T removedElement = this.log.getInfo();
            this.log = this.log.getLink();
            this.size--;
            return removedElement;
        }
        else
        {
            LLNode<T> current = this.log;
            for(int i = 0; i < index - 1; i++)
            {
                current = current.getLink();
            }
            T removedElement = current.getLink().getInfo();
            current.setLink(current.getLink().getLink());
            this.size--;
            return removedElement; 
        }
    }
    
    // removes the first occurance of the specified element from this
    // list, if it is present
    public boolean remove(T element)
    {
        for(int i = 0; i < this.size(); i++)
        {
            if(this.get(i).equals(element))
            {
                this.remove(i);
                return true;
            }
        }
        return false;
    }
        
    // removes all of the elements from this list
    public void clear()
    {
        this.log = null;
        this.size = 0;
    }
}
