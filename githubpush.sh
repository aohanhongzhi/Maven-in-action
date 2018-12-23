#!/bin/bash
basepath=$(cd `dirname $0`; pwd)
echo $basepath

git add $basepath/*

git commit -m "修改了Tp04的web.xml文件，添加了映射"

git push -u origin master
