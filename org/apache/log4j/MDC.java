/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.ThreadLocalMap;

public class MDC {
    static final MDC mdc = new MDC();
    static final int HT_SIZE = 7;
    boolean java1 = Loader.isJava1();
    Object tlm;
    private Method removeMethod;

    private MDC() {
        if (!this.java1) {
            this.tlm = new ThreadLocalMap();
        }
        try {
            this.removeMethod = ThreadLocal.class.getMethod("remove", null);
        } catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
    }

    public static void put(String key, Object o) {
        if (mdc != null) {
            mdc.put0(key, o);
        }
    }

    public static Object get(String key) {
        if (mdc != null) {
            return mdc.get0(key);
        }
        return null;
    }

    public static void remove(String key) {
        if (mdc != null) {
            mdc.remove0(key);
        }
    }

    public static Hashtable getContext() {
        if (mdc != null) {
            return mdc.getContext0();
        }
        return null;
    }

    public static void clear() {
        if (mdc != null) {
            mdc.clear0();
        }
    }

    private void put0(String key, Object o) {
        if (this.java1 || this.tlm == null) {
            return;
        }
        Hashtable<String, Object> ht = (Hashtable<String, Object>)((ThreadLocalMap)this.tlm).get();
        if (ht == null) {
            ht = new Hashtable<String, Object>(7);
            ((ThreadLocalMap)this.tlm).set(ht);
        }
        ht.put(key, o);
    }

    private Object get0(String key) {
        if (this.java1 || this.tlm == null) {
            return null;
        }
        Hashtable ht = (Hashtable)((ThreadLocalMap)this.tlm).get();
        if (ht != null && key != null) {
            return ht.get(key);
        }
        return null;
    }

    private void remove0(String key) {
        Hashtable ht;
        if (!this.java1 && this.tlm != null && (ht = (Hashtable)((ThreadLocalMap)this.tlm).get()) != null) {
            ht.remove(key);
            if (ht.isEmpty()) {
                this.clear0();
            }
        }
    }

    private Hashtable getContext0() {
        if (this.java1 || this.tlm == null) {
            return null;
        }
        return (Hashtable)((ThreadLocalMap)this.tlm).get();
    }

    private void clear0() {
        if (!this.java1 && this.tlm != null) {
            Hashtable ht = (Hashtable)((ThreadLocalMap)this.tlm).get();
            if (ht != null) {
                ht.clear();
            }
            if (this.removeMethod != null) {
                try {
                    this.removeMethod.invoke(this.tlm, null);
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException invocationTargetException) {
                    // empty catch block
                }
            }
        }
    }
}

