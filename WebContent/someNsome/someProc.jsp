<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dao" class="homepage.someDao" />
<jsp:useBean id="dto" class="homepage.someDto" />
<jsp:setProperty property="*" name="dto"/>
