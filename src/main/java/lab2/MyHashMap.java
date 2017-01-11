package lab2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ihb on 10.01.17.
 */
public class MyHashMap<K,V> implements Map {

    Entity<K,V>[] table;
    int capacity = 16;
    private int threshold = 0;
    int size = 0;



    @Override
    public int size() {
        return size;
    }

    @Override
    public final V get(Object key) {
        // key instanceof K ??
        if(key == null){
            return getForNull();
        }

        Entity<K,V> entity = table[indexFor(key.hashCode(), table.length)]
            if(entity.hash == key || entity.key.equals(key))
                return entity.getValue();
        return null;
    }

    @Override
    public Object put(Object key, Object value) {

        if(!containsKey(key)) {
            if (size == threshold) {
                threshold += capacity;
                Entity[] newTable = new Entity[threshold];
                System.arraycopy(table, 0, newTable, 0, table.length);
                table = newTable;
            }
        }

        for()



        return value;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }






    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    int indexFor(int h, int length)
    {
        return h & (length - 1);
    }

    public V getForNull() {
        return forNull;
    }





    class Entity<K,V> {
        int hash;
        K key;
        V value;
        Entity<K,V> next;

        Entity(int hash, K key, V value, Entity<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
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
    public Set<Entry> entrySet() {
        return null;
    }
}
