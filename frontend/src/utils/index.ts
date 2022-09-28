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