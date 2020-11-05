package com.example.voleyapp.model;

public enum TypeVolleyMatch {
    LARGO(25, 5, 15),
    MEDIO(25, 3, 25),
    CORTO(15, 3, 15),
    CALENTAMIENTO(15, 1, 15);

    private int points;
    private int sets;
    private int pointsLastSet;

    TypeVolleyMatch(int points, int sets, int pointsLastSet) {
        this.points = points;
        this.sets = sets;
        this.pointsLastSet = pointsLastSet;
    }

    public int getPoints() {
        return points;
    }

    public int getSets() {
        return sets;
    }

    public int getPointsLastSet() {
        return pointsLastSet;
    }
}
