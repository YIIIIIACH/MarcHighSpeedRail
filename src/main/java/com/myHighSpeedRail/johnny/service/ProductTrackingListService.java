package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductTrackingList;
import com.myHighSpeedRail.johnny.repository.ProductRepository;
import com.myHighSpeedRail.johnny.repository.ProductTrackingListRepository;

@Service
public class ProductTrackingListService {
	
	@Autowired
	private ProductTrackingListRepository ptlDao;
	
	@Autowired
	private ProductRepository pDao;
	
	public ProductTrackingList addProductToTrackingList(Integer pId,String mId) {
		
		Optional<Product> optional = pDao.findById(pId);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			ProductTrackingList aList = new ProductTrackingList();
			aList.setmemberId(mId);
			aList.setProduct(product);
			
			return ptlDao.save(aList);
		}
		throw new RuntimeException("商品不存在");
	}
	
	public List<ProductTrackingList> findByMemberId(String mId){
		return ptlDao.findByMemberId(mId);	
	}
	
	public String deleteByMemberIdAndtrackingId(String mId, Integer tId) {
		ptlDao.deleteByMemberIdAndTrackingId(mId, tId);
		return "已取消追蹤！";
	}
	
	public String deleteByMemberIdAndProductId(String mId, Integer pId) {
		ptlDao.deleteByMemberIdAndproductId(mId, pId);
		return "已取消追蹤！";
	}
	
	public boolean isProductInTrackingList(String mId, Integer pId) {
		
		 Optional<ProductTrackingList> optional = ptlDao.findByMemberIdAndProductId(mId, pId);
		 
		 return optional.isPresent();
	}
	
}
