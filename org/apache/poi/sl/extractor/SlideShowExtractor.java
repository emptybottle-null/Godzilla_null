/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.extractor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class SlideShowExtractor<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends POITextExtractor {
    private static final POILogger LOG = POILogFactory.getLogger(SlideShowExtractor.class);
    private static final String SLIDE_NUMBER_PH = "\u2039#\u203a";
    private SlideShow<S, P> slideshow;
    private boolean slidesByDefault = true;
    private boolean notesByDefault;
    private boolean commentsByDefault;
    private boolean masterByDefault;
    private Predicate<Object> filter = o -> true;

    public SlideShowExtractor(SlideShow<S, P> slideshow) {
        this.setFilesystem(slideshow);
        this.slideshow = slideshow;
    }

    @Override
    public final Object getDocument() {
        return this.slideshow.getPersistDocument();
    }

    public void setSlidesByDefault(boolean slidesByDefault) {
        this.slidesByDefault = slidesByDefault;
    }

    public void setNotesByDefault(boolean notesByDefault) {
        this.notesByDefault = notesByDefault;
    }

    public void setCommentsByDefault(boolean commentsByDefault) {
        this.commentsByDefault = commentsByDefault;
    }

    public void setMasterByDefault(boolean masterByDefault) {
        this.masterByDefault = masterByDefault;
    }

    @Override
    public POITextExtractor getMetadataTextExtractor() {
        return this.slideshow.getMetadataTextExtractor();
    }

    @Override
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Slide<S, P> slide : this.slideshow.getSlides()) {
            this.getText(slide, sb::append);
        }
        return sb.toString();
    }

    public String getText(Slide<S, P> slide) {
        StringBuilder sb = new StringBuilder();
        this.getText(slide, sb::append);
        return sb.toString();
    }

    private void getText(Slide<S, P> slide, Consumer<String> consumer) {
        if (this.slidesByDefault) {
            this.printShapeText(slide, consumer);
        }
        if (this.masterByDefault) {
            MasterSheet ms = slide.getMasterSheet();
            this.printSlideMaster(ms, consumer);
            MasterSheet<S, P> sl = slide.getSlideLayout();
            if (sl != ms) {
                this.printSlideMaster(sl, consumer);
            }
        }
        if (this.commentsByDefault) {
            this.printComments(slide, consumer);
        }
        if (this.notesByDefault) {
            this.printNotes(slide, consumer);
        }
    }

    private void printSlideMaster(MasterSheet<S, P> master, Consumer<String> consumer) {
        if (master == null) {
            return;
        }
        for (Shape shape : master) {
            TextShape ts;
            String text;
            if (!(shape instanceof TextShape) || (text = (ts = (TextShape)shape).getText()) == null || text.isEmpty() || "*".equals(text)) continue;
            if (ts.isPlaceholder()) {
                LOG.log(3, "Ignoring boiler plate (placeholder) text on slide master:", text);
                continue;
            }
            this.printTextParagraphs(ts.getTextParagraphs(), consumer);
        }
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer) {
        this.printTextParagraphs(paras, consumer, "\n");
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer, String trailer) {
        this.printTextParagraphs(paras, consumer, trailer, SlideShowExtractor::replaceTextCap);
    }

    private void printTextParagraphs(List<P> paras, Consumer<String> consumer, String trailer, Function<TextRun, String> converter) {
        for (TextParagraph p : paras) {
            for (TextRun r : p) {
                if (!this.filter.test(r)) continue;
                consumer.accept(converter.apply(r));
            }
            if (trailer.isEmpty() || !this.filter.test(trailer)) continue;
            consumer.accept(trailer);
        }
    }

    private void printHeaderFooter(Sheet<S, P> sheet, Consumer<String> consumer, Consumer<String> footerCon) {
        Sheet<S, P> m = sheet instanceof Slide ? sheet.getMasterSheet() : sheet;
        this.addSheetPlaceholderDatails(sheet, Placeholder.HEADER, consumer);
        this.addSheetPlaceholderDatails(sheet, Placeholder.FOOTER, footerCon);
        if (!this.masterByDefault) {
            return;
        }
        for (Shape s : m) {
            TextShape ts;
            PlaceholderDetails pd;
            if (!(s instanceof TextShape) || (pd = (ts = (TextShape)s).getPlaceholderDetails()) == null || !pd.isVisible() || pd.getPlaceholder() == null) continue;
            switch (pd.getPlaceholder()) {
                case HEADER: {
                    this.printTextParagraphs(ts.getTextParagraphs(), consumer);
                    break;
                }
                case FOOTER: {
                    this.printTextParagraphs(ts.getTextParagraphs(), footerCon);
                    break;
                }
                case SLIDE_NUMBER: {
                    this.printTextParagraphs(ts.getTextParagraphs(), footerCon, "\n", SlideShowExtractor::replaceSlideNumber);
                    break;
                }
            }
        }
    }

    private void addSheetPlaceholderDatails(Sheet<S, P> sheet, Placeholder placeholder, Consumer<String> consumer) {
        String headerStr;
        PlaceholderDetails headerPD = sheet.getPlaceholderDetails(placeholder);
        String string = headerStr = headerPD != null ? headerPD.getText() : null;
        if (headerStr != null && this.filter.test(headerPD)) {
            consumer.accept(headerStr);
        }
    }

    private void printShapeText(Sheet<S, P> sheet, Consumer<String> consumer) {
        LinkedList footer = new LinkedList();
        this.printHeaderFooter(sheet, consumer, footer::add);
        this.printShapeText((ShapeContainer<S, P>)sheet, consumer);
        footer.forEach(consumer);
    }

    private void printShapeText(ShapeContainer<S, P> container, Consumer<String> consumer) {
        for (Shape shape : container) {
            if (shape instanceof TextShape) {
                this.printTextParagraphs(((TextShape)shape).getTextParagraphs(), consumer);
                continue;
            }
            if (shape instanceof TableShape) {
                this.printShapeText((TableShape)shape, consumer);
                continue;
            }
            if (!(shape instanceof ShapeContainer)) continue;
            this.printShapeText((ShapeContainer)((Object)shape), consumer);
        }
    }

    private void printShapeText(TableShape<S, P> shape, Consumer<String> consumer) {
        int nrows = shape.getNumberOfRows();
        int ncols = shape.getNumberOfColumns();
        for (int row = 0; row < nrows; ++row) {
            String trailer = "";
            for (int col = 0; col < ncols; ++col) {
                TableCell<S, P> cell = shape.getCell(row, col);
                if (cell == null) continue;
                trailer = col < ncols - 1 ? "\t" : "\n";
                this.printTextParagraphs(cell.getTextParagraphs(), consumer, trailer);
            }
            if (trailer.equals("\n") || !this.filter.test("\n")) continue;
            consumer.accept("\n");
        }
    }

    private void printComments(Slide<S, P> slide, Consumer<String> consumer) {
        slide.getComments().stream().filter(this.filter).map(c -> c.getAuthor() + " - " + c.getText()).forEach(consumer);
    }

    private void printNotes(Slide<S, P> slide, Consumer<String> consumer) {
        Notes<S, P> notes = slide.getNotes();
        if (notes == null) {
            return;
        }
        LinkedList footer = new LinkedList();
        this.printHeaderFooter(notes, consumer, footer::add);
        this.printShapeText(notes, consumer);
        footer.forEach(consumer);
    }

    public List<? extends ObjectShape<S, P>> getOLEShapes() {
        ArrayList<ObjectShape<S, P>> oleShapes = new ArrayList<ObjectShape<S, P>>();
        for (Slide<S, P> slide : this.slideshow.getSlides()) {
            this.addOLEShapes(oleShapes, slide);
        }
        return oleShapes;
    }

    private void addOLEShapes(List<ObjectShape<S, P>> oleShapes, ShapeContainer<S, P> container) {
        for (Shape shape : container) {
            if (shape instanceof ShapeContainer) {
                this.addOLEShapes(oleShapes, (ShapeContainer)((Object)shape));
                continue;
            }
            if (!(shape instanceof ObjectShape)) continue;
            oleShapes.add((ObjectShape)shape);
        }
    }

    private static String replaceSlideNumber(TextRun tr) {
        String raw = tr.getRawText();
        if (!raw.contains(SLIDE_NUMBER_PH)) {
            return raw;
        }
        TextParagraph<?, ?, ?> tp = tr.getParagraph();
        TextShape<?, ?> ps = tp != null ? tp.getParentShape() : null;
        Sheet sh = ps != null ? ps.getSheet() : null;
        String slideNr = sh instanceof Slide ? Integer.toString(((Slide)sh).getSlideNumber() + 1) : "";
        return raw.replace(SLIDE_NUMBER_PH, slideNr);
    }

    private static String replaceTextCap(TextRun tr) {
        TextParagraph<?, ?, ?> tp = tr.getParagraph();
        TextShape<?, ?> sh = tp != null ? tp.getParentShape() : null;
        Placeholder ph = sh != null ? sh.getPlaceholder() : null;
        char sep = ph == Placeholder.TITLE || ph == Placeholder.CENTERED_TITLE || ph == Placeholder.SUBTITLE ? (char)'\n' : ' ';
        String txt = tr.getRawText();
        txt = txt.replace('\r', '\n');
        txt = txt.replace('\u000b', sep);
        switch (tr.getTextCap()) {
            case ALL: {
                txt = txt.toUpperCase(LocaleUtil.getUserLocale());
            }
            case SMALL: {
                txt = txt.toLowerCase(LocaleUtil.getUserLocale());
            }
        }
        return txt;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Deprecated
    public BitSet getCodepoints(String typeface, Boolean italic, Boolean bold) {
        BitSet glyphs = new BitSet();
        Predicate<Object> filterOld = this.filter;
        try {
            this.filter = o -> SlideShowExtractor.filterFonts(o, typeface, italic, bold);
            this.slideshow.getSlides().forEach(slide -> this.getText((Slide<S, P>)slide, s -> s.codePoints().forEach(glyphs::set)));
        } finally {
            this.filter = filterOld;
        }
        return glyphs;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public SparseBitSet getCodepointsInSparseBitSet(String typeface, Boolean italic, Boolean bold) {
        SparseBitSet glyphs = new SparseBitSet();
        Predicate<Object> filterOld = this.filter;
        try {
            this.filter = o -> SlideShowExtractor.filterFonts(o, typeface, italic, bold);
            this.slideshow.getSlides().forEach(slide -> this.getText((Slide<S, P>)slide, s -> s.codePoints().forEach(glyphs::set)));
        } finally {
            this.filter = filterOld;
        }
        return glyphs;
    }

    private static boolean filterFonts(Object o, String typeface, Boolean italic, Boolean bold) {
        if (!(o instanceof TextRun)) {
            return false;
        }
        TextRun tr = (TextRun)o;
        return !(!typeface.equalsIgnoreCase(tr.getFontFamily()) || italic != null && tr.isItalic() != italic.booleanValue() || bold != null && tr.isBold() != bold.booleanValue());
    }
}

