import Cookies from "js-cookie";

// TODO: FIRESTORE PART 1:
// - Fill in these functions to add cookie functionality to the firebase login.

const uid_key = 'uid'

export function addLoginCookie(uid: string): void {
  // TODO: fill out!
  Cookies.set(uid_key, uid)
}

export function removeLoginCookie(): void {
  // TODO: fill out!
  Cookies.remove(uid_key)
}

export function getLoginCookie(): string | undefined {
  // TODO: fill out!
  return Cookies.get(uid_key) 
}
