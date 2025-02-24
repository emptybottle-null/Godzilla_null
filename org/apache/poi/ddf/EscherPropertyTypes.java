/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ddf.EscherPropertyTypesHolder;

public enum EscherPropertyTypes {
    TRANSFORM__ROTATION(4, "transform.rotation"),
    PROTECTION__LOCKROTATION(119, "protection.lockrotation"),
    PROTECTION__LOCKASPECTRATIO(120, "protection.lockaspectratio"),
    PROTECTION__LOCKPOSITION(121, "protection.lockposition"),
    PROTECTION__LOCKAGAINSTSELECT(122, "protection.lockagainstselect"),
    PROTECTION__LOCKCROPPING(123, "protection.lockcropping"),
    PROTECTION__LOCKVERTICES(124, "protection.lockvertices"),
    PROTECTION__LOCKTEXT(125, "protection.locktext"),
    PROTECTION__LOCKADJUSTHANDLES(126, "protection.lockadjusthandles"),
    PROTECTION__LOCKAGAINSTGROUPING(127, "protection.lockagainstgrouping", EscherPropertyTypesHolder.BOOLEAN),
    TEXT__TEXTID(128, "text.textid"),
    TEXT__TEXTLEFT(129, "text.textleft"),
    TEXT__TEXTTOP(130, "text.texttop"),
    TEXT__TEXTRIGHT(131, "text.textright"),
    TEXT__TEXTBOTTOM(132, "text.textbottom"),
    TEXT__WRAPTEXT(133, "text.wraptext"),
    TEXT__SCALETEXT(134, "text.scaletext"),
    TEXT__ANCHORTEXT(135, "text.anchortext"),
    TEXT__TEXTFLOW(136, "text.textflow"),
    TEXT__FONTROTATION(137, "text.fontrotation"),
    TEXT__IDOFNEXTSHAPE(138, "text.idofnextshape"),
    TEXT__BIDIR(139, "text.bidir"),
    TEXT__SINGLECLICKSELECTS(187, "text.singleclickselects"),
    TEXT__USEHOSTMARGINS(188, "text.usehostmargins"),
    TEXT__ROTATETEXTWITHSHAPE(189, "text.rotatetextwithshape"),
    TEXT__SIZESHAPETOFITTEXT(190, "text.sizeshapetofittext"),
    TEXT__SIZE_TEXT_TO_FIT_SHAPE(191, "text.sizetexttofitshape", EscherPropertyTypesHolder.BOOLEAN),
    GEOTEXT__UNICODE(192, "geotext.unicode"),
    GEOTEXT__RTFTEXT(193, "geotext.rtftext"),
    GEOTEXT__ALIGNMENTONCURVE(194, "geotext.alignmentoncurve"),
    GEOTEXT__DEFAULTPOINTSIZE(195, "geotext.defaultpointsize"),
    GEOTEXT__TEXTSPACING(196, "geotext.textspacing"),
    GEOTEXT__FONTFAMILYNAME(197, "geotext.fontfamilyname"),
    GEOTEXT__REVERSEROWORDER(240, "geotext.reverseroworder"),
    GEOTEXT__HASTEXTEFFECT(241, "geotext.hastexteffect"),
    GEOTEXT__ROTATECHARACTERS(242, "geotext.rotatecharacters"),
    GEOTEXT__KERNCHARACTERS(243, "geotext.kerncharacters"),
    GEOTEXT__TIGHTORTRACK(244, "geotext.tightortrack"),
    GEOTEXT__STRETCHTOFITSHAPE(245, "geotext.stretchtofitshape"),
    GEOTEXT__CHARBOUNDINGBOX(246, "geotext.charboundingbox"),
    GEOTEXT__SCALETEXTONPATH(247, "geotext.scaletextonpath"),
    GEOTEXT__STRETCHCHARHEIGHT(248, "geotext.stretchcharheight"),
    GEOTEXT__NOMEASUREALONGPATH(249, "geotext.nomeasurealongpath"),
    GEOTEXT__BOLDFONT(250, "geotext.boldfont"),
    GEOTEXT__ITALICFONT(251, "geotext.italicfont"),
    GEOTEXT__UNDERLINEFONT(252, "geotext.underlinefont"),
    GEOTEXT__SHADOWFONT(253, "geotext.shadowfont"),
    GEOTEXT__SMALLCAPSFONT(254, "geotext.smallcapsfont"),
    GEOTEXT__STRIKETHROUGHFONT(255, "geotext.strikethroughfont"),
    BLIP__CROPFROMTOP(256, "blip.cropfromtop"),
    BLIP__CROPFROMBOTTOM(257, "blip.cropfrombottom"),
    BLIP__CROPFROMLEFT(258, "blip.cropfromleft"),
    BLIP__CROPFROMRIGHT(259, "blip.cropfromright"),
    BLIP__BLIPTODISPLAY(260, "blip.bliptodisplay"),
    BLIP__BLIPFILENAME(261, "blip.blipfilename"),
    BLIP__BLIPFLAGS(262, "blip.blipflags"),
    BLIP__TRANSPARENTCOLOR(263, "blip.transparentcolor"),
    BLIP__CONTRASTSETTING(264, "blip.contrastsetting"),
    BLIP__BRIGHTNESSSETTING(265, "blip.brightnesssetting"),
    BLIP__GAMMA(266, "blip.gamma"),
    BLIP__PICTUREID(267, "blip.pictureid"),
    BLIP__DOUBLEMOD(268, "blip.doublemod"),
    BLIP__PICTUREFILLMOD(269, "blip.picturefillmod"),
    BLIP__PICTURELINE(270, "blip.pictureline"),
    BLIP__PRINTBLIP(271, "blip.printblip"),
    BLIP__PRINTBLIPFILENAME(272, "blip.printblipfilename"),
    BLIP__PRINTFLAGS(273, "blip.printflags"),
    BLIP__NOHITTESTPICTURE(316, "blip.nohittestpicture"),
    BLIP__PICTUREGRAY(317, "blip.picturegray"),
    BLIP__PICTUREBILEVEL(318, "blip.picturebilevel"),
    BLIP__PICTUREACTIVE(319, "blip.pictureactive"),
    GEOMETRY__LEFT(320, "geometry.left"),
    GEOMETRY__TOP(321, "geometry.top"),
    GEOMETRY__RIGHT(322, "geometry.right"),
    GEOMETRY__BOTTOM(323, "geometry.bottom"),
    GEOMETRY__SHAPEPATH(324, "geometry.shapepath", EscherPropertyTypesHolder.SHAPE_PATH),
    GEOMETRY__VERTICES(325, "geometry.vertices", EscherPropertyTypesHolder.ARRAY),
    GEOMETRY__SEGMENTINFO(326, "geometry.segmentinfo", EscherPropertyTypesHolder.ARRAY),
    GEOMETRY__ADJUSTVALUE(327, "geometry.adjustvalue"),
    GEOMETRY__ADJUST2VALUE(328, "geometry.adjust2value"),
    GEOMETRY__ADJUST3VALUE(329, "geometry.adjust3value"),
    GEOMETRY__ADJUST4VALUE(330, "geometry.adjust4value"),
    GEOMETRY__ADJUST5VALUE(331, "geometry.adjust5value"),
    GEOMETRY__ADJUST6VALUE(332, "geometry.adjust6value"),
    GEOMETRY__ADJUST7VALUE(333, "geometry.adjust7value"),
    GEOMETRY__ADJUST8VALUE(334, "geometry.adjust8value"),
    GEOMETRY__ADJUST9VALUE(335, "geometry.adjust9value"),
    GEOMETRY__ADJUST10VALUE(336, "geometry.adjust10value"),
    GEOMETRY__PCONNECTIONSITES(337, "geometry.pConnectionSites"),
    GEOMETRY__PCONNECTIONSITESDIR(338, "geometry.pConnectionSitesDir"),
    GEOMETRY__XLIMO(339, "geometry.xLimo"),
    GEOMETRY__YLIMO(340, "geometry.yLimo"),
    GEOMETRY__PADJUSTHANDLES(341, "geometry.pAdjustHandles"),
    GEOMETRY__PGUIDES(342, "geometry.pGuides"),
    GEOMETRY__PINSCRIBE(343, "geometry.pInscribe"),
    GEOMETRY__CXK(344, "geometry.cxk"),
    GEOMETRY__PFRAGMENTS(345, "geometry.pFragments"),
    GEOMETRY__SHADOWOK(378, "geometry.shadowOK"),
    GEOMETRY__3DOK(379, "geometry.3dok"),
    GEOMETRY__LINEOK(380, "geometry.lineok"),
    GEOMETRY__GEOTEXTOK(381, "geometry.geotextok"),
    GEOMETRY__FILLSHADESHAPEOK(382, "geometry.fillshadeshapeok"),
    GEOMETRY__FILLOK(383, "geometry.fillok", EscherPropertyTypesHolder.BOOLEAN),
    FILL__FILLTYPE(384, "fill.filltype"),
    FILL__FILLCOLOR(385, "fill.fillcolor", EscherPropertyTypesHolder.RGB),
    FILL__FILLOPACITY(386, "fill.fillopacity"),
    FILL__FILLBACKCOLOR(387, "fill.fillbackcolor", EscherPropertyTypesHolder.RGB),
    FILL__BACKOPACITY(388, "fill.backopacity"),
    FILL__CRMOD(389, "fill.crmod"),
    FILL__PATTERNTEXTURE(390, "fill.patterntexture"),
    FILL__BLIPFILENAME(391, "fill.blipfilename"),
    FILL__BLIPFLAGS(392, "fill.blipflags"),
    FILL__WIDTH(393, "fill.width"),
    FILL__HEIGHT(394, "fill.height"),
    FILL__ANGLE(395, "fill.angle"),
    FILL__FOCUS(396, "fill.focus"),
    FILL__TOLEFT(397, "fill.toleft"),
    FILL__TOTOP(398, "fill.totop"),
    FILL__TORIGHT(399, "fill.toright"),
    FILL__TOBOTTOM(400, "fill.tobottom"),
    FILL__RECTLEFT(401, "fill.rectleft"),
    FILL__RECTTOP(402, "fill.recttop"),
    FILL__RECTRIGHT(403, "fill.rectright"),
    FILL__RECTBOTTOM(404, "fill.rectbottom"),
    FILL__DZTYPE(405, "fill.dztype"),
    FILL__SHADEPRESET(406, "fill.shadepreset"),
    FILL__SHADECOLORS(407, "fill.shadecolors", EscherPropertyTypesHolder.ARRAY),
    FILL__ORIGINX(408, "fill.originx"),
    FILL__ORIGINY(409, "fill.originy"),
    FILL__SHAPEORIGINX(410, "fill.shapeoriginx"),
    FILL__SHAPEORIGINY(411, "fill.shapeoriginy"),
    FILL__SHADETYPE(412, "fill.shadetype"),
    FILL__FILLED(443, "fill.filled"),
    FILL__HITTESTFILL(444, "fill.hittestfill"),
    FILL__SHAPE(445, "fill.shape"),
    FILL__USERECT(446, "fill.userect"),
    FILL__NOFILLHITTEST(447, "fill.nofillhittest", EscherPropertyTypesHolder.BOOLEAN),
    LINESTYLE__COLOR(448, "linestyle.color", EscherPropertyTypesHolder.RGB),
    LINESTYLE__OPACITY(449, "linestyle.opacity"),
    LINESTYLE__BACKCOLOR(450, "linestyle.backcolor", EscherPropertyTypesHolder.RGB),
    LINESTYLE__CRMOD(451, "linestyle.crmod"),
    LINESTYLE__LINETYPE(452, "linestyle.linetype"),
    LINESTYLE__FILLBLIP(453, "linestyle.fillblip"),
    LINESTYLE__FILLBLIPNAME(454, "linestyle.fillblipname"),
    LINESTYLE__FILLBLIPFLAGS(455, "linestyle.fillblipflags"),
    LINESTYLE__FILLWIDTH(456, "linestyle.fillwidth"),
    LINESTYLE__FILLHEIGHT(457, "linestyle.fillheight"),
    LINESTYLE__FILLDZTYPE(458, "linestyle.filldztype"),
    LINESTYLE__LINEWIDTH(459, "linestyle.linewidth"),
    LINESTYLE__LINEMITERLIMIT(460, "linestyle.linemiterlimit"),
    LINESTYLE__LINESTYLE(461, "linestyle.linestyle"),
    LINESTYLE__LINEDASHING(462, "linestyle.linedashing"),
    LINESTYLE__LINEDASHSTYLE(463, "linestyle.linedashstyle", EscherPropertyTypesHolder.ARRAY),
    LINESTYLE__LINESTARTARROWHEAD(464, "linestyle.linestartarrowhead"),
    LINESTYLE__LINEENDARROWHEAD(465, "linestyle.lineendarrowhead"),
    LINESTYLE__LINESTARTARROWWIDTH(466, "linestyle.linestartarrowwidth"),
    LINESTYLE__LINESTARTARROWLENGTH(467, "linestyle.linestartarrowlength"),
    LINESTYLE__LINEENDARROWWIDTH(468, "linestyle.lineendarrowwidth"),
    LINESTYLE__LINEENDARROWLENGTH(469, "linestyle.lineendarrowlength"),
    LINESTYLE__LINEJOINSTYLE(470, "linestyle.linejoinstyle"),
    LINESTYLE__LINEENDCAPSTYLE(471, "linestyle.lineendcapstyle"),
    LINESTYLE__ARROWHEADSOK(507, "linestyle.arrowheadsok"),
    LINESTYLE__ANYLINE(508, "linestyle.anyline"),
    LINESTYLE__HITLINETEST(509, "linestyle.hitlinetest"),
    LINESTYLE__LINEFILLSHAPE(510, "linestyle.linefillshape"),
    LINESTYLE__NOLINEDRAWDASH(511, "linestyle.nolinedrawdash", EscherPropertyTypesHolder.BOOLEAN),
    LINESTYLE__NOLINEDRAWDASH_LEFT(1407, "linestyle.nolinedrawdash.left", EscherPropertyTypesHolder.BOOLEAN),
    LINESTYLE__NOLINEDRAWDASH_TOP(1471, "linestyle.nolinedrawdash.top", EscherPropertyTypesHolder.BOOLEAN),
    LINESTYLE__NOLINEDRAWDASH_BOTTOM(1599, "linestyle.nolinedrawdash.bottom", EscherPropertyTypesHolder.BOOLEAN),
    LINESTYLE__NOLINEDRAWDASH_RIGHT(1535, "linestyle.nolinedrawdash.right", EscherPropertyTypesHolder.BOOLEAN),
    SHADOWSTYLE__TYPE(512, "shadowstyle.type"),
    SHADOWSTYLE__COLOR(513, "shadowstyle.color", EscherPropertyTypesHolder.RGB),
    SHADOWSTYLE__HIGHLIGHT(514, "shadowstyle.highlight"),
    SHADOWSTYLE__CRMOD(515, "shadowstyle.crmod"),
    SHADOWSTYLE__OPACITY(516, "shadowstyle.opacity"),
    SHADOWSTYLE__OFFSETX(517, "shadowstyle.offsetx"),
    SHADOWSTYLE__OFFSETY(518, "shadowstyle.offsety"),
    SHADOWSTYLE__SECONDOFFSETX(519, "shadowstyle.secondoffsetx"),
    SHADOWSTYLE__SECONDOFFSETY(520, "shadowstyle.secondoffsety"),
    SHADOWSTYLE__SCALEXTOX(521, "shadowstyle.scalextox"),
    SHADOWSTYLE__SCALEYTOX(522, "shadowstyle.scaleytox"),
    SHADOWSTYLE__SCALEXTOY(523, "shadowstyle.scalextoy"),
    SHADOWSTYLE__SCALEYTOY(524, "shadowstyle.scaleytoy"),
    SHADOWSTYLE__PERSPECTIVEX(525, "shadowstyle.perspectivex"),
    SHADOWSTYLE__PERSPECTIVEY(526, "shadowstyle.perspectivey"),
    SHADOWSTYLE__WEIGHT(527, "shadowstyle.weight"),
    SHADOWSTYLE__ORIGINX(528, "shadowstyle.originx"),
    SHADOWSTYLE__ORIGINY(529, "shadowstyle.originy"),
    SHADOWSTYLE__SHADOW(574, "shadowstyle.shadow"),
    SHADOWSTYLE__SHADOWOBSURED(575, "shadowstyle.shadowobscured"),
    PERSPECTIVE__TYPE(576, "perspective.type"),
    PERSPECTIVE__OFFSETX(577, "perspective.offsetx"),
    PERSPECTIVE__OFFSETY(578, "perspective.offsety"),
    PERSPECTIVE__SCALEXTOX(579, "perspective.scalextox"),
    PERSPECTIVE__SCALEYTOX(580, "perspective.scaleytox"),
    PERSPECTIVE__SCALEXTOY(581, "perspective.scalextoy"),
    PERSPECTIVE__SCALEYTOY(582, "perspective.scaleytoy"),
    PERSPECTIVE__PERSPECTIVEX(583, "perspective.perspectivex"),
    PERSPECTIVE__PERSPECTIVEY(584, "perspective.perspectivey"),
    PERSPECTIVE__WEIGHT(585, "perspective.weight"),
    PERSPECTIVE__ORIGINX(586, "perspective.originx"),
    PERSPECTIVE__ORIGINY(587, "perspective.originy"),
    PERSPECTIVE__PERSPECTIVEON(639, "perspective.perspectiveon"),
    THREED__SPECULARAMOUNT(640, "3d.specularamount"),
    THREED__DIFFUSEAMOUNT(641, "3d.diffuseamount"),
    THREED__SHININESS(642, "3d.shininess"),
    THREED__EDGETHICKNESS(643, "3d.edgethickness"),
    THREED__EXTRUDEFORWARD(644, "3d.extrudeforward"),
    THREED__EXTRUDEBACKWARD(645, "3d.extrudebackward"),
    RESERVED646(646, "reserved646"),
    THREED__EXTRUSIONCOLOR(647, "3d.extrusioncolor", EscherPropertyTypesHolder.RGB),
    THREED__CRMOD(648, "3d.crmod"),
    THREED__EXTRUSIONCOLOREXT(649, "3d.extrusioncolorext"),
    RESERVED650(650, "reserved650"),
    THREED__EXTRUSIONCOLOREXTMOD(651, "3d.extrusioncolorextmod"),
    RESERVED652(652, "reserved652"),
    RESERVED653(653, "reserved653"),
    THREED__BOOLEAN_PROPERTIES(655, "3d.booleanproperties"),
    THREED__EXTRUDEPLANE(666, "3d.extrudeplane"),
    THREED__3DEFFECT(700, "3d.3deffect"),
    THREED__METALLIC(701, "3d.metallic"),
    THREED__USEEXTRUSIONCOLOR(702, "3d.useextrusioncolor", EscherPropertyTypesHolder.RGB),
    THREED__LIGHTFACE(703, "3d.lightface"),
    THREEDSTYLE__YROTATIONANGLE(704, "3dstyle.yrotationangle"),
    THREEDSTYLE__XROTATIONANGLE(705, "3dstyle.xrotationangle"),
    THREEDSTYLE__ROTATIONAXISX(706, "3dstyle.rotationaxisx"),
    THREEDSTYLE__ROTATIONAXISY(707, "3dstyle.rotationaxisy"),
    THREEDSTYLE__ROTATIONAXISZ(708, "3dstyle.rotationaxisz"),
    THREEDSTYLE__ROTATIONANGLE(709, "3dstyle.rotationangle"),
    THREEDSTYLE__ROTATIONCENTERX(710, "3dstyle.rotationcenterx"),
    THREEDSTYLE__ROTATIONCENTERY(711, "3dstyle.rotationcentery"),
    THREEDSTYLE__ROTATIONCENTERZ(712, "3dstyle.rotationcenterz"),
    THREEDSTYLE__RENDERMODE(713, "3dstyle.rendermode"),
    THREEDSTYLE__TOLERANCE(714, "3dstyle.tolerance"),
    THREEDSTYLE__XVIEWPOINT(715, "3dstyle.xviewpoint"),
    THREEDSTYLE__YVIEWPOINT(716, "3dstyle.yviewpoint"),
    THREEDSTYLE__ZVIEWPOINT(717, "3dstyle.zviewpoint"),
    THREEDSTYLE__ORIGINX(718, "3dstyle.originx"),
    THREEDSTYLE__ORIGINY(719, "3dstyle.originy"),
    THREEDSTYLE__SKEWANGLE(720, "3dstyle.skewangle"),
    THREEDSTYLE__SKEWAMOUNT(721, "3dstyle.skewamount"),
    THREEDSTYLE__AMBIENTINTENSITY(722, "3dstyle.ambientintensity"),
    THREEDSTYLE__KEYX(723, "3dstyle.keyx"),
    THREEDSTYLE__KEYY(724, "3dstyle.keyy"),
    THREEDSTYLE__KEYZ(725, "3dstyle.keyz"),
    THREEDSTYLE__KEYINTENSITY(726, "3dstyle.keyintensity"),
    THREEDSTYLE__FILLX(727, "3dstyle.fillx"),
    THREEDSTYLE__FILLY(728, "3dstyle.filly"),
    THREEDSTYLE__FILLZ(729, "3dstyle.fillz"),
    THREEDSTYLE__FILLINTENSITY(730, "3dstyle.fillintensity"),
    THREEDSTYLE__CONSTRAINROTATION(763, "3dstyle.constrainrotation"),
    THREEDSTYLE__ROTATIONCENTERAUTO(764, "3dstyle.rotationcenterauto"),
    THREEDSTYLE__PARALLEL(765, "3dstyle.parallel"),
    THREEDSTYLE__KEYHARSH(766, "3dstyle.keyharsh"),
    THREEDSTYLE__FILLHARSH(767, "3dstyle.fillharsh"),
    SHAPE__MASTER(769, "shape.master"),
    SHAPE__CONNECTORSTYLE(771, "shape.connectorstyle"),
    SHAPE__BLACKANDWHITESETTINGS(772, "shape.blackandwhitesettings"),
    SHAPE__WMODEPUREBW(773, "shape.wmodepurebw"),
    SHAPE__WMODEBW(774, "shape.wmodebw"),
    SHAPE__OLEICON(826, "shape.oleicon"),
    SHAPE__PREFERRELATIVERESIZE(827, "shape.preferrelativeresize"),
    SHAPE__LOCKSHAPETYPE(828, "shape.lockshapetype"),
    SHAPE__DELETEATTACHEDOBJECT(830, "shape.deleteattachedobject"),
    SHAPE__BACKGROUNDSHAPE(831, "shape.backgroundshape"),
    CALLOUT__CALLOUTTYPE(832, "callout.callouttype"),
    CALLOUT__XYCALLOUTGAP(833, "callout.xycalloutgap"),
    CALLOUT__CALLOUTANGLE(834, "callout.calloutangle"),
    CALLOUT__CALLOUTDROPTYPE(835, "callout.calloutdroptype"),
    CALLOUT__CALLOUTDROPSPECIFIED(836, "callout.calloutdropspecified"),
    CALLOUT__CALLOUTLENGTHSPECIFIED(837, "callout.calloutlengthspecified"),
    CALLOUT__ISCALLOUT(889, "callout.iscallout"),
    CALLOUT__CALLOUTACCENTBAR(890, "callout.calloutaccentbar"),
    CALLOUT__CALLOUTTEXTBORDER(891, "callout.callouttextborder"),
    CALLOUT__CALLOUTMINUSX(892, "callout.calloutminusx"),
    CALLOUT__CALLOUTMINUSY(893, "callout.calloutminusy"),
    CALLOUT__DROPAUTO(894, "callout.dropauto"),
    CALLOUT__LENGTHSPECIFIED(895, "callout.lengthspecified"),
    GROUPSHAPE__SHAPENAME(896, "groupshape.shapename"),
    GROUPSHAPE__DESCRIPTION(897, "groupshape.description"),
    GROUPSHAPE__HYPERLINK(898, "groupshape.hyperlink"),
    GROUPSHAPE__WRAPPOLYGONVERTICES(899, "groupshape.wrappolygonvertices", EscherPropertyTypesHolder.ARRAY),
    GROUPSHAPE__WRAPDISTLEFT(900, "groupshape.wrapdistleft"),
    GROUPSHAPE__WRAPDISTTOP(901, "groupshape.wrapdisttop"),
    GROUPSHAPE__WRAPDISTRIGHT(902, "groupshape.wrapdistright"),
    GROUPSHAPE__WRAPDISTBOTTOM(903, "groupshape.wrapdistbottom"),
    GROUPSHAPE__REGROUPID(904, "groupshape.regroupid"),
    GROUPSHAPE__UNUSED906(906, "unused906"),
    GROUPSHAPE__TOOLTIP(909, "groupshape.wzTooltip"),
    GROUPSHAPE__SCRIPT(910, "groupshape.wzScript"),
    GROUPSHAPE__POSH(911, "groupshape.posh"),
    GROUPSHAPE__POSRELH(912, "groupshape.posrelh"),
    GROUPSHAPE__POSV(913, "groupshape.posv"),
    GROUPSHAPE__POSRELV(914, "groupshape.posrelv"),
    GROUPSHAPE__HR_PCT(915, "groupshape.pctHR"),
    GROUPSHAPE__HR_ALIGN(916, "groupshape.alignHR"),
    GROUPSHAPE__HR_HEIGHT(917, "groupshape.dxHeightHR"),
    GROUPSHAPE__HR_WIDTH(918, "groupshape.dxWidthHR"),
    GROUPSHAPE__SCRIPTEXT(919, "groupshape.wzScriptExtAttr"),
    GROUPSHAPE__SCRIPTLANG(920, "groupshape.scriptLang"),
    GROUPSHAPE__BORDERTOPCOLOR(923, "groupshape.borderTopColor"),
    GROUPSHAPE__BORDERLEFTCOLOR(924, "groupshape.borderLeftColor"),
    GROUPSHAPE__BORDERBOTTOMCOLOR(925, "groupshape.borderBottomColor"),
    GROUPSHAPE__BORDERRIGHTCOLOR(926, "groupshape.borderRightColor"),
    GROUPSHAPE__TABLEPROPERTIES(927, "groupshape.tableProperties"),
    GROUPSHAPE__TABLEROWPROPERTIES(928, "groupshape.tableRowProperties", EscherPropertyTypesHolder.ARRAY),
    GROUPSHAPE__WEBBOT(933, "groupshape.wzWebBot"),
    GROUPSHAPE__METROBLOB(937, "groupshape.metroBlob"),
    GROUPSHAPE__ZORDER(938, "groupshape.dhgt"),
    GROUPSHAPE__EDITEDWRAP(953, "groupshape.editedwrap"),
    GROUPSHAPE__BEHINDDOCUMENT(954, "groupshape.behinddocument"),
    GROUPSHAPE__ONDBLCLICKNOTIFY(955, "groupshape.ondblclicknotify"),
    GROUPSHAPE__ISBUTTON(956, "groupshape.isbutton"),
    GROUPSHAPE__1DADJUSTMENT(957, "groupshape.1dadjustment"),
    GROUPSHAPE__HIDDEN(958, "groupshape.hidden"),
    GROUPSHAPE__FLAGS(959, "groupshape.groupShapeBooleanProperties"),
    UNKNOWN(65535, "unknown");

    public final short propNumber;
    public final String propName;
    public final EscherPropertyTypesHolder holder;
    private static final Map<Short, EscherPropertyTypes> LOOKUP;

    private EscherPropertyTypes(int propNumber, String name) {
        this(propNumber, name, EscherPropertyTypesHolder.UNKNOWN);
    }

    private EscherPropertyTypes(int propNumber, String propName, EscherPropertyTypesHolder holder) {
        this.propNumber = (short)propNumber;
        this.propName = propName;
        this.holder = holder;
    }

    public short getPropertyId() {
        return this.propNumber;
    }

    public static EscherPropertyTypes forPropertyID(int propertyId) {
        EscherPropertyTypes rt = LOOKUP.get((short)(propertyId & 0x3FFF));
        return rt != null ? rt : UNKNOWN;
    }

    static {
        LOOKUP = Stream.of(EscherPropertyTypes.values()).collect(Collectors.toMap(EscherPropertyTypes::getPropertyId, Function.identity()));
    }
}

