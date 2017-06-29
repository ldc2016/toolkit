package com.vip.own.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

	private static final int init_core_pool_size = 8;  // 初始核心线程数
	private static final int init_max_pool_size = 40;   // 初始最大线程数
	private static final int init_keep_alive_time = 30; // 初始线程存活时间
	
	private static  ThreadPoolExecutor threadPoolExecutor = null;     
	/**
	 * 初始化线程池
	 */
	public static ThreadPoolExecutor initThreadPool(){
		// 任务队列:使用无界队列
		LinkedBlockingQueue taskQueue = new LinkedBlockingQueue<Runnable>();
				
		// 初始化线程池
		threadPoolExecutor = new ThreadPoolExecutor(init_core_pool_size, init_max_pool_size,
						                            init_keep_alive_time, TimeUnit.SECONDS, taskQueue);
		
		return threadPoolExecutor;
	}
	
	/**
	 * 修改线程池的corePoolSize
	 */
	public static void setCorePoolSize(int corePoolSize){
		threadPoolExecutor.setCorePoolSize(corePoolSize);
	}
	
}
