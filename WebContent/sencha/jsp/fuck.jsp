<%@page import="Group.GroupDto"%>
<%@page import="Group.GroupDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	GroupDao dao = new GroupDao();
	GroupDto dto = new GroupDto();
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
	String find = request.getParameter("find");
	String no = request.getParameter("no");
	String name = request.getParameter("name");
	String result = dao.mfind(find, no, name);
	out.println(result.replace('\'','\"').trim());
%>