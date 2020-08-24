package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
@Service
public class ProdServiceImpl implements IProdService {
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@Value("#{appInfo.prodImagePath}")
	String prodImagePath;
	File saveFolder;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		String folderPath = application.getRealPath(prodImagePath);
		saveFolder = new File(folderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	
	@Inject
	IProdDAO prodDAO;
	
	// 이미지 파일의 경로를 만들어 주는거???
	private void processImage(ProdVO prod) { // 이미지 경로 만드는거 
		
			
		 // 에러의 사이가 0 검증 통과 
		try {
			MultipartFile prod_image = prod.getProd_image();
     		if(prod_image != null) {
     			if(1==1)
     				throw new RuntimeException("트랜잭션 확인 위한 강제 발생 예외");
			
				prod.saveFile(saveFolder);
     		}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
			
	
	@Transactional   //  트랜잭션 관리  cross concern, 트랜잭션을 관리하겠다.  aop weaving을 통해서 트랜잭션이 관리되고 있음
	@Override
	public ServiceResult createProd(ProdVO prod) { // target에 해당 
		int iprod = prodDAO.insertProd(prod);
		processImage(prod);
		ServiceResult result = ServiceResult.FAIL;
		if(iprod > 0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public ProdVO readProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if(prod==null)
			throw new DataNotFoundException(prod_id+" 상품이 없음.");
		return prod;
	}

	@Override
	public int readProdCount(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> readProdList(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	@Transactional
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		readProd(prod.getProd_id());
		int cnt = prodDAO.updateProd(prod);
		processImage(prod);
		ServiceResult result = ServiceResult.FAIL;
		if(cnt > 0) result = ServiceResult.OK;
		return result;
	}

}
