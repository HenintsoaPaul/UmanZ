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

describe('TwoFactorSetup - Email Based', () => {
  const email = 'test@example.com'
  const defaultApiUrl = '/api'

  beforeEach(() => {
    vi.clearAllMocks()
    Object.defineProperty(navigator, 'clipboard', {
      value: {
        writeText: vi.fn().mockResolvedValue(undefined),
      },
      configurable: true,
    })
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

  it('should start email-based setup flow when clicking "Activer le 2FA"', async () => {
    mockSetupMfa.mockResolvedValueOnce({
      message: 'Code envoyé'
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
    
    expect(wrapper.text()).toContain('Vérifiez votre boîte mail')
    expect(wrapper.text()).toContain('Nous avons envoyé un code de vérification à l\'adresse ' + email)
  })

  it('should confirm setup and show scratch codes', async () => {
    mockSetupMfa.mockResolvedValueOnce({
      message: 'Code envoyé'
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
    const buttons = wrapper.findAll('button')
    const confirmButton = buttons.find(b => b.text().includes('Confirmer'))
    await confirmButton?.trigger('click')

    expect(mockConfirmMfa).toHaveBeenCalledWith(email, 123456, defaultApiUrl)
    await wrapper.vm.$nextTick()

    expect(wrapper.text()).toContain('Authentification à deux facteurs activée avec succès')
    expect(wrapper.text()).toContain('CODE-1')
    expect(wrapper.text()).toContain('CODE-4')
    expect(wrapper.emitted('mfa-updated')).toBeTruthy()
  })

  it('should show error message if verification code is not 6 digits', async () => {
    mockSetupMfa.mockResolvedValueOnce({ message: 'Code envoyé' })

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()

    const input = wrapper.find('input')
    await input.setValue('123')

    const confirmButton = wrapper.findAll('button').find(b => b.text().includes('Confirmer'))
    await confirmButton?.trigger('click')

    expect(wrapper.text()).toContain('Veuillez entrer un code valide à 6 chiffres')
    expect(mockConfirmMfa).not.toHaveBeenCalled()
  })

  it('should show error message if confirmMfa fails', async () => {
    mockSetupMfa.mockResolvedValueOnce({ message: 'Code envoyé' })
    mockConfirmMfa.mockRejectedValueOnce(new Error('Invalid code'))

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()

    await wrapper.find('input').setValue('123456')
    const confirmButton = wrapper.findAll('button').find(b => b.text().includes('Confirmer'))
    await confirmButton?.trigger('click')

    await wrapper.vm.$nextTick()
    expect(wrapper.text()).toContain('Code invalide. Veuillez réessayer.')
  })

  it('should cancel setup flow when clicking cancel button', async () => {
    mockSetupMfa.mockResolvedValueOnce({ message: 'Code envoyé' })

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()
    
    expect(wrapper.text()).toContain('Vérifiez l\'activation')

    const cancelButton = wrapper.findAll('button').find(b => b.text().includes('Annuler'))
    await cancelButton?.trigger('click')
    await wrapper.vm.$nextTick()

    expect(wrapper.text()).not.toContain('Vérifiez l\'activation')
    expect(wrapper.find('button').text()).toContain('Activer le 2FA')
  })

  it('should render currently enabled state and show disable message', async () => {
    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: true
      }
    })

    expect(wrapper.text()).toContain('Le 2FA est actuellement activé sur votre compte')
    
    const disableButton = wrapper.find('button')
    expect(disableButton.text()).toContain('Désactiver le 2FA')
    
    await disableButton.trigger('click')
    await wrapper.vm.$nextTick()
    
    expect(wrapper.text()).toContain("La désactivation du 2FA n'est pas encore implémentée")
  })

  it('should copy scratch codes to clipboard', async () => {
    mockSetupMfa.mockResolvedValueOnce({ message: 'Code envoyé' })
    mockConfirmMfa.mockResolvedValueOnce({
      scratchCodes: ['CODE-1', 'CODE-2']
    })

    const wrapper = await mountSuspended(TwoFactorSetup, {
      props: {
        email,
        mfaEnabled: false
      }
    })

    await wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()
    await wrapper.find('input').setValue('123456')
    await (wrapper.findAll('button').find(b => b.text().includes('Confirmer')))?.trigger('click')
    await wrapper.vm.$nextTick()

    const copyButton = wrapper.findAll('button').find(b => b.text().includes('Copier les codes'))
    await copyButton?.trigger('click')

    expect(navigator.clipboard.writeText).toHaveBeenCalledWith('CODE-1\nCODE-2')
  })
})
