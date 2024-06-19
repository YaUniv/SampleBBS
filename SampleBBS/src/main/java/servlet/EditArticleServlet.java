package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import dao.Dao;

//SampleBBS/EntryArticleServletにアクセスされると動作
@WebServlet("/EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditArticleServlet() {
        super();
    }

    //POSTアクセス時に動作
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestのデータの文字コードをUTF-8に設定
		request.setCharacterEncoding("UTF-8");
		
		//requestから，title, bodyの値を取得
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		//編集する記事オブジェクト
		Article articleToEdit = (Article)( request.getSession().getAttribute("editArticle") );

		//DBアクセス用のインスタンスを作成
		Dao dao = new Dao();

		//入力データから記事オブジェクトを編集
		articleToEdit.setTitle(title);
		articleToEdit.setBody(body);
		
		
		//記事をDBに更新
		dao.updateArticle(articleToEdit);

		//記事リストに戻る
		RequestDispatcher dispatcher = request.getRequestDispatcher("./ArticleListServlet");
		dispatcher.forward(request, response);
	}

}
