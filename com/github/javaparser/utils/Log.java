/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import com.github.javaparser.utils.CodeGenerationUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;

public class Log {
    private static Adapter CURRENT_ADAPTER = new SilentAdapter();

    public static void setAdapter(Adapter adapter) {
        CURRENT_ADAPTER = adapter;
    }

    @SafeVarargs
    public static void trace(String format, Supplier<Object> ... args) {
        CURRENT_ADAPTER.trace(Log.makeFormattingSupplier(format, args));
    }

    private static Supplier<String> makeFormattingSupplier(String format, Supplier<Object>[] args) {
        return () -> {
            Object[] objects = new Object[args.length];
            for (int i = 0; i < args.length; ++i) {
                objects[i] = args[i].get();
            }
            return CodeGenerationUtils.f(format, objects);
        };
    }

    @SafeVarargs
    public static void info(String format, Supplier<Object> ... args) {
        CURRENT_ADAPTER.info(Log.makeFormattingSupplier(format, args));
    }

    public static void error(Throwable throwable) {
        CURRENT_ADAPTER.error(() -> throwable, null);
    }

    @SafeVarargs
    public static void error(Throwable throwable, String format, Supplier<Object> ... args) {
        CURRENT_ADAPTER.error(() -> throwable, Log.makeFormattingSupplier(format, args));
    }

    @SafeVarargs
    public static void error(String format, Supplier<Object> ... args) {
        CURRENT_ADAPTER.error(() -> null, Log.makeFormattingSupplier(format, args));
    }

    public static interface Adapter {
        public void info(Supplier<String> var1);

        public void trace(Supplier<String> var1);

        public void error(Supplier<Throwable> var1, Supplier<String> var2);
    }

    public static class SilentAdapter
    implements Adapter {
        @Override
        public void info(Supplier<String> messageSupplier) {
        }

        @Override
        public void trace(Supplier<String> messageSupplier) {
        }

        @Override
        public void error(Supplier<Throwable> throwableSupplier, Supplier<String> messageSupplier) {
        }
    }

    public static class StandardOutStandardErrorAdapter
    implements Adapter {
        @Override
        public void info(Supplier<String> messageSupplier) {
            System.out.println(messageSupplier.get());
        }

        @Override
        public void trace(Supplier<String> messageSupplier) {
            System.out.println(messageSupplier.get());
        }

        @Override
        public void error(Supplier<Throwable> throwableSupplier, Supplier<String> messageSupplier) {
            Throwable throwable = throwableSupplier.get();
            String message = messageSupplier.get();
            if (message == null) {
                System.err.println(throwable.getMessage());
                this.printStackTrace(throwable);
            } else if (throwable == null) {
                System.err.println(message);
            } else {
                System.err.println(message + ":" + throwable.getMessage());
                this.printStackTrace(throwable);
            }
        }

        private void printStackTrace(Throwable throwable) {
            try (StringWriter sw = new StringWriter();
                 PrintWriter pw = new PrintWriter(sw);){
                throwable.printStackTrace(pw);
                this.trace(sw::toString);
            } catch (IOException e) {
                throw new AssertionError((Object)"Error in logging library");
            }
        }
    }
}

