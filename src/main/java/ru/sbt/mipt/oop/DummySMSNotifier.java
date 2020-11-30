package ru.sbt.mipt.oop;

public class DummySMSNotifier implements Notifier{
    @Override
    public void makeNotification() {
        System.out.println("Alarm caught some sensor activity!");
        System.out.println("Sending more SMS!");
    }
}