package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import log.Logger;


public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private Condition condition;
    public MainApplicationFrame() {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
            screenSize.width  - inset*2,
            screenSize.height - inset*2);

        setContentPane(desktopPane);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AddExit(this);
        LogWindow logWindow = createLogWindow();

        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400,  400);
        addWindow(gameWindow);
        condition = new Condition();
        restoreWindowState();
        setJMenuBar(generateMenuBar());

    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    private void restoreWindowState() {
        try {
            condition.readState(desktopPane);
            Logger.debug("Состояние окон восстановлено");
        } catch (IOException e) {
            Logger.debug("Ошибка чтения файла состояния: " + e.getMessage());
        } catch (PropertyVetoException e) {
            Logger.debug("Ошибка восстановления состояния окон: " + e.getMessage());
        }
    }


    private void saveWindowState() {
        try {
            condition.writeState(desktopPane);
            Logger.debug("Состояние окон сохранено");
        } catch (IOException e) {
            Logger.debug("Ошибка сохранения состояния окон: " + e.getMessage());
        }
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    

    
    private JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu testmenu = GenerateMenuHotBar.generateMenuButton("Тесты","Тест",KeyEvent.VK_S);
        testmenu.add(GenerateMenuHotBar.CreateMenuTestButton("Вывести в лог","Ы",this,KeyEvent.VK_E));
        JMenu LookMenu = GenerateMenuHotBar.generateMenuButton("Управление режимом отображения","Режим",KeyEvent.VK_1);
        LookMenu.add(GenerateMenuHotBar.CreateSystemAndCrossplatformLookAndFeel(this, UIManager.getSystemLookAndFeelClassName(),"Системный режим отображения"));
        LookMenu.add(GenerateMenuHotBar.CreateSystemAndCrossplatformLookAndFeel(this, UIManager.getCrossPlatformLookAndFeelClassName(),"Стандартный режим отображения"));
        JMenuItem exitmenu = GenerateMenuHotBar.CreateMenuExitButton("Выход","Выйти?",KeyEvent.VK_ESCAPE,this);
        JMenu LocalisationMenu = GenerateMenuHotBar.generateMenuButton("Смена языка","Смена языка кнопок",KeyEvent.VK_Y);
        LocalisationMenu.add(GenerateMenuHotBar.CreateRussionMenuLocalisator("Русский язык","Смена на русски язык",this,menuBar));
        LocalisationMenu.add(GenerateMenuHotBar.CreateEnglishMenuLocalisator("Английский язык","Смена на английский язык",this,menuBar));
        

        menuBar.add(LookMenu);
        menuBar.add(testmenu);
        menuBar.add(LocalisationMenu);
        menuBar.add(exitmenu);
        return menuBar;
    }

    private static void AddExit(Frame frame){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(GenerateMenuHotBar.showExitDialog(frame, "Выйти", "Выход из окна?")) {
                    ((MainApplicationFrame) frame).saveWindowState();
                    frame.dispose();
                    System.exit(0);
                }

            }
        }
        );}
}
