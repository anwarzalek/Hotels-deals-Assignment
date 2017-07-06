package com.anwarzalek.assignment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by anwar on 05/07/17.
 */
@WebServlet("/HotelsForm")
public class Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
                "<html>\n" +
                "<body bgcolor = \"#f0f0f0\">\n<p>" + Utilities.response(request) +
                "</p></body> " +
                "</html>"
        );
    }
}