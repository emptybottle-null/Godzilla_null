/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.BoundSize;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.ContainerWrapper;
import net.miginfocom.layout.DimConstraint;
import net.miginfocom.layout.LC;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.layout.LayoutUtil;
import net.miginfocom.layout.LinkHandler;
import net.miginfocom.layout.PlatformDefaults;
import net.miginfocom.layout.ResizeConstraint;
import net.miginfocom.layout.UnitValue;

public final class Grid {
    public static final boolean TEST_GAPS = true;
    private static final Float[] GROW_100 = new Float[]{ResizeConstraint.WEIGHT_100};
    private static final DimConstraint DOCK_DIM_CONSTRAINT = new DimConstraint();
    private static final int MAX_GRID = 30000;
    private static final int MAX_DOCK_GRID = Short.MAX_VALUE;
    private static final ResizeConstraint GAP_RC_CONST;
    private static final ResizeConstraint GAP_RC_CONST_PUSH;
    private static final CC DEF_CC;
    private final LC lc;
    private final ContainerWrapper container;
    private final LinkedHashMap<Integer, Cell> grid = new LinkedHashMap();
    private HashMap<Integer, BoundSize> wrapGapMap = null;
    private final TreeSet<Integer> rowIndexes = new TreeSet();
    private final TreeSet<Integer> colIndexes = new TreeSet();
    private final AC rowConstr;
    private final AC colConstr;
    private FlowSizeSpec colFlowSpecs = null;
    private FlowSizeSpec rowFlowSpecs = null;
    private final ArrayList<LinkedDimGroup>[] colGroupLists;
    private final ArrayList<LinkedDimGroup>[] rowGroupLists;
    private int[] width = null;
    private int[] height = null;
    private ArrayList<int[]> debugRects = null;
    private HashMap<String, Boolean> linkTargetIDs = null;
    private final int dockOffY;
    private final int dockOffX;
    private final Float[] pushXs;
    private final Float[] pushYs;
    private final ArrayList<LayoutCallback> callbackList;
    private int lastRefWidth = 0;
    private int lastRefHeight = 0;
    private static WeakHashMap<Object, int[][]>[] PARENT_ROWCOL_SIZES_MAP;
    private static WeakHashMap<Object, ArrayList<WeakCell>> PARENT_GRIDPOS_MAP;

    public Grid(ContainerWrapper container, LC lc, AC rowConstr, AC colConstr, Map<? extends ComponentWrapper, CC> ccMap, ArrayList<LayoutCallback> callbackList) {
        this.lc = lc;
        this.rowConstr = rowConstr;
        this.colConstr = colConstr;
        this.container = container;
        this.callbackList = callbackList;
        int wrap = lc.getWrapAfter() != 0 ? lc.getWrapAfter() : (lc.isFlowX() ? colConstr : rowConstr).getConstaints().length;
        boolean useVisualPadding = lc.isVisualPadding();
        ComponentWrapper[] comps = container.getComponents();
        boolean hasTagged = false;
        boolean hasPushX = false;
        boolean hasPushY = false;
        boolean hitEndOfRow = false;
        int[] cellXY = new int[2];
        ArrayList<int[]> spannedRects = new ArrayList<int[]>(2);
        Object[] specs = (lc.isFlowX() ? rowConstr : colConstr).getConstaints();
        int sizeGroupsX = 0;
        int sizeGroupsY = 0;
        int[] dockInsets = null;
        LinkHandler.clearTemporaryBounds(container.getLayout());
        int i = 0;
        while (i < comps.length) {
            boolean spanRestOfRow;
            Cell cell;
            int hideMode;
            ComponentWrapper comp = comps[i];
            CC rootCc = Grid.getCC(comp, ccMap);
            this.addLinkIDs(rootCc);
            int n = comp.isVisible() ? -1 : (hideMode = rootCc.getHideMode() != -1 ? rootCc.getHideMode() : lc.getHideMode());
            if (hideMode == 3) {
                this.setLinkedBounds(comp, rootCc, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), rootCc.isExternal());
                ++i;
                continue;
            }
            if (rootCc.getHorizontal().getSizeGroup() != null) {
                ++sizeGroupsX;
            }
            if (rootCc.getVertical().getSizeGroup() != null) {
                ++sizeGroupsY;
            }
            if (this.getPos(comp, rootCc) != null || rootCc.isExternal()) {
                CompWrap cw = new CompWrap(comp, rootCc, hideMode, useVisualPadding);
                cell = this.grid.get(null);
                if (cell == null) {
                    this.grid.put(null, new Cell(cw));
                } else {
                    cell.compWraps.add(cw);
                }
                if (!rootCc.isBoundsInGrid() || rootCc.isExternal()) {
                    this.setLinkedBounds(comp, rootCc, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight(), rootCc.isExternal());
                    ++i;
                    continue;
                }
            }
            if (rootCc.getDockSide() != -1) {
                if (dockInsets == null) {
                    dockInsets = new int[]{-32767, -32767, Short.MAX_VALUE, Short.MAX_VALUE};
                }
                this.addDockingCell(dockInsets, rootCc.getDockSide(), new CompWrap(comp, rootCc, hideMode, useVisualPadding));
                ++i;
                continue;
            }
            Boolean cellFlowX = rootCc.getFlowX();
            cell = null;
            if (rootCc.isNewline()) {
                this.wrap(cellXY, rootCc.getNewlineGapSize());
            } else if (hitEndOfRow) {
                this.wrap(cellXY, null);
            }
            hitEndOfRow = false;
            boolean isRowInGridMode = !lc.isNoGrid() && !((DimConstraint)LayoutUtil.getIndexSafe(specs, lc.isFlowX() ? cellXY[1] : cellXY[0])).isNoGrid();
            int cx = rootCc.getCellX();
            int cy = rootCc.getCellY();
            if ((cx < 0 || cy < 0) && isRowInGridMode && rootCc.getSkip() == 0) {
                while (!this.isCellFree(cellXY[1], cellXY[0], spannedRects)) {
                    if (Math.abs(this.increase(cellXY, 1)) < wrap) continue;
                    this.wrap(cellXY, null);
                }
            } else {
                if (cx >= 0 && cy >= 0) {
                    if (cy >= 0) {
                        cellXY[0] = cx;
                        cellXY[1] = cy;
                    } else if (lc.isFlowX()) {
                        cellXY[0] = cx;
                    } else {
                        cellXY[1] = cx;
                    }
                    this.ensureIndexSizes(cx, cy);
                }
                cell = this.getCell(cellXY[1], cellXY[0]);
            }
            int skipCount = rootCc.getSkip();
            for (int s = 0; s < skipCount; ++s) {
                do {
                    if (Math.abs(this.increase(cellXY, 1)) < wrap) continue;
                    this.wrap(cellXY, null);
                } while (!this.isCellFree(cellXY[1], cellXY[0], spannedRects));
            }
            if (cell == null) {
                int spanx = Math.min(!isRowInGridMode && lc.isFlowX() ? 2097051 : rootCc.getSpanX(), 30000 - cellXY[0]);
                int spany = Math.min(!isRowInGridMode && !lc.isFlowX() ? 2097051 : rootCc.getSpanY(), 30000 - cellXY[1]);
                cell = new Cell(spanx, spany, cellFlowX != null ? cellFlowX.booleanValue() : lc.isFlowX());
                this.setCell(cellXY[1], cellXY[0], cell);
                if (spanx > 1 || spany > 1) {
                    spannedRects.add(new int[]{cellXY[0], cellXY[1], spanx, spany});
                }
            }
            boolean wrapHandled = false;
            boolean splitExit = false;
            boolean bl = spanRestOfRow = (lc.isFlowX() ? rootCc.getSpanX() : rootCc.getSpanY()) == 2097051;
            for (int splitLeft = isRowInGridMode ? rootCc.getSplit() - 1 : 2097051; splitLeft >= 0 && i < comps.length; --splitLeft) {
                ComponentWrapper compAdd = comps[i];
                CC cc = Grid.getCC(compAdd, ccMap);
                this.addLinkIDs(cc);
                boolean visible = compAdd.isVisible();
                int n2 = visible ? -1 : (hideMode = cc.getHideMode() != -1 ? cc.getHideMode() : lc.getHideMode());
                if (cc.isExternal() || hideMode == 3) {
                    ++i;
                    ++splitLeft;
                    continue;
                }
                hasPushX |= (visible || hideMode > 1) && cc.getPushX() != null;
                hasPushY |= (visible || hideMode > 1) && cc.getPushY() != null;
                if (cc != rootCc) {
                    if (cc.isNewline() || !cc.isBoundsInGrid() || cc.getDockSide() != -1) break;
                    if (splitLeft > 0 && cc.getSkip() > 0) {
                        splitExit = true;
                        break;
                    }
                }
                CompWrap cw = new CompWrap(compAdd, cc, hideMode, useVisualPadding);
                cell.compWraps.add(cw);
                Cell cell2 = cell;
                cell2.hasTagged = cell2.hasTagged | cc.getTag() != null;
                hasTagged |= cell.hasTagged;
                if (cc != rootCc) {
                    if (cc.getHorizontal().getSizeGroup() != null) {
                        ++sizeGroupsX;
                    }
                    if (cc.getVertical().getSizeGroup() != null) {
                        ++sizeGroupsY;
                    }
                }
                ++i;
                if (!cc.isWrap() && (!spanRestOfRow || splitLeft != 0)) continue;
                if (cc.isWrap()) {
                    this.wrap(cellXY, cc.getWrapGapSize());
                } else {
                    hitEndOfRow = true;
                }
                wrapHandled = true;
                break;
            }
            if (wrapHandled || !isRowInGridMode) continue;
            int span = lc.isFlowX() ? cell.spanx : cell.spany;
            if (Math.abs(lc.isFlowX() ? cellXY[0] : cellXY[1]) + span >= wrap) {
                hitEndOfRow = true;
                continue;
            }
            this.increase(cellXY, splitExit ? span - 1 : span);
        }
        if (sizeGroupsX > 0 || sizeGroupsY > 0) {
            HashMap<String, int[]> sizeGroupMapX = sizeGroupsX > 0 ? new HashMap<String, int[]>(sizeGroupsX) : null;
            HashMap<String, int[]> sizeGroupMapY = sizeGroupsY > 0 ? new HashMap<String, int[]>(sizeGroupsY) : null;
            ArrayList<CompWrap> sizeGroupCWs = new ArrayList<CompWrap>(Math.max(sizeGroupsX, sizeGroupsY));
            for (Cell cell : this.grid.values()) {
                for (int i2 = 0; i2 < cell.compWraps.size(); ++i2) {
                    CompWrap cw = (CompWrap)cell.compWraps.get(i2);
                    String sgx = cw.cc.getHorizontal().getSizeGroup();
                    String sgy = cw.cc.getVertical().getSizeGroup();
                    if (sgx == null && sgy == null) continue;
                    if (sgx != null && sizeGroupMapX != null) {
                        Grid.addToSizeGroup(sizeGroupMapX, sgx, cw.getSizes(true));
                    }
                    if (sgy != null && sizeGroupMapY != null) {
                        Grid.addToSizeGroup(sizeGroupMapY, sgy, cw.getSizes(false));
                    }
                    sizeGroupCWs.add(cw);
                }
            }
            for (CompWrap cw : sizeGroupCWs) {
                if (sizeGroupMapX != null) {
                    cw.setForcedSizes((int[])sizeGroupMapX.get(cw.cc.getHorizontal().getSizeGroup()), true);
                }
                if (sizeGroupMapY == null) continue;
                cw.setForcedSizes((int[])sizeGroupMapY.get(cw.cc.getVertical().getSizeGroup()), false);
            }
        }
        if (hasTagged) {
            Grid.sortCellsByPlatform(this.grid.values(), container);
        }
        boolean ltr = LayoutUtil.isLeftToRight(lc, container);
        for (Cell cell : this.grid.values()) {
            ArrayList cws = cell.compWraps;
            int lastI = cws.size() - 1;
            for (int i3 = 0; i3 <= lastI; ++i3) {
                CompWrap cw = (CompWrap)cws.get(i3);
                ComponentWrapper cwBef = i3 > 0 ? ((CompWrap)cws.get(i3 - 1)).comp : null;
                ComponentWrapper cwAft = i3 < lastI ? ((CompWrap)cws.get(i3 + 1)).comp : null;
                String tag = Grid.getCC(cw.comp, ccMap).getTag();
                CC ccBef = cwBef != null ? Grid.getCC(cwBef, ccMap) : null;
                CC ccAft = cwAft != null ? Grid.getCC(cwAft, ccMap) : null;
                cw.calcGaps(cwBef, ccBef, cwAft, ccAft, tag, cell.flowx, ltr);
            }
        }
        this.dockOffX = Grid.getDockInsets(this.colIndexes);
        this.dockOffY = Grid.getDockInsets(this.rowIndexes);
        this.ensureIndexSizes(colConstr.getCount(), rowConstr.getCount());
        this.colGroupLists = this.divideIntoLinkedGroups(false);
        this.rowGroupLists = this.divideIntoLinkedGroups(true);
        this.pushXs = hasPushX || lc.isFillX() ? this.getDefaultPushWeights(false) : null;
        Object object = this.pushYs = hasPushY || lc.isFillY() ? this.getDefaultPushWeights(true) : null;
        if (LayoutUtil.isDesignTime(container)) {
            Grid.saveGrid(container, this.grid);
        }
    }

    private void ensureIndexSizes(int colCount, int rowCount) {
        int i;
        for (i = 0; i < colCount; ++i) {
            this.colIndexes.add(i);
        }
        for (i = 0; i < rowCount; ++i) {
            this.rowIndexes.add(i);
        }
    }

    private static CC getCC(ComponentWrapper comp, Map<? extends ComponentWrapper, CC> ccMap) {
        CC cc = ccMap.get(comp);
        return cc != null ? cc : DEF_CC;
    }

    private void addLinkIDs(CC cc) {
        String[] linkIDs;
        for (String linkID : linkIDs = cc.getLinkTargets()) {
            if (this.linkTargetIDs == null) {
                this.linkTargetIDs = new HashMap();
            }
            this.linkTargetIDs.put(linkID, null);
        }
    }

    public void invalidateContainerSize() {
        this.colFlowSpecs = null;
        this.invalidateComponentSizes();
    }

    private void invalidateComponentSizes() {
        for (Cell cell : this.grid.values()) {
            for (CompWrap compWrap : cell.compWraps) {
                compWrap.invalidateSizes();
            }
        }
    }

    public boolean layout(int[] bounds, UnitValue alignX, UnitValue alignY, boolean debug, boolean notUsed) {
        return this.layoutImpl(bounds, alignX, alignY, debug, false);
    }

    public boolean layout(int[] bounds, UnitValue alignX, UnitValue alignY, boolean debug) {
        return this.layoutImpl(bounds, alignX, alignY, debug, false);
    }

    private boolean layoutImpl(int[] bounds, UnitValue alignX, UnitValue alignY, boolean debug, boolean trialRun) {
        if (debug) {
            this.debugRects = new ArrayList();
        }
        if (this.colFlowSpecs == null) {
            this.checkSizeCalcs(bounds[2], bounds[3]);
        }
        this.resetLinkValues(true, true);
        this.layoutInOneDim(bounds[2], alignX, false, this.pushXs);
        this.layoutInOneDim(bounds[3], alignY, true, this.pushYs);
        HashMap<String, Integer> endGrpXMap = null;
        HashMap<String, Integer> endGrpYMap = null;
        int compCount = this.container.getComponentCount();
        boolean addVisualPadding = this.lc.isVisualPadding();
        boolean layoutAgain = false;
        if (compCount > 0) {
            block0: for (int j = 0; j < (this.linkTargetIDs != null ? 2 : 1); ++j) {
                boolean doAgain;
                int count = 0;
                do {
                    doAgain = false;
                    for (Cell cell : this.grid.values()) {
                        for (CompWrap cw : cell.compWraps) {
                            if (j == 0) {
                                if (!(doAgain |= this.doAbsoluteCorrections(cw, bounds))) {
                                    if (cw.cc.getHorizontal().getEndGroup() != null) {
                                        endGrpXMap = Grid.addToEndGroup(endGrpXMap, cw.cc.getHorizontal().getEndGroup(), cw.x + cw.w);
                                    }
                                    if (cw.cc.getVertical().getEndGroup() != null) {
                                        endGrpYMap = Grid.addToEndGroup(endGrpYMap, cw.cc.getVertical().getEndGroup(), cw.y + cw.h);
                                    }
                                }
                                if (this.linkTargetIDs != null && (this.linkTargetIDs.containsKey("visual") || this.linkTargetIDs.containsKey("container"))) {
                                    layoutAgain = true;
                                }
                            }
                            if (this.linkTargetIDs != null && j != 1) continue;
                            if (cw.cc.getHorizontal().getEndGroup() != null) {
                                cw.w = (Integer)endGrpXMap.get(cw.cc.getHorizontal().getEndGroup()) - cw.x;
                            }
                            if (cw.cc.getVertical().getEndGroup() != null) {
                                cw.h = (Integer)endGrpYMap.get(cw.cc.getVertical().getEndGroup()) - cw.y;
                            }
                            Object object = cw;
                            ((CompWrap)object).x = ((CompWrap)object).x + bounds[0];
                            object = cw;
                            ((CompWrap)object).y = ((CompWrap)object).y + bounds[1];
                            if (!trialRun) {
                                cw.transferBounds(addVisualPadding);
                            }
                            if (this.callbackList == null) continue;
                            for (LayoutCallback callback : this.callbackList) {
                                callback.correctBounds(cw.comp);
                            }
                        }
                    }
                    this.clearGroupLinkBounds();
                    if (++count <= (compCount << 3) + 10) continue;
                    System.err.println("Unstable cyclic dependency in absolute linked values.");
                    continue block0;
                } while (doAgain);
            }
        }
        if (debug) {
            for (Cell cell : this.grid.values()) {
                ArrayList compWraps = cell.compWraps;
                for (CompWrap cw : compWraps) {
                    LinkedDimGroup hGrp = Grid.getGroupContaining(this.colGroupLists, cw);
                    LinkedDimGroup vGrp = Grid.getGroupContaining(this.rowGroupLists, cw);
                    if (hGrp == null || vGrp == null) continue;
                    this.debugRects.add(new int[]{hGrp.lStart + bounds[0] - (hGrp.fromEnd ? hGrp.lSize : 0), vGrp.lStart + bounds[1] - (vGrp.fromEnd ? vGrp.lSize : 0), hGrp.lSize, vGrp.lSize});
                }
            }
        }
        return layoutAgain;
    }

    public void paintDebug() {
        if (this.debugRects != null) {
            this.container.paintDebugOutline(this.lc.isVisualPadding());
            ArrayList<int[]> painted = new ArrayList<int[]>();
            for (int[] r : this.debugRects) {
                if (painted.contains(r)) continue;
                this.container.paintDebugCell(r[0], r[1], r[2], r[3]);
                painted.add(r);
            }
            for (Cell cell : this.grid.values()) {
                ArrayList compWraps = cell.compWraps;
                for (CompWrap compWrap : compWraps) {
                    compWrap.comp.paintDebugOutline(this.lc.isVisualPadding());
                }
            }
        }
    }

    public ContainerWrapper getContainer() {
        return this.container;
    }

    public final int[] getWidth() {
        return this.getWidth(this.lastRefHeight);
    }

    public final int[] getWidth(int refHeight) {
        this.checkSizeCalcs(this.lastRefWidth, refHeight);
        return (int[])this.width.clone();
    }

    public final int[] getHeight() {
        return this.getHeight(this.lastRefWidth);
    }

    public final int[] getHeight(int refWidth) {
        this.checkSizeCalcs(refWidth, this.lastRefHeight);
        return (int[])this.height.clone();
    }

    private void checkSizeCalcs(int refWidth, int refHeight) {
        if (this.colFlowSpecs == null) {
            this.calcGridSizes(refWidth, refHeight);
        }
        if (refWidth > 0 && refWidth != this.lastRefWidth || refHeight > 0 && refHeight != this.lastRefHeight) {
            int[] refBounds = new int[]{0, 0, refWidth > 0 ? refWidth : this.width[1], refHeight > 0 ? refHeight : this.height[1]};
            this.layoutImpl(refBounds, null, null, false, true);
            this.calcGridSizes(refWidth, refHeight);
        }
        this.lastRefWidth = refWidth;
        this.lastRefHeight = refHeight;
    }

    private void calcGridSizes(int refWidth, int refHeight) {
        FlowSizeSpec colSpecs = this.calcRowsOrColsSizes(true, refWidth);
        FlowSizeSpec rowSpecs = this.calcRowsOrColsSizes(false, refHeight);
        this.colFlowSpecs = colSpecs;
        this.rowFlowSpecs = rowSpecs;
        this.width = this.getMinPrefMaxSumSize(true, colSpecs.sizes);
        this.height = this.getMinPrefMaxSumSize(false, rowSpecs.sizes);
        if (this.linkTargetIDs == null) {
            this.resetLinkValues(false, true);
        } else {
            this.layout(new int[]{0, 0, refWidth, refHeight}, null, null, false);
            this.resetLinkValues(false, false);
        }
        this.adjustSizeForAbsolute(true);
        this.adjustSizeForAbsolute(false);
    }

    private UnitValue[] getPos(ComponentWrapper cw, CC cc) {
        UnitValue[] callbackPos = null;
        if (this.callbackList != null) {
            for (int i = 0; i < this.callbackList.size() && callbackPos == null; ++i) {
                callbackPos = this.callbackList.get(i).getPosition(cw);
            }
        }
        UnitValue[] ccPos = cc.getPos();
        if (callbackPos == null || ccPos == null) {
            return callbackPos != null ? callbackPos : ccPos;
        }
        for (int i = 0; i < 4; ++i) {
            UnitValue cbUv = callbackPos[i];
            if (cbUv == null) continue;
            ccPos[i] = cbUv;
        }
        return ccPos;
    }

    private BoundSize[] getCallbackSize(ComponentWrapper cw) {
        if (this.callbackList != null) {
            for (LayoutCallback callback : this.callbackList) {
                BoundSize[] bs = callback.getSize(cw);
                if (bs == null) continue;
                return bs;
            }
        }
        return null;
    }

    private static int getDockInsets(TreeSet<Integer> set) {
        int c = 0;
        for (Integer i : set) {
            if (i >= -30000) break;
            ++c;
        }
        return c;
    }

    private boolean setLinkedBounds(ComponentWrapper cw, CC cc, int x, int y, int w, int h, boolean external) {
        String id;
        String string = id = cc.getId() != null ? cc.getId() : cw.getLinkId();
        if (id == null) {
            return false;
        }
        String gid = null;
        int grIx = id.indexOf(46);
        if (grIx != -1) {
            gid = id.substring(0, grIx);
            id = id.substring(grIx + 1);
        }
        Object lay = this.container.getLayout();
        boolean changed = false;
        if (external || this.linkTargetIDs != null && this.linkTargetIDs.containsKey(id)) {
            changed = LinkHandler.setBounds(lay, id, x, y, w, h, !external, false);
        }
        if (gid != null && (external || this.linkTargetIDs != null && this.linkTargetIDs.containsKey(gid))) {
            if (this.linkTargetIDs == null) {
                this.linkTargetIDs = new HashMap(4);
            }
            this.linkTargetIDs.put(gid, Boolean.TRUE);
            changed |= LinkHandler.setBounds(lay, gid, x, y, w, h, !external, true);
        }
        return changed;
    }

    private int increase(int[] p, int cnt) {
        return this.lc.isFlowX() ? p[0] + cnt : p[1] + cnt;
    }

    private void wrap(int[] cellXY, BoundSize gapSize) {
        boolean flowx = this.lc.isFlowX();
        cellXY[0] = flowx ? 0 : cellXY[0] + 1;
        int n = cellXY[1] = flowx ? cellXY[1] + 1 : 0;
        if (gapSize != null) {
            if (this.wrapGapMap == null) {
                this.wrapGapMap = new HashMap(8);
            }
            this.wrapGapMap.put(cellXY[flowx ? 1 : 0], gapSize);
        }
        if (flowx) {
            this.rowIndexes.add(cellXY[1]);
        } else {
            this.colIndexes.add(cellXY[0]);
        }
    }

    private static void sortCellsByPlatform(Collection<Cell> cells, ContainerWrapper parent) {
        String order = PlatformDefaults.getButtonOrder();
        String orderLo = order.toLowerCase();
        int unrelSize = PlatformDefaults.convertToPixels(1.0f, "u", true, 0.0f, parent, null);
        if (unrelSize == -87654312) {
            throw new IllegalArgumentException("'unrelated' not recognized by PlatformDefaults!");
        }
        int[] gapUnrel = new int[]{unrelSize, unrelSize, -2147471302};
        int[] flGap = new int[]{0, 0, -2147471302};
        for (Cell cell : cells) {
            if (!cell.hasTagged) continue;
            CompWrap prevCW = null;
            boolean nextUnrel = false;
            boolean nextPush = false;
            ArrayList<CompWrap> sortedList = new ArrayList<CompWrap>(cell.compWraps.size());
            int iSz = orderLo.length();
            for (int i = 0; i < iSz; ++i) {
                char c = orderLo.charAt(i);
                if (c == '+' || c == '_') {
                    nextUnrel = true;
                    if (c != '+') continue;
                    nextPush = true;
                    continue;
                }
                String tag = PlatformDefaults.getTagForChar(c);
                if (tag == null) continue;
                int jSz = cell.compWraps.size();
                for (int j = 0; j < jSz; ++j) {
                    CompWrap cw = (CompWrap)cell.compWraps.get(j);
                    if (!tag.equals(cw.cc.getTag())) continue;
                    if (Character.isUpperCase(order.charAt(i))) {
                        cw.adjustMinHorSizeUp((int)PlatformDefaults.getMinimumButtonWidthIncludingPadding(0.0f, parent, cw.comp));
                    }
                    sortedList.add(cw);
                    if (nextUnrel) {
                        (prevCW != null ? prevCW : cw).mergeGapSizes(gapUnrel, cell.flowx, prevCW == null);
                        if (nextPush) {
                            cw.forcedPushGaps = 1;
                            nextUnrel = false;
                            nextPush = false;
                        }
                    }
                    if (c == 'u') {
                        nextUnrel = true;
                    }
                    prevCW = cw;
                }
            }
            if (sortedList.size() > 0) {
                CompWrap cw = (CompWrap)sortedList.get(sortedList.size() - 1);
                if (nextUnrel) {
                    cw.mergeGapSizes(gapUnrel, cell.flowx, false);
                    if (nextPush) {
                        CompWrap compWrap = cw;
                        compWrap.forcedPushGaps = compWrap.forcedPushGaps | 2;
                    }
                }
                if (cw.cc.getHorizontal().getGapAfter() == null) {
                    cw.setGaps(flGap, 3);
                }
                if ((cw = (CompWrap)sortedList.get(0)).cc.getHorizontal().getGapBefore() == null) {
                    cw.setGaps(flGap, 1);
                }
            }
            if (cell.compWraps.size() == sortedList.size()) {
                cell.compWraps.clear();
            } else {
                cell.compWraps.removeAll(sortedList);
            }
            cell.compWraps.addAll(sortedList);
        }
    }

    private Float[] getDefaultPushWeights(boolean isRows) {
        ArrayList<LinkedDimGroup>[] groupLists = isRows ? this.rowGroupLists : this.colGroupLists;
        Float[] pushWeightArr = GROW_100;
        int i = 0;
        int ix = 1;
        while (i < groupLists.length) {
            ArrayList<LinkedDimGroup> grps = groupLists[i];
            Float rowPushWeight = null;
            for (LinkedDimGroup grp : grps) {
                for (int c = 0; c < grp._compWraps.size(); ++c) {
                    Float pushWeight;
                    int hideMode;
                    CompWrap cw = (CompWrap)grp._compWraps.get(c);
                    int n = cw.comp.isVisible() ? -1 : (hideMode = cw.cc.getHideMode() != -1 ? cw.cc.getHideMode() : this.lc.getHideMode());
                    Float f = hideMode < 2 ? (isRows ? cw.cc.getPushY() : cw.cc.getPushX()) : (pushWeight = null);
                    if (rowPushWeight != null && (pushWeight == null || !(pushWeight.floatValue() > rowPushWeight.floatValue()))) continue;
                    rowPushWeight = pushWeight;
                }
            }
            if (rowPushWeight != null) {
                if (pushWeightArr == GROW_100) {
                    pushWeightArr = new Float[(groupLists.length << 1) + 1];
                }
                pushWeightArr[ix] = rowPushWeight;
            }
            ++i;
            ix += 2;
        }
        return pushWeightArr;
    }

    private void clearGroupLinkBounds() {
        if (this.linkTargetIDs == null) {
            return;
        }
        for (Map.Entry<String, Boolean> o : this.linkTargetIDs.entrySet()) {
            if (o.getValue() != Boolean.TRUE) continue;
            LinkHandler.clearBounds(this.container.getLayout(), o.getKey());
        }
    }

    private void resetLinkValues(boolean parentSize, boolean compLinks) {
        Object lay = this.container.getLayout();
        if (compLinks) {
            LinkHandler.clearTemporaryBounds(lay);
        }
        boolean defIns = !this.hasDocks();
        int parW = parentSize ? this.lc.getWidth().constrain(this.container.getWidth(), Grid.getParentSize(this.container, true), this.container) : 0;
        int parH = parentSize ? this.lc.getHeight().constrain(this.container.getHeight(), Grid.getParentSize(this.container, false), this.container) : 0;
        int insX = LayoutUtil.getInsets(this.lc, 0, defIns).getPixels(0.0f, this.container, null);
        int insY = LayoutUtil.getInsets(this.lc, 1, defIns).getPixels(0.0f, this.container, null);
        int visW = parW - insX - LayoutUtil.getInsets(this.lc, 2, defIns).getPixels(0.0f, this.container, null);
        int visH = parH - insY - LayoutUtil.getInsets(this.lc, 3, defIns).getPixels(0.0f, this.container, null);
        LinkHandler.setBounds(lay, "visual", insX, insY, visW, visH, true, false);
        LinkHandler.setBounds(lay, "container", 0, 0, parW, parH, true, false);
    }

    private static LinkedDimGroup getGroupContaining(ArrayList<LinkedDimGroup>[] groupLists, CompWrap cw) {
        for (ArrayList<LinkedDimGroup> groups : groupLists) {
            for (LinkedDimGroup group : groups) {
                ArrayList cwList = group._compWraps;
                for (CompWrap aCwList : cwList) {
                    if (aCwList != cw) continue;
                    return group;
                }
            }
        }
        return null;
    }

    private boolean doAbsoluteCorrections(CompWrap cw, int[] bounds) {
        boolean changed = false;
        int[] stSz = this.getAbsoluteDimBounds(cw, bounds[2], true);
        if (stSz != null) {
            cw.setDimBounds(stSz[0], stSz[1], true);
        }
        if ((stSz = this.getAbsoluteDimBounds(cw, bounds[3], false)) != null) {
            cw.setDimBounds(stSz[0], stSz[1], false);
        }
        if (this.linkTargetIDs != null) {
            changed = this.setLinkedBounds(cw.comp, cw.cc, cw.x, cw.y, cw.w, cw.h, false);
        }
        return changed;
    }

    private void adjustSizeForAbsolute(boolean isHor) {
        int[] curSizes = isHor ? this.width : this.height;
        Cell absCell = this.grid.get(null);
        if (absCell == null || absCell.compWraps.size() == 0) {
            return;
        }
        ArrayList cws = absCell.compWraps;
        int maxEnd = 0;
        int cwSz = absCell.compWraps.size();
        for (int j = 0; j < cwSz + 3; ++j) {
            boolean doAgain = false;
            for (int i = 0; i < cwSz; ++i) {
                CompWrap cw = (CompWrap)cws.get(i);
                int[] stSz = this.getAbsoluteDimBounds(cw, 0, isHor);
                int end = stSz[0] + stSz[1];
                if (maxEnd < end) {
                    maxEnd = end;
                }
                if (this.linkTargetIDs == null) continue;
                doAgain |= this.setLinkedBounds(cw.comp, cw.cc, stSz[0], stSz[0], stSz[1], stSz[1], false);
            }
            if (!doAgain) break;
            maxEnd = 0;
            this.clearGroupLinkBounds();
        }
        if (curSizes[0] < (maxEnd += LayoutUtil.getInsets(this.lc, isHor ? 3 : 2, !this.hasDocks()).getPixels(0.0f, this.container, null))) {
            curSizes[0] = maxEnd;
        }
        if (curSizes[1] < maxEnd) {
            curSizes[1] = maxEnd;
        }
    }

    private int[] getAbsoluteDimBounds(CompWrap cw, int refSize, boolean isHor) {
        int sz;
        if (cw.cc.isExternal()) {
            if (isHor) {
                return new int[]{cw.comp.getX(), cw.comp.getWidth()};
            }
            return new int[]{cw.comp.getY(), cw.comp.getHeight()};
        }
        UnitValue[] pad = cw.cc.getPadding();
        UnitValue[] pos = this.getPos(cw.comp, cw.cc);
        if (pos == null && pad == null) {
            return null;
        }
        int st = isHor ? cw.x : cw.y;
        int n = sz = isHor ? cw.w : cw.h;
        if (pos != null) {
            UnitValue stUV = pos[isHor ? 0 : 1];
            UnitValue endUV = pos[isHor ? 2 : 3];
            int minSz = cw.getSize(0, isHor);
            int maxSz = cw.getSize(2, isHor);
            sz = Math.min(Math.max(cw.getSize(1, isHor), minSz), maxSz);
            if (stUV != null) {
                st = stUV.getPixels(stUV.getUnit() == 12 ? (float)sz : (float)refSize, this.container, cw.comp);
                if (endUV != null) {
                    sz = Math.min(Math.max((isHor ? cw.x + cw.w : cw.y + cw.h) - st, minSz), maxSz);
                }
            }
            if (endUV != null) {
                if (stUV != null) {
                    sz = Math.min(Math.max(endUV.getPixels(refSize, this.container, cw.comp) - st, minSz), maxSz);
                } else {
                    st = endUV.getPixels(refSize, this.container, cw.comp) - sz;
                }
            }
        }
        if (pad != null) {
            UnitValue uv = pad[isHor ? 1 : 0];
            int p = uv != null ? uv.getPixels(refSize, this.container, cw.comp) : 0;
            st += p;
            uv = pad[isHor ? 3 : 2];
            sz += -p + (uv != null ? uv.getPixels(refSize, this.container, cw.comp) : 0);
        }
        return new int[]{st, sz};
    }

    private void layoutInOneDim(int refSize, UnitValue align, boolean isRows, Float[] defaultPushWeights) {
        int curPos;
        boolean fromEnd = !(!isRows ? LayoutUtil.isLeftToRight(this.lc, this.container) : this.lc.isTopToBottom());
        DimConstraint[] primDCs = (isRows ? this.rowConstr : this.colConstr).getConstaints();
        FlowSizeSpec fss = isRows ? this.rowFlowSpecs : this.colFlowSpecs;
        ArrayList<LinkedDimGroup>[] rowCols = isRows ? this.rowGroupLists : this.colGroupLists;
        int[] rowColSizes = LayoutUtil.calculateSerial(fss.sizes, fss.resConstsInclGaps, defaultPushWeights, 1, refSize);
        if (LayoutUtil.isDesignTime(this.container)) {
            TreeSet<Integer> indexes = isRows ? this.rowIndexes : this.colIndexes;
            int[] ixArr = new int[indexes.size()];
            int ix = 0;
            for (Integer i : indexes) {
                ixArr[ix++] = i;
            }
            Grid.putSizesAndIndexes(this.container.getComponent(), rowColSizes, ixArr, isRows);
        }
        int n = curPos = align != null ? align.getPixels(refSize - LayoutUtil.sum(rowColSizes), this.container, null) : 0;
        if (fromEnd) {
            curPos = refSize - curPos;
        }
        for (int i = 0; i < rowCols.length; ++i) {
            ArrayList<LinkedDimGroup> linkedGroups = rowCols[i];
            int scIx = i - (isRows ? this.dockOffY : this.dockOffX);
            int bIx = i << 1;
            int bIx2 = bIx + 1;
            curPos += fromEnd ? -rowColSizes[bIx] : rowColSizes[bIx];
            DimConstraint primDC = scIx >= 0 ? primDCs[scIx >= primDCs.length ? primDCs.length - 1 : scIx] : DOCK_DIM_CONSTRAINT;
            int rowSize = rowColSizes[bIx2];
            for (LinkedDimGroup group : linkedGroups) {
                int groupSize = rowSize;
                if (group.span > 1) {
                    groupSize = LayoutUtil.sum(rowColSizes, bIx2, Math.min((group.span << 1) - 1, rowColSizes.length - bIx2 - 1));
                }
                group.layout(primDC, curPos, groupSize, group.span);
            }
            curPos += fromEnd ? -rowSize : rowSize;
        }
    }

    private static void addToSizeGroup(HashMap<String, int[]> sizeGroups, String sizeGroup, int[] size) {
        int[] sgSize = sizeGroups.get(sizeGroup);
        if (sgSize == null) {
            sizeGroups.put(sizeGroup, new int[]{size[0], size[1], size[2]});
        } else {
            sgSize[0] = Math.max(size[0], sgSize[0]);
            sgSize[1] = Math.max(size[1], sgSize[1]);
            sgSize[2] = Math.min(size[2], sgSize[2]);
        }
    }

    private static HashMap<String, Integer> addToEndGroup(HashMap<String, Integer> endGroups, String endGroup, int end) {
        if (endGroup != null) {
            Integer oldEnd;
            if (endGroups == null) {
                endGroups = new HashMap(4);
            }
            if ((oldEnd = endGroups.get(endGroup)) == null || end > oldEnd) {
                endGroups.put(endGroup, end);
            }
        }
        return endGroups;
    }

    private FlowSizeSpec calcRowsOrColsSizes(boolean isHor, int containerSize) {
        int r;
        BoundSize cSz;
        Float[] defPush;
        ArrayList<LinkedDimGroup>[] groupsLists = isHor ? this.colGroupLists : this.rowGroupLists;
        Float[] floatArray = defPush = isHor ? this.pushXs : this.pushYs;
        if (containerSize <= 0) {
            containerSize = isHor ? this.container.getWidth() : this.container.getHeight();
        }
        BoundSize boundSize = cSz = isHor ? this.lc.getWidth() : this.lc.getHeight();
        if (!cSz.isUnset()) {
            containerSize = cSz.constrain(containerSize, Grid.getParentSize(this.container, isHor), this.container);
        }
        DimConstraint[] primDCs = (isHor ? this.colConstr : this.rowConstr).getConstaints();
        TreeSet<Integer> primIndexes = isHor ? this.colIndexes : this.rowIndexes;
        int[][] rowColBoundSizes = new int[primIndexes.size()][];
        HashMap<String, int[]> sizeGroupMap = new HashMap<String, int[]>(4);
        DimConstraint[] allDCs = new DimConstraint[primIndexes.size()];
        Iterator<Integer> primIt = primIndexes.iterator();
        for (r = 0; r < rowColBoundSizes.length; ++r) {
            int cellIx = primIt.next();
            int[] rowColSizes = new int[3];
            allDCs[r] = cellIx >= -30000 && cellIx <= 30000 ? primDCs[cellIx >= primDCs.length ? primDCs.length - 1 : cellIx] : DOCK_DIM_CONSTRAINT;
            ArrayList<LinkedDimGroup> groups = groupsLists[r];
            int[] groupSizes = new int[]{Grid.getTotalGroupsSizeParallel(groups, 0, false), Grid.getTotalGroupsSizeParallel(groups, 1, false), 2097051};
            Grid.correctMinMax(groupSizes);
            BoundSize dimSize = allDCs[r].getSize();
            for (int sType = 0; sType <= 2; ++sType) {
                int rowColSize = groupSizes[sType];
                UnitValue uv = dimSize.getSize(sType);
                if (uv != null) {
                    int unit = uv.getUnit();
                    rowColSize = unit == 14 ? groupSizes[1] : (unit == 13 ? groupSizes[0] : (unit == 15 ? groupSizes[2] : uv.getPixels(containerSize, this.container, null)));
                } else if (cellIx >= -30000 && cellIx <= 30000 && rowColSize == 0) {
                    rowColSize = LayoutUtil.isDesignTime(this.container) ? LayoutUtil.getDesignTimeEmptySize() : 0;
                }
                rowColSizes[sType] = rowColSize;
            }
            Grid.correctMinMax(rowColSizes);
            Grid.addToSizeGroup(sizeGroupMap, allDCs[r].getSizeGroup(), rowColSizes);
            rowColBoundSizes[r] = rowColSizes;
        }
        if (sizeGroupMap.size() > 0) {
            for (r = 0; r < rowColBoundSizes.length; ++r) {
                if (allDCs[r].getSizeGroup() == null) continue;
                rowColBoundSizes[r] = (int[])sizeGroupMap.get(allDCs[r].getSizeGroup());
            }
        }
        ResizeConstraint[] resConstrs = Grid.getRowResizeConstraints(allDCs);
        boolean[] fillInPushGaps = new boolean[allDCs.length + 1];
        int[][] gapSizes = this.getRowGaps(allDCs, containerSize, isHor, fillInPushGaps);
        FlowSizeSpec fss = Grid.mergeSizesGapsAndResConstrs(resConstrs, fillInPushGaps, rowColBoundSizes, gapSizes);
        this.adjustMinPrefForSpanningComps(allDCs, defPush, fss, groupsLists);
        return fss;
    }

    private static int getParentSize(ComponentWrapper cw, boolean isHor) {
        ContainerWrapper p = cw.getParent();
        return p != null ? (isHor ? cw.getWidth() : cw.getHeight()) : 0;
    }

    private int[] getMinPrefMaxSumSize(boolean isHor, int[][] sizes) {
        int[] retSizes = new int[3];
        BoundSize sz = isHor ? this.lc.getWidth() : this.lc.getHeight();
        for (int i = 0; i < sizes.length; ++i) {
            if (sizes[i] == null) continue;
            int[] size = sizes[i];
            for (int sType = 0; sType <= 2; ++sType) {
                if (sz.getSize(sType) != null) {
                    if (i != 0) continue;
                    retSizes[sType] = sz.getSize(sType).getPixels(Grid.getParentSize(this.container, isHor), this.container, null);
                    continue;
                }
                int s = size[sType];
                if (s != -2147471302) {
                    if (sType == 1) {
                        int bnd = size[2];
                        if (bnd != -2147471302 && bnd < s) {
                            s = bnd;
                        }
                        if ((bnd = size[0]) > s) {
                            s = bnd;
                        }
                    }
                    int n = sType;
                    retSizes[n] = retSizes[n] + s;
                }
                if (size[2] != -2147471302 && retSizes[2] <= 2097051) continue;
                retSizes[2] = 2097051;
            }
        }
        Grid.correctMinMax(retSizes);
        return retSizes;
    }

    private static ResizeConstraint[] getRowResizeConstraints(DimConstraint[] specs) {
        ResizeConstraint[] resConsts = new ResizeConstraint[specs.length];
        for (int i = 0; i < resConsts.length; ++i) {
            resConsts[i] = specs[i].resize;
        }
        return resConsts;
    }

    private static ResizeConstraint[] getComponentResizeConstraints(ArrayList<CompWrap> compWraps, boolean isHor) {
        ResizeConstraint[] resConsts = new ResizeConstraint[compWraps.size()];
        for (int i = 0; i < resConsts.length; ++i) {
            CC fc = compWraps.get(i).cc;
            resConsts[i] = fc.getDimConstraint((boolean)isHor).resize;
            int dock = fc.getDockSide();
            if (!(isHor ? dock == 0 || dock == 2 : dock == 1 || dock == 3)) continue;
            ResizeConstraint dc = resConsts[i];
            resConsts[i] = new ResizeConstraint(dc.shrinkPrio, dc.shrink, dc.growPrio, ResizeConstraint.WEIGHT_100);
        }
        return resConsts;
    }

    private static boolean[] getComponentGapPush(ArrayList<CompWrap> compWraps, boolean isHor) {
        boolean[] barr = new boolean[compWraps.size() + 1];
        for (int i = 0; i < barr.length; ++i) {
            boolean push;
            boolean bl = push = i > 0 && compWraps.get(i - 1).isPushGap(isHor, false);
            if (!push && i < barr.length - 1) {
                push = compWraps.get(i).isPushGap(isHor, true);
            }
            barr[i] = push;
        }
        return barr;
    }

    private int[][] getRowGaps(DimConstraint[] specs, int refSize, boolean isHor, boolean[] fillInPushGaps) {
        BoundSize defGap;
        BoundSize boundSize = defGap = isHor ? this.lc.getGridGapX() : this.lc.getGridGapY();
        if (defGap == null) {
            defGap = isHor ? PlatformDefaults.getGridGapX() : PlatformDefaults.getGridGapY();
        }
        int[] defGapArr = defGap.getPixelSizes(refSize, this.container, null);
        boolean defIns = !this.hasDocks();
        UnitValue firstGap = LayoutUtil.getInsets(this.lc, isHor ? 1 : 0, defIns);
        UnitValue lastGap = LayoutUtil.getInsets(this.lc, isHor ? 3 : 2, defIns);
        int[][] retValues = new int[specs.length + 1][];
        int wgIx = 0;
        for (int i = 0; i < retValues.length; ++i) {
            BoundSize wrapGapSize;
            boolean edgeAfter;
            DimConstraint specBefore = i > 0 ? specs[i - 1] : null;
            DimConstraint specAfter = i < specs.length ? specs[i] : null;
            boolean edgeBefore = specBefore == DOCK_DIM_CONSTRAINT || specBefore == null;
            boolean bl = edgeAfter = specAfter == DOCK_DIM_CONSTRAINT || specAfter == null;
            if (edgeBefore && edgeAfter) continue;
            BoundSize boundSize2 = wrapGapSize = this.wrapGapMap == null || isHor == this.lc.isFlowX() ? null : this.wrapGapMap.get(wgIx++);
            if (wrapGapSize == null) {
                int[] gapAfter;
                int[] gapBefore = specBefore != null ? specBefore.getRowGaps(this.container, null, refSize, false) : null;
                int[] nArray = gapAfter = specAfter != null ? specAfter.getRowGaps(this.container, null, refSize, true) : null;
                if (edgeBefore && gapAfter == null && firstGap != null) {
                    int bef = firstGap.getPixels(refSize, this.container, null);
                    retValues[i] = new int[]{bef, bef, bef};
                } else if (edgeAfter && gapBefore == null && firstGap != null) {
                    int aft = lastGap.getPixels(refSize, this.container, null);
                    retValues[i] = new int[]{aft, aft, aft};
                } else {
                    int[] nArray2;
                    if (gapAfter != gapBefore) {
                        nArray2 = Grid.mergeSizes(gapAfter, gapBefore);
                    } else {
                        int[] nArray3 = new int[3];
                        nArray3[0] = defGapArr[0];
                        nArray3[1] = defGapArr[1];
                        nArray2 = nArray3;
                        nArray3[2] = defGapArr[2];
                    }
                    retValues[i] = nArray2;
                }
                if ((specBefore == null || !specBefore.isGapAfterPush()) && (specAfter == null || !specAfter.isGapBeforePush())) continue;
                fillInPushGaps[i] = true;
                continue;
            }
            retValues[i] = wrapGapSize.isUnset() ? new int[]{defGapArr[0], defGapArr[1], defGapArr[2]} : wrapGapSize.getPixelSizes(refSize, this.container, null);
            fillInPushGaps[i] = wrapGapSize.getGapPush();
        }
        return retValues;
    }

    private static int[][] getGaps(ArrayList<CompWrap> compWraps, boolean isHor) {
        int compCount = compWraps.size();
        int[][] retValues = new int[compCount + 1][];
        retValues[0] = compWraps.get(0).getGaps(isHor, true);
        for (int i = 0; i < compCount; ++i) {
            int[] gap1 = compWraps.get(i).getGaps(isHor, false);
            int[] gap2 = i < compCount - 1 ? compWraps.get(i + 1).getGaps(isHor, true) : null;
            retValues[i + 1] = Grid.mergeSizes(gap1, gap2);
        }
        return retValues;
    }

    private boolean hasDocks() {
        return this.dockOffX > 0 || this.dockOffY > 0 || this.rowIndexes.last() > 30000 || this.colIndexes.last() > 30000;
    }

    private void adjustMinPrefForSpanningComps(DimConstraint[] specs, Float[] defPush, FlowSizeSpec fss, ArrayList<LinkedDimGroup>[] groupsLists) {
        for (int r = groupsLists.length - 1; r >= 0; --r) {
            ArrayList<LinkedDimGroup> groups = groupsLists[r];
            for (LinkedDimGroup group : groups) {
                if (group.span == 1) continue;
                int[] sizes = group.getMinPrefMax();
                for (int s = 0; s <= 1; ++s) {
                    int cSize = sizes[s];
                    if (cSize == -2147471302) continue;
                    int rowSize = 0;
                    int sIx = (r << 1) + 1;
                    int len = Math.min(group.span << 1, fss.sizes.length - sIx) - 1;
                    for (int j = sIx; j < sIx + len; ++j) {
                        int sz = fss.sizes[j][s];
                        if (sz == -2147471302) continue;
                        rowSize += sz;
                    }
                    if (rowSize >= cSize || len <= 0) continue;
                    int newRowSize = 0;
                    for (int eagerness = 0; eagerness < 4 && newRowSize < cSize; ++eagerness) {
                        newRowSize = fss.expandSizes(specs, defPush, cSize, sIx, len, s, eagerness);
                    }
                }
            }
        }
    }

    private ArrayList<LinkedDimGroup>[] divideIntoLinkedGroups(boolean isRows) {
        boolean fromEnd = !(!isRows ? LayoutUtil.isLeftToRight(this.lc, this.container) : this.lc.isTopToBottom());
        TreeSet<Integer> primIndexes = isRows ? this.rowIndexes : this.colIndexes;
        TreeSet<Integer> secIndexes = isRows ? this.colIndexes : this.rowIndexes;
        DimConstraint[] primDCs = (isRows ? this.rowConstr : this.colConstr).getConstaints();
        ArrayList[] groupLists = new ArrayList[primIndexes.size()];
        int gIx = 0;
        for (int i : primIndexes) {
            DimConstraint dc = i >= -30000 && i <= 30000 ? primDCs[i >= primDCs.length ? primDCs.length - 1 : i] : DOCK_DIM_CONSTRAINT;
            ArrayList<LinkedDimGroup> groupList = new ArrayList<LinkedDimGroup>(4);
            groupLists[gIx++] = groupList;
            for (Integer ix : secIndexes) {
                boolean isPar;
                int span;
                Cell cell = isRows ? this.getCell(i, ix) : this.getCell(ix, i);
                if (cell == null || cell.compWraps.size() == 0) continue;
                int n = span = isRows ? cell.spany : cell.spanx;
                if (span > 1) {
                    span = Grid.convertSpanToSparseGrid(i, span, primIndexes);
                }
                boolean bl = isPar = cell.flowx == isRows;
                if (!isPar && cell.compWraps.size() > 1 || span > 1) {
                    int linkType = isPar ? 1 : 0;
                    LinkedDimGroup lg = new LinkedDimGroup("p," + ix, span, linkType, !isRows, fromEnd);
                    lg.setCompWraps(cell.compWraps);
                    groupList.add(lg);
                    continue;
                }
                for (int cwIx = 0; cwIx < cell.compWraps.size(); ++cwIx) {
                    CompWrap cw = (CompWrap)cell.compWraps.get(cwIx);
                    boolean rowBaselineAlign = isRows && this.lc.isTopToBottom() && dc.getAlignOrDefault(!isRows) == UnitValue.BASELINE_IDENTITY;
                    boolean isBaseline = isRows && cw.isBaselineAlign(rowBaselineAlign);
                    String linkCtx = isBaseline ? "baseline" : null;
                    boolean foundList = false;
                    int lastGl = groupList.size() - 1;
                    for (int glIx = 0; glIx <= lastGl; ++glIx) {
                        LinkedDimGroup group = (LinkedDimGroup)groupList.get(glIx);
                        if (group.linkCtx != linkCtx && (linkCtx == null || !linkCtx.equals(group.linkCtx))) continue;
                        group.addCompWrap(cw);
                        foundList = true;
                        break;
                    }
                    if (foundList) continue;
                    int linkType = isBaseline ? 2 : 1;
                    LinkedDimGroup lg = new LinkedDimGroup(linkCtx, 1, linkType, !isRows, fromEnd);
                    lg.addCompWrap(cw);
                    groupList.add(lg);
                }
            }
        }
        return groupLists;
    }

    private static int convertSpanToSparseGrid(int curIx, int span, TreeSet<Integer> indexes) {
        int lastIx = curIx + span;
        int retSpan = 1;
        for (Integer ix : indexes) {
            if (ix <= curIx) continue;
            if (ix >= lastIx) break;
            ++retSpan;
        }
        return retSpan;
    }

    private boolean isCellFree(int r, int c, ArrayList<int[]> occupiedRects) {
        if (this.getCell(r, c) != null) {
            return false;
        }
        for (int[] rect : occupiedRects) {
            if (rect[0] > c || rect[1] > r || rect[0] + rect[2] <= c || rect[1] + rect[3] <= r) continue;
            return false;
        }
        return true;
    }

    private Cell getCell(int r, int c) {
        return this.grid.get((r << 16) + (c & 0xFFFF));
    }

    private void setCell(int r, int c, Cell cell) {
        if (c < 0 || r < 0) {
            throw new IllegalArgumentException("Cell position cannot be negative. row: " + r + ", col: " + c);
        }
        if (c > 30000 || r > 30000) {
            throw new IllegalArgumentException("Cell position out of bounds. Out of cells. row: " + r + ", col: " + c);
        }
        this.rowIndexes.add(r);
        this.colIndexes.add(c);
        this.grid.put((r << 16) + (c & 0xFFFF), cell);
    }

    private void addDockingCell(int[] dockInsets, int side, CompWrap cw) {
        int c;
        int r;
        int spanx = 1;
        int spany = 1;
        switch (side) {
            case 0: 
            case 2: {
                int n;
                if (side == 0) {
                    int n2 = dockInsets[0];
                    n = n2;
                    dockInsets[0] = n2 + 1;
                } else {
                    int n3 = dockInsets[2];
                    n = n3;
                    dockInsets[2] = n3 - 1;
                }
                r = n;
                c = dockInsets[1];
                spanx = dockInsets[3] - dockInsets[1] + 1;
                this.colIndexes.add(dockInsets[3]);
                break;
            }
            case 1: 
            case 3: {
                int n;
                if (side == 1) {
                    int n4 = dockInsets[1];
                    n = n4;
                    dockInsets[1] = n4 + 1;
                } else {
                    int n5 = dockInsets[3];
                    n = n5;
                    dockInsets[3] = n5 - 1;
                }
                c = n;
                r = dockInsets[0];
                spany = dockInsets[2] - dockInsets[0] + 1;
                this.rowIndexes.add(dockInsets[2]);
                break;
            }
            default: {
                throw new IllegalArgumentException("Internal error 123.");
            }
        }
        this.rowIndexes.add(r);
        this.colIndexes.add(c);
        this.grid.put((r << 16) + (c & 0xFFFF), new Cell(cw, spanx, spany, spanx > 1));
    }

    private static void layoutBaseline(ContainerWrapper parent, ArrayList<CompWrap> compWraps, DimConstraint dc, int start, int size, int sizeType, int spanCount) {
        AboveBelow aboveBelow = Grid.getBaselineAboveBelow(compWraps, sizeType, true);
        int blRowSize = aboveBelow.sum();
        CC cc = compWraps.get(0).cc;
        UnitValue align = cc.getVertical().getAlign();
        if (spanCount == 1 && align == null) {
            align = dc.getAlignOrDefault(false);
        }
        if (align == UnitValue.BASELINE_IDENTITY) {
            align = UnitValue.CENTER;
        }
        int offset = start + aboveBelow.maxAbove + (align != null ? Math.max(0, align.getPixels(size - blRowSize, parent, null)) : 0);
        Iterator<CompWrap> iterator = compWraps.iterator();
        while (iterator.hasNext()) {
            CompWrap cw;
            CompWrap compWrap = cw = iterator.next();
            compWrap.y = compWrap.y + offset;
            if (cw.y + cw.h <= start + size) continue;
            cw.h = start + size - cw.y;
        }
    }

    private static void layoutSerial(ContainerWrapper parent, ArrayList<CompWrap> compWraps, DimConstraint dc, int start, int size, boolean isHor, int spanCount, boolean fromEnd) {
        FlowSizeSpec fss = Grid.mergeSizesGapsAndResConstrs(Grid.getComponentResizeConstraints(compWraps, isHor), Grid.getComponentGapPush(compWraps, isHor), Grid.getComponentSizes(compWraps, isHor), Grid.getGaps(compWraps, isHor));
        Float[] pushW = dc.isFill() ? GROW_100 : null;
        int[] sizes = LayoutUtil.calculateSerial(fss.sizes, fss.resConstsInclGaps, pushW, 1, size);
        Grid.setCompWrapBounds(parent, sizes, compWraps, dc.getAlignOrDefault(isHor), start, size, isHor, fromEnd);
    }

    private static void setCompWrapBounds(ContainerWrapper parent, int[] allSizes, ArrayList<CompWrap> compWraps, UnitValue rowAlign, int start, int size, boolean isHor, boolean fromEnd) {
        int totSize = LayoutUtil.sum(allSizes);
        CC cc = compWraps.get(0).cc;
        UnitValue align = Grid.correctAlign(cc, rowAlign, isHor, fromEnd);
        int cSt = start;
        int slack = size - totSize;
        if (slack > 0 && align != null) {
            int al = Math.min(slack, Math.max(0, align.getPixels(slack, parent, null)));
            cSt += fromEnd ? -al : al;
        }
        int bIx = 0;
        int iSz = compWraps.size();
        for (int i = 0; i < iSz; ++i) {
            CompWrap cw = compWraps.get(i);
            if (fromEnd) {
                cw.setDimBounds((cSt -= allSizes[bIx++]) - allSizes[bIx], allSizes[bIx], isHor);
                cSt -= allSizes[bIx++];
                continue;
            }
            cw.setDimBounds(cSt += allSizes[bIx++], allSizes[bIx], isHor);
            cSt += allSizes[bIx++];
        }
    }

    private static void layoutParallel(ContainerWrapper parent, ArrayList<CompWrap> compWraps, DimConstraint dc, int start, int size, boolean isHor, boolean fromEnd) {
        int[][] sizes = new int[compWraps.size()][];
        for (int i = 0; i < sizes.length; ++i) {
            CompWrap cw = compWraps.get(i);
            DimConstraint cDc = cw.cc.getDimConstraint(isHor);
            ResizeConstraint[] resConstr = new ResizeConstraint[]{cw.isPushGap(isHor, true) ? GAP_RC_CONST_PUSH : GAP_RC_CONST, cDc.resize, cw.isPushGap(isHor, false) ? GAP_RC_CONST_PUSH : GAP_RC_CONST};
            int[][] sz = new int[][]{cw.getGaps(isHor, true), cw.getSizes(isHor), cw.getGaps(isHor, false)};
            Float[] pushW = dc.isFill() ? GROW_100 : null;
            sizes[i] = LayoutUtil.calculateSerial(sz, resConstr, pushW, 1, size);
        }
        UnitValue rowAlign = dc.getAlignOrDefault(isHor);
        Grid.setCompWrapBounds(parent, sizes, compWraps, rowAlign, start, size, isHor, fromEnd);
    }

    private static void setCompWrapBounds(ContainerWrapper parent, int[][] sizes, ArrayList<CompWrap> compWraps, UnitValue rowAlign, int start, int size, boolean isHor, boolean fromEnd) {
        for (int i = 0; i < sizes.length; ++i) {
            CompWrap cw = compWraps.get(i);
            UnitValue align = Grid.correctAlign(cw.cc, rowAlign, isHor, fromEnd);
            int[] cSizes = sizes[i];
            int gapBef = cSizes[0];
            int cSize = cSizes[1];
            int gapAft = cSizes[2];
            int cSt = fromEnd ? start - gapBef : start + gapBef;
            int slack = size - cSize - gapBef - gapAft;
            if (slack > 0 && align != null) {
                int al = Math.min(slack, Math.max(0, align.getPixels(slack, parent, null)));
                cSt += fromEnd ? -al : al;
            }
            cw.setDimBounds(fromEnd ? cSt - cSize : cSt, cSize, isHor);
        }
    }

    private static UnitValue correctAlign(CC cc, UnitValue rowAlign, boolean isHor, boolean fromEnd) {
        UnitValue align = (isHor ? cc.getHorizontal() : cc.getVertical()).getAlign();
        if (align == null) {
            align = rowAlign;
        }
        if (align == UnitValue.BASELINE_IDENTITY) {
            align = UnitValue.CENTER;
        }
        if (fromEnd) {
            if (align == UnitValue.LEFT) {
                align = UnitValue.RIGHT;
            } else if (align == UnitValue.RIGHT) {
                align = UnitValue.LEFT;
            }
        }
        return align;
    }

    private static AboveBelow getBaselineAboveBelow(ArrayList<CompWrap> compWraps, int sType, boolean centerBaseline) {
        int maxAbove = Integer.MIN_VALUE;
        int maxBelow = Integer.MIN_VALUE;
        for (CompWrap cw : compWraps) {
            int height = cw.getSize(sType, false);
            if (height >= 2097051) {
                return new AboveBelow(1048525, 1048525);
            }
            int baseline = cw.getBaseline(sType);
            int above = baseline + cw.getGapBefore(sType, false);
            maxAbove = Math.max(above, maxAbove);
            maxBelow = Math.max(height - baseline + cw.getGapAfter(sType, false), maxBelow);
            if (!centerBaseline) continue;
            cw.setDimBounds(-baseline, height, false);
        }
        return new AboveBelow(maxAbove, maxBelow);
    }

    private static int getTotalSizeParallel(ArrayList<CompWrap> compWraps, int sType, boolean isHor) {
        int size = sType == 2 ? 2097051 : 0;
        for (CompWrap cw : compWraps) {
            int cwSize = cw.getSizeInclGaps(sType, isHor);
            if (cwSize >= 2097051) {
                return 2097051;
            }
            if (!(sType == 2 ? cwSize < size : cwSize > size)) continue;
            size = cwSize;
        }
        return Grid.constrainSize(size);
    }

    private static int getTotalSizeSerial(ArrayList<CompWrap> compWraps, int sType, boolean isHor) {
        int totSize = 0;
        int iSz = compWraps.size();
        int lastGapAfter = 0;
        for (int i = 0; i < iSz; ++i) {
            CompWrap wrap = compWraps.get(i);
            int gapBef = wrap.getGapBefore(sType, isHor);
            if (gapBef > lastGapAfter) {
                totSize += gapBef - lastGapAfter;
            }
            totSize += wrap.getSize(sType, isHor);
            lastGapAfter = wrap.getGapAfter(sType, isHor);
            if ((totSize += lastGapAfter) < 2097051) continue;
            return 2097051;
        }
        return Grid.constrainSize(totSize);
    }

    private static int getTotalGroupsSizeParallel(ArrayList<LinkedDimGroup> groups, int sType, boolean countSpanning) {
        int size = sType == 2 ? 2097051 : 0;
        for (LinkedDimGroup group : groups) {
            if (!countSpanning && group.span != 1) continue;
            int grpSize = group.getMinPrefMax()[sType];
            if (grpSize >= 2097051) {
                return 2097051;
            }
            if (!(sType == 2 ? grpSize < size : grpSize > size)) continue;
            size = grpSize;
        }
        return Grid.constrainSize(size);
    }

    private static int[][] getComponentSizes(ArrayList<CompWrap> compWraps, boolean isHor) {
        int[][] compSizes = new int[compWraps.size()][];
        for (int i = 0; i < compSizes.length; ++i) {
            compSizes[i] = compWraps.get(i).getSizes(isHor);
        }
        return compSizes;
    }

    private static FlowSizeSpec mergeSizesGapsAndResConstrs(ResizeConstraint[] resConstr, boolean[] gapPush, int[][] minPrefMaxSizes, int[][] gapSizes) {
        int[][] sizes = new int[(minPrefMaxSizes.length << 1) + 1][];
        ResizeConstraint[] resConstsInclGaps = new ResizeConstraint[sizes.length];
        sizes[0] = gapSizes[0];
        int i = 0;
        int crIx = 1;
        while (i < minPrefMaxSizes.length) {
            resConstsInclGaps[crIx] = resConstr[i];
            sizes[crIx] = minPrefMaxSizes[i];
            sizes[crIx + 1] = gapSizes[i + 1];
            if (sizes[crIx - 1] != null) {
                ResizeConstraint resizeConstraint = resConstsInclGaps[crIx - 1] = gapPush[i < gapPush.length ? i : gapPush.length - 1] ? GAP_RC_CONST_PUSH : GAP_RC_CONST;
            }
            if (i == minPrefMaxSizes.length - 1 && sizes[crIx + 1] != null) {
                resConstsInclGaps[crIx + 1] = gapPush[i + 1 < gapPush.length ? i + 1 : gapPush.length - 1] ? GAP_RC_CONST_PUSH : GAP_RC_CONST;
            }
            ++i;
            crIx += 2;
        }
        for (i = 0; i < sizes.length; ++i) {
            if (sizes[i] != null) continue;
            sizes[i] = new int[3];
        }
        return new FlowSizeSpec(sizes, resConstsInclGaps);
    }

    private static int[] mergeSizes(int[] oldValues, int[] newValues) {
        if (oldValues == null) {
            return newValues;
        }
        if (newValues == null) {
            return oldValues;
        }
        int[] ret = new int[oldValues.length];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = Grid.mergeSizes(oldValues[i], newValues[i], true);
        }
        return ret;
    }

    private static int mergeSizes(int oldValue, int newValue, boolean toMax) {
        if (oldValue == -2147471302 || oldValue == newValue) {
            return newValue;
        }
        if (newValue == -2147471302) {
            return oldValue;
        }
        return toMax != oldValue > newValue ? newValue : oldValue;
    }

    private static int constrainSize(int s) {
        return s > 0 ? (s < 2097051 ? s : 2097051) : 0;
    }

    private static void correctMinMax(int[] s) {
        if (s[0] > s[2]) {
            s[0] = s[2];
        }
        if (s[1] < s[0]) {
            s[1] = s[0];
        }
        if (s[1] > s[2]) {
            s[1] = s[2];
        }
    }

    private static Float[] extractSubArray(DimConstraint[] specs, Float[] arr, int ix, int len) {
        if (arr == null || arr.length < ix + len) {
            Float[] growLastArr = new Float[len];
            for (int i = ix + len - 1; i >= 0; i -= 2) {
                int specIx = i >> 1;
                if (specs[specIx] == DOCK_DIM_CONSTRAINT) continue;
                growLastArr[i - ix] = ResizeConstraint.WEIGHT_100;
                return growLastArr;
            }
            return growLastArr;
        }
        Float[] newArr = new Float[len];
        System.arraycopy(arr, ix, newArr, 0, len);
        return newArr;
    }

    private static synchronized void putSizesAndIndexes(Object parComp, int[] sizes, int[] ixArr, boolean isRows) {
        if (PARENT_ROWCOL_SIZES_MAP == null) {
            PARENT_ROWCOL_SIZES_MAP = new WeakHashMap[]{new WeakHashMap(4), new WeakHashMap(4)};
        }
        PARENT_ROWCOL_SIZES_MAP[isRows ? 0 : 1].put(parComp, new int[][]{ixArr, sizes});
    }

    static synchronized int[][] getSizesAndIndexes(Object parComp, boolean isRows) {
        if (PARENT_ROWCOL_SIZES_MAP == null) {
            return null;
        }
        return PARENT_ROWCOL_SIZES_MAP[isRows ? 0 : 1].get(parComp);
    }

    private static synchronized void saveGrid(ComponentWrapper parComp, LinkedHashMap<Integer, Cell> grid) {
        if (PARENT_GRIDPOS_MAP == null) {
            PARENT_GRIDPOS_MAP = new WeakHashMap(4);
        }
        ArrayList<WeakCell> weakCells = new ArrayList<WeakCell>(grid.size());
        for (Map.Entry<Integer, Cell> e : grid.entrySet()) {
            Cell cell = e.getValue();
            Integer xyInt = e.getKey();
            if (xyInt == null) continue;
            int x = xyInt << 16 >> 16;
            int y = xyInt >> 16;
            for (CompWrap cw : cell.compWraps) {
                weakCells.add(new WeakCell(cw.comp.getComponent(), x, y, cell.spanx, cell.spany));
            }
        }
        PARENT_GRIDPOS_MAP.put(parComp.getComponent(), weakCells);
    }

    static synchronized HashMap<Object, int[]> getGridPositions(Object parComp) {
        ArrayList<WeakCell> weakCells;
        ArrayList<WeakCell> arrayList = weakCells = PARENT_GRIDPOS_MAP != null ? PARENT_GRIDPOS_MAP.get(parComp) : null;
        if (weakCells == null) {
            return null;
        }
        HashMap<Object, int[]> retMap = new HashMap<Object, int[]>();
        for (WeakCell wc : weakCells) {
            Object component = wc.componentRef.get();
            if (component == null) continue;
            retMap.put(component, new int[]{wc.x, wc.y, wc.spanX, wc.spanY});
        }
        return retMap;
    }

    static {
        DOCK_DIM_CONSTRAINT.setGrowPriority(0);
        GAP_RC_CONST = new ResizeConstraint(200, ResizeConstraint.WEIGHT_100, 50, null);
        GAP_RC_CONST_PUSH = new ResizeConstraint(200, ResizeConstraint.WEIGHT_100, 50, ResizeConstraint.WEIGHT_100);
        DEF_CC = new CC();
        PARENT_ROWCOL_SIZES_MAP = null;
        PARENT_GRIDPOS_MAP = null;
    }

    private static class WeakCell {
        private final WeakReference<Object> componentRef;
        private final int x;
        private final int y;
        private final int spanX;
        private final int spanY;

        private WeakCell(Object component, int x, int y, int spanX, int spanY) {
            this.componentRef = new WeakReference<Object>(component);
            this.x = x;
            this.y = y;
            this.spanX = spanX;
            this.spanY = spanY;
        }
    }

    private static final class FlowSizeSpec {
        private final int[][] sizes;
        private final ResizeConstraint[] resConstsInclGaps;

        private FlowSizeSpec(int[][] sizes, ResizeConstraint[] resConstsInclGaps) {
            this.sizes = sizes;
            this.resConstsInclGaps = resConstsInclGaps;
        }

        private int expandSizes(DimConstraint[] specs, Float[] defGrow, int targetSize, int fromIx, int len, int sizeType, int eagerness) {
            ResizeConstraint[] resConstr = new ResizeConstraint[len];
            int[][] sizesToExpand = new int[len][];
            for (int i = 0; i < len; ++i) {
                int[] minPrefMax = this.sizes[i + fromIx];
                sizesToExpand[i] = new int[]{minPrefMax[sizeType], minPrefMax[1], minPrefMax[2]};
                if (eagerness <= 1 && i % 2 == 0) {
                    int cIx = i + fromIx - 1 >> 1;
                    DimConstraint spec = (DimConstraint)LayoutUtil.getIndexSafe(specs, cIx);
                    BoundSize sz = spec.getSize();
                    if (sizeType == 0 && sz.getMin() != null && sz.getMin().getUnit() != 13 || sizeType == 1 && sz.getPreferred() != null && sz.getPreferred().getUnit() != 14) continue;
                }
                resConstr[i] = (ResizeConstraint)LayoutUtil.getIndexSafe(this.resConstsInclGaps, i + fromIx);
            }
            Float[] growW = eagerness == 1 || eagerness == 3 ? Grid.extractSubArray(specs, defGrow, fromIx, len) : null;
            int[] newSizes = LayoutUtil.calculateSerial(sizesToExpand, resConstr, growW, 1, targetSize);
            int newSize = 0;
            for (int i = 0; i < len; ++i) {
                int s;
                this.sizes[i + fromIx][sizeType] = s = newSizes[i];
                newSize += s;
            }
            return newSize;
        }
    }

    private static class AboveBelow {
        int maxAbove;
        int maxBelow;

        AboveBelow(int maxAbove, int maxBelow) {
            this.maxAbove = maxAbove;
            this.maxBelow = maxBelow;
        }

        int sum() {
            return this.maxAbove + this.maxBelow;
        }
    }

    private final class CompWrap {
        private final ComponentWrapper comp;
        private final CC cc;
        private final int eHideMode;
        private final boolean useVisualPadding;
        private boolean sizesOk = false;
        private boolean isAbsolute;
        private int[][] gaps;
        private final int[] horSizes = new int[3];
        private final int[] verSizes = new int[3];
        private int x = -2147471302;
        private int y = -2147471302;
        private int w = -2147471302;
        private int h = -2147471302;
        private int forcedPushGaps = 0;

        private CompWrap(ComponentWrapper c, CC cc, int eHideMode, boolean useVisualPadding) {
            this.comp = c;
            this.cc = cc;
            this.eHideMode = eHideMode;
            this.useVisualPadding = useVisualPadding;
            boolean bl = this.isAbsolute = cc.getHorizontal().getSize().isAbsolute() && cc.getVertical().getSize().isAbsolute();
            if (eHideMode > 1) {
                this.gaps = new int[4][];
                for (int i = 0; i < this.gaps.length; ++i) {
                    this.gaps[i] = new int[3];
                }
            }
        }

        private int[] getSizes(boolean isHor) {
            this.validateSize();
            return isHor ? this.horSizes : this.verSizes;
        }

        private void validateSize() {
            BoundSize[] callbackSz = Grid.this.getCallbackSize(this.comp);
            if (this.isAbsolute && this.sizesOk && callbackSz == null) {
                return;
            }
            if (this.eHideMode <= 0) {
                int contentBias = this.comp.getContentBias();
                int sizeHint = contentBias == -1 ? -1 : (contentBias == 0 ? (this.w != -2147471302 ? this.w : this.comp.getWidth()) : (this.h != -2147471302 ? this.h : this.comp.getHeight()));
                BoundSize hBS = callbackSz != null && callbackSz[0] != null ? callbackSz[0] : this.cc.getHorizontal().getSize();
                BoundSize vBS = callbackSz != null && callbackSz[1] != null ? callbackSz[1] : this.cc.getVertical().getSize();
                block4: for (int i = 0; i <= 2; ++i) {
                    switch (contentBias) {
                        default: {
                            this.horSizes[i] = this.getSize(hBS, i, true, this.useVisualPadding, -1);
                            this.verSizes[i] = this.getSize(vBS, i, false, this.useVisualPadding, -1);
                            continue block4;
                        }
                        case 0: {
                            this.horSizes[i] = this.getSize(hBS, i, true, this.useVisualPadding, -1);
                            this.verSizes[i] = this.getSize(vBS, i, false, this.useVisualPadding, sizeHint > 0 ? sizeHint : this.horSizes[i]);
                            continue block4;
                        }
                        case 1: {
                            this.verSizes[i] = this.getSize(vBS, i, false, this.useVisualPadding, -1);
                            this.horSizes[i] = this.getSize(hBS, i, true, this.useVisualPadding, sizeHint > 0 ? sizeHint : this.verSizes[i]);
                        }
                    }
                }
                Grid.correctMinMax(this.horSizes);
                Grid.correctMinMax(this.verSizes);
            } else {
                Arrays.fill(this.horSizes, 0);
                Arrays.fill(this.verSizes, 0);
            }
            this.sizesOk = true;
        }

        private int getSize(BoundSize uvs, int sizeType, boolean isHor, boolean useVP, int sizeHint) {
            int size;
            if (uvs == null || uvs.getSize(sizeType) == null) {
                int[] visualPadding;
                switch (sizeType) {
                    case 0: {
                        size = isHor ? this.comp.getMinimumWidth(sizeHint) : this.comp.getMinimumHeight(sizeHint);
                        break;
                    }
                    case 1: {
                        size = isHor ? this.comp.getPreferredWidth(sizeHint) : this.comp.getPreferredHeight(sizeHint);
                        break;
                    }
                    default: {
                        int n = size = isHor ? this.comp.getMaximumWidth(sizeHint) : this.comp.getMaximumHeight(sizeHint);
                    }
                }
                if (useVP && (visualPadding = this.comp.getVisualPadding()) != null && visualPadding.length > 0) {
                    size -= isHor ? visualPadding[1] + visualPadding[3] : visualPadding[0] + visualPadding[2];
                }
            } else {
                ContainerWrapper par = this.comp.getParent();
                float refValue = isHor ? (float)par.getWidth() : (float)par.getHeight();
                size = uvs.getSize(sizeType).getPixels(refValue, par, this.comp);
            }
            return size;
        }

        private void calcGaps(ComponentWrapper before, CC befCC, ComponentWrapper after, CC aftCC, String tag, boolean flowX, boolean isLTR) {
            BoundSize befGap;
            ContainerWrapper par = this.comp.getParent();
            int parW = par.getWidth();
            int parH = par.getHeight();
            BoundSize boundSize = before != null ? (flowX ? befCC.getHorizontal() : befCC.getVertical()).getGapAfter() : (befGap = null);
            BoundSize aftGap = after != null ? (flowX ? aftCC.getHorizontal() : aftCC.getVertical()).getGapBefore() : null;
            this.mergeGapSizes(this.cc.getVertical().getComponentGaps(par, this.comp, befGap, flowX ? null : before, tag, parH, 0, isLTR), false, true);
            this.mergeGapSizes(this.cc.getHorizontal().getComponentGaps(par, this.comp, befGap, flowX ? before : null, tag, parW, 1, isLTR), true, true);
            this.mergeGapSizes(this.cc.getVertical().getComponentGaps(par, this.comp, aftGap, flowX ? null : after, tag, parH, 2, isLTR), false, false);
            this.mergeGapSizes(this.cc.getHorizontal().getComponentGaps(par, this.comp, aftGap, flowX ? after : null, tag, parW, 3, isLTR), true, false);
        }

        private void setDimBounds(int start, int size, boolean isHor) {
            if (isHor) {
                if (start != this.x || this.w != size) {
                    this.x = start;
                    this.w = size;
                    if (this.comp.getContentBias() == 0) {
                        this.invalidateSizes();
                    }
                }
            } else if (start != this.y || this.h != size) {
                this.y = start;
                this.h = size;
                if (this.comp.getContentBias() == 1) {
                    this.invalidateSizes();
                }
            }
        }

        void invalidateSizes() {
            this.sizesOk = false;
        }

        private boolean isPushGap(boolean isHor, boolean isBefore) {
            if (isHor && ((isBefore ? 1 : 2) & this.forcedPushGaps) != 0) {
                return true;
            }
            DimConstraint dc = this.cc.getDimConstraint(isHor);
            BoundSize s = isBefore ? dc.getGapBefore() : dc.getGapAfter();
            return s != null && s.getGapPush();
        }

        private void transferBounds(boolean addVisualPadding) {
            int[] visualPadding;
            if (this.cc.isExternal()) {
                return;
            }
            int compX = this.x;
            int compY = this.y;
            int compW = this.w;
            int compH = this.h;
            if (addVisualPadding && (visualPadding = this.comp.getVisualPadding()) != null) {
                compX -= visualPadding[1];
                compY -= visualPadding[0];
                compW += visualPadding[1] + visualPadding[3];
                compH += visualPadding[0] + visualPadding[2];
            }
            this.comp.setBounds(compX, compY, compW, compH);
        }

        private void setForcedSizes(int[] sizes, boolean isHor) {
            if (sizes == null) {
                return;
            }
            System.arraycopy(sizes, 0, this.getSizes(isHor), 0, 3);
            this.sizesOk = true;
        }

        private void setGaps(int[] minPrefMax, int ix) {
            if (this.gaps == null) {
                this.gaps = new int[][]{null, null, null, null};
            }
            this.gaps[ix] = minPrefMax;
        }

        private void mergeGapSizes(int[] sizes, boolean isHor, boolean isTL) {
            if (this.gaps == null) {
                this.gaps = new int[][]{null, null, null, null};
            }
            if (sizes == null) {
                return;
            }
            int gapIX = this.getGapIx(isHor, isTL);
            int[] oldGaps = this.gaps[gapIX];
            if (oldGaps == null) {
                oldGaps = new int[]{0, 0, 2097051};
                this.gaps[gapIX] = oldGaps;
            }
            oldGaps[0] = Math.max(sizes[0], oldGaps[0]);
            oldGaps[1] = Math.max(sizes[1], oldGaps[1]);
            oldGaps[2] = Math.min(sizes[2], oldGaps[2]);
        }

        private int getGapIx(boolean isHor, boolean isTL) {
            return isHor ? (isTL ? 1 : 3) : (isTL ? 0 : 2);
        }

        private int getSizeInclGaps(int sizeType, boolean isHor) {
            return this.filter(sizeType, this.getGapBefore(sizeType, isHor) + this.getSize(sizeType, isHor) + this.getGapAfter(sizeType, isHor));
        }

        private int getSize(int sizeType, boolean isHor) {
            return this.filter(sizeType, this.getSizes(isHor)[sizeType]);
        }

        private int getGapBefore(int sizeType, boolean isHor) {
            int[] gaps = this.getGaps(isHor, true);
            return gaps != null ? this.filter(sizeType, gaps[sizeType]) : 0;
        }

        private int getGapAfter(int sizeType, boolean isHor) {
            int[] gaps = this.getGaps(isHor, false);
            return gaps != null ? this.filter(sizeType, gaps[sizeType]) : 0;
        }

        private int[] getGaps(boolean isHor, boolean isTL) {
            return this.gaps[this.getGapIx(isHor, isTL)];
        }

        private int filter(int sizeType, int size) {
            if (size == -2147471302) {
                return sizeType != 2 ? 0 : 2097051;
            }
            return Grid.constrainSize(size);
        }

        private boolean isBaselineAlign(boolean defValue) {
            Float g = this.cc.getVertical().getGrow();
            if (g != null && g.intValue() != 0) {
                return false;
            }
            UnitValue al = this.cc.getVertical().getAlign();
            return (al != null ? al == UnitValue.BASELINE_IDENTITY : defValue) && this.comp.hasBaseline();
        }

        private int getBaseline(int sizeType) {
            return this.comp.getBaseline(this.getSize(sizeType, true), this.getSize(sizeType, false));
        }

        void adjustMinHorSizeUp(int minSize) {
            int[] sz = this.getSizes(true);
            if (sz[0] < minSize) {
                sz[0] = minSize;
            }
            Grid.correctMinMax(sz);
        }
    }

    private static class LinkedDimGroup {
        private static final int TYPE_SERIAL = 0;
        private static final int TYPE_PARALLEL = 1;
        private static final int TYPE_BASELINE = 2;
        private final String linkCtx;
        private final int span;
        private final int linkType;
        private final boolean isHor;
        private final boolean fromEnd;
        private final ArrayList<CompWrap> _compWraps = new ArrayList(4);
        private int lStart = 0;
        private int lSize = 0;

        private LinkedDimGroup(String linkCtx, int span, int linkType, boolean isHor, boolean fromEnd) {
            this.linkCtx = linkCtx;
            this.span = span;
            this.linkType = linkType;
            this.isHor = isHor;
            this.fromEnd = fromEnd;
        }

        private void addCompWrap(CompWrap cw) {
            this._compWraps.add(cw);
        }

        private void setCompWraps(ArrayList<CompWrap> cws) {
            if (this._compWraps != cws) {
                this._compWraps.clear();
                this._compWraps.addAll(cws);
            }
        }

        private void layout(DimConstraint dc, int start, int size, int spanCount) {
            this.lStart = start;
            this.lSize = size;
            if (this._compWraps.isEmpty()) {
                return;
            }
            ContainerWrapper parent = this._compWraps.get(0).comp.getParent();
            if (this.linkType == 1) {
                Grid.layoutParallel(parent, this._compWraps, dc, start, size, this.isHor, this.fromEnd);
            } else if (this.linkType == 2) {
                Grid.layoutBaseline(parent, this._compWraps, dc, start, size, 1, spanCount);
            } else {
                Grid.layoutSerial(parent, this._compWraps, dc, start, size, this.isHor, spanCount, this.fromEnd);
            }
        }

        private int[] getMinPrefMax() {
            int[] sizes = new int[3];
            if (!this._compWraps.isEmpty()) {
                for (int sType = 0; sType <= 1; ++sType) {
                    if (this.linkType == 1) {
                        sizes[sType] = Grid.getTotalSizeParallel(this._compWraps, sType, this.isHor);
                        continue;
                    }
                    if (this.linkType == 2) {
                        AboveBelow aboveBelow = Grid.getBaselineAboveBelow(this._compWraps, sType, false);
                        sizes[sType] = aboveBelow.sum();
                        continue;
                    }
                    sizes[sType] = Grid.getTotalSizeSerial(this._compWraps, sType, this.isHor);
                }
                sizes[2] = 2097051;
            }
            return sizes;
        }
    }

    private static class Cell {
        private final int spanx;
        private final int spany;
        private final boolean flowx;
        private final ArrayList<CompWrap> compWraps = new ArrayList(2);
        private boolean hasTagged = false;

        private Cell(CompWrap cw) {
            this(cw, 1, 1, true);
        }

        private Cell(int spanx, int spany, boolean flowx) {
            this(null, spanx, spany, flowx);
        }

        private Cell(CompWrap cw, int spanx, int spany, boolean flowx) {
            if (cw != null) {
                this.compWraps.add(cw);
            }
            this.spanx = spanx;
            this.spany = spany;
            this.flowx = flowx;
        }
    }
}

