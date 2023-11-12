package domain_model.comparator;

import domain_model.Superhero;

import java.util.Comparator;

public class SuperpowerComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return superhero1.getSuperpower().compareToIgnoreCase(superhero2.getSuperpower());

    }
}
