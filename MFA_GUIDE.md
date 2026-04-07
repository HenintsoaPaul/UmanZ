# Beginner's Guide to Multi-Factor Authentication (MFA) in UmanZ

Multi-Factor Authentication (MFA) adds an extra layer of security to your UmanZ account. Instead of relying only on your password, MFA requires a second form of verification to ensure it's really you logging in.

---

## 🛠 Prerequisites

Before you begin, you will need to install an **Authenticator App** on your smartphone. We recommend:
*   **Google Authenticator** (Available on iOS and Android)
*   **Authy** (Available on iOS and Android)
*   **Microsoft Authenticator** (Available on iOS and Android)

---

## 🚀 Step-by-Step Setup

### Step 1: Access Security Settings
1. Log in to your UmanZ account.
2. Navigate to your **Profile** or **Security** settings.
3. Look for the **Authentification à deux facteurs (2FA)** section.

### Step 2: Enable MFA
1. Click the **Activer le 2FA** (Enable 2FA) button.
2. A **QR Code** and a **Secret Key** will appear on your screen.

### Step 3: Link Your Authenticator App
1. Open your Authenticator App on your phone.
2. Choose **"Add an account"** or the **"+"** icon.
3. Select **"Scan a QR code"** and point your phone's camera at the QR code displayed on the UmanZ app.
4. *Alternatively:* If you cannot scan the QR code, select **"Enter a setup key"** and type in the **Secret Key** provided on the screen.

### Step 4: Verify and Confirm
1. Once linked, your app will show a 6-digit code for "UmanZ". This code changes every 30 seconds.
2. Enter the current 6-digit code into the verification box on the UmanZ setup screen.
3. Click **Confirmer** (Confirm).

---

## ⚠️ Important: Save Your Recovery Codes

After successful activation, you will be presented with a list of **Recovery Codes** (also known as Scratch Codes).

> [!IMPORTANT]
> **Copy and save these codes in a safe place!**
> If you lose your phone or cannot access your Authenticator App, these codes are the ONLY way to regain access to your account.

---

## 🔑 How to Log In with MFA

Next time you log in to UmanZ:
1. Enter your **Email** and **Password** as usual.
2. You will be prompted for your MFA code.
3. Open your Authenticator App, find the UmanZ code, and enter it.
4. Click **Verify** to complete your login.

---

## ❓ Frequently Asked Questions

**What if I lose my phone?**
Use one of the **Recovery Codes** you saved during setup. If you haven't saved them, please contact UmanZ support.

**Can I disable MFA?**
Yes, you can disable MFA from your Security settings, though we strongly recommend keeping it active to protect your data.

---

## 🛠 Troubleshooting

**QR Code not scanning?**
*   Ensure your screen brightness is turned up.
*   Clean your phone's camera lens.
*   Try moving your phone closer or further away from the screen.
*   If it still doesn't work, use the **Manual Setup** option by entering the **Secret Key**.

**"Invalid Secret" or "Unallowed Characters"?**
*   Make sure you are copying the **Secret Key** exactly as shown, without any extra spaces.
*   The key should only contain letters (A-Z) and numbers (2-7).
*   If you see symbols like `/`, `+`, or `=`, it means there might be a system error. Please refresh and try again.
