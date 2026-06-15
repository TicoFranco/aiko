package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentModelStateHourlyEarningsDto;
import com.test.aiko.dtos.response.EquipmentModelResponseDto;
import com.test.aiko.dtos.response.EquipmentStateResponseDto;
import com.test.aiko.dtos.response.ProfitsResponseDto;
import com.test.aiko.exceptions.ComponentNotFoundException;
import com.test.aiko.exceptions.InvalidDataException;
import com.test.aiko.models.EquipmentModel;
import com.test.aiko.models.EquipmentModelStateHourlyEarnings;
import com.test.aiko.models.EquipmentState;
import com.test.aiko.models.EquipmentStateHistory;
import com.test.aiko.repositories.EquipmentModelRepository;
import com.test.aiko.repositories.EquipmentModelStateHourlyEarningsRepository;
import com.test.aiko.repositories.EquipmentStateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EquipmentModelStateHourlyEarningsServices {

    @Autowired
    private EquipmentModelStateHourlyEarningsRepository equipmentModelStateHourlyEarningRepository;
    @Autowired
    private EquipmentStateRepository equipmentStateRepository;
    @Autowired
    private EquipmentModelRepository equipmentModelRepository;

    public void equipmentModelStateHourlyEarningsResponse(EquipmentModelStateHourlyEarnings equipmentModelStateHourlyEarnings){
        equipmentModelStateHourlyEarnings.setEquipmentModelResponseDto(new EquipmentModelResponseDto(
                equipmentModelStateHourlyEarnings.getEquipmentModel().getId(),
                equipmentModelStateHourlyEarnings.getEquipmentModel().getName()
        ));
        equipmentModelStateHourlyEarnings.setEquipmentStateResponseDto(new EquipmentStateResponseDto(
                equipmentModelStateHourlyEarnings.getEquipmentState().getId(),
                equipmentModelStateHourlyEarnings.getEquipmentState().getName(),
                equipmentModelStateHourlyEarnings.getEquipmentState().getColor()
        ));
    }

    public List<EquipmentModelStateHourlyEarnings> findAll(){
        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList = equipmentModelStateHourlyEarningRepository.findAll();
        equipmentModelStateHourlyEarningsList.forEach(this::equipmentModelStateHourlyEarningsResponse);
        return equipmentModelStateHourlyEarningsList;
    }

    public EquipmentModelStateHourlyEarnings findById(UUID id){
        Optional<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningRepository.findById(id);
        if(equipmentModelStateHourlyEarnings.isEmpty()){
            throw new ComponentNotFoundException("equipment model state hourly earnings not found");
        }
        equipmentModelStateHourlyEarningsResponse(equipmentModelStateHourlyEarnings.get());
        return equipmentModelStateHourlyEarnings.get();
    }

    public EquipmentModelStateHourlyEarnings postEquipmentModelStateHourlyEarnings(EquipmentModelStateHourlyEarningsDto equipmentModelStateHourlyEarningsDto){
        Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(equipmentModelStateHourlyEarningsDto.equipment_model_id());
        if(equipmentModel.isEmpty()){
            throw new ComponentNotFoundException("equipment model not found");
        }

        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(equipmentModelStateHourlyEarningsDto.equipment_state_id());
        if(equipmentState.isEmpty()){
            throw new ComponentNotFoundException("equipment state not found");
        }

        var equipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarnings();
        BeanUtils.copyProperties(equipmentModelStateHourlyEarningsDto,equipmentModelStateHourlyEarnings);
        equipmentModelStateHourlyEarnings.setEquipmentModel(equipmentModel.get());
        equipmentModelStateHourlyEarnings.setEquipmentState(equipmentState.get());
        equipmentModelStateHourlyEarningRepository.save(equipmentModelStateHourlyEarnings);
        equipmentModelStateHourlyEarningsResponse(equipmentModelStateHourlyEarnings);
        return equipmentModelStateHourlyEarnings;
    }

    public EquipmentModelStateHourlyEarnings putEquipmentModelStateHourlyEarnings(UUID id,EquipmentModelStateHourlyEarningsDto equipmentModelStateHourlyEarningsDto){
        EquipmentModelStateHourlyEarnings findEquipmentModelStateHourlyEarnings = findById(id);

        Optional<EquipmentModel> equipmentModel = equipmentModelRepository.findById(equipmentModelStateHourlyEarningsDto.equipment_model_id());
        if(equipmentModel.isEmpty()){
            throw new ComponentNotFoundException("equipment model not found");
        }

        Optional<EquipmentState> equipmentState = equipmentStateRepository.findById(equipmentModelStateHourlyEarningsDto.equipment_state_id());
        if(equipmentState.isEmpty()){
            throw new ComponentNotFoundException("equipment state not found");
        }

        var equipmentModelStateHourlyEarnings = new EquipmentModelStateHourlyEarnings();
        BeanUtils.copyProperties(equipmentModelStateHourlyEarningsDto,equipmentModelStateHourlyEarnings);
        equipmentModelStateHourlyEarnings.setEquipmentModel(equipmentModel.get());
        equipmentModelStateHourlyEarnings.setEquipmentState(equipmentState.get());
        equipmentModelStateHourlyEarningRepository.save(equipmentModelStateHourlyEarnings);
        equipmentModelStateHourlyEarningsResponse(equipmentModelStateHourlyEarnings);
        return equipmentModelStateHourlyEarnings;
    }

    public String deleteEquipmentModelStateHourlyEarnings(UUID id){
        Optional<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarnings = equipmentModelStateHourlyEarningRepository.findById(id);
        if(equipmentModelStateHourlyEarnings.isEmpty()){
            throw new ComponentNotFoundException("equipment model state hourly earnings not found");
        }

        equipmentModelStateHourlyEarningRepository.delete(equipmentModelStateHourlyEarnings.get());
        return "deleted";
    }

    public List<ProfitsResponseDto> calculateProfits(EquipmentModel equipmentModel,List<EquipmentStateHistory> equipmentStateHistoryList){
        List<ProfitsResponseDto> profitsTimeline = new ArrayList<>();
        EquipmentStateHistory previous = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        equipmentStateHistoryList.sort(Comparator.comparing(EquipmentStateHistory::getLocalDate));

        List<EquipmentModelStateHourlyEarnings> equipmentModelStateHourlyEarningsList =
                equipmentModelStateHourlyEarningRepository.getEquipmentModelStateHourlyEarningsByEquipmentModel(equipmentModel.getId());

        Map<EquipmentState,EquipmentModelStateHourlyEarnings> earningsMap = equipmentModelStateHourlyEarningsList.stream()
                        .collect(Collectors.toMap(EquipmentModelStateHourlyEarnings::getEquipmentState, Function.identity()));


        for(EquipmentStateHistory current : equipmentStateHistoryList){
            if(previous != null){
                EquipmentModelStateHourlyEarnings earning = earningsMap.get(previous.getEquipmentState());
                if(earning == null){
                    throw new InvalidDataException("The equipment state does not have a set earning margin");
                }
                long hours = ChronoUnit.HOURS.between(previous.getLocalDate(),current.getLocalDate());
                int profit = Math.toIntExact(earning.getValue() * hours);
                profitsTimeline.add(new ProfitsResponseDto(current.getLocalDate().format(formatter),profit));
            }
            previous = current;
        }

        Map<String, Integer> dailyEarnings = profitsTimeline.stream().collect(
                Collectors.groupingBy(ProfitsResponseDto::date,LinkedHashMap::new,
                                Collectors.summingInt(ProfitsResponseDto::value)));

        List<ProfitsResponseDto> equipmentEarnings = dailyEarnings.entrySet().stream()
                .map(entry -> new ProfitsResponseDto(entry.getKey(), entry.getValue())).toList();

        return equipmentEarnings;
    }
}
