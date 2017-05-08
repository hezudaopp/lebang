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
  "appId": 1,
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
  "userTaskId": 1, // 用户任务id
  "operatorUserId": 12, // 操作用户id
  "operatorAppId":2, // 操作用户来源app
  "operatorAppUserId":"234221", // 操作用户在来源app中user_id
  "fromStatus": 1, // 操作前任务进度
  "toStatus": 2, // 操作后任务进度
  "createdTime": 1234567890
}
```



## 接口列表：
### 岗位相关
#### 获取岗位类型列表
  - 请求URI: /post_types
  - 请求Method: GET
  - 请求参数:
    - enabled: 1 // 是否启用 默认1
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [POST结构, POST结构, ...]
```

#### 添加岗位
  - 请求URI: /posts
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: POST结构, id不用传
  - 响应成功代码: 201
  - 响应成功内容: POST结构

#### 删除岗位
  - 请求URI: /posts/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑岗位
  - 请求URI: /posts/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: POST结构, id不用传
  - 响应成功代码: 200
  - 响应成功内容: POST结构

#### 岗位列表（分页）
  - 请求URI: /posts
  - 请求Method: GET
  - 请求参数:
    - page 当前页，默认0
    - size 每页大小，默认10
    - enabled 是否启用，默认1
    - name 岗位名称，搜索岗位时模糊匹配岗位名，默认不传
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [POST结构, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 禁用/启用岗位
  - 请求URI: /posts/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容:
``` javascript
  {
    "enabled":1 // 启用状态
  }
```
  - 响应成功代码: 200
  - 响应成功内容: POST结构

### 部门相关
#### 添加部门
  - 请求URI: /depts
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: DEPT, id不用传
  - 响应成功代码: 201
  - 响应成功内容: DEPT

#### 删除部门
  - 请求URI: /depts/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑部门
  - 请求URI: /depts/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: DEPT, id不用传
  - 响应成功代码: 200
  - 响应成功内容: DEPT

#### 岗位部门
  - 请求URI: /depts
  - 请求Method: GET
  - 请求参数:（level和parentDeptId二选一）
    - enabled 是否启用，默认1
    - level 部门层级，默认为1，当为1时和parentDeptId为0是等效的
    - parentDeptId 上级部门, 默认为0，当为parentDeptId为0时和level为1是等效的
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [DEPT结构, ...]
```

#### 禁用/启用部门
  - 请求URI: /depts/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容:
``` javascript
  {
    "enabled":1 // 启用状态
  }
```
  - 响应成功代码: 200
  - 响应成功内容: DEPT结构

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
    - username, mobileNumber, email字段必须唯一
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
  - 请求URI: /users/{username}
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

### 产品模板相关
#### 添加产品模板
  - 请求URI: /product_templates
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: PRODUCT_TEMPLATE, id不用传
  - 响应成功代码: 201
  - 响应成功内容: PRODUCT_TEMPLATE

#### 删除产品模板
  - 接口说明：那么该产品模板下必须没有产品
  - 请求URI: /product_templates/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑产品模板
  - 请求URI: /product_templates/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: PRODUCT_TEMPLATE
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT_TEMPLATE

#### 产品模版列表（分页）
  - 请求URI: /product_templates
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - name:"产品模版名", 默认为空字符串，表示不对该字段过滤
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [PRODUCT_TEMPLATE, ...],
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

### 产品相关
#### 添加产品
  - 请求URI: /products
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: PRODUCT, id不用传
  - 响应成功代码: 201
  - 响应成功内容: PRODUCT

#### 编辑产品
  - 接口说明：如果要编辑产品，产品状态必须是待审核
  - 请求URI: /products/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: PRODUCT
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 产品列表（分页）
  - 请求URI: /products
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - productTemplateId: 0， 默认-1，表示筛选productTemplateId > 0的产品
    - status:1, 默认0
    - name:"产品名", 默认为空字符串，表示不对该字段过滤
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [PRODUCT, ...],
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

#### 审核产品
  - 请求URI: /products/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容:
``` javascript
  {
    "status":1, // 1: 审核通过 2: 审核拒绝
    "remark":"审核说明"
  }
```
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 禁用产品
  - 接口说明：产品状态必须是审核通过
  - 请求URI: /products/{id}/disable
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 启用产品
  - 接口说明：产品状态必须是审核通过
  - 请求URI: /products/{id}/enable
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

### 贷款相关
#### 添加贷款
  - 请求URI: /loans
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: LOAN, id不用传
  - 响应成功代码: 201
  - 响应成功内容: LOAN

#### 编辑贷款或审批贷款
  - 接口说明：修改贷款信息或审核贷款
  - 请求URI: /loans/{id}
  - 请求Method: PATCH
		  - 请求参数: loanOperate: 1. 修改 2. 提交审批申请 3. 审批通过 4. 审批拒绝 5. 放弃
  - 请求内容: LOAN, id不用传
  - 响应成功代码: 200
  - 响应成功内容: LOAN

#### 贷款列表（分页）
  - 请求URI: /loans
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - isFilterSelf: 1， 可选，默认0，是否筛选本人创建的贷款
    - sourceFinancialCommissioner: 跟单金融专员用户名 可选
    - applicantName: 申请人姓名 可选
    - applicantMobileNumber: 申请人手机号 可选
    - createdTimeFrom: 最早创建时间 可选
    - createdTimeTo: 最晚创建时间 可选
    - status:1,2,3 可选 默认为空 筛选全部 （多选，也可以只选一种状态，多选的时候用逗号分割）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "content": [LOAN, ...],
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

#### 获取贷款详情
  - 请求URI: /loans/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: LOAN

### 贷款日志相关
#### 贷款日志列表
  - 请求URI: /loan_logs
  - 请求Method: GET
  - 请求参数:
    - loanId: 1, // 必选 贷款id
    - fromStatus:1, // 必选 贷款原状态
    - toStatus: 2, // 必选 贷款目标状态
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  [LOAN_LOG, LOAN_LOG, ...]
```
