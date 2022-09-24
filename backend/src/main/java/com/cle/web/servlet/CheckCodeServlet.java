package com.cle.web.servlet;

import com.cle.util.CheckCodeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/CheckCode/*")
public class CheckCodeServlet extends BaseServlet {
    public void getCheckCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = CheckCodeUtil.outputVerifyImage(50, 100, resp.getOutputStream(), 4);
        resp.getWriter().write(code);
    }
}
