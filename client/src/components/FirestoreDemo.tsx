import { useEffect, useState } from "react";
import { addWord, getWords } from "../utils/api";
import { getLoginCookie } from "../utils/cookie";

export default function FirestoreDemo() {
  const [words, setWords] = useState<string[]>([]);

  const USER_ID = getLoginCookie() || "";

  useEffect(() => {
    getWords().then((data) => {
      setWords(data.words);
    });
  }, []);

  const addFavoriteWord = async (newWord: string) => {
    // - update the client words state to include the new word
    setWords([...words, newWord])
    // - query the backend to add the new word to the database
    await addWord(newWord);
  };

  return (
    <div className="firestore-demo">
      <h2>Firestore Demo</h2>
      {/* adding new words: */}
      <label htmlFor="new-word">Add a favorite word:</label>
      <input id="new-word" type="text" />
      <button
        onClick={() => {
          const newWord = (
            document.getElementById("new-word") as HTMLInputElement
          ).value;
          addFavoriteWord(newWord);
        }}
      >
        Add
      </button>
      {/* list of words from db: */}
      <p>
        <i>Favorite words for user {USER_ID}:</i>
      </p>
      <ul>
        {words.map((word, index) => (
          <p key={index}>{word}</p>
        ))}
      </ul>
    </div>
  );
}