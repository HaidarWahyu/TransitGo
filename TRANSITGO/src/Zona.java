public class Zona {
    public int id;
    public String nama;
    public int baris;
    public int kolom;
    public TempatNode headTempat;
    public Zona next;
    
    public Zona(int id, String nama, int baris, int kolom) {
        this.id = id;
        this.nama = nama;
        this.baris = baris;
        this.kolom = kolom;
        this.headTempat = null;
        this.next = null;
    }
    
    public void tambahTempat(String nama, String kategori) {
        TempatNode baru = new TempatNode(nama, kategori, this.id);
        if (headTempat == null) {
            headTempat = baru;
        } else {
            TempatNode temp = headTempat;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
    }
    
    // LINEAR SEARCH di linked list
    public TempatNode cariTempat(String nama) {
        TempatNode temp = headTempat;
        while (temp != null) {
            if (temp.nama.equalsIgnoreCase(nama)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    
    public boolean punyaTempatKategori(String kategori) {
        TempatNode temp = headTempat;
        while (temp != null) {
            if (temp.kategori.equalsIgnoreCase(kategori)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    public void tampilkanTempat() {
        System.out.println("\n" + nama + ":");
        TempatNode temp = headTempat;
        int no = 1;
        while (temp != null) {
            System.out.println(no + ". " + temp.nama + " (" + temp.kategori + ")");
            temp = temp.next;
            no++;
        }
        if (no == 1) {
            System.out.println("(kosong)");
        }
    }
}