/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.jsc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.optimizer.ClassCompiler;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class Main {
    private boolean printHelp;
    private ToolErrorReporter reporter = new ToolErrorReporter(true);
    private CompilerEnvirons compilerEnv = new CompilerEnvirons();
    private ClassCompiler compiler;
    private String targetName;
    private String targetPackage;
    private String destinationDir;
    private String characterEncoding;

    public static void main(String[] args) {
        Main main = new Main();
        if ((args = main.processOptions(args)) == null) {
            if (main.printHelp) {
                System.out.println(ToolErrorReporter.getMessage("msg.jsc.usage", Main.class.getName()));
                System.exit(0);
            }
            System.exit(1);
        }
        if (!main.reporter.hasReportedError()) {
            main.processSource(args);
        }
    }

    public Main() {
        this.compilerEnv.setErrorReporter(this.reporter);
        this.compiler = new ClassCompiler(this.compilerEnv);
    }

    public String[] processOptions(String[] args) {
        this.targetPackage = "";
        this.compilerEnv.setGenerateDebugInfo(false);
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (!arg.startsWith("-")) {
                int tail = args.length - i;
                if (this.targetName != null && tail > 1) {
                    this.addError("msg.multiple.js.to.file", this.targetName);
                    return null;
                }
                String[] result = new String[tail];
                for (int j = 0; j != tail; ++j) {
                    result[j] = args[i + j];
                }
                return result;
            }
            if (arg.equals("-help") || arg.equals("-h") || arg.equals("--help")) {
                this.printHelp = true;
                return null;
            }
            try {
                if (arg.equals("-version") && ++i < args.length) {
                    int version = Integer.parseInt(args[i]);
                    this.compilerEnv.setLanguageVersion(version);
                    continue;
                }
                if ((arg.equals("-opt") || arg.equals("-O")) && ++i < args.length) {
                    int optLevel = Integer.parseInt(args[i]);
                    this.compilerEnv.setOptimizationLevel(optLevel);
                    continue;
                }
            } catch (NumberFormatException e) {
                Main.badUsage(args[i]);
                return null;
            }
            if (arg.equals("-nosource")) {
                this.compilerEnv.setGeneratingSource(false);
                continue;
            }
            if (arg.equals("-debug") || arg.equals("-g")) {
                this.compilerEnv.setGenerateDebugInfo(true);
                continue;
            }
            if (arg.equals("-main-method-class") && ++i < args.length) {
                this.compiler.setMainMethodClass(args[i]);
                continue;
            }
            if (arg.equals("-encoding") && ++i < args.length) {
                this.characterEncoding = args[i];
                continue;
            }
            if (arg.equals("-o") && ++i < args.length) {
                String name = args[i];
                int end = name.length();
                if (end == 0 || !Character.isJavaIdentifierStart(name.charAt(0))) {
                    this.addError("msg.invalid.classfile.name", name);
                    continue;
                }
                for (int j = 1; j < end; ++j) {
                    char c = name.charAt(j);
                    if (Character.isJavaIdentifierPart(c)) continue;
                    if (c == '.' && j == end - 6 && name.endsWith(".class")) {
                        name = name.substring(0, j);
                        break;
                    }
                    this.addError("msg.invalid.classfile.name", name);
                    break;
                }
                this.targetName = name;
                continue;
            }
            if (arg.equals("-observe-instruction-count")) {
                this.compilerEnv.setGenerateObserverCount(true);
            }
            if (arg.equals("-package") && ++i < args.length) {
                String pkg = args[i];
                int end = pkg.length();
                for (int j = 0; j != end; ++j) {
                    char c = pkg.charAt(j);
                    if (Character.isJavaIdentifierStart(c)) {
                        ++j;
                        while (j != end && Character.isJavaIdentifierPart(c = pkg.charAt(j))) {
                            ++j;
                        }
                        if (j == end) break;
                        if (c == '.' && j != end - 1) continue;
                    }
                    this.addError("msg.package.name", this.targetPackage);
                    return null;
                }
                this.targetPackage = pkg;
                continue;
            }
            if (arg.equals("-extends") && ++i < args.length) {
                Class<?> superClass;
                String targetExtends = args[i];
                try {
                    superClass = Class.forName(targetExtends);
                } catch (ClassNotFoundException e) {
                    throw new Error(e.toString());
                }
                this.compiler.setTargetExtends(superClass);
                continue;
            }
            if (arg.equals("-implements") && ++i < args.length) {
                String targetImplements = args[i];
                StringTokenizer st = new StringTokenizer(targetImplements, ",");
                ArrayList list = new ArrayList();
                while (st.hasMoreTokens()) {
                    String className = st.nextToken();
                    try {
                        list.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        throw new Error(e.toString());
                    }
                }
                Class[] implementsClasses = list.toArray(new Class[list.size()]);
                this.compiler.setTargetImplements(implementsClasses);
                continue;
            }
            if (arg.equals("-d") && ++i < args.length) {
                this.destinationDir = args[i];
                continue;
            }
            Main.badUsage(arg);
            return null;
        }
        Main.p(ToolErrorReporter.getMessage("msg.no.file"));
        return null;
    }

    private static void badUsage(String s) {
        System.err.println(ToolErrorReporter.getMessage("msg.jsc.bad.usage", Main.class.getName(), s));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void processSource(String[] filenames) {
        for (int i = 0; i != filenames.length; ++i) {
            Object[] compiled;
            String filename = filenames[i];
            if (!filename.endsWith(".js")) {
                this.addError("msg.extension.not.js", filename);
                return;
            }
            File f = new File(filename);
            String source = this.readSource(f);
            if (source == null) {
                return;
            }
            String mainClassName = this.targetName;
            if (mainClassName == null) {
                String name = f.getName();
                String nojs = name.substring(0, name.length() - 3);
                mainClassName = this.getClassName(nojs);
            }
            if (this.targetPackage.length() != 0) {
                mainClassName = this.targetPackage + "." + mainClassName;
            }
            if ((compiled = this.compiler.compileToClassFiles(source, filename, 1, mainClassName)) == null || compiled.length == 0) {
                return;
            }
            File targetTopDir = null;
            if (this.destinationDir != null) {
                targetTopDir = new File(this.destinationDir);
            } else {
                String parent = f.getParent();
                if (parent != null) {
                    targetTopDir = new File(parent);
                }
            }
            for (int j = 0; j != compiled.length; j += 2) {
                String className = (String)compiled[j];
                byte[] bytes = (byte[])compiled[j + 1];
                File outfile = this.getOutputFile(targetTopDir, className);
                try {
                    FileOutputStream os = new FileOutputStream(outfile);
                    try {
                        os.write(bytes);
                        continue;
                    } finally {
                        os.close();
                    }
                } catch (IOException ioe) {
                    this.addFormatedError(ioe.toString());
                }
            }
        }
    }

    private String readSource(File f) {
        String absPath = f.getAbsolutePath();
        if (!f.isFile()) {
            this.addError("msg.jsfile.not.found", absPath);
            return null;
        }
        try {
            return (String)SourceReader.readFileOrUrl(absPath, true, this.characterEncoding);
        } catch (FileNotFoundException ex) {
            this.addError("msg.couldnt.open", absPath);
        } catch (IOException ioe) {
            this.addFormatedError(ioe.toString());
        }
        return null;
    }

    private File getOutputFile(File parentDir, String className) {
        File dir;
        String path = className.replace('.', File.separatorChar);
        File f = new File(parentDir, path = path.concat(".class"));
        String dirPath = f.getParent();
        if (dirPath != null && !(dir = new File(dirPath)).exists()) {
            dir.mkdirs();
        }
        return f;
    }

    String getClassName(String name) {
        char[] s = new char[name.length() + 1];
        int j = 0;
        if (!Character.isJavaIdentifierStart(name.charAt(0))) {
            s[j++] = 95;
        }
        int i = 0;
        while (i < name.length()) {
            int c = name.charAt(i);
            s[j] = Character.isJavaIdentifierPart((char)c) ? c : 95;
            ++i;
            ++j;
        }
        return new String(s).trim();
    }

    private static void p(String s) {
        System.out.println(s);
    }

    private void addError(String messageId, String arg) {
        String msg = arg == null ? ToolErrorReporter.getMessage(messageId) : ToolErrorReporter.getMessage(messageId, arg);
        this.addFormatedError(msg);
    }

    private void addFormatedError(String message) {
        this.reporter.error(message, null, -1, null, -1);
    }
}

