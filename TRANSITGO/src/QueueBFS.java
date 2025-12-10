public class QueueBFS {
    QueueNode front;
    QueueNode rear;
    int size;
    
    QueueBFS() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    void enqueue(int baris, int kolom, int waktu, int harga, String jalur, int transit) {
        QueueNode baru = new QueueNode(baris, kolom, waktu, harga, jalur, transit);
        
        if (rear == null) {
            front = rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }
        size++;
    }
    
    void enqueue(int baris, int kolom, int waktu, int harga, String jalur) {
        enqueue(baris, kolom, waktu, harga, jalur, 0);
    }
    
    QueueNode dequeue() {
        if (front == null) return null;
        
        QueueNode temp = front;
        front = front.next;
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        return temp;
    }
    
    QueueNode peek() {
        return front;
    }
    
    boolean isEmpty() {
        return front == null;
    }
    
    int getSize() {
        return size;
    }
    
    void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}
