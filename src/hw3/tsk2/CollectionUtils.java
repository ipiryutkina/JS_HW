package hw3.tsk2;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static<T> void addAll(List<? extends T> source, List<? super T> destination){
        destination.addAll(source);
    }

    public static<T> List<T> newArrayList(){
        return new ArrayList<T>();
    }

    public static<T> int indexOf(List<? super T> source, T o){
        return source.indexOf(o);
    }

    public static<T> List<T> limit(List<T> source, int size){
        return source.stream().limit(size).collect(Collectors.toList());
    }

    public static<T> void add(List<? super T> source, T o){
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2){
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static<T> boolean containsAll(List<? super T> c1, List<? extends T> c2){
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static<X, Y extends X> boolean containsAny(List<X> c1, List<Y> c2){
        HashSet<Y> hs = new HashSet<>(c2);

        for (X t:c1)
            if(hs.contains(t))
                return true;

        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static<T extends Comparable<T>, X extends T, Y extends T> List<T> range(List<? extends T> list, X min, Y max){
        ArrayList<T> res=new ArrayList<T>();
        for (T t:list){
            if(t.compareTo(min)>=0 && t.compareTo(max)<=0)
                res.add(t);
        }

        return res;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static<T, X extends T, Y extends T> List<T> range(List<? extends T> list, X min, Y max, Comparator<? super T> comparator){
        ArrayList<T> res=new ArrayList<>();
        for (T t:list){
            if(comparator.compare(t, min)>=0 && comparator.compare(t, max)<=0)
                res.add(t);
        }

        return res;
    }
}
