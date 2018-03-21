package login;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class GetResponse {

	public static String openfile(String cid,String type) throws Exception {

		Header header1 = new BasicHeader(HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2663.0 Safari/537.36");
		Header cookieheader = new BasicHeader("cookie",NewLogin.cookie);
		List<Header> headers = new ArrayList<Header>();

		headers.add(header1);
		headers.add(cookieheader);

		HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

		CloseableHttpClient httpclient = HttpClients.custom().setDefaultHeaders(headers).setRoutePlanner(routePlanner)
				.setConnectionManager(cm).build();

		 
		URIBuilder url1 = new URIBuilder("http://web.api.115.com/files");

		url1.setParameter("aid", "1").setParameter("show_dir", "1").setParameter("cid", cid).setParameter("offset", "0").setParameter("type",type );// 99涓烘枃浠讹紝0涓哄叏閮�

		HttpGet get = new HttpGet(url1.build());

		HttpResponse r1 = httpclient.execute(get);

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "锟斤拷锟侥硷拷失锟斤拷");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
	 
		get.releaseConnection();
		httpclient.close();
		
		return content1;
	}

	// 鏆傛椂鐢ㄤ笉鍒�
	public static String order(String cid) throws Exception {

		URIBuilder url1 = new URIBuilder("http://web.api.115.com/files/order");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_order", "user_ptime"));
		params.add(new BasicNameValuePair("file_id", cid));
		params.add(new BasicNameValuePair("user_asc", "0"));

		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = NewLogin.httpclient.execute(post);
		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷失锟斤拷");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		return content1;
	}

	public static String lixian() throws Exception {
		URIBuilder url1 = new URIBuilder("http://115.com/web/lixian/?ct=lixian&ac=task_lists");
		// 锟斤拷锟斤拷post锟斤拷锟斤拷锟斤拷锟�
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("page", "1"));// 默锟斤拷一页
		params.add(new BasicNameValuePair("uid", NewLogin.uid));
		params.add(new BasicNameValuePair("sign", NewLogin.sign));
		params.add(new BasicNameValuePair("time", NewLogin.time));
		// 锟斤拷锟斤拷锟斤拷锟斤拷拥锟絧ost锟斤拷
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = NewLogin.httpclient.execute(post);

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "锟斤拷询锟斤拷锟斤拷锟斤拷锟斤拷失锟斤拷");

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		return content1;
	}

	public static String addTaskLink(String link) throws Exception {// 锟斤拷拥锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷瘢锟斤拷锟斤拷锟斤拷锟斤拷锟�

		URIBuilder url1 = new URIBuilder("http://115.com/web/lixian/?ct=lixian&ac=add_task_url");
		// 锟斤拷锟斤拷post锟斤拷锟斤拷锟斤拷锟�
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("url", link));
		params.add(new BasicNameValuePair("uid", NewLogin.uid));
		params.add(new BasicNameValuePair("sign", NewLogin.sign));
		params.add(new BasicNameValuePair("time", NewLogin.time));
		// 锟斤拷硬锟斤拷锟�
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = NewLogin.httpclient.execute(post);

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "wqww");

		return content1;
	}

	public static String getDownloadLink(String pickcode) throws Exception {// 锟斤拷锟絧ickcode锟矫碉拷锟斤拷锟斤拷锟斤拷锟斤拷

		String DownloadLink = "";
		URIBuilder url1 = new URIBuilder("http://webapi.115.com/files/download");
		url1.setParameter("pickcode", pickcode);

		HttpResponse r1 = NewLogin.httpclient.execute(new HttpGet(url1.build()));

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		DownloadLink = json1.getString("file_url");

		return DownloadLink;
	}

}
