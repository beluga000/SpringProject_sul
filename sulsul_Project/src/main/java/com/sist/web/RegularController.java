package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.RegularDAO;
import com.sist.vo.RegularBoardVO;
import com.sist.vo.RegularVO;

@Controller
public class RegularController {
	@Autowired
	private RegularDAO dao;

	// regular 메인페이지 - 정기모임 리스트 ////////////
	@GetMapping("regular/regular.do")
	public String regular_list(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int rcate_no = 99;
		List<RegularVO> rList = dao.regularListData(rcate_no);
		model.addAttribute("rList", rList);

		return "regular/regular";
	}

	// regular 정기모임 생성 ////////////
	@GetMapping("regular/insert.do")
	public String insert() {
		return "regular/insert";
	}

	@GetMapping("regular/insert_ok.do")
	  public String regular_ok_vue(Model model, int no) {
	  RegularVO vo = dao.regularDetailData(no);
	  System.out.println("인서트오케 들어오니???");
	  System.out.println("vo.no:"+vo.getNo());
	  return "regular/detail";
	  }


	// regular 정기모임 디테일 ////////////
	@GetMapping("regular/detail.do")
	public String detail(Model model, int no) {
		RegularVO vo = dao.regularDetailData(no);
		RegularBoardVO bvo = dao.regularBoardDetail(no);
		model.addAttribute("vo", vo);
		return "regular/detail";
	}


//	// regular 정기모임 검색창
	@PostMapping("regular/find.do")
	public String regular_find_list(@Param("name") String name, String page, Model model) {
		if (page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		Map<String, Object> map = new HashMap<>();
		map.put("start", (curpage * 10) - 9);
		map.put("end", curpage * 10);
		map.put("name", name);
		System.out.println("map.put(\"name\", name)"+map.put("name", name));
		System.out.println("map.put(\"end\", end)"+map.put("start", curpage * 10));
		System.out.println("map.put(\"start\", start)"+map.put("start", (curpage * 10) - 9));
		List<RegularVO> fList = dao.regularFindList(map);
		model.addAttribute("fList", fList);
		int totalpage = dao.regularFindTotalPage(name);
		final int BLOCK = 3;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		if (endPage > totalpage) {
			endPage = totalpage;
		}
		System.out.println("중간에 걸린단게 모야?");
		return "regular/find";
	}

//	// regular 정기모임 글쓰기 ////////////
//	@PostMapping("regular/insert_ok.do")
//	public String regular_insert_ok(RegularBoardVO vo) {
//		System.out.println("인서트오케이");
//		List<MultipartFile> list = vo.getFiles();
//		if(list==null) { //업로드 안된상태
//			vo.setFilename("");
//			vo.setFilesize("");
//			vo.setFilecount(0);
//		}else { // 업로드가 된상태
//			String fn = "";
//			String fs = "";
//			for(MultipartFile mf:list) {
//				String of = mf.getOriginalFilename();
//				fn+=of+","; // 데이터베이스 첨부
//				File file = new File("c:\\download\\"+of);//업로드
//				fs+=mf.getSize()+",";
//				try {
//					mf.transferTo(file);
//				}catch(Exception e) {}
//			}
//			vo.setFilename(fn.substring(0,fn.lastIndexOf(","))); // 마지막에 있는 "," 지우기 위해 쓴것
//			vo.setFilesize(fs.substring(0,fs.lastIndexOf(",")));
//			vo.setFilecount(list.size());
//		}
//		System.out.println("vo.content:"+vo.getContent());
//		System.out.println("vo.getFilename:"+vo.getFilename());
//		System.out.println("vo.getFilesize:"+vo.getFilesize());
//		System.out.println("vo.getFilecount:"+vo.getFilecount());
//		dao.regularBoardInsert(vo);
//		return "redirect:detail.do?no="+vo.getRno();
//	}



}
