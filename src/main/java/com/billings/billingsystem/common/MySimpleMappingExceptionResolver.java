package com.billings.billingsystem.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
/** .
 * 上传文件超过配置值时，跳转处理
 * @author xunmin
 *
 */
public class MySimpleMappingExceptionResolver extends AbstractHandlerExceptionResolver{

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
//		ModelAndView mv = new ModelAndView("redirect:/common/error_fileupload.do");
		if (ex.getClass().equals(MaxUploadSizeExceededException.class)) {
			//<!-- 遇到MaxUploadSizeExceededException异常时，自动跳转到文件上传失败的页面 -->
			try {
				response.sendRedirect("/common/error_fileupload.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//其他的错误
			System.err.println("其他错误！");
		}
		return null;
	}

}
