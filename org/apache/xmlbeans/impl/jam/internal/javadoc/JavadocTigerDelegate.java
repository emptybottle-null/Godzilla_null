/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.ClassDoc
 *  com.sun.javadoc.ExecutableMemberDoc
 *  com.sun.javadoc.Parameter
 *  com.sun.javadoc.ProgramElementDoc
 */
package org.apache.xmlbeans.impl.jam.internal.javadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;
import org.apache.xmlbeans.impl.jam.internal.TigerDelegate;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocClassBuilder;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;

public abstract class JavadocTigerDelegate
extends TigerDelegate {
    private static final String JAVADOC_DELEGATE_IMPL = "org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocTigerDelegateImpl_150";
    public static final String ANNOTATION_DEFAULTS_DISABLED_PROPERTY = "ANNOTATION_DEFAULTS_DISABLED_PROPERTY";

    public static JavadocTigerDelegate create(JamLogger logger) {
        if (!JavadocTigerDelegate.isTigerJavadocAvailable(logger)) {
            return null;
        }
        try {
            JavadocTigerDelegate out = (JavadocTigerDelegate)Class.forName(JAVADOC_DELEGATE_IMPL).newInstance();
            out.init(logger);
            return out;
        } catch (ClassNotFoundException e) {
            JavadocTigerDelegate.issue14BuildWarning(e, logger);
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        }
        return null;
    }

    public static JavadocTigerDelegate create(ElementContext ctx) {
        if (!JavadocTigerDelegate.isTigerJavadocAvailable(ctx.getLogger())) {
            return null;
        }
        try {
            JavadocTigerDelegate out = (JavadocTigerDelegate)Class.forName(JAVADOC_DELEGATE_IMPL).newInstance();
            out.init(ctx);
            return out;
        } catch (ClassNotFoundException e) {
            ctx.getLogger().error(e);
        } catch (IllegalAccessException e) {
            ctx.getLogger().error(e);
        } catch (InstantiationException e) {
            ctx.getLogger().error(e);
        }
        return null;
    }

    public abstract boolean isEnum(ClassDoc var1);

    @Override
    public abstract void init(JamLogger var1);

    public abstract void populateAnnotationTypeIfNecessary(ClassDoc var1, MClass var2, JavadocClassBuilder var3);

    public abstract void extractAnnotations(MAnnotatedElement var1, ProgramElementDoc var2);

    public abstract void extractAnnotations(MAnnotatedElement var1, ExecutableMemberDoc var2, Parameter var3);
}

