//package com.hongdu.yuanmayuedu.urlconnection;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.GzipDecompressingEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.druid.util.StringUtils;
//import com.system.core.util.StringUtil;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.util.Map;
//
///**
// * ${DESCRIPTION}
// *
// * @author Ricky Fung
// * @create 2016-06-24 13:11
// */
//public final class HttpUtil {
//
//	private static final Logger logger = LoggerFactory
//			.getLogger(HttpUtil.class);
//
//	public static final int CONNECTION_TIMEOUT = 60 * 1000;
//
//	public static final int SOCKET_TIMEOUT = 60 * 1000;
//
//	public static final String USER_AGENT_HEADER = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36";
//	public static final String ACCEPT_HEADER = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
//	public static final String ACCEPT_LANGUAGE_HEADER = "zh-CN,zh;q=0.8,en;q=0.6";
//
//	private static final CloseableHttpClient httpclient = HttpClients
//			.createDefault();
//
//	public static String post(String url, String data) throws IOException {
//
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.setHeader("User-Agent", USER_AGENT_HEADER);
//		httpPost.setHeader("Accept", ACCEPT_HEADER);
//		httpPost.setHeader("Accept-Language", ACCEPT_LANGUAGE_HEADER);
//
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setConnectTimeout(CONNECTION_TIMEOUT)
//				.setSocketTimeout(SOCKET_TIMEOUT).build();
//
//		httpPost.setConfig(requestConfig);
//
//		CloseableHttpResponse response = null;
//		try {
//
//			StringEntity stringEntity = new StringEntity(data, "UTF-8");
//			stringEntity.setContentType("text/json");
//
//			httpPost.setEntity(stringEntity);
//			httpPost.setHeader("Content-type", "application/json");
//			System.out.println(httpPost);
//			response = httpclient.execute(httpPost);
//
//			int code = response.getStatusLine().getStatusCode();
//			System.out.println("CODE:" + code);
//			if (code == HttpStatus.SC_OK) {
//
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					// Gzip
//					if (entity.getContentEncoding() != null
//							&& "GZIP".equalsIgnoreCase(entity
//									.getContentEncoding().getName())) {
//						entity = new GzipDecompressingEntity(entity);
//					}
//
//					try {
//						return EntityUtils.toString(entity);
//					} finally {
//						EntityUtils.consume(entity);
//					}
//				}
//			} else {
//				logger.error("server:{} response error, statusCode:{}", url,
//						code);
//			}
//
//		} finally {
//			if (response != null) {
//				try {
//					response.close();
//				} catch (IOException e) {
//					logger.error("post error", e);
//				}
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 发送HttpGet请求
//	 *
//	 * @param url
//	 * @return
//	 */
//	public static String get(String url) {
//
//		HttpGet httpget = new HttpGet(url);
//		CloseableHttpResponse response = null;
//		String result = null;
//		try {
//			response = httpclient.execute(httpget);
//
//			/** 请求发送成功，并得到响应 **/
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				/** 读取服务器返回过来的json字符串数据 **/
//				result = EntityUtils.toString(response.getEntity());
//			} else {
//				logger.error("get请求提交失败:" + url);
//			}
//		} catch (IOException e) {
//			logger.error("get请求提交失败:" + url, e);
//		} finally {
//			try {
//				response.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public static String get(String url, String token) {
//
//		HttpGet httpget = new HttpGet(url);
//		httpget.addHeader("api-token", token);
//		CloseableHttpResponse response = null;
//		String result = null;
//		try {
//			response = httpclient.execute(httpget);
//
//			/** 请求发送成功，并得到响应 **/
//			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//				/** 读取服务器返回过来的json字符串数据 **/
//				result = EntityUtils.toString(response.getEntity());
//			} else {
//				logger.error("get请求提交失败:" + url);
//			}
//		} catch (IOException e) {
//			logger.error("get请求提交失败:" + url, e);
//		} finally {
//			try {
//				response.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public static String doPost(String url, String token, String data)
//			throws IOException {
//
//		HttpPost httpPost = new HttpPost(url);
//		httpPost.addHeader("api-token", token);
//
//		RequestConfig requestConfig = RequestConfig.custom()
//				.setConnectTimeout(CONNECTION_TIMEOUT)
//				.setSocketTimeout(SOCKET_TIMEOUT).build();
//
//		httpPost.setConfig(requestConfig);
//
//		CloseableHttpResponse response = null;
//		try {
//
//			StringEntity stringEntity = new StringEntity(data, "UTF-8");
//			stringEntity.setContentType("text/json");
//
//			httpPost.setEntity(stringEntity);
//			httpPost.setHeader("Content-type", "application/json");
//			System.out.println(httpPost);
//			response = httpclient.execute(httpPost);
//
//			int code = response.getStatusLine().getStatusCode();
//			System.out.println("CODE:" + code);
//			if (code == HttpStatus.SC_OK) {
//
//				HttpEntity entity = response.getEntity();
//				if (entity != null) {
//					// Gzip
//					if (entity.getContentEncoding() != null
//							&& "GZIP".equalsIgnoreCase(entity
//									.getContentEncoding().getName())) {
//						entity = new GzipDecompressingEntity(entity);
//					}
//
//					try {
//						return EntityUtils.toString(entity);
//					} finally {
//						EntityUtils.consume(entity);
//					}
//				}
//			} else {
//				logger.error("server:{} response error, statusCode:{}", url,
//						code);
//			}
//
//		} finally {
//			if (response != null) {
//				try {
//					response.close();
//				} catch (IOException e) {
//					logger.error("post error", e);
//				}
//			}
//		}
//		return null;
//	}
//
//	public static String postJsonAndHeader(String url, String jsonStr,Map<String,String> headers,
//			boolean noNeedResponse) {
//
//		DefaultHttpClient httpClient = new DefaultHttpClient();
//		HttpPost method = new HttpPost(url);
//		String str = null;
//
//		for (Map.Entry<String, String> e : headers.entrySet()) {
//			method.addHeader(e.getKey(), e.getValue());
//		}
//
//		try {
//
//			if (null != jsonStr) {
//				// 解决中文乱码问题
//				StringEntity entity = new StringEntity(jsonStr, "utf-8");
//				entity.setContentEncoding("UTF-8");
//				entity.setContentType("application/json");
//				method.setEntity(entity);
//			}
//
//			HttpResponse result = httpClient.execute(method);
//			url = URLDecoder.decode(url, "UTF-8");
//
//			if (result.getStatusLine().getStatusCode() == 200) {
//
//				if (noNeedResponse) {
//					return null;
//				}
//
//				str = EntityUtils.toString(result.getEntity());
//
//			}
//
//		} catch (IOException e) {
//			logger.error("post请求提交失败:" + url, e);
//			e.printStackTrace();
//		}
//		return str;
//
//	}
//}