/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.sl.draw.binding.CTCustomGeometry2D;
import org.apache.poi.sl.draw.binding.CTGeomGuide;
import org.apache.poi.sl.draw.binding.CTGeomGuideList;
import org.apache.poi.sl.draw.binding.CTGeomRect;
import org.apache.poi.sl.draw.binding.CTPath2D;
import org.apache.poi.sl.draw.binding.CTPath2DList;
import org.apache.poi.sl.draw.geom.AdjustValue;
import org.apache.poi.sl.draw.geom.ClosePathCommand;
import org.apache.poi.sl.draw.geom.Guide;
import org.apache.poi.sl.draw.geom.LineToCommand;
import org.apache.poi.sl.draw.geom.MoveToCommand;
import org.apache.poi.sl.draw.geom.Path;

public class CustomGeometry
implements Iterable<Path> {
    final List<Guide> adjusts = new ArrayList<Guide>();
    final List<Guide> guides = new ArrayList<Guide>();
    final List<Path> paths = new ArrayList<Path>();
    Path textBounds;

    public CustomGeometry(CTCustomGeometry2D geom) {
        CTGeomRect cTGeomRect;
        CTPath2DList cTPath2DList;
        CTGeomGuideList gdLst;
        CTGeomGuideList avLst = geom.getAvLst();
        if (avLst != null) {
            for (CTGeomGuide cTGeomGuide : avLst.getGd()) {
                this.adjusts.add(new AdjustValue(cTGeomGuide));
            }
        }
        if ((gdLst = geom.getGdLst()) != null) {
            for (CTGeomGuide cTGeomGuide : gdLst.getGd()) {
                this.guides.add(new Guide(cTGeomGuide));
            }
        }
        if ((cTPath2DList = geom.getPathLst()) != null) {
            for (CTPath2D spPath : cTPath2DList.getPath()) {
                this.paths.add(new Path(spPath));
            }
        }
        if ((cTGeomRect = geom.getRect()) != null) {
            this.textBounds = new Path();
            this.textBounds.addCommand(new MoveToCommand(cTGeomRect.getL(), cTGeomRect.getT()));
            this.textBounds.addCommand(new LineToCommand(cTGeomRect.getR(), cTGeomRect.getT()));
            this.textBounds.addCommand(new LineToCommand(cTGeomRect.getR(), cTGeomRect.getB()));
            this.textBounds.addCommand(new LineToCommand(cTGeomRect.getL(), cTGeomRect.getB()));
            this.textBounds.addCommand(new ClosePathCommand());
        }
    }

    @Override
    public Iterator<Path> iterator() {
        return this.paths.iterator();
    }

    public Path getTextBounds() {
        return this.textBounds;
    }
}

