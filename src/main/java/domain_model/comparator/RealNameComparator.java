package domain_model.comparator;

import domain_model.Superhero;

import java.util.Comparator;

public class RealNameComparator implements Comparator<Superhero> {

    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return superhero1.getRealName().compareToIgnoreCase(superhero2.getRealName());

    }
}
