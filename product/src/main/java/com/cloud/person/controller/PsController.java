package com.cloud.person.controller;

import com.cloud.kafka.event.MessageEvent;
import com.cloud.person.dto.PersonDto;
import com.cloud.person.dto.PersonVo;
import com.cloud.person.service.PsService;
import com.cloud.shop.dto.Page;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

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


    private Logger logger = LoggerFactory.getLogger(PsController.class);

    @RequestMapping("list")
    public Object listPerson(Page page) throws IOException {
        return psService.listPs(page);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<PersonDto> findPersons(@RequestBody PersonVo personDto,Page page) throws IOException {
        List<PersonDto> person = psService.findPerson(personDto, page);
        return person;
    }

    @RequestMapping("save")
    public DocWriteResponse.Result save(PersonDto personDto) throws IOException {
        DocWriteResponse.Result save = psService.save(personDto);
        return save;
    }

    @RequestMapping("del")
    public DeleteResponse del(@RequestParam("id")String id){
        logger.info("======== 删除 ============");
        return psService.delToPerson(id);
    }


}
