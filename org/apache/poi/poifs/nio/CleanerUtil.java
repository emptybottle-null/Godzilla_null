/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.nio;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.Objects;
import org.apache.poi.util.SuppressForbidden;

public final class CleanerUtil {
    public static final boolean UNMAP_SUPPORTED;
    public static final String UNMAP_NOT_SUPPORTED_REASON;
    private static final BufferCleaner CLEANER;

    private CleanerUtil() {
    }

    public static BufferCleaner getCleaner() {
        return CLEANER;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressForbidden(value="Java 9 Jigsaw whitelists access to sun.misc.Cleaner, so setAccessible works")
    private static Object unmapHackImpl() {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            MethodHandle unmapper = lookup.findVirtual(unsafeClass, "invokeCleaner", MethodType.methodType(Void.TYPE, ByteBuffer.class));
            Field f = unsafeClass.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Object theUnsafe = f.get(null);
            return CleanerUtil.newBufferCleaner(ByteBuffer.class, unmapper.bindTo(theUnsafe));
        } catch (SecurityException se) {
            try {
                throw se;
                catch (ReflectiveOperationException | RuntimeException e) {
                    Class<?> directBufferClass = Class.forName("java.nio.DirectByteBuffer");
                    Method m = directBufferClass.getMethod("cleaner", new Class[0]);
                    m.setAccessible(true);
                    MethodHandle directBufferCleanerMethod = lookup.unreflect(m);
                    Class<?> cleanerClass = directBufferCleanerMethod.type().returnType();
                    MethodHandle cleanMethod = lookup.findVirtual(cleanerClass, "clean", MethodType.methodType(Void.TYPE));
                    MethodHandle nonNullTest = lookup.findStatic(Objects.class, "nonNull", MethodType.methodType(Boolean.TYPE, Object.class)).asType(MethodType.methodType(Boolean.TYPE, cleanerClass));
                    MethodHandle noop = MethodHandles.dropArguments(MethodHandles.constant(Void.class, null).asType(MethodType.methodType(Void.TYPE)), 0, cleanerClass);
                    MethodHandle unmapper = MethodHandles.filterReturnValue(directBufferCleanerMethod, MethodHandles.guardWithTest(nonNullTest, cleanMethod, noop)).asType(MethodType.methodType(Void.TYPE, ByteBuffer.class));
                    return CleanerUtil.newBufferCleaner(directBufferClass, unmapper);
                }
            } catch (SecurityException se2) {
                return "Unmapping is not supported, because not all required permissions are given to the Hadoop JAR file: " + se2 + " [Please grant at least the following permissions: RuntimePermission(\"accessClassInPackage.sun.misc\")  and ReflectPermission(\"suppressAccessChecks\")]";
            } catch (ReflectiveOperationException | RuntimeException e) {
                return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Hadoop version: " + e;
            }
        }
    }

    private static BufferCleaner newBufferCleaner(Class<?> unmappableBufferClass, MethodHandle unmapper) {
        assert (Objects.equals(MethodType.methodType(Void.TYPE, ByteBuffer.class), unmapper.type()));
        return buffer -> {
            if (!buffer.isDirect()) {
                throw new IllegalArgumentException("unmapping only works with direct buffers");
            }
            if (!unmappableBufferClass.isInstance(buffer)) {
                throw new IllegalArgumentException("buffer is not an instance of " + unmappableBufferClass.getName());
            }
            Throwable error = AccessController.doPrivileged(() -> {
                try {
                    unmapper.invokeExact(buffer);
                    return null;
                } catch (Throwable t) {
                    return t;
                }
            });
            if (error != null) {
                throw new IOException("Unable to unmap the mapped buffer", error);
            }
        };
    }

    static {
        Object hack = AccessController.doPrivileged(CleanerUtil::unmapHackImpl);
        if (hack instanceof BufferCleaner) {
            CLEANER = (BufferCleaner)hack;
            UNMAP_SUPPORTED = true;
            UNMAP_NOT_SUPPORTED_REASON = null;
        } else {
            CLEANER = null;
            UNMAP_SUPPORTED = false;
            UNMAP_NOT_SUPPORTED_REASON = hack.toString();
        }
    }

    @FunctionalInterface
    public static interface BufferCleaner {
        public void freeBuffer(ByteBuffer var1) throws IOException;
    }
}

