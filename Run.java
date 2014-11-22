package tool;

import java.awt.TrayIcon;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class Run extends TimerTask {
	static int Size;
	int FirstSize;
	
	public void run() {
		
		System.out.println("タスク実行：" + new Date());
		File TestFile = new File("test.txt");
		Size = (int)TestFile.length();
		System.out.print(Size);
		
	            	if(FirstSize == 0){
	            	//	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが0です", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	if(FirstSize == Size){
	            	//	MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルの大きさが同じです", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	else if (FirstSize != Size) {
	            		MainUpdateCheker.icon.displayMessage("メッセージ", "ファイルが更新されました", TrayIcon.MessageType.INFO);
	            		FirstSize = Size;
	            	}
	            }					
	}

