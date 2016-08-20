
public class LinkedList
{

    protected ListNode head;
    protected ListNode tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    // Method add(Object o)
    // Adds the object at the end of the linked list
    public void add(Object o)
    {
        ListNode newElem = new ListNode(o);
        if (tail != null)
        {
        	tail.setNext(newElem);
        	newElem.setPrev(tail);
        	tail =  tail.next();
        }
        else
        {
        	head = newElem;
        	tail = newElem;
        }
    }


    // Method reverse
    // Reverses the string
    public void reverse()
    {
		ListNode prev=null;
		ListNode temp=head;


		while(temp!=null)
		{

			prev= temp.prev(); //before previous is changed
			temp.setPrev(temp.next());
			temp.setNext(prev);
			temp=temp.prev();
		}
		if (prev!=null)
		{
			head= prev.prev();
		}

//		head=temp;

    }

    // Method toString()
    // Creates a String representation of the list:
    //  Left bracket, followed by by concatenating the result of toString()
    //  called on each element of the list (separated by commas), fooled by a
    //  right bracket.
    //  Empty list:  toString => "[]"
    //  List containing the single Integer 3: toString => "[3]"
    //  List containing three integers 1, 2, 3:  toString => "[1,2,3]"
    public String toString()
    {
		String result= "[";
		ListNode current=head;

		while(current!=null)
		{
			result+=(current.next() != null?current.data()+",":current.data());
			current=current.next();
		}
		result+="]";
        return result;
    }


    // Method removeFirst(Object o)
    //  Removes the first occurrence of the Object o from the list.  If
    //    the object appears more than once, only the first occurrence is
    //    removed.  If the object does not occur in the list, the method
    //    does nothing. removeFirst and removeLast should reuse as much
    //    code as possible!
    public void removeFirst(Object o)
    {
    	ListNode temp=head;
    	ListNode curr= temp;
    	ListNode currPrev= null;

    	if (curr.data().equals(o)) {
    		head = curr.next();
    	}
    	else{

	    	while(temp!=null){

	    		if (temp.data().equals(o)){
	    			curr=temp;
	    			currPrev=temp.prev();
	    			}
	    		temp=temp.next();
	    		}

	    	currPrev.setNext(curr.next());
	    	curr=curr.next();
	    	curr.setPrev(currPrev);
    		}
    	}


    // Method removeLast(Object o)
    //  Removes the last occurrence of the Object o from the list.  If
    //    the object appears more than once, only the first occurrence is
    //    removed.  If the object does not occur in the list, the method
    //    does nothing. removeFirst and removeLast should reuse as much
    //    code as possible!
    public void removeLast(Object o)
    {
    	ListNode temp=head;
    	ListNode curr= null;
    	ListNode currPrev= null;


    	while(temp!=null){

	   		if (temp.data().equals(o)){
	   			curr=temp;
	    		currPrev=temp.prev();
	    	}

	   	temp=temp.next();

		}

    	if (curr.data().equals(head.data())) { //if elem in head
    		head = curr.next();
    	}

    	else if(curr.next()!=null)  //if elem in middle
    	{
			   temp=curr.next();
			   currPrev.setNext(temp);
			   curr=curr.next();
			   curr.setPrev(currPrev);
		   }
    	else{							//if elem at the end
			currPrev.setNext(null);
			tail=currPrev;
		   }
    	}





    public static void main(String args[])
    {
    	LinkedList l = new LinkedList();
    	l.add(new Integer(99));
        for (int i = 0; i < 5; i++)
        {
            l.add(new Integer(i));
        }
    	l.add(new Integer(99));
        for (int i = 5; i < 10; i++)
        {
            l.add(new Integer(i));
        }
    	l.add(new Integer(99));

        System.out.println(l);
        l.reverse();
        System.out.println(l);

        l.removeFirst(99);
        System.out.println(l);

        l.removeLast(new Integer(99));
        System.out.println(l);
        l.removeLast(new Integer(99));
        System.out.println(l);
        l.removeLast(new Integer(9));
        System.out.println(l);
        LinkedList l2 = new LinkedList();
        System.out.println(l2);
        l2.add(new Integer(2));
        System.out.println(l2);
        l2.removeLast(new Integer(2));
        System.out.println(l2);

    }
}
