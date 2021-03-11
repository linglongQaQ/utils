package com.demo.mapper;

import com.demo.sysfile.CsvFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;


/**
 * @Description:
 * @Author: wangyilong
 * @Date: 2021/2/23 11:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TableMapperTest {

    @Autowired
    private TableMapper tableMapper;

    @Test
    public void testCheckDB() {
        String dbName = "abc";
        String tableName = "test01";
        Map<String, String> dbList = tableMapper.checkDBExist(dbName);
        if (CollectionUtils.isEmpty(dbList)) {
            tableMapper.createDB(dbName);
        }
        tableMapper.changeDB(dbName);
        Map<String, String> tableList = tableMapper.checkTableExist(tableName);
        if (CollectionUtils.isEmpty(tableList)){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("tableName", tableName);
            List<String> list = new ArrayList<>();
            list.add("风场编号");
            list.add("时间");
            list.add("age");
            list.add("sex");
            for (String s : list) {
                if (s.equals("风场编号")) {
                    s = s.replace("风场编号","farm_code");
                    map.put(s, "VARCHAR(20)");
                }else if (s.equals("时间")){
                    s = s.replace("时间","datetime");
                    map.put(s, "datetime");
                }else if (s.equals("sex")){
                    map.put(s, "VARCHAR(20)");
                }else {
                    map.put(s, "FLOAT");
                }
            }
            tableMapper.createNewTable(map);
        }
        System.out.println("hahahhah");

    }

    @Test
    public void testCheck() {
        Map<String, String> list = tableMapper.checkTableExist("test111");

        if (!CollectionUtils.isEmpty(list)) {
            System.out.println("--------hh");
        }
        Map<String, String> map = new HashMap<>();
        map.put("tableName", "test111");
        String field[] = {"姓名", "日期", "age", "sex"};
        for (String s : field) {
            if (s.equals("姓名")) {
                s = "name";
                map.put(s, "VARCHAR(20)");
            } else if (s.equals("日期")) {
                s = "date";
                map.put(s, "datetime");
            }
            map.put(s, "VARCHAR(20)");
        }
        tableMapper.createNewTable(map);
    }

    @Test
    public void testChangeDB(){
        tableMapper.changeDB("test");
        System.out.println("---------");
    }

    @Test
    public void testLoad(){
        String path = "E:\\doc\\data\\st.csv";
        String dbName = "abc";
        String tableName = "test01";
        tableMapper.loadData(path,dbName,tableName);
    }


    /**
     * 测试将文件导入IB库
     */

    @Test
    public void testIB() {
        String dbName = "db_30001_2021";
        String tableName = "tb_30001001_2021101";
        Map<String, String> dbList = tableMapper.checkDBExist(dbName);
        if (CollectionUtils.isEmpty(dbList)) {
            tableMapper.createDB(dbName);
        }
        tableMapper.changeDB(dbName);
        Map<String, String> tableList = tableMapper.checkTableExist(tableName);
        if (CollectionUtils.isEmpty(tableList)){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("tableName", tableName);
            String str = "风机编号,时间,giWindTurbineOperationMode,giWindTurbineErrorMode,giWindTurbineYawMode,grWindTurbineEnergyOutputAll,grWindTurbineEnergyOutputDay,grWindTurbineRunTimeDay,grWindTurbineAvailableTimeDay,grWindTurbineErrorTimeDay,grPitchAngle1A,grPitchAngle1B,grPitchAngle2A,grPitchAngle2B,grPitchAngle3A,grPitchAngle3B,grPitchAngleSetPoint1,grPitchAngleSetPoint2,grPitchAngleSetPoint3,grPitchSpeed1,grPitchSpeed2,grPitchSpeed3,grPitchMotorCurrent1,grPitchMotorCurrent2,grPitchMotorCurrent3,grPitchBackupPowerCabinetTemperture1,grPitchBackupPowerCabinetTemperture2,grPitchBackupPowerCabinetTemperture3,grPitchDriverCabinetTemperture1,grPitchDriverCabinetTemperture2,grPitchDriverCabinetTemperture3,grPitchMotorTemperture1,grPitchMotorTemperture2,grPitchMotorTemperture3,grHubTemperture,grPitch1BackUpPowerCapacity,grPitch2BackUpPowerCapacity,grPitch3BackUpPowerCapacity,gbPitch1HeartBeatNormal,gbPitch2HeartBeatNormal,gbPitch3HeartBeatNormal,gbPitch1LimitSwitchActive91,gbPitch2LimitSwitchActive91,gbPitch3LimitSwitchActive91,gbPitch1LimitSwitchActive95,gbPitch2LimitSwitchActive95,gbPitch3LimitSwitchActive95,gbPitchBypassCommand,gbPitchEFCCommand,gbPitchSafeSignal,gbPitchSafetyPosition,gb5DSwitchStateA1,gb86DSwitchStateA1,gb5DSwitchStateA2,iPitch1StateWord,iPitch2StateWord,iPitch3StateWord,grPitch1DCLinkVoltage,grPitch2DCLinkVoltage,grPitch3DCLinkVoltage,grPitch1PercentageOfTorque,grPitch2PercentageOfTorque,grPitch3PercentageOfTorque,grPitch1DriveHeatSinkTemperature,grPitch2DriveHeatSinkTemperature,grPitch3DriveHeatSinkTemperature,grPitchPercentageOfBatteryLifeRemainingHealthy1,grPitchPercentageOfBatteryLifeRemainingHealthy2,grPitchPercentageOfBatteryLifeRemainingHealthy3,grGearBoxOilTemperture,grGearboxMainBearingTemperature,grGearboxHubSideBearingTemperature,grGearboxGeneratorSideBearingTemperature,grGearboxOilTemperatureAtMainPump,grGearboxOilTemperatureAtBypassPump,grGearboxOilTemperatureAtReturnOil,grGearboxOilLevel,grGearboxOilPressureA1,grGearboxOilPressureA2,grGearboxOilPressureA3,grGearboxOilpressureA4,grGearboxEnergyTankPressure,gbGearboxPumpHighSpeedOn1,gbGearboxPumpHighSpeedOn2,gbGearboxPumpLowerSpeedOn1,gbGearboxPumpLowerSpeedOn2,gbGearboxBypasspumpOn,gbGearboxOilHeaterOn,gbGearboxOilFanOn,gbGearboxOilVolumeValveOffFeedback,gbGearboxOilVolumeValveOnFeedback,gbGearboxOilBypassvalveOffFeedback,gbGearboxOilBypassValveOnFeedback,gbGearboxOilLevelOK,gbGearboxMainFilterError1,gbGearboxMainFilterError2,gbGearboxBypassPumpFilterError,gbGearboxFlowControlPressureDifference,grGeneratorWindingTemperature1,grGeneratorWindingTemperature2,grGeneratorWindingTemperature3,grGeneratorWindingTemperature4,grGeneratorWindingTemperature5,grGeneratorWindingTemperature6,grGeneratorGearboxSideBearingTemperature,grGeneratorNacelleSideBearingTemperature,grGeneratorWaterPressureIn,grGeneratorWaterPressureOut,grGeneratorWaterTemperatureIn,grGeneratorWaterTemperatureOut,grGeneratorSpeed1,grGeneratorSpeed2,grGeneratorSpacetemperature,gbGeneratorWaterPumpOn,gbGeneratorWaterFanOn,gbGeneratorWaterHeaterOn,gb86DSwitchStateA2,gb5DSwitchStateA3,gb86DSwitchStateA3,gbTransformerCoolingSystem3WayMixingValveOn,gbTransformerCoolingSystem3WayMixingValveOff,gbTransformerWaterPumpMotor,gbTransformerFan1,gbTransformerFan2,gbTransformerFan,gbTowerFireAlarmSystemEarlyWarning,gbTowerFireAlarmSystemMainWarning,gbTowerFireAlarmSystemErrorSignal,gbNacelleFireAlarmSystemMainWarning,grGridVoltageL1,grGridVoltageL2,grGridVoltageL3,grGridFrequence,grGridCurrentL1,grGridCurrentL2,grGridCurrentL3,grGridReactivePower,grConverterGeneratorSpeed,grConverterGeneratorSideVoltage,grConverterGridVoltage,grConverterPowerModuleTemperture,grConverterCoolingWaterTemperture,grConverterCP,gbConverterReadyOn,gbConverterReadyRun,gbLVRTActived,grDCVoltage,grConverterCoolingWaterTempertureOut,grConverterCoolingWaterPumpPressureIn,grConverterCoolingWaterPumpPressureOut,grConverterControlCabinetTemperature,grConverterReactiveSetpointCommand,grGeneratorSpeedSetpointAtRotorPositioningMode,grConverterReactivePower,grConverterGridFrequency,giConverterLineSideMCBActionTimes,giConverterMSUCurrent,gbConverterResetCommand,gbConverterEnforceResetCommand,gbRotorPositioningMode,gbConverterCloseBreakerCommand,gbConverterReadyReceriveTorqueCommand,gbConverterTripped,gbConverterCoolingWaterThreeWayValveCommandOn,gbConverterCoolingWaterThreeWayValveCommandOff,gbConverterCoolingWaterThreeWayValveFeedbackOn,gbConverterCoolingWaterThreeWayValveFeedbackOff,gbBreakerCabinet1Feedback,gbBreakerCabinet2Feedback,grHydraulicSystemPressure,grHydraulicYawBrakePressure,grHydraulicRotorBrakePressure,gbHydraulicPumpOn,gbHydraulicOilLevelOK,gbHydraulicMainFiltererror,gbHydraulicYawFilterError,gbHydraulicOilReturnFilterError,grHydraulicRotorLockPressure,grHydraulicOilTemperature,grHydraulicOilLevel,gbConverterAlarm,gbConverterReadyForMachineSideBreaker,gbConverterToggleBitNormal,gbConverterPositionModeReady,gbConverterFRTOutOfVoltageCurve,gbConverterCabinetFan1On,gbConverterCabinetFan2On,gbConverterCabinetFan3On,gbConverterUPSOn,gbConverterLineSideMCBOn,gbConverterCoolantHeatingOn,gbConverterCabinetHeatingOn,gbConverterCoolantPumpOn,gbConverterCoolantFan1On,gbConverterCoolantFan2On,gbConverterCoolantFan3On,grNacelleVibrationX1,grNacelleVibrationY1,grNacelleVibrationX2,grNacelleVibrationY2,grNacelleCabinetTemperture,grNacelleTemperture,grTowerBaseTemperature,grTowerBaseCabinetTemperture,grTransformerTemperture,grWindSpeed,grWindDirctionToNorth,grWindDirction,grWindSpeed1,grWindSpeed2,grWindDirction1,grWindDirction2,grYawTwistPosition,grYawSpeed,gbYawCW,gbYawCCW,gbYawMotorElectricBrakeOpen,grConverterUnitTorqueSetpoint,grConverterUnitOutputTorque,grConverterUnitFrequencySetpoint,grConverterUnitOperatingFrequency,grRotorSpeed1,grRotorSpeed2,gbRotorLock1Actived,gbRotorLock2Actived,gbRotorLock1Deactived,gbRotorLock2Deactived,gbRotorLockActiveCommand,gbRotorLockDeactiveCommand,grRotorPosition,grAirPressure,grOutdoorTemperature,grAirDensity,grConverterTorque,grPitchPositionSetpoint,grGeneratorSpeedSetpoint,grReactPowerSetPoint,grCosSetPoint,grVoltageSetPoint,giPLCTimeSettingYear,giPLCTimeSettingMonth,giPLCTimeSettingDay,giPLCTimeSettingHour,giPLCTimeSettingMin,giPLCTimeSettings,giPLCTimeSettingms,giPLCTimeSettingWeek,gbWindturbineService,gbRemoteYawEnable,gbRemoteYawCW,gbRemoteYawCCW,gbRemoteStopWindTuebine,gbRemoteStartWindTuebine,gbRemoteResetWindTuebine,gbCosSetActive,gbReactPowerSetActive,gbVoltageSetActive,gbPLCTimeSetActive,gbRemoteTyphoonModeManual,grTyphoonToNorthDirection,grEneryConsumeDaily,grWindTurbineErrorTime,grWindTurbineServiceTime,grGridErrorTime,grWindTurbineStandByTimeDaily,grWindTurbineOerationTimeDaily,grPowerProductionTimeDaily,grWindTurbineErrorTimeDaily,grWindTurbineServiceTimeDaily,grWindOKtimeDaily,grGridErrorTimeDaily,grAvailableTimeDaily,giFirstErrorNumber,giGearboxMainPumpOnTimes1,giGearboxMainPumpOnTimes2,giGearboxBypassPunpOnTimes,giGearboxOilHeaterOnTimes,giGearboxOilFanOnTimes,giGearboxOilCoolerBypassValveTimes,giGearboxOilVolumeValveTimes,giGeneratorWaterPumpOnTimes,giGeneratorWaterFanOnTimes,giGeneratorWaterHeaterOnTimes,giHydraulicPumpOnTimes,giYawTimes,giNacelleFanOnTimes,grRotorLockValveActiveTime,grRotorBrakeValveActiveTime,grGeneratorFloatingVoltage,grNacelleCabinetUPSBatteryWorkTime,grTowerCabinetUPSBatteryWorkTime,grPitch1FollowingDifference,grPitch2FollowingDifference,grPitch3FollowingDifference,grGearboxMainPumpFilter1ErrorTemperature,grGearboxMainPumpFilter2ErrorTemperature,grGearboxBypassPumpFilterErrorTemperature,grYawBrakeWearOperationTime,grHydraulicPumpEstablishPressureTime,giYawBrakeOpenTotalValveActiveTimes,giYawBrakeOpenHalfValveActiveTimes,giRotorLockValveActiveTimes,giRotorBrakeValveActiveTimes,giConverterGeneratorSideMCBActionTimes,giYawLubricationActionTimes,giPitchBearingLubricationActionTimes,giPitchBearingToothLubricationActionTimes,grYawLubricationOperationTime,grPitchBearingLubricationOperationTime,grPitchBearingToothLubricationOperationTime,giConverterWaterCoolingFanMotor1ActionTimes,giConverterWaterCoolingFanMotor2ActionTimes,giConverterWaterCoolingFanMotor3ActionTimes,giConverterWaterPumpMotorActionTimes,grNacellePositionToNorth,gbTurbineInSpecting,gbTurbinePowerLimited,gbBoxTransformerGraveGasTripSign,gbBoxTransformerLightGasTripSign,gbBoxTransformerLowVoltageSwitchTripSign,gbBoxTransformerLowVoltageSwitchErrorSign,gbBoxTransformerHighVoltageSwitchTripSign,gbBoxTransformerFuseTripSign,gbBoxTransformerEarthingKnifeSwitchTripSign,gbBoxTransformerControlSwitchTripSign,gbBoxTransformerDoorOpen,grCorrectedWindSpeed,giGridError,gbBoxTransformerRemoteSeparateHighVoltage,gbBoxTransformerRemoteConnectHighVoltage,gbBoxTransformerRemoteSeparateLowVoltage,gbBoxTransformerRemoteConnectLowVoltage,grBoxTransformerTemperature,grWindTurbineAvailableRateAll,grWindTurbineAvailableRateDay,giErrorCode1,giErrorCode2,giErrorCode3,giErrorCode4,giErrorCode5,giErrorCode6,giErrorCode7,giErrorCode8,giErrorCode9,giErrorCode10,giErrorCode11,giErrorCode12,giErrorCode13,giErrorCode14,giErrorCode15,giErrorCode16,giErrorCode17,giErrorCode18,giErrorCode19,giErrorCode20,giErrorCode21,giErrorCode22,giErrorCode23,giErrorCode24,giErrorCode25,giErrorCode26,giErrorCode27,giErrorCode28,giErrorCode29,giErrorCode30,grGearboxBypassPumpOutPressure,grGearboxOilFanOutletTemperature,grGearboxP2Temperature,grGearboxOilPumpOutletPressure1,grGearboxOilPumpOutletPressure2,grConverterPowerReductionTorqueSetpoint,giRemoteStopTurbineFeedback,giPowerReductionType,grActivePowerSetpointFeedback,gwRemotePowerLimitEnable,gbStopTurbine,gbStartTurbine,grReactivePowerSetpoint,grActivePowerSetpoint,grPitchAxisBackUpVoltage1,grPitchAxisBackUpVoltage2,grPitchAxisBackUpVoltage3,grPitchChargerOutputVoltage1,grPitchChargerOutputVoltage2,grPitchChargerOutputVoltage3,grPitchChargerOutputCurrent1,grPitchChargerOutputCurrent2,grPitchChargerOutputCurrent3,grPitchCapacitorTemperature1,grPitchCapacitorTemperature2,grPitchCapacitorTemperature3,grPitchCapacityValue1,grPitchCapacityValue2,grPitchCapacityValue3,grPitchCoolerTemperature1,grPitchCoolerTemperature2,grPitchCoolerTemperature3,grPitchAxis1HubSpeed,grPitchAxis2HubSpeed,grPitchAxis3HubSpeed,grWindTurbineRunTimeAll,grTransformerCoolingSystemWaterInPressure,grTransformerCoolingSystemWaterOutPressure,grTowerTransformerCoolingSystemWaterInTemperature,grTowerTransformerCoolingSystemWaterOutTemperature,gdiRotorTurns,grTransformerUPhaseLowVoltageTemperature,grTransformerVPhaseLowVoltageTemperature,grTransformerWPhaseLowVoltageTemperature,gbNacelleFireAlarmSystemEarlyWarning,gbNacelleFireAlarmSystemErrorSignal,grGridActivePower,grWindTurbineStandByTime,grWindTurbineOerationTime,grPowerProductionTime,grWindOKtime,gdiEneryOutput,grAvailableTime,grEneryConsume,grConverterActivePower,grConveterIGBTActivePower,grTorqueSetpoint,grTorqueDemand,grPowerDemand,grCoverterPower,grTheoreticalActivePowerOutPut,grWindTurbineEneryConsumeDay,grEneryOutputDaily,grGearboxMainPumpOnTime1,grGearboxMainPumpOnTime2,grGearboxBypasspumpOnTime,grGearboxOilHeaterOnTime,grGearboxOilFanOnTime,grGeneratorWaterPumpOnTime,grGeneratorWaterFanOnTime,grGeneratorWaterHeaterOnTime,grHydraulicPumpOnTime,grYawTime,grNacelleFanOnTime,grYawBrakeOpenTotalValveActiveTime,grYawBrakeOpenHalfValveActiveTime,grPitch1MotorOpetationTime,grPitch2MotorOpetationTime,grPitch3MotorOpetationTime,grConverterCabinetFan1OperationTime,grConverterCabinetFan2OperationTime,grConverterCabinetFan3OperationTime,grConverterWaterCoolingFanMotor1OperationTime,grConverterWaterCoolingFanMotor2OperationTime,grConverterWaterCoolingFanMotor3OperationTime,grConverterWaterPumpMotorOperationTime,grEnerySelfConsume,grEnerySelfConsumeDaily,gbHydraulicOilTemperatureHigh,gbGearboxOilVolumeControlSwitch,grNacelleRecycleWindInTemperature1,grNacelleRecycleWindInTemperature2,grNacelleRecycleWindOutTemperature1,grNacelleRecycleWindOutTemperature2,grUpsMainInputAVoltage,grUpsMainInputBVoltage,grUpsMainInputCVoltage,grUpsOutputAVoltage,grUpsOutputBVoltage,grUpsOutputCVoltage,grUpsOutputACurrent,grUpsOutputBCurrent,grUpsOutputCCurrent,grUpsOutputFrequency,grUpsMainInputAToBVoltage,grUpsMainInputBToCVoltage,grUpsMainInputCToAVoltage,grUpsMainInputACurrent,grUpsMainInputBCurrent,grUpsMainInputCCurrent,grUpsMainInputFrequency,grUpsInputPowerFactor,grUpsBypassInputAVoltage,grUpsBypassInputBVoltage,grUpsBypassInputCVoltage,grUpsBypassInputACurrent,grUpsBypassInputBCurrent,grUpsBypassInputCCurrent,grUpsBypassInputFrequency,grUpsBatteryTemperature,grUpsOutputAPowerFactor,grUpsOutputBPowerFactor,grUpsOutputCPowerFactor,grUpsOutputAActivePower,grUpsOutputBActivePower,grUpsOutputCActivePower,grPowerChangeRate,grMinPowerSetpoint";
            String[] split = str.split(",");
            for (String s : split) {
                if (s.equals("风机编号")) {
                    s = s.replace("风机编号","turbine_code");
                    map.put(s, "VARCHAR(20)");
                }else if (s.equals("时间")){
                    s = s.replace("时间","real_time");
                    map.put(s, "datetime");
                }else {
                    map.put(s, "FLOAT");
                }
            }
            tableMapper.createNewTable(map);
        }
        System.out.println("创建表成功");
        String path = "E:/doc/30000001_20201122.csv";
        tableMapper.loadData(path,dbName,tableName);
        System.out.println("导入数据成功");

    }


    /**
     * 测试入库完整流程
     */
    @Test
    public void test() throws IOException {
        String path = "E:/doc/30000001_20201125.csv";
        String fileName = CsvFileUtil.getFileName(path);
        String substring = fileName.substring(0, 5);
        String dbName = "db_"+substring;
        String tableName = "tb_"+fileName;
        Map<String, String> dbList = tableMapper.checkDBExist(dbName);
        if (CollectionUtils.isEmpty(dbList)) {
            tableMapper.createDB(dbName);
            System.out.println("创建库成功！");
        }
        tableMapper.changeDB(dbName);
        Map<String, String> tableList = tableMapper.checkTableExist(tableName);
        if (CollectionUtils.isEmpty(tableList)){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("tableName", tableName);
//            String str = "风机编号,时间,giWindTurbineOperationMode,giWindTurbineErrorMode,giWindTurbineYawMode,grWindTurbineEnergyOutputAll,grWindTurbineEnergyOutputDay,grWindTurbineRunTimeDay,grWindTurbineAvailableTimeDay,grWindTurbineErrorTimeDay,grPitchAngle1A,grPitchAngle1B,grPitchAngle2A,grPitchAngle2B,grPitchAngle3A,grPitchAngle3B,grPitchAngleSetPoint1,grPitchAngleSetPoint2,grPitchAngleSetPoint3,grPitchSpeed1,grPitchSpeed2,grPitchSpeed3,grPitchMotorCurrent1,grPitchMotorCurrent2,grPitchMotorCurrent3,grPitchBackupPowerCabinetTemperture1,grPitchBackupPowerCabinetTemperture2,grPitchBackupPowerCabinetTemperture3,grPitchDriverCabinetTemperture1,grPitchDriverCabinetTemperture2,grPitchDriverCabinetTemperture3,grPitchMotorTemperture1,grPitchMotorTemperture2,grPitchMotorTemperture3,grHubTemperture,grPitch1BackUpPowerCapacity,grPitch2BackUpPowerCapacity,grPitch3BackUpPowerCapacity,gbPitch1HeartBeatNormal,gbPitch2HeartBeatNormal,gbPitch3HeartBeatNormal,gbPitch1LimitSwitchActive91,gbPitch2LimitSwitchActive91,gbPitch3LimitSwitchActive91,gbPitch1LimitSwitchActive95,gbPitch2LimitSwitchActive95,gbPitch3LimitSwitchActive95,gbPitchBypassCommand,gbPitchEFCCommand,gbPitchSafeSignal,gbPitchSafetyPosition,gb5DSwitchStateA1,gb86DSwitchStateA1,gb5DSwitchStateA2,iPitch1StateWord,iPitch2StateWord,iPitch3StateWord,grPitch1DCLinkVoltage,grPitch2DCLinkVoltage,grPitch3DCLinkVoltage,grPitch1PercentageOfTorque,grPitch2PercentageOfTorque,grPitch3PercentageOfTorque,grPitch1DriveHeatSinkTemperature,grPitch2DriveHeatSinkTemperature,grPitch3DriveHeatSinkTemperature,grPitchPercentageOfBatteryLifeRemainingHealthy1,grPitchPercentageOfBatteryLifeRemainingHealthy2,grPitchPercentageOfBatteryLifeRemainingHealthy3,grGearBoxOilTemperture,grGearboxMainBearingTemperature,grGearboxHubSideBearingTemperature,grGearboxGeneratorSideBearingTemperature,grGearboxOilTemperatureAtMainPump,grGearboxOilTemperatureAtBypassPump,grGearboxOilTemperatureAtReturnOil,grGearboxOilLevel,grGearboxOilPressureA1,grGearboxOilPressureA2,grGearboxOilPressureA3,grGearboxOilpressureA4,grGearboxEnergyTankPressure,gbGearboxPumpHighSpeedOn1,gbGearboxPumpHighSpeedOn2,gbGearboxPumpLowerSpeedOn1,gbGearboxPumpLowerSpeedOn2,gbGearboxBypasspumpOn,gbGearboxOilHeaterOn,gbGearboxOilFanOn,gbGearboxOilVolumeValveOffFeedback,gbGearboxOilVolumeValveOnFeedback,gbGearboxOilBypassvalveOffFeedback,gbGearboxOilBypassValveOnFeedback,gbGearboxOilLevelOK,gbGearboxMainFilterError1,gbGearboxMainFilterError2,gbGearboxBypassPumpFilterError,gbGearboxFlowControlPressureDifference,grGeneratorWindingTemperature1,grGeneratorWindingTemperature2,grGeneratorWindingTemperature3,grGeneratorWindingTemperature4,grGeneratorWindingTemperature5,grGeneratorWindingTemperature6,grGeneratorGearboxSideBearingTemperature,grGeneratorNacelleSideBearingTemperature,grGeneratorWaterPressureIn,grGeneratorWaterPressureOut,grGeneratorWaterTemperatureIn,grGeneratorWaterTemperatureOut,grGeneratorSpeed1,grGeneratorSpeed2,grGeneratorSpacetemperature,gbGeneratorWaterPumpOn,gbGeneratorWaterFanOn,gbGeneratorWaterHeaterOn,gb86DSwitchStateA2,gb5DSwitchStateA3,gb86DSwitchStateA3,gbTransformerCoolingSystem3WayMixingValveOn,gbTransformerCoolingSystem3WayMixingValveOff,gbTransformerWaterPumpMotor,gbTransformerFan1,gbTransformerFan2,gbTransformerFan,gbTowerFireAlarmSystemEarlyWarning,gbTowerFireAlarmSystemMainWarning,gbTowerFireAlarmSystemErrorSignal,gbNacelleFireAlarmSystemMainWarning,grGridVoltageL1,grGridVoltageL2,grGridVoltageL3,grGridFrequence,grGridCurrentL1,grGridCurrentL2,grGridCurrentL3,grGridReactivePower,grConverterGeneratorSpeed,grConverterGeneratorSideVoltage,grConverterGridVoltage,grConverterPowerModuleTemperture,grConverterCoolingWaterTemperture,grConverterCP,gbConverterReadyOn,gbConverterReadyRun,gbLVRTActived,grDCVoltage,grConverterCoolingWaterTempertureOut,grConverterCoolingWaterPumpPressureIn,grConverterCoolingWaterPumpPressureOut,grConverterControlCabinetTemperature,grConverterReactiveSetpointCommand,grGeneratorSpeedSetpointAtRotorPositioningMode,grConverterReactivePower,grConverterGridFrequency,giConverterLineSideMCBActionTimes,giConverterMSUCurrent,gbConverterResetCommand,gbConverterEnforceResetCommand,gbRotorPositioningMode,gbConverterCloseBreakerCommand,gbConverterReadyReceriveTorqueCommand,gbConverterTripped,gbConverterCoolingWaterThreeWayValveCommandOn,gbConverterCoolingWaterThreeWayValveCommandOff,gbConverterCoolingWaterThreeWayValveFeedbackOn,gbConverterCoolingWaterThreeWayValveFeedbackOff,gbBreakerCabinet1Feedback,gbBreakerCabinet2Feedback,grHydraulicSystemPressure,grHydraulicYawBrakePressure,grHydraulicRotorBrakePressure,gbHydraulicPumpOn,gbHydraulicOilLevelOK,gbHydraulicMainFiltererror,gbHydraulicYawFilterError,gbHydraulicOilReturnFilterError,grHydraulicRotorLockPressure,grHydraulicOilTemperature,grHydraulicOilLevel,gbConverterAlarm,gbConverterReadyForMachineSideBreaker,gbConverterToggleBitNormal,gbConverterPositionModeReady,gbConverterFRTOutOfVoltageCurve,gbConverterCabinetFan1On,gbConverterCabinetFan2On,gbConverterCabinetFan3On,gbConverterUPSOn,gbConverterLineSideMCBOn,gbConverterCoolantHeatingOn,gbConverterCabinetHeatingOn,gbConverterCoolantPumpOn,gbConverterCoolantFan1On,gbConverterCoolantFan2On,gbConverterCoolantFan3On,grNacelleVibrationX1,grNacelleVibrationY1,grNacelleVibrationX2,grNacelleVibrationY2,grNacelleCabinetTemperture,grNacelleTemperture,grTowerBaseTemperature,grTowerBaseCabinetTemperture,grTransformerTemperture,grWindSpeed,grWindDirctionToNorth,grWindDirction,grWindSpeed1,grWindSpeed2,grWindDirction1,grWindDirction2,grYawTwistPosition,grYawSpeed,gbYawCW,gbYawCCW,gbYawMotorElectricBrakeOpen,grConverterUnitTorqueSetpoint,grConverterUnitOutputTorque,grConverterUnitFrequencySetpoint,grConverterUnitOperatingFrequency,grRotorSpeed1,grRotorSpeed2,gbRotorLock1Actived,gbRotorLock2Actived,gbRotorLock1Deactived,gbRotorLock2Deactived,gbRotorLockActiveCommand,gbRotorLockDeactiveCommand,grRotorPosition,grAirPressure,grOutdoorTemperature,grAirDensity,grConverterTorque,grPitchPositionSetpoint,grGeneratorSpeedSetpoint,grReactPowerSetPoint,grCosSetPoint,grVoltageSetPoint,giPLCTimeSettingYear,giPLCTimeSettingMonth,giPLCTimeSettingDay,giPLCTimeSettingHour,giPLCTimeSettingMin,giPLCTimeSettings,giPLCTimeSettingms,giPLCTimeSettingWeek,gbWindturbineService,gbRemoteYawEnable,gbRemoteYawCW,gbRemoteYawCCW,gbRemoteStopWindTuebine,gbRemoteStartWindTuebine,gbRemoteResetWindTuebine,gbCosSetActive,gbReactPowerSetActive,gbVoltageSetActive,gbPLCTimeSetActive,gbRemoteTyphoonModeManual,grTyphoonToNorthDirection,grEneryConsumeDaily,grWindTurbineErrorTime,grWindTurbineServiceTime,grGridErrorTime,grWindTurbineStandByTimeDaily,grWindTurbineOerationTimeDaily,grPowerProductionTimeDaily,grWindTurbineErrorTimeDaily,grWindTurbineServiceTimeDaily,grWindOKtimeDaily,grGridErrorTimeDaily,grAvailableTimeDaily,giFirstErrorNumber,giGearboxMainPumpOnTimes1,giGearboxMainPumpOnTimes2,giGearboxBypassPunpOnTimes,giGearboxOilHeaterOnTimes,giGearboxOilFanOnTimes,giGearboxOilCoolerBypassValveTimes,giGearboxOilVolumeValveTimes,giGeneratorWaterPumpOnTimes,giGeneratorWaterFanOnTimes,giGeneratorWaterHeaterOnTimes,giHydraulicPumpOnTimes,giYawTimes,giNacelleFanOnTimes,grRotorLockValveActiveTime,grRotorBrakeValveActiveTime,grGeneratorFloatingVoltage,grNacelleCabinetUPSBatteryWorkTime,grTowerCabinetUPSBatteryWorkTime,grPitch1FollowingDifference,grPitch2FollowingDifference,grPitch3FollowingDifference,grGearboxMainPumpFilter1ErrorTemperature,grGearboxMainPumpFilter2ErrorTemperature,grGearboxBypassPumpFilterErrorTemperature,grYawBrakeWearOperationTime,grHydraulicPumpEstablishPressureTime,giYawBrakeOpenTotalValveActiveTimes,giYawBrakeOpenHalfValveActiveTimes,giRotorLockValveActiveTimes,giRotorBrakeValveActiveTimes,giConverterGeneratorSideMCBActionTimes,giYawLubricationActionTimes,giPitchBearingLubricationActionTimes,giPitchBearingToothLubricationActionTimes,grYawLubricationOperationTime,grPitchBearingLubricationOperationTime,grPitchBearingToothLubricationOperationTime,giConverterWaterCoolingFanMotor1ActionTimes,giConverterWaterCoolingFanMotor2ActionTimes,giConverterWaterCoolingFanMotor3ActionTimes,giConverterWaterPumpMotorActionTimes,grNacellePositionToNorth,gbTurbineInSpecting,gbTurbinePowerLimited,gbBoxTransformerGraveGasTripSign,gbBoxTransformerLightGasTripSign,gbBoxTransformerLowVoltageSwitchTripSign,gbBoxTransformerLowVoltageSwitchErrorSign,gbBoxTransformerHighVoltageSwitchTripSign,gbBoxTransformerFuseTripSign,gbBoxTransformerEarthingKnifeSwitchTripSign,gbBoxTransformerControlSwitchTripSign,gbBoxTransformerDoorOpen,grCorrectedWindSpeed,giGridError,gbBoxTransformerRemoteSeparateHighVoltage,gbBoxTransformerRemoteConnectHighVoltage,gbBoxTransformerRemoteSeparateLowVoltage,gbBoxTransformerRemoteConnectLowVoltage,grBoxTransformerTemperature,grWindTurbineAvailableRateAll,grWindTurbineAvailableRateDay,giErrorCode1,giErrorCode2,giErrorCode3,giErrorCode4,giErrorCode5,giErrorCode6,giErrorCode7,giErrorCode8,giErrorCode9,giErrorCode10,giErrorCode11,giErrorCode12,giErrorCode13,giErrorCode14,giErrorCode15,giErrorCode16,giErrorCode17,giErrorCode18,giErrorCode19,giErrorCode20,giErrorCode21,giErrorCode22,giErrorCode23,giErrorCode24,giErrorCode25,giErrorCode26,giErrorCode27,giErrorCode28,giErrorCode29,giErrorCode30,grGearboxBypassPumpOutPressure,grGearboxOilFanOutletTemperature,grGearboxP2Temperature,grGearboxOilPumpOutletPressure1,grGearboxOilPumpOutletPressure2,grConverterPowerReductionTorqueSetpoint,giRemoteStopTurbineFeedback,giPowerReductionType,grActivePowerSetpointFeedback,gwRemotePowerLimitEnable,gbStopTurbine,gbStartTurbine,grReactivePowerSetpoint,grActivePowerSetpoint,grPitchAxisBackUpVoltage1,grPitchAxisBackUpVoltage2,grPitchAxisBackUpVoltage3,grPitchChargerOutputVoltage1,grPitchChargerOutputVoltage2,grPitchChargerOutputVoltage3,grPitchChargerOutputCurrent1,grPitchChargerOutputCurrent2,grPitchChargerOutputCurrent3,grPitchCapacitorTemperature1,grPitchCapacitorTemperature2,grPitchCapacitorTemperature3,grPitchCapacityValue1,grPitchCapacityValue2,grPitchCapacityValue3,grPitchCoolerTemperature1,grPitchCoolerTemperature2,grPitchCoolerTemperature3,grPitchAxis1HubSpeed,grPitchAxis2HubSpeed,grPitchAxis3HubSpeed,grWindTurbineRunTimeAll,grTransformerCoolingSystemWaterInPressure,grTransformerCoolingSystemWaterOutPressure,grTowerTransformerCoolingSystemWaterInTemperature,grTowerTransformerCoolingSystemWaterOutTemperature,gdiRotorTurns,grTransformerUPhaseLowVoltageTemperature,grTransformerVPhaseLowVoltageTemperature,grTransformerWPhaseLowVoltageTemperature,gbNacelleFireAlarmSystemEarlyWarning,gbNacelleFireAlarmSystemErrorSignal,grGridActivePower,grWindTurbineStandByTime,grWindTurbineOerationTime,grPowerProductionTime,grWindOKtime,gdiEneryOutput,grAvailableTime,grEneryConsume,grConverterActivePower,grConveterIGBTActivePower,grTorqueSetpoint,grTorqueDemand,grPowerDemand,grCoverterPower,grTheoreticalActivePowerOutPut,grWindTurbineEneryConsumeDay,grEneryOutputDaily,grGearboxMainPumpOnTime1,grGearboxMainPumpOnTime2,grGearboxBypasspumpOnTime,grGearboxOilHeaterOnTime,grGearboxOilFanOnTime,grGeneratorWaterPumpOnTime,grGeneratorWaterFanOnTime,grGeneratorWaterHeaterOnTime,grHydraulicPumpOnTime,grYawTime,grNacelleFanOnTime,grYawBrakeOpenTotalValveActiveTime,grYawBrakeOpenHalfValveActiveTime,grPitch1MotorOpetationTime,grPitch2MotorOpetationTime,grPitch3MotorOpetationTime,grConverterCabinetFan1OperationTime,grConverterCabinetFan2OperationTime,grConverterCabinetFan3OperationTime,grConverterWaterCoolingFanMotor1OperationTime,grConverterWaterCoolingFanMotor2OperationTime,grConverterWaterCoolingFanMotor3OperationTime,grConverterWaterPumpMotorOperationTime,grEnerySelfConsume,grEnerySelfConsumeDaily,gbHydraulicOilTemperatureHigh,gbGearboxOilVolumeControlSwitch,grNacelleRecycleWindInTemperature1,grNacelleRecycleWindInTemperature2,grNacelleRecycleWindOutTemperature1,grNacelleRecycleWindOutTemperature2,grUpsMainInputAVoltage,grUpsMainInputBVoltage,grUpsMainInputCVoltage,grUpsOutputAVoltage,grUpsOutputBVoltage,grUpsOutputCVoltage,grUpsOutputACurrent,grUpsOutputBCurrent,grUpsOutputCCurrent,grUpsOutputFrequency,grUpsMainInputAToBVoltage,grUpsMainInputBToCVoltage,grUpsMainInputCToAVoltage,grUpsMainInputACurrent,grUpsMainInputBCurrent,grUpsMainInputCCurrent,grUpsMainInputFrequency,grUpsInputPowerFactor,grUpsBypassInputAVoltage,grUpsBypassInputBVoltage,grUpsBypassInputCVoltage,grUpsBypassInputACurrent,grUpsBypassInputBCurrent,grUpsBypassInputCCurrent,grUpsBypassInputFrequency,grUpsBatteryTemperature,grUpsOutputAPowerFactor,grUpsOutputBPowerFactor,grUpsOutputCPowerFactor,grUpsOutputAActivePower,grUpsOutputBActivePower,grUpsOutputCActivePower,grPowerChangeRate,grMinPowerSetpoint";
//            String[] split = str.split(",");
            String[] strings = CsvFileUtil.readCsvUtil(path);
            for (String s : strings) {
                if (s.equals("风机编号")) {
                    s = s.replace("风机编号","turbine_code");
                    map.put(s, "VARCHAR(20)");
                }else if (s.equals("时间")){
                    s = s.replace("时间","real_time");
                    map.put(s, "datetime");
                }else {
                    map.put(s, "FLOAT");
                }
            }
            tableMapper.createNewTable(map);
            System.out.println("创建表成功");
            CsvFileUtil.removeFirstLine(path);
        }
        tableMapper.loadData(path,dbName,tableName);
        System.out.println("导入数据成功");
        boolean b = CsvFileUtil.deletFile(path);
        if (b){
            System.out.println("文件删除成功!");
        }else {
            System.out.println("文件删除失败!");
        }

    }

}