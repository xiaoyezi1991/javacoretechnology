package com.core.technology.test;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;


public class Decode extends javax.swing.JFrame{

	//窗口的画笔
	private java.awt.Graphics g;
	//文件打开的路径
	private String path = null;
	//图像文件的大小
	private int Filesize;
	//读取的两个保留字
	private int ch1,ch2;
	//数据偏移量
	private int dataOffset;
	//图片的宽和高
	private int image_width,image_heigh;
	//存放像素点的颜色
	private int imageR[][],imageG[][],imageB[][];

	//宽取模
	int skip_width=0;

	public  Decode(String path){
		this.path=path;
	}

	public void paint(java.awt.Graphics g){
		for (int h=0;h<image_heigh;h++){
			for (int w=0;w<image_width;w++){
				g.setColor(new java.awt.Color(imageR[h][w],imageG[h][w],imageB[h][w]));
				g.fillOval(w, h, 1, 1);
			}
		}
	}


	/**
	 * 显示出来 BMP文件
	 */
	public void doit(){
		try {
			// 创建文件输入流
			java.io.FileInputStream fis = new java.io.FileInputStream(path);
			// 将文件流包装成一个可以写基本数据类型的输出流
			java.io.DataInputStream dis = new java.io.DataInputStream(fis);

//			if(dis.read ()!='B'&&dis.read()!='M'){
//		          javax.swing.JOptionPane.showMessageDialog(null,"别拿我开玩笑，这个不是BMP文件");
//		          return ;
//			}

			int bflen=14;
			byte bf[]=new byte[bflen];
			dis.read(bf,0,bflen); //读取14字节BMP文件头


			int bilen=40;
			byte bi[]=new byte[bilen];
			dis.read(bi,0,bilen);//读取40字节BMP信息头

			// 获取一些重要数据
			image_width=ChangeInt(bi,7);        		//源图宽度

			System.out.println("宽:"+image_width);

			image_heigh=ChangeInt(bi,11);       	//源图高度
			System.out.println("高:"+image_heigh);
			//位数
			int nbitcount=(((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
			System.out.println("位数:"+nbitcount);
			//源图大小
			int nsizeimage=ChangeInt(bi,23);
			System.out.println("源图大小:"+nsizeimage);


			if  (nbitcount  ==   24 )   {
				showRGB24(dis);
			}



			//关闭文件流
			dis.close();

		} catch (Exception ef) {
			ef.printStackTrace();
		}
	}


	public void showRGB24(DataInputStream dis) throws IOException{
		this.setTitle(path);
		//弹出一个图片的窗口一个大小
		this.setSize(image_width, image_heigh);
		this.setResizable(false);
		this.setVisible(true);
		g=this.getGraphics();


		if(!(image_width*3 % 4==0)){//图片的宽度不为0
			skip_width =4-image_width*3%4;
		}

		imageR = new int[image_heigh][image_width];
		imageG = new int[image_heigh][image_width];
		imageB = new int[image_heigh][image_width];


		//按行读取 如果H,W为正则倒着来
		for (int h=image_heigh-1;h>=0;h--){
			for (int w=0;w<image_width;w++){
				//  读入三原色
				int blue  = dis.read();
				int green = dis.read();
				int red = dis.read();
				imageB[h][w]=blue;
				imageG[h][w]=green;
				imageR[h][w]=red;
				if(w==0){
					System.out.println(dis.skipBytes(skip_width));

				}
			}
		}
		repaint();
	}
	//转成int
	public int ChangeInt(byte[] bi,int start){
		return (((int)bi[start]&0xff)<<24)
				| (((int)bi[start-1]&0xff)<<16)
				| (((int)bi[start-2]&0xff)<<8)
				| (int)bi[start-3]&0xff;
	}


}
