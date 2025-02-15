/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.xmlbeans.impl.jam.JamClassIterator;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.JamService;
import org.apache.xmlbeans.impl.jam.JamServiceParams;
import org.apache.xmlbeans.impl.jam.internal.JamPrinter;
import org.apache.xmlbeans.impl.jam.provider.JamServiceFactoryImpl;

public abstract class JamServiceFactory {
    private static final JamServiceFactory DEFAULT = new JamServiceFactoryImpl();

    public static JamServiceFactory getInstance() {
        return DEFAULT;
    }

    protected JamServiceFactory() {
    }

    public abstract JamServiceParams createServiceParams();

    public abstract JamService createService(JamServiceParams var1) throws IOException;

    public abstract JamClassLoader createSystemJamClassLoader();

    public abstract JamClassLoader createJamClassLoader(ClassLoader var1);

    public static void main(String[] args) {
        try {
            JamServiceParams sp = JamServiceFactory.getInstance().createServiceParams();
            for (int i = 0; i < args.length; ++i) {
                sp.includeSourcePattern(new File[]{new File(".")}, args[i]);
            }
            JamService service = JamServiceFactory.getInstance().createService(sp);
            JamPrinter jp = JamPrinter.newInstance();
            PrintWriter out = new PrintWriter(System.out);
            JamClassIterator i = service.getClasses();
            while (i.hasNext()) {
                out.println("-------- ");
                jp.print(i.nextClass(), out);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.flush();
        System.err.flush();
    }
}

