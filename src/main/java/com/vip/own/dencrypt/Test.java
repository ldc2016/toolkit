package com.vip.own.dencrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {

	public static void main(String[] args) throws Exception {
		
		File inputFile = new File("D:\\vipshop_finance_account.csv");
		File outputFile = new File("D:\\user_info.csv");
		
		FileInputStream fin = new FileInputStream(inputFile);
		FileOutputStream fos = new FileOutputStream(outputFile);
		
		InputStreamReader fileReader = new InputStreamReader(fin);
		OutputStreamWriter fileWriter = new OutputStreamWriter(fos);
		
		BufferedReader reader = new BufferedReader(fileReader);
		BufferedWriter writer = new BufferedWriter(fileWriter);
		
		String dataLine = null;
		while((dataLine = reader.readLine()) != null ){
			String[] datas = dataLine.split(",");
			if(datas.length != 2){
				throw new RuntimeException("数据不符合规范");
			}
			
			String phoneNum = SecretUtils.decryptMode(datas[1].replace("\"", ""));
			writer.write(datas[0] + "," + phoneNum);
			writer.newLine();
			writer.flush();

		}
		
		writer.close();
		reader.close();
		System.out.println("解密完成！");
	}
	
}
