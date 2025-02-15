/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.representer;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import org.yaml.snakeyaml.external.biz.binaryEscape.BinaryEscape;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.representer.BaseRepresenter;
import org.yaml.snakeyaml.representer.Represent;

class SafeRepresenter
extends BaseRepresenter {
    protected Map<Class<? extends Object>, Tag> classTags;
    protected TimeZone timeZone = null;
    protected DumperOptions.NonPrintableStyle nonPrintableStyle;
    private static final Pattern MULTILINE_PATTERN = Pattern.compile("\n|\u0085|\u2028|\u2029");

    public SafeRepresenter() {
        this(new DumperOptions());
    }

    public SafeRepresenter(DumperOptions options) {
        this.nullRepresenter = new RepresentNull();
        this.representers.put(String.class, new RepresentString());
        this.representers.put(Boolean.class, new RepresentBoolean());
        this.representers.put(Character.class, new RepresentString());
        this.representers.put(UUID.class, new RepresentUuid());
        this.representers.put(byte[].class, new RepresentByteArray());
        RepresentPrimitiveArray primitiveArray = new RepresentPrimitiveArray();
        this.representers.put(short[].class, primitiveArray);
        this.representers.put(int[].class, primitiveArray);
        this.representers.put(long[].class, primitiveArray);
        this.representers.put(float[].class, primitiveArray);
        this.representers.put(double[].class, primitiveArray);
        this.representers.put(char[].class, primitiveArray);
        this.representers.put(boolean[].class, primitiveArray);
        this.multiRepresenters.put(Number.class, new RepresentNumber());
        this.multiRepresenters.put(List.class, new RepresentList());
        this.multiRepresenters.put(Map.class, new RepresentMap());
        this.multiRepresenters.put(Set.class, new RepresentSet());
        this.multiRepresenters.put(Iterator.class, new RepresentIterator());
        this.multiRepresenters.put(new Object[0].getClass(), new RepresentArray());
        this.multiRepresenters.put(Date.class, new RepresentDate());
        this.multiRepresenters.put(Enum.class, new RepresentEnum());
        this.multiRepresenters.put(Calendar.class, new RepresentDate());
        this.classTags = new HashMap<Class<? extends Object>, Tag>();
        this.nonPrintableStyle = options.getNonPrintableStyle();
    }

    protected Tag getTag(Class<?> clazz, Tag defaultTag) {
        if (this.classTags.containsKey(clazz)) {
            return this.classTags.get(clazz);
        }
        return defaultTag;
    }

    public Tag addClassTag(Class<? extends Object> clazz, Tag tag) {
        if (tag == null) {
            throw new NullPointerException("Tag must be provided.");
        }
        return this.classTags.put(clazz, tag);
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    protected class RepresentUuid
    implements Represent {
        protected RepresentUuid() {
        }

        @Override
        public Node representData(Object data) {
            return SafeRepresenter.this.representScalar(SafeRepresenter.this.getTag(data.getClass(), new Tag(UUID.class)), data.toString());
        }
    }

    protected class RepresentByteArray
    implements Represent {
        protected RepresentByteArray() {
        }

        @Override
        public Node representData(Object data) {
            return SafeRepresenter.this.representScalar(Tag.BINARY, BinaryEscape.escape((byte[])data), DumperOptions.ScalarStyle.LITERAL);
        }
    }

    protected class RepresentEnum
    implements Represent {
        protected RepresentEnum() {
        }

        @Override
        public Node representData(Object data) {
            Tag tag = new Tag(data.getClass());
            return SafeRepresenter.this.representScalar(SafeRepresenter.this.getTag(data.getClass(), tag), ((Enum)data).name());
        }
    }

    protected class RepresentDate
    implements Represent {
        protected RepresentDate() {
        }

        @Override
        public Node representData(Object data) {
            int gmtOffset;
            Calendar calendar;
            if (data instanceof Calendar) {
                calendar = (Calendar)data;
            } else {
                calendar = Calendar.getInstance(SafeRepresenter.this.getTimeZone() == null ? TimeZone.getTimeZone("UTC") : SafeRepresenter.this.timeZone);
                calendar.setTime((Date)data);
            }
            int years = calendar.get(1);
            int months = calendar.get(2) + 1;
            int days = calendar.get(5);
            int hour24 = calendar.get(11);
            int minutes = calendar.get(12);
            int seconds = calendar.get(13);
            int millis = calendar.get(14);
            StringBuilder buffer = new StringBuilder(String.valueOf(years));
            while (buffer.length() < 4) {
                buffer.insert(0, "0");
            }
            buffer.append("-");
            if (months < 10) {
                buffer.append("0");
            }
            buffer.append(String.valueOf(months));
            buffer.append("-");
            if (days < 10) {
                buffer.append("0");
            }
            buffer.append(String.valueOf(days));
            buffer.append("T");
            if (hour24 < 10) {
                buffer.append("0");
            }
            buffer.append(String.valueOf(hour24));
            buffer.append(":");
            if (minutes < 10) {
                buffer.append("0");
            }
            buffer.append(String.valueOf(minutes));
            buffer.append(":");
            if (seconds < 10) {
                buffer.append("0");
            }
            buffer.append(String.valueOf(seconds));
            if (millis > 0) {
                if (millis < 10) {
                    buffer.append(".00");
                } else if (millis < 100) {
                    buffer.append(".0");
                } else {
                    buffer.append(".");
                }
                buffer.append(String.valueOf(millis));
            }
            if ((gmtOffset = calendar.getTimeZone().getOffset(calendar.getTime().getTime())) == 0) {
                buffer.append('Z');
            } else {
                if (gmtOffset < 0) {
                    buffer.append('-');
                    gmtOffset *= -1;
                } else {
                    buffer.append('+');
                }
                int minutesOffset = gmtOffset / 60000;
                int hoursOffset = minutesOffset / 60;
                int partOfHour = minutesOffset % 60;
                if (hoursOffset < 10) {
                    buffer.append('0');
                }
                buffer.append(hoursOffset);
                buffer.append(':');
                if (partOfHour < 10) {
                    buffer.append('0');
                }
                buffer.append(partOfHour);
            }
            return SafeRepresenter.this.representScalar(SafeRepresenter.this.getTag(data.getClass(), Tag.TIMESTAMP), buffer.toString(), DumperOptions.ScalarStyle.PLAIN);
        }
    }

    protected class RepresentSet
    implements Represent {
        protected RepresentSet() {
        }

        @Override
        public Node representData(Object data) {
            LinkedHashMap value = new LinkedHashMap();
            Set set = (Set)data;
            for (Object key : set) {
                value.put(key, null);
            }
            return SafeRepresenter.this.representMapping(SafeRepresenter.this.getTag(data.getClass(), Tag.SET), value, DumperOptions.FlowStyle.AUTO);
        }
    }

    protected class RepresentMap
    implements Represent {
        protected RepresentMap() {
        }

        @Override
        public Node representData(Object data) {
            return SafeRepresenter.this.representMapping(SafeRepresenter.this.getTag(data.getClass(), Tag.MAP), (Map)data, DumperOptions.FlowStyle.AUTO);
        }
    }

    protected class RepresentPrimitiveArray
    implements Represent {
        protected RepresentPrimitiveArray() {
        }

        @Override
        public Node representData(Object data) {
            Class<?> type = data.getClass().getComponentType();
            if (Byte.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asByteList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Short.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asShortList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Integer.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asIntList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Long.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asLongList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Float.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asFloatList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Double.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asDoubleList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Character.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asCharList(data), DumperOptions.FlowStyle.AUTO);
            }
            if (Boolean.TYPE == type) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, this.asBooleanList(data), DumperOptions.FlowStyle.AUTO);
            }
            throw new YAMLException("Unexpected primitive '" + type.getCanonicalName() + "'");
        }

        private List<Byte> asByteList(Object in) {
            byte[] array = (byte[])in;
            ArrayList<Byte> list = new ArrayList<Byte>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }

        private List<Short> asShortList(Object in) {
            short[] array = (short[])in;
            ArrayList<Short> list = new ArrayList<Short>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }

        private List<Integer> asIntList(Object in) {
            int[] array = (int[])in;
            ArrayList<Integer> list = new ArrayList<Integer>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }

        private List<Long> asLongList(Object in) {
            long[] array = (long[])in;
            ArrayList<Long> list = new ArrayList<Long>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }

        private List<Float> asFloatList(Object in) {
            float[] array = (float[])in;
            ArrayList<Float> list = new ArrayList<Float>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(Float.valueOf(array[i]));
            }
            return list;
        }

        private List<Double> asDoubleList(Object in) {
            double[] array = (double[])in;
            ArrayList<Double> list = new ArrayList<Double>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }

        private List<Character> asCharList(Object in) {
            char[] array = (char[])in;
            ArrayList<Character> list = new ArrayList<Character>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(Character.valueOf(array[i]));
            }
            return list;
        }

        private List<Boolean> asBooleanList(Object in) {
            boolean[] array = (boolean[])in;
            ArrayList<Boolean> list = new ArrayList<Boolean>(array.length);
            for (int i = 0; i < array.length; ++i) {
                list.add(array[i]);
            }
            return list;
        }
    }

    protected class RepresentArray
    implements Represent {
        protected RepresentArray() {
        }

        @Override
        public Node representData(Object data) {
            Object[] array = (Object[])data;
            List<Object> list = Arrays.asList(array);
            return SafeRepresenter.this.representSequence(Tag.SEQ, list, DumperOptions.FlowStyle.AUTO);
        }
    }

    private static class IteratorWrapper
    implements Iterable<Object> {
        private Iterator<Object> iter;

        public IteratorWrapper(Iterator<Object> iter) {
            this.iter = iter;
        }

        @Override
        public Iterator<Object> iterator() {
            return this.iter;
        }
    }

    protected class RepresentIterator
    implements Represent {
        protected RepresentIterator() {
        }

        @Override
        public Node representData(Object data) {
            Iterator iter = (Iterator)data;
            return SafeRepresenter.this.representSequence(SafeRepresenter.this.getTag(data.getClass(), Tag.SEQ), new IteratorWrapper(iter), DumperOptions.FlowStyle.AUTO);
        }
    }

    protected class RepresentList
    implements Represent {
        protected RepresentList() {
        }

        @Override
        public Node representData(Object data) {
            return SafeRepresenter.this.representSequence(SafeRepresenter.this.getTag(data.getClass(), Tag.SEQ), (List)data, DumperOptions.FlowStyle.AUTO);
        }
    }

    protected class RepresentNumber
    implements Represent {
        protected RepresentNumber() {
        }

        @Override
        public Node representData(Object data) {
            String value;
            Tag tag;
            if (data instanceof Byte || data instanceof Short || data instanceof Integer || data instanceof Long || data instanceof BigInteger) {
                tag = Tag.INT;
                value = data.toString();
            } else {
                Number number = (Number)data;
                tag = Tag.FLOAT;
                value = number.equals(Double.NaN) ? ".NaN" : (number.equals(Double.POSITIVE_INFINITY) ? ".inf" : (number.equals(Double.NEGATIVE_INFINITY) ? "-.inf" : number.toString()));
            }
            return SafeRepresenter.this.representScalar(SafeRepresenter.this.getTag(data.getClass(), tag), value);
        }
    }

    protected class RepresentBoolean
    implements Represent {
        protected RepresentBoolean() {
        }

        @Override
        public Node representData(Object data) {
            String value = Boolean.TRUE.equals(data) ? "true" : "false";
            return SafeRepresenter.this.representScalar(Tag.BOOL, value);
        }
    }

    protected class RepresentString
    implements Represent {
        protected RepresentString() {
        }

        @Override
        public Node representData(Object data) {
            Tag tag = Tag.STR;
            DumperOptions.ScalarStyle style = null;
            String value = data.toString();
            if (SafeRepresenter.this.nonPrintableStyle == DumperOptions.NonPrintableStyle.BINARY && !StreamReader.isPrintable(value)) {
                char[] binary;
                tag = Tag.BINARY;
                try {
                    byte[] bytes = value.getBytes("UTF-8");
                    String checkValue = new String(bytes, "UTF-8");
                    if (!checkValue.equals(value)) {
                        throw new YAMLException("invalid string value has occurred");
                    }
                    binary = Base64Coder.encode(bytes);
                } catch (UnsupportedEncodingException e) {
                    throw new YAMLException(e);
                }
                value = String.valueOf(binary);
                style = DumperOptions.ScalarStyle.LITERAL;
            }
            if (SafeRepresenter.this.defaultScalarStyle == DumperOptions.ScalarStyle.PLAIN && MULTILINE_PATTERN.matcher(value).find()) {
                style = DumperOptions.ScalarStyle.LITERAL;
            }
            return SafeRepresenter.this.representScalar(tag, value, style);
        }
    }

    protected class RepresentNull
    implements Represent {
        protected RepresentNull() {
        }

        @Override
        public Node representData(Object data) {
            return SafeRepresenter.this.representScalar(Tag.NULL, "null");
        }
    }
}

