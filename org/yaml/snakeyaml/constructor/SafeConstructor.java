/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.constructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.external.biz.binaryEscape.BinaryEscape;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

public class SafeConstructor
extends BaseConstructor {
    public static final ConstructUndefined undefinedConstructor;
    private static final Map<String, Boolean> BOOL_VALUES;
    private static final int[][] RADIX_MAX;
    private static final Pattern TIMESTAMP_REGEXP;
    private static final Pattern YMD_REGEXP;

    public SafeConstructor() {
        this(new LoaderOptions());
    }

    public SafeConstructor(LoaderOptions loadingConfig) {
        super(loadingConfig);
        this.yamlConstructors.put(Tag.NULL, new ConstructYamlNull());
        this.yamlConstructors.put(Tag.BOOL, new ConstructYamlBool());
        this.yamlConstructors.put(Tag.INT, new ConstructYamlInt());
        this.yamlConstructors.put(Tag.FLOAT, new ConstructYamlFloat());
        this.yamlConstructors.put(Tag.BINARY, new ConstructYamlBinary());
        this.yamlConstructors.put(Tag.TIMESTAMP, new ConstructYamlTimestamp());
        this.yamlConstructors.put(Tag.OMAP, new ConstructYamlOmap());
        this.yamlConstructors.put(Tag.PAIRS, new ConstructYamlPairs());
        this.yamlConstructors.put(Tag.SET, new ConstructYamlSet());
        this.yamlConstructors.put(Tag.STR, new ConstructYamlStr());
        this.yamlConstructors.put(Tag.SEQ, new ConstructYamlSeq());
        this.yamlConstructors.put(Tag.MAP, new ConstructYamlMap());
        this.yamlConstructors.put(null, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.scalar, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.sequence, undefinedConstructor);
        this.yamlClassConstructors.put(NodeId.mapping, undefinedConstructor);
    }

    protected void flattenMapping(MappingNode node) {
        this.processDuplicateKeys(node);
        if (node.isMerged()) {
            node.setValue(this.mergeNode(node, true, new HashMap<Object, Integer>(), new ArrayList<NodeTuple>()));
        }
    }

    protected void processDuplicateKeys(MappingNode node) {
        List<NodeTuple> nodeValue = node.getValue();
        HashMap<Object, Integer> keys = new HashMap<Object, Integer>(nodeValue.size());
        TreeSet<Integer> toRemove = new TreeSet<Integer>();
        int i = 0;
        for (NodeTuple tuple : nodeValue) {
            Node keyNode = tuple.getKeyNode();
            if (!keyNode.getTag().equals(Tag.MERGE)) {
                Integer prevIndex;
                Object key = this.constructObject(keyNode);
                if (key != null) {
                    try {
                        key.hashCode();
                    } catch (Exception e) {
                        throw new ConstructorException("while constructing a mapping", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
                    }
                }
                if ((prevIndex = keys.put(key, i)) != null) {
                    if (!this.isAllowDuplicateKeys()) {
                        throw new DuplicateKeyException(node.getStartMark(), key, tuple.getKeyNode().getStartMark());
                    }
                    toRemove.add(prevIndex);
                }
            }
            ++i;
        }
        Iterator indices2remove = toRemove.descendingIterator();
        while (indices2remove.hasNext()) {
            nodeValue.remove((Integer)indices2remove.next());
        }
    }

    private List<NodeTuple> mergeNode(MappingNode node, boolean isPreffered, Map<Object, Integer> key2index, List<NodeTuple> values) {
        Iterator<NodeTuple> iter = node.getValue().iterator();
        block4: while (iter.hasNext()) {
            NodeTuple nodeTuple = iter.next();
            Node keyNode = nodeTuple.getKeyNode();
            Node valueNode = nodeTuple.getValueNode();
            if (keyNode.getTag().equals(Tag.MERGE)) {
                iter.remove();
                switch (valueNode.getNodeId()) {
                    case mapping: {
                        MappingNode mn = (MappingNode)valueNode;
                        this.mergeNode(mn, false, key2index, values);
                        continue block4;
                    }
                    case sequence: {
                        SequenceNode sn = (SequenceNode)valueNode;
                        List<Node> vals = sn.getValue();
                        for (Node subnode : vals) {
                            if (!(subnode instanceof MappingNode)) {
                                throw new ConstructorException("while constructing a mapping", node.getStartMark(), "expected a mapping for merging, but found " + (Object)((Object)subnode.getNodeId()), subnode.getStartMark());
                            }
                            MappingNode mnode = (MappingNode)subnode;
                            this.mergeNode(mnode, false, key2index, values);
                        }
                        continue block4;
                    }
                    default: {
                        throw new ConstructorException("while constructing a mapping", node.getStartMark(), "expected a mapping or list of mappings for merging, but found " + (Object)((Object)valueNode.getNodeId()), valueNode.getStartMark());
                    }
                }
            }
            Object key = this.constructObject(keyNode);
            if (!key2index.containsKey(key)) {
                values.add(nodeTuple);
                key2index.put(key, values.size() - 1);
                continue;
            }
            if (!isPreffered) continue;
            values.set(key2index.get(key), nodeTuple);
        }
        return values;
    }

    @Override
    protected void constructMapping2ndStep(MappingNode node, Map<Object, Object> mapping) {
        this.flattenMapping(node);
        super.constructMapping2ndStep(node, mapping);
    }

    @Override
    protected void constructSet2ndStep(MappingNode node, Set<Object> set) {
        this.flattenMapping(node);
        super.constructSet2ndStep(node, set);
    }

    private static int maxLen(int max, int radix) {
        return Integer.toString(max, radix).length();
    }

    private static int maxLen(long max, int radix) {
        return Long.toString(max, radix).length();
    }

    private Number createNumber(int sign, String number, int radix) {
        Number result;
        int[] maxArr;
        int len;
        int n = len = number != null ? number.length() : 0;
        if (sign < 0) {
            number = "-" + number;
        }
        int[] nArray = maxArr = radix < RADIX_MAX.length ? RADIX_MAX[radix] : null;
        if (maxArr != null) {
            boolean gtInt;
            boolean bl = gtInt = len > maxArr[0];
            if (gtInt) {
                if (len > maxArr[1]) {
                    return new BigInteger(number, radix);
                }
                return SafeConstructor.createLongOrBigInteger(number, radix);
            }
        }
        try {
            result = Integer.valueOf(number, radix);
        } catch (NumberFormatException e) {
            result = SafeConstructor.createLongOrBigInteger(number, radix);
        }
        return result;
    }

    protected static Number createLongOrBigInteger(String number, int radix) {
        try {
            return Long.valueOf(number, radix);
        } catch (NumberFormatException e1) {
            return new BigInteger(number, radix);
        }
    }

    static {
        int[] radixList;
        undefinedConstructor = new ConstructUndefined();
        BOOL_VALUES = new HashMap<String, Boolean>();
        BOOL_VALUES.put("yes", Boolean.TRUE);
        BOOL_VALUES.put("no", Boolean.FALSE);
        BOOL_VALUES.put("true", Boolean.TRUE);
        BOOL_VALUES.put("false", Boolean.FALSE);
        BOOL_VALUES.put("on", Boolean.TRUE);
        BOOL_VALUES.put("off", Boolean.FALSE);
        RADIX_MAX = new int[17][2];
        for (int radix : radixList = new int[]{2, 8, 10, 16}) {
            SafeConstructor.RADIX_MAX[radix] = new int[]{SafeConstructor.maxLen(Integer.MAX_VALUE, radix), SafeConstructor.maxLen(Long.MAX_VALUE, radix)};
        }
        TIMESTAMP_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)(?:(?:[Tt]|[ \t]+)([0-9][0-9]?):([0-9][0-9]):([0-9][0-9])(?:\\.([0-9]*))?(?:[ \t]*(?:Z|([-+][0-9][0-9]?)(?::([0-9][0-9])?)?))?)?$");
        YMD_REGEXP = Pattern.compile("^([0-9][0-9][0-9][0-9])-([0-9][0-9]?)-([0-9][0-9]?)$");
    }

    public static final class ConstructUndefined
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            throw new ConstructorException(null, null, "could not determine a constructor for the tag " + node.getTag(), node.getStartMark());
        }
    }

    public class ConstructYamlMap
    implements Construct {
        @Override
        public Object construct(Node node) {
            MappingNode mnode = (MappingNode)node;
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.createDefaultMap(mnode.getValue().size());
            }
            return SafeConstructor.this.constructMapping(mnode);
        }

        @Override
        public void construct2ndStep(Node node, Object object) {
            if (!node.isTwoStepsConstruction()) {
                throw new YAMLException("Unexpected recursive mapping structure. Node: " + node);
            }
            SafeConstructor.this.constructMapping2ndStep((MappingNode)node, (Map)object);
        }
    }

    public class ConstructYamlSeq
    implements Construct {
        @Override
        public Object construct(Node node) {
            SequenceNode seqNode = (SequenceNode)node;
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.newList(seqNode);
            }
            return SafeConstructor.this.constructSequence(seqNode);
        }

        @Override
        public void construct2ndStep(Node node, Object data) {
            if (!node.isTwoStepsConstruction()) {
                throw new YAMLException("Unexpected recursive sequence structure. Node: " + node);
            }
            SafeConstructor.this.constructSequenceStep2((SequenceNode)node, (List)data);
        }
    }

    public class ConstructYamlStr
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            return BaseConstructor.constructScalar((ScalarNode)node);
        }
    }

    public class ConstructYamlSet
    implements Construct {
        @Override
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                return SafeConstructor.this.constructedObjects.containsKey(node) ? SafeConstructor.this.constructedObjects.get(node) : SafeConstructor.this.createDefaultSet(((MappingNode)node).getValue().size());
            }
            return SafeConstructor.this.constructSet((MappingNode)node);
        }

        @Override
        public void construct2ndStep(Node node, Object object) {
            if (!node.isTwoStepsConstruction()) {
                throw new YAMLException("Unexpected recursive set structure. Node: " + node);
            }
            SafeConstructor.this.constructSet2ndStep((MappingNode)node, (Set)object);
        }
    }

    public class ConstructYamlPairs
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            if (!(node instanceof SequenceNode)) {
                throw new ConstructorException("while constructing pairs", node.getStartMark(), "expected a sequence, but found " + (Object)((Object)node.getNodeId()), node.getStartMark());
            }
            SequenceNode snode = (SequenceNode)node;
            ArrayList<Object[]> pairs = new ArrayList<Object[]>(snode.getValue().size());
            for (Node subnode : snode.getValue()) {
                if (!(subnode instanceof MappingNode)) {
                    throw new ConstructorException("while constructingpairs", node.getStartMark(), "expected a mapping of length 1, but found " + (Object)((Object)subnode.getNodeId()), subnode.getStartMark());
                }
                MappingNode mnode = (MappingNode)subnode;
                if (mnode.getValue().size() != 1) {
                    throw new ConstructorException("while constructing pairs", node.getStartMark(), "expected a single mapping item, but found " + mnode.getValue().size() + " items", mnode.getStartMark());
                }
                Node keyNode = mnode.getValue().get(0).getKeyNode();
                Node valueNode = mnode.getValue().get(0).getValueNode();
                Object key = SafeConstructor.this.constructObject(keyNode);
                Object value = SafeConstructor.this.constructObject(valueNode);
                pairs.add(new Object[]{key, value});
            }
            return pairs;
        }
    }

    public class ConstructYamlOmap
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            LinkedHashMap<Object, Object> omap = new LinkedHashMap<Object, Object>();
            if (!(node instanceof SequenceNode)) {
                throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a sequence, but found " + (Object)((Object)node.getNodeId()), node.getStartMark());
            }
            SequenceNode snode = (SequenceNode)node;
            for (Node subnode : snode.getValue()) {
                if (!(subnode instanceof MappingNode)) {
                    throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a mapping of length 1, but found " + (Object)((Object)subnode.getNodeId()), subnode.getStartMark());
                }
                MappingNode mnode = (MappingNode)subnode;
                if (mnode.getValue().size() != 1) {
                    throw new ConstructorException("while constructing an ordered map", node.getStartMark(), "expected a single mapping item, but found " + mnode.getValue().size() + " items", mnode.getStartMark());
                }
                Node keyNode = mnode.getValue().get(0).getKeyNode();
                Node valueNode = mnode.getValue().get(0).getValueNode();
                Object key = SafeConstructor.this.constructObject(keyNode);
                Object value = SafeConstructor.this.constructObject(valueNode);
                omap.put(key, value);
            }
            return omap;
        }
    }

    public static class ConstructYamlTimestamp
    extends AbstractConstruct {
        private Calendar calendar;

        public Calendar getCalendar() {
            return this.calendar;
        }

        @Override
        public Object construct(Node node) {
            TimeZone timeZone;
            ScalarNode scalar = (ScalarNode)node;
            String nodeValue = scalar.getValue();
            Matcher match = YMD_REGEXP.matcher(nodeValue);
            if (match.matches()) {
                String year_s = match.group(1);
                String month_s = match.group(2);
                String day_s = match.group(3);
                this.calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                this.calendar.clear();
                this.calendar.set(1, Integer.parseInt(year_s));
                this.calendar.set(2, Integer.parseInt(month_s) - 1);
                this.calendar.set(5, Integer.parseInt(day_s));
                return this.calendar.getTime();
            }
            match = TIMESTAMP_REGEXP.matcher(nodeValue);
            if (!match.matches()) {
                throw new YAMLException("Unexpected timestamp: " + nodeValue);
            }
            String year_s = match.group(1);
            String month_s = match.group(2);
            String day_s = match.group(3);
            String hour_s = match.group(4);
            String min_s = match.group(5);
            String seconds = match.group(6);
            String millis = match.group(7);
            if (millis != null) {
                seconds = seconds + "." + millis;
            }
            double fractions = Double.parseDouble(seconds);
            int sec_s = (int)Math.round(Math.floor(fractions));
            int usec = (int)Math.round((fractions - (double)sec_s) * 1000.0);
            String timezoneh_s = match.group(8);
            String timezonem_s = match.group(9);
            if (timezoneh_s != null) {
                String time = timezonem_s != null ? ":" + timezonem_s : "00";
                timeZone = TimeZone.getTimeZone("GMT" + timezoneh_s + time);
            } else {
                timeZone = TimeZone.getTimeZone("UTC");
            }
            this.calendar = Calendar.getInstance(timeZone);
            this.calendar.set(1, Integer.parseInt(year_s));
            this.calendar.set(2, Integer.parseInt(month_s) - 1);
            this.calendar.set(5, Integer.parseInt(day_s));
            this.calendar.set(11, Integer.parseInt(hour_s));
            this.calendar.set(12, Integer.parseInt(min_s));
            this.calendar.set(13, sec_s);
            this.calendar.set(14, usec);
            return this.calendar.getTime();
        }
    }

    public static class ConstructYamlBinary
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String noWhiteSpaces = BaseConstructor.constructScalar((ScalarNode)node);
            byte[] decoded = BinaryEscape.unescapeToBytes(noWhiteSpaces);
            return decoded;
        }
    }

    public class ConstructYamlFloat
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = BaseConstructor.constructScalar((ScalarNode)node).toString().replaceAll("_", "");
            int sign = 1;
            char first = value.charAt(0);
            if (first == '-') {
                sign = -1;
                value = value.substring(1);
            } else if (first == '+') {
                value = value.substring(1);
            }
            String valLower = value.toLowerCase();
            if (".inf".equals(valLower)) {
                return sign == -1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (".nan".equals(valLower)) {
                return Double.NaN;
            }
            if (value.indexOf(58) != -1) {
                String[] digits = value.split(":");
                int bes = 1;
                double val = 0.0;
                int j = digits.length;
                for (int i = 0; i < j; ++i) {
                    val += Double.parseDouble(digits[j - i - 1]) * (double)bes;
                    bes *= 60;
                }
                return (double)sign * val;
            }
            Double d = Double.valueOf(value);
            return d * (double)sign;
        }
    }

    public class ConstructYamlInt
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String value = BaseConstructor.constructScalar((ScalarNode)node).toString().replaceAll("_", "");
            int sign = 1;
            char first = value.charAt(0);
            if (first == '-') {
                sign = -1;
                value = value.substring(1);
            } else if (first == '+') {
                value = value.substring(1);
            }
            int base = 10;
            if ("0".equals(value)) {
                return 0;
            }
            if (value.startsWith("0b")) {
                value = value.substring(2);
                base = 2;
            } else if (value.startsWith("0x")) {
                value = value.substring(2);
                base = 16;
            } else if (value.startsWith("0")) {
                value = value.substring(1);
                base = 8;
            } else {
                if (value.indexOf(58) != -1) {
                    String[] digits = value.split(":");
                    int bes = 1;
                    int val = 0;
                    int j = digits.length;
                    for (int i = 0; i < j; ++i) {
                        val = (int)((long)val + Long.parseLong(digits[j - i - 1]) * (long)bes);
                        bes *= 60;
                    }
                    return SafeConstructor.this.createNumber(sign, String.valueOf(val), 10);
                }
                return SafeConstructor.this.createNumber(sign, value, 10);
            }
            return SafeConstructor.this.createNumber(sign, value, base);
        }
    }

    public class ConstructYamlBool
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            String val = BaseConstructor.constructScalar((ScalarNode)node);
            return BOOL_VALUES.get(val.toLowerCase());
        }
    }

    public class ConstructYamlNull
    extends AbstractConstruct {
        @Override
        public Object construct(Node node) {
            if (node != null) {
                BaseConstructor.constructScalar((ScalarNode)node);
            }
            return null;
        }
    }
}

