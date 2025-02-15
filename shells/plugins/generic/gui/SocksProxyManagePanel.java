/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import core.EasyI18N;
import core.ui.component.DataView;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SocksProxyManagePanel {
    private JPanel mainPanel;
    public JPanel startSocksProxyPanel;
    public JPanel socksProxyManagePanel;
    public JButton testButton;
    public JTextField socksBindAddressTextField;
    public JLabel socksBindAddressLabel;
    public JLabel socksBindPortLabel;
    public JTextField socksBindPortTextField;
    public JLabel remoteSocksProxyUrlLabel;
    public JTextField remoteSocksProxyUrlTextField;
    public JTextField remoteKeyTextField;
    public JTextField serverPacketSizeTextField;
    public JTextField serverSocketOnceReadSizeTextField;
    public JTextField clientSocketOnceReadSizeTextField;
    public JTextField clientPacketTextField;
    public JButton startSocksServerButton;
    public JButton addNewProxyButton;
    public JButton stopProxyButton;
    public JButton serverProxyConfigButton;
    public JTextField requestDelayTextField;
    public JTextField requestErrRetryTextField;
    public JTextField requestErrDelayTextField;
    public JLabel remoteKeyLabel;
    public JLabel serverSocketOnceReadSizeLabel;
    public JLabel serverPacketSizeLabel;
    public JLabel clientSocketOnceReadSizeLabel;
    public JLabel clientPacketSizeLabel;
    public JLabel requestDelayLabel;
    public JLabel requestErrRetryLabel;
    public JLabel requestErrDelayLabel;
    public JLabel statusLabel;
    public JLabel capacityLabel;
    public JTextField capacityTextField;
    public JPanel stopSocksProxy;
    public JScrollPane dataViewScrollPane;
    public DataView proxyManageDataView;
    public JButton addProxyType;

    public SocksProxyManagePanel() {
        this.$$$setupUI$$$();
        this.proxyManageDataView.addColumn("ID");
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u76d1\u542c\u5730\u5740"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u76d1\u542c\u7aef\u53e3"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u7c7b\u578b"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u76ee\u6807\u5730\u5740"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u76ee\u6807\u7aef\u53e3"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u72b6\u6001"));
        this.proxyManageDataView.addColumn(EasyI18N.getI18nString("\u9519\u8bef\u4fe1\u606f"));
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    private void $$$setupUI$$$() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new CardLayout(0, 0));
        this.startSocksProxyPanel = new JPanel();
        this.startSocksProxyPanel.setLayout(new GridLayoutManager(14, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.mainPanel.add((Component)this.startSocksProxyPanel, "startSocksProxy");
        this.socksBindAddressLabel = new JLabel();
        this.socksBindAddressLabel.setText("socks4a/5 \u7ed1\u5b9a\u5730\u5740:");
        this.startSocksProxyPanel.add((Component)this.socksBindAddressLabel, new GridConstraints(0, 0, 1, 1, 1, 0, 0, 0, null, null, null, 0, false));
        this.socksBindAddressTextField = new JTextField();
        this.socksBindAddressTextField.setText("127.0.0.1");
        this.startSocksProxyPanel.add((Component)this.socksBindAddressTextField, new GridConstraints(0, 1, 1, 1, 1, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.socksBindPortLabel = new JLabel();
        this.socksBindPortLabel.setText("socks4a/5 \u7ed1\u5b9a\u7aef\u53e3:");
        this.startSocksProxyPanel.add((Component)this.socksBindPortLabel, new GridConstraints(1, 0, 1, 1, 1, 0, 0, 0, null, null, null, 0, false));
        this.socksBindPortTextField = new JTextField();
        this.socksBindPortTextField.setText("10806");
        this.startSocksProxyPanel.add((Component)this.socksBindPortTextField, new GridConstraints(1, 1, 1, 1, 9, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.remoteSocksProxyUrlLabel = new JLabel();
        this.remoteSocksProxyUrlLabel.setText("\u8fdc\u7a0bSocks URL\u5730\u5740:");
        this.startSocksProxyPanel.add((Component)this.remoteSocksProxyUrlLabel, new GridConstraints(2, 0, 1, 1, 1, 0, 0, 0, null, null, null, 0, false));
        this.remoteKeyLabel = new JLabel();
        this.remoteKeyLabel.setText("\u8fdc\u7a0bSocks \u52a0\u5bc6Key:");
        this.startSocksProxyPanel.add((Component)this.remoteKeyLabel, new GridConstraints(3, 0, 1, 1, 1, 0, 0, 0, null, null, null, 0, false));
        this.remoteKeyTextField = new JTextField();
        this.remoteKeyTextField.setText("remoteKey");
        this.startSocksProxyPanel.add((Component)this.remoteKeyTextField, new GridConstraints(3, 1, 1, 1, 9, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.serverPacketSizeLabel = new JLabel();
        this.serverPacketSizeLabel.setText("Server\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        this.startSocksProxyPanel.add((Component)this.serverPacketSizeLabel, new GridConstraints(5, 0, 1, 1, 1, 0, 0, 0, null, null, null, 0, false));
        this.serverPacketSizeTextField = new JTextField();
        this.serverPacketSizeTextField.setText("1024000");
        this.startSocksProxyPanel.add((Component)this.serverPacketSizeTextField, new GridConstraints(5, 1, 1, 1, 9, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.serverSocketOnceReadSizeLabel = new JLabel();
        this.serverSocketOnceReadSizeLabel.setText("Server\u5957\u63a5\u5b57\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        this.startSocksProxyPanel.add((Component)this.serverSocketOnceReadSizeLabel, new GridConstraints(4, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.serverSocketOnceReadSizeTextField = new JTextField();
        this.serverSocketOnceReadSizeTextField.setText("102400");
        this.startSocksProxyPanel.add((Component)this.serverSocketOnceReadSizeTextField, new GridConstraints(4, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.clientSocketOnceReadSizeTextField = new JTextField();
        this.clientSocketOnceReadSizeTextField.setText("102400");
        this.startSocksProxyPanel.add((Component)this.clientSocketOnceReadSizeTextField, new GridConstraints(6, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.clientPacketSizeLabel = new JLabel();
        this.clientPacketSizeLabel.setText("Client\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        this.startSocksProxyPanel.add((Component)this.clientPacketSizeLabel, new GridConstraints(7, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.clientPacketTextField = new JTextField();
        this.clientPacketTextField.setText("1024000");
        this.startSocksProxyPanel.add((Component)this.clientPacketTextField, new GridConstraints(7, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.startSocksProxyPanel.add((Component)panel1, new GridConstraints(12, 0, 1, 2, 0, 3, 3, 3, null, null, null, 0, false));
        this.testButton = new JButton();
        this.testButton.setText("\u6d4b\u8bd5\u8fde\u63a5");
        panel1.add((Component)this.testButton, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        this.startSocksServerButton = new JButton();
        this.startSocksServerButton.setText("\u5f00\u542fSocksServer");
        panel1.add((Component)this.startSocksServerButton, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.startSocksProxyPanel.add((Component)panel2, new GridConstraints(13, 0, 1, 2, 0, 3, 3, 3, null, null, null, 0, false));
        this.requestDelayLabel = new JLabel();
        this.requestDelayLabel.setText("\u8bf7\u6c42\u6296\u52a8\u5ef6\u8fdf(ms)");
        this.startSocksProxyPanel.add((Component)this.requestDelayLabel, new GridConstraints(9, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.requestDelayTextField = new JTextField();
        this.requestDelayTextField.setText("10");
        this.startSocksProxyPanel.add((Component)this.requestDelayTextField, new GridConstraints(9, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.requestErrRetryLabel = new JLabel();
        this.requestErrRetryLabel.setText("\u9519\u8bef\u91cd\u8bd5\u6700\u5927\u6b21\u6570");
        this.startSocksProxyPanel.add((Component)this.requestErrRetryLabel, new GridConstraints(10, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.requestErrRetryTextField = new JTextField();
        this.requestErrRetryTextField.setText("20");
        this.startSocksProxyPanel.add((Component)this.requestErrRetryTextField, new GridConstraints(10, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.requestErrDelayLabel = new JLabel();
        this.requestErrDelayLabel.setText("\u8bf7\u6c42\u9519\u8bef\u91cd\u8bd5\u6296\u52a8\u5ef6\u65f6(ms)");
        this.startSocksProxyPanel.add((Component)this.requestErrDelayLabel, new GridConstraints(11, 0, 1, 1, 0, 0, 0, 0, null, null, null, 1, false));
        this.requestErrDelayTextField = new JTextField();
        this.requestErrDelayTextField.setText("30");
        this.startSocksProxyPanel.add((Component)this.requestErrDelayTextField, new GridConstraints(11, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.capacityLabel = new JLabel();
        this.capacityLabel.setText("\u5957\u63a5\u5b57\u7f13\u51b2\u961f\u5217\u6570");
        this.startSocksProxyPanel.add((Component)this.capacityLabel, new GridConstraints(8, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.capacityTextField = new JTextField();
        this.capacityTextField.setText("5");
        this.startSocksProxyPanel.add((Component)this.capacityTextField, new GridConstraints(8, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.remoteSocksProxyUrlTextField = new JTextField();
        this.remoteSocksProxyUrlTextField.setText("http://127.0.0.1:8088/");
        this.startSocksProxyPanel.add((Component)this.remoteSocksProxyUrlTextField, new GridConstraints(2, 1, 1, 1, 9, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.clientSocketOnceReadSizeLabel = new JLabel();
        this.clientSocketOnceReadSizeLabel.setText("Client\u5957\u63a5\u5b57\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        this.startSocksProxyPanel.add((Component)this.clientSocketOnceReadSizeLabel, new GridConstraints(6, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.socksProxyManagePanel = new JPanel();
        this.socksProxyManagePanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.mainPanel.add((Component)this.socksProxyManagePanel, "socksProxyManage");
        this.statusLabel = new JLabel();
        this.statusLabel.setText("\u5f53\u524d\u8fde\u63a5\u6570:10 \u5f53\u524d\u901f\u5ea6:100k/s \u5df2\u53d1\u5305:1000 \u5df2\u4e0a\u4f20:10mb \u5df2\u4e0b\u8f7d:20mb  \u8fd0\u884c\u65f6\u95f4:1h");
        this.socksProxyManagePanel.add((Component)this.statusLabel, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.stopSocksProxy = new JPanel();
        this.stopSocksProxy.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        this.socksProxyManagePanel.add((Component)this.stopSocksProxy, new GridConstraints(3, 0, 1, 1, 0, 3, 3, 3, null, null, null, 0, false));
        this.addNewProxyButton = new JButton();
        this.addNewProxyButton.setText("\u6dfb\u52a0\u4ee3\u7406\u7c7b\u578b");
        this.stopSocksProxy.add((Component)this.addNewProxyButton, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        this.stopProxyButton = new JButton();
        this.stopProxyButton.setText("\u505c\u6b62\u4ee3\u7406");
        this.stopSocksProxy.add((Component)this.stopProxyButton, new GridConstraints(0, 2, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        this.serverProxyConfigButton = new JButton();
        this.serverProxyConfigButton.setText("\u901a\u4fe1\u914d\u7f6e");
        this.stopSocksProxy.add((Component)this.serverProxyConfigButton, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.socksProxyManagePanel.add((Component)panel3, new GridConstraints(1, 0, 1, 1, 0, 3, 3, 3, null, null, null, 0, false));
        this.dataViewScrollPane = new JScrollPane();
        panel3.add((Component)this.dataViewScrollPane, new GridConstraints(0, 0, 1, 1, 0, 3, 5, 5, null, null, null, 0, false));
        this.proxyManageDataView = new DataView();
        this.proxyManageDataView.setAutoCreateRowSorter(true);
        this.proxyManageDataView.setFillsViewportHeight(true);
        this.dataViewScrollPane.setViewportView(this.proxyManageDataView);
    }

    public JComponent $$$getRootComponent$$$() {
        return this.mainPanel;
    }
}

