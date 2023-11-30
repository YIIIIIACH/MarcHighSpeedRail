package com.myHighSpeedRail.johnny.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.repository.ProductPhotoSegmentRepository;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductPhotoSegmentService {
	
	@Autowired
	private ProductPhotoSegmentRepository ppsDao;
	@Autowired
	private ProductRepository pDao;
	
	public List<ProductPhotoSegment> getAllPhotoSegmentByProductId(Integer id){
		return ppsDao.getAllPhotoSegmentByProductId(id);
	}
	
	public Integer savePhoto(ProductAndPhotoSegmentDto ppDto) {
		
		byte[] photoData = ppDto.photoData.getBytes();
		int dataLen = photoData.length;
		Product product = pDao.findById(ppDto.productId).get();
		
		//step 1 insert a photo to product_photo table
		//Photo p =  new Photo();
		// p = ppServ.save( p) ;
		
		// step 2 insert all photo segment to table of the new product_photo_id
		// p 
//		byte[] photoData = ppDto.photoData.getBytes();
//		int dataLen = photoData.length;
		List<ProductPhotoSegment> segList = new ArrayList<ProductPhotoSegment>();
		for(int i=0 ; i*1024 < dataLen; i++) {
			segList.add(new ProductPhotoSegment(product, i
					,Arrays.copyOfRange(photoData, i*1024, (dataLen>(i+1)*1024)?(i+1)*1024:dataLen )
					));
		}
		return ppsDao.saveAll(segList).size();
	}
}
