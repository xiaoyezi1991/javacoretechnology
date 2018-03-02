package com.core.technology.test;

import java.awt.event.ActionEvent;

import javax.swing.filechooser.FileNameExtensionFilter;


public class MenuLisener implements java.awt.event.ActionListener{


	public void actionPerformed(ActionEvent e) {
		String commen = e.getActionCommand();
		if("关于".equals(commen)){
			//System.out.println("111111111");
			javax.swing.JOptionPane.showConfirmDialog(null, "By Stchou 2010@NetJava","关于",javax.swing.JOptionPane.YES_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
		}if("退出".equals(commen)){
			System.exit(0);
		}if("打开".equals(commen)){
			//弹出文件选择框
			javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
			//过滤
			FileNameExtensionFilter filter = new FileNameExtensionFilter("BMP Images", "BMP");
			chooser.setFileFilter(filter);
			//   System.out.println("22222222");
			chooser.showOpenDialog(null);
			//得到选择文件的路径
			String path = chooser.getSelectedFile().getAbsolutePath();
			//解析，显示图像
			Decode decode = new Decode(path);
			decode.doit();

		}if("保存".equals(commen)){

		}
	}

}
