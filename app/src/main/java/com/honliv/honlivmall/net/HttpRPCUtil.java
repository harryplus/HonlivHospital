package com.honliv.honlivmall.net;

import com.honliv.honlivmall.ConstantValue;

import org.json.JSONObject;

import jsonrpc.JSONRPCClient;
import jsonrpc.JSONRPCException;
import jsonrpc.JSONRPCParams;

public class HttpRPCUtil extends JSONRPCClient {
	JSONRPCClient client;
	public HttpRPCUtil() {
		client = JSONRPCClient.create(ConstantValue.URL,
				JSONRPCParams.Versions.VERSION_2);
		client.setConnectionTimeout(3000);
		client.setSoTimeout(3000);

		/*// 设置wap的代理信息 .现在的client连接是私有的。无法设置
		if (StringUtils.isNotBlank(GloableParams.PROXY_IP)) {
			HttpHost host = new HttpHost(GloableParams.PROXY_IP,
					GloableParams.PROXY_PORT);
			httpClient.getParams()
					.setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		}*/
	}
	@Override
	protected JSONObject doJSONRequest(JSONObject request)
			throws JSONRPCException {
		// TODO Auto-generated method stub
		return null;
	}
}
