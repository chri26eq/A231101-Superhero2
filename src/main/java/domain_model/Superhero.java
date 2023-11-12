package domain_model;

public class Superhero {

    private String name;
    private String realName;
    private String superpower;
    private int yearCreated;
    private boolean isHuman;
    private int strengthLevel;


    public Superhero() {

    }

    public Superhero(String name, String realName, String superpower, int yearCreated, boolean isHuman, int strengthLevel) {
        this.name = name;
        this.realName = realName;
        this.superpower = superpower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strengthLevel = strengthLevel;
    }

    public void makeCopyOf(Superhero otherSuperhero) {
        name = otherSuperhero.name;
        realName = otherSuperhero.realName;
        superpower = otherSuperhero.superpower;
        yearCreated = otherSuperhero.yearCreated;
        isHuman = otherSuperhero.isHuman;
        strengthLevel = otherSuperhero.strengthLevel;
    }

    public boolean fieldsAreEqualTo(Superhero otherSuperhero) {
        return (name.equals(otherSuperhero.name) &&
                realName.equals(otherSuperhero.realName) &&
                superpower.equals(otherSuperhero.superpower) &&
                yearCreated == otherSuperhero.yearCreated &&
                isHuman == otherSuperhero.isHuman &&
                strengthLevel == otherSuperhero.strengthLevel);
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getSuperpower() {
        return superpower;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public int getStrengthLevel() {
        return strengthLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setHuman(boolean human) {
        isHuman = human;
    }

    public void setStrengthLevel(int strengthLevel) {
        this.strengthLevel = strengthLevel;
    }

    public int numberOfFields() {
        return Superhero.class.getDeclaredFields().length;
    }

    public String superheroFieldsNumbered() {
        return "1) Name: " + name +
                "\n2) Real Name: " + realName +
                "\n3) Superpower: " + superpower +
                "\n4) Year Created: " + yearCreated +
                "\n5) Type: " + (isHuman ? "Human" : "Nonhuman") +
                "\n6) Strength Level: " + strengthLevel +
                "\n";
    }

    public static String fieldsNumbered() {
        return """
                1) Name
                2) Real Name
                3) Superpower
                4) Year Created
                5) Type
                6) Strength Level
                """;
    }

    @Override
    public String toString() {
        return String.format("%-18s%-21s%-27s%-17d%-10s%-10d",
                name, realName, superpower, yearCreated, (isHuman ? "Human" : "Nonhuman"), strengthLevel);
    }
}
