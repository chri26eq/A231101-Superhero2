package domain_model;

public class DTO {

    Superhero superhero;
    ReturnMessage returnMessage;
    Boolean aBoolean;

    public Superhero getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public ReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(ReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
