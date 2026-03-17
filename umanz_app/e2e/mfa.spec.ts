import { test, expect } from '@playwright/test';
import axios from 'axios';

test.describe('Email MFA Login', () => {
  const email = 'test@example.com';
  const password = 'password123'; // This should match a user in your seeds or created in the test

  test('should login successfully using email MFA', async ({ page }) => {
    // 1. Go to login page
    await page.goto('/login');

    // 2. Perform initial login
    await page.fill('input[type="email"]', email);
    await page.fill('input[type="password"]', password);
    await page.click('button[type="submit"]');

    // 3. Wait for MFA step
    await expect(page.locator('text=L\'authentification à deux facteurs est activée')).toBeVisible();
    await expect(page.locator('text=Entrez le code qui vous a été envoyé par email')).toBeVisible();

    // 4. Fetch code from MailHog
    // We wait a bit for the email to arrive
    await page.waitForTimeout(2000);
    
    const mailhogUrl = process.env.MAILHOG_URL || 'http://localhost:8025/api/v2/messages';
    const response = await axios.get(mailhogUrl);
    const messages = response.data.items;
    
    expect(messages.length).toBeGreaterThan(0);
    
    // Find the latest message for our user
    const latestMessage = messages.find((m: any) => 
      m.Content.Headers.To[0].includes(email) && 
      m.Content.Headers.Subject[0].includes('Votre code de vérification')
    );
    
    expect(latestMessage).toBeDefined();
    
    // Extract code using regex
    const body = latestMessage.Content.Body;
    const codeMatch = body.match(/\d{6}/);
    expect(codeMatch).not.toBeNull();
    const mfaCode = codeMatch[0];

    // 5. Enter MFA code
    await page.fill('input[placeholder="000000"]', mfaCode);
    await page.click('button:has-text("Vérifier le code")');

    // 6. Verify successful login (redirection to dashboard)
    await expect(page).toHaveURL('/');
    await expect(page.locator('text=Tableau de bord')).toBeVisible();
  });
});
