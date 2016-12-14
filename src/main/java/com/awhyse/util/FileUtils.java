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

}
