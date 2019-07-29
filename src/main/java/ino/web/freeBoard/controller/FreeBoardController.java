package ino.web.freeBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ino.web.freeBoard.common.util.Pagination;
import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.service.FreeBoardService;
import oracle.net.aso.p;

@Controller
public class FreeBoardController {

	@Autowired
	FreeBoardService freeBoardService;

	/* 게시판 리스트 + 페이징처리 */
	@RequestMapping("/main.ino")
	public ModelAndView mainList(@RequestParam(required = false, defaultValue = "null") String category,
			@RequestParam(required = false, defaultValue = "null") String searchText,
			@RequestParam(defaultValue = "1") int curPage ,
			@RequestParam(required = false, defaultValue = "null") String year,
			@RequestParam(required = false, defaultValue = "null") String month, 
			@RequestParam(required = false, defaultValue = "null") String day) {

		List categoryList = freeBoardService.searchCategoryList();
		List yearList = freeBoardService.yearList();
		List monthList = freeBoardService.monthList();
		List dayList = freeBoardService.dayList();
		
		// System.out.println("카테고리 : " + category);
		// System.out.println("검색텍스트 : " + searchText);
		// System.out.println("페이지 : " + curPage);

		ModelAndView mav = new ModelAndView();
		Pagination page = new Pagination();

		if (category.equals("null") && searchText.equals("null") && year.equals("null")
				&& month.equals("null") && day.equals("null")) {

			int listCount = freeBoardService.freeBoardList();
			Map<String, Object> map = new HashMap<String, Object>();

			page.Pagination(listCount, curPage);

			int start = page.getStartIndex();
			int end = page.getEndIndex();
			int startPage = page.getStartPage();
			int endPage = page.getEndPage();
			int curRange = page.getCurRange();
			int pageCnt = page.getPageCnt();
			int rangeCnt = page.getRangeCnt();

			map.put("start", start);
			map.put("end", end);

			List<FreeBoardDto> list = freeBoardService.freeBoardListPaging(map);
			
			mav.addObject("categoryList", categoryList);
			mav.addObject("yearList", yearList);
			mav.addObject("monthList", monthList);
			mav.addObject("dayList", dayList);
			mav.addObject("list", list);
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("curRange", curRange);
			mav.addObject("pageCnt", pageCnt);
			mav.addObject("rangeCnt", rangeCnt);
			mav.setViewName("boardMain");
			
			return mav;
		} else {
			
			Map<String, Object> map = new HashMap<String, Object>();
			mav.addObject("year", year);
			mav.addObject("month", month);
			mav.addObject("day", day);
			
			if (1 == month.length()) {
				month = "0" + month;
			}
			if (1 == day.length()) {
				day = "0" + day;
			}
			
			String date = year + "-" + month + "-" + day;
			
			
			
			
			map.put("category", category);
			map.put("searchText", searchText);
			map.put("date", date);
			int boardListCnt = freeBoardService.searchBoardListCount(map);

			page.Pagination(boardListCnt, curPage);

			int start = page.getStartIndex();
			int end = page.getEndIndex();
			int startPage = page.getStartPage();
			int endPage = page.getEndPage();
			int curRange = page.getCurRange();
			int pageCnt = page.getPageCnt();
			int rangeCnt = page.getRangeCnt();
			

			
			map.put("start", start);
			map.put("end", end);

			List<FreeBoardDto> list = freeBoardService.searchFreeBoardListPaging(map);

			// System.out.println("리스트 사이즈 : " + list.size());
			
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("curRange", curRange);
			mav.addObject("pageCnt", pageCnt);
			mav.addObject("rangeCnt", rangeCnt);
			mav.addObject("category", category);
			mav.addObject("searchText", searchText);
			mav.addObject("categoryList", categoryList);
			mav.addObject("yearList", yearList);
			mav.addObject("monthList", monthList);
			mav.addObject("dayList", dayList);
			mav.addObject("list", list);
			mav.setViewName("boardMain");
			
			return mav;
		}
	}

	/* 글쓰기 페이지이동 */
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert() {
		return "freeBoardInsert";
	}

	/* 상세페이지 이동 */
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView detailPage(@RequestParam int num) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("detailBoard", freeBoardService.getDetailByNum(num));
		modelAndView.setViewName("freeBoardDetail");

		return modelAndView;

	}

	/* 글 수정 */
	@RequestMapping("/update.ino")
	@ResponseBody
	public String update(FreeBoardDto dto) {
		// System.out.println(dto.getContent());
		// System.out.println(dto.getName());
		// System.out.println(dto.getTitle());
		freeBoardService.freeBoardModify(dto);
		return "ok";
	}

	/* 글쓰기 */
	@RequestMapping("/Insert.ino")
	@ResponseBody
	public String insert(FreeBoardDto dto) {
		// System.out.println(dto.getContent());
		// System.out.println(dto.getName());
		// System.out.println(dto.getTitle());
		freeBoardService.freeBoardInsertPro(dto);
		return "ok";
	}

	/* 글 삭제 */
	@RequestMapping("/Delete.ino")
	public String delete(int num) {
		freeBoardService.freeBoardDelete(num);
		return "redirect:/main.ino";
	}

}
