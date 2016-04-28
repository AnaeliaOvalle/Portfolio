import java.util.Arrays;
public class PersistentBST {
	
	private String key; 
	public  BSTnode root;
	public static String[] theElements;
	public int currTime=0; 
	private static int initialSize=5;
	public static BSTnode[] historyArr=new BSTnode[initialSize];
	
	//Create a new BST, using key as the sorting key. More on what we mean by this later, under BST Keys
	public PersistentBST(String key){
		this.key= key;
		root=null;
		
	}
	
	//Insert an element into the BST, creating a new tree under a new time step.
	public void insert(String elem) {
		
		//check history array will be full. If so, create a new array with double the size
		if(historyArr.length-1<=currTime){
			newArray(historyArr);
		}
		
		//parse the json object
		String[] STRelements=parseString(elem);
		
		//get the element from the parsed object using the key value
		elem= getElement(STRelements, elem);
		
		BSTnode newNode= new BSTnode();
		newNode.setData(elem);

		
			
		//case 1: add first node
		if(root==null){
			root=newNode;
			currTime++; //increment time
			historyArr[currTime]=root; //add node to time 1 in elements
			System.out.println("Time: "+currTime+": Adding: "+newNode.getData());
		}
		
		//case 2: add another node referencing previous tree (if not null)
		else{
			if (historyArr[currTime]!=null){
				
				BSTnode newTree= new BSTnode();
				root=newTree; 
				BSTnode oldTree= historyArr[currTime];
				
				//traverse to check old tree
//				preorderTraverse(oldTree);
//				System.out.println();
				
				//copy the data of previous node to new node
				root.setData(oldTree.getData()); 
				
				//build tree path based on old tree,add new node, and fix the new tree
				root.buildPath(oldTree, root, newNode);
				currTime++;
				System.out.println("Time: "+currTime+": Adding: "+newNode.getData());
				historyArr[currTime]=root;
							
			}
		}
			
	}
	

	private String getElement(String [] STRelements, String elem) {
		elem= null;
		boolean foundKey=false;
		for (int i=0; i<STRelements.length; i++){
			
			//check if key matches (even position)
			if (i%2==0){
				
				//return element in position after the key 
				if(STRelements[i].contains(key) && foundKey==false){
					foundKey=true;
					elem=STRelements[i+1];
				}	
			}
		}
		return elem;
		
		
	}

	private String[] parseString(String elem) {
		
		//step 1: parse JSON object by comma
		String[] parseComma=elem.split(",");
		String[] parseFinal= new String[initialSize];
		
		//parse the JSON object
		int x=0;
		int values=0;
		
		//step 2: parse JSON object by colon
		for(int i=0; i<parseComma.length;i++){		
			if(parseComma[i].contains(":")){
				String[] tmp=parseComma[i].split(":");
				
				for(int j=0; j<tmp.length;j++){
					if(x>=parseFinal.length-1){
						parseFinal=newArray(parseFinal);
					}
					if(parseFinal[x]==null){
						parseFinal[x]=tmp[j];
						x++;
					}
					
					values++;
				}

			}	
		}

		
		String[] STRelements= new String[values];
		
		for(int i=0; i<STRelements.length;i++){
			STRelements[i]=parseFinal[i];
		}
		
		
		//remove brackets and quotes
		for(int i=0; i<STRelements.length; i++){
			if(STRelements[i]!=null){
				if(STRelements[i].contains("{")){
					STRelements[i] = STRelements[i].replace("{", "");
				}
				
				if(STRelements[i].contains("}")){
					STRelements[i] = STRelements[i].replace("}", "");
				}
				
				if(STRelements[i].contains("\"")){
					STRelements[i] = STRelements[i].replace("\"", "");
				}
				
			}	
		}
		
		return STRelements;

	}


	private void preorderTraverse(BSTnode oldTree) {
		if(oldTree==null){
			return;
		}
		System.out.print(oldTree.getData()+"-->");
		preorderTraverse(oldTree.left);
		preorderTraverse(oldTree.right);
	}
	

	//new parse array with double size just in case there are more elements than original length
	private String[] newArray(String[] parseFinal) {
		
		String[] newParse= new String[parseFinal.length*2];
		
		//copy all data across 
		for(int i=0; i<parseFinal.length; i++){
			newParse[i]= parseFinal[i];
			}
		//assign elements as new Elements array;
		parseFinal= newParse;

		return parseFinal;
		
	}
	
	//new history array with double size just in case there are more elements than original length
	public BSTnode[] newArray(BSTnode[] list){
			
		BSTnode[] newElements= new BSTnode[historyArr.length*2];
		
		//copy all data across array
		for(int i=0; i<historyArr.length; i++){
			newElements[i]= historyArr[i];
		}
		
		historyArr= newElements;

		return historyArr;
	}
	

	//Deletes an element from the BST, creating a new tree under a new time step.
	public void delete(String elem){
		
		//create a larger history array
		if(historyArr.length-1<=currTime){
			newArray(historyArr);
		}		
		
		//parse JSON object
		String[] STRelements=parseString(elem);
		
		//get the element from the parsed object using the key value
		elem= getElement(STRelements, elem);
		
		
		BSTnode oldTree= historyArr[currTime];
		
		currTime++;
		System.out.println("Time: "+currTime+": Deleting: "+elem);
		
		//delete node by copying the path of the node to delete then recombining the other nodes
		root=delete(oldTree,elem);
		
		historyArr[currTime]=root;
		
	    	   
	    }  
	
	
	//Returns true if elem is in the BST at time "time".
	//This operation does not change any tree and does not change the current time.

	  public BSTnode delete(BSTnode tree, String elem) 
	   {
	     if (tree == null) 
	        return null;
	     if (tree.data.compareTo(elem) == 0) 
	     {    	 
	    	 //if the node is a leaf
	        if (tree.left == null)
	        	return tree.right;
	        else if (tree.right == null)
	        	return tree.left;
	        else 
	        {
	        	//if the node has one child
			   if (tree.right.left == null) 
		       {
				   	BSTnode copy= new BSTnode (tree.data, tree.left, tree.right);
		             copy.data = tree.right.data;
		             copy.right = tree.right.right;
		             return copy;
		       } 
		       else 
		           { 
		    	   //if the node has 2 children
		        	 BSTnode copy= new BSTnode (tree.data, tree.left, tree.right);
		             copy.data = removeSmallest(copy.right);
		             return copy;
		            }
	         }
	      } 
	      else  if (elem.compareTo(tree.data) < 0) 
	      {
	    	 BSTnode copy= new BSTnode (tree.data, tree.left, tree.right);
	         copy.left = delete(tree.left, elem);
	         return copy;
	      } 
	      else 
	      {
	    	 BSTnode copy= new BSTnode (tree.data, tree.left, tree.right);
	         copy.right = delete(tree.right, elem);
	         return copy;
	      }
	   }  
	 
	  	public String removeSmallest(BSTnode copy) {
			if (copy.left.left == null) {
				String smallest = copy.left.getData();
			    copy.left = copy.left.right;
			     return smallest;
			 } 
			else 
			      {
			         return removeSmallest(copy.left);
			      }
			   }
	

	public boolean find(String elem) 
	 {	
		System.out.println("*****Finding: "+elem+"*****");
		BSTnode tree= historyArr[currTime];
		
		boolean found= findBST(tree,elem);
		if (found){
			System.out.println("Found= "+true);
			return true;
		}
		System.out.println("Found= "+false);
		return false;
	      
	 }
	

	public boolean find(String elem, int time) 
	 {	
		//if element is not parsed
		if (elem.contains(":")){
			String[] STRelements=parseString(elem);
			
			elem= getElement(STRelements, elem);
		}
		System.out.println("Finding: "+elem);
	
		BSTnode tree= historyArr[time];
		
		if (tree==null){
			return false;
		}
		
		boolean found= findBST(tree,elem);
		
		if (found==true){
			return true;
		}
		else{
			return false;
		}

	 }
	
	//helper method for find
	private boolean findBST(BSTnode tree, String elem) {
	
		if (tree== null){
			return false;
		}
		if (elem.compareTo(tree.getData()) == 0) {
		     return true;
		}
		if (elem.compareTo(tree.getData()) < 0){
		    return findBST(tree.left, elem);
		}
		
		else{
		    return findBST(tree.right, elem);
		}
		
	}

	//Returns the "current time", that is, how many opeations have been done on the BST. 
	//Each time an element is inserted or deleted, the curentTime is incremented.
	public int currentTime() {
		return currTime;
		
	}
	
	//Returns the size (number of elements in the BST) at a given time
	public int size(int time) {
//		System.out.println("*****Getting the size of BST at time: "+time+"*****");
		root=historyArr[time];
		return(size(root)); 
		
		}

	
	//Returns the size (number of elements in the BST) at the current time. Equivalent to size(currentTime()).
	public int size() {
//		System.out.println("*****Getting the size of BST at current time: "+currTime+"*****");
		root=historyArr[currTime];
		return(size(root)); 
		
		}

	//helper method to find size of BST
	private int size(BSTnode node) { 
		if (node == null) {
			  return(0); 
		  }
		else { 
		    return(size(node.left) + 1 + size(node.right)); 
		  } 
		} 
	
	//Returns a sorted array of all elements in the BST at the given time.
	//The length of the array should be equal to the size of the returned BST
	public String[] getAllElements(int time){
		System.out.println("*****Getting all elements in time:"+time+"*****");
		BSTnode tree= historyArr[time];
		theElements= new String[size(time)];
		int index=0;
		inOrderHelper(tree, theElements, index);
		
		return theElements;
		
	}
	
	//Returns a sorted array of all elements in the BST at the current time. Equivalent to getAllElements(currentTime())
	public String[] getAllElements() {
		System.out.println("*****Getting all elements in current time:"+currTime+"*****");
		BSTnode tree= historyArr[currTime];
		theElements= new String[size(currTime)];
		int index=0;
		inOrderHelper(tree, theElements, index);
				
		return theElements;
	}

	//properly orders theElements array by recursively adjusting the index with inOrder traversal
	private static int inOrderHelper(BSTnode tree, String [] theElements, int index){
		if(tree==null){
			return index;
		}
		
	    index= inOrderHelper(tree.left, theElements, index);
	    theElements[index]=tree.getData();
	    index++;
	    index=inOrderHelper(tree.right, theElements, index);
	    
	    return index;
	    
	
	}

}


