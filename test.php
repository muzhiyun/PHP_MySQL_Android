<head></head><body>
<script type="text/javascript" src="http://127.0.0.1/yiju/index.php?m=js"></script><script>document.write("<div>");yiju(false);document.write("</div>");</script>


<?php
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
	$durl='http://127.0.0.1/yiju/index.php?m=json' ;
	#echo ("$durl");
	$apidata=curl_file_get_contents($durl);
	#echo ("$apidata");
	$jsonde=(json_decode($apidata,true));	
	if (($jsonde['connet'])!='')
		return ($jsonde['connet']);
	}

echo apidata();
echo "</br>";
?>


<?php
require "index.php";
echo "</br>";
?>
</body>
