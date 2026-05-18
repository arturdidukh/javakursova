import commands.*;
import model.Warehouse;
import model.Workshop;
import service.LoggerService;
import repository.DatabaseConnector;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Warehouse warehouse = new Warehouse();
    private static Workshop workshop = new Workshop();
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerService.getLogger();

    private static Map<Integer, Command> mainCommands = new HashMap<>();
    private static Map<Integer, Command> warehouseCommands = new HashMap<>();
    private static Map<Integer, Command> workshopCommands = new HashMap<>();

    public static void main(String[] args) {
        LoggerService.setup();
        DatabaseConnector.initializeDatabase();

        logger.info("Програма 'Ювелірна Майстерня' запущена.");

        initializeCommands();

        try {
            runMainMenu();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Критичний збій системи: " + e.getMessage(), e);
            System.out.println("Сталася критична помилка. Подробиці записано в лог.");
        }

        logger.info("Програма завершила роботу.");
    }

    private static void initializeCommands() {
        mainCommands.put(1, new Command() { public void execute() { runWarehouseMenu(); } });
        mainCommands.put(2, new Command() { public void execute() { runWorkshopMenu(); } });
        mainCommands.put(3, new SaveDataCommand(warehouse, workshop));
        mainCommands.put(4, new LoadDataCommand(warehouse, workshop));
        mainCommands.put(5, new ShowHelpCommand());

        warehouseCommands.put(1, new CreateStoneCommand(warehouse));
        warehouseCommands.put(2, new ViewAllStonesCommand(warehouse));
        warehouseCommands.put(3, new EditStoneCommand(warehouse));
        warehouseCommands.put(4, new DeleteStoneCommand(warehouse));

        workshopCommands.put(1, new CreateNecklaceCommand(workshop));
        workshopCommands.put(2, new ViewAllNecklacesCommand(workshop));
        workshopCommands.put(3, new AddStoneToNecklaceCommand(workshop, warehouse));
        workshopCommands.put(4, new RemoveStoneFromNecklaceCommand(workshop));
        workshopCommands.put(5, new ViewStonesInNecklaceCommand(workshop));
        workshopCommands.put(6, new CalculateNecklaceCommand(workshop));
        workshopCommands.put(7, new SortStonesInNecklaceCommand(workshop));
        workshopCommands.put(8, new FindStonesInNecklaceCommand(workshop));
    }

    private static void runMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Керування Складом");
            System.out.println("2. Керування Майстернею");
            System.out.println("3. Зберегти дані");
            System.out.println("4. Завантажити дані");
            System.out.println("5. Довідка");
            System.out.println("5. Вийти");

            int choice = getUserChoice();
            if (choice == 9) {
                isRunning = false;
                continue;
            }

            Command command = mainCommands.get(choice);
            if (command != null) command.execute();
            else System.out.println("! Невідома команда.");
        }
    }

    private static void runWarehouseMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Керування Складом ---");
            System.out.println("1. Створити камінь");
            System.out.println("2. Показати всі камені");
            System.out.println("3. Редагувати камінь");
            System.out.println("4. Видалити камінь");
            System.out.println("5. Назад");

            int choice = getUserChoice();
            if (choice == 9) { isRunning = false; continue; }

            Command command = warehouseCommands.get(choice);
            if (command != null) command.execute();
            else System.out.println("! Невідома команда.");
        }
    }

    private static void runWorkshopMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Керування Майстернею ---");
            System.out.println("1. Створити нове намисто");
            System.out.println("2. Список намист");
            System.out.println("3. Додати камінь у намисто");
            System.out.println("4. Видалити камінь з намиста");
            System.out.println("5. Огляд намиста");
            System.out.println("6. Розрахунок (Вага/Ціна)");
            System.out.println("7. Сортування");
            System.out.println("8. Пошук за прозорістю");
            System.out.println("9. Назад");

            int choice = getUserChoice();
            if (choice == 9) { isRunning = false; continue; }

            Command command = workshopCommands.get(choice);
            if (command != null) command.execute();
            else System.out.println("! Невідома команда.");
        }
    }

    private static int getUserChoice() {
        try {
            System.out.print("Ваш вибір > ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.next();
            return -1;
        }
    }
}