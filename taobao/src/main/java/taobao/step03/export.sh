sqoop export \
--connect jdbc:mysql://172.17.150.155:3306/taobao \
--username root \
--password Cz742003! \
--table date_stat \
--export-dir /taobao_data_output/Step03/part-r-00000 \
--input-fields-terminated-by ',' \
--input-lines-terminated-by '\n' \
-m 1