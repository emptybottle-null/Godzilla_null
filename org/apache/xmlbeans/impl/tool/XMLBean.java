/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.tools.ant.BuildException
 *  org.apache.tools.ant.DirectoryScanner
 *  org.apache.tools.ant.taskdefs.Jar
 *  org.apache.tools.ant.taskdefs.Javac
 *  org.apache.tools.ant.taskdefs.MatchingTask
 *  org.apache.tools.ant.types.FileSet
 *  org.apache.tools.ant.types.Path
 *  org.apache.tools.ant.types.Path$PathElement
 *  org.apache.tools.ant.types.Reference
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.taskdefs.MatchingTask;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.tool.Extension;
import org.apache.xmlbeans.impl.tool.SchemaCodeGenerator;
import org.apache.xmlbeans.impl.tool.SchemaCompiler;

public class XMLBean
extends MatchingTask {
    private ArrayList schemas = new ArrayList();
    private Set mdefnamespaces;
    private Path classpath;
    private File destfile;
    private File schema;
    private File srcgendir;
    private File classgendir;
    private boolean quiet;
    private boolean verbose;
    private boolean debug;
    private boolean optimize;
    private boolean download;
    private boolean srconly;
    private boolean noupa;
    private boolean nopvr;
    private boolean noann;
    private boolean novdoc;
    private boolean noext = false;
    private boolean failonerror = true;
    private boolean fork = true;
    private boolean includeAntRuntime = true;
    private boolean noSrcRegen;
    private boolean includeJavaRuntime = false;
    private boolean nowarn = false;
    private String typesystemname;
    private String forkedExecutable;
    private String compiler;
    private String debugLevel;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String catalog;
    private String javasource;
    private List extensions = new ArrayList();
    private HashMap _extRouter = new HashMap(5);
    private static final String XSD = ".xsd";
    private static final String WSDL = ".wsdl";
    private static final String JAVA = ".java";
    private static final String XSDCONFIG = ".xsdconfig";
    private String source = null;

    public void execute() throws BuildException {
        if (this.schemas.size() == 0 && this.schema == null && this.fileset.getDir(this.project) == null) {
            String msg = "The 'schema' or 'dir' attribute or a nested fileset is required.";
            if (this.failonerror) {
                throw new BuildException(msg);
            }
            this.log(msg, 0);
            return;
        }
        this._extRouter.put(XSD, new HashSet());
        this._extRouter.put(WSDL, new HashSet());
        this._extRouter.put(JAVA, new HashSet());
        this._extRouter.put(XSDCONFIG, new HashSet());
        File theBasedir = this.schema;
        if (this.schema != null) {
            if (this.schema.isDirectory()) {
                DirectoryScanner scanner = this.getDirectoryScanner(this.schema);
                String[] paths = scanner.getIncludedFiles();
                this.processPaths(paths, scanner.getBasedir());
            } else {
                theBasedir = this.schema.getParentFile();
                this.processPaths(new String[]{this.schema.getName()}, theBasedir);
            }
        }
        if (this.fileset.getDir(this.project) != null) {
            this.schemas.add(this.fileset);
        }
        for (FileSet fs : this.schemas) {
            DirectoryScanner scanner = fs.getDirectoryScanner(this.project);
            File basedir = scanner.getBasedir();
            String[] paths = scanner.getIncludedFiles();
            this.processPaths(paths, basedir);
        }
        Set xsdList = (Set)this._extRouter.get(XSD);
        Set wsdlList = (Set)this._extRouter.get(WSDL);
        if (xsdList.size() + wsdlList.size() == 0) {
            this.log("Could not find any xsd or wsdl files to process.", 1);
            return;
        }
        Set javaList = (Set)this._extRouter.get(JAVA);
        Set xsdconfigList = (Set)this._extRouter.get(XSDCONFIG);
        if (this.srcgendir == null && this.srconly) {
            this.srcgendir = this.classgendir;
        }
        if (this.destfile == null && this.classgendir == null && !this.srconly) {
            this.destfile = new File("xmltypes.jar");
        }
        if (this.verbose) {
            this.quiet = false;
        }
        File[] xsdArray = xsdList.toArray(new File[xsdList.size()]);
        File[] wsdlArray = wsdlList.toArray(new File[wsdlList.size()]);
        File[] javaArray = javaList.toArray(new File[javaList.size()]);
        File[] xsdconfigArray = xsdconfigList.toArray(new File[xsdconfigList.size()]);
        ErrorLogger err = new ErrorLogger(this.verbose);
        boolean success = false;
        try {
            File tmpdir = null;
            if (this.srcgendir == null || this.classgendir == null) {
                tmpdir = SchemaCodeGenerator.createTempDir();
            }
            if (this.srcgendir == null) {
                this.srcgendir = IOUtil.createDir(tmpdir, "src");
            }
            if (this.classgendir == null) {
                this.classgendir = IOUtil.createDir(tmpdir, "classes");
            }
            if (this.classpath == null) {
                this.classpath = new Path(this.project);
                this.classpath.concatSystemClasspath();
            }
            Path.PathElement pathElement = this.classpath.createPathElement();
            pathElement.setLocation(this.classgendir);
            String[] paths = this.classpath.list();
            File[] cp = new File[paths.length];
            for (int i = 0; i < paths.length; ++i) {
                cp[i] = new File(paths[i]);
            }
            SchemaCompiler.Parameters params = new SchemaCompiler.Parameters();
            params.setBaseDir(theBasedir);
            params.setXsdFiles(xsdArray);
            params.setWsdlFiles(wsdlArray);
            params.setJavaFiles(javaArray);
            params.setConfigFiles(xsdconfigArray);
            params.setClasspath(cp);
            params.setName(this.typesystemname);
            params.setSrcDir(this.srcgendir);
            params.setClassesDir(this.classgendir);
            params.setNojavac(true);
            params.setDebug(this.debug);
            params.setVerbose(this.verbose);
            params.setQuiet(this.quiet);
            params.setDownload(this.download);
            params.setExtensions(this.extensions);
            params.setErrorListener(err);
            params.setCatalogFile(this.catalog);
            params.setIncrementalSrcGen(this.noSrcRegen);
            params.setMdefNamespaces(this.mdefnamespaces);
            params.setNoUpa(this.noupa);
            params.setNoPvr(this.nopvr);
            params.setNoAnn(this.noann);
            params.setNoVDoc(this.novdoc);
            params.setNoExt(this.noext);
            params.setJavaSource(this.javasource);
            success = SchemaCompiler.compile(params);
            if (success && !this.srconly) {
                long start = System.currentTimeMillis();
                Javac javac = new Javac();
                javac.setProject(this.project);
                javac.setTaskName(this.getTaskName());
                javac.setClasspath(this.classpath);
                if (this.compiler != null) {
                    javac.setCompiler(this.compiler);
                }
                javac.setDebug(this.debug);
                if (this.debugLevel != null) {
                    javac.setDebugLevel(this.debugLevel);
                }
                javac.setDestdir(this.classgendir);
                javac.setExecutable(this.forkedExecutable);
                javac.setFailonerror(this.failonerror);
                javac.setFork(this.fork);
                if (this.javasource != null) {
                    javac.setSource(this.javasource);
                    javac.setTarget(this.javasource);
                } else {
                    javac.setSource("1.4");
                    javac.setTarget("1.4");
                }
                javac.setIncludeantruntime(this.includeAntRuntime);
                javac.setIncludejavaruntime(this.includeJavaRuntime);
                javac.setNowarn(this.nowarn);
                javac.setSrcdir(new Path(this.project, this.srcgendir.getAbsolutePath()));
                if (this.memoryInitialSize != null) {
                    javac.setMemoryInitialSize(this.memoryInitialSize);
                }
                if (this.memoryMaximumSize != null) {
                    javac.setMemoryMaximumSize(this.memoryMaximumSize);
                }
                javac.setOptimize(this.optimize);
                javac.setVerbose(this.verbose);
                javac.execute();
                long finish = System.currentTimeMillis();
                if (!this.quiet) {
                    this.log("Time to compile code: " + (double)(finish - start) / 1000.0 + " seconds");
                }
                if (this.destfile != null) {
                    Jar jar = new Jar();
                    jar.setProject(this.project);
                    jar.setTaskName(this.getTaskName());
                    jar.setBasedir(this.classgendir);
                    jar.setDestFile(this.destfile);
                    jar.execute();
                }
            }
            if (tmpdir != null) {
                SchemaCodeGenerator.tryHardToDelete(tmpdir);
            }
        } catch (BuildException e) {
            throw e;
        } catch (Throwable e) {
            if (e instanceof InterruptedException || this.failonerror) {
                throw new BuildException(e);
            }
            this.log("Exception while building schemas: " + e.getMessage(), 0);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            this.log(sw.toString(), 3);
        }
        if (!success && this.failonerror) {
            throw new BuildException();
        }
    }

    private void processPaths(String[] paths, File baseDir) {
        for (int i = 0; i < paths.length; ++i) {
            String path;
            String possExt;
            Set set;
            int dot = paths[i].lastIndexOf(46);
            if (dot <= -1 || (set = (Set)this._extRouter.get(possExt = (path = paths[i]).substring(dot).toLowerCase())) == null) continue;
            set.add(new File(baseDir, path));
        }
    }

    public void addFileset(FileSet fileset) {
        this.schemas.add(fileset);
    }

    public File getSchema() {
        return this.schema;
    }

    public void setSchema(File schema) {
        this.schema = schema;
    }

    public void setClasspath(Path classpath) {
        if (this.classpath != null) {
            this.classpath.append(classpath);
        } else {
            this.classpath = classpath;
        }
    }

    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(this.project);
        }
        return this.classpath.createPath();
    }

    public void setClasspathRef(Reference classpathref) {
        if (this.classpath == null) {
            this.classpath = new Path(this.project);
        }
        this.classpath.createPath().setRefid(classpathref);
    }

    public Path getClasspath() {
        return this.classpath;
    }

    public File getDestfile() {
        return this.destfile;
    }

    public void setDestfile(File destfile) {
        this.destfile = destfile;
    }

    public File getSrcgendir() {
        return this.srcgendir;
    }

    public void setSrcgendir(File srcgendir) {
        this.srcgendir = srcgendir;
    }

    public File getClassgendir() {
        return this.classgendir;
    }

    public void setClassgendir(File classgendir) {
        this.classgendir = classgendir;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public boolean isDownload() {
        return this.download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }

    public boolean getOptimize() {
        return this.optimize;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public String getDebugLevel() {
        return this.debugLevel;
    }

    public void setDebugLevel(String v) {
        this.debugLevel = v;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setFork(boolean f) {
        this.fork = f;
    }

    public void setExecutable(String forkExec) {
        this.forkedExecutable = forkExec;
    }

    public String getExecutable() {
        return this.forkedExecutable;
    }

    public boolean isSrconly() {
        return this.srconly;
    }

    public void setSrconly(boolean srconly) {
        this.srconly = srconly;
    }

    public String getTypesystemname() {
        return this.typesystemname;
    }

    public Extension createExtension() {
        Extension e = new Extension();
        this.extensions.add(e);
        return e;
    }

    public void setIgnoreDuplicatesInNamespaces(String namespaces) {
        this.mdefnamespaces = new HashSet();
        StringTokenizer st = new StringTokenizer(namespaces, ",");
        while (st.hasMoreTokens()) {
            String namespace = st.nextToken().trim();
            this.mdefnamespaces.add(namespace);
        }
    }

    public String getIgnoreDuplicatesInNamespaces() {
        if (this.mdefnamespaces == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        Iterator i = this.mdefnamespaces.iterator();
        while (i.hasNext()) {
            buf.append((String)i.next());
            if (!i.hasNext()) continue;
            buf.append(",");
        }
        return buf.toString();
    }

    public void setTypesystemname(String typesystemname) {
        this.typesystemname = typesystemname;
    }

    public boolean isFailonerror() {
        return this.failonerror;
    }

    public void setFailonerror(boolean failonerror) {
        this.failonerror = failonerror;
    }

    public boolean isIncludeAntRuntime() {
        return this.includeAntRuntime;
    }

    public void setIncludeAntRuntime(boolean includeAntRuntime) {
        this.includeAntRuntime = includeAntRuntime;
    }

    public boolean isIncludeJavaRuntime() {
        return this.includeJavaRuntime;
    }

    public void setIncludeJavaRuntime(boolean includeJavaRuntime) {
        this.includeJavaRuntime = includeJavaRuntime;
    }

    public boolean isNowarn() {
        return this.nowarn;
    }

    public void setNowarn(boolean nowarn) {
        this.nowarn = nowarn;
    }

    public boolean isNoSrcRegen() {
        return this.noSrcRegen;
    }

    public void setNoSrcRegen(boolean noSrcRegen) {
        this.noSrcRegen = noSrcRegen;
    }

    public String getMemoryInitialSize() {
        return this.memoryInitialSize;
    }

    public void setMemoryInitialSize(String memoryInitialSize) {
        this.memoryInitialSize = memoryInitialSize;
    }

    public String getMemoryMaximumSize() {
        return this.memoryMaximumSize;
    }

    public void setMemoryMaximumSize(String memoryMaximumSize) {
        this.memoryMaximumSize = memoryMaximumSize;
    }

    public void setNoUpa(boolean noupa) {
        this.noupa = noupa;
    }

    public boolean isNoUpa() {
        return this.noupa;
    }

    public void setNoPvr(boolean nopvr) {
        this.nopvr = nopvr;
    }

    public boolean isNoPvr() {
        return this.nopvr;
    }

    public void setNoAnnotations(boolean noann) {
        this.noann = noann;
    }

    public boolean isNoAnnotations() {
        return this.noann;
    }

    public void setNoValidateDoc(boolean novdoc) {
        this.novdoc = novdoc;
    }

    public boolean isNoValidateDoc() {
        return this.novdoc;
    }

    public void setNoExt(boolean noext) {
        this.noext = noext;
    }

    public boolean isNoExt() {
        return this.noext;
    }

    public void setJavaSource(String javasource) {
        this.javasource = javasource;
    }

    public String getJavaSource() {
        return this.javasource;
    }

    public void setSource(String s) {
        this.source = s;
    }

    public String getCatalog() {
        return this.catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    private static URI uriFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            return f.getCanonicalFile().toURI();
        } catch (IOException e) {
            return f.getAbsoluteFile().toURI();
        }
    }

    public class ErrorLogger
    extends AbstractCollection {
        private boolean _noisy;
        private URI _baseURI;

        public ErrorLogger(boolean noisy) {
            this._noisy = noisy;
            this._baseURI = XMLBean.uriFromFile(XMLBean.this.project.getBaseDir());
        }

        @Override
        public boolean add(Object o) {
            if (o instanceof XmlError) {
                XmlError err = (XmlError)o;
                if (err.getSeverity() == 0) {
                    XMLBean.this.log(err.toString(this._baseURI), 0);
                } else if (err.getSeverity() == 1) {
                    XMLBean.this.log(err.toString(this._baseURI), 1);
                } else if (this._noisy) {
                    XMLBean.this.log(err.toString(this._baseURI), 2);
                }
            }
            return false;
        }

        @Override
        public Iterator iterator() {
            return Collections.EMPTY_LIST.iterator();
        }

        @Override
        public int size() {
            return 0;
        }
    }
}

