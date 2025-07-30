import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> dll;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    /*
    If any operation happens then make it most recent by moving it to the front of the doubly linked list.
    */

    public synchronized V get(K key){
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            dll.moveToFront(node); // making it most recent
            return node.value;

        }
        return null;
    }

    public synchronized void put(K key, V value){
        if(map.containsKey(key)){
            // if it already exist just update the value
            Node<K ,V> node = map.get(key);
            node.value = value;
            //and now as it is accessed so make it recent by moving to front
            dll.moveToFront(node);
        }
        else{
            if(map.size() >= capacity){
                Node<K, V> node = dll.removeLast(); // remove the least recently used item
                if(node != null) {
                    map.remove(node.key); // remove it from the map as well
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addFirst(newNode);
            map.put(key, newNode);
        }
    }

    public synchronized void remove(K key) {
        if(map.containsKey(key)){
            Node<K,V> node = map.get(key);
            dll.remove(node);
            map.remove(key);
        }
    }

}
