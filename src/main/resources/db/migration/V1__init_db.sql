--CLEAN-UP DATABASE--

alter table if exists answer
    drop constraint if exists answer_student_fk;
alter table if exists answer
    drop constraint if exists answer_task_fk;
alter table if exists department
    drop constraint if exists department_faculty_fk;
alter table if exists group_res
    drop constraint if exists group_department_fk;
alter table if exists student
    drop constraint if exists student_group_fk;
alter table if exists subject_class
    drop constraint if exists subject_class_group_res_fk;
alter table if exists subject_class
    drop constraint if exists subject_class_subject_fk;
alter table if exists subject_class
    drop constraint if exists subject_class_teacher_fk;
alter table if exists task_subject_class
    drop constraint if exists task_subject_class_subject_class_fk;
alter table if exists task_subject_class
    drop constraint if exists task_subject_class_task_fk;
alter table if exists teacher
    drop constraint if exists teacher_department_fk;

drop table if exists answer cascade;
drop table if exists department cascade;
drop table if exists faculty cascade;
drop table if exists group_res cascade;
drop table if exists student cascade;
drop table if exists subject cascade;
drop table if exists subject_class cascade;
drop table if exists task cascade;
drop table if exists task_subject_class cascade;
drop table if exists teacher cascade;

drop sequence if exists answer_id_seq;
drop sequence if exists department_id_seq;
drop sequence if exists faculty_id_seq;
drop sequence if exists group_id_seq;
drop sequence if exists student_id_seq;
drop sequence if exists subject_class_id_seq;
drop sequence if exists subject_id_seq;
drop sequence if exists task_id_seq;
drop sequence if exists teacher_id_seq;

--TABLES--
create sequence faculty_id_seq start 1 increment 1;
create table faculty
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create sequence department_id_seq start 1 increment 1;
create table department
(
    id         int8         not null,
    code       varchar(255) not null,
    name       varchar(255),
    faculty_id int8         not null,
    primary key (id)
);
alter table department
    add constraint unique_code unique (code);
alter table department
    add constraint department_faculty_fk foreign key (faculty_id) references faculty;

create sequence group_id_seq start 1 increment 1;
create table group_res
(
    id            int8 not null,
    grade         int8,
    list_number   int8,
    department_id int8 not null,
    primary key (id)
);
alter table group_res
    add constraint group_department_fk foreign key (department_id) references department;

create sequence student_id_seq start 1 increment 1;
create table student
(
    id          int8         not null,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255) not null,
    group_id    int8         not null,
    primary key (id)
);
alter table student
    add constraint student_group_fk foreign key (group_id) references group_res;

create sequence teacher_id_seq start 1 increment 1;
create table teacher
(
    id            int8         not null,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    middle_name   varchar(255) not null,
    department_id int8         not null,
    primary key (id)
);
alter table teacher
    add constraint teacher_department_fk foreign key (department_id) references department;

create sequence subject_id_seq start 1 increment 1;
create table subject
(
    id   int8         not null,
    name varchar(255) not null,
    primary key (id)
);

create sequence subject_class_id_seq start 1 increment 1;
create table subject_class
(
    id         int8 not null,
    group_id   int8 not null,
    subject_id int8 not null,
    teacher_id int8 not null,
    primary key (id)
);
alter table subject_class
    add constraint unique_subject_group_teacher unique (subject_id, group_id, teacher_id);
alter table subject_class
    add constraint subject_class_group_res_fk foreign key (group_id) references group_res;
alter table subject_class
    add constraint subject_class_subject_fk foreign key (subject_id) references subject;
alter table subject_class
    add constraint subject_class_teacher_fk foreign key (teacher_id) references teacher;

create sequence task_id_seq start 1 increment 1;
create table task
(
    id          int8 not null,
    deadline    timestamp,
    description varchar(255),
    topic       varchar(255),
    value       int4 not null,
    primary key (id)
);

create table task_subject_class
(
    subject_class_id int8 not null,
    task_id          int8 not null,
    primary key (subject_class_id, task_id)
);
alter table if exists task_subject_class
    add constraint task_subject_class_subject_class_fk foreign key (task_id) references task;
alter table if exists task_subject_class
    add constraint task_subject_class_task_fk foreign key (subject_class_id) references subject_class;

create sequence answer_id_seq start 1 increment 1;
create table answer
(
    id         int8 not null,
    message    varchar(255),
    student_id int8 not null,
    task_id    int8 not null,
    primary key (id)
);
alter table answer
    add constraint unique_student_task unique (student_id, task_id);
alter table answer
    add constraint answer_student_fk foreign key (student_id) references student;
alter table answer
    add constraint answer_task_fk foreign key (task_id) references task;