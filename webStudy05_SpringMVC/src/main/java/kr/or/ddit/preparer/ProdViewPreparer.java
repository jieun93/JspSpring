package kr.or.ddit.preparer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.commons.dao.IResourceDAO;
import kr.or.ddit.vo.MenuVO;

public class ProdViewPreparer implements ViewPreparer{ // 뷰를 완성하기 전에 동작한다. 
	
	IResourceDAO resDAO;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		MenuVO menu1 = new MenuVO();
		menu1.setUrl("/prod/prodList.do");
		menu1.setText("상품 목록 조회");
		
		MenuVO menu2 = new MenuVO();
		menu1.setUrl("/prod/prodInsert.do");
		menu1.setText("신규상품");
		
		List<MenuVO> menuList =new ArrayList<MenuVO>();
		menuList.add(menu1);
		menuList.add(menu2);
		Map<String, Object> scope = tilesContext.getContext(Request.REQUEST_SCOPE);
		scope.put("menuList", menuList);
		
		
	}

}
