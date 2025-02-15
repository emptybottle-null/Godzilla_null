/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import com.formdev.flatlaf.demo.DemoFrame;
import com.formdev.flatlaf.demo.DemoPrefs;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FlatLafDemo {
    static final String PREFS_ROOT_PATH = "/flatlaf-demo";
    static final String KEY_TAB = "tab";
    static boolean screenshotsMode = Boolean.parseBoolean(System.getProperty("flatlaf.demo.screenshotsMode"));

    public static void main(String[] args) {
        if (SystemInfo.isMacOS && System.getProperty("apple.laf.useScreenMenuBar") == null) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        if (screenshotsMode && !SystemInfo.isJava_9_orLater && System.getProperty("flatlaf.uiScale") == null) {
            System.setProperty("flatlaf.uiScale", "2x");
        }
        SwingUtilities.invokeLater(() -> {
            DemoPrefs.init(PREFS_ROOT_PATH);
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            DemoPrefs.initLaf(args);
            DemoFrame frame = new DemoFrame();
            if (screenshotsMode) {
                frame.setPreferredSize(new Dimension(1660, 840));
            }
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

