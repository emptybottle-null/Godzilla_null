/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.model.SelectionUtil;
import com.jediterm.terminal.util.Pair;
import java.awt.Point;
import org.jetbrains.annotations.Nullable;

public class TerminalSelection {
    private final Point myStart;
    private Point myEnd;

    public TerminalSelection(Point start) {
        this.myStart = start;
    }

    public TerminalSelection(Point start, Point end) {
        this.myStart = start;
        this.myEnd = end;
    }

    public Point getStart() {
        return this.myStart;
    }

    public Point getEnd() {
        return this.myEnd;
    }

    public void updateEnd(Point end) {
        this.myEnd = end;
    }

    public Pair<Point, Point> pointsForRun(int width) {
        Pair<Point, Point> p = SelectionUtil.sortPoints(new Point(this.myStart), new Point(this.myEnd));
        ((Point)p.second).x = Math.min(((Point)p.second).x + 1, width);
        return p;
    }

    public boolean contains(Point toTest) {
        return this.intersects(toTest.x, toTest.y, 1);
    }

    public void shiftY(int dy) {
        this.myStart.y += dy;
        this.myEnd.y += dy;
    }

    public boolean intersects(int x, int row, int length) {
        return null != this.intersect(x, row, length);
    }

    @Nullable
    public Pair<Integer, Integer> intersect(int x, int row, int length) {
        int newLength;
        int newX = x;
        Pair<Point, Point> p = SelectionUtil.sortPoints(new Point(this.myStart), new Point(this.myEnd));
        if (((Point)p.first).y == row) {
            newX = Math.max(x, ((Point)p.first).x);
        }
        if ((newLength = ((Point)p.second).y == row ? Math.min(((Point)p.second).x, x + length - 1) - newX + 1 : length - newX + x) <= 0 || row < ((Point)p.first).y || row > ((Point)p.second).y) {
            return null;
        }
        return Pair.create(newX, newLength);
    }

    public String toString() {
        return "[x=" + this.myStart.x + ",y=" + this.myStart.y + "] -> [x=" + this.myEnd.x + ",y=" + this.myEnd.y + "]";
    }
}

