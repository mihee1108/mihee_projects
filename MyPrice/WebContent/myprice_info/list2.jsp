<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<c:forEach items="${list4}" var="i">
            			<option value="${i.entpId}">${i.entpName }</option>
            		</c:forEach>