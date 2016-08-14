package com.dw.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;

public class SmartUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SmartUploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=base64");//更改响应字符流使用的编码，还能告知浏览器用什么编码进行显示
		String result = "上传成功！";
		SmartUpload su = new SmartUpload();
		//初始化对象
		JspFactory fac=JspFactory.getDefaultFactory();  
		PageContext pageContext=fac.getPageContext(this, request,response, null, false, JspWriter.DEFAULT_BUFFER, true);  
		su.initialize(pageContext); 
		//设置上传文件大小
		su.setMaxFileSize(1024*1024*10);
		//设置所有文件的大小
		su.setTotalMaxFileSize(1024*1024*40);
		//设置允许上传文件类型
		su.setAllowedFilesList("bng,jpg,gif");
		//设置不允许上传的文件类型
		try {
			su.setDeniedFilesList("html,jsp,js,htm,css");
			su.getRequest().getParameterValues(getServletName());
			//开始上传文件
			su.upload();
			//设置上传文件保存路径
			String filePath ="F:/file";
			File file = new File(filePath);
			if(!file.exists()){
				file.mkdir();
			}
			su.save(filePath);
		} catch (Exception e) {
			result="上传文件失败！";
			e.printStackTrace();
		}
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}

