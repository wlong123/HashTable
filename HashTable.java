/**
	Hashtable object that creates a hashtable using a key and a value.
	@version 9/29/15
	@author Will Long
*/
import java.lang.Math;
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
	puts an entry into the hashtable based on the hashcode of the inputted key. If that spot is taken, the object is put in the next available spot. It also calls rehash if the capacity is above the load factor
	@param K key that is to be part of the entry added to the hashtable
	@param V value that is to be part of the entry added to the hashtable
	*/
	public void put(K key, V value)
	{
		int spot = Math.abs(key.hashCode()) % HashTable.length;
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
			if(spot == HashTable.length) //resets spot to zero if at end of the hashtable
				spot = 0;
		}
		if((float) occupied / (float) HashTable.length >= LoadFactor) //checks if capacity at load factor
			rehash();
	}

	/**
	creates a string representation of the hashtable
	@return String representation of the hashtable
	*/
	public String toString()
	{
		String s = "";
		for(int i = 0; i < HashTable.length; i++)
		{
			if(HashTable[i] != null)
			{
				s += "[" + HashTable[i].key + ", " + HashTable[i].value +  ", " + HashTable[i].hashCode() + "], ";
			}
			else
			{
				s+= "[null], ";
			}
		}
		return s;
	}
	
	/**
	rehashes the hashtable. creates a new hashtable with double the capacity of the old hashtable. all of the entries are then hashed into the hashtable 
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
		for(int i = 0; i < temporary.length; i++)
		{
			if(temporary[i] != null)
				put((K) temporary[i].key, (V) temporary[i].value);
		}
	}
	
	/**
	removes an entry in the hashtable given an inputted key. The first entry that is associated with the key is removed from the hashtable. All other entries with the inputted key are rehashed. If the inputted key isn't in the hashtable, null is returned. 
	@param K key that is to be removed from the hashtable
	@return V value that is removed from hashtable
	*/
	public V remove(K key)
	{
		int spot = Math.abs(key.hashCode()) % HashTable.length;
		for(int i = Math.abs(key.hashCode()) % HashTable.length; i < HashTable.length;i++)
		{
			if(HashTable[i] == null)
				return null;
			else if(spot == Math.abs(HashTable[i].key.hashCode() % HashTable.length))
			{
				occupied--;
				V remove = HashTable[i].value;
				HashTable[i] = null;
				i++;
				//finds all other entries with same key and rehahses them
				while(HashTable[i] != null)
				{
					if(spot == Math.abs(HashTable[i].key.hashCode() % HashTable.length))
					{
						K key1 = HashTable[i].key;
						V value1 = HashTable[i].value;
						HashTable[i] = null;
						put(key1, value1);
					}
					i++;
				}
				return remove;
			}
		}
		return null;
	}
	
	/**
	gets a value in the Hashtable given a key. If the key is not in the hashtable, it returns null
	@param K key whose value is going to be returned.
	@return V value that is associated with the inputted key
	*/
	public V get(K key)
	{
		int spot = Math.abs(key.hashCode()) % HashTable.length;
		for(int i = Math.abs(key.hashCode()) % HashTable.length; i < HashTable.length;i++)
		{
			if(HashTable[i] == null)
			{
				return null;
			}
			else if(spot == Math.abs(HashTable[i].key.hashCode()) % HashTable.length)
			{
				return HashTable[i].value;
			}
		}
		return null;
	}

	/**
	checks if the hashtable contains a given key
	@param K a key that is checked whether or not it is in the hashtable
	@return boolean true if the key is in the table and false if not
	*/
	public boolean containsKey(K key)
	{
		if(get(key) == null) 
			return false;
		return true;
	}
	
	/**
	checks if a given value is in the hashtable
	@param V a value that is checked whether or not it is in the table
	@return boolean true if the value is in the table and false if not
	*/
	public boolean containsValue(V value)
	{
		int spot = Math.abs(value.hashCode()) % HashTable.length;
		for(int i = 0; i < HashTable.length; i++)
		{
			if((HashTable[i] != null) && (spot == Math.abs(HashTable[i].value.hashCode()) % HashTable.length))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	nested class that contains K and V generics which are keys and values
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

