package service;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class MoverService {
    private File[] directory; //массив файлов ресурсов
    private long allFilesSize; //суммарный размер файлов
    private long progress; //сумма перемещённых файлов
    private String[] langName; //название языка
    private final Properties properties = new Properties();

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
    public double traverseList(String sourceFilesPath, String dustFilePath) {
        // получает список файлов директории
        File directory = new File(sourceFilesPath);
        File[] files = directory.listFiles();

        // обходит список
        assert files != null;
        for(File file: files) {
            // если файл то передаёт в метод перемещеня
            if (file.isFile()) {
                if (!sourceFilesPath.equals(dustFilePath)) {
                    MoveHelper.move(file, dustFilePath);
                    if (allFilesSize > 0) {
                        progress = progress + file.length();
                        System.out.println("progress - " + MoveHelper.progresses(progress, allFilesSize));
                        MoveHelper.progresses(progress, allFilesSize);
                    } else {
                        System.out.println("progress - " + MoveHelper.progresses(progress, allFilesSize));
                        System.out.println(file.length()/allFilesSize);
                        MoveHelper.progresses(progress, allFilesSize);
                    }

                }
            } else {
                // Если это папка, то вызываем рекурсивно метод traverseList для этой папки
                File[] destSubFolder = new File[1];
                destSubFolder[0] = file;
                if (checkFileExistence(destSubFolder) == true) {
                    traverseList(file.getAbsolutePath(), dustFilePath);
                }
                file.delete();
            }
        }
        return allFilesSize/1;
    }



    public void traverseListSize(String sourceFilesPath, String dustFilePath) {
        // получает список файлов директории
        File directory = new File(sourceFilesPath);
        File[] files = directory.listFiles();
        // обходит список
        assert files != null;
        for(File file: files) {
            // если файл то прибаввляет его размер к общему размеру
            if (file.isFile()) {
                if (!sourceFilesPath.equals(dustFilePath)) {
                    allFilesSize = allFilesSize + file.length();
                    System.out.println("allFilesSize - " + allFilesSize);
                }
            } else {
                // Если это папка, то вызываем рекурсивно метод traverseListSize для этой папки
                File[] destSubFolder = new File[1];
                destSubFolder[0] = file;
                if (checkFileExistence(destSubFolder) == true) {
                    traverseListSize(file.getAbsolutePath(), dustFilePath);
                }
            }
        }
    }

    /** метод получения имени файла из массива */
    public String getLanguageName(int i) {
        System.out.println(" получено имя файла: " + directory[i].getName());
        return directory[i].getName();
    }

    /** метод получения имени языка из файла свойств */
    public void collectingLanguageNames()  {
        langName = new String[directory.length];
        for (int i = 0; i < directory.length; i++) {
            languageOpen(getLanguageName(i));
            System.out.println(properties.getProperty("language"));
            langName[i] = properties.getProperty("language");
            System.out.println("имена загружены в массив" + directory.length);
        }
    }

    public String[] getLanguagesList() {
        return langName;
    }

    /** метод получения файлов свойств с языками */
    public void openListLanguages() {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".properties");
            }
        };
        directory = new File("src/main/resources").listFiles(filter);
        System.out.println("файлы загружены в массив " + directory.length);
    }

    /** метод открытия файла свойств */
    public void languageOpen(String lang) {
        try(InputStream input = new FileInputStream("src/main/resources/" + lang);
            InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            System.out.println("поле title = " + getProperties("title"));
        }
    }

    /** метод переименования кнопок и заголовков */
    public void renameTitles(Stage stage,
                             Label pathIn,
                             Label pathOut,
                             Label start,
                             MenuButton lang,
                             String title) {
        stage.setTitle(getProperties("title"));
        pathIn.setText(getProperties("pathIn"));
        pathOut.setText(getProperties("pathOut"));
        start.setText(getProperties("move"));
        lang.setText(getProperties("languageSmall"));
        languageOpen(title);
    }

    /** метод получения значений свойств */
    public String getProperties(String key) {
        return properties.getProperty(key);
    }

    /** метод для перевода пути файла в строку */
    public String fileToStylesheetString ( File stylesheetFile ) {
        return stylesheetFile.toURI().toString();
    }

}
