import { useLoaderData, useOutlet, Await } from "react-router-dom";
import { AuthProvider } from "../authentication/AuthProvider";

export const AuthLayout = () => {
  const outlet = useOutlet();

  const { userPromise } = useLoaderData();

  return (
      <Await
        resolve={userPromise}
        errorElement={<h1>Something went wrong!</h1>}
        children={(user) => (
            <AuthProvider userData={user}>{outlet}</AuthProvider>
        )}
      />
  );
};