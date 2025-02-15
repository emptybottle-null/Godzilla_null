/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors
 *  com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator.NodeAndChildren
 */
package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.BaseGraph;
import com.google.common.graph.Network;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
public abstract class Traverser<N> {
    public static <N> Traverser<N> forGraph(SuccessorsFunction<N> graph) {
        Preconditions.checkNotNull(graph);
        return new GraphTraverser<N>(graph);
    }

    public static <N> Traverser<N> forTree(SuccessorsFunction<N> tree) {
        Preconditions.checkNotNull(tree);
        if (tree instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph)tree).isDirected(), "Undirected graphs can never be trees.");
        }
        if (tree instanceof Network) {
            Preconditions.checkArgument(((Network)tree).isDirected(), "Undirected networks can never be trees.");
        }
        return new TreeTraverser<N>(tree);
    }

    public abstract Iterable<N> breadthFirst(N var1);

    public abstract Iterable<N> breadthFirst(Iterable<? extends N> var1);

    public abstract Iterable<N> depthFirstPreOrder(N var1);

    public abstract Iterable<N> depthFirstPreOrder(Iterable<? extends N> var1);

    public abstract Iterable<N> depthFirstPostOrder(N var1);

    public abstract Iterable<N> depthFirstPostOrder(Iterable<? extends N> var1);

    private Traverser() {
    }

    private static enum Order {
        PREORDER,
        POSTORDER;

    }

    private static final class TreeTraverser<N>
    extends Traverser<N> {
        private final SuccessorsFunction<N> tree;

        TreeTraverser(SuccessorsFunction<N> tree) {
            this.tree = Preconditions.checkNotNull(tree);
        }

        @Override
        public Iterable<N> breadthFirst(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.breadthFirst((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> breadthFirst(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N startNode : startNodes) {
                this.checkThatNodeIsInTree(startNode);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(startNodes);
                }
            };
        }

        @Override
        public Iterable<N> depthFirstPreOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.depthFirstPreOrder((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N node : startNodes) {
                this.checkThatNodeIsInTree(node);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new DepthFirstPreOrderIterator(startNodes);
                }
            };
        }

        @Override
        public Iterable<N> depthFirstPostOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.depthFirstPostOrder((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N startNode : startNodes) {
                this.checkThatNodeIsInTree(startNode);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new DepthFirstPostOrderIterator(startNodes);
                }
            };
        }

        private void checkThatNodeIsInTree(N startNode) {
            this.tree.successors(startNode);
        }

        private final class DepthFirstPostOrderIterator
        extends AbstractIterator<N> {
            private final ArrayDeque<com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator.NodeAndChildren> stack = new ArrayDeque();

            DepthFirstPostOrderIterator(Iterable<? extends N> roots) {
                this.stack.addLast((com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator.NodeAndChildren)new NodeAndChildren(null, roots));
            }

            @Override
            protected N computeNext() {
                while (!this.stack.isEmpty()) {
                    NodeAndChildren top = (NodeAndChildren)this.stack.getLast();
                    if (top.childIterator.hasNext()) {
                        Object child = top.childIterator.next();
                        this.stack.addLast((com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator.NodeAndChildren)this.withChildren(child));
                        continue;
                    }
                    this.stack.removeLast();
                    if (top.node == null) continue;
                    return top.node;
                }
                return this.endOfData();
            }

            com.google.common.graph.Traverser$TreeTraverser$DepthFirstPostOrderIterator.NodeAndChildren withChildren(N node) {
                return new NodeAndChildren(node, TreeTraverser.this.tree.successors(node));
            }

            private final class NodeAndChildren {
                final @Nullable N node;
                final Iterator<? extends N> childIterator;

                NodeAndChildren(N node, Iterable<? extends N> children) {
                    this.node = node;
                    this.childIterator = children.iterator();
                }
            }
        }

        private final class DepthFirstPreOrderIterator
        extends UnmodifiableIterator<N> {
            private final Deque<Iterator<? extends N>> stack = new ArrayDeque();

            DepthFirstPreOrderIterator(Iterable<? extends N> roots) {
                this.stack.addLast(roots.iterator());
            }

            @Override
            public boolean hasNext() {
                return !this.stack.isEmpty();
            }

            @Override
            public N next() {
                Iterator childIterator;
                Iterator iterator = this.stack.getLast();
                Object result = Preconditions.checkNotNull(iterator.next());
                if (!iterator.hasNext()) {
                    this.stack.removeLast();
                }
                if ((childIterator = TreeTraverser.this.tree.successors(result).iterator()).hasNext()) {
                    this.stack.addLast(childIterator);
                }
                return result;
            }
        }

        private final class BreadthFirstIterator
        extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();

            BreadthFirstIterator(Iterable<? extends N> roots) {
                for (Object root : roots) {
                    this.queue.add(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override
            public N next() {
                Object current = this.queue.remove();
                Iterables.addAll(this.queue, TreeTraverser.this.tree.successors(current));
                return current;
            }
        }
    }

    private static final class GraphTraverser<N>
    extends Traverser<N> {
        private final SuccessorsFunction<N> graph;

        GraphTraverser(SuccessorsFunction<N> graph) {
            this.graph = Preconditions.checkNotNull(graph);
        }

        @Override
        public Iterable<N> breadthFirst(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.breadthFirst((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> breadthFirst(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N startNode : startNodes) {
                this.checkThatNodeIsInGraph(startNode);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new BreadthFirstIterator(startNodes);
                }
            };
        }

        @Override
        public Iterable<N> depthFirstPreOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.depthFirstPreOrder((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> depthFirstPreOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N startNode : startNodes) {
                this.checkThatNodeIsInGraph(startNode);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(startNodes, Order.PREORDER);
                }
            };
        }

        @Override
        public Iterable<N> depthFirstPostOrder(N startNode) {
            Preconditions.checkNotNull(startNode);
            return this.depthFirstPostOrder((Iterable<? extends N>)ImmutableSet.of(startNode));
        }

        @Override
        public Iterable<N> depthFirstPostOrder(final Iterable<? extends N> startNodes) {
            Preconditions.checkNotNull(startNodes);
            if (Iterables.isEmpty(startNodes)) {
                return ImmutableSet.of();
            }
            for (N startNode : startNodes) {
                this.checkThatNodeIsInGraph(startNode);
            }
            return new Iterable<N>(){

                @Override
                public Iterator<N> iterator() {
                    return new DepthFirstIterator(startNodes, Order.POSTORDER);
                }
            };
        }

        private void checkThatNodeIsInGraph(N startNode) {
            this.graph.successors(startNode);
        }

        private final class DepthFirstIterator
        extends AbstractIterator<N> {
            private final Deque<com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors> stack = new ArrayDeque<com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors>();
            private final Set<N> visited = new HashSet();
            private final Order order;

            DepthFirstIterator(Iterable<? extends N> roots, Order order) {
                this.stack.push((com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors)new NodeAndSuccessors(null, roots));
                this.order = order;
            }

            @Override
            protected N computeNext() {
                NodeAndSuccessors nodeAndSuccessors;
                boolean produceNode;
                do {
                    if (this.stack.isEmpty()) {
                        return this.endOfData();
                    }
                    nodeAndSuccessors = (NodeAndSuccessors)this.stack.getFirst();
                    boolean firstVisit = this.visited.add(nodeAndSuccessors.node);
                    boolean lastVisit = !nodeAndSuccessors.successorIterator.hasNext();
                    boolean bl = produceNode = firstVisit && this.order == Order.PREORDER || lastVisit && this.order == Order.POSTORDER;
                    if (lastVisit) {
                        this.stack.pop();
                        continue;
                    }
                    Object successor = nodeAndSuccessors.successorIterator.next();
                    if (this.visited.contains(successor)) continue;
                    this.stack.push((com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors)this.withSuccessors(successor));
                } while (!produceNode || nodeAndSuccessors.node == null);
                return nodeAndSuccessors.node;
            }

            com.google.common.graph.Traverser$GraphTraverser$DepthFirstIterator.NodeAndSuccessors withSuccessors(N node) {
                return new NodeAndSuccessors(node, GraphTraverser.this.graph.successors(node));
            }

            private final class NodeAndSuccessors {
                final @Nullable N node;
                final Iterator<? extends N> successorIterator;

                NodeAndSuccessors(N node, Iterable<? extends N> successors) {
                    this.node = node;
                    this.successorIterator = successors.iterator();
                }
            }
        }

        private final class BreadthFirstIterator
        extends UnmodifiableIterator<N> {
            private final Queue<N> queue = new ArrayDeque();
            private final Set<N> visited = new HashSet();

            BreadthFirstIterator(Iterable<? extends N> roots) {
                for (Object root : roots) {
                    if (!this.visited.add(root)) continue;
                    this.queue.add(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !this.queue.isEmpty();
            }

            @Override
            public N next() {
                Object current = this.queue.remove();
                for (Object neighbor : GraphTraverser.this.graph.successors(current)) {
                    if (!this.visited.add(neighbor)) continue;
                    this.queue.add(neighbor);
                }
                return current;
            }
        }
    }
}

