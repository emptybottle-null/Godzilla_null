/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public class DirectoryScanner {
    private boolean mCaseSensitive = true;
    private File mRoot;
    private JamLogger mLogger;
    private List mIncludeList = null;
    private List mExcludeList = null;
    private String[] mIncludes;
    private String[] mExcludes;
    private Vector mFilesIncluded;
    private Vector mDirsIncluded;
    private boolean mIsDirty = false;
    private String[] mIncludedFilesCache = null;

    public DirectoryScanner(File dirToScan, JamLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("null logger");
        }
        this.mLogger = logger;
        this.mRoot = dirToScan;
    }

    public void include(String pattern) {
        if (this.mIncludeList == null) {
            this.mIncludeList = new ArrayList();
        }
        this.mIncludeList.add(pattern);
        this.mIsDirty = true;
    }

    public void exclude(String pattern) {
        if (this.mExcludeList == null) {
            this.mExcludeList = new ArrayList();
        }
        this.mExcludeList.add(pattern);
        this.mIsDirty = true;
    }

    public String[] getIncludedFiles() throws IOException {
        if (!this.mIsDirty && this.mIncludedFilesCache != null) {
            return this.mIncludedFilesCache;
        }
        if (this.mIncludeList != null) {
            String[] inc = new String[this.mIncludeList.size()];
            this.mIncludeList.toArray(inc);
            this.setIncludes(inc);
        } else {
            this.setIncludes(null);
        }
        if (this.mExcludeList != null) {
            String[] exc = new String[this.mExcludeList.size()];
            this.mExcludeList.toArray(exc);
            this.setExcludes(exc);
        } else {
            this.setExcludes(null);
        }
        this.scan();
        this.mIncludedFilesCache = new String[this.mFilesIncluded.size()];
        this.mFilesIncluded.copyInto(this.mIncludedFilesCache);
        return this.mIncludedFilesCache;
    }

    public void setDirty() {
        this.mIsDirty = true;
    }

    public File getRoot() {
        return this.mRoot;
    }

    private void setIncludes(String[] includes) {
        if (includes == null) {
            this.mIncludes = null;
        } else {
            this.mIncludes = new String[includes.length];
            for (int i = 0; i < includes.length; ++i) {
                String pattern = includes[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
                if (pattern.endsWith(File.separator)) {
                    pattern = pattern + "**";
                }
                this.mIncludes[i] = pattern;
            }
        }
    }

    private void setExcludes(String[] excludes) {
        if (excludes == null) {
            this.mExcludes = null;
        } else {
            this.mExcludes = new String[excludes.length];
            for (int i = 0; i < excludes.length; ++i) {
                String pattern = excludes[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
                if (pattern.endsWith(File.separator)) {
                    pattern = pattern + "**";
                }
                this.mExcludes[i] = pattern;
            }
        }
    }

    private void scan() throws IllegalStateException, IOException {
        if (this.mIncludes == null) {
            this.mIncludes = new String[1];
            this.mIncludes[0] = "**";
        }
        if (this.mExcludes == null) {
            this.mExcludes = new String[0];
        }
        this.mFilesIncluded = new Vector();
        this.mDirsIncluded = new Vector();
        if (this.isIncluded("") && !this.isExcluded("")) {
            this.mDirsIncluded.addElement("");
        }
        this.scandir(this.mRoot, "", true);
    }

    private void scandir(File dir, String vpath, boolean fast) throws IOException {
        String[] newfiles;
        if (this.mLogger.isVerbose(this)) {
            this.mLogger.verbose("[DirectoryScanner] scanning dir " + dir + " for '" + vpath + "'");
        }
        if ((newfiles = dir.list()) == null) {
            throw new IOException("IO error scanning directory " + dir.getAbsolutePath());
        }
        for (int i = 0; i < newfiles.length; ++i) {
            String name = vpath + newfiles[i];
            File file = new File(dir, newfiles[i]);
            if (file.isDirectory()) {
                if (this.isIncluded(name) && !this.isExcluded(name)) {
                    this.mDirsIncluded.addElement(name);
                    if (this.mLogger.isVerbose(this)) {
                        this.mLogger.verbose("...including dir " + name);
                    }
                    this.scandir(file, name + File.separator, fast);
                    continue;
                }
                if (!this.couldHoldIncluded(name)) continue;
                this.scandir(file, name + File.separator, fast);
                continue;
            }
            if (!file.isFile() || !this.isIncluded(name)) continue;
            if (!this.isExcluded(name)) {
                this.mFilesIncluded.addElement(name);
                if (!this.mLogger.isVerbose(this)) continue;
                this.mLogger.verbose("...including " + name + " under '" + dir);
                continue;
            }
            if (!this.mLogger.isVerbose(this)) continue;
            this.mLogger.verbose("...EXCLUDING " + name + " under '" + dir);
        }
    }

    private boolean isIncluded(String name) {
        for (int i = 0; i < this.mIncludes.length; ++i) {
            if (!DirectoryScanner.matchPath(this.mIncludes[i], name, this.mCaseSensitive)) continue;
            return true;
        }
        return false;
    }

    private boolean couldHoldIncluded(String name) {
        for (int i = 0; i < this.mIncludes.length; ++i) {
            if (!DirectoryScanner.matchPatternStart(this.mIncludes[i], name, this.mCaseSensitive)) continue;
            return true;
        }
        return false;
    }

    private boolean isExcluded(String name) {
        for (int i = 0; i < this.mExcludes.length; ++i) {
            if (!DirectoryScanner.matchPath(this.mExcludes[i], name, this.mCaseSensitive)) continue;
            return true;
        }
        return false;
    }

    private static boolean matchPatternStart(String pattern, String str, boolean mCaseSensitive) {
        String patDir;
        int strIdxStart;
        if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
            return false;
        }
        Vector patDirs = DirectoryScanner.tokenizePath(pattern);
        Vector strDirs = DirectoryScanner.tokenizePath(str);
        int patIdxStart = 0;
        int patIdxEnd = patDirs.size() - 1;
        int strIdxEnd = strDirs.size() - 1;
        for (strIdxStart = 0; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd && !(patDir = (String)patDirs.elementAt(patIdxStart)).equals("**"); ++patIdxStart, ++strIdxStart) {
            if (DirectoryScanner.match(patDir, (String)strDirs.elementAt(strIdxStart), mCaseSensitive)) continue;
            return false;
        }
        if (strIdxStart > strIdxEnd) {
            return true;
        }
        return patIdxStart <= patIdxEnd;
    }

    private static boolean matchPath(String pattern, String str, boolean mCaseSensitive) {
        String patDir;
        int strIdxStart;
        if (str.startsWith(File.separator) != pattern.startsWith(File.separator)) {
            return false;
        }
        Vector patDirs = DirectoryScanner.tokenizePath(pattern);
        Vector strDirs = DirectoryScanner.tokenizePath(str);
        int patIdxStart = 0;
        int patIdxEnd = patDirs.size() - 1;
        int strIdxEnd = strDirs.size() - 1;
        for (strIdxStart = 0; patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd && !(patDir = (String)patDirs.elementAt(patIdxStart)).equals("**"); ++patIdxStart, ++strIdxStart) {
            if (DirectoryScanner.match(patDir, (String)strDirs.elementAt(strIdxStart), mCaseSensitive)) continue;
            return false;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (patDirs.elementAt(i).equals("**")) continue;
                return false;
            }
            return true;
        }
        if (patIdxStart > patIdxEnd) {
            return false;
        }
        while (patIdxStart <= patIdxEnd && strIdxStart <= strIdxEnd && !(patDir = (String)patDirs.elementAt(patIdxEnd)).equals("**")) {
            if (!DirectoryScanner.match(patDir, (String)strDirs.elementAt(strIdxEnd), mCaseSensitive)) {
                return false;
            }
            --patIdxEnd;
            --strIdxEnd;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; ++i) {
                if (patDirs.elementAt(i).equals("**")) continue;
                return false;
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int patIdxTmp = -1;
            for (int i = patIdxStart + 1; i <= patIdxEnd; ++i) {
                if (!patDirs.elementAt(i).equals("**")) continue;
                patIdxTmp = i;
                break;
            }
            if (patIdxTmp == patIdxStart + 1) {
                ++patIdxStart;
                continue;
            }
            int patLength = patIdxTmp - patIdxStart - 1;
            int strLength = strIdxEnd - strIdxStart + 1;
            int foundIdx = -1;
            block6: for (int i = 0; i <= strLength - patLength; ++i) {
                for (int j = 0; j < patLength; ++j) {
                    String subStr;
                    String subPat = (String)patDirs.elementAt(patIdxStart + j + 1);
                    if (!DirectoryScanner.match(subPat, subStr = (String)strDirs.elementAt(strIdxStart + i + j), mCaseSensitive)) continue block6;
                }
                foundIdx = strIdxStart + i;
                break;
            }
            if (foundIdx == -1) {
                return false;
            }
            patIdxStart = patIdxTmp;
            strIdxStart = foundIdx + patLength;
        }
        for (int i = patIdxStart; i <= patIdxEnd; ++i) {
            if (patDirs.elementAt(i).equals("**")) continue;
            return false;
        }
        return true;
    }

    private static boolean match(String pattern, String str, boolean mCaseSensitive) {
        char ch;
        int i;
        char[] patArr = pattern.toCharArray();
        char[] strArr = str.toCharArray();
        int patIdxStart = 0;
        int patIdxEnd = patArr.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strArr.length - 1;
        boolean containsStar = false;
        for (i = 0; i < patArr.length; ++i) {
            if (patArr[i] != '*') continue;
            containsStar = true;
            break;
        }
        if (!containsStar) {
            if (patIdxEnd != strIdxEnd) {
                return false;
            }
            for (i = 0; i <= patIdxEnd; ++i) {
                char ch2 = patArr[i];
                if (ch2 == '?') continue;
                if (mCaseSensitive && ch2 != strArr[i]) {
                    return false;
                }
                if (mCaseSensitive || Character.toUpperCase(ch2) == Character.toUpperCase(strArr[i])) continue;
                return false;
            }
            return true;
        }
        if (patIdxEnd == 0) {
            return true;
        }
        while ((ch = patArr[patIdxStart]) != '*' && strIdxStart <= strIdxEnd) {
            if (ch != '?') {
                if (mCaseSensitive && ch != strArr[strIdxStart]) {
                    return false;
                }
                if (!mCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxStart])) {
                    return false;
                }
            }
            ++patIdxStart;
            ++strIdxStart;
        }
        if (strIdxStart > strIdxEnd) {
            for (i = patIdxStart; i <= patIdxEnd; ++i) {
                if (patArr[i] == '*') continue;
                return false;
            }
            return true;
        }
        while ((ch = patArr[patIdxEnd]) != '*' && strIdxStart <= strIdxEnd) {
            if (ch != '?') {
                if (mCaseSensitive && ch != strArr[strIdxEnd]) {
                    return false;
                }
                if (!mCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxEnd])) {
                    return false;
                }
            }
            --patIdxEnd;
            --strIdxEnd;
        }
        if (strIdxStart > strIdxEnd) {
            for (i = patIdxStart; i <= patIdxEnd; ++i) {
                if (patArr[i] == '*') continue;
                return false;
            }
            return true;
        }
        while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd) {
            int patIdxTmp = -1;
            for (int i2 = patIdxStart + 1; i2 <= patIdxEnd; ++i2) {
                if (patArr[i2] != '*') continue;
                patIdxTmp = i2;
                break;
            }
            if (patIdxTmp == patIdxStart + 1) {
                ++patIdxStart;
                continue;
            }
            int patLength = patIdxTmp - patIdxStart - 1;
            int strLength = strIdxEnd - strIdxStart + 1;
            int foundIdx = -1;
            block8: for (int i3 = 0; i3 <= strLength - patLength; ++i3) {
                for (int j = 0; j < patLength; ++j) {
                    ch = patArr[patIdxStart + j + 1];
                    if (ch != '?' && (mCaseSensitive && ch != strArr[strIdxStart + i3 + j] || !mCaseSensitive && Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxStart + i3 + j]))) continue block8;
                }
                foundIdx = strIdxStart + i3;
                break;
            }
            if (foundIdx == -1) {
                return false;
            }
            patIdxStart = patIdxTmp;
            strIdxStart = foundIdx + patLength;
        }
        for (i = patIdxStart; i <= patIdxEnd; ++i) {
            if (patArr[i] == '*') continue;
            return false;
        }
        return true;
    }

    private static Vector tokenizePath(String path) {
        Vector<String> ret = new Vector<String>();
        StringTokenizer st = new StringTokenizer(path, File.separator);
        while (st.hasMoreTokens()) {
            ret.addElement(st.nextToken());
        }
        return ret;
    }
}

