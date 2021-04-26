package hw5.BeanUtils;

public class Animal {

    private String name;
    private int age;

    public Animal (String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    private int test(){ return 0;}
    protected void test1(){}

    @Override
    public String toString(){
        return String.format("Animal  %s , age %d", name, age);
    }
}
