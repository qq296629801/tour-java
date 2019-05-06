package cn.ymsys.api.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.ymsys.enums.ExceptionEnum;
import cn.ymsys.util.Const;
import cn.ymsys.util.OwnException;

@Service
public class FileService {

	public String save(MultipartFile file) {
		if (file.isEmpty()) {
			throw new OwnException(ExceptionEnum.E_MODEL_DEFAULT_ERROR, "null exception");
		}
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(Const.PATH + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			new OwnException(ExceptionEnum.A_SYSTEM_DEFAULT_ERROR, e.getMessage());
		}
		return fileName;
	}
}
