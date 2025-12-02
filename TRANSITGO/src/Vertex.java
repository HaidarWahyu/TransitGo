public class Vertex {
    int id;
    String nama;
    Edge edges;
    Vertex next;

    public Vertex(int id, String nama) {
        this.id = id;
        this.nama = nama;
        this.edges = null;
        this.next = null;
    }

    public void addEdge(int target, int waktu, int biaya, String moda) {
        Edge e = new Edge(target, waktu, biaya, moda);
        if (edges == null) {
            edges = e;
        } else {
            Edge temp = edges;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = e;
        }
    }

    public Edge cariEdgeKe(int target) {
        Edge temp = edges;
        while (temp != null) {
            if (temp.target == target) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}