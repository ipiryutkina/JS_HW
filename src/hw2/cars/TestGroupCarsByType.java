package hw2.cars;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

public class TestGroupCarsByType {

    public static void main(String[] args) throws Exception {

        List<Car> cars = Arrays.asList(
                new Car("Lada", "sedan"),
                new Car("Lada", "hatchback"),
                new Car("Mersedes", "sedan"),
                new Car("BMW", "crossover"),
                new Car("Ford", "hatchback"),
                new Car("Peugeot", "crossover"),
                new Car("Toyota", "sedan"),
                new Car("Mersedes", "limousine"),
                new Car("Opel", "liftback"),
                new Car("Volkswagen", "minivan"),
                new Car("Hyundai", "universal"),
                new Car("Hyundai", "van"),
                new Car("Hyundai", "minivan"),
                new Car("Volkswagen", "pickup"),
                new Car("Volvo", "sedan"),
                new Car("Lexus", "crossover"),
                new Car("Nissan", "hatchback"),
                new Car("Kia", "hatchback"),
                new Car("Mazda", "universal"),
                new Car("Subaru", "universal"),
                new Car("Chevrolet", "sedan"),
                new Car("SsangYong", "pickup"),
                new Car("Porsche", "limousine"),
                new Car("Mitsubishi", "crossover"),
                new Car("Lada", "liftback"),
                new Car("Ural", "van"),
                new Car("Kia", "minivan")
        );

        Map<String, List<Car>> carGroups = new HashMap<>();

        for (Car c : cars) {
            List<Car> carList = carGroups.get(c.getType());
            if (carList == null) {
                carGroups.put(c.getType(), carList = new ArrayList<>());
            }
            carList.add(c);
        }

        Iterator<Map.Entry<String, List<Car>>> it = carGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<Car>> pair = it.next();
            System.out.printf("%s:\n", pair.getKey());
            for (Car c : pair.getValue()) {
                System.out.printf("\t%s\n", c.toString());
            }
            System.out.printf("---------------------------------------------------\n");
        }
    }
}
