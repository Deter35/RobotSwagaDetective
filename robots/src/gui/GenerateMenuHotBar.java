package robots.src.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;  // ✅ Добавлен импорт

import javax.swing.*;

import log.Logger;

public class GenerateMenuHotBar extends JFrame {  // ✅ Убрал extends JFrame (статический класс!)

    private static void setLookAndFeel(String className, Frame frame) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            // ignore
        }
    }

    public static JMenu generateMenuButton(String title, String description, int key) {  // ✅ int key
        JMenu menu = new JMenu(title);
        menu.getAccessibleContext().setAccessibleDescription(description);
        menu.setMnemonic(key);
        return menu;
    }

    public static JMenuItem CreateMenuTestButton(String title, String description, Frame frame, int key) {  // ✅ int key
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

    public static JMenuItem CreateMenuExitButton(String title, String question, int key, Frame frame) {  // ✅ int key
        JMenuItem menu = new JMenuItem(title);
        menu.setMnemonic(key);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.yesButtonText", "Да");
                UIManager.put("OptionPane.noButtonText", "Нет");
                int confirm = JOptionPane.showConfirmDialog(frame, question, title, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();  // ✅ Только окно!
                }
            }
        });
        return menu;
    }
}
