package com.cricteam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cricteam.models.PlayerTypes;
import com.cricteam.models.PredefinedQuery;
import com.cricteam.models.TeamCircleStatusType;
import com.cricteam.service.PredefinedService;
@ComponentScan(basePackages = "com.cricteam")

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	PredefinedService preDefinedService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		if(preDefinedService.findTeamCircleStatusData()>0){
			
		}else{
			List<TeamCircleStatusType> list= new ArrayList<>();
			for (int i = 0; i < TeamCircleStatusEnum.values().length; i++) {
				TeamCircleStatusType circleStatusType= new TeamCircleStatusType(i, TeamCircleStatusEnum.values()[i].toString());
			list.add(circleStatusType);
			}
			preDefinedService.saveTeamCircleStatus(list);
		}
if(preDefinedService.findPlayerTypeData()>0){
			
		}else{
			List<PlayerTypes> list= new ArrayList<>();
			for (int i = 0; i < PlayerTypeEnum.values().length; i++) {
				PlayerTypes circleStatusType= new PlayerTypes(i+1, PlayerTypeEnum.values()[i].toString());
			list.add(circleStatusType);
			}
			preDefinedService.savePlayerType(list);
		}
	}

	

}
