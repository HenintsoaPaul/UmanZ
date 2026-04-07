import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mountSuspended } from '@nuxt/test-utils/runtime'
import TwoFactorSetup from './TwoFactorSetup.vue'

// Mock useAuth
const mockSetupMfa = vi.fn()
const mockConfirmMfa = vi.fn()
const mockGetSession = vi.fn(() => null)
const mockLogin = vi.fn()
const mockSetSession = vi.fn()
const mockLogout = vi.fn()

vi.mock('~/features/auth/useAuth', () => ({
  useAuth: () => ({
    setupMfa: mockSetupMfa,
    confirmMfa: mockConfirmMfa,
    getSession: mockGetSession,
    login: mockLogin,
    setSession: mockSetSession,
    logout: mockLogout,
    verifyMfa: vi.fn()
  })
}))

// Mock navigateTo
vi.stubGlobal('navigateTo', vi.fn())

// Disabling QRCode tests as requested
describe.skip('TwoFactorSetup - QRCode Based', () => {
  const email = 'test@example.com'
  const defaultApiUrl = '/api'

  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('should start TOTP-based setup flow when clicking "Activer le 2FA"', async () => {
    mockSetupMfa.mockResolvedValueOnce({
      qrCodeUri: 'otpauth://totp/UmanZ:test@example.com?secret=ABCDEF123456',
      secret: 'ABCDEF123456'
    })

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    const button = wrapper.find('button')
    await button.trigger('click')

    expect(mockSetupMfa).toHaveBeenCalledWith(email, defaultApiUrl)
    await wrapper.vm.$nextTick()
    
    expect(wrapper.text()).toContain('Scannez ce code QR')
    expect(wrapper.text()).toContain('Secret: ABCDEF123456')
  })
})
