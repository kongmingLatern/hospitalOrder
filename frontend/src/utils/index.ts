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
    if (key === 'cancelCount') {
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