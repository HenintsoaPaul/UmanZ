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

describe('TwoFactorSetup', () => {
  const email = 'test@example.com'
  // Using the value from nuxt.config.ts which seems to be picked up by mountSuspended
  const defaultApiUrl = '/api'

  beforeEach(() => {
    vi.clearAllMocks()
  })

  it('should render the component with activation button when MFA is disabled', async () => {
    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    expect(wrapper.text()).toContain('Authentification à deux facteurs')
    expect(wrapper.find('button').text()).toContain('Activer le 2FA')
  })

  it('should start setup flow when clicking "Activer le 2FA"', async () => {
    mockSetupMfa.mockResolvedValueOnce({
      secret: 'ABCDEF123456',
      qrCodeUri: 'otpauth://totp/UmanZ:test@example.com?secret=ABCDEF123456'
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
    // Wait for the UI to update
    await wrapper.vm.$nextTick()
    
    expect(wrapper.text()).toContain('Scannez ce code QR')
    expect(wrapper.text()).toContain('ABCDEF123456')
  })

  it('should confirm setup and show scratch codes', async () => {
    // Setup state manually or via button click
    mockSetupMfa.mockResolvedValueOnce({
      secret: 'ABCDEF123456',
      qrCodeUri: 'otpauth://totp/UmanZ:test@example.com?secret=ABCDEF123456'
    })
    
    mockConfirmMfa.mockResolvedValueOnce({
      scratchCodes: ['CODE-1', 'CODE-2', 'CODE-3', 'CODE-4']
    })

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    // 1. Click Activate
    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()

    // 2. Fill code
    const input = wrapper.find('input')
    await input.setValue('123456')

    // 3. Click Confirm
    const confirmButton = wrapper.findAll('button').find(b => b.text().includes('Confirmer'))
    await confirmButton?.trigger('click')

    expect(mockConfirmMfa).toHaveBeenCalledWith(email, 123456, defaultApiUrl)
    await wrapper.vm.$nextTick()

    expect(wrapper.text()).toContain('Authentification à deux facteurs activée avec succès')
    expect(wrapper.text()).toContain('CODE-1')
    expect(wrapper.text()).toContain('CODE-4')
    expect(wrapper.emitted('mfa-updated')).toBeTruthy()
  })

  it('should show error message if setup fails', async () => {
    mockSetupMfa.mockRejectedValueOnce(new Error('Network error'))

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()

    expect(wrapper.text()).toContain("Erreur lors de l'initialisation du 2FA")
  })
})
