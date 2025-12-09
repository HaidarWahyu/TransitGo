public class VisitedList {
    class VisitedNode {
        int vertexId;
        VisitedNode next;
        VisitedNode(int vertexId) {
            this.vertexId = vertexId;
            this.next = null;
        }
    }
    
    VisitedNode head;
    
    public VisitedList() {
        head = null;
    }
    
    public void add(int vertexId) {
        VisitedNode baru = new VisitedNode(vertexId);
        if (head == null) {
            head = baru;
        } else {
            VisitedNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
    }
    
    public boolean contains(int vertexId) {
        VisitedNode temp = head;
        while (temp != null) {
            if (temp.vertexId == vertexId) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}