package com.myHighSpeedRail.derekwu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.derekwu.dto.LostPropertyGuestDTO;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

import jakarta.persistence.EntityManager;
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
			resultList = em.createNativeQuery(empMorekeywordSearch(details),LostProperty.class).getResultList();
			
		return resultList;
	}
	
	//找全部並且page化
	public Page<LostProperty> findByPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 8, Sort.Direction.DESC, "findDate");
		
		Page<LostProperty> page = LPrepo.findAll(pgb);
		return page;
	}
	
	//修改(Update)該ID除了圖片以外的其他值
	public LostProperty updateByLostPropertyId(Integer lostPropertyId,
			Integer tripId,
			String stationName,
			Date findDate,
			String stayStation,
			String simpleOutward,
			String detailOutward,
			Boolean letterCheck,
			Boolean receiveCheck) {
		LostProperty lp = LPrepo.findByLostPropertyId(lostPropertyId);
		lp.setTripId(tripId);
		lp.setStationName(stationName);
		lp.setFindDate(findDate);
		lp.setStayStation(stayStation);
		lp.setSimpleOutward(simpleOutward);
		lp.setDetailOutward(detailOutward);
		lp.setLetterCheck(letterCheck);
		lp.setReceiveCheck(receiveCheck);
		LPrepo.save(lp);
		return lp;
		
	}
	
	//設定以page呈現多重like的搜尋Query 冷靜，先想想

	
}