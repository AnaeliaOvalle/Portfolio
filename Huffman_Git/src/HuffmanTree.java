
public class HuffmanTree extends Tree
{
	private static Tree superTree;
	private  int[] freqArray= new int[256];
	private BinaryFile outputBinary, inputBinary;
	private TextFile outputText, inputText;
	private int huffmanLength=0;
	private int inputSize=0;
	private String[] binaryTable = new String[256];
	private static int treeArrayCount=0;
	private String encoded_String="";
	private boolean verbose;
   
    
//create huffman tree from *text file*
public HuffmanTree(TextFile inputText, BinaryFile outputBinary, boolean verbose) {
	this.inputText=inputText;
	this.outputBinary=outputBinary;
	this.superTree = createHuffman();
	this.verbose=verbose;
		
	//create lookup table for each character
	createKey(binaryTable, superTree.root, encoded_String);
		
	//calculate size of huffman tree
	getHuffmanSize(superTree.root);
	
	}
    
//create huffman tree from *binary file*
	
	public HuffmanTree(BinaryFile inputBinary, TextFile outputText){
    	this.inputBinary=inputBinary;
    	this.outputText=outputText;
    	
    	//build huffmanTree
    	this.superTree=buildTree(superTree);

    }
    
    
 //get total huffman file size
	public void getHuffmanSize(GenNode node){
		//add 32 bits for binary header +16 bits for 'HF'
		huffmanLength+=48;
		
		//add the tree size
		getTreeSize(node);
		
		
		inputText.rewind();
		char c;
		
		//add the encoded size of the text
		while(!inputText.EndOfFile()){
			
			c = inputText.readChar();
			
			int i = (int) c;
			String str = binaryTable[i];
			
			for (int j = 0; j < str.length(); j++) {
			
				//huffmanLength for each character* frequency of that character
				if(str.charAt(j) == '1'){
					huffmanLength++;
					
				}else if (str.charAt(j) == '0'){
					huffmanLength++;
					}
			}
		}

	}
	
	//assist method for computing size
	private void getTreeSize(GenNode node) {
		if(node == null){
			return;
		}
		
		if(node.left == null && node.right == null){
			huffmanLength+=9;
			return;
		}
		
		huffmanLength++;
		getTreeSize(node.left);
		getTreeSize(node.right);
		
	}
	
	public void isVerbose(){
		if (verbose){
			
			//print frequency of each character
			printFrequencies();
			
			//print lookup table
			printKey();
			
			//print tree
			print(superTree.root, 1);	
			
			//print input size
			System.out.print("Original file size: "+inputSize);
			System.out.println();
			
			//print huffman size
			System.out.print("Huffman file size: "+huffmanLength);
			System.out.println();
			
		}
        	
	}
	
    public void printFrequencies() {
    	
		System.out.println("Frequencies");
		for (int i = 0; i < freqArray.length; i++) {
			
			if(freqArray[i]>0){
        		System.out.print(i+": ");
        		
//        		System.out.print((char)i+":");
        		System.out.println(freqArray[i]);
			}
		}
		System.out.println();
		
	}


	//encode huffman tree and textFile 
	public void encode(){
		
		//proceed with verbose if true
		isVerbose();
		
		huffmanLength=32;//add 32 bits for header of the binary file
		
		inputText.rewind();

		outputBinary.writeChar('H'); //add 16 bits for the magic numbers
		huffmanLength+=8; 
		outputBinary.writeChar('F'); 
		huffmanLength+=8;
		
		//serialize huffman tree
//		System.out.println("Serialized Tree: "); //uncomment to see serialized tree
		encodeHuffmanTree(superTree.root);
//		System.out.println();
		
		char c;
		
//		System.out.print("Encoded String: "); //uncomment below to see encoded binary string
		
		while(!inputText.EndOfFile()){
			c = inputText.readChar();
			
			int i = (int) c;
			String str = binaryTable[i];
			
			for (int j = 0; j < str.length(); j++) {
			
				//huffmanLength for each character* frequency of that character
				if(str.charAt(j) == '1'){
					outputBinary.writeBit(true);
//					System.out.print('1');
					huffmanLength++;
					
				}else if (str.charAt(j) == '0'){
					outputBinary.writeBit(false);
//					System.out.print('0');
					huffmanLength++;
					}
			}
		}
//		System.out.println();
//		System.out.println();
		System.out.println("File compressed.");
		outputBinary.close();
		inputText.close();

	}
	
	//serialize huffman tree
	public void encodeHuffmanTree(GenNode tree){
		if (tree == null){
			return;
		}

		if (isLeaf(tree)){
//			System.out.print('0');
//			System.out.print((char)tree.index);

			outputBinary.writeBit(false);
			outputBinary.writeChar((char)tree.index);
			huffmanLength+=9; //add 9 bits for a leaf
			return;
			
		}

		outputBinary.writeBit(true);
		huffmanLength++; //add 1 bit for an internal node
//		System.out.print('1');
		encodeHuffmanTree(tree.left);
		encodeHuffmanTree(tree.right);

	}
	
	//print each corresponding character from binaryFile
	public void decode(){
		
		if (verbose){
			print(superTree.root,1);
		}
		
		while(!inputBinary.EndOfFile()){
			traverseTree(superTree.root);
		}	
		
		System.out.println("File decompressed.");
		
	}

	//traverse tree to find each character
	public GenNode traverseTree(GenNode tree){

		if(isLeaf(tree)){
			outputText.writeChar((char)tree.index);
//			System.out.print((char)tree.index); uncomment to see decompressed message
			tree=superTree;
			return tree;	
		}
		
		if(inputBinary.readBit() == false){ //if its a 0 traverse left child
		
			return traverseTree(tree.left);	
		}	
			return traverseTree(tree.right);//if its a 1 traverse right child

	}
	
	//get frequencies of each character in textFile
	public void getFrequencies(){
		
		while(!inputText.EndOfFile()){
			char c = inputText.readChar();
			freqArray[(int)c]++;
			inputSize++;	
		}
		inputSize*=8;
	}

	//create a huffman tree to encode a file
	public Tree createHuffman(){
		
		getFrequencies();
    	
    	Tree[] treeArray=getHuffmanNodes(freqArray);
    	
    	Tree huffmanTree=getHuffmanTree(treeArray);
    	
    	return huffmanTree;
    	
    }
	
	//create node array from frequencies
	private Tree[] getHuffmanNodes (int[] freqArray){
    	Tree[] treeArray= new Tree[256];
    	
		//make an array of leaves consisting of characters with weight
		for (int i= 0; i<freqArray.length; i++){
			
			if (freqArray[i]>0){
				
				Tree tree = new Tree(i, freqArray[i]);
				treeArray[treeArrayCount]= tree;			
				treeArrayCount++; 
				
			}
		}
		
		return treeArray;
	}
	
	//create huffman tree recursively merging two smallest frequencies
	private Tree getHuffmanTree(Tree[] treeArray) {
    	
		sortThis(treeArray);
		
		if(treeArrayCount==1){
			
			superTree=treeArray[treeArrayCount];
			
			return treeArray[0];
		}
		else{
		
			Tree tree= new Tree(treeArray[treeArrayCount-1], treeArray[treeArrayCount-2]);//new node with combined frequencies
			treeArray[treeArrayCount-2]=tree;	
			sortThis(treeArray);
			treeArrayCount--;
	
			return getHuffmanTree(treeArray);	
		}
		
	}
	
	//sort the tree so that the 2 lowest frequencies are at the end of the array
	private static Tree[] sortThis (Tree[] treeArray){
		 for (int i = 0; i<treeArrayCount; i++){
		
			 int max = i;
			 for (int j = i+1; j < treeArrayCount; j++)
			      	{
					   if (treeArray[j].root.frequency > treeArray[max].root.frequency){
				          	 max= j;	 
				            Tree temp =treeArray[i];
				            treeArray[i] = treeArray[max];
				       		treeArray[max] = temp;
			      	}
			   }
		 }
		 
		 return treeArray;
		 
	}
		
	
    //create a huffman tree to decode a file
    public Tree buildTree(Tree superTree) {
    	
    	//if the next bit is a 0, write out a char
		if(inputBinary.readBit()==false)
		{
			Tree tree= new Tree(inputBinary.readChar(), 1); //makes a leaf with the character and a dummy frequency
			return tree;
		}
		
		//make an internal node that contains a 0 index and a 0 dummy frequency
		Tree tree= new Tree (buildTree(superTree), buildTree(superTree)); 
		
		return tree;
		
	}	
    
	//build lookup table
	public void createKey(String[] binaryTable, GenNode node, String encoded_String){
		
			if (!isLeaf(node)) {
				
				createKey(binaryTable, node.left,  encoded_String + '0');
				createKey(binaryTable, node.right, encoded_String + '1');
		     }
		     else {
		            binaryTable[node.index] = encoded_String;
		        }
		}
	
	//print lookup table
	public void printKey(){
		System.out.println("Code table:");
		for (int i=0; i<binaryTable.length; i++){
			if (binaryTable[i]!=null){
	      		System.out.print(+i+": ");
//	      		System.out.print((char)i+":"); //uncomment to print character in code table
	      		System.out.println(binaryTable[i]);
			}	
		}
		System.out.println();
	}
	
	//print the tree
	public void print(GenNode tree, int indent) {
		if (tree != null) {
			for(int i=0; i<indent; i++) {
				System.out.print("\t");		
			}
			
			System.out.println((char)tree.index + " : "	 + tree.frequency);
			print(tree.left, indent + 1);
			print(tree.right, indent + 1);
		}
		
	}
	
	//get size of huffman file
	public int gethuffmanLength(){
		return huffmanLength;	
	}
	
	//get size of original file
	public int getinputSize(){
		return inputSize;
	}
}


	
    
    
