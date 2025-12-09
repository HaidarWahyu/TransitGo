public class Graph {
    public Vertex head;
    public int vertexCount;

    public Graph() {
        head = null;
        vertexCount = 0;
    }

    // === GRAPH BASIC OPERATIONS ===
    public void addVertex(int id, String nama) {
        Vertex v = new Vertex(id, nama);
        if (head == null) {
            head = v;
        } else {
            Vertex temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = v;
        }
        vertexCount++;
    }

    public Vertex findVertex(int id) {
        Vertex temp = head;
        while (temp != null) {
            if (temp.id == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addEdge(int src, int dest, int waktu, int biaya, String moda) {
        Vertex v1 = findVertex(src);
        Vertex v2 = findVertex(dest);
        
        if (v1 != null && v2 != null) {
            v1.addEdge(dest, waktu, biaya, moda);
            v2.addEdge(src, waktu, biaya, moda);
        }
    }

    // === QUEUE (untuk BFS) ===
    class Queue {
        class Node {
            int data;
            Node next;
            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
        
        Node front;
        Node rear;
        
        Queue() {
            front = null;
            rear = null;
        }
        
        boolean isEmpty() {
            return front == null;
        }
        
        void enqueue(int data) {
            Node newNode = new Node(data);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
        }
        
        int dequeue() {
            if (isEmpty()) return -1;
            int data = front.data;
            front = front.next;
            if (front == null) rear = null;
            return data;
        }
    }

    // === STACK (untuk path reconstruction) ===
    class Stack {
        class Node {
            int data;
            Node next;
            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
        
        Node top;
        
        Stack() {
            top = null;
        }
        
        boolean isEmpty() {
            return top == null;
        }
        
        void push(int data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
        }
        
        int pop() {
            if (isEmpty()) return -1;
            int data = top.data;
            top = top.next;
            return data;
        }
    }

    // === BFS dengan Queue ===
    public void bfs(int startId, int goalId) {
        System.out.println("\n=== BFS: Minimal Transit ===");
        
        Queue queue = new Queue();
        
        // Linked list untuk visited
        VisitedList visited = new VisitedList();
        
        // Linked list untuk parent
        ParentList parent = new ParentList();
        
        queue.enqueue(startId);
        visited.add(startId);
        parent.add(startId, -1);
        
        while (!queue.isEmpty()) {
            int currentId = queue.dequeue();
            
            if (currentId == goalId) {
                System.out.println("✓ Tujuan ditemukan!");
                printPathBFS(parent, startId, goalId);
                return;
            }
            
            Vertex current = findVertex(currentId);
            if (current == null) continue;
            
            Edge neighbor = current.edges;
            while (neighbor != null) {
                if (!visited.contains(neighbor.target)) {
                    visited.add(neighbor.target);
                    parent.add(neighbor.target, currentId);
                    queue.enqueue(neighbor.target);
                }
                neighbor = neighbor.next;
            }
        }
        
        System.out.println("✗ Tujuan tidak ditemukan!");
    }
    
    private void printPathBFS(ParentList parent, int startId, int goalId) {
        Stack stack = new Stack();
        int currentId = goalId;
        
        while (currentId != startId) {
            stack.push(currentId);
            currentId = parent.getParent(currentId);
        }
        stack.push(startId);
        
        System.out.print("Path: ");
        int steps = 0;
        while (!stack.isEmpty()) {
            int nodeId = stack.pop();
            Vertex v = findVertex(nodeId);
            System.out.print(v != null ? v.nama : nodeId);
            if (!stack.isEmpty()) System.out.print(" -> ");
            steps++;
        }
        System.out.println("\nTotal Transit: " + (steps-1));
    }

    // === DIJKSTRA (tanpa priority queue) ===
    public Rute dijkstra(int startId, int goalId, boolean cariTermurah) {
        // Buat distance list secara manual (tanpa DistanceList class)
        DistanceManual distances = new DistanceManual();
        VisitedList visited = new VisitedList();
        ParentList parents = new ParentList();
        
        // Inisialisasi
        Vertex temp = head;
        while (temp != null) {
            int initialDist = (temp.id == startId) ? 0 : Integer.MAX_VALUE;
            distances.add(temp.id, initialDist);
            parents.add(temp.id, -1);
            temp = temp.next;
        }
        
        // Main loop Dijkstra
        for (int count = 0; count < vertexCount; count++) {
            // Cari vertex dengan distance terkecil (SELECTION SORT style)
            int currentId = distances.extractMin(visited);
            if (currentId == -1 || currentId == goalId) break;
            
            visited.add(currentId);
            Vertex current = findVertex(currentId);
            if (current == null) continue;
            
            // Update tetangga
            Edge edge = current.edges;
            while (edge != null) {
                int currentDist = distances.getDistance(currentId);
                if (currentDist != Integer.MAX_VALUE) {
                    int bobot = cariTermurah ? edge.biaya : edge.waktu;
                    int newDist = currentDist + bobot;
                    
                    if (newDist < distances.getDistance(edge.target)) {
                        distances.update(edge.target, newDist);
                        parents.add(edge.target, currentId);
                    }
                }
                edge = edge.next;
            }
        }
        
        // Rekonstruksi rute
        if (distances.getDistance(goalId) == Integer.MAX_VALUE) {
            return null;
        }
        
        return buatRute(parents, startId, goalId, cariTermurah);
    }
    
    // Inner class untuk distance manual
    class DistanceManual {
        class DNode {
            int vertexId;
            int distance;
            DNode next;
            DNode(int vertexId, int distance) {
                this.vertexId = vertexId;
                this.distance = distance;
                this.next = null;
            }
        }
        
        DNode head;
        
        DistanceManual() {
            head = null;
        }
        
        void add(int vertexId, int distance) {
            DNode baru = new DNode(vertexId, distance);
            if (head == null) {
                head = baru;
            } else {
                DNode temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = baru;
            }
        }
        
        int getDistance(int vertexId) {
            DNode temp = head;
            while (temp != null) {
                if (temp.vertexId == vertexId) {
                    return temp.distance;
                }
                temp = temp.next;
            }
            return Integer.MAX_VALUE;
        }
        
        void update(int vertexId, int newDistance) {
            DNode temp = head;
            while (temp != null) {
                if (temp.vertexId == vertexId) {
                    temp.distance = newDistance;
                    return;
                }
                temp = temp.next;
            }
        }
        
        int extractMin(VisitedList visited) {
            DNode temp = head;
            int minId = -1;
            int minDist = Integer.MAX_VALUE;
            
            while (temp != null) {
                if (!visited.contains(temp.vertexId) && temp.distance < minDist) {
                    minDist = temp.distance;
                    minId = temp.vertexId;
                }
                temp = temp.next;
            }
            
            return minId;
        }
    }
    
    private Rute buatRute(ParentList parents, int startId, int goalId, boolean cariTermurah) {
        Rute rute = new Rute();
        rute.moda = cariTermurah ? "Termurah" : "Tercepat";
        
        Stack stack = new Stack();
        int currentId = goalId;
        int totalWaktu = 0;
        int totalBiaya = 0;
        
        // Bangun path dari goal ke start
        while (currentId != startId) {
            stack.push(currentId);
            int parentId = parents.getParent(currentId);
            
            // Hitung waktu & biaya edge ini
            Vertex parent = findVertex(parentId);
            if (parent != null) {
                Edge edge = parent.cariEdgeKe(currentId);
                if (edge != null) {
                    totalWaktu += edge.waktu;
                    totalBiaya += edge.biaya;
                }
            }
            
            currentId = parentId;
        }
        stack.push(startId);
        
        // Simpan path ke rute
        while (!stack.isEmpty()) {
            rute.tambahVertex(stack.pop());
        }
        
        rute.totalWaktu = totalWaktu;
        rute.totalBiaya = totalBiaya;
        return rute;
    }

    public void printGraph() {
        System.out.println("\n=== STRUKTUR GRAPH ===");
        Vertex temp = head;
        while (temp != null) {
            System.out.print(temp.nama + " -> ");
            Edge e = temp.edges;
            while (e != null) {
                System.out.print(e.target + "(" + e.moda + ") ");
                e = e.next;
            }
            System.out.println();
            temp = temp.next;
        }
    }
}