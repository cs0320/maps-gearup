import Cookies from "js-cookie";

// TODO: FIRESTORE PART 2:
// - Fill in these functions to add cookie functionality to the firebase login.

export function addLoginCookie(uid: string): void {
  Cookies.set("uid", uid);
  // TODO: fill out!
}

export function removeLoginCookie(): void {
  Cookies.remove("uid");
  // TODO: fill out!
}

export function getLoginCookie(): string | undefined {
  const loginCookie = Cookies.get("uid");
  return loginCookie;
  // TODO: fill out!
  return undefined;
}
