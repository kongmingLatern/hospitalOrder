export interface LoginType {
  username: string;
  password: string;
  remember: boolean;
}
export interface RegistType {
  uid: string,
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
  doctorAge?: number;
  rid: string,
  position?: string;
  info: string,
  limitCount?: number
  img?: string
}
export interface OrderListType {
  orderId: string;
  uid?: string,
  realName: string,
  rId: string;
  rname: string;
  doctorId?: string,
  doctorName: string;
  orderTime: string;
  isCancel: number | string,
  isFinish: number | string
}
export interface RoomType {
  rid: string;
  rname: string;
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
export interface TabType {
  rid: string;
  rname: string;
  path?: string
}
export interface RoomInfoType {
  roomId: string
  name: string,
  description: string
  img: string
}