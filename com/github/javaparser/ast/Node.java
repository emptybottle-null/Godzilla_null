/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.ast;

import com.github.javaparser.HasParentNode;
import com.github.javaparser.Range;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.nodeTypes.NodeWithOptionalScope;
import com.github.javaparser.ast.nodeTypes.NodeWithRange;
import com.github.javaparser.ast.nodeTypes.NodeWithTokenRange;
import com.github.javaparser.ast.observer.AstObserver;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.observer.PropagatingAstObserver;
import com.github.javaparser.ast.visitor.CloneVisitor;
import com.github.javaparser.ast.visitor.EqualsVisitor;
import com.github.javaparser.ast.visitor.HashCodeVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.metamodel.InternalProperty;
import com.github.javaparser.metamodel.JavaParserMetaModel;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.metamodel.OptionalProperty;
import com.github.javaparser.metamodel.PropertyMetaModel;
import com.github.javaparser.printer.DefaultPrettyPrinter;
import com.github.javaparser.printer.Printer;
import com.github.javaparser.printer.configuration.DefaultConfigurationOption;
import com.github.javaparser.printer.configuration.DefaultPrinterConfiguration;
import com.github.javaparser.printer.configuration.PrinterConfiguration;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.utils.LineSeparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Spliterators;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class Node
implements Cloneable,
HasParentNode<Node>,
Visitable,
NodeWithRange<Node>,
NodeWithTokenRange<Node> {
    public static Comparator<NodeWithRange<?>> NODE_BY_BEGIN_POSITION = (a, b) -> {
        if (a.hasRange() && b.hasRange()) {
            return a.getRange().get().begin.compareTo(b.getRange().get().begin);
        }
        if (a.hasRange() || b.hasRange()) {
            if (a.hasRange()) {
                return 1;
            }
            return -1;
        }
        return 0;
    };
    private static final int LEVELS_TO_EXPLORE = 3;
    protected static final PrinterConfiguration prettyPrinterNoCommentsConfiguration = new DefaultPrinterConfiguration().removeOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.PRINT_COMMENTS));
    @InternalProperty
    private Range range;
    @InternalProperty
    private TokenRange tokenRange;
    @InternalProperty
    private Node parentNode;
    @InternalProperty
    private List<Node> childNodes = new LinkedList<Node>();
    @InternalProperty
    private List<Comment> orphanComments = new LinkedList<Comment>();
    @InternalProperty
    private IdentityHashMap<DataKey<?>, Object> data = null;
    @OptionalProperty
    private Comment comment;
    @InternalProperty
    private Set<AstObserver> observers = new HashSet<AstObserver>();
    @InternalProperty
    private Parsedness parsed = Parsedness.PARSED;
    @Deprecated
    public static final int ABSOLUTE_BEGIN_LINE = -1;
    @Deprecated
    public static final int ABSOLUTE_END_LINE = -2;
    public static final DataKey<SymbolResolver> SYMBOL_RESOLVER_KEY = new DataKey<SymbolResolver>(){};
    public static final DataKey<LineSeparator> LINE_SEPARATOR_KEY = new DataKey<LineSeparator>(){};
    protected static final DataKey<Printer> PRINTER_KEY = new DataKey<Printer>(){};
    protected static final DataKey<Boolean> PHANTOM_KEY = new DataKey<Boolean>(){};

    protected Node(TokenRange tokenRange) {
        this.setTokenRange(tokenRange);
    }

    protected void customInitialization() {
    }

    protected Printer getPrinter() {
        return this.findCompilationUnit().map(c -> c.getPrinter()).orElse(this.createDefaultPrinter());
    }

    protected Printer getPrinter(PrinterConfiguration configuration) {
        return this.findCompilationUnit().map(c -> c.getPrinter(configuration)).orElse(this.createDefaultPrinter(configuration));
    }

    protected Printer createDefaultPrinter() {
        return this.createDefaultPrinter(this.getDefaultPrinterConfiguration());
    }

    protected Printer createDefaultPrinter(PrinterConfiguration configuration) {
        return new DefaultPrettyPrinter(configuration);
    }

    protected PrinterConfiguration getDefaultPrinterConfiguration() {
        return new DefaultPrinterConfiguration();
    }

    public Optional<Comment> getComment() {
        return Optional.ofNullable(this.comment);
    }

    @Override
    public Optional<Range> getRange() {
        return Optional.ofNullable(this.range);
    }

    @Override
    public Optional<TokenRange> getTokenRange() {
        return Optional.ofNullable(this.tokenRange);
    }

    @Override
    public Node setTokenRange(TokenRange tokenRange) {
        this.tokenRange = tokenRange;
        this.range = tokenRange == null || !tokenRange.getBegin().hasRange() || !tokenRange.getEnd().hasRange() ? null : new Range(tokenRange.getBegin().getRange().get().begin, tokenRange.getEnd().getRange().get().end);
        return this;
    }

    @Override
    public Node setRange(Range range) {
        if (this.range == range) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.RANGE, this.range, range);
        this.range = range;
        return this;
    }

    public Node setComment(Comment comment) {
        if (this.comment == comment) {
            return this;
        }
        this.notifyPropertyChange(ObservableProperty.COMMENT, this.comment, comment);
        if (this.comment != null) {
            this.comment.setCommentedNode(null);
        }
        this.comment = comment;
        if (comment != null) {
            this.comment.setCommentedNode(this);
        }
        return this;
    }

    public final Node setLineComment(String comment) {
        return this.setComment(new LineComment(comment));
    }

    public final Node setBlockComment(String comment) {
        return this.setComment(new BlockComment(comment));
    }

    public final String toString() {
        if (this.containsData(LINE_SEPARATOR_KEY)) {
            Printer printer = this.getPrinter();
            LineSeparator lineSeparator = this.getLineEndingStyleOrDefault(LineSeparator.SYSTEM);
            PrinterConfiguration config = printer.getConfiguration();
            config.addOption(new DefaultConfigurationOption(DefaultPrinterConfiguration.ConfigOption.END_OF_LINE_CHARACTER, lineSeparator.asRawString()));
            printer.setConfiguration(config);
            return printer.print(this);
        }
        return this.getPrinter().print(this);
    }

    public final String toString(PrinterConfiguration configuration) {
        return this.getPrinter(configuration).print(this);
    }

    public final int hashCode() {
        return HashCodeVisitor.hashCode(this);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Node)) {
            return false;
        }
        return EqualsVisitor.equals(this, (Node)obj);
    }

    @Override
    public Optional<Node> getParentNode() {
        return Optional.ofNullable(this.parentNode);
    }

    public List<Node> getChildNodes() {
        return Collections.unmodifiableList(this.childNodes);
    }

    public void addOrphanComment(Comment comment) {
        this.orphanComments.add(comment);
        comment.setParentNode(this);
    }

    public boolean removeOrphanComment(Comment comment) {
        boolean removed = this.orphanComments.remove(comment);
        if (removed) {
            this.notifyPropertyChange(ObservableProperty.COMMENT, comment, null);
            comment.setParentNode(null);
        }
        return removed;
    }

    public List<Comment> getOrphanComments() {
        return new LinkedList<Comment>(this.orphanComments);
    }

    public List<Comment> getAllContainedComments() {
        LinkedList<Comment> comments = new LinkedList<Comment>();
        comments.addAll(this.getOrphanComments());
        for (Node child : this.getChildNodes()) {
            child.getComment().ifPresent(comments::add);
            comments.addAll(child.getAllContainedComments());
        }
        return comments;
    }

    @Override
    public Node setParentNode(Node newParentNode) {
        if (newParentNode == this.parentNode) {
            return this;
        }
        this.observers.forEach(o -> o.parentChange(this, this.parentNode, newParentNode));
        if (this.parentNode != null) {
            List<Node> parentChildNodes = this.parentNode.childNodes;
            for (int i = 0; i < parentChildNodes.size(); ++i) {
                if (parentChildNodes.get(i) != this) continue;
                parentChildNodes.remove(i);
            }
        }
        this.parentNode = newParentNode;
        if (this.parentNode != null) {
            this.parentNode.childNodes.add(this);
        }
        return this;
    }

    protected void setAsParentNodeOf(Node childNode) {
        if (childNode != null) {
            childNode.setParentNode(this.getParentNodeForChildren());
        }
    }

    public void tryAddImportToParentCompilationUnit(Class<?> clazz) {
        this.findAncestor(CompilationUnit.class).ifPresent(p -> p.addImport(clazz));
    }

    @Deprecated
    public <N extends Node> List<N> getChildNodesByType(Class<N> clazz) {
        ArrayList<N> nodes = new ArrayList<N>();
        for (Node child : this.getChildNodes()) {
            if (clazz.isInstance(child)) {
                nodes.add(clazz.cast(child));
            }
            nodes.addAll(child.getChildNodesByType(clazz));
        }
        return nodes;
    }

    @Deprecated
    public <N extends Node> List<N> getNodesByType(Class<N> clazz) {
        return this.getChildNodesByType(clazz);
    }

    public <M> M getData(DataKey<M> key) {
        if (this.data == null) {
            throw new IllegalStateException("No data of this type found. Use containsData to check for this first.");
        }
        Object value = this.data.get(key);
        if (value == null) {
            throw new IllegalStateException("No data of this type found. Use containsData to check for this first.");
        }
        return (M)value;
    }

    public Set<DataKey<?>> getDataKeys() {
        if (this.data == null) {
            return Collections.emptySet();
        }
        return this.data.keySet();
    }

    public <M> void setData(DataKey<M> key, M object) {
        if (this.data == null) {
            this.data = new IdentityHashMap();
        }
        this.data.put(key, object);
    }

    public boolean containsData(DataKey<?> key) {
        if (this.data == null) {
            return false;
        }
        return this.data.containsKey(key);
    }

    public void removeData(DataKey<?> key) {
        if (this.data != null) {
            this.data.remove(key);
        }
    }

    public boolean remove() {
        if (this.parentNode == null) {
            return false;
        }
        return this.parentNode.remove(this);
    }

    public boolean replace(Node node) {
        if (this.parentNode == null) {
            return false;
        }
        return this.parentNode.replace(this, node);
    }

    public void removeForced() {
        if (!this.remove()) {
            this.getParentNode().ifPresent(Node::remove);
        }
    }

    @Override
    public Node getParentNodeForChildren() {
        return this;
    }

    protected void setAsParentNodeOf(NodeList<? extends Node> list) {
        if (list != null) {
            list.setParentNode(this.getParentNodeForChildren());
        }
    }

    public <P> void notifyPropertyChange(ObservableProperty property, P oldValue, P newValue) {
        this.observers.forEach(o -> o.propertyChange(this, property, oldValue, newValue));
    }

    @Override
    public void unregister(AstObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void register(AstObserver observer) {
        this.observers.add(observer);
    }

    public void register(AstObserver observer, ObserverRegistrationMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode should be not null");
        }
        switch (mode) {
            case JUST_THIS_NODE: {
                this.register(observer);
                break;
            }
            case THIS_NODE_AND_EXISTING_DESCENDANTS: {
                this.registerForSubtree(observer);
                break;
            }
            case SELF_PROPAGATING: {
                this.registerForSubtree(PropagatingAstObserver.transformInPropagatingObserver(observer));
                break;
            }
            default: {
                throw new UnsupportedOperationException("This mode is not supported: " + (Object)((Object)mode));
            }
        }
    }

    public void registerForSubtree(AstObserver observer) {
        this.register(observer);
        this.getChildNodes().forEach(c -> c.registerForSubtree(observer));
        for (PropertyMetaModel property : this.getMetaModel().getAllPropertyMetaModels()) {
            NodeList nodeList;
            if (!property.isNodeList() || (nodeList = (NodeList)property.getValue(this)) == null) continue;
            nodeList.register(observer);
        }
    }

    @Override
    public boolean isRegistered(AstObserver observer) {
        return this.observers.contains(observer);
    }

    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (this.comment != null && node == this.comment) {
            this.removeComment();
            return true;
        }
        return false;
    }

    public Node removeComment() {
        return this.setComment(null);
    }

    public Node clone() {
        return (Node)this.accept(new CloneVisitor(), null);
    }

    public NodeMetaModel getMetaModel() {
        return JavaParserMetaModel.nodeMetaModel;
    }

    public Parsedness getParsed() {
        return this.parsed;
    }

    public Node setParsed(Parsedness parsed) {
        this.parsed = parsed;
        return this;
    }

    public boolean replace(Node node, Node replacementNode) {
        if (node == null) {
            return false;
        }
        if (this.comment != null && node == this.comment) {
            this.setComment((Comment)replacementNode);
            return true;
        }
        return false;
    }

    public Node findRootNode() {
        Node n = this;
        while (n.getParentNode().isPresent()) {
            n = n.getParentNode().get();
        }
        return n;
    }

    public Optional<CompilationUnit> findCompilationUnit() {
        Node rootNode = this.findRootNode();
        if (rootNode instanceof CompilationUnit) {
            return Optional.of((CompilationUnit)rootNode);
        }
        return Optional.empty();
    }

    public LineSeparator getLineEndingStyleOrDefault(LineSeparator defaultLineSeparator) {
        if (this.getLineEndingStyle().isStandardEol()) {
            return this.getLineEndingStyle();
        }
        return defaultLineSeparator;
    }

    public LineSeparator getLineEndingStyle() {
        Node current = this;
        if (current.containsData(LINE_SEPARATOR_KEY)) {
            LineSeparator lineSeparator = current.getData(LINE_SEPARATOR_KEY);
            return lineSeparator;
        }
        while (current.getParentNode().isPresent()) {
            if (!(current = current.getParentNode().get()).containsData(LINE_SEPARATOR_KEY)) continue;
            return current.getData(LINE_SEPARATOR_KEY);
        }
        return LineSeparator.SYSTEM;
    }

    protected SymbolResolver getSymbolResolver() {
        return this.findCompilationUnit().map(cu -> {
            if (cu.containsData(SYMBOL_RESOLVER_KEY)) {
                return cu.getData(SYMBOL_RESOLVER_KEY);
            }
            throw new IllegalStateException("Symbol resolution not configured: to configure consider setting a SymbolResolver in the ParserConfiguration");
        }).orElseThrow(() -> new IllegalStateException("The node is not inserted in a CompilationUnit"));
    }

    private Iterator<Node> treeIterator(TreeTraversal traversal) {
        switch (traversal) {
            case BREADTHFIRST: {
                return new BreadthFirstIterator(this);
            }
            case POSTORDER: {
                return new PostOrderIterator(this);
            }
            case PREORDER: {
                return new PreOrderIterator(this);
            }
            case DIRECT_CHILDREN: {
                return new DirectChildrenIterator(this);
            }
            case PARENTS: {
                return new ParentsVisitor(this);
            }
        }
        throw new IllegalArgumentException("Unknown traversal choice.");
    }

    private Iterable<Node> treeIterable(TreeTraversal traversal) {
        return () -> this.treeIterator(traversal);
    }

    public Stream<Node> stream(TreeTraversal traversal) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.treeIterator(traversal), 257), false);
    }

    public Stream<Node> stream() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.treeIterator(TreeTraversal.PREORDER), 257), false);
    }

    public void walk(TreeTraversal traversal, Consumer<Node> consumer) {
        for (Node node : this.treeIterable(traversal)) {
            consumer.accept(node);
        }
    }

    public void walk(Consumer<Node> consumer) {
        this.walk(TreeTraversal.PREORDER, consumer);
    }

    public <T extends Node> void walk(Class<T> nodeType, Consumer<T> consumer) {
        this.walk(TreeTraversal.PREORDER, (Node node) -> {
            if (nodeType.isAssignableFrom(node.getClass())) {
                consumer.accept(nodeType.cast(node));
            }
        });
    }

    public <T extends Node> List<T> findAll(Class<T> nodeType) {
        ArrayList found = new ArrayList();
        this.walk(nodeType, found::add);
        return found;
    }

    public <T extends Node> List<T> findAll(Class<T> nodeType, Predicate<T> predicate) {
        ArrayList found = new ArrayList();
        this.walk(nodeType, (T n) -> {
            if (predicate.test(n)) {
                found.add(n);
            }
        });
        return found;
    }

    public <T> Optional<T> findFirst(TreeTraversal traversal, Function<Node, Optional<T>> consumer) {
        for (Node node : this.treeIterable(traversal)) {
            Optional<T> result = consumer.apply(node);
            if (!result.isPresent()) continue;
            return result;
        }
        return Optional.empty();
    }

    public <N extends Node> Optional<N> findFirst(Class<N> nodeType) {
        return this.findFirst(TreeTraversal.PREORDER, (Node node) -> {
            if (nodeType.isAssignableFrom(node.getClass())) {
                return Optional.of(nodeType.cast(node));
            }
            return Optional.empty();
        });
    }

    public <N extends Node> Optional<N> findFirst(Class<N> nodeType, Predicate<N> predicate) {
        return this.findFirst(TreeTraversal.PREORDER, (Node node) -> {
            Node castNode;
            if (nodeType.isAssignableFrom(node.getClass()) && predicate.test(castNode = (Node)nodeType.cast(node))) {
                return Optional.of(castNode);
            }
            return Optional.empty();
        });
    }

    public boolean isAncestorOf(Node descendant) {
        return this != descendant && this.findFirst(Node.class, (N n) -> n == descendant).isPresent();
    }

    public boolean hasScope() {
        return NodeWithOptionalScope.class.isAssignableFrom(this.getClass()) && ((NodeWithOptionalScope)((Object)this)).getScope().isPresent();
    }

    public boolean isPhantom() {
        return this.isPhantom(this);
    }

    private boolean isPhantom(Node node) {
        if (!node.containsData(PHANTOM_KEY)) {
            boolean res = node.getParentNode().isPresent() && node.getParentNode().get().hasRange() && node.hasRange() && !node.getParentNode().get().getRange().get().contains(node.getRange().get()) || this.inPhantomNode(node, 3);
            node.setData(PHANTOM_KEY, res);
        }
        return node.getData(PHANTOM_KEY);
    }

    private boolean inPhantomNode(Node node, int levels) {
        return node.getParentNode().isPresent() && (this.isPhantom(node.getParentNode().get()) || this.inPhantomNode(node.getParentNode().get(), levels - 1));
    }

    public static class PostOrderIterator
    implements Iterator<Node> {
        private final Stack<List<Node>> nodesStack = new Stack();
        private final Stack<Integer> cursorStack = new Stack();
        private final Node root;
        private boolean hasNext = true;

        public PostOrderIterator(Node root) {
            this.root = root;
            this.fillStackToLeaf(root);
        }

        private void fillStackToLeaf(Node node) {
            ArrayList<Node> childNodes;
            while (!(childNodes = new ArrayList<Node>(node.getChildNodes())).isEmpty()) {
                this.nodesStack.push(childNodes);
                this.cursorStack.push(0);
                node = (Node)childNodes.get(0);
            }
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public Node next() {
            boolean levelHasNext;
            List<Node> nodes = this.nodesStack.peek();
            int cursor = this.cursorStack.peek();
            boolean bl = levelHasNext = cursor < nodes.size();
            if (levelHasNext) {
                Node node = nodes.get(cursor);
                this.fillStackToLeaf(node);
                return this.nextFromLevel();
            }
            this.nodesStack.pop();
            this.cursorStack.pop();
            boolean bl2 = this.hasNext = !this.nodesStack.empty();
            if (this.hasNext) {
                return this.nextFromLevel();
            }
            return this.root;
        }

        private Node nextFromLevel() {
            List<Node> nodes = this.nodesStack.peek();
            int cursor = this.cursorStack.pop();
            this.cursorStack.push(cursor + 1);
            return nodes.get(cursor);
        }
    }

    public static class PreOrderIterator
    implements Iterator<Node> {
        private final Stack<Node> stack = new Stack();

        public PreOrderIterator(Node node) {
            this.stack.add(node);
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public Node next() {
            Node next = this.stack.pop();
            List<Node> children = next.getChildNodes();
            for (int i = children.size() - 1; i >= 0; --i) {
                this.stack.add(children.get(i));
            }
            return next;
        }
    }

    public static class ParentsVisitor
    implements Iterator<Node> {
        private Node node;

        public ParentsVisitor(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return this.node.getParentNode().isPresent();
        }

        @Override
        public Node next() {
            this.node = this.node.getParentNode().orElse(null);
            return this.node;
        }
    }

    public static class DirectChildrenIterator
    implements Iterator<Node> {
        private final Iterator<Node> childrenIterator;

        public DirectChildrenIterator(Node node) {
            this.childrenIterator = new ArrayList<Node>(node.getChildNodes()).iterator();
        }

        @Override
        public boolean hasNext() {
            return this.childrenIterator.hasNext();
        }

        @Override
        public Node next() {
            return this.childrenIterator.next();
        }
    }

    public static class BreadthFirstIterator
    implements Iterator<Node> {
        private final Queue<Node> queue = new LinkedList<Node>();

        public BreadthFirstIterator(Node node) {
            this.queue.add(node);
        }

        @Override
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override
        public Node next() {
            Node next = this.queue.remove();
            this.queue.addAll(next.getChildNodes());
            return next;
        }
    }

    public static enum TreeTraversal {
        PREORDER,
        BREADTHFIRST,
        POSTORDER,
        PARENTS,
        DIRECT_CHILDREN;

    }

    public static enum Parsedness {
        PARSED,
        UNPARSABLE;

    }

    public static enum ObserverRegistrationMode {
        JUST_THIS_NODE,
        THIS_NODE_AND_EXISTING_DESCENDANTS,
        SELF_PROPAGATING;

    }
}

