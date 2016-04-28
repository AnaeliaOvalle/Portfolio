import java.io.*;

public class Compress
{
	private HuffmanTree hf;
	private String textFile, outputFile;
	private TextFile inputText;
	private BinaryFile outputBinary;
	
	
	public Compress(String textFile, String outputFile){
		
		this.textFile=textFile;
		this.outputFile=outputFile;
	
	}
		
	public void compressFile(boolean force, boolean verbose) throws IOException { 
		
		inputText = new TextFile(textFile, 'r');	
		outputBinary= new BinaryFile(outputFile, 'w');
		
		if(verbose){
			System.out.println("Verbose flag detected.");
			hf= new HuffmanTree(inputText, outputBinary, true);
		}
		else{
			hf= new HuffmanTree(inputText, outputBinary, false);
		}
		
		//if user called force
		if (force){
			System.out.println("Force flag detected. Compressing...");
			hf.encode();
		}
		else{	
			//if hf compressed size is larger than the original size, throw an exception. else, compress
			if(hf.gethuffmanLength()> hf.getinputSize()){
				
				//erase created binary file
				File file = new File(outputFile);
				file.delete();
				
				throw new IOException("Compressed size is not smaller than original size");
			}
			else{
				System.out.println("Compressing...");
				hf.encode();
			
			}
		}
	}

}

