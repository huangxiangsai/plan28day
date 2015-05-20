[#ftl]
[#import "util.ftl" as u]
[@u.page title="这是一个宏，为什么叫宏呢，不知道，因为它很抽象吧"]



<a class="btn btn-default" href="#" id="goBtn" role="button">发起一个delete请求</a>
<script type="text/javascript">
	
	$(function() {

		$("#goBtn").on('click',function() {
			$.ajax({
				url : '/delete/23',
				type : 'DELETE',
				success : function(data) {
					alert(data.status);
				}
			});
		});
		
	});
</script>


[/@u.page]
