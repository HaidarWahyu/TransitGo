public class TempatNode {
    String nama;
    String kategori;
    TempatNode next;
    
    TempatNode(String nama, String kategori) {
        this.nama = nama;
        this.kategori = kategori;
        this.next = null;
    }
}
