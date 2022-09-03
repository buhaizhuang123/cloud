package com.cloud.person.dao;

import com.cloud.person.dto.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author haizhuangbu
 * @date 2022/9/1 11:08
 * @mark PersonMapper
 */
public interface PersonMapper {

    @Insert("insert into person (id,name,date) values (null,#{name},#{date})")
    Integer savePerson(Person person);

    @Select("select * from person where id = #{id}")
    Person getPerson(Person person);

}
