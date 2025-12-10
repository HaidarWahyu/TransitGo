public class HasilPencarian {
    String namaTempat;
    String kategori;
    int zonaBaris;
    int zonaKolom;
    String namaZona;
    HasilPencarian next; 
    
    HasilPencarian(String namaTempat, String kategori, int baris, int kolom, String namaZona) {
        this.namaTempat = namaTempat;
        this.kategori = kategori;
        this.zonaBaris = baris;
        this.zonaKolom = kolom;
        this.namaZona = namaZona;
        this.next = null;
    }
    
    String getKoordinat() {
        return "[" + zonaBaris + "," + zonaKolom + "]";
    }
    
    String getInfo() {
        return namaTempat + " (" + kategori + ") - Zona " + getKoordinat() + " " + namaZona;
    }
}
