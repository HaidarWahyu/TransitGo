import java.util.Scanner;

public class Main {
    int GRID_SIZE = 10;
    Zona[][] kotaGrid = new Zona[GRID_SIZE][GRID_SIZE];
    GraphTransportasi graphMRT = new GraphTransportasi("MRT", GRID_SIZE);
    GraphTransportasi graphBus = new GraphTransportasi("Bus", GRID_SIZE);
    StackRiwayat riwayatPencarian = new StackRiwayat();
    HasilRuteNode hasilRuteHead = null;
    Scanner scanner = new Scanner(System.in);
    SortingAlgorithm sortingAlgo = new SortingAlgorithm();
    
    public void run() {
        DataKota data = new DataKota();
        data.inisialisasiSemua(kotaGrid, graphMRT, graphBus, GRID_SIZE);
        TampilanUI.tampilkanWelcome();
        boolean running = true;
        
        while (running) {
            TampilanUI.tampilkanMenuUtama();
            int pilihan = inputAngka("Pilih menu: ");
            
            switch (pilihan) {
                case 1: menuPetaKota(); break;
                case 2: menuCariTempat(); break;
                case 3: menuCariRute(); break;
                case 4: menuRiwayat(); break;
                case 5: menuDetailZona(); break;
                case 6: menuJaringanTransportasi(); break;
                case 7: menuTambahTempat(); break;
                case 8: menuDemoSearching(); break;
                case 9: menuDemoSorting(); break;
                case 0: running = false; break;
                default: System.out.println("Error: Input tidak valid!"); break;
            }
        }
        
        TampilanUI.tampilkanGoodbye();
        scanner.close();
    }
    
    void menuPetaKota() {
        TampilanUI.tampilkanPetaKota(kotaGrid, graphMRT, graphBus, GRID_SIZE);
    }
    
    void menuCariTempat() {
        TampilanUI.tampilkanHeaderCariTempat();
        int pilihan = inputAngka("Pilih metode pencarian (1/2): ");
        
        if (pilihan != 1 && pilihan != 2) {
            System.out.println("Error: Input tidak valid!");
            return;
        }
        
        System.out.print("Masukkan keyword pencarian: ");
        String keyword = scanner.nextLine().trim();
        
        if (keyword.isEmpty()) {
            TampilanUI.tampilkanError("Keyword tidak boleh kosong!");
            return;
        }
        
        TampilanUI.tampilkanHasilPencarianHeader(keyword);
        int ditemukan;
        if (pilihan == 1) {
            ditemukan = PencarianService.linearSearchByNama(kotaGrid, GRID_SIZE, keyword, riwayatPencarian);
        } else {
            ditemukan = PencarianService.linearSearchByKategori(kotaGrid, GRID_SIZE, keyword, riwayatPencarian);
        }
        TampilanUI.tampilkanHasilPencarianFooter(ditemukan);
    }
    
    void menuCariRute() {
        TampilanUI.tampilkanHeaderCariRute();
        
        System.out.println("\n>> Masukkan lokasi ASAL:");
        int asalBaris = inputAngka("   Baris (0-9): ");
        int asalKolom = inputAngka("   Kolom (0-9): ");
        
        System.out.println("\n>> Masukkan lokasi TUJUAN:");
        int tujuanBaris = inputAngka("   Baris (0-9): ");
        int tujuanKolom = inputAngka("   Kolom (0-9): ");
        
        if (!PencarianService.validasiKoordinat(asalBaris, asalKolom, GRID_SIZE) || 
            !PencarianService.validasiKoordinat(tujuanBaris, tujuanKolom, GRID_SIZE)) {
            TampilanUI.tampilkanError("Koordinat tidak valid! Harus antara 0-9.");
            return;
        }
        
        if (asalBaris == tujuanBaris && asalKolom == tujuanKolom) {
            TampilanUI.tampilkanError("Lokasi asal dan tujuan sama!");
            return;
        }
        
        System.out.println("\n>> Urutkan berdasarkan:");
        System.out.println("   [1] Waktu tercepat");
        System.out.println("   [2] Harga termurah");
        System.out.println("   [3] Transit tersedikit");
        int kriteria = inputAngka("   Pilih (1/2/3): ");
        if (kriteria < 1 || kriteria > 3) {
            System.out.println("Error: Input tidak valid! Menggunakan default: Waktu tercepat");
            kriteria = 1;
        }
        
        System.out.println("\n>> Algoritma sorting:");
        System.out.println("   [1] Bubble Sort");
        System.out.println("   [2] Selection Sort");
        System.out.println("   [3] Insertion Sort");
        int algoritma = inputAngka("   Pilih (1/2/3): ");
        if (algoritma < 1 || algoritma > 3) {
            System.out.println("Error: Input tidak valid! Menggunakan default: Bubble Sort");
            algoritma = 1;
        }
        
        System.out.println("\n>> Mencari rute dengan BFS...");
        hasilRuteHead = PencarianService.cariSemuaRute(graphMRT, graphBus, GRID_SIZE,
                                                     asalBaris, asalKolom,
                                                     tujuanBaris, tujuanKolom);
        
        int jumlahHasil = countNodes(hasilRuteHead);
        
        String[] namaAlgoritma = {"", "Bubble Sort", "Selection Sort", "Insertion Sort"};
        String[] namaKriteria = {"", "Waktu", "Harga", "Transit"};
        
        System.out.println(">> Mengurutkan dengan " + namaAlgoritma[algoritma] + 
                          " berdasarkan " + namaKriteria[kriteria] + "...");
        
        hasilRuteHead = sortingAlgo.sortHasil(hasilRuteHead, algoritma, kriteria);
        
        HasilRute[] hasilRuteArray = linkedListToArray(hasilRuteHead, jumlahHasil);
        
        TampilanUI.tampilkanHasilRute(hasilRuteArray, jumlahHasil,
                                      asalBaris, asalKolom, tujuanBaris, tujuanKolom);
    }
    
    void menuRiwayat() {
        TampilanUI.tampilkanRiwayat(riwayatPencarian);
    }
    
    void menuDetailZona() {
        TampilanUI.tampilkanHeaderDetailZona();
        int baris = inputAngka("Masukkan baris zona (0-9): ");
        int kolom = inputAngka("Masukkan kolom zona (0-9): ");
        
        if (!PencarianService.validasiKoordinat(baris, kolom, GRID_SIZE)) {
            TampilanUI.tampilkanError("Koordinat tidak valid!");
            return;
        }
        
        TampilanUI.tampilkanDetailZona(kotaGrid[baris][kolom], graphMRT, graphBus);
    }
    
    void menuJaringanTransportasi() {
        TampilanUI.tampilkanHeaderJaringan();
        int pilihan = inputAngka("Pilih (1/2/3): ");
        
        if (pilihan != 1 && pilihan != 2 && pilihan != 3) {
            System.out.println("Error: Input tidak valid!");
            return;
        }
        
        if (pilihan == 1 || pilihan == 3) {
            TampilanUI.tampilkanJaringan(graphMRT, GRID_SIZE);
        }
        
        if (pilihan == 2 || pilihan == 3) {
            TampilanUI.tampilkanJaringan(graphBus, GRID_SIZE);
        }
    }
    
    void menuTambahTempat() {
        TampilanUI.tampilkanHeaderTambahTempat();
        int baris = inputAngka("Masukkan baris zona (0-9): ");
        int kolom = inputAngka("Masukkan kolom zona (0-9): ");
        
        if (!PencarianService.validasiKoordinat(baris, kolom, GRID_SIZE)) {
            TampilanUI.tampilkanError("Koordinat tidak valid!");
            return;
        }
        
        System.out.print("Nama tempat: ");
        String nama = scanner.nextLine().trim();
        
        if (nama.isEmpty()) {
            TampilanUI.tampilkanError("Nama tidak boleh kosong!");
            return;
        }
        
        System.out.println("Kategori: toko, rumah, kantor, restoran, kesehatan,");
        System.out.println("          pendidikan, hiburan, taman, transportasi, industri");
        System.out.print("Pilih kategori: ");
        String kategori = scanner.nextLine().toLowerCase().trim();
        
        if (kategori.isEmpty()) {
            kategori = "lainnya";
        }
        
        kotaGrid[baris][kolom].tambahTempat(nama, kategori);
        TampilanUI.tampilkanKonfirmasiTambah(nama, baris, kolom, kotaGrid[baris][kolom].namaZona);
    }
    
    void menuDemoSearching() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMO ALGORITMA SEARCHING                  ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Linear Search - Cari Tempat (Linked List)         ║");
        System.out.println("║  [2] Linear Search - Cari Zona (Array 2D)              ║");
        System.out.println("║  [3] Binary Search - Cari Rute by Waktu (Sorted Array) ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        int pilihan = inputAngka("Pilih demo (1/2/3): ");
        
        switch (pilihan) {
            case 1: demoLinearSearchTempat(); break;
            case 2: demoLinearSearchZona(); break;
            case 3: demoBinarySearch(); break;
            default: System.out.println("Error: Input tidak valid!"); break;
        }
    }
    
    void demoLinearSearchTempat() {
        System.out.println("\n═══ LINEAR SEARCH - Linked List Traversal ═══");
        System.out.println("Kompleksitas: O(n) dimana n = total tempat");
        System.out.println();
        
        System.out.print("Masukkan nama tempat yang dicari: ");
        String keyword = scanner.nextLine().trim();
        
        if (keyword.isEmpty()) {
            TampilanUI.tampilkanError("Keyword tidak boleh kosong!");
            return;
        }
        
        System.out.println("\n>> Proses Linear Search:");
        System.out.println("   - Traverse setiap zona di Array 2D [10x10]");
        System.out.println("   - Untuk setiap zona, traverse Linked List tempat");
        System.out.println("   - Bandingkan nama tempat dengan keyword");
        System.out.println();
        
        TampilanUI.tampilkanHasilPencarianHeader(keyword);
        int ditemukan = PencarianService.linearSearchByNama(kotaGrid, GRID_SIZE, keyword, riwayatPencarian);
        TampilanUI.tampilkanHasilPencarianFooter(ditemukan);
    }
    
    void demoLinearSearchZona() {
        System.out.println("\n═══ LINEAR SEARCH - Array 2D ═══");
        System.out.println("Kompleksitas: O(n²) dimana n = GRID_SIZE");
        System.out.println();
        
        System.out.println("Jenis zona yang tersedia:");
        System.out.println("  Perumahan, Pertokoan, Perkantoran, Industri, Pendidikan,");
        System.out.println("  Kesehatan, Hiburan, Taman, Transportasi, Campuran");
        System.out.println();
        
        System.out.print("Masukkan jenis zona yang dicari: ");
        String namaZona = scanner.nextLine().trim();
        
        if (namaZona.isEmpty()) {
            TampilanUI.tampilkanError("Nama zona tidak boleh kosong!");
            return;
        }
        
        System.out.println("\n>> Proses Linear Search:");
        System.out.println("   - Traverse setiap elemen di Array 2D");
        System.out.println("   - Bandingkan namaZona dengan keyword");
        System.out.println();
        
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│  HASIL PENCARIAN ZONA: \"" + namaZona + "\"│");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        
        int count = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (kotaGrid[i][j].namaZona.toLowerCase().contains(namaZona.toLowerCase())) {
                    count++;
                    System.out.printf("│  %d. Zona[%d][%d] - %s (%d tempat)%n", 
                                     count, i, j, kotaGrid[i][j].namaZona, 
                                     kotaGrid[i][j].jumlahTempat);
                }
            }
        }
        
        if (count == 0) {
            System.out.println("│  Tidak ada zona dengan nama tersebut.");
        } else {
            System.out.println("├────────────────────────────────────────────────────────────────┤");
            System.out.printf("│  Total: %d zona ditemukan%n", count);
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
    }
    
    void demoBinarySearch() {
        System.out.println("\n═══ BINARY SEARCH - Sorted Array ═══");
        System.out.println("Kompleksitas: O(log n)");
        System.out.println("SYARAT: Array harus sudah TERURUT!");
        System.out.println();
        
        System.out.println(">> Pertama, kita perlu data rute. Masukkan koordinat:");
        
        System.out.println("\n>> Lokasi ASAL:");
        int asalBaris = inputAngka("   Baris (0-9): ");
        int asalKolom = inputAngka("   Kolom (0-9): ");
        
        System.out.println("\n>> Lokasi TUJUAN:");
        int tujuanBaris = inputAngka("   Baris (0-9): ");
        int tujuanKolom = inputAngka("   Kolom (0-9): ");
        
        if (!PencarianService.validasiKoordinat(asalBaris, asalKolom, GRID_SIZE) || 
            !PencarianService.validasiKoordinat(tujuanBaris, tujuanKolom, GRID_SIZE)) {
            TampilanUI.tampilkanError("Koordinat tidak valid!");
            return;
        }
        
        hasilRuteHead = PencarianService.cariSemuaRute(graphMRT, graphBus, GRID_SIZE,
                                                   asalBaris, asalKolom,
                                                   tujuanBaris, tujuanKolom);
        
        int jumlahHasil = countNodes(hasilRuteHead);
        
        if (jumlahHasil == 0) {
            TampilanUI.tampilkanError("Tidak ada rute ditemukan. Coba koordinat lain.");
            return;
        }
        
        System.out.println("\n>> Mengurutkan hasil dengan Bubble Sort by Waktu...");
        sortingAlgo.bubbleSortByWaktu(hasilRuteHead);
        
        HasilRute[] hasilRuteArray = linkedListToArray(hasilRuteHead, jumlahHasil);
        
        System.out.println("\n>> Data rute (sudah terurut by waktu):");
        for (int i = 0; i < jumlahHasil; i++) {
            System.out.printf("   Index %d: %s - %d menit - %s%n", 
                             i, hasilRuteArray[i].moda, hasilRuteArray[i].totalWaktu,
                             hasilRuteArray[i].getHargaFormatted());
        }
        
        System.out.println();
        int targetWaktu = inputAngka("Masukkan waktu (menit) yang ingin dicari: ");
        
        System.out.println("\n>> Proses Binary Search:");
        System.out.println("   - left = 0, right = " + (jumlahHasil - 1));
        
        HasilRuteNode hasilNode = PencarianService.binarySearchRuteByWaktu(hasilRuteHead, targetWaktu);
        
        System.out.println();
        if (hasilNode != null) {
            System.out.println("┌────────────────────────────────────────────────────────────────┐");
            System.out.println("│  [✓] DITEMUKAN!");
            System.out.printf("│      Moda  : %s%n", hasilNode.data.moda);
            System.out.printf("│      Waktu : %d menit%n", hasilNode.data.totalWaktu);
            System.out.printf("│      Harga : %s%n", hasilNode.data.getHargaFormatted());
            System.out.println("└────────────────────────────────────────────────────────────────┘");
        } else {
            System.out.println("┌────────────────────────────────────────────────────────────────┐");
            System.out.println("│  [✗] TIDAK DITEMUKAN rute dengan waktu " + targetWaktu + " menit");
            System.out.println("└────────────────────────────────────────────────────────────────┘");
        }
    }
    
    void menuDemoSorting() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║               DEMO ALGORITMA SORTING                   ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  Akan menampilkan perbandingan 3 algoritma sorting:    ║");
        System.out.println("║  - Bubble Sort                                         ║");
        System.out.println("║  - Selection Sort                                      ║");
        System.out.println("║  - Insertion Sort                                      ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.println("\n>> Masukkan koordinat untuk mendapatkan data rute:");
        
        System.out.println("\n>> Lokasi ASAL:");
        int asalBaris = inputAngka("   Baris (0-9): ");
        int asalKolom = inputAngka("   Kolom (0-9): ");
        
        System.out.println("\n>> Lokasi TUJUAN:");
        int tujuanBaris = inputAngka("   Baris (0-9): ");
        int tujuanKolom = inputAngka("   Kolom (0-9): ");
        
        if (!PencarianService.validasiKoordinat(asalBaris, asalKolom, GRID_SIZE) || 
            !PencarianService.validasiKoordinat(tujuanBaris, tujuanKolom, GRID_SIZE)) {
            TampilanUI.tampilkanError("Koordinat tidak valid!");
            return;
        }
        
        HasilRuteNode originalHead = PencarianService.cariSemuaRute(graphMRT, graphBus, GRID_SIZE,
                                                                 asalBaris, asalKolom,
                                                                 tujuanBaris, tujuanKolom);
        
        int jumlahHasil = countNodes(originalHead);
        
        if (jumlahHasil == 0) {
            TampilanUI.tampilkanError("Tidak ada rute ditemukan. Coba koordinat lain.");
            return;
        }
        
        System.out.println("\n>> Urutkan berdasarkan:");
        System.out.println("   [1] Waktu");
        System.out.println("   [2] Harga");
        System.out.println("   [3] Transit");
        int kriteria = inputAngka("   Pilih (1/2/3): ");
        if (kriteria < 1 || kriteria > 3) {
            System.out.println("Error: Input tidak valid! Menggunakan default: Waktu");
            kriteria = 1;
        }
        
        String[] namaKriteria = {"", "Waktu", "Harga", "Transit"};
        
        HasilRute[] hasilRuteArray = linkedListToArray(originalHead, jumlahHasil);
        
        System.out.println("\n┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│  DATA SEBELUM SORTING:                                         │");
        System.out.println("├────────────────────────────────────────────────────────────────┤");
        for (int i = 0; i < jumlahHasil; i++) {
            System.out.printf("│  %d. %s - Waktu: %d mnt, Harga: %s, Transit: %d%n",
                             i+1, hasilRuteArray[i].moda, hasilRuteArray[i].totalWaktu,
                             hasilRuteArray[i].getHargaFormatted(), hasilRuteArray[i].jumlahTransit);
        }
        System.out.println("└────────────────────────────────────────────────────────────────┘");
        
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("                    PERBANDINGAN SORTING");
        System.out.println("                  Kriteria: " + namaKriteria[kriteria]);
        System.out.println("═══════════════════════════════════════════════════════════════");
        
        HasilRuteNode copyBubble = copyLinkedList(originalHead);
        HasilRuteNode copySelection = copyLinkedList(originalHead);
        HasilRuteNode copyInsertion = copyLinkedList(originalHead);
        
        System.out.println("\n>> BUBBLE SORT");
        System.out.println("   Cara kerja: Bandingkan elemen bersebelahan, tukar jika salah urutan");
        if (kriteria == 1) sortingAlgo.bubbleSortByWaktu(copyBubble);
        else if (kriteria == 2) sortingAlgo.bubbleSortByHarga(copyBubble);
        else if (kriteria == 3) sortingAlgo.bubbleSortByTransit(copyBubble);
        else {
            System.out.println("Error: Kriteria sorting tidak valid!");
            return;
        }
        HasilRute[] arrayBubble = linkedListToArray(copyBubble, jumlahHasil);
        tampilkanHasilSort(arrayBubble, jumlahHasil);
        
        System.out.println("\n>> SELECTION SORT");
        System.out.println("   Cara kerja: Cari minimum, pindahkan ke posisi awal, ulangi");
        if (kriteria == 1) copySelection = sortingAlgo.selectionSortByWaktu(copySelection);
        else if (kriteria == 2) copySelection = sortingAlgo.selectionSortByHarga(copySelection);
        else if (kriteria == 3) copySelection = sortingAlgo.selectionSortByTransit(copySelection);
        else {
            System.out.println("Error: Kriteria sorting tidak valid!");
            return;
        }
        HasilRute[] arraySelection = linkedListToArray(copySelection, jumlahHasil);
        tampilkanHasilSort(arraySelection, jumlahHasil);
        
        System.out.println("\n>> INSERTION SORT");
        System.out.println("   Cara kerja: Ambil elemen, sisipkan di posisi yang tepat");
        if (kriteria == 1) copyInsertion = sortingAlgo.insertionSortByWaktu(copyInsertion);
        else if (kriteria == 2) copyInsertion = sortingAlgo.insertionSortByHarga(copyInsertion);
        else if (kriteria == 3) copyInsertion = sortingAlgo.insertionSortByTransit(copyInsertion);
        else {
            System.out.println("Error: Kriteria sorting tidak valid!");
            return;
        }
        HasilRute[] arrayInsertion = linkedListToArray(copyInsertion, jumlahHasil);
        tampilkanHasilSort(arrayInsertion, jumlahHasil);
        
        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("   Semua algoritma menghasilkan urutan yang SAMA!");
        System.out.println("   Perbedaannya ada di CARA dan EFISIENSI prosesnya.");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }
    
    HasilRuteNode copyLinkedList(HasilRuteNode head) {
        if (head == null) return null;
        HasilRuteNode newHead = new HasilRuteNode(new HasilRute(
            head.data.moda, head.data.jalur, 
            head.data.totalWaktu, head.data.totalHarga, 
            head.data.jumlahTransit
        ));
        HasilRuteNode current = head.next;
        HasilRuteNode newCurrent = newHead;
        
        while (current != null) {
            newCurrent.next = new HasilRuteNode(new HasilRute(
                current.data.moda, current.data.jalur,
                current.data.totalWaktu, current.data.totalHarga,
                current.data.jumlahTransit
            ));
            newCurrent = newCurrent.next;
            current = current.next;
        }
        
        return newHead;
    }
    
    int countNodes(HasilRuteNode head) {
        int count = 0;
        HasilRuteNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    HasilRute[] linkedListToArray(HasilRuteNode head, int size) {
        HasilRute[] array = new HasilRute[size];
        HasilRuteNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    void tampilkanHasilSort(HasilRute[] arr, int length) {
        System.out.println("   Hasil:");
        for (int i = 0; i < length; i++) {
            System.out.printf("     %d. %s - Waktu: %d mnt, Harga: %s, Transit: %d%n",
                             i+1, arr[i].moda, arr[i].totalWaktu,
                             arr[i].getHargaFormatted(), arr[i].jumlahTransit);
        }
    }
    
    int inputAngka(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("[!] Masukkan angka. " + prompt);
            scanner.next();
        }
        int angka = scanner.nextInt();
        scanner.nextLine();
        return angka;
    }
    
    public static void main(String[] args) {
        Main program = new Main();
        program.run();
    }
}