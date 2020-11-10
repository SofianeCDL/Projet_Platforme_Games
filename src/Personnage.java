import processing.core.PApplet;
import processing.core.PVector;

public class Personnage extends Main {
    private PVector position;

    private float vitesse;
    private float acceleration;
    private float deceleration;

    private Terrain terrain;

    private float taillePersonnage;

    private float vitesseSaut;
    private float accelerationSaut;
    private int nombreSaut;

    private boolean memmetumG, memmetumD;
    private boolean peutSauter;

    private float hauteurMax;


    public Personnage(Terrain terrain) {
        this.terrain = terrain;

        this.position = this.terrain.pointApparition();

        this.vitesse = 0.f;
        this.acceleration = (float) 0.1;
        this.deceleration = (float) 0.42;

        this.taillePersonnage = 10.f;

        this.vitesseSaut = 10.f;
        this.accelerationSaut = (float) 0.1;
        this.hauteurMax = this.terrain.getListePoints().get(0).y - 200;

        this.peutSauter = false;

        this.memmetumG = false;
        this.memmetumD = false;

        this.nombreSaut = 0;


    }

    public void avancer(boolean gauche, boolean droite) {
        if ((gauche || droite) && this.vitesse < 4.f) {
            this.vitesse += this.acceleration;
        }

        if (gauche || this.memmetumG) {
            this.memmetumG = true;
            this.memmetumD = false;
            this.position.x -= this.vitesse;
        }
        if (droite || this.memmetumD) {
            this.memmetumG = false;
            this.memmetumD = true;
            this.position.x += this.vitesse;
        }

       if (this.vitesse < 0) {
            this.memmetumG = false;
            this.memmetumD = false;
            this.vitesse = 0.f;
        }

        if (!gauche && !droite && this.vitesse > 0.f){
            this.vitesse -= this.acceleration * (3.f/2.f);
        }

    }
    public void saut(boolean saut) {
        /*this.vitesseSaut += this.accelerationSaut;
        this.position.y += this.vitesseSaut;

        if (this.position.y >= this.terrain.pointApparition().y) {
            this.position.y = this.terrain.pointApparition().y;
            this.vitesseSaut = 0.f;
            this.peutSauter = true;
        }

        if (saut && this.peutSauter) {
            this.vitesseSaut = -10.f;
            this.peutSauter = false;
            //this.nombreSaut++;
        }*/
        if (saut && this.hauteurMax < this.position.y && !this.peutSauter) {
            this.position.y -= this.vitesseSaut;
            if (this.vitesseSaut > 0) {
                this.vitesseSaut -= this.accelerationSaut;
            }
        } else if (this.position.y  < this.terrain.getListePoints().get(0).y - this.taillePersonnage) {
            this.peutSauter = true;
            this.position.y += this.vitesseSaut;

            if (this.position.y  < this.terrain.getListePoints().get(0).y - this.taillePersonnage) {
                this.vitesseSaut += this.accelerationSaut;
            } else {
                this.peutSauter = false;
                this.vitesseSaut = 10.f;
                this.position.y = this.terrain.pointApparition().y;
            }
        }
    }

    public void afficherPersonnage() {
        Main.processing.fill(255);
        Main.processing.stroke(255);
        Main.processing.square(this.position.x, this.position.y - this.taillePersonnage, this.taillePersonnage);

        Main.processing.fill(0, 255, 0);
        Main.processing.textSize(30);
        Main.processing.text(this.vitesse, 25, 100);
        Main.processing.text(this.position.y, 25, 150);
    }
}
