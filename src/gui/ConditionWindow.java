// CoordinatesWindow.java
package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ConditionWindow extends JInternalFrame implements Observer {
    private final JLabel robotXLabel;
    private final JLabel robotYLabel;
    private final JLabel robotDirectionLabel;
    private final JLabel robotVelocityLabel;
    private final JLabel targetXLabel;
    private final JLabel targetYLabel;
    private final RobotModel model;

    public ConditionWindow(RobotModel model) {
        super("Координаты робота", true, true, true, true);
        this.model = model;

        // Подписываемся на уведомления от модели
        model.addObserver(this);

        // Создаем панель с информацией
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Добавляем метки
        panel.add(new JLabel("Позиция робота X:"));
        robotXLabel = new JLabel(String.valueOf(model.getRobotPositionX()));
        panel.add(robotXLabel);

        panel.add(new JLabel("Позиция робота Y:"));
        robotYLabel = new JLabel(String.valueOf(model.getRobotPositionY()));
        panel.add(robotYLabel);

        panel.add(new JLabel("Направление робота (рад):"));
        robotDirectionLabel = new JLabel(String.format("%.3f", model.getRobotDirection()));
        panel.add(robotDirectionLabel);

        panel.add(new JLabel("Скорость робота :"));
        robotVelocityLabel = new JLabel(String.valueOf(model.getVelocity()));
        panel.add(robotVelocityLabel);

        panel.add(new JLabel("Цель X:"));
        targetXLabel = new JLabel(String.valueOf(model.getTargetPositionX()));
        panel.add(targetXLabel);

        panel.add(new JLabel("Цель Y:"));
        targetYLabel = new JLabel(String.valueOf(model.getTargetPositionY()));
        panel.add(targetYLabel);

        getContentPane().add(panel);
        setSize(250, 200);
        setLocation(320, 10);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Обновляем отображение координат
        SwingUtilities.invokeLater(() -> {
            robotXLabel.setText(String.format("%.1f", model.getRobotPositionX()));
            robotYLabel.setText(String.format("%.1f", model.getRobotPositionY()));
            robotDirectionLabel.setText(String.format("%.3f", model.getRobotDirection()));
            targetXLabel.setText(String.valueOf(model.getTargetPositionX()));
            targetYLabel.setText(String.valueOf(model.getTargetPositionY()));
        });
    }
}