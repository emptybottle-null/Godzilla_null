/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDefaultsAddon;
import com.formdev.flatlaf.FlatIconColors;
import com.formdev.flatlaf.FlatInputMaps;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.LinuxFontPolicy;
import com.formdev.flatlaf.MnemonicHandler;
import com.formdev.flatlaf.UIDefaultsLoader;
import com.formdev.flatlaf.ui.FlatPopupFactory;
import com.formdev.flatlaf.ui.JBRCustomDecorations;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.MultiResolutionImageSupport;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;

public abstract class FlatLaf
extends BasicLookAndFeel {
    static final Logger LOG = Logger.getLogger(FlatLaf.class.getName());
    private static final String DESKTOPFONTHINTS = "awt.font.desktophints";
    private static List<Object> customDefaultsSources;
    private String desktopPropertyName;
    private String desktopPropertyName2;
    private PropertyChangeListener desktopPropertyListener;
    private static boolean aquaLoaded;
    private static boolean updateUIPending;
    private PopupFactory oldPopupFactory;
    private MnemonicHandler mnemonicHandler;
    private Consumer<UIDefaults> postInitialization;
    private Boolean oldFrameWindowDecorated;
    private Boolean oldDialogWindowDecorated;

    public static boolean install(LookAndFeel newLookAndFeel) {
        try {
            UIManager.setLookAndFeel(newLookAndFeel);
            return true;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "FlatLaf: Failed to initialize look and feel '" + newLookAndFeel.getClass().getName() + "'.", ex);
            return false;
        }
    }

    public static void installLafInfo(String lafName, Class<? extends LookAndFeel> lafClass) {
        UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo(lafName, lafClass.getName()));
    }

    @Override
    public String getID() {
        return "FlatLaf - " + this.getName();
    }

    public abstract boolean isDark();

    public static boolean isLafDark() {
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        return lookAndFeel instanceof FlatLaf && ((FlatLaf)lookAndFeel).isDark();
    }

    @Override
    public boolean getSupportsWindowDecorations() {
        if (SystemInfo.isJetBrainsJVM_11_orLater && SystemInfo.isWindows_10_orLater && JBRCustomDecorations.isSupported()) {
            return false;
        }
        return SystemInfo.isWindows_10_orLater;
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

    @Override
    public Icon getDisabledIcon(JComponent component, Icon icon) {
        if (icon instanceof DisabledIconProvider) {
            return ((DisabledIconProvider)((Object)icon)).getDisabledIcon();
        }
        if (icon instanceof ImageIcon) {
            Object grayFilter = UIManager.get("Component.grayFilter");
            ImageFilter filter = grayFilter instanceof ImageFilter ? (ImageFilter)grayFilter : GrayFilter.createDisabledIconFilter(this.isDark());
            Function<Image, Image> mapper = img -> {
                FilteredImageSource producer = new FilteredImageSource(img.getSource(), filter);
                return Toolkit.getDefaultToolkit().createImage(producer);
            };
            Image image = ((ImageIcon)icon).getImage();
            return new ImageIconUIResource(MultiResolutionImageSupport.map(image, mapper));
        }
        return null;
    }

    @Override
    public void initialize() {
        if (SystemInfo.isMacOS) {
            this.initializeAqua();
        }
        super.initialize();
        this.oldPopupFactory = PopupFactory.getSharedInstance();
        PopupFactory.setSharedInstance(new FlatPopupFactory());
        this.mnemonicHandler = new MnemonicHandler();
        this.mnemonicHandler.install();
        if (SystemInfo.isWindows) {
            this.desktopPropertyName = "win.messagebox.font";
        } else if (SystemInfo.isLinux) {
            this.desktopPropertyName = "gnome.Gtk/FontName";
            this.desktopPropertyName2 = "gnome.Xft/DPI";
        }
        if (this.desktopPropertyName != null) {
            this.desktopPropertyListener = e -> {
                String propertyName = e.getPropertyName();
                if (this.desktopPropertyName.equals(propertyName) || propertyName.equals(this.desktopPropertyName2)) {
                    FlatLaf.reSetLookAndFeel();
                } else if (DESKTOPFONTHINTS.equals(propertyName) && UIManager.getLookAndFeel() instanceof FlatLaf) {
                    this.putAATextInfo(UIManager.getLookAndFeelDefaults());
                    FlatLaf.updateUILater();
                }
            };
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.addPropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.addPropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.addPropertyChangeListener(DESKTOPFONTHINTS, this.desktopPropertyListener);
        }
        this.postInitialization = defaults -> {
            Color linkColor = defaults.getColor("Component.linkColor");
            if (linkColor != null) {
                new HTMLEditorKit().getStyleSheet().addRule(String.format("a { color: #%06x; }", linkColor.getRGB() & 0xFFFFFF));
            }
        };
        Boolean useWindowDecorations = FlatSystemProperties.getBooleanStrict("flatlaf.useWindowDecorations", null);
        if (useWindowDecorations != null) {
            this.oldFrameWindowDecorated = JFrame.isDefaultLookAndFeelDecorated();
            this.oldDialogWindowDecorated = JDialog.isDefaultLookAndFeelDecorated();
            JFrame.setDefaultLookAndFeelDecorated(useWindowDecorations);
            JDialog.setDefaultLookAndFeelDecorated(useWindowDecorations);
        }
    }

    @Override
    public void uninitialize() {
        if (this.desktopPropertyListener != null) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            toolkit.removePropertyChangeListener(this.desktopPropertyName, this.desktopPropertyListener);
            if (this.desktopPropertyName2 != null) {
                toolkit.removePropertyChangeListener(this.desktopPropertyName2, this.desktopPropertyListener);
            }
            toolkit.removePropertyChangeListener(DESKTOPFONTHINTS, this.desktopPropertyListener);
            this.desktopPropertyName = null;
            this.desktopPropertyName2 = null;
            this.desktopPropertyListener = null;
        }
        if (this.oldPopupFactory != null) {
            PopupFactory.setSharedInstance(this.oldPopupFactory);
            this.oldPopupFactory = null;
        }
        if (this.mnemonicHandler != null) {
            this.mnemonicHandler.uninstall();
            this.mnemonicHandler = null;
        }
        new HTMLEditorKit().getStyleSheet().addRule("a { color: blue; }");
        this.postInitialization = null;
        if (this.oldFrameWindowDecorated != null) {
            JFrame.setDefaultLookAndFeelDecorated(this.oldFrameWindowDecorated);
            JDialog.setDefaultLookAndFeelDecorated(this.oldDialogWindowDecorated);
            this.oldFrameWindowDecorated = null;
            this.oldDialogWindowDecorated = null;
        }
        super.uninitialize();
    }

    private void initializeAqua() {
        BasicLookAndFeel aquaLaf;
        if (aquaLoaded) {
            return;
        }
        aquaLoaded = true;
        String aquaLafClassName = "com.apple.laf.AquaLookAndFeel";
        try {
            if (SystemInfo.isJava_9_orLater) {
                Method m = UIManager.class.getMethod("createLookAndFeel", String.class);
                aquaLaf = (BasicLookAndFeel)m.invoke(null, "Mac OS X");
            } else {
                aquaLaf = (BasicLookAndFeel)Class.forName(aquaLafClassName).newInstance();
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "FlatLaf: Failed to initialize Aqua look and feel '" + aquaLafClassName + "'.", ex);
            throw new IllegalStateException();
        }
        PopupFactory oldPopupFactory = PopupFactory.getSharedInstance();
        aquaLaf.initialize();
        aquaLaf.uninitialize();
        PopupFactory.setSharedInstance(oldPopupFactory);
    }

    @Override
    public UIDefaults getDefaults() {
        UIDefaults defaults = super.getDefaults();
        defaults.put("laf.dark", (Object)this.isDark());
        defaults.addResourceBundle("com.formdev.flatlaf.resources.Bundle");
        this.putDefaults(defaults, defaults.getColor("control"), "Button.disabledBackground", "EditorPane.disabledBackground", "EditorPane.inactiveBackground", "FormattedTextField.disabledBackground", "PasswordField.disabledBackground", "Spinner.disabledBackground", "TextArea.disabledBackground", "TextArea.inactiveBackground", "TextField.disabledBackground", "TextPane.disabledBackground", "TextPane.inactiveBackground", "ToggleButton.disabledBackground");
        this.putDefaults(defaults, defaults.getColor("textInactiveText"), "Button.disabledText", "CheckBox.disabledText", "CheckBoxMenuItem.disabledForeground", "Menu.disabledForeground", "MenuItem.disabledForeground", "RadioButton.disabledText", "RadioButtonMenuItem.disabledForeground", "Spinner.disabledForeground", "ToggleButton.disabledText");
        this.putDefaults(defaults, defaults.getColor("textText"), "DesktopIcon.foreground");
        this.initFonts(defaults);
        FlatLaf.initIconColors(defaults, this.isDark());
        FlatInputMaps.initInputMaps(defaults);
        ServiceLoader<FlatDefaultsAddon> addonLoader = ServiceLoader.load(FlatDefaultsAddon.class);
        ArrayList<FlatDefaultsAddon> addons = new ArrayList<FlatDefaultsAddon>();
        for (FlatDefaultsAddon addon : addonLoader) {
            addons.add(addon);
        }
        addons.sort((addon1, addon2) -> addon1.getPriority() - addon2.getPriority());
        List<Class<?>> lafClassesForDefaultsLoading = this.getLafClassesForDefaultsLoading();
        if (lafClassesForDefaultsLoading != null) {
            UIDefaultsLoader.loadDefaultsFromProperties(lafClassesForDefaultsLoading, addons, this.getAdditionalDefaults(), this.isDark(), defaults);
        } else {
            UIDefaultsLoader.loadDefaultsFromProperties(this.getClass(), addons, this.getAdditionalDefaults(), this.isDark(), defaults);
        }
        if (SystemInfo.isMacOS && Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
            defaults.put("MenuBarUI", "com.apple.laf.AquaMenuBarUI");
            defaults.put("MenuBar.backgroundPainter", BorderFactory.createEmptyBorder());
        }
        this.putAATextInfo(defaults);
        this.applyAdditionalDefaults(defaults);
        for (FlatDefaultsAddon addon : addons) {
            addon.afterDefaultsLoading(this, defaults);
        }
        defaults.put("laf.scaleFactor", t -> Float.valueOf(UIScale.getUserScaleFactor()));
        if (this.postInitialization != null) {
            this.postInitialization.accept(defaults);
            this.postInitialization = null;
        }
        return defaults;
    }

    void applyAdditionalDefaults(UIDefaults defaults) {
    }

    protected List<Class<?>> getLafClassesForDefaultsLoading() {
        return null;
    }

    protected Properties getAdditionalDefaults() {
        return null;
    }

    private void initFonts(UIDefaults defaults) {
        FontUIResource uiFont = null;
        if (SystemInfo.isWindows) {
            Font winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.messagebox.font");
            if (winFont != null) {
                uiFont = FlatLaf.createCompositeFont(winFont.getFamily(), winFont.getStyle(), winFont.getSize());
            }
        } else if (SystemInfo.isMacOS) {
            String fontName = SystemInfo.isMacOS_10_15_Catalina_orLater ? (SystemInfo.isJetBrainsJVM_11_orLater ? ".AppleSystemUIFont" : "Helvetica Neue") : (SystemInfo.isMacOS_10_11_ElCapitan_orLater ? ".SF NS Text" : "Lucida Grande");
            uiFont = FlatLaf.createCompositeFont(fontName, 0, 13);
        } else if (SystemInfo.isLinux) {
            Font font = LinuxFontPolicy.getFont();
            FontUIResource fontUIResource = uiFont = font instanceof FontUIResource ? (FontUIResource)font : new FontUIResource(font);
        }
        if (uiFont == null) {
            uiFont = FlatLaf.createCompositeFont("SansSerif", 0, 12);
        }
        uiFont = UIScale.applyCustomScaleFactor(uiFont);
        ActiveFont activeFont = new ActiveFont(1.0f);
        for (Object key : defaults.keySet()) {
            if (!(key instanceof String) || !((String)key).endsWith(".font") && !((String)key).endsWith("Font")) continue;
            defaults.put(key, activeFont);
        }
        defaults.put("ProgressBar.font", new ActiveFont(0.85f));
        defaults.put("defaultFont", uiFont);
    }

    static FontUIResource createCompositeFont(String family, int style, int size) {
        Font font = StyleContext.getDefaultStyleContext().getFont(family, style, size);
        return font instanceof FontUIResource ? (FontUIResource)font : new FontUIResource(font);
    }

    public static void initIconColors(UIDefaults defaults, boolean dark) {
        for (FlatIconColors c : FlatIconColors.values()) {
            if (c.light != !dark && c.dark != dark) continue;
            defaults.put(c.key, new ColorUIResource(c.rgb));
        }
    }

    private void putAATextInfo(UIDefaults defaults) {
        if (SystemInfo.isMacOS && SystemInfo.isJetBrainsJVM) {
            defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else if (SystemInfo.isJava_9_orLater) {
            Map hints;
            Object aaHint;
            Object desktopHints = Toolkit.getDefaultToolkit().getDesktopProperty(DESKTOPFONTHINTS);
            if (desktopHints instanceof Map && (aaHint = (hints = (Map)desktopHints).get(RenderingHints.KEY_TEXT_ANTIALIASING)) != null && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF && aaHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT) {
                defaults.put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                defaults.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, hints.get(RenderingHints.KEY_TEXT_LCD_CONTRAST));
            }
        } else {
            try {
                Object key = Class.forName("sun.swing.SwingUtilities2").getField("AA_TEXT_PROPERTY_KEY").get(null);
                Object value = Class.forName("sun.swing.SwingUtilities2$AATextInfo").getMethod("getAATextInfo", Boolean.TYPE).invoke(null, true);
                defaults.put(key, value);
            } catch (Exception ex) {
                Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    private void putDefaults(UIDefaults defaults, Object value, String ... keys) {
        for (String key : keys) {
            defaults.put(key, value);
        }
    }

    static List<Object> getCustomDefaultsSources() {
        return customDefaultsSources;
    }

    public static void registerCustomDefaultsSource(String packageName) {
        FlatLaf.registerCustomDefaultsSource(packageName, null);
    }

    public static void unregisterCustomDefaultsSource(String packageName) {
        FlatLaf.unregisterCustomDefaultsSource(packageName, null);
    }

    public static void registerCustomDefaultsSource(String packageName, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList<Object>();
        }
        customDefaultsSources.add(packageName);
        customDefaultsSources.add(classLoader);
    }

    public static void unregisterCustomDefaultsSource(String packageName, ClassLoader classLoader) {
        if (customDefaultsSources == null) {
            return;
        }
        int size = customDefaultsSources.size();
        for (int i = 0; i < size - 1; ++i) {
            Object source = customDefaultsSources.get(i);
            if (!packageName.equals(source) || customDefaultsSources.get(i + 1) != classLoader) continue;
            customDefaultsSources.remove(i + 1);
            customDefaultsSources.remove(i);
            break;
        }
    }

    public static void registerCustomDefaultsSource(File folder) {
        if (customDefaultsSources == null) {
            customDefaultsSources = new ArrayList<Object>();
        }
        customDefaultsSources.add(folder);
    }

    public static void unregisterCustomDefaultsSource(File folder) {
        if (customDefaultsSources == null) {
            return;
        }
        customDefaultsSources.remove(folder);
    }

    private static void reSetLookAndFeel() {
        EventQueue.invokeLater(() -> {
            LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
            try {
                UIManager.setLookAndFeel(lookAndFeel);
                PropertyChangeEvent e = new PropertyChangeEvent(UIManager.class, "lookAndFeel", lookAndFeel, lookAndFeel);
                for (PropertyChangeListener l : UIManager.getPropertyChangeListeners()) {
                    l.propertyChange(e);
                }
                FlatLaf.updateUI();
            } catch (UnsupportedLookAndFeelException ex) {
                LOG.log(Level.SEVERE, "FlatLaf: Failed to reinitialize look and feel '" + lookAndFeel.getClass().getName() + "'.", ex);
            }
        });
    }

    public static void updateUI() {
        for (Window w : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(w);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void updateUILater() {
        Class<FlatLaf> clazz = FlatLaf.class;
        synchronized (FlatLaf.class) {
            if (updateUIPending) {
                // ** MonitorExit[var0] (shouldn't be in output)
                return;
            }
            updateUIPending = true;
            // ** MonitorExit[var0] (shouldn't be in output)
            EventQueue.invokeLater(() -> {
                FlatLaf.updateUI();
                Class<FlatLaf> clazz = FlatLaf.class;
                synchronized (FlatLaf.class) {
                    updateUIPending = false;
                    // ** MonitorExit[var0] (shouldn't be in output)
                    return;
                }
            });
            return;
        }
    }

    public static boolean isShowMnemonics() {
        return MnemonicHandler.isShowMnemonics();
    }

    public static void showMnemonics(Component c) {
        MnemonicHandler.showMnemonics(true, c);
    }

    public static void hideMnemonics() {
        MnemonicHandler.showMnemonics(false, null);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public static interface DisabledIconProvider {
        public Icon getDisabledIcon();
    }

    private static class ImageIconUIResource
    extends ImageIcon
    implements UIResource {
        ImageIconUIResource(Image image) {
            super(image);
        }
    }

    private static class ActiveFont
    implements UIDefaults.ActiveValue {
        private final float scaleFactor;
        private Font font;
        private Font lastDefaultFont;

        ActiveFont(float scaleFactor) {
            this.scaleFactor = scaleFactor;
        }

        @Override
        public Object createValue(UIDefaults table) {
            Font defaultFont = UIManager.getFont("defaultFont");
            if (this.lastDefaultFont != defaultFont) {
                this.lastDefaultFont = defaultFont;
                if (this.scaleFactor != 1.0f) {
                    int newFontSize = Math.round((float)defaultFont.getSize() * this.scaleFactor);
                    this.font = new FontUIResource(defaultFont.deriveFont((float)newFontSize));
                } else {
                    this.font = defaultFont instanceof UIResource ? defaultFont : new FontUIResource(defaultFont);
                }
            }
            return this.font;
        }
    }
}

