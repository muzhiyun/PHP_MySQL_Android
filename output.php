<?php
require "connet.php";

$maxid=$conn->query("select max(id) from shiju");
echo ($maxid);


$sql = "SELECT id,connet,ip,time FROM shiju where id = (select max(id) from shiju)";
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
		echo try_json_encode($row["id"],$row["connet"],$row["ip"],$row["time"]);
		echo "</br></br>";
    }
} else {
    echo "读取出错";
}
$conn->close();

?>