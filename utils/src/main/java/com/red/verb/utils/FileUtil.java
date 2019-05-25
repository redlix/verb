package com.red.verb.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * file 操作工具类
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/05/24     red        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2019-05-24 09:37
 * @since 1.0.0
 */
@Slf4j
public class FileUtil {
	private static final String SINGLE_SLASH = File.separator;
	private static final String DOUBLE_SLASH = File.separator + SINGLE_SLASH;
	private static final String SAVE_PATH = System.getProperty("user.dir");

	public static String[] uploadFile(MultipartFile file){
		log.info("成功获取文件");
		//获取文件名
		String fileName = file.getOriginalFilename();
		String separator = "/";
		String[] suffixName = {".jpg",".png",".mp3"};
		//判断类型
		String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf("."), fileName.length()):"";
		log.info("文件初始名称为：" + fileName + " 类型为：" + type);
		// 2. 使用随机生成的字符串+源图片扩展名组成新的图片名称,防止图片重名
		String newfileName = UUID.randomUUID().toString().replaceAll("-","") + fileName.substring(fileName.lastIndexOf("."));
		//存放磁盘的路径以及判断有没有
		String suffix = "//" + Calendar.getInstance().get(Calendar.YEAR) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1);
		File filePath = suffixName[2].equals(type)? new File(SAVE_PATH + "//data//" +"image" + suffix): new File(SAVE_PATH + "//data//" +"audio" + suffix);
		if (!filePath.exists() && !filePath.isDirectory()) {
			if (separator.equals(File.separator)) {
				log.info("Liunx下创建");
				filePath.setWritable(true, false);
				filePath.mkdirs();
			} else {
				log.info("windows下创建");
				filePath.mkdirs();
			}
		}
		//transferto()方法，是springmvc封装的方法，用于图片上传时，把内存中图片写入磁盘
		log.info("存储地址：" + filePath.getPath());
		try {
			file.transferTo(new File(filePath.getPath()+ "//" + newfileName));
			String[] response= new String[2];
			response[0] = filePath.getPath()+ "//" + newfileName;
			response[1] = type;
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			throw MyExceptionUtil.mxe("上传文件失败！",e);
		}
	}
}
