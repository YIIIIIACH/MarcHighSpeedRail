package com.myHighSpeedRail.derekwu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.derekwu.dto.LostPropertyGuestDTO;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;
import com.myHighSpeedRail.derekwu.service.FindLostService;
import com.myHighSpeedRail.derekwu.service.LostPropertyService;

@RestController
public class LostPropertyController {

	
	@Autowired
	private LostPropertyRepository lpRepo;
	
	@Autowired
	private LostPropertyService lpServ;
	
	@Autowired
	private FindLostService flServ;
	
	
	//新增登記遺失物
	@PostMapping("/LostProperty/add")
	public LostProperty postLostProperty(@RequestBody LostProperty lostProperty) {
		LostProperty resLostProperty = lpRepo.save(lostProperty);
		return resLostProperty;
	}
	//查詢遺失物(員工)
	@GetMapping("/LostProperty/backend/search/detailOutward")
	public List<LostProperty> searchDetailOutward(@RequestParam("detailOutward") String detailOutward) {
		return lpRepo.searchLosttProperty(detailOutward);	
	}
	
	//查詢遺失物(訪客或會員)
	@GetMapping("/LostProperty/frontend/search/simpleOutward")
	public List<LostPropertyGuestDTO> searchSimpleOutward(@RequestParam("simpleOutward") String simpleOutward) {
		return lpServ.showToGuest(simpleOutward);
	}
	
	//修改遺失物(員工)
	@PostMapping("/LostProperty/backend/updateById/{lostPropertyId}")
	public LostProperty updateLostProperty(@PathVariable("lostPropertyId")Integer lostPropertyId,@RequestBody LostProperty lostProperty) {
		//依據ID找到遺失物資料
		lostProperty.setLostPropertyId(lostPropertyId);
		//設定所有遺失物資料
		LostProperty lp = lpRepo.save(lostProperty);
		//若找到的話
		if(lp.getReceiveCheck()==true) {
			flServ.addNewFindLost(lostPropertyId,lp);
		}
		return lp;
	}
	
	//刪除遺失物(員工)
	
	@DeleteMapping("/LostProperty/backend/deleteByLostPropertyId")
	public String deleteByLostPropertyId(Integer lostPropertyId) {
		lpRepo.deleteByLostPropertyId(lostPropertyId);
		return "刪除了LostPropertyId = "+lostPropertyId+" 的遺失物";
	}
	
	//多重查詢遺失物(員工)
	@GetMapping("/LostProperty/backend/search/detailMoreOutward")
	public List<LostProperty> searchMoreDetailsOutward(@RequestParam("details") String details){
		return lpServ.searchMoreDetails(details);
	}
}
