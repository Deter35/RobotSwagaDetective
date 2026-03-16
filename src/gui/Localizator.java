package gui;

import javax.swing.*;

public class Localizator {

    public static void LoadRussianLocalisation(){
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

    public  static void LoadEnglishLocalisation(){
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
