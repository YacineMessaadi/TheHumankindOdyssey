package com.messaadi.humankind.models;

import com.messaadi.humankind.models.Human;
import com.messaadi.humankind.models.Technology;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;


public class Colony implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected ArrayList<Human> colonie = new ArrayList<>();
    public int Nbturn = 0;
    public String nom;
    private int qi;
    private int nourriture;
    private int rendementCulture = 10;
    public ArrayList<Technology> technos = new ArrayList<>();
    private int epoque;
    public int mortExpe;
    public int mortFaim;
    public int mortGuerre;
    public int mortAge;
    public int babys;
    public boolean vaccinne;
    private int HumansMax = 0;
    private int tourDepart;
    private boolean enExploration;
    private boolean nouvellesHabitations;
    private int quota_bomb;
    private ArrayList<String> notifs;

    public Colony(String nom) {
        this.nom = nom;
        for(int i = 0; i < (int) ((Math.random()*9)+2) ; i++) {
            colonie.add(new Human(true));
            colonie.add(new Human(false));
        }
        this.nourriture = 10;
        this.epoque = 1 ;
        this.rendementCulture =0;
        this.vaccinne = false;
        this.tourDepart = 0;
        this.enExploration = false;
        this.nouvellesHabitations = false;
        this.quota_bomb = 400;
        this.notifs = new ArrayList<String>();
    }
    public boolean getExploration() {
        return this.enExploration;
    }
    public void attaquer() {
        Random alea = new Random();
        notifs.add("Tu attaques une colonie ennemie !");
        int tailleEnnemie = alea.nextInt(this.colonie.size());
        if(tailleEnnemie-1 <1) {
            notifs.add("Tu as perdu le combat ...\n");
            this.colonie.clear();
        }
        else {
            int perte = alea.nextInt(this.colonie.size()/2);
            int gagne = alea.nextInt(this.colonie.size()/2);
            for(int i=0; i<perte ; i++)
                this.colonie.remove(0);
            for(int i=0; i<gagne; i++)
                this.colonie.add(new Human());
            notifs.add("Tu as perdu "+perte+" habitants, mais tu en as gagné "+gagne+".");
        }
    }

    public boolean getVaccinne() {return this.vaccinne;}
    public void vaccinner() {this.vaccinne = true;}

    public void passerTemps() {
        faim();
        vieillir();
        enterrerMorts();
        reproduction();
        attaqueEnnemie();
        if(!this.getVaccinne()) maladie();
        recupererCulture();
        LEMONDEVATILMOURIRDUNEBOMBENUCLEAIRE();
        testDecouverte();
        boolHabitations();
        coucou();
    }

    public void coucou() {
        Random r = new Random();
        if(r.nextInt(10)<=3 && this.Nbturn >=5) {
            notifs.add("Une autre colonie est venue te rendre visite.");
            int tmp = r.nextInt(10);
            int tmpB = r.nextInt(10);

            for(int i =0; i<tmp; i++)
                this.colonie.add(new Human());
            this.nourriture += tmpB;

            notifs.add(tmp+" personnes ont décidé de rester. Ils t'ont apporté "+tmpB+" nourritures pour te remercier.");
        }
    }

    public void boolHabitations() {
        if(this.nouvellesHabitations) {
            Random alea= new Random();
            int tmp = alea.nextInt(50);
            for(int i=0 ; i<tmp; i++)
                this.colonie.add(new Human());
            this.nourriture-=25;
            notifs.add("Tes nouvelles habitations ont attiré "+tmp+" nouveaux habitants !\n");

        }
    }

    public void newHabitations() {
        this.nouvellesHabitations = !this.nouvellesHabitations;
    }

    public boolean getNouvellesHabitations() {return this.nouvellesHabitations;}

    public void testDecouverte() {
        if(this.enExploration) this.tourDepart++;
        if(this.tourDepart ==5) {
            this.tourDepart = 0;
            this.enExploration = false;
            Random alea = new Random();

            int retour = alea.nextInt(10);
            notifs.add("L'expédition est maintenant terminée ! ");
            notifs.add("Résultat : ");
            notifs.add(retour+" habitants sont revenus;");
            for(int i = 0; i<retour; i++)
                this.colonie.add(new Human());
            int newD =0;
            if(alea.nextInt(100) < 34) {
                Technology nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(alea.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                while(technos.contains(nouvelleTechnology)) {
                    nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(alea.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                }
                technos.add(nouvelleTechnology);
                qi += 7;
                newD++;
                notifs.add("Tu as découvert "+ nouvelleTechnology.name());
            }


        }
    }

    public void LEMONDEVATILMOURIRDUNEBOMBENUCLEAIRE() {
        if(this.technos.contains(Technology.BOMBENUCLEAIRE)) {
            Random alea = new Random();
            if(alea.nextInt(800)<=quota_bomb) {
                notifs.add("Trump a balancé une bombe sur : ");
                notifs.add("F I N    D U    M O N D E");
                this.colonie.clear();
            }
        }
    }
    public void lettres() {
        if(quota_bomb >= 50) {
            quota_bomb-=50;
            notifs.add("Trump t'envoie ses amitiés en retour.");
        }
    }

    public void recupererCulture() {
        this.nourriture+=this.rendementCulture;
    }
    public void cultiver() {
        this.rendementCulture++;
        notifs.add("Ton rendement est augmenté de 1/tour !");
    }
    public void attaqueEnnemie() {

        Random r = new Random();
        int nbr = r.nextInt(50);
        Random randChanceAttaque = new Random();
        int chanceAttaque = randChanceAttaque.nextInt(10);

        if(Nbturn >= 10 && chanceAttaque < 3) {

            if(nbr < 10) {
                notifs.add("Ta force a intimidé les ennemis et ils ne t'ont finalement pas attaqué !");
            }
            else {
                double tauxDeMorts = (int) colonie.size() * nbr/100;
                int morts = (int) tauxDeMorts;

                notifs.add("Attaque ennemie !");
                notifs.add("Tu as perdu "+ morts + " Humans.");

                for(int i = 0 ; i < morts ; i++) {
                    colonie.remove(0);
                    mortGuerre+= 1;
                }
            }

            System.out.println("");
            if(this.colonie.size() == 0 ) notifs.add("Votre tribu s'est faite décimée...");

        }
    }

    public void decouvrir() {
        notifs.add("L'expédition reviendra dans 5 tours !");
        this.tourDepart = this.Nbturn;
        if(this.colonie.size() <=10) notifs.add("Il faut 10 habitants pour faire une expédition !");
        else {
            for(int i=0;i<10;i++)
                this.colonie.remove(0);
        }
        this.enExploration = true;
    }

    public void maladie() {
        Random alea = new Random();
        if(alea.nextInt(100)<=2 && Nbturn >= 10) {
            notifs.add("Une épidémie s'abbat sur votre population !");
            int tmp =  alea.nextInt((int) (this.colonie.size()*0.8));
            for(int i = 0; i< tmp; i++)
                if(colonie.size() > 0) {
                    this.colonie.remove(0);
                }
        }
    }

    public int getEpoque() {return this.epoque;}



    public void changerEpoque(int epoque) {
        this.epoque = epoque +1 ;
    }

    public int getTaille() {
        return colonie.size();
    }

    public int getQi() {
        return qi;
    }

    public int getMortExpe() {
        return mortExpe;
    }
    public int getMortFaim() {
        return mortFaim;
    }
    public int getMortGuerre() {
        return mortGuerre;
    }
    public int getMortAge() {
        return mortAge;
    }

    public void setQi(int qi) {
        this.qi = qi;
    }

    public void expedition() {
        nourriture--;
        Random r = new Random();
        int nbr = r.nextInt(100);

        if(technos.contains(Technology.VOITURE)) {
            if(nbr < 42) {
                Technology nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(r.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                while(technos.contains(nouvelleTechnology)) {
                    nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(r.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                }
                technos.add(nouvelleTechnology);
                qi += 7;

                notifs.add("Vous avez découvert : " + nouvelleTechnology.name());


                if(r.nextInt(100) < 21) {
                    colonie.remove(0);
                    notifs.add("Un villageois est mort durant l'expédition !");
                    mortExpe++;
                }
            }
        }
        else {
            if(nbr < 34) {
                Technology nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(r.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                while(technos.contains(nouvelleTechnology)) {
                    nouvelleTechnology = Technology.getTechnologyEpoque(epoque).get(r.nextInt(Technology.getTechnologyEpoque(epoque).size()));
                }
                technos.add(nouvelleTechnology);
                qi += 7;

                notifs.add("Vous avez découvert : " + nouvelleTechnology.name());

                if(r.nextInt(100) < 21) {
                    colonie.remove(0);
                    notifs.add("Un villageois est mort durant l'expédition !");
                    mortExpe++;
                }
            }
            else {
                notifs.add("Vous n'avez rien découvert...");
                if(r.nextInt(100) < 21) {
                    colonie.remove(0);
                    notifs.add("Un villageois est mort durant l'expédition !");
                    mortExpe++;
                }
            }
        }
    }

    public void chasser() {
        int food = 0;
        if(technos.contains(Technology.FEU)) {
            nourriture += 3;
            food = 3;
        }
        else {
            nourriture += 2;
            food = 2;
        }
        notifs.add("Vous avez attrapé une bête sauvage (+" + food + " pts nourriture)");
    }



    public int getNourriture() {
        return nourriture;
    }

    public void faim() {
        nourriture--;
        if(nourriture<5) {
            if(colonie.size()>0) colonie.remove(0);
            notifs.add("Un villageois est mort de la famine !");
            mortFaim++;
        }
    }

    public void reproduction() {
        if(nourriture>colonie.size()) {
            ArrayList<Human> bebes = new ArrayList<Human>();
            for(Human h: colonie) {
                if(!h.isMort() && !h.isParent() && h.getAge()>13) {
                    Random r = new Random();
                    int idx = r.nextInt(colonie.size());
                    if(colonie.get(idx).isMale()!=h.isMale()) {
                        Human b = new Human();
                        b.setQiEnfant(h, colonie.get(idx));
                        bebes.add(b);
                        h.setParent(true);
                        colonie.get(idx).setParent(true);
                    }
                }
            }
            for(Human h: bebes) {
                colonie.add(h);
                h.setEsperanceVie(20*this.getEpoque());
                babys++;
            }
            if(bebes.size()>0) notifs.add("Votre colonie accueille " + bebes.size() + " nouveaux nés !");;
            bebes.clear();
            for(Human h: colonie) {
                h.setParent(false);
            }
        }

    }

    private void vieillir() {
        for(Human h: colonie) {
            h.vieillir();
        }
    }

    private void enterrerMorts() {
        if(colonie.size()==0) return;
        ArrayList<Integer> morts = new ArrayList<>();
        for(Human p : colonie) {
            if(p.isMort() == true) morts.add(colonie.indexOf(p));
        }
        for(int i : morts) {
            if(i<colonie.size()) colonie.remove(i);
        }
        if(morts.size()>0) notifs.add(morts.size() + " sont morts de vieillesse...");
        mortAge += morts.size();
    }

    public int getBaby() {
        return babys;
    }

    public int getHumansMax() {
        return HumansMax;
    }

    public void setHumansMax(int HumansMax) {
        this.HumansMax = HumansMax;
    }


    public ArrayList<String> getNotifs() {
        return notifs;
    }

    public void setNotifs(ArrayList<String> notifs) {
        this.notifs = notifs;
    }
}
