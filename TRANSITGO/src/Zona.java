public class Zona {
    int baris;
    int kolom;
    String namaZona;
    TempatNode headTempat; 
    int jumlahTempat;
    
    Zona(int baris, int kolom, String namaZona) {
        this.baris = baris;
        this.kolom = kolom;
        this.namaZona = namaZona;
        this.headTempat = null;
        this.jumlahTempat = 0;
    }
    
    void tambahTempat(String nama, String kategori) {
        TempatNode baru = new TempatNode(nama, kategori);
        
        if (headTempat == null) {
            headTempat = baru;
        } else {
            TempatNode current = headTempat;
            while (current.next != null) {
                current = current.next;
            }
            current.next = baru;
        }
        jumlahTempat++;
    }
    
    boolean hapusTempat(String nama) {
        if (headTempat == null) return false;
        if (headTempat.nama.equalsIgnoreCase(nama)) {
            headTempat = headTempat.next;
            jumlahTempat--;
            return true;
        }
        TempatNode current = headTempat;
        while (current.next != null) {
            if (current.next.nama.equalsIgnoreCase(nama)) {
                current.next = current.next.next;
                jumlahTempat--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    TempatNode cariTempat(String keyword) {
        TempatNode current = headTempat;
        while (current != null) {
            if (current.nama.toLowerCase().contains(keyword.toLowerCase())) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
