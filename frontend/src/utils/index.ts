import type { VueWrapper } from "@vue/test-utils/dist/vueWrapper";

export function randomString() {
  let len = 32;
  let chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
  let maxPos = chars.length;
  let character = '';
  for (let i = 0; i < len; i++) {
    character += chars.charAt(Math.floor(Math.random() * maxPos))
  }
  return character;
}

export function formatData(data: Record<string, any>[]) {
  return data.map(item => {
    return {
      ...item,
      isCancel: item.isCancel ? '是' : '否',
      isFinish: item.isFinish ? '是' : '否',
    }
  })
};

export function formatObject(data: Record<string, any>) {
  for (const key in data) {
    if (key === 'cancelCount' || key === 'age') {
      continue;
    }
    if (data[key] === 0) {
      data[key] = '否'
    } else if (data[key] === 1) {
      data[key] = '是'
    }
  }
  return data
};
export function hasOwnProperty<T, K extends PropertyKey>(
  obj: T,
  prop: K
): obj is T & Record<K, unknown> {
  return Object.prototype.hasOwnProperty.call(obj, prop);
}

export function getStartEndTime(num = 0) {
  // 一天的毫秒数
  const MillisecondsADay = 24 * 60 * 60 * 1000 * num
  // 今日开始时间戳
  const todayStartTime = new Date(new Date().setHours(0, 0, 0, 0)).getTime()
  // 今日结束时间戳
  const todayEndTime = new Date(new Date().setHours(23, 59, 59, 999)).getTime()
  // 昨日开始时间戳
  const yesterdayStartTime = todayStartTime - MillisecondsADay
  // 昨日结束时间戳
  const yesterdayEndTime = todayEndTime - MillisecondsADay
  return [
    yesterdayStartTime,
    yesterdayEndTime
  ]
}

export function formatDate(Unix: any, formate = 'Y-m-d h:i:s', complement = true, alike = false) {
  let current = new Date(Date.parse(new Date() as any));
  let current_year = current.getFullYear();
  let current_month = current.getMonth() + 1;
  let current_date = current.getDate();
  let timestamp = new Date(Unix * 1000);
  let year = timestamp.getFullYear();
  let month = timestamp.getMonth() + 1;
  let date = timestamp.getDate();
  let hour = timestamp.getHours();
  let minute = timestamp.getMinutes();
  let second = timestamp.getSeconds();
  let month_str: string | number = ''
  let date_str: string | number = ''
  let hour_str: string | number = ''
  let minute_str: string | number = ''
  let second_str: string | number = ''

  if (alike && current_year === year) {
    formate = formate.replace(/Y-/g, '');
  } else {
  }
  if (alike && current_year === year && current_month === month && current_date === date) {
    formate = formate.replace(/m-d /g, '');
  }
  if (complement) {
    month_str = month < 10 ? '0' + month : month;
    date_str = date < 10 ? '0' + date : date;
    hour_str = hour < 10 ? '0' + hour : hour;
    minute_str = minute < 10 ? '0' + minute : minute;
    second_str = second < 10 ? '0' + second : second;
  }
  formate = formate.replace(/Y/g, String(year));
  formate = formate.replace(/m/g, String(month_str));
  formate = formate.replace(/d/g, String(date_str));
  formate = formate.replace(/h/g, String(hour_str));
  formate = formate.replace(/i/g, String(minute_str));
  formate = formate.replace(/s/g, String(second_str));
  return formate;
}

export function isNotEmpty(data: any): boolean {
  if (data === null || data === undefined || data === '') {
    return false
  }
  return true
}
export const findTestWrapper = (wrapper: VueWrapper<any>, tag: string): any => {
  return wrapper.findAll(`[data-test="${tag}"]`)
}
