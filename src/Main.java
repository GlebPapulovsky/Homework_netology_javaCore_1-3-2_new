import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static final String dirPathForSaves = "C:\\Users\\papul\\IdeaProjects\\java_core_2\\Games\\savegames";

    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(1, 1, 1, 1);
        GameProgress gameProgress2 = new GameProgress(2, 2, 2, 2);
        GameProgress gameProgress3 = new GameProgress(3, 3, 3, 3);

        gameProgress1.saveGame(dirPathForSaves, "save1.dat");
        gameProgress2.saveGame(dirPathForSaves, "save2.dat");
        gameProgress3.saveGame(dirPathForSaves, "save3.dat");

        String filePath1 = "C:\\Users\\papul\\IdeaProjects\\java_core_2\\Games\\savegames\\save1.dat";
        String filePath2 = "C:\\Users\\papul\\IdeaProjects\\java_core_2\\Games\\savegames\\save2.dat";
        String filePath3 = "C:\\Users\\papul\\IdeaProjects\\java_core_2\\Games\\savegames\\save3.dat";

        String[] files = {filePath1, filePath2, filePath3};
        createZipForSaves(dirPathForSaves, files);


    }

    public static void createZipForSaves(String path, String[] files) {
        String zipFile = path + "\\savesZip.zip";

        try {

            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String file : files) {
                File srcFile = new File(file);
                FileInputStream fis = new FileInputStream(srcFile);
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }

            zos.close();
            System.out.println("Zip архив успешно создан.");
        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (!file.getName().contains(".zip")) {
                    if (file.delete()) {
                        System.out.println("Файл сохранения вне архива успешно удален!");
                    }
                }
            }
        } else {
            System.out.println("Неверно указан путь к файлу.");
        }
    }
}
