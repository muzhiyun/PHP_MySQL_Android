<?php

$servername="localhost";
$username="yiju";
$passwd="Myyiju2017..";
$dbname="yiju";
//sql设置

$conn=new mysqli($servername,$username,$passwd,$dbname);

if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
} 
echo "连接成功";
echo "</br>";
//sql连接


?>