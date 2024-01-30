-- 创建商品列表
create table shop
(
    shop_name        varchar(64) not null comment '商品名称',
    background_color varchar(64) comment '背景颜色'
)

-- 创建商铺
create table shop_store
(
    user_id     varchar(36) comment '用户id',
    shop_id     varchar(36) comment '商品id',
    shop_num    int comment '商品数量',
    order_mark  varchar(255) comment '商品备注',
    shop_sts    varchar(24) comment '商品状态 0 预购,1 完成',
    create_time datetime,
    modify_time datetime
);
-- 添加图片地址
alter table shop add img_url varchar(4000);

create table s_timer (
                         id varchar(24),
                         name varchar(36)
)
