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