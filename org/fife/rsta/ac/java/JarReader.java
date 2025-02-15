/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.fife.rsta.ac.java.PackageMapNode;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

class JarReader {
    private LibraryInfo info;
    private PackageMapNode packageMap;
    private long lastModified;

    public JarReader(LibraryInfo info) throws IOException {
        this.info = info;
        this.packageMap = new PackageMapNode();
        this.loadCompletions();
    }

    public void addCompletions(CompletionProvider provider, String[] pkgNames, Set<Completion> addTo) {
        this.checkLastModified();
        this.packageMap.addCompletions(this.info, provider, pkgNames, addTo);
    }

    private void checkLastModified() {
        long newLastModified = this.info.getLastModified();
        if (newLastModified != 0L && newLastModified != this.lastModified) {
            int count = this.packageMap.clearClassFiles();
            System.out.println("DEBUG: Cleared " + count + " cached ClassFiles");
            this.lastModified = newLastModified;
        }
    }

    public boolean containsClass(String className) {
        return this.packageMap.containsClass(className);
    }

    public boolean containsPackage(String pkgName) {
        return this.packageMap.containsPackage(pkgName);
    }

    public ClassFile getClassEntry(String[] items) {
        return this.packageMap.getClassEntry(this.info, items);
    }

    public void getClassesInPackage(List<ClassFile> addTo, String[] pkgs, boolean inPkg) {
        this.packageMap.getClassesInPackage(this.info, addTo, pkgs, inPkg);
    }

    public List<ClassFile> getClassesWithNamesStartingWith(String prefix) {
        ArrayList<ClassFile> res = new ArrayList<ClassFile>();
        String currentPkg = "";
        this.packageMap.getClassesWithNamesStartingWith(this.info, prefix, currentPkg, res);
        return res;
    }

    public LibraryInfo getLibraryInfo() {
        return (LibraryInfo)this.info.clone();
    }

    private void loadCompletions() throws IOException {
        this.packageMap = this.info.createPackageMap();
        this.lastModified = this.info.getLastModified();
    }

    public String toString() {
        return "[JarReader: " + this.getLibraryInfo() + "]";
    }
}

