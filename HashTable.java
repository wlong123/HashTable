/**
	Hash Table Project
For this project, you will be creating a simple HashTable object.

API - Version 0

This version of HashTable will do a simple put using an object's hashcode. It does not use separate key and value objects. Create the appropriate private fields and set a default load factor.

HashTable()

Default constructor. Initializes to capacity 100.

HashTable(int capacity)

public void put(Object obj)

Puts the object in the hashtable. Deals with collisions by placing the object in the next open spot (OR ask John Lima for an even better way of finding an open spot!).

public String toString()

String representation of the HashTable.

private void rehash()

Doubles the size of the HashTable and rehashes each item contained within. Should be called anytime calling the put function makes the current fill of the HashTable exceed the load factor.
	@version 9/29/15
	@author Will Long
*
public class HashTable
{
	private static final double LoadFactor = .6;
	private int occupied;
	private Object[] HashTable;
	
	/**
	default constructor
	*/
	public HashTable()
	{
		HashTable = new Object[100];
		occupied = 0;
	}
	
	/**
	constructor that takes in a capacity
	@param capacity inputted capacity of the hashtable
	*/
	public HashTable(int capacity)
	{
		HashTable = new Object[capacity];
		occupied = 0;
	}
	
	/**
	puts an inputted object into its spot in the hashtable. If that spot is taken, the object is put in the next available spot. 
	@param obj takes in any object
	*/
	public void put(Object obj)
	{
		int spot = obj.hashCode() % HashTable.length;
		while(spot < HashTable.length)
		{
			if(HashTable[spot] == null)
			{
				HashTable[spot] = obj.hashCode();
				spot+=HashTable.length + 1;
				occupied += 1;
			}
			else 
			{
				spot+=1;
			}
			if(spot == HashTable.length)
				spot = 0;
		}
		if((float) occupied / (float) HashTable.length >= LoadFactor) //checks if capacity at load factor
			rehash();
	}
	
	/**
	creates a string representation of the hashtable
	@return String repreentation of the hashtable
	*/
	public String toString()
	{
		String s = "";
		for(int i = 0; i < HashTable.length; i++)
		{
			if(HashTable[i] != null)
			{
				s += HashTable[i].hashCode() + ", ";
			}
			else
			{
				s+= "null, ";
			}
		}
		return s;
	}
	
	/**
	rehashes the hashtable. creates a new hashtable with double the capacity of the old hashtable. The values in the old hashtable are placed into the new hashtable
	*/
	public void rehash()
	{
		//temporary arra is occupied with all vlaues of hashtable
		Object[] Temporary = new Object[HashTable.length]; 
		for(int i = 0; i < Temporary.length;i++)
		{
			if(HashTable[i] != null)
				Temporary[i] = HashTable[i];
		}
		//create new hashtable which is occupied with values in temporary array
		HashTable = new Object[HashTable.length * 2];
		for(int i =0; i < Temporary.length; i++)
		{
			if(Temporary[i] != null);
				HashTable[i] = Temporary[i];
		}
	}
}

