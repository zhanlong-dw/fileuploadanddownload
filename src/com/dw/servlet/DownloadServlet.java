package com.dw.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获取文件下载路径
		String path = "F:/file/";
		String filename = request.getParameter("filename");
		File file = new File(path + filename);
		if(file.exists()){
			//设置相应类型让浏览器知道用什么打开  用application/octet-stream也可以，看是什么浏览器
			response.setContentType("application/x-msdownload");
			//设置头信息
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream ouputStream = response.getOutputStream();
			byte b[] = new byte[1024];
			int n ;
			while((n = inputStream.read(b)) != -1){
				ouputStream.write(b,0,n);
			}
			//关闭流
			ouputStream.close();
			inputStream.close();
		}else{
			request.setAttribute("errorResult", "文件不存在,下载失败!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
