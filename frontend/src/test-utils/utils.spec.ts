import { describe, it, expect } from 'vitest'
import { randomString } from '../utils';
describe('randomString', () => {
  it('Random string length = 32', () => {
    expect(randomString()).toMatchInlineSnapshot('"TBfsJX2MJimRsKpN3hKjJek4eCSf2Y5E"')
  })
})