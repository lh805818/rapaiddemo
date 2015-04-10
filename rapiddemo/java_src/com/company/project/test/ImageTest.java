package com.company.project.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageTest {

	/**
	 *
	 * @author qincd
	 * @email qincd@hyxt.com
	 * @date Dec 10, 2014 5:13:03 PM
	 */
	public static void main(String[] args) {
		String image = "C:/Users/qince/Pictures/360壁纸 6864.jpg";
//		BufferedImage newimage = zoomImage(0.3d,image);
//		try {
//			writeHighQuality(newimage, "d:/test.jpg");
//		} catch (ImageFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		String logo = "C:/Users/qince/Pictures/t01c9ab36714f05cb38.png";
//		String qrcode = "http://192.168.1.82/*NN5anbtgB/img";
//		drawImageAndText(image,logo,qrcode,"100","d:/test1.jpg");
		
		try {
			//createImage();
			BufferedImage src = ImageIO.read(new File(image));
			try {
				cutImg(src, 120, 153, 22, 33, 200, 200);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 平滑等比缩放图片
	 *
	 * @author qincd
	 * @email qincd@hyxt.com
	 * @date Dec 10, 2014 5:18:42 PM
	 */
	public static BufferedImage zoomImage(double presslimit,Object...params) {
		try {
			BufferedImage sourceimage = null;
			if (params != null && params[0] instanceof String) {
				if (params[0].toString().startsWith("http")) {
					sourceimage = ImageIO.read(new URL(params[0].toString()));
				}
				else {
					sourceimage = ImageIO.read(new File(params[0].toString()));
				}
			}
			else {
				sourceimage = (BufferedImage) params[0];
			}
			int width = sourceimage.getWidth();
			int height = sourceimage.getHeight();
			
			int newwidth = (int)(width * presslimit);
			int newheight = (int)(height * presslimit);
			
			BufferedImage newimage = new BufferedImage(newwidth,newheight,BufferedImage.TYPE_INT_RGB);
			Image pressimage = sourceimage.getScaledInstance(newwidth, newheight, Image.SCALE_SMOOTH);
			newimage.getGraphics().drawImage(pressimage, 0, 0, null);
			return newimage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void drawImageAndText(String image,String logo,String qrcode,String text,String savepath) {
		BufferedImage bi = new BufferedImage(720,1033,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setBackground(Color.white);
		g.clearRect(0, 0, 720, 1033);
		
		// 画用户上传的图片
		int ix = 12;
		int iy = 15;
		BufferedImage newimage = zoomImage(0.3d,image);
		g.drawImage(newimage, ix,iy,null);
		
		// 画logo
		int lx = ix;
		int ly = iy + newimage.getHeight() + 30;
		BufferedImage logoimage = zoomImage(1d, logo);
		g.drawImage(logoimage, lx,ly,null);
		
		// 画文字
		// 1).画数字
		int tx = ix + logoimage.getWidth() + 10;
		int ty = ly + 50;
		int nx = tx;
		int ny = ty;
		Font f = new Font("宋体", Font.BOLD, 88);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString(text, tx, ty);
		
		// 2).画文字1
		tx += text.length() * 45;
		ty -= 30;
		Font f2 = new Font("宋体", Font.BOLD, 32);
		g.setFont(f2);
		g.drawString("抵用", tx, ty);
		
		// 3).画文字2
		ty += 30;
		g.drawString("元券", tx, ty);
		
		// 4).画文字4（最后一行）
		Font f3 = new Font("宋体", Font.BOLD, 18);
		g.setFont(f3);
		ny += 20;
		String msg = "11月4日至12月19日全场通用";
		if (msg.length() > 12) {
			double cl = msg.length();
			double n = cl / 12d;
			double l = Math.ceil(n);
			for (int m = 0; m < l && m < 5; m++) {
				int start = m * 12;
				int end = (m + 1) * 12 > msg.length() ? msg.length() : (m + 1) * 12;
				g.drawString(msg.substring(start, end), nx, ny + m * 20);
			}
		} else {
			g.drawString(msg, nx, ny);
		}
		
		// 画二维码
		int qx = tx + 70;
		int qy = ly - 30;
		BufferedImage qrcodeimage = zoomImage(0.9d, qrcode);
		g.drawImage(qrcodeimage, qx,qy,null);
		g.dispose();
		
		try {
			writeHighQuality(bi, savepath);
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 高质量输出图片到文件
	 *
	 * @author qincd
	 * @email qincd@hyxt.com
	 * @date Dec 10, 2014 5:25:07 PM
	 */
	public static void writeHighQuality(BufferedImage image,String imageFullPath) throws ImageFormatException, IOException {
		FileOutputStream fos = new FileOutputStream(imageFullPath);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
		encoder.setJPEGEncodeParam(param);
		param.setQuality(0.9f, true);
		encoder.encode(image, param);
		fos.close();
	}
	
	public static void createImage() throws IOException {
		// 1，建立画板
		BufferedImage return_image = new BufferedImage(720, 1033, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = return_image.createGraphics();
		g.setBackground(Color.WHITE);
		int drawImgW = 720, drawImgH = 1033;
		g.clearRect(0, 0, drawImgW, drawImgH);
		// 2,获取照片
		// String imgurl =
		// "http://lironghuatest.ematong.com/wxdyh/upload/picmsg/158/2740222990.jpg";
		String imgurl = "C:/Users/qince/Pictures/360壁纸 6864.jpg";
		DataInputStream photo_in = new DataInputStream(new FileInputStream(new File(imgurl)));
		BufferedImage image = ImageIO.read(photo_in);

		int oldW = image.getWidth(), oldH = image.getHeight(); // 原图横纵大小
		int cutW = oldW > oldH ? oldH : oldW;
		int startx = 0, starty = 0;// 剪切照片的其实坐标
		int photox = 12, photoy = 15;// 照片坐标
		//int codex = -4, codey = 740;// 二维码坐标
		int codex = 500, codey = 740;// 二维码坐标
		int msg1x = 46, msg1y = 975;// 扫码有惊喜坐标
		int msg2x = 343, msg2y = 783;// 留言坐标
		//int logox = 500, logoy = 790; // logo坐标4
		int logox = -4, logoy = 740; // logo坐标4
		// int logox = 536,logoy = 796 ; //logo坐标3
		// int logox = 309,logoy = 740 ; //logo坐标1
		// int logox = 377,logoy = 776 ; //logo坐标2
		double zoomRate = 1;

		if (oldW > oldH) { // 横着的长方形
			startx = (oldW - oldH) / 2;
		} else {// 竖着的长方形
			starty = (oldH - oldW) / 2;
		}
		
		if (image.getWidth() > image.getHeight()) {
			if (image.getWidth() > drawImgW) {
				zoomRate = (double) drawImgW / image.getWidth();
			}
			if (image.getWidth() < drawImgW) {
				zoomRate = (double) image.getWidth() / drawImgW;
			}
		} else {
			if (image.getHeight() > drawImgH) {
				zoomRate = (double) drawImgH / image.getHeight();
			}
			if (image.getWidth() < drawImgW) {
				zoomRate = (double) image.getHeight() / drawImgH;
			}
		}

		BufferedImage photo_image = null;
		try {
			photo_image = cutImg(image, cutW, cutW, (int) Math.round(startx * zoomRate), (int) Math.round(starty * zoomRate), (int) Math.round(oldW * zoomRate), (int) Math.round(oldH
					* zoomRate));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 1).画图片
		g.drawImage(photo_image, photox, photoy, 697, 697, null);
		
		// 2).画logo
		String logourl = "D:/qincd/DEV/develop/develop/apache-tomcat-5.5.20/webapps/zdffad/upload/153/1418267236944_source.jpg";
		DataInputStream logo_in = new DataInputStream(new FileInputStream(new File(logourl)));
		BufferedImage logo_image = null;
		
		try {
			logo_image = ImageIO.read(logo_in);
		} catch (IIOException e) {
		}
		
		double logozoomrate = 1;
		if (logo_image.getWidth() > logo_image.getHeight()) {
			if (logo_image.getWidth() > 225) {
				logozoomrate = (double) 225 / logo_image.getWidth();
			}
			if (logo_image.getWidth() < 225) {
				logozoomrate = (double) logo_image.getWidth() / 225;
			}
		} else {
			if (logo_image.getHeight() > 225) {
				logozoomrate = (double) 225 / logo_image.getHeight();
			}
			if (logo_image.getWidth() < 225) {
				logozoomrate = (double) logo_image.getHeight() / 225;
			}
		}
		
		int logoW = (int) Math.round(logo_image.getWidth() * logozoomrate), logoH = (int) Math.round(logo_image.getHeight() * logozoomrate);
		int logoX = ((485 - logoW) / 2 + 220), logoY = ((225 - logoH) / 2 + 740);
		logoX = photox;
		logoY = 740;
		
		BufferedImage resizeImg = resizeImg(logo_image, logoW, logoH); // 拉伸之后
		// 周大福的logo大小160*160
		g.drawImage(resizeImg, logoX, logoY, logoW, logoH, null);
		logo_image.flush();

		// 3).画文字
		String quanvalue = String.valueOf(9);
		String leftblank = "";
		for (int i=quanvalue.length();i<3;i++) {
			leftblank += "  ";
		}
		quanvalue = leftblank + quanvalue;
		String quantype = "2";
		String bigtextunit = "1".equals(quantype) ? "元": "折";
		
		// 3.1)画金额
		int quanvalueX = logoX + resizeImg.getWidth() + 20;
		System.out.println("quanvalueX:" + quanvalueX);
		int quanvalueY = logoY + 80;
		Font f = new Font("文泉驿", Font.BOLD, 120 - (quanvalue.length() - 1) * 10);
		g.setFont(f);
		g.setColor(Color.red);
		g.drawString(quanvalue, quanvalueX, quanvalueY);
		
		// 3.2)画文字1->抵用
		String text1 = "抵用";
		int stringWidth  =g.getFontMetrics().stringWidth(quanvalue);
		System.out.println("stringWidth:" + stringWidth);
		int text1X = quanvalueX + stringWidth;
		System.out.println("text1X:" + text1X);
		int text1Y = quanvalueY - 30;
		f = new Font("文泉驿", Font.PLAIN, 36);
		g.setFont(f);
		g.drawString(text1, text1X, text1Y);
		
		// 3.3)画文字2->元/折券
		String text2 = bigtextunit + "券";
		int text2X = quanvalueX + stringWidth;
		System.out.println("text2X:" + text2X);
		int text2Y = quanvalueY + 10;
		g.drawString(text2, text2X, text2Y);
		
		// 3.4)画大文字下面的一行小提示文字
		int msgX = logoX + + resizeImg.getWidth() + 5;
		int msgY = text2Y + 40;
		String msg = "有效期:2014/12/11至2015/01/15";
		f = new Font("文泉驿", Font.PLAIN, 18);
		g.setFont(f);
		g.drawString(msg, msgX, msgY);
		
		// 4).画二维码
		codey = logoY - 20;
		String qrcodeUrl = "http://192.168.1.82/*NN5anbtgB/img";
		BufferedImage code_image = ImageIO.read(new URL(qrcodeUrl));
		g.drawImage(code_image, codex, codey, 223, 223, null);
		

		// 5，关闭画板 释放资源
		g.dispose();
		return_image.flush();
		image.flush();

		// 6,保存生成的图片到本地
		String resultpicname = "d:/qq.jpg";
		File fileIn = new File(resultpicname);
		OutputStream os = new FileOutputStream(fileIn);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(return_image);

	}
	
	/**
	 * 裁剪图片
	 * 
	 * @param src
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage cropImg(BufferedImage src, int width, int height, int x, int y) {
		int old_w = src.getWidth(null);
		int old_h = src.getHeight(null);
		double rate1 = 1.0 * old_w / width;
		double rate2 = 1.0 * old_h / height;
		int new_w;
		int new_h;
		if (rate1 > rate2) {
			new_h = height;
			new_w = (int) Math.round(1.0 * new_h * old_w / old_h);
		} else {
			new_w = width;
			new_h = (int) Math.round(1.0 * new_w * old_h / old_w);
		}

		// 裁剪图片
		int g_w = old_w, g_h = old_h;
		if (rate1 > rate2) {
			g_w = (int) Math.round(1.0 * width * old_h / height);
		} else {
			g_h = (int) Math.round(1.0 * old_w * height / width);
		}
		ImageFilter cropFilter = new CropImageFilter(x, y, g_w, g_h);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter));
		BufferedImage tag = new BufferedImage(g_w, g_h, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(img, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		return tag;
	}

	/**
	 * 等比缩放
	 * 
	 * @param src
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resizeImg(BufferedImage src, int width, int height) {
		int old_w = src.getWidth(null);
		int old_h = src.getHeight(null);
		int new_w;
		int new_h;
		// 计算比率
		double rate1 = 1.0 * old_w / width;
		double rate2 = 1.0 * old_h / height;
		if (rate1 > rate2) {
			new_w = width;
			new_h = (int) Math.round(1.0 * new_w * old_h / old_w);
		} else {
			new_h = height;
			new_w = (int) Math.round(1.0 * new_h * old_w / old_h);
		}
		BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(src, 0, 0, new_w, new_h, null);
		g.dispose();
		return tag;
	}

	public static BufferedImage cutImg(BufferedImage src, int width, int height, int x, int y, int oldW, int oldH) throws Exception {

		int old_w = src.getWidth(null);
		int old_h = src.getHeight(null);
		int newWidth = (int) Math.round(width * old_w / oldW);
		int newHeight = (int) Math.round(height * old_h / oldH);

		int newx = (int) Math.round(x * old_w / oldW);
		int newy = (int) Math.round(y * old_h / oldH);

		double rate1 = 1.0 * old_w / newWidth;
		double rate2 = 1.0 * old_h / newHeight;
		int new_w;
		int new_h;
		if (rate1 > rate2) {
			new_h = newHeight;
			new_w = (int) Math.round(1.0 * new_h * old_w / old_h);
		} else {
			new_w = newWidth;
			new_h = (int) Math.round(1.0 * new_w * old_h / old_w);
		}

		// 裁剪图片
		int g_w = old_w, g_h = old_h;
		if (rate1 > rate2) {
			g_w = (int) Math.round(1.0 * newWidth * old_h / newHeight);
		} else {
			g_h = (int) Math.round(1.0 * old_w * newHeight / newWidth);
		}
		ImageFilter cropFilter = new CropImageFilter(newx, newy, g_w, g_h);
		Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter));
		BufferedImage tag = new BufferedImage(g_w, g_h, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(img, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		return tag;
	}
}
