import { describe, it, expect } from 'vitest'
import { formatData, formatObject, getStartEndTime, randomString, formatDate } from '../utils';
describe('randomString', () => {
  it.skip('Random string length = 32', () => {
    expect(randomString()).toMatchInlineSnapshot('"TBfsJX2MJimRsKpN3hKjJek4eCSf2Y5E"')
  })
})

describe('format data', () => {
  it('format dataSource', () => {
    const dataSource = [
      {
        isCancel: 0,
        isFinish: 0,
      }, {
        isCancel: 1,
        isFinish: 1,
      }]
    expect(formatData(dataSource)).toEqual([
      {
        isCancel: '否',
        isFinish: '否',
      }, {
        isCancel: '是',
        isFinish: '是',
      }]
    )
  });

  it('format object1', () => {
    const dataSource =
    {
      isCancel: 0,
      isFinish: 0,
    }
    expect(formatObject(dataSource)).toEqual(
      {
        isCancel: '否',
        isFinish: '否',
      })
  });
  it('format object2', () => {
    const dataSource =
    {
      isAuth: 0,
      isAllow: 0,
    }
    expect(formatObject(dataSource)).toEqual(
      {
        isAuth: '否',
        isAllow: '否',
      })
  });
  it('format object3', () => {
    const dataSource =
    {
      cancelCount: 0,
      isAuth: 0,
      isAllow: 0,
    }
    expect(formatObject(dataSource)).toEqual(
      {
        cancelCount: 0,
        isAuth: '否',
        isAllow: '否',
      })
  });
})

describe('test date', () => {
  it.skip('should get tomorrow date', () => {
    const result = getStartEndTime(-1)
    expect(result[0]).toMatchInlineSnapshot('1664899200')
  })

  it.skip('should get current date', () => {
    const selectId = 1
    let time: number
    if (selectId === 1) {
      time = getStartEndTime(-1)[0] + 8 * 60 * 60 * 1000
    } else {
      time = getStartEndTime(-1)[0] + 13 * 60 * 60 * 1000
    }
    expect(time).toMatchInlineSnapshot('"2023-09-03 08:00:00"')
    expect(formatDate(time)).toMatchInlineSnapshot('')
  })
});