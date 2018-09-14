package com.liang.account.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import liang.dao.jdbc.common.CrudBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * sql自动生成
 *
 * @author
 * @date 2018年4月11日
 */
public class SqlGenerator {

  private static final Logger logger = LoggerFactory.getLogger(SqlGenerator.class);

  /**
   * 根据实体类生成建表语句
   *
   * @param className 全类名
   * @author
   * @date 2018年4月11日
   */
  public static String generateSql(String className) {
    try {
      Class<?> clz = Class.forName(className);
      className = CrudBuilder.convertToTableName(clz.getSimpleName());
      Field[] fields = clz.getDeclaredFields();
      StringBuffer column = new StringBuffer();

      String strchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
      String longStr = " BIGINT(20) NOT NULL,";
      String intStr = " INT(11) NOT NULL,";
      String dateStr = " DATETIME NOT NULL,";
      String varchar = strchar;
      for (Field f : fields) {
        if (f.getType() == long.class || f.getType() == Long.class) {
          varchar = longStr;
        } else if (f.getType() == Integer.class || f.getType() == int.class) {
          varchar = intStr;
        } else if (f.getType() == String.class) {
          varchar = strchar;
        } else if (f.getType() == Date.class || f.getType() == java.sql.Date.class) {
          varchar = dateStr;
        }
        column.append(" \n `" + CrudBuilder.convertToColumnName(f.getName()) + "`").append(varchar);
      }
      StringBuffer sql = new StringBuffer();
      sql.append("\n DROP TABLE IF EXISTS `" + className + "`; ")
          .append(" \n CREATE TABLE `" + className + "`  (")
          .append(" \n `id` BIGINT(20) NOT NULL AUTO_INCREMENT,")
          .append(" \n " + column)
          .append(" \n PRIMARY KEY (`id`)")
          .append(
              " \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
      return sql.toString();
    } catch (ClassNotFoundException e) {
      logger.debug("该类未找到！");
      return null;
    }

  }

  /**
   * 获取包下的所有类名称,获取的结果类似于 XXX.java
   *
   * @author
   * @date 2018年4月11日
   */
  public static List<String> getAllClasses(String packageName) {
    List<String> classList = new ArrayList<String>();
    String className = "";
    File f = new File(packageName);
    if (f.exists() && f.isDirectory()) {
      File[] files = f.listFiles();
      for (File file : files) {
        className = file.getName();
        classList.add(className);
      }
      return classList;
    } else {
      logger.debug("包路径未找到！");
      return null;
    }
  }

  /**
   * 将string 写入sql文件
   *
   * @author
   * @date 2018年4月11日
   */
  public static void StringToSql(String str, String path) {
    byte[] sourceByte = str.getBytes();
    if (null != sourceByte) {
      try {
        File file = new File(path);     //文件路径（路径+文件名）
        if (!file.exists()) {   //文件不存在则创建文件，先创建目录
          File dir = new File(file.getParent());
          dir.mkdirs();
          file.createNewFile();
        }
        FileOutputStream outStream = new FileOutputStream(file);    //文件输出流用于将数据写入文件
        outStream.write(sourceByte);
        outStream.flush();
        outStream.close();  //关闭文件输出流
        System.out.println("生成成功");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
//    System.out.println(SqlGenerator.generateSql("com.liang.account.bo.Account"));
    System.out.println(SqlGenerator.generateSql("com.liang.account.bo.UserToken"));
  }
}
