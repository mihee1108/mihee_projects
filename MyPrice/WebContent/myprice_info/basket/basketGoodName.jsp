<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   				 <c:forEach items="${goodName}" var="i2">
							<option value="${i2.goodId}">${i2.goodName}</option>
					</c:forEach>