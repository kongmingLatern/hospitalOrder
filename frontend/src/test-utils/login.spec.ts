import type { LoginType } from './../type/index';
import { describe, it, expect } from 'vitest'
import { isNotEmpty } from '@/utils';
const login = (loginState: LoginType) => {
  return new Promise((resolve, reject) => {
    if (isNotEmpty(loginState.username) && isNotEmpty(loginState.password)) {
      resolve({
        status: 200,
        data: {
          message: '登录成功'
        }
      })
    } else {
      reject({
        status: 400,
        data: {
          message: '登录失败'
        }
      })
    }
  })
}
describe('Login', () => {
  it('should be logined', () => {
    const loginState: LoginType = {
      username: '1212',
      password: '121212',
      remember: true,
    }
    login(loginState).then((res: any) => {
      expect(res.data.message).toEqual('登录成功')
    }).catch(err => {
      expect(err.data.message).toEqual('登录失败')
    })
  })
  it('should not be logined', () => {
    const loginState: LoginType = {
      username: '',
      password: '121212',
      remember: true,
    }
    login(loginState).then((res: any) => {
      expect(res.data.message).toEqual('登录成功')
    }).catch(err => {
      expect(err.data.message).toEqual('登录失败')
    })
  })
})