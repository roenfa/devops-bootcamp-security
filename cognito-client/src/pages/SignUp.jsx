import React from "react";

import FormSignup from "../components/Forms/FormSignup/FormSignUp";
import SignCard from "../components/Forms/FormCard/SignCard";

export default function SignUp() {
    return (
        <div className="sign-in-and-sign-up">
        <SignCard children={<FormSignup />} />
        </div>
    );
    }
    