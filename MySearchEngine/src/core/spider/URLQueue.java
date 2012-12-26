package core.spider;

import java.util.*;

public class URLQueue {

	private static Queue<String> unvisitedURLS=new LinkedList<String>();
	private static Queue<String> visitedURLS=new LinkedList<String>();
	private static Set<String> urls=new HashSet<String>();
	
	/**
	 * 插入一个URL
	 * @param url 
	 */
	public static void addURL(String url)
	{
		if(!urls.contains(url))
		{
			urls.add(url);
			unvisitedURLS.add(url);
		}
	}
	/**
	 *插入一组URL
	 * @param urls
	 */
	public static void addURLS(ArrayList<String> urls)
	{
		for(String url:urls)
		{
			addURL(url);
		}
	}
	/**
	 *插入一组URL
	 * @param urls
	 */
	public static void addURLS(String[] urls)
	{
		for(String url:urls)
		{
			addURL(url);
		}
	}
	/**
	 * @return 返回一个需要爬的url
	 */
	public static String getNextURL()
	{
		String url=unvisitedURLS.remove();
		visitedURLS.add(url);
		return url;
	}
	/**
	 * 当没有访问的url个数大于等于numOfURLs时，返回numOfURLs个URL，否则返回所有剩下的URL
	 * @param numOfURLs 指定要返回的URL的个数
	 * @return 返回一批URL
	 */
	public static String[] getURLS(int numOfURLs)
	{
		String []urls=null;
		if(numOfURLs<unvisitedURLS.size())
		{
			urls=new String[numOfURLs];
			for(int i=0;i<numOfURLs;i++)
			{
				urls[i]=unvisitedURLS.remove();
				visitedURLS.add(urls[i]);
			}
		}
		else
		{
			int num=unvisitedURLS.size();
			urls=new String[num];
			for(int i=0;i<num;i++)
			{
				urls[i]=unvisitedURLS.remove();
				visitedURLS.add(urls[i]);
			}
		}
		return urls;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
