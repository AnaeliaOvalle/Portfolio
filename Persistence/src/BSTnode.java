
public class BSTnode<T> implements Comparable {
		public BSTnode root;
    	public String data;
    	public BSTnode left;
    	public BSTnode right;
    	
    	public BSTnode(){
    		root=null;
		
    	}

	    public BSTnode(String data)
	    {
	        this.setData(data);
	    }
	    

	    public BSTnode(String data, BSTnode left, BSTnode right)
	    {
	    	this.data=data;
	    	this.left=left;
	    	this.right=right;
	    }

        public void setRightChild(BSTnode rightChild) {
    		this.right = rightChild;
    	}

        public void setLeftChild(BSTnode leftChild) {
     		this.left = leftChild;
     	}
        
        @Override
        public String toString()
        {
            return this.getData();
        }
        
    	public int compareTo(Object o) {
    		// TODO Auto-generated method stub
    		return 0;
    	}

		
		public void buildPath(BSTnode oldTree, BSTnode newTree, BSTnode newNode) { //build path referencing old tree
			//basecase
			
			int comp= (newNode.getData()).compareToIgnoreCase(oldTree.getData());
			
			if(comp<0){
				
				if(oldTree.left==null && newTree.left==null){ //if nothing in old and new tree
					//set the new node as left child of newTree
					newTree.left=newNode;
				}
				else{
					BSTnode node= new BSTnode();
					
					//asterisk resembles previously constructed node
//					node.data=oldTree.left.data+"*";
					node.setData(oldTree.left.getData());
					newTree.left=node;	

					oldTree.buildPath(oldTree.left, newTree.left, newNode);

				}
				if(oldTree.right!=null){
					newTree.right=oldTree.right;	
				}
				
				
			}
			else if (comp>=0){
				if(oldTree.right==null && newTree.right==null){
					newTree.right=newNode;
					
				}
				else{
					BSTnode node= new BSTnode();
					node.setData(oldTree.right.getData());
					newTree.right=node;
					oldTree.buildPath(oldTree.right, newTree.right, newNode);

				}
				
				if(oldTree.left!=null){
					newTree.left=oldTree.left;
				}
			}
			
		}		


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
}
		
		
		
    
        




