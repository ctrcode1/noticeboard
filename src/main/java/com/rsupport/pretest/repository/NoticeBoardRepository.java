package com.rsupport.pretest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rsupport.pretest.model.entity.NoticeBoardEntity;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity, Integer>, JpaSpecificationExecutor<NoticeBoardEntity> {

}
