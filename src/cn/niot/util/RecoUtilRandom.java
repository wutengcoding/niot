package cn.niot.util;
/**
 * 
 * @author sq
 * @date 2014-08-28
 *
 */

public class RecoUtilRandom {
	public static final String SELECT_TYPEANDRULES = "select * from iotidcode";
	public static final String SELECT_TYPETONAME="select * from typetotablename";
	
	//added by sq,lly wb
	//2014-08-28
	public static final String COUNT_EANUPC="select count(*)length from EANUPC";
	public static final String SELECT_RANDOMINDEX_EANUPC="select * from EANUPC limit ?,1";
	public static final String SELECT_RANDOMINDEX_COUNTRYREGIONCODE = "select * from countryregioncode limit ?,1";
	public static final String COUNT_COUNTRYREGIONCODE="select count(*)length from countryregioncode";
	public static final String SELECT_RANDOMINDEX_PHONENUMBER = "select * from phonenumber limit ?,1";
	public static final String COUNT_PHONENUMBER="select count(*)length from phonenumber";
	
	
	//Table Name
	public static final String EANUPC_TABLE="EANUPC";
	public static final String DistrictNo_TABLE="districtno";
	public static final String AdminDivision_TABLE="admindivision";
	public static final String AdminDivision1_TABLE="admindivision1";
	public static final String AnimalDisease_TABLE="Animaldisease";
	public static final String GassCompany_TABLE="gasscompany";
	public static final String GainStoreHouse_TABLE="grainstorehouse";
	public static final String GeographicInfoCode_TABLE="GeographicInfoCode";
	public static final String WirtschaftsTypCode_TABLE="WirtschaftsTypCode";
	public static final String JadejewelryClass_TABLE="JadejewelryClass";
	public static final String GrainsIndex_TABLE="grainsindex";
	public static final String Port_TABLE="port";
	public static final String ForestTypes_TABLE="ForestTypes";
	public static final String VoltageClass_TABLE="voltageclass";
	public static final String NormalAndShortCycleSpeciality_TABLE="normalandshortcyclespeciality";
	public static final String GrainAdministrative_TABLE="grainsadministrative";
	public static final String HarmfulFactor_TABL="HarmfulFactor";
	public static final String TabaccoStandardPart_TABLE="tabaccostandardpart";
	public static final String HighwayTransportation4c6_TABLE="highwaytransportation4c6";
	public static final String DZClassify_TABLE="dzclassify";
	public static final String GainsDiseases_TABLE="grainsdiseases";
	public static final String TabaccoMachineProduct_TABLE="tabaccomachineproduct";
	public static final String TabaccoMachineProducer_TABLE="tabaccomachineproducer";
	public static final String goalsocialeconomic_TABLE="goalsocialeconomic";
	public static final String BusinessAdminis_TABLE="admindivision";
	public static final String JadejewelryMaterialclassif_TABLE="JadejewelryMaterialclassif";
	public static final String ConstructionProducts_TABLE="constructionproducts";
	public static final String HighwayMaintenance4_TABLE="highwaymaintenance4";
	public static final String PowerMaterials53_TABLE="powermaterials53";
	public static final String PowerMaterials54_TABLE="powermaterials54";
	public static final String PowerMaterials51_TABLE="powermaterials51";
	public static final String PowerMaterials52_TABLE="powermaterials52";
	public static final String PowerMaterials50_TABLE="powermaterials50";
	public static final String PowerMaterials49_TABLE="powermaterials49";
	public static final String TreeDiseaseCode_TABLE="TreeDiseaseCode";
	public static final String RailwayStationCode_TABLE="RailwayStationCode";
	public static final String DZClassify710_TABLE="dzclassify710";
	public static final String FoodAccount_TABLE="foodaccount";
	public static final String GeneralManufacturingProcess_TABLE="generalmanufacturingprocess";
	public static final String Machinery9_TABLE="machinery9";
	public static final String tabaccoC_TABLE="tabaccoC";
	public static final String CoastalAdminAreaId_TABLE="CoastalAdminAreaId";
	public static final String HighwayTransportation_TABLE="highwaytransportation";
	public static final String HighwayTransportation4b9_TABLE="highwaytransportation4b9";
	public static final String HighwayTransportation4c3_TABLE="highwaytransportation4c3";
	public static final String PowerMaterials46_TABLE="powermaterials46";
	public static final String PowerMaterials45_TABLE="powermaterials45";
	public static final String PowerMaterials44_TABLE="powermaterials44";
	public static final String MaintenanceSystemPTwo_TABLE="maintenancesystemptwo";
	public static final String HighwayDatabase71_TABLE="highwaydatabase71";
	public static final String ElectricPowerGeography_TABLE="electricpowergeography";
	public static final String GrainEstablishment_TABLE="grainestablishment";
	public static final String HighwayMaintenance3_TABLE="highwaymaintenance3";
	public static final String Machinery2_TABLE="machinery2";
	public static final String FoodTrade_TABLE="foodtradestatistics";
	public static final String GainsEquipment_TABLE="grainsequipment";
	public static final String RoadTransportation21_TABLE="roadtransportation21";
	public static final String InfectiousDiseases_TABLE="Infectiousdiseases";
	public static final String RoadTransportation50_TABLE="roadtransportation50";
	public static final String RoadTransportation53_TABLE="roadtransportation53";
	public static final String Wasteproducts_TABLE="Wasteproducts";
	public static final String HighwayDatabase17_TABLE="highwaydatabase17";
	public static final String ModeofProduction_TABLE="modeofproduction";
	public static final String TerminationofPregnancy_TABLE="terminationofpregnancy";
	public static final String TobaccoLeafForm_TABLE="tobaccoleafform";
	public static final String HighwayDatabase46_TABLE="highwaydatabase46";
	public static final String HealthSupervisionObject_TABLE="healthsupervisionobject";
	public static final String DileveryPlace_TABLE="deliveryplace";
	public static final String InformationSafe_TABLE="InformationSafe";
	public static final String ClassificationOfCivilAviation_TABLE="classificationofcivilaviation";
	public static final String ChildrenExcrement_TABLE="childrenexcrement";
	
	public static final String measureunit_TABLE="measureunit";
	
	
	
	//Others
	//public static final char ChineseBox1[]={'4','5','6','7','8','9'};
	//public static final char ChineseBox2[]={'e','f','E','F'};
	//public static final char ChineseBox3[]={'0','1','2','3','4','5','6','7','8','9','a','A'};
	//public static final char ChineseBox4[]={'0','1','2','3','4','5'};
	
	//liuliying
	public static final String vehiclenowj_TABLE="vehiclenowj";
	public static final String vehiclenoarmy_TABLE="vehiclenoarmy";
	public static final String machinery6_TABLE="machinery6";
	public static final String vehiclenonormal_TABLE="vehiclenonormal";
	public static final String tcmdisease_TABLE="tcmdisease";
	public static final String tcmfeature_TABLE="tcmfeature";
	public static final String grainsprocess_TABLE="grainsprocess";
	public static final String CarProductCompnent_TABLE="CarProductCompnent";
	public static final String PortTariff9_TABLE="porttariff9";
	public static final String PortTariff25_TABLE="porttariff25";
	public static final String clothesclass_TABLE="clothesclass";
	//public static final String CoastalAdminAreaId_TABLE="CoastalAdminAreaId";
	//public static final String goalsocialeconomic_TABLE="goalsocialeconomic";
	public static final String InternationalShipCode_TABLE="InternationalShipCode";
	public static final String funeralservice_TABLE="funeralservice";
	public static final String funeralfacilities_TABLE="funeralfacilities";
	public static final String funeralsupplies_TABLE="funeralsupplies";
	public static final String tabaccoelectriccomponent_TABLE="tabaccoelectriccomponent";
	public static final String tabaccomaterial_TABLE="tabaccomaterial";
	public static final String Naturaldisaster_TABLE="Naturaldisaster";
	public static final String securityaccounterments_TABLE="securityaccounterments";
	public static final String textilefabricnamecode_TABLE="textilefabricnamecode";
	public static final String propertiesmainmaterial_TABLE="propertiesmainmaterial";
	public static final String  propertiesmain_TABLE="propertiesmain";
	public static final String  propertiesfibercharacteristics_TABLE="propertiesfibercharacteristics";
	public static final String  propertiesmixed_TABLE="propertiesmixed";
	public static final String  propertiesfabric_TABLE="propertiesfabric";
	public static final String  propertiesdyeingandfinishing_TABLE="propertiesdyeingandfinishing";
	public static final String  machinery8_TABLE="machinery8";
	public static final String  machinery7_TABLE="machinery7";
	public static final String  medicalInstrument_TABLE="medicalInstrument";
	public static final String  grainstechnicaleconomy_TABLE="grainstechnicaleconomy";
	public static final String  GeographicalCode_TABLE="GeographicalCode";
	public static final String  meatandvegetable_TABLE="meatandvegetable";
	public static final String  grainequipment_TABLE="grainequipment";
	public static final String  machinery5_TABLE="machinery5";
	public static final String  provinceadmincode_TABLE="provinceadmincode";
	public static final String  grainsinformation_TABLE="grainsinformation";
	public static final String  NuclearelementNation_TABLE="NuclearelementNation";
	public static final String  Nuclearelements_TABLE="Nuclearelements";
	public static final String  machinery3_TABLE="machinery3";
	public static final String  machinery4_TABLE="machinery4";
	public static final String  electricpower_TABLE="electricpower";
	public static final String  workerhealthsupervision_TABLE="workerhealthsupervision";
	public static final String  PesticideFormulationCode_TABLE="PesticideFormulationCode";
	public static final String  officialposition_TABLE="officialposition";
	public static final String  roadtransportation22_TABLE="roadtransportation22";
	public static final String  specialvehicle_TABLE="specialvehicle";
	public static final String  roadtransportation41_TABLE="roadtransportation41";
	public static final String  electronicmap_TABLE="electronicmap";
	public static final String  roadtransportation32_TABLE="roadtransportation32";
	public static final String  communicationmediacode_TABLE="communicationmediacode";
	public static final String  untransportableproduct_TABLE="untransportableproduct";
	public static final String  barcodeforcommodity_TABLE="barcodeforcommodity";
	public static final int EXTERNALLENTH = 16;
	public static final int INTERNALLENTH = 8;
	//public static final String COUNT_COUNTRYREGIONCODE = "select count(*)length from countryregioncode";
	public static final String SELECT__RANDOMINDEX_COUNTRYREGIONCODE = "select * from countryregioncode limit ?,1";

	//wangbin
	public static final String ClothesName_TABLE="ClothesName";
	public static final String HydrologicData_TABLE="hydrologicdata";
	public static final String grainsSmartWMS_TABLE="grainsSmartWMS";
	public static final String machinery10_TABLE="machinery10";
	public static final String countryregioncode_TABLE="countryregioncode";
	public static final String geographicinformation_TABLE="geographicinformation";
	public static final String tobaccoorganization_TABLE="tobaccoorganization";
	public static final String passengerCarCode_TABLE="passengerCarCode";
	public static final String grainsqualitystandard_TABLE="grainsqualitystandard";
	public static final String measuringinstrument_TABLE="measuringinstrument";
	public static final String highwaydatabase47_TABLE="highwaydatabase47";
	public static final String TCLBatteryProduct_TABLE="TCLBatteryProduct";
	public static final String ProductCode_TABLE="ProductCode";
	public static final String civilaviation_TABLE="civilaviation";
	public static final String LogisticsInf_TABLE="LogisticsInf";
	public static final String waterwaytransportation_TABLE="waterwaytransportation";
	public static final String mountainrangeandpeakname_TABLE="mountainrangeandpeakname";
	public static final String tobaccoleafclass_TABLE="tobaccoleafclass";
	public static final String intellectualproperty_TABLE="intellectualproperty";
	public static final String highwaytransportation_TABLE="highwaytransportation";
	public static final String highwaytransportation4b1_TABLE="highwaytransportation4b1";
	public static final String roadtransportation5_TABLE="roadtransportation5";
	public static final String highwaytransportation4b7_TABLE="highwaytransportation4b7";
	public static final String highwaytransportation4b10_TABLE="highwaytransportation4b10";
	public static final String porttariff26_TABLE="porttariff26";
	public static final String porttariff3_TABLE="porttariff3";
	public static final String Logisticsoperation_TABLE="Logisticsoperation";
	public static final String physicalactivityfrequency_TABLE="physicalactivityfrequency";
	public static final String hygieneagencypersonnel_TABLE="hygieneagencypersonnel";
	public static final String tobaccomaterials_TABLE="tobaccomaterials";
	public static final String InternetWebService_TABLE="InternetWebService";
	public static final String roadtransportation64_TABLE="roadtransportation64";
	public static final String roadtransportation63_TABLE="roadtransportation63";
	public static final String drinkingclass_TABLE="drinkingclass";
	public static final String drinkingfrequency_TABLE="drinkingfrequency";
	public static final String grainconditiondetection_TABLE="grainconditiondetection";
	public static final String trafficinformationcollection_TABLE="trafficinformationcollection";
	public static final String electricpowerindustry_TABLE="electricpowerindustry";
	public static final String powergoodsp2_TABLE="powergoodsp2";
	public static final String TravleDocumentCode_TABLE="TravleDocumentCode";
	public static final String roadtransportation60_TABLE="roadtransportation60";
	public static final String FireInfomation_TABLE="FireInfomation";
	public static final String tobaccoleafcolor_TABLE="tobaccoleafcolor";
	public static final String grainsattribute_TABLE="grainsattribute";
	public static final String NavigationShip_TABLE="NavigationShip";
	public static final String TheCenteralPartyCommitte_TABLE="TheCenteralPartyCommitte";
	public static final String porttariff10_TABLE="porttariff10";
	public static final String powergrid_TABLE="powergrid";
	
	//added by WB on 0917
	public static final String pharmaceuticalmachinery_TABLE="pharmaceuticalmachinery";
	
	//读test中TableNametoTypeNametoId表
	public static final String SELECT_TableNametoTypeNametoId="select * from TableNametoTypeNametoId";
}
