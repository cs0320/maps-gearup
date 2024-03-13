import { expect, test } from "@playwright/test";
import { clearUser } from "../../src/utils/api";

/**
  The general shapes of tests in Playwright Test are:
    1. Navigate to a URL
    2. Interact with the page
    3. Assert something about the page against your expectations
  Look for this pattern in the tests below!
 */

const SPOOF_UID = "mock-user-id";

test.beforeEach(
  "add spoof uid cookie to browser",
  async ({ context, page }) => {
    // - Add "uid" cookie to the browser context
    await context.addCookies([
      {
        name: "uid",
        value: SPOOF_UID,
        url: "http://localhost:8000",
      },
    ]);

    // wipe everything for this spoofed UID in the database.
    await clearUser(SPOOF_UID);
  }
);

/**
 * Don't worry about the "async" yet. We'll cover it in more detail
 * for the next sprint. For now, just think about "await" as something
 * you put before parts of your test that might take time to run,
 * like any interaction with the page.
 */
test("on page load, I see the gearup screen and skip auth.", async ({
  page,
}) => {
  // Notice: http, not https! Our front-end is not set up for HTTPs.
  await page.goto("http://localhost:8000/");
  await expect(page.getByLabel("Gearup Title")).toBeVisible();
  // <i> with aria-label favorite-words-header should include the SPOOF_UID
  await expect(page.getByLabel("favorite-words-header")).toContainText(
    SPOOF_UID
  );
});

test("I can add a word to my favorites list", async ({ page }) => {
  await page.goto("http://localhost:8000/");
  // - get the <p> elements inside the <ul> with aria-label="favorite-words"
  const favoriteWords = await page.$$('[aria-label="favorite-words"] p');
  await expect(favoriteWords).toHaveLength(0);

  await page.fill('[aria-label="word-input"]', "hello");
  await page.click('[aria-label="add-word-button"]');

  const favoriteWordsAfter = await page.$$('[aria-label="favorite-words"] p');
  await expect(favoriteWordsAfter).toHaveLength(1);

  // .. and this works on refresh
  await page.reload();
  // wait a small amount of time for the backend fetch to update the site state.
  await page.waitForTimeout(1000);
  const favoriteWordsAfterReload = await page.$$(
    '[aria-label="favorite-words"] p'
  );
  await expect(favoriteWordsAfterReload).toHaveLength(1);
});
