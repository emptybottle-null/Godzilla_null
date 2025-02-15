/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.analysis;

import java.util.ArrayList;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.analysis.Analyzer;
import javassist.bytecode.analysis.Frame;
import javassist.bytecode.stackmap.BasicBlock;

public class ControlFlow {
    private CtClass clazz;
    private MethodInfo methodInfo;
    private Block[] basicBlocks;
    private Frame[] frames;

    public ControlFlow(CtMethod method) throws BadBytecode {
        this(method.getDeclaringClass(), method.getMethodInfo2());
    }

    public ControlFlow(CtClass ctclazz, MethodInfo minfo) throws BadBytecode {
        Block b;
        int i;
        this.clazz = ctclazz;
        this.methodInfo = minfo;
        this.frames = null;
        this.basicBlocks = (Block[])new BasicBlock.Maker(){

            @Override
            protected BasicBlock makeBlock(int pos) {
                return new Block(pos, ControlFlow.this.methodInfo);
            }

            @Override
            protected BasicBlock[] makeArray(int size) {
                return new Block[size];
            }
        }.make(minfo);
        if (this.basicBlocks == null) {
            this.basicBlocks = new Block[0];
        }
        int size = this.basicBlocks.length;
        int[] counters = new int[size];
        for (i = 0; i < size; ++i) {
            b = this.basicBlocks[i];
            b.index = i;
            b.entrances = new Block[b.incomings()];
            counters[i] = 0;
        }
        for (i = 0; i < size; ++i) {
            b = this.basicBlocks[i];
            for (int k = 0; k < b.exits(); ++k) {
                Block e = b.exit(k);
                int n = e.index;
                int n2 = counters[n];
                counters[n] = n2 + 1;
                e.entrances[n2] = b;
            }
            Catcher[] catchers = b.catchers();
            for (int k = 0; k < catchers.length; ++k) {
                Block catchBlock = catchers[k].node;
                int n = catchBlock.index;
                int n3 = counters[n];
                counters[n] = n3 + 1;
                catchBlock.entrances[n3] = b;
            }
        }
    }

    public Block[] basicBlocks() {
        return this.basicBlocks;
    }

    public Frame frameAt(int pos) throws BadBytecode {
        if (this.frames == null) {
            this.frames = new Analyzer().analyze(this.clazz, this.methodInfo);
        }
        return this.frames[pos];
    }

    public Node[] dominatorTree() {
        int size = this.basicBlocks.length;
        if (size == 0) {
            return null;
        }
        Node[] nodes = new Node[size];
        boolean[] visited = new boolean[size];
        int[] distance = new int[size];
        for (int i = 0; i < size; ++i) {
            nodes[i] = new Node(this.basicBlocks[i]);
            visited[i] = false;
        }
        Access access = new Access(nodes){

            @Override
            BasicBlock[] exits(Node n) {
                return n.block.getExit();
            }

            @Override
            BasicBlock[] entrances(Node n) {
                return ((Node)n).block.entrances;
            }
        };
        nodes[0].makeDepth1stTree(null, visited, 0, distance, access);
        do {
            for (int i = 0; i < size; ++i) {
                visited[i] = false;
            }
        } while (nodes[0].makeDominatorTree(visited, distance, access));
        Node.setChildren(nodes);
        return nodes;
    }

    public Node[] postDominatorTree() {
        boolean changed;
        int size = this.basicBlocks.length;
        if (size == 0) {
            return null;
        }
        Node[] nodes = new Node[size];
        boolean[] visited = new boolean[size];
        int[] distance = new int[size];
        for (int i = 0; i < size; ++i) {
            nodes[i] = new Node(this.basicBlocks[i]);
            visited[i] = false;
        }
        Access access = new Access(nodes){

            @Override
            BasicBlock[] exits(Node n) {
                return ((Node)n).block.entrances;
            }

            @Override
            BasicBlock[] entrances(Node n) {
                return n.block.getExit();
            }
        };
        int counter = 0;
        for (int i = 0; i < size; ++i) {
            if (nodes[i].block.exits() != 0) continue;
            counter = nodes[i].makeDepth1stTree(null, visited, counter, distance, access);
        }
        do {
            int i;
            for (i = 0; i < size; ++i) {
                visited[i] = false;
            }
            changed = false;
            for (i = 0; i < size; ++i) {
                if (nodes[i].block.exits() != 0 || !nodes[i].makeDominatorTree(visited, distance, access)) continue;
                changed = true;
            }
        } while (changed);
        Node.setChildren(nodes);
        return nodes;
    }

    public static class Catcher {
        private Block node;
        private int typeIndex;

        Catcher(BasicBlock.Catch c) {
            this.node = (Block)c.body;
            this.typeIndex = c.typeIndex;
        }

        public Block block() {
            return this.node;
        }

        public String type() {
            if (this.typeIndex == 0) {
                return "java.lang.Throwable";
            }
            return this.node.method.getConstPool().getClassInfo(this.typeIndex);
        }
    }

    public static class Node {
        private Block block;
        private Node parent;
        private Node[] children;

        Node(Block b) {
            this.block = b;
            this.parent = null;
        }

        public String toString() {
            StringBuffer sbuf = new StringBuffer();
            sbuf.append("Node[pos=").append(this.block().position());
            sbuf.append(", parent=");
            sbuf.append(this.parent == null ? "*" : Integer.toString(this.parent.block().position()));
            sbuf.append(", children{");
            for (int i = 0; i < this.children.length; ++i) {
                sbuf.append(this.children[i].block().position()).append(", ");
            }
            sbuf.append("}]");
            return sbuf.toString();
        }

        public Block block() {
            return this.block;
        }

        public Node parent() {
            return this.parent;
        }

        public int children() {
            return this.children.length;
        }

        public Node child(int n) {
            return this.children[n];
        }

        int makeDepth1stTree(Node caller, boolean[] visited, int counter, int[] distance, Access access) {
            int index = this.block.index;
            if (visited[index]) {
                return counter;
            }
            visited[index] = true;
            this.parent = caller;
            BasicBlock[] exits = access.exits(this);
            if (exits != null) {
                for (int i = 0; i < exits.length; ++i) {
                    Node n = access.node(exits[i]);
                    counter = n.makeDepth1stTree(this, visited, counter, distance, access);
                }
            }
            distance[index] = counter++;
            return counter;
        }

        boolean makeDominatorTree(boolean[] visited, int[] distance, Access access) {
            BasicBlock[] entrances;
            int index = this.block.index;
            if (visited[index]) {
                return false;
            }
            visited[index] = true;
            boolean changed = false;
            BasicBlock[] exits = access.exits(this);
            if (exits != null) {
                for (int i = 0; i < exits.length; ++i) {
                    Node n = access.node(exits[i]);
                    if (!n.makeDominatorTree(visited, distance, access)) continue;
                    changed = true;
                }
            }
            if ((entrances = access.entrances(this)) != null) {
                for (int i = 0; i < entrances.length; ++i) {
                    Node n;
                    if (this.parent == null || (n = Node.getAncestor(this.parent, access.node(entrances[i]), distance)) == this.parent) continue;
                    this.parent = n;
                    changed = true;
                }
            }
            return changed;
        }

        private static Node getAncestor(Node n1, Node n2, int[] distance) {
            while (n1 != n2) {
                if (distance[n1.block.index] < distance[n2.block.index]) {
                    n1 = n1.parent;
                } else {
                    n2 = n2.parent;
                }
                if (n1 != null && n2 != null) continue;
                return null;
            }
            return n1;
        }

        private static void setChildren(Node[] all) {
            int i;
            int size = all.length;
            int[] nchildren = new int[size];
            for (i = 0; i < size; ++i) {
                nchildren[i] = 0;
            }
            for (i = 0; i < size; ++i) {
                Node p = all[i].parent;
                if (p == null) continue;
                int n = p.block.index;
                nchildren[n] = nchildren[n] + 1;
            }
            for (i = 0; i < size; ++i) {
                all[i].children = new Node[nchildren[i]];
            }
            for (i = 0; i < size; ++i) {
                nchildren[i] = 0;
            }
            for (i = 0; i < size; ++i) {
                Node n = all[i];
                Node p = n.parent;
                if (p == null) continue;
                int n2 = p.block.index;
                int n3 = nchildren[n2];
                nchildren[n2] = n3 + 1;
                p.children[n3] = n;
            }
        }
    }

    static abstract class Access {
        Node[] all;

        Access(Node[] nodes) {
            this.all = nodes;
        }

        Node node(BasicBlock b) {
            return this.all[((Block)b).index];
        }

        abstract BasicBlock[] exits(Node var1);

        abstract BasicBlock[] entrances(Node var1);
    }

    public static class Block
    extends BasicBlock {
        public Object clientData = null;
        int index;
        MethodInfo method;
        Block[] entrances;

        Block(int pos, MethodInfo minfo) {
            super(pos);
            this.method = minfo;
        }

        @Override
        protected void toString2(StringBuffer sbuf) {
            super.toString2(sbuf);
            sbuf.append(", incoming{");
            for (int i = 0; i < this.entrances.length; ++i) {
                sbuf.append(this.entrances[i].position).append(", ");
            }
            sbuf.append("}");
        }

        BasicBlock[] getExit() {
            return this.exit;
        }

        public int index() {
            return this.index;
        }

        public int position() {
            return this.position;
        }

        public int length() {
            return this.length;
        }

        public int incomings() {
            return this.incoming;
        }

        public Block incoming(int n) {
            return this.entrances[n];
        }

        public int exits() {
            return this.exit == null ? 0 : this.exit.length;
        }

        public Block exit(int n) {
            return (Block)this.exit[n];
        }

        public Catcher[] catchers() {
            ArrayList<Catcher> catchers = new ArrayList<Catcher>();
            BasicBlock.Catch c = this.toCatch;
            while (c != null) {
                catchers.add(new Catcher(c));
                c = c.next;
            }
            return catchers.toArray(new Catcher[catchers.size()]);
        }
    }
}

