package core.spider;

import java.util.concurrent.ConcurrentLinkedQueue;

public class DispatcherURL implements Runnable{

	private WebSite website;
	public DispatcherURL(WebSite website)
	{
		this.website=website;
	}
	@Override
	public void run() {
		int depth=1;
		String subUrl=null;
		ConcurrentLinkedQueue<String> urlQueue1=new ConcurrentLinkedQueue<String>();
		ConcurrentLinkedQueue<String> urlQueue2=new ConcurrentLinkedQueue<String>();
		urlQueue1.add(website.getWebSiteURL());
		while(depth<=website.getVisitedDepth())
		{
			while(!urlQueue1.isEmpty())
			{
				subUrl=urlQueue1.poll();
				//启动爬虫
				//存在一个问题队列还没插满，就开始访问了，而且速度很快。
				
			}
			depth++;
			while(depth<=website.getVisitedDepth()&&!urlQueue2.isEmpty())
			{
				subUrl=urlQueue2.poll();
				//启动爬虫
				
			}
			depth++;
		}
		
	}
//	public void dispatcher()
//	{
//		String []urls={"www.hao123.com","www.baidu.com","www.google.com","www.renren.com","www.sohu.com"};
//		
//		URLQueue.addURLS(urls);
//		String url=null;
//		int i=0;
//		while(i<5)
//		{
//			i++;
//			url=URLQueue.getNextURL();
//			Thread t=new Thread(new Spider(url));
//			t.start();
//		}
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
