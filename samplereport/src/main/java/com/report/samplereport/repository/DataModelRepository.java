package com.report.samplereport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.samplereport.models.DataModel;

@Repository
public interface DataModelRepository extends JpaRepository<DataModel,Long>{

}
