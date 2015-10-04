public class HashTableRunner
{
	public static void main(String[] args)
	{
		HashTable<Integer, String> h = new HashTable<Integer, String>(10);
		h.put(1, "first");
		h.put(2, "second");
		System.out.println(h.toString());
	}
}