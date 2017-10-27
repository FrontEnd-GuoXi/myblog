	
	$(function(){
        var start = 0,
            end = 3;


        init();
        function init(){
        	promiseAjax(start,end).then(
        		(data)=>{        			
        			var ContentSize = null,
        			    TextSpread = null,
        			    TextShrink = null,
        			    TextShrinkArr = [],
                  TextSpreadFilter = null;


	     			 for(let i=0; i<end; i++){
       				   	 ContentSize = data[i].content.replace(/<\/?[^>]*>/g,"").length;
        				   TextSpread = data[i].content;
                   TextSpreadFilter = TextSpread.replace(/<\/?[^>]*>/g,"");
                   console.log(TextSpreadFilter);
        				   if(ContentSize>173){
		        				 TextShrink = TextSpreadFilter.substring(0,173);
		        				 TextShrinkArr = TextShrink.split("");
		        				 TextShrinkArr.splice(170,3,".",".",".");
		        				 TextShrink = TextShrinkArr.join("");
		        				 TextSpreadShrink(TextShrink,data,i,TextSpread);				 	
        				   		 
        				   }else{
							       TextSpreadShrink(TextSpread,data,i);

        				   }

	     			  }

        	},
        		(jqXHR)=>{
        			console.log(jqXHR);
        		});
        }


        function TextSpreadShrink(TextObj,data,i,TextSpread){
           var str ="";
		   TextObj += "<span class='text-spread'>阅读全文<span class='glyphicon glyphicon-fullscreen'></span></span>";
		   str +=`<div class="article-row">
			         <div class="article-item">
				      	<h2 class="article-title">${data[i].title}</h2>
				      	<div class="article-content">${TextObj}</div>
				      </div>
			      </div>`;
	       $(".article-wrap").append(str);
	       console.log(str);

        	  $(".text-spread").on('click',function(){

               let StrHtml = `<div class='basic-information'>
                              <span class="author">${data[i].author}</span>
                              <span class="public-time">${data[i].time}</span>
                              <span class="text-shrink">收起<span class="glyphicon glyphicon-resize-small"></span></span>
                              </div>`;   	  

               $(this).parents(".article-content").html(TextSpread);
   		 	       console.log($(this).parents(".article-item").append(StrHtml))

	 	      $(".text-shrink").on('click',function(){
	 	      	    //TextObj = TextObj.replace("<span class='text-spread'>阅读全文<span class='glyphicon glyphicon-fullscreen'></span></span>","");
	 	      		  $(this).parents(".article-content").html(TextObj);
	 	       });
        				   		 });
        }


 		function promiseAjax(start,end){
 			return new Promise((resolve,reject)=>{
  					ajaxArticle(start,end,resolve,reject);
 			});
 		}	

 		function ajaxArticle(start,end,resolve,reject){
	     	$.ajax({
	     		url:'http://localhost:8080/myblog/home?username=000000&start='+start+'&end='+end,
	     		type:'GET',
	     		dataType:'json',
	     		success:function(data){
	     			console.log(data);
	     			resolve(data);
	     		},
	     		error:function(jqXHR,textStatus,errorThrown){
	     			console.log(textStatus,errorThrown);
	     			reject(jqXHR);
	     		}
	     	});
 		}

  	    $(".previous").on('click',function(){
  	    	        let $this = $(this);
  	    	        btnAnimate($this);
  	    });
  	    $(".next").on('click',function(){
  	    	        let $this = $(this);
  	    	        btnAnimate($this);
  	    });

  	    function btnAnimate($this){
   	    	        new Promise((resolve,reject) => {
  	    	        	  $this.css({'transform':'scale(0.92,0.92)'});
  	    	        	  setTimeout(()=>{ resolve(); },200);
  	    	        }).then(() =>{
  	    	        	  $this.css({'transform':'scale(1,1)'});
  	    	        });
  	    }

	});
