package com.core.technology.test;

public class BMPMain extends javax.swing.JFrame{
	MyPanle myPanle = null;//我的面板
	static int windos_width=400,windos_height=300;




	public static void main(String[] args) {
		BMPMain main = new BMPMain();
		main.showUI();
	}

	/**
	 * 显示窗体
	 */
	public void showUI(){
		this.setTitle("我的BMP解码器");
		this.setSize(windos_width, windos_height);
		this.setLocation(200, 100);
		this.setResizable(false);

		myPanle = new MyPanle();
		this.add(myPanle);

		this.setJMenuBar(getMB());
		this.setVisible(true);

	}

	//得到菜单条
	public javax.swing.JMenuBar getMB(){
		javax.swing.JMenuBar mb = new javax.swing.JMenuBar();

		javax.swing.JMenu File = new javax.swing.JMenu("文件");
		javax.swing.JMenu Help = new javax.swing.JMenu("帮助");

		javax.swing.JMenuItem open = new javax.swing.JMenuItem("打开");
		//	javax.swing.JMenuItem save = new javax.swing.JMenuItem("保存");
		javax.swing.JMenuItem exit = new javax.swing.JMenuItem("退出");
		File.add(open);
		//File.add(save);
		File.addSeparator();
		File.add(exit);


		javax.swing.JMenuItem about = new javax.swing.JMenuItem("关于");
		Help.add(about);
		//添加菜单监听器
		MenuLisener al = new MenuLisener();
		about.addActionListener(al);
		open.addActionListener(al);
		//save.addActionListener(al);
		exit.addActionListener(al);

		mb.add(File);
		mb.add(Help);
		return mb;
	}
}



//定义自己的面板
class MyPanle extends javax.swing.JPanel{

}
