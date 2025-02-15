/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.tool.CommandLine;

public class RunXQuery {
    public static void printUsage() {
        System.out.println("Run an XQuery against an XML instance");
        System.out.println("Usage:");
        System.out.println("xquery [-verbose] [-pretty] [-q <query> | -qf query.xq] [file.xml]*");
        System.out.println(" -q <query> to specify a query on the command-line");
        System.out.println(" -qf <query> to specify a file containing a query");
        System.out.println(" -pretty pretty-prints the results");
        System.out.println(" -license prints license information");
        System.out.println(" the query is run on each XML file specified");
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        HashSet<String> flags = new HashSet<String>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("verbose");
        flags.add("pretty");
        CommandLine cl = new CommandLine(args, flags, Arrays.asList("q", "qf"));
        if (cl.getOpt("h") != null || cl.getOpt("help") != null || cl.getOpt("usage") != null) {
            RunXQuery.printUsage();
            System.exit(0);
            return;
        }
        String[] badopts = cl.getBadOpts();
        if (badopts.length > 0) {
            for (int i = 0; i < badopts.length; ++i) {
                System.out.println("Unrecognized option: " + badopts[i]);
            }
            RunXQuery.printUsage();
            System.exit(0);
            return;
        }
        if (cl.getOpt("license") != null) {
            CommandLine.printLicense();
            System.exit(0);
            return;
        }
        if (cl.getOpt("version") != null) {
            CommandLine.printVersion();
            System.exit(0);
            return;
        }
        args = cl.args();
        if (args.length == 0) {
            RunXQuery.printUsage();
            System.exit(0);
            return;
        }
        boolean verbose = cl.getOpt("verbose") != null;
        boolean pretty = cl.getOpt("pretty") != null;
        String query = cl.getOpt("q");
        String queryfile = cl.getOpt("qf");
        if (query == null && queryfile == null) {
            System.err.println("No query specified");
            System.exit(0);
            return;
        }
        if (query != null && queryfile != null) {
            System.err.println("Specify -qf or -q, not both.");
            System.exit(0);
            return;
        }
        try {
            if (queryfile != null) {
                int ch;
                File queryFile = new File(queryfile);
                FileInputStream is = new FileInputStream(queryFile);
                InputStreamReader r = new InputStreamReader(is);
                StringBuffer sb = new StringBuffer();
                while ((ch = r.read()) >= 0) {
                    sb.append((char)ch);
                }
                r.close();
                is.close();
                query = sb.toString();
            }
        } catch (Throwable e) {
            System.err.println("Cannot read query file: " + e.getMessage());
            System.exit(1);
            return;
        }
        if (verbose) {
            System.out.println("Compile Query:");
            System.out.println(query);
            System.out.println();
        }
        try {
            query = XmlBeans.compileQuery(query);
        } catch (Exception e) {
            System.err.println("Error compiling query: " + e.getMessage());
            System.exit(1);
            return;
        }
        File[] files = cl.getFiles();
        for (int i = 0; i < files.length; ++i) {
            XmlObject x;
            try {
                if (verbose) {
                    int ch;
                    FileInputStream is = new FileInputStream(files[i]);
                    while ((ch = ((InputStream)is).read()) >= 0) {
                        System.out.write(ch);
                    }
                    ((InputStream)is).close();
                    System.out.println();
                }
                x = XmlObject.Factory.parse(files[i]);
            } catch (Throwable e) {
                System.err.println("Error parsing instance: " + e.getMessage());
                System.exit(1);
                return;
            }
            if (verbose) {
                System.out.println("Executing Query...");
                System.err.println();
            }
            XmlObject[] result = null;
            try {
                result = x.execQuery(query);
            } catch (Throwable e) {
                System.err.println("Error executing query: " + e.getMessage());
                System.exit(1);
                return;
            }
            if (verbose) {
                System.out.println("Query Result:");
            }
            XmlOptions opts = new XmlOptions();
            opts.setSaveOuter();
            if (pretty) {
                opts.setSavePrettyPrint();
            }
            for (int j = 0; j < result.length; ++j) {
                result[j].save(System.out, opts);
                System.out.println();
            }
        }
    }
}

