/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.spherical.twod;

import java.util.List;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.apache.commons.math3.geometry.spherical.twod.Circle;
import org.apache.commons.math3.geometry.spherical.twod.S2Point;
import org.apache.commons.math3.geometry.spherical.twod.Vertex;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Edge {
    private final Vertex start;
    private Vertex end;
    private final double length;
    private final Circle circle;

    Edge(Vertex start, Vertex end, double length, Circle circle) {
        this.start = start;
        this.end = end;
        this.length = length;
        this.circle = circle;
        start.setOutgoing(this);
        end.setIncoming(this);
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public double getLength() {
        return this.length;
    }

    public Circle getCircle() {
        return this.circle;
    }

    public Vector3D getPointAt(double alpha) {
        return this.circle.getPointAt(alpha + this.circle.getPhase(this.start.getLocation().getVector()));
    }

    void setNextEdge(Edge next) {
        this.end = next.getStart();
        this.end.setIncoming(this);
        this.end.bindWith(this.getCircle());
    }

    void split(Circle splitCircle, List<Edge> outsideList, List<Edge> insideList) {
        double edgeStart = this.circle.getPhase(this.start.getLocation().getVector());
        Arc arc = this.circle.getInsideArc(splitCircle);
        double arcRelativeStart = MathUtils.normalizeAngle(arc.getInf(), edgeStart + Math.PI) - edgeStart;
        double arcRelativeEnd = arcRelativeStart + arc.getSize();
        double unwrappedEnd = arcRelativeEnd - Math.PI * 2;
        double tolerance = this.circle.getTolerance();
        Vertex previousVertex = this.start;
        if (unwrappedEnd >= this.length - tolerance) {
            insideList.add(this);
        } else {
            double alreadyManagedLength = 0.0;
            if (unwrappedEnd >= 0.0) {
                previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + unwrappedEnd))), unwrappedEnd, insideList, splitCircle);
                alreadyManagedLength = unwrappedEnd;
            }
            if (arcRelativeStart >= this.length - tolerance) {
                if (unwrappedEnd >= 0.0) {
                    previousVertex = this.addSubEdge(previousVertex, this.end, this.length - alreadyManagedLength, outsideList, splitCircle);
                } else {
                    outsideList.add(this);
                }
            } else {
                previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - alreadyManagedLength, outsideList, splitCircle);
                alreadyManagedLength = arcRelativeStart;
                if (arcRelativeEnd >= this.length - tolerance) {
                    previousVertex = this.addSubEdge(previousVertex, this.end, this.length - alreadyManagedLength, insideList, splitCircle);
                } else {
                    previousVertex = this.addSubEdge(previousVertex, new Vertex(new S2Point(this.circle.getPointAt(edgeStart + arcRelativeStart))), arcRelativeStart - alreadyManagedLength, insideList, splitCircle);
                    alreadyManagedLength = arcRelativeStart;
                    previousVertex = this.addSubEdge(previousVertex, this.end, this.length - alreadyManagedLength, outsideList, splitCircle);
                }
            }
        }
    }

    private Vertex addSubEdge(Vertex subStart, Vertex subEnd, double subLength, List<Edge> list, Circle splitCircle) {
        if (subLength <= this.circle.getTolerance()) {
            return subStart;
        }
        subEnd.bindWith(splitCircle);
        Edge edge = new Edge(subStart, subEnd, subLength, this.circle);
        list.add(edge);
        return subEnd;
    }
}

