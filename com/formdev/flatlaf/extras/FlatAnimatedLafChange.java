/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.Animator;
import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.VolatileImage;
import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.RootPaneContainer;

public class FlatAnimatedLafChange {
    public static int duration = 160;
    public static int resolution = 40;
    private static Animator animator;
    private static final Map<JLayeredPane, JComponent> oldUIsnapshots;
    private static final Map<JLayeredPane, JComponent> newUIsnapshots;
    private static float alpha;
    private static boolean inShowSnapshot;

    public static void showSnapshot() {
        if (!FlatSystemProperties.getBoolean("flatlaf.animatedLafChange", true)) {
            return;
        }
        if (animator != null) {
            animator.stop();
        }
        alpha = 1.0f;
        FlatAnimatedLafChange.showSnapshot(true, oldUIsnapshots);
    }

    private static void showSnapshot(final boolean useAlpha, Map<JLayeredPane, JComponent> map) {
        Window[] windows;
        inShowSnapshot = true;
        for (Window window : windows = Window.getWindows()) {
            VolatileImage snapshot;
            if (!(window instanceof RootPaneContainer) || !window.isShowing() || (snapshot = window.createVolatileImage(window.getWidth(), window.getHeight())) == null) continue;
            JLayeredPane layeredPane = ((RootPaneContainer)((Object)window)).getLayeredPane();
            layeredPane.paint(snapshot.getGraphics());
            JComponent snapshotLayer = new JComponent(){

                @Override
                public void paint(Graphics g) {
                    if (inShowSnapshot || snapshot.contentsLost()) {
                        return;
                    }
                    if (useAlpha) {
                        ((Graphics2D)g).setComposite(AlphaComposite.getInstance(3, alpha));
                    }
                    g.drawImage(snapshot, 0, 0, null);
                }

                @Override
                public void removeNotify() {
                    super.removeNotify();
                    snapshot.flush();
                }
            };
            if (!useAlpha) {
                snapshotLayer.setOpaque(true);
            }
            snapshotLayer.setSize(layeredPane.getSize());
            layeredPane.add((Component)snapshotLayer, (Object)(JLayeredPane.DRAG_LAYER + (useAlpha ? 2 : 1)));
            map.put(layeredPane, snapshotLayer);
        }
        inShowSnapshot = false;
    }

    public static void hideSnapshotWithAnimation() {
        if (!FlatSystemProperties.getBoolean("flatlaf.animatedLafChange", true)) {
            return;
        }
        if (oldUIsnapshots.isEmpty()) {
            return;
        }
        FlatAnimatedLafChange.showSnapshot(false, newUIsnapshots);
        animator = new Animator(duration, fraction -> {
            if ((double)fraction < 0.1 || (double)fraction > 0.9) {
                return;
            }
            alpha = 1.0f - fraction;
            for (Map.Entry<JLayeredPane, JComponent> e : oldUIsnapshots.entrySet()) {
                if (!e.getKey().isShowing()) continue;
                e.getValue().repaint();
            }
        }, () -> {
            FlatAnimatedLafChange.hideSnapshot();
            animator = null;
        });
        animator.setResolution(resolution);
        animator.start();
    }

    private static void hideSnapshot() {
        FlatAnimatedLafChange.hideSnapshot(oldUIsnapshots);
        FlatAnimatedLafChange.hideSnapshot(newUIsnapshots);
    }

    private static void hideSnapshot(Map<JLayeredPane, JComponent> map) {
        for (Map.Entry<JLayeredPane, JComponent> e : map.entrySet()) {
            e.getKey().remove(e.getValue());
            e.getKey().repaint();
        }
        map.clear();
    }

    public static void stop() {
        if (animator != null) {
            animator.stop();
        } else {
            FlatAnimatedLafChange.hideSnapshot();
        }
    }

    static {
        oldUIsnapshots = new WeakHashMap<JLayeredPane, JComponent>();
        newUIsnapshots = new WeakHashMap<JLayeredPane, JComponent>();
    }
}

