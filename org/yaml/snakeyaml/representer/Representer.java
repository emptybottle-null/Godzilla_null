/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.representer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.annotation.YamlComment;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.events.CommentEvent;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.SafeRepresenter;

public class Representer
extends SafeRepresenter {
    protected Map<Class<? extends Object>, TypeDescription> typeDefinitions = Collections.emptyMap();

    public Representer() {
        this.representers.put(null, new RepresentJavaBean());
    }

    public Representer(DumperOptions options) {
        super(options);
        this.representers.put(null, new RepresentJavaBean());
    }

    public TypeDescription addTypeDescription(TypeDescription td) {
        if (Collections.EMPTY_MAP == this.typeDefinitions) {
            this.typeDefinitions = new HashMap<Class<? extends Object>, TypeDescription>();
        }
        if (td.getTag() != null) {
            this.addClassTag((Class)td.getType(), td.getTag());
        }
        td.setPropertyUtils(this.getPropertyUtils());
        return this.typeDefinitions.put(td.getType(), td);
    }

    @Override
    public void setPropertyUtils(PropertyUtils propertyUtils) {
        super.setPropertyUtils(propertyUtils);
        Collection<TypeDescription> tds = this.typeDefinitions.values();
        for (TypeDescription typeDescription : tds) {
            typeDescription.setPropertyUtils(propertyUtils);
        }
    }

    protected MappingNode representJavaBean(Set<Property> properties, Object javaBean) {
        ArrayList<NodeTuple> value = new ArrayList<NodeTuple>(properties.size());
        Tag customTag = (Tag)this.classTags.get(javaBean.getClass());
        Tag tag = customTag != null ? customTag : new Tag(javaBean.getClass());
        MappingNode node = new MappingNode(tag, value, DumperOptions.FlowStyle.AUTO);
        this.representedObjects.put(javaBean, node);
        DumperOptions.FlowStyle bestStyle = DumperOptions.FlowStyle.FLOW;
        Iterator<Property> iterator = properties.iterator();
        while (iterator.hasNext()) {
            YamlComment comment;
            Node nodeValue;
            Property property;
            Object memberValue;
            Tag customPropertyTag = (memberValue = (property = iterator.next()).get(javaBean)) == null ? null : (Tag)this.classTags.get(memberValue.getClass());
            NodeTuple tuple = this.representJavaBeanProperty(javaBean, property, memberValue, customPropertyTag);
            if (tuple == null) continue;
            Node nodeKey = tuple.getKeyNode();
            if (!((ScalarNode)nodeKey).isPlain()) {
                bestStyle = DumperOptions.FlowStyle.BLOCK;
            }
            if (!((nodeValue = tuple.getValueNode()) instanceof ScalarNode) || !((ScalarNode)nodeValue).isPlain()) {
                bestStyle = DumperOptions.FlowStyle.BLOCK;
            }
            if ((comment = property.getAnnotation(YamlComment.class)) != null) {
                if (nodeKey.getBlockComments() == null) {
                    nodeKey.setBlockComments(new ArrayList<CommentLine>());
                }
                nodeKey.getBlockComments().add(new CommentLine(new CommentEvent(CommentType.BLOCK, comment.Comment(), null, null)));
            }
            value.add(tuple);
        }
        if (this.defaultFlowStyle != DumperOptions.FlowStyle.AUTO) {
            node.setFlowStyle(this.defaultFlowStyle);
        } else {
            node.setFlowStyle(bestStyle);
        }
        return node;
    }

    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
        ScalarNode nodeKey = (ScalarNode)this.representData(property.getName());
        boolean hasAlias = this.representedObjects.containsKey(propertyValue);
        Node nodeValue = this.representData(propertyValue);
        if (propertyValue != null && !hasAlias) {
            NodeId nodeId = nodeValue.getNodeId();
            if (customTag == null) {
                if (nodeId == NodeId.scalar) {
                    if (property.getType() != Enum.class && propertyValue instanceof Enum) {
                        nodeValue.setTag(Tag.STR);
                    }
                } else {
                    if (nodeId == NodeId.mapping && property.getType() == propertyValue.getClass() && !(propertyValue instanceof Map) && !nodeValue.getTag().equals(Tag.SET)) {
                        nodeValue.setTag(Tag.MAP);
                    }
                    this.checkGlobalTag(property, nodeValue, propertyValue);
                }
            }
        }
        return new NodeTuple(nodeKey, nodeValue);
    }

    protected void checkGlobalTag(Property property, Node node, Object object) {
        block10: {
            Class<?>[] arguments;
            block11: {
                if (object.getClass().isArray() && object.getClass().getComponentType().isPrimitive()) {
                    return;
                }
                arguments = property.getActualTypeArguments();
                if (arguments == null) break block10;
                if (node.getNodeId() != NodeId.sequence) break block11;
                Class<?> t = arguments[0];
                SequenceNode snode = (SequenceNode)node;
                Iterable<Object> memberList = Collections.EMPTY_LIST;
                if (object.getClass().isArray()) {
                    memberList = Arrays.asList((Object[])object);
                } else if (object instanceof Iterable) {
                    memberList = (Iterable)object;
                }
                Iterator iter = memberList.iterator();
                if (!iter.hasNext()) break block10;
                for (Node childNode : snode.getValue()) {
                    Object member = iter.next();
                    if (member == null || !t.equals(member.getClass()) || childNode.getNodeId() != NodeId.mapping) continue;
                    childNode.setTag(Tag.MAP);
                }
                break block10;
            }
            if (object instanceof Set) {
                Class<?> t = arguments[0];
                MappingNode mnode = (MappingNode)node;
                Iterator<NodeTuple> iter = mnode.getValue().iterator();
                Set set = (Set)object;
                for (Object member : set) {
                    NodeTuple tuple = iter.next();
                    Node keyNode = tuple.getKeyNode();
                    if (!t.equals(member.getClass()) || keyNode.getNodeId() != NodeId.mapping) continue;
                    keyNode.setTag(Tag.MAP);
                }
            } else if (object instanceof Map) {
                Class<?> keyType = arguments[0];
                Class<?> valueType = arguments[1];
                MappingNode mnode = (MappingNode)node;
                for (NodeTuple tuple : mnode.getValue()) {
                    this.resetTag(keyType, tuple.getKeyNode());
                    this.resetTag(valueType, tuple.getValueNode());
                }
            }
        }
    }

    private void resetTag(Class<? extends Object> type, Node node) {
        Tag tag = node.getTag();
        if (tag.matches(type)) {
            if (Enum.class.isAssignableFrom(type)) {
                node.setTag(Tag.STR);
            } else {
                node.setTag(Tag.MAP);
            }
        }
    }

    protected Set<Property> getProperties(Class<? extends Object> type) {
        if (this.typeDefinitions.containsKey(type)) {
            return this.typeDefinitions.get(type).getProperties();
        }
        return this.getPropertyUtils().getProperties(type);
    }

    protected class RepresentJavaBean
    implements Represent {
        protected RepresentJavaBean() {
        }

        @Override
        public Node representData(Object data) {
            return Representer.this.representJavaBean(Representer.this.getProperties(data.getClass()), data);
        }
    }
}

