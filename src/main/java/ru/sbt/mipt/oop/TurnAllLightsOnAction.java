package ru.sbt.mipt.oop;

public class TurnAllLightsOnAction implements Action{

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Light) {
            ((Light) actionable).setOn(true);
            System.out.println("Light " + ((Light) actionable).getId() + " was turned off.");
        }
    }
}