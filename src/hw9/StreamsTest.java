package hw9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StreamsTest {

    public static void main(String[] args) throws Exception {

        //Stream<String> streamOfArray = Stream.of("a", "b", "c");
        List<Person> someCollection = new ArrayList<>();
        someCollection.add(new Person(true, "Vova", 37));
        someCollection.add(new Person(false, "Masha", 30));
        someCollection.add(new Person(true, "Petya", 25));
        someCollection.add(new Person(false, "Dasha", 20));

        for (Person p:someCollection){
            System.out.println(p.toString());
        }

        Map<String, Person> m = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform( p -> new Person(p.isMan()?false:true,p.getName()+"aaa", p.getAge() + 30))
                .toMap(p -> p.getName(), p -> p);


        for (Map.Entry<String, Person> entry : m.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }

    }
}
