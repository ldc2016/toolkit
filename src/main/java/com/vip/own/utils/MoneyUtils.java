package com.vip.own.utils;

import java.math.BigDecimal;

public class MoneyUtils {
	public static BigDecimal convertIntToBigDecimal(int money,int scale,int  roundingMode ){
		
		return new BigDecimal(money).divide(com.vip.own.constant.SmsPropertyContants.AMOUNTRATE,scale,roundingMode);
	}
	
	
	public static BigDecimal convertIntToBigDecimal(int money,int scale){
		return convertIntToBigDecimal( money, scale,com.vip.own.constant.SmsPropertyContants.DEFAULT_ROUNDINGMODE);
		
	}
	
	
	public static BigDecimal convertIntToBigDecimal(int money){
		return convertIntToBigDecimal(money,com.vip.own.constant.SmsPropertyContants.DEFAULT_SCALE,com.vip.own.constant.SmsPropertyContants.DEFAULT_ROUNDINGMODE);
		
	}
	
	public static Long convertBigDecimalToLong(BigDecimal money){
		BigDecimal convertMoney = money.multiply(com.vip.own.constant.SmsPropertyContants.AMOUNTRATE).setScale(0);
		return Long.parseLong(convertMoney.toString());
	}
	
}
