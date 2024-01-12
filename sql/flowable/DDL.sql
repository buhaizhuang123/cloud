-- 创建英文四级表
create table Eng_Four_Lever
(
    chinese_name    varchar(64),
    english_name    varchar(64),
    phonetic_symbol varchar(36)
);

-- 英文非理解单词
create table ENGLISH_NO_CARE_HIS
(
    english_name varchar(64) primary key
)