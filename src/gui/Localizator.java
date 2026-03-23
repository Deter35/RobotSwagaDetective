package gui;

import javax.swing.*;
import java.awt.*;

public class Localizator {

    public static void LoadRussianLocalisation(JMenuBar menuBAr){
        menuBAr.getMenu(0).setText("Режим Отображения");
        menuBAr.getMenu(0).getItem(0).setText("Системный режим отображения");
        menuBAr.getMenu(0).getItem(1).setText("Стандартный режим отображения");
        menuBAr.getMenu(1).setText("Тесты");
        menuBAr.getMenu(1).getItem(0).setText("Вывести в лог");
        menuBAr.getMenu(2).setText("Смена языка");
        menuBAr.getMenu(2).getItem(0).setText("Русский");
        menuBAr.getMenu(2).getItem(1).setText("Английский");
        menuBAr.getMenu(3).setText("Выйти");
        Component comp = menuBAr.getComponent(3);
        JMenuItem exitMenu = (JMenuItem) comp;
        exitMenu.setText("Выйти");
        //menuBAr.getMenu(3).
        UIManager.put("InternalFrame.closeButtonToolTip", "Закрыть");
        UIManager.put("InternalFrame.iconButtonToolTip", "Свернуть");
        UIManager.put("InternalFrame.maxButtonToolTip", "Развернуть");
        UIManager.put("InternalFrame.restoreButtonToolTip", "Восстановить");
        UIManager.put("InternalFrameTitlePane.closeButtonAccessibleName", "Закрыть");
        UIManager.put("InternalFrameTitlePane.closeButtonText", "Закрыть");
        UIManager.put("InternalFrameTitlePane.iconifyButtonAccessibleName", "Свернуть");
        UIManager.put("InternalFrameTitlePane.maximizeButtonAccessibleName", "Развернуть");
        UIManager.put("InternalFrameTitlePane.maximizeButtonText", "Развернуть");
        UIManager.put("InternalFrameTitlePane.minimizeButtonText", "Свернуть");
        UIManager.put("InternalFrameTitlePane.moveButtonText", "Переместить");
        UIManager.put("InternalFrameTitlePane.restoreButtonText", "Восстановить");
        UIManager.put("InternalFrameTitlePane.sizeButtonText", "Размер");

    }

    public  static void LoadEnglishLocalisation(JMenuBar menuBar){
        menuBar.getMenu(0).setText("Display mode");
        menuBar.getMenu(0).getItem(0).setText("System display mode");
        menuBar.getMenu(0).getItem(1).setText("Standard display mode");
        menuBar.getMenu(1).setText("Tests");
        menuBar.getMenu(1).getItem(0).setText("Output to log");
        menuBar.getMenu(2).setText("Change language");
        menuBar.getMenu(2).getItem(0).setText("Russian");
        menuBar.getMenu(2).getItem(1).setText("English");
        Component comp = menuBar.getComponent(3);
        JMenuItem exitMenu = (JMenuItem) comp;
        exitMenu.setText("Exit");
        UIManager.put("InternalFrame.closeButtonToolTip", "Close");
        UIManager.put("InternalFrame.iconButtonToolTip", "Minimize");
        UIManager.put("InternalFrame.maxButtonToolTip", "Maximize");
        UIManager.put("InternalFrame.restoreButtonToolTip", "Restore");
        UIManager.put("InternalFrameTitlePane.closeButtonAccessibleName", "Close");
        UIManager.put("InternalFrameTitlePane.closeButtonText", "Close");
        UIManager.put("InternalFrameTitlePane.iconifyButtonAccessibleName", "Minimize");
        UIManager.put("InternalFrameTitlePane.maximizeButtonAccessibleName", "Maximize");
        UIManager.put("InternalFrameTitlePane.maximizeButtonText", "Maximize");
        UIManager.put("InternalFrameTitlePane.minimizeButtonText", "Minimize");
        UIManager.put("InternalFrameTitlePane.moveButtonText", "Move");
        UIManager.put("InternalFrameTitlePane.restoreButtonText", "Restore");
        UIManager.put("InternalFrameTitlePane.sizeButtonText", "Size");

    }
}
