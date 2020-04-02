package app;

/**
 * ImplementationHashMap
 */
public class ImplementationHashMap<K, V> {

    private NodeLinkedList<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 1 << 4; // 16
    private int size = 0;

    public ImplementationHashMap(int capacity) {
        this.buckets = new NodeLinkedList[capacity];
    }

    public static void main(String[] args) {

    }

    public int size() {
        return size;
    }

    private int getBucketSize() {
        return buckets.length;
    }

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    public void put(K key, V value) {
        NodeLinkedList<K, V> entry = new NodeLinkedList<>(key, value, null);

        // Calculation of Index where the value is to be stored
        int bucket = getHash(key) % getBucketSize();

        NodeLinkedList<K, V> existing = buckets[bucket];
        if (existing == null) {
            buckets[bucket] = entry;
            size++;
        } else {
            // compare the keys see if key already exists
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    }

    public V get(K key) {
        NodeLinkedList<K, V> bucket = buckets[getHash(key) % getBucketSize()];

        while (bucket != null) {
            if (key == bucket.key) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }
}