package com.sportslightadmin.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	// 파일을 여기서 받아준다.. 무슨 파일? 글쓴 파일인가?
	public static UUID saveCopyFile(MultipartFile file, String filePath) {
		// 첨부파일이 있으면 중복방지를 위해 난수값을 발생시켜 파일 이름 앞에 붙여서 저장하기.
		String fileName = file.getOriginalFilename();

		// 중복파일명을 처리하기 위한 난수 발생
		UUID random = UUID.randomUUID();

		// urlPath를 호출하면서 request 정보를 넘겨준다.
		// 앞의 위치에 첨부파일을 저자해라.
//		File ff = new File(urlPath(filePath), random + "_" + fileName);
		File ff = new File(filePath, random + "_" + fileName);

		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return random; // 첨부파일에 대한 정보를 내보내줌.
	} // end saveCopyFile()

	public static String urlPath(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.print("root" + root);

		// root 위치에 temp라는 폴더
		String saveDirectory = root + "temp" + File.separator; // 구분자로 넣어도 됨 "temp" + "/"
		
		// root 위치에 temp라는 폴더가 없어서 temp라는 폴더를 생성해야한다.
		File fe = new File(saveDirectory);
		if (!fe.exists()) // 폴더가 존재하지 않으면
			fe.mkdir(); // 폴더를 생성해줘라

		return saveDirectory; // 클라이언트가 첨부파일을 넣어서 넘기면 첨부파일이 저장된 위치를 넘겨준다.
	}

} // end class
