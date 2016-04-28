	class GenNode{
		
    // actual ascii character value
    int index;

    // count of characters
    int frequency;

    // left child
    GenNode left;

    //right child
    GenNode right;


    public GenNode()
    {

    }

    public GenNode(int index, int frequency)
    {
        this.index = index;
        this.frequency = frequency;
    }
    
    public boolean isLeaf(GenNode tree)
    {
        return (tree.left == null && tree.right==null);
    }

    public void setRightChild(GenNode rightChild) {
		this.right = rightChild;
	}

    public void setLeftChild(GenNode leftChild) {
 		this.left = leftChild;
 	}
    
    @Override
    public String toString()
    {
        return this.index + " : " + this.frequency;
    }
}