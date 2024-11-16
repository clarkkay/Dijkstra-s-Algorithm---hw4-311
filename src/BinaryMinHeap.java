import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BinaryMinHeap<T> extends PurePriorityQueue<T> {
    private ArrayList<T> heap = new ArrayList<T>();
    private HashMap<T, Integer> location = new HashMap<T, Integer>();

    public BinaryMinHeap(Comparator<T> comp) {
        super(comp);
    }

    /**
     * 
     *
     * @return The number of elements in the heap
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Adds an element to the heap.
     *
     * @param item An element not in the heap that will be added to it.
     */
    @Override
    public void add(T item) {
        /* TODO */
        heap.add(item);
        location.put(item, heap.size() - 1);
        bubbleUp(heap.size() - 1);
    }
    private void bubbleUp(int index){
        while(index > 0){
            int parent = (index - 1) / 2;
            if (comp.compare(heap.get(index), heap.get(parent)) < 0){
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }
    private void bubbleDown(int index){
        while(true){
            int smallest = index;
            int leftC = 2 * index + 1;
            int rightC = 2 * index + 2;

            if (leftC < heap.size() && comp.compare(heap.get(leftC), heap.get(rightC)) < 0){
                smallest = leftC;
            }
            if (rightC < heap.size() && comp.compare(heap.get(rightC), heap.get(smallest)) < 0){
                smallest = rightC;
            }
            if(smallest != index){
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }

        }
    }
    private void swap(int i, int j){
        T temp  = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        location.put(heap.get(i), i);
        location.put(heap.get(j), j);
    }

    /**
     * 
     *
     * @return Returns the minimum element of the heap without removing it.
     */
    @Override
    public T getMin() {
        return heap.get(0);
    }

    /**
     * Removes the minimum element from the heap and returns it.
     *
     * @return The minimum element that was in the heap when the method was invoked.
     */
    @Override
    public T extractMin() {
        /* TODO */
        if(heap.isEmpty()) return null;
        T min = getMin();
        T lastItem = heap.get(heap.size() - 1);
        heap.set(0, lastItem);
        location.put(lastItem, 0);

        heap.remove(heap.size() - 1);
        location.remove(min);

        if(!heap.isEmpty()){
            bubbleDown(0);
        }
        return min;
    }

    /**
     * Anytime the key decreases for an element in the heap, this method must be
     * invoked to restored the heap property. Here, key refers to the value
     * determining the ordering of heap elements as used in the Comparator.
     *
     * @param item An item in the heap that has had its key decreased.
     */
    @Override
    public void keyDecreased(T item) {
        /* TODO */
        Integer index = location.get(item);
        if(index != null){
            bubbleUp(index);
        }
    }
}
