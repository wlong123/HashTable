public class HashTable
{
	private static final double LoadFactor = .6;
	private int occupied;
	private Object[] HashTable;
	
	//default constructer
	public HashTable()
	{
		HashTable = new Object[100];
		occupied = 0;
	}
	
	//constructor that takes in a capacity
	public HashTable(int capacity)
	{
		HashTable = new Object[capacity];
		occupied = 0;
	}
	
	//variable spot is set to hashcode value. if that spot in the hashtable is empty it places it there and is done. Otherwise, spot is increased by 1. After placing the object, it checks whether it is at the load factor and rehashes if it is.
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
		if((float) occupied / (float) HashTable.length >= LoadFactor)
			rehash();
	}
	
	//goes through hashtable and creates a string representation of it
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
	
	//I created a temporary array and made it a copy of hashTable. I then created a new HashTable with double the length and copied the values from Temporary into it
	public void rehash()
	{
		Object[] Temporary = new Object[HashTable.length];
		for(int i = 0; i < Temporary.length;i++)
		{
			if(HashTable[i] != null)
				Temporary[i] = HashTable[i];
		}
		HashTable = new Object[HashTable.length * 2];
		for(int i =0; i < Temporary.length; i++)
		{
			if(Temporary[i] != null);
				HashTable[i] = Temporary[i];
		}
	}
}

public class HashTableRunner
{
	public static void main(String[] args)
	{
		HashTable h = new HashTable();
		for(int i = 0; i < 100;i++)
		{
			h.put(i);
		}
		System.out.println(h.toString());
	}
}



