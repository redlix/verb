package com.red.verb.utils;

import com.red.verb.exception.MyException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * SerializableUtil
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/05/26     red        -
 * </pre>
 *
 * @author redli
 * @version 1.0.0 2019-05-26 10:01
 * @since 1.0.0
 */
@Slf4j
public class SerializableUtil {
	/**
	 * 序列化
	 * @param object
	 * @return byte[]
	 * @author Wang926454
	 * @date 2018/9/4 15:14
	 */
	public static byte[] serializable(Object object) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
			throw new MyException("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
				throw new MyException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
			}
		}
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return java.lang.Object
	 * @author Wang926454
	 * @date 2018/9/4 15:14
	 */
	public static Object unserializable(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			log.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
			throw new MyException("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
		} catch (IOException e) {
			log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
			throw new MyException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bais != null) {
					bais.close();
				}
			} catch (IOException e) {
				log.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
				throw new MyException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
			}
		}
	}
}

