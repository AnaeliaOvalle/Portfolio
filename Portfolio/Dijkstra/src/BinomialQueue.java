
public class BinomialQueue {

	Node head; 
	int sizeHeads;
	int size;
	int index;
	Node[]Pointers;
	
	public BinomialQueue(int size){
		head=null;
		this.size=size;
		Pointers=new Node[size];
	}
	
	public BinomialQueue(Node node){
		head=node;

	}
	
	public BinomialQueue(){
		head=null;
	}
	
	
	
	public void insertElem(int element, int key) { //makes a 1-node BinomialQueue
		
		Node x= new Node(element, key);
		Pointers[index]=x;
		index++;
		
		BinomialQueue newHeap= new BinomialQueue(x);
		
		if (head==null){
			
			head=x;
			sizeHeads++;
		}
		else{
			
			head= BinomialQueueUnion(newHeap);
			sizeHeads++;
		}
		

	
	}
	
	
	public int removeSmallest(){

		Node minRoot= BinomialQueueMinimum(this); 
		
		
		if (minRoot==null){
			return 0;
		}
		
		
		Node prev= this.head;
		
		//if the head is the minimum element
		if (prev==minRoot){
			this.head=prev.sibling;
		}
		else{
			
			//move to next root until the following in the minimum
			while(prev!=null && prev.sibling!=minRoot){
				prev=prev.sibling;
			}
				
			//remove min priority queue 
			if(prev!=null && prev.sibling!=null){
				
				//attach possible siblings to new root list!
				if(prev.sibling.sibling!=null){
					prev.sibling= prev.sibling.sibling;
				}
				else{
					prev.sibling=null;
				}
			}
			else{
				prev.sibling=null;
			}
			
		}
		
		//make new binomial queue
		BinomialQueue removeKids= new BinomialQueue();
		
		//if the minremoved has no children
		if(minRoot.child==null){
			removeKids.head=this.head;
		}
		else{
			
			//reverse child list
			Node tmp=minRoot.child;
						
			if(tmp!=null){
				
				Node currNode=tmp;
				Node prevNode=null;
				Node nextNode=null;
				
				//if there's more than one element in child list
				if(tmp.sibling!=null){
					
					//reverse list
					while(currNode!=null){
						currNode.parent=null;
						nextNode = currNode.sibling;
						currNode.sibling = prevNode;
						prevNode = currNode;
						currNode = nextNode;
						}
				
				removeKids.head=prevNode;
				
				//one element in child list
				}else{
					
					tmp.parent=null;
					removeKids.head=tmp;
				}
			}
			
			Node tmp2=removeKids.head;
			
			while(tmp2!=null){
				
				tmp2=tmp2.sibling;
			}
		
			head=BinomialQueueUnion(removeKids);
		}
		
		return minRoot.element;
		
	}
	

	public void decreaseKey(int x, int k) throws IllegalArgumentException{
		
		Node getNode=null;
		int point=0;
		
		//find the element to decrease
		for(int i=1; i<Pointers.length; i++){
			if (Pointers[i].element==x){
				point=i;
			}
		}
		
		getNode=Pointers[point];
		
		if (k>getNode.key){
			throw new IllegalArgumentException("Key cannot be decreased");
		}
		else{
			getNode.key=k;//change the key
			Node kid=getNode; //kid
			Node parent=kid.parent; //parent
			int temp1;
			int temp2;
			
			 //while kid is less than the parent, bubble up!
			while(parent!=null && kid.key<=parent.key){
				
				//find and swtich pointers 
				int parentPos=0;
				
				for(int i=1; i<Pointers.length; i++){
					if (Pointers[i].element==parent.element){
						parentPos=i;
					}
				}
				
				//parent becomes lower value
				Pointers[point]=kid.parent;
				Pointers[parentPos]=kid;
				temp1=parent.key;
				parent.key=kid.key;
				kid.key=temp1; 
				
				//exchange elements
				temp2=parent.element;
				parent.element=kid.element;
				kid.element=temp2;
				
				kid=parent; //move kid up
				parent=kid.parent; //get parent of moved kid
							
			}
			
		}
		
	}
	
	public void findMin(){
		Node minHeap=BinomialQueueMinimum(this);
		System.out.println(minHeap.element);
		
		int initialVal=Integer.MAX_VALUE;
		Node min=null;
		
		for (int i = 1;i<Pointers.length; i++){
			if (min==null && Pointers[i].key<initialVal){
				min=Pointers[i];
				
			}
			else if(Pointers[i].key<min.key){
				min=Pointers[i];
			
			}
		}

	}
	
	//returns a pointer to the node with the minimum key in an n-node of heap H
	public Node BinomialQueueMinimum (BinomialQueue H){
		
		Node minNode= null;
		Node x= H.head;
		int minVal= Integer.MAX_VALUE;
		
		while(x!=null){
			//look for minimum binomial tree
			if (x.key<minVal){
				minVal=x.key;
				minNode=x;
			}
			
			x=x.sibling;
		
		}
		return minNode;
	}
	
	//makes low the new head of the linked list of node high's children
	public Node BinomialLink(Node low, Node high){
		high.parent=low;
		high.sibling=low.child;
		low.child=high;
		
		low.degree=low.degree+1;
		
		return low;
		
	}
	
	//unite 2 binomial heaps, returning the resulting heap. 
	public Node BinomialQueueUnion(BinomialQueue H2){
	
		BinomialQueue H= new BinomialQueue();
		H.head=BinomialQueueMerge(this.head, H2.head);

		if (H.head==null){
			return null;
		}
		else{
			Node prev=null;
			Node x= H.head;
			Node next=x.sibling;
		
			//move along the root list
			while(next!=null){
				if(x.degree!=next.degree || next.sibling!=null && next.sibling.degree==x.degree){
					prev=x;  //case 1: when degree of tree 1 isnt the same as degree of tree 2, move forward
					x=next; //case 4: when x is the first of 3 roots with equal degree

				}
				//case 2: tree 1 and tree 2 have same degree but key of tree 1 is less than key of tree 2 
				else if(x.degree==next.degree && x.key<=next.key){
					x.sibling=next.sibling; //case 3: 
					x=BinomialLink(x, next);
				}
				
				//case 3: Previous tree doesnt exist, tree 1 and tree 2 have same degree but key of tree 2 is less than key of tree 1
				else if(prev==null && x.degree==next.degree && next.key<=x.key){
					H.head=next;
					next=BinomialLink(next,x);
					x=next;
				}
				
				//case 4: Previous tree exists, tree 1 and tree 2 have same degree but key of tree 2 is less than key of tree 1
				else if(prev!=null && x.degree==next.degree && next.key<=x.key ){
					prev.sibling=next;
					next=BinomialLink(next,x);
					x=next;
				}
				
			next=x.sibling;
			
			}
					
		}
		return H.head; //return linked list H that is sorted by degree
	}
	
	//merges the root lists of binomial heaps left and right into a singly linked list sorted by degree
	private Node BinomialQueueMerge(Node left, Node right) {
		
		 //base cases: if left list is done or right list is done
		 if(left==null){
			 return right;
		 }
		 if(right==null){
			 return left;
		 }
			
		 //new list to become sorted list 
		 Node currHead=null;
		 Node heap1=left;
		 Node heap2=right;
		   
		  if(heap1.degree<=heap2.degree){
			  currHead=heap1;
			  currHead.sibling=BinomialQueueMerge(heap1.sibling, right);
		  }
		  else{
			  currHead=heap2;
			  currHead.sibling=BinomialQueueMerge(left, heap2.sibling);
		  }
		  
		 return currHead;
		 
		 }
	
	public boolean isEmpty(){
		
		if (head==null){
			return true;
		}
		return false;
	}
	
	public void printQueue() {
		System.out.println("****Initial Binomial Q*****");
		Node tmp1;
		Node tmp2;
		Node tmp3;
		Node tmp4;
		Node tmp5;

	
	for(tmp1=this.head;tmp1!=null;tmp1=tmp1.sibling){
		System.out.println("Value: "+tmp1.element+" Priority: "+tmp1.key);
		
		for (tmp2=tmp1.child;tmp2!=null; tmp2=tmp2.child){
			
			System.out.println("	Child: "+tmp2.element+" Priority: "+tmp2.key);

			
			for(tmp3=tmp2.sibling; tmp3!=null; tmp3=tmp3.sibling){
				System.out.println("		Child's sibling: "+tmp3.element+" Key: "+tmp3.key);
				
				for(tmp4=tmp3.child; tmp4!=null;tmp4=tmp4.sibling){
					System.out.println("			Sibling's kid: " +tmp4.element+" Key: "+tmp4.key);
					
					for(tmp5=tmp4.sibling; tmp5!=null; tmp5=tmp5.sibling){
						System.out.println("		Sibling's kid's sibling: "+tmp5.element+" Key: "+tmp5.key);
						
					}
				}
			}
		}	
	}

}

	class Node{
		
		Node parent;
		Node child;
		Node sibling;
		int key; 
		int degree;
		int element; 
		int priority;
		
		public Node(int key){
			this.key=key;
			parent=null;
			child=null;
			sibling=null;
			degree=0;
			
		}
		public Node(int element, int key){
			this.element=element;
			this.key=key; 
			parent=null;
			child=null;
			sibling=null;
			degree=0;
		}
	}
	

}
