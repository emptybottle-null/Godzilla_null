/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.FlexibleCompletor;

public abstract class ShellConsole {
    private static final Class[] NO_ARG = new Class[0];
    private static final Class[] BOOLEAN_ARG = new Class[]{Boolean.TYPE};
    private static final Class[] STRING_ARG = new Class[]{String.class};
    private static final Class[] CHARSEQ_ARG = new Class[]{CharSequence.class};

    protected ShellConsole() {
    }

    public abstract InputStream getIn();

    public abstract String readLine() throws IOException;

    public abstract String readLine(String var1) throws IOException;

    public abstract void flush() throws IOException;

    public abstract void print(String var1) throws IOException;

    public abstract void println() throws IOException;

    public abstract void println(String var1) throws IOException;

    private static Object tryInvoke(Object obj, String method, Class[] paramTypes, Object ... args) {
        try {
            Method m = obj.getClass().getDeclaredMethod(method, paramTypes);
            if (m != null) {
                return m.invoke(obj, args);
            }
        } catch (NoSuchMethodException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
            // empty catch block
        }
        return null;
    }

    public static ShellConsole getConsole(InputStream in, PrintStream ps, Charset cs) {
        return new SimpleShellConsole(in, ps, cs);
    }

    public static ShellConsole getConsole(Scriptable scope, Charset cs) {
        ClassLoader classLoader = ShellConsole.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        if (classLoader == null) {
            return null;
        }
        try {
            Class<?> readerClass = Kit.classOrNull(classLoader, "jline.console.ConsoleReader");
            if (readerClass != null) {
                return ShellConsole.getJLineShellConsoleV2(classLoader, readerClass, scope, cs);
            }
            readerClass = Kit.classOrNull(classLoader, "jline.ConsoleReader");
            if (readerClass != null) {
                return ShellConsole.getJLineShellConsoleV1(classLoader, readerClass, scope, cs);
            }
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
        return null;
    }

    private static JLineShellConsoleV1 getJLineShellConsoleV1(ClassLoader classLoader, Class<?> readerClass, Scriptable scope, Charset cs) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> c = readerClass.getConstructor(new Class[0]);
        Object reader = c.newInstance(new Object[0]);
        ShellConsole.tryInvoke(reader, "setBellEnabled", BOOLEAN_ARG, Boolean.FALSE);
        Class<?> completorClass = Kit.classOrNull(classLoader, "jline.Completor");
        Object completor = Proxy.newProxyInstance(classLoader, new Class[]{completorClass}, new FlexibleCompletor(completorClass, scope));
        ShellConsole.tryInvoke(reader, "addCompletor", new Class[]{completorClass}, completor);
        return new JLineShellConsoleV1(reader, cs);
    }

    private static JLineShellConsoleV2 getJLineShellConsoleV2(ClassLoader classLoader, Class<?> readerClass, Scriptable scope, Charset cs) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> c = readerClass.getConstructor(new Class[0]);
        Object reader = c.newInstance(new Object[0]);
        ShellConsole.tryInvoke(reader, "setBellEnabled", BOOLEAN_ARG, Boolean.FALSE);
        Class<?> completorClass = Kit.classOrNull(classLoader, "jline.console.completer.Completer");
        Object completor = Proxy.newProxyInstance(classLoader, new Class[]{completorClass}, new FlexibleCompletor(completorClass, scope));
        ShellConsole.tryInvoke(reader, "addCompleter", new Class[]{completorClass}, completor);
        return new JLineShellConsoleV2(reader, cs);
    }

    private static class SimpleShellConsole
    extends ShellConsole {
        private final InputStream in;
        private final PrintWriter out;
        private final BufferedReader reader;

        SimpleShellConsole(InputStream in, PrintStream ps, Charset cs) {
            this.in = in;
            this.out = new PrintWriter(ps);
            this.reader = new BufferedReader(new InputStreamReader(in, cs));
        }

        @Override
        public InputStream getIn() {
            return this.in;
        }

        @Override
        public String readLine() throws IOException {
            return this.reader.readLine();
        }

        @Override
        public String readLine(String prompt) throws IOException {
            if (prompt != null) {
                this.out.write(prompt);
                this.out.flush();
            }
            return this.reader.readLine();
        }

        @Override
        public void flush() throws IOException {
            this.out.flush();
        }

        @Override
        public void print(String s) throws IOException {
            this.out.print(s);
        }

        @Override
        public void println() throws IOException {
            this.out.println();
        }

        @Override
        public void println(String s) throws IOException {
            this.out.println(s);
        }
    }

    private static class ConsoleInputStream
    extends InputStream {
        private static final byte[] EMPTY = new byte[0];
        private final ShellConsole console;
        private final Charset cs;
        private byte[] buffer = EMPTY;
        private int cursor = -1;
        private boolean atEOF = false;

        public ConsoleInputStream(ShellConsole console, Charset cs) {
            this.console = console;
            this.cs = cs;
        }

        @Override
        public synchronized int read(byte[] b, int off, int len) throws IOException {
            if (b == null) {
                throw new NullPointerException();
            }
            if (off < 0 || len < 0 || len > b.length - off) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return 0;
            }
            if (!this.ensureInput()) {
                return -1;
            }
            int n = Math.min(len, this.buffer.length - this.cursor);
            for (int i = 0; i < n; ++i) {
                b[off + i] = this.buffer[this.cursor + i];
            }
            if (n < len) {
                b[off + n++] = 10;
            }
            this.cursor += n;
            return n;
        }

        @Override
        public synchronized int read() throws IOException {
            if (!this.ensureInput()) {
                return -1;
            }
            if (this.cursor == this.buffer.length) {
                ++this.cursor;
                return 10;
            }
            return this.buffer[this.cursor++];
        }

        private boolean ensureInput() throws IOException {
            if (this.atEOF) {
                return false;
            }
            if (this.cursor < 0 || this.cursor > this.buffer.length) {
                if (this.readNextLine() == -1) {
                    this.atEOF = true;
                    return false;
                }
                this.cursor = 0;
            }
            return true;
        }

        private int readNextLine() throws IOException {
            String line = this.console.readLine(null);
            if (line != null) {
                this.buffer = line.getBytes(this.cs);
                return this.buffer.length;
            }
            this.buffer = EMPTY;
            return -1;
        }
    }

    private static class JLineShellConsoleV2
    extends ShellConsole {
        private final Object reader;
        private final InputStream in;

        JLineShellConsoleV2(Object reader, Charset cs) {
            this.reader = reader;
            this.in = new ConsoleInputStream(this, cs);
        }

        @Override
        public InputStream getIn() {
            return this.in;
        }

        @Override
        public String readLine() throws IOException {
            return (String)ShellConsole.tryInvoke(this.reader, "readLine", NO_ARG, new Object[0]);
        }

        @Override
        public String readLine(String prompt) throws IOException {
            return (String)ShellConsole.tryInvoke(this.reader, "readLine", STRING_ARG, new Object[]{prompt});
        }

        @Override
        public void flush() throws IOException {
            ShellConsole.tryInvoke(this.reader, "flush", NO_ARG, new Object[0]);
        }

        @Override
        public void print(String s) throws IOException {
            ShellConsole.tryInvoke(this.reader, "print", CHARSEQ_ARG, new Object[]{s});
        }

        @Override
        public void println() throws IOException {
            ShellConsole.tryInvoke(this.reader, "println", NO_ARG, new Object[0]);
        }

        @Override
        public void println(String s) throws IOException {
            ShellConsole.tryInvoke(this.reader, "println", CHARSEQ_ARG, new Object[]{s});
        }
    }

    private static class JLineShellConsoleV1
    extends ShellConsole {
        private final Object reader;
        private final InputStream in;

        JLineShellConsoleV1(Object reader, Charset cs) {
            this.reader = reader;
            this.in = new ConsoleInputStream(this, cs);
        }

        @Override
        public InputStream getIn() {
            return this.in;
        }

        @Override
        public String readLine() throws IOException {
            return (String)ShellConsole.tryInvoke(this.reader, "readLine", NO_ARG, new Object[0]);
        }

        @Override
        public String readLine(String prompt) throws IOException {
            return (String)ShellConsole.tryInvoke(this.reader, "readLine", STRING_ARG, new Object[]{prompt});
        }

        @Override
        public void flush() throws IOException {
            ShellConsole.tryInvoke(this.reader, "flushConsole", NO_ARG, new Object[0]);
        }

        @Override
        public void print(String s) throws IOException {
            ShellConsole.tryInvoke(this.reader, "printString", STRING_ARG, new Object[]{s});
        }

        @Override
        public void println() throws IOException {
            ShellConsole.tryInvoke(this.reader, "printNewline", NO_ARG, new Object[0]);
        }

        @Override
        public void println(String s) throws IOException {
            ShellConsole.tryInvoke(this.reader, "printString", STRING_ARG, new Object[]{s});
            ShellConsole.tryInvoke(this.reader, "printNewline", NO_ARG, new Object[0]);
        }
    }
}

