public class SortingAlgorithm {
    
    public void bubbleSortByWaktu(HasilRuteNode head) {
        if (head == null || head.next == null) return;
        
        boolean swapped;
        do {
            swapped = false;
            HasilRuteNode current = head;
            HasilRuteNode prev = null;
            
            while (current != null && current.next != null) {
                HasilRuteNode next = current.next;
                
                if (current.data.totalWaktu > next.data.totalWaktu) {
                    if (prev == null) {
                        current.next = next.next;
                        next.next = current;
                        head = next;
                    } else {
                        prev.next = next;
                        current.next = next.next;
                        next.next = current;
                    }
                    swapped = true;
                    prev = next;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
    }
    
    public void bubbleSortByHarga(HasilRuteNode head) {
        if (head == null || head.next == null) return;
        
        boolean swapped;
        do {
            swapped = false;
            HasilRuteNode current = head;
            HasilRuteNode prev = null;
            
            while (current != null && current.next != null) {
                HasilRuteNode next = current.next;
                
                if (current.data.totalHarga > next.data.totalHarga) {
                    if (prev == null) {
                        current.next = next.next;
                        next.next = current;
                        head = next;
                    } else {
                        prev.next = next;
                        current.next = next.next;
                        next.next = current;
                    }
                    swapped = true;
                    prev = next;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
    }
    
    public void bubbleSortByTransit(HasilRuteNode head) {
        if (head == null || head.next == null) return;
        
        boolean swapped;
        do {
            swapped = false;
            HasilRuteNode current = head;
            HasilRuteNode prev = null;
            
            while (current != null && current.next != null) {
                HasilRuteNode next = current.next;
                
                if (current.data.jumlahTransit > next.data.jumlahTransit) {
                    if (prev == null) {
                        current.next = next.next;
                        next.next = current;
                        head = next;
                    } else {
                        prev.next = next;
                        current.next = next.next;
                        next.next = current;
                    }
                    swapped = true;
                    prev = next;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
    }
    
    public HasilRuteNode selectionSortByWaktu(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode current = head;
        HasilRuteNode sortedHead = null;
        HasilRuteNode sortedTail = null;
        
        while (current != null) {
            HasilRuteNode minNode = current;
            HasilRuteNode minPrev = null;
            HasilRuteNode temp = current;
            HasilRuteNode prev = null;
            
            while (temp != null) {
                if (temp.data.totalWaktu < minNode.data.totalWaktu) {
                    minNode = temp;
                    minPrev = prev;
                }
                prev = temp;
                temp = temp.next;
            }
            
            if (minPrev == null) {
                current = current.next;
            } else {
                minPrev.next = minNode.next;
            }
            
            if (sortedHead == null) {
                sortedHead = minNode;
                sortedTail = minNode;
                minNode.next = null;
            } else {
                sortedTail.next = minNode;
                sortedTail = minNode;
                minNode.next = null;
            }
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode selectionSortByHarga(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode current = head;
        HasilRuteNode sortedHead = null;
        HasilRuteNode sortedTail = null;
        
        while (current != null) {
            HasilRuteNode minNode = current;
            HasilRuteNode minPrev = null;
            HasilRuteNode temp = current;
            HasilRuteNode prev = null;
            
            while (temp != null) {
                if (temp.data.totalHarga < minNode.data.totalHarga) {
                    minNode = temp;
                    minPrev = prev;
                }
                prev = temp;
                temp = temp.next;
            }
            
            if (minPrev == null) {
                current = current.next;
            } else {
                minPrev.next = minNode.next;
            }
            
            if (sortedHead == null) {
                sortedHead = minNode;
                sortedTail = minNode;
                minNode.next = null;
            } else {
                sortedTail.next = minNode;
                sortedTail = minNode;
                minNode.next = null;
            }
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode selectionSortByTransit(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode current = head;
        HasilRuteNode sortedHead = null;
        HasilRuteNode sortedTail = null;
        
        while (current != null) {
            HasilRuteNode minNode = current;
            HasilRuteNode minPrev = null;
            HasilRuteNode temp = current;
            HasilRuteNode prev = null;
            
            while (temp != null) {
                if (temp.data.jumlahTransit < minNode.data.jumlahTransit) {
                    minNode = temp;
                    minPrev = prev;
                }
                prev = temp;
                temp = temp.next;
            }
            
            if (minPrev == null) {
                current = current.next;
            } else {
                minPrev.next = minNode.next;
            }
            
            if (sortedHead == null) {
                sortedHead = minNode;
                sortedTail = minNode;
                minNode.next = null;
            } else {
                sortedTail.next = minNode;
                sortedTail = minNode;
                minNode.next = null;
            }
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode insertionSortByWaktu(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode sortedHead = null;
        HasilRuteNode current = head;
        
        while (current != null) {
            HasilRuteNode next = current.next;
            
            if (sortedHead == null || current.data.totalWaktu < sortedHead.data.totalWaktu) {
                current.next = sortedHead;
                sortedHead = current;
            } else {
                HasilRuteNode search = sortedHead;
                while (search.next != null && search.next.data.totalWaktu < current.data.totalWaktu) {
                    search = search.next;
                }
                current.next = search.next;
                search.next = current;
            }
            
            current = next;
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode insertionSortByHarga(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode sortedHead = null;
        HasilRuteNode current = head;
        
        while (current != null) {
            HasilRuteNode next = current.next;
            
            if (sortedHead == null || current.data.totalHarga < sortedHead.data.totalHarga) {
                current.next = sortedHead;
                sortedHead = current;
            } else {
                HasilRuteNode search = sortedHead;
                while (search.next != null && search.next.data.totalHarga < current.data.totalHarga) {
                    search = search.next;
                }
                current.next = search.next;
                search.next = current;
            }
            
            current = next;
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode insertionSortByTransit(HasilRuteNode head) {
        if (head == null || head.next == null) return head;
        
        HasilRuteNode sortedHead = null;
        HasilRuteNode current = head;
        
        while (current != null) {
            HasilRuteNode next = current.next;
            
            if (sortedHead == null || current.data.jumlahTransit < sortedHead.data.jumlahTransit) {
                current.next = sortedHead;
                sortedHead = current;
            } else {
                HasilRuteNode search = sortedHead;
                while (search.next != null && search.next.data.jumlahTransit < current.data.jumlahTransit) {
                    search = search.next;
                }
                current.next = search.next;
                search.next = current;
            }
            
            current = next;
        }
        
        return sortedHead;
    }
    
    public HasilRuteNode sortHasil(HasilRuteNode head, int algoritma, int kriteria) {
        switch (algoritma) {
            case 1:
                switch (kriteria) {
                    case 1: bubbleSortByWaktu(head); break;
                    case 2: bubbleSortByHarga(head); break;
                    case 3: bubbleSortByTransit(head); break;
                }
                break;
            case 2:
                switch (kriteria) {
                    case 1: head = selectionSortByWaktu(head); break;
                    case 2: head = selectionSortByHarga(head); break;
                    case 3: head = selectionSortByTransit(head); break;
                }
                break;
            case 3:
                switch (kriteria) {
                    case 1: head = insertionSortByWaktu(head); break;
                    case 2: head = insertionSortByHarga(head); break;
                    case 3: head = insertionSortByTransit(head); break;
                }
                break;
        }
        return head;
    }
}