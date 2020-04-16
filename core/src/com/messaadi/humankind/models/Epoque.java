package com.messaadi.humankind.models;

public enum Epoque {

    PREHISTOIRE(1),
    ANTIQUITE(2),
    MOYEN_AGE(3),
    TEMPS_MODERNES(4),
    EPOQUE_CONTEMPORAINE(5);

    private final int AGE;

    Epoque(int age) {this.AGE=age;}
    public int getAGE() {return this.AGE;}

}
