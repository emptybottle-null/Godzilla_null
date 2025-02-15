/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.apache.tools.ant.BuildException
 *  org.apache.tools.ant.DirectoryScanner
 *  org.apache.tools.ant.Task
 *  org.apache.tools.ant.types.FileSet
 */
package com.kitfox.svg.app.ant;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.app.beans.SVGIcon;
import com.kitfox.svg.xml.ColorTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

public class SVGToImageAntTask
extends Task {
    private ArrayList<FileSet> filesets = new ArrayList();
    boolean verbose = false;
    File destDir;
    private String format = "png";
    Color backgroundColor = null;
    int width = -1;
    int height = -1;
    boolean antiAlias = true;
    String interpolation = "bicubic";
    boolean clipToViewBox = false;
    boolean sizeToFit = true;

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setBackgroundColor(String bgColor) {
        this.backgroundColor = ColorTable.parseColor(bgColor);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAntiAlias(boolean antiAlias) {
        this.antiAlias = antiAlias;
    }

    public void setInterpolation(String interpolation) {
        this.interpolation = interpolation;
    }

    public void setSizeToFit(boolean sizeToFit) {
        this.sizeToFit = sizeToFit;
    }

    public void setClipToViewBox(boolean clipToViewBox) {
        this.clipToViewBox = clipToViewBox;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    public void addFileset(FileSet set) {
        this.filesets.add(set);
    }

    public void execute() {
        if (this.verbose) {
            this.log("Building SVG images");
        }
        for (FileSet fs : this.filesets) {
            DirectoryScanner scanner = fs.getDirectoryScanner(this.getProject());
            String[] files = scanner.getIncludedFiles();
            try {
                File basedir = scanner.getBasedir();
                if (this.verbose) {
                    this.log("Scaning " + basedir);
                }
                for (int i = 0; i < files.length; ++i) {
                    this.translate(basedir, files[i]);
                }
            } catch (Exception e) {
                throw new BuildException((Throwable)e);
            }
        }
    }

    private void translate(File baseDir, String shortName) throws BuildException {
        File outFile;
        Matcher matchName;
        File source = new File(baseDir, shortName);
        if (this.verbose) {
            this.log("Reading file: " + source);
        }
        if ((matchName = Pattern.compile("(.*)\\.svg", 2).matcher(shortName)).matches()) {
            shortName = matchName.group(1);
        }
        shortName = shortName + "." + this.format;
        SVGIcon icon = new SVGIcon();
        icon.setSvgURI(source.toURI());
        icon.setAntiAlias(this.antiAlias);
        if (this.interpolation.equals("nearest neighbor")) {
            icon.setInterpolation(0);
        } else if (this.interpolation.equals("bilinear")) {
            icon.setInterpolation(1);
        } else if (this.interpolation.equals("bicubic")) {
            icon.setInterpolation(2);
        }
        int iconWidth = this.width > 0 ? this.width : icon.getIconWidth();
        int iconHeight = this.height > 0 ? this.height : icon.getIconHeight();
        icon.setClipToViewbox(this.clipToViewBox);
        icon.setPreferredSize(new Dimension(iconWidth, iconHeight));
        icon.setScaleToFit(this.sizeToFit);
        BufferedImage image = new BufferedImage(iconWidth, iconHeight, 2);
        Graphics2D g = image.createGraphics();
        if (this.backgroundColor != null) {
            g.setColor(this.backgroundColor);
            g.fillRect(0, 0, iconWidth, iconHeight);
        }
        g.setClip(0, 0, iconWidth, iconHeight);
        icon.paintIcon(null, (Graphics)g, 0, 0);
        g.dispose();
        File file = outFile = this.destDir == null ? new File(baseDir, shortName) : new File(this.destDir, shortName);
        if (this.verbose) {
            this.log("Writing file: " + outFile);
        }
        try {
            ImageIO.write((RenderedImage)image, this.format, outFile);
        } catch (IOException e) {
            this.log("Error writing image: " + e.getMessage());
            throw new BuildException((Throwable)e);
        }
        SVGCache.getSVGUniverse().clear();
    }
}

