sqoop export \
--connect jdbc:mysql://172.17.150.155:3306/taobao \
--username root \
--password Cz742003! \
--table user_behavior \
--export-dir /taobao_data_output/Step02/user_summary/part-r-00000 \
--input-fields-terminated-by '\t' \
--input-lines-terminated-by '\n' \
--columns "name,value" \
-m 1