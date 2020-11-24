package ru.sbt.mipt.oop;

public class TurnAllLightsOffAction implements Action{
    private String id;

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Light) {
            ((Light) actionable).setOn(false);
            System.out.println("Light " + ((Light) actionable).getId() + " was turned off.");
            }
        }

    public void setId(String id) {
        this.id = id;
    }
}
