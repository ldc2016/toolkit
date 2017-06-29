package com.vip.own.threadpool;

/**
 * 工作线程类
 * @author dacheng.liu
 *
 */
public class TestWorkThread implements Runnable {

	/**
	 * 线程执行任务逻辑
	 */
	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(5*1000); // 休眠5s
		} catch (InterruptedException e) {
			System.out.println("Exception info : " + e.getMessage());
		}
		System.out.println("**** currThreadId **** " + Thread.currentThread());
		
		System.out.println("---------------------");
	}

}
