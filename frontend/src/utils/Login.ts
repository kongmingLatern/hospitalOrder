export default class LoginState {
  #uid: string = ''
  #username: string = ''
  #isAuth: boolean = false

  constructor(uid: string, username: string, isAuth: boolean) {
    this.#uid = uid
    this.#username = username
    this.#isAuth = isAuth
  }

  get uid() {
    return this.#uid
  }

  get username() {
    return this.#username
  }

  get isAuth() {
    return this.#isAuth
  }


  set uid(uid: string) {
    this.#uid = uid
  }

  set username(username: string) {
    this.#username = username
  }

  set isAuth(isAuth: boolean) {
    this.#isAuth = isAuth
  }


  setLocalStorage() {
    localStorage.setItem('uid', this.#uid)
    localStorage.setItem('username', this.#username)
    localStorage.setItem('isAuth', this.#isAuth ? '1' : '0')
  }

  getLocalStorage(key: string): string | null {
    return localStorage.getItem(key)
  }

}