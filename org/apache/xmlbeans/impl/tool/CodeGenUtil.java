/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.xmlbeans.SystemProperties;

public class CodeGenUtil {
    public static String DEFAULT_MEM_START = "8m";
    public static String DEFAULT_MEM_MAX = "256m";
    public static String DEFAULT_COMPILER = "javac";
    public static String DEFAULT_JAR = "jar";

    public static URI resolve(URI base, URI child) {
        URI ruri = base.resolve(child);
        if ("file".equals(ruri.getScheme()) && !child.equals(ruri) && base.getPath().startsWith("//") && !ruri.getPath().startsWith("//")) {
            String path = "///".concat(ruri.getPath());
            try {
                ruri = new URI("file", null, path, ruri.getQuery(), ruri.getFragment());
            } catch (URISyntaxException uris) {
                // empty catch block
            }
        }
        return ruri;
    }

    static void addAllJavaFiles(List srcFiles, List args) {
        for (File f : srcFiles) {
            if (!f.isDirectory()) {
                args.add(CodeGenUtil.quoteAndEscapeFilename(f.getAbsolutePath()));
                continue;
            }
            List<File> inside = Arrays.asList(f.listFiles(new FileFilter(){

                @Override
                public boolean accept(File file) {
                    return file.isFile() && file.getName().endsWith(".java") || file.isDirectory();
                }
            }));
            CodeGenUtil.addAllJavaFiles(inside, args);
        }
    }

    private static String quoteAndEscapeFilename(String filename) {
        if (filename.indexOf(" ") < 0) {
            return filename;
        }
        return "\"" + filename.replaceAll("\\\\", "\\\\\\\\") + "\"";
    }

    private static String quoteNoEscapeFilename(String filename) {
        if (filename.indexOf(" ") < 0 || File.separatorChar == '/') {
            return filename;
        }
        return "\"" + filename + "\"";
    }

    public static boolean externalCompile(List srcFiles, File outdir, File[] cp, boolean debug) {
        return CodeGenUtil.externalCompile(srcFiles, outdir, cp, debug, DEFAULT_COMPILER, null, DEFAULT_MEM_START, DEFAULT_MEM_MAX, false, false);
    }

    public static boolean externalCompile(List srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String memStart, String memMax, boolean quiet, boolean verbose) {
        return CodeGenUtil.externalCompile(srcFiles, outdir, cp, debug, javacPath, null, memStart, memMax, quiet, verbose);
    }

    public static boolean externalCompile(List srcFiles, File outdir, File[] cp, boolean debug, String javacPath, String genver, String memStart, String memMax, boolean quiet, boolean verbose) {
        ArrayList<String> args = new ArrayList<String>();
        File javac = CodeGenUtil.findJavaTool(javacPath == null ? DEFAULT_COMPILER : javacPath);
        assert (javac.exists()) : "compiler not found " + javac;
        args.add(javac.getAbsolutePath());
        if (outdir == null) {
            outdir = new File(".");
        } else {
            args.add("-d");
            args.add(CodeGenUtil.quoteAndEscapeFilename(outdir.getAbsolutePath()));
        }
        if (cp == null) {
            cp = CodeGenUtil.systemClasspath();
        }
        if (cp.length > 0) {
            StringBuffer classPath = new StringBuffer();
            classPath.append(outdir.getAbsolutePath());
            for (int i = 0; i < cp.length; ++i) {
                classPath.append(File.pathSeparator);
                classPath.append(cp[i].getAbsolutePath());
            }
            args.add("-classpath");
            args.add(CodeGenUtil.quoteAndEscapeFilename(classPath.toString()));
        }
        if (genver == null) {
            genver = "1.6";
        }
        args.add("-source");
        args.add(genver);
        args.add("-target");
        args.add(genver);
        args.add(debug ? "-g" : "-g:none");
        if (verbose) {
            args.add("-verbose");
        }
        CodeGenUtil.addAllJavaFiles(srcFiles, args);
        File clFile = null;
        try {
            clFile = File.createTempFile("javac", "");
            FileWriter fw = new FileWriter(clFile);
            Iterator i = args.iterator();
            i.next();
            while (i.hasNext()) {
                String arg = (String)i.next();
                fw.write(arg);
                fw.write(10);
            }
            fw.close();
            ArrayList newargs = new ArrayList();
            newargs.add(args.get(0));
            if (memStart != null && memStart.length() != 0) {
                newargs.add("-J-Xms" + memStart);
            }
            if (memMax != null && memMax.length() != 0) {
                newargs.add("-J-Xmx" + memMax);
            }
            newargs.add("@" + clFile.getAbsolutePath());
            args = newargs;
        } catch (Exception e) {
            System.err.println("Could not create command-line file for javac");
        }
        try {
            String[] strArgs = args.toArray(new String[args.size()]);
            if (verbose) {
                System.out.print("compile command:");
                for (int i = 0; i < strArgs.length; ++i) {
                    System.out.print(" " + strArgs[i]);
                }
                System.out.println();
            }
            Process proc = Runtime.getRuntime().exec(strArgs);
            StringBuffer errorBuffer = new StringBuffer();
            StringBuffer outputBuffer = new StringBuffer();
            ThreadedReader out = new ThreadedReader(proc.getInputStream(), outputBuffer);
            ThreadedReader err = new ThreadedReader(proc.getErrorStream(), errorBuffer);
            proc.waitFor();
            if (verbose || proc.exitValue() != 0) {
                if (outputBuffer.length() > 0) {
                    System.out.println(outputBuffer.toString());
                    System.out.flush();
                }
                if (errorBuffer.length() > 0) {
                    System.err.println(errorBuffer.toString());
                    System.err.flush();
                }
                if (proc.exitValue() != 0) {
                    return false;
                }
            }
        } catch (Throwable e) {
            System.err.println(e.toString());
            System.err.println(e.getCause());
            e.printStackTrace(System.err);
            return false;
        }
        if (clFile != null) {
            clFile.delete();
        }
        return true;
    }

    public static File[] systemClasspath() {
        ArrayList<File> cp = new ArrayList<File>();
        String[] systemcp = SystemProperties.getProperty("java.class.path").split(File.pathSeparator);
        for (int i = 0; i < systemcp.length; ++i) {
            cp.add(new File(systemcp[i]));
        }
        return cp.toArray(new File[cp.size()]);
    }

    public static boolean externalJar(File srcdir, File outfile) {
        return CodeGenUtil.externalJar(srcdir, outfile, DEFAULT_JAR, false, false);
    }

    public static boolean externalJar(File srcdir, File outfile, String jarPath, boolean quiet, boolean verbose) {
        ArrayList<String> args = new ArrayList<String>();
        File jar = CodeGenUtil.findJavaTool(jarPath == null ? DEFAULT_JAR : jarPath);
        assert (jar.exists()) : "jar not found " + jar;
        args.add(jar.getAbsolutePath());
        args.add("cf");
        args.add(CodeGenUtil.quoteNoEscapeFilename(outfile.getAbsolutePath()));
        args.add("-C");
        args.add(CodeGenUtil.quoteNoEscapeFilename(srcdir.getAbsolutePath()));
        args.add(".");
        try {
            String[] strArgs = args.toArray(new String[args.size()]);
            if (verbose) {
                System.out.print("jar command:");
                for (int i = 0; i < strArgs.length; ++i) {
                    System.out.print(" " + strArgs[i]);
                }
                System.out.println();
            }
            Process proc = Runtime.getRuntime().exec(strArgs);
            StringBuffer errorBuffer = new StringBuffer();
            StringBuffer outputBuffer = new StringBuffer();
            ThreadedReader out = new ThreadedReader(proc.getInputStream(), outputBuffer);
            ThreadedReader err = new ThreadedReader(proc.getErrorStream(), errorBuffer);
            proc.waitFor();
            if (verbose || proc.exitValue() != 0) {
                if (outputBuffer.length() > 0) {
                    System.out.println(outputBuffer.toString());
                    System.out.flush();
                }
                if (errorBuffer.length() > 0) {
                    System.err.println(errorBuffer.toString());
                    System.err.flush();
                }
                if (proc.exitValue() != 0) {
                    return false;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace(System.err);
            return false;
        }
        return true;
    }

    private static File findJavaTool(String tool) {
        File toolFile = new File(tool);
        if (toolFile.isFile()) {
            return toolFile;
        }
        File result = new File(tool + ".exe");
        if (result.isFile()) {
            return result;
        }
        String home = SystemProperties.getProperty("java.home");
        String sep = File.separator;
        result = new File(home + sep + ".." + sep + "bin", tool);
        if (result.isFile()) {
            return result;
        }
        if ((result = new File(result.getPath() + ".exe")).isFile()) {
            return result;
        }
        result = new File(home + sep + "bin", tool);
        if (result.isFile()) {
            return result;
        }
        if ((result = new File(result.getPath() + ".exe")).isFile()) {
            return result;
        }
        return toolFile;
    }

    private static class ThreadedReader {
        public ThreadedReader(InputStream stream, final StringBuffer output) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            Thread readerThread = new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        String s;
                        while ((s = reader.readLine()) != null) {
                            output.append(s + "\n");
                        }
                    } catch (Exception exception) {
                        // empty catch block
                    }
                }
            });
            readerThread.start();
        }
    }
}

