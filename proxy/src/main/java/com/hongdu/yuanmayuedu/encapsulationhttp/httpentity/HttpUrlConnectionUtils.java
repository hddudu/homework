package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * https://lily-better.iteye.com/blog/2104294 上传文件工具类
 *
 * 原生java发起 post请求工具类
 * 1.第一行是“ -----------------------------7d92221b604bc ”作为分隔符，然后是“ /r/n ” 回车换行符。 这个7d92221b604bc 分隔符浏览器是随机生成的。
 *
 * 2.第二行是Content-Disposition: form-data; name="file2"; filename="D:/huhu.txt";name=对应input的name值，filename对应要上传的文件名（包括路径在内），
 *
 * 3.第三行如果是文件就有Content-Type: text/plain；这里上传的是txt文件所以是text/plain,如果上穿的是jpg图片的话就是image/jpg了，可以自己试试看看。
 *
 * 然后就是回车换行符。
 *
 * 4.在下就是文件或参数的内容或值了。如：hello word。
 *
 * 5.最后一行是-----------------------------7da2e536604c8--,注意最后多了二个--;
 * @author dudu
 * @since 2019-07-03
 *
 */
public class HttpUrlConnectionUtils {
	
	private static Logger logger = org.apache.log4j.Logger.getLogger(HttpUrlConnectionUtils.class);


	private final static String BOUNDARY = UUID.randomUUID().toString()
			.toLowerCase().replaceAll("-", "");// 边界标识
	private final static String PREFIX = "--";// 必须存在
	private final static String LINE_END = "\r\n";

	public static final String MULTIPART_FROM_DATA = "multipart/form-data";
	public static final String CHARSET = "UTF-8";

	public static String sendRequestFile(String requestUrl, Map<String, String> reqText
											, Map<String, File> reqFiles) {

		HttpURLConnection connection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = null;
		try {
			URL url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setReadTimeout(3000);
			connection.setConnectTimeout(1000 * 10);

			connection.setRequestMethod("POST");

			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			connection.setRequestProperty("Charset", CHARSET);
			connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			// 往服务器端写内容 也就是发起http请求需要带的参数
			outputStream = new DataOutputStream(connection.getOutputStream());

			//请求参数部分
			writeParams(reqText, outputStream);
			// 请求上传文件部分
			writeFile(reqFiles, outputStream);

			//请求结束标志
			String endTag = PREFIX + BOUNDARY + PREFIX + LINE_END;
			System.out.println("结束标志： " + endTag);
			outputStream.write(endTag.getBytes());
			outputStream.flush();
			// 读取服务器端返回的内容
			System.out.println("---------------------------------响应体--------------------------------------------------");
			System.out.println("ResponseCode:" + connection.getResponseCode()
					+ ",ResponseMessage:" + connection.getResponseMessage());
			if (connection.getResponseCode() == 200) {
				inputStream = connection.getInputStream();
			} else {
				inputStream = connection.getErrorStream();
			}

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
			stringBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			System.out.println("返回报文:" + stringBuffer.toString());
		} catch (Exception e) {
			logger.error("出现失败！" + e);
		} finally {
			try {
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}

				if (outputStream != null) {
					outputStream.close();
					outputStream = null;
				}

				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}

				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}
			} catch (IOException ex) {
				logger.error(ex.getMessage(), ex);
				try {
					throw new Exception(ex);
				} catch (Exception e) {
					logger.error("error!!!!!!!!!!!!!!");
				}
			}
		}
		return stringBuffer.toString();
	}
	/**
	 * 对post上传的文件进行编码处理并写入数据流中
	 *
	 * @throws IOException
	 *
	 * */
	private static void writeFile(Map<String, File> reqFiles, OutputStream outputStream) {
		InputStream is = null;
		try {
			String msg = "请求上传文件部分:\n";
			if (reqFiles == null || reqFiles.isEmpty()) {
				msg += "空";
			} else {
				StringBuilder requestParams = new StringBuilder();
				Set<Map.Entry<String, File>> set = reqFiles.entrySet();
				Iterator<Map.Entry<String, File>> it = set.iterator();
				while (it.hasNext()) {
					Map.Entry<String, File> entry = it.next();
					requestParams.append(PREFIX).append(BOUNDARY).append(LINE_END);
					requestParams.append("Content-Disposition: form-data; name=\"")
							.append(entry.getKey()).append("\"; filename=\"")
							.append(entry.getValue().getName()).append("\"")
							.append(LINE_END);
					requestParams.append("Content-Type:")
							.append(getContentType(entry.getValue()))
							.append(LINE_END);
					requestParams.append("Content-Transfer-Encoding: 8bit").append(
							LINE_END);
					/**
					 * // 参数头设置完以后需要两个换行，然后才是参数内容
					 */
					requestParams.append(LINE_END);

					outputStream.write(requestParams.toString().getBytes());

					is = new FileInputStream(entry.getValue());

					byte[] buffer = new byte[1024*1024];
					int len;
					while ((len = is.read(buffer)) !=  -1) {
						outputStream.write(buffer, 0, len);
					}
					outputStream.write(LINE_END.getBytes());
					outputStream.flush();

					msg += requestParams.toString();
				}
			}
			System.out.println(msg);
		} catch (Exception e) {
			logger.error("writeParams failed", e);
		}

	}

	/**
	 *
	 * @param file
	 * @return
	 */
	private static String getContentType(File file) {
		String streamContentType = "application/octet-stream";
		String imageContentType;
		ImageInputStream image = null;
		try {
			image = ImageIO.createImageInputStream(file);
			if (image == null) {
				return streamContentType;
			}
			Iterator<ImageReader> it = ImageIO.getImageReaders(image);
			if (it.hasNext()) {
				imageContentType = "image/" + it.next().getFormatName();
				return imageContentType;
			}
		} catch (IOException e) {
			logger.error("method getContentType failed", e);
		} finally {
			try{
				if (image != null) {
					image.close();
				}
			}catch(IOException e){
				logger.error("ImageInputStream close failed", e);;
			}

		}
		return streamContentType;
	}

	/**
	 * 对post参数进行编码处理并写入数据流中
	 * @param reqText
	 * @param outputStream
	 */
	private static void writeParams(Map<String, String> reqText, OutputStream outputStream) {
		try {
			String msg = "请求参数部分:\n";
			if (reqText == null || reqText.isEmpty()) {
				msg += "空";
			} else {
				StringBuilder requestParams = new StringBuilder();
				Set<Map.Entry<String, String>> sets = reqText.entrySet();
				Iterator<Map.Entry<String, String>> iterator = sets.iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> enty = iterator.next();
					requestParams.append(PREFIX).append(BOUNDARY).append(LINE_END);
					requestParams.append("Content-Disposition: form-data; name=\"")
							.append(enty.getKey()).append("\"").append(LINE_END);
					requestParams.append("Content-Type: text/plain; charset=utf-8")
							.append(LINE_END);
					requestParams.append("Content-Transfer-Encoding: 8bit").append(
							LINE_END);
					/**
					 * 参数头设置完以后需要两个换行，然后才是参数内容
					 */
					requestParams.append(LINE_END);
					requestParams.append(enty.getValue());
					requestParams.append(LINE_END);
				}
				System.out.println("请求参数" + requestParams.toString());
				outputStream.write(requestParams.toString().getBytes());
				outputStream.flush();

				msg += requestParams.toString();
				System.out.println(msg);
			}
		} catch (Exception e) {
			logger.error("writeParams failed", e);
		}
	}

	/**
	 * 通过拼接的方式构造请求内容，实现参数传输
	 * @param url 访问的服务器URL
	 * @param paramsMap  普通参数
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> post(String url, JSONObject paramsMap) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		try {
			StringBuilder postParams = new StringBuilder();
			for(Map.Entry entry : paramsMap.entrySet()) {
				if(postParams.length() != 0) {
					postParams.append("&");
				}
				postParams.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"))
							.append("=")
							.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
			}
			logger.info("参数 ："+postParams.toString());
			
			byte[] postDataBytes = postParams.toString().getBytes("UTF-8");
			
			URL uri = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
			//设置时间
			urlConnection.setReadTimeout(5 * 1000);
			urlConnection.setConnectTimeout(2000);
			/**
			 * //允许输出
			 */
			urlConnection.setDoOutput(true);
			/**
			 * //允许输入
			 */
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("connection", "keep-alive");
			urlConnection.setRequestProperty("Charsert", "UTF-8");
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			
			//发起请求
			urlConnection.getOutputStream().write(postDataBytes);
			//读取响应
			Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8")); 
			
			StringBuilder sb = new StringBuilder();
			for (int c; (c = in.read()) >= 0;) {
				sb.append((char)c);
			}
			in.close();
			urlConnection.disconnect();
			
			String responseStr = sb.toString();
			logger.info("HttpUrlConnectionUtils - responseStr <== " + responseStr);
			int statusCode = urlConnection.getResponseCode();
			logger.info("HttpUrlConnectionUtils - statusCode <== " + statusCode);
			if (HttpServletResponse.SC_OK == statusCode) {
		        JSONObject dataJson = (JSONObject) JSONObject.parse(responseStr);
		        map = new HashMap(dataJson);
		    }
		} catch (MalformedURLException e) {
			logger.error("不支持的url格式类型！" + e);
		} catch (IOException e) {
			logger.error("IO错误！");
		}
		
		return map;
	}

	/**
	 * shi用get方式读取http中的数据
	 *  采用行解析方式
	 * @param url
	 * @param jsonObject ： 这个参数没有很大必要： 因为参数都拼接在url后面了
	 * @return
	 */
	public static String doGet(String url, JSONObject jsonObject) {

		try {
			// 创建URL对象
			URL url1 = new URL(url);
			// 打开连接 获取连接对象
			URLConnection urlConnection = url1.openConnection();
			// 从连接对象中获取网络连接中的输入字节流对象
			InputStream inputStream = urlConnection.getInputStream();
			// 将输入字节流包装成输入字符流对象,并进行字符编码
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, CHARSET);
			// 创建一个输入缓冲区对象，将字符流对象传入
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			// 定义一个字符串变量，用来接收输入缓冲区中的每一行字符串数据
			String line;
			// 创建一个可变字符串对象，用来装载缓冲区对象的数据，使用字符串追加的方式，将响应的所有数据都保存在该对象中
			StringBuilder stringBuilder = new StringBuilder();
			// 使用循环逐行读取输入缓冲区的数据，每次循环读入一行字符串数据赋值给line字符串变量，直到读取的行为空时标识内容读取结束循环
			while ((line = bufferedReader.readLine()) !=  null) {
				// 将从输入缓冲区读取到的数据追加到可变字符对象中
				stringBuilder.append(line);
			}
			// 依次关闭打开的输入流
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			// 将可变字符串转换成String对象返回
			return stringBuilder.toString();
		} catch (Exception e) {
			logger.error("出现error!" + e);
		}
		return null;
	}
	/**
	 * 使用POST方法读取HTTP中的数据
	 *
	 * @param urlAddress url地址
	 * @param params     参数
	 * @return 请求的响应数据
	 */
	public static String doPOST(String urlAddress, String params) {
		try {
			// 创建URL对象
			URL url = new URL(urlAddress);
			// 打开连接 获取连接对象
			URLConnection connection = url.openConnection();
			// 设置请求编码
			connection.addRequestProperty("encoding", "UTF-8");
			// 设置允许输入
			connection.setDoInput(true);
			// 设置允许输出
			connection.setDoOutput(true);

			// 从连接对象中获取输出字节流对象
			OutputStream outputStream = connection.getOutputStream();
			// 将输出的字节流对象包装成字符流写出对象
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			// 创建一个输出缓冲区对象,将要输出的字符流写出对象传入
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			// 向输出缓冲区中写入请求参数
			bufferedWriter.write(params);
			// 刷新输出缓冲区
			bufferedWriter.flush();

			// 从连接对象中获取输入字节流对象
			InputStream inputStream = connection.getInputStream();
			// 将输入字节流对象包装成输入字符流对象，并将字符编码为UTF-8格式
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// 创建一个输入缓冲区对象，将要输入的字符流对象传入
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			// 创建一个字符串对象，用来接收每次从输入缓冲区中读入的字符串
			String line;
			// 创建一个可变字符串对象，用来装载缓冲区对象的最终数据，使用字符串追加的方式，将响应的所有数据都保存在该对象中
			StringBuilder stringBuilder = new StringBuilder();
			// 使用循环逐行读取缓冲区的数据，每次循环读入一行字符串数据赋值给line字符串变量，直到读取的行为空时标识内容读取结束循环
			while ((line = bufferedReader.readLine()) != null) {
				// 将缓冲区读取到的数据追加到可变字符对象中
				stringBuilder.append(line);
			}
			// 依次关闭打开的输入流
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			// 依次关闭打开的输出流
			bufferedWriter.close();
			outputStreamWriter.close();
			outputStream.close();
			// 将可变字符串转换成String对象返回
			return stringBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 如果要上次或者下载文件，就需要将字节流包装成文件流。
	 */

	/**
	 * 记忆要点：
	 * 很多初学者对Java的IO流不熟悉，容易对输入输出流搞混了，可使用以下方法记：
	 * 所谓的输入和输出，都是针对我们计算机自身而言的，从外部到计算机就是输入。
	 * 从计算机到外部就是输出。网络资源在计算机外部，要获取某个网页数据到我们计算机显示就要使用输入流读入，如果要提交某个数据到指定网站，就要使用输出流写出。
	 *
	 * 网络通信采用用字节流来传输，不管是发送和接收，Java都提供了将字节流包装成字符流以及其他流的类，使开发人员可以很方便的操作资源。
	 *
	 * 注意事项：
	 * 1.POST请求方式记得要设置URLConnection对象的setDoOutput方法里的值为true，这样我们才可以往输出流中写出我们的参数数据。参数写出完毕后要调用输出缓冲区的flush方法刷新输出缓冲区，将缓冲区的数据输出。
	 * 2.打开的输入输出流要记得关闭。
	 *
	 * 作者：蓝士钦
	 * 链接：https://www.jianshu.com/p/5abaee4c29fd
	 * 来源：简书
	 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
	 */


}
