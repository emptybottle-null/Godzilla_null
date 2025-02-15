/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.xslf.util.EMFHandler;
import org.apache.poi.xslf.util.MFProxy;
import org.apache.poi.xslf.util.PPTHandler;
import org.apache.poi.xslf.util.WMFHandler;

public final class PPTX2PNG {
    private static final String INPUT_PAT_REGEX = "(?<slideno>[^|]+)\\|(?<format>[^|]+)\\|(?<basename>.+)\\.(?<ext>[^.]++)";
    private static final Pattern INPUT_PATTERN = Pattern.compile("(?<slideno>[^|]+)\\|(?<format>[^|]+)\\|(?<basename>.+)\\.(?<ext>[^.]++)");
    private static final String OUTPUT_PAT_REGEX = "${basename}-${slideno}.${format}";
    private String slidenumStr = "-1";
    private float scale = 1.0f;
    private File file = null;
    private String format = "png";
    private File outdir = null;
    private String outfile = null;
    private boolean quiet = false;
    private String outPattern = "${basename}-${slideno}.${format}";
    private File dumpfile = null;
    private String fixSide = "scale";
    private boolean ignoreParse = false;
    private boolean extractEmbedded = false;

    private static void usage(String error) {
        String msg = "Usage: PPTX2PNG [options] <ppt or pptx file or 'stdin'>\n" + (error == null ? "" : "Error: " + error + "\n") + "Options:\n    -scale <float>    scale factor\n    -fixSide <side>   specify side (long,short,width,height) to fix - use <scale> as amount of pixels\n    -slide <integer>  1-based index of a slide to render\n    -format <type>    png,gif,jpg (,null for testing)\n    -outdir <dir>     output directory, defaults to origin of the ppt/pptx file\n    -outfile <file>   output filename, defaults to '" + OUTPUT_PAT_REGEX + "'\n    -outpat <pattern> output filename pattern, defaults to '" + OUTPUT_PAT_REGEX + "'\n                      patterns: basename, slideno, format, ext\n    -dump <file>      dump the annotated records to a file\n    -quiet            do not write to console (for normal processing)\n    -ignoreParse      ignore parsing error and continue with the records read until the error\n    -extractEmbedded  extract embedded parts";
        System.out.println(msg);
    }

    public static void main(String[] args) throws Exception {
        PPTX2PNG p2p = new PPTX2PNG();
        if (p2p.parseCommandLine(args)) {
            p2p.processFile();
        }
    }

    private PPTX2PNG() {
    }

    private boolean parseCommandLine(String[] args) {
        boolean isStdin;
        if (args.length == 0) {
            PPTX2PNG.usage(null);
            return false;
        }
        block26: for (int i = 0; i < args.length; ++i) {
            String opt = i + 1 < args.length ? args[i + 1] : null;
            switch (args[i]) {
                case "-scale": {
                    if (opt == null) continue block26;
                    this.scale = Float.parseFloat(opt);
                    ++i;
                    continue block26;
                }
                case "-slide": {
                    this.slidenumStr = opt;
                    ++i;
                    continue block26;
                }
                case "-format": {
                    this.format = opt;
                    ++i;
                    continue block26;
                }
                case "-outdir": {
                    if (opt == null) continue block26;
                    this.outdir = new File(opt);
                    ++i;
                    continue block26;
                }
                case "-outfile": {
                    this.outfile = opt;
                    ++i;
                    continue block26;
                }
                case "-outpat": {
                    this.outPattern = opt;
                    ++i;
                    continue block26;
                }
                case "-quiet": {
                    this.quiet = true;
                    continue block26;
                }
                case "-dump": {
                    if (opt != null) {
                        this.dumpfile = new File(opt);
                        ++i;
                        continue block26;
                    }
                    this.dumpfile = new File("pptx2png.dump");
                    continue block26;
                }
                case "-fixside": {
                    if (opt != null) {
                        this.fixSide = opt.toLowerCase(Locale.ROOT);
                        ++i;
                        continue block26;
                    }
                    this.fixSide = "long";
                    continue block26;
                }
                case "-ignoreParse": {
                    this.ignoreParse = true;
                    continue block26;
                }
                case "-extractEmbedded": {
                    this.extractEmbedded = true;
                    continue block26;
                }
                default: {
                    this.file = new File(args[i]);
                }
            }
        }
        boolean bl = isStdin = this.file != null && "stdin".equalsIgnoreCase(this.file.getName());
        if (!(isStdin || this.file != null && this.file.exists())) {
            PPTX2PNG.usage("File not specified or it doesn't exist");
            return false;
        }
        if (this.format == null || !this.format.matches("^(png|gif|jpg|null)$")) {
            PPTX2PNG.usage("Invalid format given");
            return false;
        }
        if (this.outdir == null) {
            if (isStdin) {
                PPTX2PNG.usage("When reading from STDIN, you need to specify an outdir.");
                return false;
            }
            this.outdir = this.file.getParentFile();
        }
        if (!this.outdir.exists()) {
            PPTX2PNG.usage("Outdir doesn't exist");
            return false;
        }
        if (!("null".equals(this.format) || this.outdir != null && this.outdir.exists() && this.outdir.isDirectory())) {
            PPTX2PNG.usage("Output directory doesn't exist");
            return false;
        }
        if (this.scale < 0.0f) {
            PPTX2PNG.usage("Invalid scale given");
            return false;
        }
        if (!"long,short,width,height,scale".contains(this.fixSide)) {
            PPTX2PNG.usage("<fixside> must be one of long / short / width / height");
            return false;
        }
        return true;
    }

    private void processFile() throws IOException {
        if (!this.quiet) {
            System.out.println("Processing " + this.file);
        }
        try (MFProxy proxy = this.initProxy(this.file);){
            Set<Integer> slidenum = proxy.slideIndexes(this.slidenumStr);
            if (slidenum.isEmpty()) {
                PPTX2PNG.usage("slidenum must be either -1 (for all) or within range: [1.." + proxy.getSlideCount() + "] for " + this.file);
                return;
            }
            Dimension2DDouble dim = new Dimension2DDouble();
            double lenSide = this.getDimensions(proxy, dim);
            int width = Math.max((int)Math.rint(((Dimension2D)dim).getWidth()), 1);
            int height = Math.max((int)Math.rint(((Dimension2D)dim).getHeight()), 1);
            for (int slideNo : slidenum) {
                proxy.setSlideNo(slideNo);
                if (!this.quiet) {
                    String title = proxy.getTitle();
                    System.out.println("Rendering slide " + slideNo + (title == null ? "" : ": " + title.trim()));
                }
                this.dumpRecords(proxy);
                this.extractEmbedded(proxy, slideNo);
                BufferedImage img = new BufferedImage(width, height, 2);
                Graphics2D graphics = img.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                graphics.setRenderingHint(Drawable.BUFFERED_IMAGE, new WeakReference<BufferedImage>(img));
                graphics.scale((double)this.scale / lenSide, (double)this.scale / lenSide);
                graphics.setComposite(AlphaComposite.Clear);
                graphics.fillRect(0, 0, width, height);
                graphics.setComposite(AlphaComposite.SrcOver);
                proxy.draw(graphics);
                if (!"null".equals(this.format)) {
                    ImageIO.write((RenderedImage)img, this.format, new File(this.outdir, this.calcOutFile(proxy, slideNo)));
                }
                graphics.dispose();
                img.flush();
            }
        } catch (NoScratchpadException e) {
            PPTX2PNG.usage("'" + this.file.getName() + "': Format not supported - try to include poi-scratchpad.jar into the CLASSPATH.");
            return;
        }
        if (!this.quiet) {
            System.out.println("Done");
        }
    }

    private double getDimensions(MFProxy proxy, Dimension2D dim) {
        double lenSide;
        Dimension2D pgsize = proxy.getSize();
        switch (this.fixSide) {
            default: {
                lenSide = 1.0;
                break;
            }
            case "long": {
                lenSide = Math.max(pgsize.getWidth(), pgsize.getHeight());
                break;
            }
            case "short": {
                lenSide = Math.min(pgsize.getWidth(), pgsize.getHeight());
                break;
            }
            case "width": {
                lenSide = pgsize.getWidth();
                break;
            }
            case "height": {
                lenSide = pgsize.getHeight();
            }
        }
        dim.setSize(pgsize.getWidth() * (double)this.scale / lenSide, pgsize.getHeight() * (double)this.scale / lenSide);
        return lenSide;
    }

    private void dumpRecords(MFProxy proxy) throws IOException {
        if (this.dumpfile == null) {
            return;
        }
        GenericRecord gr = proxy.getRoot();
        try (GenericRecordJsonWriter fw = new GenericRecordJsonWriter(this.dumpfile){

            @Override
            protected boolean printBytes(String name, Object o) {
                return false;
            }
        };){
            if (gr == null) {
                fw.writeError(this.file.getName() + " doesn't support GenericRecord interface and can't be dumped to a file.");
            } else {
                fw.write(gr);
            }
        }
    }

    private void extractEmbedded(MFProxy proxy, int slideNo) throws IOException {
        if (!this.extractEmbedded) {
            return;
        }
        for (EmbeddedExtractor.EmbeddedPart ep : proxy.getEmbeddings(slideNo)) {
            String filename = ep.getName();
            filename = new File(filename == null ? "dummy.dat" : filename).getName();
            filename = this.calcOutFile(proxy, slideNo).replaceFirst("\\.\\w+$", "") + "_" + filename;
            FileOutputStream fos = new FileOutputStream(new File(this.outdir, filename));
            Throwable throwable = null;
            try {
                fos.write(ep.getData().get());
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                if (fos == null) continue;
                if (throwable != null) {
                    try {
                        fos.close();
                    } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    continue;
                }
                fos.close();
            }
        }
    }

    private MFProxy initProxy(File file) throws IOException {
        MFProxy proxy;
        String fileName = file.getName().toLowerCase(Locale.ROOT);
        if ("stdin".equals(fileName)) {
            InputStream bis = FileMagic.prepareToCheckMagic(System.in);
            FileMagic fm = FileMagic.valueOf(bis);
            switch (fm) {
                case EMF: {
                    proxy = new EMFHandler();
                    break;
                }
                case WMF: {
                    proxy = new WMFHandler();
                    break;
                }
                default: {
                    proxy = new PPTHandler();
                }
            }
            proxy.setIgnoreParse(this.ignoreParse);
            proxy.setQuite(this.quiet);
            proxy.parse(bis);
        } else {
            switch (fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(46)) : "") {
                case ".emf": {
                    proxy = new EMFHandler();
                    break;
                }
                case ".wmf": {
                    proxy = new WMFHandler();
                    break;
                }
                default: {
                    proxy = new PPTHandler();
                }
            }
            proxy.parse(file);
        }
        return proxy;
    }

    private String calcOutFile(MFProxy proxy, int slideNo) {
        if (this.outfile != null) {
            return this.outfile;
        }
        String inname = String.format(Locale.ROOT, "%04d|%s|%s", slideNo, this.format, this.file.getName());
        String outpat = proxy.getSlideCount() > 1 ? this.outPattern : this.outPattern.replaceAll("-?\\$\\{slideno}", "");
        return INPUT_PATTERN.matcher(inname).replaceAll(outpat);
    }

    static class NoScratchpadException
    extends IOException {
        NoScratchpadException() {
        }

        NoScratchpadException(Throwable cause) {
            super(cause);
        }
    }
}

