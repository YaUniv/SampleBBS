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

//EntryArticlePageServletにアクセスされた場合に動作
@WebServlet("/EditArticlePageServlet")
public class EditArticlePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//コンストラクタ（省略可能）
    public EditArticlePageServlet() {
        super();
    }

    //GETアクセス時に動作
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession()でsessionを取得し，getAttribute("userId")でuserIdという名前の値を取得.
		//ログイン時に設定した自分のuserIdが取れる．
		//ただし，そのままではObject型になってしまうため，Stringにキャストして変数に受け取る．
//		String editorId = (String) request.getSession().getAttribute("userId");
		//DBアクセス用のインスタンスを作成
		Dao dao = new Dao();
//		//自身が登録した最新の記事を取り出す場合は下記を使用
//		Article article = dao.getNewestArticleByEditorId(editorId);
		//自身が登録した最新の記事を取り出す場合は下記を使用
		Article article = dao.getArticleById(Integer.parseInt(request.getParameter("id")));
		
		//編集するArticleをSessionに
		request.getSession(false).setAttribute("editArticle" , article);
		
		//./WEB-INF/jsp/entryArticle.jspを表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/jsp/editArticle.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGetに丸投げ
		doGet(request, response);
	}

}
