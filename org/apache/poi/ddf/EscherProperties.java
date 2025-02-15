/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="5.0.0")
public interface EscherProperties {
    public static final short TRANSFORM__ROTATION = EscherPropertyTypes.TRANSFORM__ROTATION.propNumber;
    public static final short PROTECTION__LOCKROTATION = EscherPropertyTypes.PROTECTION__LOCKROTATION.propNumber;
    public static final short PROTECTION__LOCKASPECTRATIO = EscherPropertyTypes.PROTECTION__LOCKASPECTRATIO.propNumber;
    public static final short PROTECTION__LOCKPOSITION = EscherPropertyTypes.PROTECTION__LOCKPOSITION.propNumber;
    public static final short PROTECTION__LOCKAGAINSTSELECT = EscherPropertyTypes.PROTECTION__LOCKAGAINSTSELECT.propNumber;
    public static final short PROTECTION__LOCKCROPPING = EscherPropertyTypes.PROTECTION__LOCKCROPPING.propNumber;
    public static final short PROTECTION__LOCKVERTICES = EscherPropertyTypes.PROTECTION__LOCKVERTICES.propNumber;
    public static final short PROTECTION__LOCKTEXT = EscherPropertyTypes.PROTECTION__LOCKTEXT.propNumber;
    public static final short PROTECTION__LOCKADJUSTHANDLES = EscherPropertyTypes.PROTECTION__LOCKADJUSTHANDLES.propNumber;
    public static final short PROTECTION__LOCKAGAINSTGROUPING = EscherPropertyTypes.PROTECTION__LOCKAGAINSTGROUPING.propNumber;
    public static final short TEXT__TEXTID = EscherPropertyTypes.TEXT__TEXTID.propNumber;
    public static final short TEXT__TEXTLEFT = EscherPropertyTypes.TEXT__TEXTLEFT.propNumber;
    public static final short TEXT__TEXTTOP = EscherPropertyTypes.TEXT__TEXTTOP.propNumber;
    public static final short TEXT__TEXTRIGHT = EscherPropertyTypes.TEXT__TEXTRIGHT.propNumber;
    public static final short TEXT__TEXTBOTTOM = EscherPropertyTypes.TEXT__TEXTBOTTOM.propNumber;
    public static final short TEXT__WRAPTEXT = EscherPropertyTypes.TEXT__WRAPTEXT.propNumber;
    public static final short TEXT__SCALETEXT = EscherPropertyTypes.TEXT__SCALETEXT.propNumber;
    public static final short TEXT__ANCHORTEXT = EscherPropertyTypes.TEXT__ANCHORTEXT.propNumber;
    public static final short TEXT__TEXTFLOW = EscherPropertyTypes.TEXT__TEXTFLOW.propNumber;
    public static final short TEXT__FONTROTATION = EscherPropertyTypes.TEXT__FONTROTATION.propNumber;
    public static final short TEXT__IDOFNEXTSHAPE = EscherPropertyTypes.TEXT__IDOFNEXTSHAPE.propNumber;
    public static final short TEXT__BIDIR = EscherPropertyTypes.TEXT__BIDIR.propNumber;
    public static final short TEXT__SINGLECLICKSELECTS = EscherPropertyTypes.TEXT__SINGLECLICKSELECTS.propNumber;
    public static final short TEXT__USEHOSTMARGINS = EscherPropertyTypes.TEXT__USEHOSTMARGINS.propNumber;
    public static final short TEXT__ROTATETEXTWITHSHAPE = EscherPropertyTypes.TEXT__ROTATETEXTWITHSHAPE.propNumber;
    public static final short TEXT__SIZESHAPETOFITTEXT = EscherPropertyTypes.TEXT__SIZESHAPETOFITTEXT.propNumber;
    public static final short TEXT__SIZE_TEXT_TO_FIT_SHAPE = EscherPropertyTypes.TEXT__SIZE_TEXT_TO_FIT_SHAPE.propNumber;
    public static final short GEOTEXT__UNICODE = EscherPropertyTypes.GEOTEXT__UNICODE.propNumber;
    public static final short GEOTEXT__RTFTEXT = EscherPropertyTypes.GEOTEXT__RTFTEXT.propNumber;
    public static final short GEOTEXT__ALIGNMENTONCURVE = EscherPropertyTypes.GEOTEXT__ALIGNMENTONCURVE.propNumber;
    public static final short GEOTEXT__DEFAULTPOINTSIZE = EscherPropertyTypes.GEOTEXT__DEFAULTPOINTSIZE.propNumber;
    public static final short GEOTEXT__TEXTSPACING = EscherPropertyTypes.GEOTEXT__TEXTSPACING.propNumber;
    public static final short GEOTEXT__FONTFAMILYNAME = EscherPropertyTypes.GEOTEXT__FONTFAMILYNAME.propNumber;
    public static final short GEOTEXT__REVERSEROWORDER = EscherPropertyTypes.GEOTEXT__REVERSEROWORDER.propNumber;
    public static final short GEOTEXT__HASTEXTEFFECT = EscherPropertyTypes.GEOTEXT__HASTEXTEFFECT.propNumber;
    public static final short GEOTEXT__ROTATECHARACTERS = EscherPropertyTypes.GEOTEXT__ROTATECHARACTERS.propNumber;
    public static final short GEOTEXT__KERNCHARACTERS = EscherPropertyTypes.GEOTEXT__KERNCHARACTERS.propNumber;
    public static final short GEOTEXT__TIGHTORTRACK = EscherPropertyTypes.GEOTEXT__TIGHTORTRACK.propNumber;
    public static final short GEOTEXT__STRETCHTOFITSHAPE = EscherPropertyTypes.GEOTEXT__STRETCHTOFITSHAPE.propNumber;
    public static final short GEOTEXT__CHARBOUNDINGBOX = EscherPropertyTypes.GEOTEXT__CHARBOUNDINGBOX.propNumber;
    public static final short GEOTEXT__SCALETEXTONPATH = EscherPropertyTypes.GEOTEXT__SCALETEXTONPATH.propNumber;
    public static final short GEOTEXT__STRETCHCHARHEIGHT = EscherPropertyTypes.GEOTEXT__STRETCHCHARHEIGHT.propNumber;
    public static final short GEOTEXT__NOMEASUREALONGPATH = EscherPropertyTypes.GEOTEXT__NOMEASUREALONGPATH.propNumber;
    public static final short GEOTEXT__BOLDFONT = EscherPropertyTypes.GEOTEXT__BOLDFONT.propNumber;
    public static final short GEOTEXT__ITALICFONT = EscherPropertyTypes.GEOTEXT__ITALICFONT.propNumber;
    public static final short GEOTEXT__UNDERLINEFONT = EscherPropertyTypes.GEOTEXT__UNDERLINEFONT.propNumber;
    public static final short GEOTEXT__SHADOWFONT = EscherPropertyTypes.GEOTEXT__SHADOWFONT.propNumber;
    public static final short GEOTEXT__SMALLCAPSFONT = EscherPropertyTypes.GEOTEXT__SMALLCAPSFONT.propNumber;
    public static final short GEOTEXT__STRIKETHROUGHFONT = EscherPropertyTypes.GEOTEXT__STRIKETHROUGHFONT.propNumber;
    public static final short BLIP__CROPFROMTOP = EscherPropertyTypes.BLIP__CROPFROMTOP.propNumber;
    public static final short BLIP__CROPFROMBOTTOM = EscherPropertyTypes.BLIP__CROPFROMBOTTOM.propNumber;
    public static final short BLIP__CROPFROMLEFT = EscherPropertyTypes.BLIP__CROPFROMLEFT.propNumber;
    public static final short BLIP__CROPFROMRIGHT = EscherPropertyTypes.BLIP__CROPFROMRIGHT.propNumber;
    public static final short BLIP__BLIPTODISPLAY = EscherPropertyTypes.BLIP__BLIPTODISPLAY.propNumber;
    public static final short BLIP__BLIPFILENAME = EscherPropertyTypes.BLIP__BLIPFILENAME.propNumber;
    public static final short BLIP__BLIPFLAGS = EscherPropertyTypes.BLIP__BLIPFLAGS.propNumber;
    public static final short BLIP__TRANSPARENTCOLOR = EscherPropertyTypes.BLIP__TRANSPARENTCOLOR.propNumber;
    public static final short BLIP__CONTRASTSETTING = EscherPropertyTypes.BLIP__CONTRASTSETTING.propNumber;
    public static final short BLIP__BRIGHTNESSSETTING = EscherPropertyTypes.BLIP__BRIGHTNESSSETTING.propNumber;
    public static final short BLIP__GAMMA = EscherPropertyTypes.BLIP__GAMMA.propNumber;
    public static final short BLIP__PICTUREID = EscherPropertyTypes.BLIP__PICTUREID.propNumber;
    public static final short BLIP__DOUBLEMOD = EscherPropertyTypes.BLIP__DOUBLEMOD.propNumber;
    public static final short BLIP__PICTUREFILLMOD = EscherPropertyTypes.BLIP__PICTUREFILLMOD.propNumber;
    public static final short BLIP__PICTURELINE = EscherPropertyTypes.BLIP__PICTURELINE.propNumber;
    public static final short BLIP__PRINTBLIP = EscherPropertyTypes.BLIP__PRINTBLIP.propNumber;
    public static final short BLIP__PRINTBLIPFILENAME = EscherPropertyTypes.BLIP__PRINTBLIPFILENAME.propNumber;
    public static final short BLIP__PRINTFLAGS = EscherPropertyTypes.BLIP__PRINTFLAGS.propNumber;
    public static final short BLIP__NOHITTESTPICTURE = EscherPropertyTypes.BLIP__NOHITTESTPICTURE.propNumber;
    public static final short BLIP__PICTUREGRAY = EscherPropertyTypes.BLIP__PICTUREGRAY.propNumber;
    public static final short BLIP__PICTUREBILEVEL = EscherPropertyTypes.BLIP__PICTUREBILEVEL.propNumber;
    public static final short BLIP__PICTUREACTIVE = EscherPropertyTypes.BLIP__PICTUREACTIVE.propNumber;
    public static final short GEOMETRY__LEFT = EscherPropertyTypes.GEOMETRY__LEFT.propNumber;
    public static final short GEOMETRY__TOP = EscherPropertyTypes.GEOMETRY__TOP.propNumber;
    public static final short GEOMETRY__RIGHT = EscherPropertyTypes.GEOMETRY__RIGHT.propNumber;
    public static final short GEOMETRY__BOTTOM = EscherPropertyTypes.GEOMETRY__BOTTOM.propNumber;
    public static final short GEOMETRY__SHAPEPATH = EscherPropertyTypes.GEOMETRY__SHAPEPATH.propNumber;
    public static final short GEOMETRY__VERTICES = EscherPropertyTypes.GEOMETRY__VERTICES.propNumber;
    public static final short GEOMETRY__SEGMENTINFO = EscherPropertyTypes.GEOMETRY__SEGMENTINFO.propNumber;
    public static final short GEOMETRY__ADJUSTVALUE = EscherPropertyTypes.GEOMETRY__ADJUSTVALUE.propNumber;
    public static final short GEOMETRY__ADJUST2VALUE = EscherPropertyTypes.GEOMETRY__ADJUST2VALUE.propNumber;
    public static final short GEOMETRY__ADJUST3VALUE = EscherPropertyTypes.GEOMETRY__ADJUST3VALUE.propNumber;
    public static final short GEOMETRY__ADJUST4VALUE = EscherPropertyTypes.GEOMETRY__ADJUST4VALUE.propNumber;
    public static final short GEOMETRY__ADJUST5VALUE = EscherPropertyTypes.GEOMETRY__ADJUST5VALUE.propNumber;
    public static final short GEOMETRY__ADJUST6VALUE = EscherPropertyTypes.GEOMETRY__ADJUST6VALUE.propNumber;
    public static final short GEOMETRY__ADJUST7VALUE = EscherPropertyTypes.GEOMETRY__ADJUST7VALUE.propNumber;
    public static final short GEOMETRY__ADJUST8VALUE = EscherPropertyTypes.GEOMETRY__ADJUST8VALUE.propNumber;
    public static final short GEOMETRY__ADJUST9VALUE = EscherPropertyTypes.GEOMETRY__ADJUST9VALUE.propNumber;
    public static final short GEOMETRY__ADJUST10VALUE = EscherPropertyTypes.GEOMETRY__ADJUST10VALUE.propNumber;
    public static final short GEOMETRY__PCONNECTIONSITES = EscherPropertyTypes.GEOMETRY__PCONNECTIONSITES.propNumber;
    public static final short GEOMETRY__PCONNECTIONSITESDIR = EscherPropertyTypes.GEOMETRY__PCONNECTIONSITESDIR.propNumber;
    public static final short GEOMETRY__XLIMO = EscherPropertyTypes.GEOMETRY__XLIMO.propNumber;
    public static final short GEOMETRY__YLIMO = EscherPropertyTypes.GEOMETRY__YLIMO.propNumber;
    public static final short GEOMETRY__PADJUSTHANDLES = EscherPropertyTypes.GEOMETRY__PADJUSTHANDLES.propNumber;
    public static final short GEOMETRY__PGUIDES = EscherPropertyTypes.GEOMETRY__PGUIDES.propNumber;
    public static final short GEOMETRY__PINSCRIBE = EscherPropertyTypes.GEOMETRY__PINSCRIBE.propNumber;
    public static final short GEOMETRY__CXK = EscherPropertyTypes.GEOMETRY__CXK.propNumber;
    public static final short GEOMETRY__PFRAGMENTS = EscherPropertyTypes.GEOMETRY__PFRAGMENTS.propNumber;
    public static final short GEOMETRY__SHADOWok = EscherPropertyTypes.GEOMETRY__SHADOWOK.propNumber;
    public static final short GEOMETRY__3DOK = EscherPropertyTypes.GEOMETRY__3DOK.propNumber;
    public static final short GEOMETRY__LINEOK = EscherPropertyTypes.GEOMETRY__LINEOK.propNumber;
    public static final short GEOMETRY__GEOTEXTOK = EscherPropertyTypes.GEOMETRY__GEOTEXTOK.propNumber;
    public static final short GEOMETRY__FILLSHADESHAPEOK = EscherPropertyTypes.GEOMETRY__FILLSHADESHAPEOK.propNumber;
    public static final short GEOMETRY__FILLOK = EscherPropertyTypes.GEOMETRY__FILLOK.propNumber;
    public static final short FILL__FILLTYPE = EscherPropertyTypes.FILL__FILLTYPE.propNumber;
    public static final short FILL__FILLCOLOR = EscherPropertyTypes.FILL__FILLCOLOR.propNumber;
    public static final short FILL__FILLOPACITY = EscherPropertyTypes.FILL__FILLOPACITY.propNumber;
    public static final short FILL__FILLBACKCOLOR = EscherPropertyTypes.FILL__FILLBACKCOLOR.propNumber;
    public static final short FILL__BACKOPACITY = EscherPropertyTypes.FILL__BACKOPACITY.propNumber;
    public static final short FILL__CRMOD = EscherPropertyTypes.FILL__CRMOD.propNumber;
    public static final short FILL__PATTERNTEXTURE = EscherPropertyTypes.FILL__PATTERNTEXTURE.propNumber;
    public static final short FILL__BLIPFILENAME = EscherPropertyTypes.FILL__BLIPFILENAME.propNumber;
    public static final short FILL__BLIPFLAGS = EscherPropertyTypes.FILL__BLIPFLAGS.propNumber;
    public static final short FILL__WIDTH = EscherPropertyTypes.FILL__WIDTH.propNumber;
    public static final short FILL__HEIGHT = EscherPropertyTypes.FILL__HEIGHT.propNumber;
    public static final short FILL__ANGLE = EscherPropertyTypes.FILL__ANGLE.propNumber;
    public static final short FILL__FOCUS = EscherPropertyTypes.FILL__FOCUS.propNumber;
    public static final short FILL__TOLEFT = EscherPropertyTypes.FILL__TOLEFT.propNumber;
    public static final short FILL__TOTOP = EscherPropertyTypes.FILL__TOTOP.propNumber;
    public static final short FILL__TORIGHT = EscherPropertyTypes.FILL__TORIGHT.propNumber;
    public static final short FILL__TOBOTTOM = EscherPropertyTypes.FILL__TOBOTTOM.propNumber;
    public static final short FILL__RECTLEFT = EscherPropertyTypes.FILL__RECTLEFT.propNumber;
    public static final short FILL__RECTTOP = EscherPropertyTypes.FILL__RECTTOP.propNumber;
    public static final short FILL__RECTRIGHT = EscherPropertyTypes.FILL__RECTRIGHT.propNumber;
    public static final short FILL__RECTBOTTOM = EscherPropertyTypes.FILL__RECTBOTTOM.propNumber;
    public static final short FILL__DZTYPE = EscherPropertyTypes.FILL__DZTYPE.propNumber;
    public static final short FILL__SHADEPRESET = EscherPropertyTypes.FILL__SHADEPRESET.propNumber;
    public static final short FILL__SHADECOLORS = EscherPropertyTypes.FILL__SHADECOLORS.propNumber;
    public static final short FILL__ORIGINX = EscherPropertyTypes.FILL__ORIGINX.propNumber;
    public static final short FILL__ORIGINY = EscherPropertyTypes.FILL__ORIGINY.propNumber;
    public static final short FILL__SHAPEORIGINX = EscherPropertyTypes.FILL__SHAPEORIGINX.propNumber;
    public static final short FILL__SHAPEORIGINY = EscherPropertyTypes.FILL__SHAPEORIGINY.propNumber;
    public static final short FILL__SHADETYPE = EscherPropertyTypes.FILL__SHADETYPE.propNumber;
    public static final short FILL__FILLED = EscherPropertyTypes.FILL__FILLED.propNumber;
    public static final short FILL__HITTESTFILL = EscherPropertyTypes.FILL__HITTESTFILL.propNumber;
    public static final short FILL__SHAPE = EscherPropertyTypes.FILL__SHAPE.propNumber;
    public static final short FILL__USERECT = EscherPropertyTypes.FILL__USERECT.propNumber;
    public static final short FILL__NOFILLHITTEST = EscherPropertyTypes.FILL__NOFILLHITTEST.propNumber;
    public static final short LINESTYLE__COLOR = EscherPropertyTypes.LINESTYLE__COLOR.propNumber;
    public static final short LINESTYLE__OPACITY = EscherPropertyTypes.LINESTYLE__OPACITY.propNumber;
    public static final short LINESTYLE__BACKCOLOR = EscherPropertyTypes.LINESTYLE__BACKCOLOR.propNumber;
    public static final short LINESTYLE__CRMOD = EscherPropertyTypes.LINESTYLE__CRMOD.propNumber;
    public static final short LINESTYLE__LINETYPE = EscherPropertyTypes.LINESTYLE__LINETYPE.propNumber;
    public static final short LINESTYLE__FILLBLIP = EscherPropertyTypes.LINESTYLE__FILLBLIP.propNumber;
    public static final short LINESTYLE__FILLBLIPNAME = EscherPropertyTypes.LINESTYLE__FILLBLIPNAME.propNumber;
    public static final short LINESTYLE__FILLBLIPFLAGS = EscherPropertyTypes.LINESTYLE__FILLBLIPFLAGS.propNumber;
    public static final short LINESTYLE__FILLWIDTH = EscherPropertyTypes.LINESTYLE__FILLWIDTH.propNumber;
    public static final short LINESTYLE__FILLHEIGHT = EscherPropertyTypes.LINESTYLE__FILLHEIGHT.propNumber;
    public static final short LINESTYLE__FILLDZTYPE = EscherPropertyTypes.LINESTYLE__FILLDZTYPE.propNumber;
    public static final short LINESTYLE__LINEWIDTH = EscherPropertyTypes.LINESTYLE__LINEWIDTH.propNumber;
    public static final short LINESTYLE__LINEMITERLIMIT = EscherPropertyTypes.LINESTYLE__LINEMITERLIMIT.propNumber;
    public static final short LINESTYLE__LINESTYLE = EscherPropertyTypes.LINESTYLE__LINESTYLE.propNumber;
    public static final short LINESTYLE__LINEDASHING = EscherPropertyTypes.LINESTYLE__LINEDASHING.propNumber;
    public static final short LINESTYLE__LINEDASHSTYLE = EscherPropertyTypes.LINESTYLE__LINEDASHSTYLE.propNumber;
    public static final short LINESTYLE__LINESTARTARROWHEAD = EscherPropertyTypes.LINESTYLE__LINESTARTARROWHEAD.propNumber;
    public static final short LINESTYLE__LINEENDARROWHEAD = EscherPropertyTypes.LINESTYLE__LINEENDARROWHEAD.propNumber;
    public static final short LINESTYLE__LINESTARTARROWWIDTH = EscherPropertyTypes.LINESTYLE__LINESTARTARROWWIDTH.propNumber;
    public static final short LINESTYLE__LINESTARTARROWLENGTH = EscherPropertyTypes.LINESTYLE__LINESTARTARROWLENGTH.propNumber;
    public static final short LINESTYLE__LINEENDARROWWIDTH = EscherPropertyTypes.LINESTYLE__LINEENDARROWWIDTH.propNumber;
    public static final short LINESTYLE__LINEENDARROWLENGTH = EscherPropertyTypes.LINESTYLE__LINEENDARROWLENGTH.propNumber;
    public static final short LINESTYLE__LINEJOINSTYLE = EscherPropertyTypes.LINESTYLE__LINEJOINSTYLE.propNumber;
    public static final short LINESTYLE__LINEENDCAPSTYLE = EscherPropertyTypes.LINESTYLE__LINEENDCAPSTYLE.propNumber;
    public static final short LINESTYLE__ARROWHEADSOK = EscherPropertyTypes.LINESTYLE__ARROWHEADSOK.propNumber;
    public static final short LINESTYLE__ANYLINE = EscherPropertyTypes.LINESTYLE__ANYLINE.propNumber;
    public static final short LINESTYLE__HITLINETEST = EscherPropertyTypes.LINESTYLE__HITLINETEST.propNumber;
    public static final short LINESTYLE__LINEFILLSHAPE = EscherPropertyTypes.LINESTYLE__LINEFILLSHAPE.propNumber;
    public static final short LINESTYLE__NOLINEDRAWDASH = EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH.propNumber;
    public static final short LINESTYLE__NOLINEDRAWDASH_LEFT = EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH_LEFT.propNumber;
    public static final short LINESTYLE__NOLINEDRAWDASH_TOP = EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH_TOP.propNumber;
    public static final short LINESTYLE__NOLINEDRAWDASH_BOTTOM = EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH_BOTTOM.propNumber;
    public static final short LINESTYLE__NOLINEDRAWDASH_RIGHT = EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH_RIGHT.propNumber;
    public static final short SHADOWSTYLE__TYPE = EscherPropertyTypes.SHADOWSTYLE__TYPE.propNumber;
    public static final short SHADOWSTYLE__COLOR = EscherPropertyTypes.SHADOWSTYLE__COLOR.propNumber;
    public static final short SHADOWSTYLE__HIGHLIGHT = EscherPropertyTypes.SHADOWSTYLE__HIGHLIGHT.propNumber;
    public static final short SHADOWSTYLE__CRMOD = EscherPropertyTypes.SHADOWSTYLE__CRMOD.propNumber;
    public static final short SHADOWSTYLE__OPACITY = EscherPropertyTypes.SHADOWSTYLE__OPACITY.propNumber;
    public static final short SHADOWSTYLE__OFFSETX = EscherPropertyTypes.SHADOWSTYLE__OFFSETX.propNumber;
    public static final short SHADOWSTYLE__OFFSETY = EscherPropertyTypes.SHADOWSTYLE__OFFSETY.propNumber;
    public static final short SHADOWSTYLE__SECONDOFFSETX = EscherPropertyTypes.SHADOWSTYLE__SECONDOFFSETX.propNumber;
    public static final short SHADOWSTYLE__SECONDOFFSETY = EscherPropertyTypes.SHADOWSTYLE__SECONDOFFSETY.propNumber;
    public static final short SHADOWSTYLE__SCALEXTOX = EscherPropertyTypes.SHADOWSTYLE__SCALEXTOX.propNumber;
    public static final short SHADOWSTYLE__SCALEYTOX = EscherPropertyTypes.SHADOWSTYLE__SCALEYTOX.propNumber;
    public static final short SHADOWSTYLE__SCALEXTOY = EscherPropertyTypes.SHADOWSTYLE__SCALEXTOY.propNumber;
    public static final short SHADOWSTYLE__SCALEYTOY = EscherPropertyTypes.SHADOWSTYLE__SCALEYTOY.propNumber;
    public static final short SHADOWSTYLE__PERSPECTIVEX = EscherPropertyTypes.SHADOWSTYLE__PERSPECTIVEX.propNumber;
    public static final short SHADOWSTYLE__PERSPECTIVEY = EscherPropertyTypes.SHADOWSTYLE__PERSPECTIVEY.propNumber;
    public static final short SHADOWSTYLE__WEIGHT = EscherPropertyTypes.SHADOWSTYLE__WEIGHT.propNumber;
    public static final short SHADOWSTYLE__ORIGINX = EscherPropertyTypes.SHADOWSTYLE__ORIGINX.propNumber;
    public static final short SHADOWSTYLE__ORIGINY = EscherPropertyTypes.SHADOWSTYLE__ORIGINY.propNumber;
    public static final short SHADOWSTYLE__SHADOW = EscherPropertyTypes.SHADOWSTYLE__SHADOW.propNumber;
    public static final short SHADOWSTYLE__SHADOWOBSURED = EscherPropertyTypes.SHADOWSTYLE__SHADOWOBSURED.propNumber;
    public static final short PERSPECTIVE__TYPE = EscherPropertyTypes.PERSPECTIVE__TYPE.propNumber;
    public static final short PERSPECTIVE__OFFSETX = EscherPropertyTypes.PERSPECTIVE__OFFSETX.propNumber;
    public static final short PERSPECTIVE__OFFSETY = EscherPropertyTypes.PERSPECTIVE__OFFSETY.propNumber;
    public static final short PERSPECTIVE__SCALEXTOX = EscherPropertyTypes.PERSPECTIVE__SCALEXTOX.propNumber;
    public static final short PERSPECTIVE__SCALEYTOX = EscherPropertyTypes.PERSPECTIVE__SCALEYTOX.propNumber;
    public static final short PERSPECTIVE__SCALEXTOY = EscherPropertyTypes.PERSPECTIVE__SCALEXTOY.propNumber;
    public static final short PERSPECTIVE__SCALEYTOY = EscherPropertyTypes.PERSPECTIVE__SCALEYTOY.propNumber;
    public static final short PERSPECTIVE__PERSPECTIVEX = EscherPropertyTypes.PERSPECTIVE__PERSPECTIVEX.propNumber;
    public static final short PERSPECTIVE__PERSPECTIVEY = EscherPropertyTypes.PERSPECTIVE__PERSPECTIVEY.propNumber;
    public static final short PERSPECTIVE__WEIGHT = EscherPropertyTypes.PERSPECTIVE__WEIGHT.propNumber;
    public static final short PERSPECTIVE__ORIGINX = EscherPropertyTypes.PERSPECTIVE__ORIGINX.propNumber;
    public static final short PERSPECTIVE__ORIGINY = EscherPropertyTypes.PERSPECTIVE__ORIGINY.propNumber;
    public static final short PERSPECTIVE__PERSPECTIVEON = EscherPropertyTypes.PERSPECTIVE__PERSPECTIVEON.propNumber;
    public static final short THREED__SPECULARAMOUNT = EscherPropertyTypes.THREED__SPECULARAMOUNT.propNumber;
    public static final short THREED__DIFFUSEAMOUNT = EscherPropertyTypes.THREED__DIFFUSEAMOUNT.propNumber;
    public static final short THREED__SHININESS = EscherPropertyTypes.THREED__SHININESS.propNumber;
    public static final short THREED__EDGETHICKNESS = EscherPropertyTypes.THREED__EDGETHICKNESS.propNumber;
    public static final short THREED__EXTRUDEFORWARD = EscherPropertyTypes.THREED__EXTRUDEFORWARD.propNumber;
    public static final short THREED__EXTRUDEBACKWARD = EscherPropertyTypes.THREED__EXTRUDEBACKWARD.propNumber;
    public static final short THREED__EXTRUDEPLANE = EscherPropertyTypes.THREED__EXTRUDEPLANE.propNumber;
    public static final short THREED__EXTRUSIONCOLOR = EscherPropertyTypes.THREED__EXTRUSIONCOLOR.propNumber;
    public static final short THREED__CRMOD = EscherPropertyTypes.THREED__CRMOD.propNumber;
    public static final short THREED__3DEFFECT = EscherPropertyTypes.THREED__3DEFFECT.propNumber;
    public static final short THREED__METALLIC = EscherPropertyTypes.THREED__METALLIC.propNumber;
    public static final short THREED__USEEXTRUSIONCOLOR = EscherPropertyTypes.THREED__USEEXTRUSIONCOLOR.propNumber;
    public static final short THREED__LIGHTFACE = EscherPropertyTypes.THREED__LIGHTFACE.propNumber;
    public static final short THREEDSTYLE__YROTATIONANGLE = EscherPropertyTypes.THREEDSTYLE__YROTATIONANGLE.propNumber;
    public static final short THREEDSTYLE__XROTATIONANGLE = EscherPropertyTypes.THREEDSTYLE__XROTATIONANGLE.propNumber;
    public static final short THREEDSTYLE__ROTATIONAXISX = EscherPropertyTypes.THREEDSTYLE__ROTATIONAXISX.propNumber;
    public static final short THREEDSTYLE__ROTATIONAXISY = EscherPropertyTypes.THREEDSTYLE__ROTATIONAXISY.propNumber;
    public static final short THREEDSTYLE__ROTATIONAXISZ = EscherPropertyTypes.THREEDSTYLE__ROTATIONAXISZ.propNumber;
    public static final short THREEDSTYLE__ROTATIONANGLE = EscherPropertyTypes.THREEDSTYLE__ROTATIONANGLE.propNumber;
    public static final short THREEDSTYLE__ROTATIONCENTERX = EscherPropertyTypes.THREEDSTYLE__ROTATIONCENTERX.propNumber;
    public static final short THREEDSTYLE__ROTATIONCENTERY = EscherPropertyTypes.THREEDSTYLE__ROTATIONCENTERY.propNumber;
    public static final short THREEDSTYLE__ROTATIONCENTERZ = EscherPropertyTypes.THREEDSTYLE__ROTATIONCENTERZ.propNumber;
    public static final short THREEDSTYLE__RENDERMODE = EscherPropertyTypes.THREEDSTYLE__RENDERMODE.propNumber;
    public static final short THREEDSTYLE__TOLERANCE = EscherPropertyTypes.THREEDSTYLE__TOLERANCE.propNumber;
    public static final short THREEDSTYLE__XVIEWPOINT = EscherPropertyTypes.THREEDSTYLE__XVIEWPOINT.propNumber;
    public static final short THREEDSTYLE__YVIEWPOINT = EscherPropertyTypes.THREEDSTYLE__YVIEWPOINT.propNumber;
    public static final short THREEDSTYLE__ZVIEWPOINT = EscherPropertyTypes.THREEDSTYLE__ZVIEWPOINT.propNumber;
    public static final short THREEDSTYLE__ORIGINX = EscherPropertyTypes.THREEDSTYLE__ORIGINX.propNumber;
    public static final short THREEDSTYLE__ORIGINY = EscherPropertyTypes.THREEDSTYLE__ORIGINY.propNumber;
    public static final short THREEDSTYLE__SKEWANGLE = EscherPropertyTypes.THREEDSTYLE__SKEWANGLE.propNumber;
    public static final short THREEDSTYLE__SKEWAMOUNT = EscherPropertyTypes.THREEDSTYLE__SKEWAMOUNT.propNumber;
    public static final short THREEDSTYLE__AMBIENTINTENSITY = EscherPropertyTypes.THREEDSTYLE__AMBIENTINTENSITY.propNumber;
    public static final short THREEDSTYLE__KEYX = EscherPropertyTypes.THREEDSTYLE__KEYX.propNumber;
    public static final short THREEDSTYLE__KEYY = EscherPropertyTypes.THREEDSTYLE__KEYY.propNumber;
    public static final short THREEDSTYLE__KEYZ = EscherPropertyTypes.THREEDSTYLE__KEYZ.propNumber;
    public static final short THREEDSTYLE__KEYINTENSITY = EscherPropertyTypes.THREEDSTYLE__KEYINTENSITY.propNumber;
    public static final short THREEDSTYLE__FILLX = EscherPropertyTypes.THREEDSTYLE__FILLX.propNumber;
    public static final short THREEDSTYLE__FILLY = EscherPropertyTypes.THREEDSTYLE__FILLY.propNumber;
    public static final short THREEDSTYLE__FILLZ = EscherPropertyTypes.THREEDSTYLE__FILLZ.propNumber;
    public static final short THREEDSTYLE__FILLINTENSITY = EscherPropertyTypes.THREEDSTYLE__FILLINTENSITY.propNumber;
    public static final short THREEDSTYLE__CONSTRAINROTATION = EscherPropertyTypes.THREEDSTYLE__CONSTRAINROTATION.propNumber;
    public static final short THREEDSTYLE__ROTATIONCENTERAUTO = EscherPropertyTypes.THREEDSTYLE__ROTATIONCENTERAUTO.propNumber;
    public static final short THREEDSTYLE__PARALLEL = EscherPropertyTypes.THREEDSTYLE__PARALLEL.propNumber;
    public static final short THREEDSTYLE__KEYHARSH = EscherPropertyTypes.THREEDSTYLE__KEYHARSH.propNumber;
    public static final short THREEDSTYLE__FILLHARSH = EscherPropertyTypes.THREEDSTYLE__FILLHARSH.propNumber;
    public static final short SHAPE__MASTER = EscherPropertyTypes.SHAPE__MASTER.propNumber;
    public static final short SHAPE__CONNECTORSTYLE = EscherPropertyTypes.SHAPE__CONNECTORSTYLE.propNumber;
    public static final short SHAPE__BLACKANDWHITESETTINGS = EscherPropertyTypes.SHAPE__BLACKANDWHITESETTINGS.propNumber;
    public static final short SHAPE__WMODEPUREBW = EscherPropertyTypes.SHAPE__WMODEPUREBW.propNumber;
    public static final short SHAPE__WMODEBW = EscherPropertyTypes.SHAPE__WMODEBW.propNumber;
    public static final short SHAPE__OLEICON = EscherPropertyTypes.SHAPE__OLEICON.propNumber;
    public static final short SHAPE__PREFERRELATIVERESIZE = EscherPropertyTypes.SHAPE__PREFERRELATIVERESIZE.propNumber;
    public static final short SHAPE__LOCKSHAPETYPE = EscherPropertyTypes.SHAPE__LOCKSHAPETYPE.propNumber;
    public static final short SHAPE__DELETEATTACHEDOBJECT = EscherPropertyTypes.SHAPE__DELETEATTACHEDOBJECT.propNumber;
    public static final short SHAPE__BACKGROUNDSHAPE = EscherPropertyTypes.SHAPE__BACKGROUNDSHAPE.propNumber;
    public static final short CALLOUT__CALLOUTTYPE = EscherPropertyTypes.CALLOUT__CALLOUTTYPE.propNumber;
    public static final short CALLOUT__XYCALLOUTGAP = EscherPropertyTypes.CALLOUT__XYCALLOUTGAP.propNumber;
    public static final short CALLOUT__CALLOUTANGLE = EscherPropertyTypes.CALLOUT__CALLOUTANGLE.propNumber;
    public static final short CALLOUT__CALLOUTDROPTYPE = EscherPropertyTypes.CALLOUT__CALLOUTDROPTYPE.propNumber;
    public static final short CALLOUT__CALLOUTDROPSPECIFIED = EscherPropertyTypes.CALLOUT__CALLOUTDROPSPECIFIED.propNumber;
    public static final short CALLOUT__CALLOUTLENGTHSPECIFIED = EscherPropertyTypes.CALLOUT__CALLOUTLENGTHSPECIFIED.propNumber;
    public static final short CALLOUT__ISCALLOUT = EscherPropertyTypes.CALLOUT__ISCALLOUT.propNumber;
    public static final short CALLOUT__CALLOUTACCENTBAR = EscherPropertyTypes.CALLOUT__CALLOUTACCENTBAR.propNumber;
    public static final short CALLOUT__CALLOUTTEXTBORDER = EscherPropertyTypes.CALLOUT__CALLOUTTEXTBORDER.propNumber;
    public static final short CALLOUT__CALLOUTMINUSX = EscherPropertyTypes.CALLOUT__CALLOUTMINUSX.propNumber;
    public static final short CALLOUT__CALLOUTMINUSY = EscherPropertyTypes.CALLOUT__CALLOUTMINUSY.propNumber;
    public static final short CALLOUT__DROPAUTO = EscherPropertyTypes.CALLOUT__DROPAUTO.propNumber;
    public static final short CALLOUT__LENGTHSPECIFIED = EscherPropertyTypes.CALLOUT__LENGTHSPECIFIED.propNumber;
    public static final short GROUPSHAPE__SHAPENAME = EscherPropertyTypes.GROUPSHAPE__SHAPENAME.propNumber;
    public static final short GROUPSHAPE__DESCRIPTION = EscherPropertyTypes.GROUPSHAPE__DESCRIPTION.propNumber;
    public static final short GROUPSHAPE__HYPERLINK = EscherPropertyTypes.GROUPSHAPE__HYPERLINK.propNumber;
    public static final short GROUPSHAPE__WRAPPOLYGONVERTICES = EscherPropertyTypes.GROUPSHAPE__WRAPPOLYGONVERTICES.propNumber;
    public static final short GROUPSHAPE__WRAPDISTLEFT = EscherPropertyTypes.GROUPSHAPE__WRAPDISTLEFT.propNumber;
    public static final short GROUPSHAPE__WRAPDISTTOP = EscherPropertyTypes.GROUPSHAPE__WRAPDISTTOP.propNumber;
    public static final short GROUPSHAPE__WRAPDISTRIGHT = EscherPropertyTypes.GROUPSHAPE__WRAPDISTRIGHT.propNumber;
    public static final short GROUPSHAPE__WRAPDISTBOTTOM = EscherPropertyTypes.GROUPSHAPE__WRAPDISTBOTTOM.propNumber;
    public static final short GROUPSHAPE__REGROUPID = EscherPropertyTypes.GROUPSHAPE__REGROUPID.propNumber;
    public static final short GROUPSHAPE__UNUSED906 = EscherPropertyTypes.GROUPSHAPE__UNUSED906.propNumber;
    public static final short GROUPSHAPE__TOOLTIP = EscherPropertyTypes.GROUPSHAPE__TOOLTIP.propNumber;
    public static final short GROUPSHAPE__SCRIPT = EscherPropertyTypes.GROUPSHAPE__SCRIPT.propNumber;
    public static final short GROUPSHAPE__POSH = EscherPropertyTypes.GROUPSHAPE__POSH.propNumber;
    public static final short GROUPSHAPE__POSRELH = EscherPropertyTypes.GROUPSHAPE__POSRELH.propNumber;
    public static final short GROUPSHAPE__POSV = EscherPropertyTypes.GROUPSHAPE__POSV.propNumber;
    public static final short GROUPSHAPE__POSRELV = EscherPropertyTypes.GROUPSHAPE__POSRELV.propNumber;
    public static final short GROUPSHAPE__HR_PCT = EscherPropertyTypes.GROUPSHAPE__HR_PCT.propNumber;
    public static final short GROUPSHAPE__HR_ALIGN = EscherPropertyTypes.GROUPSHAPE__HR_ALIGN.propNumber;
    public static final short GROUPSHAPE__HR_HEIGHT = EscherPropertyTypes.GROUPSHAPE__HR_HEIGHT.propNumber;
    public static final short GROUPSHAPE__HR_WIDTH = EscherPropertyTypes.GROUPSHAPE__HR_WIDTH.propNumber;
    public static final short GROUPSHAPE__SCRIPTEXT = EscherPropertyTypes.GROUPSHAPE__SCRIPTEXT.propNumber;
    public static final short GROUPSHAPE__SCRIPTLANG = EscherPropertyTypes.GROUPSHAPE__SCRIPTLANG.propNumber;
    public static final short GROUPSHAPE__BORDERTOPCOLOR = EscherPropertyTypes.GROUPSHAPE__BORDERTOPCOLOR.propNumber;
    public static final short GROUPSHAPE__BORDERLEFTCOLOR = EscherPropertyTypes.GROUPSHAPE__BORDERLEFTCOLOR.propNumber;
    public static final short GROUPSHAPE__BORDERBOTTOMCOLOR = EscherPropertyTypes.GROUPSHAPE__BORDERBOTTOMCOLOR.propNumber;
    public static final short GROUPSHAPE__BORDERRIGHTCOLOR = EscherPropertyTypes.GROUPSHAPE__BORDERRIGHTCOLOR.propNumber;
    public static final short GROUPSHAPE__TABLEPROPERTIES = EscherPropertyTypes.GROUPSHAPE__TABLEPROPERTIES.propNumber;
    public static final short GROUPSHAPE__TABLEROWPROPERTIES = EscherPropertyTypes.GROUPSHAPE__TABLEROWPROPERTIES.propNumber;
    public static final short GROUPSHAPE__WEBBOT = EscherPropertyTypes.GROUPSHAPE__WEBBOT.propNumber;
    public static final short GROUPSHAPE__METROBLOB = EscherPropertyTypes.GROUPSHAPE__METROBLOB.propNumber;
    public static final short GROUPSHAPE__ZORDER = EscherPropertyTypes.GROUPSHAPE__ZORDER.propNumber;
    public static final short GROUPSHAPE__EDITEDWRAP = EscherPropertyTypes.GROUPSHAPE__EDITEDWRAP.propNumber;
    public static final short GROUPSHAPE__BEHINDDOCUMENT = EscherPropertyTypes.GROUPSHAPE__BEHINDDOCUMENT.propNumber;
    public static final short GROUPSHAPE__ONDBLCLICKNOTIFY = EscherPropertyTypes.GROUPSHAPE__ONDBLCLICKNOTIFY.propNumber;
    public static final short GROUPSHAPE__ISBUTTON = EscherPropertyTypes.GROUPSHAPE__ISBUTTON.propNumber;
    public static final short GROUPSHAPE__1DADJUSTMENT = EscherPropertyTypes.GROUPSHAPE__1DADJUSTMENT.propNumber;
    public static final short GROUPSHAPE__HIDDEN = EscherPropertyTypes.GROUPSHAPE__HIDDEN.propNumber;
    public static final short GROUPSHAPE__FLAGS = EscherPropertyTypes.GROUPSHAPE__FLAGS.propNumber;
    public static final short GROUPSHAPE__PRINT = EscherPropertyTypes.GROUPSHAPE__FLAGS.propNumber;

    public static String getPropertyName(short propertyId) {
        return EscherPropertyTypes.forPropertyID((int)propertyId).propName;
    }

    public static byte getPropertyType(short propertyId) {
        return (byte)EscherPropertyTypes.forPropertyID((int)propertyId).holder.ordinal();
    }
}

