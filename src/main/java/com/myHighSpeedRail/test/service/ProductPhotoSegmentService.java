package com.myHighSpeedRail.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.ProductPhoto;
import com.myHighSpeedRail.johnny.repository.ProductPhotoRepository;
import com.myHighSpeedRail.test.dto.PostPhotoDto;
import com.myHighSpeedRail.test.model.ProductPhotoSegment;
import com.myHighSpeedRail.test.repository.ProductPhotoSegmentRepository;

@Service
public class ProductPhotoSegmentService {
	@Autowired
	private ProductPhotoSegmentRepository ppsDao;
	@Autowired
	private ProductPhotoRepository rrDao;
	public List<ProductPhotoSegment> getAllSegmentByPhotoId(Integer id){
		return ppsDao.getAllSegmentByPhotoId(id);
	}
	public Integer savePhoto(PostPhotoDto ppDto) {
		byte[] photoData = ppDto.photoData.getBytes();
		int dataLen = photoData.length;
		ProductPhoto pp = rrDao.findById(6).get();
		List<ProductPhotoSegment> segList=new ArrayList<ProductPhotoSegment>();
		for(int i=0 ; i*1024 < dataLen; i++) {
			segList.add(new ProductPhotoSegment(pp, i
					,Arrays.copyOfRange(photoData, i*1024, (dataLen>(i+1)*1024)?(i+1)*1024:dataLen )
					));
		}
		return ppsDao.saveAll(segList).size();
	}
}
