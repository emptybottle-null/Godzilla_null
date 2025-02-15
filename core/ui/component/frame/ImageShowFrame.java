/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.frame;

import core.EasyI18N;
import core.annotation.NoI18N;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.functions;

public class ImageShowFrame
extends JFrame {
    private JPanel panel = new JPanel(new BorderLayout());
    @NoI18N
    private JLabel imageLabel;

    private ImageShowFrame(Frame owner, ImageIcon imageIcon, String title, int width, int height) {
        super(title);
        this.imageLabel = new JLabel(imageIcon);
        this.panel.add(this.imageLabel);
        this.add(this.panel);
        functions.setWindowSize(this, width, height);
        this.setLocationRelativeTo(owner);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon, String title, int width, int height) {
        width += 50;
        height += 50;
        if (title == null || title.trim().length() < 1) {
            title = String.format("image info Width:%s Height:%s", imageIcon.getIconWidth(), imageIcon.getIconHeight());
        }
        ImageShowFrame imageShowDialog = new ImageShowFrame(owner, imageIcon, title, width, height);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon, String title) {
        ImageShowFrame.showImageDiaolog(owner, imageIcon, title, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    public static void showImageDiaolog(ImageIcon imageIcon, String title) {
        ImageShowFrame.showImageDiaolog(null, imageIcon, title);
    }

    public static void showImageDiaolog(Frame owner, ImageIcon imageIcon) {
        ImageShowFrame.showImageDiaolog(owner, imageIcon, null);
    }

    public static void showImageDiaolog(ImageIcon imageIcon) {
        ImageShowFrame.showImageDiaolog(null, imageIcon);
    }
}

