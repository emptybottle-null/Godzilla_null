/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ProxyInputStream;

public class ObservableInputStream
extends ProxyInputStream {
    private final List<Observer> observers;

    public ObservableInputStream(InputStream inputStream) {
        this(inputStream, new ArrayList<Observer>());
    }

    private ObservableInputStream(InputStream inputStream, List<Observer> observers) {
        super(inputStream);
        this.observers = observers;
    }

    public ObservableInputStream(InputStream inputStream, Observer ... observers) {
        this(inputStream, Arrays.asList(observers));
    }

    public void add(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void close() throws IOException {
        IOException ioe = null;
        try {
            super.close();
        } catch (IOException e) {
            ioe = e;
        }
        if (ioe == null) {
            this.noteClosed();
        } else {
            this.noteError(ioe);
        }
    }

    public void consume() throws IOException {
        byte[] buffer = IOUtils.byteArray();
        while (this.read(buffer) != -1) {
        }
    }

    public List<Observer> getObservers() {
        return this.observers;
    }

    protected void noteClosed() throws IOException {
        for (Observer observer : this.getObservers()) {
            observer.closed();
        }
    }

    protected void noteDataByte(int value) throws IOException {
        for (Observer observer : this.getObservers()) {
            observer.data(value);
        }
    }

    protected void noteDataBytes(byte[] buffer, int offset, int length) throws IOException {
        for (Observer observer : this.getObservers()) {
            observer.data(buffer, offset, length);
        }
    }

    protected void noteError(IOException exception) throws IOException {
        for (Observer observer : this.getObservers()) {
            observer.error(exception);
        }
    }

    protected void noteFinished() throws IOException {
        for (Observer observer : this.getObservers()) {
            observer.finished();
        }
    }

    private void notify(byte[] buffer, int offset, int result, IOException ioe) throws IOException {
        if (ioe != null) {
            this.noteError(ioe);
            throw ioe;
        }
        if (result == -1) {
            this.noteFinished();
        } else if (result > 0) {
            this.noteDataBytes(buffer, offset, result);
        }
    }

    @Override
    public int read() throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read();
        } catch (IOException ex) {
            ioe = ex;
        }
        if (ioe != null) {
            this.noteError(ioe);
            throw ioe;
        }
        if (result == -1) {
            this.noteFinished();
        } else {
            this.noteDataByte(result);
        }
        return result;
    }

    @Override
    public int read(byte[] buffer) throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read(buffer);
        } catch (IOException ex) {
            ioe = ex;
        }
        this.notify(buffer, 0, result, ioe);
        return result;
    }

    @Override
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read(buffer, offset, length);
        } catch (IOException ex) {
            ioe = ex;
        }
        this.notify(buffer, offset, result, ioe);
        return result;
    }

    public void remove(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    public static abstract class Observer {
        public void closed() throws IOException {
        }

        public void data(byte[] buffer, int offset, int length) throws IOException {
        }

        public void data(int value) throws IOException {
        }

        public void error(IOException exception) throws IOException {
            throw exception;
        }

        public void finished() throws IOException {
        }
    }
}

