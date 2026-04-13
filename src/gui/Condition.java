package gui;

import log.Logger;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.*;


public class Condition {
    File position;
    public static String[] defaults = {"Unknown", "100", "100", "350", "350", "false", "false"};

    public Condition(){
        position = new File("position.txt");
        if(!position.exists()){
            try {
                position.createNewFile();
                System.out.println("file created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeState(JDesktopPane desktopPane) throws IOException {
        FileWriter fw = new FileWriter(position, false);
        JInternalFrame frame;
        fw.write("Title" + ":" +  "X" + ":" + "Y" + ":" + "Width" + ":" + "Height" + ":" + "isIcon" + ":" + "isMaximum" + "\n");
        for(int i = 0; i < desktopPane.getComponentCount(); i++) {
            frame = desktopPane.getAllFrames()[i];
            fw.write(frame.getTitle() + ":"
                    + frame.getX() + ":" + frame.getY() + ":"
                    + (int)(frame.getSize().getWidth()) + ":" + (int)(frame.getSize().getHeight()) + ":"
                    + frame.isIcon() + ":" + frame.isMaximum() +"\n");
        }
        fw.close();
    }

    public void readState(JDesktopPane desktopPane) throws IOException, PropertyVetoException {
        BufferedReader buffer = new BufferedReader(new FileReader(position));
        buffer.readLine();
        String line;
        JInternalFrame frame1;
        while((line = buffer.readLine()) != null) {
            if(line == null || line.trim().isEmpty()) {
            Logger.debug("Пустая строка - что восстанавливать то?");
            continue;
        }
            String[] parts = line.split(":");
            if(parts.length < 7) {
                Logger.debug("Строка неполноценная ,ожидал 7 полей, получил " + parts.length);
                continue;
            }
            for(int j =0;j<parts.length;j++){
                if(parts[j] == null || parts[j].trim().isEmpty()) {
                    Logger.debug("Поле " + j + " пустое в строке: " + line + " Запуск с дефолтными координатами");
                    parts[j] = defaults[j];
                }
            }
            String title = parts[0];
            if(title == null || title.trim().isEmpty()) {
                Logger.debug("Заголовок окна - пустой, не-знаю что восстанавливать");
                continue;
            }
            for(int i = 0; i < desktopPane.getComponentCount(); i++) {
                if(title.equals(desktopPane.getAllFrames()[i].getTitle())) {
                    frame1 = desktopPane.getAllFrames()[i];

                    frame1.setLocation(parseIntDefault(parts[1]), parseIntDefault(parts[2]));
                    if(parseIntDefault(parts[3]) <= 0 || parseIntDefault(parts[4]) <= 0){
                        Logger.debug("Wrong size");
                        frame1.setSize(350,350);
                    } else {
                        frame1.setSize(parseIntDefault(parts[3]), parseIntDefault(parts[4]));
                    }
                    if(parseBooleanDefault(parts[5]) && parseBooleanDefault(parts[6])) {
                        Logger.debug("Wrong statement isIcon and isMaximum");
                        frame1.setIcon(false);
                        frame1.setMaximum(false);
                        frame1.setSize(350,350);
                    } else {
                        frame1.setIcon(parseBooleanDefault(parts[5]));
                        frame1.setMaximum(parseBooleanDefault(parts[6]));
                    }
                }
            }
        }
        buffer.close();
    }

    public int parseIntDefault(String s){
        int res;
        try {
            res = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 350;
        }
        return res;
    }

    public boolean parseBooleanDefault(String s){
        boolean res;
        try {
            res = Boolean.parseBoolean(s);
        } catch (Exception e) {
            return false;
        }
        return res;
    }
}