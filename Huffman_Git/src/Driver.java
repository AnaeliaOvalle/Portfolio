import java.io.*;

public class Driver {
	
	private static int index=0;
	
	public static void main(String[] args) throws IOException, IllegalArgumentException {
		
		//check to make sure arguments have been entered
		if (args.length == 0) {
			
			throw new IllegalArgumentException("No arguments detected");

		}
		
		//increment index to find the last 2 entries in array and use as reference for files
		for (String string:args){
				index++;
			}
		
		
		String arrayArgs="";
		
		for (int i = 0; i < args.length; i++) {
		    String str = args[i];
		
		    if (str.charAt(0) == '-') {
	
		    	arrayArgs+=str.substring(0, 2);
		    }
		    
		}
		
		String commands=arrayArgs.toLowerCase();

				
		//compress options
		if ((commands.contains("-c")) && (commands.contains("-f")) && (commands.contains("-v"))){//force compress and verbose
			Compress thisFile= new Compress(args[index-2] ,args[index-1]);
			thisFile.compressFile(true, true);

		}
		
		else if (commands.contains("-c") && (commands.contains("-f"))){ //force compress
			Compress thisFile= new Compress(args[index-2] ,args[index-1]);
			thisFile.compressFile(true, false);
			
		}
	
		else if ((commands.contains("-c")) && (commands.contains("-v"))){ //regular compress and verbose
			Compress thisFile= new Compress(args[index-2] ,args[index-1]);
			thisFile.compressFile(false, true);
			
		}
		
		else if ((commands.contains("-c"))){ //regular compress
			Compress thisFile= new Compress(args[index-2] ,args[index-1]);
			thisFile.compressFile(false, false);
			
		}
		
//		uncompress options
		if ((commands.contains("-u")) && (commands.contains("-f")) && (commands.contains("-v"))){//force uncompress and verbose
			Decompress thisFile= new Decompress(args[index-2] ,args[index-1], true, true);
			thisFile.decompressFile();
			
		}
		
		else if (commands.contains("-u") && (commands.contains("-f"))){ //force uncompress
			Decompress thisFile= new Decompress(args[index-2] ,args[index-1], true, false);
			thisFile.decompressFile();
			
		}
	
		else if ((commands.contains("-u")) && (commands.contains("-v"))){ //regular compress and verbose
			Decompress thisFile= new Decompress(args[index-2] ,args[index-1], false, true);
			thisFile.decompressFile();
			
		}
		
		else if ((commands.contains("-u"))){ //regular uncompress
			Decompress thisFile= new Decompress(args[index-2] ,args[index-1], false, false);
			thisFile.decompressFile();
			
		}		
		
	}

}


