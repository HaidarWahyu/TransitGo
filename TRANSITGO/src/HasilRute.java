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

    // 1. Comparator gabungan (waktu → harga → transit)
    int compareToFull(HasilRute other) {
        if (this.totalWaktu != other.totalWaktu)
            return this.totalWaktu - other.totalWaktu;

        if (this.totalHarga != other.totalHarga)
            return this.totalHarga - other.totalHarga;

        return this.jumlahTransit - other.jumlahTransit;
    }

    String getRuteLengkap() {
        return "Moda: " + moda +
               " | Jalur: " + jalur +
               " | Waktu: " + getWaktuFormatted() +
               " | Harga: " + getHargaFormatted() +
               " | Transit: " + jumlahTransit + " kali";
    }

    // 3. Hitung skor efisiensi
    double getEfisiensiScore() {
        double wWaktu = 0.5;
        double wHarga = 0.3;
        double wTransit = 0.2;

        return (wWaktu * totalWaktu) +
               (wHarga * totalHarga) +
               (wTransit * jumlahTransit);
    }

    // 4. Copy object
    HasilRute copy() {
        return new HasilRute(
            this.moda,
            this.jalur,
            this.totalWaktu,
            this.totalHarga,
            this.jumlahTransit
        );
    }
}
