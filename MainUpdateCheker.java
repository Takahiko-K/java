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
				//���j���[�A�C�e���̍쐬
				MenuItem watchingItem = new MenuItem("�Ď��Ώۈꗗ");
				MenuItem stopItem = new MenuItem("�ꎞ��~");
				MenuItem restartItem = new MenuItem("�ĊJ");
				MenuItem exitItem = new MenuItem("�I��");
				restartItem.setEnabled(false);
			
				//�A�C�R���̍쐬
				SystemTray tray = SystemTray.getSystemTray();
				icon = new TrayIcon(ImageIO.read(new File("tray_icon.png")));
				tray.add(icon);
		
				// �Ď��Ώۈꗗ
				watchingItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								UpdateChekerFrame.watching();
							}
				});
			
				// �ꎞ��~���j���[
				stopItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						icon.displayMessage("���m�点","�ꎞ��~���܂���",MessageType.INFO);
						task.cancel();
						task = null;
						stopItem.setEnabled(false);
						restartItem.setEnabled(true);
					}
				});
			
				// �ĊJ
				restartItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						icon.displayMessage("���m�点","�ĊJ���܂���",MessageType.INFO);
						if (task == null) {
							task = new Run();
						}
						System.out.println("�ĊJ���܂�");
						timer.schedule(task, 0, 5000);
						stopItem.setEnabled(true);
						restartItem.setEnabled(false);
					}
				});
			
				// �I�����j���[
				exitItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
				}
				});
			
				// �|�b�v�A�b�v���j���[
				PopupMenu menu = new PopupMenu();
        
				// ���j���[�Ƀ��j���[�A�C�e����ǉ�
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
