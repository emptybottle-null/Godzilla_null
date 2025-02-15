/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.provider;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.JamService;
import org.apache.xmlbeans.impl.jam.JamServiceFactory;
import org.apache.xmlbeans.impl.jam.JamServiceParams;
import org.apache.xmlbeans.impl.jam.internal.JamClassLoaderImpl;
import org.apache.xmlbeans.impl.jam.internal.JamServiceContextImpl;
import org.apache.xmlbeans.impl.jam.internal.JamServiceImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.parser.ParserClassBuilder;
import org.apache.xmlbeans.impl.jam.internal.reflect.ReflectClassBuilder;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.CompositeJamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;
import org.apache.xmlbeans.impl.jam.provider.JamServiceContext;
import org.apache.xmlbeans.impl.jam.provider.ResourcePath;

public class JamServiceFactoryImpl
extends JamServiceFactory {
    public static final String USE_NEW_PARSER = "JamServiceFactoryImpl.use-new-parser";
    private static final String PREFIX = "[JamServiceFactoryImpl]";

    @Override
    public JamServiceParams createServiceParams() {
        return new JamServiceContextImpl();
    }

    @Override
    public JamService createService(JamServiceParams jsps) throws IOException {
        if (!(jsps instanceof JamServiceContextImpl)) {
            throw new IllegalArgumentException("JamServiceParams must be instantiated by this JamServiceFactory.");
        }
        JamClassLoader clToUse = this.createClassLoader((JamServiceContextImpl)jsps);
        ((JamServiceContextImpl)jsps).setClassLoader(clToUse);
        return new JamServiceImpl((ElementContext)((Object)jsps), this.getSpecifiedClasses((JamServiceContextImpl)jsps));
    }

    @Override
    public JamClassLoader createSystemJamClassLoader() {
        JamServiceParams params = this.createServiceParams();
        params.setUseSystemClasspath(true);
        try {
            JamService service = this.createService(params);
            return service.getClassLoader();
        } catch (IOException reallyUnexpected) {
            reallyUnexpected.printStackTrace();
            throw new IllegalStateException(reallyUnexpected.getMessage());
        }
    }

    @Override
    public JamClassLoader createJamClassLoader(ClassLoader cl) {
        JamServiceParams params = this.createServiceParams();
        params.setUseSystemClasspath(false);
        params.setPropertyInitializer(null);
        params.addClassLoader(cl);
        try {
            JamService service = this.createService(params);
            return service.getClassLoader();
        } catch (IOException reallyUnexpected) {
            reallyUnexpected.printStackTrace();
            throw new IllegalStateException(reallyUnexpected.getMessage());
        }
    }

    protected String[] getSpecifiedClasses(JamServiceContext params) throws IOException {
        return params.getAllClassnames();
    }

    protected JamClassLoader createClassLoader(JamServiceContext ctx) throws IOException {
        JamClassBuilder builder = this.createBuilder(ctx);
        return new JamClassLoaderImpl((ElementContext)((Object)ctx), builder, ctx.getInitializer());
    }

    protected JamClassBuilder createBuilder(JamServiceContext ctx) throws IOException {
        JamLogger log = ctx.getLogger();
        ArrayList<JamClassBuilder> builders = new ArrayList<JamClassBuilder>();
        JamClassBuilder b = ctx.getBaseBuilder();
        if (b != null) {
            builders.add(b);
        }
        b = this.createSourceBuilder(ctx);
        if (log.isVerbose(this)) {
            log.verbose("added classbuilder for sources");
        }
        if (b != null) {
            builders.add(b);
        }
        b = this.createClassfileBuilder(ctx);
        if (log.isVerbose(this)) {
            log.verbose("added classbuilder for custom classpath");
        }
        if (b != null) {
            builders.add(b);
        }
        ClassLoader[] cls = ctx.getReflectionClassLoaders();
        for (int i = 0; i < cls.length; ++i) {
            if (log.isVerbose(this)) {
                log.verbose("added classbuilder for classloader " + cls[i].getClass());
            }
            builders.add(new ReflectClassBuilder(cls[i]));
        }
        JamClassBuilder[] barray = new JamClassBuilder[builders.size()];
        builders.toArray(barray);
        CompositeJamClassBuilder out = new CompositeJamClassBuilder(barray);
        ((JamClassBuilder)out).init((ElementContext)((Object)ctx));
        if (log.isVerbose(this)) {
            log.verbose("returning a composite of " + barray.length + " class builders.");
            MClass c = ((JamClassBuilder)out).build("java.lang", "Object");
            c = ((JamClassBuilder)out).build("javax.ejb", "SessionBean");
        }
        return out;
    }

    protected JamClassBuilder createSourceBuilder(JamServiceContext ctx) throws IOException {
        File[] sources = ctx.getSourceFiles();
        if (sources == null || sources.length == 0) {
            if (ctx.isVerbose(this)) {
                ctx.verbose("[JamServiceFactoryImpl]no source files present, skipping source ClassBuilder");
            }
            return null;
        }
        if (ctx.getProperty(USE_NEW_PARSER) == null) {
            return new JavadocClassBuilder();
        }
        return new ParserClassBuilder(ctx);
    }

    protected JamClassBuilder createClassfileBuilder(JamServiceContext jp) throws IOException {
        ResourcePath cp = jp.getInputClasspath();
        if (cp == null) {
            return null;
        }
        URL[] urls = cp.toUrlPath();
        URLClassLoader cl = new URLClassLoader(urls);
        return new ReflectClassBuilder(cl);
    }
}

