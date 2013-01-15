package core.spider;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Robot {

	private HashMap<String,LinkedList<String>> userAgents=new HashMap<String,LinkedList<String>>();
	
	/**
	 * 解析robot的协议
	 * @param robotContent
	 */
	private void parseRobotContent(String robotContent)
	{
		String []lines=robotContent.split("\n");
		int index=0;
		int lineLength=lines.length;
		String lineContent=null;
		String user_Agent=null;
		LinkedList<String> disallowVisit=null;
		while(index<lineLength)
		{
			lineContent=lines[index];
			if(lineContent.trim().startsWith("User-agent"))
			{
				user_Agent=lineContent.trim().substring("User-agent:".length());
				disallowVisit=new LinkedList<String>();
				lineContent=lines[++index];
				while(lineContent.trim().startsWith("Disallow"))
				{
					disallowVisit.add(lineContent.trim().substring("Disallow:".length()));
					if(index<lineLength-1)
						lineContent=lines[++index];
					else
						break;
				}
				userAgents.put(user_Agent,disallowVisit);
				
			}
			else//注释行
			{
				index++;
			}

		}
	}
	public Robot(String robotContent)
	{
		parseRobotContent(robotContent);
	}
	/**
	 * 该方法用于测试
	 */
	public void testPrint()
	{
		for(Object o:userAgents.entrySet())
		{
			Map.Entry entry=(Map.Entry<String, LinkedList<String>>)o;
			String key=(String)entry.getKey();
			LinkedList<String> value=(LinkedList<String>)entry.getValue();
			System.out.println(key);
			System.out.println(value.toString());
		}
	}
	/**
	 * 判断相应URL在robot协议中是否允许访问
	 * @param url
	 * @return
	 */
	public boolean isAllowToVisited(String url)
	{
		LinkedList<String> disallows=userAgents.get("*");
		if(disallows.get(0).equals(""))
			return true;
		else if(disallows.get(0).trim().equals("/"))
			return false;
		else
		{
			boolean result=true;
			String disallow=null;
			Iterator iter=disallows.iterator();
			while(iter.hasNext())
			{
				disallow=(String)iter.next();
				result=_isAllowToVisited(url,disallow);
				if(result==false)
					break;
			}
			return result;
		}
	}
	private boolean _isAllowToVisited(String url,String disallow)
	{
		int index=url.indexOf('/',7);
		String webSite=url.substring(0,index);
		String disallowPattern=dealDisallowPattern(webSite+disallow);
		Pattern p=Pattern.compile(disallowPattern);
		Matcher m=p.matcher(url);
		if(m.find())
			return false;
		else
			return true;
		
	}
	/**
	 * 处理不允许访问的字符串中的*.
	 * @param disallowPattern
	 * @return
	 */
	private String dealDisallowPattern(String disallowPattern)
	{
		String result=disallowPattern;
		int index=disallowPattern.indexOf("*");
		if(index!=-1)
		{
			String pre=disallowPattern.substring(0, index);
			String last=disallowPattern.substring(index+1);
			result=pre+".*"+last;
		}
		return result;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	   InputStream in=new FileInputStream(new File("G:"+File.separator+"1.txt"));
	   BufferedReader br=new BufferedReader(new InputStreamReader(in,"utf-8"));
	   StringBuffer strBuf=new StringBuffer();
	   String line=null;
	   while((line=br.readLine())!=null)
	   {
		   strBuf.append(line+"\n");
	   }
	   Robot robot=new Robot(strBuf.toString());
	   robot.testPrint();
	}

}
