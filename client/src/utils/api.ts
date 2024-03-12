import { getLoginCookie } from "./cookie";

const HOST = "http://localhost:3232";

async function queryAPI(
  endpoint: string,
  query_params: Record<string, string>
) {
  // query_params is a dictionary of key-value pairs that gets added to the URL as query parameters
  // e.g. { foo: "bar", hell: "o" } becomes "?foo=bar&hell=o"
  const paramsString = new URLSearchParams(query_params).toString();
  const url = `${HOST}/${endpoint}?${paramsString}`;
  const response = await fetch(url);
  if (!response.ok) {
    console.error(response.status, response.statusText);
  }
  return response.json();
}

// TODO: FIRESTORE PART 3:
// - Figure out what the query params should be for each of these functions (see comment above)

// Question to review TA: is this even a good exercise ??? Is there a better learning portion for this part of the full-stack integration?
// .. tryna balance the learning and tedium, knowing also that a lot of this will be async. Also want to give them
// good frontend-backend integration stencil because this is the last project before their final

export async function addWord(word: string) {
  return await queryAPI("add-word", {
    // TODO: fill out!
    uid: getLoginCookie() || "",
    word: word,
  });
}

export async function getWords() {
  return await queryAPI("list-words", {
    // TODO: fill out!
    uid: getLoginCookie() || "",
  });
}
