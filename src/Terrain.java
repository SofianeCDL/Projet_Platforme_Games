import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Terrain extends Main {
    private final ArrayList<PVector> listePoints;

    private float tailleTerrain;

    public Terrain() {
        this.listePoints = new ArrayList<>();

        this.listePoints.add(new PVector(0, Main.processing.height / 2.f));
        this.listePoints.add(new PVector(Main.processing.width, Main.processing.height / 2.f));

        this.tailleTerrain = Main.processing.width;

    }

    public PVector pointApparition() {
        int pointMillieuA = (this.listePoints.size() / 2 ) - 1;
        int pointMillieuB = (this.listePoints.size() / 2 );

        PVector pointA = this.listePoints.get(pointMillieuA);
        PVector pointB = this.listePoints.get(pointMillieuB);

        return new PVector((pointA.x + pointB.x) / 2.f, (pointA.y + pointB.y) / 2.f);
    }

    public void affichageTerrain() {
        Main.processing.stroke(255);
        for (int i = 0 ; i < this.listePoints.size() - 1 ; ++i) {
            PVector pointA = this.listePoints.get(i);
            PVector pointB = this.listePoints.get(i + 1);
            Main.processing.line(pointA.x, pointA.y, pointB.x, pointB.y);
        }
    }

    public ArrayList<PVector> getListePoints() {
        return listePoints;
    }
}
