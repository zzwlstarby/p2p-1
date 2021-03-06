package com.platform.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;
import com.platform.constant.SystemConstant;
import com.platform.util.JSONUtil;
import com.platform.util.SmbUtil;
import com.platform.util.StringUtil;
import com.platform.util.UUIDUtil;

@Service("baseAction")
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	protected static Logger log = Logger.getLogger(BaseAction.class);

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	/**
	 * 设置页面信息
	 * 
	 * @param pageTitle
	 */
	protected String layout(String pageTitle, String pageUrl, String layout) {
		return layout(null, null, pageTitle, pageUrl, layout);
	}
	
	/**
	 * 设置页面信息
	 * 
	 * @param pageBar
	 * @param pageMenu
	 * @param pageTitle
	 */
	protected String layout(String pageBar, String pageMenu, String pageTitle, String pageUrl, String layout) {
		// 页面信息
		request.setAttribute("PageBar", pageBar);
		request.setAttribute("PageMenu", pageMenu);
		request.setAttribute("PageTitle", pageTitle);
		request.setAttribute("PageUrl", pageUrl);
		
		request.setAttribute("StringUtil", new StringUtil());
		request.setAttribute("ImageServer", SystemConstant.IMAGE_URL);
		request.setAttribute("SiteServer", SystemConstant.SITE_URL);
		request.setAttribute("WebServer", SystemConstant.WEB_URL);
		request.setAttribute("PublishServer", SystemConstant.PUBLISH_URL);
		
		return layout;
	}

	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 * @return
	 */
	protected String getParameter(String parameter) {
		String[] vs = getParameters(parameter);
		if (vs != null) {
			return vs[0];
		}
		return null;
	}

	/**
	 * 获取多个值
	 * 
	 * @param parameter
	 * @return
	 */
	protected String[] getParameters(String parameter) {
		return ((Map<String, String[]>) request.getAttribute("paramsMap")).get(parameter);
	}
	
	// 获取UA
	protected Map<String, String> getUserAgentMap() {
		Map<String, String> ua = new HashMap<String, String>();
		ua.put("terminalid", getParameter("ua_terminalid"));
		ua.put("phone", getParameter("ua_phone"));
		ua.put("version", getParameter("ua_version"));
		ua.put("rom", getParameter("ua_rom"));
		ua.put("platform", getParameter("ua_platform"));
		return ua;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String redirect(String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public void print(Map<String, Object> map) {
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(JSONUtil.getJSON(map));
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	public void ajax(String content) {
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			log.error(e);
		}
	}

	public String uploadLocal(String folderName, File file, String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		String filename = UUIDUtil.get() + suffix;
		String filepath = folderName + "/" + filename;

		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			File folder = new File(SystemConstant.IMAGE_LOCATION + folderName);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			fos = new FileOutputStream(SystemConstant.IMAGE_LOCATION + filepath);
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (Exception e) {
			return null;
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(fis);
		}
		return filepath;
	}

	public String uploadSamba(String folderName, File file, String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		String filename = UUIDUtil.get() + suffix;
		String filepath = folderName + "/" + filename;
		SmbUtil.copyLocal2Server(file, filepath);
		return filepath;
	}

	public void deleteFile(String url, String[] rules) {
		SmbUtil.deleteFile(url);
		if (StringUtil.isNotBlank(url)) {
			if(rules != null){
				for (String suffix : rules) {
					SmbUtil.deleteFile(url + "_" + suffix + ".jpg");
				}
			}
		}
	}

}
