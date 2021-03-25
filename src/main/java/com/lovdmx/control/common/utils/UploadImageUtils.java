package com.lovdmx.control.common.utils;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadImageUtils {

	/**
	 * 上传多张图片
	 * 
	 * @param imgs     多张图片
	 * @param path     路径
	 * @param basePath 项目路径
	 * @return String
	 */
	public static String uploadImages(CommonsMultipartFile imgs[], String path, String basePath) {
		String val = "";
		if (imgs != null) {
			try {
				for (int i = 0; i < imgs.length; i++) {
					String imgName = imgs[i].getOriginalFilename();
					if (imgName != null && !imgName.equals("")) {
						String name = ImageUtils.changeFileName(imgName);
						if (imgs.length - 1 > i) {
							val += path + File.separator + name + ":";
						} else {
							val += path + File.separator + name;
						}
						if (!imgs[i].isEmpty()) {
							File file = new File(basePath + File.separator + path + File.separator + name);
							imgs[i].transferTo(file);
						}
					} else {
						val += ":";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	/**
	 * 上传一张图片
	 * 
	 * @param img      图片
	 * @param path     路径
	 * @param basePath 项目路径
	 * @return String
	 */
	public static String uploadImage(MultipartFile img, String path, String basePath, String name) {
		if (img != null && img.getSize() > 0) {
			try {
				String ImgName = img.getOriginalFilename();
				if (name == null || name == "") {
					name = ImageUtils.changeFileName(ImgName);
				}
				File file = new File(basePath + File.separator + path + File.separator + name);
				img.transferTo(file);
				name = path + File.separator + name;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	/**
	 * 删除图片
	 * 
	 * @param basePath 项目路径
	 * @param url      路径
	 * @return boolean
	 */
	public static boolean deteleImage(String basePath, String url) {
		boolean bool = false;
		if (url != null && !url.equals("")) {

			File file = new File(basePath + File.separator + url);

			// 判断文件是否存在
			boolean flag = false;
			flag = file.exists();

			if (flag) {
				// 删除图片
				file.delete();
				bool = true;
			}
		}

		return bool;
	}

	/**
	 * 删除多张图片(冒号分割)
	 * 
	 * @param basePath 项目路径
	 * @param urls     路径（多个）
	 * @return boolean
	 */
	public static boolean deteleImages(String basePath, String urls) {
		boolean bool = true;
		if (urls != null && !urls.equals("") && !urls.equals(":")) {

			String[] split = urls.split(":");
			for (String string : split) {
				File file = new File(basePath + File.separator + string);
				// 判断文件是否存在
				boolean flag = false;
				flag = file.exists();

				if (flag) {
					// 删除图片
					file.delete();
				} else {
					bool = false;
				}
			}

		}
		return bool;
	}

	
}
