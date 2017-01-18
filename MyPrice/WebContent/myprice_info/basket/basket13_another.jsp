<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

                       
                       <c:forEach items="${another13}" var="i2">
                     <option value="${i2.code}">${i2.codeName}</option>
               </c:forEach>