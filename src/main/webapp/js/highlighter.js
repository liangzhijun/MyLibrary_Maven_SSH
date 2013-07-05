/*----------------------------------------*\ 
     * ʹ�� js ���Ǹ����ؼ��� by markcxz(markcxz@aol.com)
     * ����˵��: 
     * obj: ����, Ҫ���и�����ʾ��html��ǩ�ڵ�. 
     * hlWords: �ַ���, Ҫ���и����Ĺؼ��ʴ�, ʹ�� ����(|)���ո� �ָ������� . 
     * cssClass: �ַ���, �����ؼ���ͻ����ʾ������cssα��. 
     * �ο�����: javascript HTML DOM ������ʾҳ���ض��ִ� By shawl.qiu
    \*----------------------------------------*/ 
    function MarkHighLight(obj,hlWords,cssClass){
    
        hlWords=AnalyzeHighLightWords(hlWords);
        
        if(obj==null || hlWords.length==0)
            return;
        if(cssClass==null)
            cssClass="highlight";
            
        MarkHighLightCore(obj,hlWords);
        
        //------------ִ�и������ǵĺ��ķ���----------------------------

        function MarkHighLightCore(obj,keyWords){
        	
            var re=new RegExp(keyWords, "i"); 
            for(var i=0; i<obj.childNodes.length; i++){
                var childObj=obj.childNodes[i];
                if(childObj.nodeType==3){
                    if(childObj.data.search(re)==-1) continue; 
                    var reResult = new RegExp("("+keyWords+")", "gi"); 
                    var objResult = document.createElement("b");
                    objResult.innerHTML = childObj.data.replace(reResult,"<em style='color:#F00;'>$1</em>");                     
                    if(childObj.data == objResult.childNodes[0].innerHTML) continue; 
                    obj.replaceChild(objResult,childObj);                                      
                }else if(childObj.nodeType==1){
                    MarkHighLightCore(childObj,keyWords);
                }
            }
        }        

        //----------�����ؼ���----------------------

        function AnalyzeHighLightWords(hlWords)
        {
            if(hlWords==null) return "";
            hlWords=hlWords.replace(/\s+/g,"|").replace(/\|+/g,"|");            
            hlWords=hlWords.replace(/(^\|*)|(\|*$)/g, "");
            
            if(hlWords.length==0) return "";
            var wordsArr=hlWords.split("|"); 
            
            if(wordsArr.length>1){
                var resultArr=BubbleSort(wordsArr);
                var result="";
                for(var i=0;i<resultArr.length;i++){
                    result=result+"|"+resultArr[i];
                }                
                return result.replace(/(^\|*)|(\|*$)/g, "");

            }else{
                return hlWords;
            } 
        }    
        
        //-----����ð�����򷨰ѳ��Ĺؼ��ʷ�ǰ��-----    

        function BubbleSort(arr){        
            var temp, exchange;    
            for(var i=0;i<arr.length;i++){            
                exchange=false;                
                for(var j=arr.length-2;j>=i;j--){                
                    if((arr[j+1].length)>(arr[j]).length){                    
                        temp=arr[j+1]; arr[j+1]=arr[j]; arr[j]=temp;
                        exchange=true;
                    }
                }                
                if(!exchange)break;
            }
            return arr;            
        }
    
    }
    //----------------end------------------------