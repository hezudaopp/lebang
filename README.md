## 接口统一规范约定：
### 协议：Http
### 风格：Restful
  - 资源名词尽量复数形式
  - PUT 用来更新资源的全部字段 PATCH 用来更新资源的部分字段
  - JSON字段采用驼峰格式
  - 失败响应格式如下:
``` javascript
{
  "errorCode": "Not Found", 
  "errorMessage":"资源未找到"
}
```
### 请求数据格式：JSON
### 响应数据格式：JSON
### 响应代码：
  - 200: 成功
  - 201: 创建成功
  - 400: 失败
  - 401: 用户认证失败或权限不够
  - 403: 资源不允许访问
  - 404: 资源不存在
  - 405: 请求方法错误
  - 500: 服务端错误

## 基础数据结构：

###### APP渠道：APP
``` javascript
   {
     "id": 1,
     "name": "app1",
     "secret": "23dsfdsgvfdfertjuyiu5642ewwefvf",
     "enabled": true,
     "createdTime": 1494313266,
     "modifiedTime": 1494313487
   }
```

### 用户信息：USER
``` javascript
{
      "id": 1,
      "username": "lebang",
      "password": "471dc9c238566c9687b4f03822dd0bddec2069d883554fd5e8b7cff7b538447fd4f35420751447b3",
      "role": "ROLE_ADMIN",
      "status": 1,
      "createdTime": 1493863306,
      "modifiedTime": 1493863306,
      "enabled": true,
      "authorities": [
        {
          "authority": "ROLE_ADMIN"
        }
      ],
      "accountNonExpired": true,
      "accountNonLocked": true,
      "credentialsNonExpired": true
}
```

### 任务类型：TASK_TYPE
``` javascript
{
  "id": 2,
  "name": "微信群",
  "enabled": true,
  "createdTime": 1493780049,
  "modifiedTime": 1493780049
}
```

### 任务信息：TASK
``` javascript
{
  "id": 17,
  "name": "我的任务",
  "taskTypeId": 2,
  "taskTypeName": "微信群",
  "cost": 18,
  "price": 19.89,
  "amount": 5,
  "leftAmount": 5,
  "completedAmount":3,
  "acceptedAmount":2,
  "reviewPeriod": null,
  "beginTime": 1493790500,
  "endTime": 1503780500,
  "deviceTypeMask": 127,
  "eachPersonLimit": 1,
  "recycleDaysLimit": 0,
  "recommendedPerson": null,
  "enabled": true,
  "cityLimited": true,
  "createdTime": 1493977197,
  "modifiedTime": 1493977830,
  "taskProcedures": [
    {
      "id": 25,
      "taskId": 17,
      "procedureOrder": 1,
      "description": "order 2",
      "images": "1.jpg,2.jpg",
      "createdTime": 1493977197,
      "modifiedTime": 1493977197
    },
    {
      "id": 24,
      "taskId": 17,
      "procedureOrder": 3,
      "description": "order 1",
      "images": "1.jpg,2.jpg",
      "createdTime": 1493977197,
      "modifiedTime": 1493977197
    },
    ...
  ],
  "taskCities": [
    {
      "id": 1,
      "taskId": 17,
      "provinceId": 1,
      "cityId": 1,
      "createdTime": 1493977197,
      "modifiedTime": 1493977197
    },
    {
      "id": 4,
      "taskId": 17,
      "provinceId": 1,
      "cityId": 3,
      "createdTime": 1493977830,
      "modifiedTime": 1493977830
    },
    ...
  ]
}
```

### 任务步骤：TASK_PROCEDURE
``` javascript
{
  "id": 19,
  "taskId": 1,
  "procedureOrder": 5,
  "description": "order 1",
  "images": "1.jpg,2.jpg",
  "createdTime": 1493968385,
  "modifiedTime": 1493968385
}
```

### 用户任务：USER_TASK
``` javascript
{
  "id": 1,
  "appId": "1",
  "appUserId": "9822438",
  "taskId": 1,
  "taskName": "评论快去",
  "price": 19.89,
  "taskTypeName": "淘宝评论",
  "provinceId": null,
  "cityId": null,
  "note": "",
  "images": "1.jpg,2.jpg",
  "status": 1,
  "taskEndTime": 1503780500,
  "reviewerUserId": null,
  "reviewEndTime": 3641434072,
  "completedTime": 1493951272,
  "reviewedTime": null,
  "createdTime": 1493910000,
  "modifiedTime": 1493951272
}
```

### 用户任务日志：USER_TASK_LOG
``` javascript
{
  "id": 2, // 用户任务日志id
  "userTaskId": "1", // 用户任务id
  "operatorUserId": 12, // 操作用户id
  "operatorAppId":2, // 操作用户来源app
  "operatorAppUserId":"234221", // 操作用户在来源app中user_id
  "fromStatus": 1, // 操作前任务进度
  "toStatus": 2, // 操作后任务进度
  "createdTime": 1234567890
}
```

### 任务渠道统计：TASK_APP_STATISTICS
``` javascript
{
    "id": 1,
    "taskId": 2,
    "appId": 4,
    "beginTime": 1494259200, // 统计开始时间（包含）， 按天统计的话就是一天的开始时间
    "endTime": 1494345600, // 统计结束时间（不包含），按天统计的话就是第二天的开始时间
    "totalFlow": 39.78, // 审核通过的流水
    "receivedAmount": 6, // 领取的任务数
    "completedAmount": 2, // 完成的任务数
    "acceptedAmount": 2  // 审核通过的任务数
  }
```

### 审核统计：REVIEWER_TASK_STATISTICS
``` javascript
{
  "id": 3,
  "reviewerUserId": 7, //审核人员UserId
  "beginTime": 1493568000,
  "endTime": 1496246400,
  "reviewedAmount": 1, // 审核通过和拒绝总数量
  "acceptedAmount": 1 // 审核通过数量
}
```

## 接口列表：

### APP渠道相关
#### 添加APP渠道
  - 请求URI: /apps
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: APP, id不用传
  - 响应成功代码: 201
  - 响应成功内容: APP

#### 禁用启用APP渠道
  - 请求URI: /apps/{id}/enabled/{enabled}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: APP

#### APP渠道详情
  - 请求URI: /apps/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: APP

#### APP渠道列表
  - 请求URI: /apps/all
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [APP, APP, ...]
```

#### APP渠道列表（分页）
  - 请求URI: /apps
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [APP, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

### 角色相关
#### 获取角色列表
  - 请求URI: /roles
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [
    "ROLE_ADMIN",
    "ROLE_TASK_REVIEWER",
    "ROLE_REPORT_VIEWER"
  ]
```

### 用户相关
#### 用户登录
  - 接口说明: 目前支持两个方式的登录，password和refresh_token
  - 请求URI: /oauth/token
  - 请求Method: POST
  - 请求参数:
    - grant_type: password or refresh_token
    - username: 登录用户名 // grant_type为password时需要
    - password: 用户登录密码 // grant_type为password时需要
    - refresh_token: 9caeb2e2-89ac-4438-b029-e0ee1fb9fd4e // grant_type为refresh_token时需要
  - 请求头部:
    - Authorization: Basic ZW50OmFtcG5 //向服务端开发人员拿
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "access_token": "ef5979dc-1ca5-48e7-9472-fc4e5094da90",
    "token_type": "bearer",
    "refresh_token": "9caeb2e2-89ac-4438-b029-e0ee1fb9fd4e",
    "expires_in": 14,
    "scope": "read write"
  }
```

#### 用户退出
  - 请求URI: /users/logout
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求头部: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 修改密码
  - 请求URI: /users/password
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求头部: 无
  - 请求内容:
``` javascript
  {
    "oldPassword": "123456",
    "newPassword1": "654321",
    "newPassword2": "654321"
  }
```
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 添加用户
  - 接口说明：
    - username字段必须唯一
  - 请求URI: /users
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: USER
  - 响应成功代码: 201
  - 响应成功内容: 无

#### 用户列表（分页）
  - 请求URI: /users
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [USER, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 用户详情
  - 请求URI: /users/{userId}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER

#### 当前登录用户用户详情
  - 请求URI: /users/me
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER

### 任务类型相关
#### 添加任务类型
  - 请求URI: /task_types
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: TASK_TYPE, id不用传
  - 响应成功代码: 201
  - 响应成功内容: TASK_TYPE

#### 禁用启用任务类型
  - 请求URI: /task_types/{id}/enabled/{enabled}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: TASK_TYPE

#### 任务类型详情
  - 请求URI: /task_types/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: TASK_TYPE

#### 任务类型列表
  - 请求URI: /tasks/all
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [TASK_TYPE, TASK_TYPE, ...]
```

#### 任务类型列表（分页）
  - 请求URI: /tasks
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [TASK_TYPE, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

### 任务相关
#### 添加任务
  - 请求URI: /tasks
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: TASK, id不用传
  - 响应成功代码: 201
  - 响应成功内容: TASK

#### 禁用启用任务
  - 请求URI: /tasks/{id}/enabled/{enabled}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: TASK

#### 编辑任务
  - 请求URI: /tasks/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: TASK
  - 响应成功代码: 200
  - 响应成功内容: TASK

#### 任务详情
  - 请求URI: /tasks/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: TASK

#### 任务列表（分页）
  - 请求URI: /tasks
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - beginTime:1234567890, 任务开始时间
    - endTime:1234567890, 任务结束时间
    - taskTypeId: 1, 任务类型id
    - enabled: true, 是否启用
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [TASK, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

### 任务步骤相关
#### 添加任务步骤
  - 请求URI: /task_procedures
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: TASK_PROCEDURE, id不用传
  - 响应成功代码: 201
  - 响应成功内容: TASK_PROCEDURE

#### 任务步骤详情
  - 请求URI: /task_procedures/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: TASK_PROCEDURE

#### 删除任务
  - 请求URI: /task_procedures/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑任务
  - 请求URI: /task_procedures/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: TASK
  - 响应成功代码: 200
  - 响应成功内容: TASK_PROCEDURE

#### 任务步骤列表
  - 接口说明: 任务的步骤列表
  - 请求URI: /task_procedures/tasks/{taskId}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [TASK_PROCEDURE, TASK_PROCEDURE, ...]
```

### 用户任务相关
#### 用户领取任务(供渠道app调用)
  - 请求URI: /user_tasks
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: USER_TASK, id不用传
  - 响应成功代码: 201
  - 响应成功内容: USER_TASK

#### 用户完成任务(供渠道app调用)
  - 接口说明：用户完成任务后系统会自动分配审核人员审核
  - 请求URI: /user_tasks/{id}/completed
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: USER_TASK
  - 响应成功代码: 200
  - 响应成功内容: USER_TASK

#### 用户任务详情
  - 请求URI: /user_tasks/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER_TASK

#### 用户任务列表
  - 请求URI: /user_tasks
  - 请求Method: GET
  - 请求参数:
     - page: 1, 默认0
     - size:10, 默认10
     - reviewerUserId:7, 审核人userId，默认-1，表示不区分审核人（必须是管理员才能查看所有用户任务列表）
     - status:2,3 用户任务状态，逗号分隔，表示多个状态
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
{
  "content": [USER_TASK, ...],
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "size": 2,
  "number": 0,
  "first": true,
  "sort": null,
  "numberOfElements": 1
}
```

#### 审核用户任务
  - 请求URI: /user_tasks/{id}/status/{userTaskStatus}
  - 请求URI说明: userTaskStatus: 2. 审核通过 3. 审核拒绝 4. 重做任务
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER_TASK

### 统计报表相关
#### 生成任务app渠道报表
  - 接口说明: 每天00:10自动执行
  - 请求URI: /statistics/task_app_statistics
  - 请求Method: PUT
  - 请求参数: days: 统计天数，默认7天，最大允许30天（今天数据不参与统计）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 二维数据，第一个维度表示天数，第二个维度表示任务APP渠道按天统计结果
``` javascript
    [
    [{TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...}],
    [{TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...}],
    ...
    ]
```

#### 任务app渠道总报表
  - 请求URI: /statistics/task_app_statistics
  - 请求Method: GET
  - 请求参数:
    - days: 统计天数，默认7天，最大允许30天（当天数据不参与统计）,如果为0，表示统计全部数据（不包括当天数据）
    - isDistinctTask: 是否根据任务来统计
    - isDistinctApp: 是否根据APP渠道来统计
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...]
```

#### 任务报表
  - 请求URI: /statistics/task_app_statistics/tasks/{taskId}
  - 请求Method: GET
  - 请求参数:
    - days: 统计天数，默认7天，最大允许30天（当天数据不参与统计）,如果为0，表示统计全部数据（不包括当天数据）
    - isDistinctApp: 是否根据APP渠道来统计
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...]
```

#### APP渠道报表
  - 请求URI: /statistics/task_app_statistics/apps/{appId}
  - 请求Method: GET
  - 请求参数:
    - days: 统计天数，默认7天，最大允许30天（当天数据不参与统计）,如果为0，表示统计全部数据（不包括当天数据）
    - isDistinctApp: 是否根据APP渠道来统计
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...]
```

#### 任务APP渠道报表
  - 请求URI: /statistics/task_app_statistics/tasks/{taskId}/apps/{appId}
  - 请求Method: GET
  - 请求参数:
    - days: 统计天数，默认7天，最大允许30天（当天数据不参与统计）,如果为0，表示统计全部数据（不包括当天数据）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [TASK_APP_STATISTICS, TASK_APP_STATISTICS, ...]
```

#### 生成审核任务统计报表
  - 接口说明: 每月1号00:20自动执行
  - 请求URI: /statistics/reviewer_task_statistics
  - 请求Method: PUT
  - 请求参数:
    - months: 统计月数，默认3个月，最大允许12个月（当月数据不参与统计）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 二维数据，第一个维度表示月份，第二个维度表示审核人员任务统计报表按月统计结果
``` javascript
    [
    [{REVIEWER_TASK_STATISTICS, REVIEWER_TASK_STATISTICS, ...}],
    [{REVIEWER_TASK_STATISTICS, REVIEWER_TASK_STATISTICS, ...}],
    ...
    ]
```

#### 按审核人和月份统计审核报表
  - 接口说明: 通过审核人和月份分类统计审核统计数据
  - 请求URI: /statistics/reviewer_task_statistics
  - 请求Method: GET
  - 请求参数:
    - months: 统计月数，默认3个月，最大允许12个月（当月数据不参与统计）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [REVIEWER_TASK_STATISTICS, REVIEWER_TASK_STATISTICS, ...]
```

#### 按审核人统计审核报表
  - 接口说明: 通过审核人统计全部审核数据（不包含当月）
  - 请求URI: /statistics/reviewer_task_statistics/reviewer_user_ids/{reviewerUserIdsStr}
  - 请求Method: GET
  - 请求路径变量:
    - reviewerUserIdsStr: 审核人userId列表，逗号分隔
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
    [REVIEWER_TASK_STATISTICS, REVIEWER_TASK_STATISTICS, ...]
```