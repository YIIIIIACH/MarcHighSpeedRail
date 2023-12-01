package com.myHighSpeedRail.marc.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.BookingBuinessSeatDto;
import com.myHighSpeedRail.marc.dto.BookingBuinessSeatResponseDto;
import com.myHighSpeedRail.marc.dto.ImplSingleScheduleTemplateDto;
import com.myHighSpeedRail.marc.dto.ScheduleResultDto;
import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.model.ScheduleDetail;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.ScheduleArriveService;
import com.myHighSpeedRail.marc.service.ScheduleDetailService;
import com.myHighSpeedRail.marc.service.ScheduleRestSeatService;
import com.myHighSpeedRail.marc.service.ScheduleSeatStatusService;
import com.myHighSpeedRail.marc.service.ScheduleService;
import com.myHighSpeedRail.marc.service.StationService;
import com.myHighSpeedRail.marc.service.TicketDiscountService;

@Controller
public class ScheduleController {
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private ScheduleDetailService schdServ;
	@Autowired
	private ScheduleArriveService scharrServ;
	@Autowired
	private ScheduleRestSeatService schrsServ;
	@Autowired
	private ScheduleSeatStatusService schssServ;
	@Autowired
	private TicketDiscountService tdServ;
	@Autowired
	private RailRouteSegmentService rrsServ;
	@Autowired
	private RailRouteStopStationService rrssServ;
	@Autowired
	private ScheduleArriveService schArrServ;
	@Autowired
	private StationService sServ;
	
	//new version of schedule template impplementation , which are specific the discount seat type range;
	@SuppressWarnings("deprecation")
	@PostMapping("/impSingleScheduleTemplate")
	public ResponseEntity<String> impSingleScheduleTemplate(@RequestBody ImplSingleScheduleTemplateDto dto){
		try {
			System.out.println( dto.implDate);
			String[] ymd = dto.implDate.split("-");
			/// the Month of java.util.Date is from 0 to 11 , so must Minus one!!!!!!!!1
			Date tarDate = new Date((Integer.valueOf(ymd[0])%100)+100,Integer.valueOf(ymd[1])-1,Integer.valueOf(ymd[2]));
			System.out.print(tarDate);
			Schedule sch = schServ.implSingleScheduleTemplate(tarDate, dto.schtId);
			schdServ.setupScheduleDetail( sch.getScheduleId(), dto.seatRangeList, dto.discountTypeIdList);
			scharrServ.setupScheduleArriveBySchedule(sch.getScheduleId());
			
			schrsServ.setupScheduleRestSeat(sch.getScheduleId());
			
			schssServ.setupSchdeuleSeatStatus(sch.getScheduleId());		
			return new ResponseEntity<String>("implement success", HttpStatus.OK);			
		}catch(Exception e) {
			return new ResponseEntity<String>("exception happend",HttpStatus.CONFLICT);
		}
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/impScheduleTemplate")
	public ResponseEntity<String> implementTemplate(@RequestParam(value="date") String dateStr){
		try {
			String[] ymd = dateStr.split("-");
			/// the Month of java.util.Date is from 0 to 11 , so must Minus one!!!!!!!!1
			Date tarDate = new Date((Integer.valueOf(ymd[0])%100)+100,Integer.valueOf(ymd[1])-1,Integer.valueOf(ymd[2]));
			List<Schedule> schList = schServ.impScheduleTemplate(tarDate);
			for( Schedule sch : schList) {
				schdServ.setupDefaultScheduleDetail(sch.getScheduleId());
				
				scharrServ.setupScheduleArriveBySchedule(sch.getScheduleId());
				
				schrsServ.setupScheduleRestSeat(sch.getScheduleId());
				
				schssServ.setupSchdeuleSeatStatus(sch.getScheduleId());				
			}
			return new ResponseEntity<String>("implement success", HttpStatus.OK);			
		}catch(Exception e) {
			return new ResponseEntity<String>("exception happend",HttpStatus.CONFLICT);
		}
	}
	@CrossOrigin
	@SuppressWarnings("deprecation")
	@GetMapping("/searchScheduleByTimeGetOnOffStation/{onStationId}/{offStationId}/{proximateTime}")
	public@ResponseBody List<ScheduleResultDto> searchScheduleByTimeGetOnOffStation(@PathVariable Integer onStationId,
			@PathVariable Integer offStationId,
			@PathVariable String proximateTime){
		Station onSt = sServ.findById(onStationId).get();
		Station offSt =sServ.findById(offStationId).get();
		/*
		 * 1.  1班次編號  2. 乘車站  3.到達乘車站時間  4. 下車站 5.下車時間 6.所花費時間 7.價格 8.可用優惠 9.該班次所有停靠站 10. 該模式下可以選擇的優惠
		// get schedule in condition --> select on get on off station to get the route_segment of that schdule-->
		 * use rail_route segment to calculate the arrive time of get on and off station . 
		 * and use rail_route_segment_ticket_price and discount type to calculate the ticket price
		 * and use rail_route_id to get all rail_route_stop_station of the rail_route .. 
		 */
		// parse proximateTime  yyyy-MM-dd-HH-mm  warning 2023 must set as 123 !!!!
		String [] pxtStr = proximateTime.split("-");
		Date pxt= new Date();
		pxt.setYear( Integer.valueOf( pxtStr[0])-1900);
		pxt.setMonth( Integer.valueOf( pxtStr[1])-1);
		pxt.setDate( Integer.valueOf( pxtStr[2]));
		pxt.setHours(Integer.valueOf( pxtStr[3]));
		pxt.setMinutes(Integer.valueOf(pxtStr[4]));
		// step1 List<Schedule> schList = select schArr.schedule scheduleArrive as schArr where schArr.station.stationId=:onStationId and  schArr.arriveTime between :priximateTime-offset and :proximateTime+offset;
		System.out.println(pxt);
		List<Schedule> schInTimeRangeList = schArrServ.getScheduleByArriveStationTimeRange(onStationId, pxt , Long.valueOf(120));// default set range to two hours
		// step2 select schSeg.schedule  from ScheduleSegment  schSeg where schSeg.schedule in :schList and schSeg.endStation.stationId=offStationId
		// check on and off station
		List<RailRoute> railRouteList = rrsServ.findScheduleByStartStationEndStation(onStationId, offStationId);
		List<Schedule> resSch = schInTimeRangeList.stream().filter((Schedule sch)->{
			for(RailRoute rr: railRouteList) {
				if( rr.getRailRouteId()== sch.getRailRoute().getRailRouteId())	return true;
			}
			return false;
		}).collect( Collectors.toList() );
		// step3 getAll DiscountType of schedule in scheduleList 
		// pack search schedule on ScheduleResultDto
		List<ScheduleResultDto> searchRes = new ArrayList<ScheduleResultDto>();
		resSch.stream().forEach((sch)->{
			RailRouteSegment rrs = rrsServ.findByRailRouteIdStartStationEndStation( sch.getRailRoute().getRailRouteId(),
					onStationId, offStationId).get(0);
			searchRes.add( new ScheduleResultDto( sch.getScheduleId(), onSt, offSt 
					, schArrServ.findByScheduleIdStationId(sch.getScheduleId(), onStationId).getArriveTime()
					, schArrServ.findByScheduleIdStationId( sch.getScheduleId(), offStationId).getArriveTime()
					,  rrs.getRailRouteSegmentDurationMinute(), rrs.getRailRouteSegmentTicketPrice() 
					, schdServ.getScheduleAllDiscountType(sch.getScheduleId())
					));
		});
		return searchRes;
		
	}
	/*
	 * 寫出訂票搜尋controller功能能夠忽略過去時間班次、沒有符合選擇優惠座位的班次、超出訂票期限的車次
	 */
	@SuppressWarnings("deprecation")
	@GetMapping("/searchBookableScheduleByTimeGetOnOffStation/{onStationId}/{offStationId}/{proximateTime}/{discountType}")
	public@ResponseBody List<ScheduleResultDto> searchBookableScheduleByTimeGetOnOffStation(@PathVariable Integer onStationId,
			@PathVariable Integer offStationId,
			@PathVariable String proximateTime,
			@PathVariable String discountType
			){
		Station onSt = sServ.findById(onStationId).get();
		Station offSt =sServ.findById(offStationId).get();
		/*
		 * 1.  1班次編號  2. 乘車站  3.到達乘車站時間  4. 下車站 5.下車時間 6.所花費時間 7.價格 8.可用優惠 9.該班次所有停靠站 10. 該模式下可以選擇的優惠
		// get schedule in condition --> select on get on off station to get the route_segment of that schdule-->
		 * use rail_route segment to calculate the arrive time of get on and off station . 
		 * and use rail_route_segment_ticket_price and discount type to calculate the ticket price
		 * and use rail_route_id to get all rail_route_stop_station of the rail_route .. 
		 */
		// parse proximateTime  yyyy-MM-dd-HH-mm  warning 2023 must set as 123 !!!!
		String [] pxtStr = proximateTime.split("-");
		Date pxt= new Date();
		pxt.setYear( Integer.valueOf( pxtStr[0])-1900);
		pxt.setMonth( Integer.valueOf( pxtStr[1])-1);
		pxt.setDate( Integer.valueOf( pxtStr[2]));
		pxt.setHours(Integer.valueOf( pxtStr[3]));
		pxt.setMinutes(Integer.valueOf(pxtStr[4]));
		// step1 List<Schedule> schList = select schArr.schedule scheduleArrive as schArr where schArr.station.stationId=:onStationId and  schArr.arriveTime between :priximateTime-offset and :proximateTime+offset;
		List<Schedule> schInTimeRangeList = schArrServ.getScheduleByArriveStationTimeRangeFilterPastFilterDiscountAdvancePurchaseLimit(onStationId, pxt , Long.valueOf(120),discountType);// default set range to two hours
		// step2 select schSeg.schedule  from ScheduleSegment  schSeg where schSeg.schedule in :schList and schSeg.endStation.stationId=offStationId
		// check on and off station
		List<RailRoute> railRouteList = rrsServ.findScheduleByStartStationEndStation(onStationId, offStationId);
		List<Schedule> resSch = schInTimeRangeList.stream().filter((Schedule sch)->{
			for(RailRoute rr: railRouteList) {
				if( rr.getRailRouteId()== sch.getRailRoute().getRailRouteId()) {
					if( schdServ.conainDiscountType(sch.getScheduleId(), discountType))
						return true;
				}
			}
			return false;
		}).collect( Collectors.toList() );
		// step3 getAll DiscountType of schedule in scheduleList 
		// pack search schedule on ScheduleResultDto
		List<ScheduleResultDto> searchRes = new ArrayList<ScheduleResultDto>();
		resSch.stream().forEach((sch)->{
			RailRouteSegment rrs = rrsServ.findByRailRouteIdStartStationEndStation( sch.getRailRoute().getRailRouteId(),
					onStationId, offStationId).get(0);
			searchRes.add( new ScheduleResultDto( sch.getScheduleId(), onSt, offSt 
					, schArrServ.findByScheduleIdStationId(sch.getScheduleId(), onStationId).getArriveTime()
					, schArrServ.findByScheduleIdStationId( sch.getScheduleId(), offStationId).getArriveTime()
					,  rrs.getRailRouteSegmentDurationMinute(), rrs.getRailRouteSegmentTicketPrice() 
					, schdServ.getScheduleAllDiscountType(sch.getScheduleId())
					));
		});
		return searchRes;
		
	}
	
	@PostMapping("/requestBusinessBook")
	public @ResponseBody BookingBuinessSeatResponseDto requestBusinessBook(@RequestBody BookingBuinessSeatDto bbsDto){
//		bbsDto.scheduleId
		Schedule sch = schServ.findById(bbsDto.scheduleId);
		RailRouteStopStation rrss1 = rrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), bbsDto.startStation.getStationId()).get(0);
		RailRouteStopStation rrss2 = rrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), bbsDto.endStation.getStationId()).get(0);
		
		Integer stStSeq = rrss1.getRailRouteStopStationSequence();
		Integer edStSeq = rrss2.getRailRouteStopStationSequence();
		if(stStSeq >= edStSeq) {
			return new BookingBuinessSeatResponseDto();
		}
		Long mask = 0L;
		mask |= (1L << edStSeq)-1;
		mask >>= stStSeq;
		mask <<= stStSeq;
		List<ScheduleDetail> schdList = schdServ.getScheduleDiscountRange(sch.getScheduleId(), bbsDto.ticketDiscountName);
		ScheduleDetail schd = schdServ.getScheduleDiscountRange(sch.getScheduleId(), bbsDto.ticketDiscountName).get(0); 
		List<ScheduleSeatStatus> schssList =schssServ.findByScheduleSeatRange(sch, schd.getSeatRangeStart(), schd.getSeatRangeEnd()	);
		BookingBuinessSeatResponseDto res = new BookingBuinessSeatResponseDto();
		res.seatList= schssList.stream().map((a)-> a.getSeat()).toList();
		res.bookedSeatIdList = new ArrayList<Integer>();
		for( ScheduleSeatStatus schss : schssList) {
			if((schss.getScheduleStatus() & mask) >0) {
				res.bookedSeatIdList.add(schss.getSchedule().getScheduleId());
			}
		}
		res.startStation= rrss1.getStopStation();
		res.endStation = rrss2.getStopStation();
		return res;
	}
	
}


