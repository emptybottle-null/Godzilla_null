/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app;

import com.kitfox.svg.app.PlayerThreadListener;
import java.util.HashSet;

public class PlayerThread
implements Runnable {
    HashSet<PlayerThreadListener> listeners = new HashSet();
    double curTime = 0.0;
    double timeStep = 0.2;
    public static final int PS_STOP = 0;
    public static final int PS_PLAY_FWD = 1;
    public static final int PS_PLAY_BACK = 2;
    int playState = 0;
    Thread thread = new Thread(this);

    public PlayerThread() {
        this.thread.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        while (this.thread != null) {
            PlayerThread playerThread = this;
            synchronized (playerThread) {
                switch (this.playState) {
                    case 1: {
                        this.curTime += this.timeStep;
                        break;
                    }
                    case 2: {
                        this.curTime -= this.timeStep;
                        if (!(this.curTime < 0.0)) break;
                        this.curTime = 0.0;
                        break;
                    }
                }
                this.fireTimeUpdateEvent();
            }
            try {
                Thread.sleep((long)(this.timeStep * 1000.0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void exit() {
        this.thread = null;
    }

    public synchronized void addListener(PlayerThreadListener listener) {
        this.listeners.add(listener);
    }

    public synchronized double getCurTime() {
        return this.curTime;
    }

    public synchronized void setCurTime(double time) {
        this.curTime = time;
    }

    public synchronized double getTimeStep() {
        return this.timeStep;
    }

    public synchronized void setTimeStep(double time) {
        this.timeStep = time;
        if (this.timeStep < 0.01) {
            this.timeStep = 0.01;
        }
    }

    public synchronized int getPlayState() {
        return this.playState;
    }

    public synchronized void setPlayState(int playState) {
        this.playState = playState;
    }

    private void fireTimeUpdateEvent() {
        for (PlayerThreadListener listener : this.listeners) {
            listener.updateTime(this.curTime, this.timeStep, this.playState);
        }
    }
}

