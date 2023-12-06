package com.myHighSpeedRail.derekwu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.derekwu.dto.LostPropertyGuestDTO;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

@Service
public class LostPropertyService {

	@Autowired
	private LostPropertyRepository LPrepo;
	
	//自定義Query需要的Annotation
	@PersistenceContext
	private EntityManager em;
	
	//把8攔資料只秀出4攔給使用者看
	public List<LostPropertyGuestDTO> showToGuest(String simpleOutward) {
		
		List<LostProperty> lpList = LPrepo.searchSimpleLosttProperty(simpleOutward);
		List<LostPropertyGuestDTO> lpgDTOList = new ArrayList<>();
		
		for (LostProperty Lpgdto : lpList) {
			LostPropertyGuestDTO lGDTO = new LostPropertyGuestDTO();
			lGDTO.setStationName(Lpgdto.getStationName());
			lGDTO.setFindDate(Lpgdto.getFindDate());
			lGDTO.setStayStation(Lpgdto.getStayStation());
			lGDTO.setSimpleOutward(Lpgdto.getSimpleOutward());
			
			lpgDTOList.add(lGDTO);
		}
		return lpgDTOList;
	}
	
	//多重like搜尋
	public static String empMorekeywordSearch(String keywords) {
		String searchQuery = "select * from lost_property where ";
		//防呆:刪掉結尾的空格
		while(keywords.endsWith(" ")) {
			keywords = keywords.replaceFirst(".$","");
		}
		String arrayKeywords[] = keywords.split(" ");
		for(int i=0;i<arrayKeywords.length;i++) {
			if(i==0)searchQuery =searchQuery + "detail_outward like '%" + arrayKeywords[i] + "%'";
			else searchQuery = searchQuery + " and detail_outward like '%" + arrayKeywords[i] + "%'";
		}
		
		return searchQuery;
	}
	
	//設定多重like的搜尋Query
	public List<LostProperty> searchMoreDetails(String details){
		List<LostProperty> resultList = new ArrayList<>();
		try {
			resultList = em.createNativeQuery(empMorekeywordSearch(details),LostProperty.class).getResultList();
		}catch(Exception e){
		}
		return resultList;
	}
}