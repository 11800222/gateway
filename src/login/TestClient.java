package login;

import java.net.URISyntaxException;
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
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestClient {
	public static CloseableHttpClient httpclient;
	public static String uid;
	public static String sign;
	public static String time;

	public static String newAndSetHttpClient(String cookie) throws Exception {
		Header header1 = new BasicHeader(
				HttpHeaders.USER_AGENT,
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2663.0 Safari/537.36");
		Header cookieheader = new BasicHeader("cookie", cookie);
		List<Header> headers = new ArrayList<Header>();

		headers.add(header1);
		headers.add(cookieheader);
		if (true) { // if dubug in Fiddler
			HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(
					proxy);
			httpclient = HttpClients.custom().setDefaultHeaders(headers)
					.setRoutePlanner(routePlanner).build();
		} else {
			httpclient = HttpClients.custom().setDefaultHeaders(headers)
					.build();
		}

		URIBuilder url1 = new URIBuilder("http://127.0.0.1:8080/wangpan/blank.html"); 
		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		String content = EntityUtils.toString(r1.getEntity(), "UTF-8");
		return content;
	}
 
	public static String SetPostBody(String uuid, String ssign, String ttime)
			throws Exception {
		uid = uuid;
		sign = ssign;
		time = ttime;
		return GetResponse.lixian();
	}

	public String addTaskLink(String link) throws Exception {// 閿熸枻鎷锋嫢閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷风槩顑掗敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�

		URIBuilder url1 = new URIBuilder(
				"http://115.com/web/lixian/?ct=lixian&ac=add_task_url");
		// 閿熸枻鎷烽敓鏂ゆ嫹post閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟�
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("url", link));

		params.add(new BasicNameValuePair("uid", NewLogin.uid));
		params.add(new BasicNameValuePair("sign",NewLogin.sign));
		params.add(new BasicNameValuePair("time",NewLogin.time));

		// 閿熸枻鎷风‖閿熸枻鎷烽敓锟�
		HttpPost post = new HttpPost(url1.build());
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
		post.setEntity(entity);

		HttpResponse r1 = httpclient.execute(post);

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		String filename = json1.getString("name");

		if (r1.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new Exception("UncleDrew:" + "suss");

		return filename;
	}

	public String getDownloadLink(String pickcode) throws Exception {// 閿熸枻鎷烽敓绲ckcode閿熺煫纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
		String DownloadLink = "";
		URIBuilder url1 = new URIBuilder("http://webapi.115.com/files/download");
		url1.setParameter("pickcode", pickcode);

		HttpResponse r1 = httpclient.execute(new HttpGet(url1.build()));

		String content1 = EntityUtils.toString(r1.getEntity(), "UTF-8");
		JSONObject json1 = JSONObject.fromObject(content1);

		DownloadLink = json1.getString("file_url");
		return DownloadLink;
	}

}
