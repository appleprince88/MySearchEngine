package core.spider;

public class DispatcherURL {

	
	public void dispatcher()
	{
		String []urls={"www.hao123.com","www.baidu.com","www.google.com","www.renren.com","www.sohu.com"};
		
		URLQueue.addURLS(urls);
		String url=null;
		int i=0;
		while(i<5)
		{
			i++;
			url=URLQueue.getNextURL();
			Thread t=new Thread(new Spider(url));
			t.start();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("dfdsf");
		DispatcherURL du=new DispatcherURL();
		du.dispatcher();
	}

}
