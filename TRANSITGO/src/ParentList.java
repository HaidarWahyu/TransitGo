public class ParentList {
    class ParentNode {
        int vertexId;
        int parentId;
        ParentNode next;
        ParentNode(int vertexId, int parentId) {
            this.vertexId = vertexId;
            this.parentId = parentId;
            this.next = null;
        }
    }
    
    ParentNode head;
    
    public ParentList() {
        head = null;
    }
    
    public void add(int vertexId, int parentId) {
        // Cek apakah sudah ada
        ParentNode existing = find(vertexId);
        if (existing != null) {
            existing.parentId = parentId;
            return;
        }
        
        ParentNode baru = new ParentNode(vertexId, parentId);
        if (head == null) {
            head = baru;
        } else {
            ParentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
    }
    
    public int getParent(int vertexId) {
        ParentNode node = find(vertexId);
        return node != null ? node.parentId : -1;
    }
    
    private ParentNode find(int vertexId) {
        ParentNode temp = head;
        while (temp != null) {
            if (temp.vertexId == vertexId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}