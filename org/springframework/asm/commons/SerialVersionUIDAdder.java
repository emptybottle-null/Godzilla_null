/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm.commons;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.asm.ClassAdapter;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.commons.SerialVersionUIDAdder$Item;

public class SerialVersionUIDAdder
extends ClassAdapter {
    protected boolean computeSVUID;
    protected boolean hasSVUID;
    protected int access;
    protected String name;
    protected String[] interfaces;
    protected Collection svuidFields = new ArrayList();
    protected boolean hasStaticInitializer;
    protected Collection svuidConstructors = new ArrayList();
    protected Collection svuidMethods = new ArrayList();

    public SerialVersionUIDAdder(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    public void visit(int n, int n2, String string, String string2, String string3, String[] stringArray) {
        boolean bl = this.computeSVUID = (n2 & 0x200) == 0;
        if (this.computeSVUID) {
            this.name = string;
            this.access = n2;
            this.interfaces = stringArray;
        }
        super.visit(n, n2, string, string2, string3, stringArray);
    }

    public MethodVisitor visitMethod(int n, String string, String string2, String string3, String[] stringArray) {
        if (this.computeSVUID) {
            if (string.equals("<clinit>")) {
                this.hasStaticInitializer = true;
            }
            int n2 = n & 0xD3F;
            if ((n & 2) == 0) {
                if (string.equals("<init>")) {
                    this.svuidConstructors.add(new SerialVersionUIDAdder$Item(string, n2, string2));
                } else if (!string.equals("<clinit>")) {
                    this.svuidMethods.add(new SerialVersionUIDAdder$Item(string, n2, string2));
                }
            }
        }
        return this.cv.visitMethod(n, string, string2, string3, stringArray);
    }

    public FieldVisitor visitField(int n, String string, String string2, String string3, Object object) {
        if (this.computeSVUID) {
            if (string.equals("serialVersionUID")) {
                this.computeSVUID = false;
                this.hasSVUID = true;
            }
            int n2 = n & 0xDF;
            if ((n & 2) == 0 || (n & 0x88) == 0) {
                this.svuidFields.add(new SerialVersionUIDAdder$Item(string, n2, string2));
            }
        }
        return super.visitField(n, string, string2, string3, object);
    }

    public void visitEnd() {
        if (this.computeSVUID && !this.hasSVUID) {
            try {
                this.cv.visitField(24, "serialVersionUID", "J", null, new Long(this.computeSVUID()));
            } catch (Throwable throwable) {
                throw new RuntimeException("Error while computing SVUID for " + this.name, throwable);
            }
        }
        super.visitEnd();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected long computeSVUID() throws IOException {
        if (this.hasSVUID) {
            return 0L;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        FilterOutputStream filterOutputStream = null;
        long l = 0L;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            filterOutputStream = new DataOutputStream(byteArrayOutputStream);
            ((DataOutputStream)filterOutputStream).writeUTF(this.name.replace('/', '.'));
            ((DataOutputStream)filterOutputStream).writeInt(this.access & 0x611);
            Arrays.sort(this.interfaces);
            for (int i = 0; i < this.interfaces.length; ++i) {
                ((DataOutputStream)filterOutputStream).writeUTF(this.interfaces[i].replace('/', '.'));
            }
            this.writeItems(this.svuidFields, (DataOutputStream)filterOutputStream, false);
            if (this.hasStaticInitializer) {
                ((DataOutputStream)filterOutputStream).writeUTF("<clinit>");
                ((DataOutputStream)filterOutputStream).writeInt(8);
                ((DataOutputStream)filterOutputStream).writeUTF("()V");
            }
            this.writeItems(this.svuidConstructors, (DataOutputStream)filterOutputStream, true);
            this.writeItems(this.svuidMethods, (DataOutputStream)filterOutputStream, true);
            ((DataOutputStream)filterOutputStream).flush();
            byte[] byArray = this.computeSHAdigest(byteArrayOutputStream.toByteArray());
            for (int i = Math.min(byArray.length, 8) - 1; i >= 0; --i) {
                l = l << 8 | (long)(byArray[i] & 0xFF);
            }
        } finally {
            if (filterOutputStream != null) {
                filterOutputStream.close();
            }
        }
        return l;
    }

    protected byte[] computeSHAdigest(byte[] byArray) {
        try {
            return MessageDigest.getInstance("SHA").digest(byArray);
        } catch (Exception exception) {
            throw new UnsupportedOperationException(exception);
        }
    }

    private void writeItems(Collection collection, DataOutputStream dataOutputStream, boolean bl) throws IOException {
        int n = collection.size();
        Object[] objectArray = collection.toArray(new SerialVersionUIDAdder$Item[n]);
        Arrays.sort(objectArray);
        for (int i = 0; i < n; ++i) {
            dataOutputStream.writeUTF(((SerialVersionUIDAdder$Item)objectArray[i]).name);
            dataOutputStream.writeInt(((SerialVersionUIDAdder$Item)objectArray[i]).access);
            dataOutputStream.writeUTF(bl ? ((SerialVersionUIDAdder$Item)objectArray[i]).desc.replace('/', '.') : ((SerialVersionUIDAdder$Item)objectArray[i]).desc);
        }
    }
}

