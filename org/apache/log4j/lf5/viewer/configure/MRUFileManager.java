/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.configure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class MRUFileManager {
    private static final String CONFIG_FILE_NAME = "mru_file_manager";
    private static final int DEFAULT_MAX_SIZE = 3;
    private int _maxSize = 0;
    private LinkedList _mruFileList;

    public MRUFileManager() {
        this.load();
        this.setMaxSize(3);
    }

    public MRUFileManager(int maxSize) {
        this.load();
        this.setMaxSize(maxSize);
    }

    public void save() {
        File file = new File(this.getFilename());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this._mruFileList);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return this._mruFileList.size();
    }

    public Object getFile(int index) {
        if (index < this.size()) {
            return this._mruFileList.get(index);
        }
        return null;
    }

    public InputStream getInputStream(int index) throws IOException, FileNotFoundException {
        if (index < this.size()) {
            Object o = this.getFile(index);
            if (o instanceof File) {
                return this.getInputStream((File)o);
            }
            return this.getInputStream((URL)o);
        }
        return null;
    }

    public void set(File file) {
        this.setMRU(file);
    }

    public void set(URL url) {
        this.setMRU(url);
    }

    public String[] getMRUFileList() {
        if (this.size() == 0) {
            return null;
        }
        String[] ss = new String[this.size()];
        for (int i = 0; i < this.size(); ++i) {
            Object o = this.getFile(i);
            ss[i] = o instanceof File ? ((File)o).getAbsolutePath() : o.toString();
        }
        return ss;
    }

    public void moveToTop(int index) {
        this._mruFileList.add(0, this._mruFileList.remove(index));
    }

    public static void createConfigurationDirectory() {
        String home = System.getProperty("user.home");
        String sep = System.getProperty("file.separator");
        File f = new File(home + sep + "lf5");
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    protected InputStream getInputStream(File file) throws IOException, FileNotFoundException {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        return reader;
    }

    protected InputStream getInputStream(URL url) throws IOException {
        return url.openStream();
    }

    protected void setMRU(Object o) {
        int index = this._mruFileList.indexOf(o);
        if (index == -1) {
            this._mruFileList.add(0, o);
            this.setMaxSize(this._maxSize);
        } else {
            this.moveToTop(index);
        }
    }

    protected void load() {
        MRUFileManager.createConfigurationDirectory();
        File file = new File(this.getFilename());
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                this._mruFileList = (LinkedList)ois.readObject();
                ois.close();
                Iterator it = this._mruFileList.iterator();
                while (it.hasNext()) {
                    Object o = it.next();
                    if (o instanceof File || o instanceof URL) continue;
                    it.remove();
                }
            } catch (Exception e) {
                this._mruFileList = new LinkedList();
            }
        } else {
            this._mruFileList = new LinkedList();
        }
    }

    protected String getFilename() {
        String home = System.getProperty("user.home");
        String sep = System.getProperty("file.separator");
        return home + sep + "lf5" + sep + CONFIG_FILE_NAME;
    }

    protected void setMaxSize(int maxSize) {
        if (maxSize < this._mruFileList.size()) {
            for (int i = 0; i < this._mruFileList.size() - maxSize; ++i) {
                this._mruFileList.removeLast();
            }
        }
        this._maxSize = maxSize;
    }
}

