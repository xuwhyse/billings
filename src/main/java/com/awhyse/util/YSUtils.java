package com.awhyse.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * 压缩算法
 * 
 * author:xumin 2016-12-15 下午2:32:34
 */
public class YSUtils {

	static final int bufSize = 1024;//1024
	/**
	 * @param args
	 *            author:xumin 2016-12-15 下午2:32:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十多个的话啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十多个的话" +
				"啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十多个的话啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十多个的话" +
				"啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十多个的话啊三个地方的回归是打工奋斗还过节是打工奋斗京沪高姐十多个地方换马甲十sssssss";
		byte[] temp = str.getBytes();
		System.err.println(temp.length);
		try {
			temp = compress(temp,1);
			System.err.println(temp.length);
			temp = uncompress(temp);
			System.err.println(new String(temp));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 压缩算法 感觉1,2都不错
	 * @param inputByte
	 * @param level 1-9   1:压缩快，压缩率没大的高，不过还不错
	 * @return
	 * @throws IOException
	 * author:xumin 
	 * 2016-12-15 下午2:49:23
	 */
	public static byte[] compress(byte[] inputByte,int level) throws IOException {
		int len = 0;
		Deflater defl = new Deflater(level, true);
		defl.setInput(inputByte);
		defl.setLevel(level);
		defl.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outputByte = new byte[bufSize];
		try {
			while (!defl.finished()) {
				// 压缩并将压缩后的内容输出到字节输出流bos中
				len = defl.deflate(outputByte);
				bos.write(outputByte, 0, len);
			}
			defl.end();
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}
	/**
	 * 解压算法
	 * @param inputByte
	 * @return
	 * @throws IOException
	 * author:xumin 
	 * 2016-12-15 下午2:50:53
	 */
	public static byte[] uncompress(byte[] inputByte) throws IOException {
		int len = 0;
		Inflater infl = new Inflater(true);
		infl.setInput(inputByte);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] outByte = new byte[bufSize];
		try {
			while (!infl.finished()) {
				// 解压缩并将解压缩后的内容输出到字节输出流bos中
				len = infl.inflate(outByte);
				if (len == 0) {
					break;
				}
				bos.write(outByte, 0, len);
			}
			infl.end();
		} catch (Exception e) {
			//
		} finally {
			bos.close();
		}
		return bos.toByteArray();
	}

	
}
