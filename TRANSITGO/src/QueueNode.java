public class QueueNode {
    int baris;
    int kolom;
    int totalWaktu;
    int totalHarga;
    String jalur;     
    int jumlahTransit;
    QueueNode next;
    
    QueueNode(int baris, int kolom, int waktu, int harga, String jalur, int transit) {
        this.baris = baris;
        this.kolom = kolom;
        this.totalWaktu = waktu;
        this.totalHarga = harga;
        this.jalur = jalur;
        this.jumlahTransit = transit;
        this.next = null;
    }
    
    QueueNode(int baris, int kolom, int waktu, int harga, String jalur) {
        this(baris, kolom, waktu, harga, jalur, hitungTransitDariJalur(jalur));
    }
    
    private static int hitungTransitDariJalur(String jalur) {
        int count = 0;
        for (int i = 0; i < jalur.length(); i++) {
            if (jalur.charAt(i) == '>') count++;
        }
        return count;
    }
    
    String getKoordinat() {
        return "[" + baris + "," + kolom + "]";
    }
}
