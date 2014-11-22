package tool;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

class MainUpdateCheker{
	static TrayIcon icon;
	static TimerTask task;
	static Timer timer;
	
	public static void main(String args[]) throws InterruptedException{
		try{
				//メニューアイテムの作成
				MenuItem watchingItem = new MenuItem("監視対象一覧");
				MenuItem stopItem = new MenuItem("一時停止");
				MenuItem restartItem = new MenuItem("再開");
				MenuItem exitItem = new MenuItem("終了");
				restartItem.setEnabled(false);
			
				//アイコンの作成
				SystemTray tray = SystemTray.getSystemTray();
				icon = new TrayIcon(ImageIO.read(new File("tray_icon.png")));
				tray.add(icon);
		
				// 監視対象一覧
				watchingItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								UpdateChekerFrame.watching();
							}
				});
			
				// 一時停止メニュー
				stopItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						icon.displayMessage("お知らせ","一時停止しました",MessageType.INFO);
						task.cancel();
						task = null;
						stopItem.setEnabled(false);
						restartItem.setEnabled(true);
					}
				});
			
				// 再開
				restartItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						icon.displayMessage("お知らせ","再開しました",MessageType.INFO);
						if (task == null) {
							task = new Run();
						}
						System.out.println("再開します");
						timer.schedule(task, 0, 5000);
						stopItem.setEnabled(true);
						restartItem.setEnabled(false);
					}
				});
			
				// 終了メニュー
				exitItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
				}
				});
			
				// ポップアップメニュー
				PopupMenu menu = new PopupMenu();
        
				// メニューにメニューアイテムを追加
				menu.add(stopItem);
				menu.add(restartItem);
				menu.add(exitItem);
				menu.add(watchingItem);
			
				icon.setPopupMenu(menu);
		
				}catch (IOException ex) {
					ex.printStackTrace();
				}catch (AWTException ex) {
					ex.printStackTrace();
				}
				MainUpdateCheker main = new MainUpdateCheker();
				main.timerperiod();
	}
	void timerperiod() throws InterruptedException{
		task = new Run();
		timer = new Timer();

		timer.schedule(task, 0, 5000);
	}
}
