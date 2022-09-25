export interface LoginType {
  username: string;
  password: string;
  remember: boolean;
}
export interface RegistType {
  userName: string;
  age: number | string,
  password: string;
  realName: string
  cancelCount?: number,
  isAllow?: number
}