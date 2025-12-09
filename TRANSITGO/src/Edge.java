public class Edge {
    int target;
    int waktu;
    int biaya;
    String moda;
    Edge next;

    public Edge(int target, int waktu, int biaya, String moda) {
        this.target = target;
        this.waktu = waktu;
        this.biaya = biaya;
        this.moda = moda;
        this.next = null;
    }
}