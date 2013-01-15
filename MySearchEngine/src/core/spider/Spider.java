package core.spider;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Spider implements Runnable {

	private String url;

	public Spider(String url)
	{
		this.url=url;
	}
	@Override
	public void run() {
		
	}
	
	public String getContent(String url)
	{
		String content=null;
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		StringBuffer strBuf = new StringBuffer();
		HttpResponse response=null;
		try {
			response = client.execute(httpGet);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream=entity.getContent();
					writeContentToFile(instream);
					
					instream=entity.getContent();					
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "gb2312"));
					String line = null;
					if (entity.getContentLength() > 0) {
						strBuf = new StringBuffer(
								(int) entity.getContentLength());
						while ((line = reader.readLine()) != null) {
							strBuf.append(line);
						}
						content=strBuf.toString();
					}
				}
				if (entity != null) {
					entity.consumeContent();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;
	}
	private void writeContentToFile(InputStream instream)
	{
		String filename="G:"+File.separator+"s"+File.separator+System.currentTimeMillis()+".txt";
		File f=new File(filename);
		
		try {
			f.createNewFile();
			OutputStream out=new FileOutputStream(f);
			byte []data=new byte[1024];
			int length=-1;
			while((length=instream.read(data))!=-1)
			{
				out.write(data,0,length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				instream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Spider spider=new Spider("http://www.baidu.com");
		spider.getContent("http://www.baidu.com");
		
	}


}
