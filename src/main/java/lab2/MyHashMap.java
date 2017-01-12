package lab2;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by ihb on 10.01.17.
 */
public class MyHashMap<K, V> implements Map<K, V> {

    Entity<K,V>[] table;
    int capacity = 16;
    private int threshold = 0;
    double loadFactor = 0.75;
    int size = 0;


    MyHashMap() {
        this.table = new Entity[capacity];
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;


    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(Object key) {
        // key instanceof K ??
        if (key == null) {
            return getForNull(key);
        }

        int hash = hash(key.hashCode());
        Entity<K, V> entity = table[indexFor(hash, table.length)];

        while (entity != null) {
            if (entity.hash == hash && (entity.key == key || key.equals(entity.key)))
                return entity.getValue();

            entity = entity.next;
        }

        return null;
    }


    @Override
    public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key.hashCode());

        int i = indexFor(hash, table.length);
        Entity<K, V> entity = table[i];

        while (entity != null){
            if (entity.hash == hash && (entity.key == key || key.equals(entity.key))) {
                V oldValue = entity.value;
                entity.value = value;
                return oldValue;
            }
            entity = entity.next;
        }

        addEntry(hash, key, value, i);
        return null;
    }

    void addEntry(int hash, K key, V value, int index) {
        Entity<K,V> e = table[index];
        table[index] = new Entity<>(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    private void resize(int newCapacity) {
        Entity<K,V>[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entity[] newTable = new Entity[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(Entity<K,V>[] newTable) {
        Entity<K,V>[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entity<K,V> e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entity<K,V> next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    private V putForNullKey(V value) {

        Entity<K,V> entity = table[0];

        while (entity != null){
            if (entity.key == null) {
                V oldValue = entity.value;
                entity.value = value;
                return oldValue;
            }
            entity = entity.next;
        }

        addEntry(0, null, value, 0);

        return null;
    }



    @Override
    public V remove(Object key) {
        Entity<K,V> entity = removeEntryForKey(key);
        return (entity == null ? null : entity.value);
    }

    final Entity<K,V> removeEntryForKey(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);
        Entity<K,V> prev = table[i];
        Entity<K,V> e = prev;

        while (e != null) {
            Entity<K,V> next = e.next;
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null)
            return containsNullValue();

        Entity<K,V>[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entity<K,V> e = tab[i] ; e != null ; e = e.next)
                if (value.equals(e.value))
                    return true;
        return false;
    }

    private boolean containsNullValue() {
        Entity<K,V>[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entity<K,V> e = tab[i] ; e != null ; e = e.next)
                if (e.value == null)
                    return true;
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    final Entity<K,V> getEntry(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        for (Entity<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }





    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    int indexFor(int h, int length) {
        return h & (length - 1);
    }

    public V getForNull(Object key) {
        Entity<K, V> entity = table[0];
        while (entity != null) {
            if (entity.key == key || entity.key.equals(key))
                return entity.getValue();

            entity = entity.next;
        }

        return null;
    }


    class Entity<K, V> {
        int hash;
        K key;
        V value;
        Entity<K, V> next;

        Entity(int hash, K key, V value, Entity<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }
    }












































    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }
}
