package core.spider;

public class Spider implements Runnable {

	private String url;

	public Spider(String url)
	{
		this.url=url;
	}
	@Override
	public void run() {
		//查看网站robot协议，是否允许爬
		if(Robot.isAllowToVisited(url))
		{
			
			System.out.println(url+"...."+System.currentTimeMillis());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}


}
