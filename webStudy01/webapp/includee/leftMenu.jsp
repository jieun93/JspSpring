<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath()%>">
              <span data-feather="home"></span>
              Home(Welcome) <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=STANDARDJSP">
              <span data-feather="file"></span>
              STANDARDJSP
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALCULATE">
              <span data-feather="shopping-cart"></span>
            	  계산기
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALENDAR">
              <span data-feather="users"></span>
            	  달력
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=SESSIONTIMER">
              <span data-feather="bar-chart-2"></span>
            	  세션타이머
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CONTEXTBROWSING">
              <span data-feather="bar-chart-2"></span>
            	 파일
            </a>
          </li>
        </ul>
      </div>
    </nav>