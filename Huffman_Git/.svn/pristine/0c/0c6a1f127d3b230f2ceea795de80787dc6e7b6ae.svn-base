import java.io.*;

public class Decompress 
{
	private HuffmanTree hf;
	private String outputFile;
	private String inputFile;
	private TextFile outputText;
	private BinaryFile inputBinary;
	private boolean force;
	private boolean verbose;
	
	public Decompress(String inputFile, String outputFile, boolean force, boolean verbose){
		
		this.inputFile=inputFile;
		this.outputFile=outputFile;
		this.force=force;
		this.verbose=verbose;
	
	}
	public void decompressFile() throws IOException{
		
		//delete old binary file
		File file = new File(outputFile);
		file.delete();
		
		inputBinary = new BinaryFile(inputFile, 'r');
		outputText = new TextFile(outputFile, 'w'); 
		
		int i=0;
		String huffmanCheck = "";//check if it's a huffman file
		
		while(i < 2){	
			char c =inputBinary.readChar();
			huffmanCheck = huffmanCheck+c;
			i++;
		}
		System.out.println("Checking if this is a huffman file...");
		if (huffmanCheck.matches("HF")){
			System.out.println("Decompressing file...");
			hf= new HuffmanTree(inputBinary, outputText);
			hf.decode();	
		}
		
		else{
			throw new IOException ("This is not a huffman file!");
		}
		
	}
}


