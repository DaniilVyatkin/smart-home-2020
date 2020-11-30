package ru.sbt.mipt.oop;

public class OpenDoorAction implements Action{
    private String id;

    @Override
    public void applyActionTo(Actionable actionable) {
        if (actionable instanceof Door) {
            if (((Door) actionable).getId().equals(this.id)) {
                ((Door) actionable).setOpen(true);
                System.out.println("Door " + id + " was opened.");
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }
}