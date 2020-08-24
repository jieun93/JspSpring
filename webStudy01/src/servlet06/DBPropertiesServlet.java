package servlet06;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
import servlet06.dao.DataBasePropertyDAOImpl;
import servlet06.dao.iDataBasePropertyDAO;
import servlet06.service.DataBasePropertyServiceImpl;
import servlet06.service.iDataBasePropertyService;

@WebServlet("/jdbcDesc.do")
public class DBPropertiesServlet  extends HttpServlet{
	
	iDataBasePropertyService service = new DataBasePropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청을 받고 분석하는곳?
		Map<String, Object> modelMap = new HashMap<>(); // 저장하기  vo대신에 많이 활용한다. 
		req.setAttribute("modelMap", modelMap);
	
		service.readDataBaseProperties(modelMap);
		
		
		String goPage = "/WEB-INF/views/jdbcDesc.jsp"; //  서버사이드 경로 표기 방식
		req.getRequestDispatcher(goPage).forward(req, resp);
		
	}
}
