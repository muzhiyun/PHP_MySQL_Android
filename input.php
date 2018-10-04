<?php

require "connet.php";
#echo $_POST["shiju"];
$shiju=$_POST["shiju"];
$ip="192.168.2.1";
$time="2010-10-25 00:00:00";


#echo "<pre>";
#print_r ($shiju);
#echo ($shiju);
#echo "</pre>";
//接受post变量


$sql="INSERT INTO shiju (connet,ip,time) VALUES ('$shiju','$ip','$time')";
if ($conn->query($sql) === TRUE) {
    echo "新记录插入成功";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
//sql插入数据


$conn->close();
?>