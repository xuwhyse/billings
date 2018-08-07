package com.awhyse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	/**
	 * @param args
	 *            author:xumin 2016-11-22 下午8:39:56
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "C:/Users/whyse/Desktop/任务/小T/dce_future_puzzles.dce_future_puzzles";
		List<String> listStr = readFileByLines(path);
		String[]  strs = listStr.get(0).split(" ");
		System.err.println(strs);
	}

	public static void writeToFileAll(String str, String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				// file.mkdirs();
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file, false); // 如果追加方式用true
			out.write(str.getBytes("utf-8"));// 注意需要转换对应的字符集
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	public static String readFileAll(String path) {
		StringBuffer sb = new StringBuffer();
		String tempstr = null;
		try {
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();
			// BufferedReader br=new BufferedReader(new FileReader(file));
			// while((tempstr=br.readLine())!=null)
			// sb.append(tempstr);
			// 另一种读取方式
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while ((tempstr = br.readLine()) != null)
				sb.append(tempstr);
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
		return sb.toString();
	}

	public static List<String> readFileByLines(String fileName) {
		File file = new File(fileName);
		if(!file.exists()){
			System.err.println("文件不存在");
		}
		List<String>  listStr = new ArrayList<>(60);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				// 把当前行号显示出来
//				System.out.println("line " + line + ": " + tempString);
//				line++;
				listStr.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return listStr;
	}

	/**
	 * 在一段字节码里面找到符合 UTF-8 编码的字节数量. 取其数量占比
	 * 同时考虑到了 BOM 位 (仅在初始值时) 未针对 Unicode 其它编码方式进行判断。
	 * 该端代码具有很强的针对性, 如果文件是文本则可以很高效的分辨出是不是UTF-8, 但是如果是二进制文件, 则存在较大几率上的误差.
	 * <p>
	 * 参照格式为：
	 * 0x0*******
	 * <p>
	 * 0x110*****
	 * 0x10******
	 * <p>
	 * 0x1110****
	 * 0x10******
	 * 0x10******
	 * <p>
	 * 0x11110***
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 * <p>
	 * 0x111110**
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 * <p>
	 * 0x1111110*
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 * 0x10******
	 *
	 * @param raw 指定的字节码
	 * @return 数量占比  如果是100的可以任务是对的
	 */
	private static int utf8(byte[] raw) {
		int i, len;
		int utf8 = 0, ascii = 0;

		if (raw.length > 3) {
			if ((raw[0] == (byte) 0xEF) && (raw[1] == (byte) 0xBB) && (raw[2] == (byte) 0xBF)) {
				return 100;
			}
		}
		len = raw.length;
		int child = 0;
		f:
		for (i = 0; i < len; ) {

			// UTF-8 中一定没有 FF 和 FE
			if ((raw[i] & (byte) 0xFF) == (byte) 0xFF || (raw[i] & (byte) 0xFE) == (byte) 0xFE) {
				return 0;
			}

			if (child == 0) {
				if ((raw[i] & (byte) 0x7F) == raw[i] && raw[i] != 0) {
					// ASCII 在所有编码中格式为 0x0*******
					ascii++;
				} else if ((raw[i] & (byte) 0xC0) == (byte) 0xC0) {
					// 如果是 0x11****** 形式的, 则有一定可能性是 UTF-8 的
					for (int bit = 0; bit < 8; bit++) {
						if ((((byte) (0x80 >> bit)) & raw[i]) == ((byte) (0x80 >> bit))) {
							child = bit;
						} else {
							break;
						}
					}
					utf8++;
				}
				i++;
			} else {
				child = (raw.length - i > child) ? child : (raw.length - i);
				boolean currentNotUtf8 = false;
				for (int children = 0; children < child; children++) {
					// 格式必须是 10******
					if ((raw[i + children] & ((byte) 0x80)) != ((byte) 0x80)) {
						if ((raw[i + children] & (byte) 0x7F) == raw[i + children] && raw[i] != 0) {
							// ASCII 在所有编码中格式为 0x0*******
							ascii++;
						}
						currentNotUtf8 = true;
					}
				}
				if (currentNotUtf8) {
					utf8--;
					i++;
				} else {
					utf8 += child;
					i += child;
				}
				child = 0;
			}
		}
		// 纯ascii的, 也可以理解为纯 utf-8 的。
		if (ascii == len) {
			return 100;
		}
		// 把 ascii 的也算成 utf-8 的。
		return (int) (100 * ((float) (utf8 + ascii) / (float) len));
	}

}
