package com.bit.paperhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.SearchDto;
import com.bit.paperhouse.dto.WriterDto;
import com.bit.paperhouse.service.SearchService;

@Controller
public class SearchController {

	final int VIEW_TOTAL_PAGE = 6;
	final int VIEW_MORE_WRITER = 5;
	final int VIEW_MORE_ARTICLE = 7;
	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value = "/search")
	public String search(SearchDto searchDto, Model model) {
		System.out.println("SearchController search");
		if(searchDto.getNowPage() == 0) {
			SearchDto sd = new SearchDto();
			sd.setStart(1);
			sd.setEnd(5);
			List<WriterDto> bestWriters = searchService.getBestWriter(sd);
			
			sd.setStart(1);
			sd.setEnd(7);
			List<ArticleDto> bestArticles = searchService.getBestArticle(sd);
			
			model.addAttribute("bestWriters", bestWriters);
			model.addAttribute("bestArticles", bestArticles);
		}
		
		return "/search";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSearchWlist", method = RequestMethod.GET)
	public List<WriterDto> getSearchList(SearchDto searchDto) {
		if(searchDto.getSearchSort() == null || searchDto.getSort() == null) {
			searchDto.setSearchSort("WRITER_NAME");
			searchDto.setSort("ASC");
		}
		System.out.println("getSearchWlist");
		int np = searchDto.getNowPage();
		int start = np*VIEW_TOTAL_PAGE+1;
		int end = start+VIEW_TOTAL_PAGE-1;
		
		searchDto.setStart(start);
		searchDto.setEnd(end);
		
		List<WriterDto> list = searchService.getSearchWriter(searchDto);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSearchClist",method = RequestMethod.GET)
	public Map<String, Object> getSearchCont(SearchDto searchDto){
		if(searchDto.getSearchSort()==null || searchDto.getSort() ==null) {
			searchDto.setSearchSort("VIEWCOUNT");
			searchDto.setSort("DESC");
		}
		System.out.println("getSearchClist");
		int np = searchDto.getNowPage();
		int start = np*VIEW_TOTAL_PAGE+1;
		int end = start+VIEW_TOTAL_PAGE-1;
		
		searchDto.setStart(start);
		searchDto.setEnd(end);
		Map<String, Object> map = searchService.getSearchCont(searchDto);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/moreWriter", method = RequestMethod.GET)
	public List<WriterDto> moreWriter(SearchDto searchDto){
		int np = searchDto.getNowPage();
		System.out.println("wirter:"+np);
		int start = np * VIEW_MORE_WRITER+1;
		int end = start + VIEW_MORE_WRITER -1;
		searchDto.setStart(start);
		searchDto.setEnd(end);
		
		return searchService.getBestWriter(searchDto);
	}
	
	@ResponseBody
	@RequestMapping(value = "/moreArticle", method = RequestMethod.GET)
	public List<ArticleDto> moreArticle(SearchDto searchDto){
		int np = searchDto.getNowPage();
		System.out.println("Article:"+np);
		int start = np * VIEW_MORE_ARTICLE+1;
		int end = start + VIEW_MORE_ARTICLE -1;
		searchDto.setStart(start);
		searchDto.setEnd(end);
		List<ArticleDto> list = searchService.getBestArticle(searchDto);
		System.out.println(list);
		return searchService.getBestArticle(searchDto);
	}
}