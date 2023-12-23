package com.myHighSpeedRail.johnny.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.dto.ProductTrackingRequestDto;
import com.myHighSpeedRail.johnny.dto.TrackingListResponse;
import com.myHighSpeedRail.johnny.model.ProductTrackingList;
import com.myHighSpeedRail.johnny.service.ProductService;
import com.myHighSpeedRail.johnny.service.ProductTrackingListService;

@Controller
public class ProductTrackingListController {
	
	@Autowired
	private ProductTrackingListService ptlServ;
	@Autowired
	private ProductService pServ;
	
	@PostMapping("/ProductTrackingList/add")
	@ResponseBody
	public ResponseEntity<String> addProductToTrackingList(@RequestBody ProductTrackingRequestDto ptl) {
		
		boolean isProductInTrackingList = ptlServ.isProductInTrackingList(ptl.mId, ptl.pId);
		
		if(isProductInTrackingList) {
			return new ResponseEntity<String> ("商品已在追蹤清單內",HttpStatus.OK);
		}else {
			ptlServ.addProductToTrackingList(ptl.pId, ptl.mId);
			return new ResponseEntity<String> ("追蹤商品成功",HttpStatus.OK);
		}
		
		
		
		
	}
	
	@GetMapping("/ProductTrackingList")
	@ResponseBody
	public List<TrackingListResponse> findTrackingListByMemberId(@RequestParam("mId") String mId){
		
		List<ProductTrackingList> trackings = ptlServ.findByMemberId(mId);
		
		List<TrackingListResponse> resList = new ArrayList<>();
		
		for(ProductTrackingList tracking : trackings) {
				TrackingListResponse res = new TrackingListResponse();
				ProductAndPhotoSegmentDto product = pServ.findProductById(tracking.getProduct().getProductId());
				res.productId = product.productId;
				res.trackingId = tracking.getProductTrackingListId();
				res.productName = product.productName;
				res.productPrice = product.productPrice;
				res.trackingDate = tracking.getTrackingDate();
				res.productType = product.productType;
				res.photoData = product.photoData;
				resList.add(res);	
		}
		
		return resList;
		
		
	}
	
	@DeleteMapping("/ProductTrackingList/delete")
	@ResponseBody
	public String deleteByMemberIdAndTrackingId(@RequestParam("mId") String mId, @RequestParam("tId") Integer tId) {
		return ptlServ.deleteByMemberIdAndtrackingId(mId, tId);
	}
	
	@DeleteMapping("/ProductTrackingList/delete2")
	@ResponseBody
	public String deleteByMemberIdAndProductId(@RequestParam("mId") String mId, @RequestParam("pId") Integer pId) {
		return ptlServ.deleteByMemberIdAndProductId(mId, pId);
	}
	
	
	
}
