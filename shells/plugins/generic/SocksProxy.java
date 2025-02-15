/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic;

import core.EasyI18N;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.socksServer.HttpRequestHandle;
import core.socksServer.HttpToSocks;
import core.socksServer.PortForward;
import core.socksServer.SimpleHttpRequestHandle;
import core.socksServer.SocketStatus;
import core.socksServer.SocksServerConfig;
import core.ui.component.dialog.GOptionPane;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import shells.plugins.generic.gui.SocksProxyManagePanel;
import shells.plugins.generic.gui.dialog.ChooseNewRetransmissionDialog;
import shells.plugins.generic.gui.dialog.SocksClientRetransmissionConfigManage;
import shells.plugins.generic.model.Retransmission;
import shells.plugins.generic.model.enums.RetransmissionType;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

public class SocksProxy
implements Plugin {
    SocksProxyManagePanel socksProxyManage;
    JPanel mainPanel;
    HttpToSocks proxyContext;
    SocksServerConfig socksServerConfig;
    ArrayList<Retransmission> retransmissionList = new ArrayList();
    JPopupMenu rightClickMenu;

    public SocksProxy(HttpRequestHandle requestHandle) {
        this.socksProxyManage = new SocksProxyManagePanel();
        this.mainPanel = new JPanel(new GridBagLayout());
        this.mainPanel.add(new JLabel("\u4e0b\u4e2a\u7248\u672c\u5f00\u653e"));
        this.rightClickMenu = new JPopupMenu();
        this.socksServerConfig = new SocksServerConfig("127.0.0.1", 1088);
        this.socksServerConfig.requestHandle = requestHandle;
        this.parseConfig();
        this.proxyContext = new HttpToSocks(this.socksServerConfig);
        automaticBindClick.bindJButtonClick(this.socksProxyManage.getClass(), this.socksProxyManage, SocksProxy.class, this);
        JMenuItem stopItem = new JMenuItem("\u505c\u6b62\u4ee3\u7406");
        stopItem.setActionCommand("stop");
        JMenuItem removeItem = new JMenuItem("\u5220\u9664\u4ee3\u7406");
        removeItem.setActionCommand("remove");
        JMenuItem refreshItem = new JMenuItem("\u5237\u65b0\u4ee3\u7406");
        refreshItem.setActionCommand("refresh");
        this.rightClickMenu.add(stopItem);
        this.rightClickMenu.add(removeItem);
        this.rightClickMenu.add(refreshItem);
        automaticBindClick.bindMenuItemClick(this.rightClickMenu, null, this);
        this.socksProxyManage.proxyManageDataView.setRightClickMenu(this.rightClickMenu, true);
        EasyI18N.installObject(this);
        EasyI18N.installObject(this.socksProxyManage);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SocksProxyManagePanel");
        frame.setContentPane(new SocksProxy(new SimpleHttpRequestHandle()).getView());
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setVisible(true);
    }

    public void setHttpRequestHandle(HttpRequestHandle requestHandle) {
        this.socksServerConfig.requestHandle = requestHandle;
    }

    protected void parseConfig() {
        this.socksServerConfig.setBindAddress(this.socksProxyManage.socksBindAddressTextField.getText().trim());
        this.socksServerConfig.setBindPort(Integer.parseInt(this.socksProxyManage.socksBindPortTextField.getText().trim()));
        this.socksServerConfig.remoteProxyUrl = this.socksProxyManage.remoteSocksProxyUrlTextField.getText().trim();
        this.socksServerConfig.remoteKey = this.socksProxyManage.remoteKeyTextField.getText().trim();
        this.socksServerConfig.serverSocketOnceReadSize = Integer.parseInt(this.socksProxyManage.serverSocketOnceReadSizeTextField.getText().trim());
        this.socksServerConfig.serverPacketSize = Integer.parseInt(this.socksProxyManage.serverPacketSizeTextField.getText().trim());
        this.socksServerConfig.clientSocketOnceReadSize.set(Integer.parseInt(this.socksProxyManage.clientSocketOnceReadSizeTextField.getText().trim()));
        this.socksServerConfig.clientPacketSize.set(Integer.parseInt(this.socksProxyManage.clientPacketTextField.getText().trim()));
        this.socksServerConfig.capacity.set(Integer.parseInt(this.socksProxyManage.capacityTextField.getText().trim()));
        this.socksServerConfig.requestDelay.set(Integer.parseInt(this.socksProxyManage.requestDelayTextField.getText().trim()));
        this.socksServerConfig.requestErrRetry.set(Integer.parseInt(this.socksProxyManage.requestErrRetryTextField.getText().trim()));
        this.socksServerConfig.requestErrDelay.set(Integer.parseInt(this.socksProxyManage.requestErrDelayTextField.getText().trim()));
    }

    public boolean testProxyContext(boolean initContext, boolean closeContext) throws UnsupportedOperationException {
        boolean flag = false;
        try {
            if (initContext) {
                this.proxyContext.reset();
                String sessionId = this.proxyContext.generateSessionId();
                if (sessionId == null) {
                    new UnsupportedOperationException("\u672a\u80fd\u83b7\u53d6\u5230Session");
                }
            }
            flag = this.proxyContext.testConnect();
            if (closeContext) {
                try {
                    this.proxyContext.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("\u901a\u4fe1\u65f6\u53d1\u751f\u9519\u8bef \u8bf7\u68c0\u67e5\u670d\u52a1\u662f\u5426\u542f\u52a8 \u7f51\u7edc\u662f\u5426\u7545\u901a \u5bc6\u94a5\u662f\u5426\u6b63\u786e!", e);
        }
        return flag;
    }

    private void stopMenuItemClick(ActionEvent e) {
        String identifier = this.socksProxyManage.proxyManageDataView.GetSelectRow1()[0];
        Retransmission ref = this.retransmissionList.stream().filter(retransmission -> retransmission.identifier.equals(identifier)).findFirst().get();
        if (ref.socketStatus.isActive()) {
            if (ref.socketStatus.stop()) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u5173\u95ed\u6210\u529f!");
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u5173\u95ed\u5931\u8d25!");
            }
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u5df2\u5173\u95ed!");
        }
        this.refreshTable();
    }

    private void removeMenuItemClick(ActionEvent e) {
        String identifier = this.socksProxyManage.proxyManageDataView.GetSelectRow1()[0];
        Retransmission ref = this.retransmissionList.stream().filter(retransmission -> retransmission.identifier.equals(identifier)).findFirst().get();
        if (!ref.socketStatus.isActive()) {
            this.retransmissionList.remove(ref);
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u5df2\u5220\u9664!");
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u76ee\u6807\u662f\u542f\u52a8\u72b6\u6001 \u9700\u505c\u6b62\u670d\u52a1\u624d\u53ef\u8fdb\u884c\u5220\u9664!");
        }
        this.refreshTable();
    }

    private void refreshMenuItemClick(ActionEvent e) {
        this.refreshTable();
    }

    private void testButtonClick(ActionEvent actionEvent) {
        this.parseConfig();
        try {
            if (!this.testProxyContext(true, true)) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u65e0\u6cd5\u8fdb\u884c\u901a\u4fe1 \u8bf7\u68c0\u67e5\u670d\u52a1\u662f\u5426\u542f\u52a8 \u7f51\u7edc\u662f\u5426\u7545\u901a!");
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "successfully!");
            }
        } catch (Exception e) {
            Log.error(e);
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), e.getMessage());
        }
    }

    private void startSocksServerButtonClick(ActionEvent actionEvent) {
        this.parseConfig();
        try {
            if (!this.testProxyContext(true, false)) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u65e0\u6cd5\u8fdb\u884c\u901a\u4fe1 \u8bf7\u68c0\u67e5\u670d\u52a1\u662f\u5426\u542f\u52a8 \u7f51\u7edc\u662f\u5426\u7545\u901a!");
            } else if (this.proxyContext.start()) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "socks\u4ee3\u7406\u5df2\u542f\u52a8!");
                ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, "socksProxyManage");
                new Thread(this::calcTips).start();
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "socks\u4ee3\u7406\u542f\u52a8\u5931\u8d25!");
            }
        } catch (Exception e) {
            Log.error(e);
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), e.getMessage());
        }
    }

    private void addNewProxyButtonClick(ActionEvent actionEvent) {
        Retransmission choose = ChooseNewRetransmissionDialog.chooseNewProxy(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()));
        if (choose != null && choose.retransmissionType != RetransmissionType.NULL) {
            SocketStatus socketStatus = null;
            if (choose.retransmissionType == RetransmissionType.PORT_FORWARD) {
                PortForward portForward = new PortForward(new InetSocketAddress(choose.listenAddress, choose.listenPort), this.proxyContext, choose.targetAddress, String.valueOf(choose.targetPort));
                portForward.start();
                socketStatus = portForward;
            } else if (choose.retransmissionType == RetransmissionType.PORT_MAP) {
                socketStatus = this.proxyContext.addBindMirror(choose.listenAddress, String.valueOf(choose.listenPort), choose.targetAddress, String.valueOf(choose.targetPort));
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            choose.socketStatus = socketStatus;
            this.retransmissionList.add(choose);
            if (socketStatus.isActive()) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), "\u542f\u52a8\u6210\u529f!");
            } else {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.socksProxyManage.getMainPanel()), String.format(EasyI18N.getI18nString("\u542f\u52a8\u5931\u8d25! \u9519\u8bef\u4fe1\u606f:%s"), socketStatus.getErrorMessage()));
            }
            this.refreshTable();
        } else {
            Log.error("\u7528\u6237\u53d6\u6d88\u9009\u62e9.......");
        }
    }

    private void stopProxyButtonClick(ActionEvent actionEvent) {
        this.proxyContext.reset();
        this.retransmissionList.clear();
        this.refreshTable();
        ((CardLayout)this.mainPanel.getLayout()).show(this.mainPanel, "startSocksProxy");
    }

    private void serverProxyConfigButtonClick(ActionEvent actionEvent) {
        SocksClientRetransmissionConfigManage.socksServerConfig(UiFunction.getParentWindow(this.mainPanel), this.socksServerConfig);
    }

    protected void refreshTable() {
        this.socksProxyManage.proxyManageDataView.RemoveALL();
        this.retransmissionList.stream().forEach(retransmission -> {
            Vector<Object> row = new Vector<Object>();
            row.add(retransmission.identifier);
            row.add(retransmission.listenAddress);
            row.add(retransmission.listenPort);
            row.add((Object)retransmission.retransmissionType);
            row.add(retransmission.targetAddress);
            row.add(retransmission.targetPort);
            row.add(retransmission.socketStatus.isActive());
            row.add(retransmission.socketStatus.getErrorMessage());
            this.socksProxyManage.proxyManageDataView.AddRow(row);
        });
    }

    protected void calcTips() {
        long lastUpload = 0L;
        long lastDownload = 0L;
        while (this.proxyContext.isAlive()) {
            long runtime = (System.currentTimeMillis() - this.proxyContext.getStartSocksTime()) / 1000L;
            long connNum = this.proxyContext.getSession().size();
            long uploadSpeed = this.proxyContext.getSummaryUploadBytes() - lastUpload;
            long downloadSpeed = this.proxyContext.getSummaryDownloadBytes() - lastDownload;
            lastUpload = this.proxyContext.getSummaryUploadBytes();
            lastDownload = this.proxyContext.getSummaryDownloadBytes();
            String status = String.format(EasyI18N.getI18nString("\u5f53\u524d\u8fde\u63a5\u6570:%d \u5f53\u524d\u4e0a\u4f20\u901f\u5ea6:%s/s \u5f53\u524d\u4e0b\u8f7d\u901f\u5ea6:%s/s \u53d1\u9001\u6210\u529f\u8bf7\u6c42:%s \u53d1\u9001\u5931\u8d25\u8bf7\u6c42:%s \u76d1\u542c\u5730\u5740:%s \u76d1\u542c\u7aef\u53e3:%d \u5df2\u4e0a\u4f20:%s \u5df2\u4e0b\u8f7d:%s  \u8fd0\u884c\u65f6\u95f4:%ds"), connNum, functions.getNetworSpeedk(uploadSpeed), functions.getNetworSpeedk(downloadSpeed), this.proxyContext.getRequestSuccessNum(), this.proxyContext.getRequestFailureNum(), this.socksServerConfig.bindAddress, this.socksServerConfig.bindPort, functions.getNetworSpeedk(lastUpload), functions.getNetworSpeedk(lastDownload), runtime);
            this.socksProxyManage.statusLabel.setText(status);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        this.stopProxyButtonClick(null);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        automaticBindClick.bindJButtonClick(SocksProxy.class, this, SocksProxy.class, this);
    }

    @Override
    public JPanel getView() {
        return this.mainPanel;
    }
}

