package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

		// 컨테이너의 동작 특성
/**
 * 서블릿 운영 과정에서 컨테이너의 역할
 * container : 컨테이너에 의해 관리되는 객체의 라이프 사이클 제어자.
 * Web Container == Servlet Container == JSP Container == WAS(web Application Server) --tomcat
 *  톰캣의 역할
 *   1. 서블릿의 객체 생성 : 해당 서블릿을 대상으로 최초의 요청이 발생(load-on-startup으로 생성시점 제어 가능).
 *   	생성된 서블릿은 기본 싱글턴으로 관리 
 *   	-init
 *   
 *   2. 매핑된 요청이 발생하면, 관련된 콜백 메소드 호출 
 *   	- service, do???메소드 
 *   
 *   
 *   3. 필요없어지면, GC(gabige collection0 의 대상으로 설정.
 *   	- destroy
 *   
 */
public class DescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//  기본생성자  -- 싱글톤
    public DescriptionServlet() {
        super();
      System.out.println(getClass().getSimpleName()+"서블릿 객체 생성");
    }
    
    
    //-- 싱글톤
    // serveletConfig --> 톰캣에 의해 만들어졌다. // web.xml에 대한 정보를 가지고 있다. 
    // 서블릿을 등록한것을 가져요는거???
    // 
    @Override
    		public void init(ServletConfig config) throws ServletException {
    			super.init(config);  // 제거하면 안된다. 
    			String param = config.getInitParameter("test");
    			Enumeration<String> names =  config.getInitParameterNames(); // 현재 전달되는 파라미터의 이름을 가져오는거
    			while (names.hasMoreElements()) {
    				String name = (String) names.nextElement();
					String value = config.getInitParameter(name);
					System.out.printf("%s : %s", name, value);
				}
    			System.out.println(getClass().getSimpleName()+"서블릿 초기화, 전달 파라미터 : "+param);
    			
    		}

    	// request가 어떻게 되는지 알아야 한다. 
     @Override
    		protected void service(HttpServletRequest req, HttpServletResponse resp)
    				throws ServletException, IOException {
    	 	System.out.println("service 메소드 첫라인");
    	// 	super.service(req, resp); //  doget을 호출하는 작업을 한다. 
    	 	System.out.println("service 메소드 마지막 라인 ");
    		}
     

     
    // 요청 콜백 doget  요청이 들어돌때 마다 반복되는거 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(Thread.currentThread().getName()); // 현재 실행중인  쓰레드 name값을 확인할수 잇따.-->확장 cgi 방식이다./ 쓰레드가 재활용되고 있다는 거 
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	//-- 싱글톤  메로리가 부족해서 오래된것을 제거하기위해서 호출된다. 
	// detroy 는 실행이 안될수도 있는 메서드 이다. 
	@Override
			public void destroy() {
				super.destroy();
				System.out.println(getClass().getSimpleName()+"서블릿 소멸 ");
	}

	
}
