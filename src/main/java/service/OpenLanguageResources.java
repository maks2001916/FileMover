package service;

import ui.HomeWimdow;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class OpenLanguageResources {
    private File[] directory;
    private String[] langName;
    private final Properties properties = new Properties();

    public OpenLanguageResources() {
        openListLanguages();
        collectingLanguageNames();
    }

    public File[] getDirectory() {
        return directory;
    }

    public String[] getPropertiesLangName() {
        return langName;
    }

    /** метод для загрузки файлов свойств */
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

    /** метод получения имени файла из массива */
    public String getLanguageName(int i) {
        System.out.println(" получено имя файла: " + directory[i].getName());
        return directory[i].getName();
    }

    /** метод получения массива имён языка из файла свойств */
    public void collectingLanguageNames() {
        //массив размером количества файлов свойствgetLanguagesList
        langName = new String[directory.length];
        for (int i = 0; i < directory.length; i++) {
            //открывает файл свойств и загружает его в объект properties
            languageOpen(getLanguageName(i));
            System.out.println("language" + properties.getProperty("language"));
            langName[i] = properties.getProperty("language");
            System.out.println("имена загружены в массив" + directory.length);
        }
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

    /** метод получения значений свойств */
    public String getProperties(String key) {
        return properties.getProperty(key);
    }

    public void renameTitles(HomeWimdow homeWimdow,
                             int lang) {
        languageOpen(getLanguageName(lang));
        homeWimdow.getStage().setTitle(getProperties("title"));
        homeWimdow.getPathIn().setText(getProperties("pathIn"));
        homeWimdow.getPathOut().setText(getProperties("pathOut"));
        homeWimdow.getLabelStart().setText(getProperties("move"));
        //меняет  текст в меню для выбора языка
        homeWimdow.getSelectLanguage().setText(getProperties("language"));
        for (int i = 0; i < homeWimdow.getSelectLanguage().getItems().size(); i++) {
            homeWimdow.getSelectLanguage().getItems().
                    get(i).setText(getPropertiesLangName()[i]);
        }
    }
}
