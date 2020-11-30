package ru.sbt.mipt.oop;

public class TurnAllLightsOffAction implements Action{

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Light) {
            ((Light) actionable).setOn(false);
            System.out.println("Light " + ((Light) actionable).getId() + " was turned off.");
        }
    }
}