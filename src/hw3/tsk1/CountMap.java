package hw3.tsk1;

import java.util.Map;

public interface CountMap<K extends Comparable<K>> {
    // добавляет элемент в этот контейнер.
    void add(K el);

    //Возвращает количество добавлений данного элемента
    int getCount(K el);

    //Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
    int remove(K el);

    //количество разных элементов
    int size();

    //Добавить все элементы из source в текущий контейнер,
    // при совпадении ключей, суммировать значения
    public void addAll(CountMap<? extends K> source);

    //Вернуть java.util.Map. ключ - добавленный элемент,
    // значение - количество его добавлений
    Map<K, Integer> toMap();

    //Тот же самый контракт как и toMap(), только всю информацию записать в destination
    void toMap(Map<? super K, Integer> destination);
}

