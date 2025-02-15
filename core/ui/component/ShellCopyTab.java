/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.EasyI18N;
import core.annotation.DisplayName;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.ShellManage;
import core.ui.component.GBC;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import util.Log;
import util.automaticBindClick;

@DisplayName(DisplayName="\u63d2\u4ef6\u6807\u7b7e\u7ba1\u7406")
public class ShellCopyTab
extends JPanel {
    private final ShellEntity shellEntity;
    private final Payload payload;
    private int currentComponent = 0;
    private final JComboBox<String> pluginComboBox;
    private final JLabel pluginLabel;
    private final JButton scanButton;
    private final JButton addButton;
    private final LinkedHashMap<String, Object> pluginMap;
    private final ArrayList pluginInstanceList;

    public ShellCopyTab(ShellEntity shellEntity) {
        super(new GridBagLayout());
        this.shellEntity = shellEntity;
        this.payload = shellEntity.getPayloadModule();
        this.pluginLabel = new JLabel("\u63d2\u4ef6");
        this.addButton = new JButton("\u6dfb\u52a0");
        this.scanButton = new JButton("\u626b\u63cf");
        this.pluginMap = new LinkedHashMap();
        this.pluginInstanceList = new ArrayList();
        this.pluginComboBox = new JComboBox();
        this.addComponent(this.pluginLabel, this.pluginComboBox);
        this.addComponent(this.scanButton, this.addButton);
        automaticBindClick.bindJButtonClick(this, this);
    }

    public void scanButtonClick(ActionEvent e) {
        this.scan();
    }

    public void addButtonClick(ActionEvent e) throws Exception {
        Object pluginObject;
        String select = (String)this.pluginComboBox.getSelectedItem();
        if (select != null && (pluginObject = this.pluginMap.get(select)) != null) {
            ShellManage shellManage = this.shellEntity.getFrame();
            JTabbedPane tabbedPane = shellManage.getTabbedPane();
            Class<?> pluginClass = pluginObject.getClass();
            if (Plugin.class.isAssignableFrom(pluginClass)) {
                Plugin plugin = (Plugin)pluginClass.newInstance();
                plugin.init(this.shellEntity);
                tabbedPane.addTab(select, plugin.getView());
                EasyI18N.installObject(plugin);
                EasyI18N.installObject(plugin.getView());
                this.pluginInstanceList.add(plugin);
            } else if (JPanel.class.isAssignableFrom(pluginClass)) {
                JPanel panel = (JPanel)pluginClass.getConstructor(ShellEntity.class).newInstance(this.shellEntity);
                EasyI18N.installObject(panel);
                tabbedPane.addTab(select, panel);
                this.pluginInstanceList.add(panel);
            }
        }
    }

    public int scan() {
        this.pluginComboBox.removeAllItems();
        this.pluginMap.clear();
        ShellManage shellManage = this.shellEntity.getFrame();
        this.pluginMap.putAll(shellManage.getGlobalComponent());
        this.pluginMap.putAll(shellManage.getPluginMap());
        for (String keyString : this.pluginMap.keySet()) {
            this.pluginComboBox.addItem(keyString);
        }
        return this.pluginComboBox.getItemCount();
    }

    void addComponent(Component label, Component component) {
        GBC gbcl = new GBC(0, this.currentComponent).setInsets(5, -40, 0, 0);
        GBC gbc = new GBC(1, this.currentComponent, 3, 1).setInsets(5, 20, 0, 0);
        super.add(label, gbcl);
        super.add(component, gbc);
        ++this.currentComponent;
    }

    public void closePlugin() {
        this.pluginInstanceList.stream().forEach(o -> {
            try {
                Method method = o.getClass().getDeclaredMethod("closePlugin", null);
                if (method != null) {
                    method.invoke(o, null);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        });
    }
}

