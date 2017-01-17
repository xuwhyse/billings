package com.awhyse.concurrent.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成工具
 * @author xumin 2015-6-3
 * 
 */
public class EWCodeUtil {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final String CHARSET = "utf-8";  
    private static final String FORMAT_NAME = "JPG";  
    // 二维码尺寸,注意如果二维码尺寸比较小，logo比较大，就扫描不出来，一般二维码是logo 4倍大小
    private static final int QRCODE_SIZE = 400; //300
    // LOGO宽度  
    private static final int WIDTH = 80;//60
    // LOGO高度  
    private static final int HEIGHT = 100;//60

	/**
	 * Administrator 2015-6-3 上午11:25:48
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "谦卑者为尊贵,这个要记住";
		String path = "G:/";
		try {
			encode(content, path);
//			encode(content, path+"/iap.png", path, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建带logo的二维码
	 *Administrator  2015-6-3 下午1:28:18
	 * @param content  扫描内容
	 * @param imgPath  logo地址
	 * @param needCompress  是否压缩成我们指定的logo大小60*60
	 * @return
	 * @throws Exception
	 */
	private static BufferedImage createImage(String content, String imgPath,  
            boolean needCompress) throws Exception {  
		BufferedImage image = getNormalQRCode(content);
        if (imgPath == null || "".equals(imgPath)) {  
            return image;  
        }
        // 插入图片,logo图片大小由我们定
        insertImage(image, imgPath, needCompress);  
        return image;  
    } 
	/**
	 * 
	 * xumin  2015-6-11 上午11:48:20
	 * @param content
	 * @param is  logo的输入流
	 * @param needCompress
	 * @return
	 * @throws Exception
	 */
	private static BufferedImage createImage(String content, InputStream is,
			boolean needCompress) throws Exception {
		BufferedImage image = getNormalQRCode(content);
        if (is == null ) {  
            return image;  
        }
        // 插入图片,logo图片大小由我们定
        insertImage(image, is, needCompress);  
        return image;  
	}  
	/**
	 * 获取普通的二维码图片对象
	 * xumin  2015-6-11 上午11:45:46
	 * @param content
	 * @return
	 * @throws WriterException
	 */
	private static BufferedImage getNormalQRCode(String content) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK  
                        : WHITE);  
            }  
        }  
		return image;
	}
	/** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param imgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String imgPath,  
            boolean needCompress) throws Exception {  
        File file = new File(imgPath);  
        if (!file.exists()) {  
            System.err.println(""+imgPath+"   该文件不存在！");  
            return;  
        }  
        Image src = ImageIO.read(new File(imgPath));
        //改造以下
        insertImageDo(source,src,needCompress);
    }
    /**
     * 
     * xumin  2015-6-11 上午11:56:24
     * @param source  普通二维码图片
     * @param is  这个常常变，现在是logo输入流
     * @param needCompress
     * @throws Exception 
     */
    private static void insertImage(BufferedImage source, InputStream is,
			boolean needCompress) throws Exception {
    	 Image src = ImageIO.read(is);
         //改造以下
         insertImageDo(source,src,needCompress);
	}
    /**
     * 
     * xumin  2015-6-11 上午11:54:18
     * @param source  普通二维码的BufferedImage对象
     * @param src logo的image对象
     * @param needCompress
     */
    private static void insertImageDo(BufferedImage source, Image src,
			boolean needCompress) {
    	int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > WIDTH) {  
                width = WIDTH;  
            }  
            if (height > HEIGHT) {  
                height = HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height,  
                    Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (QRCODE_SIZE - width) / 2;  
        int y = (QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
	}
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath, String destPath,  
            boolean needCompress) throws Exception {  
        BufferedImage image = createImage(content, imgPath,  
                needCompress);  
        mkdirs(destPath);  
        String file = new Random().nextInt(99999999)+".jpg";  
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));  
    }  
  
    /** 
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常) 
     * @author lanyuan 
     * Email: mmm333zzz520@163.com 
     * @date 2013-12-11 上午10:16:36 
     * @param destPath 存放目录 
     */  
    public static void mkdirs(String destPath) {  
        File file =new File(destPath);      
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)  
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath, String destPath)  
            throws Exception {  
        encode(content, imgPath, destPath, false);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String destPath,  
            boolean needCompress) throws Exception {  
        encode(content, null, destPath, needCompress);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static void encode(String content, String destPath) throws Exception {  
        encode(content, null, destPath, false);  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath,  
            OutputStream output, boolean needCompress) throws Exception {  
        BufferedImage image = createImage(content, imgPath,  
                needCompress);  
        ImageIO.write(image, FORMAT_NAME, output);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */  
    public static void encode(String content, OutputStream output)  
            throws Exception {  
        encode(content, null, output, false);  
    }
    /**
     * 
     * xumin  2015-6-11 上午11:24:49
     * @param uFSDownloadMetadata
     * @param contentURL  跳转的内容url
     * @return
     * @throws Exception 
     */
	public static Map<String, Object> getQRMap(InputStream is, String contentURL) throws Exception {
		// TODO Auto-generated method stub
		BufferedImage image = createImage(contentURL, is, true);//容易抛出异常，获取压缩过的logo二维码
		//转换并存储
		Map<String, Object> tar = new HashMap<String, Object>(5);
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		ImageIO.write(image, "jpg", os);//这边就默认是jpg格式了
		InputStream RQIs = new ByteArrayInputStream(os.toByteArray());
		int size = os.toByteArray().length;
		tar.put("InputStream", RQIs);
		tar.put("size", size);
		return tar;
	}
	@SuppressWarnings("unused")
	private static InputStream getInputStreamByImage(BufferedImage image) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		ImageIO.write(image, "jpg", os);  
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		return is;
	}
  
    /** 
     * 解析二维码 
     *  
     * @param file 
     *            二维码图片 
     * @return 
     * @throws Exception 
     */  
//    public static String decode(File file) throws Exception {  
//        BufferedImage image;  
//        image = ImageIO.read(file);  
//        if (image == null) {  
//            return null;  
//        }  
//        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(  
//                image);  
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
//        Result result;  
//        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
//        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);  
//        result = new MultiFormatReader().decode(bitmap, hints);  
//        String resultStr = result.getText();  
//        return resultStr;  
//    }  
  
    /** 
     * 解析二维码 
     *  
     * @param path 
     *            二维码图片地址 
     * @return 
     * @throws Exception 
     */  
//    public static String decode(String path) throws Exception {  
//        return .decode(new File(path));  
//    }  

}