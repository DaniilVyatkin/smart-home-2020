package ru.sbt.mipt.oop;

public class TurnLightOnAction implements Action{
    private String id;

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Light) {
            if (((Light) actionable).getId().equals(this.id)) {
                ((Light) actionable).setOn(true);
                System.out.println("Light " + this.id + " was turned on.");
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }
}