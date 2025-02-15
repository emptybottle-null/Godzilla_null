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
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

public class GenericRecordXmlWriter
implements Closeable {
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    private static final Pattern ESC_CHARS;
    private static final List<Map.Entry<Class, GenericRecordHandler>> handler;
    private final PrintWriter fw;
    private int indent = 0;
    private boolean withComments = true;
    private int childIndex = 0;
    private boolean attributePhase = true;

    private static void handler(Class c, GenericRecordHandler printer) {
        handler.add(new AbstractMap.SimpleEntry<Class, GenericRecordHandler>(c, printer));
    }

    public GenericRecordXmlWriter(File fileName) throws IOException {
        OutputStream os = "null".equals(fileName.getName()) ? new GenericRecordJsonWriter.NullOutputStream() : new FileOutputStream(fileName);
        this.fw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
    }

    public GenericRecordXmlWriter(Appendable buffer) {
        this.fw = new PrintWriter(new GenericRecordJsonWriter.AppendableWriter(buffer));
    }

    public static String marshal(GenericRecord record) {
        return GenericRecordXmlWriter.marshal(record, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String marshal(GenericRecord record, boolean withComments) {
        StringBuilder sb = new StringBuilder();
        try (GenericRecordXmlWriter w = new GenericRecordXmlWriter(sb);){
            w.setWithComments(withComments);
            w.write(record);
            String string = sb.toString();
            return string;
        } catch (IOException e) {
            return "<record/>";
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
        this.write("record", record);
    }

    protected void write(String name, GenericRecord record) {
        String tabs = this.tabs();
        Enum type = record.getGenericRecordType();
        String recordName = type != null ? type.name() : record.getClass().getSimpleName();
        this.fw.append(tabs);
        this.fw.append("<" + name + " type=\"");
        this.fw.append(recordName);
        this.fw.append("\"");
        if (this.childIndex > 0) {
            this.fw.append(" index=\"");
            this.fw.print(this.childIndex);
            this.fw.append("\"");
        }
        this.attributePhase = true;
        boolean hasComplex = this.writeProperties(record);
        this.attributePhase = false;
        if (hasComplex |= this.writeChildren(record, hasComplex)) {
            this.fw.append(tabs);
            this.fw.println("</" + name + ">");
        } else {
            this.fw.println("/>");
        }
    }

    protected boolean writeProperties(GenericRecord record) {
        Map<String, Supplier<?>> prop = record.getGenericProperties();
        if (prop == null || prop.isEmpty()) {
            return false;
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        List<Map.Entry> complex = prop.entrySet().stream().flatMap(this::writeProp).collect(Collectors.toList());
        this.attributePhase = false;
        if (!complex.isEmpty()) {
            this.fw.println(">");
            ++this.indent;
            complex.forEach(this::writeProp);
            --this.indent;
        }
        this.childIndex = oldChildIndex;
        return !complex.isEmpty();
    }

    protected boolean writeChildren(GenericRecord record, boolean hasComplexProperties) {
        List<? extends GenericRecord> list = record.getGenericChildren();
        if (list == null || list.isEmpty()) {
            return false;
        }
        if (!hasComplexProperties) {
            this.fw.print(">");
        }
        ++this.indent;
        this.fw.println();
        this.fw.println(this.tabs() + "<children>");
        ++this.indent;
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        list.forEach(l -> {
            this.writeValue("record", l);
            ++this.childIndex;
        });
        this.childIndex = oldChildIndex;
        this.fw.println();
        --this.indent;
        this.fw.println(this.tabs() + "</children>");
        --this.indent;
        return true;
    }

    public void writeError(String errorMsg) {
        this.printObject("error", errorMsg);
    }

    protected Stream<Map.Entry<String, Supplier<?>>> writeProp(Map.Entry<String, Supplier<?>> me) {
        Object obj = me.getValue().get();
        if (obj == null) {
            return Stream.empty();
        }
        boolean isComplex = GenericRecordXmlWriter.isComplex(obj);
        if (this.attributePhase == isComplex) {
            return isComplex ? Stream.of(new AbstractMap.SimpleEntry<String, Supplier<Object>>(me.getKey(), () -> obj)) : Stream.empty();
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        this.writeValue(me.getKey(), obj);
        this.childIndex = oldChildIndex;
        return Stream.empty();
    }

    protected static boolean isComplex(Object obj) {
        return !(obj instanceof Number) && !(obj instanceof Boolean) && !(obj instanceof Character) && !(obj instanceof String) && !(obj instanceof Color) && !(obj instanceof Enum);
    }

    protected void writeValue(String name, Object value) {
        assert (name != null);
        if (value instanceof GenericRecord) {
            this.printGenericRecord(name, value);
        } else if (value != null) {
            if (name.endsWith(">")) {
                this.fw.print("\t");
            }
            handler.stream().filter(h -> GenericRecordXmlWriter.matchInstanceOrArray((Class)h.getKey(), value)).findFirst().ifPresent(h -> ((GenericRecordHandler)h.getValue()).print(this, name, value));
        }
    }

    protected static boolean matchInstanceOrArray(Class key, Object instance) {
        return key.isInstance(instance) || Array.class.equals((Object)key) && instance.getClass().isArray();
    }

    protected void openName(String name) {
        name = name.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.print(" " + name.replace('>', ' ').trim() + "=\"");
        } else {
            this.fw.print(this.tabs() + "<" + name);
            if (name.endsWith(">")) {
                this.fw.println();
            }
        }
    }

    protected void closeName(String name) {
        name = name.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.append("\"");
        } else if (name.endsWith(">")) {
            this.fw.println(this.tabs() + "\t</" + name);
        } else {
            this.fw.println("/>");
        }
    }

    protected boolean printNumber(String name, Object o) {
        assert (this.attributePhase);
        this.openName(name);
        Number n = (Number)o;
        this.fw.print(n.toString());
        this.closeName(name);
        return true;
    }

    protected boolean printBoolean(String name, Object o) {
        assert (this.attributePhase);
        this.openName(name);
        this.fw.write(((Boolean)o).toString());
        this.closeName(name);
        return true;
    }

    protected boolean printList(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name + ">");
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        ((List)o).forEach(e -> {
            this.writeValue("item>", e);
            ++this.childIndex;
        });
        this.childIndex = oldChildIndex;
        this.closeName(name + ">");
        return true;
    }

    protected boolean printArray(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name + ">");
        int length = Array.getLength(o);
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        while (this.childIndex < length) {
            this.writeValue("item>", Array.get(o, this.childIndex));
            ++this.childIndex;
        }
        this.childIndex = oldChildIndex;
        this.closeName(name + ">");
        return true;
    }

    protected void printGenericRecord(String name, Object value) {
        this.write(name, (GenericRecord)value);
    }

    protected boolean printAnnotatedFlag(String name, Object o) {
        assert (!this.attributePhase);
        GenericRecordUtil.AnnotatedFlag af = (GenericRecordUtil.AnnotatedFlag)o;
        Number n = af.getValue().get();
        int len = n instanceof Byte ? 2 : (n instanceof Short ? 4 : (n instanceof Integer ? 8 : 16));
        this.openName(name);
        this.fw.print(" flag=\"0x");
        this.fw.print(this.trimHex(n.longValue(), len));
        this.fw.print('\"');
        if (this.withComments) {
            this.fw.print(" description=\"");
            this.fw.print(af.getDescription());
            this.fw.print("\"");
        }
        this.closeName(name);
        return true;
    }

    protected boolean printBytes(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name + ">");
        this.fw.write(Base64.getEncoder().encodeToString((byte[])o));
        this.closeName(name + ">");
        return true;
    }

    protected boolean printPoint(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name);
        Point2D p = (Point2D)o;
        this.fw.println(" x=\"" + p.getX() + "\" y=\"" + p.getY() + "\"/>");
        this.closeName(name);
        return true;
    }

    protected boolean printDimension(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name);
        Dimension2D p = (Dimension2D)o;
        this.fw.println(" width=\"" + p.getWidth() + "\" height=\"" + p.getHeight() + "\"/>");
        this.closeName(name);
        return true;
    }

    protected boolean printRectangle(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name);
        Rectangle2D p = (Rectangle2D)o;
        this.fw.println(" x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getWidth() + "\" height=\"" + p.getHeight() + "\"/>");
        this.closeName(name);
        return true;
    }

    protected boolean printPath(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name + ">");
        PathIterator iter = ((Path2D)o).getPathIterator(null);
        double[] pnts = new double[6];
        this.indent += 2;
        String t = this.tabs();
        this.indent -= 2;
        while (!iter.isDone()) {
            this.fw.print(t);
            int segType = iter.currentSegment(pnts);
            this.fw.print("<pathelement ");
            switch (segType) {
                case 0: {
                    this.fw.print("type=\"move\" x=\"" + pnts[0] + "\" y=\"" + pnts[1] + "\"");
                    break;
                }
                case 1: {
                    this.fw.print("type=\"lineto\" x=\"" + pnts[0] + "\" y=\"" + pnts[1] + "\"");
                    break;
                }
                case 2: {
                    this.fw.print("type=\"quad\" x1=\"" + pnts[0] + "\" y1=\"" + pnts[1] + "\" x2=\"" + pnts[2] + "\" y2=\"" + pnts[3] + "\"");
                    break;
                }
                case 3: {
                    this.fw.print("type=\"cubic\" x1=\"" + pnts[0] + "\" y1=\"" + pnts[1] + "\" x2=\"" + pnts[2] + "\" y2=\"" + pnts[3] + "\" x3=\"" + pnts[4] + "\" y3=\"" + pnts[5] + "\"");
                    break;
                }
                case 4: {
                    this.fw.print("type=\"close\"");
                }
            }
            this.fw.println("/>");
            iter.next();
        }
        this.closeName(name + ">");
        return true;
    }

    protected boolean printObject(String name, Object o) {
        this.openName(name + ">");
        String str = o.toString();
        Matcher m = ESC_CHARS.matcher(str);
        int pos = 0;
        while (m.find()) {
            String match;
            this.fw.write(str, pos, m.start());
            switch (match = m.group()) {
                case "<": {
                    this.fw.write("&lt;");
                    break;
                }
                case ">": {
                    this.fw.write("&gt;");
                    break;
                }
                case "&": {
                    this.fw.write("&amp;");
                    break;
                }
                case "'": {
                    this.fw.write("&apos;");
                    break;
                }
                case "\"": {
                    this.fw.write("&quot;");
                    break;
                }
                default: {
                    this.fw.write("&#x");
                    this.fw.write(Long.toHexString(match.codePointAt(0)));
                    this.fw.write(";");
                }
            }
            pos = m.end();
        }
        this.fw.append(str, pos, str.length());
        this.closeName(name + ">");
        return true;
    }

    protected boolean printAffineTransform(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name);
        AffineTransform xForm = (AffineTransform)o;
        this.fw.write("<" + name + " scaleX=\"" + xForm.getScaleX() + "\" shearX=\"" + xForm.getShearX() + "\" transX=\"" + xForm.getTranslateX() + "\" scaleY=\"" + xForm.getScaleY() + "\" shearY=\"" + xForm.getShearY() + "\" transY=\"" + xForm.getTranslateY() + "\"/>");
        this.closeName(name);
        return true;
    }

    protected boolean printColor(String name, Object o) {
        assert (this.attributePhase);
        this.openName(name);
        int rgb = ((Color)o).getRGB();
        this.fw.print("0x" + this.trimHex(rgb, 8));
        this.closeName(name);
        return true;
    }

    protected boolean printBufferedImage(String name, Object o) {
        assert (!this.attributePhase);
        this.openName(name);
        BufferedImage bi = (BufferedImage)o;
        this.fw.println(" width=\"" + bi.getWidth() + "\" height=\"" + bi.getHeight() + "\" bands=\"" + bi.getColorModel().getNumComponents() + "\"");
        this.closeName(name);
        return true;
    }

    protected String trimHex(long l, int size) {
        String b = Long.toHexString(l);
        int len = b.length();
        return ZEROS.substring(0, Math.max(0, size - len)) + b.substring(Math.max(0, len - size), len);
    }

    static {
        ESC_CHARS = Pattern.compile("[<>&'\"\\p{Cntrl}]");
        handler = new ArrayList<Map.Entry<Class, GenericRecordHandler>>();
        char[] t = new char[255];
        Arrays.fill(t, '\t');
        TABS = new String(t);
        GenericRecordXmlWriter.handler(String.class, GenericRecordXmlWriter::printObject);
        GenericRecordXmlWriter.handler(Number.class, GenericRecordXmlWriter::printNumber);
        GenericRecordXmlWriter.handler(Boolean.class, GenericRecordXmlWriter::printBoolean);
        GenericRecordXmlWriter.handler(List.class, GenericRecordXmlWriter::printList);
        GenericRecordXmlWriter.handler(GenericRecordUtil.AnnotatedFlag.class, GenericRecordXmlWriter::printAnnotatedFlag);
        GenericRecordXmlWriter.handler(byte[].class, GenericRecordXmlWriter::printBytes);
        GenericRecordXmlWriter.handler(Point2D.class, GenericRecordXmlWriter::printPoint);
        GenericRecordXmlWriter.handler(Dimension2D.class, GenericRecordXmlWriter::printDimension);
        GenericRecordXmlWriter.handler(Rectangle2D.class, GenericRecordXmlWriter::printRectangle);
        GenericRecordXmlWriter.handler(Path2D.class, GenericRecordXmlWriter::printPath);
        GenericRecordXmlWriter.handler(AffineTransform.class, GenericRecordXmlWriter::printAffineTransform);
        GenericRecordXmlWriter.handler(Color.class, GenericRecordXmlWriter::printColor);
        GenericRecordXmlWriter.handler(BufferedImage.class, GenericRecordXmlWriter::printBufferedImage);
        GenericRecordXmlWriter.handler(Array.class, GenericRecordXmlWriter::printArray);
        GenericRecordXmlWriter.handler(Object.class, GenericRecordXmlWriter::printObject);
    }

    @FunctionalInterface
    protected static interface GenericRecordHandler {
        public boolean print(GenericRecordXmlWriter var1, String var2, Object var3);
    }
}

