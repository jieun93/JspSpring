package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	IBoardDAO boardDAO;
	@Inject
	IAttatchDAO attatchDAO;
	
	@Inject
	WebApplicationContext context;
	@Value("#{appInfo.attatchPath}")
	String attatchPath;
	File saveFolder;
	
	@PostConstruct
	public void init() {
		String realPath = context.getServletContext().getRealPath(attatchPath);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}

	private void deleteBinary(String[] delAttSaveNames) { // 파일삭제
		if(delAttSaveNames==null || delAttSaveNames.length==0) return;
		try {
			for(String delName : delAttSaveNames) {
				FileUtils.forceDelete(new File(saveFolder, delName));// 서버에 있는 실제 파일을 지우는거 			
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int processAttatches(BoardVO board){ // 파일 저장
		int[] delNos =  board.getDeleteAttatches();
		int rowcnt = 0;
		String[] delAttSaveNames = null;
		if(delNos!=null && delNos.length>0) {
			delAttSaveNames = new String[delNos.length];
			for(int i=0; i<delNos.length; i++) {
				delAttSaveNames[i] = attatchDAO.selectAttatch(delNos[i]).getAtt_savename(); 
			}
			rowcnt = attatchDAO.deleteAttatchs(board); // meta data
			deleteBinary(delAttSaveNames);
		}
		
		List<AttachVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt += attatchDAO.insertAttatchs(board);
			
			try {
				for(AttachVO attatch : attatchList) {
						attatch.getRealFile().transferTo(new File(saveFolder, attatch.getAtt_savename()));
				}
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e);
			}// try end
		}

		deleteBinary(delAttSaveNames);
		return rowcnt;
	}
	
	@Transactional
	@Override
	public ServiceResult createBoard(BoardVO board) {
		int rowcnt = boardDAO.insertBoard(board);
		rowcnt += processAttatches(board);
		ServiceResult result = null;
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int readBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> readBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO readBoard(int bo_no) {
		BoardVO board = boardDAO.selectBoard(bo_no);
		if(board==null) {
			throw new DataNotFoundException(bo_no+"에 해당하는 글이 없음.");
		}
		boardDAO.incrementHit(bo_no);
		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		BoardVO savedBoard = readBoard(board.getBo_no());
		ServiceResult result = null;
		if(savedBoard.getBo_pass().equals(board.getBo_pass())) {
			int rowcnt = boardDAO.updateBoard(board);
			if(rowcnt>0) {
				processAttatches(board);
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		BoardVO savedBoard = readBoard(board.getBo_no());
		ServiceResult result = null;
		if(savedBoard.getBo_pass().equals(board.getBo_pass())) {
			List<AttachVO> attatchList = savedBoard.getAttatchList();
			String[] delAttNames = null;
			if(attatchList!=null && !attatchList.isEmpty()) {
				delAttNames = new String[attatchList.size()];
				for(int i=0; i<delAttNames.length; i++) {
					delAttNames[i] = attatchList.get(i).getAtt_savename();
				}
			}
			int rowcnt = boardDAO.deleteBoard(board);
			
			if(rowcnt>0) {
				deleteBinary(delAttNames);
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public AttachVO downloadAttatch(int att_no) {
		AttachVO attatch = attatchDAO.selectAttatch(att_no);
		if(attatch==null) {
			throw new DataNotFoundException(att_no+" 파일이 없음.");
		}
		attatchDAO.incrementDownCount(att_no);
		return attatch;
	}

}
