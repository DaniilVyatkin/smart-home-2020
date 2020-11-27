package ru.sbt.mipt.oop;

interface EventHandler {
    void handle(SensorEvent event, SmartHome smartHome);
}