import { expect, test } from "@playwright/test";

/**
  The general shapes of tests in Playwright Test are:
    1. Navigate to a URL
    2. Interact with the page
    3. Assert something about the page against your expectations
  Look for this pattern in the tests below!
 */

// If you needed to do something before every test case...
test.beforeEach("add spoof uid cookie to browser", async ({ context }) => {
  // TODO: FIRESTORE PART 5:
  // - Add "uid" cookie to the browser context
  // see: https://playwright.dev/docs/api/class-browsercontext#browser-context-add-cookies
  await context.addCookies([
    {
      name: "uid",
      value: "mock-user-id",
      path: "/",
    },
  ]);

  // Possibly wipe everything for this spoofed UID in the database.
  

});

/**
 * Don't worry about the "async" yet. We'll cover it in more detail
 * for the next sprint. For now, just think about "await" as something
 * you put before parts of your test that might take time to run,
 * like any interaction with the page.
 */
test("on page load, I see the gearup screen and skip auth.", async ({ page }) => {
  // Notice: http, not https! Our front-end is not set up for HTTPs.
  await page.goto("http://localhost:8000/");
  await expect(page.getByLabel("Gearup Title")).toBeVisible();
});