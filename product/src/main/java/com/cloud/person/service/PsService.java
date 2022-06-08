package com.cloud.person.service;

import com.cloud.person.dto.PersonDto;
import com.cloud.person.dto.PersonVo;
import com.cloud.shop.dto.Page;
import org.elasticsearch.action.DocWriteResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/5/28 12:00
 * @mark PsService
 */
public interface PsService {

    Object listPs(Page page) throws IOException;

    List<PersonDto> findPerson(PersonVo personDto, Page page) throws IOException;

    DocWriteResponse.Result save(PersonDto personDto) throws IOException;

}
