package com.messaadi.humankind.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static Colony col;
    static Epoque ep;
    static int tour;
    static int nbMax;
    static int nbExplo;
    static int nbExploVoiture;
    static int nbChasse;
    public static String lignee, location;
    public static boolean failure;


    public Main() {
        col = new Colony("Tribu");
        ep = Epoque.PREHISTOIRE;
        tour = 0;
        failure = false;
        for (Human h : col.colonie) {
            h.setEsperanceVie(20 * col.getEpoque());
        }
    }

    public Colony getCol() {
        return col;
    }

    public static Epoque getEp() {
        return ep;
    }

    public static int getTour() {
        return tour;
    }

    public static int getNbMax() {
        return nbMax;
    }

    public static int getNbExplo() {
        return nbExplo;
    }

    public static int getNbExploVoiture() {
        return nbExploVoiture;
    }

    public static int getNbChasse() {
        return nbChasse;
    }

    public static String getLignee() {
        return lignee;
    }

    public static String getLocation() {
        return location;
    }


    public static void turn(int choix) {
        col.getNotifs().clear();
        if(col.getTaille()<=0){
            failure = true;
            return;
        }
        checkEpoqueChange();

        if (col.getHumansMax() < col.getTaille()) {
            col.setHumansMax(col.getTaille());
        }

        if (choix != 0) {
            if (choix == 1) {
                col.chasser();
                nbChasse++;
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 2 && !col.technos.contains(Technology.VOITURE)) {
                col.expedition();
                nbExplo++;
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 2 && col.technos.contains(Technology.VOITURE)) {
                col.expedition();
                nbExploVoiture++;
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 3 && col.technos.contains(Technology.AGRICULTURE)) {
                col.cultiver();
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 6 && !col.getVaccinne() && col.technos.contains(Technology.VACCINS)) {
                col.vaccinner();
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 4 && col.technos.contains(Technology.BATEAU) && col.technos.contains(Technology.BOUSSOLE) && !col.getExploration()) {
                col.decouvrir();
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 5 && col.technos.contains(Technology.CANON)) {
                col.attaquer();
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 7 && col.technos.contains(Technology.CIMENT)) {
                col.newHabitations();
                col.passerTemps();
                col.Nbturn++;
            } else if (choix == 8 && col.technos.contains(Technology.IMPRIMERIE)) {
                col.lettres();
                col.passerTemps();
                col.Nbturn++;
            }
        }
        col.getNotifs().add("Choisissez votre action:");
    }

    public static void checkEpoqueChange() {
        boolean allTechnoDiscovered = true;
        for (Technology t : Technology.getTechnologyEpoque(col.getEpoque())) {
            if (!col.technos.contains(t)) {
                allTechnoDiscovered = false;
                return;
            }
        }
        if (allTechnoDiscovered) {
            col.changerEpoque(col.getEpoque());
        }
        if (col.getEpoque() == 6) {
            col.getNotifs().add("VICTOIRE !");
            System.exit(0);
        }
    }

    public static String vac() {
        String res;
        if (col.getVaccinne())
            res = "Oui";
        else
            res = "Non";
        return res;
    }

}
