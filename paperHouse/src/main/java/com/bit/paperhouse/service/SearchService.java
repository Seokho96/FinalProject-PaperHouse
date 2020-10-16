package com.bit.paperhouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.paperhouse.dao.SearchRepository;
import com.bit.paperhouse.dto.ArticleDto;
import com.bit.paperhouse.dto.SearchDto;
import com.bit.paperhouse.dto.WriterDto;

@Service
@Transactional
public class SearchService {

	@Autowired
	SearchRepository searchRepository;
	
	public List<WriterDto> getSearchWriter(SearchDto searchDto) {
		List<WriterDto> list = searchRepository.getSearchWriter(searchDto);
	
		return list;
	}
	
	public Map<String, Object> getSearchCont(SearchDto searchDto) {
		List<ArticleDto> list = searchRepository.getSearchCont(searchDto);
		List<ArticleDto> poem = new ArrayList<ArticleDto>();
		List<ArticleDto> novel = new ArrayList<ArticleDto>();
		List<ArticleDto> essay = new ArrayList<ArticleDto>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (ArticleDto arti : list) {
			if(arti.getCategorys().equals("시")) {
				poem.add(arti);
			}else if(arti.getCategorys().equals("소설")) {
				novel.add(arti);
			}else if(arti.getCategorys().equals("에세이")) {
				essay.add(arti);
			}
		}
		map.put("list", list);
		map.put("poem", poem);
		map.put("novel", novel);
		map.put("essay", essay);
		return map;
	}
	
	public List<WriterDto> getBestWriter(SearchDto searchDto) {
		return searchRepository.getBestWriter(searchDto);
	}
	
	public List<ArticleDto> getBestArticle(SearchDto searchDto) {
		return searchRepository.getBestArticle(searchDto);
	}
	
}
