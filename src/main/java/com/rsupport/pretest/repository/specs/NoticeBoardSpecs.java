package com.rsupport.pretest.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.rsupport.pretest.model.entity.NoticeBoardEntity;

public class NoticeBoardSpecs {
	public static Specification<NoticeBoardEntity> equalTo(String columnName, String value) {
        if(columnName == null || columnName.isEmpty() || value == null || value.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(columnName).as(String.class), value);
    }
}
