package hw9;

public class Person {

    private final boolean man;
    private final String name;
    private Person spouse;
    private int age;

    public Person(boolean man, String name, int age) {
        this.man = man;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getSpouse() {
        return spouse;
    }

    public boolean isMan() {
        return (man == true);
    }

    public boolean isMarried() {
        return (spouse != null);
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (person.man != this.man) {
            //divorces
            if (person.spouse != null) {
                person.spouse.divorce();
            }
            person.divorce();
            if (spouse != null) {
                spouse.divorce();
            }
            divorce();
            //marry
            person.spouse = this;
            this.spouse = person;
        }
        return true;
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (spouse != null) {
            spouse = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format("Person: name - %s, age - %d", getName(), getAge());
    }
}

