/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;

public class GenericRecordJsonWriter
implements Closeable {
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    private static final Pattern ESC_CHARS;
    private static final String NL;
    private static final List<Map.Entry<Class, GenericRecordHandler>> handler;
    protected final AppendableWriter aw;
    protected final PrintWriter fw;
    protected int indent = 0;
    protected boolean withComments = true;
    protected int childIndex = 0;

    private static void handler(Class c, GenericRecordHandler printer) {
        handler.add(new AbstractMap.SimpleEntry<Class, GenericRecordHandler>(c, printer));
    }

    public GenericRecordJsonWriter(File fileName) throws IOException {
        OutputStream os = "null".equals(fileName.getName()) ? new NullOutputStream() : new FileOutputStream(fileName);
        this.aw = new AppendableWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        this.fw = new PrintWriter(this.aw);
    }

    public GenericRecordJsonWriter(Appendable buffer) {
        this.aw = new AppendableWriter(buffer);
        this.fw = new PrintWriter(this.aw);
    }

    public static String marshal(GenericRecord record) {
        return GenericRecordJsonWriter.marshal(record, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String marshal(GenericRecord record, boolean withComments) {
        StringBuilder sb = new StringBuilder();
        try (GenericRecordJsonWriter w = new GenericRecordJsonWriter(sb);){
            w.setWithComments(withComments);
            w.write(record);
            String string = sb.toString();
            return string;
        } catch (IOException e) {
            return "{}";
        }
    }

    public void setWithComments(boolean withComments) {
        this.withComments = withComments;
    }

    @Override
    public void close() throws IOException {
        this.fw.close();
    }

    protected String tabs() {
        return TABS.substring(0, Math.min(this.indent, TABS.length()));
    }

    public void write(GenericRecord record) {
        String tabs = this.tabs();
        Enum type = record.getGenericRecordType();
        String recordName = type != null ? type.name() : record.getClass().getSimpleName();
        this.fw.append(tabs);
        this.fw.append("{");
        if (this.withComments) {
            this.fw.append("   /* ");
            this.fw.append(recordName);
            if (this.childIndex > 0) {
                this.fw.append(" - index: ");
                this.fw.print(this.childIndex);
            }
            this.fw.append(" */");
        }
        this.fw.println();
        boolean hasProperties = this.writeProperties(record);
        this.fw.println();
        this.writeChildren(record, hasProperties);
        this.fw.append(tabs);
        this.fw.append("}");
    }

    protected boolean writeProperties(GenericRecord record) {
        Map<String, Supplier<?>> prop = record.getGenericProperties();
        if (prop == null || prop.isEmpty()) {
            return false;
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        long cnt = prop.entrySet().stream().filter(e -> this.writeProp((String)e.getKey(), (Supplier)e.getValue())).count();
        this.childIndex = oldChildIndex;
        return cnt > 0L;
    }

    protected boolean writeChildren(GenericRecord record, boolean hasProperties) {
        List<? extends GenericRecord> list = record.getGenericChildren();
        if (list == null || list.isEmpty()) {
            return false;
        }
        ++this.indent;
        this.aw.setHoldBack(this.tabs() + (hasProperties ? ", " : "") + "\"children\": [" + NL);
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        long cnt = list.stream().filter(l -> this.writeValue(null, l) && ++this.childIndex > 0).count();
        this.childIndex = oldChildIndex;
        this.aw.setHoldBack(null);
        if (cnt > 0L) {
            this.fw.println();
            this.fw.println(this.tabs() + "]");
        }
        --this.indent;
        return cnt > 0L;
    }

    public void writeError(String errorMsg) {
        this.fw.append("{ error: ");
        this.printObject("error", errorMsg);
        this.fw.append(" }");
    }

    protected boolean writeProp(String name, Supplier<?> value) {
        boolean isNext = this.childIndex > 0;
        this.aw.setHoldBack(isNext ? NL + this.tabs() + "\t, " : this.tabs() + "\t  ");
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        boolean written = this.writeValue(name, value.get());
        this.childIndex = oldChildIndex + (written ? 1 : 0);
        this.aw.setHoldBack(null);
        return written;
    }

    protected boolean writeValue(String name, Object o) {
        if (this.childIndex > 0) {
            this.aw.setHoldBack(",");
        }
        GenericRecordHandler grh = o == null ? GenericRecordJsonWriter::printNull : (GenericRecordHandler)handler.stream().filter(h -> GenericRecordJsonWriter.matchInstanceOrArray((Class)h.getKey(), o)).findFirst().map(Map.Entry::getValue).orElse(null);
        boolean result = grh != null && grh.print(this, name, o);
        this.aw.setHoldBack(null);
        return result;
    }

    protected static boolean matchInstanceOrArray(Class key, Object instance) {
        return key.isInstance(instance) || Array.class.equals((Object)key) && instance.getClass().isArray();
    }

    protected void printName(String name) {
        this.fw.print(name != null ? "\"" + name + "\": " : "");
    }

    protected boolean printNull(String name, Object o) {
        this.printName(name);
        this.fw.write("null");
        return true;
    }

    protected boolean printNumber(String name, Object o) {
        Number n = (Number)o;
        this.printName(name);
        if (o instanceof Float) {
            this.fw.print(n.floatValue());
            return true;
        }
        if (o instanceof Double) {
            this.fw.print(n.doubleValue());
            return true;
        }
        this.fw.print(n.longValue());
        int size = n instanceof Byte ? 2 : (n instanceof Short ? 4 : (n instanceof Integer ? 8 : (n instanceof Long ? 16 : -1)));
        long l = n.longValue();
        if (this.withComments && size > 0 && (l < 0L || l > 9L)) {
            this.fw.write(" /* 0x");
            this.fw.write(GenericRecordJsonWriter.trimHex(l, size));
            this.fw.write(" */");
        }
        return true;
    }

    protected boolean printBoolean(String name, Object o) {
        this.printName(name);
        this.fw.write(((Boolean)o).toString());
        return true;
    }

    protected boolean printList(String name, Object o) {
        this.printName(name);
        this.fw.println("[");
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        ((List)o).forEach(e -> {
            this.writeValue(null, e);
            ++this.childIndex;
        });
        this.childIndex = oldChildIndex;
        this.fw.write(this.tabs() + "\t]");
        return true;
    }

    protected boolean printGenericRecord(String name, Object o) {
        this.printName(name);
        ++this.indent;
        this.write((GenericRecord)o);
        --this.indent;
        return true;
    }

    protected boolean printAnnotatedFlag(String name, Object o) {
        this.printName(name);
        GenericRecordUtil.AnnotatedFlag af = (GenericRecordUtil.AnnotatedFlag)o;
        this.fw.print(af.getValue().get().longValue());
        if (this.withComments) {
            this.fw.write(" /* ");
            this.fw.write(af.getDescription());
            this.fw.write(" */ ");
        }
        return true;
    }

    protected boolean printBytes(String name, Object o) {
        this.printName(name);
        this.fw.write(34);
        this.fw.write(Base64.getEncoder().encodeToString((byte[])o));
        this.fw.write(34);
        return true;
    }

    protected boolean printPoint(String name, Object o) {
        this.printName(name);
        Point2D p = (Point2D)o;
        this.fw.write("{ \"x\": " + p.getX() + ", \"y\": " + p.getY() + " }");
        return true;
    }

    protected boolean printDimension(String name, Object o) {
        this.printName(name);
        Dimension2D p = (Dimension2D)o;
        this.fw.write("{ \"width\": " + p.getWidth() + ", \"height\": " + p.getHeight() + " }");
        return true;
    }

    protected boolean printRectangle(String name, Object o) {
        this.printName(name);
        Rectangle2D p = (Rectangle2D)o;
        this.fw.write("{ \"x\": " + p.getX() + ", \"y\": " + p.getY() + ", \"width\": " + p.getWidth() + ", \"height\": " + p.getHeight() + " }");
        return true;
    }

    protected boolean printPath(String name, Object o) {
        this.printName(name);
        PathIterator iter = ((Path2D)o).getPathIterator(null);
        double[] pnts = new double[6];
        this.fw.write("[");
        this.indent += 2;
        String t = this.tabs();
        this.indent -= 2;
        boolean isNext = false;
        while (!iter.isDone()) {
            this.fw.println(isNext ? ", " : "");
            this.fw.print(t);
            isNext = true;
            int segType = iter.currentSegment(pnts);
            this.fw.append("{ \"type\": ");
            switch (segType) {
                case 0: {
                    this.fw.write("\"move\", \"x\": " + pnts[0] + ", \"y\": " + pnts[1]);
                    break;
                }
                case 1: {
                    this.fw.write("\"lineto\", \"x\": " + pnts[0] + ", \"y\": " + pnts[1]);
                    break;
                }
                case 2: {
                    this.fw.write("\"quad\", \"x1\": " + pnts[0] + ", \"y1\": " + pnts[1] + ", \"x2\": " + pnts[2] + ", \"y2\": " + pnts[3]);
                    break;
                }
                case 3: {
                    this.fw.write("\"cubic\", \"x1\": " + pnts[0] + ", \"y1\": " + pnts[1] + ", \"x2\": " + pnts[2] + ", \"y2\": " + pnts[3] + ", \"x3\": " + pnts[4] + ", \"y3\": " + pnts[5]);
                    break;
                }
                case 4: {
                    this.fw.write("\"close\"");
                }
            }
            this.fw.append(" }");
            iter.next();
        }
        this.fw.write("]");
        return true;
    }

    protected boolean printObject(String name, Object o) {
        this.printName(name);
        this.fw.write(34);
        String str = o.toString();
        Matcher m = ESC_CHARS.matcher(str);
        int pos = 0;
        while (m.find()) {
            String match;
            this.fw.append(str, pos, m.start());
            switch (match = m.group()) {
                case "\n": {
                    this.fw.write("\\\\n");
                    break;
                }
                case "\r": {
                    this.fw.write("\\\\r");
                    break;
                }
                case "\t": {
                    this.fw.write("\\\\t");
                    break;
                }
                case "\b": {
                    this.fw.write("\\\\b");
                    break;
                }
                case "\f": {
                    this.fw.write("\\\\f");
                    break;
                }
                case "\\": {
                    this.fw.write("\\\\\\\\");
                    break;
                }
                case "\"": {
                    this.fw.write("\\\\\"");
                    break;
                }
                default: {
                    this.fw.write("\\\\u");
                    this.fw.write(GenericRecordJsonWriter.trimHex(match.charAt(0), 4));
                }
            }
            pos = m.end();
        }
        this.fw.append(str, pos, str.length());
        this.fw.write(34);
        return true;
    }

    protected boolean printAffineTransform(String name, Object o) {
        this.printName(name);
        AffineTransform xForm = (AffineTransform)o;
        this.fw.write("{ \"scaleX\": " + xForm.getScaleX() + ", \"shearX\": " + xForm.getShearX() + ", \"transX\": " + xForm.getTranslateX() + ", \"scaleY\": " + xForm.getScaleY() + ", \"shearY\": " + xForm.getShearY() + ", \"transY\": " + xForm.getTranslateY() + " }");
        return true;
    }

    protected boolean printColor(String name, Object o) {
        this.printName(name);
        int rgb = ((Color)o).getRGB();
        this.fw.print(rgb);
        if (this.withComments) {
            this.fw.write(" /* 0x");
            this.fw.write(GenericRecordJsonWriter.trimHex(rgb, 8));
            this.fw.write(" */");
        }
        return true;
    }

    protected boolean printArray(String name, Object o) {
        this.printName(name);
        this.fw.write("[");
        int length = Array.getLength(o);
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        while (this.childIndex < length) {
            this.writeValue(null, Array.get(o, this.childIndex));
            ++this.childIndex;
        }
        this.childIndex = oldChildIndex;
        this.fw.write(this.tabs() + "\t]");
        return true;
    }

    static String trimHex(long l, int size) {
        String b = Long.toHexString(l);
        int len = b.length();
        return ZEROS.substring(0, Math.max(0, size - len)) + b.substring(Math.max(0, len - size), len);
    }

    static {
        ESC_CHARS = Pattern.compile("[\"\\p{Cntrl}\\\\]");
        NL = System.getProperty("line.separator");
        handler = new ArrayList<Map.Entry<Class, GenericRecordHandler>>();
        char[] t = new char[255];
        Arrays.fill(t, '\t');
        TABS = new String(t);
        GenericRecordJsonWriter.handler(String.class, GenericRecordJsonWriter::printObject);
        GenericRecordJsonWriter.handler(Number.class, GenericRecordJsonWriter::printNumber);
        GenericRecordJsonWriter.handler(Boolean.class, GenericRecordJsonWriter::printBoolean);
        GenericRecordJsonWriter.handler(List.class, GenericRecordJsonWriter::printList);
        GenericRecordJsonWriter.handler(GenericRecord.class, GenericRecordJsonWriter::printGenericRecord);
        GenericRecordJsonWriter.handler(GenericRecordUtil.AnnotatedFlag.class, GenericRecordJsonWriter::printAnnotatedFlag);
        GenericRecordJsonWriter.handler(byte[].class, GenericRecordJsonWriter::printBytes);
        GenericRecordJsonWriter.handler(Point2D.class, GenericRecordJsonWriter::printPoint);
        GenericRecordJsonWriter.handler(Dimension2D.class, GenericRecordJsonWriter::printDimension);
        GenericRecordJsonWriter.handler(Rectangle2D.class, GenericRecordJsonWriter::printRectangle);
        GenericRecordJsonWriter.handler(Path2D.class, GenericRecordJsonWriter::printPath);
        GenericRecordJsonWriter.handler(AffineTransform.class, GenericRecordJsonWriter::printAffineTransform);
        GenericRecordJsonWriter.handler(Color.class, GenericRecordJsonWriter::printColor);
        GenericRecordJsonWriter.handler(Array.class, GenericRecordJsonWriter::printArray);
        GenericRecordJsonWriter.handler(Object.class, GenericRecordJsonWriter::printObject);
    }

    static class AppendableWriter
    extends Writer {
        private final Appendable appender;
        private final Writer writer;
        private String holdBack;

        AppendableWriter(Appendable buffer) {
            super(buffer);
            this.appender = buffer;
            this.writer = null;
        }

        AppendableWriter(Writer writer) {
            super(writer);
            this.appender = null;
            this.writer = writer;
        }

        void setHoldBack(String holdBack) {
            this.holdBack = holdBack;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (this.holdBack != null) {
                if (this.appender != null) {
                    this.appender.append(this.holdBack);
                } else {
                    this.writer.write(this.holdBack);
                }
                this.holdBack = null;
            }
            if (this.appender != null) {
                this.appender.append(String.valueOf(cbuf), off, len);
            } else {
                this.writer.write(cbuf, off, len);
            }
        }

        @Override
        public void flush() throws IOException {
            Appendable o;
            Appendable appendable = o = this.appender != null ? this.appender : this.writer;
            if (o instanceof Flushable) {
                ((Flushable)((Object)o)).flush();
            }
        }

        @Override
        public void close() throws IOException {
            Appendable o;
            this.flush();
            Appendable appendable = o = this.appender != null ? this.appender : this.writer;
            if (o instanceof Closeable) {
                ((Closeable)((Object)o)).close();
            }
        }
    }

    static class NullOutputStream
    extends OutputStream {
        NullOutputStream() {
        }

        @Override
        public void write(byte[] b, int off, int len) {
        }

        @Override
        public void write(int b) {
        }

        @Override
        public void write(byte[] b) {
        }
    }

    @FunctionalInterface
    protected static interface GenericRecordHandler {
        public boolean print(GenericRecordJsonWriter var1, String var2, Object var3);
    }
}

