create table bldg(
    bldg_id varchar2(10) primary key,
    bldg_name varchar2(30),
    on_fire char(1) not null,
    geom sdo_geometry not null
);

create table hydrant(
    hydrant_id varchar2(10) primary key,
    geom sdo_geometry not null
);

insert into user_sdo_geom_metadata(table_name, column_name, diminfo, srid)
values('bldg', 'geom', SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X', 0, 820, 0.005),
       SDO_DIM_ELEMENT('Y', 0, 580, 0.005)), NULL);

insert into user_sdo_geom_metadata(table_name, column_name, diminfo, srid)
values('hydrant', 'geom', SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X', 0, 820, 0.005),
       SDO_DIM_ELEMENT('Y', 0, 580, 0.005)), NULL);

create index bldg_spatial_idx
    on bldg(geom)
    indextype is mdsys.spatial_index;

create index hydrant_spatial_idx
    on hydrant(geom)
    indextype is mdsys.spatial_index;
