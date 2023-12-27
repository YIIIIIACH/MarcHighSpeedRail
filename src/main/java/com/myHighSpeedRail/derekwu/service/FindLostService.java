package com.myHighSpeedRail.derekwu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.myHighSpeedRail.derekwu.dto.FindLostDetailDTO;
import com.myHighSpeedRail.derekwu.model.FindLost;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.FindLostRepository;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

@Service
public class FindLostService {
	@Autowired
	private FindLostRepository flRepo;
	
	@Autowired
	private LostPropertyRepository lpRepo;

	public void addNewFindLost(Integer id, @RequestBody LostProperty lp) {
		FindLost findLost = new FindLost();
		findLost.setFindLostId(id);
		findLost.setFindLostDate(new Date());
		findLost.setLostProperty(lp);
		System.out.println(findLost);

		flRepo.save(findLost);

	}
	public List<FindLost> findAll(){
		return flRepo.findAll();
	}
	
	public List<FindLostDetailDTO> showFindLost(){
		List<FindLost> fl = flRepo.findAll();
		List<FindLostDetailDTO> flDtoList = new ArrayList<>();
		for(FindLost flList:fl) {
			FindLostDetailDTO fldDto = new FindLostDetailDTO();
			fldDto.setDetailOutward(flList.getLostProperty().getDetailOutward());
			fldDto.setFindLostDate(flList.getFindLostDate());
			fldDto.setFindLostId(flList.getFindLostId());
			fldDto.setLostPropertyId(flList.getLostProperty().getLostPropertyId());
			fldDto.setSimpleOutward(flList.getLostProperty().getSimpleOutward());
			fldDto.setStayStation(flList.getLostProperty().getStayStation());
			
			flDtoList.add(fldDto);
		}
		
		return flDtoList;
	}
}
