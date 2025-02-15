/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.Terminal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PreConnectHandler
implements Questioner,
KeyListener {
    private Object mySync = new Object();
    private Terminal myTerminal;
    private StringBuffer myAnswer;
    private boolean myVisible;

    public PreConnectHandler(Terminal terminal) {
        this.myTerminal = terminal;
        this.myVisible = true;
    }

    @Override
    public String questionHidden(String question) {
        this.myVisible = false;
        String answer = this.questionVisible(question, null);
        this.myVisible = true;
        return answer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String questionVisible(String question, String defValue) {
        Object object = this.mySync;
        synchronized (object) {
            this.myTerminal.writeUnwrappedString(question);
            this.myAnswer = new StringBuffer();
            if (defValue != null) {
                this.myAnswer.append(defValue);
                this.myTerminal.writeUnwrappedString(defValue);
            }
            try {
                this.mySync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String answerStr = this.myAnswer.toString();
            this.myAnswer = null;
            return answerStr;
        }
    }

    @Override
    public void showMessage(String message) {
        this.myTerminal.writeUnwrappedString(message);
        this.myTerminal.nextLine();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (this.myAnswer == null) {
            return;
        }
        Object object = this.mySync;
        synchronized (object) {
            boolean release = false;
            switch (e.getKeyCode()) {
                case 8: {
                    if (this.myAnswer.length() <= 0) break;
                    this.myTerminal.backspace();
                    this.myTerminal.eraseInLine(0);
                    this.myAnswer.deleteCharAt(this.myAnswer.length() - 1);
                    break;
                }
                case 10: {
                    this.myTerminal.nextLine();
                    release = true;
                }
            }
            if (release) {
                this.mySync.notifyAll();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (this.myAnswer == null) {
            return;
        }
        char c = e.getKeyChar();
        if (Character.getType(c) != 15) {
            if (this.myVisible) {
                this.myTerminal.writeCharacters(Character.toString(c));
            }
            this.myAnswer.append(c);
        }
    }
}

