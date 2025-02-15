/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.xmlbeans.impl.repackage.Repackager;

public class Repackage {
    private File _sourceBase;
    private File _targetBase;
    private List _fromPackages;
    private List _toPackages;
    private Pattern _packagePattern;
    private Repackager _repackager;
    private Map _movedDirs;
    private List _moveAlongFiles;
    private int _skippedFiles;

    public static void main(String[] args) throws Exception {
        new Repackage(args).repackage();
    }

    private Repackage(String[] args) {
        String sourceDir = null;
        String targetDir = null;
        String repackageSpec = null;
        boolean failure = false;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-repackage") && i + 1 < args.length) {
                repackageSpec = args[++i];
                continue;
            }
            if (args[i].equals("-f") && i + 1 < args.length) {
                sourceDir = args[++i];
                continue;
            }
            if (args[i].equals("-t") && i + 1 < args.length) {
                targetDir = args[++i];
                continue;
            }
            failure = true;
        }
        if (failure || repackageSpec == null || sourceDir == null ^ targetDir == null) {
            throw new RuntimeException("Usage: repackage -repackage [spec] [ -f [sourcedir] -t [targetdir] ]");
        }
        this._repackager = new Repackager(repackageSpec);
        if (sourceDir == null || targetDir == null) {
            return;
        }
        this._sourceBase = new File(sourceDir);
        this._targetBase = new File(targetDir);
    }

    public void repackage() throws Exception {
        if (this._sourceBase == null || this._targetBase == null) {
            System.out.println(this._repackager.repackage(this.readInputStream(System.in)).toString());
            return;
        }
        this._fromPackages = this._repackager.getFromPackages();
        this._toPackages = this._repackager.getToPackages();
        this._packagePattern = Pattern.compile("^\\s*package\\s+((?:\\w|\\.)*)\\s*;", 8);
        this._moveAlongFiles = new ArrayList();
        this._movedDirs = new HashMap();
        this._targetBase.mkdirs();
        ArrayList files = new ArrayList();
        this.fillFiles(files, this._sourceBase);
        System.out.println("Repackaging " + files.size() + " files ...");
        int prefixLength = this._sourceBase.getCanonicalPath().length();
        for (int i = 0; i < files.size(); ++i) {
            File from = (File)files.get(i);
            String name = from.getCanonicalPath().substring(prefixLength + 1);
            this.repackageFile(name);
        }
        this.finishMovingFiles();
        if (this._skippedFiles > 0) {
            System.out.println("Skipped " + this._skippedFiles + " unmodified files.");
        }
    }

    private boolean fileIsUnchanged(String name) {
        File sourceFile = new File(this._sourceBase, name);
        File targetFile = new File(this._targetBase, name);
        return sourceFile.lastModified() < targetFile.lastModified();
    }

    public void repackageFile(String name) throws IOException {
        if (name.endsWith(".java")) {
            this.repackageJavaFile(name);
        } else if (name.endsWith(".xsdconfig") || name.endsWith(".xml") || name.endsWith(".g")) {
            this.repackageNonJavaFile(name);
        } else if (name.startsWith("bin" + File.separatorChar)) {
            this.repackageNonJavaFile(name);
        } else {
            this.moveAlongWithJavaFiles(name);
        }
    }

    public void moveAlongWithJavaFiles(String name) {
        this._moveAlongFiles.add(name);
    }

    public void finishMovingFiles() throws IOException {
        Iterator i = this._moveAlongFiles.iterator();
        while (i.hasNext()) {
            String name;
            String toName = name = (String)i.next();
            String srcDir = Repackager.dirForPath(name);
            String toDir = (String)this._movedDirs.get(srcDir);
            if (toDir != null) {
                toName = new File(toDir, new File(name).getName()).toString();
            }
            if (name.endsWith(".html")) {
                this.repackageNonJavaFile(name, toName);
                continue;
            }
            this.justMoveNonJavaFile(name, toName);
        }
    }

    public void repackageNonJavaFile(String name) throws IOException {
        File sourceFile = new File(this._sourceBase, name);
        File targetFile = new File(this._targetBase, name);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            ++this._skippedFiles;
        } else {
            this.writeFile(targetFile, this._repackager.repackage(this.readFile(sourceFile)));
        }
    }

    public void repackageNonJavaFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(this._sourceBase, sourceName);
        File targetFile = new File(this._targetBase, targetName);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            ++this._skippedFiles;
        } else {
            this.writeFile(targetFile, this._repackager.repackage(this.readFile(sourceFile)));
        }
    }

    public void justMoveNonJavaFile(String sourceName, String targetName) throws IOException {
        File sourceFile = new File(this._sourceBase, sourceName);
        File targetFile = new File(this._targetBase, targetName);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            ++this._skippedFiles;
        } else {
            Repackage.copyFile(sourceFile, targetFile);
        }
    }

    public void repackageJavaFile(String name) throws IOException {
        File sourceFile = new File(this._sourceBase, name);
        StringBuffer sb = this.readFile(sourceFile);
        Matcher packageMatcher = this._packagePattern.matcher(sb);
        if (packageMatcher.find()) {
            boolean swapped;
            String pkg = packageMatcher.group(1);
            int pkgStart = packageMatcher.start(1);
            int pkgEnd = packageMatcher.end(1);
            if (packageMatcher.find()) {
                throw new RuntimeException("Two package specifications found: " + name);
            }
            ArrayList filePath = Repackager.splitPath(name, File.separatorChar);
            String srcDir = Repackager.dirForPath(name);
            do {
                swapped = false;
                for (int i = 1; i < filePath.size(); ++i) {
                    String spec1 = (String)filePath.get(i - 1);
                    String spec2 = (String)filePath.get(i);
                    if (spec1.indexOf(58) >= spec2.indexOf(58)) continue;
                    filePath.set(i - 1, spec2);
                    filePath.set(i, spec1);
                    swapped = true;
                }
            } while (swapped);
            ArrayList pkgPath = Repackager.splitPath(pkg, '.');
            int f = filePath.size() - 2;
            if (f < 0 || filePath.size() - 1 < pkgPath.size()) {
                throw new RuntimeException("Package spec differs from file path: " + name);
            }
            for (int i = pkgPath.size() - 1; i >= 0; --i) {
                if (!pkgPath.get(i).equals(filePath.get(f))) {
                    throw new RuntimeException("Package spec differs from file path: " + name);
                }
                --f;
            }
            List changeTo = null;
            List changeFrom = null;
            block3: for (int i = 0; i < this._fromPackages.size(); ++i) {
                List from = (List)this._fromPackages.get(i);
                if (from.size() > pkgPath.size()) continue;
                for (int j = 0; j < from.size(); ++j) {
                    if (!from.get(j).equals(pkgPath.get(j))) continue block3;
                }
                changeFrom = from;
                changeTo = (List)this._toPackages.get(i);
                break;
            }
            if (changeTo != null) {
                int i;
                String newPkg = "";
                String newName = "";
                for (i = 0; i < changeTo.size(); ++i) {
                    if (i > 0) {
                        newPkg = newPkg + ".";
                        newName = newName + File.separatorChar;
                    }
                    newPkg = newPkg + changeTo.get(i);
                    newName = newName + changeTo.get(i);
                }
                for (i = filePath.size() - pkgPath.size() - 2; i >= 0; --i) {
                    newName = (String)filePath.get(i) + File.separatorChar + newName;
                }
                for (i = changeFrom.size(); i < pkgPath.size(); ++i) {
                    newName = newName + File.separatorChar + (String)pkgPath.get(i);
                    newPkg = newPkg + '.' + (String)pkgPath.get(i);
                }
                newName = newName + File.separatorChar + (String)filePath.get(filePath.size() - 1);
                sb.replace(pkgStart, pkgEnd, newPkg);
                name = newName;
                String newDir = Repackager.dirForPath(name);
                if (!srcDir.equals(newDir)) {
                    this._movedDirs.put(srcDir, newDir);
                }
            }
        }
        File targetFile = new File(this._targetBase, name);
        if (sourceFile.lastModified() < targetFile.lastModified()) {
            ++this._skippedFiles;
            return;
        }
        this.writeFile(new File(this._targetBase, name), this._repackager.repackage(sb));
    }

    void writeFile(File f, StringBuffer chars) throws IOException {
        f.getParentFile().mkdirs();
        FileOutputStream out = new FileOutputStream(f);
        OutputStreamWriter w = new OutputStreamWriter(out);
        StringReader r = new StringReader(chars.toString());
        Repackage.copy(r, w);
        ((Reader)r).close();
        ((Writer)w).close();
        ((OutputStream)out).close();
    }

    StringBuffer readFile(File f) throws IOException {
        FileInputStream in = new FileInputStream(f);
        InputStreamReader r = new InputStreamReader(in);
        StringWriter w = new StringWriter();
        Repackage.copy(r, w);
        w.close();
        ((Reader)r).close();
        ((InputStream)in).close();
        return w.getBuffer();
    }

    StringBuffer readInputStream(InputStream is) throws IOException {
        InputStreamReader r = new InputStreamReader(is);
        StringWriter w = new StringWriter();
        Repackage.copy(r, w);
        w.close();
        ((Reader)r).close();
        return w.getBuffer();
    }

    public static void copyFile(File from, File to) throws IOException {
        to.getParentFile().mkdirs();
        FileInputStream in = new FileInputStream(from);
        FileOutputStream out = new FileOutputStream(to);
        Repackage.copy(in, out);
        out.close();
        in.close();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        int n;
        byte[] buffer = new byte[16384];
        while ((n = in.read(buffer, 0, buffer.length)) >= 0) {
            out.write(buffer, 0, n);
        }
    }

    public static void copy(Reader r, Writer w) throws IOException {
        int n;
        char[] buffer = new char[16384];
        while ((n = r.read(buffer, 0, buffer.length)) >= 0) {
            w.write(buffer, 0, n);
        }
    }

    public void fillFiles(ArrayList files, File file) throws IOException {
        if (!file.isDirectory()) {
            files.add(file);
            return;
        }
        if (file.getName().equals("build")) {
            return;
        }
        if (file.getName().equals("CVS")) {
            return;
        }
        String[] entries = file.list();
        for (int i = 0; i < entries.length; ++i) {
            this.fillFiles(files, new File(file, entries[i]));
        }
    }

    public void recursiveDelete(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            String[] entries = file.list();
            for (int i = 0; i < entries.length; ++i) {
                this.recursiveDelete(new File(file, entries[i]));
            }
        }
        file.delete();
    }
}

