import { describe, it, expect, vi, beforeEach } from 'vitest'
import { useAuth } from './useAuth'

// Mock Nuxt globals/composables
const mockFetch = vi.fn()
vi.stubGlobal('$fetch', mockFetch)

// Use stubGlobal for useRuntimeConfig instead of mocking #app
// This avoids breaking other plugins that might use #app exports like defineNuxtPlugin
vi.stubGlobal('useRuntimeConfig', () => ({
  public: {
    apiUrl: '/api'
  }
}))

// Mock navigateTo if needed (it might be used in logout)
vi.stubGlobal('navigateTo', vi.fn())

describe('useAuth', () => {
  const auth = useAuth()
  const email = 'test@example.com'
  const apiUrl = 'https://api.test.com'

  beforeEach(() => {
    vi.clearAllMocks()
    // Clear localStorage for setSession/getSession tests
    if (typeof localStorage !== 'undefined') {
        localStorage.clear()
    }
  })

  describe('setupMfa', () => {
    it('should call the setup endpoint and return secret and qrCodeUri', async () => {
      const mockResult = { secret: 'secret123', qrCodeUri: 'otpauth://...' }
      mockFetch.mockResolvedValueOnce(mockResult)

      const result = await auth.setupMfa(email, apiUrl)

      expect(mockFetch).toHaveBeenCalledWith(`${apiUrl}/auth/mfa/setup`, {
        method: 'POST',
        body: { email }
      })
      expect(result).toEqual(mockResult)
    })
  })

  describe('confirmMfa', () => {
    it('should call the confirm endpoint and return scratch codes', async () => {
      const code = 123456
      const mockResult = { scratchCodes: ['code1', 'code2'] }
      mockFetch.mockResolvedValueOnce(mockResult)

      const result = await auth.confirmMfa(email, code, apiUrl)

      expect(mockFetch).toHaveBeenCalledWith(`${apiUrl}/auth/mfa/confirm`, {
        method: 'POST',
        body: { email, code }
      })
      expect(result).toEqual(mockResult)
    })
  })

  describe('verifyMfa', () => {
    it('should call the verify endpoint with 2FA code', async () => {
      const code = 123456
      const mockResponse = { idTalent: 1, email, admin: false }
      mockFetch.mockResolvedValueOnce(mockResponse)

      const result = await auth.verifyMfa(email, code, undefined, apiUrl)

      expect(mockFetch).toHaveBeenCalledWith(`${apiUrl}/auth/mfa/verify`, {
        method: 'POST',
        body: { email, code, scratchCode: undefined }
      })
      expect(result).toEqual(mockResponse)
    })

    it('should call the verify endpoint with scratch code', async () => {
      const scratchCode = 'scratch-123'
      const mockResponse = { idTalent: 1, email, admin: false }
      mockFetch.mockResolvedValueOnce(mockResponse)

      const result = await auth.verifyMfa(email, undefined, scratchCode, apiUrl)

      expect(mockFetch).toHaveBeenCalledWith(`${apiUrl}/auth/mfa/verify`, {
        method: 'POST',
        body: { email, code: undefined, scratchCode }
      })
      expect(result).toEqual(mockResponse)
    })

    it('should use apiUrl from runtime config if not provided', async () => {
      const code = 123456
      mockFetch.mockResolvedValueOnce({})

      await auth.verifyMfa(email, code)

      expect(mockFetch).toHaveBeenCalledWith('/api/auth/mfa/verify', expect.any(Object))
    })
  })
})
