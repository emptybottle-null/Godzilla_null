/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Segment;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.NodesSet;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PolygonsSet
extends AbstractRegion<Euclidean2D, Euclidean1D> {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;
    private Vector2D[][] vertices;

    public PolygonsSet(double tolerance) {
        super(tolerance);
    }

    public PolygonsSet(BSPTree<Euclidean2D> tree, double tolerance) {
        super(tree, tolerance);
    }

    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> boundary, double tolerance) {
        super(boundary, tolerance);
    }

    public PolygonsSet(double xMin, double xMax, double yMin, double yMax, double tolerance) {
        super(PolygonsSet.boxBoundary(xMin, xMax, yMin, yMax, tolerance), tolerance);
    }

    public PolygonsSet(double hyperplaneThickness, Vector2D ... vertices) {
        super(PolygonsSet.verticesToTree(hyperplaneThickness, vertices), hyperplaneThickness);
    }

    @Deprecated
    public PolygonsSet() {
        this(1.0E-10);
    }

    @Deprecated
    public PolygonsSet(BSPTree<Euclidean2D> tree) {
        this(tree, 1.0E-10);
    }

    @Deprecated
    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> boundary) {
        this(boundary, 1.0E-10);
    }

    @Deprecated
    public PolygonsSet(double xMin, double xMax, double yMin, double yMax) {
        this(xMin, xMax, yMin, yMax, 1.0E-10);
    }

    private static Line[] boxBoundary(double xMin, double xMax, double yMin, double yMax, double tolerance) {
        if (xMin >= xMax - tolerance || yMin >= yMax - tolerance) {
            return null;
        }
        Vector2D minMin = new Vector2D(xMin, yMin);
        Vector2D minMax = new Vector2D(xMin, yMax);
        Vector2D maxMin = new Vector2D(xMax, yMin);
        Vector2D maxMax = new Vector2D(xMax, yMax);
        return new Line[]{new Line(minMin, maxMin, tolerance), new Line(maxMin, maxMax, tolerance), new Line(maxMax, minMax, tolerance), new Line(minMax, minMin, tolerance)};
    }

    private static BSPTree<Euclidean2D> verticesToTree(double hyperplaneThickness, Vector2D ... vertices) {
        int n = vertices.length;
        if (n == 0) {
            return new BSPTree<Euclidean2D>(Boolean.TRUE);
        }
        Vertex[] vArray = new Vertex[n];
        for (int i = 0; i < n; ++i) {
            vArray[i] = new Vertex(vertices[i]);
        }
        ArrayList<Edge> edges = new ArrayList<Edge>(n);
        for (int i = 0; i < n; ++i) {
            Vertex start = vArray[i];
            Vertex end = vArray[(i + 1) % n];
            Line line = start.sharedLineWith(end);
            if (line == null) {
                line = new Line(start.getLocation(), end.getLocation(), hyperplaneThickness);
            }
            edges.add(new Edge(start, end, line));
            for (Vertex vertex : vArray) {
                if (vertex == start || vertex == end || !(FastMath.abs(line.getOffset(vertex.getLocation())) <= hyperplaneThickness)) continue;
                vertex.bindWith(line);
            }
        }
        BSPTree<Euclidean2D> tree = new BSPTree<Euclidean2D>();
        PolygonsSet.insertEdges(hyperplaneThickness, tree, edges);
        return tree;
    }

    private static void insertEdges(double hyperplaneThickness, BSPTree<Euclidean2D> node, List<Edge> edges) {
        int index = 0;
        Edge inserted = null;
        while (inserted == null && index < edges.size()) {
            if ((inserted = edges.get(index++)).getNode() == null) {
                if (node.insertCut(inserted.getLine())) {
                    inserted.setNode(node);
                    continue;
                }
                inserted = null;
                continue;
            }
            inserted = null;
        }
        if (inserted == null) {
            BSPTree<Euclidean2D> parent = node.getParent();
            if (parent == null || node == parent.getMinus()) {
                node.setAttribute(Boolean.TRUE);
            } else {
                node.setAttribute(Boolean.FALSE);
            }
            return;
        }
        ArrayList<Edge> plusList = new ArrayList<Edge>();
        ArrayList<Edge> minusList = new ArrayList<Edge>();
        block5: for (Edge edge : edges) {
            Side startSide;
            if (edge == inserted) continue;
            double startOffset = inserted.getLine().getOffset(edge.getStart().getLocation());
            double endOffset = inserted.getLine().getOffset(edge.getEnd().getLocation());
            Side side = FastMath.abs(startOffset) <= hyperplaneThickness ? Side.HYPER : (startSide = startOffset < 0.0 ? Side.MINUS : Side.PLUS);
            Side endSide = FastMath.abs(endOffset) <= hyperplaneThickness ? Side.HYPER : (endOffset < 0.0 ? Side.MINUS : Side.PLUS);
            switch (startSide) {
                case PLUS: {
                    Vertex splitPoint;
                    if (endSide == Side.MINUS) {
                        splitPoint = edge.split(inserted.getLine());
                        minusList.add(splitPoint.getOutgoing());
                        plusList.add(splitPoint.getIncoming());
                        continue block5;
                    }
                    plusList.add(edge);
                    continue block5;
                }
                case MINUS: {
                    Vertex splitPoint;
                    if (endSide == Side.PLUS) {
                        splitPoint = edge.split(inserted.getLine());
                        minusList.add(splitPoint.getIncoming());
                        plusList.add(splitPoint.getOutgoing());
                        continue block5;
                    }
                    minusList.add(edge);
                    continue block5;
                }
            }
            if (endSide == Side.PLUS) {
                plusList.add(edge);
                continue;
            }
            if (endSide != Side.MINUS) continue;
            minusList.add(edge);
        }
        if (!plusList.isEmpty()) {
            PolygonsSet.insertEdges(hyperplaneThickness, node.getPlus(), plusList);
        } else {
            node.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!minusList.isEmpty()) {
            PolygonsSet.insertEdges(hyperplaneThickness, node.getMinus(), minusList);
        } else {
            node.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    public PolygonsSet buildNew(BSPTree<Euclidean2D> tree) {
        return new PolygonsSet(tree, this.getTolerance());
    }

    @Override
    protected void computeGeometricalProperties() {
        Vector2D[][] v = this.getVertices();
        if (v.length == 0) {
            BSPTree tree = this.getTree(false);
            if (tree.getCut() == null && ((Boolean)tree.getAttribute()).booleanValue()) {
                this.setSize(Double.POSITIVE_INFINITY);
                this.setBarycenter(Vector2D.NaN);
            } else {
                this.setSize(0.0);
                this.setBarycenter(new Vector2D(0.0, 0.0));
            }
        } else if (v[0][0] == null) {
            this.setSize(Double.POSITIVE_INFINITY);
            this.setBarycenter(Vector2D.NaN);
        } else {
            double sum = 0.0;
            double sumX = 0.0;
            double sumY = 0.0;
            for (Vector2D[] loop : v) {
                double x1 = loop[loop.length - 1].getX();
                double y1 = loop[loop.length - 1].getY();
                for (Vector2D point : loop) {
                    double x0 = x1;
                    double y0 = y1;
                    x1 = point.getX();
                    y1 = point.getY();
                    double factor = x0 * y1 - y0 * x1;
                    sum += factor;
                    sumX += factor * (x0 + x1);
                    sumY += factor * (y0 + y1);
                }
            }
            if (sum < 0.0) {
                this.setSize(Double.POSITIVE_INFINITY);
                this.setBarycenter(Vector2D.NaN);
            } else {
                this.setSize(sum / 2.0);
                this.setBarycenter(new Vector2D(sumX / (3.0 * sum), sumY / (3.0 * sum)));
            }
        }
    }

    public Vector2D[][] getVertices() {
        if (this.vertices == null) {
            if (this.getTree(false).getCut() == null) {
                this.vertices = new Vector2D[0][];
            } else {
                SegmentsBuilder visitor = new SegmentsBuilder(this.getTolerance());
                this.getTree(true).visit(visitor);
                List<ConnectableSegment> segments = visitor.getSegments();
                int pending = segments.size();
                if ((pending -= this.naturalFollowerConnections(segments)) > 0) {
                    pending -= this.splitEdgeConnections(segments);
                }
                if (pending > 0) {
                    pending -= this.closeVerticesConnections(segments);
                }
                ArrayList<List<Segment>> loops = new ArrayList<List<Segment>>();
                ConnectableSegment s = this.getUnprocessed(segments);
                while (s != null) {
                    List<Segment> loop = this.followLoop(s);
                    if (loop != null) {
                        if (loop.get(0).getStart() == null) {
                            loops.add(0, loop);
                        } else {
                            loops.add(loop);
                        }
                    }
                    s = this.getUnprocessed(segments);
                }
                this.vertices = new Vector2D[loops.size()][];
                int i = 0;
                for (List list : loops) {
                    int j;
                    Vector2D[] array;
                    if (list.size() < 2 || list.size() == 2 && ((Segment)list.get(0)).getStart() == null && ((Segment)list.get(1)).getEnd() == null) {
                        Line line = ((Segment)list.get(0)).getLine();
                        this.vertices[i++] = new Vector2D[]{null, line.toSpace((Point)new Vector1D(-3.4028234663852886E38)), line.toSpace((Point)new Vector1D(3.4028234663852886E38))};
                        continue;
                    }
                    if (((Segment)list.get(0)).getStart() == null) {
                        array = new Vector2D[list.size() + 2];
                        j = 0;
                        for (Segment segment : list) {
                            double x;
                            if (j == 0) {
                                x = ((Vector1D)segment.getLine().toSubSpace((Point)segment.getEnd())).getX();
                                x -= FastMath.max(1.0, FastMath.abs(x / 2.0));
                                array[j++] = null;
                                array[j++] = segment.getLine().toSpace((Point)new Vector1D(x));
                            }
                            if (j < array.length - 1) {
                                array[j++] = segment.getEnd();
                            }
                            if (j != array.length - 1) continue;
                            x = ((Vector1D)segment.getLine().toSubSpace((Point)segment.getStart())).getX();
                            x += FastMath.max(1.0, FastMath.abs(x / 2.0));
                            array[j++] = segment.getLine().toSpace((Point)new Vector1D(x));
                        }
                        this.vertices[i++] = array;
                        continue;
                    }
                    array = new Vector2D[list.size()];
                    j = 0;
                    for (Segment segment : list) {
                        array[j++] = segment.getStart();
                    }
                    this.vertices[i++] = array;
                }
            }
        }
        return (Vector2D[][])this.vertices.clone();
    }

    private int naturalFollowerConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        block0: for (ConnectableSegment segment : segments) {
            if (segment.getNext() != null) continue;
            BSPTree<Euclidean2D> node = segment.getNode();
            BSPTree<Euclidean2D> end = segment.getEndNode();
            for (ConnectableSegment candidateNext : segments) {
                if (candidateNext.getPrevious() != null || candidateNext.getNode() != end || candidateNext.getStartNode() != node) continue;
                segment.setNext(candidateNext);
                candidateNext.setPrevious(segment);
                ++connected;
                continue block0;
            }
        }
        return connected;
    }

    private int splitEdgeConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        block0: for (ConnectableSegment segment : segments) {
            if (segment.getNext() != null) continue;
            Hyperplane<Euclidean2D> hyperplane = segment.getNode().getCut().getHyperplane();
            BSPTree<Euclidean2D> end = segment.getEndNode();
            for (ConnectableSegment candidateNext : segments) {
                if (candidateNext.getPrevious() != null || candidateNext.getNode().getCut().getHyperplane() != hyperplane || candidateNext.getStartNode() != end) continue;
                segment.setNext(candidateNext);
                candidateNext.setPrevious(segment);
                ++connected;
                continue block0;
            }
        }
        return connected;
    }

    private int closeVerticesConnections(List<ConnectableSegment> segments) {
        int connected = 0;
        for (ConnectableSegment segment : segments) {
            if (segment.getNext() != null || segment.getEnd() == null) continue;
            Vector2D end = segment.getEnd();
            ConnectableSegment selectedNext = null;
            double min = Double.POSITIVE_INFINITY;
            for (ConnectableSegment candidateNext : segments) {
                double distance;
                if (candidateNext.getPrevious() != null || candidateNext.getStart() == null || !((distance = Vector2D.distance(end, candidateNext.getStart())) < min)) continue;
                selectedNext = candidateNext;
                min = distance;
            }
            if (!(min <= this.getTolerance())) continue;
            segment.setNext(selectedNext);
            selectedNext.setPrevious(segment);
            ++connected;
        }
        return connected;
    }

    private ConnectableSegment getUnprocessed(List<ConnectableSegment> segments) {
        for (ConnectableSegment segment : segments) {
            if (segment.isProcessed()) continue;
            return segment;
        }
        return null;
    }

    private List<Segment> followLoop(ConnectableSegment defining) {
        ConnectableSegment next;
        ArrayList<Segment> loop = new ArrayList<Segment>();
        loop.add(defining);
        defining.setProcessed(true);
        for (next = defining.getNext(); next != defining && next != null; next = next.getNext()) {
            loop.add(next);
            next.setProcessed(true);
        }
        if (next == null) {
            for (ConnectableSegment previous = defining.getPrevious(); previous != null; previous = previous.getPrevious()) {
                loop.add(0, previous);
                previous.setProcessed(true);
            }
        }
        this.filterSpuriousVertices(loop);
        if (loop.size() == 2 && ((Segment)loop.get(0)).getStart() != null) {
            return null;
        }
        return loop;
    }

    private void filterSpuriousVertices(List<Segment> loop) {
        for (int i = 0; i < loop.size(); ++i) {
            Segment previous = loop.get(i);
            int j = (i + 1) % loop.size();
            Segment next = loop.get(j);
            if (next == null || !Precision.equals(previous.getLine().getAngle(), next.getLine().getAngle(), Precision.EPSILON)) continue;
            loop.set(j, new Segment(previous.getStart(), next.getEnd(), previous.getLine()));
            loop.remove(i--);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class SegmentsBuilder
    implements BSPTreeVisitor<Euclidean2D> {
        private final double tolerance;
        private final List<ConnectableSegment> segments;

        SegmentsBuilder(double tolerance) {
            this.tolerance = tolerance;
            this.segments = new ArrayList<ConnectableSegment>();
        }

        @Override
        public BSPTreeVisitor.Order visitOrder(BSPTree<Euclidean2D> node) {
            return BSPTreeVisitor.Order.MINUS_SUB_PLUS;
        }

        @Override
        public void visitInternalNode(BSPTree<Euclidean2D> node) {
            BoundaryAttribute attribute = (BoundaryAttribute)node.getAttribute();
            NodesSet<Euclidean2D> splitters = attribute.getSplitters();
            if (attribute.getPlusOutside() != null) {
                this.addContribution(attribute.getPlusOutside(), node, splitters, false);
            }
            if (attribute.getPlusInside() != null) {
                this.addContribution(attribute.getPlusInside(), node, splitters, true);
            }
        }

        @Override
        public void visitLeafNode(BSPTree<Euclidean2D> node) {
        }

        private void addContribution(SubHyperplane<Euclidean2D> sub, BSPTree<Euclidean2D> node, Iterable<BSPTree<Euclidean2D>> splitters, boolean reversed) {
            AbstractSubHyperplane absSub = (AbstractSubHyperplane)sub;
            Line line = (Line)sub.getHyperplane();
            List<Interval> intervals = ((IntervalsSet)absSub.getRemainingRegion()).asList();
            for (Interval i : intervals) {
                Point startV = Double.isInfinite(i.getInf()) ? null : line.toSpace((Point)new Vector1D(i.getInf()));
                Point endV = Double.isInfinite(i.getSup()) ? null : line.toSpace((Point)new Vector1D(i.getSup()));
                BSPTree<Euclidean2D> startN = this.selectClosest((Vector2D)startV, splitters);
                BSPTree<Euclidean2D> endN = this.selectClosest((Vector2D)endV, splitters);
                if (reversed) {
                    this.segments.add(new ConnectableSegment((Vector2D)endV, (Vector2D)startV, line.getReverse(), node, endN, startN));
                    continue;
                }
                this.segments.add(new ConnectableSegment((Vector2D)startV, (Vector2D)endV, line, node, startN, endN));
            }
        }

        private BSPTree<Euclidean2D> selectClosest(Vector2D point, Iterable<BSPTree<Euclidean2D>> candidates) {
            BSPTree<Euclidean2D> selected = null;
            double min = Double.POSITIVE_INFINITY;
            for (BSPTree<Euclidean2D> node : candidates) {
                double distance = FastMath.abs(node.getCut().getHyperplane().getOffset(point));
                if (!(distance < min)) continue;
                selected = node;
                min = distance;
            }
            return min <= this.tolerance ? selected : null;
        }

        public List<ConnectableSegment> getSegments() {
            return this.segments;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class ConnectableSegment
    extends Segment {
        private final BSPTree<Euclidean2D> node;
        private final BSPTree<Euclidean2D> startNode;
        private final BSPTree<Euclidean2D> endNode;
        private ConnectableSegment previous;
        private ConnectableSegment next;
        private boolean processed;

        ConnectableSegment(Vector2D start, Vector2D end, Line line, BSPTree<Euclidean2D> node, BSPTree<Euclidean2D> startNode, BSPTree<Euclidean2D> endNode) {
            super(start, end, line);
            this.node = node;
            this.startNode = startNode;
            this.endNode = endNode;
            this.previous = null;
            this.next = null;
            this.processed = false;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public BSPTree<Euclidean2D> getStartNode() {
            return this.startNode;
        }

        public BSPTree<Euclidean2D> getEndNode() {
            return this.endNode;
        }

        public ConnectableSegment getPrevious() {
            return this.previous;
        }

        public void setPrevious(ConnectableSegment previous) {
            this.previous = previous;
        }

        public ConnectableSegment getNext() {
            return this.next;
        }

        public void setNext(ConnectableSegment next) {
            this.next = next;
        }

        public void setProcessed(boolean processed) {
            this.processed = processed;
        }

        public boolean isProcessed() {
            return this.processed;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class Edge {
        private final Vertex start;
        private final Vertex end;
        private final Line line;
        private BSPTree<Euclidean2D> node;

        Edge(Vertex start, Vertex end, Line line) {
            this.start = start;
            this.end = end;
            this.line = line;
            this.node = null;
            start.setOutgoing(this);
            end.setIncoming(this);
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }

        public Line getLine() {
            return this.line;
        }

        public void setNode(BSPTree<Euclidean2D> node) {
            this.node = node;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public Vertex split(Line splitLine) {
            Vertex splitVertex = new Vertex(this.line.intersection(splitLine));
            splitVertex.bindWith(splitLine);
            Edge startHalf = new Edge(this.start, splitVertex, this.line);
            Edge endHalf = new Edge(splitVertex, this.end, this.line);
            startHalf.node = this.node;
            endHalf.node = this.node;
            return splitVertex;
        }
    }

    private static class Vertex {
        private final Vector2D location;
        private Edge incoming;
        private Edge outgoing;
        private final List<Line> lines;

        Vertex(Vector2D location) {
            this.location = location;
            this.incoming = null;
            this.outgoing = null;
            this.lines = new ArrayList<Line>();
        }

        public Vector2D getLocation() {
            return this.location;
        }

        public void bindWith(Line line) {
            this.lines.add(line);
        }

        public Line sharedLineWith(Vertex vertex) {
            for (Line line1 : this.lines) {
                for (Line line2 : vertex.lines) {
                    if (line1 != line2) continue;
                    return line1;
                }
            }
            return null;
        }

        public void setIncoming(Edge incoming) {
            this.incoming = incoming;
            this.bindWith(incoming.getLine());
        }

        public Edge getIncoming() {
            return this.incoming;
        }

        public void setOutgoing(Edge outgoing) {
            this.outgoing = outgoing;
            this.bindWith(outgoing.getLine());
        }

        public Edge getOutgoing() {
            return this.outgoing;
        }
    }
}

