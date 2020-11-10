import processing.core.PApplet;

public class Main extends PApplet {

    public static PApplet processing;

    private Terrain t;
    private Personnage p;

    protected boolean gauche, droite, saut;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public  void settings() {
        size(1500, 800);
    }

    public void setup() {
        processing = this;
        background(0);

        t = new Terrain();
        p = new Personnage(t);

        this.gauche = false;
        this.droite = false;
    }

    public void draw() {

        background(0);
        t.affichageTerrain();
        p.afficherPersonnage();
        p.avancer(this.gauche, this.droite);
        p.saut(this.saut);


    }

    public void keyPressed(){
        if (key == 'q') this.gauche = true;
        if (key == 'd') this.droite = true;
        if (key == ' ') this.saut = true;
    }

    public void keyReleased() {
        if (key == 'q') this.gauche = false;
        if (key == 'd') this.droite = false;
        if (key == ' ') this.saut = false;
    }

}
