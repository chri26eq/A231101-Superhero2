package domain_model.comparator;

import domain_model.Superhero;

import java.util.Comparator;

public class IsHumanComparator implements Comparator<Superhero> {


    @Override
    public int compare(Superhero superhero1, Superhero superhero2) {
        return Boolean.compare(superhero2.isHuman(),superhero1.isHuman());//superhero2 før superhero1 gør den reversed.

    }
}
