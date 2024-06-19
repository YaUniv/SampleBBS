<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- ここから，インポート宣言 -->    
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import ="beans.Article" %>
<%@page import="java.sql.Timestamp"%>
<!-- ここまで，インポート宣言 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit article</title>
</head>
<body>
<label>記事編集</label><br><br>
	<!-- EntryArticleServletにデータを送信するformを宣言する.登録処理なので，methodはpostを指定．
	typeがsubmitになっている「送信する」ボタンがクリックされたとき，inputタグとtextareaタグのvalueを送る．
	サーブレット側では，request.getParameter("なまえ")メソッドを使い，各inputの値を文字列型で受け取れる．
	「なまえ」の部分には，inputタグのnameを指定する．（例)タイトルの値を取得する -> request.getParameter("title");
	-->
	<label>編集前</label><br>
	<%Article a = (Article)( request.getSession(false).getAttribute("editArticle") );%>
	<label>タイトル：<br><%=a.getTitle()%></label> <br>
	<label>本文：<br><%=a.getBody()%></label> <br>
		
	<form action="./EditArticleServlet" method="post">
		<p><label>タイトル：</label><br><input type="text" name="title" size="40" maxlength="30" placeholder="タイトルを入力してください．"></p>
		<p><label>本文：</label><br><textarea name="body" rows="5" cols="40" placeholder="本文を入力してください．"></textarea></p>
		<p><input type="submit" value="送信する"></p>
	</form>
</body>
</html>