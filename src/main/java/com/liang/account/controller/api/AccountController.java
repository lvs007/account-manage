package com.liang.account.controller.api;

import com.liang.account.service.AccountService;
import com.liang.common.exception.ParameterException;
import com.liang.mvc.annotation.Login;
import com.liang.mvc.commons.ResponseUtils;
import com.liang.mvc.commons.SpringContextHolder;
import com.liang.mvc.filter.LoginUtils;
import com.liang.mvc.filter.UserInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liangzhiyan on 2017/3/17.
 */
@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Login
  @ResponseBody
  public Object getAccount(@RequestParam long id) {
    HttpServletRequest request = SpringContextHolder.getRequest();
    UserInfo userInfo = LoginUtils.getCurrentUser(request);
    if (userInfo.getId() != id) {
      throw ParameterException.throwException("不能获取别人的信息");
    }
    return accountService.getAccount(id);
  }

  @PostMapping
  @ResponseBody
  public Object register(@RequestParam String userName,
      @RequestParam String password1,
      @RequestParam String password2,
      @RequestParam String nickName) {
    if (!StringUtils.equals(password1, password2)) {
      throw ParameterException.throwException("密码不一致");
    }
    return accountService.register(userName, password1, nickName);
  }

  @ResponseBody
  public Object listData() {
    Map<String, Object> map = new HashMap<>();
    map.put("setup", "nihao");
    map.put("punchline", "liang");
    return map;
  }

  List<Map<String, Object>> mapList = new ArrayList<>();

  @ResponseBody
  public Object listTableData() {
    for (int i = 0; i < 5; i++) {
      Map<String, Object> map = new HashMap<>();
      map.put("id", i);
      map.put("name", "liang" + i);
      map.put("desc", "zhi" + i);
      map.put("url", "www.baidu.com");
      mapList.add(map);
    }
    return mapList;
  }

  @PostMapping
  @ResponseBody
  public Object addData(@RequestParam(required = false,name = "name") String name,
      @RequestParam(required = false,name = "desc") String desc,
      @RequestParam(required = false,name = "url") String url,
      @RequestBody(required = false) Map<String, Object> params) {
    System.out.println("params: "+params);
    System.out.println(SpringContextHolder.getRequest().getParameterMap());
    System.out.println("name:" + name + ",desc:" + desc + ",url:" + url);
    mapList.add(params);
    return ResponseUtils.SuccessResponse();
  }
}
