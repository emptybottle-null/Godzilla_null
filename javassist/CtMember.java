/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import javassist.CtClass;
import javassist.CtClassType;
import javassist.Modifier;

public abstract class CtMember {
    CtMember next;
    protected CtClass declaringClass;

    protected CtMember(CtClass clazz) {
        this.declaringClass = clazz;
        this.next = null;
    }

    final CtMember next() {
        return this.next;
    }

    void nameReplaced() {
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append("@");
        buffer.append(Integer.toHexString(this.hashCode()));
        buffer.append("[");
        buffer.append(Modifier.toString(this.getModifiers()));
        this.extendToString(buffer);
        buffer.append("]");
        return buffer.toString();
    }

    protected abstract void extendToString(StringBuffer var1);

    public CtClass getDeclaringClass() {
        return this.declaringClass;
    }

    public boolean visibleFrom(CtClass clazz) {
        int mod = this.getModifiers();
        if (Modifier.isPublic(mod)) {
            return true;
        }
        if (Modifier.isPrivate(mod)) {
            return clazz == this.declaringClass;
        }
        String declName = this.declaringClass.getPackageName();
        String fromName = clazz.getPackageName();
        boolean visible = declName == null ? fromName == null : declName.equals(fromName);
        if (!visible && Modifier.isProtected(mod)) {
            return clazz.subclassOf(this.declaringClass);
        }
        return visible;
    }

    public abstract int getModifiers();

    public abstract void setModifiers(int var1);

    public boolean hasAnnotation(Class<?> clz) {
        return this.hasAnnotation(clz.getName());
    }

    public abstract boolean hasAnnotation(String var1);

    public abstract Object getAnnotation(Class<?> var1) throws ClassNotFoundException;

    public abstract Object[] getAnnotations() throws ClassNotFoundException;

    public abstract Object[] getAvailableAnnotations();

    public abstract String getName();

    public abstract String getSignature();

    public abstract String getGenericSignature();

    public abstract void setGenericSignature(String var1);

    public abstract byte[] getAttribute(String var1);

    public abstract void setAttribute(String var1, byte[] var2);

    static class Cache
    extends CtMember {
        private CtMember methodTail = this;
        private CtMember consTail = this;
        private CtMember fieldTail = this;

        @Override
        protected void extendToString(StringBuffer buffer) {
        }

        @Override
        public boolean hasAnnotation(String clz) {
            return false;
        }

        @Override
        public Object getAnnotation(Class<?> clz) throws ClassNotFoundException {
            return null;
        }

        @Override
        public Object[] getAnnotations() throws ClassNotFoundException {
            return null;
        }

        @Override
        public byte[] getAttribute(String name) {
            return null;
        }

        @Override
        public Object[] getAvailableAnnotations() {
            return null;
        }

        @Override
        public int getModifiers() {
            return 0;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getSignature() {
            return null;
        }

        @Override
        public void setAttribute(String name, byte[] data) {
        }

        @Override
        public void setModifiers(int mod) {
        }

        @Override
        public String getGenericSignature() {
            return null;
        }

        @Override
        public void setGenericSignature(String sig) {
        }

        Cache(CtClassType decl) {
            super(decl);
            this.fieldTail.next = this;
        }

        CtMember methodHead() {
            return this;
        }

        CtMember lastMethod() {
            return this.methodTail;
        }

        CtMember consHead() {
            return this.methodTail;
        }

        CtMember lastCons() {
            return this.consTail;
        }

        CtMember fieldHead() {
            return this.consTail;
        }

        CtMember lastField() {
            return this.fieldTail;
        }

        void addMethod(CtMember method) {
            method.next = this.methodTail.next;
            this.methodTail.next = method;
            if (this.methodTail == this.consTail) {
                this.consTail = method;
                if (this.methodTail == this.fieldTail) {
                    this.fieldTail = method;
                }
            }
            this.methodTail = method;
        }

        void addConstructor(CtMember cons) {
            cons.next = this.consTail.next;
            this.consTail.next = cons;
            if (this.consTail == this.fieldTail) {
                this.fieldTail = cons;
            }
            this.consTail = cons;
        }

        void addField(CtMember field) {
            field.next = this;
            this.fieldTail.next = field;
            this.fieldTail = field;
        }

        static int count(CtMember head, CtMember tail) {
            int n = 0;
            while (head != tail) {
                ++n;
                head = head.next;
            }
            return n;
        }

        void remove(CtMember mem) {
            CtMember node;
            CtMember m = this;
            while ((node = m.next) != this) {
                if (node == mem) {
                    m.next = node.next;
                    if (node == this.methodTail) {
                        this.methodTail = m;
                    }
                    if (node == this.consTail) {
                        this.consTail = m;
                    }
                    if (node != this.fieldTail) break;
                    this.fieldTail = m;
                    break;
                }
                m = m.next;
            }
        }
    }
}

