package com.example.voleyapp.model;

public class Player {
    public enum Position {
        REMATADOR,
        LIBERO,
        BLOQUEADOR,
        COLOCADOR,
        ZAGUERO;
    }

    public enum Skill {
        BUENO,
        MEDIO,
        MALO;
    }

    private Position position;
    private Skill skill;
    private String name;

    public Player(Position position, Skill skill, String name) {
        this.position = position;
        this.skill = skill;
        this.name = name;
    }

    public Player(String name) {
        this.name = name;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
