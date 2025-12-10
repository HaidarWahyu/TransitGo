public class PencarianService {
    
    public static int linearSearchByNama(Zona[][] kotaGrid, int gridSize, String keyword, 
                                          StackRiwayat riwayat) {
        return linearSearchTempat(kotaGrid, gridSize, keyword, true, riwayat);
    }
    
    public static int linearSearchByKategori(Zona[][] kotaGrid, int gridSize, String keyword,
                                              StackRiwayat riwayat) {
        return linearSearchTempat(kotaGrid, gridSize, keyword, false, riwayat);
    }
    
    private static int linearSearchTempat(Zona[][] kotaGrid, int gridSize, String keyword,
                                           boolean byNama, StackRiwayat riwayat) {
        int ditemukan = 0;
        String keywordLower = keyword.toLowerCase();
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Zona zona = kotaGrid[i][j];
                TempatNode current = zona.headTempat;
                
                while (current != null) {
                    boolean cocok;
                    
                    if (byNama) {
                        cocok = current.nama.toLowerCase().contains(keywordLower);
                    } else {
                        cocok = current.kategori.toLowerCase().contains(keywordLower);
                    }
                    
                    if (cocok) {
                        ditemukan++;
                        TampilanUI.tampilkanItemHasil(ditemukan, current.nama, 
                                                      current.kategori, i, j, zona.namaZona);
                        riwayat.push(keyword, i, j, current.nama);
                    }
                    
                    current = current.next;
                }
            }
        }
        
        return ditemukan;
    }
    
    public static TempatNode linearSearchExact(Zona zona, String namaTempat) {
        TempatNode current = zona.headTempat;
        
        while (current != null) {
            if (current.nama.equalsIgnoreCase(namaTempat)) {
                return current;
            }
            current = current.next;
        }
        
        return null;
    }
    
    public static Zona linearSearchZonaByNama(Zona[][] kotaGrid, int gridSize, String namaZona) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (kotaGrid[i][j].namaZona.equalsIgnoreCase(namaZona)) {
                    return kotaGrid[i][j];
                }
            }
        }
        return null;
    }
    
    public static HasilRuteNode linearSearchRuteByModa(HasilRuteNode head, String moda) {
        HasilRuteNode current = head;
        while (current != null) {
            if (current.data.moda.equalsIgnoreCase(moda)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    public static HasilRuteNode binarySearchRuteByWaktu(HasilRuteNode head, int targetWaktu) {
        HasilRuteNode[] array = linkedListToArray(head);
        int jumlahHasil = countNodes(head);
        
        int left = 0;
        int right = jumlahHasil - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid].data.totalWaktu == targetWaktu) {
                return array[mid];
            } else if (array[mid].data.totalWaktu < targetWaktu) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }
    
    public static HasilRuteNode binarySearchRuteByHarga(HasilRuteNode head, int targetHarga) {
        HasilRuteNode[] array = linkedListToArray(head);
        int jumlahHasil = countNodes(head);
        
        int left = 0;
        int right = jumlahHasil - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid].data.totalHarga == targetHarga) {
                return array[mid];
            } else if (array[mid].data.totalHarga < targetHarga) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return null;
    }
    
    public static HasilRuteNode jumpSearchRuteByWaktu(HasilRuteNode head, int targetWaktu) {
        HasilRuteNode[] array = linkedListToArray(head);
        int jumlahHasil = countNodes(head);
        
        if (jumlahHasil == 0) return null;
        
        int jump = (int) Math.sqrt(jumlahHasil);
        int prev = 0;
        int curr = jump;
        
        while (curr < jumlahHasil && array[curr].data.totalWaktu < targetWaktu) {
            prev = curr;
            curr += jump;
        }
        
        for (int i = prev; i < Math.min(curr + 1, jumlahHasil); i++) {
            if (array[i].data.totalWaktu == targetWaktu) {
                return array[i];
            }
        }
        
        return null;
    }
    
    public static HasilRuteNode interpolationSearchRuteByWaktu(HasilRuteNode head, int targetWaktu) {
        HasilRuteNode[] array = linkedListToArray(head);
        int jumlahHasil = countNodes(head);
        
        int low = 0;
        int high = jumlahHasil - 1;
        
        while (low <= high && targetWaktu >= array[low].data.totalWaktu 
               && targetWaktu <= array[high].data.totalWaktu) {
            
            if (array[high].data.totalWaktu == array[low].data.totalWaktu) {
                if (array[low].data.totalWaktu == targetWaktu) {
                    return array[low];
                }
                return null;
            }
            
            int pos = low + ((targetWaktu - array[low].data.totalWaktu) * (high - low)) 
                      / (array[high].data.totalWaktu - array[low].data.totalWaktu);
            
            if (array[pos].data.totalWaktu == targetWaktu) {
                return array[pos];
            } else if (array[pos].data.totalWaktu < targetWaktu) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        
        return null;
    }
    
    public static HasilRute cariRuteBFS(GraphTransportasi graph, int gridSize,
                                         int asalBaris, int asalKolom,
                                         int tujuanBaris, int tujuanKolom) {
        if (!graph.adaKoneksi(asalBaris, asalKolom)) {
            return null;
        }
        
        QueueBFS queue = new QueueBFS();
        boolean[][] visited = new boolean[gridSize][gridSize];
        
        String jalurAwal = "[" + asalBaris + "," + asalKolom + "]";
        queue.enqueue(asalBaris, asalKolom, 0, 0, jalurAwal, 0);
        visited[asalBaris][asalKolom] = true;
        
        while (!queue.isEmpty()) {
            QueueNode current = queue.dequeue();
            
            if (current.baris == tujuanBaris && current.kolom == tujuanKolom) {
                int transit = hitungTransit(current.jalur);
                return new HasilRute(graph.namaModa, current.jalur,
                                    current.totalWaktu, current.totalHarga, transit);
            }
            
            GraphNode neighbor = graph.getKoneksi(current.baris, current.kolom);
            while (neighbor != null) {
                if (!visited[neighbor.zonaBaris][neighbor.zonaKolom]) {
                    visited[neighbor.zonaBaris][neighbor.zonaKolom] = true;
                    
                    String jalurBaru = current.jalur + 
                        " -> [" + neighbor.zonaBaris + "," + neighbor.zonaKolom + "]";
                    
                    queue.enqueue(
                        neighbor.zonaBaris, 
                        neighbor.zonaKolom,
                        current.totalWaktu + neighbor.waktuTempuh,
                        current.totalHarga + neighbor.harga,
                        jalurBaru,
                        current.jumlahTransit + 1
                    );
                }
                neighbor = neighbor.next;
            }
        }
        
        return null;
    }
    
    public static HasilRuteNode cariSemuaRute(GraphTransportasi graphMRT, GraphTransportasi graphBus,
                                               int gridSize, int asalBaris, int asalKolom,
                                               int tujuanBaris, int tujuanKolom) {
        HasilRuteNode head = null;
        HasilRuteNode tail = null;
        
        HasilRute ruteMRT = cariRuteBFS(graphMRT, gridSize, asalBaris, asalKolom, 
                                        tujuanBaris, tujuanKolom);
        if (ruteMRT != null) {
            HasilRuteNode newNode = new HasilRuteNode(ruteMRT);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        HasilRute ruteBus = cariRuteBFS(graphBus, gridSize, asalBaris, asalKolom, 
                                        tujuanBaris, tujuanKolom);
        if (ruteBus != null) {
            HasilRuteNode newNode = new HasilRuteNode(ruteBus);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        return head;
    }
    
    private static int hitungTransit(String jalur) {
        int count = 0;
        for (int i = 0; i < jalur.length(); i++) {
            if (jalur.charAt(i) == '>') count++;
        }
        return count;
    }
    
    public static boolean validasiKoordinat(int baris, int kolom, int gridSize) {
        return baris >= 0 && baris < gridSize && kolom >= 0 && kolom < gridSize;
    }
    
    private static HasilRuteNode[] linkedListToArray(HasilRuteNode head) {
        int count = countNodes(head);
        HasilRuteNode[] array = new HasilRuteNode[count];
        HasilRuteNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current;
            current = current.next;
        }
        return array;
    }
    
    private static int countNodes(HasilRuteNode head) {
        int count = 0;
        HasilRuteNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}