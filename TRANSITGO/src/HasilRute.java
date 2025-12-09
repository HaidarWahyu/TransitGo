public class HasilRute {
    String moda;
    String jalur;
    int totalWaktu;
    int totalHarga;
    int jumlahTransit;
    
    public HasilRute(String moda, String jalur, int waktu, int harga, int transit) {
        this.moda = moda;
        this.jalur = jalur;
        this.totalWaktu = waktu;
        this.totalHarga = harga;
        this.jumlahTransit = transit;
    }
    
    String getHargaFormatted() {
        return "Rp " + formatAngka(totalHarga);
    }
    
    String getWaktuFormatted() {
        if (totalWaktu >= 60) {
            int jam = totalWaktu / 60;
            int menit = totalWaktu % 60;
            return jam + " jam " + menit + " menit";
        }
        return totalWaktu + " menit";
    }
    
    private String formatAngka(int angka) {
        String str = String.valueOf(angka);
        StringBuilder result = new StringBuilder();
        int count = 0;
        
        for (int i = str.length() - 1; i >= 0; i--) {
            if (count > 0 && count % 3 == 0) {
                result.insert(0, ".");
            }
            result.insert(0, str.charAt(i));
            count++;
        }
        
        return result.toString();
    }
    
    int compareTo(HasilRute other) {
        return this.totalWaktu - other.totalWaktu;
    }
    
    int compareToByHarga(HasilRute other) {
        return this.totalHarga - other.totalHarga;
    }
    
    int compareToByTransit(HasilRute other) {
        return this.jumlahTransit - other.jumlahTransit;
    }
}
