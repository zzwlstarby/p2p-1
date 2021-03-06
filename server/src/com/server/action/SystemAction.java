package com.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSONArray;
import com.cslc.dao.account.Account;
import com.cslc.dao.account.AccountDao;
import com.cslc.dao.systemdef.Systemdef;
import com.cslc.dao.systemdef.SystemdefDao;
import com.cslc.service.BizService;
import com.platform.base.BaseAction;
import com.platform.constant.SystemConstant;
import com.platform.util.StringUtil;

@ParentPackage("app")
@Namespace("/")
public class SystemAction extends BaseAction {
	@Resource
	private AccountDao accountDao;

	@Resource
	private SystemdefDao systemdefDao;
	
	@Resource
	private BizService bizService;

	// 获取用户信息
	@Action("170")
	public void getuserinfo() {
		long accountid = Long.parseLong(getParameter("accountid"));

		Map<String, Object> result = new HashMap<String, Object>();

		Account account = accountDao.selectById(accountid);

		if (account.getTradecount() > 0) {
			result.put("isnewer", 2);
		} else {
			result.put("isnewer", 1);
		}

		// 是否设置登录密码
		if (StringUtil.isBlank(account.getLoginpwd())) {
			result.put("issetloginpwd", 2);
		} else {
			result.put("issetloginpwd", 1);
		}

		result.put("accountid", accountid);
		result.put("status", account.getStatus());
		result.put("mobile", account.getMobile());

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;
	}

	// 获取公共信息
	@Action("171")
	public void getpublicinfo() {

		Map<String, Object> result = new HashMap<String, Object>();
		// 资金安全
		Map<String, Object> security = new HashMap<String, Object>();
		Map<String, Object> securitymap = bizService.getSystemjson(Systemdef.SECURITY_BANK);
		security.put("title", securitymap.get("title"));
		security.put("url", securitymap.get("url"));
		result.put("security", security);
		

		// 启动页
		Map<String, Object> startimage = new HashMap<String, Object>();
		Map<String, Object> startimagemap = bizService.getSystemjson(Systemdef.APP_START_IMAGE);
		if (StringUtil.getPlatform(request).equals("android")) {
			startimage.put("appstartimageurl", startimagemap.get("android"));
		} else {
			startimage.put("appstartimageurl", startimagemap.get("ios"));
		}
		result.put("startimage", startimage);

		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;

	}

	// 更多
	@Action("160")
	public void getmore() {

		String accountid = getParameter("accountid");

		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String, Object>> itemlist = new ArrayList<Map<String, Object>>();

		Map<String, Object> invitemap = new HashMap<String, Object>();
		invitemap.put("title", "邀请好友");
		invitemap.put("tip", "送红包啦");
		invitemap.put("url", SystemConstant.WEB_URL+"/invite.html");
		itemlist.add(invitemap);

		Map<String, Object> awardmap = new HashMap<String, Object>();
		awardmap.put("title", "有奖活动");
		awardmap.put("tip", "送红包了！");
		awardmap.put("url", SystemConstant.WEB_URL+"/activity.html");
		itemlist.add(awardmap);

		Map<String, Object> questionmap = new HashMap<String, Object>();
		questionmap.put("title", "常见问题");
		questionmap.put("tip", "怎么体现？");
		questionmap.put("url", SystemConstant.WEB_URL+"/question.html");
		itemlist.add(questionmap);

		Map<String, Object> backmap = new HashMap<String, Object>();
		backmap.put("title", "意见反馈");
		backmap.put("tip", "有回复哦");
		backmap.put("url", SystemConstant.WEB_URL+"/messagelist.html");
		itemlist.add(backmap);

		Map<String, Object> aboutmap = new HashMap<String, Object>();
		aboutmap.put("title", "关于我们");
		aboutmap.put("tip", "400-998-0925");
		itemlist.add(aboutmap);

		Map<String, Object> encouragemap = new HashMap<String, Object>();
		encouragemap.put("title", "鼓励一下");
		itemlist.add(encouragemap);

		Map<String, Object> versionmap = new HashMap<String, Object>();
		versionmap.put("title", "当前版本");
		versionmap.put("ios", JSONArray.parse(bizService.getSystemval(Systemdef.IOS_VERSION)));
		
		versionmap.put("android", JSONArray.parse(bizService.getSystemval(Systemdef.ANDROID_VERSION)));
		itemlist.add(versionmap);

		result.put("items", itemlist);
		
		result.put("returnCode", 0);
		result.put("returnMsg", "success");
		print(result);
		return;
	}
	
	

}
