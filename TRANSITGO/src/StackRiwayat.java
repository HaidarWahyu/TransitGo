public class StackRiwayat {
    StackNode top;
    int size;
    int maxSize;  

    StackRiwayat() {
        this.top = null;
        this.size = 0;
        this.maxSize = 50;
    }
  
    StackRiwayat(int maxSize) {
        this.top = null;
        this.size = 0;
        this.maxSize = maxSize;
    }
    
    void push(String keyword, int baris, int kolom, String namaTempat) {
        if (size >= maxSize) {
            hapusTerbawah();
        }
        
        StackNode baru = new StackNode(keyword, baris, kolom, namaTempat);
        baru.next = top;
        top = baru;
        size++;
    }

    void push(String keyword, int baris, int kolom) {
        push(keyword, baris, kolom, "");
    }
    
    StackNode pop() {
        if (top == null) return null;
        StackNode temp = top;
        top = top.next;
        size--;
        return temp;
    }

    StackNode peek() {
        return top;
    }
    
    boolean isEmpty() {
        return top == null;
    }
    
    int getSize() {
        return size;
    }
    
    private void hapusTerbawah() {
        if (top == null || top.next == null) {
            top = null;
            size = 0;
            return;
        }
        
        StackNode current = top;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
        size--;
    }
 
    void clear() {
        top = null;
        size = 0;
    }
    
    StackNode cari(String keyword) {
        StackNode current = top;
        while (current != null) {
            if (current.keyword.toLowerCase().contains(keyword.toLowerCase())) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
