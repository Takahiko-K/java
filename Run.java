package tool;

import java.awt.TrayIcon;
import java.io.File;
import java.util.Date;
import java.util.TimerTask;

public class Run extends TimerTask {
	static int Size;
	int FirstSize;
	
	public void run() {
		
		System.out.println("�^�X�N���s�F" + new Date());
		File TestFile = new File("test.txt");
		Size = (int)TestFile.length();
		System.out.print(Size);
		
	            	if(FirstSize == 0){
	            	//	MainUpdateCheker.icon.displayMessage("���b�Z�[�W", "�t�@�C���̑傫����0�ł�", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	if(FirstSize == Size){
	            	//	MainUpdateCheker.icon.displayMessage("���b�Z�[�W", "�t�@�C���̑傫���������ł�", TrayIcon.MessageType.WARNING);
	            		FirstSize = Size;
	            	}
	            	
	            	else if (FirstSize != Size) {
	            		MainUpdateCheker.icon.displayMessage("���b�Z�[�W", "�t�@�C�����X�V����܂���", TrayIcon.MessageType.INFO);
	            		FirstSize = Size;
	            	}
	            }					
	}

