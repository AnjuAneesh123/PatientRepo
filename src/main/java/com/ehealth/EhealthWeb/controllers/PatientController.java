package com.ehealth.EhealthWeb.controllers;

import com.ehealth.EhealthWeb.model.PateientModel;
import com.ehealth.EhealthWeb.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController
{
    List<PateientModel> patientModelLists = new ArrayList<>();

    @Autowired //Dependency injection
    PatientRepository patientRepository;
    //Get method
     @GetMapping(path = "/")
    public  String addPatients()
    {
        return"Patient added successfully";
    }


    //Post method
    @PostMapping(path = "/view",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public  String viewPatients(@RequestBody PateientModel pateientModel)
    {
        System.out.println("Name :"+pateientModel.getPatientName());
        System.out.println("Age :"+pateientModel.getPatientAge());
        System.out.println("Phone number :"+pateientModel.getPatientPhoneNumber());

        return "Patient added successfully";

    }

    //Return JSON Array as response
    @PostMapping(path = "/viewPatients",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public  List<PateientModel> viewPatientsDat(@RequestBody PateientModel pateientModel)
    {
        System.out.println("Name :"+pateientModel.getPatientName());
        System.out.println("Age :"+pateientModel.getPatientAge());
        System.out.println("Phone number :"+pateientModel.getPatientPhoneNumber());

        List<PateientModel> pateientModelList = new ArrayList<>();
        pateientModelList.add(pateientModel);
        return pateientModelList;

    }

  //Usage of Hashmaps
    //to get response as { "status" : "success"}
    @PostMapping(path = "/addPatients",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public HashMap addPatientsData(@RequestBody PateientModel pateientModel)
    {
        System.out.println("Name :"+pateientModel.getPatientName());
        System.out.println("Age :"+pateientModel.getPatientAge());
        System.out.println("Phone number :"+pateientModel.getPatientPhoneNumber());

        List<PateientModel> pateientModelList = new ArrayList<>();
        pateientModelList.add(pateientModel);
        HashMap<String,Object> patientAddMap = new HashMap<>();
        patientAddMap.put("Status","success");
        patientAddMap.put("PatientRecord",pateientModel);//passing patient model

        return patientAddMap;

    }
//usage of Maps
    @PostMapping(path = "/addPatientsRecord",consumes = "application/json",produces = "application/json")
    @ResponseBody
    public Map<String, Object> addPatientsRecord(@RequestBody PateientModel pateientModel)
    {
        System.out.println("Name :"+pateientModel.getPatientName());
        System.out.println("Age :"+pateientModel.getPatientAge());
        System.out.println("Phone number :"+pateientModel.getPatientPhoneNumber());

        List<PateientModel> pateientModelList = new ArrayList<>();
        pateientModelList.add(pateientModel);
        patientModelLists.add(pateientModel);
        Map<String,Object> patientAddMap = new HashMap<>();//As the map is an interface, the object creation should be done with hashmap
        patientAddMap.put("Status","success");
        patientAddMap.put("PatientRecord",pateientModelList);//passing patient list
        patientRepository.save(pateientModel);

        return patientAddMap;

    }
    //To get Dynamically adding values using the previous method
    @GetMapping(path = "/viewData")
    public HashMap viewData()
    {
HashMap hashMap = new HashMap<>();
hashMap.put("status","success");
hashMap.put("PatientRecord",patientRepository.findAll());
return hashMap;


    }

    @PostMapping(path = "/searchPhone",consumes = "application/json",produces = "application/json")
    public  HashMap searchPhone(@RequestBody PateientModel requestNumber)
    {
        int found = 0;
        System.out.println("the phone number"+requestNumber.getPatientPhoneNumber());
        PateientModel pateientModel = new PateientModel();
        for (PateientModel model:patientModelLists)
        {
            System.out.println("model"+model.getPatientPhoneNumber());
            System.out.println("requested"+requestNumber.getPatientPhoneNumber());
            if (model.getPatientPhoneNumber().equals(requestNumber.getPatientPhoneNumber()))
            {
                found = 1;
                pateientModel = model;
                break;
            }
            else
            {

            }
        }
        HashMap<String,Object>hashMap = new HashMap<>();
        if (found == 1)
        {
            hashMap.put("status","success");
            hashMap.put("Patient",pateientModel);
        }
        else
        {
            hashMap.put("status","failed");

        }
        return  hashMap;


    }


    @PostMapping(path = "/deleteUser",consumes = "application/json",produces = "application/json")
    public  HashMap deletePhone(@RequestBody PateientModel requestNumber)
    {
        int found = 0;
        int index = 0;
        System.out.println("the phone number"+requestNumber.getPatientPhoneNumber());
        PateientModel pateientModel = new PateientModel();
        for (PateientModel model:patientModelLists)
        {
            System.out.println("model"+model.getPatientPhoneNumber());
            System.out.println("requested"+requestNumber.getPatientPhoneNumber());
            if (model.getPatientPhoneNumber().equals(requestNumber.getPatientPhoneNumber()))
            {
                found = 1;
                index = patientModelLists.indexOf(model);
                pateientModel = model;
                break;
            }
            else
            {

            }
        }
        HashMap<String,Object>hashMap = new HashMap<>();
        if (found == 1)
        {
            patientModelLists.remove(index);
            hashMap.put("status","Deleted Successfully");
        }
        else
        {
            hashMap.put("status","failed");

        }
        return  hashMap;


    }


    //Returning data as list: outpu look like [{values},{values}]

    @GetMapping(path = "/viewPatientlis")
    public List viewpatientList()
    {
        List<PateientModel>patientlist = (List<PateientModel>) patientRepository.findAll();
        return patientlist;


    }

    //Using Native Queries
    //select Query
    @GetMapping(path = "/viewPatientlist")
    public List viewPatientListUsingNativeQuery()
    {
        List<PateientModel>patientlist = patientRepository.getPatientList();
        return patientlist;


    }
    //Select Query using ?
    @PostMapping(path = "/searchPatientlistByPhone",consumes = "application/json",produces = "application/json")
    public List serachPatientListByPhone(@RequestBody PateientModel model)
    {
        List<PateientModel>patientlist = patientRepository.searchPatientByPhone(model.getPatientPhoneNumber());
        return patientlist;


    }

    //Search Query using  @Param annotation
    @PostMapping(path =  "/multiSearch",consumes = "application/json",produces = "application/json")
    public  HashMap searchUsingNameAndPhone(@RequestBody PateientModel model)
    {
        List<PateientModel> list = patientRepository.serchpatientByNameAndPhone(model.getPatientPhoneNumber(),model.getPatientName());

       HashMap<String, Object> map = new HashMap<>();
        if(list.isEmpty())
        {
            map.put("Status","Not Found");
        }
        else
        {
            map.put("Status","Found");
            map.put("Data",list);

        }
        return  map;
    }

    //Delete Query
    @DeleteMapping(path = "/deletePatient",consumes = "application/json",produces = "application/json")
    public  HashMap deletePatient(@RequestBody PateientModel model)
    {
       int value = patientRepository.deleteAPatient(model.getPatientId());
        System.out.println(value);
        HashMap<String, String>map = new HashMap<>();

        if (value == 1)
{
    map.put("Status","Deleted successfully");

}
        else {
            map.put("Status","Could'nt Delete");

        }

       return map;

    }

    @PutMapping(path = "/updatePatient",consumes = "application/json",produces = "application/json")
    public  HashMap updatePatient(@RequestBody PateientModel model)
    {
       int value = patientRepository.updatePatientDetails(model.getPatientId(), model.getPatientAge(), model.getPatientName(),model.getPatientPhoneNumber());
        HashMap<String,String> map = new HashMap<>();
        if (value == 1)
        {
            map.put("Status","Updated Successfully");
        }
        else
        {
            map.put("Status","Coundnt update");

        }
        return  map;
    }
    @RequestMapping(path = "",method = RequestMethod.POST)
    public  String deletePatients()
    {
        return "";
    }

}
