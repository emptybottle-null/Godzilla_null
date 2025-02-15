/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.DrawTextShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.util.Internal;

public class DrawTableShape
extends DrawShape {
    @Internal
    public static final int borderSize = 2;

    public DrawTableShape(TableShape<?, ?> shape) {
        super(shape);
    }

    protected Drawable getGroupShape(Graphics2D graphics) {
        if (this.shape instanceof GroupShape) {
            DrawFactory df = DrawFactory.getInstance(graphics);
            return df.getDrawable((GroupShape)this.shape);
        }
        return null;
    }

    @Override
    public void applyTransform(Graphics2D graphics) {
        Drawable d = this.getGroupShape(graphics);
        if (d != null) {
            d.applyTransform(graphics);
        } else {
            super.applyTransform(graphics);
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        Drawable d = this.getGroupShape(graphics);
        if (d != null) {
            d.draw(graphics);
            return;
        }
        Shape ts = this.getShape();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint((PlaceableShape<?, ?>)((Object)ts));
        int rows = ts.getNumberOfRows();
        int cols = ts.getNumberOfColumns();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                TableCell tc = ts.getCell(row, col);
                if (tc == null || tc.isMerged()) continue;
                Paint fillPaint = drawPaint.getPaint(graphics, tc.getFillStyle().getPaint());
                graphics.setPaint(fillPaint);
                Rectangle2D cellAnc = tc.getAnchor();
                DrawPaint.fillPaintWorkaround(graphics, cellAnc);
                for (TableCell.BorderEdge edge : TableCell.BorderEdge.values()) {
                    Line2D.Double line;
                    StrokeStyle stroke = tc.getBorderStyle(edge);
                    if (stroke == null) continue;
                    graphics.setStroke(DrawTableShape.getStroke(stroke));
                    Paint linePaint = drawPaint.getPaint(graphics, stroke.getPaint());
                    graphics.setPaint(linePaint);
                    double x = cellAnc.getX();
                    double y = cellAnc.getY();
                    double w = cellAnc.getWidth();
                    double h = cellAnc.getHeight();
                    switch (edge) {
                        default: {
                            line = new Line2D.Double(x - 2.0, y + h, x + w + 2.0, y + h);
                            break;
                        }
                        case left: {
                            line = new Line2D.Double(x, y, x, y + h + 2.0);
                            break;
                        }
                        case right: {
                            line = new Line2D.Double(x + w, y, x + w, y + h + 2.0);
                            break;
                        }
                        case top: {
                            line = new Line2D.Double(x - 2.0, y, x + w + 2.0, y);
                        }
                    }
                    graphics.draw(line);
                }
            }
        }
        this.drawContent(graphics);
    }

    @Override
    public void drawContent(Graphics2D graphics) {
        Drawable d = this.getGroupShape(graphics);
        if (d != null) {
            d.drawContent(graphics);
            return;
        }
        Shape ts = this.getShape();
        DrawFactory df = DrawFactory.getInstance(graphics);
        int rows = ts.getNumberOfRows();
        int cols = ts.getNumberOfColumns();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                TableCell tc = ts.getCell(row, col);
                if (tc == null) continue;
                DrawTextShape dts = df.getDrawable(tc);
                dts.drawContent(graphics);
            }
        }
    }

    protected TableShape<?, ?> getShape() {
        return (TableShape)this.shape;
    }

    public void setAllBorders(Object ... args) {
        Shape table = this.getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = new TableCell.BorderEdge[]{TableCell.BorderEdge.top, TableCell.BorderEdge.left, null, null};
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                edges[2] = col == cols - 1 ? TableCell.BorderEdge.right : null;
                edges[3] = row == rows - 1 ? TableCell.BorderEdge.bottom : null;
                DrawTableShape.setEdges(table.getCell(row, col), edges, args);
            }
        }
    }

    public void setOutsideBorders(Object ... args) {
        if (args.length == 0) {
            return;
        }
        Shape table = this.getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = new TableCell.BorderEdge[4];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                edges[0] = col == 0 ? TableCell.BorderEdge.left : null;
                edges[1] = col == cols - 1 ? TableCell.BorderEdge.right : null;
                edges[2] = row == 0 ? TableCell.BorderEdge.top : null;
                edges[3] = row == rows - 1 ? TableCell.BorderEdge.bottom : null;
                DrawTableShape.setEdges(table.getCell(row, col), edges, args);
            }
        }
    }

    public void setInsideBorders(Object ... args) {
        if (args.length == 0) {
            return;
        }
        Shape table = this.getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = new TableCell.BorderEdge[2];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                edges[0] = col > 0 && col < cols - 1 ? TableCell.BorderEdge.right : null;
                edges[1] = row > 0 && row < rows - 1 ? TableCell.BorderEdge.bottom : null;
                DrawTableShape.setEdges(table.getCell(row, col), edges, args);
            }
        }
    }

    private static void setEdges(TableCell<?, ?> cell, TableCell.BorderEdge[] edges, Object ... args) {
        if (cell == null) {
            return;
        }
        for (TableCell.BorderEdge be : edges) {
            if (be == null) continue;
            if (args.length == 0) {
                cell.removeBorder(be);
                continue;
            }
            for (Object o : args) {
                if (o instanceof Double) {
                    cell.setBorderWidth(be, (Double)o);
                    continue;
                }
                if (o instanceof Color) {
                    cell.setBorderColor(be, (Color)o);
                    continue;
                }
                if (o instanceof StrokeStyle.LineDash) {
                    cell.setBorderDash(be, (StrokeStyle.LineDash)((Object)o));
                    continue;
                }
                if (!(o instanceof StrokeStyle.LineCompound)) continue;
                cell.setBorderCompound(be, (StrokeStyle.LineCompound)((Object)o));
            }
        }
    }
}

