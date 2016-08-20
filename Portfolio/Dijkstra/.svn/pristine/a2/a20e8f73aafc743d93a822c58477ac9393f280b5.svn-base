
public class LinkedList
{

	public Link head;
	public Link tail;
	private int length;

	LinkedList() 
	{
		head = tail = new Link();
		length = 0;
	}


	public void clear() 
	{
		head.setNext(null);
		tail = head;
		length = 0;
	}

	public int size() 
	{
		return length;
	}

	public void add(Link newLink) 
	{
		newLink.setNext(head.next);
		head.setNext(newLink);
		length++;
		
	}
	
	public void add(int vertex, String key) 
	{
		tail.setNext(new Link(vertex, key, null));
		tail = tail.getNext();
		length++;
	}

	public void add(int index, int vertex, String key) 
	{
		Assert.notFalse(index >= 0 && index <= length,"Index not in list");
		Link tmp = head;
		for (int i = 0; i < index; i++)
		{
			tmp = tmp.next;
		}
		tmp.next = new Link(vertex,key, tmp.next);
		length++;
	}

	public void remove(int index)
	{
		Assert.notFalse(index >= 0 && index < length);
		Link tmp = head;
		for (int  i = 0; i < index; i++)
		{
			tmp = tmp.next;
		}
		tmp.next = tmp.next.next;
		length--;
	}

	public void remove(Object key)
	{
		Link tmp = head;
		
		while (tmp.next != null && !tmp.next.key.equals(key))
		{
			tmp = tmp.next;
		}
		
		
		if (tmp.next != null)
		{
			tmp.next = tmp.next.next;
			length--;
		}

	}

	public Object get(int index)
	{
		Assert.notFalse(index >= 0 && index < length,"Index not in list");
		Link tmp = head.next;
		for (int i = 0; i < length; i++)
		{
			tmp = tmp.next;
		}
		return tmp.key;
	}


	class Link {


		public String key;
		public int vertex;
		public Link next;

		Link(int vertex, String key,  Link next) {
			this.key=key;
			this.vertex=vertex;
			this.next=next;
		}

		Link(Link next) {
			this.next=next;
		}

		Link() { 
			
			
		}

		Link getNext() {
			return next; 
		}
		
		void setNext(Link nextelem) {
			next = nextelem;
		}

		String getKey() {
			return key;
		}
		
		void setKey(String key) {
			this.key=key;
		}
		
		int getVertex() {
			return vertex;
		}

		void setVertex(int vertex) {
			this.vertex=vertex;
		}
		
		
		
		
	}

}


