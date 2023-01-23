import { useState } from "react";

export const useLocalStorage = (properties) => {
  const [storedValue, setStoredValue] = useState(() => {
    try {
      const value = window.localStorage.getItem(properties.key);
      if (value) {
        return JSON.parse(value);
      } else {
        window.localStorage.setItem(properties.key, JSON.stringify(properties.value));
        return properties.value;
      }
    } catch (err) {
      return properties.value;
    }
  });

  const setValue = (newValue) => {
    try {
      window.localStorage.setItem(properties.key, JSON.stringify(newValue.value));
    } catch (err) {}
    setStoredValue(newValue.value);
  };
  
  return [storedValue, setValue];
};