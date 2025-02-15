/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;

@Internal
class XSLFPropertiesDelegate {
    private static final POILogger LOG = POILogFactory.getLogger(XSLFPropertiesDelegate.class);

    XSLFPropertiesDelegate() {
    }

    public static XSLFFillProperties getFillDelegate(XmlObject props) {
        return XSLFPropertiesDelegate.getDelegate(XSLFFillProperties.class, props);
    }

    public static XSLFGeometryProperties getGeometryDelegate(XmlObject props) {
        return XSLFPropertiesDelegate.getDelegate(XSLFGeometryProperties.class, props);
    }

    public static XSLFEffectProperties getEffectDelegate(XmlObject props) {
        return XSLFPropertiesDelegate.getDelegate(XSLFEffectProperties.class, props);
    }

    private static <T> T getDelegate(Class<T> clazz, XmlObject props) {
        XSLFFillProperties obj = null;
        if (props == null) {
            return null;
        }
        if (props instanceof CTShapeProperties) {
            obj = new ShapeDelegate((CTShapeProperties)props);
        } else if (props instanceof CTBackgroundProperties) {
            obj = new BackgroundDelegate((CTBackgroundProperties)props);
        } else if (props instanceof CTStyleMatrixReference) {
            obj = new StyleMatrixDelegate((CTStyleMatrixReference)props);
        } else if (props instanceof CTTableCellProperties) {
            obj = new TableCellDelegate((CTTableCellProperties)props);
        } else if (props instanceof CTNoFillProperties || props instanceof CTSolidColorFillProperties || props instanceof CTGradientFillProperties || props instanceof CTBlipFillProperties || props instanceof CTPatternFillProperties || props instanceof CTGroupFillProperties) {
            obj = new FillPartDelegate(props);
        } else if (props instanceof CTFillProperties) {
            obj = new FillDelegate((CTFillProperties)props);
        } else if (props instanceof CTLineProperties) {
            obj = new LineStyleDelegate((CTLineProperties)props);
        } else if (props instanceof CTTextCharacterProperties) {
            obj = new TextCharDelegate((CTTextCharacterProperties)props);
        } else {
            LOG.log(7, props.getClass() + " is an unknown properties type");
            return null;
        }
        if (clazz.isInstance(obj)) {
            return (T)obj;
        }
        LOG.log(5, obj.getClass() + " doesn't implement " + clazz);
        return null;
    }

    private static class TextCharDelegate
    implements XSLFFillProperties {
        final CTTextCharacterProperties props;

        TextCharDelegate(CTTextCharacterProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
            this.props.setBlipFill(blipFill);
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        @Override
        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
            this.props.setGrpFill(grpFill);
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        @Override
        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    private static class LineStyleDelegate
    implements XSLFFillProperties {
        final CTLineProperties props;

        LineStyleDelegate(CTLineProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return null;
        }

        @Override
        public boolean isSetBlipFill() {
            return false;
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        @Override
        public void unsetBlipFill() {
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return null;
        }

        @Override
        public boolean isSetGrpFill() {
            return false;
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        @Override
        public void unsetGrpFill() {
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return true;
        }
    }

    private static class FillPartDelegate
    implements XSLFFillProperties {
        final XmlObject props;

        FillPartDelegate(XmlObject props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.isSetNoFill() ? (CTNoFillProperties)this.props : null;
        }

        @Override
        public boolean isSetNoFill() {
            return this.props instanceof CTNoFillProperties;
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return null;
        }

        @Override
        public void unsetNoFill() {
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.isSetSolidFill() ? (CTSolidColorFillProperties)this.props : null;
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props instanceof CTSolidColorFillProperties;
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return null;
        }

        @Override
        public void unsetSolidFill() {
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.isSetGradFill() ? (CTGradientFillProperties)this.props : null;
        }

        @Override
        public boolean isSetGradFill() {
            return this.props instanceof CTGradientFillProperties;
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return null;
        }

        @Override
        public void unsetGradFill() {
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.isSetBlipFill() ? (CTBlipFillProperties)this.props : null;
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props instanceof CTBlipFillProperties;
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        @Override
        public void unsetBlipFill() {
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.isSetPattFill() ? (CTPatternFillProperties)this.props : null;
        }

        @Override
        public boolean isSetPattFill() {
            return this.props instanceof CTPatternFillProperties;
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return null;
        }

        @Override
        public void unsetPattFill() {
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.isSetGrpFill() ? (CTGroupFillProperties)this.props : null;
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props instanceof CTGroupFillProperties;
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        @Override
        public void unsetGrpFill() {
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    private static class FillDelegate
    implements XSLFFillProperties {
        final CTFillProperties props;

        FillDelegate(CTFillProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
            this.props.setBlipFill(blipFill);
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        @Override
        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
            this.props.setGrpFill(grpFill);
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        @Override
        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    private static class StyleMatrixDelegate
    implements XSLFFillProperties {
        final CTStyleMatrixReference props;

        StyleMatrixDelegate(CTStyleMatrixReference props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return null;
        }

        @Override
        public boolean isSetNoFill() {
            return false;
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return null;
        }

        @Override
        public void unsetNoFill() {
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return null;
        }

        @Override
        public boolean isSetSolidFill() {
            return false;
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return null;
        }

        @Override
        public void unsetSolidFill() {
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return null;
        }

        @Override
        public boolean isSetGradFill() {
            return false;
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return null;
        }

        @Override
        public void unsetGradFill() {
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return null;
        }

        @Override
        public boolean isSetBlipFill() {
            return false;
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return null;
        }

        @Override
        public void unsetBlipFill() {
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return null;
        }

        @Override
        public boolean isSetPattFill() {
            return false;
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return null;
        }

        @Override
        public void unsetPattFill() {
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return null;
        }

        @Override
        public boolean isSetGrpFill() {
            return false;
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return null;
        }

        @Override
        public void unsetGrpFill() {
        }

        @Override
        public boolean isSetMatrixStyle() {
            return true;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return this.props;
        }

        @Override
        public boolean isLineStyle() {
            XmlCursor cur = this.props.newCursor();
            String name = cur.getName().getLocalPart();
            cur.dispose();
            return "lnRef".equals(name);
        }
    }

    private static class TableCellDelegate
    implements XSLFFillProperties {
        final CTTableCellProperties props;

        TableCellDelegate(CTTableCellProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
            this.props.setBlipFill(blipFill);
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        @Override
        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
            this.props.setGrpFill(grpFill);
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        @Override
        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    private static class BackgroundDelegate
    implements XSLFFillProperties,
    XSLFEffectProperties {
        final CTBackgroundProperties props;

        BackgroundDelegate(CTBackgroundProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
            this.props.setBlipFill(blipFill);
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        @Override
        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
            this.props.setGrpFill(grpFill);
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        @Override
        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        @Override
        public CTEffectList getEffectLst() {
            return this.props.getEffectLst();
        }

        @Override
        public boolean isSetEffectLst() {
            return this.props.isSetEffectLst();
        }

        @Override
        public void setEffectLst(CTEffectList effectLst) {
            this.props.setEffectLst(effectLst);
        }

        @Override
        public CTEffectList addNewEffectLst() {
            return this.props.addNewEffectLst();
        }

        @Override
        public void unsetEffectLst() {
            this.props.unsetEffectLst();
        }

        @Override
        public CTEffectContainer getEffectDag() {
            return this.props.getEffectDag();
        }

        @Override
        public boolean isSetEffectDag() {
            return this.props.isSetEffectDag();
        }

        @Override
        public void setEffectDag(CTEffectContainer effectDag) {
            this.props.setEffectDag(effectDag);
        }

        @Override
        public CTEffectContainer addNewEffectDag() {
            return this.props.addNewEffectDag();
        }

        @Override
        public void unsetEffectDag() {
            this.props.unsetEffectDag();
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    private static class ShapeDelegate
    implements XSLFFillProperties,
    XSLFGeometryProperties,
    XSLFEffectProperties {
        final CTShapeProperties props;

        ShapeDelegate(CTShapeProperties props) {
            this.props = props;
        }

        @Override
        public CTNoFillProperties getNoFill() {
            return this.props.getNoFill();
        }

        @Override
        public boolean isSetNoFill() {
            return this.props.isSetNoFill();
        }

        @Override
        public void setNoFill(CTNoFillProperties noFill) {
            this.props.setNoFill(noFill);
        }

        @Override
        public CTNoFillProperties addNewNoFill() {
            return this.props.addNewNoFill();
        }

        @Override
        public void unsetNoFill() {
            this.props.unsetNoFill();
        }

        @Override
        public CTSolidColorFillProperties getSolidFill() {
            return this.props.getSolidFill();
        }

        @Override
        public boolean isSetSolidFill() {
            return this.props.isSetSolidFill();
        }

        @Override
        public void setSolidFill(CTSolidColorFillProperties solidFill) {
            this.props.setSolidFill(solidFill);
        }

        @Override
        public CTSolidColorFillProperties addNewSolidFill() {
            return this.props.addNewSolidFill();
        }

        @Override
        public void unsetSolidFill() {
            this.props.unsetSolidFill();
        }

        @Override
        public CTGradientFillProperties getGradFill() {
            return this.props.getGradFill();
        }

        @Override
        public boolean isSetGradFill() {
            return this.props.isSetGradFill();
        }

        @Override
        public void setGradFill(CTGradientFillProperties gradFill) {
            this.props.setGradFill(gradFill);
        }

        @Override
        public CTGradientFillProperties addNewGradFill() {
            return this.props.addNewGradFill();
        }

        @Override
        public void unsetGradFill() {
            this.props.unsetGradFill();
        }

        @Override
        public CTBlipFillProperties getBlipFill() {
            return this.props.getBlipFill();
        }

        @Override
        public boolean isSetBlipFill() {
            return this.props.isSetBlipFill();
        }

        @Override
        public void setBlipFill(CTBlipFillProperties blipFill) {
            this.props.setBlipFill(blipFill);
        }

        @Override
        public CTBlipFillProperties addNewBlipFill() {
            return this.props.addNewBlipFill();
        }

        @Override
        public void unsetBlipFill() {
            this.props.unsetBlipFill();
        }

        @Override
        public CTPatternFillProperties getPattFill() {
            return this.props.getPattFill();
        }

        @Override
        public boolean isSetPattFill() {
            return this.props.isSetPattFill();
        }

        @Override
        public void setPattFill(CTPatternFillProperties pattFill) {
            this.props.setPattFill(pattFill);
        }

        @Override
        public CTPatternFillProperties addNewPattFill() {
            return this.props.addNewPattFill();
        }

        @Override
        public void unsetPattFill() {
            this.props.unsetPattFill();
        }

        @Override
        public CTGroupFillProperties getGrpFill() {
            return this.props.getGrpFill();
        }

        @Override
        public boolean isSetGrpFill() {
            return this.props.isSetGrpFill();
        }

        @Override
        public void setGrpFill(CTGroupFillProperties grpFill) {
            this.props.setGrpFill(grpFill);
        }

        @Override
        public CTGroupFillProperties addNewGrpFill() {
            return this.props.addNewGrpFill();
        }

        @Override
        public void unsetGrpFill() {
            this.props.unsetGrpFill();
        }

        @Override
        public CTCustomGeometry2D getCustGeom() {
            return this.props.getCustGeom();
        }

        @Override
        public boolean isSetCustGeom() {
            return this.props.isSetCustGeom();
        }

        @Override
        public void setCustGeom(CTCustomGeometry2D custGeom) {
            this.props.setCustGeom(custGeom);
        }

        @Override
        public CTCustomGeometry2D addNewCustGeom() {
            return this.props.addNewCustGeom();
        }

        @Override
        public void unsetCustGeom() {
            this.props.unsetCustGeom();
        }

        @Override
        public CTPresetGeometry2D getPrstGeom() {
            return this.props.getPrstGeom();
        }

        @Override
        public boolean isSetPrstGeom() {
            return this.props.isSetPrstGeom();
        }

        @Override
        public void setPrstGeom(CTPresetGeometry2D prstGeom) {
            this.props.setPrstGeom(prstGeom);
        }

        @Override
        public CTPresetGeometry2D addNewPrstGeom() {
            return this.props.addNewPrstGeom();
        }

        @Override
        public void unsetPrstGeom() {
            this.props.unsetPrstGeom();
        }

        @Override
        public CTEffectList getEffectLst() {
            return this.props.getEffectLst();
        }

        @Override
        public boolean isSetEffectLst() {
            return this.props.isSetEffectLst();
        }

        @Override
        public void setEffectLst(CTEffectList effectLst) {
            this.props.setEffectLst(effectLst);
        }

        @Override
        public CTEffectList addNewEffectLst() {
            return this.props.addNewEffectLst();
        }

        @Override
        public void unsetEffectLst() {
            this.props.unsetEffectLst();
        }

        @Override
        public CTEffectContainer getEffectDag() {
            return this.props.getEffectDag();
        }

        @Override
        public boolean isSetEffectDag() {
            return this.props.isSetEffectDag();
        }

        @Override
        public void setEffectDag(CTEffectContainer effectDag) {
            this.props.setEffectDag(effectDag);
        }

        @Override
        public CTEffectContainer addNewEffectDag() {
            return this.props.addNewEffectDag();
        }

        @Override
        public void unsetEffectDag() {
            this.props.unsetEffectDag();
        }

        @Override
        public boolean isSetMatrixStyle() {
            return false;
        }

        @Override
        public CTStyleMatrixReference getMatrixStyle() {
            return null;
        }

        @Override
        public boolean isLineStyle() {
            return false;
        }
    }

    public static interface XSLFEffectProperties {
        public CTEffectList getEffectLst();

        public boolean isSetEffectLst();

        public void setEffectLst(CTEffectList var1);

        public CTEffectList addNewEffectLst();

        public void unsetEffectLst();

        public CTEffectContainer getEffectDag();

        public boolean isSetEffectDag();

        public void setEffectDag(CTEffectContainer var1);

        public CTEffectContainer addNewEffectDag();

        public void unsetEffectDag();
    }

    public static interface XSLFGeometryProperties {
        public CTCustomGeometry2D getCustGeom();

        public boolean isSetCustGeom();

        public void setCustGeom(CTCustomGeometry2D var1);

        public CTCustomGeometry2D addNewCustGeom();

        public void unsetCustGeom();

        public CTPresetGeometry2D getPrstGeom();

        public boolean isSetPrstGeom();

        public void setPrstGeom(CTPresetGeometry2D var1);

        public CTPresetGeometry2D addNewPrstGeom();

        public void unsetPrstGeom();
    }

    public static interface XSLFFillProperties {
        public CTNoFillProperties getNoFill();

        public boolean isSetNoFill();

        public void setNoFill(CTNoFillProperties var1);

        public CTNoFillProperties addNewNoFill();

        public void unsetNoFill();

        public CTSolidColorFillProperties getSolidFill();

        public boolean isSetSolidFill();

        public void setSolidFill(CTSolidColorFillProperties var1);

        public CTSolidColorFillProperties addNewSolidFill();

        public void unsetSolidFill();

        public CTGradientFillProperties getGradFill();

        public boolean isSetGradFill();

        public void setGradFill(CTGradientFillProperties var1);

        public CTGradientFillProperties addNewGradFill();

        public void unsetGradFill();

        public CTBlipFillProperties getBlipFill();

        public boolean isSetBlipFill();

        public void setBlipFill(CTBlipFillProperties var1);

        public CTBlipFillProperties addNewBlipFill();

        public void unsetBlipFill();

        public CTPatternFillProperties getPattFill();

        public boolean isSetPattFill();

        public void setPattFill(CTPatternFillProperties var1);

        public CTPatternFillProperties addNewPattFill();

        public void unsetPattFill();

        public CTGroupFillProperties getGrpFill();

        public boolean isSetGrpFill();

        public void setGrpFill(CTGroupFillProperties var1);

        public CTGroupFillProperties addNewGrpFill();

        public void unsetGrpFill();

        public boolean isSetMatrixStyle();

        public CTStyleMatrixReference getMatrixStyle();

        public boolean isLineStyle();
    }
}

