
public class tableEntry {
		
		int distance;
		int path;
		boolean known;
		
		public tableEntry(){
			this.distance=0;
			this.path=-1;
			known=false;
		}
		public tableEntry(int distance, int path, boolean known){
			this.distance=distance;
			this.path=path;
			this.known=known;
			
		}
	}
	
	

