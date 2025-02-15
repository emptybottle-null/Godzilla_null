/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.fife.rsta.ac.OutputCollector;

public class IOUtil {
    private static Map<String, String> DEFAULT_ENV;

    private IOUtil() {
    }

    private static Map<String, String> getDefaultEnvMap() {
        if (DEFAULT_ENV != null) {
            return DEFAULT_ENV;
        }
        try {
            DEFAULT_ENV = System.getenv();
        } catch (SecurityException e) {
            DEFAULT_ENV = Collections.emptyMap();
        }
        return DEFAULT_ENV;
    }

    public static String getEnvSafely(String var) {
        String value = null;
        try {
            value = System.getenv(var);
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return value;
    }

    public static String[] getEnvironmentSafely(String[] toAdd) {
        Map<String, String> env = IOUtil.getDefaultEnvMap();
        if (toAdd != null) {
            HashMap<String, String> temp = new HashMap<String, String>(env);
            for (int i = 0; i < toAdd.length; i += 2) {
                temp.put(toAdd[i], toAdd[i + 1]);
            }
            env = temp;
        }
        int count = env.size();
        String[] vars = new String[count];
        int i = 0;
        for (Map.Entry<String, String> entry : env.entrySet()) {
            vars[i++] = entry.getKey() + "=" + entry.getValue();
        }
        return vars;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int waitForProcess(Process p, StringBuilder stdout, StringBuilder stderr) throws IOException {
        InputStream in = p.getInputStream();
        InputStream err = p.getErrorStream();
        Thread t1 = new Thread(new OutputCollector(in, stdout));
        Thread t2 = new Thread(new OutputCollector(err, stderr));
        t1.start();
        t2.start();
        int rc = -1;
        try {
            rc = p.waitFor();
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
            p.destroy();
        } finally {
            in.close();
            err.close();
        }
        return rc;
    }

    public static void main(String[] args) {
        for (String arg : args) {
            String value = IOUtil.getEnvSafely(arg);
            System.out.println(arg + "=" + value);
        }
    }
}

