import java.util.Arrays;


public class PersistentStack {
	private String str;
	private int initialSize=5;
	private ListNode[] elements=new ListNode[initialSize];
	public static int index=1;
	private static int currentTime=0;
	private ListNode top= null;
	
	public PersistentStack(){ //constructor
		
	}
	
	/* Push the string elem onto the current stack, creating a new current stack. 
	 * If the array contating all of the old stacks is full, 
	 * create a new one twice as large and copy all the data across.
	 */
	public void push (String elem){

		//check if old stack if full. If it is, create stack with double the size
		if(elements.length-1<=index){
			newArray(elements);
		}
		
		//case 1: creating the first instance of a new stack 
			if (top==null){
			
			//set node to top
				top= new ListNode(elem);
				
				//first instance of stack
				if(index==1){
					elements[index]=top;
					System.out.println("Time: "+index+": Added: "+elements[index].data);	
					currentTime++;

					}

			} 
			
			//case 2: create an instance of a stack. Set new top
			else if (top.data==""){
				//set node to top
				top= new ListNode(elem);
				currentTime++;
				index++;
				elements[index]=top;
				System.out.println("Time: "+index+": Added: "+elements[index].data);	
				
				}
			
			//case 3: push onto existing stack	
			else{
				ListNode node= new ListNode(elem);
				node.next=top;
				top=node;
				currentTime++;
				index++;
				elements[index]=top;
				System.out.println("Time: "+index+": Added: "+elements[index].data);	
				
				
			}
	}

	/*
	 * Pops the top element from the current stack, creating a new current stack.
	 *  If the array contating all of 
	 *  the old stacks is full, create a new one twice as large and copy all the data across
	 */
	public String pop() {
		
		//check if old stack if full. If it is, create stack with double the size

		if(elements.length-1<=index){
			
			newArray(elements);
		}
		
		ListNode node= top;
		
		//case 1: the list is empty
		if(top.data==""){
			node.data="";
			System.out.println("List is empty.");
			currentTime++; //not sure if I should do this? 
			index++;
			
		}
		//case 2: pop the last element of the list
		else if(top.next==null){
			currentTime++;
			index++;
			elements[index]=top;
			top.data= "";
			System.out.println("List will be empty after this pop");
			
		}
		
		//case 3: pop the top of the list and move the top to next element
		else{
			System.out.println("Time: "+currentTime+": Popped: "+elements[index].data);
				top=top.next;
				index++;
				currentTime++;
				elements[index]=top;
			}
		
		return node.data;	
		
	}
		
	public ListNode[] newArray(ListNode[] list){
		
//		System.out.println("*******NEW LIST*********");
		
		ListNode[] newElements= new ListNode[elements.length*2];
		
		//copy all data across 
		for(int i=0; i<elements.length; i++){
			newElements[i]= elements[i];
			}
		
		elements= newElements;

		return elements;
	}
		
	
	/*
	 * Returns the "current time", that is, how many opeations have been done on the stack.
	 *  Each time a push or pop operation is done, the curentTime is incremented
	 */
	public int currentTime(){
		return currentTime;
	}
	

	/*
	 * Returns the size (number of elements in the stack) at a given time
	 */
	public int size(int time){
		ListNode node= elements[time];
		int size=0;
		
		if(node.data!=""){
			while(node!=null){
				size++;
				node=node.next;	
			}
		}
		
//		System.out.println("*****SIZE*****");
//		System.out.println("Size is: "+ size);
//		System.out.println("**************");
		return size;
	}
	
	/*
	 * Returns the size (number of elements in the stack) at the current time. Equivalent to size(currentTime()).
	 */
	public int size(){
		ListNode node= elements[index];
		int size=0;
		if(node.data!=""){
			while(node!=null){
				size++;
				node=node.next;	
			}
		}

//		System.out.println("*****SIZE*****");
//		System.out.println("Size is: "+ size);
//		System.out.println("**************");
		return size;
		
	}
	
	/*
	 *  Returns an array of all of the elements at a given time. The length of the returned string array 
	 *  should be the same as the number of elements in the stack at that time. reversed == false, then first element in the return 
	 *  array is the top of the stack, if reversed == true, the last element in the return array is the top of the stack
	 */
	public String[] getAllElements(int time, boolean reversed){
		System.out.println("*****Getting all elements in current time:"+time+"*****");
		ListNode node= elements[time];
		ListNode temp1= node;
		ListNode temp2= node;
		
		//iterate through current node list to get size of array
		int size=0;
		while(temp1!=null){
			size++;
			temp1=temp1.next;	
		}
		
		//create String array
		String[] elementstoString= new String[size];
		int indexString=0;
		
		if (reversed){
			System.out.println("Reverse detected.");
			
//			while temp isn't null and we don't run into an Array out of bounds exception
			while(temp2!=null){
				elementstoString[--size]=temp2.data;
				temp2=temp2.next;
			}
			
		}
		else{
			while(temp2!=null){
				elementstoString[indexString]=temp2.data;
				temp2=temp2.next;
				indexString++;
			}
			
		}
		
		return elementstoString;
	}
	


}
