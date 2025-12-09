public class TempatNode {
    String nama;
    String kategori;
    int zonaId;
    TempatNode next;
    
    public TempatNode(String nama, String kategori, int zonaId) {
        this.nama = nama;
        this.kategori = kategori;
        this.zonaId = zonaId;
        this.next = null;
    }
}