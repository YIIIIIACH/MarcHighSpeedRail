package com.myHighSpeedRail.derekwu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.derekwu.dto.LostPropertyGuestDTO;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

@Service
public class LostPropertyService {

	@Autowired
	private LostPropertyRepository LPrepo;

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
}