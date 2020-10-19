package com.bit.paperhouse.service;

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
	
	public Map<String, Object> getSearchWriter(SearchDto searchDto) {
		List<WriterDto> list = searchRepository.getSearchWriter(searchDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		if(list.size()<5) {
			map.put("more", false);
		}else {
			map.put("more", true);
		}
		return map;
	}
	
	public Map<String, Object> getSearchCont(SearchDto searchDto) {
		List<ArticleDto> list = searchRepository.getSearchCont(searchDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		if(list.size()<7) {
			map.put("more", false);
		}else {
			map.put("more", true);
		}
		return map;
	}
	
	public List<WriterDto> getBestWriter(SearchDto searchDto) {
		return searchRepository.getBestWriter(searchDto);
	}
	
	public List<ArticleDto> getBestArticle(SearchDto searchDto) {
		return searchRepository.getBestArticle(searchDto);
	}
	
}
