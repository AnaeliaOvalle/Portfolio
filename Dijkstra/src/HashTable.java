import java.util.Arrays;


public class HashTable extends LinkedList {
	String[] names;
	LinkedList[] hashArr;
	
	int arrSize=1223; //prime number
	int nameSize=1223;
	int numVertex=0;
	
	public HashTable(){

		hashArr=new LinkedList[arrSize];
		Arrays.fill(hashArr, null);
		
		//make new list of names
		names=new String[nameSize];
		
	}
	
	//insert only in names array
	public void insert (String key){
		
		if(numVertex==nameSize){
			names=newArray();
		}
		
		if (!key.equals(".")){
		
			names[numVertex]=key;
			
			//insert into hashtable
			insert(numVertex, key);
			
			numVertex++;
		}
		
	}
	
	//insert 
	public void insert(Integer integer, String key) {
		
		int hashValue = elfHash(key);
		
		names[integer]=key;
		
		int i= hashValue%arrSize;
		
		Link newLink= new Link(integer, key, null);
	
		if(hashArr[i]==null){
			
			//case 1: create new list in table
			LinkedList list= new LinkedList();
			hashArr[i]=list;
			
			list.add(newLink);
		}
		
		else{
			
			//case 2: add to existing list
			LinkedList list=hashArr[i];
	
			list.add(newLink);
				
		}
	}

	private int elfHash(String key) {
		
		int hashValue = 0;
		long g;
		int j;
		
		for (j=0; j<key.length(); j++) {
			hashValue= (hashValue << 4) + (int) key.charAt(j);
			g = hashValue & 0xF0000000L;
			if (g != 0){
				hashValue ^= g >>> 24;				
				hashValue &= ~g;
			}
		}
		
		return hashValue;
	}

	private String[] newArray() {
		
		String[] newArr= new String[names.length*2];
		
		//copy all data across list
		for(int i=0; i<names.length; i++){
			newArr[i]= names[i];
			
			}
		
		names= newArr;

		return names;
		
	}

	public Object find(String key) {
		
		int hashValue=elfHash(key);
		
		int i= hashValue%arrSize;
		
		Link tmp= new Link();
		Object found=null;
		
		if(hashArr[i]!=null){
			
			LinkedList list= hashArr[i];

			for(tmp= list.head.getNext(); tmp!=null; tmp=tmp.next){
				
				if(tmp.getKey().equals(key)){
					
					found=tmp.getVertex();
				}
			}
			
		}
		
		return found;
	}

	public void delete(String key) {

			
		int hashValue=elfHash(key);
		int i= hashValue%arrSize;
		
		Link tmp= new Link();
		
		if(hashArr[i]!=null){
			
			LinkedList list= hashArr[i];
			list.remove(key);
			
			tmp = list.head;
			
			while (tmp.next != null && !tmp.next.key.equals(key))
			{
				tmp = tmp.next;
			}
			
			names[tmp.getVertex()]=null;
			
			if (tmp.next != null)
			{
				tmp.next = tmp.next.next;

			}

		}
			
	}
		
		
}


	

