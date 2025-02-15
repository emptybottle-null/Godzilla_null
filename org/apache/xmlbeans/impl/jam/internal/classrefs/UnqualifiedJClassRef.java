/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.classrefs;

import java.io.StringWriter;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRefContext;

public class UnqualifiedJClassRef
implements JClassRef {
    private static final boolean VERBOSE = false;
    private static final String PREFIX = "[UnqualifiedJClassRef]";
    private String mUnqualifiedClassname;
    private String mQualifiedClassname = null;
    private JClassRefContext mContext;

    public static JClassRef create(String qualifiedClassname, JClassRefContext ctx) {
        throw new IllegalStateException("Unqualified names currently disabled.");
    }

    private UnqualifiedJClassRef(String ucname, JClassRefContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("null ctx");
        }
        if (ucname == null) {
            throw new IllegalArgumentException("null ucname");
        }
        this.mContext = ctx;
        this.mUnqualifiedClassname = ucname;
    }

    @Override
    public JClass getRefClass() {
        return this.mContext.getClassLoader().loadClass(this.getQualifiedName());
    }

    @Override
    public String getQualifiedName() {
        String candidateName;
        if (this.mQualifiedClassname != null) {
            return this.mQualifiedClassname;
        }
        int arrayDimensions = 0;
        int bracket = this.mUnqualifiedClassname.indexOf(91);
        if (bracket != -1) {
            candidateName = this.mUnqualifiedClassname.substring(0, bracket);
            do {
                ++arrayDimensions;
            } while ((bracket = this.mUnqualifiedClassname.indexOf(91, bracket + 1)) != -1);
        } else {
            candidateName = this.mUnqualifiedClassname;
        }
        String name = this.qualifyName(candidateName);
        if (name == null) {
            throw new IllegalStateException("unable to handle unqualified java type reference '" + candidateName + " [" + this.mUnqualifiedClassname + "]'. " + "This is still partially NYI.");
        }
        if (arrayDimensions > 0) {
            StringWriter out = new StringWriter();
            for (int i = 0; i < arrayDimensions; ++i) {
                out.write(91);
            }
            out.write(76);
            out.write(name);
            out.write(59);
            this.mQualifiedClassname = out.toString();
        } else {
            this.mQualifiedClassname = name;
        }
        return this.mQualifiedClassname;
    }

    private String qualifyName(String ucname) {
        String out = null;
        out = this.checkExplicitImport(ucname);
        if (out != null) {
            return out;
        }
        out = this.checkJavaLang(ucname);
        if (out != null) {
            return out;
        }
        out = this.checkSamePackage(ucname);
        if (out != null) {
            return out;
        }
        out = this.checkAlreadyQualified(ucname);
        if (out != null) {
            return out;
        }
        return null;
    }

    private String checkSamePackage(String ucname) {
        String name = this.mContext.getPackageName() + "." + ucname;
        JClass clazz = this.mContext.getClassLoader().loadClass(name);
        return clazz.isUnresolvedType() ? null : clazz.getQualifiedName();
    }

    private String checkJavaLang(String ucname) {
        String name = "java.lang." + ucname;
        JClass clazz = this.mContext.getClassLoader().loadClass(name);
        return clazz.isUnresolvedType() ? null : clazz.getQualifiedName();
    }

    private String checkAlreadyQualified(String ucname) {
        JClass clazz = this.mContext.getClassLoader().loadClass(ucname);
        return clazz.isUnresolvedType() ? null : clazz.getQualifiedName();
    }

    private String checkExplicitImport(String ucname) {
        String[] imports = this.mContext.getImportSpecs();
        for (int i = 0; i < imports.length; ++i) {
            String last = UnqualifiedJClassRef.lastSegment(imports[i]);
            if (!last.equals(ucname)) continue;
            return imports[i];
        }
        return null;
    }

    private static String lastSegment(String s) {
        int lastDot = s.lastIndexOf(".");
        if (lastDot == -1) {
            return s;
        }
        return s.substring(lastDot + 1);
    }

    private static String firstSegment(String s) {
        int lastDot = s.indexOf(".");
        if (lastDot == -1) {
            return s;
        }
        return s.substring(0, lastDot);
    }
}

