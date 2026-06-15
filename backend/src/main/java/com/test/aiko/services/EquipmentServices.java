package com.test.aiko.services;

import com.test.aiko.dtos.request.EquipmentDto;
import com.test.aiko.dtos.request.PostLicenseDto;
import com.test.aiko.dtos.response.*;
import com.test.aiko.exceptions.*;
import com.test.aiko.models.Equipment;
import com.test.aiko.models.EquipmentModel;
import com.test.aiko.models.User;
import com.test.aiko.repositories.EquipmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipmentServices {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EquipmentModelServices equipmentModelServices;
    @Autowired
    private UserServices userServices;
    @Autowired
    private EquipmentModelStateHourlyEarningsServices equipmentModelStateHourlyEarningsServices;

    public void equipmentResponse(Equipment equipment){
        if(equipment.getUser() != null){
            equipment.setEquipmentOwnerResponseDto(new EquipmentOwnerResponseDto(
                    equipment.getUser().getId(),
                    equipment.getUser().getEmail(),
                    equipment.getUser().getUsername()
            ));
        }
        equipment.setEquipmentModelResponseDto(new EquipmentModelResponseDto(
                equipment.getEquipmentModel().getId(),
                equipment.getEquipmentModel().getName()
        ));
        List<EquipmentPositionHistoryResponseDto> equipmentPositionHistoryResponseDtoList = new ArrayList<>();
        equipment.getEquipmentPositionHistoryList()
                .forEach(equipmentPositionHistory -> equipmentPositionHistoryResponseDtoList.add(
                        new EquipmentPositionHistoryResponseDto(
                                equipmentPositionHistory.getId(),
                                equipmentPositionHistory.getLocalDate(),
                                equipmentPositionHistory.getLat(),
                                equipmentPositionHistory.getLon()
                        )
                ));
        List<EquipmentStateHistoryResponseDto> equipmentStateHistoryResponseDtoList = new ArrayList<>();
        equipment.getEquipmentStateHistoryList()
                .forEach(equipmentStateHistory -> equipmentStateHistoryResponseDtoList.add(
                        new EquipmentStateHistoryResponseDto(
                                equipmentStateHistory.getId(),
                                new EquipmentStateResponseDto(
                                        equipmentStateHistory.getEquipmentState().getId(),
                                        equipmentStateHistory.getEquipmentState().getName(),
                                        equipmentStateHistory.getEquipmentState().getColor()
                                ),
                                equipmentStateHistory.getLocalDate()
                        )
                ));
        equipment.setEquipmentPositionHistoryResponseDtoList(equipmentPositionHistoryResponseDtoList);
        equipment.setEquipmentStateHistoryResponseDtoList(equipmentStateHistoryResponseDtoList);
    }

    public List<Equipment> findAllEquipments(){
        List<Equipment> equipments = equipmentRepository.findAll();
        equipments.forEach(this::equipmentResponse);
        return equipments;
    }

    public Equipment findEquipmentById(UUID id){
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if(equipment.isEmpty()){
            throw new ComponentNotFoundException("equipment not found");
        }
        equipmentResponse(equipment.get());
        return equipment.get();
    }

    public Equipment postEquipment(EquipmentDto equipmentDto){
        EquipmentModel equipmentModel = equipmentModelServices.findEquipmentModelById(equipmentDto.model_id());

        var equipment = new Equipment();
        BeanUtils.copyProperties(equipmentDto,equipment);
        equipment.setEquipmentModel(equipmentModel);
        equipmentRepository.save(equipment);
        equipmentResponse(equipment);
        return equipment;
    }

    public Equipment putEquipment(UUID id,EquipmentDto equipmentDto){
        Equipment equipment = findEquipmentById(id);
        EquipmentModel equipmentModel = equipmentModelServices.findEquipmentModelById(id);
        BeanUtils.copyProperties(equipmentDto,equipment);
        equipment.setEquipmentModel(equipmentModel);
        equipmentRepository.save(equipment);
        equipmentResponse(equipment);
        return equipment;
    }

    public String deleteEquipment(UUID id){
        Equipment equipment = findEquipmentById(id);
        equipmentRepository.delete(equipment);
        return "deleted";
    }

    public Equipment postUserOwner(PostLicenseDto postLicenseDto){
        Optional<Equipment> optEquipment = equipmentRepository.findEquipmentByLicense(postLicenseDto.license());
        if(optEquipment.isEmpty()){
            throw new InvalidLicenseException();
        }
        Equipment equipment = optEquipment.get();
        User user = userServices.findUser(postLicenseDto.email());
        if(equipment.getUser() != null){
            throw new LincenseAlreadyActivatedException();
        }
        equipment.setUser(user);
        equipmentRepository.save(equipment);
        equipmentResponse(equipment);
        return equipment;
    }

    public String removeEquipmentOwner(PostLicenseDto postLicenseDto){
        Optional<Equipment> optEquipment = equipmentRepository.findEquipmentByLicense(postLicenseDto.license());
        if(optEquipment.isEmpty()){
            throw new InvalidLicenseException();
        }
        Equipment equipment = optEquipment.get();
        User user = userServices.findUser(postLicenseDto.email());
        if(!equipment.getUser().equals(user)){
            throw new UserNotFoundException();
        }
        equipment.setUser(null);
        equipmentRepository.save(equipment);
        return "deleted";
    }

    public UserResponseDto getEquipmentsByUser(String email){
        User user = userServices.findUser(email);
        user.getEquipmentList().forEach(this::equipmentResponse);
        UserResponseDto response = new UserResponseDto(
                user.getRealUsername(),
                user.getEmail(),
                user.getEquipmentList()
        );
        return response;
    }

    public List<ProfitsTimelineResponseDto> getProfitsTimelineByEquipment(String email){
        List<ProfitsTimelineResponseDto> profitsTimelineList = new ArrayList<>();
        User user = userServices.findUser(email);

        user.getEquipmentList().forEach(equipment -> profitsTimelineList.add(new ProfitsTimelineResponseDto(
                new EquipmentResponseDto(equipment.getId(),equipment.getName(),equipment.getLicense()),
                equipmentModelStateHourlyEarningsServices.calculateProfits(equipment.getEquipmentModel(),
                        equipment.getEquipmentStateHistoryList()))));

        return profitsTimelineList;
    }
}
