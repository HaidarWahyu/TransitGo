public class DataKota {
    
    void inisialisasiZona(Zona[][] kotaGrid, int gridSize) {
        String[] jenisZona = {
            "Perumahan", "Pertokoan", "Perkantoran", "Industri",
            "Pendidikan", "Kesehatan", "Hiburan", "Taman",
            "Transportasi", "Campuran"
        };
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                String nama = jenisZona[(i + j) % jenisZona.length];
                kotaGrid[i][j] = new Zona(i, j, nama);
            }
        }
    }
    
    void inisialisasiTempat(Zona[][] kotaGrid) {
        kotaGrid[0][0].tambahTempat("Rumah Pak Budi", "rumah");
        kotaGrid[0][0].tambahTempat("Rumah Bu Ani", "rumah");
        kotaGrid[0][0].tambahTempat("Minimarket Sejahtera", "toko");
        
        kotaGrid[1][1].tambahTempat("Toko Kamera Fokus", "toko");
        kotaGrid[1][1].tambahTempat("Toko Elektronik Jaya", "toko");
        kotaGrid[1][1].tambahTempat("Toko Buku Gramedia", "toko");
        
        kotaGrid[2][2].tambahTempat("Kantor PT Maju Jaya", "kantor");
        kotaGrid[2][2].tambahTempat("Kantor Bank BCA", "kantor");
        
        kotaGrid[2][3].tambahTempat("Kantor PT ABC Tech", "kantor");
        kotaGrid[2][3].tambahTempat("Startup XYZ", "kantor");
        
        kotaGrid[3][5].tambahTempat("Toko Olahraga Sport", "toko");
        kotaGrid[3][5].tambahTempat("Restoran Padang Sederhana", "restoran");
        
        kotaGrid[3][7].tambahTempat("RS Sehat Selalu", "kesehatan");
        kotaGrid[3][7].tambahTempat("Apotek K-24", "kesehatan");
        kotaGrid[3][7].tambahTempat("Klinik Pratama", "kesehatan");
        
        kotaGrid[5][2].tambahTempat("Stasiun MRT Central", "transportasi");
        kotaGrid[5][2].tambahTempat("Terminal Bus Kota", "transportasi");
        
        kotaGrid[5][5].tambahTempat("Mall Central Plaza", "hiburan");
        kotaGrid[5][5].tambahTempat("Bioskop XXI", "hiburan");
        kotaGrid[5][5].tambahTempat("Toko Kamera Pro", "toko");
        kotaGrid[5][5].tambahTempat("Food Court Nusantara", "restoran");
        
        kotaGrid[5][7].tambahTempat("Taman Kota Indah", "taman");
        kotaGrid[5][7].tambahTempat("Lapangan Olahraga", "olahraga");
        
        kotaGrid[6][4].tambahTempat("SMA Negeri 1", "pendidikan");
        kotaGrid[6][4].tambahTempat("Bimbel Primagama", "pendidikan");
        
        kotaGrid[7][3].tambahTempat("Cafe Kopi Kenangan", "restoran");
        kotaGrid[7][3].tambahTempat("Co-working Space", "kantor");
        
        kotaGrid[8][2].tambahTempat("Universitas Negeri", "pendidikan");
        kotaGrid[8][2].tambahTempat("Perpustakaan Kota", "pendidikan");
        kotaGrid[8][2].tambahTempat("Kantin Mahasiswa", "restoran");
        
        kotaGrid[8][8].tambahTempat("Pabrik Tekstil", "industri");
        kotaGrid[8][8].tambahTempat("Gudang Logistik", "industri");
        
        kotaGrid[9][5].tambahTempat("Terminal Bus Selatan", "transportasi");
        
        kotaGrid[9][9].tambahTempat("Toko Kamera Vintage", "toko");
        kotaGrid[9][9].tambahTempat("Kafe Santai", "restoran");
        kotaGrid[9][9].tambahTempat("Galeri Seni", "hiburan");
    }
    
    void inisialisasiMRT(GraphTransportasi graphMRT, int gridSize) {
        graphMRT.tambahKoneksiDuaArah(0, 2, 1, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(1, 2, 2, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(2, 2, 3, 2, 4, 5000);
        graphMRT.tambahKoneksiDuaArah(3, 2, 4, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(4, 2, 5, 2, 4, 6000);
        graphMRT.tambahKoneksiDuaArah(5, 2, 6, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(6, 2, 7, 2, 4, 5000);
        graphMRT.tambahKoneksiDuaArah(7, 2, 8, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(8, 2, 9, 2, 4, 6000);
        
        graphMRT.tambahKoneksiDuaArah(5, 0, 5, 1, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 1, 5, 2, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 2, 5, 3, 4, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 3, 5, 4, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 4, 5, 5, 4, 6000);
        graphMRT.tambahKoneksiDuaArah(5, 5, 5, 6, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 6, 5, 7, 4, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 7, 5, 8, 3, 5000);
        graphMRT.tambahKoneksiDuaArah(5, 8, 5, 9, 4, 6000);
        
        graphMRT.tambahKoneksiDuaArah(2, 2, 3, 3, 5, 6000);
        graphMRT.tambahKoneksiDuaArah(3, 3, 4, 4, 5, 6000);
        graphMRT.tambahKoneksiDuaArah(4, 4, 5, 5, 5, 6000);
    }
    
    void inisialisasiBus(GraphTransportasi graphBus, int gridSize) {
        for (int i = 0; i < gridSize - 1; i++) {
            graphBus.tambahKoneksiDuaArah(0, i, 0, i+1, 8, 2500);
            graphBus.tambahKoneksiDuaArah(9, i, 9, i+1, 8, 2500);
            graphBus.tambahKoneksiDuaArah(i, 0, i+1, 0, 8, 2500);
            graphBus.tambahKoneksiDuaArah(i, 9, i+1, 9, 8, 2500);
        }
        
        for (int i = 0; i < gridSize - 1; i++) {
            graphBus.tambahKoneksiDuaArah(i, i, i+1, i+1, 10, 3000);
        }
        
        for (int i = 0; i < gridSize - 1; i++) {
            graphBus.tambahKoneksiDuaArah(i, 5, i+1, 5, 9, 2500);
        }
        
        for (int j = 0; j < gridSize - 1; j++) {
            graphBus.tambahKoneksiDuaArah(3, j, 3, j+1, 9, 2500);
        }
        
        for (int j = 0; j < gridSize - 1; j++) {
            graphBus.tambahKoneksiDuaArah(7, j, 7, j+1, 9, 2500);
        }
        
        graphBus.tambahKoneksiDuaArah(1, 1, 1, 3, 12, 3500);
        graphBus.tambahKoneksiDuaArah(1, 1, 3, 1, 12, 3500);
        graphBus.tambahKoneksiDuaArah(2, 3, 3, 5, 15, 4000);
        graphBus.tambahKoneksiDuaArah(3, 7, 5, 7, 12, 3500);
        graphBus.tambahKoneksiDuaArah(6, 3, 8, 2, 14, 3500);
        graphBus.tambahKoneksiDuaArah(7, 5, 9, 5, 12, 3000);
        graphBus.tambahKoneksiDuaArah(4, 6, 6, 4, 14, 3500);
        graphBus.tambahKoneksiDuaArah(2, 8, 4, 8, 12, 3000);
        graphBus.tambahKoneksiDuaArah(6, 6, 8, 8, 14, 3500);
    }
    
    void inisialisasiSemua(Zona[][] kotaGrid, GraphTransportasi graphMRT, 
                           GraphTransportasi graphBus, int gridSize) {
        inisialisasiZona(kotaGrid, gridSize);
        inisialisasiTempat(kotaGrid);
        inisialisasiMRT(graphMRT, gridSize);
        inisialisasiBus(graphBus, gridSize);
    }
}