<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div id="mapContainer" style="width: 80vw;height: 80vh;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1965125d8bfdc12035fae1f11ca346bd"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	
	<script>	
		const options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(35.865559, 128.593409), // 지도의 중심좌표
			level: 5 // 지도의 레벨 (확대, 축소 정보)
		};

		const map = new kakao.maps.Map(mapContainer, options);
		
		function getRestaurantList() {
			axios.get('/restaurant/ajaxGetList').then(function(res) {
					console.log(res.data)
					
					res.data.forEach(function(item) {
						const na = {
								'Ga': item.lng, 
								'Ha': item.lat
						}
						var marker = new kakao.maps.Maker({
							position: na
						})
						marker.setMap()
					})
			})
		}
		
		getRestaurantLsit()
	</script>
</div>