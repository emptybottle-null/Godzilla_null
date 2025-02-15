/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.constructor;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.composer.Composer;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.ConstructorException;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.CollectionNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;

public abstract class BaseConstructor {
    protected final Map<NodeId, Construct> yamlClassConstructors = new EnumMap<NodeId, Construct>(NodeId.class);
    protected final Map<Tag, Construct> yamlConstructors = new HashMap<Tag, Construct>();
    protected final Map<String, Construct> yamlMultiConstructors = new HashMap<String, Construct>();
    protected Composer composer;
    final Map<Node, Object> constructedObjects = new HashMap<Node, Object>();
    private final Set<Node> recursiveObjects = new HashSet<Node>();
    private final ArrayList<RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>>> maps2fill = new ArrayList();
    private final ArrayList<RecursiveTuple<Set<Object>, Object>> sets2fill = new ArrayList();
    protected Tag rootTag = null;
    private PropertyUtils propertyUtils;
    private boolean explicitPropertyUtils = false;
    private boolean allowDuplicateKeys = true;
    private boolean wrappedToRootException = false;
    private boolean enumCaseSensitive = false;
    protected final Map<Class<? extends Object>, TypeDescription> typeDefinitions = new HashMap<Class<? extends Object>, TypeDescription>();
    protected final Map<Class<? extends Object>, Construct> typeRedefinition = new HashMap<Class<? extends Object>, Construct>();
    protected final Map<Tag, Class<? extends Object>> typeTags = new HashMap<Tag, Class<? extends Object>>();
    protected LoaderOptions loadingConfig;

    public BaseConstructor() {
        this(new LoaderOptions());
    }

    public BaseConstructor(LoaderOptions loadingConfig) {
        this.typeDefinitions.put(SortedMap.class, new TypeDescription(SortedMap.class, Tag.OMAP, TreeMap.class));
        this.typeDefinitions.put(SortedSet.class, new TypeDescription(SortedSet.class, Tag.SET, TreeSet.class));
        this.typeRedefinition.put(byte[].class, new SafeConstructor.ConstructYamlBinary());
        this.loadingConfig = loadingConfig;
    }

    public void setComposer(Composer composer) {
        this.composer = composer;
    }

    public boolean checkData() {
        return this.composer.checkNode();
    }

    public Object getData() throws NoSuchElementException {
        if (!this.composer.checkNode()) {
            throw new NoSuchElementException("No document is available.");
        }
        Node node = this.composer.getNode();
        if (this.rootTag != null) {
            node.setTag(this.rootTag);
        }
        return this.constructDocument(node);
    }

    public Object getSingleData(Class<?> type) {
        Node node = this.composer.getSingleNode();
        if (node != null && !Tag.NULL.equals(node.getTag())) {
            if (Object.class != type) {
                node.setTag(new Tag(type));
            } else if (this.rootTag != null) {
                node.setTag(this.rootTag);
            }
            return this.constructDocument(node);
        }
        Construct construct = this.yamlConstructors.get(Tag.NULL);
        return construct.construct(node);
    }

    protected final Object constructDocument(Node node) {
        try {
            Object data = this.constructObject(node);
            this.fillRecursive();
            Object object = data;
            return object;
        } catch (RuntimeException e) {
            if (this.wrappedToRootException && !(e instanceof YAMLException)) {
                throw new YAMLException(e);
            }
            throw e;
        } finally {
            this.constructedObjects.clear();
            this.recursiveObjects.clear();
        }
    }

    private void fillRecursive() {
        if (!this.maps2fill.isEmpty()) {
            for (RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>> recursiveTuple : this.maps2fill) {
                RecursiveTuple<Object, Object> key_value = recursiveTuple._2();
                recursiveTuple._1().put(key_value._1(), key_value._2());
            }
            this.maps2fill.clear();
        }
        if (!this.sets2fill.isEmpty()) {
            for (RecursiveTuple<Object, Object> recursiveTuple : this.sets2fill) {
                ((Set)recursiveTuple._1()).add(recursiveTuple._2());
            }
            this.sets2fill.clear();
        }
    }

    protected Object constructObject(Node node) {
        if (this.constructedObjects.containsKey(node)) {
            return this.constructedObjects.get(node);
        }
        return this.constructObjectNoCheck(node);
    }

    protected Object constructObjectNoCheck(Node node) {
        if (this.recursiveObjects.contains(node)) {
            throw new ConstructorException(null, null, "found unconstructable recursive node", node.getStartMark());
        }
        this.recursiveObjects.add(node);
        Construct constructor = this.getConstructor(node);
        Object data = this.constructedObjects.containsKey(node) ? this.constructedObjects.get(node) : constructor.construct(node);
        this.finalizeConstruction(node, data);
        this.constructedObjects.put(node, data);
        this.recursiveObjects.remove(node);
        if (node.isTwoStepsConstruction()) {
            constructor.construct2ndStep(node, data);
        }
        return data;
    }

    protected Construct getConstructor(Node node) {
        if (node.useClassConstructor()) {
            return this.yamlClassConstructors.get((Object)node.getNodeId());
        }
        Construct constructor = this.yamlConstructors.get(node.getTag());
        if (constructor == null) {
            for (String prefix : this.yamlMultiConstructors.keySet()) {
                if (!node.getTag().startsWith(prefix)) continue;
                return this.yamlMultiConstructors.get(prefix);
            }
            return this.yamlConstructors.get(null);
        }
        return constructor;
    }

    protected static String constructScalar(ScalarNode node) {
        return node.getValue();
    }

    protected List<Object> createDefaultList(int initSize) {
        return new ArrayList<Object>(initSize);
    }

    protected Set<Object> createDefaultSet(int initSize) {
        return new LinkedHashSet<Object>(initSize);
    }

    protected Map<Object, Object> createDefaultMap(int initSize) {
        return new LinkedHashMap<Object, Object>(initSize);
    }

    protected Object createArray(Class<?> type, int size) {
        return Array.newInstance(type.getComponentType(), size);
    }

    protected Object finalizeConstruction(Node node, Object data) {
        Class<? extends Object> type = node.getType();
        if (this.typeDefinitions.containsKey(type)) {
            return this.typeDefinitions.get(type).finalizeConstruction(data);
        }
        return data;
    }

    protected Object newInstance(Node node) {
        try {
            return this.newInstance(Object.class, node);
        } catch (InstantiationException e) {
            throw new YAMLException(e);
        }
    }

    protected final Object newInstance(Class<?> ancestor, Node node) throws InstantiationException {
        return this.newInstance(ancestor, node, true);
    }

    protected Object newInstance(Class<?> ancestor, Node node, boolean tryDefault) throws InstantiationException {
        TypeDescription td;
        Object instance;
        Class<? extends Object> type = node.getType();
        if (this.typeDefinitions.containsKey(type) && (instance = (td = this.typeDefinitions.get(type)).newInstance(node)) != null) {
            return instance;
        }
        if (this.typeRedefinition.containsKey(type)) {
            return this.typeRedefinition.get(type).construct(node);
        }
        if (tryDefault && ancestor.isAssignableFrom(type) && !Modifier.isAbstract(type.getModifiers())) {
            try {
                Constructor<? extends Object> c = type.getDeclaredConstructor(new Class[0]);
                c.setAccessible(true);
                return c.newInstance(new Object[0]);
            } catch (NoSuchMethodException e) {
                throw new InstantiationException("NoSuchMethodException:" + e.getLocalizedMessage());
            } catch (Exception e) {
                throw new YAMLException(e);
            }
        }
        throw new InstantiationException();
    }

    protected Set<Object> newSet(CollectionNode<?> node) {
        try {
            return (Set)this.newInstance(Set.class, node);
        } catch (InstantiationException e) {
            return this.createDefaultSet(node.getValue().size());
        }
    }

    protected List<Object> newList(SequenceNode node) {
        try {
            return (List)this.newInstance(List.class, node);
        } catch (InstantiationException e) {
            return this.createDefaultList(node.getValue().size());
        }
    }

    protected Map<Object, Object> newMap(MappingNode node) {
        try {
            return (Map)this.newInstance(Map.class, node);
        } catch (InstantiationException e) {
            return this.createDefaultMap(node.getValue().size());
        }
    }

    protected List<? extends Object> constructSequence(SequenceNode node) {
        List<Object> result = this.newList(node);
        this.constructSequenceStep2(node, result);
        return result;
    }

    protected Set<? extends Object> constructSet(SequenceNode node) {
        Set<Object> result = this.newSet(node);
        this.constructSequenceStep2(node, result);
        return result;
    }

    protected Object constructArray(SequenceNode node) {
        return this.constructArrayStep2(node, this.createArray(node.getType(), node.getValue().size()));
    }

    protected void constructSequenceStep2(SequenceNode node, Collection<Object> collection) {
        for (Node child : node.getValue()) {
            collection.add(this.constructObject(child));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected Object constructArrayStep2(SequenceNode node, Object array) {
        Class<?> componentType = node.getType().getComponentType();
        int index = 0;
        for (Node child : node.getValue()) {
            if (child.getType() == Object.class) {
                child.setType(componentType);
            }
            Object value = this.constructObject(child);
            if (componentType.isPrimitive()) {
                if (value == null) {
                    throw new NullPointerException("Unable to construct element value for " + child);
                }
                if (Byte.TYPE.equals(componentType)) {
                    Array.setByte(array, index, ((Number)value).byteValue());
                } else if (Short.TYPE.equals(componentType)) {
                    Array.setShort(array, index, ((Number)value).shortValue());
                } else if (Integer.TYPE.equals(componentType)) {
                    Array.setInt(array, index, ((Number)value).intValue());
                } else if (Long.TYPE.equals(componentType)) {
                    Array.setLong(array, index, ((Number)value).longValue());
                } else if (Float.TYPE.equals(componentType)) {
                    Array.setFloat(array, index, ((Number)value).floatValue());
                } else if (Double.TYPE.equals(componentType)) {
                    Array.setDouble(array, index, ((Number)value).doubleValue());
                } else if (Character.TYPE.equals(componentType)) {
                    Array.setChar(array, index, ((Character)value).charValue());
                } else {
                    if (!Boolean.TYPE.equals(componentType)) throw new YAMLException("unexpected primitive type");
                    Array.setBoolean(array, index, (Boolean)value);
                }
            } else {
                Array.set(array, index, value);
            }
            ++index;
        }
        return array;
    }

    protected Set<Object> constructSet(MappingNode node) {
        Set<Object> set = this.newSet(node);
        this.constructSet2ndStep(node, set);
        return set;
    }

    protected Map<Object, Object> constructMapping(MappingNode node) {
        Map<Object, Object> mapping = this.newMap(node);
        this.constructMapping2ndStep(node, mapping);
        return mapping;
    }

    protected void constructMapping2ndStep(MappingNode node, Map<Object, Object> mapping) {
        List<NodeTuple> nodeValue = node.getValue();
        for (NodeTuple tuple : nodeValue) {
            Node keyNode = tuple.getKeyNode();
            Node valueNode = tuple.getValueNode();
            Object key = this.constructObject(keyNode);
            if (key != null) {
                try {
                    key.hashCode();
                } catch (Exception e) {
                    throw new ConstructorException("while constructing a mapping", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
                }
            }
            Object value = this.constructObject(valueNode);
            if (keyNode.isTwoStepsConstruction()) {
                if (this.loadingConfig.getAllowRecursiveKeys()) {
                    this.postponeMapFilling(mapping, key, value);
                    continue;
                }
                throw new YAMLException("Recursive key for mapping is detected but it is not configured to be allowed.");
            }
            mapping.put(key, value);
        }
    }

    protected void postponeMapFilling(Map<Object, Object> mapping, Object key, Object value) {
        this.maps2fill.add(0, new RecursiveTuple<Map<Object, Object>, RecursiveTuple<Object, Object>>(mapping, new RecursiveTuple<Object, Object>(key, value)));
    }

    protected void constructSet2ndStep(MappingNode node, Set<Object> set) {
        List<NodeTuple> nodeValue = node.getValue();
        for (NodeTuple tuple : nodeValue) {
            Node keyNode = tuple.getKeyNode();
            Object key = this.constructObject(keyNode);
            if (key != null) {
                try {
                    key.hashCode();
                } catch (Exception e) {
                    throw new ConstructorException("while constructing a Set", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
                }
            }
            if (keyNode.isTwoStepsConstruction()) {
                this.postponeSetFilling(set, key);
                continue;
            }
            set.add(key);
        }
    }

    protected void postponeSetFilling(Set<Object> set, Object key) {
        this.sets2fill.add(0, new RecursiveTuple<Set<Object>, Object>(set, key));
    }

    public void setPropertyUtils(PropertyUtils propertyUtils) {
        this.propertyUtils = propertyUtils;
        this.explicitPropertyUtils = true;
        Collection<TypeDescription> tds = this.typeDefinitions.values();
        for (TypeDescription typeDescription : tds) {
            typeDescription.setPropertyUtils(propertyUtils);
        }
    }

    public final PropertyUtils getPropertyUtils() {
        if (this.propertyUtils == null) {
            this.propertyUtils = new PropertyUtils();
        }
        return this.propertyUtils;
    }

    public TypeDescription addTypeDescription(TypeDescription definition) {
        if (definition == null) {
            throw new NullPointerException("TypeDescription is required.");
        }
        Tag tag = definition.getTag();
        this.typeTags.put(tag, definition.getType());
        definition.setPropertyUtils(this.getPropertyUtils());
        return this.typeDefinitions.put(definition.getType(), definition);
    }

    public final boolean isExplicitPropertyUtils() {
        return this.explicitPropertyUtils;
    }

    public boolean isAllowDuplicateKeys() {
        return this.allowDuplicateKeys;
    }

    public void setAllowDuplicateKeys(boolean allowDuplicateKeys) {
        this.allowDuplicateKeys = allowDuplicateKeys;
    }

    public boolean isWrappedToRootException() {
        return this.wrappedToRootException;
    }

    public void setWrappedToRootException(boolean wrappedToRootException) {
        this.wrappedToRootException = wrappedToRootException;
    }

    public boolean isEnumCaseSensitive() {
        return this.enumCaseSensitive;
    }

    public void setEnumCaseSensitive(boolean enumCaseSensitive) {
        this.enumCaseSensitive = enumCaseSensitive;
    }

    private static class RecursiveTuple<T, K> {
        private final T _1;
        private final K _2;

        public RecursiveTuple(T _1, K _2) {
            this._1 = _1;
            this._2 = _2;
        }

        public K _2() {
            return this._2;
        }

        public T _1() {
            return this._1;
        }
    }
}

