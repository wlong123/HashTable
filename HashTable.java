/**
	Hashtable object that creates a hashtable using a key and a value.
	@version 9/29/15
	@author Will Long
*/
public class HashTable<K,V>
{
	private static final double LoadFactor = .6;
	private int occupied;
	private Entry<K,V>[] HashTable;
	
	/**
	default constructor
	*/
	public HashTable()
	{
		HashTable = new Entry[100];
		occupied = 0;
	}
	
	/**
	constructor that takes in a capacity
	@param capacity inputted capacity of the hashtable
	*/
	public HashTable(int capacity)
	{
		HashTable = new Entry[capacity];
		occupied = 0;
	}
	
	/**
	puts an inputted object into its spot in the hashtable. If that spot is taken, the object is put in the next available spot. 
	@param obj Object to be hashed and added to hashtable
	*/
	public void put(K key, V value)
	{
		int spot = key.hashCode() % HashTable.length;
		while(spot < HashTable.length)
		{
			if(HashTable[spot] == null)
			{
				HashTable[spot] = new Entry(key, value);
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
		//temporary array is occupied with all vlaues of hashtable
		Entry[] temporary = new Entry[HashTable.length]; 
		for(int i = 0; i < temporary.length;i++)
		{
			if(HashTable[i] != null)
				temporary[i] = HashTable[i];
		}
		//create new hashtable which is occupied with values in temporary array
		HashTable = new Entry[HashTable.length * 2];
		for(int i =0; i < temporary.length; i++)
		{
			if(temporary[i] != null);
				put((K) temporary[i].key, (V) temporary[i].value);
		}
	}
	
	public V remove(K key)
	{
		int spot = key.hashCode() % HashTable.length;
		for(int i = 0; i < HashTable.length;i++)
		{
			if(spot == (HashTable[i].key.hashCode() % HashTable.length))
			{
				occupied--;
				V remove = HashTable[i].value;
				HashTable[i] = null;
				return remove;
			}
		}
		return null;
	}
	/*
	public V get(K key)
	{
		
	}
	
	public boolean containsKey(K key)
	{
		
	}
	
	public boolean containsValue(V value)
	{
		
	}
	
	*/
	private class Entry<K,V>
	{
		public K key;
		public V value;
		public Entry(K k, V v)
		{
			key = k;
			value = v;
		}
	}
}

