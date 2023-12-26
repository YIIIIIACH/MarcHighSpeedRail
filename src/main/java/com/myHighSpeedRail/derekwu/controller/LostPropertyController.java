package com.myHighSpeedRail.derekwu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public ResponseEntity<LostProperty> postLostProperty(
	    @RequestParam(name = "lostProperty") String lostPropertyJson,
	    @RequestPart(name = "lostPhoto", required = false) MultipartFile lostPhoto) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        LostProperty lostProperty = objectMapper.readValue(lostPropertyJson, LostProperty.class);

	        // 將 MultipartFile 的檔案內容轉換為 byte[]
	        if (lostPhoto != null) {
	            byte[] lostPhotoBytes = lostPhoto.getBytes();
	            lostProperty.setLostPhoto(lostPhotoBytes);
	        }

	        LostProperty savedLostProperty = lpRepo.save(lostProperty);
	        return new ResponseEntity<>(savedLostProperty, HttpStatus.CREATED);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
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
	public LostProperty updateLostProperty(@PathVariable("lostPropertyId")Integer lostPropertyId,@RequestBody LostProperty lostProperty
			) {
		//依據ID找到遺失物資料

		lostProperty.setLostPropertyId(lostPropertyId);
		//設定所有遺失物資料
		LostProperty lp = lpServ.updateByLostPropertyId(lostPropertyId,
				lostProperty.getTripId(),
				lostProperty.getStationName(),
				lostProperty.getFindDate(),
				lostProperty.getStayStation(),
				lostProperty.getSimpleOutward(),
				lostProperty.getDetailOutward(),
				lostProperty.getLetterCheck(),
				lostProperty.getReceiveCheck());
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
	
	//查詢所有遺失物(員工)
	@GetMapping("/LostProperty/backend/findAll")
	public Page<LostProperty> findAll(){
		return lpServ.findByPage(1);
	}
	
	//換頁數(員工)
		@GetMapping("/LostProperty/backend/findAll/{pageNumber}")
		public Page<LostProperty> findpage(@PathVariable("pageNumber")Integer pageNumber){
			return lpServ.findByPage(pageNumber);
		}
	
	
	
	
}
