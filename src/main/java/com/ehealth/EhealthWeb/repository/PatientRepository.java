package com.ehealth.EhealthWeb.repository;

import com.ehealth.EhealthWeb.model.PateientModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PatientRepository extends CrudRepository<PateientModel,Integer>
{

    @Query(value = "select * from patients",nativeQuery = true)
    public List<PateientModel>getPatientList();

    @Query(value = "select * from patients where patient_phone_number=?",nativeQuery = true)
    public  List<PateientModel>searchPatientByPhone(String phone);

    @Query(value = "select * from patients where patient_phone_number= :num and patient_name= :name",nativeQuery = true)
    public List<PateientModel>serchpatientByNameAndPhone(@Param("num")String number,@Param("name")String name);

    @Transactional
    @Modifying
    @Query(value = "delete from patients where patient_id = :patientId",nativeQuery = true)
    public  int deleteAPatient(@Param("patientId")int patientId);

    @Transactional
    @Modifying
    @Query(value = "update patients set patient_age = :patientAge,patient_name= :patientName,patient_phone_number= :patientPhone where patient_id= :patientId",nativeQuery = true)
    public int updatePatientDetails(@Param("patientId")int patientId,@Param("patientAge")String patientAge,@Param("patientName")String patientName,@Param("patientPhone")String patientPhone);
}
