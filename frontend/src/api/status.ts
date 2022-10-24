export const enum STATUS {
  // 登录成功
  LOGIN_SUCCESS = 20001,
  // 登录失败
  LOGIN_FAIL = 20000,

  // 登录成功，但是被封禁
  LOGIN_BAN = 20010,
  // 登录成功，且是管理员
  LOGIN_AUTH = 20021,

  // POST 请求成功
  POST_SUCCESS = 20011,
  // POST 请求失败
  POST_FAIL = 20010,

  // GET 请求查询成功
  GET_SUCCESS = 20041,
  // GET 请求查询失败
  GET_FAIL = 20040,


  // PUT 请求更新成功
  PUT_SUCCESS = 20031,
  // PUT 请求更新失败
  PUT_FAIL = 20030,

  // DELETE 请求删除成功
  DELETE_SUCCESS = 20021,
  // DELETE 请求删除失败
  DELETE_FAIL = 20020,

  // 系统错误
  SYSTEM_ERROR = 50001,
  // 超时
  TIMEOUT = 50002,
  // 未知错误
  UNKNOWN_ERROR = 59999,
  // 业务错误
  BUSINESS_ERROR = 60002
}