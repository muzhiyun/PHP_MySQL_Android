<?php
require "connet.php";

$maxid=$conn->query("select max(id) from shiju");
$maxid=mysqli_fetch_row($maxid);
#$maxid=json_encode($maxid);
#var_dump($maxid);
#echo ($maxid[0]);
#echo "</br>";
$max_id=$maxid[0];

#echo "</br>";
#echo mt_rand(1,$max_id);
#cho "</br>";
$id=mt_rand(1,$max_id);
#echo $id."/".$max_id;
$sql = "SELECT id,connet,ip,time FROM shiju where id = ".$id;
#echo $sql;
//select * from title where id = (select max(id) 
//select * from shiju where id = (select max(id) from shiju)
$result = $conn->query($sql);
#echo ($result);

function try_json_encode($id_temp,$connet_temp,$ip_temp,$time_temp){
	$before=array("id"=>$id_temp,"connet"=>$connet_temp,"ip"=>$ip_temp,"time"=>$time_temp);
	$after=json_encode($before);
	#echo gettype($before);
	#echo "</br>";
	#echo gettype($after);
	#echo "</br>";
	#echo json_encode($before);
	#echo "</br>";
	#echo ($after);
	#echo "</br>";
	return $after;
}

if ($result->num_rows > 0) {
    // 输出数据
    while($row = $result->fetch_assoc()) {
        #echo "id: " . $row["id"]. "</br>connet: " . $row["connet"]. "</br>".$row["ip"]."</br>".$row["time"];
		#echo "</br>";
		#cho "</br></br>";
		if (!empty($_GET['m']))
		{
			if ($_GET['m']=="json")
			{
				echo try_json_encode($row["id"],$row["connet"],$row["ip"],$row["time"]);
			}
			if($_GET['m']=="js")
			{
				echo "var str=\"".$row["connet"]."\";function yiju(){document.write(str);}";
			}
		}
		else
			{
				echo "<!DOCTYPE html>
					<html>
					<head>
					<title>一句诗词</title>
					</head>
					<body>";
				echo $row["connet"];
				echo "</body>
					</html>";
					#echo "</br></br>";
					#echo "</br></br>";
					#<!-- <a href="index.php">提交页面</a> -->
			}
    }
} else {
    echo "读取出错";
}
$conn->close();



?>


