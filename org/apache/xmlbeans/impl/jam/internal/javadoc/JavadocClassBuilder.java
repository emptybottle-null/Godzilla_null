/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.javadoc.ClassDoc
 *  com.sun.javadoc.ConstructorDoc
 *  com.sun.javadoc.Doc
 *  com.sun.javadoc.ExecutableMemberDoc
 *  com.sun.javadoc.FieldDoc
 *  com.sun.javadoc.MethodDoc
 *  com.sun.javadoc.Parameter
 *  com.sun.javadoc.ProgramElementDoc
 *  com.sun.javadoc.RootDoc
 *  com.sun.javadoc.SourcePosition
 *  com.sun.javadoc.Tag
 *  com.sun.javadoc.Type
 */
package org.apache.xmlbeans.impl.jam.internal.javadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.SourcePosition;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.xmlbeans.impl.jam.annotation.JavadocTagParser;
import org.apache.xmlbeans.impl.jam.internal.JamServiceContextImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.PrimitiveClassImpl;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocRunner;
import org.apache.xmlbeans.impl.jam.internal.javadoc.JavadocTigerDelegate;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MInvokable;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.mutable.MParameter;
import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;
import org.apache.xmlbeans.impl.jam.provider.JamClassBuilder;
import org.apache.xmlbeans.impl.jam.provider.JamClassPopulator;
import org.apache.xmlbeans.impl.jam.provider.JamServiceContext;

public class JavadocClassBuilder
extends JamClassBuilder
implements JamClassPopulator {
    public static final String ARGS_PROPERTY = "javadoc.args";
    public static final String PARSETAGS_PROPERTY = "javadoc.parsetags";
    private RootDoc mRootDoc = null;
    private JavadocTigerDelegate mTigerDelegate = null;
    private JavadocTagParser mTagParser = null;
    private boolean mParseTags = true;

    @Override
    public void init(ElementContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("null context");
        }
        super.init(ctx);
        this.getLogger().verbose("init()", (Object)this);
        this.initDelegate(ctx);
        this.initJavadoc((JamServiceContext)((Object)ctx));
    }

    @Override
    public MClass build(String packageName, String className) {
        int i;
        String loadme;
        ClassDoc cd;
        this.assertInitialized();
        if (this.getLogger().isVerbose(this)) {
            this.getLogger().verbose("trying to build '" + packageName + "' '" + className + "'");
        }
        if ((cd = this.mRootDoc.classNamed(loadme = packageName.trim().length() > 0 ? packageName + '.' + className : className)) == null) {
            if (this.getLogger().isVerbose(this)) {
                this.getLogger().verbose("no ClassDoc for " + loadme);
            }
            return null;
        }
        ArrayList<String> importSpecs = null;
        ClassDoc[] imported = cd.importedClasses();
        if (imported != null) {
            importSpecs = new ArrayList<String>();
            for (i = 0; i < imported.length; ++i) {
                importSpecs.add(JavadocClassBuilder.getFdFor((Type)imported[i]));
            }
        }
        if ((imported = cd.importedPackages()) != null) {
            if (importSpecs == null) {
                importSpecs = new ArrayList();
            }
            for (i = 0; i < imported.length; ++i) {
                importSpecs.add(imported[i].name() + ".*");
            }
        }
        String[] importSpecsArray = null;
        if (importSpecs != null) {
            importSpecsArray = new String[importSpecs.size()];
            importSpecs.toArray(importSpecsArray);
        }
        MClass out = this.createClassToBuild(packageName, className, importSpecsArray, this);
        out.setArtifact(cd);
        return out;
    }

    @Override
    public void populate(MClass dest) {
        ClassDoc s;
        if (dest == null) {
            throw new IllegalArgumentException("null dest");
        }
        this.assertInitialized();
        ClassDoc src = (ClassDoc)dest.getArtifact();
        if (src == null) {
            throw new IllegalStateException("null artifact");
        }
        dest.setModifiers(src.modifierSpecifier());
        dest.setIsInterface(src.isInterface());
        if (this.mTigerDelegate != null) {
            dest.setIsEnumType(this.mTigerDelegate.isEnum(src));
        }
        if ((s = src.superclass()) != null) {
            dest.setSuperclass(JavadocClassBuilder.getFdFor((Type)s));
        }
        ClassDoc[] ints = src.interfaces();
        for (int i = 0; i < ints.length; ++i) {
            dest.addInterface(JavadocClassBuilder.getFdFor((Type)ints[i]));
        }
        FieldDoc[] fields = src.fields();
        for (int i = 0; i < fields.length; ++i) {
            this.populate(dest.addNewField(), fields[i]);
        }
        ConstructorDoc[] ctors = src.constructors();
        for (int i = 0; i < ctors.length; ++i) {
            this.populate(dest.addNewConstructor(), (ExecutableMemberDoc)ctors[i]);
        }
        MethodDoc[] methods = src.methods();
        for (int i = 0; i < methods.length; ++i) {
            this.populate(dest.addNewMethod(), methods[i]);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.populateAnnotationTypeIfNecessary(src, dest, this);
        }
        this.addAnnotations(dest, (ProgramElementDoc)src);
        JavadocClassBuilder.addSourcePosition((MElement)dest, (Doc)src);
        ClassDoc[] inners = src.innerClasses();
        if (inners != null) {
            for (int i = 0; i < inners.length; ++i) {
                MClass inner = dest.addNewInnerClass(inners[i].typeName());
                inner.setArtifact(inners[i]);
                this.populate(inner);
            }
        }
    }

    public MMethod addMethod(MClass dest, MethodDoc doc) {
        MMethod out = dest.addNewMethod();
        this.populate(out, doc);
        return out;
    }

    private void initDelegate(ElementContext ctx) {
        this.mTigerDelegate = JavadocTigerDelegate.create(ctx);
    }

    private void initJavadoc(JamServiceContext serviceContext) {
        String classPath;
        File[] files;
        this.mTagParser = serviceContext.getTagParser();
        String pct = serviceContext.getProperty(PARSETAGS_PROPERTY);
        if (pct != null) {
            this.mParseTags = Boolean.valueOf(pct);
            this.getLogger().verbose("mParseTags=" + this.mParseTags, (Object)this);
        }
        try {
            files = serviceContext.getSourceFiles();
        } catch (IOException ioe) {
            this.getLogger().error(ioe);
            return;
        }
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("No source files in context.");
        }
        String sourcePath = serviceContext.getInputSourcepath() == null ? null : serviceContext.getInputSourcepath().toString();
        String string = classPath = serviceContext.getInputClasspath() == null ? null : serviceContext.getInputClasspath().toString();
        if (this.getLogger().isVerbose(this)) {
            this.getLogger().verbose("sourcePath =" + sourcePath);
            this.getLogger().verbose("classPath =" + classPath);
            for (int i = 0; i < files.length; ++i) {
                this.getLogger().verbose("including '" + files[i] + "'");
            }
        }
        JavadocRunner jdr = JavadocRunner.newInstance();
        try {
            PrintWriter out = null;
            if (this.getLogger().isVerbose(this)) {
                out = new PrintWriter(System.out);
            }
            this.mRootDoc = jdr.run(files, out, sourcePath, classPath, this.getJavadocArgs(serviceContext), this.getLogger());
            if (this.mRootDoc == null) {
                this.getLogger().error("Javadoc returned a null root");
            } else {
                if (this.getLogger().isVerbose(this)) {
                    this.getLogger().verbose(" received " + this.mRootDoc.classes().length + " ClassDocs from javadoc: ");
                }
                ClassDoc[] classes = this.mRootDoc.classes();
                for (int i = 0; i < classes.length; ++i) {
                    if (classes[i].containingClass() != null) continue;
                    if (this.getLogger().isVerbose(this)) {
                        this.getLogger().verbose("..." + classes[i].qualifiedName());
                    }
                    ((JamServiceContextImpl)serviceContext).includeClass(JavadocClassBuilder.getFdFor((Type)classes[i]));
                }
            }
        } catch (FileNotFoundException e) {
            this.getLogger().error(e);
        } catch (IOException e) {
            this.getLogger().error(e);
        }
    }

    private void populate(MField dest, FieldDoc src) {
        dest.setArtifact(src);
        dest.setSimpleName(src.name());
        dest.setType(JavadocClassBuilder.getFdFor(src.type()));
        dest.setModifiers(src.modifierSpecifier());
        this.addAnnotations(dest, (ProgramElementDoc)src);
        JavadocClassBuilder.addSourcePosition((MElement)dest, (Doc)src);
    }

    private void populate(MMethod dest, MethodDoc src) {
        if (dest == null) {
            throw new IllegalArgumentException("null dest");
        }
        if (src == null) {
            throw new IllegalArgumentException("null src");
        }
        this.populate((MInvokable)dest, (ExecutableMemberDoc)src);
        dest.setReturnType(JavadocClassBuilder.getFdFor(src.returnType()));
    }

    private void populate(MInvokable dest, ExecutableMemberDoc src) {
        if (dest == null) {
            throw new IllegalArgumentException("null dest");
        }
        if (src == null) {
            throw new IllegalArgumentException("null src");
        }
        dest.setArtifact(src);
        dest.setSimpleName(src.name());
        dest.setModifiers(src.modifierSpecifier());
        ClassDoc[] exceptions = src.thrownExceptions();
        for (int i = 0; i < exceptions.length; ++i) {
            dest.addException(JavadocClassBuilder.getFdFor((Type)exceptions[i]));
        }
        Parameter[] params = src.parameters();
        for (int i = 0; i < params.length; ++i) {
            this.populate(dest.addNewParameter(), src, params[i]);
        }
        this.addAnnotations(dest, (ProgramElementDoc)src);
        JavadocClassBuilder.addSourcePosition((MElement)dest, (Doc)src);
    }

    private void populate(MParameter dest, ExecutableMemberDoc method, Parameter src) {
        dest.setArtifact(src);
        dest.setSimpleName(src.name());
        dest.setType(JavadocClassBuilder.getFdFor(src.type()));
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, method, src);
        }
    }

    private String[] getJavadocArgs(JamServiceContext ctx) {
        String prop = ctx.getProperty(ARGS_PROPERTY);
        if (prop == null) {
            return null;
        }
        StringTokenizer t = new StringTokenizer(prop);
        String[] out = new String[t.countTokens()];
        int i = 0;
        while (t.hasMoreTokens()) {
            out[i++] = t.nextToken();
        }
        return out;
    }

    private void addAnnotations(MAnnotatedElement dest, ProgramElementDoc src) {
        String comments = src.commentText();
        if (comments != null) {
            dest.createComment().setText(comments);
        }
        Tag[] tags = src.tags();
        for (int i = 0; i < tags.length; ++i) {
            if (this.getLogger().isVerbose(this)) {
                this.getLogger().verbose("...'" + tags[i].name() + "' ' " + tags[i].text());
            }
            this.mTagParser.parse(dest, tags[i]);
        }
        if (this.mTigerDelegate != null) {
            this.mTigerDelegate.extractAnnotations(dest, src);
        }
    }

    public static String getFdFor(Type t) {
        if (t == null) {
            throw new IllegalArgumentException("null type");
        }
        String dim = t.dimension();
        if (dim == null || dim.length() == 0) {
            ClassDoc cd = t.asClassDoc();
            if (cd != null) {
                ClassDoc outer = cd.containingClass();
                if (outer == null) {
                    return cd.qualifiedName();
                }
                String simpleName = cd.name();
                simpleName = simpleName.substring(simpleName.lastIndexOf(46) + 1);
                return outer.qualifiedName() + '$' + simpleName;
            }
            return t.qualifiedTypeName();
        }
        StringWriter out = new StringWriter();
        int iL = dim.length() / 2;
        for (int i = 0; i < iL; ++i) {
            out.write("[");
        }
        String primFd = PrimitiveClassImpl.getPrimitiveClassForName(t.qualifiedTypeName());
        if (primFd != null) {
            out.write(primFd);
        } else {
            out.write("L");
            if (t.asClassDoc() != null) {
                out.write(t.asClassDoc().qualifiedName());
            } else {
                out.write(t.qualifiedTypeName());
            }
            out.write(";");
        }
        return out.toString();
    }

    public static void addSourcePosition(MElement dest, Doc src) {
        SourcePosition pos = src.position();
        if (pos != null) {
            JavadocClassBuilder.addSourcePosition(dest, pos);
        }
    }

    public static void addSourcePosition(MElement dest, SourcePosition pos) {
        MSourcePosition sp = dest.createSourcePosition();
        sp.setColumn(pos.column());
        sp.setLine(pos.line());
        File f = pos.file();
        if (f != null) {
            sp.setSourceURI(f.toURI());
        }
    }
}

