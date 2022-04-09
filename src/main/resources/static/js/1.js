

var CoreUtil = (function (){
    var coreUtil = {};
    coreUtil.sendAjax = function (url,params,method,headers,ft,dataType,contentType,noAuthorityFt,async){
        layui.jquery.ajax({
            url:url,
            cache:false,
            data:params,
            type:method == undefined?"POST":method,
            contentType:contentType == undefined?"application/json;charset=utf-8":contentType,
            dataType:"json",
            async:async  == undefined?true:async,
            beforeSend:function (request){
                if(headers == undefined){
                    alert("success")
                }else if(headers){
                    alert("success")
                    request.setRequestHeader('authorization',CoreUtil.getData('access_token'));
                    request.setRequestHeader('refresh_token',CoreUtil.getData('refresh_token'));
                }else{
                    alert("success")
                    request.setRequestHeader('authorization',CoreUtil.getData('access_token'));
                }

            },
            success:function (resp){

                if(typeof ft == 'function'){
                    if(resp.code == 4010001){
                         凭证过期重新登录
                        window.location.href='/api/user/login'
                    }else if(resp.code == 4010002){
                         需要刷新token
                        var reurl = url;
                        var reparams = params;
                        var remethod = method;
                        var reheaders = headers;
                        var recontentType = contentType;
                        var redataType = dataType;
                        var reft = ft;
                        var reasync = async
                        var renoAuthorityFt = noAuthorityFt;
                        CoreUtil.sendAjax('/api/user/token',null,'GET',true,function (resp){
                            if(resp.code == 0){
                                CoreUtil.setData('access_token',resp.data)
                                CoreUtil.sendAjax(reurl,reparams,remethod,reheaders,reft,redataType,recontentType,renoAuthorityFt,reasync)
                            }else{
                                window.location.href='/api/user/login'
                            }
                        })

                    }else if(resp.code == 0){
                        if(ft != null && ft != undefined){
                            ft(resp)
                        }
                    }else if(resp.code == 4030001){
                        if(noAuthorityFt != null && noAuthorityFt != undefined){
                            noAuthorityFt(resp)
                        }
                    }else{
                        layer.msg(code.msg)
                    }
                }
            },
            error:function (XMLHttpRequest,textStatus,errorThrow){
                if(XMLHttpRequest.status = 404){
                    window.location.href='/index/404'
                }else{
                    layer.msg("服务器出了点问题，请稍后重试！！")
                }
            }

        });
    };
    coreUtil.setData = function (key,value){
        layui.sessionData('LocalData',{
            key:key,
            value:value
        })
    }
    coreUtil.getData = function(key){
        let localData = layui.sessionData('LocalData')
        return localData[key];
    }
        /*格式化时间格式*/
    coreUtil.formattime=function (val) {
        if(val==null||val==undefined){
            return "";
        }
        var date=new Date(val);
        var year=date.getFullYear();
        var month=date.getMonth()+1;
        month=month>9?month:('0'+month);
        var day=date.getDate();
        day=day>9?day:('0'+day);
        var hh=date.getHours();
        hh=hh>9?hh:('0'+hh);
        var mm=date.getMinutes();
        mm=mm>9?mm:('0'+mm);
        var ss=date.getSeconds();
        ss=ss>9?ss:('0'+ss);
        var time=year+'-'+month+'-'+day+' '+hh+':'+mm+':'+ss;
        return time;
    };

    return coreUtil;
})(CoreUtil,window)