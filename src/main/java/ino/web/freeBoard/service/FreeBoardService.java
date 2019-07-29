package ino.web.freeBoard.service;

import ino.web.freeBoard.dto.FreeBoardDto;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardService {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/*전체 리스트 카운트*/
	public int freeBoardList(){
		return sqlSessionTemplate.selectOne("freeBoardGetList");
	}
	
	/*검색 리스트 카운트*/
	public int searchBoardListCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("searchBoardGetListCount", map);
	}
	
	/*전체 리스트 페이징*/
	public List<FreeBoardDto> freeBoardListPaging(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("BoardGetListPaging",map);
	}
	
	/*검색 리스트 페이징*/
	public List<FreeBoardDto> searchFreeBoardListPaging(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("searchBoardGetListPaging",map);
	}
	
	/*게시글 등록*/
	public void freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
	}
	
	/*게시글 상세페이지*/
	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}
	
//	public int getNewNum(){
//		return sqlSessionTemplate.selectOne("freeBoardNewNum");
//	}
	
	/*게시글 수정*/
	public void freeBoardModify(FreeBoardDto dto){
		sqlSessionTemplate.update("freeBoardModify", dto);
	}
	
	/*게시글 삭제*/
	public void freeBoardDelete (int num) {
		sqlSessionTemplate.delete("freeBoardDelete", num);
	}
	
	
	/*검색조건 리스트*/
	public List searchCategoryList() {
		return sqlSessionTemplate.selectList("searchCategoryList");
	}
	
	
	/*연도 리스트*/
	public List yearList() {
		return sqlSessionTemplate.selectList("yearSearchList");
	}
	
	/*달 리스트*/
	public List monthList() {
		return sqlSessionTemplate.selectList("monthSearchList");
	}
	
	/*일 리스트*/
	public List dayList() {
		return sqlSessionTemplate.selectList("daySearchList");
	}
	
	
}