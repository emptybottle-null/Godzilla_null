/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import java.util.ArrayList;
import javax.swing.Timer;

public class Animator {
    private int duration;
    private int resolution = 10;
    private Interpolator interpolator;
    private final ArrayList<TimingTarget> targets = new ArrayList();
    private final Runnable endRunnable;
    private boolean running;
    private boolean hasBegun;
    private boolean timeToStop;
    private long startTime;
    private Timer timer;

    public static boolean useAnimation() {
        return FlatSystemProperties.getBoolean("flatlaf.animation", true);
    }

    public Animator(int duration) {
        this(duration, null, null);
    }

    public Animator(int duration, TimingTarget target) {
        this(duration, target, null);
    }

    public Animator(int duration, TimingTarget target, Runnable endRunnable) {
        this.setDuration(duration);
        this.addTarget(target);
        this.endRunnable = endRunnable;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.throwExceptionIfRunning();
        if (duration <= 0) {
            throw new IllegalArgumentException();
        }
        this.duration = duration;
    }

    public int getResolution() {
        return this.resolution;
    }

    public void setResolution(int resolution) {
        this.throwExceptionIfRunning();
        if (resolution <= 0) {
            throw new IllegalArgumentException();
        }
        this.resolution = resolution;
    }

    public Interpolator getInterpolator() {
        return this.interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.throwExceptionIfRunning();
        this.interpolator = interpolator;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addTarget(TimingTarget target) {
        if (target == null) {
            return;
        }
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            if (!this.targets.contains(target)) {
                this.targets.add(target);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeTarget(TimingTarget target) {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            this.targets.remove(target);
        }
    }

    public void start() {
        this.throwExceptionIfRunning();
        this.running = true;
        this.hasBegun = false;
        this.timeToStop = false;
        this.startTime = System.nanoTime() / 1000000L;
        if (this.timer == null) {
            this.timer = new Timer(this.resolution, e -> {
                if (!this.hasBegun) {
                    this.begin();
                    this.hasBegun = true;
                }
                this.timingEvent(this.getTimingFraction());
            });
        } else {
            this.timer.setDelay(this.resolution);
        }
        this.timer.setInitialDelay(0);
        this.timer.start();
    }

    public void stop() {
        this.stop(false);
    }

    public void cancel() {
        this.stop(true);
    }

    private void stop(boolean cancel) {
        if (!this.running) {
            return;
        }
        if (this.timer != null) {
            this.timer.stop();
        }
        if (!cancel) {
            this.end();
        }
        this.running = false;
        this.timeToStop = false;
    }

    public void restart() {
        this.cancel();
        this.start();
    }

    public boolean isRunning() {
        return this.running;
    }

    private float getTimingFraction() {
        long currentTime = System.nanoTime() / 1000000L;
        long elapsedTime = currentTime - this.startTime;
        this.timeToStop = elapsedTime >= (long)this.duration;
        float fraction = this.clampFraction((float)elapsedTime / (float)this.duration);
        if (this.interpolator != null) {
            fraction = this.clampFraction(this.interpolator.interpolate(fraction));
        }
        return fraction;
    }

    private float clampFraction(float fraction) {
        if (fraction < 0.0f) {
            return 0.0f;
        }
        if (fraction > 1.0f) {
            return 1.0f;
        }
        return fraction;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void timingEvent(float fraction) {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget target : this.targets) {
                target.timingEvent(fraction);
            }
        }
        if (this.timeToStop) {
            this.stop();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void begin() {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget target : this.targets) {
                target.begin();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void end() {
        ArrayList<TimingTarget> arrayList = this.targets;
        synchronized (arrayList) {
            for (TimingTarget target : this.targets) {
                target.end();
            }
        }
        if (this.endRunnable != null) {
            this.endRunnable.run();
        }
    }

    private void throwExceptionIfRunning() {
        if (this.isRunning()) {
            throw new IllegalStateException();
        }
    }

    @FunctionalInterface
    public static interface Interpolator {
        public float interpolate(float var1);
    }

    @FunctionalInterface
    public static interface TimingTarget {
        public void timingEvent(float var1);

        default public void begin() {
        }

        default public void end() {
        }
    }
}

