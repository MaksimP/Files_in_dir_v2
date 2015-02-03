import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class General {

    public static void main(String[] args) throws AWTException {

        WorkingThread workingThread_WEB;
        WorkingThread workingThread_RA72;

        SystemTray systemTray = SystemTray.getSystemTray();
        BufferedImage bufferedImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();

        graphics.setColor(Color.MAGENTA);
        graphics.drawString("F", 5, 12);
        graphics.dispose();
        //Image image = Toolkit.getDefaultToolkit().createImage();
        TrayIcon trayIcon = new TrayIcon(bufferedImage);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        final PopupMenu popupMenu = new PopupMenu();
        MenuItem item = new MenuItem("Exit");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popupMenu.add(item);
        trayIcon.setPopupMenu(popupMenu);

        while (true)
        {
            workingThread_RA72 = new WorkingThread(Const.getParse_RA72());
            workingThread_WEB = new WorkingThread(Const.getParse_WEB());

            workingThread_RA72.start();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            workingThread_WEB.start();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                workingThread_RA72.join();
                workingThread_WEB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.gc();
        }

    }
}
