public class DoublyLinkedList<K,V> {
    private final Node<K, V> head;
   private final Node<K,V> tail;

   public DoublyLinkedList(){
       head = new Node<>(null, null);
       tail = new Node<>(null, null);
       head.next = tail;
       tail.prev = head;
   }

   public void addFirst(Node<K, V> node){
       node.next = head.next;
       head.next.prev = node;
       head.next = node;
       node.prev = head;

   }

    public void remove(Node<K, V> node){
         node.prev.next = node.next;
         node.next.prev = node.prev;
    }

    public void moveToFront(Node<K, V> node) {
        remove(node);
        addFirst(node);
    }

    public Node<K, V> removeLast() {
        if(tail.prev == head){
            return null; // List is empty
        }
        Node<K, V> last = tail.prev;
        remove(last);
        return last;

    }
}
