
public class ListNode {
	
	public String data;
	public ListNode next;
	
	public ListNode(){
		
	}
	
	public ListNode(String data){
		this.data = data;
		
	}
	
	public ListNode(String data, ListNode next){
		this.data = data;
		this.next = next;
		
	}
	
	public String getData(){
		return data;
	}
	
	public void setData(String data){
		this.data= data;
	}
	
	public void setNext(ListNode next){
		this.next=next;
	}
	
    @Override
    public String toString() {
        return data.toString();
    }

}