/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.Spliterators;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.usermodel.ObjectData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.MFProxy;
import org.apache.poi.xslf.util.PPTX2PNG;

@Internal
class PPTHandler
extends MFProxy {
    private SlideShow<?, ?> ppt;
    private Slide<?, ?> slide;
    private static final String RANGE_PATTERN = "(^|,)(?<from>\\d+)?(-(?<to>\\d+))?";

    PPTHandler() {
    }

    @Override
    public void parse(File file) throws IOException {
        try {
            this.ppt = SlideShowFactory.create(file, null, true);
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
        this.slide = this.ppt.getSlides().get(0);
    }

    @Override
    public void parse(InputStream is) throws IOException {
        try {
            this.ppt = SlideShowFactory.create(is, null);
        } catch (IOException e) {
            if (e.getMessage().contains("scratchpad")) {
                throw new PPTX2PNG.NoScratchpadException(e);
            }
            throw e;
        }
        this.slide = this.ppt.getSlides().get(0);
    }

    @Override
    public Dimension2D getSize() {
        return this.ppt.getPageSize();
    }

    @Override
    public int getSlideCount() {
        return this.ppt.getSlides().size();
    }

    @Override
    public void setSlideNo(int slideNo) {
        this.slide = this.ppt.getSlides().get(slideNo - 1);
    }

    @Override
    public String getTitle() {
        return this.slide.getTitle();
    }

    @Override
    public Set<Integer> slideIndexes(String range) {
        final Matcher matcher = Pattern.compile(RANGE_PATTERN).matcher(range);
        Spliterators.AbstractSpliterator<Matcher> sp = new Spliterators.AbstractSpliterator<Matcher>((long)range.length(), 272){

            @Override
            public boolean tryAdvance(Consumer<? super Matcher> action) {
                boolean b = matcher.find();
                if (b) {
                    action.accept(matcher);
                }
                return b;
            }
        };
        return StreamSupport.stream(sp, false).flatMap(this::range).collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public void draw(Graphics2D ctx) {
        this.slide.draw(ctx);
    }

    @Override
    public void close() throws IOException {
        if (this.ppt != null) {
            this.ppt.close();
        }
    }

    @Override
    public GenericRecord getRoot() {
        return this.ppt instanceof GenericRecord ? (GenericRecord)((Object)this.ppt) : null;
    }

    private Stream<Integer> range(Matcher m) {
        int from;
        int slideCount = this.ppt.getSlides().size();
        String fromStr = m.group("from");
        String toStr = m.group("to");
        int n = from = fromStr == null || fromStr.isEmpty() ? 1 : Integer.parseInt(fromStr);
        int to = toStr == null ? from : (toStr.isEmpty() || (fromStr == null || fromStr.isEmpty()) && "1".equals(toStr) ? slideCount : Integer.parseInt(toStr));
        return IntStream.rangeClosed(from, to).filter(i -> i <= slideCount).boxed();
    }

    @Override
    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int slideNo) {
        return () -> this.ppt.getSlides().get(slideNo).getShapes().stream().filter(s -> s instanceof ObjectShape).map(PPTHandler::fromObjectShape).iterator();
    }

    private static EmbeddedExtractor.EmbeddedPart fromObjectShape(Shape s) {
        ObjectShape os = (ObjectShape)s;
        ObjectData od = os.getObjectData();
        EmbeddedExtractor.EmbeddedPart embed = new EmbeddedExtractor.EmbeddedPart();
        embed.setName(od.getFileName());
        embed.setData(() -> {
            try (InputStream is = od.getInputStream();){
                byte[] byArray = IOUtils.toByteArray(is);
                return byArray;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return embed;
    }
}

