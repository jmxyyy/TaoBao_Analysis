sqoop export \
--connect jdbc:mysql://172.17.150.155:3306/taobao \
--username root \
--password Cz742003! \
--table product_stat \
--export-dir /taobao_data_output/Step05/part-r-00000 \
--input-fields-terminated-by ',' \
--input-lines-terminated-by '\n' \
-m 1