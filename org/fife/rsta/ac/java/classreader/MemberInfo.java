/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader;

import java.io.DataInputStream;
import java.io.IOException;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.Util;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;
import org.fife.rsta.ac.java.classreader.attributes.Signature;

public abstract class MemberInfo {
    protected ClassFile cf;
    private int accessFlags;
    private boolean deprecated;
    public static final String DEPRECATED = "Deprecated";
    public static final String SIGNATURE = "Signature";
    public static final String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";

    protected MemberInfo(ClassFile cf, int accessFlags) {
        this.cf = cf;
        this.accessFlags = accessFlags;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public ClassFile getClassFile() {
        return this.cf;
    }

    public abstract String getName();

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public abstract String getDescriptor();

    public boolean isFinal() {
        return (this.getAccessFlags() & 0x10) > 0;
    }

    public boolean isStatic() {
        return (this.getAccessFlags() & 8) > 0;
    }

    protected AttributeInfo readAttribute(DataInputStream in, String attrName, int attrLength) throws IOException {
        AttributeInfo ai = null;
        if (DEPRECATED.equals(attrName)) {
            this.deprecated = true;
        } else if (SIGNATURE.equals(attrName)) {
            int signatureIndex = in.readUnsignedShort();
            String typeSig = this.cf.getUtf8ValueFromConstantPool(signatureIndex);
            ai = new Signature(this.cf, typeSig);
        } else if (RUNTIME_VISIBLE_ANNOTATIONS.equals(attrName)) {
            Util.skipBytes(in, attrLength);
        } else {
            ai = AttributeInfo.readUnsupportedAttribute(this.cf, in, attrName, attrLength);
        }
        return ai;
    }
}

