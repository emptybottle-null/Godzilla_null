/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.java.ClassCompletion;
import org.fife.rsta.ac.java.JarReader;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.JarLibraryInfo;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.buildpath.SourceLocation;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.rjc.ast.ImportDeclaration;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JarManager {
    private List<JarReader> classFileSources = new ArrayList<JarReader>();
    private static boolean checkModified;

    public JarManager() {
        JarManager.setCheckModifiedDatestamps(true);
    }

    public void addCompletions(CompletionProvider p, String text, Set<Completion> addTo) {
        if (text.length() == 0) {
            return;
        }
        if (text.indexOf(46) > -1) {
            String[] pkgNames = Util.splitOnChar(text, 46);
            for (int i = 0; i < this.classFileSources.size(); ++i) {
                JarReader jar = this.classFileSources.get(i);
                jar.addCompletions(p, pkgNames, addTo);
            }
        } else {
            String lowerCaseText = text.toLowerCase();
            for (int i = 0; i < this.classFileSources.size(); ++i) {
                JarReader jar = this.classFileSources.get(i);
                List<ClassFile> classFiles = jar.getClassesWithNamesStartingWith(lowerCaseText);
                if (classFiles == null) continue;
                for (ClassFile cf : classFiles) {
                    if (!org.fife.rsta.ac.java.classreader.Util.isPublic(cf.getAccessFlags())) continue;
                    addTo.add(new ClassCompletion(p, cf));
                }
            }
        }
    }

    public boolean addClassFileSource(File jarFile) throws IOException {
        if (jarFile == null) {
            throw new IllegalArgumentException("jarFile cannot be null");
        }
        return this.addClassFileSource(new JarLibraryInfo(jarFile));
    }

    public boolean addClassFileSource(LibraryInfo info) throws IOException {
        if (info == null) {
            throw new IllegalArgumentException("info cannot be null");
        }
        for (int i = 0; i < this.classFileSources.size(); ++i) {
            JarReader jar = this.classFileSources.get(i);
            LibraryInfo info2 = jar.getLibraryInfo();
            if (!info2.equals(info)) continue;
            SourceLocation source = info.getSourceLocation();
            SourceLocation source2 = info2.getSourceLocation();
            if (source == null && source2 != null || source != null && !source.equals(source2)) {
                this.classFileSources.set(i, new JarReader((LibraryInfo)info.clone()));
                return true;
            }
            return false;
        }
        this.classFileSources.add(new JarReader(info));
        return true;
    }

    public void addCurrentJreClassFileSource() throws IOException {
        this.addClassFileSource(LibraryInfo.getMainJreJarInfo());
    }

    public void clearClassFileSources() {
        this.classFileSources.clear();
    }

    public static boolean getCheckModifiedDatestamps() {
        return checkModified;
    }

    public ClassFile getClassEntry(String className) {
        String[] items = Util.splitOnChar(className, 46);
        for (int i = 0; i < this.classFileSources.size(); ++i) {
            JarReader jar = this.classFileSources.get(i);
            ClassFile cf = jar.getClassEntry(items);
            if (cf == null) continue;
            return cf;
        }
        return null;
    }

    public List<ClassFile> getClassesWithUnqualifiedName(String name, List<ImportDeclaration> importDeclarations) {
        ArrayList<ClassFile> result = null;
        for (ImportDeclaration idec : importDeclarations) {
            if (idec.isStatic()) continue;
            if (idec.isWildcard()) {
                String qualified = idec.getName();
                qualified = qualified.substring(0, qualified.indexOf(42));
                ClassFile entry = this.getClassEntry(qualified = qualified + name);
                if (entry == null) continue;
                if (result == null) {
                    result = new ArrayList(1);
                }
                result.add(entry);
                continue;
            }
            String name2 = idec.getName();
            String unqualifiedName2 = name2.substring(name2.lastIndexOf(46) + 1);
            if (!unqualifiedName2.equals(name)) continue;
            ClassFile entry = this.getClassEntry(name2);
            if (entry != null) {
                if (result == null) {
                    result = new ArrayList(1);
                }
                result.add(entry);
                continue;
            }
            System.err.println("ERROR: Class not found! - " + name2);
        }
        String qualified = "java.lang." + name;
        ClassFile entry = this.getClassEntry(qualified);
        if (entry != null) {
            if (result == null) {
                result = new ArrayList<ClassFile>(1);
            }
            result.add(entry);
        }
        return result;
    }

    public List<ClassFile> getClassesInPackage(String pkgName, boolean inPkg) {
        ArrayList<ClassFile> list = new ArrayList<ClassFile>();
        String[] pkgs = Util.splitOnChar(pkgName, 46);
        for (int i = 0; i < this.classFileSources.size(); ++i) {
            JarReader jar = this.classFileSources.get(i);
            jar.getClassesInPackage(list, pkgs, inPkg);
        }
        return list;
    }

    public List<LibraryInfo> getClassFileSources() {
        ArrayList<LibraryInfo> jarList = new ArrayList<LibraryInfo>(this.classFileSources.size());
        for (JarReader reader : this.classFileSources) {
            jarList.add(reader.getLibraryInfo());
        }
        return jarList;
    }

    public SourceLocation getSourceLocForClass(String className) {
        SourceLocation sourceLoc = null;
        for (int i = 0; i < this.classFileSources.size(); ++i) {
            JarReader jar = this.classFileSources.get(i);
            if (!jar.containsClass(className)) continue;
            sourceLoc = jar.getLibraryInfo().getSourceLocation();
            break;
        }
        return sourceLoc;
    }

    public boolean removeClassFileSource(File jar) {
        return this.removeClassFileSource(new JarLibraryInfo(jar));
    }

    public boolean removeClassFileSource(LibraryInfo toRemove) {
        Iterator<JarReader> i = this.classFileSources.iterator();
        while (i.hasNext()) {
            JarReader reader = i.next();
            LibraryInfo info = reader.getLibraryInfo();
            if (!info.equals(toRemove)) continue;
            i.remove();
            return true;
        }
        return false;
    }

    public static void setCheckModifiedDatestamps(boolean check) {
        checkModified = check;
    }
}

