/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.Doclet
 *  com.sun.javadoc.RootDoc
 *  com.sun.tools.javadoc.Main
 */
package org.apache.xmlbeans.impl.jam.internal.javadoc;

import com.sun.javadoc.Doclet;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocClassloadingException;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocResults;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public class JavadocRunner
extends Doclet {
    private static final String JAVADOC_RUNNER_150 = "org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocRunner_150";

    public static JavadocRunner newInstance() {
        try {
            Class.forName("com.sun.javadoc.AnnotationDesc");
        } catch (ClassNotFoundException e) {
            return new JavadocRunner();
        }
        try {
            Class<?> onefive = Class.forName(JAVADOC_RUNNER_150);
            return (JavadocRunner)((Object)onefive.newInstance());
        } catch (ClassNotFoundException cnfe) {
        } catch (IllegalAccessException e) {
        } catch (InstantiationException instantiationException) {
            // empty catch block
        }
        return new JavadocRunner();
    }

    synchronized RootDoc run(File[] files, PrintWriter out, String sourcePath, String classPath, String[] javadocArgs, JamLogger logger) throws IOException, FileNotFoundException {
        PrintWriter spewWriter;
        if (files == null || files.length == 0) {
            throw new FileNotFoundException("No input files found.");
        }
        ArrayList<String> argList = new ArrayList<String>();
        if (javadocArgs != null) {
            argList.addAll(Arrays.asList(javadocArgs));
        }
        argList.add("-private");
        if (sourcePath != null) {
            argList.add("-sourcepath");
            argList.add(sourcePath);
        }
        if (classPath != null) {
            argList.add("-classpath");
            argList.add(classPath);
            argList.add("-docletpath");
            argList.add(classPath);
        }
        for (int i = 0; i < files.length; ++i) {
            argList.add(files[i].toString());
            if (out == null) continue;
            out.println(files[i].toString());
        }
        String[] args = new String[argList.size()];
        argList.toArray(args);
        StringWriter spew = null;
        if (out == null) {
            spew = new StringWriter();
            spewWriter = new PrintWriter(spew);
        } else {
            spewWriter = out;
        }
        ClassLoader originalCCL = Thread.currentThread().getContextClassLoader();
        try {
            JavadocResults.prepare();
            if (logger.isVerbose((Object)this)) {
                logger.verbose("Invoking javadoc.  Command line equivalent is: ");
                StringWriter sw = new StringWriter();
                sw.write("javadoc ");
                for (int i = 0; i < args.length; ++i) {
                    sw.write("'");
                    sw.write(args[i]);
                    sw.write("' ");
                }
                logger.verbose("  " + sw.toString());
            }
            int result = Main.execute((String)"JAM", (PrintWriter)spewWriter, (PrintWriter)spewWriter, (PrintWriter)spewWriter, (String)((Object)((Object)this)).getClass().getName(), (String[])args);
            RootDoc root = JavadocResults.getRoot();
            if (result != 0 || root == null) {
                spewWriter.flush();
                if (JavadocClassloadingException.isClassloadingProblemPresent()) {
                    throw new JavadocClassloadingException();
                }
                throw new RuntimeException("Unknown javadoc problem: result=" + result + ", root=" + root + ":\n" + (spew == null ? "" : spew.toString()));
            }
            RootDoc rootDoc = root;
            return rootDoc;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            Thread.currentThread().setContextClassLoader(originalCCL);
        }
    }

    public static boolean start(RootDoc root) {
        JavadocResults.setRoot(root);
        return true;
    }
}

