drop index hydrant_spatial_idx;
drop index bldg_spatial_idx;
delete from user_sdo_geom_metadata where table_name = 'BLDG';
delete from user_sdo_geom_metadata where table_name = 'HYDRANT';
drop table hydrant;
drop table bldg;
