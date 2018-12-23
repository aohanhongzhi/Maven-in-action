<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table width="100%" border="1">
  <tr>
    <td width="51" bgcolor="#CCCCCC"><div align="center"><strong><span class="STYLE2">选择</span></strong></div></td>
    <td width="111" bgcolor="#CCCCCC"><div align="center"><strong>序号</strong></div></td>
    <td width="137" bgcolor="#CCCCCC"><div align="center"><strong>用户名</strong></div></td>
    <td width="105" bgcolor="#CCCCCC"><div align="center"><strong>年龄</strong></div></td>
    <td width="101" bgcolor="#CCCCCC"><div align="center"><strong>状态</strong></div></td>
  </tr>
  
  <c:forEach items="${userList }" var="_user" varStatus="status">
  <c:choose>
     <c:when test="${status.index%2==0 }">
       <tr bgcolor="#FFFFFF">
     </c:when>
     <c:otherwise>
       <tr bgcolor="#99FFFF">
     </c:otherwise>
  </c:choose>
    <td><input type="checkbox" name="checkbox" value="${_user.urId }"></td>
    <td>${status.index+1 }</td>
    <td>${_user.urUserName }</td>
    <td>${_user.urAge }</td>
    <td>${_user.urStatus }</td>
  </tr>
  </c:forEach>
</table>
