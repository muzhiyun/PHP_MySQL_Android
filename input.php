<?php
ini_set('max_execution_time','0');
$shiju=$_POST["shiju"];
//接受post变量
  
 function curl_file_get_contents($durl){  
    $ch = curl_init();  
    curl_setopt($ch, CURLOPT_URL, $durl);  
	curl_setopt($ch, CURLOPT_TIMEOUT,2); 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true) ; // 获取数据返回    
    curl_setopt($ch, CURLOPT_BINARYTRANSFER, true) ; // 在启用 CURLOPT_RETURNTRANSFER 时候将获取数据返回    
    $data = curl_exec($ch);  
    curl_close($ch);  
    return $data;  
	}
	
 function apidata(){
	$durl='http://yijuzhan.com/api/word.php?m=json' ;
	#echo ("$durl");
	$apidata=curl_file_get_contents($durl);
	#echo ("$apidata");
	$jsonde=(json_decode($apidata,true));	
	if (($jsonde['content'])!='')
		#echo "</br>";
		
		return ($jsonde['content']);
	
	#echo "<pre>";
	#print_r ($apidata);
	#echo "</pre>";
	#echo "finish";
	}
 function spider(){
   	$shiju=apidata();
   	#echo ($shiju);
   	#return $shiju;
   	addsql($shiju);

   	}


 function addsql($shiju){
	if ($shiju!="")
	{
		require "connet.php";

		$judgment = "SELECT id FROM shiju where connet = '".$shiju."'";
		#echo "</br>";
		#echo $judgment;
		$sqlask = $conn->query($judgment);
		#echo "</br>";
		#echo $sqlask;
		$sqlresult = mysqli_fetch_row($sqlask);
		#echo "</br>";
		#echo $sqlresult[0];
		$sqlid=$sqlresult[0];
		$ip="192.168.2.1";
		#$time="2010-10-25 00:00:00";
		if (empty($sqlid))
			{
				
				#echo "id为空";
				$sql="INSERT INTO shiju (connet,ip,time) VALUES ('$shiju','$ip',now())";
				if ($conn->query($sql) === TRUE) 		//sql插入数据
				{
					
					#echo ($jsonde['content']);
					#echo "&nbsp;&nbsp;新记录插入成功";
					echo "<script>alert('新记录插入成功!');location.href='".$_SERVER["HTTP_REFERER"]."';</script>"; 
				} 
				else 
				{
					echo "Error: " . $sql . "：" . $conn->error. "<br>" ;
				}
			}
		else
			{
				echo "<script>alert('该记录已存在，插入失败!');location.href='".$_SERVER["HTTP_REFERER"]."';</script>"; 
			}
		#echo "&nbsp;&nbsp;";
		$conn->close();
	}
	}

addsql($shiju);
?>