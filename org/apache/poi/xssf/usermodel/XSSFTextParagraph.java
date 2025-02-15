/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.model.ParagraphPropertyFetcher;
import org.apache.poi.xssf.usermodel.ListAutoNumber;
import org.apache.poi.xssf.usermodel.TextAlign;
import org.apache.poi.xssf.usermodel.TextFontAlign;
import org.apache.poi.xssf.usermodel.XSSFLineBreak;
import org.apache.poi.xssf.usermodel.XSSFTextRun;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public class XSSFTextParagraph
implements Iterable<XSSFTextRun> {
    private final CTTextParagraph _p;
    private final CTShape _shape;
    private final List<XSSFTextRun> _runs;

    XSSFTextParagraph(CTTextParagraph p, CTShape ctShape) {
        this._p = p;
        this._shape = ctShape;
        this._runs = new ArrayList<XSSFTextRun>();
        for (XmlObject ch : this._p.selectPath("*")) {
            CTRegularTextRun r;
            if (ch instanceof CTRegularTextRun) {
                CTRegularTextRun r2 = (CTRegularTextRun)ch;
                this._runs.add(new XSSFTextRun(r2, this));
                continue;
            }
            if (ch instanceof CTTextLineBreak) {
                CTTextLineBreak br = (CTTextLineBreak)ch;
                r = CTRegularTextRun.Factory.newInstance();
                r.setRPr(br.getRPr());
                r.setT("\n");
                this._runs.add(new XSSFTextRun(r, this));
                continue;
            }
            if (!(ch instanceof CTTextField)) continue;
            CTTextField f = (CTTextField)ch;
            r = CTRegularTextRun.Factory.newInstance();
            r.setRPr(f.getRPr());
            r.setT(f.getT());
            this._runs.add(new XSSFTextRun(r, this));
        }
    }

    public String getText() {
        StringBuilder out = new StringBuilder();
        for (XSSFTextRun r : this._runs) {
            out.append(r.getText());
        }
        return out.toString();
    }

    @Internal
    public CTTextParagraph getXmlObject() {
        return this._p;
    }

    @Internal
    public CTShape getParentShape() {
        return this._shape;
    }

    public List<XSSFTextRun> getTextRuns() {
        return this._runs;
    }

    @Override
    public Iterator<XSSFTextRun> iterator() {
        return this._runs.iterator();
    }

    public XSSFTextRun addNewTextRun() {
        CTRegularTextRun r = this._p.addNewR();
        CTTextCharacterProperties rPr = r.addNewRPr();
        rPr.setLang("en-US");
        XSSFTextRun run = new XSSFTextRun(r, this);
        this._runs.add(run);
        return run;
    }

    public XSSFTextRun addLineBreak() {
        CTTextLineBreak br = this._p.addNewBr();
        CTTextCharacterProperties brProps = br.addNewRPr();
        if (this._runs.size() > 0) {
            CTTextCharacterProperties prevRun = this._runs.get(this._runs.size() - 1).getRPr();
            brProps.set(prevRun);
        }
        CTRegularTextRun r = CTRegularTextRun.Factory.newInstance();
        r.setRPr(brProps);
        r.setT("\n");
        XSSFLineBreak run = new XSSFLineBreak(r, this, brProps);
        this._runs.add(run);
        return run;
    }

    public TextAlign getTextAlign() {
        ParagraphPropertyFetcher<TextAlign> fetcher = new ParagraphPropertyFetcher<TextAlign>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetAlgn()) {
                    TextAlign val = TextAlign.values()[props.getAlgn().intValue() - 1];
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? TextAlign.LEFT : (TextAlign)((Object)fetcher.getValue());
    }

    public void setTextAlign(TextAlign align) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (align == null) {
            if (pr.isSetAlgn()) {
                pr.unsetAlgn();
            }
        } else {
            pr.setAlgn(STTextAlignType.Enum.forInt(align.ordinal() + 1));
        }
    }

    public TextFontAlign getTextFontAlign() {
        ParagraphPropertyFetcher<TextFontAlign> fetcher = new ParagraphPropertyFetcher<TextFontAlign>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetFontAlgn()) {
                    TextFontAlign val = TextFontAlign.values()[props.getFontAlgn().intValue() - 1];
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? TextFontAlign.BASELINE : (TextFontAlign)((Object)fetcher.getValue());
    }

    public void setTextFontAlign(TextFontAlign align) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (align == null) {
            if (pr.isSetFontAlgn()) {
                pr.unsetFontAlgn();
            }
        } else {
            pr.setFontAlgn(STTextFontAlignType.Enum.forInt(align.ordinal() + 1));
        }
    }

    public String getBulletFont() {
        ParagraphPropertyFetcher<String> fetcher = new ParagraphPropertyFetcher<String>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuFont()) {
                    this.setValue(props.getBuFont().getTypeface());
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return (String)fetcher.getValue();
    }

    public void setBulletFont(String typeface) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextFont font = pr.isSetBuFont() ? pr.getBuFont() : pr.addNewBuFont();
        font.setTypeface(typeface);
    }

    public String getBulletCharacter() {
        ParagraphPropertyFetcher<String> fetcher = new ParagraphPropertyFetcher<String>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuChar()) {
                    this.setValue(props.getBuChar().getChar());
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return (String)fetcher.getValue();
    }

    public void setBulletCharacter(String str) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextCharBullet c = pr.isSetBuChar() ? pr.getBuChar() : pr.addNewBuChar();
        c.setChar(str);
    }

    public Color getBulletFontColor() {
        ParagraphPropertyFetcher<Color> fetcher = new ParagraphPropertyFetcher<Color>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuClr() && props.getBuClr().isSetSrgbClr()) {
                    CTSRgbColor clr = props.getBuClr().getSrgbClr();
                    byte[] rgb = clr.getVal();
                    this.setValue(new Color(0xFF & rgb[0], 0xFF & rgb[1], 0xFF & rgb[2]));
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return (Color)fetcher.getValue();
    }

    public void setBulletFontColor(Color color) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTColor c = pr.isSetBuClr() ? pr.getBuClr() : pr.addNewBuClr();
        CTSRgbColor clr = c.isSetSrgbClr() ? c.getSrgbClr() : c.addNewSrgbClr();
        clr.setVal(new byte[]{(byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue()});
    }

    public double getBulletFontSize() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuSzPct()) {
                    this.setValue((double)props.getBuSzPct().getVal() * 0.001);
                    return true;
                }
                if (props.isSetBuSzPts()) {
                    this.setValue((double)(-props.getBuSzPts().getVal()) * 0.01);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 100.0 : (Double)fetcher.getValue();
    }

    public void setBulletFontSize(double bulletSize) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (bulletSize >= 0.0) {
            CTTextBulletSizePercent pt = pr.isSetBuSzPct() ? pr.getBuSzPct() : pr.addNewBuSzPct();
            pt.setVal((int)(bulletSize * 1000.0));
            if (pr.isSetBuSzPts()) {
                pr.unsetBuSzPts();
            }
        } else {
            CTTextBulletSizePoint pt = pr.isSetBuSzPts() ? pr.getBuSzPts() : pr.addNewBuSzPts();
            pt.setVal((int)(-bulletSize * 100.0));
            if (pr.isSetBuSzPct()) {
                pr.unsetBuSzPct();
            }
        }
    }

    public void setIndent(double value) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (value == -1.0) {
            if (pr.isSetIndent()) {
                pr.unsetIndent();
            }
        } else {
            pr.setIndent(Units.toEMU(value));
        }
    }

    public double getIndent() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetIndent()) {
                    this.setValue(Units.toPoints(props.getIndent()));
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void setLeftMargin(double value) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (value == -1.0) {
            if (pr.isSetMarL()) {
                pr.unsetMarL();
            }
        } else {
            pr.setMarL(Units.toEMU(value));
        }
    }

    public double getLeftMargin() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetMarL()) {
                    double val = Units.toPoints(props.getMarL());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void setRightMargin(double value) {
        CTTextParagraphProperties pr;
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (value == -1.0) {
            if (pr.isSetMarR()) {
                pr.unsetMarR();
            }
        } else {
            pr.setMarR(Units.toEMU(value));
        }
    }

    public double getRightMargin() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetMarR()) {
                    double val = Units.toPoints(props.getMarR());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public double getDefaultTabSize() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetDefTabSz()) {
                    double val = Units.toPoints(props.getDefTabSz());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public double getTabStop(final int idx) {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                CTTextTabStopList tabStops;
                if (props.isSetTabLst() && idx < (tabStops = props.getTabLst()).sizeOfTabArray()) {
                    CTTextTabStop ts = tabStops.getTabArray(idx);
                    double val = Units.toPoints(ts.getPos());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void addTabStop(double value) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextTabStopList tabStops = pr.isSetTabLst() ? pr.getTabLst() : pr.addNewTabLst();
        tabStops.addNewTab().setPos(Units.toEMU(value));
    }

    public void setLineSpacing(double linespacing) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextSpacing spc = CTTextSpacing.Factory.newInstance();
        if (linespacing >= 0.0) {
            spc.addNewSpcPct().setVal((int)(linespacing * 1000.0));
        } else {
            spc.addNewSpcPts().setVal((int)(-linespacing * 100.0));
        }
        pr.setLnSpc(spc);
    }

    public double getLineSpacing() {
        CTTextNormalAutofit normAutofit;
        double lnSpc;
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetLnSpc()) {
                    CTTextSpacing spc = props.getLnSpc();
                    if (spc.isSetSpcPct()) {
                        this.setValue((double)spc.getSpcPct().getVal() * 0.001);
                    } else if (spc.isSetSpcPts()) {
                        this.setValue((double)(-spc.getSpcPts().getVal()) * 0.01);
                    }
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        double d = lnSpc = fetcher.getValue() == null ? 100.0 : (Double)fetcher.getValue();
        if (lnSpc > 0.0 && (normAutofit = this._shape.getTxBody().getBodyPr().getNormAutofit()) != null) {
            double scale = 1.0 - (double)normAutofit.getLnSpcReduction() / 100000.0;
            lnSpc *= scale;
        }
        return lnSpc;
    }

    public void setSpaceBefore(double spaceBefore) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextSpacing spc = CTTextSpacing.Factory.newInstance();
        if (spaceBefore >= 0.0) {
            spc.addNewSpcPct().setVal((int)(spaceBefore * 1000.0));
        } else {
            spc.addNewSpcPts().setVal((int)(-spaceBefore * 100.0));
        }
        pr.setSpcBef(spc);
    }

    public double getSpaceBefore() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetSpcBef()) {
                    CTTextSpacing spc = props.getSpcBef();
                    if (spc.isSetSpcPct()) {
                        this.setValue((double)spc.getSpcPct().getVal() * 0.001);
                    } else if (spc.isSetSpcPts()) {
                        this.setValue((double)(-spc.getSpcPts().getVal()) * 0.01);
                    }
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void setSpaceAfter(double spaceAfter) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextSpacing spc = CTTextSpacing.Factory.newInstance();
        if (spaceAfter >= 0.0) {
            spc.addNewSpcPct().setVal((int)(spaceAfter * 1000.0));
        } else {
            spc.addNewSpcPts().setVal((int)(-spaceAfter * 100.0));
        }
        pr.setSpcAft(spc);
    }

    public double getSpaceAfter() {
        ParagraphPropertyFetcher<Double> fetcher = new ParagraphPropertyFetcher<Double>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetSpcAft()) {
                    CTTextSpacing spc = props.getSpcAft();
                    if (spc.isSetSpcPct()) {
                        this.setValue((double)spc.getSpcPct().getVal() * 0.001);
                    } else if (spc.isSetSpcPts()) {
                        this.setValue((double)(-spc.getSpcPts().getVal()) * 0.01);
                    }
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void setLevel(int level) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        pr.setLvl(level);
    }

    public int getLevel() {
        CTTextParagraphProperties pr = this._p.getPPr();
        if (pr == null) {
            return 0;
        }
        return pr.getLvl();
    }

    public boolean isBullet() {
        ParagraphPropertyFetcher<Boolean> fetcher = new ParagraphPropertyFetcher<Boolean>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuNone()) {
                    this.setValue(false);
                    return true;
                }
                if (props.isSetBuFont() && (props.isSetBuChar() || props.isSetBuAutoNum())) {
                    this.setValue(true);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    public void setBullet(boolean flag) {
        CTTextParagraphProperties pr;
        if (this.isBullet() == flag) {
            return;
        }
        CTTextParagraphProperties cTTextParagraphProperties = pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        if (!flag) {
            pr.addNewBuNone();
            if (pr.isSetBuAutoNum()) {
                pr.unsetBuAutoNum();
            }
            if (pr.isSetBuBlip()) {
                pr.unsetBuBlip();
            }
            if (pr.isSetBuChar()) {
                pr.unsetBuChar();
            }
            if (pr.isSetBuClr()) {
                pr.unsetBuClr();
            }
            if (pr.isSetBuClrTx()) {
                pr.unsetBuClrTx();
            }
            if (pr.isSetBuFont()) {
                pr.unsetBuFont();
            }
            if (pr.isSetBuFontTx()) {
                pr.unsetBuFontTx();
            }
            if (pr.isSetBuSzPct()) {
                pr.unsetBuSzPct();
            }
            if (pr.isSetBuSzPts()) {
                pr.unsetBuSzPts();
            }
            if (pr.isSetBuSzTx()) {
                pr.unsetBuSzTx();
            }
        } else {
            if (pr.isSetBuNone()) {
                pr.unsetBuNone();
            }
            if (!pr.isSetBuFont()) {
                pr.addNewBuFont().setTypeface("Arial");
            }
            if (!pr.isSetBuAutoNum()) {
                pr.addNewBuChar().setChar("\u2022");
            }
        }
    }

    public void setBullet(ListAutoNumber scheme, int startAt) {
        if (startAt < 1) {
            throw new IllegalArgumentException("Start Number must be greater or equal that 1");
        }
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextAutonumberBullet lst = pr.isSetBuAutoNum() ? pr.getBuAutoNum() : pr.addNewBuAutoNum();
        lst.setType(STTextAutonumberScheme.Enum.forInt(scheme.ordinal() + 1));
        lst.setStartAt(startAt);
        if (!pr.isSetBuFont()) {
            pr.addNewBuFont().setTypeface("Arial");
        }
        if (pr.isSetBuNone()) {
            pr.unsetBuNone();
        }
        if (pr.isSetBuBlip()) {
            pr.unsetBuBlip();
        }
        if (pr.isSetBuChar()) {
            pr.unsetBuChar();
        }
    }

    public void setBullet(ListAutoNumber scheme) {
        CTTextParagraphProperties pr = this._p.isSetPPr() ? this._p.getPPr() : this._p.addNewPPr();
        CTTextAutonumberBullet lst = pr.isSetBuAutoNum() ? pr.getBuAutoNum() : pr.addNewBuAutoNum();
        lst.setType(STTextAutonumberScheme.Enum.forInt(scheme.ordinal() + 1));
        if (!pr.isSetBuFont()) {
            pr.addNewBuFont().setTypeface("Arial");
        }
        if (pr.isSetBuNone()) {
            pr.unsetBuNone();
        }
        if (pr.isSetBuBlip()) {
            pr.unsetBuBlip();
        }
        if (pr.isSetBuChar()) {
            pr.unsetBuChar();
        }
    }

    public boolean isBulletAutoNumber() {
        ParagraphPropertyFetcher<Boolean> fetcher = new ParagraphPropertyFetcher<Boolean>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuAutoNum()) {
                    this.setValue(true);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    public int getBulletAutoNumberStart() {
        ParagraphPropertyFetcher<Integer> fetcher = new ParagraphPropertyFetcher<Integer>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuAutoNum() && props.getBuAutoNum().isSetStartAt()) {
                    this.setValue(props.getBuAutoNum().getStartAt());
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? 0 : (Integer)fetcher.getValue();
    }

    public ListAutoNumber getBulletAutoNumberScheme() {
        ParagraphPropertyFetcher<ListAutoNumber> fetcher = new ParagraphPropertyFetcher<ListAutoNumber>(this.getLevel()){

            @Override
            public boolean fetch(CTTextParagraphProperties props) {
                if (props.isSetBuAutoNum()) {
                    this.setValue(ListAutoNumber.values()[props.getBuAutoNum().getType().intValue() - 1]);
                    return true;
                }
                return false;
            }
        };
        this.fetchParagraphProperty(fetcher);
        return fetcher.getValue() == null ? ListAutoNumber.ARABIC_PLAIN : (ListAutoNumber)((Object)fetcher.getValue());
    }

    private boolean fetchParagraphProperty(ParagraphPropertyFetcher visitor) {
        boolean ok = false;
        if (this._p.isSetPPr()) {
            ok = visitor.fetch(this._p.getPPr());
        }
        if (!ok) {
            ok = visitor.fetch(this._shape);
        }
        return ok;
    }

    public String toString() {
        return "[" + this.getClass() + "]" + this.getText();
    }
}

