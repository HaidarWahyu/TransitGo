public class HasilRuteNode {
    HasilRute data;
    HasilRuteNode next;
    
    public HasilRuteNode(HasilRute data) {
        this.data = data;
        this.next = null;
    }
    
    public HasilRute getData() {
        return data;
    }
    
    public void setNext(HasilRuteNode next) {
        this.next = next;
    }
    
    public HasilRuteNode getNext() {
        return next;
    }
}