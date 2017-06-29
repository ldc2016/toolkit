package com.vip.own.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vip.own.entity.MothBillsEntity;

public class MakeExcelThreadEntity implements Runnable {
	
    private int round;

    public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public void run() {
		String srcFile = "D:\\新客账单数据0606.xlsx";
		String dstFile = "D:\\新客账单数据00606_records_" + round + ".xlsx";
		String txtFilePath = "D:\\vipshop_finance_month_bills_"+ round +".csv";

		InputStream in =null;
		InputStream is = null;
		OutputStream os = null;
		try {
			
			is = new FileInputStream(srcFile);
	        os = new FileOutputStream(dstFile);
	       
	        XSSFWorkbook dstWorkBook = new XSSFWorkbook(is);
	        XSSFSheet sheet0 = dstWorkBook.getSheetAt(0);
	        XSSFCellStyle cellStyle = dstWorkBook.createCellStyle();
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        
	        BufferedReader bufferedReader = FileReaderUtils.initFileReader(txtFilePath);
	        final MothBillsEntity mothBillsEntity = new MothBillsEntity();

	        int recorCcounts  = 0;
	        int rowNum = 1;
	        while(true){
	            String data = getMonthBillsEntity(bufferedReader,mothBillsEntity);
	            if(StringUtils.isBlank(data)){
	                break;
	            }

	            int result = makeExcelSheet(mothBillsEntity, sheet0, cellStyle,rowNum);
	            rowNum++ ;
	            if(result > 0){
	                recorCcounts ++;
	            }
	        }

	        dstWorkBook.write(os);
	        System.out.println("本次导出记录数为： " + recorCcounts);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
			    if (in != null) {
					in.close();
			    }
			    if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	 private static int makeExcelSheet(MothBillsEntity mothBillsEntity, XSSFSheet sheet, XSSFCellStyle cellStyle,int rowNum) {

	        if (mothBillsEntity == null) {
	            return 0;
	        }

	        XSSFRow row = sheet.createRow(rowNum);
	        if (cellStyle != null) {
	            row.setRowStyle(cellStyle);
	        }

	        XSSFCell cell0 = row.createCell(0);
	        if (mothBillsEntity.getCreateUser()!= null) {
	            cell0.setCellValue(mothBillsEntity.getCreateUser());
	            cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell1 = row.createCell(1);
	        if (mothBillsEntity.getCreateTime() != null) {
	            cell1.setCellValue(mothBillsEntity.getCreateTime());
	            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell2 = row.createCell(2);
	        if (mothBillsEntity.getUpdateUser() != null) {
	            cell2.setCellValue(mothBillsEntity.getUpdateUser());
	            cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell3 = row.createCell(3);
	        if (mothBillsEntity.getUpdateTime() != null) {
	            cell3.setCellValue(mothBillsEntity.getUpdateTime());
	            cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell4 = row.createCell(4);
	        if (mothBillsEntity.getCheckId() != null) {
	            cell4.setCellValue(mothBillsEntity.getCheckId());
	            cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell5 = row.createCell(5);
	        if (mothBillsEntity.getDateTime() != null) {
	            cell5.setCellValue(mothBillsEntity.getDateTime());
	            cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell6 = row.createCell(6);
	        if (mothBillsEntity.getMonthBillNo() != null) {
	            cell6.setCellValue(mothBillsEntity.getMonthBillNo());
	            cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell7 = row.createCell(7);
	        if (mothBillsEntity.getCurrFee() != null) {
	            cell7.setCellValue(mothBillsEntity.getCurrFee());
	            cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell8 = row.createCell(8);
	        if (mothBillsEntity.getCurrNeedPayFee() != null) {
	            cell8.setCellValue(mothBillsEntity.getCurrNeedPayFee());
	            cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell9 = row.createCell(9);
	        if (mothBillsEntity.getCurrNeedPayInterest() != null) {
	            cell9.setCellValue(mothBillsEntity.getCurrNeedPayInterest());
	            cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell10 = row.createCell(10);
	        if (mothBillsEntity.getCurrNeedPayCapital() != null) {
	            cell10.setCellValue(mothBillsEntity.getCurrNeedPayCapital());
	            cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell11 = row.createCell(11);
	        if (mothBillsEntity.getCurrNeedPayTotal() != null) {
	            cell11.setCellValue(mothBillsEntity.getCurrNeedPayTotal());
	            cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell12 = row.createCell(12);
	        if (mothBillsEntity.getCurrInterest() != null) {
	            cell12.setCellValue(mothBillsEntity.getCurrInterest());
	            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell13 = row.createCell(13);
	        if (mothBillsEntity.getCurrCapital() != null) {
	            cell13.setCellValue(mothBillsEntity.getCurrCapital());
	            cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }
	        
	        XSSFCell cell14 = row.createCell(14);
	        if (mothBillsEntity.getCurrYesFee() != null) {
	            cell14.setCellValue(mothBillsEntity.getCurrYesFee());
	            cell14.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell15 = row.createCell(15);
	        if (mothBillsEntity.getCurrYesInterest() != null) {
	            cell15.setCellValue(mothBillsEntity.getCurrYesInterest());
	            cell15.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell16 = row.createCell(16);
	        if (mothBillsEntity.getCurrYesCapital() != null) {
	            cell16.setCellValue(mothBillsEntity.getCurrYesCapital());
	            cell16.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell17 = row.createCell(17);
	        if (mothBillsEntity.getCurrYesTotal() != null) {
	            cell17.setCellValue(mothBillsEntity.getCurrYesTotal());
	            cell17.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell18 = row.createCell(18);
	        if (mothBillsEntity.getLastRepayTime() != null) {
	            cell18.setCellValue(mothBillsEntity.getLastRepayTime());
	            cell18.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell19 = row.createCell(19);
	        if (mothBillsEntity.getLateDays() != null) {
	            cell19.setCellValue(mothBillsEntity.getLateDays());
	            cell19.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell20 = row.createCell(20);
	        if (mothBillsEntity.getOverDueStatus() != null) {
	            cell20.setCellValue(mothBillsEntity.getOverDueStatus());
	            cell20.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell21 = row.createCell(21);
	        if (mothBillsEntity.getPayOffStatus() != null) {
	            cell21.setCellValue(mothBillsEntity.getPayOffStatus());
	            cell21.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell22 = row.createCell(22);
	        if (mothBillsEntity.getCurrYesStagingAmount() != null) {
	            cell22.setCellValue(mothBillsEntity.getCurrYesStagingAmount());
	            cell22.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell23 = row.createCell(23);
	        if (mothBillsEntity.getCurrCanStagingAmount() != null) {
	            cell23.setCellValue(mothBillsEntity.getCurrCanStagingAmount());
	            cell23.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell24 = row.createCell(24);
	        if (mothBillsEntity.getCurrNoStagingAmount() != null) {
	            cell24.setCellValue(mothBillsEntity.getCurrNoStagingAmount());
	            cell24.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        XSSFCell cell25 = row.createCell(25);
	        if (mothBillsEntity.getUid() != null) {
	            cell25.setCellValue(mothBillsEntity.getUid());
	            cell25.setCellType(HSSFCell.CELL_TYPE_STRING);
	        }

	        return 1;

	    }

	    private static String getMonthBillsEntity(BufferedReader bufferedReader,final MothBillsEntity mothBillsEntity) {
	        String metaData = FileReaderUtils.getMetaData(bufferedReader);
	        if(StringUtils.isNotBlank(metaData)){
	            String[] monthBillMetaDatas = metaData.split(",");
	            mothBillsEntity.setCreateUser(monthBillMetaDatas[0]);
	            mothBillsEntity.setCreateTime(monthBillMetaDatas[1]);
	            mothBillsEntity.setUpdateUser(monthBillMetaDatas[2]);
	            mothBillsEntity.setUpdateTime(monthBillMetaDatas[3]);
	            mothBillsEntity.setCheckId(monthBillMetaDatas[4]);
	            mothBillsEntity.setDateTime(monthBillMetaDatas[5]);
	            mothBillsEntity.setMonthBillNo(monthBillMetaDatas[6]);
	            mothBillsEntity.setCurrFee(monthBillMetaDatas[7]);
	            mothBillsEntity.setCurrNeedPayFee(monthBillMetaDatas[8]);
	            mothBillsEntity.setCurrNeedPayInterest(monthBillMetaDatas[9]);
	            mothBillsEntity.setCurrNeedPayCapital(monthBillMetaDatas[10]);
	            mothBillsEntity.setCurrNeedPayTotal(monthBillMetaDatas[11]);
	            mothBillsEntity.setCurrInterest(monthBillMetaDatas[12]);
	            mothBillsEntity.setCurrCapital(monthBillMetaDatas[13]);
	            mothBillsEntity.setCurrYesFee(monthBillMetaDatas[14]);
	            mothBillsEntity.setCurrYesInterest(monthBillMetaDatas[15]);
	            mothBillsEntity.setCurrYesCapital(monthBillMetaDatas[16]);
	            mothBillsEntity.setCurrYesTotal(monthBillMetaDatas[17]);
	            mothBillsEntity.setLastRepayTime(monthBillMetaDatas[18]);
	            mothBillsEntity.setLateDays(monthBillMetaDatas[19]);
	            mothBillsEntity.setOverDueStatus(monthBillMetaDatas[20]);
	            mothBillsEntity.setPayOffStatus(monthBillMetaDatas[21]);
	            mothBillsEntity.setCurrYesStagingAmount(monthBillMetaDatas[22]);
	            mothBillsEntity.setCurrCanStagingAmount(monthBillMetaDatas[23]);
	            mothBillsEntity.setCurrNoStagingAmount(monthBillMetaDatas[24]);
	            mothBillsEntity.setUid(monthBillMetaDatas[25]);
	        }
	  
	        return metaData;
	    }

}
