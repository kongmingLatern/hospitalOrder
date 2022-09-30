package com.cle.web.servlet;

import com.cle.util.CheckCodeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/api/CheckCode/*")
public class CheckCodeServlet extends BaseServlet {
    /**
     * 验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getCheckCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _width = req.getParameter("width");
        String _height = req.getParameter("height");
        HttpSession session = req.getSession();
        int width = Integer.parseInt(_width);
        int height = Integer.parseInt(_height);
        String code = CheckCodeUtil.outputVerifyImage(width, height, resp.getOutputStream(), 4);
        session.setAttribute("checkCode", code);
    }
}
