<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试用户CRUD操作</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 页面加载完后，自动发searchUsersServlet请求，加载到userListDiv中显示
		$("#userListDiv").load("userAction!searchUsers.action");
	});
    // 新增 按钮事件触发函数
	function toAdd() {
    	// 获取addForm中的请求信息
		var _data = $("#addForm").serialize();
		//alert(_data);
		// 发添加新用户的Ajax请求
		$.ajax({
			type : 'post',
			url : 'userAction!addUser.action',
			data : _data,
			success : function(msg) {
				alert(msg.message);
				// 更新最新的用户列表信息
				$("#userListDiv").load("userAction!searchUsers.action");
			}
		});
	}
	function toEdit() {
		var _data = $("#editForm").serialize();
		alert(_data);
		$.ajax({
			type : 'post',
			url : 'userAction!editUser.action',
			data : _data,
			success : function(msg) {
				alert(msg.message);
				$("#userListDiv").load("userAction!searchUsers.action");
			}
		});
	}

	function toDelete() {
		var chks = $("input[name='checkbox']:checked");
		if (chks.length == 0) {
			alert("请选择要删除的用户");
		} else if (chks.length > 1) {
			alert("一次只能删除一个用户");
		} else {
			var to = confirm("您确定要删除选中的用户?");
			if (to) {
				var _data = "id=" + chks.val();
				$.ajax({
					type : 'post',
					data : _data,
					url : 'userAction!deleteUser.action',
					success : function(msg) {
						alert(msg);
						$("#userListDiv").load("userAction!searchUsers.action");
					}
				});
			}
		}
	}

	function toShowAdd() {
		$("#LayerAdd").show(1000);
	}
	function toShowEdit() {
		//alert($("input[name='checkbox']:checked").length);
		var chks = $("input[name='checkbox']:checked");
		if (chks.length == 0) {
			alert("请选择要编辑的用户");
		} else if (chks.length > 1) {
			alert("一次只能修改一个用户");
		} else {
			var _data = "id=" + chks.val();
			$.ajax({
				type : 'post',
				data : _data,
				url : 'userAction!searchUser.action?type=byId',
				dataType : 'json',
				success : function(msg) {
					$("#editForm #id").val(msg.urId);
					$("#editForm #userName").val(msg.urUserName);
					$("#editForm #age").val(msg.urAge);
					$("#editForm #status").val(msg.urStatus);
					//alert($("#editForm #age").val());
					$("#LayerEdit").show(1000);
				}
			});
		}

	}
	function toCloseAdd() {
		$("#LayerAdd").hide(1000);
	}
	function toCloseEdit() {
		$("#LayerEdit").hide(1000);
	}
</script>
<style type="text/css">
<!--
.STYLE2 {
	color: #000000
}

#LayerAdd {
	position: absolute;
	left: 113px;
	top: 183px;
	width: 434px;
	height: 193px;
	z-index: 1;
	background-color: #99FFFF;
	display: none;
}

#LayerEdit {
	position: absolute;
	left: 113px;
	top: 183px;
	width: 434px;
	height: 193px;
	z-index: 1;
	background-color: #99FFFF;
	display: none;
}
-->
</style>
</head>

<body>
	<div id="LayerAdd">
		<form name="addForm" name="addForm" id="addForm" method="post"
			action="">

			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="2" align="center"><strong><BR>添加新用户<br></strong></td>
				</tr>
				<tr>
					<td width="47%" align="right">用户名：</td>
					<td width="53%"><input name="user.urUserName" type="text"
						id="userName"></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input name="user.urPassword" type="password" id="password"></td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><input name="user.urAge" type="text" id="age"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						name="Submit4" value="添加" onclick="toAdd()"> <input
						type="button" name="Submit5" value="关闭" onclick="toCloseAdd()"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="LayerEdit">
		<form name="editForm" id="editForm" method="post" action="">
			<input type="hidden" name="id" id="id" />
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="2" align="center"><strong><br>
							修改用户信息<br> </strong></td>
				</tr>
				<tr>
					<td width="47%" align="right">用户名：</td>
					<td width="53%"><input name="userName" type="text"
						id="userName" readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">年龄：</td>
					<td><input name="age" type="text" id="age"></td>
				</tr>
				<tr>
					<td align="right">状态：</td>
					<td><select name="status" id="status">
							<option value="Active">Active</option>
							<option value="Inactive">Inactive</option>
							<option value="Locked">Locked</option>
							<option value="Deleted">Deleted</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						name="Submit4" value="修改" onclick="toEdit()"> <input
						type="button" name="Submit5" value="关闭" onclick="toCloseEdit()"></td>
				</tr>
			</table>
		</form>
	</div>

	<p>&nbsp;</p>
	<p>测试用户CRUD页面</p>
	<table width="539" border="1">
		<tr>
			<td colspan="5" align="right"><input type="button" name="Submit"
				value="新增" onclick="toShowAdd()"> <input type="submit"
				name="Submit2" value="修改" onclick="toShowEdit()"> <input
				type="button" name="Submit3" value="删除" onclick="toDelete()"></td>
		</tr>

		<tr>
			<td>
				<div id="userListDiv"></div>
			</td>
		</tr>
	</table>
	<p>&nbsp;</p>
</body>
</html>
