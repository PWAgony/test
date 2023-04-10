import java.util.function.Function;

class Animal {
    String animal;

    public String getAnimal() {
        return animal;
    }
}
class Dog extends Animal {
    String dog;

    public String getDog() {
        return dog;
    }
}
class SubDog extends Dog {
    String subDog;

    public String getSubDog() {
        return subDog;
    }
}
public class Example {
    public static void main(String[] args) {
        Function<? super Dog, ? extends Dog> animalToDog = Example::dogToDog;
        SubDog subDog = new SubDog();

        Animal animal = animalToDog.apply(subDog);
    }

    public static Dog dogToDog(Animal animal) {
        return new SubDog();
    }
}
