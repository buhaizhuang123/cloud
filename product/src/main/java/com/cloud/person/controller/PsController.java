package com.cloud.person.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.person.dto.PersonDto;
import com.cloud.person.dto.PersonVo;
import com.cloud.person.service.PsService;
import com.cloud.person.service.S1Service;
import com.cloud.shop.dto.Page;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    private S1Service s1Service;

    private Logger logger = LoggerFactory.getLogger(PsController.class);

    @RequestMapping("list")
    @SentinelResource(value = "listPsMine", blockHandler = "listAllFallBack", blockHandlerClass = PsController.class)
    public Object listPerson(Page page) throws IOException {
        return psService.listPs(page);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<PersonDto> findPersons(@RequestBody PersonVo personDto, Page page) throws IOException {
        List<PersonDto> person = psService.findPerson(personDto, page);
        return person;
    }

    @RequestMapping("save")
    public DocWriteResponse.Result save(PersonDto personDto) throws IOException {
        DocWriteResponse.Result save = psService.save(personDto);
        return save;
    }

    @RequestMapping("del")
    public DeleteResponse del(@RequestParam("id") String id) {
        logger.info("======== 删除 ============");
        return psService.delToPerson(id);
    }

    @RequestMapping("/list1")
    public List<Long> getList1(@RequestBody List<Long> list) {
        return list;
    }


    @RequestMapping("/list2")
    public List<Long> getList2(List<Long> list) {
        return list;
    }

    @RequestMapping("show")
    public void showExcel(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=123.xls");
        // 预览
//        response.setContentType("application/octet-stream");
        // 下载
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        ServletOutputStream outputStream = response.getOutputStream();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet morn = hssfWorkbook.createSheet("morn");
        HSSFRow row = morn.createRow(0);
        for (int i = 0; i < 10; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(i);
        }
        hssfWorkbook.write(outputStream);
        outputStream.close();
    }


    public static Object listAllFallBack(BlockException e) {
        return null;
    }


    @RequestMapping("/insert")
    public void insert(String data) {
        s1Service.insert(data);
    }

}
