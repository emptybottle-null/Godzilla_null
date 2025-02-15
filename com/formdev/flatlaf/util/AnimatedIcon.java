/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JComponent;

public interface AnimatedIcon
extends Icon {
    @Override
    default public void paintIcon(Component c, Graphics g, int x, int y) {
        AnimationSupport.paintIcon(this, c, g, x, y);
    }

    public void paintIconAnimated(Component var1, Graphics var2, int var3, int var4, float var5);

    public float getValue(Component var1);

    default public boolean isAnimationEnabled() {
        return true;
    }

    default public int getAnimationDuration() {
        return 150;
    }

    default public int getAnimationResolution() {
        return 10;
    }

    default public Animator.Interpolator getAnimationInterpolator() {
        return CubicBezierEasing.STANDARD_EASING;
    }

    default public Object getClientPropertyKey() {
        return this.getClass();
    }

    public static class AnimationSupport {
        private float startValue;
        private float targetValue;
        private float animatedValue;
        private float fraction;
        private Animator animator;
        private int x;
        private int y;

        public static void paintIcon(AnimatedIcon icon, Component c, Graphics g, int x, int y) {
            if (!AnimationSupport.isAnimationEnabled(icon, c)) {
                AnimationSupport.paintIconImpl(icon, c, g, x, y, null);
                return;
            }
            JComponent jc = (JComponent)c;
            Object key = icon.getClientPropertyKey();
            AnimationSupport as = (AnimationSupport)jc.getClientProperty(key);
            if (as == null) {
                as = new AnimationSupport();
                as.targetValue = as.animatedValue = icon.getValue(c);
                as.startValue = as.animatedValue;
                as.x = x;
                as.y = y;
                jc.putClientProperty(key, as);
            } else {
                float value = icon.getValue(c);
                if (value != as.targetValue) {
                    if (as.animator == null) {
                        AnimationSupport as2 = as;
                        as.animator = new Animator(icon.getAnimationDuration(), fraction -> {
                            if (!c.isDisplayable()) {
                                as2.animator.stop();
                                return;
                            }
                            as2.animatedValue = as2.startValue + (as2.targetValue - as2.startValue) * fraction;
                            as2.fraction = fraction;
                            c.repaint(as2.x, as2.y, icon.getIconWidth(), icon.getIconHeight());
                        }, () -> {
                            as2.startValue = as2.animatedValue = as2.targetValue;
                            as2.animator = null;
                        });
                    }
                    if (as.animator.isRunning()) {
                        as.animator.cancel();
                        int duration2 = (int)((float)icon.getAnimationDuration() * as.fraction);
                        if (duration2 > 0) {
                            as.animator.setDuration(duration2);
                        }
                        as.startValue = as.animatedValue;
                    } else {
                        as.animator.setDuration(icon.getAnimationDuration());
                        as.animator.setResolution(icon.getAnimationResolution());
                        as.animator.setInterpolator(icon.getAnimationInterpolator());
                        as.animatedValue = as.startValue;
                    }
                    as.targetValue = value;
                    as.animator.start();
                }
                as.x = x;
                as.y = y;
            }
            AnimationSupport.paintIconImpl(icon, c, g, x, y, as);
        }

        private static void paintIconImpl(AnimatedIcon icon, Component c, Graphics g, int x, int y, AnimationSupport as) {
            float value = as != null ? as.animatedValue : icon.getValue(c);
            icon.paintIconAnimated(c, g, x, y, value);
        }

        private static boolean isAnimationEnabled(AnimatedIcon icon, Component c) {
            return Animator.useAnimation() && icon.isAnimationEnabled() && c instanceof JComponent;
        }

        public static void saveIconLocation(AnimatedIcon icon, Component c, int x, int y) {
            if (!AnimationSupport.isAnimationEnabled(icon, c)) {
                return;
            }
            AnimationSupport as = (AnimationSupport)((JComponent)c).getClientProperty(icon.getClientPropertyKey());
            if (as != null) {
                as.x = x;
                as.y = y;
            }
        }
    }
}

