insert into product(name, status) values ('커피원두파는집', 'ACTIVE');

insert into plan(product_id, plan_name, plan_description, plan_price) VALUES (1, '아라비카', '좋은 향미가 풍부하고 카페인 함량이 적어 전 세계 커피 산출량의 약 70%', 7000);

insert into option_group_spec (name, plan_id, exclusive, basic) VALUES ('기본', 1, 1, 1);
insert into option_spec(option_group_spec_id, name, price) VALUES (1, '100g', 7000);
insert into option_spec(option_group_spec_id, name, price) VALUES (1, '200g', 13000);
insert into option_spec(option_group_spec_id, name, price) VALUES (1, '300g', 19000);

insert into option_group_spec (name, plan_id, exclusive, basic) VALUES ('원두', 1, 0, 0);
insert into option_spec(option_group_spec_id, name, price) VALUES (2, '과테말라', 0);
insert into option_spec(option_group_spec_id, name, price) VALUES (2, '탄자니아 킬리만자로', 1000);
insert into option_spec(option_group_spec_id, name, price) VALUES (2, '브라질 산토', 1500);
insert into option_spec(option_group_spec_id, name, price) VALUES (2, '케냐', 2000);

insert into option_group_spec (name, plan_id, exclusive, basic) VALUES ('로스팅 추가 선택', 1, 0, 0);
insert into option_spec(option_group_spec_id, name, price) VALUES (3, '귤', 1000);
insert into option_spec(option_group_spec_id, name, price) VALUES (3, '아몬드', 1500);
insert into option_spec(option_group_spec_id, name, price) VALUES (3, '딸기', 1500);
insert into option_spec(option_group_spec_id, name, price) VALUES (3, '청포도', 2000);