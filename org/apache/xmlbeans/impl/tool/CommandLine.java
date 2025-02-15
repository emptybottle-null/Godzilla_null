/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.IOUtil;

public class CommandLine {
    private Map _options;
    private String[] _badopts;
    private String[] _args;
    private List _files;
    private List _urls;
    private File _baseDir;
    private static final File[] EMPTY_FILEARRAY = new File[0];
    private static final URL[] EMPTY_URLARRAY = new URL[0];

    public CommandLine(String[] args, Collection flags, Collection scheme) {
        if (flags == null || scheme == null) {
            throw new IllegalArgumentException("collection required (use Collections.EMPTY_SET if no options)");
        }
        this._options = new LinkedHashMap();
        ArrayList<String> badopts = new ArrayList<String>();
        ArrayList<String> endargs = new ArrayList<String>();
        for (int i = 0; i < args.length; ++i) {
            if (args[i].indexOf(45) == 0) {
                String opt = args[i].substring(1);
                String val = null;
                if (flags.contains(opt)) {
                    val = "";
                } else if (scheme.contains(opt)) {
                    val = i + 1 < args.length ? args[++i] : "";
                } else {
                    badopts.add(args[i]);
                }
                this._options.put(opt, val);
                continue;
            }
            endargs.add(args[i]);
        }
        this._badopts = badopts.toArray(new String[badopts.size()]);
        this._args = endargs.toArray(new String[endargs.size()]);
    }

    public static void printLicense() {
        try {
            IOUtil.copyCompletely(new DefaultClassLoaderResourceLoader().getResourceAsStream("LICENSE.txt"), System.out);
        } catch (Exception e) {
            System.out.println("License available in this JAR in LICENSE.txt");
        }
    }

    public static void printVersion() {
        System.out.println(XmlBeans.getVendor() + ", " + XmlBeans.getTitle() + ".XmlBeans version " + XmlBeans.getVersion());
    }

    public String[] args() {
        String[] result = new String[this._args.length];
        System.arraycopy(this._args, 0, result, 0, this._args.length);
        return result;
    }

    public String[] getBadOpts() {
        return this._badopts;
    }

    public String getOpt(String opt) {
        return (String)this._options.get(opt);
    }

    private static List collectFiles(File[] dirs) {
        ArrayList<File> files = new ArrayList<File>();
        for (int i = 0; i < dirs.length; ++i) {
            File f = dirs[i];
            if (!f.isDirectory()) {
                files.add(f);
                continue;
            }
            files.addAll(CommandLine.collectFiles(f.listFiles()));
        }
        return files;
    }

    private List getFileList() {
        if (this._files == null) {
            String[] args = this.args();
            File[] files = new File[args.length];
            boolean noBaseDir = false;
            for (int i = 0; i < args.length; ++i) {
                files[i] = new File(args[i]);
                if (!noBaseDir && this._baseDir == null) {
                    if (files[i].isDirectory()) {
                        this._baseDir = files[i];
                        continue;
                    }
                    this._baseDir = files[i].getParentFile();
                    continue;
                }
                URI currUri = files[i].toURI();
                if (this._baseDir == null || !this._baseDir.toURI().relativize(currUri).equals(currUri)) continue;
                this._baseDir = null;
                noBaseDir = true;
            }
            this._files = Collections.unmodifiableList(CommandLine.collectFiles(files));
        }
        return this._files;
    }

    private List getUrlList() {
        if (this._urls == null) {
            String[] args = this.args();
            ArrayList<URL> urls = new ArrayList<URL>();
            for (int i = 0; i < args.length; ++i) {
                if (!CommandLine.looksLikeURL(args[i])) continue;
                try {
                    urls.add(new URL(args[i]));
                    continue;
                } catch (MalformedURLException mfEx) {
                    System.err.println("ignoring invalid url: " + args[i] + ": " + mfEx.getMessage());
                }
            }
            this._urls = Collections.unmodifiableList(urls);
        }
        return this._urls;
    }

    private static boolean looksLikeURL(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("ftp:") || str.startsWith("file:");
    }

    public URL[] getURLs() {
        return this.getUrlList().toArray(EMPTY_URLARRAY);
    }

    public File[] getFiles() {
        return this.getFileList().toArray(EMPTY_FILEARRAY);
    }

    public File getBaseDir() {
        return this._baseDir;
    }

    public File[] filesEndingWith(String ext) {
        ArrayList<File> result = new ArrayList<File>();
        for (File f : this.getFileList()) {
            if (!f.getName().endsWith(ext) || CommandLine.looksLikeURL(f.getPath())) continue;
            result.add(f);
        }
        return result.toArray(EMPTY_FILEARRAY);
    }
}

