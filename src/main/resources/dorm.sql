-- 校区数据表
create table tb_school(
  school_ID int primary key auto_increment comment '校区号',
  school_name varchar(64) comment '校区名'
);

-- 楼区数据表
create table tb_dorm(
  dorm_ID int primary key auto_increment comment '楼区号',
  dorm_name varchar(16) comment '楼区名',
  school_ID int comment '校区号',
  constraint schoolID_FK foreign key (school_ID) references tb_school (school_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
--  宿舍基本信息表，外键是校区，楼区
create table tb_room(
  room_ID varchar(16) primary key comment '宿舍号',
  members varchar(64) comment '宿舍成员名字，以逗号分隔',
  `count` int comment '宿舍人数',
  dorm_ID int comment '楼区号',
  school_ID int comment '校区号',
  constraint room_schoolID_FK foreign key (school_ID) references tb_school (school_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint dormID_FK foreign key (dorm_ID) references tb_dorm (dorm_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 学院表
create table tb_college(
  college_ID int primary key auto_increment comment '学院号',
  college_name varchar(64) comment '学院名'
);
-- 班级表
create table tb_class(
  class_ID int primary key auto_increment comment '班级号',
  class_name varchar(16) comment '班级名',
  college_ID int comment '学院号',
  constraint collegeID_FK foreign key (college_ID) references tb_college (college_ID) ON DELETE CASCADE ON UPDATE CASCADE
);
-- #学生基本信息表
create table tb_stu(
  stu_ID bigint(20) primary key comment '学号',
  stu_name varchar(32) not null comment '学生姓名',
  age int comment '年龄',
  sex varchar(16) comment '性别',
  class_ID int comment '班级号',
  college_ID int comment '学院号',
  stu_phone varchar(32) comment '联系电话',
  address varchar(64) comment '家庭住址',
  room_ID varchar(16) comment '宿舍号',
  bed int comment '床位',
  checkIn datetime comment '入住时间',
  identity varchar(16) comment '身份',
  password varchar(32) comment '密码',
  constraint stu_roomID_FK foreign key (room_ID) references tb_room (room_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint stu_classID_FK foreign key (class_ID) references tb_class (class_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint stu_collegeID_FK foreign key (college_ID) references tb_college (college_ID) ON DELETE CASCADE ON UPDATE CASCADE
);


--  违纪数据表，外键是学生表的学号
create table tb_punish(
  id int primary key auto_increment comment '违纪号',
  stu_ID bigint(20) comment '学号',
  sign varchar(16) comment '标记类型，学生，宿舍或者宿管',
  type varchar(32) comment '违纪类型',
  comments text comment '违纪事件',
  createTime datetime comment '违纪时间',
  constraint punish_stuID_FK foreign key (stu_ID) references tb_stu (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 宿管信息表
create table tb_manager(
  manager_ID bigint(20) primary key comment '工号',
  manager_name varchar(32) not null comment '宿管姓名',
  age int comment '年龄',
  sex varchar(16) comment '性别',
  stu_phone varchar(32) comment '联系电话',
  address varchar(64) comment '家庭住址',
  dorm_ID int comment '管理楼区',
  school_ID int comment '所属校区',
  workTime datetime comment '入职时间',
  constraint manage_schoolID_FK foreign key (school_ID) references tb_school (school_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint manage_dormID_FK foreign key (dorm_ID) references tb_dorm (dorm_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 维修工人表
create table tb_worker(
  worker_ID bigint primary key comment '维修人工号',
  worker_name varchar(32) not null comment '工人姓名',
  age int comment '年龄',
  sex varchar(16) comment '性别',
  stu_phone varchar(32) comment '联系电话',
  address varchar(64) comment '家庭住址',
  workTime datetime comment '入职时间'
);

-- 工资表
create table tb_salary(
  id int primary key auto_increment comment '工资号',
  work_ID bigint(20) comment '工号',
  sign varchar(16) comment '员工身份',
  years int comment '年份',
  months int comment '月份',
  basePay float comment '基本工资',
  workdays int comment '出勤天数',
  absencedays int comment '缺勤天数',
  allowance float comment '岗位津贴',
  deserved float comment '应得',
  payTime datetime comment '发放时间',
  payStatus varchar(16) comment '发放状态',
  constraint salary_manageID_FK foreign key (work_ID) references  tb_manager (id) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint salary_workerID_FK foreign key (work_ID) references  tb_worker (id) ON DELETE CASCADE ON UPDATE CASCADE
);



-- 报修单
create table tb_repair(
  id int primary key auto_increment comment '报修号',
  room_ID varchar(16) comment '宿舍号',
  worker_ID bigint comment '维修人工号',
  reason text comment '报修事由',
  reporter varchar(16) comment '报修人',
  reportTime datetime comment '报修日期',
  images varchar(64) comment '报修图片',
  remarks text comment '备注',
  repairTime datetime comment '维修日期',
  result text comment '维修结果',
  pay float comment '花费开销',
  constraint repair_FK foreign key (worker_ID) references tb_worker (id) ON DELETE CASCADE ON UPDATE CASCADE,
  constraint repair_room_FK foreign key (room_ID) references tb_room (room_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- #水电表
create table tb_water_elec(
  id int primary key auto_increment comment '水电号',
  room_ID varchar(16) comment '宿舍号',
  last_time datetime comment '上次抄表时间',
  now_time datetime comment '本次抄表时间',
  water float comment '用水量',
  electric float comment '用电量',
  pay float comment '应交费用',
  payStatus varchar(16) comment '缴费状态',
  constraint water_elec_FK foreign key (room_ID) references tb_room (room_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 卫生记录表
create table tb_health(
  id int primary key auto_increment comment '卫生号',
  room_ID varchar(16) comment '宿舍号',
  checkTime datetime comment '检查日期',
  checkResult datetime comment '检查结果',
  remark text comment '备注',
  constraint health_FK foreign key (room_ID) references tb_room (room_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

--  学生宿舍费记录表
create table tb_fees(
  id int primary key auto_increment comment '宿舍费号',
  stu_ID bigint(20) comment '学号',
  years int comment '宿舍费年份',
  fee float comment '金额',
  payStatus varchar(16) comment '缴费状态',
  payTime datetime comment '缴费时间',
  constraint fees_FK foreign key (stu_ID) references tb_stu (id) on DELETE cascade on UPDATE cascade
);

--  来访登记表
create table tb_visitor(
  id int primary key auto_increment comment '登记号',
  visit_name varchar(16) comment '来访人姓名',
  visit_phone varchar(32) comment '来访人电话',
  reason text comment '来访事由',
  visit_time datetime comment '来访时间',
  leave_time datetime comment '离开时间',
  manage_name varchar(16) comment '值班人员',
  remark text comment '备注'
);

--  留宿申请表
create table tb_stay(
  id int primary key auto_increment comment '留宿号',
  stu_ID bigint(20) comment '学号',
  reason text comment '留宿事由',
  start_time datetime comment '开始时间',
  end_time datetime comment '结束时间',
  parent varchar(16) comment '监护人姓名',
  teacher varchar(16) comment '辅导员姓名',
  constraint stay_FK foreign key (stu_ID) references tb_stu (id) on DELETE cascade on UPDATE cascade
);

-- 公告表
create table tb_notice(
  id int primary key auto_increment comment '公告号',
  content text comment '公告内容',
  notice_time datetime comment '发布时间',
  notice_peoper varchar(16) comment '发布人'
);