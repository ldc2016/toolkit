package com.vip.own.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池测试入口
 * @author dacheng.liu
 *
 */
public class ThreadPoolLearnTest {
	
	public static void main(String[] args) throws InterruptedException {
		int currActiveThreadCount = 0;
		
		ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtils.initThreadPool();
		
		currActiveThreadCount = threadPoolExecutor.getActiveCount(); // 未添加任务时，线程池中存活线程数
		System.out.println("------------ ThreadPoolLearnTest , currActiveThreadCount -------------->  ：" + currActiveThreadCount);
		
		// 初始化50个工作线程
		for (int i = 0; i < 100; i++) {
			TestWorkThread testTask = new TestWorkThread();
			threadPoolExecutor.submit(testTask);
		}
		
		currActiveThreadCount = threadPoolExecutor.getActiveCount();
		System.out.println("------------ ThreadPoolLearnTest , after add task into queue currActiveThreadCount -------------->  ：" + currActiveThreadCount);
		
		Thread.currentThread().sleep(30*1000);
		
		
		// 修改线程池核心线程数量
		threadPoolExecutor.setCorePoolSize(2);
		currActiveThreadCount = threadPoolExecutor.getActiveCount();
		System.out.println("------------ ThreadPoolLearnTest, after modify corePoolSize currActiveThreadCount -------------->  ：" + currActiveThreadCount);
		
		// 关闭线程池
		threadPoolExecutor.shutdown();
	}

}
