package ru.sbt.mipt.oop;

public class CloseDoorAction implements Action {
    private String id;

    public CloseDoorAction(String id) {
        this.id = id;
    }

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(this.id)) {
                ((Door) actionable).setOpen(false);
                System.out.println("Door " + id + " was closed.");
            }
        }
    }
}