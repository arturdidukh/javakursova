package ui;

import model.Stone;
import model.PreciousStone;
import model.SemiPreciousStone;
import repository.StoneRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private StoneRepository repo = new StoneRepository();

    public MainFrame() {
        setTitle("Склад дорогоцінного каміння");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnLoad = new JButton("Оновити таблицю");
        JButton btnAdd = new JButton("Додати камінь");
        JButton btnClear = new JButton("Очистити базу");

        btnPanel.add(btnLoad);
        btnPanel.add(btnAdd);
        btnPanel.add(btnClear);

        String[] columns = {"Назва", "Вага (кар)", "Ціна за карат", "Прозорість", "Тип"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        btnLoad.addActionListener(e -> refreshTable());

        btnClear.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Ви впевнені, що хочете видалити ВСІ дані з бази?",
                    "Підтвердження", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repo.deleteAll();
                refreshTable();
            }
        });

        btnAdd.addActionListener(e -> showAddDialog());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    private void showAddDialog() {
        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField transparencyField = new JTextField();
        String[] types = {"PreciousStone", "SemiPreciousStone"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        Object[] message = {
                "Назва:", nameField,
                "Вага (карати):", weightField,
                "Ціна за карат ($):", priceField,
                "Прозорість (1-10):", transparencyField,
                "Тип каменя:", typeBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Додати новий камінь", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String weightStr = weightField.getText().replace(",", ".");
                String priceStr = priceField.getText().replace(",", ".");
                String transStr = transparencyField.getText().trim();

                if (name.isEmpty() || weightStr.isEmpty() || priceStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Помилка: Заповніть усі поля!");
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double price = Double.parseDouble(priceStr);
                int transparency = Integer.parseInt(transStr);
                String selectedType = (String) typeBox.getSelectedItem();

                Stone newStone;
                if ("PreciousStone".equals(selectedType)) {
                    newStone = new PreciousStone(name, weight, price, transparency);
                } else {
                    newStone = new SemiPreciousStone(name, weight, price, transparency);
                }

                repo.save(newStone);
                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Помилка: Введіть числові значення (через крапку)!");
            }
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Stone> stones = repo.findAll();
        for (Stone s : stones) {
            tableModel.addRow(new Object[]{
                    s.getName(),
                    s.getCaratWeight(),
                    s.getPricePerCarat(),
                    s.getTransparency(),
                    s.getClass().getSimpleName()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}