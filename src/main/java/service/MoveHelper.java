package service;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MoveHelper {

    /** метод перемещения файла, 1 - перемещаемый файл 2 - путь до целевой дериктории перемещения */
    public static void move(File file, String destFilePath) {
        File movedFile = new File(destFilePath, file.getName());
        if(file.renameTo(movedFile)) {
            file.delete();
        } else {
            System.out.println("Ошибка перемещения файла: " + file.getName());
        }
    }

    // Метод для проверки, является ли папка пустой
    //public static boolean isEmptyFolder(File folder) {
    //    File[] files = folder.listFiles();
    //    return (files == null || files.length == 0);
    //}
}
