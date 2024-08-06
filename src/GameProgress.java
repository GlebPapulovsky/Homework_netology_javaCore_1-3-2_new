import java.io.*;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public void saveGame(String filePath, String fileName) {
        GameProgress gm = new GameProgress(this.health, this.weapons, this.lvl, this.distance);
        File dir = new File(filePath);
        File f = new File(filePath + "\\" + fileName);
        if (!f.exists()) {
            File ff = new File(filePath + "\\" + fileName);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath + "\\" + fileName);
             ObjectOutputStream objectOutputStreams = new ObjectOutputStream(fos)) {
            objectOutputStreams.writeObject(gm);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}