class Tree extends GenNode implements Comparable<Tree>
{
    GenNode root;

    //create tree with childless node AKA leaf
    public Tree(){
    	
    }
    public Tree(int index, int frequency)
    {
        root = new GenNode(index, frequency);
    }

    //subtree with null as root
    public Tree(Tree tree1, Tree tree2)
    {
        root = new GenNode();
        root.left = tree1.root;
        root.right = tree2.root;
        root.frequency = tree1.root.frequency + tree2.root.frequency;
    }
    
    @Override
    public int compareTo(Tree t)
    {
        return root.frequency - t.root.frequency;
    }

}