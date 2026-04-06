package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.jdi.event.LocatableEvent;
import log.Logger;

public class GenerateMenuHotBar  {

    private static void setLookAndFeel(String className, Frame frame) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            // ignore
        }
    }

    public static JMenu generateMenuButton(String title, String description, int key) {
        JMenu menu = new JMenu(title);
        menu.getAccessibleContext().setAccessibleDescription(description);
        menu.setMnemonic(key);
        return menu;
    }

    public static JMenuItem CreateMenuTestButton(String title, String description, Frame frame, int key) {
        JMenuItem menu = new JMenuItem(title);
        menu.setMnemonic(key);
        menu.addActionListener((event) -> Logger.debug("Новая строка"));
        return menu;
    }

    public static JMenuItem CreateSystemAndCrossplatformLookAndFeel(Frame frame, String comp, String title) {
        JMenuItem menu = new JMenuItem(title);
        menu.addActionListener((event) -> setLookAndFeel(comp, frame));
        return menu;
    }

    public static JMenuItem CreateMenuExitButton(String title, String question, int key, Frame frame) {
        JMenuItem menu = new JMenuItem(title);
        menu.setMnemonic(key);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowEvent closeEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeEvent);
                }

        });
        return menu;
    }


    public static JMenuItem CreateEnglishMenuLocalisator(String title,String description,Frame frame,JMenuBar menuBar){
        JMenuItem menu = new JMenuItem(title);
        menu.getAccessibleContext().setAccessibleDescription(description);
        menu.addActionListener(e -> {
            Localizator.LoadEnglishLocalisation(menuBar);
            SwingUtilities.updateComponentTreeUI(frame);

        });

        return menu;
    }
    public static JMenuItem CreateRussionMenuLocalisator(String title,String description,Frame frame,JMenuBar menuBar){
        JMenuItem menu = new JMenuItem(title);
        menu.getAccessibleContext().setAccessibleDescription(description);
        menu.addActionListener(e -> {
            Localizator.LoadRussianLocalisation(menuBar);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        return menu;
    }

    public static boolean showExitDialog(Frame frame, String question, String title) {

            UIManager.put("OptionPane.yesButtonText", "Да");
            UIManager.put("OptionPane.noButtonText", "Нет");
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    question,
                    title,
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {

                return true;// Полное закрытие приложения
            }
            return  false;
    }


}
