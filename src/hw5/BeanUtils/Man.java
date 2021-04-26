package hw5.BeanUtils;

public class Man extends Animal{
    String passport;

    public Man (String name, int age, String passport){
        super(name, age);
        this.passport = passport;
    }

    public String getPassport(){
        return passport;
    }

    public void setPassport(String passport){
        this.passport = passport;
    }

    @Override
    public String toString(){
        return String.format("Man   %s , age %d, passport %s", getName(), getAge(), passport);
    }
}
