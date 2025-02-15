/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.tools.web;

import java.io.IOException;
import java.net.Socket;
import javassist.tools.web.Webserver;

class ServiceThread
extends Thread {
    Webserver web;
    Socket sock;

    public ServiceThread(Webserver w, Socket s) {
        this.web = w;
        this.sock = s;
    }

    @Override
    public void run() {
        try {
            this.web.process(this.sock);
        } catch (IOException iOException) {
            // empty catch block
        }
    }
}

