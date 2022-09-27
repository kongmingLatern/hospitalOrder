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
export interface DoctorType {
  doctorId: string | number;
  doctorName: string;
  doctorAge: number;
  rid: string,
  position: string;
  info: string,
  limitCount: number
}
export interface OrderListType {
  id: string;
  name: string;
  age: number;
  order: string;
  dateTime: string;
}
export interface RoomType {
  id: string;
  name: string;
  room: string
}
export interface UserType {
  uid?: string;
  userName: string,
  age: number | undefined,
  password: string,
  realName: string,
  cancelCount?: number,
  isAllow?: number,
  isAuth: number | undefined
}
