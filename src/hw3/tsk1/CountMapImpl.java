package hw3.tsk1;

import java.util.*;

public class CountMapImpl<K extends Comparable<K>> implements CountMap<K> {

    private HashMap<K, Integer> counter;

    public CountMapImpl() {
        counter = new HashMap<>();
    }

    public void add(K el) {
        Integer count = counter.get(el);
        counter.put(el, count == null ? 1 : count + 1);
    }

    public int getCount(K el) {
        Integer count = counter.get(el);
        return count == null ? 0 : count;
    }

    public int remove(K el) {
        Integer count = counter.get(el);
        if (count != null) {
            counter.remove(el);
            return count;
        }
        return 0;
    }

    public int size() {
        return counter.size();
    }

    public void addAll(CountMap<? extends K> source) {

        Iterator<? extends Map.Entry<? extends K, Integer>> it = source.toMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<? extends K, Integer> pair = it.next();
            Integer count = counter.get(pair.getKey());
            //counter.merge(pair.getKey(), pair.getValue(), (oldVal, newVal) -> oldVal + newVal);
            counter.put(pair.getKey(), pair.getValue() + (count == null ? 0 : count));
        }
    }

    public Map<K, Integer> toMap() {
        Map<K, Integer> destination = new HashMap<>();
        destination.putAll(counter);
        return destination;
    }

    public void toMap(Map<? super K, Integer> destination) {
        if (destination != null) {
            destination.clear();
            destination.putAll(counter);
        }

    }

    public void print() {
        Iterator<Map.Entry<K, Integer>> it = counter.entrySet().iterator();
        System.out.print("\n{\n");
        while (it.hasNext()) {
            Map.Entry<K, Integer> pair = it.next();
            System.out.printf("\t%s: %d ", pair.getKey().toString(), pair.getValue());
        }
        System.out.print("\n}\n");
    }
}
