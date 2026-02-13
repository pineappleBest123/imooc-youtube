
# 使用Axios发起HTTP请求

    const httpRequest = axios.create({
        // 请求的后端接口的基础路径
        baseURL:'http://localhost:15005',
        // 接口超时响应时间
        timeout:10000
    });


### 一、发起GET请求
1、发起没有请求参数的GET请求

    httpRequest.get('/path');

2、发起有请求参数的GET请求

    http://localhost:15005/path?p1=1&p2=2

    let params = {
        p1:1,
        p2:2
    };

    httpRequest.get('/path', {params});


### 二、发起POST请求

    let params = {
        p1:1,
        p2:2
    };

    httpRequest.post('/path', params);


### 三、发起PUT请求（添加配置）

    let params = {
        p1:1,
        p2:2
    };

    return await httpRequest.put('/file-slices', params, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });


### 四、发起DELETE请求

    let params = {
        p1:1
    };

    return await httpRequest.delete('/path', {params});


