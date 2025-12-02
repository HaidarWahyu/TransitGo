public class GraphNode {
    int zonaBaris;
    int zonaKolom;
    int waktuTempuh;
    int harga;
    GraphNode next;
    
    GraphNode(int baris, int kolom, int waktu, int harga) {
        this.zonaBaris = baris;
        this.zonaKolom = kolom;
        this.waktuTempuh = waktu;
        this.harga = harga;
        this.next = null;
    }
    
    String getKoordinat() {
        return "[" + zonaBaris + "," + zonaKolom + "]";
    }
}
