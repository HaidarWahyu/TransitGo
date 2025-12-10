public class TampilanUI {
    
    public static void tampilkanWelcome() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                    ║");
        System.out.println("║     ████████╗██████╗  █████╗ ███╗   ██╗███████╗██╗████████╗ ██████╗  ██████╗       ║");
        System.out.println("║     ╚══██╔══╝██╔══██╗██╔══██╗████╗  ██║██╔════╝██║╚══██╔══╝██╔════╝ ██╔═══██╗      ║");
        System.out.println("║        ██║   ██████╔╝███████║██╔██╗ ██║███████╗██║   ██║   ██║  ███╗██║   ██║      ║");
        System.out.println("║        ██║   ██╔══██╗██╔══██║██║╚██╗██║╚════██║██║   ██║   ██║   ██║██║   ██║      ║");
        System.out.println("║        ██║   ██║  ██║██║  ██║██║ ╚████║███████║██║   ██║   ╚██████╔╝╚██████╔╝      ║");
        System.out.println("║        ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝   ╚═╝    ╚═════╝  ╚═════╝       ║");
        System.out.println("║                                                                                    ║");
        System.out.println("║                    SISTEM REKOMENDASI TRANSPORTASI BERBASIS ZONA                   ║");
        System.out.println("║                                 Kota Grid 10 x 10                                  ║");
        System.out.println("║                                                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Struktur Data: LinkedList, Stack, Queue, Graph, Sorting, Searching                ║");
        System.out.println("║  Transportasi : MRT (cepat) dan Bus (ekonomis)                                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    public static void tampilkanGoodbye() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                              ║");
        System.out.println("║                    Terima kasih telah menggunakan                            ║");
        System.out.println("║              Sistem Rekomendasi Transportasi Berbasis Zona!                  ║");
        System.out.println("║                                                                              ║");
        System.out.println("║                         Sampai jumpa kembali!                                ║");
        System.out.println("║                                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void tampilkanMenuUtama() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    MENU UTAMA                          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Lihat Peta Kota                                   ║");
        System.out.println("║  [2] Cari Tempat (Linear Search)                       ║");
        System.out.println("║  [3] Cari Rute Transportasi (BFS + Sorting)            ║");
        System.out.println("║  [4] Lihat Riwayat Pencarian (Stack)                   ║");
        System.out.println("║  [5] Lihat Detail Zona                                 ║");
        System.out.println("║  [6] Lihat Jaringan Transportasi (Graph)               ║");
        System.out.println("║  [7] Tambah Tempat Baru (Linked List Insert)           ║");
        System.out.println("║  [8] Demo Algoritma Searching                          ║");
        System.out.println("║  [9] Demo Algoritma Sorting                            ║");
        System.out.println("║  [0] Keluar                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanPetaKota(Zona[][] kotaGrid, GraphTransportasi graphMRT, 
                                          GraphTransportasi graphBus, int gridSize) {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                   PETA KOTA 10x10                                     ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════╣");
        
        // Header kolom
        System.out.print("║      ");
        for (int j = 0; j < gridSize; j++) {
            System.out.printf("   %d   ", j);
        }
        System.out.println("   ║");
        
        // Garis atas
        System.out.print("║    ┌");
        for (int j = 0; j < gridSize; j++) {
            System.out.print("──────" + (j < gridSize-1 ? "┬" : "┐"));
        }
        System.out.println("  ║");
        
        // Isi grid
        for (int i = 0; i < gridSize; i++) {
            System.out.printf("║  %d │", i);
            for (int j = 0; j < gridSize; j++) {
                String simbol = getSimbolZona(i, j, kotaGrid, graphMRT, graphBus);
                System.out.printf(" %s │", simbol);
            }
            System.out.println("  ║");
            
            if (i < gridSize - 1) {
                System.out.print("║    ├");
                for (int j = 0; j < gridSize; j++) {
                    System.out.print("──────" + (j < gridSize-1 ? "┼" : "┤"));
                }
                System.out.println("  ║");
            }
        }
        
        System.out.print("║    └");
        for (int j = 0; j < gridSize; j++) {
            System.out.print("──────" + (j < gridSize-1 ? "┴" : "┘"));
        }
        System.out.println("  ║");
        
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  LEGENDA:                                                                             ║");
        System.out.println("║    M  = Stasiun MRT        B  = Halte Bus       MB = MRT + Bus                        ║");
        System.out.println("║    *  = Ada tempat         [ ] = Zona kosong    Angka = Koordinat                     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
    }
    
    private static String getSimbolZona(int baris, int kolom, Zona[][] kotaGrid,
    GraphTransportasi graphMRT, GraphTransportasi graphBus) {
        boolean adaMRT = graphMRT.adaKoneksi(baris, kolom);
        boolean adaBus = graphBus.adaKoneksi(baris, kolom);
        boolean adaTempat = kotaGrid[baris][kolom].headTempat != null;
        
        String simbol;
        if (adaMRT && adaBus) {
            simbol = "MB";
        } else if (adaMRT) {
            simbol = "M ";
        } else if (adaBus) {
            simbol = "B ";
        } else {
            simbol = "  ";
        }
        
        if (adaTempat) {
            simbol = simbol.trim() + "*";
        }
        
        while (simbol.length() < 4) {
            simbol = " " + simbol + " ";
        }
        if (simbol.length() > 4) {
            simbol = simbol.substring(0, 4);
        }
        
        return simbol;
    }
    
    public static void tampilkanHeaderCariTempat() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                   PENCARIAN TEMPAT                     ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Cari berdasarkan nama tempat                      ║");
        System.out.println("║  [2] Cari berdasarkan kategori                         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHeaderCariRute() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              PENCARIAN RUTE TRANSPORTASI               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHeaderDetailZona() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    DETAIL ZONA                         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHeaderJaringan() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║             JARINGAN TRANSPORTASI                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Lihat jaringan MRT                                ║");
        System.out.println("║  [2] Lihat jaringan Bus                                ║");
        System.out.println("║  [3] Lihat keduanya (ringkasan)                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHeaderTambahTempat() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                 TAMBAH TEMPAT BARU                     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHasilPencarianHeader(String keyword) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│                      HASIL PENCARIAN                           │");
        System.out.println("│  Keyword: \"" + keyword + "\"");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
    }
    
    public static void tampilkanItemHasil(int nomor, String nama, String kategori, 
    int baris, int kolom, String namaZona) {
        System.out.printf("│  %d. %s%n", nomor, nama);
        System.out.printf("│     Kategori : %s%n", kategori);
        System.out.printf("│     Lokasi   : Zona[%d][%d] - %s%n", baris, kolom, namaZona);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
    }
    
    public static void tampilkanHasilPencarianFooter(int ditemukan) {
        if (ditemukan == 0) {
            System.out.println("│  Tidak ada tempat yang ditemukan dengan keyword tersebut.     │");
        } else {
            System.out.printf("│  Total: %d tempat ditemukan                                    │%n", ditemukan);
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanHasilRute(HasilRute[] hasilRute, int jumlahHasil,
                                           int asalB, int asalK, int tujuanB, int tujuanK) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║  HASIL PENCARIAN RUTE: [%d,%d] ──────────> [%d,%d]                          ║%n",
                         asalB, asalK, tujuanB, tujuanK);
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        
        if (jumlahHasil == 0) {
            System.out.println("║                                                                            ║");
            System.out.println("║  [!] Tidak ada rute transportasi yang tersedia.                            ║");
            System.out.println("║      Coba gunakan zona yang memiliki akses MRT atau Bus.                   ║");
            System.out.println("║                                                                            ║");
        } else {
            for (int i = 0; i < jumlahHasil; i++) {
                HasilRute hr = hasilRute[i];
                
                if (i == 0) {
                    System.out.println("║  ★★★ REKOMENDASI TERBAIK ★★★                                             ║");
                } else {
                    System.out.printf("║  --- Alternatif %d ---                                                    ║%n", i);
                }
                
                System.out.printf("║  Moda      : %-60s  ║%n", hr.moda);
                System.out.printf("║  Waktu     : %-60s  ║%n", hr.getWaktuFormatted());
                System.out.printf("║  Harga     : %-60s  ║%n", hr.getHargaFormatted());
                System.out.printf("║  Transit   : %d kali                                                       ║%n", hr.jumlahTransit);
                System.out.println("║  Jalur     :                                                               ║");
                
                String jalur = hr.jalur;
                int maxLen = 70;
                while (jalur.length() > 0) {
                    String bagian;
                    if (jalur.length() <= maxLen) {
                        bagian = jalur;
                        jalur = "";
                    } else {
                        bagian = jalur.substring(0, maxLen);
                        jalur = jalur.substring(maxLen);
                    }
                    System.out.printf("║    %-72s  ║%n", bagian);
                }
                
                System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
            }
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanRiwayat(StackRiwayat riwayat) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║               RIWAYAT PENCARIAN (Stack)                ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        
        if (riwayat.isEmpty()) {
            System.out.println("║  Belum ada riwayat pencarian.                          ║");
            System.out.println("║  Gunakan menu [2] Cari Tempat untuk mencari.           ║");
        } else {
            System.out.println("║  (Terbaru di atas - LIFO)                              ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            
            StackNode current = riwayat.top;
            int nomor = 1;
            
            while (current != null && nomor <= 10) {
                System.out.printf("║  %2d. Keyword: \"%s\"%n", nomor, current.keyword);
                System.out.printf("║      Hasil  : Zona[%d][%d]%n", 
                                 current.hasilBaris, current.hasilKolom);
                if (!current.namaTempat.isEmpty()) {
                    System.out.printf("║      Tempat : %s%n", current.namaTempat);
                }
                System.out.println("║      ─────────────────────────────────────────────────");
                
                current = current.next;
                nomor++;
            }
            
            if (riwayat.getSize() > 10) {
                System.out.printf("║  ... dan %d riwayat lainnya                           ║%n", 
                                 riwayat.getSize() - 10);
            }
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanDetailZona(Zona zona, GraphTransportasi graphMRT, 
                                            GraphTransportasi graphBus) {
        int baris = zona.baris;
        int kolom = zona.kolom;
        
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.printf("│  ZONA [%d][%d] - %s%n", baris, kolom, zona.namaZona);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        boolean adaMRT = graphMRT.adaKoneksi(baris, kolom);
        boolean adaBus = graphBus.adaKoneksi(baris, kolom);
        
        System.out.print("│  Transportasi: ");
        if (adaMRT && adaBus) {
            System.out.println("MRT dan Bus tersedia");
        } else if (adaMRT) {
            System.out.println("Hanya MRT");
        } else if (adaBus) {
            System.out.println("Hanya Bus");
        } else {
            System.out.println("Tidak ada transportasi umum");
        }
        
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.printf("│  DAFTAR TEMPAT (%d tempat):%n", zona.jumlahTempat);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (zona.headTempat == null) {
            System.out.println("│    (Tidak ada tempat terdaftar di zona ini)");
        } else {
            TempatNode current = zona.headTempat;
            int nomor = 1;
            while (current != null) {
                System.out.printf("│    %d. %s%n", nomor, current.nama);
                System.out.printf("│       Kategori: %s%n", current.kategori);
                current = current.next;
                nomor++;
            }
        }
        
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.println("│  KONEKSI LANGSUNG:");
        
        if (adaMRT) {
            System.out.println("│    MRT ke zona:");
            GraphNode mrt = graphMRT.getKoneksi(baris, kolom);
            while (mrt != null) {
                System.out.printf("│      → [%d,%d] (waktu: %d menit, harga: Rp%d)%n",
                                 mrt.zonaBaris, mrt.zonaKolom, 
                                 mrt.waktuTempuh, mrt.harga);
                mrt = mrt.next;
            }
        }
        
        if (adaBus) {
            System.out.println("│    Bus ke zona:");
            GraphNode bus = graphBus.getKoneksi(baris, kolom);
            while (bus != null) {
                System.out.printf("│      → [%d,%d] (waktu: %d menit, harga: Rp%d)%n",
                                 bus.zonaBaris, bus.zonaKolom, 
                                 bus.waktuTempuh, bus.harga);
                bus = bus.next;
            }
        }
        
        if (!adaMRT && !adaBus) {
            System.out.println("│    (Tidak ada koneksi transportasi dari zona ini)");
        }
        
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanJaringan(GraphTransportasi graph, int gridSize) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.printf("│                  JARINGAN %s                                  │%n", graph.namaModa);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        int totalKoneksi = 0;
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                GraphNode node = graph.getKoneksi(i, j);
                if (node != null) {
                    System.out.printf("│  [%d,%d] terhubung ke:%n", i, j);
                    while (node != null) {
                        System.out.printf("│    → [%d,%d] (waktu: %2d mnt, Rp%d)%n",
                                         node.zonaBaris, node.zonaKolom, 
                                         node.waktuTempuh, node.harga);
                        totalKoneksi++;
                        node = node.next;
                    }
                }
            }
        }
        
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        System.out.printf("│  Total koneksi: %d                                             │%n", totalKoneksi);
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanKonfirmasiTambah(String nama, int baris, int kolom, String namaZona) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  [✓] Tempat berhasil ditambahkan!                      ║");
        System.out.printf("║      %s%n", nama);
        System.out.printf("║      Lokasi: Zona[%d][%d] - %s%n", baris, kolom, namaZona);
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanError(String pesan) {
        System.out.println("\n[!] " + pesan);
    }
    
    public static void tampilkanInfo(String pesan) {
        System.out.println("\n[i] " + pesan);
    }
    
    public static void tampilkanHeaderSearching() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              SEARCHING ALGORITHMS                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Linear Search - Cari Tempat                       ║");
        System.out.println("║  [2] Linear Search - Cari Zona by Jenis                ║");
        System.out.println("║  [3] Binary Search - Cari Rute by Waktu                ║");
        System.out.println("║  [4] Binary Search - Cari Rute by Harga                ║");
        System.out.println("║  [5] Jump Search - Cari Rute by Waktu                  ║");
        System.out.println("║  [6] Interpolation Search - Cari Rute by Waktu         ║");
        System.out.println("║  [0] Kembali                                           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public static void tampilkanHasilLinearSearchTempat(HasilPencarian head, String keyword) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│           HASIL LINEAR SEARCH - TEMPAT                         │");
        System.out.println("│  Keyword: \"" + keyword + "\"");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (head == null) {
            System.out.println("│  Tidak ada tempat yang ditemukan.                              │");
        } else {
            HasilPencarian current = head;
            int nomor = 1;
            while (current != null) {
                System.out.printf("│  %d. %s%n", nomor, current.namaTempat);
                System.out.printf("│     Kategori: %s%n", current.kategori);
                System.out.printf("│     Lokasi  : Zona[%d][%d] - %s%n", 
                                 current.zonaBaris, current.zonaKolom, current.namaZona);
                System.out.println("├────────────────────────────────────────────────────────────────┤");
                current = current.next;
                nomor++;
            }
            System.out.printf("│  Total ditemukan: %d tempat                                    │%n", nomor - 1);
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanHasilLinearSearchZona(Zona[] hasil, int count, String jenisZona) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│           HASIL LINEAR SEARCH - ZONA                           │");
        System.out.println("│  Jenis Zona: \"" + jenisZona + "\"");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        int found = 0;
        for (int i = 0; i < count && hasil[i] != null; i++) {
            System.out.printf("│  %d. Zona[%d][%d] - %s%n", 
                             i + 1, hasil[i].baris, hasil[i].kolom, hasil[i].namaZona);
            System.out.printf("│     Jumlah tempat: %d%n", hasil[i].jumlahTempat);
            found++;
        }
        
        if (found == 0) {
            System.out.println("│  Tidak ada zona yang ditemukan.                                │");
        } else {
            System.out.println("├────────────────────────────────────────────────────────────────┤");
            System.out.printf("│  Total ditemukan: %d zona                                      │%n", found);
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanHasilBinarySearch(HasilRute[] hasilRute, int index, 
                                                   int target, String kriteria) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│              HASIL BINARY SEARCH                               │");
        System.out.printf("│  Target %s: %d%n", kriteria, target);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (index == -1) {
            System.out.println("│  Rute dengan kriteria tersebut TIDAK DITEMUKAN.                │");
            System.out.println("│  Coba cari dengan nilai yang berbeda.                          │");
        } else {
            System.out.println("│  DITEMUKAN pada index: " + index);
            System.out.println("├────────────────────────────────────────────────────────────────┤");
            HasilRute hr = hasilRute[index];
            System.out.printf("│  Moda    : %s%n", hr.moda);
            System.out.printf("│  Waktu   : %s%n", hr.getWaktuFormatted());
            System.out.printf("│  Harga   : %s%n", hr.getHargaFormatted());
            System.out.printf("│  Transit : %d kali%n", hr.jumlahTransit);
            System.out.println("│  Jalur   :");
            
            String jalur = hr.jalur;
            int maxLen = 55;
            while (jalur.length() > 0) {
                String bagian = jalur.length() <= maxLen ? jalur : jalur.substring(0, maxLen);
                System.out.printf("│    %s%n", bagian);
                jalur = jalur.length() <= maxLen ? "" : jalur.substring(maxLen);
            }
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanHasilJumpSearch(HasilRute[] hasilRute, int index, 
                                                 int target) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│              HASIL JUMP SEARCH                                 │");
        System.out.printf("│  Target waktu: %d menit%n", target);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (index == -1) {
            System.out.println("│  Rute dengan waktu tersebut TIDAK DITEMUKAN.                   │");
        } else {
            System.out.println("│  DITEMUKAN pada index: " + index);
            HasilRute hr = hasilRute[index];
            System.out.printf("│  Moda: %s | Waktu: %s | Harga: %s%n", 
                             hr.moda, hr.getWaktuFormatted(), hr.getHargaFormatted());
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanHasilInterpolationSearch(HasilRute[] hasilRute, int index, 
                                                          int target) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│              HASIL INTERPOLATION SEARCH                        │");
        System.out.printf("│  Target waktu: %d menit%n", target);
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (index == -1) {
            System.out.println("│  Rute dengan waktu tersebut TIDAK DITEMUKAN.                   │");
        } else {
            System.out.println("│  DITEMUKAN pada index: " + index);
            HasilRute hr = hasilRute[index];
            System.out.printf("│  Moda: %s | Waktu: %s | Harga: %s%n", 
                             hr.moda, hr.getWaktuFormatted(), hr.getHargaFormatted());
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    public static void tampilkanDaftarRuteUntukSearch(HasilRute[] hasilRute, int jumlahHasil) {
        System.out.println();
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│              DAFTAR RUTE TERSEDIA (Sudah di-sort)              │");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        if (jumlahHasil == 0) {
            System.out.println("│  Belum ada rute. Cari rute terlebih dahulu (Menu 3).           │");
        } else {
            for (int i = 0; i < jumlahHasil; i++) {
                HasilRute hr = hasilRute[i];
                System.out.printf("│  [%d] %s - Waktu: %d menit - Harga: Rp%d%n", 
                                 i, hr.moda, hr.totalWaktu, hr.totalHarga);
            }
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
}
