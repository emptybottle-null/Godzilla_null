/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.fife.rsta.ac.java.ClassCompletion;
import org.fife.rsta.ac.java.PackageNameCompletion;
import org.fife.rsta.ac.java.Util;
import org.fife.rsta.ac.java.buildpath.LibraryInfo;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public class PackageMapNode {
    private SortedMap<String, PackageMapNode> subpackages = new TreeMap<String, PackageMapNode>(String.CASE_INSENSITIVE_ORDER);
    private SortedMap<String, ClassFile> classFiles = new TreeMap<String, ClassFile>(String.CASE_INSENSITIVE_ORDER);

    public void add(String className) {
        String[] tokens = Util.splitOnChar(className, 47);
        PackageMapNode pmn = this;
        for (int i = 0; i < tokens.length - 1; ++i) {
            String pkg = tokens[i];
            PackageMapNode child = (PackageMapNode)pmn.subpackages.get(pkg);
            if (child == null) {
                child = new PackageMapNode();
                pmn.subpackages.put(pkg, child);
            }
            pmn = child;
        }
        className = tokens[tokens.length - 1];
        className = className.substring(0, className.length() - 6);
        pmn.classFiles.put(className, null);
    }

    public void addCompletions(LibraryInfo info, CompletionProvider provider, String[] pkgNames, Set<Completion> addTo) {
        PackageMapNode map = this;
        for (int i = 0; i < pkgNames.length - 1; ++i) {
            map = (PackageMapNode)map.subpackages.get(pkgNames[i]);
            if (map != null) continue;
            return;
        }
        String fromKey = pkgNames[pkgNames.length - 1];
        String toKey = fromKey + '{';
        SortedMap<String, PackageMapNode> subpackages = map.subpackages.subMap(fromKey, toKey);
        if (!subpackages.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < pkgNames.length - 1; ++j) {
                sb.append(pkgNames[j]).append('.');
            }
            String earlierPackages = sb.toString();
            for (Map.Entry<String, PackageMapNode> entry : subpackages.entrySet()) {
                String completionPackageName = entry.getKey();
                String text = earlierPackages + completionPackageName;
                addTo.add(new PackageNameCompletion(provider, text, fromKey));
            }
        }
        SortedMap<String, ClassFile> sm = map.classFiles.subMap(fromKey, toKey);
        for (Map.Entry<String, ClassFile> entry : sm.entrySet()) {
            boolean inPkg;
            String key = entry.getKey();
            ClassFile cf = entry.getValue();
            if (cf != null) {
                boolean inPkg2 = false;
                if (!inPkg2 && !org.fife.rsta.ac.java.classreader.Util.isPublic(cf.getAccessFlags())) continue;
                addTo.add(new ClassCompletion(provider, cf));
                continue;
            }
            String[] items = new String[pkgNames.length];
            System.arraycopy(pkgNames, 0, items, 0, pkgNames.length - 1);
            items[items.length - 1] = key;
            cf = this.getClassEntry(info, items);
            if (cf == null || !(inPkg = false) && !org.fife.rsta.ac.java.classreader.Util.isPublic(cf.getAccessFlags())) continue;
            addTo.add(new ClassCompletion(provider, cf));
        }
    }

    public int clearClassFiles() {
        return this.clearClassFilesImpl(this);
    }

    private int clearClassFilesImpl(PackageMapNode pmn) {
        int clearedCount = 0;
        for (Map.Entry<String, ClassFile> entry : pmn.classFiles.entrySet()) {
            entry.setValue(null);
            ++clearedCount;
        }
        for (Map.Entry<String, Object> entry : pmn.subpackages.entrySet()) {
            clearedCount += this.clearClassFilesImpl((PackageMapNode)entry.getValue());
        }
        return clearedCount;
    }

    public boolean containsClass(String className) {
        String[] items = className.split("\\.");
        PackageMapNode pmn = this;
        for (int i = 0; i < items.length - 1; ++i) {
            pmn = (PackageMapNode)pmn.subpackages.get(items[i]);
            if (pmn != null) continue;
            return false;
        }
        return pmn.classFiles.containsKey(items[items.length - 1]);
    }

    public boolean containsPackage(String pkgName) {
        String[] items = Util.splitOnChar(pkgName, 46);
        PackageMapNode pmn = this;
        for (int i = 0; i < items.length; ++i) {
            pmn = (PackageMapNode)pmn.subpackages.get(items[i]);
            if (pmn != null) continue;
            return false;
        }
        return true;
    }

    public ClassFile getClassEntry(LibraryInfo info, String[] items) {
        PackageMapNode pmn = this;
        for (int i = 0; i < items.length - 1; ++i) {
            pmn = (PackageMapNode)pmn.subpackages.get(items[i]);
            if (pmn != null) continue;
            return null;
        }
        String className = items[items.length - 1];
        if (pmn.classFiles.containsKey(className)) {
            ClassFile value = (ClassFile)pmn.classFiles.get(className);
            if (value != null) {
                return value;
            }
            try {
                StringBuilder name = new StringBuilder(items[0]);
                for (int i = 1; i < items.length; ++i) {
                    name.append('/').append(items[i]);
                }
                name.append(".class");
                ClassFile cf = info.createClassFile(name.toString());
                pmn.classFiles.put(className, cf);
                return cf;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void getClassesInPackage(LibraryInfo info, List<ClassFile> addTo, String[] pkgs, boolean inPkg) {
        PackageMapNode map = this;
        for (int i = 0; i < pkgs.length; ++i) {
            map = (PackageMapNode)map.subpackages.get(pkgs[i]);
            if (map != null) continue;
            return;
        }
        try {
            info.bulkClassFileCreationStart();
            try {
                for (Map.Entry<String, ClassFile> entry : map.classFiles.entrySet()) {
                    ClassFile cf = entry.getValue();
                    if (cf == null) {
                        StringBuilder name = new StringBuilder(pkgs[0]);
                        for (int j = 1; j < pkgs.length; ++j) {
                            name.append('/').append(pkgs[j]);
                        }
                        name.append('/');
                        name.append(entry.getKey()).append(".class");
                        cf = info.createClassFileBulk(name.toString());
                        map.classFiles.put(entry.getKey(), cf);
                        PackageMapNode.possiblyAddTo(addTo, cf, inPkg);
                        continue;
                    }
                    PackageMapNode.possiblyAddTo(addTo, cf, inPkg);
                }
            } finally {
                info.bulkClassFileCreationEnd();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void getClassesWithNamesStartingWith(LibraryInfo info, String prefix, String currentPkg, List<ClassFile> addTo) {
        int prefixLen = prefix.length();
        for (Map.Entry<String, PackageMapNode> entry : this.subpackages.entrySet()) {
            String key = entry.getKey();
            PackageMapNode child = entry.getValue();
            child.getClassesWithNamesStartingWith(info, prefix, currentPkg + key + "/", addTo);
        }
        for (Map.Entry<String, Object> entry : this.classFiles.entrySet()) {
            String className = entry.getKey();
            if (!className.regionMatches(true, 0, prefix, 0, prefixLen)) continue;
            ClassFile cf = (ClassFile)entry.getValue();
            if (cf == null) {
                String fqClassName = currentPkg + className + ".class";
                try {
                    cf = info.createClassFile(fqClassName);
                    entry.setValue(cf);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (cf == null) continue;
            addTo.add(cf);
        }
    }

    private static final void possiblyAddTo(Collection<ClassFile> addTo, ClassFile cf, boolean inPkg) {
        if (inPkg || org.fife.rsta.ac.java.classreader.Util.isPublic(cf.getAccessFlags())) {
            addTo.add(cf);
        }
    }
}

