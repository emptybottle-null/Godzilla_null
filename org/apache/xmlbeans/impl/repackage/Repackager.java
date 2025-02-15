/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Repackager {
    private List _fromPackages = new ArrayList();
    private List _toPackages = new ArrayList();
    private Matcher[] _fromMatchers;
    private String[] _toPackageNames;

    public Repackager(String repackageSpecs) {
        boolean swapped;
        ArrayList repackages = Repackager.splitPath(repackageSpecs, ';');
        do {
            swapped = false;
            for (int i = 1; i < repackages.size(); ++i) {
                String spec1 = (String)repackages.get(i - 1);
                String spec2 = (String)repackages.get(i);
                if (spec1.indexOf(58) >= spec2.indexOf(58)) continue;
                repackages.set(i - 1, spec2);
                repackages.set(i, spec1);
                swapped = true;
            }
        } while (swapped);
        for (int i = 0; i < repackages.size(); ++i) {
            String spec = (String)repackages.get(i);
            int j = spec.indexOf(58);
            if (j < 0 || spec.indexOf(58, j + 1) >= 0) {
                throw new RuntimeException("Illegal repackage specification: " + spec);
            }
            String from = spec.substring(0, j);
            String to = spec.substring(j + 1);
            this._fromPackages.add(Repackager.splitPath(from, '.'));
            this._toPackages.add(Repackager.splitPath(to, '.'));
        }
        this._fromMatchers = new Matcher[this._fromPackages.size() * 2];
        this._toPackageNames = new String[this._fromPackages.size() * 2];
        this.addPatterns('.', 0);
        this.addPatterns('/', this._fromPackages.size());
    }

    void addPatterns(char sep, int off) {
        for (int i = 0; i < this._fromPackages.size(); ++i) {
            List from = (List)this._fromPackages.get(i);
            List to = (List)this._toPackages.get(i);
            String pattern = "";
            for (int j = 0; j < from.size(); ++j) {
                if (j > 0) {
                    pattern = pattern + "\\" + sep;
                }
                pattern = pattern + from.get(j);
            }
            String toPackage = "";
            for (int j = 0; j < to.size(); ++j) {
                if (j > 0) {
                    toPackage = toPackage + sep;
                }
                toPackage = toPackage + to.get(j);
            }
            this._fromMatchers[off + i] = Pattern.compile(pattern).matcher("");
            this._toPackageNames[off + i] = toPackage;
        }
    }

    public StringBuffer repackage(StringBuffer sb) {
        StringBuffer result = null;
        for (int i = 0; i < this._fromMatchers.length; ++i) {
            Matcher m = this._fromMatchers[i];
            m.reset(sb);
            boolean found = m.find();
            while (found) {
                if (result == null) {
                    result = new StringBuffer();
                }
                m.appendReplacement(result, this._toPackageNames[i]);
                found = m.find();
            }
            if (result == null) continue;
            m.appendTail(result);
            sb = result;
            result = null;
        }
        return sb;
    }

    public List getFromPackages() {
        return this._fromPackages;
    }

    public List getToPackages() {
        return this._toPackages;
    }

    public static ArrayList splitPath(String path, char separator) {
        int i;
        ArrayList<String> components = new ArrayList<String>();
        while ((i = path.indexOf(separator)) >= 0) {
            components.add(path.substring(0, i));
            path = path.substring(i + 1);
        }
        if (path.length() > 0) {
            components.add(path);
        }
        return components;
    }

    public static String dirForPath(String path) {
        return new File(path).getParent();
    }
}

