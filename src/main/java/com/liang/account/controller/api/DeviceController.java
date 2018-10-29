package com.liang.account.controller.api;

import com.liang.account.bo.Device;
import com.liang.account.service.DeviceService;
import liang.mvc.annotation.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeviceController {

  @Autowired
  private DeviceService deviceService;

  @Login
  @ResponseBody
  public Object uploadDeviceInfo(Device device) {
    return deviceService.uploadDeviceInfo(device);
  }

  @Login
  @ResponseBody
  public Object getUserDevice(long userId) {
    return deviceService.getUserDevice(userId);
  }

}
