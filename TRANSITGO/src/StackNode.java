public class StackNode {
    String keyword;
    int hasilBaris;
    int hasilKolom;
    String namaTempat;
    StackNode next;

    StackNode(String keyword, int baris, int kolom, String namaTempat) {
        this.keyword = keyword;
        this.hasilBaris = baris;
        this.hasilKolom = kolom;
        this.namaTempat = namaTempat;
        this.next = null;
    }
    
    StackNode(String keyword, int baris, int kolom) {
        this(keyword, baris, kolom, "");
    }
  
    String getInfo() {
        return "\"" + keyword + "\" -> [" + hasilBaris + "," + hasilKolom + "]" + 
               (namaTempat.isEmpty() ? "" : " (" + namaTempat + ")");
    }
}