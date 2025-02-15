/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.JamServiceParams;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.annotation.DefaultAnnotationProxy;
import org.apache.xmlbeans.impl.jam.annotation.JavadocTagParser;
import org.apache.xmlbeans.impl.jam.annotation.WhitespaceDelimitedTagParser;
import org.apache.xmlbeans.impl.jam.internal.DirectoryScanner;
import org.apache.xmlbeans.impl.jam.internal.JamLoggerImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.provider.CompositeJamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;
import org.apache.xmlbeans.impl.jam.provider.JamServiceContext;
import org.apache.xmlbeans.impl.jam.provider.ResourcePath;
import org.apache.xmlbeans.impl.jam.visitor.CompositeMVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;
import org.apache.xmlbeans.impl.jam.visitor.PropertyInitializer;

public class JamServiceContextImpl
extends JamLoggerImpl
implements JamServiceContext,
JamServiceParams,
ElementContext {
    private static final char INNER_CLASS_SEPARATOR = '$';
    private boolean m14WarningsEnabled = false;
    private Properties mProperties = null;
    private Map mSourceRoot2Scanner = null;
    private Map mClassRoot2Scanner = null;
    private List mClasspath = null;
    private List mSourcepath = null;
    private List mToolClasspath = null;
    private List mIncludeClasses = null;
    private List mExcludeClasses = null;
    private boolean mUseSystemClasspath = true;
    private JavadocTagParser mTagParser = null;
    private MVisitor mCommentInitializer = null;
    private MVisitor mPropertyInitializer = new PropertyInitializer();
    private List mOtherInitializers = null;
    private List mUnstructuredSourceFiles = null;
    private List mClassLoaders = null;
    private List mBaseBuilders = null;
    private JamClassLoader mLoader = null;
    private static final String PREFIX = "[JamServiceContextImpl] ";

    public void setClassLoader(JamClassLoader loader) {
        this.mLoader = loader;
    }

    @Override
    public JamClassBuilder getBaseBuilder() {
        if (this.mBaseBuilders == null || this.mBaseBuilders.size() == 0) {
            return null;
        }
        if (this.mBaseBuilders.size() == 1) {
            return (JamClassBuilder)this.mBaseBuilders.get(0);
        }
        JamClassBuilder[] comp = new JamClassBuilder[this.mBaseBuilders.size()];
        this.mBaseBuilders.toArray(comp);
        return new CompositeJamClassBuilder(comp);
    }

    @Override
    public JavadocTagParser getTagParser() {
        if (this.mTagParser == null) {
            this.mTagParser = new WhitespaceDelimitedTagParser();
            this.mTagParser.init(this);
        }
        return this.mTagParser;
    }

    @Override
    public String[] getAllClassnames() throws IOException {
        HashSet<String> all = new HashSet<String>();
        if (this.mIncludeClasses != null) {
            all.addAll(this.mIncludeClasses);
        }
        Iterator i = this.getAllDirectoryScanners();
        while (i.hasNext()) {
            DirectoryScanner ds = (DirectoryScanner)i.next();
            String[] files = ds.getIncludedFiles();
            for (int j = 0; j < files.length; ++j) {
                if (files[j].indexOf(36) != -1) continue;
                all.add(JamServiceContextImpl.filename2classname(files[j]));
            }
        }
        if (this.mExcludeClasses != null) {
            all.removeAll(this.mExcludeClasses);
        }
        String[] out = new String[all.size()];
        all.toArray(out);
        return out;
    }

    @Override
    public JamLogger getLogger() {
        return this;
    }

    @Override
    public File[] getSourceFiles() throws IOException {
        HashSet<File> set = new HashSet<File>();
        if (this.mSourceRoot2Scanner != null) {
            for (DirectoryScanner ds : this.mSourceRoot2Scanner.values()) {
                if (this.isVerbose(this)) {
                    this.verbose("[JamServiceContextImpl]  checking scanner for dir" + ds.getRoot());
                }
                String[] files = ds.getIncludedFiles();
                for (int j = 0; j < files.length; ++j) {
                    if (this.isVerbose(this)) {
                        this.verbose("[JamServiceContextImpl]  ...including a source file " + files[j]);
                    }
                    set.add(new File(ds.getRoot(), files[j]));
                }
            }
        }
        if (this.mUnstructuredSourceFiles != null) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] adding " + this.mUnstructuredSourceFiles.size() + " other source files");
            }
            set.addAll(this.mUnstructuredSourceFiles);
        }
        File[] out = new File[set.size()];
        set.toArray(out);
        return out;
    }

    public File[] getUnstructuredSourceFiles() {
        if (this.mUnstructuredSourceFiles == null) {
            return null;
        }
        File[] out = new File[this.mUnstructuredSourceFiles.size()];
        this.mUnstructuredSourceFiles.toArray(out);
        return out;
    }

    @Override
    public ResourcePath getInputClasspath() {
        return JamServiceContextImpl.createJPath(this.mClasspath);
    }

    @Override
    public ResourcePath getInputSourcepath() {
        return JamServiceContextImpl.createJPath(this.mSourcepath);
    }

    @Override
    public ResourcePath getToolClasspath() {
        return JamServiceContextImpl.createJPath(this.mToolClasspath);
    }

    @Override
    public String getProperty(String name) {
        return this.mProperties == null ? null : this.mProperties.getProperty(name);
    }

    @Override
    public MVisitor getInitializer() {
        ArrayList<MVisitor> initers = new ArrayList<MVisitor>();
        if (this.mCommentInitializer != null) {
            initers.add(this.mCommentInitializer);
        }
        if (this.mPropertyInitializer != null) {
            initers.add(this.mPropertyInitializer);
        }
        if (this.mOtherInitializers != null) {
            initers.addAll(this.mOtherInitializers);
        }
        MVisitor[] inits = new MVisitor[initers.size()];
        initers.toArray(inits);
        return new CompositeMVisitor(inits);
    }

    @Override
    public void addClassBuilder(JamClassBuilder builder) {
        if (this.mBaseBuilders == null) {
            this.mBaseBuilders = new ArrayList();
        }
        this.mBaseBuilders.add(builder);
    }

    public void setCommentInitializer(MVisitor initializer) {
        this.mCommentInitializer = initializer;
    }

    @Override
    public void setPropertyInitializer(MVisitor initializer) {
        this.mPropertyInitializer = initializer;
    }

    @Override
    public void addInitializer(MVisitor initializer) {
        if (this.mOtherInitializers == null) {
            this.mOtherInitializers = new ArrayList();
        }
        this.mOtherInitializers.add(initializer);
    }

    @Override
    public void setJavadocTagParser(JavadocTagParser tp) {
        this.mTagParser = tp;
        tp.init(this);
    }

    @Override
    public void includeSourceFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("null file");
        }
        file = file.getAbsoluteFile();
        if (this.isVerbose(this)) {
            this.verbose("[JamServiceContextImpl] adding source ");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException(file + " cannot be included as a source file because it is a directory.");
        }
        if (this.mUnstructuredSourceFiles == null) {
            this.mUnstructuredSourceFiles = new ArrayList();
        }
        this.mUnstructuredSourceFiles.add(file.getAbsoluteFile());
    }

    @Override
    public void includeSourcePattern(File[] sourcepath, String pattern) {
        if (sourcepath == null) {
            throw new IllegalArgumentException("null sourcepath");
        }
        if (sourcepath.length == 0) {
            throw new IllegalArgumentException("empty sourcepath");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern");
        }
        if ((pattern = pattern.trim()).length() == 0) {
            throw new IllegalArgumentException("empty pattern");
        }
        for (int i = 0; i < sourcepath.length; ++i) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] including '" + pattern + "' under " + sourcepath[i]);
            }
            this.addSourcepath(sourcepath[i]);
            this.getSourceScanner(sourcepath[i]).include(pattern);
        }
    }

    @Override
    public void includeClassPattern(File[] classpath, String pattern) {
        if (classpath == null) {
            throw new IllegalArgumentException("null classpath");
        }
        if (classpath.length == 0) {
            throw new IllegalArgumentException("empty classpath");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern");
        }
        if ((pattern = pattern.trim()).length() == 0) {
            throw new IllegalArgumentException("empty pattern");
        }
        for (int i = 0; i < classpath.length; ++i) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] including '" + pattern + "' under " + classpath[i]);
            }
            this.addClasspath(classpath[i]);
            this.getClassScanner(classpath[i]).include(pattern);
        }
    }

    @Override
    public void excludeSourcePattern(File[] sourcepath, String pattern) {
        if (sourcepath == null) {
            throw new IllegalArgumentException("null sourcepath");
        }
        if (sourcepath.length == 0) {
            throw new IllegalArgumentException("empty sourcepath");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern");
        }
        if ((pattern = pattern.trim()).length() == 0) {
            throw new IllegalArgumentException("empty pattern");
        }
        for (int i = 0; i < sourcepath.length; ++i) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] EXCLUDING '" + pattern + "' under " + sourcepath[i]);
            }
            this.addSourcepath(sourcepath[i]);
            this.getSourceScanner(sourcepath[i]).exclude(pattern);
        }
    }

    @Override
    public void excludeClassPattern(File[] classpath, String pattern) {
        if (classpath == null) {
            throw new IllegalArgumentException("null classpath");
        }
        if (classpath.length == 0) {
            throw new IllegalArgumentException("empty classpath");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern");
        }
        if ((pattern = pattern.trim()).length() == 0) {
            throw new IllegalArgumentException("empty pattern");
        }
        for (int i = 0; i < classpath.length; ++i) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] EXCLUDING '" + pattern + "' under " + classpath[i]);
            }
            this.addClasspath(classpath[i]);
            this.getClassScanner(classpath[i]).exclude(pattern);
        }
    }

    @Override
    public void includeSourceFile(File[] sourcepath, File sourceFile) {
        File root = this.getPathRootForFile(sourcepath, sourceFile);
        this.includeSourcePattern(new File[]{root}, this.source2pattern(root, sourceFile));
    }

    @Override
    public void excludeSourceFile(File[] sourcepath, File sourceFile) {
        File root = this.getPathRootForFile(sourcepath, sourceFile);
        this.excludeSourcePattern(new File[]{root}, this.source2pattern(root, sourceFile));
    }

    @Override
    public void includeClassFile(File[] classpath, File classFile) {
        File root = this.getPathRootForFile(classpath, classFile);
        this.includeClassPattern(new File[]{root}, this.source2pattern(root, classFile));
    }

    @Override
    public void excludeClassFile(File[] classpath, File classFile) {
        File root = this.getPathRootForFile(classpath, classFile);
        this.excludeClassPattern(new File[]{root}, this.source2pattern(root, classFile));
    }

    @Override
    public void includeClass(String qualifiedClassname) {
        if (this.mIncludeClasses == null) {
            this.mIncludeClasses = new ArrayList();
        }
        this.mIncludeClasses.add(qualifiedClassname);
    }

    @Override
    public void excludeClass(String qualifiedClassname) {
        if (this.mExcludeClasses == null) {
            this.mExcludeClasses = new ArrayList();
        }
        this.mExcludeClasses.add(qualifiedClassname);
    }

    @Override
    public void addClasspath(File classpathElement) {
        if (this.mClasspath == null) {
            this.mClasspath = new ArrayList();
        } else if (this.mClasspath.contains(classpathElement)) {
            return;
        }
        this.mClasspath.add(classpathElement);
    }

    @Override
    public void setLoggerWriter(PrintWriter out) {
        super.setOut(out);
    }

    @Override
    public void setJamLogger(JamLogger logger) {
        throw new IllegalStateException("NYI");
    }

    @Override
    public void addSourcepath(File sourcepathElement) {
        if (this.mSourcepath == null) {
            this.mSourcepath = new ArrayList();
        } else if (this.mSourcepath.contains(sourcepathElement)) {
            return;
        }
        this.mSourcepath.add(sourcepathElement);
    }

    @Override
    public void addToolClasspath(File classpathElement) {
        if (this.mToolClasspath == null) {
            this.mToolClasspath = new ArrayList();
        } else if (this.mToolClasspath.contains(classpathElement)) {
            return;
        }
        this.mToolClasspath.add(classpathElement);
    }

    @Override
    public void setProperty(String name, String value) {
        if (this.mProperties == null) {
            this.mProperties = new Properties();
        }
        this.mProperties.setProperty(name, value);
    }

    @Override
    public void set14WarningsEnabled(boolean b) {
        this.m14WarningsEnabled = b;
    }

    @Override
    public void setParentClassLoader(JamClassLoader loader) {
        throw new IllegalStateException("NYI");
    }

    @Override
    public void setUseSystemClasspath(boolean use) {
        this.mUseSystemClasspath = use;
    }

    @Override
    public void addClassLoader(ClassLoader cl) {
        if (this.mClassLoaders == null) {
            this.mClassLoaders = new ArrayList();
        }
        this.mClassLoaders.add(cl);
    }

    @Override
    public ClassLoader[] getReflectionClassLoaders() {
        if (this.mClassLoaders == null) {
            if (this.mUseSystemClasspath) {
                return new ClassLoader[]{ClassLoader.getSystemClassLoader()};
            }
            return new ClassLoader[0];
        }
        ClassLoader[] out = new ClassLoader[this.mClassLoaders.size() + (this.mUseSystemClasspath ? 1 : 0)];
        for (int i = 0; i < this.mClassLoaders.size(); ++i) {
            out[i] = (ClassLoader)this.mClassLoaders.get(i);
        }
        if (this.mUseSystemClasspath) {
            out[out.length - 1] = ClassLoader.getSystemClassLoader();
        }
        return out;
    }

    @Override
    public boolean is14WarningsEnabled() {
        return this.m14WarningsEnabled;
    }

    @Override
    public JamClassLoader getClassLoader() {
        return this.mLoader;
    }

    @Override
    public AnnotationProxy createAnnotationProxy(String jsr175typename) {
        DefaultAnnotationProxy out = new DefaultAnnotationProxy();
        out.init(this);
        return out;
    }

    private File getPathRootForFile(File[] sourcepath, File sourceFile) {
        if (sourcepath == null) {
            throw new IllegalArgumentException("null sourcepath");
        }
        if (sourcepath.length == 0) {
            throw new IllegalArgumentException("empty sourcepath");
        }
        if (sourceFile == null) {
            throw new IllegalArgumentException("null sourceFile");
        }
        sourceFile = sourceFile.getAbsoluteFile();
        if (this.isVerbose(this)) {
            this.verbose("[JamServiceContextImpl] Getting root for " + sourceFile + "...");
        }
        for (int i = 0; i < sourcepath.length; ++i) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] ...looking in " + sourcepath[i]);
            }
            if (!this.isContainingDir(sourcepath[i].getAbsoluteFile(), sourceFile)) continue;
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] ...found it!");
            }
            return sourcepath[i].getAbsoluteFile();
        }
        throw new IllegalArgumentException(sourceFile + " is not in the given path.");
    }

    private boolean isContainingDir(File dir, File file) {
        if (this.isVerbose(this)) {
            this.verbose("[JamServiceContextImpl] ... ...isContainingDir " + dir + "  " + file);
        }
        if (file == null) {
            return false;
        }
        if (dir.equals(file)) {
            if (this.isVerbose(this)) {
                this.verbose("[JamServiceContextImpl] ... ...yes!");
            }
            return true;
        }
        return this.isContainingDir(dir, file.getParentFile());
    }

    private String source2pattern(File root, File sourceFile) {
        if (this.isVerbose(this)) {
            this.verbose("[JamServiceContextImpl] source2pattern " + root + "  " + sourceFile);
        }
        String r = root.getAbsolutePath();
        String s = sourceFile.getAbsolutePath();
        if (this.isVerbose(this)) {
            this.verbose("[JamServiceContextImpl] source2pattern returning " + s.substring(r.length() + 1));
        }
        return s.substring(r.length() + 1);
    }

    private static String filename2classname(String filename) {
        int extDot = filename.lastIndexOf(46);
        if (extDot != -1) {
            filename = filename.substring(0, extDot);
        }
        filename = filename.replace('/', '.');
        filename = filename.replace('\\', '.');
        return filename;
    }

    private Iterator getAllDirectoryScanners() {
        ArrayList out = new ArrayList();
        if (this.mSourceRoot2Scanner != null) {
            out.addAll(this.mSourceRoot2Scanner.values());
        }
        if (this.mClassRoot2Scanner != null) {
            out.addAll(this.mClassRoot2Scanner.values());
        }
        return out.iterator();
    }

    private static ResourcePath createJPath(Collection filelist) {
        if (filelist == null || filelist.size() == 0) {
            return null;
        }
        File[] files = new File[filelist.size()];
        filelist.toArray(files);
        return ResourcePath.forFiles(files);
    }

    private DirectoryScanner getSourceScanner(File srcRoot) {
        DirectoryScanner out;
        if (this.mSourceRoot2Scanner == null) {
            this.mSourceRoot2Scanner = new HashMap();
        }
        if ((out = (DirectoryScanner)this.mSourceRoot2Scanner.get(srcRoot)) == null) {
            out = new DirectoryScanner(srcRoot, this);
            this.mSourceRoot2Scanner.put(srcRoot, out);
        }
        return out;
    }

    private DirectoryScanner getClassScanner(File clsRoot) {
        DirectoryScanner out;
        if (this.mClassRoot2Scanner == null) {
            this.mClassRoot2Scanner = new HashMap();
        }
        if ((out = (DirectoryScanner)this.mClassRoot2Scanner.get(clsRoot)) == null) {
            out = new DirectoryScanner(clsRoot, this);
            this.mClassRoot2Scanner.put(clsRoot, out);
        }
        return out;
    }
}

