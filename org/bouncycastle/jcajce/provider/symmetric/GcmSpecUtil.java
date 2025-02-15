/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.util.Integers;

class GcmSpecUtil {
    static final Class gcmSpecClass = ClassUtil.loadClass(GcmSpecUtil.class, "javax.crypto.spec.GCMParameterSpec");

    GcmSpecUtil() {
    }

    static boolean gcmSpecExists() {
        return gcmSpecClass != null;
    }

    static boolean isGcmSpec(AlgorithmParameterSpec algorithmParameterSpec) {
        return gcmSpecClass != null && gcmSpecClass.isInstance(algorithmParameterSpec);
    }

    static boolean isGcmSpec(Class clazz) {
        return gcmSpecClass == clazz;
    }

    static AlgorithmParameterSpec extractGcmSpec(ASN1Primitive aSN1Primitive) throws InvalidParameterSpecException {
        try {
            GCMParameters gCMParameters = GCMParameters.getInstance(aSN1Primitive);
            Constructor constructor = gcmSpecClass.getConstructor(Integer.TYPE, byte[].class);
            return (AlgorithmParameterSpec)constructor.newInstance(Integers.valueOf(gCMParameters.getIcvLen() * 8), gCMParameters.getNonce());
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new InvalidParameterSpecException("No constructor found!");
        } catch (Exception exception) {
            throw new InvalidParameterSpecException("Construction failed: " + exception.getMessage());
        }
    }

    static GCMParameters extractGcmParameters(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        try {
            Method method = gcmSpecClass.getDeclaredMethod("getTLen", new Class[0]);
            Method method2 = gcmSpecClass.getDeclaredMethod("getIV", new Class[0]);
            return new GCMParameters((byte[])method2.invoke(algorithmParameterSpec, new Object[0]), (Integer)method.invoke(algorithmParameterSpec, new Object[0]) / 8);
        } catch (Exception exception) {
            throw new InvalidParameterSpecException("Cannot process GCMParameterSpec");
        }
    }
}

