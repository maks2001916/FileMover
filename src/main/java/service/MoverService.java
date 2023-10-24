package service;

import java.io.File;
import java.util.ArrayList;

public class MoverService {

    /** метод проверки существования файла */
    public boolean checkFileExistence(File[] source) {
        for (File file: source) {
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    /** метод обхода всех файлов и папок в исходной папке */
    public void traverseList(String sourceFilesPath, String dustFilePath) {
        // получает список файлов директории
        File directory = new File(sourceFilesPath);
        File[] files = directory.listFiles();
        // обходит список
        assert files != null;
        for(File file: files) {
            // если файл то передаёт в метод перемещеня
            if (file.isFile()) {
                MoveHelper.move(file, dustFilePath);
            } else {
                // Если это папка, то вызываем рекурсивно метод traverseList для этой папки
                File[] destSubFolder = new File[1];
                destSubFolder[0] = file;
                if (checkFileExistence(destSubFolder) == true) {
                    traverseList(file.getAbsolutePath(), dustFilePath);
                }
            }
        }
    }
}
