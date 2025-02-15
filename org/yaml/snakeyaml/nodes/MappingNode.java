/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.nodes;

import java.util.List;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.nodes.CollectionNode;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;

public class MappingNode
extends CollectionNode<NodeTuple> {
    private List<NodeTuple> value;
    private boolean merged = false;

    public MappingNode(Tag tag, boolean resolved, List<NodeTuple> value, Mark startMark, Mark endMark, DumperOptions.FlowStyle flowStyle) {
        super(tag, startMark, endMark, flowStyle);
        if (value == null) {
            throw new NullPointerException("value in a Node is required.");
        }
        this.value = value;
        this.resolved = resolved;
    }

    public MappingNode(Tag tag, List<NodeTuple> value, DumperOptions.FlowStyle flowStyle) {
        this(tag, true, value, null, null, flowStyle);
    }

    @Deprecated
    public MappingNode(Tag tag, boolean resolved, List<NodeTuple> value, Mark startMark, Mark endMark, Boolean flowStyle) {
        this(tag, resolved, value, startMark, endMark, DumperOptions.FlowStyle.fromBoolean(flowStyle));
    }

    @Deprecated
    public MappingNode(Tag tag, List<NodeTuple> value, Boolean flowStyle) {
        this(tag, value, DumperOptions.FlowStyle.fromBoolean(flowStyle));
    }

    @Override
    public NodeId getNodeId() {
        return NodeId.mapping;
    }

    @Override
    public List<NodeTuple> getValue() {
        return this.value;
    }

    public void setValue(List<NodeTuple> mergedValue) {
        this.value = mergedValue;
    }

    public void setOnlyKeyType(Class<? extends Object> keyType) {
        for (NodeTuple nodes : this.value) {
            nodes.getKeyNode().setType(keyType);
        }
    }

    public void setTypes(Class<? extends Object> keyType, Class<? extends Object> valueType) {
        for (NodeTuple nodes : this.value) {
            nodes.getValueNode().setType(valueType);
            nodes.getKeyNode().setType(keyType);
        }
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (NodeTuple node : this.getValue()) {
            buf.append("{ key=");
            buf.append(node.getKeyNode());
            buf.append("; value=");
            if (node.getValueNode() instanceof CollectionNode) {
                buf.append(System.identityHashCode(node.getValueNode()));
            } else {
                buf.append(node.toString());
            }
            buf.append(" }");
        }
        String values = buf.toString();
        return "<" + this.getClass().getName() + " (tag=" + this.getTag() + ", values=" + values + ")>";
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public boolean isMerged() {
        return this.merged;
    }
}

