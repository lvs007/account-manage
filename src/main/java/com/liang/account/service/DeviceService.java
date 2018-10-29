package com.liang.account.service;

import com.liang.account.bo.Device;
import com.liang.account.common.ResponseDataBuilder;
import com.liang.account.dao.DeviceDao;
import java.util.List;
import javax.transaction.Transactional;
import liang.common.util.LockUtils;
import liang.dao.jdbc.common.SearchFilter.Operator;
import liang.dao.jdbc.common.SqlPath;
import liang.mvc.commons.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

  @Autowired
  private DeviceDao deviceDao;

  @Transactional
  public ResponseData uploadDeviceInfo(Device device) {
    synchronized (LockUtils.get(device.getUuid())) {
      Device oldDevice = deviceDao.findOne(SqlPath.where("uuid", Operator.EQ, device.getUuid()));
      if (oldDevice == null) {
        oldDevice = device;
        oldDevice.setInUse(true);
        oldDevice.setCreateTime((int) (System.currentTimeMillis() / 1000));
        oldDevice.setUpdateTime(oldDevice.getCreateTime());
        deviceDao.insert(device);
      } else {
        List<Device> deviceList = deviceDao.findAll(SqlPath.where("userId", Operator.EQ,
            device.getUserId()).and(SqlPath.where("inUse", Operator.EQ, true)));
        for (Device d : deviceList) {
          d.setInUse(false);
          d.setUpdateTime((int) (System.currentTimeMillis() / 1000));
          deviceDao.update(d);
        }
        device.setId(oldDevice.getId());
        device.setInUse(true);
        device.setCreateTime(oldDevice.getCreateTime());
        device.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        deviceDao.update(device);
      }
    }
    return ResponseDataBuilder.buildSuccessResponseData(null);
  }

  public ResponseData getUserDevice(long userId) {
    Device device = deviceDao.findOne(SqlPath.where("userId", Operator.EQ, userId)
        .and(SqlPath.where("inUse", Operator.EQ, true)));
    return ResponseDataBuilder.buildSuccessResponseData(device);
  }

}
