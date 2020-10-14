package ru.sbt.mipt.oop;

public class CloseHallDoorAction implements Action {
    private String id;

    @Override
    public void applyActionTo(Actionable actionable) {
        // По плану нужно было, чтобы при закрытии входной двери вызывался CloseHallDoorAction от всего дома,
        // и тогда везде выключался бы свет.
        // Но я не придумал, как передать сюда smartHome без добавления его в сигнатуру интерфейса Action
        if (actionable instanceof SmartHome) {
            TurnLightOffAction turnLightOffAction = new TurnLightOffAction();
            actionable.execute(turnLightOffAction);
        }
        if (actionable instanceof Room) {
            if (((Room) actionable).getName().equals("hall")) {
                for (Door door: ((Room) actionable).getDoors()) {
                    if (door.getId().equals(this.id)) {
                        System.out.println("Команда на закрытие входной двери. " +
                                "Нужно выключить весь свет, но я не знаю, как:(");
                    }
                }
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }
}