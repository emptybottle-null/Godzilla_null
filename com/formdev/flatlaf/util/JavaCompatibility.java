/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class JavaCompatibility {
    private static Method drawStringUnderlineCharAtMethod;
    private static Method getClippedStringMethod;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
        Class<JavaCompatibility> clazz = JavaCompatibility.class;
        synchronized (JavaCompatibility.class) {
            if (drawStringUnderlineCharAtMethod == null) {
                try {
                    Class[] classArray;
                    Class<?> cls = Class.forName(SystemInfo.isJava_9_orLater ? "javax.swing.plaf.basic.BasicGraphicsUtils" : "sun.swing.SwingUtilities2");
                    if (SystemInfo.isJava_9_orLater) {
                        Class[] classArray2 = new Class[6];
                        classArray2[0] = JComponent.class;
                        classArray2[1] = Graphics2D.class;
                        classArray2[2] = String.class;
                        classArray2[3] = Integer.TYPE;
                        classArray2[4] = Float.TYPE;
                        classArray = classArray2;
                        classArray2[5] = Float.TYPE;
                    } else {
                        Class[] classArray3 = new Class[6];
                        classArray3[0] = JComponent.class;
                        classArray3[1] = Graphics.class;
                        classArray3[2] = String.class;
                        classArray3[3] = Integer.TYPE;
                        classArray3[4] = Integer.TYPE;
                        classArray = classArray3;
                        classArray3[5] = Integer.TYPE;
                    }
                    drawStringUnderlineCharAtMethod = cls.getMethod("drawStringUnderlineCharAt", classArray);
                } catch (Exception ex) {
                    Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }
            // ** MonitorExit[var6_6] (shouldn't be in output)
            try {
                if (SystemInfo.isJava_9_orLater) {
                    drawStringUnderlineCharAtMethod.invoke(null, c, g, text, underlinedIndex, Float.valueOf(x), Float.valueOf(y));
                } else {
                    drawStringUnderlineCharAtMethod.invoke(null, c, g, text, underlinedIndex, x, y);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String getClippedString(JComponent c, FontMetrics fm, String string, int availTextWidth) {
        Class<JavaCompatibility> clazz = JavaCompatibility.class;
        synchronized (JavaCompatibility.class) {
            if (getClippedStringMethod == null) {
                try {
                    Class<?> cls = Class.forName(SystemInfo.isJava_9_orLater ? "javax.swing.plaf.basic.BasicGraphicsUtils" : "sun.swing.SwingUtilities2");
                    getClippedStringMethod = cls.getMethod(SystemInfo.isJava_9_orLater ? "getClippedString" : "clipStringIfNecessary", JComponent.class, FontMetrics.class, String.class, Integer.TYPE);
                } catch (Exception ex) {
                    Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }
            // ** MonitorExit[var4_4] (shouldn't be in output)
            try {
                return (String)getClippedStringMethod.invoke(null, c, fm, string, availTextWidth);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }
}

