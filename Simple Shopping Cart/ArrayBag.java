
/**
 * ArrayBag.java - array-based implementation of the Bag ADT
 */
public class ArrayBag<T> implements BagInterface<T>
{
    //declare our properties
    private T[] bag;            //the array holding our entries
    private int numEntries;     //how many entries currently in our Bag
    private final static int DEFAULT_CAPACITY = 25;     
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size
    private boolean integrityOK;    //flag value to signal data is not corrupt
    
    //define our constructors
    
    //default constructor
    public ArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }
    
    //parameterized constructor
    public ArrayBag(int capacity)
    {
        integrityOK = false;
        
        if (capacity <= 0)
        {
            bag = (T[]) new Object[DEFAULT_CAPACITY];   // capacity too small, use default value
        } else if (capacity <= MAX_CAPACITY) {  // capacity is good
            bag = (T[]) new Object[capacity];
        } else {
            throw new IllegalStateException("Attempted to create a bag that exceeds max capacity.");
        }
        
        numEntries = 0;
        integrityOK = true;
    }
    
    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }
    
    //isArrayFull() - helper method for add
    private boolean isArrayFull()
    {
        return (numEntries >= bag.length);
    }
    
    //define our interface methods
    
    //Adds a new entry to this bag
    // @param newEntry - the object to be added as a new entry
    // @return TRUE if add was successful, FALSE otherwise
    public boolean add(T newEntry)
    {
        checkIntegrity();

        //check if full, can't add, so return false
        if (isArrayFull())
            return false;
            
        integrityOK = false;
        
        //if room, add to next spot
        bag[numEntries] = newEntry;
        //increment numEntries
        numEntries++;
        
        integrityOK = true;
        return true;
        
    }
    
    //Retrieves all entries that are in this bag
    // @return A newly allocated array of all the entries in this bag
    //  NOTE:  If the bag is empty, the returned array is empty
    public T[] toArray()
    {
        checkIntegrity();
        
        //create a new array of size numEntries
        T[] resultArray = (T[]) new Object[numEntries];
        
        //copy contents of bag into the result array
        for (int i = 0; i < numEntries; i++)
            resultArray[i] = bag[i];
            
        return resultArray;
    }
    
    //Gets the current number of entries in this bag
    // @return The integer number of entries currently in the bag
    public int getCurrentSize()
    {
        return numEntries;
    }
    
    //Sees whether the bag is empty
    // @return TRUE if the bag is empty, FALSE otherwise
    public boolean isEmpty()
    {
        return (numEntries == 0);
    }
    
    //getIndexOf() - helper method, returns the index of first instance of given entry
    //  @param anEntry - the entry to look for
    //  @return index of entry if found, -1 if not found
    private int getIndexOf(T anEntry)
    {   
        boolean found = false;      //early termination flag
        int i = 0;
        int result = -1;
        
        while(!found && i < numEntries)
        {
            if (bag[i].equals(anEntry))     //We found it
            {
                found = true;               //set termination flag
                result = i;                 //save index where found
            }
            i++;
        }
        
        return result;
    }
    
    //removeEntry(int index) -- helper method for remove
    //  PRE-CONDITION:  index < numEntries
    //  @param index - the index of the element to be removed
    //  @return the element that was removed from the index, or NULL if no removal
    private T removeEntry(int index)
    {
        if (index < 0)
            return null;
            
        integrityOK = false;
        
        //save element that was removed
        T result = bag[index];
        
        //copy last element into the spot
        bag[index] = bag[numEntries - 1];
        
        //delete last spot
        bag[numEntries - 1] = null;
        
        numEntries--;
        
        integrityOK = true;
        
        return result;
        
        
    }
    
    //Remove (general, unspecified version) an item
    //Removes one unspecified entry from this bag, if possible
    // @return either the removed entry (if successful), or NULL
    public T remove()
    {
        checkIntegrity();
        
        return removeEntry(numEntries - 1);
    }
    
    //Remove (specific version)
    //Removes one occurrence of a given entry from the bag, if possible
    // @param anEntry - the entry to be removed
    // @return TRUE if successful, FALSE otherwise
    public boolean remove(T anEntry)
    {
        checkIntegrity();
        
        int index = getIndexOf(anEntry);    //find index where anEntry is found
        T result = removeEntry(index);      //user helper to remove entry from index
        
        if (result == null)
            return false;
        else
            return true;
    }
    
    //Removes all entries from this bag
    public void clear()
    {
        while(!isEmpty())
            remove();
        
    }
    
    //Counts the number of times a given entry appears in this bag
    // @param anEntry - the entry to be counted
    // @return the number of times anEntry appears in the bag
    public int getFrequencyOf(T anEntry)
    {
        int i = 0;
        int result = 0;
        
        while(i < numEntries)
        {
            if (bag[i].equals(anEntry))     //We found it
            {
                result++;                   //add 1 to our count
            }
            i++;
        }
        
        return result;
    }
    
    //Tests whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @return TRUE if the bag contains anEntry, FALSE otherwise
    public boolean contains(T anEntry)
    {
        //option 1:
        return (getFrequencyOf(anEntry) > 0);
        
        //option 2:
        //return (getIndexOf(anEntry) != -1);
    }
    
    
}