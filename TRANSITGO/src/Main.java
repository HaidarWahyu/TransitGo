public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEM REKOMENDASI RUTE TRANSPORTASI ===\n");
        
        // 1. Buat zona-zona (Linked List)
        System.out.println("=== SETUP ZONA ===");
        Zona zona1 = new Zona(101, "Zona Pusat", 1, 1);
        Zona zona2 = new Zona(102, "Zona Utara", 1, 2);
        Zona zona3 = new Zona(103, "Zona Timur", 2, 2);
        Zona zona4 = new Zona(104, "Zona Selatan", 2, 3);
        Zona zona5 = new Zona(105, "Zona Barat", 3, 3);
        
        // 2. Tambah tempat ke zona (Linked List tempat)
        zona1.tambahTempat("Rumah Andi", "rumah");
        zona1.tambahTempat("Kantor A", "kantor");
        
        zona3.tambahTempat("Toko Kamera Jaya", "toko kamera");
        zona3.tambahTempat("Minimarket", "minimarket");
        
        zona5.tambahTempat("Toko Kamera Sentosa", "toko kamera");
        zona5.tambahTempat("Restoran", "restoran");
        
        zona4.tambahTempat("Mall", "mall");
        
        // 3. Buat graph MRT
        System.out.println("\n=== SETUP GRAPH MRT ===");
        Graph graphMRT = new Graph();
        
        // Tambah vertex (zona)
        graphMRT.addVertex(101, "Zona Pusat");
        graphMRT.addVertex(102, "Zona Utara");
        graphMRT.addVertex(103, "Zona Timur");
        graphMRT.addVertex(104, "Zona Selatan");
        graphMRT.addVertex(105, "Zona Barat");
        
        // Tambah edge (jalur MRT)
        graphMRT.addEdge(101, 102, 5, 5000, "MRT");
        graphMRT.addEdge(102, 103, 8, 8000, "MRT");
        graphMRT.addEdge(103, 104, 4, 4000, "MRT");
        graphMRT.addEdge(104, 105, 10, 10000, "MRT");
        graphMRT.addEdge(101, 105, 15, 15000, "MRT"); // jalur langsung
        
        graphMRT.printGraph();
        
        // 4. LINEAR SEARCH: Cari zona yang punya "toko kamera"
        System.out.println("\n=== LINEAR SEARCH: Zona dengan toko kamera ===");
        Zona[] semuaZona = {zona1, zona2, zona3, zona4, zona5};
        for (Zona zona : semuaZona) {
            if (zona.punyaTempatKategori("toko kamera")) {
                System.out.println(zona.nama + " memiliki toko kamera");
            }
        }
        
        // 5. BFS: Cari rute dengan transit minimal
        System.out.println("\n=== BFS: Rute dari Zona Pusat ke Zona Barat ===");
        graphMRT.bfs(101, 105);
        
        // 6. DIJKSTRA: Cari rute tercepat
        System.out.println("\n=== DIJKSTRA: Rute tercepat ===");
        Rute ruteCepat = graphMRT.dijkstra(101, 105, false);
        if (ruteCepat != null) {
            ruteCepat.printRute(graphMRT);
        }
        
        // 7. DIJKSTRA: Cari rute termurah
        System.out.println("\n=== DIJKSTRA: Rute termurah ===");
        Rute ruteMurah = graphMRT.dijkstra(101, 105, true);
        if (ruteMurah != null) {
            ruteMurah.printRute(graphMRT);
        }
        
        // 8. SORTING: Contoh sorting manual (SELECTION SORT - lebih cocok untuk Dijkstra)
        System.out.println("\n=== SORTING: Selection Sort Example ===");
        int[] angka = {5, 2, 8, 1, 9};
        System.out.print("Sebelum sorting: ");
        for (int n : angka) System.out.print(n + " ");
        
        // Selection Sort (sama seperti di Dijkstra extractMin)
        for (int i = 0; i < angka.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < angka.length; j++) {
                if (angka[j] < angka[minIndex]) {
                    minIndex = j;
                }
            }
            // Tukar
            int temp = angka[minIndex];
            angka[minIndex] = angka[i];
            angka[i] = temp;
        }
        
        System.out.print("\nSetelah sorting: ");
        for (int n : angka) System.out.print(n + " ");
        
        // 9. Tampilkan semua tempat
        System.out.println("\n\n=== DAFTAR TEMPAT PER ZONA ===");
        for (Zona zona : semuaZona) {
            zona.tampilkanTempat();
        }
        
        System.out.println("\n=== PROGRAM SELESAI ===");
    }
}