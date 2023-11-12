package domain_model.comparator;

import domain_model.Superhero;

import java.util.Comparator;

public class NameComparator implements Comparator<Superhero> {


    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return superhero1.getName().compareToIgnoreCase(superhero2.getName());

    }
}
