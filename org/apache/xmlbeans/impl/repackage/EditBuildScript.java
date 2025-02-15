/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class EditBuildScript {
    public static void main(String[] args) throws Exception {
        String tokenStr;
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        args[0] = args[0].replace('/', File.separatorChar);
        File buildFile = new File(args[0]);
        StringBuffer sb = EditBuildScript.readFile(buildFile);
        int i = sb.indexOf(tokenStr = "<property name=\"" + args[1] + "\" value=\"");
        if (i < 0) {
            throw new IllegalArgumentException("Can't find token: " + tokenStr);
        }
        int j = i + tokenStr.length();
        while (sb.charAt(j) != '\"') {
            ++j;
        }
        sb.replace(i + tokenStr.length(), j, args[2]);
        EditBuildScript.writeFile(buildFile, sb);
    }

    static StringBuffer readFile(File f) throws IOException {
        FileInputStream in = new FileInputStream(f);
        InputStreamReader r = new InputStreamReader(in);
        StringWriter w = new StringWriter();
        EditBuildScript.copy(r, w);
        w.close();
        ((Reader)r).close();
        ((InputStream)in).close();
        return w.getBuffer();
    }

    static void writeFile(File f, StringBuffer chars) throws IOException {
        FileOutputStream out = new FileOutputStream(f);
        OutputStreamWriter w = new OutputStreamWriter(out);
        StringReader r = new StringReader(chars.toString());
        EditBuildScript.copy(r, w);
        ((Reader)r).close();
        ((Writer)w).close();
        ((OutputStream)out).close();
    }

    static void copy(Reader r, Writer w) throws IOException {
        int n;
        char[] buffer = new char[16384];
        while ((n = r.read(buffer, 0, buffer.length)) >= 0) {
            w.write(buffer, 0, n);
        }
    }
}

