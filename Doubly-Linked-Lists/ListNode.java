public class ListNode
{
    private Object data;
    private ListNode next;
    private ListNode prev;

    public ListNode(Object d)
    {
        data = d;
        next = null;
        prev = null;
    }

    public ListNode(Object data, ListNode next, ListNode prev)
    {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }


    public Object data()
    {
        return data;
    }

    public ListNode next()
    {
        return next;
    }

    public ListNode prev()
    {
        return prev;
    }
    public void setNext(ListNode newNext)
    {
        this.next = newNext;
    }

    public void setPrev(ListNode newPrev)
    {
        this.prev = newPrev;
    }

    public void setData(Object newData)
    {
        this.data = newData;
    }
}
