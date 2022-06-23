package com.cloud.person.controller;

import com.cloud.kafka.event.MessageEvent;
import com.cloud.person.dto.PersonDto;
import com.cloud.person.dto.PersonVo;
import com.cloud.person.service.PsService;
import com.cloud.shop.dto.Page;
import org.elasticsearch.action.DocWriteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/5/28 11:58
 * @mark PsController
 */
@RestController
@RequestMapping("/ps")
public class PsController {

    @Autowired
    private PsService psService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("list")
    public Object listPerson(Page page) throws IOException {
        return psService.listPs(page);
    }

    @RequestMapping("search")
    public List<PersonDto> findPersons(PersonVo personDto, Page page) throws IOException {
        List<PersonDto> person = psService.findPerson(personDto, page);
        return person;
    }

    @RequestMapping("save")
    public DocWriteResponse.Result save(PersonDto personDto) throws IOException {
        DocWriteResponse.Result save = psService.save(personDto);
        return save;
    }

    @RequestMapping("sendEvent")
    public void sendEvent(String message) {
        MessageEvent messageEvent = new MessageEvent(message, applicationContext);
        applicationContext.publishEvent(messageEvent);
    }

}
