package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.model.Train;
import com.myHighSpeedRail.marc.repository.SeatRepository;

@Service
public class SeatService {
	@Autowired
	private SeatRepository seatDao;
	@Autowired
	private TrainService tServ;
	
	public List<Seat> getAll(){
		return seatDao.findAll();
	}
	
	public List<Seat> findByTrainId(Integer trainId){
		return seatDao.findByTrainId(trainId);
	}
	
	public void  setupSeats()throws Exception {
		// get all train;
		// if that train alreay have seats will not create seat for that train
		List<Train> tList = tServ.findAll();
		List<Seat> allSeat = new ArrayList<Seat> ();
		for( Train train: tList) {
//			System.out.println(train.getTrainId());
			
			if( seatDao.findByTrainId(train.getTrainId()).isEmpty()) {
				//
				int seq=1;
				for(int car=1; car<=5; car++) {
					if(car!=3) {
					for( int row=1; row<=20; row++) {
//						/public Seat(Train train, Integer carriage, Integer trainSeatSequenece, String seatCode, String seatDescirption) {
						allSeat.add( new Seat(train,car,seq++,String.valueOf(row)+"-A","靠窗"));
						allSeat.add( new Seat(train,car,seq++,String.valueOf(row)+"-B","中座"));
						allSeat.add( new Seat(train,car,seq++,String.valueOf(row)+"-C","靠走道"));
						allSeat.add( new Seat(train,car,seq++,String.valueOf(row)+"-D","靠走道"));
						allSeat.add(new Seat(train,car,seq++,String.valueOf(row)+"-E","靠窗"));
					}
					}
				}
				for( int row=1; row<=11; row++) {
//					/public Seat(Train train, Integer carriage, Integer trainSeatSequenece, String seatCode, String seatDescirption) {
					allSeat.add( new Seat(train,3,seq++,String.valueOf(row)+"-A","靠窗"));
					allSeat.add( new Seat(train,3,seq++,String.valueOf(row)+"-B","中座"));
					allSeat.add( new Seat(train,3,seq++,String.valueOf(row)+"-C","靠走道"));
					allSeat.add( new Seat(train,3,seq++,String.valueOf(row)+"-D","靠走道"));
					allSeat.add( new Seat(train,3,seq++,String.valueOf(row)+"-E","靠窗"));
				}
				seatDao.saveAll(allSeat);
			}
		}
	}
}
/*
1 - 100 seats 1 to 20
2 - 100 seats 1 to 20
3 - 55  seats 1 to 11( preserve)
4 - 100 seats 1 to 20
5 - 100 seats 1 to 20

*/