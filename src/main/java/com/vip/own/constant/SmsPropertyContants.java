package com.vip.own.constant;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

public class SmsPropertyContants {
	public final static BigDecimal AMOUNTRATE = new BigDecimal(100);
	
	public final static int DEFAULT_SCALE=2;
	
	public final static int DEFAULT_ROUNDINGMODE =BigDecimal.ROUND_HALF_DOWN;
	
	public static final Format CURRENCY_FORMAT = new DecimalFormat("#,##0.00");
}
