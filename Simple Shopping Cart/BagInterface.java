
/**
 * BagInterface.java - ADT Bag type
 * Describes the operations of a bag of objects
 */ 
public interface BagInterface<T>
{
    //Gets the current number of entries in this bag
    // @return The integer number of entries currently in the bag
    public int getCurrentSize();
    
    //Sees whether the bag is empty
    // @return TRUE if the bag is empty, FALSE otherwise
    public boolean isEmpty();
    
    //Adds a new entry to this bag
    // @param newEntry - the object to be added as a new entry
    // @return TRUE if add was successful, FALSE otherwise
    public boolean add(T newEntry);
    
    //Remove (general, unspecified version) an item
    //Removes one unspecified entry from this bag, if possible
    // @return either the removed entry (if successful), or NULL
    public T remove();
    
    //Remove (specific version)
    //Removes one occurrence of a given entry from the bag, if possible
    // @param anEntry - the entry to be removed
    // @return TRUE if successful, FALSE otherwise
    public boolean remove(T anEntry);
    
    //Removes all entries from this bag
    public void clear();
    
    //Counts the number of times a given entry appears in this bag
    // @param anEntry - the entry to be counted
    // @return the number of times anEntry appears in the bag
    public int getFrequencyOf(T anEntry);
    
    //Tests whether this bag contains a given entry
    // @param anEntry - the entry to find
    // @return TRUE if the bag contains anEntry, FALSE otherwise
    public boolean contains(T anEntry);
    
    //Retrieves all entries that are in this bag
    // @return A newly allocated array of all the entries in this bag
    //  NOTE:  If the bag is empty, the returned array is empty
    public T[] toArray();

}