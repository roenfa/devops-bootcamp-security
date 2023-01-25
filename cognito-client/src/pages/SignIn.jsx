import React from "react";

import FormSignin from "../components/Forms/FormSignIn/FormSignIn";
import SignCard from "../components/Forms/FormCard/SignCard";

export default function SignIn() {
    return (
        <div className="">
        <SignCard children={<FormSignin />} />
        </div>
    );
    }
    