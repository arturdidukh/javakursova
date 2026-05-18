package service;

import model.Warehouse;
import model.Workshop;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileService {

    private static final Logger logger = LoggerService.getLogger();

    private FileService() {}


    public static void saveWarehouse(Warehouse warehouse, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {

            oos.writeObject(warehouse);

            logger.info("Склад успішно збережено у файл: " + filename);
            System.out.println("[+] Склад збережено.");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Критична помилка при спробі збереження файлу!", e);
            System.err.println("Помилка збереження Складу.");
        }
    }

    public static void saveWorkshop(Workshop workshop, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(workshop);

            logger.info("Майстерню успішно збережено у файл: " + filename);
            System.out.println("[+] Майстерню збережено.");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Помилка збереження Майстерні (Workshop) у файл " + filename, e);
            System.err.println("Помилка збереження Майстерні.");
        }
    }

    public static Warehouse loadWarehouse(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            logger.info("Склад завантажено з файлу: " + filename);
            System.out.println("[+] Склад завантажено.");
            return (Warehouse) ois.readObject();

        } catch (FileNotFoundException e) {
            logger.warning("Файл складу не знайдено (" + filename + "). Створено новий.");
            System.out.println("[!] Файл не знайдено. Створено новий Склад.");
            return new Warehouse();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Критична помилка завантаження Складу з " + filename, e);
            System.err.println("Помилка завантаження. Створено новий Склад.");
            return new Warehouse();
        }
    }

    public static Workshop loadWorkshop(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            logger.info("Майстерню завантажено з файлу: " + filename);
            System.out.println("[+] Майстерню завантажено.");
            return (Workshop) ois.readObject();

        } catch (FileNotFoundException e) {
            logger.warning("Файл майстерні не знайдено (" + filename + "). Створено нову.");
            System.out.println("[!] Файл не знайдено. Створено нову Майстерню.");
            return new Workshop();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Критична помилка завантаження Майстерні з " + filename, e);
            System.err.println("Помилка завантаження. Створено нову Майстерню.");
            return new Workshop();
        }
    }
}