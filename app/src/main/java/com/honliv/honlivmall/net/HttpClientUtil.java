package com.honliv.honlivmall.net;

import com.alibaba.fastjson.JSONObject;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.util.LogUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HttpClientUtil {
    private HttpClient client;
    private HttpRequest request;
    private HttpPost post;
    private HttpGet get;
    private HttpResponse response;
    private static Header[] headers;

    static {
        headers = new Header[10];// 服务器通过gethead拿。
        headers[0] = new BasicHeader("Appkey", "12343");
        headers[1] = new BasicHeader("Udid", "");// 手机串号
        headers[2] = new BasicHeader("Os", "android");//
        headers[3] = new BasicHeader("Osversion", "");//
        headers[4] = new BasicHeader("Appversion", "");// 2.0
        headers[5] = new BasicHeader("Sourceid", "");//
        headers[6] = new BasicHeader("Ver", "");
        headers[7] = new BasicHeader("Userid", "123");// 要设置这二个东西
        headers[8] = new BasicHeader("Usersession", "122333");
        headers[9] = new BasicHeader("Content-Type", "text/plain; charset=utf-8");

        GloableParams.RequestID = new Random().nextInt(10000);
        //headers[9] = new BasicHeader("X-JSON-RPC", "UserInfo");
    }

    public HttpClientUtil() {
        client = new DefaultHttpClient();
        // 设置wap的代理信息
        if (StringUtils.isNotBlank(GloableParams.PROXY_IP)) {
            HttpHost host = new HttpHost(GloableParams.PROXY_IP,
                    GloableParams.PROXY_PORT);
            client.getParams()
                    .setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
        }
    }

    /**
     * 发送带参数post请求
     *
     * @param url
     * @param params 如果调用无参的方法，请只加一个key为mehtod=方法名
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public String sendPost(String url, Map<String, Object> params) {
        if (params == null) {
            throw new IllegalArgumentException("请设置params的值");//////////
        }
        post = new HttpPost(url);
        // 设置公共头信息
        post.setHeaders(headers);

        String method = (String) params.get("method");
        post.setHeader("X-JSON-RPC", method);

        // 设置参数
        //HashMap<String, Object> map = new HashMap<String, Object>();
        //JSONObject jsParams = new JSONObject(new HashMap<String, Object>());

        params.remove("method");//////////

        JSONObject jsParams = null;
        if (params != null && params.size() > 0) {

            jsParams = new JSONObject(params);
        } else {
            jsParams = new JSONObject(new HashMap<String, Object>());
        }

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("id", GloableParams.RequestID);
        map.put("method", method);
        map.put("params", jsParams);

        JSONObject jsobj = new JSONObject(map);

        try {
            LogUtil.info(GloableParams.RequestID + "=ID-----发送前==" + new String(jsobj.toJSONString().getBytes(), "GBK"));
        } catch (UnsupportedEncodingException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        try {
//		InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream(jsobj.toJSONString().getBytes()),jsobj.toJSONString().length());
            //InputStreamEntity entity = new InputStreamEntity(new ByteArrayInputStream(new String(jsobj.toJSONString().getBytes(),"GBK").getBytes("UTF-8")),jsobj.toJSONString().length());
            StringEntity entity = new StringEntity(jsobj.toJSONString(), ConstantValue.CHARSET);
            post.setEntity(entity);

            HttpResponse response;

            response = client.execute(post);
            LogUtil.info("code===" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                // response.getEntity().getContent()
                // 需要将回复内容（getEntity()）转换成字符串信息

                String source;
                try {
                    InputStream ins = response.getEntity().getContent();
                    source = convertStream(ins);

                    return source;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            LogUtil.info("1111111无网络111111");
            e1.printStackTrace();
        }
        //=====================================================

		/*if (params != null && params.size() > 0) {
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> item : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair(item.getKey(),
						item.getValue());
				parameters.add(pair);

			}
			try {
				UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(
						parameters, ConstantValue.CHARSET);
				post.setEntity(encodedFormEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			response = client.execute(post);
			
			if (response.getStatusLine().getStatusCode() == 200) {
				// response.getEntity().getContent()
				// 需要将回复内容（getEntity()）转换成字符串信息
				return EntityUtils.toString(response.getEntity(),
						ConstantValue.CHARSET);
				// return response.getEntity().getContent();//拿到输入流
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("return null..............");*/
        return null;
    }

    /**
     * 发送带参数的GET提交，获取模拟数据，参数传null即可
     *
     * @param url
     * @param parames 因为是模拟的假数据，所以该参数不需要传递，填null即可
     * @return 返回服务端返回的json数据
     */
    public String sendGet(String url, String parames) {

		/*
		 * 1创建服务器HttpClient client = new DefaultHttpClient(); 2输入地址HttpGet get =
		 * new HttpGet(path+"?"+parames);//在这之前要把参数配置好。 3执行语句HttpResponse
		 * response = client.execute(get); int code =
		 * response.getStatusLine().getStatusCode();先拿第一行，再拿响应码
		 */

        if (parames != null) {
            get = new HttpGet(url + "?" + parames);//正常使用要打开这个
        } else {
            get = new HttpGet(url);// 模拟使用,parames传null进来就好了
        }
        try {
            response = client.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(),
                        ConstantValue.CHARSET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送xml文件到服务器端
     *
     * @param url
     * @param xml
     */
    public InputStream sendXml(String url, String xml) {
        post = new HttpPost(url);

        // 设置连接参数
        // HttpParams httpParams = new BasicHttpParams();//
        // httpParams = new BasicHttpParams();
        // HttpConnectionParams.setConnectionTimeout(httpParams, 8000);
        // HttpConnectionParams.setSoTimeout(httpParams, 8000);
        // post.setParams(httpParams);
        // 设置传输内容
        try {
            StringEntity entity = new StringEntity(xml, ConstantValue.CHARSET);
            post.setEntity(entity);

            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                return response.getEntity().getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertStream(InputStream input) throws Exception {
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        try {
            byte[] buf = new byte[512];
            int c = input.read(buf);
            while (c != -1) {
                bais.write(buf, 0, c);
                c = input.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bais.toByteArray(), "utf-8");
    }
}
