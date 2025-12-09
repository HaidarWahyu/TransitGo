public class Rute {
    class PathNode {
        int vertexId;
        PathNode next;
        PathNode(int vertexId) {
            this.vertexId = vertexId;
            this.next = null;
        }
    }
    
    public PathNode headPath;
    public PathNode tailPath;
    public int panjangPath;
    public int totalWaktu;
    public int totalBiaya;
    public String moda;
    public Rute next;
    
    public Rute() {
        headPath = null;
        tailPath = null;
        panjangPath = 0;
        totalWaktu = 0;
        totalBiaya = 0;
        moda = "";
        next = null;
    }
    
    public void tambahVertex(int vertexId) {
        PathNode baru = new PathNode(vertexId);
        if (headPath == null) {
            headPath = baru;
            tailPath = baru;
        } else {
            tailPath.next = baru;
            tailPath = baru;
        }
        panjangPath++;
    }
    
    public void printRute(Graph graph) {
        System.out.print(moda + ": ");
        PathNode current = headPath;
        while (current != null) {
            Vertex v = graph.findVertex(current.vertexId);
            System.out.print(v != null ? v.nama : current.vertexId);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println(" (Waktu: " + totalWaktu + "m, Biaya: " + totalBiaya + ")");
    }
}