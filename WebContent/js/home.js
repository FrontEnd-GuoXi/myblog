	
	$(function(){


      function init(){

      }

      function ajax(start,end){
          $.ajax({
                url:`http://localhost:8080/myblog/home?username=000000&start=${start}&end=${end}`,
                type:'GET',
                dateType:'json',
                success:(data)=>{
                  template(data);
                },
                error:() => {

                }
          });

      }

      function template(data){
          var template="",str="",cleanStr="",partStr="";
          for(let i=0; i<data.length; i++){
            cleanStr = data[i].content.replace(/<\/?[^>]*>/g,"");
            cleanStr.length > 27 ? partStr=cleanStr.substring(0,27) : partStr=cleanStr;
            template+=`<div class="article-row">
                          <div class="article-item">
                            <h2 class="article-title">${data[i].title}</h2>
                            <div class="article-content">${partStr+=...}
                              <span class='text-spread'>
                                阅读全文
                                <span class='glyphicon glyphicon-fullscreen'></span>
                              </span>
                            </div>
                          </div>
                       </div>`
            $(template).appendTo(".article-wrap");
          }

          $(".text-spread").on('click',function(e){
            
          });

      }

  });